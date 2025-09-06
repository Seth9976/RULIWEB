package androidx.core.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.widget.EdgeEffect;

public final class EdgeEffectCompat {
    static class Api21Impl {
        static void onPull(EdgeEffect edgeEffect0, float f, float f1) {
            edgeEffect0.onPull(f, f1);
        }
    }

    static class Api31Impl {
        public static EdgeEffect create(Context context0, AttributeSet attributeSet0) {
            try {
                return new EdgeEffect(context0, attributeSet0);
            }
            catch(Throwable unused_ex) {
                return new EdgeEffect(context0);
            }
        }

        public static float getDistance(EdgeEffect edgeEffect0) {
            try {
                return edgeEffect0.getDistance();
            }
            catch(Throwable unused_ex) {
                return 0.0f;
            }
        }

        public static float onPullDistance(EdgeEffect edgeEffect0, float f, float f1) {
            try {
                return edgeEffect0.onPullDistance(f, f1);
            }
            catch(Throwable unused_ex) {
                edgeEffect0.onPull(f, f1);
                return 0.0f;
            }
        }
    }

    private final EdgeEffect mEdgeEffect;

    @Deprecated
    public EdgeEffectCompat(Context context0) {
        this.mEdgeEffect = new EdgeEffect(context0);
    }

    public static EdgeEffect create(Context context0, AttributeSet attributeSet0) {
        return Build.VERSION.SDK_INT < 0x1F ? new EdgeEffect(context0) : Api31Impl.create(context0, attributeSet0);
    }

    @Deprecated
    public boolean draw(Canvas canvas0) {
        return this.mEdgeEffect.draw(canvas0);
    }

    @Deprecated
    public void finish() {
        this.mEdgeEffect.finish();
    }

    public static float getDistance(EdgeEffect edgeEffect0) {
        return Build.VERSION.SDK_INT < 0x1F ? 0.0f : Api31Impl.getDistance(edgeEffect0);
    }

    @Deprecated
    public boolean isFinished() {
        return this.mEdgeEffect.isFinished();
    }

    @Deprecated
    public boolean onAbsorb(int v) {
        this.mEdgeEffect.onAbsorb(v);
        return true;
    }

    public static void onPull(EdgeEffect edgeEffect0, float f, float f1) {
        Api21Impl.onPull(edgeEffect0, f, f1);
    }

    @Deprecated
    public boolean onPull(float f) {
        this.mEdgeEffect.onPull(f);
        return true;
    }

    @Deprecated
    public boolean onPull(float f, float f1) {
        EdgeEffectCompat.onPull(this.mEdgeEffect, f, f1);
        return true;
    }

    public static float onPullDistance(EdgeEffect edgeEffect0, float f, float f1) {
        if(Build.VERSION.SDK_INT >= 0x1F) {
            return Api31Impl.onPullDistance(edgeEffect0, f, f1);
        }
        EdgeEffectCompat.onPull(edgeEffect0, f, f1);
        return f;
    }

    @Deprecated
    public boolean onRelease() {
        this.mEdgeEffect.onRelease();
        return this.mEdgeEffect.isFinished();
    }

    @Deprecated
    public void setSize(int v, int v1) {
        this.mEdgeEffect.setSize(v, v1);
    }
}

