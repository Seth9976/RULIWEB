package androidx.fragment.app;

import android.app.Activity;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View.OnAttachStateChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.core.view.ViewCompat;
import androidx.fragment.R.id;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.lifecycle.ViewModelStoreOwner;

class FragmentStateManager {
    private static final String TAG = "FragmentManager";
    private static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
    private static final String TARGET_STATE_TAG = "android:target_state";
    private static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
    private static final String VIEW_REGISTRY_STATE_TAG = "android:view_registry_state";
    private static final String VIEW_STATE_TAG = "android:view_state";
    private final FragmentLifecycleCallbacksDispatcher mDispatcher;
    private final Fragment mFragment;
    private int mFragmentManagerState;
    private final FragmentStore mFragmentStore;
    private boolean mMovingToState;

    FragmentStateManager(FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher0, FragmentStore fragmentStore0, Fragment fragment0) {
        this.mMovingToState = false;
        this.mFragmentManagerState = -1;
        this.mDispatcher = fragmentLifecycleCallbacksDispatcher0;
        this.mFragmentStore = fragmentStore0;
        this.mFragment = fragment0;
    }

    FragmentStateManager(FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher0, FragmentStore fragmentStore0, Fragment fragment0, FragmentState fragmentState0) {
        this.mMovingToState = false;
        this.mFragmentManagerState = -1;
        this.mDispatcher = fragmentLifecycleCallbacksDispatcher0;
        this.mFragmentStore = fragmentStore0;
        this.mFragment = fragment0;
        fragment0.mSavedViewState = null;
        fragment0.mSavedViewRegistryState = null;
        fragment0.mBackStackNesting = 0;
        fragment0.mInLayout = false;
        fragment0.mAdded = false;
        fragment0.mTargetWho = fragment0.mTarget == null ? null : fragment0.mTarget.mWho;
        fragment0.mTarget = null;
        if(fragmentState0.mSavedFragmentState != null) {
            fragment0.mSavedFragmentState = fragmentState0.mSavedFragmentState;
            return;
        }
        fragment0.mSavedFragmentState = new Bundle();
    }

