package com.google.firebase.messaging;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ImageDownload implements Closeable, AutoCloseable {
    private static final int MAX_IMAGE_SIZE_BYTES = 0x100000;
    private volatile Future future;
    private Task task;
    private final URL url;

    private ImageDownload(URL uRL0) {
        this.url = uRL0;
    }

    public Bitmap blockingDownload() throws IOException {
        if(Log.isLoggable("FirebaseMessaging", 4)) {
            Log.i("FirebaseMessaging", "Starting download of: " + this.url);
        }
        byte[] arr_b = this.blockingDownloadBytes();
        Bitmap bitmap0 = BitmapFactory.decodeByteArray(arr_b, 0, arr_b.length);
        if(bitmap0 == null) {
            throw new IOException("Failed to decode image: " + this.url);
        }
        if(Log.isLoggable("FirebaseMessaging", 3)) {
            Log.d("FirebaseMessaging", "Successfully downloaded image: " + this.url);
        }
        return bitmap0;
    }

    private byte[] blockingDownloadBytes() throws IOException {
        byte[] arr_b;
        URLConnection uRLConnection0 = this.url.openConnection();
        if(uRLConnection0.getContentLength() > 0x100000) {
            throw new IOException("Content-Length exceeds max size of 1048576");
        }
        try(InputStream inputStream0 = uRLConnection0.getInputStream()) {
            arr_b = ByteStreams.toByteArray(ByteStreams.limit(inputStream0, 0x100001L));
        }
        if(Log.isLoggable("FirebaseMessaging", 2)) {
            Log.v("FirebaseMessaging", "Downloaded " + arr_b.length + " bytes from " + this.url);
        }
        if(arr_b.length > 0x100000) {
            throw new IOException("Image exceeds max size of 1048576");
        }
        return arr_b;
    }

    @Override
    public void close() {
        this.future.cancel(true);
    }

    public static ImageDownload create(String s) {
        if(TextUtils.isEmpty(s)) {
            return null;
        }
        try {
            return new ImageDownload(new URL(s));
        }
        catch(MalformedURLException unused_ex) {
            Log.w("FirebaseMessaging", "Not downloading image, bad URL: " + s);
            return null;
        }
    }

    public Task getTask() {
        return (Task)Preconditions.checkNotNull(this.task);
    }

    // 检测为 Lambda 实现
    void lambda$start$0$com-google-firebase-messaging-ImageDownload(TaskCompletionSource taskCompletionSource0) [...]

    public void start(ExecutorService executorService0) {
        TaskCompletionSource taskCompletionSource0 = new TaskCompletionSource();
        this.future = executorService0.submit(() -> try {
            taskCompletionSource0.setResult(this.blockingDownload());
        }
        catch(Exception exception0) {
            taskCompletionSource0.setException(exception0);
        });
        this.task = taskCompletionSource0.getTask();
    }
}

