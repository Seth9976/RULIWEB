package androidx.lifecycle;

import android.os.Bundle;
import androidx.lifecycle.viewmodel.CreationExtras.Key;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.InitializerViewModelFactoryBuilder;
import androidx.savedstate.SavedStateRegistry.SavedStateProvider;
import androidx.savedstate.SavedStateRegistryOwner;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u001A*\u0010\u0012\u001A\u00020\u00132\u0006\u0010\u0014\u001A\u00020\u00062\u0006\u0010\u0015\u001A\u00020\t2\u0006\u0010\u0016\u001A\u00020\u00042\b\u0010\u0017\u001A\u0004\u0018\u00010\u0002H\u0002\u001A\f\u0010\u0012\u001A\u00020\u0013*\u00020\u0018H\u0007\u001A\u001F\u0010\u0019\u001A\u00020\u001A\"\f\b\u0000\u0010\u001B*\u00020\u0006*\u00020\t*\u0002H\u001BH\u0007¢\u0006\u0002\u0010\u001C\"\u0016\u0010\u0000\u001A\b\u0012\u0004\u0012\u00020\u00020\u00018\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00060\u00018\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0007\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\b\u001A\b\u0012\u0004\u0012\u00020\t0\u00018\u0006X\u0087\u0004¢\u0006\u0002\n\u0000\"\u0018\u0010\n\u001A\u00020\u000B*\u00020\u00068@X\u0080\u0004¢\u0006\u0006\u001A\u0004\b\f\u0010\r\"\u0018\u0010\u000E\u001A\u00020\u000F*\u00020\t8@X\u0080\u0004¢\u0006\u0006\u001A\u0004\b\u0010\u0010\u0011¨\u0006\u001D"}, d2 = {"DEFAULT_ARGS_KEY", "Landroidx/lifecycle/viewmodel/CreationExtras$Key;", "Landroid/os/Bundle;", "SAVED_STATE_KEY", "", "SAVED_STATE_REGISTRY_OWNER_KEY", "Landroidx/savedstate/SavedStateRegistryOwner;", "VIEWMODEL_KEY", "VIEW_MODEL_STORE_OWNER_KEY", "Landroidx/lifecycle/ViewModelStoreOwner;", "savedStateHandlesProvider", "Landroidx/lifecycle/SavedStateHandlesProvider;", "getSavedStateHandlesProvider", "(Landroidx/savedstate/SavedStateRegistryOwner;)Landroidx/lifecycle/SavedStateHandlesProvider;", "savedStateHandlesVM", "Landroidx/lifecycle/SavedStateHandlesVM;", "getSavedStateHandlesVM", "(Landroidx/lifecycle/ViewModelStoreOwner;)Landroidx/lifecycle/SavedStateHandlesVM;", "createSavedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "savedStateRegistryOwner", "viewModelStoreOwner", "key", "defaultArgs", "Landroidx/lifecycle/viewmodel/CreationExtras;", "enableSavedStateHandles", "", "T", "(Landroidx/savedstate/SavedStateRegistryOwner;)V", "lifecycle-viewmodel-savedstate_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class SavedStateHandleSupport {
    public static final Key DEFAULT_ARGS_KEY = null;
    private static final String SAVED_STATE_KEY = "androidx.lifecycle.internal.SavedStateHandlesProvider";
    public static final Key SAVED_STATE_REGISTRY_OWNER_KEY = null;
    private static final String VIEWMODEL_KEY = "androidx.lifecycle.internal.SavedStateHandlesVM";
    public static final Key VIEW_MODEL_STORE_OWNER_KEY;

    static {
        SavedStateHandleSupport.SAVED_STATE_REGISTRY_OWNER_KEY = new SavedStateHandleSupport.SAVED_STATE_REGISTRY_OWNER_KEY.1();
        SavedStateHandleSupport.VIEW_MODEL_STORE_OWNER_KEY = new SavedStateHandleSupport.VIEW_MODEL_STORE_OWNER_KEY.1();
        SavedStateHandleSupport.DEFAULT_ARGS_KEY = new SavedStateHandleSupport.DEFAULT_ARGS_KEY.1();
    }

    public static final SavedStateHandle createSavedStateHandle(CreationExtras creationExtras0) {
        Intrinsics.checkNotNullParameter(creationExtras0, "<this>");
        SavedStateRegistryOwner savedStateRegistryOwner0 = (SavedStateRegistryOwner)creationExtras0.get(SavedStateHandleSupport.SAVED_STATE_REGISTRY_OWNER_KEY);
        if(savedStateRegistryOwner0 == null) {
            throw new IllegalArgumentException("CreationExtras must have a value by `SAVED_STATE_REGISTRY_OWNER_KEY`");
        }
        ViewModelStoreOwner viewModelStoreOwner0 = (ViewModelStoreOwner)creationExtras0.get(SavedStateHandleSupport.VIEW_MODEL_STORE_OWNER_KEY);
        if(viewModelStoreOwner0 == null) {
            throw new IllegalArgumentException("CreationExtras must have a value by `VIEW_MODEL_STORE_OWNER_KEY`");
        }
        Bundle bundle0 = (Bundle)creationExtras0.get(SavedStateHandleSupport.DEFAULT_ARGS_KEY);
        String s = (String)creationExtras0.get(NewInstanceFactory.VIEW_MODEL_KEY);
        if(s == null) {
            throw new IllegalArgumentException("CreationExtras must have a value by `VIEW_MODEL_KEY`");
        }
        return SavedStateHandleSupport.createSavedStateHandle(savedStateRegistryOwner0, viewModelStoreOwner0, s, bundle0);
    }

    private static final SavedStateHandle createSavedStateHandle(SavedStateRegistryOwner savedStateRegistryOwner0, ViewModelStoreOwner viewModelStoreOwner0, String s, Bundle bundle0) {
        SavedStateHandlesProvider savedStateHandlesProvider0 = SavedStateHandleSupport.getSavedStateHandlesProvider(savedStateRegistryOwner0);
        SavedStateHandlesVM savedStateHandlesVM0 = SavedStateHandleSupport.getSavedStateHandlesVM(viewModelStoreOwner0);
        SavedStateHandle savedStateHandle0 = (SavedStateHandle)savedStateHandlesVM0.getHandles().get(s);
        if(savedStateHandle0 == null) {
            Bundle bundle1 = savedStateHandlesProvider0.consumeRestoredStateForKey(s);
            SavedStateHandle savedStateHandle1 = SavedStateHandle.Companion.createHandle(bundle1, bundle0);
            savedStateHandlesVM0.getHandles().put(s, savedStateHandle1);
            return savedStateHandle1;
        }
        return savedStateHandle0;
    }

    public static final void enableSavedStateHandles(SavedStateRegistryOwner savedStateRegistryOwner0) {
        Intrinsics.checkNotNullParameter(savedStateRegistryOwner0, "<this>");
        State lifecycle$State0 = savedStateRegistryOwner0.getLifecycle().getCurrentState();
        if(lifecycle$State0 != State.INITIALIZED && lifecycle$State0 != State.CREATED) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if(savedStateRegistryOwner0.getSavedStateRegistry().getSavedStateProvider("androidx.lifecycle.internal.SavedStateHandlesProvider") == null) {
            SavedStateHandlesProvider savedStateHandlesProvider0 = new SavedStateHandlesProvider(savedStateRegistryOwner0.getSavedStateRegistry(), ((ViewModelStoreOwner)savedStateRegistryOwner0));
            savedStateRegistryOwner0.getSavedStateRegistry().registerSavedStateProvider("androidx.lifecycle.internal.SavedStateHandlesProvider", savedStateHandlesProvider0);
            savedStateRegistryOwner0.getLifecycle().addObserver(new SavedStateHandleAttacher(savedStateHandlesProvider0));
        }
    }

    public static final SavedStateHandlesProvider getSavedStateHandlesProvider(SavedStateRegistryOwner savedStateRegistryOwner0) {
        Intrinsics.checkNotNullParameter(savedStateRegistryOwner0, "<this>");
        SavedStateProvider savedStateRegistry$SavedStateProvider0 = savedStateRegistryOwner0.getSavedStateRegistry().getSavedStateProvider("androidx.lifecycle.internal.SavedStateHandlesProvider");
        SavedStateHandlesProvider savedStateHandlesProvider0 = savedStateRegistry$SavedStateProvider0 instanceof SavedStateHandlesProvider ? ((SavedStateHandlesProvider)savedStateRegistry$SavedStateProvider0) : null;
        if(savedStateHandlesProvider0 == null) {
            throw new IllegalStateException("enableSavedStateHandles() wasn\'t called prior to createSavedStateHandle() call");
        }
        return savedStateHandlesProvider0;
    }

    public static final SavedStateHandlesVM getSavedStateHandlesVM(ViewModelStoreOwner viewModelStoreOwner0) {
        Intrinsics.checkNotNullParameter(viewModelStoreOwner0, "<this>");
        InitializerViewModelFactoryBuilder initializerViewModelFactoryBuilder0 = new InitializerViewModelFactoryBuilder();
        initializerViewModelFactoryBuilder0.addInitializer(Reflection.getOrCreateKotlinClass(SavedStateHandlesVM.class), androidx.lifecycle.SavedStateHandleSupport.savedStateHandlesVM.1.1.INSTANCE);
        return (SavedStateHandlesVM)new ViewModelProvider(viewModelStoreOwner0, initializerViewModelFactoryBuilder0.build()).get("androidx.lifecycle.internal.SavedStateHandlesVM", SavedStateHandlesVM.class);

        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/lifecycle/SavedStateHandlesVM;", "Landroidx/lifecycle/viewmodel/CreationExtras;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class androidx.lifecycle.SavedStateHandleSupport.savedStateHandlesVM.1.1 extends Lambda implements Function1 {
            public static final androidx.lifecycle.SavedStateHandleSupport.savedStateHandlesVM.1.1 INSTANCE;

            static {
                androidx.lifecycle.SavedStateHandleSupport.savedStateHandlesVM.1.1.INSTANCE = new androidx.lifecycle.SavedStateHandleSupport.savedStateHandlesVM.1.1();
            }

            androidx.lifecycle.SavedStateHandleSupport.savedStateHandlesVM.1.1() {
                super(1);
            }

            public final SavedStateHandlesVM invoke(CreationExtras creationExtras0) {
                Intrinsics.checkNotNullParameter(creationExtras0, "$this$initializer");
                return new SavedStateHandlesVM();
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((CreationExtras)object0));
            }
        }

    }
}

