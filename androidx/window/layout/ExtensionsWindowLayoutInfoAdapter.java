package androidx.window.layout;

import android.app.Activity;
import android.graphics.Rect;
import androidx.window.core.Bounds;
import androidx.window.extensions.layout.DisplayFeature;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001F\u0010\u0003\u001A\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0000¢\u0006\u0002\b\tJ\u001D\u0010\u0003\u001A\u00020\n2\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u000B\u001A\u00020\fH\u0000¢\u0006\u0002\b\tJ\u0018\u0010\r\u001A\u00020\u000E2\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u000F\u001A\u00020\u0010H\u0002¨\u0006\u0011"}, d2 = {"Landroidx/window/layout/ExtensionsWindowLayoutInfoAdapter;", "", "()V", "translate", "Landroidx/window/layout/FoldingFeature;", "activity", "Landroid/app/Activity;", "oemFeature", "Landroidx/window/extensions/layout/FoldingFeature;", "translate$window_release", "Landroidx/window/layout/WindowLayoutInfo;", "info", "Landroidx/window/extensions/layout/WindowLayoutInfo;", "validBounds", "", "bounds", "Landroidx/window/core/Bounds;", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class ExtensionsWindowLayoutInfoAdapter {
    public static final ExtensionsWindowLayoutInfoAdapter INSTANCE;

    static {
        ExtensionsWindowLayoutInfoAdapter.INSTANCE = new ExtensionsWindowLayoutInfoAdapter();
    }

    public final FoldingFeature translate$window_release(Activity activity0, androidx.window.extensions.layout.FoldingFeature foldingFeature0) {
        State foldingFeature$State0;
        Type hardwareFoldingFeature$Type0;
        Intrinsics.checkNotNullParameter(activity0, "activity");
        Intrinsics.checkNotNullParameter(foldingFeature0, "oemFeature");
        switch(foldingFeature0.getType()) {
            case 1: {
                hardwareFoldingFeature$Type0 = Type.Companion.getFOLD();
                break;
            }
            case 2: {
                hardwareFoldingFeature$Type0 = Type.Companion.getHINGE();
                break;
            }
            default: {
                return null;
            }
        }
        switch(foldingFeature0.getState()) {
            case 1: {
                foldingFeature$State0 = State.FLAT;
                break;
            }
            case 2: {
                foldingFeature$State0 = State.HALF_OPENED;
                break;
            }
            default: {
                return null;
            }
        }
        Rect rect0 = foldingFeature0.getBounds();
        Intrinsics.checkNotNullExpressionValue(rect0, "oemFeature.bounds");
        if(this.validBounds(activity0, new Bounds(rect0))) {
            Rect rect1 = foldingFeature0.getBounds();
            Intrinsics.checkNotNullExpressionValue(rect1, "oemFeature.bounds");
            return new HardwareFoldingFeature(new Bounds(rect1), hardwareFoldingFeature$Type0, foldingFeature$State0);
        }
        return null;
    }

    public final WindowLayoutInfo translate$window_release(Activity activity0, androidx.window.extensions.layout.WindowLayoutInfo windowLayoutInfo0) {
        FoldingFeature foldingFeature0;
        Intrinsics.checkNotNullParameter(activity0, "activity");
        Intrinsics.checkNotNullParameter(windowLayoutInfo0, "info");
        List list0 = windowLayoutInfo0.getDisplayFeatures();
        Intrinsics.checkNotNullExpressionValue(list0, "info.displayFeatures");
        Collection collection0 = new ArrayList();
        for(Object object0: list0) {
            DisplayFeature displayFeature0 = (DisplayFeature)object0;
            if(displayFeature0 instanceof androidx.window.extensions.layout.FoldingFeature) {
                Intrinsics.checkNotNullExpressionValue(displayFeature0, "feature");
                foldingFeature0 = ExtensionsWindowLayoutInfoAdapter.INSTANCE.translate$window_release(activity0, ((androidx.window.extensions.layout.FoldingFeature)displayFeature0));
            }
            else {
                foldingFeature0 = null;
            }
            if(foldingFeature0 != null) {
                collection0.add(foldingFeature0);
            }
        }
        return new WindowLayoutInfo(((List)collection0));
    }

    private final boolean validBounds(Activity activity0, Bounds bounds0) {
        Rect rect0 = WindowMetricsCalculatorCompat.INSTANCE.computeCurrentWindowMetrics(activity0).getBounds();
        if(bounds0.isZero()) {
            return false;
        }
        if(bounds0.getWidth() != rect0.width() && bounds0.getHeight() != rect0.height()) {
            return false;
        }
        return bounds0.getWidth() >= rect0.width() || bounds0.getHeight() >= rect0.height() ? bounds0.getWidth() != rect0.width() || bounds0.getHeight() != rect0.height() : false;
    }
}

