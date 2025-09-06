package androidx.activity;

import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001A9\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\n\b\u0002\u0010\u0003\u001A\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0005\u001A\u00020\u00062\u0017\u0010\u0007\u001A\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\n¨\u0006\u000B"}, d2 = {"addCallback", "Landroidx/activity/OnBackPressedCallback;", "Landroidx/activity/OnBackPressedDispatcher;", "owner", "Landroidx/lifecycle/LifecycleOwner;", "enabled", "", "onBackPressed", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "activity_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class OnBackPressedDispatcherKt {
    public static final OnBackPressedCallback addCallback(OnBackPressedDispatcher onBackPressedDispatcher0, LifecycleOwner lifecycleOwner0, boolean z, Function1 function10) {
        Intrinsics.checkNotNullParameter(onBackPressedDispatcher0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "onBackPressed");
        androidx.activity.OnBackPressedDispatcherKt.addCallback.callback.1 onBackPressedDispatcherKt$addCallback$callback$10 = new OnBackPressedCallback(z) {
            @Override  // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                function10.invoke(this);
            }
        };
        if(lifecycleOwner0 != null) {
            onBackPressedDispatcher0.addCallback(lifecycleOwner0, onBackPressedDispatcherKt$addCallback$callback$10);
            return onBackPressedDispatcherKt$addCallback$callback$10;
        }
        onBackPressedDispatcher0.addCallback(onBackPressedDispatcherKt$addCallback$callback$10);
        return onBackPressedDispatcherKt$addCallback$callback$10;
    }

    public static OnBackPressedCallback addCallback$default(OnBackPressedDispatcher onBackPressedDispatcher0, LifecycleOwner lifecycleOwner0, boolean z, Function1 function10, int v, Object object0) {
        if((v & 1) != 0) {
            lifecycleOwner0 = null;
        }
        if((v & 2) != 0) {
            z = true;
        }
        return OnBackPressedDispatcherKt.addCallback(onBackPressedDispatcher0, lifecycleOwner0, z, function10);
    }
}

