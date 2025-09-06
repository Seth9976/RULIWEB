package com.google.android.gms.common.api;

import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.util.concurrent.TimeUnit;

public abstract class PendingResult {
    public interface StatusListener {
        void onComplete(Status arg1);
    }

    public void addStatusListener(StatusListener pendingResult$StatusListener0) {
        throw new UnsupportedOperationException();
    }

    @ResultIgnorabilityUnspecified
    public abstract Result await();

    @ResultIgnorabilityUnspecified
    public abstract Result await(long arg1, TimeUnit arg2);

    public abstract void cancel();

    public abstract boolean isCanceled();

    public abstract void setResultCallback(ResultCallback arg1);

    public abstract void setResultCallback(ResultCallback arg1, long arg2, TimeUnit arg3);

    public TransformedResult then(ResultTransform resultTransform0) {
        throw new UnsupportedOperationException();
    }
}

