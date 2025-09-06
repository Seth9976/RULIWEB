package com.google.android.gms.common.images;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.widget.ImageView;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zam;
import com.google.android.gms.internal.base.zat;
import com.google.android.gms.internal.base.zau;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ExecutorService;

public final class ImageManager {
    final class ImageReceiver extends ResultReceiver {
        final ImageManager zaa;
        private final Uri zab;
        private final ArrayList zac;

        ImageReceiver(Uri uri0) {
            super(new zau(Looper.getMainLooper()));
            this.zab = uri0;
            this.zac = new ArrayList();
        }

        @Override  // android.os.ResultReceiver
        public final void onReceiveResult(int v, Bundle bundle0) {
            ParcelFileDescriptor parcelFileDescriptor0 = (ParcelFileDescriptor)bundle0.getParcelable("com.google.android.gms.extra.fileDescriptor");
            ImageManager.this.zaf.execute(new zaa(ImageManager.this, this.zab, parcelFileDescriptor0));
        }

        public final void zab(zag zag0) {
            Asserts.checkMainThread("ImageReceiver.addImageRequest() must be called in the main thread");
            this.zac.add(zag0);
        }

        public final void zac(zag zag0) {
            Asserts.checkMainThread("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.zac.remove(zag0);
        }

        public final void zad() {
            Intent intent0 = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
            intent0.setPackage("com.google.android.gms");
            intent0.putExtra("com.google.android.gms.extras.uri", this.zab);
            intent0.putExtra("com.google.android.gms.extras.resultReceiver", this);
            intent0.putExtra("com.google.android.gms.extras.priority", 3);
            ImageManager.this.zad.sendBroadcast(intent0);
        }
    }

    public interface OnImageLoadedListener {
        void onImageLoaded(Uri arg1, Drawable arg2, boolean arg3);
    }

    private static final Object zaa;
    private static final HashSet zab;
    private static ImageManager zac;
    private final Context zad;
    private final Handler zae;
    private final ExecutorService zaf;
    private final zam zag;
    private final Map zah;
    private final Map zai;
    private final Map zaj;

    static {
        ImageManager.zaa = new Object();
        ImageManager.zab = new HashSet();
    }

    private ImageManager(Context context0, boolean z) {
        this.zad = context0.getApplicationContext();
        this.zae = new zau(Looper.getMainLooper());
        this.zaf = zat.zaa().zab(4, 2);
        this.zag = new zam();
        this.zah = new HashMap();
        this.zai = new HashMap();
        this.zaj = new HashMap();
    }

    public static ImageManager create(Context context0) {
        if(ImageManager.zac == null) {
            ImageManager.zac = new ImageManager(context0, false);
        }
        return ImageManager.zac;
    }

    public void loadImage(ImageView imageView0, int v) {
        this.zaj(new zae(imageView0, v));
    }

    public void loadImage(ImageView imageView0, Uri uri0) {
        this.zaj(new zae(imageView0, uri0));
    }

    public void loadImage(ImageView imageView0, Uri uri0, int v) {
        zae zae0 = new zae(imageView0, uri0);
        zae0.zab = v;
        this.zaj(zae0);
    }

    public void loadImage(OnImageLoadedListener imageManager$OnImageLoadedListener0, Uri uri0) {
        this.zaj(new zaf(imageManager$OnImageLoadedListener0, uri0));
    }

    public void loadImage(OnImageLoadedListener imageManager$OnImageLoadedListener0, Uri uri0, int v) {
        zaf zaf0 = new zaf(imageManager$OnImageLoadedListener0, uri0);
        zaf0.zab = v;
        this.zaj(zaf0);
    }

    public final void zaj(zag zag0) {
        Asserts.checkMainThread("ImageManager.loadImage() must be called in the main thread");
        new zab(this, zag0).run();
    }
}

