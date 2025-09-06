package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.SystemClock;
import com.google.android.gms.common.internal.Asserts;

final class zab implements Runnable {
    final ImageManager zaa;
    private final zag zab;

    public zab(ImageManager imageManager0, zag zag0) {
        this.zaa = imageManager0;
        super();
        this.zab = zag0;
    }

    @Override
    public final void run() {
        Asserts.checkMainThread("LoadImageRunnable must be executed on the main thread");
        ImageReceiver imageManager$ImageReceiver0 = (ImageReceiver)this.zaa.zah.get(this.zab);
        if(imageManager$ImageReceiver0 != null) {
            this.zaa.zah.remove(this.zab);
            imageManager$ImageReceiver0.zac(this.zab);
        }
        zag zag0 = this.zab;
        zad zad0 = zag0.zaa;
        Uri uri0 = zad0.zaa;
        if(uri0 != null) {
            Long long0 = (Long)this.zaa.zaj.get(uri0);
            if(long0 != null) {
                if(SystemClock.elapsedRealtime() - ((long)long0) < 3600000L) {
                    this.zab.zab(this.zaa.zad, this.zaa.zag, true);
                    return;
                }
                this.zaa.zaj.remove(zad0.zaa);
            }
            this.zab.zaa(null, false, true, false);
            ImageReceiver imageManager$ImageReceiver1 = (ImageReceiver)this.zaa.zai.get(zad0.zaa);
            if(imageManager$ImageReceiver1 == null) {
                ImageReceiver imageManager$ImageReceiver2 = new ImageReceiver(this.zaa, zad0.zaa);
                this.zaa.zai.put(zad0.zaa, imageManager$ImageReceiver2);
                imageManager$ImageReceiver1 = imageManager$ImageReceiver2;
            }
            imageManager$ImageReceiver1.zab(this.zab);
            zag zag1 = this.zab;
            if(!(zag1 instanceof zaf)) {
                this.zaa.zah.put(zag1, imageManager$ImageReceiver1);
            }
            synchronized(ImageManager.zaa) {
                if(!ImageManager.zab.contains(zad0.zaa)) {
                    ImageManager.zab.add(zad0.zaa);
                    imageManager$ImageReceiver1.zad();
                }
            }
            return;
        }
        zag0.zab(this.zaa.zad, this.zaa.zag, true);
    }
}

