package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.SupervisorKt;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001A\u00020\u0003*\u00020\u00048F¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"JOB_KEY", "", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "Landroidx/lifecycle/ViewModel;", "getViewModelScope", "(Landroidx/lifecycle/ViewModel;)Lkotlinx/coroutines/CoroutineScope;", "lifecycle-viewmodel-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ViewModelKt {
    private static final String JOB_KEY = "androidx.lifecycle.ViewModelCoroutineScope.JOB_KEY";

    public static final CoroutineScope getViewModelScope(ViewModel viewModel0) {
        Intrinsics.checkNotNullParameter(viewModel0, "<this>");
        CoroutineScope coroutineScope0 = (CoroutineScope)viewModel0.getTag("androidx.lifecycle.ViewModelCoroutineScope.JOB_KEY");
        if(coroutineScope0 != null) {
            return coroutineScope0;
        }
        Object object0 = viewModel0.setTagIfAbsent("androidx.lifecycle.ViewModelCoroutineScope.JOB_KEY", new CloseableCoroutineScope(SupervisorKt.SupervisorJob$default(null, 1, null).plus(Dispatchers.getMain().getImmediate())));
        Intrinsics.checkNotNullExpressionValue(object0, "setTagIfAbsent(\n        …Main.immediate)\n        )");
        return (CoroutineScope)object0;
    }
}

