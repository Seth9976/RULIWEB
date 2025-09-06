package androidx.core.graphics;

import android.graphics.Rect;

public final class Insets {
    static class Api29Impl {
        static android.graphics.Insets of(int v, int v1, int v2, int v3) {
            return android.graphics.Insets.of(v, v1, v2, v3);
        }
    }

    public static final Insets NONE;
    public final int bottom;
    public final int left;
    public final int right;
    public final int top;

    static {
        Insets.NONE = new Insets(0, 0, 0, 0);
    }

    private Insets(int v, int v1, int v2, int v3) {
        this.left = v;
        this.top = v1;
        this.right = v2;
        this.bottom = v3;
    }

    public static Insets add(Insets insets0, Insets insets1) {
        return Insets.of(insets0.left + insets1.left, insets0.top + insets1.top, insets0.right + insets1.right, insets0.bottom + insets1.bottom);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 == null || this.getClass() != object0.getClass() || this.bottom != ((Insets)object0).bottom) {
            return false;
        }
        if(this.left != ((Insets)object0).left) {
            return false;
        }
        return this.right == ((Insets)object0).right ? this.top == ((Insets)object0).top : false;
    }

    @Override
    public int hashCode() {
        return ((this.left * 0x1F + this.top) * 0x1F + this.right) * 0x1F + this.bottom;
    }

    public static Insets max(Insets insets0, Insets insets1) {
        return Insets.of(Math.max(insets0.left, insets1.left), Math.max(insets0.top, insets1.top), Math.max(insets0.right, insets1.right), Math.max(insets0.bottom, insets1.bottom));
    }

    public static Insets min(Insets insets0, Insets insets1) {
        return Insets.of(Math.min(insets0.left, insets1.left), Math.min(insets0.top, insets1.top), Math.min(insets0.right, insets1.right), Math.min(insets0.bottom, insets1.bottom));
    }

    public static Insets of(int v, int v1, int v2, int v3) {
        return v != 0 || v1 != 0 || v2 != 0 || v3 != 0 ? new Insets(v, v1, v2, v3) : Insets.NONE;
    }

    public static Insets of(Rect rect0) {
        return Insets.of(rect0.left, rect0.top, rect0.right, rect0.bottom);
    }

    public static Insets subtract(Insets insets0, Insets insets1) {
        return Insets.of(insets0.left - insets1.left, insets0.top - insets1.top, insets0.right - insets1.right, insets0.bottom - insets1.bottom);
    }

    public static Insets toCompatInsets(android.graphics.Insets insets0) {
        return Insets.of(insets0.left, insets0.top, insets0.right, insets0.bottom);
    }

    public android.graphics.Insets toPlatformInsets() {
        return Api29Impl.of(this.left, this.top, this.right, this.bottom);
    }

    @Override
    public String toString() {
        return "Insets{left=" + this.left + ", top=" + this.top + ", right=" + this.right + ", bottom=" + this.bottom + '}';
    }

    @Deprecated
    public static Insets wrap(android.graphics.Insets insets0) {
        return Insets.toCompatInsets(insets0);
    }
}

