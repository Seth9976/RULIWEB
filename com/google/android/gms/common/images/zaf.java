package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Objects;
import java.lang.ref.WeakReference;

public final class zaf extends zag {
    private final WeakReference zac;

    public zaf(OnImageLoadedListener imageManager$OnImageLoadedListener0, Uri uri0) {
        super(uri0, 0);
        Asserts.checkNotNull(imageManager$OnImageLoadedListener0);
        this.zac = new WeakReference(imageManager$OnImageLoadedListener0);
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof zaf)) {
            return false;
        }
        OnImageLoadedListener imageManager$OnImageLoadedListener0 = (OnImageLoadedListener)this.zac.get();
        OnImageLoadedListener imageManager$OnImageLoadedListener1 = (OnImageLoadedListener)((zaf)object0).zac.get();
        return imageManager$OnImageLoadedListener1 != null && imageManager$OnImageLoadedListener0 != null && Objects.equal(imageManager$OnImageLoadedListener1, imageManager$OnImageLoadedListener0) && Objects.equal(((zaf)object0).zaa, this.zaa);
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zaa});
    }

    @Override  // com.google.android.gms.common.images.zag
    protected final void zaa(Drawable drawable0, boolean z, boolean z1, boolean z2) {
        if(!z1) {
            OnImageLoadedListener imageManager$OnImageLoadedListener0 = (OnImageLoadedListener)this.zac.get();
            if(imageManager$OnImageLoadedListener0 != null) {
                imageManager$OnImageLoadedListener0.onImageLoaded(this.zaa.zaa, drawable0, z2);
            }
        }
    }
}

