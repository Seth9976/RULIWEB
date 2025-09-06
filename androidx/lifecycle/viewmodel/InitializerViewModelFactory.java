package androidx.lifecycle.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory.-CC;
import androidx.lifecycle.ViewModelProvider.Factory;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B!\u0012\u001A\u0010\u0002\u001A\u000E\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003\"\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0002\u0010\u0005J-\u0010\u0007\u001A\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t2\f\u0010\n\u001A\b\u0012\u0004\u0012\u0002H\b0\u000B2\u0006\u0010\f\u001A\u00020\rH\u0016¢\u0006\u0002\u0010\u000ER\u001C\u0010\u0002\u001A\u000E\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\u000F"}, d2 = {"Landroidx/lifecycle/viewmodel/InitializerViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "initializers", "", "Landroidx/lifecycle/viewmodel/ViewModelInitializer;", "([Landroidx/lifecycle/viewmodel/ViewModelInitializer;)V", "[Landroidx/lifecycle/viewmodel/ViewModelInitializer;", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "extras", "Landroidx/lifecycle/viewmodel/CreationExtras;", "(Ljava/lang/Class;Landroidx/lifecycle/viewmodel/CreationExtras;)Landroidx/lifecycle/ViewModel;", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class InitializerViewModelFactory implements Factory {
    private final ViewModelInitializer[] initializers;

    public InitializerViewModelFactory(ViewModelInitializer[] arr_viewModelInitializer) {
        Intrinsics.checkNotNullParameter(arr_viewModelInitializer, "initializers");
        super();
        this.initializers = arr_viewModelInitializer;
    }

    @Override  // androidx.lifecycle.ViewModelProvider$Factory
    public ViewModel create(Class class0) {
        return ViewModelProvider.Factory.-CC.$default$create(this, class0);
    }

    @Override  // androidx.lifecycle.ViewModelProvider$Factory
    public ViewModel create(Class class0, CreationExtras creationExtras0) {
        Intrinsics.checkNotNullParameter(class0, "modelClass");
        Intrinsics.checkNotNullParameter(creationExtras0, "extras");
        ViewModelInitializer[] arr_viewModelInitializer = this.initializers;
        ViewModel viewModel0 = null;
        for(int v = 0; v < arr_viewModelInitializer.length; ++v) {
            ViewModelInitializer viewModelInitializer0 = arr_viewModelInitializer[v];
            if(Intrinsics.areEqual(viewModelInitializer0.getClazz$lifecycle_viewmodel_release(), class0)) {
                Object object0 = viewModelInitializer0.getInitializer$lifecycle_viewmodel_release().invoke(creationExtras0);
                viewModel0 = object0 instanceof ViewModel ? ((ViewModel)object0) : null;
            }
        }
        if(viewModel0 == null) {
            throw new IllegalArgumentException("No initializer set for given class " + class0.getName());
        }
        return viewModel0;
    }
}

