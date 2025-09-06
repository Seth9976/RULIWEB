package androidx.core.view.insets;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.GradientDrawable;
import android.view.animation.PathInterpolator;

public class GradientProtection extends Protection {
    private static final float[] ALPHAS;
    private int mColor;
    private final int[] mColors;
    private final GradientDrawable mDrawable;
    private boolean mHasColor;
    private float mScale;

    static {
        GradientProtection.ALPHAS = new float[100];
        PathInterpolator pathInterpolator0 = new PathInterpolator(0.42f, 0.0f, 0.58f, 1.0f);
        for(int v = 99; v >= 0; --v) {
            GradientProtection.ALPHAS[v] = pathInterpolator0.getInterpolation(((float)(99 - v)) / 99.0f);
        }
    }

    public GradientProtection(int v) {
        super(v);
        GradientDrawable gradientDrawable0 = new GradientDrawable();
        this.mDrawable = gradientDrawable0;
        this.mColors = new int[GradientProtection.ALPHAS.length];
        this.mColor = 0;
        this.mScale = 1.2f;
        switch(v) {
            case 1: {
                gradientDrawable0.setOrientation(GradientDrawable.Orientation.LEFT_RIGHT);
                return;
            }
            case 2: {
                gradientDrawable0.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
                return;
            }
            case 4: {
                gradientDrawable0.setOrientation(GradientDrawable.Orientation.RIGHT_LEFT);
                return;
            }
            case 8: {
                gradientDrawable0.setOrientation(GradientDrawable.Orientation.BOTTOM_TOP);
            }
        }
    }

    public GradientProtection(int v, int v1) {
        this(v);
        this.setColor(v1);
    }

    @Override  // androidx.core.view.insets.Protection
    void dispatchColorHint(int v) {
        if(!this.mHasColor) {
            this.setColorInner(v);
        }
    }

    public int getColor() {
        return this.mColor;
    }

    public float getScale() {
        return this.mScale;
    }

    @Override  // androidx.core.view.insets.Protection
    int getThickness(int v) {
        return (int)(this.mScale * ((float)v));
    }

    public void setColor(int v) {
        this.mHasColor = true;
        this.setColorInner(v);
    }

    private void setColorInner(int v) {
        if(this.mColor != v) {
            this.mColor = v;
            GradientProtection.toColors(v, this.mColors);
            this.mDrawable.setColors(this.mColors);
            this.setDrawable(this.mDrawable);
        }
    }

    public void setScale(float f) {
        if(f < 0.0f) {
            throw new IllegalArgumentException("Scale must not be negative.");
        }
        this.mScale = f;
        this.updateLayout();
    }

    private static void toColors(int v, int[] arr_v) {
        for(int v1 = arr_v.length - 1; v1 >= 0; --v1) {
            arr_v[v1] = Color.argb(((int)(GradientProtection.ALPHAS[v1] * ((float)Color.alpha(v)))), Color.red(v), Color.green(v), Color.blue(v));
        }
    }
}

