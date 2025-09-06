package com.android.volley;

import android.os.Handler;
import java.util.concurrent.Executor;

public class ExecutorDelivery implements ResponseDelivery {
    static class ResponseDeliveryRunnable implements Runnable {
        private final Request mRequest;
        private final Response mResponse;
        private final Runnable mRunnable;

        public ResponseDeliveryRunnable(Request request0, Response response0, Runnable runnable0) {
            this.mRequest = request0;
            this.mResponse = response0;
            this.mRunnable = runnable0;
        }

        @Override
        public void run() {
            if(this.mRequest.isCanceled()) {
                this.mRequest.finish("canceled-at-delivery");
                return;
            }
            if(this.mResponse.isSuccess()) {
                this.mRequest.deliverResponse(this.mResponse.result);
            }
            else {
                this.mRequest.deliverError(this.mResponse.error);
            }
            if(this.mResponse.intermediate) {
                this.mRequest.addMarker("intermediate-response");
            }
            else {
                this.mRequest.finish("done");
            }
            Runnable runnable0 = this.mRunnable;
            if(runnable0 != null) {
                runnable0.run();
            }
        }
    }

    private final Executor mResponsePoster;

    public ExecutorDelivery(Handler handler0) {
        this.mResponsePoster = new Executor() {
            @Override
            public void execute(Runnable runnable0) {
                handler0.post(runnable0);
            }
        };
    }

    public ExecutorDelivery(Executor executor0) {
        this.mResponsePoster = executor0;
    }

    @Override  // com.android.volley.ResponseDelivery
    public void postError(Request request0, VolleyError volleyError0) {
        request0.addMarker("post-error");
        ResponseDeliveryRunnable executorDelivery$ResponseDeliveryRunnable0 = new ResponseDeliveryRunnable(request0, Response.error(volleyError0), null);
        this.mResponsePoster.execute(executorDelivery$ResponseDeliveryRunnable0);
    }

    @Override  // com.android.volley.ResponseDelivery
    public void postResponse(Request request0, Response response0) {
        this.postResponse(request0, response0, null);
    }

    @Override  // com.android.volley.ResponseDelivery
    public void postResponse(Request request0, Response response0, Runnable runnable0) {
        request0.markDelivered();
        request0.addMarker("post-response");
        ResponseDeliveryRunnable executorDelivery$ResponseDeliveryRunnable0 = new ResponseDeliveryRunnable(request0, response0, runnable0);
        this.mResponsePoster.execute(executorDelivery$ResponseDeliveryRunnable0);
    }
}

