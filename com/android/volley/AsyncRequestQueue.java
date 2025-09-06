package com.android.volley;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AsyncRequestQueue extends RequestQueue {
    public static class Builder {
        private AsyncCache mAsyncCache;
        private Cache mCache;
        private ExecutorFactory mExecutorFactory;
        private final AsyncNetwork mNetwork;
        private ResponseDelivery mResponseDelivery;

        public Builder(AsyncNetwork asyncNetwork0) {
            this.mAsyncCache = null;
            this.mCache = null;
            this.mExecutorFactory = null;
            this.mResponseDelivery = null;
            if(asyncNetwork0 == null) {
                throw new IllegalArgumentException("Network cannot be null");
            }
            this.mNetwork = asyncNetwork0;
        }

        public AsyncRequestQueue build() {
            Cache cache0 = this.mCache;
            if(cache0 == null && this.mAsyncCache == null) {
                throw new IllegalArgumentException("You must set one of the cache objects");
            }
            if(cache0 == null) {
                this.mCache = new ThrowingCache(null);
            }
            if(this.mResponseDelivery == null) {
                this.mResponseDelivery = new ExecutorDelivery(new Handler(Looper.getMainLooper()));
            }
            if(this.mExecutorFactory == null) {
                this.mExecutorFactory = this.getDefaultExecutorFactory();
            }
            return new AsyncRequestQueue(this.mCache, this.mNetwork, this.mAsyncCache, this.mResponseDelivery, this.mExecutorFactory, null);
        }

        private ExecutorFactory getDefaultExecutorFactory() {
            return new ExecutorFactory() {
                @Override  // com.android.volley.AsyncRequestQueue$ExecutorFactory
                public ExecutorService createBlockingExecutor(BlockingQueue blockingQueue0) {
                    return this.getNewThreadPoolExecutor(4, "BlockingExecutor", blockingQueue0);
                }

                @Override  // com.android.volley.AsyncRequestQueue$ExecutorFactory
                public ExecutorService createNonBlockingExecutor(BlockingQueue blockingQueue0) {
                    return this.getNewThreadPoolExecutor(1, "Non-BlockingExecutor", blockingQueue0);
                }

                @Override  // com.android.volley.AsyncRequestQueue$ExecutorFactory
                public ScheduledExecutorService createNonBlockingScheduledExecutor() {
                    return new ScheduledThreadPoolExecutor(0, this.getThreadFactory("ScheduledExecutor"));
                }

                private ThreadPoolExecutor getNewThreadPoolExecutor(int v, String s, BlockingQueue blockingQueue0) {
                    return new ThreadPoolExecutor(0, v, 60L, TimeUnit.SECONDS, blockingQueue0, this.getThreadFactory(s));
                }

                private ThreadFactory getThreadFactory(String s) {
                    return new ThreadFactory() {
                        @Override
                        public Thread newThread(Runnable runnable0) {
                            Thread thread0 = Executors.defaultThreadFactory().newThread(runnable0);
                            thread0.setName("Volley-" + s);
                            return thread0;
                        }
                    };
                }
            };
        }

        public Builder setAsyncCache(AsyncCache asyncCache0) {
            this.mAsyncCache = asyncCache0;
            return this;
        }

        public Builder setCache(Cache cache0) {
            this.mCache = cache0;
            return this;
        }

        public Builder setExecutorFactory(ExecutorFactory asyncRequestQueue$ExecutorFactory0) {
            this.mExecutorFactory = asyncRequestQueue$ExecutorFactory0;
            return this;
        }

        public Builder setResponseDelivery(ResponseDelivery responseDelivery0) {
            this.mResponseDelivery = responseDelivery0;
            return this;
        }
    }

    class CacheParseTask extends RequestTask {
        Entry entry;
        long startTimeMillis;

        CacheParseTask(Request request0, Entry cache$Entry0, long v) {
            super(request0);
            this.entry = cache$Entry0;
            this.startTimeMillis = v;
        }

        @Override
        public void run() {
            this.mRequest.addMarker("cache-hit");
            Response response0 = this.mRequest.parseNetworkResponse(new NetworkResponse(200, this.entry.data, false, 0L, this.entry.allResponseHeaders));
            this.mRequest.addMarker("cache-hit-parsed");
            if(!this.entry.refreshNeeded(this.startTimeMillis)) {
                AsyncRequestQueue.this.getResponseDelivery().postResponse(this.mRequest, response0);
                return;
            }
            this.mRequest.addMarker("cache-hit-refresh-needed");
            this.mRequest.setCacheEntry(this.entry);
            response0.intermediate = true;
            if(!AsyncRequestQueue.this.mWaitingRequestManager.maybeAddToWaitingRequests(this.mRequest)) {
                AsyncRequestQueue.this.getResponseDelivery().postResponse(this.mRequest, response0, new Runnable() {
                    @Override
                    public void run() {
                        AsyncRequestQueue.this.sendRequestOverNetwork(CacheParseTask.this.mRequest);
                    }
                });
                return;
            }
            AsyncRequestQueue.this.getResponseDelivery().postResponse(this.mRequest, response0);
        }
    }

    class CachePutTask extends RequestTask {
        Response response;

        CachePutTask(Request request0, Response response0) {
            super(request0);
            this.response = response0;
        }

        @Override
        public void run() {
            if(AsyncRequestQueue.this.mAsyncCache != null) {
                String s = this.mRequest.getCacheKey();
                Entry cache$Entry0 = this.response.cacheEntry;
                com.android.volley.AsyncRequestQueue.CachePutTask.1 asyncRequestQueue$CachePutTask$10 = new OnWriteCompleteCallback() {
                    @Override  // com.android.volley.AsyncCache$OnWriteCompleteCallback
                    public void onWriteComplete() {
                        AsyncRequestQueue.this.finishRequest(CachePutTask.this.mRequest, CachePutTask.this.response, true);
                    }
                };
                AsyncRequestQueue.this.mAsyncCache.put(s, cache$Entry0, asyncRequestQueue$CachePutTask$10);
                return;
            }
            AsyncRequestQueue.this.getCache().put(this.mRequest.getCacheKey(), this.response.cacheEntry);
            AsyncRequestQueue.this.finishRequest(this.mRequest, this.response, true);
        }
    }

    class CacheTask extends RequestTask {
        CacheTask(Request request0) {
            super(request0);
        }

        @Override
        public void run() {
            if(this.mRequest.isCanceled()) {
                this.mRequest.finish("cache-discard-canceled");
                return;
            }
            this.mRequest.addMarker("cache-queue-take");
            if(AsyncRequestQueue.this.mAsyncCache != null) {
                String s = this.mRequest.getCacheKey();
                com.android.volley.AsyncRequestQueue.CacheTask.1 asyncRequestQueue$CacheTask$10 = new OnGetCompleteCallback() {
                    @Override  // com.android.volley.AsyncCache$OnGetCompleteCallback
                    public void onGetComplete(Entry cache$Entry0) {
                        AsyncRequestQueue.this.handleEntry(cache$Entry0, CacheTask.this.mRequest);
                    }
                };
                AsyncRequestQueue.this.mAsyncCache.get(s, asyncRequestQueue$CacheTask$10);
                return;
            }
            Entry cache$Entry0 = AsyncRequestQueue.this.getCache().get(this.mRequest.getCacheKey());
            AsyncRequestQueue.this.handleEntry(cache$Entry0, this.mRequest);
        }
    }

    public static abstract class ExecutorFactory {
        public abstract ExecutorService createBlockingExecutor(BlockingQueue arg1);

        public abstract ExecutorService createNonBlockingExecutor(BlockingQueue arg1);

        public abstract ScheduledExecutorService createNonBlockingScheduledExecutor();
    }

    class NetworkParseTask extends RequestTask {
        NetworkResponse networkResponse;

        NetworkParseTask(Request request0, NetworkResponse networkResponse0) {
            super(request0);
            this.networkResponse = networkResponse0;
        }

        @Override
        public void run() {
            Response response0 = this.mRequest.parseNetworkResponse(this.networkResponse);
            this.mRequest.addMarker("network-parse-complete");
            if(this.mRequest.shouldCache() && response0.cacheEntry != null) {
                if(AsyncRequestQueue.this.mAsyncCache != null) {
                    CachePutTask asyncRequestQueue$CachePutTask0 = new CachePutTask(AsyncRequestQueue.this, this.mRequest, response0);
                    AsyncRequestQueue.this.mNonBlockingExecutor.execute(asyncRequestQueue$CachePutTask0);
                    return;
                }
                AsyncRequestQueue.this.mBlockingExecutor.execute(new CachePutTask(AsyncRequestQueue.this, this.mRequest, response0));
                return;
            }
            AsyncRequestQueue.this.finishRequest(this.mRequest, response0, false);
        }
    }

    class NetworkTask extends RequestTask {
        NetworkTask(Request request0) {
            super(request0);
        }

        @Override
        public void run() {
            if(this.mRequest.isCanceled()) {
                this.mRequest.finish("network-discard-cancelled");
                this.mRequest.notifyListenerResponseNotUsable();
                return;
            }
            long v = SystemClock.elapsedRealtime();
            this.mRequest.addMarker("network-queue-take");
            AsyncRequestQueue.this.mNetwork.performRequest(this.mRequest, new OnRequestComplete() {
                @Override  // com.android.volley.AsyncNetwork$OnRequestComplete
                public void onError(VolleyError volleyError0) {
                    volleyError0.setNetworkTimeMs(SystemClock.elapsedRealtime() - v);
                    ParseErrorTask asyncRequestQueue$ParseErrorTask0 = new ParseErrorTask(AsyncRequestQueue.this, NetworkTask.this.mRequest, volleyError0);
                    AsyncRequestQueue.this.mBlockingExecutor.execute(asyncRequestQueue$ParseErrorTask0);
                }

                @Override  // com.android.volley.AsyncNetwork$OnRequestComplete
                public void onSuccess(NetworkResponse networkResponse0) {
                    NetworkTask.this.mRequest.addMarker("network-http-complete");
                    if(networkResponse0.notModified && NetworkTask.this.mRequest.hasHadResponseDelivered()) {
                        NetworkTask.this.mRequest.finish("not-modified");
                        NetworkTask.this.mRequest.notifyListenerResponseNotUsable();
                        return;
                    }
                    NetworkParseTask asyncRequestQueue$NetworkParseTask0 = new NetworkParseTask(AsyncRequestQueue.this, NetworkTask.this.mRequest, networkResponse0);
                    AsyncRequestQueue.this.mBlockingExecutor.execute(asyncRequestQueue$NetworkParseTask0);
                }
            });
        }
    }

    class ParseErrorTask extends RequestTask {
        VolleyError volleyError;

        ParseErrorTask(Request request0, VolleyError volleyError0) {
            super(request0);
            this.volleyError = volleyError0;
        }

        @Override
        public void run() {
            VolleyError volleyError0 = this.volleyError;
            AsyncRequestQueue.this.getResponseDelivery().postError(this.mRequest, volleyError0);
            this.mRequest.notifyListenerResponseNotUsable();
        }
    }

    static class ThrowingCache implements Cache {
        private ThrowingCache() {
        }

        ThrowingCache(com.android.volley.AsyncRequestQueue.1 asyncRequestQueue$10) {
        }

        @Override  // com.android.volley.Cache
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override  // com.android.volley.Cache
        public Entry get(String s) {
            throw new UnsupportedOperationException();
        }

        @Override  // com.android.volley.Cache
        public void initialize() {
            throw new UnsupportedOperationException();
        }

        @Override  // com.android.volley.Cache
        public void invalidate(String s, boolean z) {
            throw new UnsupportedOperationException();
        }

        @Override  // com.android.volley.Cache
        public void put(String s, Entry cache$Entry0) {
            throw new UnsupportedOperationException();
        }

        @Override  // com.android.volley.Cache
        public void remove(String s) {
            throw new UnsupportedOperationException();
        }
    }

    private static final int DEFAULT_BLOCKING_THREAD_POOL_SIZE = 4;
    private final AsyncCache mAsyncCache;
    private ExecutorService mBlockingExecutor;
    private final Object mCacheInitializationLock;
    private ExecutorFactory mExecutorFactory;
    private volatile boolean mIsCacheInitialized;
    private final AsyncNetwork mNetwork;
    private ExecutorService mNonBlockingExecutor;
    private ScheduledExecutorService mNonBlockingScheduledExecutor;
    private final List mRequestsAwaitingCacheInitialization;
    private final WaitingRequestManager mWaitingRequestManager;

    private AsyncRequestQueue(Cache cache0, AsyncNetwork asyncNetwork0, AsyncCache asyncCache0, ResponseDelivery responseDelivery0, ExecutorFactory asyncRequestQueue$ExecutorFactory0) {
        super(cache0, asyncNetwork0, 0, responseDelivery0);
        this.mWaitingRequestManager = new WaitingRequestManager(this);
        this.mRequestsAwaitingCacheInitialization = new ArrayList();
        this.mIsCacheInitialized = false;
        this.mCacheInitializationLock = new Object[0];
        this.mAsyncCache = asyncCache0;
        this.mNetwork = asyncNetwork0;
        this.mExecutorFactory = asyncRequestQueue$ExecutorFactory0;
    }

    AsyncRequestQueue(Cache cache0, AsyncNetwork asyncNetwork0, AsyncCache asyncCache0, ResponseDelivery responseDelivery0, ExecutorFactory asyncRequestQueue$ExecutorFactory0, com.android.volley.AsyncRequestQueue.1 asyncRequestQueue$10) {
        this(cache0, asyncNetwork0, asyncCache0, responseDelivery0, asyncRequestQueue$ExecutorFactory0);
    }

    @Override  // com.android.volley.RequestQueue
    void beginRequest(Request request0) {
        if(!this.mIsCacheInitialized) {
            Object object0 = this.mCacheInitializationLock;
            synchronized(object0) {
                if(!this.mIsCacheInitialized) {
                    this.mRequestsAwaitingCacheInitialization.add(request0);
                    return;
                }
            }
        }
        if(request0.shouldCache()) {
            if(this.mAsyncCache != null) {
                this.mNonBlockingExecutor.execute(new CacheTask(this, request0));
                return;
            }
            this.mBlockingExecutor.execute(new CacheTask(this, request0));
            return;
        }
        this.sendRequestOverNetwork(request0);
    }

    private void finishRequest(Request request0, Response response0, boolean z) {
        if(z) {
            request0.addMarker("network-cache-written");
        }
        request0.markDelivered();
        this.getResponseDelivery().postResponse(request0, response0);
        request0.notifyListenerResponseReceived(response0);
    }

    private static PriorityBlockingQueue getBlockingQueue() {
        return new PriorityBlockingQueue(11, new Comparator() {
            @Override
            public int compare(Object object0, Object object1) {
                return this.compare(((Runnable)object0), ((Runnable)object1));
            }

            public int compare(Runnable runnable0, Runnable runnable1) {
                if(runnable0 instanceof RequestTask) {
                    return runnable1 instanceof RequestTask ? ((RequestTask)runnable0).compareTo(((RequestTask)runnable1)) : 1;
                }
                return runnable1 instanceof RequestTask ? -1 : 0;
            }
        });
    }

    private void handleEntry(Entry cache$Entry0, Request request0) {
        if(cache$Entry0 == null) {
            request0.addMarker("cache-miss");
            if(this.mWaitingRequestManager.maybeAddToWaitingRequests(request0)) {
                return;
            }
            this.sendRequestOverNetwork(request0);
            return;
        }
        long v = System.currentTimeMillis();
        if(cache$Entry0.isExpired(v)) {
            request0.addMarker("cache-hit-expired");
            request0.setCacheEntry(cache$Entry0);
            if(!this.mWaitingRequestManager.maybeAddToWaitingRequests(request0)) {
                this.sendRequestOverNetwork(request0);
            }
            return;
        }
        this.mBlockingExecutor.execute(new CacheParseTask(this, request0, cache$Entry0, v));
    }

    private void onCacheInitializationComplete() {
        synchronized(this.mCacheInitializationLock) {
            ArrayList arrayList0 = new ArrayList(this.mRequestsAwaitingCacheInitialization);
            this.mRequestsAwaitingCacheInitialization.clear();
            this.mIsCacheInitialized = true;
        }
        for(Object object0: arrayList0) {
            this.beginRequest(((Request)object0));
        }
    }

    @Override  // com.android.volley.RequestQueue
    void sendRequestOverNetwork(Request request0) {
        this.mNonBlockingExecutor.execute(new NetworkTask(this, request0));
    }

    @Override  // com.android.volley.RequestQueue
    public void start() {
        this.stop();
        this.mNonBlockingExecutor = this.mExecutorFactory.createNonBlockingExecutor(AsyncRequestQueue.getBlockingQueue());
        this.mBlockingExecutor = this.mExecutorFactory.createBlockingExecutor(AsyncRequestQueue.getBlockingQueue());
        this.mNonBlockingScheduledExecutor = this.mExecutorFactory.createNonBlockingScheduledExecutor();
        this.mNetwork.setBlockingExecutor(this.mBlockingExecutor);
        this.mNetwork.setNonBlockingExecutor(this.mNonBlockingExecutor);
        this.mNetwork.setNonBlockingScheduledExecutor(this.mNonBlockingScheduledExecutor);
        if(this.mAsyncCache != null) {
            this.mNonBlockingExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    AsyncRequestQueue.this.mAsyncCache.initialize(new OnWriteCompleteCallback() {
                        @Override  // com.android.volley.AsyncCache$OnWriteCompleteCallback
                        public void onWriteComplete() {
                            AsyncRequestQueue.this.onCacheInitializationComplete();
                        }
                    });
                }
            });
            return;
        }
        this.mBlockingExecutor.execute(new Runnable() {
            @Override
            public void run() {
                AsyncRequestQueue.this.getCache().initialize();
                AsyncRequestQueue.this.mNonBlockingExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        AsyncRequestQueue.this.onCacheInitializationComplete();
                    }
                });
            }
        });
    }

    @Override  // com.android.volley.RequestQueue
    public void stop() {
        ExecutorService executorService0 = this.mNonBlockingExecutor;
        if(executorService0 != null) {
            executorService0.shutdownNow();
            this.mNonBlockingExecutor = null;
        }
        ExecutorService executorService1 = this.mBlockingExecutor;
        if(executorService1 != null) {
            executorService1.shutdownNow();
            this.mBlockingExecutor = null;
        }
        ScheduledExecutorService scheduledExecutorService0 = this.mNonBlockingScheduledExecutor;
        if(scheduledExecutorService0 != null) {
            scheduledExecutorService0.shutdownNow();
            this.mNonBlockingScheduledExecutor = null;
        }
    }
}

