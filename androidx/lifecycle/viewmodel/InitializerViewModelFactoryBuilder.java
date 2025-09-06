package androidx.lifecycle.viewmodel;

import androidx.lifecycle.ViewModelProvider.Factory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@ViewModelFactoryDsl
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J7\u0010\u0006\u001A\u00020\u0007\"\b\b\u0000\u0010\b*\u00020\t2\f\u0010\n\u001A\b\u0012\u0004\u0012\u0002H\b0\u000B2\u0017\u0010\f\u001A\u0013\u0012\u0004\u0012\u00020\u000E\u0012\u0004\u0012\u0002H\b0\r¢\u0006\u0002\b\u000FJ\u0006\u0010\u0010\u001A\u00020\u0011R\u0018\u0010\u0003\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/lifecycle/viewmodel/InitializerViewModelFactoryBuilder;", "", "()V", "initializers", "", "Landroidx/lifecycle/viewmodel/ViewModelInitializer;", "addInitializer", "", "T", "Landroidx/lifecycle/ViewModel;", "clazz", "Lkotlin/reflect/KClass;", "initializer", "Lkotlin/Function1;", "Landroidx/lifecycle/viewmodel/CreationExtras;", "Lkotlin/ExtensionFunctionType;", "build", "Landroidx/lifecycle/ViewModelProvider$Factory;", "lifecycle-viewmodel_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class InitializerViewModelFactoryBuilder {
    private final List initializers;

    public InitializerViewModelFactoryBuilder() {
        this.initializers = new ArrayList();
    }

    public final void addInitializer(KClass kClass0, Function1 function10) {
        Intrinsics.checkNotNullParameter(kClass0, "clazz");
        Intrinsics.checkNotNullParameter(function10, "initializer");
        ViewModelInitializer viewModelInitializer0 = new ViewModelInitializer(JvmClassMappingKt.getJavaClass(kClass0), function10);
        this.initializers.add(viewModelInitializer0);
    }

    public final Factory build() {
        ViewModelInitializer[] arr_viewModelInitializer = (ViewModelInitializer[])this.initializers.toArray(new ViewModelInitializer[0]);
        return new InitializerViewModelFactory(((ViewModelInitializer[])Arrays.copyOf(arr_viewModelInitializer, arr_viewModelInitializer.length)));
    }
}

