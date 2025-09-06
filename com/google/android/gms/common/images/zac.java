package com.google.android.gms.common.images;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.SystemClock;
import com.google.android.gms.common.internal.Asserts;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

final class zac implements Runnable {
    final ImageManager zaa;
    private final Uri zab;
    private final Bitmap zac;
    private final CountDownLatch zad;

    public zac(ImageManager imageManager0, Uri uri0, Bitmap bitmap0, boolean z, CountDownLatch countDownLatch0) {
        this.zaa = imageManager0;
        super();
        this.zab = uri0;
        this.zac = bitmap0;
        this.zad = countDownLatch0;
    }

    @Override
    public final void run() {
        Asserts.checkMainThread("OnBitmapLoadedRunnable must be executed in the main thread");
        ImageReceiver imageManager$ImageReceiver0 = (ImageReceiver)this.zaa.zai.remove(this.zab);
        if(imageManager$ImageReceiver0 != null) {
            ArrayList arrayList0 = imageManager$ImageReceiver0.zac;
            int v = arrayList0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                zag zag0 = (zag)arrayList0.get(v1);
                Bitmap bitmap0 = this.zac;
                if(bitmap0 == null) {
                    Long long0 = SystemClock.elapsedRealtime();
                    this.zaa.zaj.put(this.zab, long0);
                    zag0.zab(this.zaa.zad, this.zaa.zag, false);
                }
                else {
                    zag0.zac(this.zaa.zad, bitmap0, false);
                }
                if(!(zag0 instanceof zaf)) {
                    this.zaa.zah.remove(zag0);
                }
            }
        }
        this.zad.countDown();
        synchronized(ImageManager.zaa) {
            ImageManager.zab.remove(this.zab);
        }
    }
}

