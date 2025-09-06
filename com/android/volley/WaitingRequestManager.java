package com.android.volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

class WaitingRequestManager implements NetworkRequestCompleteListener {
    private final CacheDispatcher mCacheDispatcher;
    private final BlockingQueue mNetworkQueue;
    private final RequestQueue mRequestQueue;
    private final ResponseDelivery mResponseDelivery;
    private final Map mWaitingRequests;

    WaitingRequestManager(CacheDispatcher cacheDispatcher0, BlockingQueue blockingQueue0, ResponseDelivery responseDelivery0) {
        this.mWaitingRequests = new HashMap();
        this.mRequestQueue = null;
        this.mResponseDelivery = responseDelivery0;
        this.mCacheDispatcher = cacheDispatcher0;
        this.mNetworkQueue = blockingQueue0;
    }

    WaitingRequestManager(RequestQueue requestQueue0) {
        this.mWaitingRequests = new HashMap();
        this.mRequestQueue = requestQueue0;
        this.mResponseDelivery = requestQueue0.getResponseDelivery();
        this.mCacheDispatcher = null;
        this.mNetworkQueue = null;
    }

    boolean maybeAddToWaitingRequests(Request request0) {
        synchronized(this) {
            String s = request0.getCacheKey();
            if(this.mWaitingRequests.containsKey(s)) {
                List list0 = (List)this.mWaitingRequests.get(s);
                if(list0 == null) {
                    list0 = new ArrayList();
                }
                request0.addMarker("waiting-for-response");
                list0.add(request0);
                this.mWaitingRequests.put(s, list0);
                if(VolleyLog.DEBUG) {
                    VolleyLog.d("Request for cacheKey=%s is in flight, putting on hold.", new Object[]{s});
                }
                return true;
            }
            this.mWaitingRequests.put(s, null);
            request0.setNetworkRequestCompleteListener(this);
            if(VolleyLog.DEBUG) {
                VolleyLog.d("new request, sending to network %s", new Object[]{s});
            }
            return false;
        }
    }

    @Override  // com.android.volley.Request$NetworkRequestCompleteListener
    public void onNoUsableResponseReceived(Request request0) {
        synchronized(this) {
            String s = request0.getCacheKey();
            List list0 = (List)this.mWaitingRequests.remove(s);
            if(list0 != null && !list0.isEmpty()) {
                if(VolleyLog.DEBUG) {
                    VolleyLog.v("%d waiting requests for cacheKey=%s; resend to network", new Object[]{list0.size(), s});
                }
                Request request1 = (Request)list0.remove(0);
                this.mWaitingRequests.put(s, list0);
                request1.setNetworkRequestCompleteListener(this);
                RequestQueue requestQueue0 = this.mRequestQueue;
                if(requestQueue0 != null) {
                    requestQueue0.sendRequestOverNetwork(request1);
                }
                else if(this.mCacheDispatcher != null) {
                    BlockingQueue blockingQueue0 = this.mNetworkQueue;
                    if(blockingQueue0 != null) {
                        try {
                            blockingQueue0.put(request1);
                        }
                        catch(InterruptedException interruptedException0) {
                            VolleyLog.e("Couldn\'t add request to queue. %s", new Object[]{interruptedException0.toString()});
                            Thread.currentThread().interrupt();
                            this.mCacheDispatcher.quit();
                        }
                    }
                }
            }
        }
    }

    @Override  // com.android.volley.Request$NetworkRequestCompleteListener
    public void onResponseReceived(Request request0, Response response0) {
        List list0;
        if(response0.cacheEntry != null && !response0.cacheEntry.isExpired()) {
            String s = request0.getCacheKey();
            synchronized(this) {
                list0 = (List)this.mWaitingRequests.remove(s);
            }
            if(list0 != null) {
                if(VolleyLog.DEBUG) {
                    VolleyLog.v("Releasing %d waiting requests for cacheKey=%s.", new Object[]{list0.size(), s});
                }
                for(Object object0: list0) {
                    this.mResponseDelivery.postResponse(((Request)object0), response0);
                }
            }
            return;
        }
        this.onNoUsableResponseReceived(request0);
    }
}

