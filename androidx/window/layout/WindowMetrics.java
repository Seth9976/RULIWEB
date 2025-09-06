package androidx.window.layout;

import android.graphics.Rect;
import androidx.window.core.Bounds;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\u0018\u00002\u00020\u0001B\u000F\b\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000F\b\u0000\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\n\u001A\u00020\u000B2\b\u0010\f\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\r\u001A\u00020\u000EH\u0016J\b\u0010\u000F\u001A\u00020\u0010H\u0016R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001A\u00020\u00038F¢\u0006\u0006\u001A\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Landroidx/window/layout/WindowMetrics;", "", "bounds", "Landroid/graphics/Rect;", "(Landroid/graphics/Rect;)V", "_bounds", "Landroidx/window/core/Bounds;", "(Landroidx/window/core/Bounds;)V", "getBounds", "()Landroid/graphics/Rect;", "equals", "", "other", "hashCode", "", "toString", "", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class WindowMetrics {
    private final Bounds _bounds;

    public WindowMetrics(Rect rect0) {
        Intrinsics.checkNotNullParameter(rect0, "bounds");
        this(new Bounds(rect0));
    }

    public WindowMetrics(Bounds bounds0) {
        Intrinsics.checkNotNullParameter(bounds0, "_bounds");
        super();
        this._bounds = bounds0;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 == null || !Intrinsics.areEqual(this.getClass(), object0.getClass()) ? false : Intrinsics.areEqual(this._bounds, ((WindowMetrics)object0)._bounds);
    }

    public final Rect getBounds() {
        return this._bounds.toRect();
    }

    @Override
    public int hashCode() {
        return this._bounds.hashCode();
    }

    @Override
    public String toString() {
        return "WindowMetrics { bounds: " + this.getBounds() + " }";
    }
}

