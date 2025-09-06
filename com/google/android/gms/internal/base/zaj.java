package com.google.android.gms.internal.base;

import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.Drawable;

final class zaj extends Drawable.ConstantState {
    int zaa;
    int zab;

    zaj(zaj zaj0) {
        if(zaj0 != null) {
            this.zaa = zaj0.zaa;
            this.zab = zaj0.zab;
        }
    }

    @Override  // android.graphics.drawable.Drawable$ConstantState
    public final int getChangingConfigurations() {
        return this.zaa;
    }

    @Override  // android.graphics.drawable.Drawable$ConstantState
    public final Drawable newDrawable() {
        return new zak(this);
    }
}

