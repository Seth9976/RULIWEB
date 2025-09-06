package com.google.android.gms.common.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.internal.base.zam;

public abstract class zag {
    final zad zaa;
    protected int zab;

    public zag(Uri uri0, int v) {
        this.zab = 0;
        this.zaa = new zad(uri0);
        this.zab = v;
    }

    protected abstract void zaa(Drawable arg1, boolean arg2, boolean arg3, boolean arg4);

    final void zab(Context context0, zam zam0, boolean z) {
        int v = this.zab;
        this.zaa((v == 0 ? null : context0.getResources().getDrawable(v)), z, false, false);
    }

    final void zac(Context context0, Bitmap bitmap0, boolean z) {
        Asserts.checkNotNull(bitmap0);
        this.zaa(new BitmapDrawable(context0.getResources(), bitmap0), false, false, true);
    }
}

