package androidx.lifecycle;

import android.app.Application;
import android.os.Bundle;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import java.lang.reflect.Constructor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0016¢\u0006\u0002\u0010\u0003B\u0019\b\u0016\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bB#\b\u0017\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\b\u0010\t\u001A\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000BJ%\u0010\u0011\u001A\u0002H\u0012\"\b\b\u0000\u0010\u0012*\u00020\u00132\f\u0010\u0014\u001A\b\u0012\u0004\u0012\u0002H\u00120\u0015H\u0016¢\u0006\u0002\u0010\u0016J-\u0010\u0011\u001A\u0002H\u0012\"\b\b\u0000\u0010\u0012*\u00020\u00132\f\u0010\u0014\u001A\b\u0012\u0004\u0012\u0002H\u00120\u00152\u0006\u0010\u0017\u001A\u00020\u0018H\u0016¢\u0006\u0002\u0010\u0019J+\u0010\u0011\u001A\u0002H\u0012\"\b\b\u0000\u0010\u0012*\u00020\u00132\u0006\u0010\u001A\u001A\u00020\u001B2\f\u0010\u0014\u001A\b\u0012\u0004\u0012\u0002H\u00120\u0015¢\u0006\u0002\u0010\u001CJ\u0010\u0010\u001D\u001A\u00020\u001E2\u0006\u0010\u001F\u001A\u00020\u0013H\u0017R\u0010\u0010\u0004\u001A\u0004\u0018\u00010\u0005X\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001A\u0004\u0018\u00010\nX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001A\u0004\u0018\u00010\u000EX\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u000F\u001A\u0004\u0018\u00010\u0010X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Landroidx/lifecycle/SavedStateViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$OnRequeryFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "()V", "application", "Landroid/app/Application;", "owner", "Landroidx/savedstate/SavedStateRegistryOwner;", "(Landroid/app/Application;Landroidx/savedstate/SavedStateRegistryOwner;)V", "defaultArgs", "Landroid/os/Bundle;", "(Landroid/app/Application;Landroidx/savedstate/SavedStateRegistryOwner;Landroid/os/Bundle;)V", "factory", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "savedStateRegistry", "Landroidx/savedstate/SavedStateRegistry;", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "extras", "Landroidx/lifecycle/viewmodel/CreationExtras;", "(Ljava/lang/Class;Landroidx/lifecycle/viewmodel/CreationExtras;)Landroidx/lifecycle/ViewModel;", "key", "", "(Ljava/lang/String;Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "onRequery", "", "viewModel", "lifecycle-viewmodel-savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class SavedStateViewModelFactory extends OnRequeryFactory implements Factory {
    private Application application;
    private Bundle defaultArgs;
    private final Factory factory;
    private Lifecycle lifecycle;
    private SavedStateRegistry savedStateRegistry;

    public SavedStateViewModelFactory() {
        this.factory = new AndroidViewModelFactory();
    }

    public SavedStateViewModelFactory(Application application0, SavedStateRegistryOwner savedStateRegistryOwner0) {
        Intrinsics.checkNotNullParameter(savedStateRegistryOwner0, "owner");
        this(application0, savedStateRegistryOwner0, null);
    }

    public SavedStateViewModelFactory(Application application0, SavedStateRegistryOwner savedStateRegistryOwner0, Bundle bundle0) {
        Intrinsics.checkNotNullParameter(savedStateRegistryOwner0, "owner");
        super();
        this.savedStateRegistry = savedStateRegistryOwner0.getSavedStateRegistry();
        this.lifecycle = savedStateRegistryOwner0.getLifecycle();
        this.defaultArgs = bundle0;
        this.application = application0;
        this.factory = application0 == null ? new AndroidViewModelFactory() : AndroidViewModelFactory.Companion.getInstance(application0);
    }

    @Override  // androidx.lifecycle.ViewModelProvider$Factory
    public ViewModel create(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "modelClass");
        String s = class0.getCanonicalName();
        if(s == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        return this.create(s, class0);
    }

    @Override  // androidx.lifecycle.ViewModelProvider$Factory
    public ViewModel create(Class class0, CreationExtras creationExtras0) {
        Intrinsics.checkNotNullParameter(class0, "modelClass");
        Intrinsics.checkNotNullParameter(creationExtras0, "extras");
        String s = (String)creationExtras0.get(NewInstanceFactory.VIEW_MODEL_KEY);
        if(s == null) {
            throw new IllegalStateException("VIEW_MODEL_KEY must always be provided by ViewModelProvider");
        }
        if(creationExtras0.get(SavedStateHandleSupport.SAVED_STATE_REGISTRY_OWNER_KEY) != null && creationExtras0.get(SavedStateHandleSupport.VIEW_MODEL_STORE_OWNER_KEY) != null) {
            Application application0 = (Application)creationExtras0.get(AndroidViewModelFactory.APPLICATION_KEY);
            boolean z = AndroidViewModel.class.isAssignableFrom(class0);
            Constructor constructor0 = !z || application0 == null ? SavedStateViewModelFactoryKt.findMatchingConstructor(class0, SavedStateViewModelFactoryKt.VIEWMODEL_SIGNATURE) : SavedStateViewModelFactoryKt.findMatchingConstructor(class0, SavedStateViewModelFactoryKt.ANDROID_VIEWMODEL_SIGNATURE);
            if(constructor0 == null) {
                return this.factory.create(class0, creationExtras0);
            }
            return !z || application0 == null ? SavedStateViewModelFactoryKt.newInstance(class0, constructor0, new Object[]{SavedStateHandleSupport.createSavedStateHandle(creationExtras0)}) : SavedStateViewModelFactoryKt.newInstance(class0, constructor0, new Object[]{application0, SavedStateHandleSupport.createSavedStateHandle(creationExtras0)});
        }
        if(this.lifecycle == null) {
            throw new IllegalStateException("SAVED_STATE_REGISTRY_OWNER_KEY andVIEW_MODEL_STORE_OWNER_KEY must be provided in the creation extras tosuccessfully create a ViewModel.");
        }
        return this.create(s, class0);
    }

    public final ViewModel create(String s, Class class0) {
        ViewModel viewModel0;
        Intrinsics.checkNotNullParameter(s, "key");
        Intrinsics.checkNotNullParameter(class0, "modelClass");
        Lifecycle lifecycle0 = this.lifecycle;
        if(lifecycle0 == null) {
            throw new UnsupportedOperationException("SavedStateViewModelFactory constructed with empty constructor supports only calls to create(modelClass: Class<T>, extras: CreationExtras).");
        }
        boolean z = AndroidViewModel.class.isAssignableFrom(class0);
        Constructor constructor0 = !z || this.application == null ? SavedStateViewModelFactoryKt.findMatchingConstructor(class0, SavedStateViewModelFactoryKt.access$getVIEWMODEL_SIGNATURE$p()) : SavedStateViewModelFactoryKt.findMatchingConstructor(class0, SavedStateViewModelFactoryKt.access$getANDROID_VIEWMODEL_SIGNATURE$p());
        if(constructor0 == null) {
            return this.application == null ? NewInstanceFactory.Companion.getInstance().create(class0) : this.factory.create(class0);
        }
        SavedStateRegistry savedStateRegistry0 = this.savedStateRegistry;
        Intrinsics.checkNotNull(savedStateRegistry0);
        SavedStateHandleController savedStateHandleController0 = LegacySavedStateHandleController.create(savedStateRegistry0, lifecycle0, s, this.defaultArgs);
        if(z) {
            Application application0 = this.application;
            if(application0 == null) {
                viewModel0 = SavedStateViewModelFactoryKt.newInstance(class0, constructor0, new Object[]{savedStateHandleController0.getHandle()});
            }
            else {
                Intrinsics.checkNotNull(application0);
                viewModel0 = SavedStateViewModelFactoryKt.newInstance(class0, constructor0, new Object[]{application0, savedStateHandleController0.getHandle()});
            }
        }
        else {
            viewModel0 = SavedStateViewModelFactoryKt.newInstance(class0, constructor0, new Object[]{savedStateHandleController0.getHandle()});
        }
        viewModel0.setTagIfAbsent("androidx.lifecycle.savedstate.vm.tag", savedStateHandleController0);
        return viewModel0;
    }

    @Override  // androidx.lifecycle.ViewModelProvider$OnRequeryFactory
    public void onRequery(ViewModel viewModel0) {
        Intrinsics.checkNotNullParameter(viewModel0, "viewModel");
        if(this.lifecycle != null) {
            SavedStateRegistry savedStateRegistry0 = this.savedStateRegistry;
            Intrinsics.checkNotNull(savedStateRegistry0);
            Lifecycle lifecycle0 = this.lifecycle;
            Intrinsics.checkNotNull(lifecycle0);
            LegacySavedStateHandleController.attachHandleIfNeeded(viewModel0, savedStateRegistry0, lifecycle0);
        }
    }
}

