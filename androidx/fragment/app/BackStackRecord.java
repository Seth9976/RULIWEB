package androidx.fragment.app;

import android.util.Log;
import androidx.lifecycle.Lifecycle.State;
import java.io.PrintWriter;
import java.util.ArrayList;

final class BackStackRecord extends FragmentTransaction implements BackStackEntry, OpGenerator {
    private static final String TAG = "FragmentManager";
    boolean mBeingSaved;
    boolean mCommitted;
    int mIndex;
    final FragmentManager mManager;

    BackStackRecord(BackStackRecord backStackRecord0) {
        super(backStackRecord0.mManager.getFragmentFactory(), (backStackRecord0.mManager.getHost() == null ? null : backStackRecord0.mManager.getHost().getContext().getClassLoader()), backStackRecord0);
        this.mManager = backStackRecord0.mManager;
        this.mCommitted = backStackRecord0.mCommitted;
        this.mIndex = backStackRecord0.mIndex;
        this.mBeingSaved = backStackRecord0.mBeingSaved;
    }

    BackStackRecord(FragmentManager fragmentManager0) {
        super(fragmentManager0.getFragmentFactory(), (fragmentManager0.getHost() == null ? null : fragmentManager0.getHost().getContext().getClassLoader()));
        this.mIndex = -1;
        this.mBeingSaved = false;
        this.mManager = fragmentManager0;
    }

