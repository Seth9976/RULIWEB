package com.google.android.gms.common.api;

import android.util.Log;

public abstract class ResultCallbacks implements ResultCallback {
    public abstract void onFailure(Status arg1);

    @Override  // com.google.android.gms.common.api.ResultCallback
    public final void onResult(Result result0) {
        Status status0 = result0.getStatus();
        if(status0.isSuccess()) {
            this.onSuccess(result0);
            return;
        }
        this.onFailure(status0);
        if(result0 instanceof Releasable) {
            try {
                ((Releasable)result0).release();
            }
            catch(RuntimeException runtimeException0) {
                Log.w("ResultCallbacks", "Unable to release " + result0, runtimeException0);
            }
        }
    }

    public abstract void onSuccess(Result arg1);
}

