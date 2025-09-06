package androidx.fragment.app;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.Lifecycle.State;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.SavedStateHandleSupport;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;

class FragmentViewLifecycleOwner implements HasDefaultViewModelProviderFactory, ViewModelStoreOwner, SavedStateRegistryOwner {
    private Factory mDefaultFactory;
    private final Fragment mFragment;
    private LifecycleRegistry mLifecycleRegistry;
    private SavedStateRegistryController mSavedStateRegistryController;
    private final ViewModelStore mViewModelStore;

    FragmentViewLifecycleOwner(Fragment fragment0, ViewModelStore viewModelStore0) {
        this.mLifecycleRegistry = null;
        this.mSavedStateRegistryController = null;
        this.mFragment = fragment0;
        this.mViewModelStore = viewModelStore0;
    }

    @Override  // androidx.lifecycle.HasDefaultViewModelProviderFactory
    public CreationExtras getDefaultViewModelCreationExtras() {
        Application application0;
        for(Context context0 = this.mFragment.requireContext().getApplicationContext(); true; context0 = ((ContextWrapper)context0).getBaseContext()) {
            application0 = null;
            if(!(context0 instanceof ContextWrapper)) {
                break;
            }
            if(context0 instanceof Application) {
                application0 = (Application)context0;
                break;
            }
        }
        CreationExtras creationExtras0 = new MutableCreationExtras();
        if(application0 != null) {
            ((MutableCreationExtras)creationExtras0).set(AndroidViewModelFactory.APPLICATION_KEY, application0);
        }
        ((MutableCreationExtras)creationExtras0).set(SavedStateHandleSupport.SAVED_STATE_REGISTRY_OWNER_KEY, this.mFragment);
        ((MutableCreationExtras)creationExtras0).set(SavedStateHandleSupport.VIEW_MODEL_STORE_OWNER_KEY, this);
        if(this.mFragment.getArguments() != null) {
            ((MutableCreationExtras)creationExtras0).set(SavedStateHandleSupport.DEFAULT_ARGS_KEY, this.mFragment.getArguments());
        }
        return creationExtras0;
    }

    @Override  // androidx.lifecycle.HasDefaultViewModelProviderFactory
    public Factory getDefaultViewModelProviderFactory() {
        Application application0;
        Factory viewModelProvider$Factory0 = this.mFragment.getDefaultViewModelProviderFactory();
        if(!viewModelProvider$Factory0.equals(this.mFragment.mDefaultFactory)) {
            this.mDefaultFactory = viewModelProvider$Factory0;
            return viewModelProvider$Factory0;
        }
        if(this.mDefaultFactory == null) {
            for(Context context0 = this.mFragment.requireContext().getApplicationContext(); true; context0 = ((ContextWrapper)context0).getBaseContext()) {
                application0 = null;
                if(!(context0 instanceof ContextWrapper)) {
                    break;
                }
                if(context0 instanceof Application) {
                    application0 = (Application)context0;
                    break;
                }
            }
            this.mDefaultFactory = new SavedStateViewModelFactory(application0, this.mFragment, this.mFragment.getArguments());
        }
        return this.mDefaultFactory;
    }

    @Override  // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        this.initialize();
        return this.mLifecycleRegistry;
    }

    @Override  // androidx.savedstate.SavedStateRegistryOwner
    public SavedStateRegistry getSavedStateRegistry() {
        this.initialize();
        return this.mSavedStateRegistryController.getSavedStateRegistry();
    }

    @Override  // androidx.lifecycle.ViewModelStoreOwner
    public ViewModelStore getViewModelStore() {
        this.initialize();
        return this.mViewModelStore;
    }

    void handleLifecycleEvent(Event lifecycle$Event0) {
        this.mLifecycleRegistry.handleLifecycleEvent(lifecycle$Event0);
    }

    void initialize() {
        if(this.mLifecycleRegistry == null) {
            this.mLifecycleRegistry = new LifecycleRegistry(this);
            SavedStateRegistryController savedStateRegistryController0 = SavedStateRegistryController.create(this);
            this.mSavedStateRegistryController = savedStateRegistryController0;
            savedStateRegistryController0.performAttach();
        }
    }

    boolean isInitialized() {
        return this.mLifecycleRegistry != null;
    }

    void performRestore(Bundle bundle0) {
        this.mSavedStateRegistryController.performRestore(bundle0);
    }

    void performSave(Bundle bundle0) {
        this.mSavedStateRegistryController.performSave(bundle0);
    }

    void setCurrentState(State lifecycle$State0) {
        this.mLifecycleRegistry.setCurrentState(lifecycle$State0);
    }
}

