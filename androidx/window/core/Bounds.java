package androidx.window.core;

import android.graphics.Rect;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000B\n\u0002\b\f\n\u0002\u0010\u000E\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u000F\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004B%\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\u0006\u0012\u0006\u0010\b\u001A\u00020\u0006\u0012\u0006\u0010\t\u001A\u00020\u0006¢\u0006\u0002\u0010\nJ\u0013\u0010\u0018\u001A\u00020\u00102\b\u0010\u0019\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u001A\u001A\u00020\u0006H\u0016J\u0006\u0010\u001B\u001A\u00020\u0003J\b\u0010\u001C\u001A\u00020\u001DH\u0016R\u0011\u0010\t\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u0011\u0010\r\u001A\u00020\u00068F¢\u0006\u0006\u001A\u0004\b\u000E\u0010\fR\u0011\u0010\u000F\u001A\u00020\u00108F¢\u0006\u0006\u001A\u0004\b\u000F\u0010\u0011R\u0011\u0010\u0012\u001A\u00020\u00108F¢\u0006\u0006\u001A\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\fR\u0011\u0010\b\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u0014\u0010\fR\u0011\u0010\u0007\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u0015\u0010\fR\u0011\u0010\u0016\u001A\u00020\u00068F¢\u0006\u0006\u001A\u0004\b\u0017\u0010\f¨\u0006\u001E"}, d2 = {"Landroidx/window/core/Bounds;", "", "rect", "Landroid/graphics/Rect;", "(Landroid/graphics/Rect;)V", "left", "", "top", "right", "bottom", "(IIII)V", "getBottom", "()I", "height", "getHeight", "isEmpty", "", "()Z", "isZero", "getLeft", "getRight", "getTop", "width", "getWidth", "equals", "other", "hashCode", "toRect", "toString", "", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class Bounds {
    private final int bottom;
    private final int left;
    private final int right;
    private final int top;

    public Bounds(int v, int v1, int v2, int v3) {
        this.left = v;
        this.top = v1;
        this.right = v2;
        this.bottom = v3;
    }

    public Bounds(Rect rect0) {
        Intrinsics.checkNotNullParameter(rect0, "rect");
        this(rect0.left, rect0.top, rect0.right, rect0.bottom);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!Intrinsics.areEqual(this.getClass(), (object0 == null ? null : object0.getClass()))) {
            return false;
        }
        if(object0 == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.window.core.Bounds");
        }
        if(this.left != ((Bounds)object0).left) {
            return false;
        }
        if(this.top != ((Bounds)object0).top) {
            return false;
        }
        return this.right == ((Bounds)object0).right ? this.bottom == ((Bounds)object0).bottom : false;
    }

    public final int getBottom() {
        return this.bottom;
    }

    public final int getHeight() {
        return this.bottom - this.top;
    }

    public final int getLeft() {
        return this.left;
    }

    public final int getRight() {
        return this.right;
    }

    public final int getTop() {
        return this.top;
    }

    public final int getWidth() {
        return this.right - this.left;
    }

    @Override
    public int hashCode() {
        return ((this.left * 0x1F + this.top) * 0x1F + this.right) * 0x1F + this.bottom;
    }

    public final boolean isEmpty() {
        return this.getHeight() == 0 || this.getWidth() == 0;
    }

    public final boolean isZero() {
        return this.getHeight() == 0 && this.getWidth() == 0;
    }

    public final Rect toRect() {
        return new Rect(this.left, this.top, this.right, this.bottom);
    }

    @Override
    public String toString() {
        return "Bounds { [" + this.left + ',' + this.top + ',' + this.right + ',' + this.bottom + "] }";
    }
}

