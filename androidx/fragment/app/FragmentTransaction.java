package androidx.fragment.app;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.strictmode.FragmentStrictMode;
import androidx.lifecycle.Lifecycle.State;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public abstract class FragmentTransaction {
    static final class Op {
        int mCmd;
        State mCurrentMaxState;
        int mEnterAnim;
        int mExitAnim;
        Fragment mFragment;
        boolean mFromExpandedOp;
        State mOldMaxState;
        int mPopEnterAnim;
        int mPopExitAnim;

        Op() {
        }

        Op(int v, Fragment fragment0) {
            this.mCmd = v;
            this.mFragment = fragment0;
            this.mFromExpandedOp = false;
            this.mOldMaxState = State.RESUMED;
            this.mCurrentMaxState = State.RESUMED;
        }

        Op(int v, Fragment fragment0, State lifecycle$State0) {
            this.mCmd = v;
            this.mFragment = fragment0;
            this.mFromExpandedOp = false;
            this.mOldMaxState = fragment0.mMaxState;
            this.mCurrentMaxState = lifecycle$State0;
        }

        Op(int v, Fragment fragment0, boolean z) {
            this.mCmd = v;
            this.mFragment = fragment0;
            this.mFromExpandedOp = z;
            this.mOldMaxState = State.RESUMED;
            this.mCurrentMaxState = State.RESUMED;
        }

        Op(Op fragmentTransaction$Op0) {
            this.mCmd = fragmentTransaction$Op0.mCmd;
            this.mFragment = fragmentTransaction$Op0.mFragment;
            this.mFromExpandedOp = fragmentTransaction$Op0.mFromExpandedOp;
            this.mEnterAnim = fragmentTransaction$Op0.mEnterAnim;
            this.mExitAnim = fragmentTransaction$Op0.mExitAnim;
            this.mPopEnterAnim = fragmentTransaction$Op0.mPopEnterAnim;
            this.mPopExitAnim = fragmentTransaction$Op0.mPopExitAnim;
            this.mOldMaxState = fragmentTransaction$Op0.mOldMaxState;
            this.mCurrentMaxState = fragmentTransaction$Op0.mCurrentMaxState;
        }
    }

    static final int OP_ADD = 1;
    static final int OP_ATTACH = 7;
    static final int OP_DETACH = 6;
    static final int OP_HIDE = 4;
    static final int OP_NULL = 0;
    static final int OP_REMOVE = 3;
    static final int OP_REPLACE = 2;
    static final int OP_SET_MAX_LIFECYCLE = 10;
    static final int OP_SET_PRIMARY_NAV = 8;
    static final int OP_SHOW = 5;
    static final int OP_UNSET_PRIMARY_NAV = 9;
    public static final int TRANSIT_ENTER_MASK = 0x1000;
    public static final int TRANSIT_EXIT_MASK = 0x2000;
    public static final int TRANSIT_FRAGMENT_CLOSE = 0x2002;
    public static final int TRANSIT_FRAGMENT_FADE = 0x1003;
    public static final int TRANSIT_FRAGMENT_MATCH_ACTIVITY_CLOSE = 0x2005;
    public static final int TRANSIT_FRAGMENT_MATCH_ACTIVITY_OPEN = 4100;
    public static final int TRANSIT_FRAGMENT_OPEN = 0x1001;
    public static final int TRANSIT_NONE = 0;
    public static final int TRANSIT_UNSET = -1;
    boolean mAddToBackStack;
    boolean mAllowAddToBackStack;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    private final ClassLoader mClassLoader;
    ArrayList mCommitRunnables;
    int mEnterAnim;
    int mExitAnim;
    private final FragmentFactory mFragmentFactory;
    String mName;
    ArrayList mOps;
    int mPopEnterAnim;
    int mPopExitAnim;
    boolean mReorderingAllowed;
    ArrayList mSharedElementSourceNames;
    ArrayList mSharedElementTargetNames;
    int mTransition;

    @Deprecated
    public FragmentTransaction() {
        this.mOps = new ArrayList();
        this.mAllowAddToBackStack = true;
        this.mReorderingAllowed = false;
        this.mFragmentFactory = null;
        this.mClassLoader = null;
    }

    FragmentTransaction(FragmentFactory fragmentFactory0, ClassLoader classLoader0) {
        this.mOps = new ArrayList();
        this.mAllowAddToBackStack = true;
        this.mReorderingAllowed = false;
        this.mFragmentFactory = fragmentFactory0;
        this.mClassLoader = classLoader0;
    }

    FragmentTransaction(FragmentFactory fragmentFactory0, ClassLoader classLoader0, FragmentTransaction fragmentTransaction0) {
        this(fragmentFactory0, classLoader0);
        for(Object object0: fragmentTransaction0.mOps) {
            this.mOps.add(new Op(((Op)object0)));
        }
        this.mEnterAnim = fragmentTransaction0.mEnterAnim;
        this.mExitAnim = fragmentTransaction0.mExitAnim;
        this.mPopEnterAnim = fragmentTransaction0.mPopEnterAnim;
        this.mPopExitAnim = fragmentTransaction0.mPopExitAnim;
        this.mTransition = fragmentTransaction0.mTransition;
        this.mAddToBackStack = fragmentTransaction0.mAddToBackStack;
        this.mAllowAddToBackStack = fragmentTransaction0.mAllowAddToBackStack;
        this.mName = fragmentTransaction0.mName;
        this.mBreadCrumbShortTitleRes = fragmentTransaction0.mBreadCrumbShortTitleRes;
        this.mBreadCrumbShortTitleText = fragmentTransaction0.mBreadCrumbShortTitleText;
        this.mBreadCrumbTitleRes = fragmentTransaction0.mBreadCrumbTitleRes;
        this.mBreadCrumbTitleText = fragmentTransaction0.mBreadCrumbTitleText;
        if(fragmentTransaction0.mSharedElementSourceNames != null) {
            ArrayList arrayList0 = new ArrayList();
            this.mSharedElementSourceNames = arrayList0;
            arrayList0.addAll(fragmentTransaction0.mSharedElementSourceNames);
        }
        if(fragmentTransaction0.mSharedElementTargetNames != null) {
            ArrayList arrayList1 = new ArrayList();
            this.mSharedElementTargetNames = arrayList1;
            arrayList1.addAll(fragmentTransaction0.mSharedElementTargetNames);
        }
        this.mReorderingAllowed = fragmentTransaction0.mReorderingAllowed;
    }

    public FragmentTransaction add(int v, Fragment fragment0) {
        this.doAddOp(v, fragment0, null, 1);
        return this;
    }

    public FragmentTransaction add(int v, Fragment fragment0, String s) {
        this.doAddOp(v, fragment0, s, 1);
        return this;
    }

    public final FragmentTransaction add(int v, Class class0, Bundle bundle0) {
        return this.add(v, this.createFragment(class0, bundle0));
    }

    public final FragmentTransaction add(int v, Class class0, Bundle bundle0, String s) {
        return this.add(v, this.createFragment(class0, bundle0), s);
    }

    FragmentTransaction add(ViewGroup viewGroup0, Fragment fragment0, String s) {
        fragment0.mContainer = viewGroup0;
        return this.add(viewGroup0.getId(), fragment0, s);
    }

    public FragmentTransaction add(Fragment fragment0, String s) {
        this.doAddOp(0, fragment0, s, 1);
        return this;
    }

    public final FragmentTransaction add(Class class0, Bundle bundle0, String s) {
        return this.add(this.createFragment(class0, bundle0), s);
    }

    void addOp(Op fragmentTransaction$Op0) {
        this.mOps.add(fragmentTransaction$Op0);
        fragmentTransaction$Op0.mEnterAnim = this.mEnterAnim;
        fragmentTransaction$Op0.mExitAnim = this.mExitAnim;
        fragmentTransaction$Op0.mPopEnterAnim = this.mPopEnterAnim;
        fragmentTransaction$Op0.mPopExitAnim = this.mPopExitAnim;
    }

    public FragmentTransaction addSharedElement(View view0, String s) {
        String s1 = ViewCompat.getTransitionName(view0);
        if(s1 == null) {
            throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
        }
        if(this.mSharedElementSourceNames == null) {
            this.mSharedElementSourceNames = new ArrayList();
            this.mSharedElementTargetNames = new ArrayList();
        }
        else {
            if(this.mSharedElementTargetNames.contains(s)) {
                throw new IllegalArgumentException("A shared element with the target name \'" + s + "\' has already been added to the transaction.");
            }
            if(this.mSharedElementSourceNames.contains(s1)) {
                throw new IllegalArgumentException("A shared element with the source name \'" + s1 + "\' has already been added to the transaction.");
            }
        }
        this.mSharedElementSourceNames.add(s1);
        this.mSharedElementTargetNames.add(s);
        return this;
    }

    public FragmentTransaction addToBackStack(String s) {
        if(!this.mAllowAddToBackStack) {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        }
        this.mAddToBackStack = true;
        this.mName = s;
        return this;
    }

    public FragmentTransaction attach(Fragment fragment0) {
        this.addOp(new Op(7, fragment0));
        return this;
    }

    public abstract int commit();

    public abstract int commitAllowingStateLoss();

    public abstract void commitNow();

    public abstract void commitNowAllowingStateLoss();

    private Fragment createFragment(Class class0, Bundle bundle0) {
        FragmentFactory fragmentFactory0 = this.mFragmentFactory;
        if(fragmentFactory0 == null) {
            throw new IllegalStateException("Creating a Fragment requires that this FragmentTransaction was built with FragmentManager.beginTransaction()");
        }
        ClassLoader classLoader0 = this.mClassLoader;
        if(classLoader0 == null) {
            throw new IllegalStateException("The FragmentManager must be attached to itshost to create a Fragment");
        }
        Fragment fragment0 = fragmentFactory0.instantiate(classLoader0, class0.getName());
        if(bundle0 != null) {
            fragment0.setArguments(bundle0);
        }
        return fragment0;
    }

    public FragmentTransaction detach(Fragment fragment0) {
        this.addOp(new Op(6, fragment0));
        return this;
    }

    public FragmentTransaction disallowAddToBackStack() {
        if(this.mAddToBackStack) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.mAllowAddToBackStack = false;
        return this;
    }

    void doAddOp(int v, Fragment fragment0, String s, int v1) {
        if(fragment0.mPreviousWho != null) {
            FragmentStrictMode.onFragmentReuse(fragment0, fragment0.mPreviousWho);
        }
        Class class0 = fragment0.getClass();
        int v2 = class0.getModifiers();
        if(class0.isAnonymousClass() || !Modifier.isPublic(v2) || class0.isMemberClass() && !Modifier.isStatic(v2)) {
            throw new IllegalStateException("Fragment " + class0.getCanonicalName() + " must be a public static class to be  properly recreated from instance state.");
        }
        if(s != null) {
            if(fragment0.mTag != null && !s.equals(fragment0.mTag)) {
                throw new IllegalStateException("Can\'t change tag of fragment " + fragment0 + ": was " + fragment0.mTag + " now " + s);
            }
            fragment0.mTag = s;
        }
        switch(v) {
            case -1: {
                throw new IllegalArgumentException("Can\'t add fragment " + fragment0 + " with tag " + s + " to container view with no id");
            label_12:
                if(fragment0.mFragmentId != 0 && fragment0.mFragmentId != v) {
                    throw new IllegalStateException("Can\'t change container ID of fragment " + fragment0 + ": was " + fragment0.mFragmentId + " now " + v);
                }
                fragment0.mFragmentId = v;
                fragment0.mContainerId = v;
                break;
            }
            case 0: {
                break;
            }
            default: {
                goto label_12;
            }
        }
        this.addOp(new Op(v1, fragment0));
    }

    public FragmentTransaction hide(Fragment fragment0) {
        this.addOp(new Op(4, fragment0));
        return this;
    }

    public boolean isAddToBackStackAllowed() {
        return this.mAllowAddToBackStack;
    }

    public boolean isEmpty() {
        return this.mOps.isEmpty();
    }

    public FragmentTransaction remove(Fragment fragment0) {
        this.addOp(new Op(3, fragment0));
        return this;
    }

    public FragmentTransaction replace(int v, Fragment fragment0) {
        return this.replace(v, fragment0, null);
    }

    public FragmentTransaction replace(int v, Fragment fragment0, String s) {
        if(v == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        this.doAddOp(v, fragment0, s, 2);
        return this;
    }

    public final FragmentTransaction replace(int v, Class class0, Bundle bundle0) {
        return this.replace(v, class0, bundle0, null);
    }

    public final FragmentTransaction replace(int v, Class class0, Bundle bundle0, String s) {
        return this.replace(v, this.createFragment(class0, bundle0), s);
    }

    public FragmentTransaction runOnCommit(Runnable runnable0) {
        this.disallowAddToBackStack();
        if(this.mCommitRunnables == null) {
            this.mCommitRunnables = new ArrayList();
        }
        this.mCommitRunnables.add(runnable0);
        return this;
    }

    @Deprecated
    public FragmentTransaction setAllowOptimization(boolean z) {
        return this.setReorderingAllowed(z);
    }

    @Deprecated
    public FragmentTransaction setBreadCrumbShortTitle(int v) {
        this.mBreadCrumbShortTitleRes = v;
        this.mBreadCrumbShortTitleText = null;
        return this;
    }

    @Deprecated
    public FragmentTransaction setBreadCrumbShortTitle(CharSequence charSequence0) {
        this.mBreadCrumbShortTitleRes = 0;
        this.mBreadCrumbShortTitleText = charSequence0;
        return this;
    }

    @Deprecated
    public FragmentTransaction setBreadCrumbTitle(int v) {
        this.mBreadCrumbTitleRes = v;
        this.mBreadCrumbTitleText = null;
        return this;
    }

    @Deprecated
    public FragmentTransaction setBreadCrumbTitle(CharSequence charSequence0) {
        this.mBreadCrumbTitleRes = 0;
        this.mBreadCrumbTitleText = charSequence0;
        return this;
    }

    public FragmentTransaction setCustomAnimations(int v, int v1) {
        return this.setCustomAnimations(v, v1, 0, 0);
    }

    public FragmentTransaction setCustomAnimations(int v, int v1, int v2, int v3) {
        this.mEnterAnim = v;
        this.mExitAnim = v1;
        this.mPopEnterAnim = v2;
        this.mPopExitAnim = v3;
        return this;
    }

    public FragmentTransaction setMaxLifecycle(Fragment fragment0, State lifecycle$State0) {
        this.addOp(new Op(10, fragment0, lifecycle$State0));
        return this;
    }

    public FragmentTransaction setPrimaryNavigationFragment(Fragment fragment0) {
        this.addOp(new Op(8, fragment0));
        return this;
    }

    public FragmentTransaction setReorderingAllowed(boolean z) {
        this.mReorderingAllowed = z;
        return this;
    }

    public FragmentTransaction setTransition(int v) {
        this.mTransition = v;
        return this;
    }

    @Deprecated
    public FragmentTransaction setTransitionStyle(int v) {
        return this;
    }

    public FragmentTransaction show(Fragment fragment0) {
        this.addOp(new Op(5, fragment0));
        return this;
    }
}

