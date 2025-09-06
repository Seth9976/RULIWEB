package androidx.fragment.app;

import android.animation.Animator;
import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View.OnCreateContextMenuListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.ActivityResultRegistryOwner;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.arch.core.util.Function;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.SharedElementCallback;
import androidx.core.view.LayoutInflaterCompat;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.Lifecycle.State;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandleSupport;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory;
import androidx.lifecycle.ViewModelProvider.Factory;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.MutableCreationExtras;
import androidx.loader.app.LoaderManager;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryController;
import androidx.savedstate.SavedStateRegistryOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Fragment implements ComponentCallbacks, View.OnCreateContextMenuListener, ActivityResultCaller, HasDefaultViewModelProviderFactory, LifecycleOwner, ViewModelStoreOwner, SavedStateRegistryOwner {
    static class AnimationInfo {
        Boolean mAllowEnterTransitionOverlap;
        Boolean mAllowReturnTransitionOverlap;
        View mAnimatingAway;
        int mEnterAnim;
        Object mEnterTransition;
        SharedElementCallback mEnterTransitionCallback;
        boolean mEnterTransitionPostponed;
        int mExitAnim;
        Object mExitTransition;
        SharedElementCallback mExitTransitionCallback;
        View mFocusedView;
        boolean mIsPop;
        int mNextTransition;
        int mPopEnterAnim;
        int mPopExitAnim;
        float mPostOnViewCreatedAlpha;
        Object mReenterTransition;
        Object mReturnTransition;
        Object mSharedElementEnterTransition;
        Object mSharedElementReturnTransition;
        ArrayList mSharedElementSourceNames;
        ArrayList mSharedElementTargetNames;

        AnimationInfo() {
            this.mEnterTransition = null;
            this.mReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
            this.mExitTransition = null;
            this.mReenterTransition = Fragment.USE_DEFAULT_TRANSITION;
            this.mSharedElementEnterTransition = null;
            this.mSharedElementReturnTransition = Fragment.USE_DEFAULT_TRANSITION;
            this.mEnterTransitionCallback = null;
            this.mExitTransitionCallback = null;
            this.mPostOnViewCreatedAlpha = 1.0f;
            this.mFocusedView = null;
        }
    }

    static class Api19Impl {
        static void cancelPendingInputEvents(View view0) {
            view0.cancelPendingInputEvents();
        }
    }

    public static class InstantiationException extends RuntimeException {
        public InstantiationException(String s, Exception exception0) {
            super(s, exception0);
        }
    }

    static abstract class OnPreAttachedListener {
        private OnPreAttachedListener() {
        }

        OnPreAttachedListener(androidx.fragment.app.Fragment.1 fragment$10) {
        }

        abstract void onPreAttached();
    }

    public static class SavedState implements Parcelable {
        public static final Parcelable.Creator CREATOR;
        final Bundle mState;

        static {
            SavedState.CREATOR = new Parcelable.ClassLoaderCreator() {
                public SavedState createFromParcel(Parcel parcel0) {
                    return new SavedState(parcel0, null);
                }

                public SavedState createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                    return new SavedState(parcel0, classLoader0);
                }

                @Override  // android.os.Parcelable$Creator
                public Object createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0);
                }

                @Override  // android.os.Parcelable$ClassLoaderCreator
                public Object createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                    return this.createFromParcel(parcel0, classLoader0);
                }

                public SavedState[] newArray(int v) {
                    return new SavedState[v];
                }

                @Override  // android.os.Parcelable$Creator
                public Object[] newArray(int v) {
                    return this.newArray(v);
                }
            };
        }

        SavedState(Bundle bundle0) {
            this.mState = bundle0;
        }

        SavedState(Parcel parcel0, ClassLoader classLoader0) {
            Bundle bundle0 = parcel0.readBundle();
            this.mState = bundle0;
            if(classLoader0 != null && bundle0 != null) {
                bundle0.setClassLoader(classLoader0);
            }
        }

        @Override  // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override  // android.os.Parcelable
        public void writeToParcel(Parcel parcel0, int v) {
            parcel0.writeBundle(this.mState);
        }
    }

    static final int ACTIVITY_CREATED = 4;
    static final int ATTACHED = 0;
    static final int AWAITING_ENTER_EFFECTS = 6;
    static final int AWAITING_EXIT_EFFECTS = 3;
    static final int CREATED = 1;
    static final int INITIALIZING = -1;
    static final int RESUMED = 7;
    static final int STARTED = 5;
    static final Object USE_DEFAULT_TRANSITION = null;
    static final int VIEW_CREATED = 2;
    boolean mAdded;
    AnimationInfo mAnimationInfo;
    Bundle mArguments;
    int mBackStackNesting;
    boolean mBeingSaved;
    private boolean mCalled;
    FragmentManager mChildFragmentManager;
    ViewGroup mContainer;
    int mContainerId;
    private int mContentLayoutId;
    Factory mDefaultFactory;
    boolean mDeferStart;
    boolean mDetached;
    int mFragmentId;
    FragmentManager mFragmentManager;
    boolean mFromLayout;
    boolean mHasMenu;
    boolean mHidden;
    boolean mHiddenChanged;
    FragmentHostCallback mHost;
    boolean mInLayout;
    boolean mIsCreated;
    private Boolean mIsPrimaryNavigationFragment;
    LayoutInflater mLayoutInflater;
    LifecycleRegistry mLifecycleRegistry;
    State mMaxState;
    boolean mMenuVisible;
    private final AtomicInteger mNextLocalRequestCode;
    private final ArrayList mOnPreAttachedListeners;
    Fragment mParentFragment;
    boolean mPerformedCreateView;
    Runnable mPostponedDurationRunnable;
    Handler mPostponedHandler;
    public String mPreviousWho;
    boolean mRemoving;
    boolean mRestored;
    boolean mRetainInstance;
    boolean mRetainInstanceChangedWhileDetached;
    Bundle mSavedFragmentState;
    private final OnPreAttachedListener mSavedStateAttachListener;
    SavedStateRegistryController mSavedStateRegistryController;
    Boolean mSavedUserVisibleHint;
    Bundle mSavedViewRegistryState;
    SparseArray mSavedViewState;
    int mState;
    String mTag;
    Fragment mTarget;
    int mTargetRequestCode;
    String mTargetWho;
    boolean mUserVisibleHint;
    View mView;
    FragmentViewLifecycleOwner mViewLifecycleOwner;
    MutableLiveData mViewLifecycleOwnerLiveData;
    String mWho;

    static {
        Fragment.USE_DEFAULT_TRANSITION = new Object();
    }

    public Fragment() {
        this.mState = -1;
        this.mWho = "3d5f512d-220a-4830-9a8b-ae23e9a340f7";
        this.mTargetWho = null;
        this.mIsPrimaryNavigationFragment = null;
        this.mChildFragmentManager = new FragmentManagerImpl();
        this.mMenuVisible = true;
        this.mUserVisibleHint = true;
        this.mPostponedDurationRunnable = () -> {
            if(Fragment.this.mAnimationInfo != null && Fragment.this.ensureAnimationInfo().mEnterTransitionPostponed) {
                if(Fragment.this.mHost == null) {
                    Fragment.this.ensureAnimationInfo().mEnterTransitionPostponed = false;
                    return;
                }
                if(Looper.myLooper() != Fragment.this.mHost.getHandler().getLooper()) {
                    Fragment.this.mHost.getHandler().postAtFrontOfQueue(() -> {
                        AnimationInfo fragment$AnimationInfo0 = Fragment.this.mAnimationInfo;
                        if(fragment$AnimationInfo0 != null) {
                            fragment$AnimationInfo0.mEnterTransitionPostponed = false;
                        }
                        if(Fragment.this.mView != null) {
                            ViewGroup viewGroup0 = Fragment.this.mContainer;
                            if(viewGroup0 != null) {
                                FragmentManager fragmentManager0 = Fragment.this.mFragmentManager;
                                if(fragmentManager0 != null) {
                                    SpecialEffectsController specialEffectsController0 = SpecialEffectsController.getOrCreateController(viewGroup0, fragmentManager0);
                                    specialEffectsController0.markPostponedState();
                                    if(false) {
                                        Fragment.this.mHost.getHandler().post(new Runnable() {
                                            @Override
                                            public void run() {
                                                specialEffectsController0.executePendingOperations();
                                            }
                                        });
                                    }
                                    else {
                                        specialEffectsController0.executePendingOperations();
                                    }
                                    Handler handler0 = Fragment.this.mPostponedHandler;
                                    if(handler0 != null) {
                                        handler0.removeCallbacks(Fragment.this.mPostponedDurationRunnable);
                                        Fragment.this.mPostponedHandler = null;
                                    }
                                }
                            }
                        }
                    });
                    return;
                }
                Fragment.this.callStartTransitionListener(true);
            }

            class androidx.fragment.app.Fragment.3 implements Runnable {
                @Override
                public void run() {
                    Fragment.this.callStartTransitionListener(false);
                }
            }

        };
        this.mMaxState = State.RESUMED;
        this.mViewLifecycleOwnerLiveData = new MutableLiveData();
        this.mNextLocalRequestCode = new AtomicInteger();
        this.mOnPreAttachedListeners = new ArrayList();
        this.mSavedStateAttachListener = new OnPreAttachedListener() {
            {
                Fragment.this = fragment0;
                super(null);
            }

            @Override  // androidx.fragment.app.Fragment$OnPreAttachedListener
            void onPreAttached() {
                Fragment.this.mSavedStateRegistryController.performAttach();
                SavedStateHandleSupport.enableSavedStateHandles(Fragment.this);
            }
        };
        this.initLifecycle();
    }

    public Fragment(int v) {
        this.mContentLayoutId = v;
    }

    // 检测为 Lambda 实现
    void callStartTransitionListener(boolean z) [...]

    FragmentContainer createFragmentContainer() {
        return new FragmentContainer() {
            @Override  // androidx.fragment.app.FragmentContainer
            public View onFindViewById(int v) {
                if(Fragment.this.mView == null) {
                    throw new IllegalStateException("Fragment " + Fragment.this + " does not have a view");
                }
                return Fragment.this.mView.findViewById(v);
            }

            @Override  // androidx.fragment.app.FragmentContainer
            public boolean onHasView() {
                return Fragment.this.mView != null;
            }
        };
    }

    public void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
        printWriter0.print(s);
        printWriter0.print("mFragmentId=#");
        printWriter0.print(Integer.toHexString(this.mFragmentId));
        printWriter0.print(" mContainerId=#");
        printWriter0.print(Integer.toHexString(this.mContainerId));
        printWriter0.print(" mTag=");
        printWriter0.println(this.mTag);
        printWriter0.print(s);
        printWriter0.print("mState=");
        printWriter0.print(this.mState);
        printWriter0.print(" mWho=");
        printWriter0.print(this.mWho);
        printWriter0.print(" mBackStackNesting=");
        printWriter0.println(this.mBackStackNesting);
        printWriter0.print(s);
        printWriter0.print("mAdded=");
        printWriter0.print(this.mAdded);
        printWriter0.print(" mRemoving=");
        printWriter0.print(this.mRemoving);
        printWriter0.print(" mFromLayout=");
        printWriter0.print(this.mFromLayout);
        printWriter0.print(" mInLayout=");
        printWriter0.println(this.mInLayout);
        printWriter0.print(s);
        printWriter0.print("mHidden=");
        printWriter0.print(this.mHidden);
        printWriter0.print(" mDetached=");
        printWriter0.print(this.mDetached);
        printWriter0.print(" mMenuVisible=");
        printWriter0.print(this.mMenuVisible);
        printWriter0.print(" mHasMenu=");
        printWriter0.println(this.mHasMenu);
        printWriter0.print(s);
        printWriter0.print("mRetainInstance=");
        printWriter0.print(this.mRetainInstance);
        printWriter0.print(" mUserVisibleHint=");
        printWriter0.println(this.mUserVisibleHint);
        if(this.mFragmentManager != null) {
            printWriter0.print(s);
            printWriter0.print("mFragmentManager=");
            printWriter0.println(this.mFragmentManager);
        }
        if(this.mHost != null) {
            printWriter0.print(s);
            printWriter0.print("mHost=");
            printWriter0.println(this.mHost);
        }
        if(this.mParentFragment != null) {
            printWriter0.print(s);
            printWriter0.print("mParentFragment=");
            printWriter0.println(this.mParentFragment);
        }
        if(this.mArguments != null) {
            printWriter0.print(s);
            printWriter0.print("mArguments=");
            printWriter0.println(this.mArguments);
        }
        if(this.mSavedFragmentState != null) {
            printWriter0.print(s);
            printWriter0.print("mSavedFragmentState=");
            printWriter0.println(this.mSavedFragmentState);
        }
        if(this.mSavedViewState != null) {
            printWriter0.print(s);
            printWriter0.print("mSavedViewState=");
            printWriter0.println(this.mSavedViewState);
        }
        if(this.mSavedViewRegistryState != null) {
            printWriter0.print(s);
            printWriter0.print("mSavedViewRegistryState=");
            printWriter0.println(this.mSavedViewRegistryState);
        }
        Fragment fragment0 = this.getTargetFragment(false);
        if(fragment0 != null) {
            printWriter0.print(s);
            printWriter0.print("mTarget=");
            printWriter0.print(fragment0);
            printWriter0.print(" mTargetRequestCode=");
            printWriter0.println(this.mTargetRequestCode);
        }
        printWriter0.print(s);
        printWriter0.print("mPopDirection=");
        printWriter0.println(this.getPopDirection());
        if(this.getEnterAnim() != 0) {
            printWriter0.print(s);
            printWriter0.print("getEnterAnim=");
            printWriter0.println(this.getEnterAnim());
        }
        if(this.getExitAnim() != 0) {
            printWriter0.print(s);
            printWriter0.print("getExitAnim=");
            printWriter0.println(this.getExitAnim());
        }
        if(this.getPopEnterAnim() != 0) {
            printWriter0.print(s);
            printWriter0.print("getPopEnterAnim=");
            printWriter0.println(this.getPopEnterAnim());
        }
        if(this.getPopExitAnim() != 0) {
            printWriter0.print(s);
            printWriter0.print("getPopExitAnim=");
            printWriter0.println(this.getPopExitAnim());
        }
        if(this.mContainer != null) {
            printWriter0.print(s);
            printWriter0.print("mContainer=");
            printWriter0.println(this.mContainer);
        }
        if(this.mView != null) {
            printWriter0.print(s);
            printWriter0.print("mView=");
            printWriter0.println(this.mView);
        }
        if(this.getAnimatingAway() != null) {
            printWriter0.print(s);
            printWriter0.print("mAnimatingAway=");
            printWriter0.println(this.getAnimatingAway());
        }
        if(this.getContext() != null) {
            LoaderManager.getInstance(this).dump(s, fileDescriptor0, printWriter0, arr_s);
        }
        printWriter0.print(s);
        printWriter0.println("Child " + this.mChildFragmentManager + ":");
        this.mChildFragmentManager.dump(s + "  ", fileDescriptor0, printWriter0, arr_s);
    }

    private AnimationInfo ensureAnimationInfo() {
        if(this.mAnimationInfo == null) {
            this.mAnimationInfo = new AnimationInfo();
        }
        return this.mAnimationInfo;
    }

    @Override
    public final boolean equals(Object object0) {
        return super.equals(object0);
    }

    // 去混淆评级： 低(20)
    Fragment findFragmentByWho(String s) {
        return s.equals(this.mWho) ? this : this.mChildFragmentManager.findFragmentByWho(s);
    }

    String generateActivityResultKey() [...] // 潜在的解密器

    public final FragmentActivity getActivity() {
        return this.mHost == null ? null : ((FragmentActivity)this.mHost.getActivity());
    }

    public boolean getAllowEnterTransitionOverlap() {
        return this.mAnimationInfo == null || this.mAnimationInfo.mAllowEnterTransitionOverlap == null ? true : this.mAnimationInfo.mAllowEnterTransitionOverlap.booleanValue();
    }

    public boolean getAllowReturnTransitionOverlap() {
        return this.mAnimationInfo == null || this.mAnimationInfo.mAllowReturnTransitionOverlap == null ? true : this.mAnimationInfo.mAllowReturnTransitionOverlap.booleanValue();
    }

    View getAnimatingAway() {
        return this.mAnimationInfo == null ? null : this.mAnimationInfo.mAnimatingAway;
    }

    public final Bundle getArguments() {
        return this.mArguments;
    }

    public final FragmentManager getChildFragmentManager() {
        if(this.mHost == null) {
            throw new IllegalStateException("Fragment " + this + " has not been attached yet.");
        }
        return this.mChildFragmentManager;
    }

    public Context getContext() {
        return this.mHost == null ? null : this.mHost.getContext();
    }

    @Override  // androidx.lifecycle.HasDefaultViewModelProviderFactory
    public CreationExtras getDefaultViewModelCreationExtras() {
        Application application0;
        for(Context context0 = this.requireContext().getApplicationContext(); true; context0 = ((ContextWrapper)context0).getBaseContext()) {
            application0 = null;
            if(!(context0 instanceof ContextWrapper)) {
                break;
            }
            if(context0 instanceof Application) {
                application0 = (Application)context0;
                break;
            }
        }
        if(application0 == null && FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "Could not find Application instance from Context " + this.requireContext().getApplicationContext() + ", you will not be able to use AndroidViewModel with the default ViewModelProvider.Factory");
        }
        CreationExtras creationExtras0 = new MutableCreationExtras();
        if(application0 != null) {
            ((MutableCreationExtras)creationExtras0).set(AndroidViewModelFactory.APPLICATION_KEY, application0);
        }
        ((MutableCreationExtras)creationExtras0).set(SavedStateHandleSupport.SAVED_STATE_REGISTRY_OWNER_KEY, this);
        ((MutableCreationExtras)creationExtras0).set(SavedStateHandleSupport.VIEW_MODEL_STORE_OWNER_KEY, this);
        if(this.getArguments() != null) {
            ((MutableCreationExtras)creationExtras0).set(SavedStateHandleSupport.DEFAULT_ARGS_KEY, this.getArguments());
        }
        return creationExtras0;
    }

    @Override  // androidx.lifecycle.HasDefaultViewModelProviderFactory
    public Factory getDefaultViewModelProviderFactory() {
        Application application0;
        if(this.mFragmentManager == null) {
            throw new IllegalStateException("Can\'t access ViewModels from detached fragment");
        }
        if(this.mDefaultFactory == null) {
            for(Context context0 = this.requireContext().getApplicationContext(); true; context0 = ((ContextWrapper)context0).getBaseContext()) {
                application0 = null;
                if(!(context0 instanceof ContextWrapper)) {
                    break;
                }
                if(context0 instanceof Application) {
                    application0 = (Application)context0;
                    break;
                }
            }
            if(application0 == null && FragmentManager.isLoggingEnabled(3)) {
                Log.d("FragmentManager", "Could not find Application instance from Context " + this.requireContext().getApplicationContext() + ", you will need CreationExtras to use AndroidViewModel with the default ViewModelProvider.Factory");
            }
            this.mDefaultFactory = new SavedStateViewModelFactory(application0, this, this.getArguments());
        }
        return this.mDefaultFactory;
    }

    int getEnterAnim() {
        return this.mAnimationInfo == null ? 0 : this.mAnimationInfo.mEnterAnim;
    }

    public Object getEnterTransition() {
        return this.mAnimationInfo == null ? null : this.mAnimationInfo.mEnterTransition;
    }

    SharedElementCallback getEnterTransitionCallback() {
        return this.mAnimationInfo == null ? null : this.mAnimationInfo.mEnterTransitionCallback;
    }

    int getExitAnim() {
        return this.mAnimationInfo == null ? 0 : this.mAnimationInfo.mExitAnim;
    }

    public Object getExitTransition() {
        return this.mAnimationInfo == null ? null : this.mAnimationInfo.mExitTransition;
    }

    SharedElementCallback getExitTransitionCallback() {
        return this.mAnimationInfo == null ? null : this.mAnimationInfo.mExitTransitionCallback;
    }

    View getFocusedView() {
        return this.mAnimationInfo == null ? null : this.mAnimationInfo.mFocusedView;
    }

    @Deprecated
    public final FragmentManager getFragmentManager() {
        return this.mFragmentManager;
    }

    public final Object getHost() {
        return this.mHost == null ? null : this.mHost.onGetHost();
    }

    public final int getId() {
        return this.mFragmentId;
    }

    public final LayoutInflater getLayoutInflater() {
        return this.mLayoutInflater == null ? this.performGetLayoutInflater(null) : this.mLayoutInflater;
    }

    @Deprecated
    public LayoutInflater getLayoutInflater(Bundle bundle0) {
        FragmentHostCallback fragmentHostCallback0 = this.mHost;
        if(fragmentHostCallback0 == null) {
            throw new IllegalStateException("onGetLayoutInflater() cannot be executed until the Fragment is attached to the FragmentManager.");
        }
        LayoutInflater layoutInflater0 = fragmentHostCallback0.onGetLayoutInflater();
        LayoutInflaterCompat.setFactory2(layoutInflater0, this.mChildFragmentManager.getLayoutInflaterFactory());
        return layoutInflater0;
    }

    @Override  // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.mLifecycleRegistry;
    }

    @Deprecated
    public LoaderManager getLoaderManager() {
        return LoaderManager.getInstance(this);
    }

    private int getMinimumMaxLifecycleState() {
        return this.mMaxState == State.INITIALIZED || this.mParentFragment == null ? this.mMaxState.ordinal() : Math.min(this.mMaxState.ordinal(), this.mParentFragment.getMinimumMaxLifecycleState());
    }

    int getNextTransition() {
        return this.mAnimationInfo == null ? 0 : this.mAnimationInfo.mNextTransition;
    }

    public final Fragment getParentFragment() {
        return this.mParentFragment;
    }

    public final FragmentManager getParentFragmentManager() {
        FragmentManager fragmentManager0 = this.mFragmentManager;
        if(fragmentManager0 == null) {
            throw new IllegalStateException("Fragment " + this + " not associated with a fragment manager.");
        }
        return fragmentManager0;
    }

    boolean getPopDirection() {
        return this.mAnimationInfo == null ? false : this.mAnimationInfo.mIsPop;
    }

    int getPopEnterAnim() {
        return this.mAnimationInfo == null ? 0 : this.mAnimationInfo.mPopEnterAnim;
    }

    int getPopExitAnim() {
        return this.mAnimationInfo == null ? 0 : this.mAnimationInfo.mPopExitAnim;
    }

    float getPostOnViewCreatedAlpha() {
        return this.mAnimationInfo == null ? 1.0f : this.mAnimationInfo.mPostOnViewCreatedAlpha;
    }

    public Object getReenterTransition() {
        AnimationInfo fragment$AnimationInfo0 = this.mAnimationInfo;
        if(fragment$AnimationInfo0 == null) {
            return null;
        }
        return fragment$AnimationInfo0.mReenterTransition == Fragment.USE_DEFAULT_TRANSITION ? this.getExitTransition() : this.mAnimationInfo.mReenterTransition;
    }

    public final Resources getResources() {
        return this.requireContext().getResources();
    }

    @Deprecated
    public final boolean getRetainInstance() {
        FragmentStrictMode.onGetRetainInstanceUsage(this);
        return this.mRetainInstance;
    }

    public Object getReturnTransition() {
        AnimationInfo fragment$AnimationInfo0 = this.mAnimationInfo;
        if(fragment$AnimationInfo0 == null) {
            return null;
        }
        return fragment$AnimationInfo0.mReturnTransition == Fragment.USE_DEFAULT_TRANSITION ? this.getEnterTransition() : this.mAnimationInfo.mReturnTransition;
    }

    @Override  // androidx.savedstate.SavedStateRegistryOwner
    public final SavedStateRegistry getSavedStateRegistry() {
        return this.mSavedStateRegistryController.getSavedStateRegistry();
    }

    public Object getSharedElementEnterTransition() {
        return this.mAnimationInfo == null ? null : this.mAnimationInfo.mSharedElementEnterTransition;
    }

    public Object getSharedElementReturnTransition() {
        AnimationInfo fragment$AnimationInfo0 = this.mAnimationInfo;
        if(fragment$AnimationInfo0 == null) {
            return null;
        }
        return fragment$AnimationInfo0.mSharedElementReturnTransition == Fragment.USE_DEFAULT_TRANSITION ? this.getSharedElementEnterTransition() : this.mAnimationInfo.mSharedElementReturnTransition;
    }

    ArrayList getSharedElementSourceNames() {
        return this.mAnimationInfo == null || this.mAnimationInfo.mSharedElementSourceNames == null ? new ArrayList() : this.mAnimationInfo.mSharedElementSourceNames;
    }

    ArrayList getSharedElementTargetNames() {
        return this.mAnimationInfo == null || this.mAnimationInfo.mSharedElementTargetNames == null ? new ArrayList() : this.mAnimationInfo.mSharedElementTargetNames;
    }

    public final String getString(int v) {
        return this.getResources().getString(v);
    }

    public final String getString(int v, Object[] arr_object) {
        return this.getResources().getString(v, arr_object);
    }

    public final String getTag() {
        return this.mTag;
    }

    private Fragment getTargetFragment(boolean z) {
        if(z) {
            FragmentStrictMode.onGetTargetFragmentUsage(this);
        }
        Fragment fragment0 = this.mTarget;
        if(fragment0 != null) {
            return fragment0;
        }
        FragmentManager fragmentManager0 = this.mFragmentManager;
        if(fragmentManager0 != null) {
            return this.mTargetWho == null ? null : fragmentManager0.findActiveFragment(this.mTargetWho);
        }
        return null;
    }

    @Deprecated
    public final Fragment getTargetFragment() {
        return this.getTargetFragment(true);
    }

    @Deprecated
    public final int getTargetRequestCode() {
        FragmentStrictMode.onGetTargetFragmentRequestCodeUsage(this);
        return this.mTargetRequestCode;
    }

    public final CharSequence getText(int v) {
        return this.getResources().getText(v);
    }

    @Deprecated
    public boolean getUserVisibleHint() {
        return this.mUserVisibleHint;
    }

    public View getView() {
        return this.mView;
    }

    public LifecycleOwner getViewLifecycleOwner() {
        LifecycleOwner lifecycleOwner0 = this.mViewLifecycleOwner;
        if(lifecycleOwner0 == null) {
            throw new IllegalStateException("Can\'t access the Fragment View\'s LifecycleOwner when getView() is null i.e., before onCreateView() or after onDestroyView()");
        }
        return lifecycleOwner0;
    }

    public LiveData getViewLifecycleOwnerLiveData() {
        return this.mViewLifecycleOwnerLiveData;
    }

    @Override  // androidx.lifecycle.ViewModelStoreOwner
    public ViewModelStore getViewModelStore() {
        if(this.mFragmentManager == null) {
            throw new IllegalStateException("Can\'t access ViewModels from detached fragment");
        }
        if(this.getMinimumMaxLifecycleState() == State.INITIALIZED.ordinal()) {
            throw new IllegalStateException("Calling getViewModelStore() before a Fragment reaches onCreate() when using setMaxLifecycle(INITIALIZED) is not supported");
        }
        return this.mFragmentManager.getViewModelStore(this);
    }

    public final boolean hasOptionsMenu() {
        return this.mHasMenu;
    }

    @Override
    public final int hashCode() {
        return super.hashCode();
    }

    private void initLifecycle() {
        this.mLifecycleRegistry = new LifecycleRegistry(this);
        this.mSavedStateRegistryController = SavedStateRegistryController.create(this);
        this.mDefaultFactory = null;
        if(!this.mOnPreAttachedListeners.contains(this.mSavedStateAttachListener)) {
            this.registerOnPreAttachListener(this.mSavedStateAttachListener);
        }
    }

    void initState() {
        this.initLifecycle();
        this.mPreviousWho = this.mWho;
        this.mWho = "d50cdc6f-f003-4350-9fcb-26c84309e44e";
        this.mAdded = false;
        this.mRemoving = false;
        this.mFromLayout = false;
        this.mInLayout = false;
        this.mRestored = false;
        this.mBackStackNesting = 0;
        this.mFragmentManager = null;
        this.mChildFragmentManager = new FragmentManagerImpl();
        this.mHost = null;
        this.mFragmentId = 0;
        this.mContainerId = 0;
        this.mTag = null;
        this.mHidden = false;
        this.mDetached = false;
    }

    @Deprecated
    public static Fragment instantiate(Context context0, String s) {
        return Fragment.instantiate(context0, s, null);
    }

    @Deprecated
    public static Fragment instantiate(Context context0, String s, Bundle bundle0) {
        try {
            Fragment fragment0 = (Fragment)FragmentFactory.loadFragmentClass(context0.getClassLoader(), s).getConstructor(null).newInstance(null);
            if(bundle0 != null) {
                bundle0.setClassLoader(fragment0.getClass().getClassLoader());
                fragment0.setArguments(bundle0);
            }
            return fragment0;
        }
        catch(java.lang.InstantiationException instantiationException0) {
            throw new InstantiationException("Unable to instantiate fragment " + s + ": make sure class name exists, is public, and has an empty constructor that is public", instantiationException0);
        }
        catch(IllegalAccessException illegalAccessException0) {
            throw new InstantiationException("Unable to instantiate fragment " + s + ": make sure class name exists, is public, and has an empty constructor that is public", illegalAccessException0);
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            throw new InstantiationException("Unable to instantiate fragment " + s + ": could not find Fragment constructor", noSuchMethodException0);
        }
        catch(InvocationTargetException invocationTargetException0) {
            throw new InstantiationException("Unable to instantiate fragment " + s + ": calling Fragment constructor caused an exception", invocationTargetException0);
        }
    }

    public final boolean isAdded() {
        return this.mHost != null && this.mAdded;
    }

    public final boolean isDetached() {
        return this.mDetached;
    }

    // 去混淆评级： 低(30)
    public final boolean isHidden() {
        return this.mHidden || this.mFragmentManager != null && this.mFragmentManager.isParentHidden(this.mParentFragment);
    }

    final boolean isInBackStack() {
        return this.mBackStackNesting > 0;
    }

    public final boolean isInLayout() {
        return this.mInLayout;
    }

    // 去混淆评级： 低(30)
    public final boolean isMenuVisible() {
        return this.mMenuVisible && (this.mFragmentManager == null || this.mFragmentManager.isParentMenuVisible(this.mParentFragment));
    }

    boolean isPostponed() {
        return this.mAnimationInfo == null ? false : this.mAnimationInfo.mEnterTransitionPostponed;
    }

    public final boolean isRemoving() {
        return this.mRemoving;
    }

    public final boolean isResumed() {
        return this.mState >= 7;
    }

    public final boolean isStateSaved() {
        return this.mFragmentManager == null ? false : this.mFragmentManager.isStateSaved();
    }

    // 去混淆评级： 低(20)
    public final boolean isVisible() {
        return this.isAdded() && !this.isHidden() && (this.mView != null && this.mView.getWindowToken() != null && this.mView.getVisibility() == 0);
    }

    void noteStateNotSaved() {
        this.mChildFragmentManager.noteStateNotSaved();
    }

    @Deprecated
    public void onActivityCreated(Bundle bundle0) {
        this.mCalled = true;
    }

    @Deprecated
    public void onActivityResult(int v, int v1, Intent intent0) {
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Fragment " + this + " received the following in onActivityResult(): requestCode: " + v + " resultCode: " + v1 + " data: " + intent0);
        }
    }

    @Deprecated
    public void onAttach(Activity activity0) {
        this.mCalled = true;
    }

    public void onAttach(Context context0) {
        this.mCalled = true;
        Activity activity0 = this.mHost == null ? null : this.mHost.getActivity();
        if(activity0 != null) {
            this.mCalled = false;
            this.onAttach(activity0);
        }
    }

    @Deprecated
    public void onAttachFragment(Fragment fragment0) {
    }

    @Override  // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration0) {
        this.mCalled = true;
    }

    public boolean onContextItemSelected(MenuItem menuItem0) [...] // Inlined contents

    public void onCreate(Bundle bundle0) {
        this.mCalled = true;
        this.restoreChildFragmentState(bundle0);
        if(!this.mChildFragmentManager.isStateAtLeast(1)) {
            this.mChildFragmentManager.dispatchCreate();
        }
    }

    public Animation onCreateAnimation(int v, boolean z, int v1) [...] // Inlined contents

    public Animator onCreateAnimator(int v, boolean z, int v1) [...] // Inlined contents

    @Override  // android.view.View$OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu0, View view0, ContextMenu.ContextMenuInfo contextMenu$ContextMenuInfo0) {
        this.requireActivity().onCreateContextMenu(contextMenu0, view0, contextMenu$ContextMenuInfo0);
    }

    @Deprecated
    public void onCreateOptionsMenu(Menu menu0, MenuInflater menuInflater0) {
    }

    public View onCreateView(LayoutInflater layoutInflater0, ViewGroup viewGroup0, Bundle bundle0) {
        return this.mContentLayoutId == 0 ? null : layoutInflater0.inflate(this.mContentLayoutId, viewGroup0, false);
    }

    public void onDestroy() {
        this.mCalled = true;
    }

    @Deprecated
    public void onDestroyOptionsMenu() {
    }

    public void onDestroyView() {
        this.mCalled = true;
    }

    public void onDetach() {
        this.mCalled = true;
    }

    public LayoutInflater onGetLayoutInflater(Bundle bundle0) {
        return this.getLayoutInflater(bundle0);
    }

    public void onHiddenChanged(boolean z) {
    }

    @Deprecated
    public void onInflate(Activity activity0, AttributeSet attributeSet0, Bundle bundle0) {
        this.mCalled = true;
    }

    public void onInflate(Context context0, AttributeSet attributeSet0, Bundle bundle0) {
        this.mCalled = true;
        Activity activity0 = this.mHost == null ? null : this.mHost.getActivity();
        if(activity0 != null) {
            this.mCalled = false;
            this.onInflate(activity0, attributeSet0, bundle0);
        }
    }

    @Override  // android.content.ComponentCallbacks
    public void onLowMemory() {
        this.mCalled = true;
    }

    public void onMultiWindowModeChanged(boolean z) {
    }

    @Deprecated
    public boolean onOptionsItemSelected(MenuItem menuItem0) [...] // Inlined contents

    @Deprecated
    public void onOptionsMenuClosed(Menu menu0) {
    }

    public void onPause() {
        this.mCalled = true;
    }

    public void onPictureInPictureModeChanged(boolean z) {
    }

    @Deprecated
    public void onPrepareOptionsMenu(Menu menu0) {
    }

    public void onPrimaryNavigationFragmentChanged(boolean z) {
    }

    @Deprecated
    public void onRequestPermissionsResult(int v, String[] arr_s, int[] arr_v) {
    }

    public void onResume() {
        this.mCalled = true;
    }

    public void onSaveInstanceState(Bundle bundle0) {
    }

    public void onStart() {
        this.mCalled = true;
    }

    public void onStop() {
        this.mCalled = true;
    }

    public void onViewCreated(View view0, Bundle bundle0) {
    }

    public void onViewStateRestored(Bundle bundle0) {
        this.mCalled = true;
    }

    void performActivityCreated(Bundle bundle0) {
        this.mChildFragmentManager.noteStateNotSaved();
        this.mState = 3;
        this.mCalled = false;
        this.onActivityCreated(bundle0);
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onActivityCreated()");
    }

    void performAttach() {
        for(Object object0: this.mOnPreAttachedListeners) {
            ((OnPreAttachedListener)object0).onPreAttached();
        }
        this.mOnPreAttachedListeners.clear();
        this.mChildFragmentManager.attachController(this.mHost, this.createFragmentContainer(), this);
        this.mState = 0;
        this.mCalled = false;
        this.onAttach(this.mHost.getContext());
        if(!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onAttach()");
        }
        this.mFragmentManager.dispatchOnAttachFragment(this);
        this.mChildFragmentManager.dispatchAttach();
    }

    void performConfigurationChanged(Configuration configuration0) {
        this.onConfigurationChanged(configuration0);
    }

    boolean performContextItemSelected(MenuItem menuItem0) {
        return this.mHidden ? false : this.mChildFragmentManager.dispatchContextItemSelected(menuItem0);
    }

    void performCreate(Bundle bundle0) {
        this.mChildFragmentManager.noteStateNotSaved();
        this.mState = 1;
        this.mCalled = false;
        this.mLifecycleRegistry.addObserver(new LifecycleEventObserver() {
            @Override  // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
                if(lifecycle$Event0 == Event.ON_STOP && Fragment.this.mView != null) {
                    Api19Impl.cancelPendingInputEvents(Fragment.this.mView);
                }
            }
        });
        this.mSavedStateRegistryController.performRestore(bundle0);
        this.onCreate(bundle0);
        this.mIsCreated = true;
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onCreate()");
    }

    boolean performCreateOptionsMenu(Menu menu0, MenuInflater menuInflater0) {
        boolean z = false;
        if(!this.mHidden) {
            if(this.mHasMenu && this.mMenuVisible) {
                z = true;
            }
            return this.mChildFragmentManager.dispatchCreateOptionsMenu(menu0, menuInflater0) | z;
        }
        return false;
    }

    void performCreateView(LayoutInflater layoutInflater0, ViewGroup viewGroup0, Bundle bundle0) {
        this.mChildFragmentManager.noteStateNotSaved();
        this.mPerformedCreateView = true;
        this.mViewLifecycleOwner = new FragmentViewLifecycleOwner(this, this.getViewModelStore());
        View view0 = this.onCreateView(layoutInflater0, viewGroup0, bundle0);
        this.mView = view0;
        if(view0 != null) {
            this.mViewLifecycleOwner.initialize();
            ViewTreeLifecycleOwner.set(this.mView, this.mViewLifecycleOwner);
            ViewTreeViewModelStoreOwner.set(this.mView, this.mViewLifecycleOwner);
            ViewTreeSavedStateRegistryOwner.set(this.mView, this.mViewLifecycleOwner);
            this.mViewLifecycleOwnerLiveData.setValue(this.mViewLifecycleOwner);
            return;
        }
        if(this.mViewLifecycleOwner.isInitialized()) {
            throw new IllegalStateException("Called getViewLifecycleOwner() but onCreateView() returned null");
        }
        this.mViewLifecycleOwner = null;
    }

    void performDestroy() {
        this.mChildFragmentManager.dispatchDestroy();
        this.mLifecycleRegistry.handleLifecycleEvent(Event.ON_DESTROY);
        this.mState = 0;
        this.mCalled = false;
        this.mIsCreated = false;
        this.onDestroy();
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroy()");
    }

    void performDestroyView() {
        this.mChildFragmentManager.dispatchDestroyView();
        if(this.mView != null && this.mViewLifecycleOwner.getLifecycle().getCurrentState().isAtLeast(State.CREATED)) {
            this.mViewLifecycleOwner.handleLifecycleEvent(Event.ON_DESTROY);
        }
        this.mState = 1;
        this.mCalled = false;
        this.onDestroyView();
        if(!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDestroyView()");
        }
        LoaderManager.getInstance(this).markForRedelivery();
        this.mPerformedCreateView = false;
    }

    void performDetach() {
        this.mState = -1;
        this.mCalled = false;
        this.onDetach();
        this.mLayoutInflater = null;
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onDetach()");
    }

    LayoutInflater performGetLayoutInflater(Bundle bundle0) {
        LayoutInflater layoutInflater0 = this.onGetLayoutInflater(bundle0);
        this.mLayoutInflater = layoutInflater0;
        return layoutInflater0;
    }

    void performLowMemory() {
        this.onLowMemory();
    }

    void performMultiWindowModeChanged(boolean z) {
    }

    boolean performOptionsItemSelected(MenuItem menuItem0) {
        return this.mHidden ? false : this.mChildFragmentManager.dispatchOptionsItemSelected(menuItem0);
    }

    void performOptionsMenuClosed(Menu menu0) {
        if(!this.mHidden) {
            this.mChildFragmentManager.dispatchOptionsMenuClosed(menu0);
        }
    }

    void performPause() {
        this.mChildFragmentManager.dispatchPause();
        if(this.mView != null) {
            this.mViewLifecycleOwner.handleLifecycleEvent(Event.ON_PAUSE);
        }
        this.mLifecycleRegistry.handleLifecycleEvent(Event.ON_PAUSE);
        this.mState = 6;
        this.mCalled = false;
        this.onPause();
        if(!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onPause()");
        }
    }

    void performPictureInPictureModeChanged(boolean z) {
    }

    boolean performPrepareOptionsMenu(Menu menu0) {
        boolean z = false;
        if(!this.mHidden) {
            if(this.mHasMenu && this.mMenuVisible) {
                z = true;
            }
            return this.mChildFragmentManager.dispatchPrepareOptionsMenu(menu0) | z;
        }
        return false;
    }

    void performPrimaryNavigationFragmentChanged() {
        boolean z = this.mFragmentManager.isPrimaryNavigation(this);
        if(this.mIsPrimaryNavigationFragment != null && this.mIsPrimaryNavigationFragment.booleanValue() == z) {
            return;
        }
        this.mIsPrimaryNavigationFragment = Boolean.valueOf(z);
        this.mChildFragmentManager.dispatchPrimaryNavigationFragmentChanged();
    }

    void performResume() {
        this.mChildFragmentManager.noteStateNotSaved();
        this.mChildFragmentManager.execPendingActions(true);
        this.mState = 7;
        this.mCalled = false;
        this.onResume();
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onResume()");
    }

    void performSaveInstanceState(Bundle bundle0) {
        this.onSaveInstanceState(bundle0);
        this.mSavedStateRegistryController.performSave(bundle0);
        Bundle bundle1 = this.mChildFragmentManager.saveAllStateInternal();
        if(bundle1 != null) {
            bundle0.putParcelable("android:support:fragments", bundle1);
        }
    }

    void performStart() {
        this.mChildFragmentManager.noteStateNotSaved();
        this.mChildFragmentManager.execPendingActions(true);
        this.mState = 5;
        this.mCalled = false;
        this.onStart();
        throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStart()");
    }

    void performStop() {
        this.mChildFragmentManager.dispatchStop();
        if(this.mView != null) {
            this.mViewLifecycleOwner.handleLifecycleEvent(Event.ON_STOP);
        }
        this.mLifecycleRegistry.handleLifecycleEvent(Event.ON_STOP);
        this.mState = 4;
        this.mCalled = false;
        this.onStop();
        if(!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onStop()");
        }
    }

    void performViewCreated() {
        this.onViewCreated(this.mView, this.mSavedFragmentState);
        this.mChildFragmentManager.dispatchViewCreated();
    }

    public void postponeEnterTransition() {
        this.ensureAnimationInfo().mEnterTransitionPostponed = true;
    }

    public final void postponeEnterTransition(long v, TimeUnit timeUnit0) {
        this.ensureAnimationInfo().mEnterTransitionPostponed = true;
        Handler handler0 = this.mPostponedHandler;
        if(handler0 != null) {
            handler0.removeCallbacks(this.mPostponedDurationRunnable);
        }
        FragmentManager fragmentManager0 = this.mFragmentManager;
        this.mPostponedHandler = fragmentManager0 == null ? new Handler(Looper.getMainLooper()) : fragmentManager0.getHost().getHandler();
        this.mPostponedHandler.removeCallbacks(this.mPostponedDurationRunnable);
        this.mPostponedHandler.postDelayed(this.mPostponedDurationRunnable, timeUnit0.toMillis(v));
    }

    private ActivityResultLauncher prepareCallInternal(ActivityResultContract activityResultContract0, Function function0, ActivityResultCallback activityResultCallback0) {
        if(this.mState > 1) {
            throw new IllegalStateException("Fragment " + this + " is attempting to registerForActivityResult after being created. Fragments must call registerForActivityResult() before they are created (i.e. initialization, onAttach(), or onCreate()).");
        }
        AtomicReference atomicReference0 = new AtomicReference();
        this.registerOnPreAttachListener(new OnPreAttachedListener(function0, atomicReference0, activityResultContract0, activityResultCallback0) {
            final ActivityResultCallback val$callback;
            final ActivityResultContract val$contract;
            final AtomicReference val$ref;
            final Function val$registryProvider;

            {
                Fragment.this = fragment0;
                this.val$registryProvider = function0;
                this.val$ref = atomicReference0;
                this.val$contract = activityResultContract0;
                this.val$callback = activityResultCallback0;
                super(null);
            }

            @Override  // androidx.fragment.app.Fragment$OnPreAttachedListener
            void onPreAttached() {
                ActivityResultLauncher activityResultLauncher0 = ((ActivityResultRegistry)this.val$registryProvider.apply(null)).register("fragment_dc3b78cf-b777-4fe9-a71f-ac861a469fd1_rq#0", Fragment.this, this.val$contract, this.val$callback);
                this.val$ref.set(activityResultLauncher0);
            }
        });
        return new ActivityResultLauncher() {
            @Override  // androidx.activity.result.ActivityResultLauncher
            public ActivityResultContract getContract() {
                return activityResultContract0;
            }

            @Override  // androidx.activity.result.ActivityResultLauncher
            public void launch(Object object0, ActivityOptionsCompat activityOptionsCompat0) {
                ActivityResultLauncher activityResultLauncher0 = (ActivityResultLauncher)atomicReference0.get();
                if(activityResultLauncher0 == null) {
                    throw new IllegalStateException("Operation cannot be started before fragment is in created state");
                }
                activityResultLauncher0.launch(object0, activityOptionsCompat0);
            }

            @Override  // androidx.activity.result.ActivityResultLauncher
            public void unregister() {
                ActivityResultLauncher activityResultLauncher0 = (ActivityResultLauncher)atomicReference0.getAndSet(null);
                if(activityResultLauncher0 != null) {
                    activityResultLauncher0.unregister();
                }
            }
        };
    }

    @Override  // androidx.activity.result.ActivityResultCaller
    public final ActivityResultLauncher registerForActivityResult(ActivityResultContract activityResultContract0, ActivityResultCallback activityResultCallback0) {
        return this.prepareCallInternal(activityResultContract0, new Function() {
            // 去混淆评级： 低(20)
            public ActivityResultRegistry apply(Void void0) {
                return Fragment.this.mHost instanceof ActivityResultRegistryOwner ? ((ActivityResultRegistryOwner)Fragment.this.mHost).getActivityResultRegistry() : Fragment.this.requireActivity().getActivityResultRegistry();
            }

            @Override  // androidx.arch.core.util.Function
            public Object apply(Object object0) {
                return this.apply(((Void)object0));
            }
        }, activityResultCallback0);
    }

    @Override  // androidx.activity.result.ActivityResultCaller
    public final ActivityResultLauncher registerForActivityResult(ActivityResultContract activityResultContract0, ActivityResultRegistry activityResultRegistry0, ActivityResultCallback activityResultCallback0) {
        return this.prepareCallInternal(activityResultContract0, new Function() {
            public ActivityResultRegistry apply(Void void0) {
                return activityResultRegistry0;
            }

            @Override  // androidx.arch.core.util.Function
            public Object apply(Object object0) {
                return this.apply(((Void)object0));
            }
        }, activityResultCallback0);
    }

    public void registerForContextMenu(View view0) {
        view0.setOnCreateContextMenuListener(this);
    }

    private void registerOnPreAttachListener(OnPreAttachedListener fragment$OnPreAttachedListener0) {
        if(this.mState >= 0) {
            fragment$OnPreAttachedListener0.onPreAttached();
            return;
        }
        this.mOnPreAttachedListeners.add(fragment$OnPreAttachedListener0);
    }

    @Deprecated
    public final void requestPermissions(String[] arr_s, int v) {
        if(this.mHost == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        this.getParentFragmentManager().launchRequestPermissions(this, arr_s, v);
    }

    public final FragmentActivity requireActivity() {
        FragmentActivity fragmentActivity0 = this.getActivity();
        if(fragmentActivity0 == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to an activity.");
        }
        return fragmentActivity0;
    }

    public final Bundle requireArguments() {
        Bundle bundle0 = this.getArguments();
        if(bundle0 == null) {
            throw new IllegalStateException("Fragment " + this + " does not have any arguments.");
        }
        return bundle0;
    }

    public final Context requireContext() {
        Context context0 = this.getContext();
        if(context0 == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to a context.");
        }
        return context0;
    }

    @Deprecated
    public final FragmentManager requireFragmentManager() {
        return this.getParentFragmentManager();
    }

    public final Object requireHost() {
        Object object0 = this.getHost();
        if(object0 == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to a host.");
        }
        return object0;
    }

    public final Fragment requireParentFragment() {
        Fragment fragment0 = this.getParentFragment();
        if(fragment0 == null) {
            throw this.getContext() == null ? new IllegalStateException("Fragment " + this + " is not attached to any Fragment or host") : new IllegalStateException("Fragment " + this + " is not a child Fragment, it is directly attached to " + this.getContext());
        }
        return fragment0;
    }

    public final View requireView() {
        View view0 = this.getView();
        if(view0 == null) {
            throw new IllegalStateException("Fragment " + this + " did not return a View from onCreateView() or this was called before onCreateView().");
        }
        return view0;
    }

    void restoreChildFragmentState(Bundle bundle0) {
        if(bundle0 != null) {
            Parcelable parcelable0 = bundle0.getParcelable("android:support:fragments");
            if(parcelable0 != null) {
                this.mChildFragmentManager.restoreSaveStateInternal(parcelable0);
                this.mChildFragmentManager.dispatchCreate();
            }
        }
    }

    private void restoreViewState() {
        if(FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "moveto RESTORE_VIEW_STATE: " + this);
        }
        if(this.mView != null) {
            this.restoreViewState(this.mSavedFragmentState);
        }
        this.mSavedFragmentState = null;
    }

    final void restoreViewState(Bundle bundle0) {
        SparseArray sparseArray0 = this.mSavedViewState;
        if(sparseArray0 != null) {
            this.mView.restoreHierarchyState(sparseArray0);
            this.mSavedViewState = null;
        }
        if(this.mView != null) {
            this.mViewLifecycleOwner.performRestore(this.mSavedViewRegistryState);
            this.mSavedViewRegistryState = null;
        }
        this.mCalled = false;
        this.onViewStateRestored(bundle0);
        if(!this.mCalled) {
            throw new SuperNotCalledException("Fragment " + this + " did not call through to super.onViewStateRestored()");
        }
        if(this.mView != null) {
            this.mViewLifecycleOwner.handleLifecycleEvent(Event.ON_CREATE);
        }
    }

    public void setAllowEnterTransitionOverlap(boolean z) {
        this.ensureAnimationInfo().mAllowEnterTransitionOverlap = Boolean.valueOf(z);
    }

    public void setAllowReturnTransitionOverlap(boolean z) {
        this.ensureAnimationInfo().mAllowReturnTransitionOverlap = Boolean.valueOf(z);
    }

    void setAnimations(int v, int v1, int v2, int v3) {
        if(this.mAnimationInfo == null && v == 0 && v1 == 0 && v2 == 0 && v3 == 0) {
            return;
        }
        this.ensureAnimationInfo().mEnterAnim = v;
        this.ensureAnimationInfo().mExitAnim = v1;
        this.ensureAnimationInfo().mPopEnterAnim = v2;
        this.ensureAnimationInfo().mPopExitAnim = v3;
    }

    public void setArguments(Bundle bundle0) {
        if(this.mFragmentManager != null && this.isStateSaved()) {
            throw new IllegalStateException("Fragment already added and state has been saved");
        }
        this.mArguments = bundle0;
    }

    public void setEnterSharedElementCallback(SharedElementCallback sharedElementCallback0) {
        this.ensureAnimationInfo().mEnterTransitionCallback = sharedElementCallback0;
    }

    public void setEnterTransition(Object object0) {
        this.ensureAnimationInfo().mEnterTransition = object0;
    }

    public void setExitSharedElementCallback(SharedElementCallback sharedElementCallback0) {
        this.ensureAnimationInfo().mExitTransitionCallback = sharedElementCallback0;
    }

    public void setExitTransition(Object object0) {
        this.ensureAnimationInfo().mExitTransition = object0;
    }

    void setFocusedView(View view0) {
        this.ensureAnimationInfo().mFocusedView = view0;
    }

    @Deprecated
    public void setHasOptionsMenu(boolean z) {
        if(this.mHasMenu != z) {
            this.mHasMenu = z;
            if(this.isAdded() && !this.isHidden()) {
                this.mHost.onSupportInvalidateOptionsMenu();
            }
        }
    }

    public void setInitialSavedState(SavedState fragment$SavedState0) {
        if(this.mFragmentManager != null) {
            throw new IllegalStateException("Fragment already added");
        }
        this.mSavedFragmentState = fragment$SavedState0 == null || fragment$SavedState0.mState == null ? null : fragment$SavedState0.mState;
    }

    public void setMenuVisibility(boolean z) {
        if(this.mMenuVisible != z) {
            this.mMenuVisible = z;
            if(this.mHasMenu && this.isAdded() && !this.isHidden()) {
                this.mHost.onSupportInvalidateOptionsMenu();
            }
        }
    }

    void setNextTransition(int v) {
        if(this.mAnimationInfo == null && v == 0) {
            return;
        }
        this.ensureAnimationInfo();
        this.mAnimationInfo.mNextTransition = v;
    }

    void setPopDirection(boolean z) {
        if(this.mAnimationInfo == null) {
            return;
        }
        this.ensureAnimationInfo().mIsPop = z;
    }

    void setPostOnViewCreatedAlpha(float f) {
        this.ensureAnimationInfo().mPostOnViewCreatedAlpha = f;
    }

    public void setReenterTransition(Object object0) {
        this.ensureAnimationInfo().mReenterTransition = object0;
    }

    @Deprecated
    public void setRetainInstance(boolean z) {
        FragmentStrictMode.onSetRetainInstanceUsage(this);
        this.mRetainInstance = z;
        FragmentManager fragmentManager0 = this.mFragmentManager;
        if(fragmentManager0 != null) {
            if(z) {
                fragmentManager0.addRetainedFragment(this);
                return;
            }
            fragmentManager0.removeRetainedFragment(this);
            return;
        }
        this.mRetainInstanceChangedWhileDetached = true;
    }

    public void setReturnTransition(Object object0) {
        this.ensureAnimationInfo().mReturnTransition = object0;
    }

    public void setSharedElementEnterTransition(Object object0) {
        this.ensureAnimationInfo().mSharedElementEnterTransition = object0;
    }

    void setSharedElementNames(ArrayList arrayList0, ArrayList arrayList1) {
        this.ensureAnimationInfo();
        this.mAnimationInfo.mSharedElementSourceNames = arrayList0;
        this.mAnimationInfo.mSharedElementTargetNames = arrayList1;
    }

    public void setSharedElementReturnTransition(Object object0) {
        this.ensureAnimationInfo().mSharedElementReturnTransition = object0;
    }

    @Deprecated
    public void setTargetFragment(Fragment fragment0, int v) {
        if(fragment0 != null) {
            FragmentStrictMode.onSetTargetFragmentUsage(this, fragment0, v);
        }
        FragmentManager fragmentManager0 = fragment0 == null ? null : fragment0.mFragmentManager;
        if(this.mFragmentManager != null && fragmentManager0 != null && this.mFragmentManager != fragmentManager0) {
            throw new IllegalArgumentException("Fragment " + fragment0 + " must share the same FragmentManager to be set as a target fragment");
        }
        for(Fragment fragment1 = fragment0; fragment1 != null; fragment1 = fragment1.getTargetFragment(false)) {
            if(fragment1.equals(this)) {
                throw new IllegalArgumentException("Setting " + fragment0 + " as the target of " + this + " would create a target cycle");
            }
        }
        if(fragment0 == null) {
            this.mTargetWho = null;
            this.mTarget = null;
        }
        else if(this.mFragmentManager == null || fragment0.mFragmentManager == null) {
            this.mTargetWho = null;
            this.mTarget = fragment0;
        }
        else {
            this.mTargetWho = fragment0.mWho;
            this.mTarget = null;
        }
        this.mTargetRequestCode = v;
    }

    @Deprecated
    public void setUserVisibleHint(boolean z) {
        FragmentStrictMode.onSetUserVisibleHint(this, z);
        if(!this.mUserVisibleHint && z && this.mState < 5 && this.mFragmentManager != null && this.isAdded() && this.mIsCreated) {
            this.mFragmentManager.performPendingDeferredStart(this.mFragmentManager.createOrGetFragmentStateManager(this));
        }
        this.mUserVisibleHint = z;
        this.mDeferStart = this.mState < 5 && !z;
        if(this.mSavedFragmentState != null) {
            this.mSavedUserVisibleHint = Boolean.valueOf(z);
        }
    }

    public boolean shouldShowRequestPermissionRationale(String s) {
        return this.mHost == null ? false : this.mHost.onShouldShowRequestPermissionRationale(s);
    }

    public void startActivity(Intent intent0) {
        this.startActivity(intent0, null);
    }

    public void startActivity(Intent intent0, Bundle bundle0) {
        FragmentHostCallback fragmentHostCallback0 = this.mHost;
        if(fragmentHostCallback0 == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        fragmentHostCallback0.onStartActivityFromFragment(this, intent0, -1, bundle0);
    }

    @Deprecated
    public void startActivityForResult(Intent intent0, int v) {
        this.startActivityForResult(intent0, v, null);
    }

    @Deprecated
    public void startActivityForResult(Intent intent0, int v, Bundle bundle0) {
        if(this.mHost == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        this.getParentFragmentManager().launchStartActivityForResult(this, intent0, v, bundle0);
    }

    @Deprecated
    public void startIntentSenderForResult(IntentSender intentSender0, int v, Intent intent0, int v1, int v2, int v3, Bundle bundle0) throws IntentSender.SendIntentException {
        if(this.mHost == null) {
            throw new IllegalStateException("Fragment " + this + " not attached to Activity");
        }
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Fragment " + this + " received the following in startIntentSenderForResult() requestCode: " + v + " IntentSender: " + intentSender0 + " fillInIntent: " + intent0 + " options: " + bundle0);
        }
        this.getParentFragmentManager().launchStartIntentSenderForResult(this, intentSender0, v, intent0, v1, v2, v3, bundle0);
    }

    // 检测为 Lambda 实现
    public void startPostponedEnterTransition() [...]

    @Override
    public String toString() [...] // 潜在的解密器

    public void unregisterForContextMenu(View view0) {
        view0.setOnCreateContextMenuListener(null);
    }

    class androidx.fragment.app.Fragment.1 implements Runnable {
        @Override
        public void run() {
            Fragment.this.startPostponedEnterTransition();
        }
    }

}

