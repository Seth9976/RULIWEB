package com.google.firebase.installations;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

final class AwaitListener implements OnCompleteListener {
    private final CountDownLatch latch;

    AwaitListener() {
        this.latch = new CountDownLatch(1);
    }

    public boolean await(long v, TimeUnit timeUnit0) throws InterruptedException {
        return this.latch.await(v, timeUnit0);
    }

    @Override  // com.google.android.gms.tasks.OnCompleteListener
    public void onComplete(Task task0) {
        this.latch.countDown();
    }

    public void onSuccess() {
        this.latch.countDown();
    }
}

