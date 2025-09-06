package androidx.core.util;

import android.util.SizeF;

public final class SizeFCompat {
    static final class Api21Impl {
        static SizeF toSizeF(SizeFCompat sizeFCompat0) {
            Preconditions.checkNotNull(sizeFCompat0);
            return new SizeF(sizeFCompat0.getWidth(), sizeFCompat0.getHeight());
        }

        static SizeFCompat toSizeFCompat(SizeF sizeF0) {
            Preconditions.checkNotNull(sizeF0);
            return new SizeFCompat(sizeF0.getWidth(), sizeF0.getHeight());
        }
    }

    private final float mHeight;
    private final float mWidth;

    public SizeFCompat(float f, float f1) {
        this.mWidth = Preconditions.checkArgumentFinite(f, "width");
        this.mHeight = Preconditions.checkArgumentFinite(f1, "height");
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof SizeFCompat ? ((SizeFCompat)object0).mWidth == this.mWidth && ((SizeFCompat)object0).mHeight == this.mHeight : false;
    }

    public float getHeight() {
        return this.mHeight;
    }

    public float getWidth() {
        return this.mWidth;
    }

    @Override
    public int hashCode() {
        return Float.floatToIntBits(this.mWidth) ^ Float.floatToIntBits(this.mHeight);
    }

    public SizeF toSizeF() {
        return Api21Impl.toSizeF(this);
    }

    public static SizeFCompat toSizeFCompat(SizeF sizeF0) {
        return Api21Impl.toSizeFCompat(sizeF0);
    }

    @Override
    public String toString() {
        return this.mWidth + "x" + this.mHeight;
    }
}

