package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.base.zak;
import com.google.android.gms.internal.base.zal;
import java.lang.ref.WeakReference;

public final class zae extends zag {
    private final WeakReference zac;

    public zae(ImageView imageView0, int v) {
        super(Uri.EMPTY, v);
        Asserts.checkNotNull(imageView0);
        this.zac = new WeakReference(imageView0);
    }

    public zae(ImageView imageView0, Uri uri0) {
        super(uri0, 0);
        Asserts.checkNotNull(imageView0);
        this.zac = new WeakReference(imageView0);
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zae)) {
            return false;
        }
        ImageView imageView0 = (ImageView)this.zac.get();
        ImageView imageView1 = (ImageView)((zae)object0).zac.get();
        return imageView1 != null && imageView0 != null && Objects.equal(imageView1, imageView0);
    }

    @Override
    public final int hashCode() {
        return 0;
    }

    @Override  // com.google.android.gms.common.images.zag
    protected final void zaa(Drawable drawable0, boolean z, boolean z1, boolean z2) {
        boolean z3 = false;
        ImageView imageView0 = (ImageView)this.zac.get();
        if(imageView0 != null) {
            if(!z1 && !z2 && imageView0 instanceof zal) {
                zal zal0 = (zal)imageView0;
                throw null;
            }
            if(!z1 && !z) {
                z3 = true;
                Drawable drawable1 = imageView0.getDrawable();
                if(drawable1 == null) {
                    drawable1 = null;
                }
                else if(drawable1 instanceof zak) {
                    drawable1 = ((zak)drawable1).zaa();
                }
                drawable0 = new zak(drawable1, drawable0);
            }
            imageView0.setImageDrawable(drawable0);
            if(imageView0 instanceof zal) {
                zal zal1 = (zal)imageView0;
                throw null;
            }
            else if(drawable0 != null && z3) {
                ((zak)drawable0).zab(0xFA);
            }
        }
    }
}

