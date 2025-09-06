package com.google.android.material.animation;

import android.graphics.drawable.Drawable;
import android.util.Property;
import java.util.WeakHashMap;

public class DrawableAlphaProperty extends Property {
    public static final Property DRAWABLE_ALPHA_COMPAT;
    private final WeakHashMap alphaCache;

    static {
        DrawableAlphaProperty.DRAWABLE_ALPHA_COMPAT = new DrawableAlphaProperty();
    }

    private DrawableAlphaProperty() {
        super(Integer.class, "drawableAlphaCompat");
        this.alphaCache = new WeakHashMap();
    }

    public Integer get(Drawable drawable0) {
        return drawable0.getAlpha();
    }

    @Override  // android.util.Property
    public Object get(Object object0) {
        return this.get(((Drawable)object0));
    }

    public void set(Drawable drawable0, Integer integer0) {
        drawable0.setAlpha(((int)integer0));
    }

    @Override  // android.util.Property
    public void set(Object object0, Object object1) {
        this.set(((Drawable)object0), ((Integer)object1));
    }
}

