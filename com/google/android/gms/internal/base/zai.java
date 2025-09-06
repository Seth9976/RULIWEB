package com.google.android.gms.internal.base;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.Drawable;

final class zai extends Drawable {
    private static final zai zaa;
    private static final zah zab;

    static {
        zai.zaa = new zai();
        zai.zab = new zah(null);
    }

    @Override  // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas0) {
    }

    @Override  // android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        return zai.zab;
    }

    @Override  // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -2;
    }

    @Override  // android.graphics.drawable.Drawable
    public final void setAlpha(int v) {
    }

    @Override  // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter0) {
    }
}

