package androidx.lifecycle.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider.Factory;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001A%\u0010\u0000\u001A\u00020\u00012\u0017\u0010\u0002\u001A\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u001A7\u0010\u0007\u001A\u00020\u0005\"\n\b\u0000\u0010\b\u0018\u0001*\u00020\t*\u00020\u00042\u0019\b\b\u0010\u0007\u001A\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u0002H\b0\u0003¢\u0006\u0002\b\u0006H\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000B"}, d2 = {"viewModelFactory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "builder", "Lkotlin/Function1;", "Landroidx/lifecycle/viewmodel/InitializerViewModelFactoryBuilder;", "", "Lkotlin/ExtensionFunctionType;", "initializer", "VM", "Landroidx/lifecycle/ViewModel;", "Landroidx/lifecycle/viewmodel/CreationExtras;", "lifecycle-viewmodel_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class InitializerViewModelFactoryKt {
    public static final void initializer(InitializerViewModelFactoryBuilder initializerViewModelFactoryBuilder0, Function1 function10) {
        Intrinsics.checkNotNullParameter(initializerViewModelFactoryBuilder0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "initializer");
        Intrinsics.reifiedOperationMarker(4, "VM");
        initializerViewModelFactoryBuilder0.addInitializer(Reflection.getOrCreateKotlinClass(ViewModel.class), function10);
    }

    public static final Factory viewModelFactory(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "builder");
        InitializerViewModelFactoryBuilder initializerViewModelFactoryBuilder0 = new InitializerViewModelFactoryBuilder();
        function10.invoke(initializerViewModelFactoryBuilder0);
        return initializerViewModelFactoryBuilder0.build();
    }
}