    FragmentStateManager(FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher0, FragmentStore fragmentStore0, ClassLoader classLoader0, FragmentFactory fragmentFactory0, FragmentState fragmentState0) {
        this.mMovingToState = false;
        this.mFragmentManagerState = -1;
        this.mDispatcher = fragmentLifecycleCallbacksDispatcher0;
        this.mFragmentStore = fragmentStore0;
        Fragment fragment0 = fragmentState0.instantiate(fragmentFactory0, classLoader0);
        this.mFragment = fragment0;
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Instantiated fragment " + fragment0);
        }
    }

    void activityCreated() {
        if(FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "moveto ACTIVITY_CREATED: " + this.mFragment);
        }
        this.mFragment.performActivityCreated(this.mFragment.mSavedFragmentState);
        this.mDispatcher.dispatchOnFragmentActivityCreated(this.mFragment, this.mFragment.mSavedFragmentState, false);
    }

    void addViewToContainer() {
        int v = this.mFragmentStore.findFragmentIndexInContainer(this.mFragment);
        this.mFragment.mContainer.addView(this.mFragment.mView, v);
    }

    void attach() {
        if(FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "moveto ATTACHED: " + this.mFragment);
        }
        FragmentStateManager fragmentStateManager0 = null;
        if(this.mFragment.mTarget != null) {
            FragmentStateManager fragmentStateManager1 = this.mFragmentStore.getFragmentStateManager(this.mFragment.mTarget.mWho);
            if(fragmentStateManager1 == null) {
                throw new IllegalStateException("Fragment " + this.mFragment + " declared target fragment " + this.mFragment.mTarget + " that does not belong to this FragmentManager!");
            }
            this.mFragment.mTargetWho = this.mFragment.mTarget.mWho;
            this.mFragment.mTarget = null;
            fragmentStateManager0 = fragmentStateManager1;
        }
        else if(this.mFragment.mTargetWho != null) {
            fragmentStateManager0 = this.mFragmentStore.getFragmentStateManager(this.mFragment.mTargetWho);
            if(fragmentStateManager0 == null) {
                throw new IllegalStateException("Fragment " + this.mFragment + " declared target fragment " + this.mFragment.mTargetWho + " that does not belong to this FragmentManager!");
            }
        }
        if(fragmentStateManager0 != null) {
            fragmentStateManager0.moveToExpectedState();
        }
        this.mFragment.mHost = this.mFragment.mFragmentManager.getHost();
        this.mFragment.mParentFragment = this.mFragment.mFragmentManager.getParent();
        this.mDispatcher.dispatchOnFragmentPreAttached(this.mFragment, false);
        this.mFragment.performAttach();
        this.mDispatcher.dispatchOnFragmentAttached(this.mFragment, false);
    }

    int computeExpectedState() {
        if(this.mFragment.mFragmentManager == null) {
            return this.mFragment.mState;
        }
        int v = this.mFragmentManagerState;
        switch(this.mFragment.mMaxState) {
            case 1: {
                break;
            }
            case 2: {
                v = Math.min(v, 5);
                break;
            }
            case 3: {
                v = Math.min(v, 1);
                break;
            }
            case 4: {
                v = Math.min(v, 0);
                break;
            }
            default: {
                v = Math.min(v, -1);
            }
        }
        if(this.mFragment.mFromLayout) {
            if(this.mFragment.mInLayout) {
                v = Math.max(this.mFragmentManagerState, 2);
                if(this.mFragment.mView != null && this.mFragment.mView.getParent() == null) {
                    v = Math.min(v, 2);
                }
            }
            else {
                v = this.mFragmentManagerState >= 4 ? Math.min(v, 1) : Math.min(v, this.mFragment.mState);
            }
        }
        if(!this.mFragment.mAdded) {
            v = Math.min(v, 1);
        }
        LifecycleImpact specialEffectsController$Operation$LifecycleImpact0 = this.mFragment.mContainer == null ? null : SpecialEffectsController.getOrCreateController(this.mFragment.mContainer, this.mFragment.getParentFragmentManager()).getAwaitingCompletionLifecycleImpact(this);
        if(specialEffectsController$Operation$LifecycleImpact0 == LifecycleImpact.ADDING) {
            v = Math.min(v, 6);
        }
        else if(specialEffectsController$Operation$LifecycleImpact0 == LifecycleImpact.REMOVING) {
            v = Math.max(v, 3);
        }
        else if(this.mFragment.mRemoving) {
            v = this.mFragment.isInBackStack() ? Math.min(v, 1) : Math.min(v, -1);
        }
        if(this.mFragment.mDeferStart && this.mFragment.mState < 5) {
            v = Math.min(v, 4);
        }
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "computeExpectedState() of " + v + " for " + this.mFragment);
        }
        return v;
    }

    void create() {
        if(FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "moveto CREATED: " + this.mFragment);
        }
        if(!this.mFragment.mIsCreated) {
            this.mDispatcher.dispatchOnFragmentPreCreated(this.mFragment, this.mFragment.mSavedFragmentState, false);
            this.mFragment.performCreate(this.mFragment.mSavedFragmentState);
            this.mDispatcher.dispatchOnFragmentCreated(this.mFragment, this.mFragment.mSavedFragmentState, false);
            return;
        }
        this.mFragment.restoreChildFragmentState(this.mFragment.mSavedFragmentState);
        this.mFragment.mState = 1;
    }

    void createView() {
        ViewGroup viewGroup0;
        String s;
        if(this.mFragment.mFromLayout) {
            return;
        }
        if(FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.mFragment);
        }
        LayoutInflater layoutInflater0 = this.mFragment.performGetLayoutInflater(this.mFragment.mSavedFragmentState);
        if(this.mFragment.mContainer == null) {
            switch(this.mFragment.mContainerId) {
                case -1: {
                    throw new IllegalArgumentException("Cannot create fragment " + this.mFragment + " for a container view with no id");
                label_11:
                    viewGroup0 = (ViewGroup)this.mFragment.mFragmentManager.getContainer().onFindViewById(this.mFragment.mContainerId);
                    if(viewGroup0 == null) {
                        if(!this.mFragment.mRestored) {
                            try {
                                s = "unknown";
                                s = this.mFragment.getResources().getResourceName(this.mFragment.mContainerId);
                            }
                            catch(Resources.NotFoundException unused_ex) {
                            }
                            throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(this.mFragment.mContainerId) + " (" + s + ") for fragment " + this.mFragment);
                        }
                    }
                    else if(!(viewGroup0 instanceof FragmentContainerView)) {
                        FragmentStrictMode.onWrongFragmentContainer(this.mFragment, viewGroup0);
                    }
                    break;
                }
                case 0: {
                    viewGroup0 = null;
                    break;
                }
                default: {
                    goto label_11;
                }
            }
        }
        else {
            viewGroup0 = this.mFragment.mContainer;
        }
        this.mFragment.mContainer = viewGroup0;
        this.mFragment.performCreateView(layoutInflater0, viewGroup0, this.mFragment.mSavedFragmentState);
        if(this.mFragment.mView != null) {
            this.mFragment.mView.setSaveFromParentEnabled(false);
            this.mFragment.mView.setTag(id.fragment_container_view_tag, this.mFragment);
            if(viewGroup0 != null) {
                this.addViewToContainer();
            }
            if(this.mFragment.mHidden) {
                this.mFragment.mView.setVisibility(8);
            }
            if(ViewCompat.isAttachedToWindow(this.mFragment.mView)) {
                ViewCompat.requestApplyInsets(this.mFragment.mView);
            }
            else {
                View view0 = this.mFragment.mView;
                view0.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                    @Override  // android.view.View$OnAttachStateChangeListener
                    public void onViewAttachedToWindow(View view0) {
                        view0.removeOnAttachStateChangeListener(this);
                        ViewCompat.requestApplyInsets(view0);
                    }

                    @Override  // android.view.View$OnAttachStateChangeListener
                    public void onViewDetachedFromWindow(View view0) {
                    }
                });
            }
            this.mFragment.performViewCreated();
            this.mDispatcher.dispatchOnFragmentViewCreated(this.mFragment, this.mFragment.mView, this.mFragment.mSavedFragmentState, false);
            int v = this.mFragment.mView.getVisibility();
            float f = this.mFragment.mView.getAlpha();
            this.mFragment.setPostOnViewCreatedAlpha(f);
            if(this.mFragment.mContainer != null && v == 0) {
                View view1 = this.mFragment.mView.findFocus();
                if(view1 != null) {
                    this.mFragment.setFocusedView(view1);
                    if(FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "requestFocus: Saved focused view " + view1 + " for Fragment " + this.mFragment);
                    }
                }
                this.mFragment.mView.setAlpha(0.0f);
            }
        }
        this.mFragment.mState = 2;
    }

    void destroy() {
        if(FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "movefrom CREATED: " + this.mFragment);
        }
        boolean z = true;
        boolean z1 = this.mFragment.mRemoving && !this.mFragment.isInBackStack();
        if(z1 && !this.mFragment.mBeingSaved) {
            this.mFragmentStore.setSavedState(this.mFragment.mWho, null);
        }
        if(!z1 && !this.mFragmentStore.getNonConfig().shouldDestroy(this.mFragment)) {
            if(this.mFragment.mTargetWho != null) {
                Fragment fragment0 = this.mFragmentStore.findActiveFragment(this.mFragment.mTargetWho);
                if(fragment0 != null && fragment0.mRetainInstance) {
                    this.mFragment.mTarget = fragment0;
                }
            }
            this.mFragment.mState = 0;
            return;
        }
        FragmentHostCallback fragmentHostCallback0 = this.mFragment.mHost;
        if(fragmentHostCallback0 instanceof ViewModelStoreOwner) {
            z = this.mFragmentStore.getNonConfig().isCleared();
        }
        else if(fragmentHostCallback0.getContext() instanceof Activity) {
            z = true ^ ((Activity)fragmentHostCallback0.getContext()).isChangingConfigurations();
        }
        if(z1 && !this.mFragment.mBeingSaved || z) {
            this.mFragmentStore.getNonConfig().clearNonConfigState(this.mFragment);
        }
        this.mFragment.performDestroy();
        this.mDispatcher.dispatchOnFragmentDestroyed(this.mFragment, false);
        for(Object object0: this.mFragmentStore.getActiveFragmentStateManagers()) {
            FragmentStateManager fragmentStateManager0 = (FragmentStateManager)object0;
            if(fragmentStateManager0 != null) {
                Fragment fragment1 = fragmentStateManager0.getFragment();
                if(this.mFragment.mWho.equals(fragment1.mTargetWho)) {
                    fragment1.mTarget = this.mFragment;
                    fragment1.mTargetWho = null;
                }
            }
        }
        if(this.mFragment.mTargetWho != null) {
            this.mFragment.mTarget = this.mFragmentStore.findActiveFragment(this.mFragment.mTargetWho);
        }
        this.mFragmentStore.makeInactive(this);
    }

    void destroyFragmentView() {
        if(FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "movefrom CREATE_VIEW: " + this.mFragment);
        }
        if(this.mFragment.mContainer != null && this.mFragment.mView != null) {
            this.mFragment.mContainer.removeView(this.mFragment.mView);
        }
        this.mFragment.performDestroyView();
        this.mDispatcher.dispatchOnFragmentViewDestroyed(this.mFragment, false);
        this.mFragment.mContainer = null;
        this.mFragment.mView = null;
        this.mFragment.mViewLifecycleOwner = null;
        this.mFragment.mViewLifecycleOwnerLiveData.setValue(null);
        this.mFragment.mInLayout = false;
    }

    void detach() {
        if(FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "movefrom ATTACHED: " + this.mFragment);
        }
        this.mFragment.performDetach();
        this.mDispatcher.dispatchOnFragmentDetached(this.mFragment, false);
        this.mFragment.mState = -1;
        this.mFragment.mHost = null;
        this.mFragment.mParentFragment = null;
        this.mFragment.mFragmentManager = null;
        if(this.mFragment.mRemoving && !this.mFragment.isInBackStack() || this.mFragmentStore.getNonConfig().shouldDestroy(this.mFragment)) {
            if(FragmentManager.isLoggingEnabled(3)) {
                Log.d("FragmentManager", "initState called for fragment: " + this.mFragment);
            }
            this.mFragment.initState();
        }
    }

    void ensureInflatedView() {
        if(this.mFragment.mFromLayout && this.mFragment.mInLayout && !this.mFragment.mPerformedCreateView) {
            if(FragmentManager.isLoggingEnabled(3)) {
                Log.d("FragmentManager", "moveto CREATE_VIEW: " + this.mFragment);
            }
            LayoutInflater layoutInflater0 = this.mFragment.performGetLayoutInflater(this.mFragment.mSavedFragmentState);
            this.mFragment.performCreateView(layoutInflater0, null, this.mFragment.mSavedFragmentState);
            if(this.mFragment.mView != null) {
                this.mFragment.mView.setSaveFromParentEnabled(false);
                this.mFragment.mView.setTag(id.fragment_container_view_tag, this.mFragment);
                if(this.mFragment.mHidden) {
                    this.mFragment.mView.setVisibility(8);
                }
                this.mFragment.performViewCreated();
                this.mDispatcher.dispatchOnFragmentViewCreated(this.mFragment, this.mFragment.mView, this.mFragment.mSavedFragmentState, false);
                this.mFragment.mState = 2;
            }
        }
    }

    Fragment getFragment() {
        return this.mFragment;
    }

    private boolean isFragmentViewChild(View view0) {
        if(view0 == this.mFragment.mView) {
            return true;
        }
        for(ViewParent viewParent0 = view0.getParent(); viewParent0 != null; viewParent0 = viewParent0.getParent()) {
            if(viewParent0 == this.mFragment.mView) {
                return true;
            }
        }
        return false;
    }

    void moveToExpectedState() {
        if(this.mMovingToState) {
            if(FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Ignoring re-entrant call to moveToExpectedState() for " + this.getFragment());
            }
            return;
        }
        try {
            this.mMovingToState = true;
            boolean z = false;
            int v1;
            while((v1 = this.computeExpectedState()) != this.mFragment.mState) {
                if(v1 > this.mFragment.mState) {
                    switch(this.mFragment.mState + 1) {
                        case 0: {
                            this.attach();
                            break;
                        }
                        case 1: {
                            this.create();
                            break;
                        }
                        case 2: {
                            this.ensureInflatedView();
                            this.createView();
                            break;
                        }
                        case 3: {
                            this.activityCreated();
                            break;
                        }
                        case 4: {
                            if(this.mFragment.mView != null && this.mFragment.mContainer != null) {
                                SpecialEffectsController.getOrCreateController(this.mFragment.mContainer, this.mFragment.getParentFragmentManager()).enqueueAdd(State.from(this.mFragment.mView.getVisibility()), this);
                            }
                            this.mFragment.mState = 4;
                            break;
                        }
                        case 5: {
                            this.start();
                            break;
                        }
                        case 6: {
                            this.mFragment.mState = 6;
                            break;
                        }
                        case 7: {
                            this.resume();
                        }
                    }
                }
                else {
                    switch(this.mFragment.mState - 1) {
                        case -1: {
                            this.detach();
                            break;
                        }
                        case 0: {
                            if(this.mFragment.mBeingSaved && this.mFragmentStore.getSavedState(this.mFragment.mWho) == null) {
                                this.saveState();
                            }
                            this.destroy();
                            break;
                        }
                        case 1: {
                            this.destroyFragmentView();
                            this.mFragment.mState = 1;
                            break;
                        }
                        case 2: {
                            this.mFragment.mInLayout = false;
                            this.mFragment.mState = 2;
                            break;
                        }
                        case 3: {
                            if(FragmentManager.isLoggingEnabled(3)) {
                                Log.d("FragmentManager", "movefrom ACTIVITY_CREATED: " + this.mFragment);
                            }
                            if(this.mFragment.mBeingSaved) {
                                this.saveState();
                            }
                            else if(this.mFragment.mView != null && this.mFragment.mSavedViewState == null) {
                                this.saveViewState();
                            }
                            if(this.mFragment.mView != null && this.mFragment.mContainer != null) {
                                SpecialEffectsController.getOrCreateController(this.mFragment.mContainer, this.mFragment.getParentFragmentManager()).enqueueRemove(this);
                            }
                            this.mFragment.mState = 3;
                            break;
                        }
                        case 4: {
                            this.stop();
                            break;
                        }
                        case 5: {
                            this.mFragment.mState = 5;
                            break;
                        }
                        case 6: {
                            this.pause();
                        }
                    }
                }
                z = true;
            }
            if(!z && this.mFragment.mState == -1 && this.mFragment.mRemoving && !this.mFragment.isInBackStack() && !this.mFragment.mBeingSaved) {
                if(FragmentManager.isLoggingEnabled(3)) {
                    Log.d("FragmentManager", "Cleaning up state of never attached fragment: " + this.mFragment);
                }
                this.mFragmentStore.getNonConfig().clearNonConfigState(this.mFragment);
                this.mFragmentStore.makeInactive(this);
                if(FragmentManager.isLoggingEnabled(3)) {
                    Log.d("FragmentManager", "initState called for fragment: " + this.mFragment);
                }
                this.mFragment.initState();
            }
            if(this.mFragment.mHiddenChanged) {
                if(this.mFragment.mView != null && this.mFragment.mContainer != null) {
                    SpecialEffectsController specialEffectsController0 = SpecialEffectsController.getOrCreateController(this.mFragment.mContainer, this.mFragment.getParentFragmentManager());
                    if(this.mFragment.mHidden) {
                        specialEffectsController0.enqueueHide(this);
                    }
                    else {
                        specialEffectsController0.enqueueShow(this);
                    }
                }
                if(this.mFragment.mFragmentManager != null) {
                    this.mFragment.mFragmentManager.invalidateMenuForFragment(this.mFragment);
                }
                this.mFragment.mHiddenChanged = false;
                this.mFragment.mChildFragmentManager.dispatchOnHiddenChanged();
            }
        }
        finally {
            this.mMovingToState = false;
        }
    }

    void pause() {
        if(FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "movefrom RESUMED: " + this.mFragment);
        }
        this.mFragment.performPause();
        this.mDispatcher.dispatchOnFragmentPaused(this.mFragment, false);
    }

    void restoreState(ClassLoader classLoader0) {
        if(this.mFragment.mSavedFragmentState != null) {
            this.mFragment.mSavedFragmentState.setClassLoader(classLoader0);
            this.mFragment.mSavedViewState = this.mFragment.mSavedFragmentState.getSparseParcelableArray("android:view_state");
            this.mFragment.mSavedViewRegistryState = this.mFragment.mSavedFragmentState.getBundle("android:view_registry_state");
            this.mFragment.mTargetWho = this.mFragment.mSavedFragmentState.getString("android:target_state");
            if(this.mFragment.mTargetWho != null) {
                this.mFragment.mTargetRequestCode = this.mFragment.mSavedFragmentState.getInt("android:target_req_state", 0);
            }
            if(this.mFragment.mSavedUserVisibleHint == null) {
                this.mFragment.mUserVisibleHint = this.mFragment.mSavedFragmentState.getBoolean("android:user_visible_hint", true);
            }
            else {
                this.mFragment.mUserVisibleHint = this.mFragment.mSavedUserVisibleHint.booleanValue();
                this.mFragment.mSavedUserVisibleHint = null;
            }
            if(!this.mFragment.mUserVisibleHint) {
                this.mFragment.mDeferStart = true;
            }
        }
    }

    void resume() {
        if(FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "moveto RESUMED: " + this.mFragment);
        }
        View view0 = this.mFragment.getFocusedView();
        if(view0 != null && this.isFragmentViewChild(view0)) {
            boolean z = view0.requestFocus();
            if(FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "requestFocus: Restoring focused view " + view0 + " " + (z ? "succeeded" : "failed") + " on Fragment " + this.mFragment + " resulting in focused view " + this.mFragment.mView.findFocus());
            }
        }
        this.mFragment.setFocusedView(null);
        this.mFragment.performResume();
        this.mDispatcher.dispatchOnFragmentResumed(this.mFragment, false);
        this.mFragment.mSavedFragmentState = null;
        this.mFragment.mSavedViewState = null;
        this.mFragment.mSavedViewRegistryState = null;
    }

    private Bundle saveBasicState() {
        Bundle bundle0 = new Bundle();
        this.mFragment.performSaveInstanceState(bundle0);
        this.mDispatcher.dispatchOnFragmentSaveInstanceState(this.mFragment, bundle0, false);
        if(bundle0.isEmpty()) {
            bundle0 = null;
        }
        if(this.mFragment.mView != null) {
            this.saveViewState();
        }
        if(this.mFragment.mSavedViewState != null) {
            if(bundle0 == null) {
                bundle0 = new Bundle();
            }
            bundle0.putSparseParcelableArray("android:view_state", this.mFragment.mSavedViewState);
        }
        if(this.mFragment.mSavedViewRegistryState != null) {
            if(bundle0 == null) {
                bundle0 = new Bundle();
            }
            bundle0.putBundle("android:view_registry_state", this.mFragment.mSavedViewRegistryState);
        }
        if(!this.mFragment.mUserVisibleHint) {
            if(bundle0 == null) {
                bundle0 = new Bundle();
            }
            bundle0.putBoolean("android:user_visible_hint", this.mFragment.mUserVisibleHint);
        }
        return bundle0;
    }

    SavedState saveInstanceState() {
        if(this.mFragment.mState > -1) {
            Bundle bundle0 = this.saveBasicState();
            return bundle0 == null ? null : new SavedState(bundle0);
        }
        return null;
    }

    void saveState() {
        FragmentState fragmentState0 = new FragmentState(this.mFragment);
        if(this.mFragment.mState <= -1 || fragmentState0.mSavedFragmentState != null) {
            fragmentState0.mSavedFragmentState = this.mFragment.mSavedFragmentState;
        }
        else {
            fragmentState0.mSavedFragmentState = this.saveBasicState();
            if(this.mFragment.mTargetWho != null) {
                if(fragmentState0.mSavedFragmentState == null) {
                    fragmentState0.mSavedFragmentState = new Bundle();
                }
                fragmentState0.mSavedFragmentState.putString("android:target_state", this.mFragment.mTargetWho);
                if(this.mFragment.mTargetRequestCode != 0) {
                    fragmentState0.mSavedFragmentState.putInt("android:target_req_state", this.mFragment.mTargetRequestCode);
                }
            }
        }
        this.mFragmentStore.setSavedState(this.mFragment.mWho, fragmentState0);
    }

    void saveViewState() {
        if(this.mFragment.mView != null) {
            if(FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Saving view state for fragment " + this.mFragment + " with view " + this.mFragment.mView);
            }
            SparseArray sparseArray0 = new SparseArray();
            this.mFragment.mView.saveHierarchyState(sparseArray0);
            if(sparseArray0.size() > 0) {
                this.mFragment.mSavedViewState = sparseArray0;
            }
            Bundle bundle0 = new Bundle();
            this.mFragment.mViewLifecycleOwner.performSave(bundle0);
            if(!bundle0.isEmpty()) {
                this.mFragment.mSavedViewRegistryState = bundle0;
            }
        }
    }

    void setFragmentManagerState(int v) {
        this.mFragmentManagerState = v;
    }

    void start() {
        if(FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "moveto STARTED: " + this.mFragment);
        }
        this.mFragment.performStart();
        this.mDispatcher.dispatchOnFragmentStarted(this.mFragment, false);
    }

    void stop() {
        if(FragmentManager.isLoggingEnabled(3)) {
            Log.d("FragmentManager", "movefrom STARTED: " + this.mFragment);
        }
        this.mFragment.performStop();
        this.mDispatcher.dispatchOnFragmentStopped(this.mFragment, false);
    }
}