    void bumpBackStackNesting(int v) {
        if(this.mAddToBackStack) {
            if(FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + v);
            }
            int v1 = this.mOps.size();
            for(int v2 = 0; v2 < v1; ++v2) {
                Op fragmentTransaction$Op0 = (Op)this.mOps.get(v2);
                if(fragmentTransaction$Op0.mFragment != null) {
                    fragmentTransaction$Op0.mFragment.mBackStackNesting += v;
                    if(FragmentManager.isLoggingEnabled(2)) {
                        Log.v("FragmentManager", "Bump nesting of " + fragmentTransaction$Op0.mFragment + " to " + fragmentTransaction$Op0.mFragment.mBackStackNesting);
                    }
                }
            }
        }
    }

    void collapseOps() {
        for(int v = this.mOps.size() - 1; v >= 0; --v) {
            Op fragmentTransaction$Op0 = (Op)this.mOps.get(v);
            if(fragmentTransaction$Op0.mFromExpandedOp) {
                if(fragmentTransaction$Op0.mCmd == 8) {
                    fragmentTransaction$Op0.mFromExpandedOp = false;
                    this.mOps.remove(v - 1);
                    --v;
                }
                else {
                    int v1 = fragmentTransaction$Op0.mFragment.mContainerId;
                    fragmentTransaction$Op0.mCmd = 2;
                    fragmentTransaction$Op0.mFromExpandedOp = false;
                    for(int v2 = v - 1; v2 >= 0; --v2) {
                        Op fragmentTransaction$Op1 = (Op)this.mOps.get(v2);
                        if(fragmentTransaction$Op1.mFromExpandedOp && fragmentTransaction$Op1.mFragment.mContainerId == v1) {
                            this.mOps.remove(v2);
                            --v;
                        }
                    }
                }
            }
        }
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public int commit() {
        return this.commitInternal(false);
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public int commitAllowingStateLoss() {
        return this.commitInternal(true);
    }

    int commitInternal(boolean z) {
        if(this.mCommitted) {
            throw new IllegalStateException("commit already called");
        }
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Commit: " + this);
            PrintWriter printWriter0 = new PrintWriter(new LogWriter("FragmentManager"));
            this.dump("  ", printWriter0);
            printWriter0.close();
        }
        this.mCommitted = true;
        this.mIndex = this.mAddToBackStack ? this.mManager.allocBackStackIndex() : -1;
        this.mManager.enqueueAction(this, z);
        return this.mIndex;
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public void commitNow() {
        this.disallowAddToBackStack();
        this.mManager.execSingleAction(this, false);
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public void commitNowAllowingStateLoss() {
        this.disallowAddToBackStack();
        this.mManager.execSingleAction(this, true);
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction detach(Fragment fragment0) {
        if(fragment0.mFragmentManager != null && fragment0.mFragmentManager != this.mManager) {
            throw new IllegalStateException("Cannot detach Fragment attached to a different FragmentManager. Fragment Fragment{5c385f04} (ed4e3edd-ec39-4fae-9637-7812d86c33ec) is already attached to a FragmentManager.");
        }
        return super.detach(fragment0);
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    void doAddOp(int v, Fragment fragment0, String s, int v1) {
        super.doAddOp(v, fragment0, s, v1);
        fragment0.mFragmentManager = this.mManager;
    }

    public void dump(String s, PrintWriter printWriter0) {
        this.dump(s, printWriter0, true);
    }

    public void dump(String s, PrintWriter printWriter0, boolean z) {
        String s1;
        if(z) {
            printWriter0.print(s);
            printWriter0.print("mName=");
            printWriter0.print(this.mName);
            printWriter0.print(" mIndex=");
            printWriter0.print(this.mIndex);
            printWriter0.print(" mCommitted=");
            printWriter0.println(this.mCommitted);
            if(this.mTransition != 0) {
                printWriter0.print(s);
                printWriter0.print("mTransition=#");
                printWriter0.print(Integer.toHexString(this.mTransition));
            }
            if(this.mEnterAnim != 0 || this.mExitAnim != 0) {
                printWriter0.print(s);
                printWriter0.print("mEnterAnim=#");
                printWriter0.print(Integer.toHexString(this.mEnterAnim));
                printWriter0.print(" mExitAnim=#");
                printWriter0.println(Integer.toHexString(this.mExitAnim));
            }
            if(this.mPopEnterAnim != 0 || this.mPopExitAnim != 0) {
                printWriter0.print(s);
                printWriter0.print("mPopEnterAnim=#");
                printWriter0.print(Integer.toHexString(this.mPopEnterAnim));
                printWriter0.print(" mPopExitAnim=#");
                printWriter0.println(Integer.toHexString(this.mPopExitAnim));
            }
            if(this.mBreadCrumbTitleRes != 0 || this.mBreadCrumbTitleText != null) {
                printWriter0.print(s);
                printWriter0.print("mBreadCrumbTitleRes=#");
                printWriter0.print(Integer.toHexString(this.mBreadCrumbTitleRes));
                printWriter0.print(" mBreadCrumbTitleText=");
                printWriter0.println(this.mBreadCrumbTitleText);
            }
            if(this.mBreadCrumbShortTitleRes != 0 || this.mBreadCrumbShortTitleText != null) {
                printWriter0.print(s);
                printWriter0.print("mBreadCrumbShortTitleRes=#");
                printWriter0.print(Integer.toHexString(this.mBreadCrumbShortTitleRes));
                printWriter0.print(" mBreadCrumbShortTitleText=");
                printWriter0.println(this.mBreadCrumbShortTitleText);
            }
        }
        if(!this.mOps.isEmpty()) {
            printWriter0.print(s);
            printWriter0.println("Operations:");
            int v = this.mOps.size();
            for(int v1 = 0; v1 < v; ++v1) {
                Op fragmentTransaction$Op0 = (Op)this.mOps.get(v1);
                switch(fragmentTransaction$Op0.mCmd) {
                    case 0: {
                        s1 = "NULL";
                        break;
                    }
                    case 1: {
                        s1 = "ADD";
                        break;
                    }
                    case 2: {
                        s1 = "REPLACE";
                        break;
                    }
                    case 3: {
                        s1 = "REMOVE";
                        break;
                    }
                    case 4: {
                        s1 = "HIDE";
                        break;
                    }
                    case 5: {
                        s1 = "SHOW";
                        break;
                    }
                    case 6: {
                        s1 = "DETACH";
                        break;
                    }
                    case 7: {
                        s1 = "ATTACH";
                        break;
                    }
                    case 8: {
                        s1 = "SET_PRIMARY_NAV";
                        break;
                    }
                    case 9: {
                        s1 = "UNSET_PRIMARY_NAV";
                        break;
                    }
                    case 10: {
                        s1 = "OP_SET_MAX_LIFECYCLE";
                        break;
                    }
                    default: {
                        s1 = "cmd=" + fragmentTransaction$Op0.mCmd;
                    }
                }
                printWriter0.print(s);
                printWriter0.print("  Op #");
                printWriter0.print(v1);
                printWriter0.print(": ");
                printWriter0.print(s1);
                printWriter0.print(" ");
                printWriter0.println(fragmentTransaction$Op0.mFragment);
                if(z) {
                    if(fragmentTransaction$Op0.mEnterAnim != 0 || fragmentTransaction$Op0.mExitAnim != 0) {
                        printWriter0.print(s);
                        printWriter0.print("enterAnim=#");
                        printWriter0.print(Integer.toHexString(fragmentTransaction$Op0.mEnterAnim));
                        printWriter0.print(" exitAnim=#");
                        printWriter0.println(Integer.toHexString(fragmentTransaction$Op0.mExitAnim));
                    }
                    if(fragmentTransaction$Op0.mPopEnterAnim != 0 || fragmentTransaction$Op0.mPopExitAnim != 0) {
                        printWriter0.print(s);
                        printWriter0.print("popEnterAnim=#");
                        printWriter0.print(Integer.toHexString(fragmentTransaction$Op0.mPopEnterAnim));
                        printWriter0.print(" popExitAnim=#");
                        printWriter0.println(Integer.toHexString(fragmentTransaction$Op0.mPopExitAnim));
                    }
                }
            }
        }
    }

    void executeOps() {
        int v = this.mOps.size();
        for(int v1 = 0; v1 < v; ++v1) {
            Op fragmentTransaction$Op0 = (Op)this.mOps.get(v1);
            Fragment fragment0 = fragmentTransaction$Op0.mFragment;
            if(fragment0 != null) {
                fragment0.mBeingSaved = this.mBeingSaved;
                fragment0.setPopDirection(false);
                fragment0.setNextTransition(this.mTransition);
                fragment0.setSharedElementNames(this.mSharedElementSourceNames, this.mSharedElementTargetNames);
            }
            switch(fragmentTransaction$Op0.mCmd) {
                case 1: {
                    fragment0.setAnimations(fragmentTransaction$Op0.mEnterAnim, fragmentTransaction$Op0.mExitAnim, fragmentTransaction$Op0.mPopEnterAnim, fragmentTransaction$Op0.mPopExitAnim);
                    this.mManager.setExitAnimationOrder(fragment0, false);
                    this.mManager.addFragment(fragment0);
                    break;
                }
                case 3: {
                    fragment0.setAnimations(fragmentTransaction$Op0.mEnterAnim, fragmentTransaction$Op0.mExitAnim, fragmentTransaction$Op0.mPopEnterAnim, fragmentTransaction$Op0.mPopExitAnim);
                    this.mManager.removeFragment(fragment0);
                    break;
                }
                case 4: {
                    fragment0.setAnimations(fragmentTransaction$Op0.mEnterAnim, fragmentTransaction$Op0.mExitAnim, fragmentTransaction$Op0.mPopEnterAnim, fragmentTransaction$Op0.mPopExitAnim);
                    this.mManager.hideFragment(fragment0);
                    break;
                }
                case 5: {
                    fragment0.setAnimations(fragmentTransaction$Op0.mEnterAnim, fragmentTransaction$Op0.mExitAnim, fragmentTransaction$Op0.mPopEnterAnim, fragmentTransaction$Op0.mPopExitAnim);
                    this.mManager.setExitAnimationOrder(fragment0, false);
                    this.mManager.showFragment(fragment0);
                    break;
                }
                case 6: {
                    fragment0.setAnimations(fragmentTransaction$Op0.mEnterAnim, fragmentTransaction$Op0.mExitAnim, fragmentTransaction$Op0.mPopEnterAnim, fragmentTransaction$Op0.mPopExitAnim);
                    this.mManager.detachFragment(fragment0);
                    break;
                }
                case 7: {
                    fragment0.setAnimations(fragmentTransaction$Op0.mEnterAnim, fragmentTransaction$Op0.mExitAnim, fragmentTransaction$Op0.mPopEnterAnim, fragmentTransaction$Op0.mPopExitAnim);
                    this.mManager.setExitAnimationOrder(fragment0, false);
                    this.mManager.attachFragment(fragment0);
                    break;
                }
                case 8: {
                    this.mManager.setPrimaryNavigationFragment(fragment0);
                    break;
                }
                case 9: {
                    this.mManager.setPrimaryNavigationFragment(null);
                    break;
                }
                case 10: {
                    this.mManager.setMaxLifecycle(fragment0, fragmentTransaction$Op0.mCurrentMaxState);
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Unknown cmd: " + fragmentTransaction$Op0.mCmd);
                }
            }
        }
    }

    void executePopOps() {
        for(int v = this.mOps.size() - 1; v >= 0; --v) {
            Op fragmentTransaction$Op0 = (Op)this.mOps.get(v);
            Fragment fragment0 = fragmentTransaction$Op0.mFragment;
            if(fragment0 != null) {
                fragment0.mBeingSaved = this.mBeingSaved;
                fragment0.setPopDirection(true);
                fragment0.setNextTransition(FragmentManager.reverseTransit(this.mTransition));
                fragment0.setSharedElementNames(this.mSharedElementTargetNames, this.mSharedElementSourceNames);
            }
            switch(fragmentTransaction$Op0.mCmd) {
                case 1: {
                    fragment0.setAnimations(fragmentTransaction$Op0.mEnterAnim, fragmentTransaction$Op0.mExitAnim, fragmentTransaction$Op0.mPopEnterAnim, fragmentTransaction$Op0.mPopExitAnim);
                    this.mManager.setExitAnimationOrder(fragment0, true);
                    this.mManager.removeFragment(fragment0);
                    break;
                }
                case 3: {
                    fragment0.setAnimations(fragmentTransaction$Op0.mEnterAnim, fragmentTransaction$Op0.mExitAnim, fragmentTransaction$Op0.mPopEnterAnim, fragmentTransaction$Op0.mPopExitAnim);
                    this.mManager.addFragment(fragment0);
                    break;
                }
                case 4: {
                    fragment0.setAnimations(fragmentTransaction$Op0.mEnterAnim, fragmentTransaction$Op0.mExitAnim, fragmentTransaction$Op0.mPopEnterAnim, fragmentTransaction$Op0.mPopExitAnim);
                    this.mManager.showFragment(fragment0);
                    break;
                }
                case 5: {
                    fragment0.setAnimations(fragmentTransaction$Op0.mEnterAnim, fragmentTransaction$Op0.mExitAnim, fragmentTransaction$Op0.mPopEnterAnim, fragmentTransaction$Op0.mPopExitAnim);
                    this.mManager.setExitAnimationOrder(fragment0, true);
                    this.mManager.hideFragment(fragment0);
                    break;
                }
                case 6: {
                    fragment0.setAnimations(fragmentTransaction$Op0.mEnterAnim, fragmentTransaction$Op0.mExitAnim, fragmentTransaction$Op0.mPopEnterAnim, fragmentTransaction$Op0.mPopExitAnim);
                    this.mManager.attachFragment(fragment0);
                    break;
                }
                case 7: {
                    fragment0.setAnimations(fragmentTransaction$Op0.mEnterAnim, fragmentTransaction$Op0.mExitAnim, fragmentTransaction$Op0.mPopEnterAnim, fragmentTransaction$Op0.mPopExitAnim);
                    this.mManager.setExitAnimationOrder(fragment0, true);
                    this.mManager.detachFragment(fragment0);
                    break;
                }
                case 8: {
                    this.mManager.setPrimaryNavigationFragment(null);
                    break;
                }
                case 9: {
                    this.mManager.setPrimaryNavigationFragment(fragment0);
                    break;
                }
                case 10: {
                    this.mManager.setMaxLifecycle(fragment0, fragmentTransaction$Op0.mOldMaxState);
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Unknown cmd: " + fragmentTransaction$Op0.mCmd);
                }
            }
        }
    }

    Fragment expandOps(ArrayList arrayList0, Fragment fragment0) {
        for(int v = 0; v < this.mOps.size(); ++v) {
            Op fragmentTransaction$Op0 = (Op)this.mOps.get(v);
            int v1 = fragmentTransaction$Op0.mCmd;
            if(v1 == 1) {
                arrayList0.add(fragmentTransaction$Op0.mFragment);
            }
            else {
                switch(v1) {
                    case 2: {
                        Fragment fragment1 = fragmentTransaction$Op0.mFragment;
                        int v2 = fragment1.mContainerId;
                        int v3 = arrayList0.size() - 1;
                        boolean z = false;
                        while(v3 >= 0) {
                            Fragment fragment2 = (Fragment)arrayList0.get(v3);
                            if(fragment2.mContainerId == v2) {
                                if(fragment2 == fragment1) {
                                    z = true;
                                }
                                else {
                                    if(fragment2 == fragment0) {
                                        this.mOps.add(v, new Op(9, fragment2, true));
                                        ++v;
                                        fragment0 = null;
                                    }
                                    Op fragmentTransaction$Op1 = new Op(3, fragment2, true);
                                    fragmentTransaction$Op1.mEnterAnim = fragmentTransaction$Op0.mEnterAnim;
                                    fragmentTransaction$Op1.mPopEnterAnim = fragmentTransaction$Op0.mPopEnterAnim;
                                    fragmentTransaction$Op1.mExitAnim = fragmentTransaction$Op0.mExitAnim;
                                    fragmentTransaction$Op1.mPopExitAnim = fragmentTransaction$Op0.mPopExitAnim;
                                    this.mOps.add(v, fragmentTransaction$Op1);
                                    arrayList0.remove(fragment2);
                                    ++v;
                                }
                            }
                            --v3;
                        }
                        if(z) {
                            this.mOps.remove(v);
                            --v;
                        }
                        else {
                            fragmentTransaction$Op0.mCmd = 1;
                            fragmentTransaction$Op0.mFromExpandedOp = true;
                            arrayList0.add(fragment1);
                        }
                        break;
                    }
                    case 3: 
                    case 6: {
                        arrayList0.remove(fragmentTransaction$Op0.mFragment);
                        if(fragmentTransaction$Op0.mFragment == fragment0) {
                            this.mOps.add(v, new Op(9, fragmentTransaction$Op0.mFragment));
                            ++v;
                            fragment0 = null;
                        }
                        break;
                    }
                    case 7: {
                        arrayList0.add(fragmentTransaction$Op0.mFragment);
                        break;
                    }
                    case 8: {
                        this.mOps.add(v, new Op(9, fragment0, true));
                        fragmentTransaction$Op0.mFromExpandedOp = true;
                        ++v;
                        fragment0 = fragmentTransaction$Op0.mFragment;
                    }
                }
            }
        }
        return fragment0;
    }

    @Override  // androidx.fragment.app.FragmentManager$OpGenerator
    public boolean generateOps(ArrayList arrayList0, ArrayList arrayList1) {
        if(FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Run: " + this);
        }
        arrayList0.add(this);
        arrayList1.add(Boolean.FALSE);
        if(this.mAddToBackStack) {
            this.mManager.addBackStackState(this);
        }
        return true;
    }

    @Override  // androidx.fragment.app.FragmentManager$BackStackEntry
    public CharSequence getBreadCrumbShortTitle() {
        return this.mBreadCrumbShortTitleRes == 0 ? this.mBreadCrumbShortTitleText : this.mManager.getHost().getContext().getText(this.mBreadCrumbShortTitleRes);
    }

    @Override  // androidx.fragment.app.FragmentManager$BackStackEntry
    public int getBreadCrumbShortTitleRes() {
        return this.mBreadCrumbShortTitleRes;
    }

    @Override  // androidx.fragment.app.FragmentManager$BackStackEntry
    public CharSequence getBreadCrumbTitle() {
        return this.mBreadCrumbTitleRes == 0 ? this.mBreadCrumbTitleText : this.mManager.getHost().getContext().getText(this.mBreadCrumbTitleRes);
    }

    @Override  // androidx.fragment.app.FragmentManager$BackStackEntry
    public int getBreadCrumbTitleRes() {
        return this.mBreadCrumbTitleRes;
    }

    @Override  // androidx.fragment.app.FragmentManager$BackStackEntry
    public int getId() {
        return this.mIndex;
    }

    @Override  // androidx.fragment.app.FragmentManager$BackStackEntry
    public String getName() {
        return this.mName;
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction hide(Fragment fragment0) {
        if(fragment0.mFragmentManager != null && fragment0.mFragmentManager != this.mManager) {
            throw new IllegalStateException("Cannot hide Fragment attached to a different FragmentManager. Fragment Fragment{ad829e2} (c0d5d7b5-10e0-44c5-8dee-8b7079c8bee8) is already attached to a FragmentManager.");
        }
        return super.hide(fragment0);
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public boolean isEmpty() {
        return this.mOps.isEmpty();
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction remove(Fragment fragment0) {
        if(fragment0.mFragmentManager != null && fragment0.mFragmentManager != this.mManager) {
            throw new IllegalStateException("Cannot remove Fragment attached to a different FragmentManager. Fragment Fragment{3544466c} (ac637eff-e0aa-4ceb-bb97-5931541ec344) is already attached to a FragmentManager.");
        }
        return super.remove(fragment0);
    }

    public void runOnCommitRunnables() {
        if(this.mCommitRunnables != null) {
            for(int v = 0; v < this.mCommitRunnables.size(); ++v) {
                ((Runnable)this.mCommitRunnables.get(v)).run();
            }
            this.mCommitRunnables = null;
        }
    }

    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction setMaxLifecycle(Fragment fragment0, State lifecycle$State0) {
        if(fragment0.mFragmentManager != this.mManager) {
            throw new IllegalArgumentException("Cannot setMaxLifecycle for Fragment not attached to FragmentManager " + this.mManager);
        }
        if(lifecycle$State0 == State.INITIALIZED && fragment0.mState > -1) {
            throw new IllegalArgumentException("Cannot set maximum Lifecycle to " + lifecycle$State0 + " after the Fragment has been created");
        }
        if(lifecycle$State0 == State.DESTROYED) {
            throw new IllegalArgumentException("Cannot set maximum Lifecycle to " + lifecycle$State0 + ". Use remove() to remove the fragment from the FragmentManager and trigger its destruction.");
        }
        return super.setMaxLifecycle(fragment0, lifecycle$State0);
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction setPrimaryNavigationFragment(Fragment fragment0) {
        if(fragment0 != null && fragment0.mFragmentManager != null && fragment0.mFragmentManager != this.mManager) {
            throw new IllegalStateException("Cannot setPrimaryNavigation for Fragment attached to a different FragmentManager. Fragment Fragment{7ed8d252} (70013fb7-dcf2-4135-91c7-c9a011a031de) is already attached to a FragmentManager.");
        }
        return super.setPrimaryNavigationFragment(fragment0);
    }

    // 去混淆评级： 低(20)
    @Override  // androidx.fragment.app.FragmentTransaction
    public FragmentTransaction show(Fragment fragment0) {
        if(fragment0.mFragmentManager != null && fragment0.mFragmentManager != this.mManager) {
            throw new IllegalStateException("Cannot show Fragment attached to a different FragmentManager. Fragment Fragment{59709058} (52132767-3e4a-4aa7-84c2-6a141a64307c) is already attached to a FragmentManager.");
        }
        return super.show(fragment0);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder(0x80);
        stringBuilder0.append("BackStackEntry{");
        stringBuilder0.append(Integer.toHexString(System.identityHashCode(this)));
        if(this.mIndex >= 0) {
            stringBuilder0.append(" #");
            stringBuilder0.append(this.mIndex);
        }
        if(this.mName != null) {
            stringBuilder0.append(" ");
            stringBuilder0.append(this.mName);
        }
        stringBuilder0.append("}");
        return stringBuilder0.toString();
    }

    Fragment trackAddedFragmentsInPop(ArrayList arrayList0, Fragment fragment0) {
        for(int v = this.mOps.size() - 1; v >= 0; --v) {
            Op fragmentTransaction$Op0 = (Op)this.mOps.get(v);
            switch(fragmentTransaction$Op0.mCmd) {
                case 3: 
                case 6: {
                    arrayList0.add(fragmentTransaction$Op0.mFragment);
                    break;
                }
                case 1: 
                case 7: {
                    arrayList0.remove(fragmentTransaction$Op0.mFragment);
                    break;
                }
                case 8: {
                    fragment0 = null;
                    break;
                }
                case 9: {
                    fragment0 = fragmentTransaction$Op0.mFragment;
                    break;
                }
                case 10: {
                    fragmentTransaction$Op0.mCurrentMaxState = fragmentTransaction$Op0.mOldMaxState;
                }
            }
        }
        return fragment0;
    }
}

