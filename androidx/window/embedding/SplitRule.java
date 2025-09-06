package androidx.window.embedding;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.view.WindowMetrics;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0017\u0018\u00002\u00020\u0001:\u0002\u0017\u0018B/\b\u0000\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001A\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001A\u00020\u0003¢\u0006\u0002\u0010\bJ\u000E\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u0012J\u0013\u0010\u0013\u001A\u00020\u00102\b\u0010\u0014\u001A\u0004\u0018\u00010\u0015H\u0096\u0002J\b\u0010\u0016\u001A\u00020\u0003H\u0016R\u0011\u0010\u0007\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\nR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\nR\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000E¨\u0006\u0019"}, d2 = {"Landroidx/window/embedding/SplitRule;", "Landroidx/window/embedding/EmbeddingRule;", "minWidth", "", "minSmallestWidth", "splitRatio", "", "layoutDirection", "(IIFI)V", "getLayoutDirection", "()I", "getMinSmallestWidth", "getMinWidth", "getSplitRatio", "()F", "checkParentMetrics", "", "parentMetrics", "Landroid/view/WindowMetrics;", "equals", "other", "", "hashCode", "Api30Impl", "LayoutDir", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public class SplitRule extends EmbeddingRule {
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/window/embedding/SplitRule$Api30Impl;", "", "()V", "getBounds", "Landroid/graphics/Rect;", "windowMetrics", "Landroid/view/WindowMetrics;", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Api30Impl {
        public static final Api30Impl INSTANCE;

        static {
            Api30Impl.INSTANCE = new Api30Impl();
        }

        public final Rect getBounds(WindowMetrics windowMetrics0) {
            Intrinsics.checkNotNullParameter(windowMetrics0, "windowMetrics");
            Rect rect0 = windowMetrics0.getBounds();
            Intrinsics.checkNotNullExpressionValue(rect0, "windowMetrics.bounds");
            return rect0;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001B\n\u0000\b\u0081\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Landroidx/window/embedding/SplitRule$LayoutDir;", "", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
    public @interface LayoutDir {
    }

    private final int layoutDirection;
    private final int minSmallestWidth;
    private final int minWidth;
    private final float splitRatio;

    public SplitRule() {
        this(0, 0, 0.0f, 0, 15, null);
    }

    public SplitRule(int v, int v1, float f, int v2) {
        this.minWidth = v;
        this.minSmallestWidth = v1;
        this.splitRatio = f;
        this.layoutDirection = v2;
    }

    public SplitRule(int v, int v1, float f, int v2, int v3, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v3 & 1) != 0) {
            v = 0;
        }
        if((v3 & 2) != 0) {
            v1 = 0;
        }
        if((v3 & 4) != 0) {
            f = 0.5f;
        }
        if((v3 & 8) != 0) {
            v2 = 3;
        }
        this(v, v1, f, v2);
    }

    public final boolean checkParentMetrics(WindowMetrics windowMetrics0) {
        Intrinsics.checkNotNullParameter(windowMetrics0, "parentMetrics");
        if(Build.VERSION.SDK_INT <= 30) {
            return false;
        }
        Rect rect0 = Api30Impl.INSTANCE.getBounds(windowMetrics0);
        return (this.minWidth == 0 || rect0.width() >= this.minWidth) && (this.minSmallestWidth == 0 || Math.min(rect0.width(), rect0.height()) >= this.minSmallestWidth);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof SplitRule)) {
            return false;
        }
        if(this.minWidth != ((SplitRule)object0).minWidth) {
            return false;
        }
        return this.minSmallestWidth == ((SplitRule)object0).minSmallestWidth ? this.splitRatio == ((SplitRule)object0).splitRatio && this.layoutDirection == ((SplitRule)object0).layoutDirection : false;
    }

    public final int getLayoutDirection() {
        return this.layoutDirection;
    }

    public final int getMinSmallestWidth() {
        return this.minSmallestWidth;
    }

    public final int getMinWidth() {
        return this.minWidth;
    }

    public final float getSplitRatio() {
        return this.splitRatio;
    }

    @Override
    public int hashCode() {
        return ((this.minWidth * 0x1F + this.minSmallestWidth) * 0x1F + Float.floatToIntBits(this.splitRatio)) * 0x1F + this.layoutDirection;
    }
}

