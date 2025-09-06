package androidx.fragment.app;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater.Factory2;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherOwner;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.IntentSenderRequest.Builder;
import androidx.activity.result.IntentSenderRequest;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions;
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult;
import androidx.core.app.MultiWindowModeChangedInfo;
import androidx.core.app.OnMultiWindowModeChangedProvider;
import androidx.core.app.OnPictureInPictureModeChangedProvider;
import androidx.core.app.PictureInPictureModeChangedInfo;
import androidx.core.content.OnConfigurationChangedProvider;
import androidx.core.content.OnTrimMemoryProvider;
import androidx.core.util.Consumer;
import androidx.core.view.MenuHost;
import androidx.core.view.MenuProvider;
import androidx.fragment.R.id;
import androidx.fragment.app.strictmode.FragmentStrictMode.Policy;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.Lifecycle.State;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class FragmentManager implements FragmentResultOwner {
    public interface BackStackEntry {
        @Deprecated
        CharSequence getBreadCrumbShortTitle();

        @Deprecated
        int getBreadCrumbShortTitleRes();

        @Deprecated
        CharSequence getBreadCrumbTitle();

        @Deprecated
        int getBreadCrumbTitleRes();

        int getId();

        String getName();
    }

    class ClearBackStackState implements OpGenerator {
        private final String mName;

        ClearBackStackState(String s) {
            this.mName = s;
        }

        @Override  // androidx.fragment.app.FragmentManager$OpGenerator
        public boolean generateOps(ArrayList arrayList0, ArrayList arrayList1) {
            return FragmentManager.this.clearBackStackState(arrayList0, arrayList1, this.mName);
        }
    }

    static class FragmentIntentSenderContract extends ActivityResultContract {
        public Intent createIntent(Context context0, IntentSenderRequest intentSenderRequest0) {
            Intent intent0 = new Intent("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST");
            Intent intent1 = intentSenderRequest0.getFillInIntent();
            if(intent1 != null) {
                Bundle bundle0 = intent1.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                if(bundle0 != null) {
                    intent0.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundle0);
                    intent1.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                    if(intent1.getBooleanExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", false)) {
                        intentSenderRequest0 = new Builder(intentSenderRequest0.getIntentSender()).setFillInIntent(null).setFlags(intentSenderRequest0.getFlagsValues(), intentSenderRequest0.getFlagsMask()).build();
                    }
                }
            }
            intent0.putExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST", intentSenderRequest0);
            if(FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "CreateIntent created the following intent: " + intent0);
            }
            return intent0;
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Intent createIntent(Context context0, Object object0) {
            return this.createIntent(context0, ((IntentSenderRequest)object0));
        }

        public ActivityResult parseResult(int v, Intent intent0) {
            return new ActivityResult(v, intent0);
        }

        @Override  // androidx.activity.result.contract.ActivityResultContract
        public Object parseResult(int v, Intent intent0) {
            return this.parseResult(v, intent0);
        }
    }

    public static abstract class FragmentLifecycleCallbacks {
        @Deprecated
        public void onFragmentActivityCreated(FragmentManager fragmentManager0, Fragment fragment0, Bundle bundle0) {
        }

        public void onFragmentAttached(FragmentManager fragmentManager0, Fragment fragment0, Context context0) {
        }

        public void onFragmentCreated(FragmentManager fragmentManager0, Fragment fragment0, Bundle bundle0) {
        }

        public void onFragmentDestroyed(FragmentManager fragmentManager0, Fragment fragment0) {
        }

        public void onFragmentDetached(FragmentManager fragmentManager0, Fragment fragment0) {
        }

        public void onFragmentPaused(FragmentManager fragmentManager0, Fragment fragment0) {
        }

        public void onFragmentPreAttached(FragmentManager fragmentManager0, Fragment fragment0, Context context0) {
        }

        public void onFragmentPreCreated(FragmentManager fragmentManager0, Fragment fragment0, Bundle bundle0) {
        }

        public void onFragmentResumed(FragmentManager fragmentManager0, Fragment fragment0) {
        }

        public void onFragmentSaveInstanceState(FragmentManager fragmentManager0, Fragment fragment0, Bundle bundle0) {
        }

        public void onFragmentStarted(FragmentManager fragmentManager0, Fragment fragment0) {
        }

        public void onFragmentStopped(FragmentManager fragmentManager0, Fragment fragment0) {
        }

        public void onFragmentViewCreated(FragmentManager fragmentManager0, Fragment fragment0, View view0, Bundle bundle0) {
        }

        public void onFragmentViewDestroyed(FragmentManager fragmentManager0, Fragment fragment0) {
        }
    }

    static class LaunchedFragmentInfo implements Parcelable {
        public static final Parcelable.Creator CREATOR;
        int mRequestCode;
        String mWho;

        static {
            LaunchedFragmentInfo.CREATOR = new Parcelable.Creator() {
                public LaunchedFragmentInfo createFromParcel(Parcel parcel0) {
                    return new LaunchedFragmentInfo(parcel0);
                }

                @Override  // android.os.Parcelable$Creator
                public Object createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0);
                }

                public LaunchedFragmentInfo[] newArray(int v) {
                    return new LaunchedFragmentInfo[v];
                }

                @Override  // android.os.Parcelable$Creator
                public Object[] newArray(int v) {
                    return this.newArray(v);
                }
            };
        }

        LaunchedFragmentInfo(Parcel parcel0) {
            this.mWho = parcel0.readString();
            this.mRequestCode = parcel0.readInt();
        }

        LaunchedFragmentInfo(String s, int v) {
            this.mWho = s;
            this.mRequestCode = v;
        }

        @Override  // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override  // android.os.Parcelable
        public void writeToParcel(Parcel parcel0, int v) {
            parcel0.writeString(this.mWho);
            parcel0.writeInt(this.mRequestCode);
        }
    }

    static class LifecycleAwareResultListener implements FragmentResultListener {
        private final Lifecycle mLifecycle;
        private final FragmentResultListener mListener;
        private final LifecycleEventObserver mObserver;

        LifecycleAwareResultListener(Lifecycle lifecycle0, FragmentResultListener fragmentResultListener0, LifecycleEventObserver lifecycleEventObserver0) {
            this.mLifecycle = lifecycle0;
            this.mListener = fragmentResultListener0;
            this.mObserver = lifecycleEventObserver0;
        }

        public boolean isAtLeast(State lifecycle$State0) {
            return this.mLifecycle.getCurrentState().isAtLeast(lifecycle$State0);
        }

        @Override  // androidx.fragment.app.FragmentResultListener
        public void onFragmentResult(String s, Bundle bundle0) {
            this.mListener.onFragmentResult(s, bundle0);
        }

        public void removeObserver() {
            this.mLifecycle.removeObserver(this.mObserver);
        }
    }

    public interface OnBackStackChangedListener {
        void onBackStackChanged();
    }

    interface OpGenerator {
        boolean generateOps(ArrayList arg1, ArrayList arg2);
    }

    class PopBackStackState implements OpGenerator {
        final int mFlags;
        final int mId;
        final String mName;

        PopBackStackState(String s, int v, int v1) {
            this.mName = s;
            this.mId = v;
            this.mFlags = v1;
        }

        @Override  // androidx.fragment.app.FragmentManager$OpGenerator
        public boolean generateOps(ArrayList arrayList0, ArrayList arrayList1) {
            return FragmentManager.this.mPrimaryNav == null || this.mId >= 0 || this.mName != null || !FragmentManager.this.mPrimaryNav.getChildFragmentManager().popBackStackImmediate() ? FragmentManager.this.popBackStackState(arrayList0, arrayList1, this.mName, this.mId, this.mFlags) : false;
        }
    }

    class RestoreBackStackState implements OpGenerator {
        private final String mName;

        RestoreBackStackState(String s) {
            this.mName = s;
        }

        @Override  // androidx.fragment.app.FragmentManager$OpGenerator
        public boolean generateOps(ArrayList arrayList0, ArrayList arrayList1) {
            return FragmentManager.this.restoreBackStackState(arrayList0, arrayList1, this.mName);
        }
    }

    class SaveBackStackState implements OpGenerator {
        private final String mName;

        SaveBackStackState(String s) {
            this.mName = s;
        }

        @Override  // androidx.fragment.app.FragmentManager$OpGenerator
        public boolean generateOps(ArrayList arrayList0, ArrayList arrayList1) {
            return FragmentManager.this.saveBackStackState(arrayList0, arrayList1, this.mName);
        }
    }

    private static boolean DEBUG = false;
    private static final String EXTRA_CREATED_FILLIN_INTENT = "androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE";
    static final String FRAGMENT_MANAGER_STATE_TAG = "state";
    static final String FRAGMENT_NAME_PREFIX = "fragment_";
    static final String FRAGMENT_STATE_TAG = "state";
    public static final int POP_BACK_STACK_INCLUSIVE = 1;
    static final String RESULT_NAME_PREFIX = "result_";
    static final String SAVED_STATE_TAG = "android:support:fragments";
    public static final String TAG = "FragmentManager";
    ArrayList mBackStack;
    private ArrayList mBackStackChangeListeners;
    private final AtomicInteger mBackStackIndex;
    private final Map mBackStackStates;
    private FragmentContainer mContainer;
    private ArrayList mCreatedMenus;
    int mCurState;
    private SpecialEffectsControllerFactory mDefaultSpecialEffectsControllerFactory;
    private boolean mDestroyed;
    private Runnable mExecCommit;
    private boolean mExecutingActions;
    private FragmentFactory mFragmentFactory;
    private final FragmentStore mFragmentStore;
    private boolean mHavePendingDeferredStart;
    private FragmentHostCallback mHost;
    private FragmentFactory mHostFragmentFactory;
    ArrayDeque mLaunchedFragments;
    private final FragmentLayoutInflaterFactory mLayoutInflaterFactory;
    private final FragmentLifecycleCallbacksDispatcher mLifecycleCallbacksDispatcher;
    private final MenuProvider mMenuProvider;
    private boolean mNeedMenuInvalidate;
    private FragmentManagerViewModel mNonConfig;
    private final CopyOnWriteArrayList mOnAttachListeners;
    private final OnBackPressedCallback mOnBackPressedCallback;
    private OnBackPressedDispatcher mOnBackPressedDispatcher;
    private final Consumer mOnConfigurationChangedListener;
    private final Consumer mOnMultiWindowModeChangedListener;
    private final Consumer mOnPictureInPictureModeChangedListener;
    private final Consumer mOnTrimMemoryListener;
    private Fragment mParent;
    private final ArrayList mPendingActions;
    Fragment mPrimaryNav;
    private ActivityResultLauncher mRequestPermissions;
    private final Map mResultListeners;
    private final Map mResults;
    private SpecialEffectsControllerFactory mSpecialEffectsControllerFactory;
    private ActivityResultLauncher mStartActivityForResult;
    private ActivityResultLauncher mStartIntentSenderForResult;
    private boolean mStateSaved;
    private boolean mStopped;
    private Policy mStrictModePolicy;
    private ArrayList mTmpAddedFragments;
    private ArrayList mTmpIsPop;
    private ArrayList mTmpRecords;

    static {
    }

    public FragmentManager() {
        this.mPendingActions = new ArrayList();
        this.mFragmentStore = new FragmentStore();
        this.mLayoutInflaterFactory = new FragmentLayoutInflaterFactory(this);
        this.mOnBackPressedCallback = new OnBackPressedCallback(false) {
            @Override  // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                FragmentManager.this.handleOnBackPressed();
            }
        };
        this.mBackStackIndex = new AtomicInteger();
        this.mBackStackStates = Collections.synchronizedMap(new HashMap());
        this.mResults = Collections.synchronizedMap(new HashMap());
        this.mResultListeners = Collections.synchronizedMap(new HashMap());
        this.mLifecycleCallbacksDispatcher = new FragmentLifecycleCallbacksDispatcher(this);
        this.mOnAttachListeners = new CopyOnWriteArrayList();
        this.mOnConfigurationChangedListener = (Configuration configuration0) -> if(this.isParentAdded()) {
            this.dispatchConfigurationChanged(configuration0, false);
        };
        this.mOnTrimMemoryListener = (Integer integer0) -> if(this.isParentAdded() && ((int)integer0) == 80) {
            this.dispatchLowMemory(false);
        };
        this.mOnMultiWindowModeChangedListener = (MultiWindowModeChangedInfo multiWindowModeChangedInfo0) -> if(this.isParentAdded()) {
            this.dispatchMultiWindowModeChanged(multiWindowModeChangedInfo0.isInMultiWindowMode(), false);
        };
        this.mOnPictureInPictureModeChangedListener = (PictureInPictureModeChangedInfo pictureInPictureModeChangedInfo0) -> if(this.isParentAdded()) {
            this.dispatchPictureInPictureModeChanged(pictureInPictureModeChangedInfo0.isInPictureInPictureMode(), false);
        };
        this.mMenuProvider = new MenuProvider() {
            @Override  // androidx.core.view.MenuProvider
            public void onCreateMenu(Menu menu0, MenuInflater menuInflater0) {
                FragmentManager.this.dispatchCreateOptionsMenu(menu0, menuInflater0);
            }

            @Override  // androidx.core.view.MenuProvider
            public void onMenuClosed(Menu menu0) {
                FragmentManager.this.dispatchOptionsMenuClosed(menu0);
            }

            @Override  // androidx.core.view.MenuProvider
            public boolean onMenuItemSelected(MenuItem menuItem0) {
                return FragmentManager.this.dispatchOptionsItemSelected(menuItem0);
            }

            @Override  // androidx.core.view.MenuProvider
            public void onPrepareMenu(Menu menu0) {
                FragmentManager.this.dispatchPrepareOptionsMenu(menu0);
            }
        };
        this.mCurState = -1;
        this.mFragmentFactory = null;
        this.mHostFragmentFactory = new FragmentFactory() {
            @Override  // androidx.fragment.app.FragmentFactory
            public Fragment instantiate(ClassLoader classLoader0, String s) {
                return FragmentManager.this.getHost().instantiate(FragmentManager.this.getHost().getContext(), s, null);
            }
        };
        this.mSpecialEffectsControllerFactory = null;
        this.mDefaultSpecialEffectsControllerFactory = new SpecialEffectsControllerFactory() {
            @Override  // androidx.fragment.app.SpecialEffectsControllerFactory
            public SpecialEffectsController createController(ViewGroup viewGroup0) {
                return new DefaultSpecialEffectsController(viewGroup0);
            }
        };
        this.mLaunchedFragments = new ArrayDeque();
        this.mExecCommit = () -> {
            FragmentManager.this.ensureExecReady(true);
            boolean z1 = false;
            while(FragmentManager.this.generateOpsForPendingActions(FragmentManager.this.mTmpRecords, FragmentManager.this.mTmpIsPop)) {
                try {
                    z1 = true;
                    FragmentManager.this.mExecutingActions = true;
                    FragmentManager.this.removeRedundantOperationsAndExecute(FragmentManager.this.mTmpRecords, FragmentManager.this.mTmpIsPop);
                }
                finally {
                    FragmentManager.this.cleanupExec();
                }
            }
            FragmentManager.this.updateOnBackPressedCallbackEnabled();
            FragmentManager.this.doPendingDeferredStart();
            FragmentManager.this.mFragmentStore.burpActive();
            return z1;
        };
    }

    void addBackStackState(BackStackRecord backStackRecord0) {
        if(this.mBackStack == null) {
            this.mBackStack = new ArrayList();
        }
        this.mBackStack.add(backStackRecord0);
    }

    FragmentStateManager addFragment(Fragment fragment0) {
        if(fragment0.mPreviousWho != null) {
            FragmentStrictMode.onFragmentReuse(fragment0, fragment0.mPreviousWho);
        }
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "add: " + fragment0);
        }
        FragmentStateManager fragmentStateManager0 = this.createOrGetFragmentStateManager(fragment0);
        fragment0.mFragmentManager = this;
        this.mFragmentStore.makeActive(fragmentStateManager0);
        if(!fragment0.mDetached) {
            this.mFragmentStore.addFragment(fragment0);
            fragment0.mRemoving = false;
            if(fragment0.mView == null) {
                fragment0.mHiddenChanged = false;
            }
            if(this.isMenuAvailable(fragment0)) {
                this.mNeedMenuInvalidate = true;
            }
        }
        return fragmentStateManager0;
    }

    public void addFragmentOnAttachListener(FragmentOnAttachListener fragmentOnAttachListener0) {
        this.mOnAttachListeners.add(fragmentOnAttachListener0);
    }

    public void addOnBackStackChangedListener(OnBackStackChangedListener fragmentManager$OnBackStackChangedListener0) {
        if(this.mBackStackChangeListeners == null) {
            this.mBackStackChangeListeners = new ArrayList();
        }
        this.mBackStackChangeListeners.add(fragmentManager$OnBackStackChangedListener0);
    }

    void addRetainedFragment(Fragment fragment0) {
        this.mNonConfig.addRetainedFragment(fragment0);
    }

    int allocBackStackIndex() {
        return this.mBackStackIndex.getAndIncrement();
    }

    void attachController(FragmentHostCallback fragmentHostCallback0, FragmentContainer fragmentContainer0, Fragment fragment0) {
        if(this.mHost != null) {
            throw new IllegalStateException("Already attached");
        }
        this.mHost = fragmentHostCallback0;
        this.mContainer = fragmentContainer0;
        this.mParent = fragment0;
        if(fragment0 != null) {
            this.addFragmentOnAttachListener(new FragmentOnAttachListener() {
                @Override  // androidx.fragment.app.FragmentOnAttachListener
                public void onAttachFragment(FragmentManager fragmentManager0, Fragment fragment0) {
                }
            });
        }
        else if(fragmentHostCallback0 instanceof FragmentOnAttachListener) {
            this.addFragmentOnAttachListener(((FragmentOnAttachListener)fragmentHostCallback0));
        }
        if(this.mParent != null) {
            this.updateOnBackPressedCallbackEnabled();
        }
        if(fragmentHostCallback0 instanceof OnBackPressedDispatcherOwner) {
            OnBackPressedDispatcherOwner onBackPressedDispatcherOwner0 = (OnBackPressedDispatcherOwner)fragmentHostCallback0;
            OnBackPressedDispatcher onBackPressedDispatcher0 = onBackPressedDispatcherOwner0.getOnBackPressedDispatcher();
            this.mOnBackPressedDispatcher = onBackPressedDispatcher0;
            if(fragment0 != null) {
                onBackPressedDispatcherOwner0 = fragment0;
            }
            onBackPressedDispatcher0.addCallback(onBackPressedDispatcherOwner0, this.mOnBackPressedCallback);
        }
        if(fragment0 != null) {
            this.mNonConfig = fragment0.mFragmentManager.getChildNonConfig(fragment0);
        }
        else if(fragmentHostCallback0 instanceof ViewModelStoreOwner) {
            this.mNonConfig = FragmentManagerViewModel.getInstance(((ViewModelStoreOwner)fragmentHostCallback0).getViewModelStore());
        }
        else {
            this.mNonConfig = new FragmentManagerViewModel(false);
        }
        this.mNonConfig.setIsStateSaved(this.isStateSaved());
        this.mFragmentStore.setNonConfig(this.mNonConfig);
        FragmentHostCallback fragmentHostCallback1 = this.mHost;
        if(fragmentHostCallback1 instanceof SavedStateRegistryOwner && fragment0 == null) {
            SavedStateRegistry savedStateRegistry0 = ((SavedStateRegistryOwner)fragmentHostCallback1).getSavedStateRegistry();
            savedStateRegistry0.registerSavedStateProvider("android:support:fragments", () -> this.saveAllStateInternal());
            Bundle bundle0 = savedStateRegistry0.consumeRestoredStateForKey("android:support:fragments");
            if(bundle0 != null) {
                this.restoreSaveStateInternal(bundle0);
            }
        }
        FragmentHostCallback fragmentHostCallback2 = this.mHost;
        if(fragmentHostCallback2 instanceof ActivityResultRegistryOwner) {
            ActivityResultRegistry activityResultRegistry0 = ((ActivityResultRegistryOwner)fragmentHostCallback2).getActivityResultRegistry();
            String s = fragment0 == null ? "" : fragment0.mWho + ":";
            this.mStartActivityForResult = activityResultRegistry0.register("FragmentManager:" + s + "StartActivityForResult", new StartActivityForResult(), new ActivityResultCallback() {
                public void onActivityResult(ActivityResult activityResult0) {
                    LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo0 = (LaunchedFragmentInfo)FragmentManager.this.mLaunchedFragments.pollFirst();
                    if(fragmentManager$LaunchedFragmentInfo0 == null) {
                        Log.w("FragmentManager", "No Activities were started for result for " + this);
                        return;
                    }
                    String s = fragmentManager$LaunchedFragmentInfo0.mWho;
                    int v = fragmentManager$LaunchedFragmentInfo0.mRequestCode;
                    Fragment fragment0 = FragmentManager.this.mFragmentStore.findFragmentByWho(s);
                    if(fragment0 == null) {
                        Log.w("FragmentManager", "Activity result delivered for unknown Fragment " + s);
                        return;
                    }
                    fragment0.onActivityResult(v, activityResult0.getResultCode(), activityResult0.getData());
                }

                @Override  // androidx.activity.result.ActivityResultCallback
                public void onActivityResult(Object object0) {
                    this.onActivityResult(((ActivityResult)object0));
                }
            });
            this.mStartIntentSenderForResult = activityResultRegistry0.register("FragmentManager:" + s + "StartIntentSenderForResult", new FragmentIntentSenderContract(), new ActivityResultCallback() {
                public void onActivityResult(ActivityResult activityResult0) {
                    LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo0 = (LaunchedFragmentInfo)FragmentManager.this.mLaunchedFragments.pollFirst();
                    if(fragmentManager$LaunchedFragmentInfo0 == null) {
                        Log.w("FragmentManager", "No IntentSenders were started for " + this);
                        return;
                    }
                    String s = fragmentManager$LaunchedFragmentInfo0.mWho;
                    int v = fragmentManager$LaunchedFragmentInfo0.mRequestCode;
                    Fragment fragment0 = FragmentManager.this.mFragmentStore.findFragmentByWho(s);
                    if(fragment0 == null) {
                        Log.w("FragmentManager", "Intent Sender result delivered for unknown Fragment " + s);
                        return;
                    }
                    fragment0.onActivityResult(v, activityResult0.getResultCode(), activityResult0.getData());
                }

                @Override  // androidx.activity.result.ActivityResultCallback
                public void onActivityResult(Object object0) {
                    this.onActivityResult(((ActivityResult)object0));
                }
            });
            this.mRequestPermissions = activityResultRegistry0.register("FragmentManager:" + s + "RequestPermissions", new RequestMultiplePermissions(), new ActivityResultCallback() {
                @Override  // androidx.activity.result.ActivityResultCallback
                public void onActivityResult(Object object0) {
                    this.onActivityResult(((Map)object0));
                }

                public void onActivityResult(Map map0) {
                    String[] arr_s = (String[])map0.keySet().toArray(new String[0]);
                    ArrayList arrayList0 = new ArrayList(map0.values());
                    int[] arr_v = new int[arrayList0.size()];
                    for(int v = 0; v < arrayList0.size(); ++v) {
                        arr_v[v] = ((Boolean)arrayList0.get(v)).booleanValue() ? 0 : -1;
                    }
                    LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo0 = (LaunchedFragmentInfo)FragmentManager.this.mLaunchedFragments.pollFirst();
                    if(fragmentManager$LaunchedFragmentInfo0 == null) {
                        Log.w("FragmentManager", "No permissions were requested for " + this);
                        return;
                    }
                    String s = fragmentManager$LaunchedFragmentInfo0.mWho;
                    int v1 = fragmentManager$LaunchedFragmentInfo0.mRequestCode;
                    Fragment fragment0 = FragmentManager.this.mFragmentStore.findFragmentByWho(s);
                    if(fragment0 == null) {
                        Log.w("FragmentManager", "Permission request result delivered for unknown Fragment " + s);
                        return;
                    }
                    fragment0.onRequestPermissionsResult(v1, arr_s, arr_v);
                }
            });
        }
        FragmentHostCallback fragmentHostCallback3 = this.mHost;
        if(fragmentHostCallback3 instanceof OnConfigurationChangedProvider) {
            ((OnConfigurationChangedProvider)fragmentHostCallback3).addOnConfigurationChangedListener(this.mOnConfigurationChangedListener);
        }
        FragmentHostCallback fragmentHostCallback4 = this.mHost;
        if(fragmentHostCallback4 instanceof OnTrimMemoryProvider) {
            ((OnTrimMemoryProvider)fragmentHostCallback4).addOnTrimMemoryListener(this.mOnTrimMemoryListener);
        }
        FragmentHostCallback fragmentHostCallback5 = this.mHost;
        if(fragmentHostCallback5 instanceof OnMultiWindowModeChangedProvider) {
            ((OnMultiWindowModeChangedProvider)fragmentHostCallback5).addOnMultiWindowModeChangedListener(this.mOnMultiWindowModeChangedListener);
        }
        FragmentHostCallback fragmentHostCallback6 = this.mHost;
        if(fragmentHostCallback6 instanceof OnPictureInPictureModeChangedProvider) {
            ((OnPictureInPictureModeChangedProvider)fragmentHostCallback6).addOnPictureInPictureModeChangedListener(this.mOnPictureInPictureModeChangedListener);
        }
        FragmentHostCallback fragmentHostCallback7 = this.mHost;
        if(fragmentHostCallback7 instanceof MenuHost && fragment0 == null) {
            ((MenuHost)fragmentHostCallback7).addMenuProvider(this.mMenuProvider);
        }
    }

    void attachFragment(Fragment fragment0) {
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "attach: " + fragment0);
        }
        if(fragment0.mDetached) {
            fragment0.mDetached = false;
            if(!fragment0.mAdded) {
                this.mFragmentStore.addFragment(fragment0);
                if(FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "add from attach: " + fragment0);
                }
                if(this.isMenuAvailable(fragment0)) {
                    this.mNeedMenuInvalidate = true;
                }
            }
        }
    }

    public FragmentTransaction beginTransaction() {
        return new BackStackRecord(this);
    }

    boolean checkForMenus() {
        boolean z = false;
        for(Object object0: this.mFragmentStore.getActiveFragments()) {
            Fragment fragment0 = (Fragment)object0;
            if(fragment0 != null) {
                z = this.isMenuAvailable(fragment0);
            }
            if(z) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    private void checkStateLoss() {
        if(this.isStateSaved()) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
    }

    private void cleanupExec() {
        this.mExecutingActions = false;
        this.mTmpIsPop.clear();
        this.mTmpRecords.clear();
    }

    public void clearBackStack(String s) {
        this.enqueueAction((ArrayList arrayList0, ArrayList arrayList1) -> (FragmentManager.this.restoreBackStackState(arrayList0, arrayList1, s) ? FragmentManager.this.popBackStackState(arrayList0, arrayList1, s, -1, 1) : false), false);
    }

    // 检测为 Lambda 实现
    boolean clearBackStackState(ArrayList arrayList0, ArrayList arrayList1, String s) [...]

    private void clearBackStackStateViewModels() {
        boolean z;
        FragmentHostCallback fragmentHostCallback0 = this.mHost;
        if(fragmentHostCallback0 instanceof ViewModelStoreOwner) {
            z = this.mFragmentStore.getNonConfig().isCleared();
        }
        else {
            z = fragmentHostCallback0.getContext() instanceof Activity ? !((Activity)this.mHost.getContext()).isChangingConfigurations() : true;
        }
        if(z) {
            for(Object object0: this.mBackStackStates.values()) {
                for(Object object1: ((BackStackState)object0).mFragments) {
                    this.mFragmentStore.getNonConfig().clearNonConfigState(((String)object1));
                }
            }
        }
    }

    @Override  // androidx.fragment.app.FragmentResultOwner
    public final void clearFragmentResult(String s) {
        this.mResults.remove(s);
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Clearing fragment result with key " + s);
        }
    }

    @Override  // androidx.fragment.app.FragmentResultOwner
    public final void clearFragmentResultListener(String s) {
        LifecycleAwareResultListener fragmentManager$LifecycleAwareResultListener0 = (LifecycleAwareResultListener)this.mResultListeners.remove(s);
        if(fragmentManager$LifecycleAwareResultListener0 != null) {
            fragmentManager$LifecycleAwareResultListener0.removeObserver();
        }
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Clearing FragmentResultListener for key " + s);
        }
    }

    private Set collectAllSpecialEffectsController() {
        Set set0 = new HashSet();
        for(Object object0: this.mFragmentStore.getActiveFragmentStateManagers()) {
            ViewGroup viewGroup0 = ((FragmentStateManager)object0).getFragment().mContainer;
            if(viewGroup0 != null) {
                set0.add(SpecialEffectsController.getOrCreateController(viewGroup0, this.getSpecialEffectsControllerFactory()));
            }
        }
        return set0;
    }

    private Set collectChangedControllers(ArrayList arrayList0, int v, int v1) {
        Set set0 = new HashSet();
        while(v < v1) {
            for(Object object0: ((BackStackRecord)arrayList0.get(v)).mOps) {
                Fragment fragment0 = ((Op)object0).mFragment;
                if(fragment0 != null) {
                    ViewGroup viewGroup0 = fragment0.mContainer;
                    if(viewGroup0 != null) {
                        set0.add(SpecialEffectsController.getOrCreateController(viewGroup0, this));
                    }
                }
            }
            ++v;
        }
        return set0;
    }

    FragmentStateManager createOrGetFragmentStateManager(Fragment fragment0) {
        FragmentStateManager fragmentStateManager0 = this.mFragmentStore.getFragmentStateManager(fragment0.mWho);
        if(fragmentStateManager0 != null) {
            return fragmentStateManager0;
        }
        FragmentStateManager fragmentStateManager1 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, fragment0);
        fragmentStateManager1.restoreState(this.mHost.getContext().getClassLoader());
        fragmentStateManager1.setFragmentManagerState(this.mCurState);
        return fragmentStateManager1;
    }

    void detachFragment(Fragment fragment0) {
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "detach: " + fragment0);
        }
        if(!fragment0.mDetached) {
            fragment0.mDetached = true;
            if(fragment0.mAdded) {
                if(FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "remove from detach: " + fragment0);
                }
                this.mFragmentStore.removeFragment(fragment0);
                if(this.isMenuAvailable(fragment0)) {
                    this.mNeedMenuInvalidate = true;
                }
                this.setVisibleRemovingFragment(fragment0);
            }
        }
    }

    void dispatchActivityCreated() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.setIsStateSaved(false);
        this.dispatchStateChange(4);
    }

    void dispatchAttach() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.setIsStateSaved(false);
        this.dispatchStateChange(0);
    }

    void dispatchConfigurationChanged(Configuration configuration0, boolean z) {
        if(z && this.mHost instanceof OnConfigurationChangedProvider) {
            this.throwException(new IllegalStateException("Do not call dispatchConfigurationChanged() on host. Host implements OnConfigurationChangedProvider and automatically dispatches configuration changes to fragments."));
        }
        for(Object object0: this.mFragmentStore.getFragments()) {
            Fragment fragment0 = (Fragment)object0;
            if(fragment0 != null) {
                fragment0.performConfigurationChanged(configuration0);
                if(z) {
                    fragment0.mChildFragmentManager.dispatchConfigurationChanged(configuration0, true);
                }
            }
        }
    }

    boolean dispatchContextItemSelected(MenuItem menuItem0) {
        if(this.mCurState < 1) {
            return false;
        }
        for(Object object0: this.mFragmentStore.getFragments()) {
            if(((Fragment)object0) != null && ((Fragment)object0).performContextItemSelected(menuItem0)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    void dispatchCreate() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.setIsStateSaved(false);
        this.dispatchStateChange(1);
    }

    boolean dispatchCreateOptionsMenu(Menu menu0, MenuInflater menuInflater0) {
        if(this.mCurState < 1) {
            return false;
        }
        ArrayList arrayList0 = null;
        boolean z = false;
        for(Object object0: this.mFragmentStore.getFragments()) {
            Fragment fragment0 = (Fragment)object0;
            if(fragment0 != null && this.isParentMenuVisible(fragment0) && fragment0.performCreateOptionsMenu(menu0, menuInflater0)) {
                if(arrayList0 == null) {
                    arrayList0 = new ArrayList();
                }
                arrayList0.add(fragment0);
                z = true;
            }
        }
        if(this.mCreatedMenus != null) {
            for(int v = 0; v < this.mCreatedMenus.size(); ++v) {
                boolean z1 = arrayList0 != null && arrayList0.contains(((Fragment)this.mCreatedMenus.get(v)));
            }
        }
        this.mCreatedMenus = arrayList0;
        return z;
    }

    void dispatchDestroy() {
        this.mDestroyed = true;
        this.execPendingActions(true);
        this.endAnimatingAwayFragments();
        this.clearBackStackStateViewModels();
        this.dispatchStateChange(-1);
        FragmentHostCallback fragmentHostCallback0 = this.mHost;
        if(fragmentHostCallback0 instanceof OnTrimMemoryProvider) {
            ((OnTrimMemoryProvider)fragmentHostCallback0).removeOnTrimMemoryListener(this.mOnTrimMemoryListener);
        }
        FragmentHostCallback fragmentHostCallback1 = this.mHost;
        if(fragmentHostCallback1 instanceof OnConfigurationChangedProvider) {
            ((OnConfigurationChangedProvider)fragmentHostCallback1).removeOnConfigurationChangedListener(this.mOnConfigurationChangedListener);
        }
        FragmentHostCallback fragmentHostCallback2 = this.mHost;
        if(fragmentHostCallback2 instanceof OnMultiWindowModeChangedProvider) {
            ((OnMultiWindowModeChangedProvider)fragmentHostCallback2).removeOnMultiWindowModeChangedListener(this.mOnMultiWindowModeChangedListener);
        }
        FragmentHostCallback fragmentHostCallback3 = this.mHost;
        if(fragmentHostCallback3 instanceof OnPictureInPictureModeChangedProvider) {
            ((OnPictureInPictureModeChangedProvider)fragmentHostCallback3).removeOnPictureInPictureModeChangedListener(this.mOnPictureInPictureModeChangedListener);
        }
        FragmentHostCallback fragmentHostCallback4 = this.mHost;
        if(fragmentHostCallback4 instanceof MenuHost && this.mParent == null) {
            ((MenuHost)fragmentHostCallback4).removeMenuProvider(this.mMenuProvider);
        }
        this.mHost = null;
        this.mContainer = null;
        this.mParent = null;
        if(this.mOnBackPressedDispatcher != null) {
            this.mOnBackPressedCallback.remove();
            this.mOnBackPressedDispatcher = null;
        }
        ActivityResultLauncher activityResultLauncher0 = this.mStartActivityForResult;
        if(activityResultLauncher0 != null) {
            activityResultLauncher0.unregister();
            this.mStartIntentSenderForResult.unregister();
            this.mRequestPermissions.unregister();
        }
    }

    void dispatchDestroyView() {
        this.dispatchStateChange(1);
    }

    void dispatchLowMemory(boolean z) {
        if(z && this.mHost instanceof OnTrimMemoryProvider) {
            this.throwException(new IllegalStateException("Do not call dispatchLowMemory() on host. Host implements OnTrimMemoryProvider and automatically dispatches low memory callbacks to fragments."));
        }
        for(Object object0: this.mFragmentStore.getFragments()) {
            Fragment fragment0 = (Fragment)object0;
            if(fragment0 != null) {
                fragment0.performLowMemory();
                if(z) {
                    fragment0.mChildFragmentManager.dispatchLowMemory(true);
                }
            }
        }
    }

    void dispatchMultiWindowModeChanged(boolean z, boolean z1) {
        if(z1 && this.mHost instanceof OnMultiWindowModeChangedProvider) {
            this.throwException(new IllegalStateException("Do not call dispatchMultiWindowModeChanged() on host. Host implements OnMultiWindowModeChangedProvider and automatically dispatches multi-window mode changes to fragments."));
        }
        for(Object object0: this.mFragmentStore.getFragments()) {
            Fragment fragment0 = (Fragment)object0;
            if(fragment0 != null && z1) {
                fragment0.mChildFragmentManager.dispatchMultiWindowModeChanged(z, true);
            }
        }
    }

    void dispatchOnAttachFragment(Fragment fragment0) {
        for(Object object0: this.mOnAttachListeners) {
            ((FragmentOnAttachListener)object0).onAttachFragment(this, fragment0);
        }
    }

    void dispatchOnHiddenChanged() {
        for(Object object0: this.mFragmentStore.getActiveFragments()) {
            Fragment fragment0 = (Fragment)object0;
            if(fragment0 != null) {
                fragment0.onHiddenChanged(fragment0.isHidden());
                fragment0.mChildFragmentManager.dispatchOnHiddenChanged();
            }
        }
    }

    boolean dispatchOptionsItemSelected(MenuItem menuItem0) {
        if(this.mCurState < 1) {
            return false;
        }
        for(Object object0: this.mFragmentStore.getFragments()) {
            if(((Fragment)object0) != null && ((Fragment)object0).performOptionsItemSelected(menuItem0)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    void dispatchOptionsMenuClosed(Menu menu0) {
        if(this.mCurState >= 1) {
            for(Object object0: this.mFragmentStore.getFragments()) {
                Fragment fragment0 = (Fragment)object0;
                if(fragment0 != null) {
                    fragment0.performOptionsMenuClosed(menu0);
                }
            }
        }
    }

    private void dispatchParentPrimaryNavigationFragmentChanged(Fragment fragment0) {
        if(fragment0 != null && fragment0.equals(this.findActiveFragment(fragment0.mWho))) {
            fragment0.performPrimaryNavigationFragmentChanged();
        }
    }

    void dispatchPause() {
        this.dispatchStateChange(5);
    }

    void dispatchPictureInPictureModeChanged(boolean z, boolean z1) {
        if(z1 && this.mHost instanceof OnPictureInPictureModeChangedProvider) {
            this.throwException(new IllegalStateException("Do not call dispatchPictureInPictureModeChanged() on host. Host implements OnPictureInPictureModeChangedProvider and automatically dispatches picture-in-picture mode changes to fragments."));
        }
        for(Object object0: this.mFragmentStore.getFragments()) {
            Fragment fragment0 = (Fragment)object0;
            if(fragment0 != null && z1) {
                fragment0.mChildFragmentManager.dispatchPictureInPictureModeChanged(z, true);
            }
        }
    }

    boolean dispatchPrepareOptionsMenu(Menu menu0) {
        boolean z = false;
        if(this.mCurState < 1) {
            return false;
        }
        for(Object object0: this.mFragmentStore.getFragments()) {
            Fragment fragment0 = (Fragment)object0;
            if(fragment0 != null && this.isParentMenuVisible(fragment0) && fragment0.performPrepareOptionsMenu(menu0)) {
                z = true;
            }
        }
        return z;
    }

    void dispatchPrimaryNavigationFragmentChanged() {
        this.updateOnBackPressedCallbackEnabled();
        this.dispatchParentPrimaryNavigationFragmentChanged(this.mPrimaryNav);
    }

    void dispatchResume() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.setIsStateSaved(false);
        this.dispatchStateChange(7);
    }

    void dispatchStart() {
        this.mStateSaved = false;
        this.mStopped = false;
        this.mNonConfig.setIsStateSaved(false);
        this.dispatchStateChange(5);
    }

    private void dispatchStateChange(int v) {
        try {
            this.mExecutingActions = true;
            this.mFragmentStore.dispatchStateChange(v);
            this.moveToState(v, false);
            for(Object object0: this.collectAllSpecialEffectsController()) {
                ((SpecialEffectsController)object0).forceCompleteAllOperations();
            }
        }
        finally {
            this.mExecutingActions = false;
        }
        this.execPendingActions(true);
    }

    void dispatchStop() {
        this.mStopped = true;
        this.mNonConfig.setIsStateSaved(true);
        this.dispatchStateChange(4);
    }

    void dispatchViewCreated() {
        this.dispatchStateChange(2);
    }

    private void doPendingDeferredStart() {
        if(this.mHavePendingDeferredStart) {
            this.mHavePendingDeferredStart = false;
            this.startPendingDeferredFragments();
        }
    }

    public void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
        this.mFragmentStore.dump(s, fileDescriptor0, printWriter0, arr_s);
        ArrayList arrayList0 = this.mCreatedMenus;
        if(arrayList0 != null) {
            int v1 = arrayList0.size();
            if(v1 > 0) {
                printWriter0.print(s);
                printWriter0.println("Fragments Created Menus:");
                for(int v2 = 0; v2 < v1; ++v2) {
                    Fragment fragment0 = (Fragment)this.mCreatedMenus.get(v2);
                    printWriter0.print(s);
                    printWriter0.print("  #");
                    printWriter0.print(v2);
                    printWriter0.print(": ");
                    printWriter0.println("Fragment{51766cea} (f0d790fa-4330-4c14-ab99-cc8d537b445b)");
                }
            }
        }
        ArrayList arrayList1 = this.mBackStack;
        if(arrayList1 != null) {
            int v3 = arrayList1.size();
            if(v3 > 0) {
                printWriter0.print(s);
                printWriter0.println("Back Stack:");
                for(int v4 = 0; v4 < v3; ++v4) {
                    BackStackRecord backStackRecord0 = (BackStackRecord)this.mBackStack.get(v4);
                    printWriter0.print(s);
                    printWriter0.print("  #");
                    printWriter0.print(v4);
                    printWriter0.print(": ");
                    printWriter0.println(backStackRecord0.toString());
                    backStackRecord0.dump(s + "    ", printWriter0);
                }
            }
        }
        printWriter0.print(s);
        printWriter0.println("Back Stack Index: " + this.mBackStackIndex.get());
        synchronized(this.mPendingActions) {
            int v6 = this.mPendingActions.size();
            if(v6 > 0) {
                printWriter0.print(s);
                printWriter0.println("Pending Actions:");
                for(int v = 0; v < v6; ++v) {
                    OpGenerator fragmentManager$OpGenerator0 = (OpGenerator)this.mPendingActions.get(v);
                    printWriter0.print(s);
                    printWriter0.print("  #");
                    printWriter0.print(v);
                    printWriter0.print(": ");
                    printWriter0.println(fragmentManager$OpGenerator0);
                }
            }
        }
        printWriter0.print(s);
        printWriter0.println("FragmentManager misc state:");
        printWriter0.print(s);
        printWriter0.print("  mHost=");
        printWriter0.println(this.mHost);
        printWriter0.print(s);
        printWriter0.print("  mContainer=");
        printWriter0.println(this.mContainer);
        if(this.mParent != null) {
            printWriter0.print(s);
            printWriter0.print("  mParent=");
            printWriter0.println(this.mParent);
        }
        printWriter0.print(s);
        printWriter0.print("  mCurState=");
        printWriter0.print(this.mCurState);
        printWriter0.print(" mStateSaved=");
        printWriter0.print(this.mStateSaved);
        printWriter0.print(" mStopped=");
        printWriter0.print(this.mStopped);
        printWriter0.print(" mDestroyed=");
        printWriter0.println(this.mDestroyed);
        if(this.mNeedMenuInvalidate) {
            printWriter0.print(s);
            printWriter0.print("  mNeedMenuInvalidate=");
            printWriter0.println(this.mNeedMenuInvalidate);
        }
    }

    @Deprecated
    public static void enableDebugLogging(boolean z) {
        FragmentManager.DEBUG = z;
    }

    private void endAnimatingAwayFragments() {
        for(Object object0: this.collectAllSpecialEffectsController()) {
            ((SpecialEffectsController)object0).forceCompleteAllOperations();
        }
    }

    void enqueueAction(OpGenerator fragmentManager$OpGenerator0, boolean z) {
        if(!z) {
            if(this.mHost == null) {
                throw this.mDestroyed ? new IllegalStateException("FragmentManager has been destroyed") : new IllegalStateException("FragmentManager has not been attached to a host.");
            }
            this.checkStateLoss();
        }
        synchronized(this.mPendingActions) {
            if(this.mHost == null) {
                if(!z) {
                    throw new IllegalStateException("Activity has been destroyed");
                }
                return;
            }
            this.mPendingActions.add(fragmentManager$OpGenerator0);
            this.scheduleCommit();
        }
    }

    private void ensureExecReady(boolean z) {
        if(this.mExecutingActions) {
            throw new IllegalStateException("FragmentManager is already executing transactions");
        }
        if(this.mHost == null) {
            throw this.mDestroyed ? new IllegalStateException("FragmentManager has been destroyed") : new IllegalStateException("FragmentManager has not been attached to a host.");
        }
        if(Looper.myLooper() != this.mHost.getHandler().getLooper()) {
            throw new IllegalStateException("Must be called from main thread of fragment host");
        }
        if(!z) {
            this.checkStateLoss();
        }
        if(this.mTmpRecords == null) {
            this.mTmpRecords = new ArrayList();
            this.mTmpIsPop = new ArrayList();
        }
    }

    // 检测为 Lambda 实现
    boolean execPendingActions(boolean z) [...]

    void execSingleAction(OpGenerator fragmentManager$OpGenerator0, boolean z) {
        if(z && (this.mHost == null || this.mDestroyed)) {
            return;
        }
        this.ensureExecReady(z);
        if(fragmentManager$OpGenerator0.generateOps(this.mTmpRecords, this.mTmpIsPop)) {
            try {
                this.mExecutingActions = true;
                this.removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            }
            finally {
                this.cleanupExec();
            }
        }
        this.updateOnBackPressedCallbackEnabled();
        this.doPendingDeferredStart();
        this.mFragmentStore.burpActive();
    }

    private static void executeOps(ArrayList arrayList0, ArrayList arrayList1, int v, int v1) {
        while(v < v1) {
            BackStackRecord backStackRecord0 = (BackStackRecord)arrayList0.get(v);
            if(((Boolean)arrayList1.get(v)).booleanValue()) {
                backStackRecord0.bumpBackStackNesting(-1);
                backStackRecord0.executePopOps();
            }
            else {
                backStackRecord0.bumpBackStackNesting(1);
                backStackRecord0.executeOps();
            }
            ++v;
        }
    }

    private void executeOpsTogether(ArrayList arrayList0, ArrayList arrayList1, int v, int v1) {
        boolean z = ((BackStackRecord)arrayList0.get(v)).mReorderingAllowed;
        ArrayList arrayList2 = this.mTmpAddedFragments;
        if(arrayList2 == null) {
            this.mTmpAddedFragments = new ArrayList();
        }
        else {
            arrayList2.clear();
        }
        this.mTmpAddedFragments.addAll(this.mFragmentStore.getFragments());
        Fragment fragment0 = this.getPrimaryNavigationFragment();
        int v2 = v;
        boolean z1 = false;
        while(v2 < v1) {
            BackStackRecord backStackRecord0 = (BackStackRecord)arrayList0.get(v2);
            fragment0 = ((Boolean)arrayList1.get(v2)).booleanValue() ? backStackRecord0.trackAddedFragmentsInPop(this.mTmpAddedFragments, fragment0) : backStackRecord0.expandOps(this.mTmpAddedFragments, fragment0);
            z1 = z1 || backStackRecord0.mAddToBackStack;
            ++v2;
        }
        this.mTmpAddedFragments.clear();
        if(!z && this.mCurState >= 1) {
            for(int v3 = v; v3 < v1; ++v3) {
                for(Object object0: ((BackStackRecord)arrayList0.get(v3)).mOps) {
                    Fragment fragment1 = ((Op)object0).mFragment;
                    if(fragment1 != null && fragment1.mFragmentManager != null) {
                        FragmentStateManager fragmentStateManager0 = this.createOrGetFragmentStateManager(fragment1);
                        this.mFragmentStore.makeActive(fragmentStateManager0);
                    }
                }
            }
        }
        FragmentManager.executeOps(arrayList0, arrayList1, v, v1);
        boolean z2 = ((Boolean)arrayList1.get(v1 - 1)).booleanValue();
        for(int v4 = v; v4 < v1; ++v4) {
            BackStackRecord backStackRecord1 = (BackStackRecord)arrayList0.get(v4);
            if(z2) {
                for(int v5 = backStackRecord1.mOps.size() - 1; v5 >= 0; --v5) {
                    Fragment fragment2 = ((Op)backStackRecord1.mOps.get(v5)).mFragment;
                    if(fragment2 != null) {
                        this.createOrGetFragmentStateManager(fragment2).moveToExpectedState();
                    }
                }
            }
            else {
                for(Object object1: backStackRecord1.mOps) {
                    Fragment fragment3 = ((Op)object1).mFragment;
                    if(fragment3 != null) {
                        this.createOrGetFragmentStateManager(fragment3).moveToExpectedState();
                    }
                }
            }
        }
        this.moveToState(this.mCurState, true);
        for(Object object2: this.collectChangedControllers(arrayList0, v, v1)) {
            ((SpecialEffectsController)object2).updateOperationDirection(z2);
            ((SpecialEffectsController)object2).markPostponedState();
            ((SpecialEffectsController)object2).executePendingOperations();
        }
        while(v < v1) {
            BackStackRecord backStackRecord2 = (BackStackRecord)arrayList0.get(v);
            if(((Boolean)arrayList1.get(v)).booleanValue() && backStackRecord2.mIndex >= 0) {
                backStackRecord2.mIndex = -1;
            }
            backStackRecord2.runOnCommitRunnables();
            ++v;
        }
        if(z1) {
            this.reportBackStackChanged();
        }
    }

    public boolean executePendingTransactions() {
        boolean z = this.execPendingActions(true);
        this.forcePostponedTransactions();
        return z;
    }

    Fragment findActiveFragment(String s) {
        return this.mFragmentStore.findActiveFragment(s);
    }

    private int findBackStackIndex(String s, int v, boolean z) {
        if(this.mBackStack != null && !this.mBackStack.isEmpty()) {
            if(s == null && v < 0) {
                return z ? 0 : this.mBackStack.size() - 1;
            }
            int v1;
            for(v1 = this.mBackStack.size() - 1; v1 >= 0; --v1) {
                BackStackRecord backStackRecord0 = (BackStackRecord)this.mBackStack.get(v1);
                if(s != null && s.equals(backStackRecord0.getName()) || v >= 0 && v == backStackRecord0.mIndex) {
                    break;
                }
            }
            if(v1 < 0) {
                return v1;
            }
            if(z) {
                while(v1 > 0) {
                    BackStackRecord backStackRecord1 = (BackStackRecord)this.mBackStack.get(v1 - 1);
                    if((s == null || !s.equals(backStackRecord1.getName())) && (v < 0 || v != backStackRecord1.mIndex)) {
                        break;
                    }
                    --v1;
                }
                return v1;
            }
            return v1 == this.mBackStack.size() - 1 ? -1 : v1 + 1;
        }
        return -1;
    }

    public static Fragment findFragment(View view0) {
        Fragment fragment0 = FragmentManager.findViewFragment(view0);
        if(fragment0 == null) {
            throw new IllegalStateException("View " + view0 + " does not have a Fragment set");
        }
        return fragment0;
    }

    public Fragment findFragmentById(int v) {
        return this.mFragmentStore.findFragmentById(v);
    }

    public Fragment findFragmentByTag(String s) {
        return this.mFragmentStore.findFragmentByTag(s);
    }

    Fragment findFragmentByWho(String s) {
        return this.mFragmentStore.findFragmentByWho(s);
    }

    static FragmentManager findFragmentManager(View view0) {
        FragmentActivity fragmentActivity0;
        Fragment fragment0 = FragmentManager.findViewFragment(view0);
        if(fragment0 != null) {
            if(!fragment0.isAdded()) {
                throw new IllegalStateException("The Fragment " + fragment0 + " that owns View " + view0 + " has already been destroyed. Nested fragments should always use the child FragmentManager.");
            }
            return fragment0.getChildFragmentManager();
        }
        for(Context context0 = view0.getContext(); true; context0 = ((ContextWrapper)context0).getBaseContext()) {
            fragmentActivity0 = null;
            if(!(context0 instanceof ContextWrapper)) {
                break;
            }
            if(context0 instanceof FragmentActivity) {
                fragmentActivity0 = (FragmentActivity)context0;
                break;
            }
        }
        if(fragmentActivity0 == null) {
            throw new IllegalStateException("View " + view0 + " is not within a subclass of FragmentActivity.");
        }
        return fragmentActivity0.getSupportFragmentManager();
    }

    private static Fragment findViewFragment(View view0) {
        while(view0 != null) {
            Fragment fragment0 = FragmentManager.getViewFragment(view0);
            if(fragment0 != null) {
                return fragment0;
            }
            ViewParent viewParent0 = view0.getParent();
            view0 = viewParent0 instanceof View ? ((View)viewParent0) : null;
        }
        return null;
    }

    private void forcePostponedTransactions() {
        for(Object object0: this.collectAllSpecialEffectsController()) {
            ((SpecialEffectsController)object0).forcePostponedExecutePendingOperations();
        }
    }

    private boolean generateOpsForPendingActions(ArrayList arrayList0, ArrayList arrayList1) {
        synchronized(this.mPendingActions) {
            if(this.mPendingActions.isEmpty()) {
                return false;
            }
            try {
                int v3 = this.mPendingActions.size();
                boolean z = false;
                for(int v = 0; v < v3; ++v) {
                    z |= ((OpGenerator)this.mPendingActions.get(v)).generateOps(arrayList0, arrayList1);
                }
                return z;
            }
            finally {
                this.mPendingActions.clear();
                this.mHost.getHandler().removeCallbacks(this.mExecCommit);
            }
        }
    }

    int getActiveFragmentCount() {
        return this.mFragmentStore.getActiveFragmentCount();
    }

    List getActiveFragments() {
        return this.mFragmentStore.getActiveFragments();
    }

    public BackStackEntry getBackStackEntryAt(int v) {
        return (BackStackEntry)this.mBackStack.get(v);
    }

    public int getBackStackEntryCount() {
        return this.mBackStack == null ? 0 : this.mBackStack.size();
    }

    private FragmentManagerViewModel getChildNonConfig(Fragment fragment0) {
        return this.mNonConfig.getChildNonConfig(fragment0);
    }

    FragmentContainer getContainer() {
        return this.mContainer;
    }

    public Fragment getFragment(Bundle bundle0, String s) {
        String s1 = bundle0.getString(s);
        if(s1 == null) {
            return null;
        }
        Fragment fragment0 = this.findActiveFragment(s1);
        if(fragment0 == null) {
            this.throwException(new IllegalStateException("Fragment no longer exists for key " + s + ": unique id " + s1));
        }
        return fragment0;
    }

    private ViewGroup getFragmentContainer(Fragment fragment0) {
        if(fragment0.mContainer != null) {
            return fragment0.mContainer;
        }
        if(fragment0.mContainerId <= 0) {
            return null;
        }
        if(this.mContainer.onHasView()) {
            View view0 = this.mContainer.onFindViewById(fragment0.mContainerId);
            return view0 instanceof ViewGroup ? ((ViewGroup)view0) : null;
        }
        return null;
    }

    public FragmentFactory getFragmentFactory() {
        FragmentFactory fragmentFactory0 = this.mFragmentFactory;
        if(fragmentFactory0 != null) {
            return fragmentFactory0;
        }
        return this.mParent == null ? this.mHostFragmentFactory : this.mParent.mFragmentManager.getFragmentFactory();
    }

    FragmentStore getFragmentStore() {
        return this.mFragmentStore;
    }

    public List getFragments() {
        return this.mFragmentStore.getFragments();
    }

    public FragmentHostCallback getHost() {
        return this.mHost;
    }

    LayoutInflater.Factory2 getLayoutInflaterFactory() {
        return this.mLayoutInflaterFactory;
    }

    FragmentLifecycleCallbacksDispatcher getLifecycleCallbacksDispatcher() {
        return this.mLifecycleCallbacksDispatcher;
    }

    Fragment getParent() {
        return this.mParent;
    }

    public Fragment getPrimaryNavigationFragment() {
        return this.mPrimaryNav;
    }

    SpecialEffectsControllerFactory getSpecialEffectsControllerFactory() {
        SpecialEffectsControllerFactory specialEffectsControllerFactory0 = this.mSpecialEffectsControllerFactory;
        if(specialEffectsControllerFactory0 != null) {
            return specialEffectsControllerFactory0;
        }
        return this.mParent == null ? this.mDefaultSpecialEffectsControllerFactory : this.mParent.mFragmentManager.getSpecialEffectsControllerFactory();
    }

    public Policy getStrictModePolicy() {
        return this.mStrictModePolicy;
    }

    static Fragment getViewFragment(View view0) {
        Object object0 = view0.getTag(id.fragment_container_view_tag);
        return object0 instanceof Fragment ? ((Fragment)object0) : null;
    }

    ViewModelStore getViewModelStore(Fragment fragment0) {
        return this.mNonConfig.getViewModelStore(fragment0);
    }

    void handleOnBackPressed() {
        this.execPendingActions(true);
        if(this.mOnBackPressedCallback.isEnabled()) {
            this.popBackStackImmediate();
            return;
        }
        this.mOnBackPressedDispatcher.onBackPressed();
    }

    void hideFragment(Fragment fragment0) {
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "hide: " + fragment0);
        }
        if(!fragment0.mHidden) {
            fragment0.mHidden = true;
            fragment0.mHiddenChanged ^= true;
            this.setVisibleRemovingFragment(fragment0);
        }
    }

    void invalidateMenuForFragment(Fragment fragment0) {
        if(fragment0.mAdded && this.isMenuAvailable(fragment0)) {
            this.mNeedMenuInvalidate = true;
        }
    }

    public boolean isDestroyed() {
        return this.mDestroyed;
    }

    // 去混淆评级： 低(20)
    public static boolean isLoggingEnabled(int v) {
        return FragmentManager.DEBUG || Log.isLoggable("FragmentManager", v);
    }

    // 去混淆评级： 低(30)
    private boolean isMenuAvailable(Fragment fragment0) {
        return fragment0.mHasMenu && fragment0.mMenuVisible || fragment0.mChildFragmentManager.checkForMenus();
    }

    // 去混淆评级： 低(30)
    private boolean isParentAdded() {
        return this.mParent == null ? true : this.mParent.isAdded() && this.mParent.getParentFragmentManager().isParentAdded();
    }

    boolean isParentHidden(Fragment fragment0) {
        return fragment0 == null ? false : fragment0.isHidden();
    }

    boolean isParentMenuVisible(Fragment fragment0) {
        return fragment0 == null ? true : fragment0.isMenuVisible();
    }

    boolean isPrimaryNavigation(Fragment fragment0) {
        if(fragment0 == null) {
            return true;
        }
        FragmentManager fragmentManager0 = fragment0.mFragmentManager;
        return fragment0.equals(fragmentManager0.getPrimaryNavigationFragment()) && this.isPrimaryNavigation(fragmentManager0.mParent);
    }

    boolean isStateAtLeast(int v) {
        return this.mCurState >= v;
    }

    // 去混淆评级： 低(20)
    public boolean isStateSaved() {
        return this.mStateSaved || this.mStopped;
    }

    // 检测为 Lambda 实现
    Bundle lambda$attachController$4$androidx-fragment-app-FragmentManager() [...]

    // 检测为 Lambda 实现
    void lambda$new$0$androidx-fragment-app-FragmentManager(Configuration configuration0) [...]

    // 检测为 Lambda 实现
    void lambda$new$1$androidx-fragment-app-FragmentManager(Integer integer0) [...]

    // 检测为 Lambda 实现
    void lambda$new$2$androidx-fragment-app-FragmentManager(MultiWindowModeChangedInfo multiWindowModeChangedInfo0) [...]

    // 检测为 Lambda 实现
    void lambda$new$3$androidx-fragment-app-FragmentManager(PictureInPictureModeChangedInfo pictureInPictureModeChangedInfo0) [...]

    void launchRequestPermissions(Fragment fragment0, String[] arr_s, int v) {
        if(this.mRequestPermissions != null) {
            LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo0 = new LaunchedFragmentInfo(fragment0.mWho, v);
            this.mLaunchedFragments.addLast(fragmentManager$LaunchedFragmentInfo0);
            this.mRequestPermissions.launch(arr_s);
            return;
        }
        this.mHost.onRequestPermissionsFromFragment(fragment0, arr_s, v);
    }

    void launchStartActivityForResult(Fragment fragment0, Intent intent0, int v, Bundle bundle0) {
        if(this.mStartActivityForResult != null) {
            LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo0 = new LaunchedFragmentInfo(fragment0.mWho, v);
            this.mLaunchedFragments.addLast(fragmentManager$LaunchedFragmentInfo0);
            if(intent0 != null && bundle0 != null) {
                intent0.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundle0);
            }
            this.mStartActivityForResult.launch(intent0);
            return;
        }
        this.mHost.onStartActivityFromFragment(fragment0, intent0, v, bundle0);
    }

    void launchStartIntentSenderForResult(Fragment fragment0, IntentSender intentSender0, int v, Intent intent0, int v1, int v2, int v3, Bundle bundle0) throws IntentSender.SendIntentException {
        if(this.mStartIntentSenderForResult != null) {
            if(bundle0 != null) {
                if(intent0 == null) {
                    intent0 = new Intent();
                    intent0.putExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", true);
                }
                if(FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "ActivityOptions " + bundle0 + " were added to fillInIntent " + intent0 + " for fragment " + fragment0);
                }
                intent0.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundle0);
            }
            IntentSenderRequest intentSenderRequest0 = new Builder(intentSender0).setFillInIntent(intent0).setFlags(v2, v1).build();
            LaunchedFragmentInfo fragmentManager$LaunchedFragmentInfo0 = new LaunchedFragmentInfo(fragment0.mWho, v);
            this.mLaunchedFragments.addLast(fragmentManager$LaunchedFragmentInfo0);
            if(FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Fragment " + fragment0 + "is launching an IntentSender for result ");
            }
            this.mStartIntentSenderForResult.launch(intentSenderRequest0);
            return;
        }
        this.mHost.onStartIntentSenderFromFragment(fragment0, intentSender0, v, intent0, v1, v2, v3, bundle0);
    }

    void moveToState(int v, boolean z) {
        if(this.mHost == null && v != -1) {
            throw new IllegalStateException("No activity");
        }
        if(z || v != this.mCurState) {
            this.mCurState = v;
            this.mFragmentStore.moveToExpectedState();
            this.startPendingDeferredFragments();
            if(this.mNeedMenuInvalidate) {
                FragmentHostCallback fragmentHostCallback0 = this.mHost;
                if(fragmentHostCallback0 != null && this.mCurState == 7) {
                    fragmentHostCallback0.onSupportInvalidateOptionsMenu();
                    this.mNeedMenuInvalidate = false;
                }
            }
        }
    }

    void noteStateNotSaved() {
        if(this.mHost != null) {
            this.mStateSaved = false;
            this.mStopped = false;
            this.mNonConfig.setIsStateSaved(false);
            for(Object object0: this.mFragmentStore.getFragments()) {
                Fragment fragment0 = (Fragment)object0;
                if(fragment0 != null) {
                    fragment0.noteStateNotSaved();
                }
            }
        }
    }

    void onContainerAvailable(FragmentContainerView fragmentContainerView0) {
        for(Object object0: this.mFragmentStore.getActiveFragmentStateManagers()) {
            FragmentStateManager fragmentStateManager0 = (FragmentStateManager)object0;
            Fragment fragment0 = fragmentStateManager0.getFragment();
            if(fragment0.mContainerId == fragmentContainerView0.getId() && fragment0.mView != null && fragment0.mView.getParent() == null) {
                fragment0.mContainer = fragmentContainerView0;
                fragmentStateManager0.addViewToContainer();
            }
        }
    }

    @Deprecated
    public FragmentTransaction openTransaction() {
        return this.beginTransaction();
    }

    void performPendingDeferredStart(FragmentStateManager fragmentStateManager0) {
        Fragment fragment0 = fragmentStateManager0.getFragment();
        if(fragment0.mDeferStart) {
            if(this.mExecutingActions) {
                this.mHavePendingDeferredStart = true;
                return;
            }
            fragment0.mDeferStart = false;
            fragmentStateManager0.moveToExpectedState();
        }
    }

    public void popBackStack() {
        this.enqueueAction(new PopBackStackState(this, null, -1, 0), false);
    }

    public void popBackStack(int v, int v1) {
        this.popBackStack(v, v1, false);
    }

    void popBackStack(int v, int v1, boolean z) {
        if(v < 0) {
            throw new IllegalArgumentException("Bad id: " + v);
        }
        this.enqueueAction(new PopBackStackState(this, null, v, v1), z);
    }

    public void popBackStack(String s, int v) {
        this.enqueueAction(new PopBackStackState(this, s, -1, v), false);
    }

    private boolean popBackStackImmediate(String s, int v, int v1) {
        this.execPendingActions(false);
        this.ensureExecReady(true);
        if(this.mPrimaryNav != null && v < 0 && s == null && this.mPrimaryNav.getChildFragmentManager().popBackStackImmediate()) {
            return true;
        }
        boolean z = this.popBackStackState(this.mTmpRecords, this.mTmpIsPop, s, v, v1);
        if(z) {
            try {
                this.mExecutingActions = true;
                this.removeRedundantOperationsAndExecute(this.mTmpRecords, this.mTmpIsPop);
            }
            finally {
                this.cleanupExec();
            }
        }
        this.updateOnBackPressedCallbackEnabled();
        this.doPendingDeferredStart();
        this.mFragmentStore.burpActive();
        return z;
    }

    public boolean popBackStackImmediate() {
        return this.popBackStackImmediate(null, -1, 0);
    }

    public boolean popBackStackImmediate(int v, int v1) {
        if(v < 0) {
            throw new IllegalArgumentException("Bad id: " + v);
        }
        return this.popBackStackImmediate(null, v, v1);
    }

    public boolean popBackStackImmediate(String s, int v) {
        return this.popBackStackImmediate(s, -1, v);
    }

    boolean popBackStackState(ArrayList arrayList0, ArrayList arrayList1, String s, int v, int v1) {
        int v2 = this.findBackStackIndex(s, v, (v1 & 1) != 0);
        if(v2 < 0) {
            return false;
        }
        for(int v3 = this.mBackStack.size() - 1; v3 >= v2; --v3) {
            arrayList0.add(((BackStackRecord)this.mBackStack.remove(v3)));
            arrayList1.add(Boolean.TRUE);
        }
        return true;
    }

    public void putFragment(Bundle bundle0, String s, Fragment fragment0) {
        if(fragment0.mFragmentManager != this) {
            this.throwException(new IllegalStateException("Fragment " + fragment0 + " is not currently in the FragmentManager"));
        }
        bundle0.putString(s, fragment0.mWho);
    }

    public void registerFragmentLifecycleCallbacks(FragmentLifecycleCallbacks fragmentManager$FragmentLifecycleCallbacks0, boolean z) {
        this.mLifecycleCallbacksDispatcher.registerFragmentLifecycleCallbacks(fragmentManager$FragmentLifecycleCallbacks0, z);
    }

    void removeFragment(Fragment fragment0) {
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "remove: " + fragment0 + " nesting=" + fragment0.mBackStackNesting);
        }
        boolean z = fragment0.isInBackStack();
        if(fragment0.mDetached && z) {
            return;
        }
        this.mFragmentStore.removeFragment(fragment0);
        if(this.isMenuAvailable(fragment0)) {
            this.mNeedMenuInvalidate = true;
        }
        fragment0.mRemoving = true;
        this.setVisibleRemovingFragment(fragment0);
    }

    public void removeFragmentOnAttachListener(FragmentOnAttachListener fragmentOnAttachListener0) {
        this.mOnAttachListeners.remove(fragmentOnAttachListener0);
    }

    public void removeOnBackStackChangedListener(OnBackStackChangedListener fragmentManager$OnBackStackChangedListener0) {
        ArrayList arrayList0 = this.mBackStackChangeListeners;
        if(arrayList0 != null) {
            arrayList0.remove(fragmentManager$OnBackStackChangedListener0);
        }
    }

    private void removeRedundantOperationsAndExecute(ArrayList arrayList0, ArrayList arrayList1) {
        if(!arrayList0.isEmpty()) {
            if(arrayList0.size() != arrayList1.size()) {
                throw new IllegalStateException("Internal error with the back stack records");
            }
            int v = arrayList0.size();
            int v2 = 0;
            for(int v1 = 0; v1 < v; ++v1) {
                if(!((BackStackRecord)arrayList0.get(v1)).mReorderingAllowed) {
                    if(v2 != v1) {
                        this.executeOpsTogether(arrayList0, arrayList1, v2, v1);
                    }
                    v2 = v1 + 1;
                    if(((Boolean)arrayList1.get(v1)).booleanValue()) {
                        while(v2 < v && ((Boolean)arrayList1.get(v2)).booleanValue() && !((BackStackRecord)arrayList0.get(v2)).mReorderingAllowed) {
                            ++v2;
                        }
                    }
                    this.executeOpsTogether(arrayList0, arrayList1, v1, v2);
                    v1 = v2 - 1;
                }
            }
            if(v2 != v) {
                this.executeOpsTogether(arrayList0, arrayList1, v2, v);
            }
        }
    }

    void removeRetainedFragment(Fragment fragment0) {
        this.mNonConfig.removeRetainedFragment(fragment0);
    }

    private void reportBackStackChanged() {
        if(this.mBackStackChangeListeners != null) {
            for(int v = 0; v < this.mBackStackChangeListeners.size(); ++v) {
                ((OnBackStackChangedListener)this.mBackStackChangeListeners.get(v)).onBackStackChanged();
            }
        }
    }

    void restoreAllState(Parcelable parcelable0, FragmentManagerNonConfig fragmentManagerNonConfig0) {
        if(this.mHost instanceof ViewModelStoreOwner) {
            this.throwException(new IllegalStateException("You must use restoreSaveState when your FragmentHostCallback implements ViewModelStoreOwner"));
        }
        this.mNonConfig.restoreFromSnapshot(fragmentManagerNonConfig0);
        this.restoreSaveStateInternal(parcelable0);
    }

    public void restoreBackStack(String s) {
        this.enqueueAction((ArrayList arrayList0, ArrayList arrayList1) -> {
            boolean z;
            BackStackState backStackState0 = (BackStackState)FragmentManager.this.mBackStackStates.remove(s);
            if(backStackState0 == null) {
                return false;
            }
            HashMap hashMap0 = new HashMap();
            for(Object object0: arrayList0) {
                BackStackRecord backStackRecord0 = (BackStackRecord)object0;
                if(backStackRecord0.mBeingSaved) {
                    for(Object object1: backStackRecord0.mOps) {
                        Op fragmentTransaction$Op0 = (Op)object1;
                        if(fragmentTransaction$Op0.mFragment != null) {
                            hashMap0.put(fragmentTransaction$Op0.mFragment.mWho, fragmentTransaction$Op0.mFragment);
                        }
                    }
                }
            }
            Iterator iterator2 = backStackState0.instantiate(FragmentManager.this, hashMap0).iterator();
        alab1:
            while(true) {
                for(z = false; true; z = true) {
                    if(!iterator2.hasNext()) {
                        break alab1;
                    }
                    Object object2 = iterator2.next();
                    if(!((BackStackRecord)object2).generateOps(arrayList0, arrayList1) && !z) {
                        break;
                    }
                }
            }
            return z;
        }, false);
    }

    // 检测为 Lambda 实现
    boolean restoreBackStackState(ArrayList arrayList0, ArrayList arrayList1, String s) [...]

    void restoreSaveState(Parcelable parcelable0) {
        if(this.mHost instanceof SavedStateRegistryOwner) {
            this.throwException(new IllegalStateException("You cannot use restoreSaveState when your FragmentHostCallback implements SavedStateRegistryOwner."));
        }
        this.restoreSaveStateInternal(parcelable0);
    }

    void restoreSaveStateInternal(Parcelable parcelable0) {
        FragmentStateManager fragmentStateManager0;
        if(parcelable0 != null) {
            for(Object object0: ((Bundle)parcelable0).keySet()) {
                String s = (String)object0;
                if(s.startsWith("result_")) {
                    Bundle bundle0 = ((Bundle)parcelable0).getBundle(s);
                    if(bundle0 != null) {
                        bundle0.setClassLoader(this.mHost.getContext().getClassLoader());
                        this.mResults.put(s.substring(7), bundle0);
                    }
                }
            }
            ArrayList arrayList0 = new ArrayList();
            for(Object object1: ((Bundle)parcelable0).keySet()) {
                String s1 = (String)object1;
                if(s1.startsWith("fragment_")) {
                    Bundle bundle1 = ((Bundle)parcelable0).getBundle(s1);
                    if(bundle1 != null) {
                        bundle1.setClassLoader(this.mHost.getContext().getClassLoader());
                        arrayList0.add(((FragmentState)bundle1.getParcelable("state")));
                    }
                }
            }
            this.mFragmentStore.restoreSaveState(arrayList0);
            FragmentManagerState fragmentManagerState0 = (FragmentManagerState)((Bundle)parcelable0).getParcelable("state");
            if(fragmentManagerState0 != null) {
                this.mFragmentStore.resetActiveFragments();
                for(Object object2: fragmentManagerState0.mActive) {
                    FragmentState fragmentState0 = this.mFragmentStore.setSavedState(((String)object2), null);
                    if(fragmentState0 != null) {
                        Fragment fragment0 = this.mNonConfig.findRetainedFragmentByWho(fragmentState0.mWho);
                        if(fragment0 == null) {
                            ClassLoader classLoader0 = this.mHost.getContext().getClassLoader();
                            FragmentFactory fragmentFactory0 = this.getFragmentFactory();
                            fragmentStateManager0 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, classLoader0, fragmentFactory0, fragmentState0);
                        }
                        else {
                            if(FragmentManager.isLoggingEnabled(2)) {
                                Log.v("FragmentManager", "restoreSaveState: re-attaching retained " + fragment0);
                            }
                            fragmentStateManager0 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, fragment0, fragmentState0);
                        }
                        Fragment fragment1 = fragmentStateManager0.getFragment();
                        fragment1.mFragmentManager = this;
                        if(FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "restoreSaveState: active (" + fragment1.mWho + "): " + fragment1);
                        }
                        fragmentStateManager0.restoreState(this.mHost.getContext().getClassLoader());
                        this.mFragmentStore.makeActive(fragmentStateManager0);
                        fragmentStateManager0.setFragmentManagerState(this.mCurState);
                    }
                }
                for(Object object3: this.mNonConfig.getRetainedFragments()) {
                    Fragment fragment2 = (Fragment)object3;
                    if(!this.mFragmentStore.containsActiveFragment(fragment2.mWho)) {
                        if(FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "Discarding retained Fragment " + fragment2 + " that was not found in the set of active Fragments " + fragmentManagerState0.mActive);
                        }
                        this.mNonConfig.removeRetainedFragment(fragment2);
                        fragment2.mFragmentManager = this;
                        FragmentStateManager fragmentStateManager1 = new FragmentStateManager(this.mLifecycleCallbacksDispatcher, this.mFragmentStore, fragment2);
                        fragmentStateManager1.setFragmentManagerState(1);
                        fragmentStateManager1.moveToExpectedState();
                        fragment2.mRemoving = true;
                        fragmentStateManager1.moveToExpectedState();
                    }
                }
                this.mFragmentStore.restoreAddedFragments(fragmentManagerState0.mAdded);
                if(fragmentManagerState0.mBackStack == null) {
                    this.mBackStack = null;
                }
                else {
                    this.mBackStack = new ArrayList(fragmentManagerState0.mBackStack.length);
                    for(int v1 = 0; v1 < fragmentManagerState0.mBackStack.length; ++v1) {
                        BackStackRecord backStackRecord0 = fragmentManagerState0.mBackStack[v1].instantiate(this);
                        if(FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "restoreAllState: back stack #" + v1 + " (index " + backStackRecord0.mIndex + "): " + backStackRecord0);
                            PrintWriter printWriter0 = new PrintWriter(new LogWriter("FragmentManager"));
                            backStackRecord0.dump("  ", printWriter0, false);
                            printWriter0.close();
                        }
                        this.mBackStack.add(backStackRecord0);
                    }
                }
                this.mBackStackIndex.set(fragmentManagerState0.mBackStackIndex);
                if(fragmentManagerState0.mPrimaryNavActiveWho != null) {
                    Fragment fragment3 = this.findActiveFragment(fragmentManagerState0.mPrimaryNavActiveWho);
                    this.mPrimaryNav = fragment3;
                    this.dispatchParentPrimaryNavigationFragmentChanged(fragment3);
                }
                ArrayList arrayList1 = fragmentManagerState0.mBackStackStateKeys;
                if(arrayList1 != null) {
                    for(int v = 0; v < arrayList1.size(); ++v) {
                        String s2 = (String)arrayList1.get(v);
                        BackStackState backStackState0 = (BackStackState)fragmentManagerState0.mBackStackStates.get(v);
                        this.mBackStackStates.put(s2, backStackState0);
                    }
                }
                this.mLaunchedFragments = new ArrayDeque(fragmentManagerState0.mLaunchedFragments);
            }
        }
    }

    @Deprecated
    FragmentManagerNonConfig retainNonConfig() {
        if(this.mHost instanceof ViewModelStoreOwner) {
            this.throwException(new IllegalStateException("You cannot use retainNonConfig when your FragmentHostCallback implements ViewModelStoreOwner."));
        }
        return this.mNonConfig.getSnapshot();
    }

    static int reverseTransit(int v) {
        switch(v) {
            case 0x1001: {
                return 0x2002;
            }
            case 0x2002: {
                return 0x1001;
            }
            default: {
                if(v != 0x2005) {
                    switch(v) {
                        case 0x1003: {
                            return 0x1003;
                        }
                        case 4100: {
                            return 0x2005;
                        }
                        default: {
                            return 0;
                        }
                    }
                }
                return 4100;
            }
        }
    }

    Parcelable saveAllState() {
        if(this.mHost instanceof SavedStateRegistryOwner) {
            this.throwException(new IllegalStateException("You cannot use saveAllState when your FragmentHostCallback implements SavedStateRegistryOwner."));
        }
        Parcelable parcelable0 = this.saveAllStateInternal();
        return ((Bundle)parcelable0).isEmpty() ? null : parcelable0;
    }

    Bundle saveAllStateInternal() {
        BackStackRecordState[] arr_backStackRecordState;
        Bundle bundle0 = new Bundle();
        this.forcePostponedTransactions();
        this.endAnimatingAwayFragments();
        this.execPendingActions(true);
        this.mStateSaved = true;
        this.mNonConfig.setIsStateSaved(true);
        ArrayList arrayList0 = this.mFragmentStore.saveActiveFragments();
        ArrayList arrayList1 = this.mFragmentStore.getAllSavedState();
        if(!arrayList1.isEmpty()) {
            ArrayList arrayList2 = this.mFragmentStore.saveAddedFragments();
            ArrayList arrayList3 = this.mBackStack;
            if(arrayList3 == null) {
                arr_backStackRecordState = null;
            }
            else {
                int v = arrayList3.size();
                if(v > 0) {
                    arr_backStackRecordState = new BackStackRecordState[v];
                    for(int v1 = 0; v1 < v; ++v1) {
                        arr_backStackRecordState[v1] = new BackStackRecordState(((BackStackRecord)this.mBackStack.get(v1)));
                        if(FragmentManager.isLoggingEnabled(2)) {
                            Log.v("FragmentManager", "saveAllState: adding back stack #" + v1 + ": " + this.mBackStack.get(v1));
                        }
                    }
                }
                else {
                    arr_backStackRecordState = null;
                }
            }
            FragmentManagerState fragmentManagerState0 = new FragmentManagerState();
            fragmentManagerState0.mActive = arrayList0;
            fragmentManagerState0.mAdded = arrayList2;
            fragmentManagerState0.mBackStack = arr_backStackRecordState;
            fragmentManagerState0.mBackStackIndex = this.mBackStackIndex.get();
            Fragment fragment0 = this.mPrimaryNav;
            if(fragment0 != null) {
                fragmentManagerState0.mPrimaryNavActiveWho = fragment0.mWho;
            }
            fragmentManagerState0.mBackStackStateKeys.addAll(this.mBackStackStates.keySet());
            fragmentManagerState0.mBackStackStates.addAll(this.mBackStackStates.values());
            fragmentManagerState0.mLaunchedFragments = new ArrayList(this.mLaunchedFragments);
            bundle0.putParcelable("state", fragmentManagerState0);
            for(Object object0: this.mResults.keySet()) {
                bundle0.putBundle("result_" + ((String)object0), ((Bundle)this.mResults.get(((String)object0))));
            }
            for(Object object1: arrayList1) {
                Bundle bundle1 = new Bundle();
                bundle1.putParcelable("state", ((FragmentState)object1));
                bundle0.putBundle("fragment_" + ((FragmentState)object1).mWho, bundle1);
            }
        }
        else if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "saveAllState: no fragments!");
            return bundle0;
        }
        return bundle0;
    }

    public void saveBackStack(String s) {
        this.enqueueAction((ArrayList arrayList0, ArrayList arrayList1) -> {
            String s1;
            int v = FragmentManager.this.findBackStackIndex(s, -1, true);
            if(v < 0) {
                return false;
            }
            for(int v1 = v; v1 < FragmentManager.this.mBackStack.size(); ++v1) {
                BackStackRecord backStackRecord0 = (BackStackRecord)FragmentManager.this.mBackStack.get(v1);
                if(!backStackRecord0.mReorderingAllowed) {
                    FragmentManager.this.throwException(new IllegalArgumentException("saveBackStack(\"" + s + "\") included FragmentTransactions must use setReorderingAllowed(true) to ensure that the back stack can be restored as an atomic operation. Found " + backStackRecord0 + " that did not use setReorderingAllowed(true)."));
                }
            }
            HashSet hashSet0 = new HashSet();
            for(int v2 = v; v2 < FragmentManager.this.mBackStack.size(); ++v2) {
                BackStackRecord backStackRecord1 = (BackStackRecord)FragmentManager.this.mBackStack.get(v2);
                HashSet hashSet1 = new HashSet();
                HashSet hashSet2 = new HashSet();
                for(Object object0: backStackRecord1.mOps) {
                    Op fragmentTransaction$Op0 = (Op)object0;
                    Fragment fragment0 = fragmentTransaction$Op0.mFragment;
                    if(fragment0 != null) {
                        if(!fragmentTransaction$Op0.mFromExpandedOp || (fragmentTransaction$Op0.mCmd == 1 || fragmentTransaction$Op0.mCmd == 2 || fragmentTransaction$Op0.mCmd == 8)) {
                            hashSet0.add(fragment0);
                            hashSet1.add(fragment0);
                        }
                        if(fragmentTransaction$Op0.mCmd == 1 || fragmentTransaction$Op0.mCmd == 2) {
                            hashSet2.add(fragment0);
                        }
                    }
                }
                hashSet1.removeAll(hashSet2);
                if(!hashSet1.isEmpty()) {
                    StringBuilder stringBuilder0 = new StringBuilder("saveBackStack(\"");
                    stringBuilder0.append(s);
                    stringBuilder0.append("\") must be self contained and not reference fragments from non-saved FragmentTransactions. Found reference to fragment");
                    if(hashSet1.size() == 1) {
                        Object object1 = hashSet1.iterator().next();
                        s1 = " " + object1;
                    }
                    else {
                        s1 = "s " + hashSet1;
                    }
                    stringBuilder0.append(s1);
                    stringBuilder0.append(" in ");
                    stringBuilder0.append(backStackRecord1);
                    stringBuilder0.append(" that were previously added to the FragmentManager through a separate FragmentTransaction.");
                    FragmentManager.this.throwException(new IllegalArgumentException(stringBuilder0.toString()));
                }
            }
            ArrayDeque arrayDeque0 = new ArrayDeque(hashSet0);
            while(!arrayDeque0.isEmpty()) {
                Fragment fragment1 = (Fragment)arrayDeque0.removeFirst();
                if(fragment1.mRetainInstance) {
                    FragmentManager.this.throwException(new IllegalArgumentException("saveBackStack(\"" + s + "\") must not contain retained fragments. Found " + (hashSet0.contains(fragment1) ? "direct reference to retained " : "retained child ") + "fragment " + fragment1));
                }
                for(Object object2: fragment1.mChildFragmentManager.getActiveFragments()) {
                    Fragment fragment2 = (Fragment)object2;
                    if(fragment2 != null) {
                        arrayDeque0.addLast(fragment2);
                    }
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for(Object object3: hashSet0) {
                arrayList2.add(((Fragment)object3).mWho);
            }
            ArrayList arrayList3 = new ArrayList(FragmentManager.this.mBackStack.size() - v);
            for(int v3 = v; v3 < FragmentManager.this.mBackStack.size(); ++v3) {
                arrayList3.add(null);
            }
            BackStackState backStackState0 = new BackStackState(arrayList2, arrayList3);
            for(int v4 = FragmentManager.this.mBackStack.size() - 1; v4 >= v; --v4) {
                BackStackRecord backStackRecord2 = (BackStackRecord)FragmentManager.this.mBackStack.remove(v4);
                BackStackRecord backStackRecord3 = new BackStackRecord(backStackRecord2);
                backStackRecord3.collapseOps();
                arrayList3.set(v4 - v, new BackStackRecordState(backStackRecord3));
                backStackRecord2.mBeingSaved = true;
                arrayList0.add(backStackRecord2);
                arrayList1.add(Boolean.TRUE);
            }
            FragmentManager.this.mBackStackStates.put(s, backStackState0);
            return true;
        }, false);
    }

    // 检测为 Lambda 实现
    boolean saveBackStackState(ArrayList arrayList0, ArrayList arrayList1, String s) [...]

    public SavedState saveFragmentInstanceState(Fragment fragment0) {
        FragmentStateManager fragmentStateManager0 = this.mFragmentStore.getFragmentStateManager(fragment0.mWho);
        if(fragmentStateManager0 == null || !fragmentStateManager0.getFragment().equals(fragment0)) {
            this.throwException(new IllegalStateException("Fragment " + fragment0 + " is not currently in the FragmentManager"));
        }
        return fragmentStateManager0.saveInstanceState();
    }

    void scheduleCommit() {
        synchronized(this.mPendingActions) {
            if(this.mPendingActions.size() == 1) {
                this.mHost.getHandler().removeCallbacks(this.mExecCommit);
                this.mHost.getHandler().post(this.mExecCommit);
                this.updateOnBackPressedCallbackEnabled();
            }
        }
    }

    void setExitAnimationOrder(Fragment fragment0, boolean z) {
        ViewGroup viewGroup0 = this.getFragmentContainer(fragment0);
        if(viewGroup0 != null && viewGroup0 instanceof FragmentContainerView) {
            ((FragmentContainerView)viewGroup0).setDrawDisappearingViewsLast(!z);
        }
    }

    public void setFragmentFactory(FragmentFactory fragmentFactory0) {
        this.mFragmentFactory = fragmentFactory0;
    }

    @Override  // androidx.fragment.app.FragmentResultOwner
    public final void setFragmentResult(String s, Bundle bundle0) {
        LifecycleAwareResultListener fragmentManager$LifecycleAwareResultListener0 = (LifecycleAwareResultListener)this.mResultListeners.get(s);
        if(fragmentManager$LifecycleAwareResultListener0 == null || !fragmentManager$LifecycleAwareResultListener0.isAtLeast(State.STARTED)) {
            this.mResults.put(s, bundle0);
        }
        else {
            fragmentManager$LifecycleAwareResultListener0.onFragmentResult(s, bundle0);
        }
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Setting fragment result with key " + s + " and result " + bundle0);
        }
    }

    @Override  // androidx.fragment.app.FragmentResultOwner
    public final void setFragmentResultListener(String s, LifecycleOwner lifecycleOwner0, FragmentResultListener fragmentResultListener0) {
        Lifecycle lifecycle0 = lifecycleOwner0.getLifecycle();
        if(lifecycle0.getCurrentState() == State.DESTROYED) {
            return;
        }
        androidx.fragment.app.FragmentManager.6 fragmentManager$60 = new LifecycleEventObserver() {
            @Override  // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
                if(lifecycle$Event0 == Event.ON_START) {
                    Bundle bundle0 = (Bundle)FragmentManager.this.mResults.get(s);
                    if(bundle0 != null) {
                        fragmentResultListener0.onFragmentResult(s, bundle0);
                        FragmentManager.this.clearFragmentResult(s);
                    }
                }
                if(lifecycle$Event0 == Event.ON_DESTROY) {
                    lifecycle0.removeObserver(this);
                    FragmentManager.this.mResultListeners.remove(s);
                }
            }
        };
        LifecycleAwareResultListener fragmentManager$LifecycleAwareResultListener0 = new LifecycleAwareResultListener(lifecycle0, fragmentResultListener0, fragmentManager$60);
        LifecycleAwareResultListener fragmentManager$LifecycleAwareResultListener1 = (LifecycleAwareResultListener)this.mResultListeners.put(s, fragmentManager$LifecycleAwareResultListener0);
        if(fragmentManager$LifecycleAwareResultListener1 != null) {
            fragmentManager$LifecycleAwareResultListener1.removeObserver();
        }
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Setting FragmentResultListener with key " + s + " lifecycleOwner " + lifecycle0 + " and listener " + fragmentResultListener0);
        }
        lifecycle0.addObserver(fragmentManager$60);
    }

    void setMaxLifecycle(Fragment fragment0, State lifecycle$State0) {
        if(!fragment0.equals(this.findActiveFragment(fragment0.mWho)) || fragment0.mHost != null && fragment0.mFragmentManager != this) {
            throw new IllegalArgumentException("Fragment " + fragment0 + " is not an active fragment of FragmentManager " + this);
        }
        fragment0.mMaxState = lifecycle$State0;
    }

    void setPrimaryNavigationFragment(Fragment fragment0) {
        if(fragment0 != null && (!fragment0.equals(this.findActiveFragment(fragment0.mWho)) || fragment0.mHost != null && fragment0.mFragmentManager != this)) {
            throw new IllegalArgumentException("Fragment " + fragment0 + " is not an active fragment of FragmentManager " + this);
        }
        Fragment fragment1 = this.mPrimaryNav;
        this.mPrimaryNav = fragment0;
        this.dispatchParentPrimaryNavigationFragmentChanged(fragment1);
        this.dispatchParentPrimaryNavigationFragmentChanged(this.mPrimaryNav);
    }

    void setSpecialEffectsControllerFactory(SpecialEffectsControllerFactory specialEffectsControllerFactory0) {
        this.mSpecialEffectsControllerFactory = specialEffectsControllerFactory0;
    }

    public void setStrictModePolicy(Policy fragmentStrictMode$Policy0) {
        this.mStrictModePolicy = fragmentStrictMode$Policy0;
    }

    private void setVisibleRemovingFragment(Fragment fragment0) {
        ViewGroup viewGroup0 = this.getFragmentContainer(fragment0);
        if(viewGroup0 != null && fragment0.getEnterAnim() + fragment0.getExitAnim() + fragment0.getPopEnterAnim() + fragment0.getPopExitAnim() > 0) {
            if(viewGroup0.getTag(id.visible_removing_fragment_view_tag) == null) {
                viewGroup0.setTag(id.visible_removing_fragment_view_tag, fragment0);
            }
            ((Fragment)viewGroup0.getTag(id.visible_removing_fragment_view_tag)).setPopDirection(fragment0.getPopDirection());
        }
    }

    void showFragment(Fragment fragment0) {
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "show: " + fragment0);
        }
        if(fragment0.mHidden) {
            fragment0.mHidden = false;
            fragment0.mHiddenChanged = !fragment0.mHiddenChanged;
        }
    }

    private void startPendingDeferredFragments() {
        for(Object object0: this.mFragmentStore.getActiveFragmentStateManagers()) {
            this.performPendingDeferredStart(((FragmentStateManager)object0));
        }
    }

    private void throwException(RuntimeException runtimeException0) {
        Log.e("FragmentManager", runtimeException0.getMessage());
        Log.e("FragmentManager", "Activity state:");
        PrintWriter printWriter0 = new PrintWriter(new LogWriter("FragmentManager"));
        FragmentHostCallback fragmentHostCallback0 = this.mHost;
        if(fragmentHostCallback0 == null) {
            try {
                this.dump("  ", null, printWriter0, new String[0]);
            }
            catch(Exception exception1) {
                Log.e("FragmentManager", "Failed dumping state", exception1);
            }
        }
        else {
            try {
                fragmentHostCallback0.onDump("  ", null, printWriter0, new String[0]);
            }
            catch(Exception exception0) {
                Log.e("FragmentManager", "Failed dumping state", exception0);
            }
        }
        throw runtimeException0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder(0x80);
        stringBuilder0.append("FragmentManager{");
        stringBuilder0.append(Integer.toHexString(System.identityHashCode(this)));
        stringBuilder0.append(" in ");
        Fragment fragment0 = this.mParent;
        if(fragment0 == null) {
            FragmentHostCallback fragmentHostCallback0 = this.mHost;
            if(fragmentHostCallback0 == null) {
                stringBuilder0.append("null");
            }
            else {
                stringBuilder0.append(fragmentHostCallback0.getClass().getSimpleName());
                stringBuilder0.append("{");
                stringBuilder0.append(Integer.toHexString(System.identityHashCode(this.mHost)));
                stringBuilder0.append("}");
            }
        }
        else {
            stringBuilder0.append(fragment0.getClass().getSimpleName());
            stringBuilder0.append("{");
            stringBuilder0.append(Integer.toHexString(System.identityHashCode(this.mParent)));
            stringBuilder0.append("}");
        }
        stringBuilder0.append("}}");
        return stringBuilder0.toString();
    }

    public void unregisterFragmentLifecycleCallbacks(FragmentLifecycleCallbacks fragmentManager$FragmentLifecycleCallbacks0) {
        this.mLifecycleCallbacksDispatcher.unregisterFragmentLifecycleCallbacks(fragmentManager$FragmentLifecycleCallbacks0);
    }

    private void updateOnBackPressedCallbackEnabled() {
        boolean z = true;
        synchronized(this.mPendingActions) {
            if(!this.mPendingActions.isEmpty()) {
                this.mOnBackPressedCallback.setEnabled(true);
                return;
            }
        }
        OnBackPressedCallback onBackPressedCallback0 = this.mOnBackPressedCallback;
        if(this.getBackStackEntryCount() <= 0 || !this.isPrimaryNavigation(this.mParent)) {
            z = false;
        }
        onBackPressedCallback0.setEnabled(z);
    }

    class androidx.fragment.app.FragmentManager.5 implements Runnable {
        @Override
        public void run() {
            FragmentManager.this.execPendingActions(true);
        }
    }

}

