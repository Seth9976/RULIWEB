package com.google.android.gms.common.api.internal;

import android.os.Handler;
import java.util.concurrent.Executor;

public final class zabk implements Executor {
    public final Handler zaa;

    public zabk(Handler handler0) {
        this.zaa = handler0;
    }

    @Override
    public final void execute(Runnable runnable0) {
        this.zaa.post(runnable0);
    }
}

