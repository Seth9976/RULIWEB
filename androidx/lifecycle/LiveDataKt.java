package androidx.lifecycle;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u001A@\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0014\b\u0004\u0010\u0006\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\b0\u0007H\u0087\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\t"}, d2 = {"observe", "Landroidx/lifecycle/Observer;", "T", "Landroidx/lifecycle/LiveData;", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onChanged", "Lkotlin/Function1;", "", "lifecycle-livedata-core-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class LiveDataKt {
    @Deprecated(message = "This extension method is not required when using Kotlin 1.4. You should remove \"import androidx.lifecycle.observe\"")
    public static final Observer observe(LiveData liveData0, LifecycleOwner lifecycleOwner0, Function1 function10) {
        Intrinsics.checkNotNullParameter(liveData0, "<this>");
        Intrinsics.checkNotNullParameter(lifecycleOwner0, "owner");
        Intrinsics.checkNotNullParameter(function10, "onChanged");
        Observer observer0 = new Observer() {
            @Override  // androidx.lifecycle.Observer
            public final void onChanged(Object object0) {
                function10.invoke(object0);
            }
        };
        liveData0.observe(lifecycleOwner0, observer0);
        return observer0;
    }
}

