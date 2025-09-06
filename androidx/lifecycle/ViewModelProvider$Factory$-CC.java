package androidx.lifecycle;

import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.ViewModelInitializer;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

public final class ViewModelProvider.Factory.-CC {
    public static ViewModel $default$create(Factory _this, Class class0) {
        Intrinsics.checkNotNullParameter(class0, "modelClass");
        throw new UnsupportedOperationException("Factory.create(String) is unsupported.  This Factory requires `CreationExtras` to be passed into `create` method.");
    }

    public static ViewModel $default$create(Factory _this, Class class0, CreationExtras creationExtras0) {
        Intrinsics.checkNotNullParameter(class0, "modelClass");
        Intrinsics.checkNotNullParameter(creationExtras0, "extras");
        return _this.create(class0);
    }

    static {
    }

    @JvmStatic
    public static Factory from(ViewModelInitializer[] arr_viewModelInitializer) {
        return Factory.Companion.from(arr_viewModelInitializer);
    }
}

