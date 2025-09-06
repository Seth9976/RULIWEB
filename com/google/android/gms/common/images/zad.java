package com.google.android.gms.common.images;

import android.net.Uri;
import com.google.android.gms.common.internal.Objects;

final class zad {
    public final Uri zaa;

    public zad(Uri uri0) {
        this.zaa = uri0;
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof zad ? Objects.equal(((zad)object0).zaa, this.zaa) : false;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zaa});
    }
}

