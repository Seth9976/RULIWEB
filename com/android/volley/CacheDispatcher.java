package com.android.volley;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

public class CacheDispatcher extends Thread {
    private static final boolean DEBUG;
    private final Cache mCache;
    private final BlockingQueue mCacheQueue;
    private final ResponseDelivery mDelivery;
    private final BlockingQueue mNetworkQueue;
    private volatile boolean mQuit;
    private final WaitingRequestManager mWaitingRequestManager;

    static {
        CacheDispatcher.DEBUG = VolleyLog.DEBUG;
    }

    public CacheDispatcher(BlockingQueue blockingQueue0, BlockingQueue blockingQueue1, Cache cache0, ResponseDelivery responseDelivery0) {
        this.mQuit = false;
        this.mCacheQueue = blockingQueue0;
        this.mNetworkQueue = blockingQueue1;
        this.mCache = cache0;
        this.mDelivery = responseDelivery0;
        this.mWaitingRequestManager = new WaitingRequestManager(this, blockingQueue1, responseDelivery0);
    }

    private void processRequest() throws InterruptedException {
        this.processRequest(((Request)this.mCacheQueue.take()));
    }

    void processRequest(Request request0) throws InterruptedException {
        request0.addMarker("cache-queue-take");
        request0.sendEvent(1);
        try {
            if(request0.isCanceled()) {
                request0.finish("cache-discard-canceled");
                return;
            }
            String s = request0.getCacheKey();
            Entry cache$Entry0 = this.mCache.get(s);
            if(cache$Entry0 == null) {
                request0.addMarker("cache-miss");
                if(!this.mWaitingRequestManager.maybeAddToWaitingRequests(request0)) {
                    this.mNetworkQueue.put(request0);
                }
                return;
            }
            long v1 = System.currentTimeMillis();
            if(cache$Entry0.isExpired(v1)) {
                request0.addMarker("cache-hit-expired");
                request0.setCacheEntry(cache$Entry0);
                if(!this.mWaitingRequestManager.maybeAddToWaitingRequests(request0)) {
                    this.mNetworkQueue.put(request0);
                }
                return;
            }
            request0.addMarker("cache-hit");
            Response response0 = request0.parseNetworkResponse(new NetworkResponse(cache$Entry0.data, cache$Entry0.responseHeaders));
            request0.addMarker("cache-hit-parsed");
            if(!response0.isSuccess()) {
                request0.addMarker("cache-parsing-failed");
                String s1 = request0.getCacheKey();
                this.mCache.invalidate(s1, true);
                request0.setCacheEntry(null);
                if(!this.mWaitingRequestManager.maybeAddToWaitingRequests(request0)) {
                    this.mNetworkQueue.put(request0);
                }
                return;
            }
            if(cache$Entry0.refreshNeeded(v1)) {
                request0.addMarker("cache-hit-refresh-needed");
                request0.setCacheEntry(cache$Entry0);
                response0.intermediate = true;
                if(this.mWaitingRequestManager.maybeAddToWaitingRequests(request0)) {
                    this.mDelivery.postResponse(request0, response0);
                }
                else {
                    com.android.volley.CacheDispatcher.1 cacheDispatcher$10 = new Runnable() {
                        @Override
                        public void run() {
                            try {
                                CacheDispatcher.this.mNetworkQueue.put(request0);
                            }
                            catch(InterruptedException unused_ex) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    };
                    this.mDelivery.postResponse(request0, response0, cacheDispatcher$10);
                }
            }
            else {
                this.mDelivery.postResponse(request0, response0);
            }
        }
        finally {
            request0.sendEvent(2);
        }
    }

    public void quit() {
        this.mQuit = true;
        this.interrupt();
    }

    @Override
    public void run() {
        if(CacheDispatcher.DEBUG) {
            VolleyLog.v("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.mCache.initialize();
        while(true) {
            try {
            label_4:
                this.processRequest();
                goto label_4;
            }
            catch(InterruptedException unused_ex) {
            }
            if(this.mQuit) {
                break;
            }
            VolleyLog.e("Ignoring spurious interrupt of CacheDispatcher thread; use quit() to terminate it", new Object[0]);
        }
        Thread.currentThread().interrupt();
    }
}

