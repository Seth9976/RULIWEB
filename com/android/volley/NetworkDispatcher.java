package com.android.volley;

import android.net.TrafficStats;
import android.os.Process;
import android.os.SystemClock;
import java.util.concurrent.BlockingQueue;

public class NetworkDispatcher extends Thread {
    private final Cache mCache;
    private final ResponseDelivery mDelivery;
    private final Network mNetwork;
    private final BlockingQueue mQueue;
    private volatile boolean mQuit;

    public NetworkDispatcher(BlockingQueue blockingQueue0, Network network0, Cache cache0, ResponseDelivery responseDelivery0) {
        this.mQuit = false;
        this.mQueue = blockingQueue0;
        this.mNetwork = network0;
        this.mCache = cache0;
        this.mDelivery = responseDelivery0;
    }

    private void addTrafficStatsTag(Request request0) {
        TrafficStats.setThreadStatsTag(request0.getTrafficStatsTag());
    }

    private void parseAndDeliverNetworkError(Request request0, VolleyError volleyError0) {
        this.mDelivery.postError(request0, volleyError0);
    }

    private void processRequest() throws InterruptedException {
        this.processRequest(((Request)this.mQueue.take()));
    }

    void processRequest(Request request0) {
        long v = SystemClock.elapsedRealtime();
        request0.sendEvent(3);
        try {
            request0.addMarker("network-queue-take");
            if(!request0.isCanceled()) {
                this.addTrafficStatsTag(request0);
                NetworkResponse networkResponse0 = this.mNetwork.performRequest(request0);
                request0.addMarker("network-http-complete");
                if(networkResponse0.notModified && request0.hasHadResponseDelivered()) {
                    request0.finish("not-modified");
                    request0.notifyListenerResponseNotUsable();
                    return;
                }
                Response response0 = request0.parseNetworkResponse(networkResponse0);
                request0.addMarker("network-parse-complete");
                if(request0.shouldCache() && response0.cacheEntry != null) {
                    String s = request0.getCacheKey();
                    this.mCache.put(s, response0.cacheEntry);
                    request0.addMarker("network-cache-written");
                }
                request0.markDelivered();
                this.mDelivery.postResponse(request0, response0);
                request0.notifyListenerResponseReceived(response0);
                return;
            }
            request0.finish("network-discard-cancelled");
            request0.notifyListenerResponseNotUsable();
        }
        catch(VolleyError volleyError0) {
            volleyError0.setNetworkTimeMs(SystemClock.elapsedRealtime() - v);
            this.parseAndDeliverNetworkError(request0, volleyError0);
            request0.notifyListenerResponseNotUsable();
        }
        catch(Exception exception0) {
            VolleyLog.e(exception0, "Unhandled exception %s", new Object[]{exception0.toString()});
            VolleyError volleyError1 = new VolleyError(exception0);
            volleyError1.setNetworkTimeMs(SystemClock.elapsedRealtime() - v);
            this.mDelivery.postError(request0, volleyError1);
            request0.notifyListenerResponseNotUsable();
        }
        finally {
            request0.sendEvent(4);
        }
    }

    public void quit() {
        this.mQuit = true;
        this.interrupt();
    }

    @Override
    public void run() {
        Process.setThreadPriority(10);
        while(true) {
            try {
            label_1:
                this.processRequest();
                goto label_1;
            }
            catch(InterruptedException unused_ex) {
            }
            if(this.mQuit) {
                break;
            }
            VolleyLog.e("Ignoring spurious interrupt of NetworkDispatcher thread; use quit() to terminate it", new Object[0]);
        }
        Thread.currentThread().interrupt();
    }
}

