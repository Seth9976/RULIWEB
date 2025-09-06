package androidx.lifecycle;

import android.app.Application;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0017\u0010\u0005\u001A\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0003H\u0016¢\u0006\u0002\u0010\u0007R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Landroidx/lifecycle/AndroidViewModel;", "Landroidx/lifecycle/ViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "getApplication", "T", "()Landroid/app/Application;", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class AndroidViewModel extends ViewModel {
    private final Application application;

    public AndroidViewModel(Application application0) {
        Intrinsics.checkNotNullParameter(application0, "application");
        super();
        this.application = application0;
    }

    public Application getApplication() {
        Intrinsics.checkNotNull(this.application, "null cannot be cast to non-null type T of androidx.lifecycle.AndroidViewModel.getApplication");
        return this.application;
    }
}

