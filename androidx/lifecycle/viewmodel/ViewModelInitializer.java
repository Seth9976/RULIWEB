package androidx.lifecycle.viewmodel;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B,\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0017\u0010\u0006\u001A\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\b\t¢\u0006\u0002\u0010\nR\u001A\u0010\u0004\u001A\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR%\u0010\u0006\u001A\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\b\tX\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000E¨\u0006\u000F"}, d2 = {"Landroidx/lifecycle/viewmodel/ViewModelInitializer;", "T", "Landroidx/lifecycle/ViewModel;", "", "clazz", "Ljava/lang/Class;", "initializer", "Lkotlin/Function1;", "Landroidx/lifecycle/viewmodel/CreationExtras;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Class;Lkotlin/jvm/functions/Function1;)V", "getClazz$lifecycle_viewmodel_release", "()Ljava/lang/Class;", "getInitializer$lifecycle_viewmodel_release", "()Lkotlin/jvm/functions/Function1;", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ViewModelInitializer {
    private final Class clazz;
    private final Function1 initializer;

    public ViewModelInitializer(Class class0, Function1 function10) {
        Intrinsics.checkNotNullParameter(class0, "clazz");
        Intrinsics.checkNotNullParameter(function10, "initializer");
        super();
        this.clazz = class0;
        this.initializer = function10;
    }

    public final Class getClazz$lifecycle_viewmodel_release() {
        return this.clazz;
    }

    public final Function1 getInitializer$lifecycle_viewmodel_release() {
        return this.initializer;
    }
}

