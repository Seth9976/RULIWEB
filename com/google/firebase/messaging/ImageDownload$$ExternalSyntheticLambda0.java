package com.google.firebase.messaging;

import com.google.android.gms.tasks.TaskCompletionSource;

public final class ImageDownload..ExternalSyntheticLambda0 implements Runnable {
    public final ImageDownload f$0;
    public final TaskCompletionSource f$1;

    public ImageDownload..ExternalSyntheticLambda0(ImageDownload imageDownload0, TaskCompletionSource taskCompletionSource0) {
        this.f$0 = imageDownload0;
        this.f$1 = taskCompletionSource0;
    }

    @Override
    public final void run() {
        this.f$0.lambda$start$0$com-google-firebase-messaging-ImageDownload(this.f$1);
    }
}

