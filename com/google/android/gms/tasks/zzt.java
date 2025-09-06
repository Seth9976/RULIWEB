package com.google.android.gms.tasks;

import java.util.concurrent.Executor;

final class zzt implements Executor {
    @Override
    public final void execute(Runnable runnable0) {
        runnable0.run();
    }
}

