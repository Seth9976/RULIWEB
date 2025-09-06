package com.google.firebase.analytics;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class zza extends ThreadPoolExecutor {
    zza(FirebaseAnalytics firebaseAnalytics0, int v, int v1, long v2, TimeUnit timeUnit0, BlockingQueue blockingQueue0) {
        super(0, 1, 30L, timeUnit0, blockingQueue0);
    }
}

