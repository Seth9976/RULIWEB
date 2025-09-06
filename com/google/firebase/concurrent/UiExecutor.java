package com.google.firebase.concurrent;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

public enum UiExecutor implements Executor {
    INSTANCE;

    private static final Handler HANDLER;

    static {
        UiExecutor.HANDLER = new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(Runnable runnable0) {
        UiExecutor.HANDLER.post(runnable0);
    }
}

