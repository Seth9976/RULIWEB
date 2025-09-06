package androidx.fragment.app;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback;
import androidx.core.app.ActivityCompat.RequestPermissionsRequestCodeValidator;
import androidx.core.app.ActivityCompat;
import androidx.core.app.OnMultiWindowModeChangedProvider;
import androidx.core.app.OnPictureInPictureModeChangedProvider;
import androidx.core.app.SharedElementCallback;
import androidx.core.content.OnConfigurationChangedProvider;
import androidx.core.content.OnTrimMemoryProvider;
import androidx.core.util.Consumer;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.Lifecycle.State;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.loader.app.LoaderManager;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class FragmentActivity extends ComponentActivity implements OnRequestPermissionsResultCallback, RequestPermissionsRequestCodeValidator {
    class HostCallbacks extends FragmentHostCallback implements OnBackPressedDispatcherOwner, ActivityResultRegistryOwner, OnMultiWindowModeChangedProvider, OnPictureInPictureModeChangedProvider, OnConfigurationChangedProvider, OnTrimMemoryProvider, MenuHost, FragmentOnAttachListener, ViewModelStoreOwner, SavedStateRegistryOwner {
        @Override  // androidx.core.view.MenuHost
        public void addMenuProvider(MenuProvider menuProvider0) {
            FragmentActivity.this.addMenuProvider(menuProvider0);
        }

        @Override  // androidx.core.view.MenuHost
        public void addMenuProvider(MenuProvider menuProvider0, LifecycleOwner lifecycleOwner0) {
            FragmentActivity.this.addMenuProvider(menuProvider0, lifecycleOwner0);
        }

        @Override  // androidx.core.view.MenuHost
        public void addMenuProvider(MenuProvider menuProvider0, LifecycleOwner lifecycleOwner0, State lifecycle$State0) {
            FragmentActivity.this.addMenuProvider(menuProvider0, lifecycleOwner0, lifecycle$State0);
        }

        @Override  // androidx.core.content.OnConfigurationChangedProvider
        public void addOnConfigurationChangedListener(Consumer consumer0) {
            FragmentActivity.this.addOnConfigurationChangedListener(consumer0);
        }

        @Override  // androidx.core.app.OnMultiWindowModeChangedProvider
        public void addOnMultiWindowModeChangedListener(Consumer consumer0) {
            FragmentActivity.this.addOnMultiWindowModeChangedListener(consumer0);
        }

        @Override  // androidx.core.app.OnPictureInPictureModeChangedProvider
        public void addOnPictureInPictureModeChangedListener(Consumer consumer0) {
            FragmentActivity.this.addOnPictureInPictureModeChangedListener(consumer0);
        }

        @Override  // androidx.core.content.OnTrimMemoryProvider
        public void addOnTrimMemoryListener(Consumer consumer0) {
            FragmentActivity.this.addOnTrimMemoryListener(consumer0);
        }

        @Override  // androidx.activity.result.ActivityResultRegistryOwner
        public ActivityResultRegistry getActivityResultRegistry() {
            return FragmentActivity.this.getActivityResultRegistry();
        }

        @Override  // androidx.lifecycle.LifecycleOwner
        public Lifecycle getLifecycle() {
            return FragmentActivity.this.mFragmentLifecycleRegistry;
        }

        @Override  // androidx.activity.OnBackPressedDispatcherOwner
        public OnBackPressedDispatcher getOnBackPressedDispatcher() {
            return FragmentActivity.this.getOnBackPressedDispatcher();
        }

        @Override  // androidx.savedstate.SavedStateRegistryOwner
        public SavedStateRegistry getSavedStateRegistry() {
            return FragmentActivity.this.getSavedStateRegistry();
        }

        @Override  // androidx.lifecycle.ViewModelStoreOwner
        public ViewModelStore getViewModelStore() {
            return FragmentActivity.this.getViewModelStore();
        }

        @Override  // androidx.core.view.MenuHost
        public void invalidateMenu() {
            FragmentActivity.this.invalidateMenu();
        }

        @Override  // androidx.fragment.app.FragmentOnAttachListener
        public void onAttachFragment(FragmentManager fragmentManager0, Fragment fragment0) {
        }

        @Override  // androidx.fragment.app.FragmentHostCallback
        public void onDump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
            FragmentActivity.this.dump(s, fileDescriptor0, printWriter0, arr_s);
        }

        @Override  // androidx.fragment.app.FragmentHostCallback
        public View onFindViewById(int v) {
            return FragmentActivity.this.findViewById(v);
        }

        public FragmentActivity onGetHost() {
            return FragmentActivity.this;
        }

        @Override  // androidx.fragment.app.FragmentHostCallback
        public Object onGetHost() {
            return this.onGetHost();
        }

        @Override  // androidx.fragment.app.FragmentHostCallback
        public LayoutInflater onGetLayoutInflater() {
            return FragmentActivity.this.getLayoutInflater().cloneInContext(FragmentActivity.this);
        }

        @Override  // androidx.fragment.app.FragmentHostCallback
        public int onGetWindowAnimations() {
            Window window0 = FragmentActivity.this.getWindow();
            return window0 == null ? 0 : window0.getAttributes().windowAnimations;
        }

        @Override  // androidx.fragment.app.FragmentHostCallback
        public boolean onHasView() {
            Window window0 = FragmentActivity.this.getWindow();
            return window0 != null && window0.peekDecorView() != null;
        }

        @Override  // androidx.fragment.app.FragmentHostCallback
        public boolean onHasWindowAnimations() {
            return FragmentActivity.this.getWindow() != null;
        }

        @Override  // androidx.fragment.app.FragmentHostCallback
        public boolean onShouldSaveFragmentState(Fragment fragment0) {
            return !FragmentActivity.this.isFinishing();
        }

        @Override  // androidx.fragment.app.FragmentHostCallback
        public boolean onShouldShowRequestPermissionRationale(String s) {
            return ActivityCompat.shouldShowRequestPermissionRationale(FragmentActivity.this, s);
        }

        @Override  // androidx.fragment.app.FragmentHostCallback
        public void onSupportInvalidateOptionsMenu() {
            this.invalidateMenu();
        }

        @Override  // androidx.core.view.MenuHost
        public void removeMenuProvider(MenuProvider menuProvider0) {
            FragmentActivity.this.removeMenuProvider(menuProvider0);
        }

        @Override  // androidx.core.content.OnConfigurationChangedProvider
        public void removeOnConfigurationChangedListener(Consumer consumer0) {
            FragmentActivity.this.removeOnConfigurationChangedListener(consumer0);
        }

        @Override  // androidx.core.app.OnMultiWindowModeChangedProvider
        public void removeOnMultiWindowModeChangedListener(Consumer consumer0) {
            FragmentActivity.this.removeOnMultiWindowModeChangedListener(consumer0);
        }

        @Override  // androidx.core.app.OnPictureInPictureModeChangedProvider
        public void removeOnPictureInPictureModeChangedListener(Consumer consumer0) {
            FragmentActivity.this.removeOnPictureInPictureModeChangedListener(consumer0);
        }

        @Override  // androidx.core.content.OnTrimMemoryProvider
        public void removeOnTrimMemoryListener(Consumer consumer0) {
            FragmentActivity.this.removeOnTrimMemoryListener(consumer0);
        }
    }

    static final String LIFECYCLE_TAG = "android:support:lifecycle";
    boolean mCreated;
    final LifecycleRegistry mFragmentLifecycleRegistry;
    final FragmentController mFragments;
    boolean mResumed;
    boolean mStopped;

    public FragmentActivity() {
        this.mFragments = FragmentController.createController(new HostCallbacks(this));
        this.mFragmentLifecycleRegistry = new LifecycleRegistry(this);
        this.mStopped = true;
        this.init();
    }

    public FragmentActivity(int v) {
        super(v);
        this.mFragments = FragmentController.createController(new HostCallbacks(this));
        this.mFragmentLifecycleRegistry = new LifecycleRegistry(this);
        this.mStopped = true;
        this.init();
    }

    final View dispatchFragmentsOnCreateView(View view0, String s, Context context0, AttributeSet attributeSet0) {
        return this.mFragments.onCreateView(view0, s, context0, attributeSet0);
    }

    @Override  // android.app.Activity
    public void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
        super.dump(s, fileDescriptor0, printWriter0, arr_s);
        if(!this.shouldDumpInternalState(arr_s)) {
            return;
        }
        printWriter0.print(s);
        printWriter0.print("Local FragmentActivity ");
        printWriter0.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter0.println(" State:");
        printWriter0.print(s + "  ");
        printWriter0.print("mCreated=");
        printWriter0.print(this.mCreated);
        printWriter0.print(" mResumed=");
        printWriter0.print(this.mResumed);
        printWriter0.print(" mStopped=");
        printWriter0.print(this.mStopped);
        if(this.getApplication() != null) {
            LoaderManager.getInstance(this).dump(s + "  ", fileDescriptor0, printWriter0, arr_s);
        }
        this.mFragments.getSupportFragmentManager().dump(s, fileDescriptor0, printWriter0, arr_s);
    }

    public FragmentManager getSupportFragmentManager() {
        return this.mFragments.getSupportFragmentManager();
    }

    @Deprecated
    public LoaderManager getSupportLoaderManager() {
        return LoaderManager.getInstance(this);
    }

    private void init() {
        this.getSavedStateRegistry().registerSavedStateProvider("android:support:lifecycle", () -> {
            this.markFragmentsCreated();
            this.mFragmentLifecycleRegistry.handleLifecycleEvent(Event.ON_STOP);
            return new Bundle();
        });
        this.addOnConfigurationChangedListener((Configuration configuration0) -> this.mFragments.noteStateNotSaved());
        this.addOnNewIntentListener((Intent intent0) -> this.mFragments.noteStateNotSaved());
        this.addOnContextAvailableListener((Context context0) -> this.mFragments.attachHost(null));
    }

    // 检测为 Lambda 实现
    Bundle lambda$init$0$androidx-fragment-app-FragmentActivity() [...]

    // 检测为 Lambda 实现
    void lambda$init$1$androidx-fragment-app-FragmentActivity(Configuration configuration0) [...]

    // 检测为 Lambda 实现
    void lambda$init$2$androidx-fragment-app-FragmentActivity(Intent intent0) [...]

    // 检测为 Lambda 实现
    void lambda$init$3$androidx-fragment-app-FragmentActivity(Context context0) [...]

    void markFragmentsCreated() {
        while(FragmentActivity.markState(this.getSupportFragmentManager(), State.CREATED)) {
        }
    }

    private static boolean markState(FragmentManager fragmentManager0, State lifecycle$State0) {
        boolean z = false;
        for(Object object0: fragmentManager0.getFragments()) {
            Fragment fragment0 = (Fragment)object0;
            if(fragment0 != null) {
                if(fragment0.getHost() != null) {
                    z |= FragmentActivity.markState(fragment0.getChildFragmentManager(), lifecycle$State0);
                }
                if(fragment0.mViewLifecycleOwner != null && fragment0.mViewLifecycleOwner.getLifecycle().getCurrentState().isAtLeast(State.STARTED)) {
                    fragment0.mViewLifecycleOwner.setCurrentState(lifecycle$State0);
                    z = true;
                }
                if(fragment0.mLifecycleRegistry.getCurrentState().isAtLeast(State.STARTED)) {
                    fragment0.mLifecycleRegistry.setCurrentState(lifecycle$State0);
                    z = true;
                }
            }
        }
        return z;
    }

    @Override  // androidx.activity.ComponentActivity
    protected void onActivityResult(int v, int v1, Intent intent0) {
        this.mFragments.noteStateNotSaved();
        super.onActivityResult(v, v1, intent0);
    }

    @Deprecated
    public void onAttachFragment(Fragment fragment0) {
    }

    @Override  // androidx.activity.ComponentActivity
    protected void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Event.ON_CREATE);
        this.mFragments.dispatchCreate();
    }

    @Override  // android.app.Activity
    public View onCreateView(View view0, String s, Context context0, AttributeSet attributeSet0) {
        View view1 = this.dispatchFragmentsOnCreateView(view0, s, context0, attributeSet0);
        return view1 == null ? super.onCreateView(view0, s, context0, attributeSet0) : view1;
    }

    @Override  // android.app.Activity
    public View onCreateView(String s, Context context0, AttributeSet attributeSet0) {
        View view0 = this.dispatchFragmentsOnCreateView(null, s, context0, attributeSet0);
        return view0 == null ? super.onCreateView(s, context0, attributeSet0) : view0;
    }

    @Override  // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mFragments.dispatchDestroy();
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Event.ON_DESTROY);
    }

    @Override  // androidx.activity.ComponentActivity
    public boolean onMenuItemSelected(int v, MenuItem menuItem0) {
        if(super.onMenuItemSelected(v, menuItem0)) {
            return true;
        }
        return v == 6 ? this.mFragments.dispatchContextItemSelected(menuItem0) : false;
    }

    @Override  // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mResumed = false;
        this.mFragments.dispatchPause();
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Event.ON_PAUSE);
    }

    @Override  // android.app.Activity
    protected void onPostResume() {
        super.onPostResume();
        this.onResumeFragments();
    }

    @Override  // androidx.activity.ComponentActivity, androidx.core.app.ActivityCompat$OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int v, String[] arr_s, int[] arr_v) {
        this.mFragments.noteStateNotSaved();
        super.onRequestPermissionsResult(v, arr_s, arr_v);
    }

    @Override  // android.app.Activity
    protected void onResume() {
        this.mFragments.noteStateNotSaved();
        super.onResume();
        this.mResumed = true;
        this.mFragments.execPendingActions();
    }

    protected void onResumeFragments() {
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Event.ON_RESUME);
        this.mFragments.dispatchResume();
    }

    @Override  // android.app.Activity
    protected void onStart() {
        this.mFragments.noteStateNotSaved();
        super.onStart();
        this.mStopped = false;
        if(!this.mCreated) {
            this.mCreated = true;
            this.mFragments.dispatchActivityCreated();
        }
        this.mFragments.execPendingActions();
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Event.ON_START);
        this.mFragments.dispatchStart();
    }

    @Override  // android.app.Activity
    public void onStateNotSaved() {
        this.mFragments.noteStateNotSaved();
    }

    @Override  // android.app.Activity
    protected void onStop() {
        super.onStop();
        this.mStopped = true;
        this.markFragmentsCreated();
        this.mFragments.dispatchStop();
        this.mFragmentLifecycleRegistry.handleLifecycleEvent(Event.ON_STOP);
    }

    public void setEnterSharedElementCallback(SharedElementCallback sharedElementCallback0) {
        ActivityCompat.setEnterSharedElementCallback(this, sharedElementCallback0);
    }

    public void setExitSharedElementCallback(SharedElementCallback sharedElementCallback0) {
        ActivityCompat.setExitSharedElementCallback(this, sharedElementCallback0);
    }

    public void startActivityFromFragment(Fragment fragment0, Intent intent0, int v) {
        this.startActivityFromFragment(fragment0, intent0, v, null);
    }

    public void startActivityFromFragment(Fragment fragment0, Intent intent0, int v, Bundle bundle0) {
        if(v == -1) {
            ActivityCompat.startActivityForResult(this, intent0, -1, bundle0);
            return;
        }
        fragment0.startActivityForResult(intent0, v, bundle0);
    }

    @Deprecated
    public void startIntentSenderFromFragment(Fragment fragment0, IntentSender intentSender0, int v, Intent intent0, int v1, int v2, int v3, Bundle bundle0) throws IntentSender.SendIntentException {
        if(v == -1) {
            ActivityCompat.startIntentSenderForResult(this, intentSender0, -1, intent0, v1, v2, v3, bundle0);
            return;
        }
        fragment0.startIntentSenderForResult(intentSender0, v, intent0, v1, v2, v3, bundle0);
    }

    public void supportFinishAfterTransition() {
        ActivityCompat.finishAfterTransition(this);
    }

    @Deprecated
    public void supportInvalidateOptionsMenu() {
        this.invalidateMenu();
    }

    public void supportPostponeEnterTransition() {
        ActivityCompat.postponeEnterTransition(this);
    }

    public void supportStartPostponedEnterTransition() {
        ActivityCompat.startPostponedEnterTransition(this);
    }

    @Override  // androidx.core.app.ActivityCompat$RequestPermissionsRequestCodeValidator
    @Deprecated
    public final void validateRequestPermissionsRequestCode(int v) {
    }
}

