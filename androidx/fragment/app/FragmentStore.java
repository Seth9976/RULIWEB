package androidx.fragment.app;

import android.util.Log;
import android.view.ViewGroup;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class FragmentStore {
    private static final String TAG = "FragmentManager";
    private final HashMap mActive;
    private final ArrayList mAdded;
    private FragmentManagerViewModel mNonConfig;
    private final HashMap mSavedState;

    FragmentStore() {
        this.mAdded = new ArrayList();
        this.mActive = new HashMap();
        this.mSavedState = new HashMap();
    }

    void addFragment(Fragment fragment0) {
        if(this.mAdded.contains(fragment0)) {
            throw new IllegalStateException("Fragment already added: " + fragment0);
        }
        synchronized(this.mAdded) {
            this.mAdded.add(fragment0);
        }
        fragment0.mAdded = true;
    }

    void burpActive() {
        this.mActive.values().removeAll(Collections.singleton(null));
    }

    boolean containsActiveFragment(String s) {
        return this.mActive.get(s) != null;
    }

    void dispatchStateChange(int v) {
        for(Object object0: this.mActive.values()) {
            FragmentStateManager fragmentStateManager0 = (FragmentStateManager)object0;
            if(fragmentStateManager0 != null) {
                fragmentStateManager0.setFragmentManagerState(v);
            }
        }
    }

    void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
        if(!this.mActive.isEmpty()) {
            printWriter0.print(s);
            printWriter0.println("Active Fragments:");
            for(Object object0: this.mActive.values()) {
                FragmentStateManager fragmentStateManager0 = (FragmentStateManager)object0;
                printWriter0.print(s);
                if(fragmentStateManager0 == null) {
                    printWriter0.println("null");
                }
                else {
                    Fragment fragment0 = fragmentStateManager0.getFragment();
                    printWriter0.println(fragment0);
                    fragment0.dump(s + "    ", fileDescriptor0, printWriter0, arr_s);
                }
            }
        }
        int v = this.mAdded.size();
        if(v > 0) {
            printWriter0.print(s);
            printWriter0.println("Added Fragments:");
            for(int v1 = 0; v1 < v; ++v1) {
                Fragment fragment1 = (Fragment)this.mAdded.get(v1);
                printWriter0.print(s);
                printWriter0.print("  #");
                printWriter0.print(v1);
                printWriter0.print(": ");
                printWriter0.println("Fragment{326493a3} (f8bfaf8f-36d4-486b-9b7c-3289c4909058)");
            }
        }
    }

    Fragment findActiveFragment(String s) {
        FragmentStateManager fragmentStateManager0 = (FragmentStateManager)this.mActive.get(s);
        return fragmentStateManager0 == null ? null : fragmentStateManager0.getFragment();
    }

    Fragment findFragmentById(int v) {
        for(int v1 = this.mAdded.size() - 1; v1 >= 0; --v1) {
            Fragment fragment0 = (Fragment)this.mAdded.get(v1);
            if(fragment0 != null && fragment0.mFragmentId == v) {
                return fragment0;
            }
        }
        for(Object object0: this.mActive.values()) {
            FragmentStateManager fragmentStateManager0 = (FragmentStateManager)object0;
            if(fragmentStateManager0 != null) {
                Fragment fragment1 = fragmentStateManager0.getFragment();
                if(fragment1.mFragmentId == v) {
                    return fragment1;
                }
                if(false) {
                    break;
                }
            }
        }
        return null;
    }

    Fragment findFragmentByTag(String s) {
        if(s != null) {
            for(int v = this.mAdded.size() - 1; v >= 0; --v) {
                Fragment fragment0 = (Fragment)this.mAdded.get(v);
                if(fragment0 != null && s.equals(fragment0.mTag)) {
                    return fragment0;
                }
            }
        }
        if(s != null) {
            for(Object object0: this.mActive.values()) {
                FragmentStateManager fragmentStateManager0 = (FragmentStateManager)object0;
                if(fragmentStateManager0 != null) {
                    Fragment fragment1 = fragmentStateManager0.getFragment();
                    if(s.equals(fragment1.mTag)) {
                        return fragment1;
                    }
                    if(false) {
                        break;
                    }
                }
            }
        }
        return null;
    }

    Fragment findFragmentByWho(String s) {
        for(Object object0: this.mActive.values()) {
            FragmentStateManager fragmentStateManager0 = (FragmentStateManager)object0;
            if(fragmentStateManager0 != null) {
                Fragment fragment0 = fragmentStateManager0.getFragment().findFragmentByWho(s);
                if(fragment0 != null) {
                    return fragment0;
                }
                if(false) {
                    break;
                }
            }
        }
        return null;
    }

    int findFragmentIndexInContainer(Fragment fragment0) {
        ViewGroup viewGroup0 = fragment0.mContainer;
        if(viewGroup0 == null) {
            return -1;
        }
        int v = this.mAdded.indexOf(fragment0);
        for(int v1 = v - 1; v1 >= 0; --v1) {
            Fragment fragment1 = (Fragment)this.mAdded.get(v1);
            if(fragment1.mContainer == viewGroup0 && fragment1.mView != null) {
                return viewGroup0.indexOfChild(fragment1.mView) + 1;
            }
        }
        while(true) {
            ++v;
            if(v >= this.mAdded.size()) {
                break;
            }
            Fragment fragment2 = (Fragment)this.mAdded.get(v);
            if(fragment2.mContainer == viewGroup0 && fragment2.mView != null) {
                return viewGroup0.indexOfChild(fragment2.mView);
            }
        }
        return -1;
    }

    int getActiveFragmentCount() {
        return this.mActive.size();
    }

    List getActiveFragmentStateManagers() {
        List list0 = new ArrayList();
        for(Object object0: this.mActive.values()) {
            FragmentStateManager fragmentStateManager0 = (FragmentStateManager)object0;
            if(fragmentStateManager0 != null) {
                ((ArrayList)list0).add(fragmentStateManager0);
            }
        }
        return list0;
    }

    List getActiveFragments() {
        List list0 = new ArrayList();
        for(Object object0: this.mActive.values()) {
            FragmentStateManager fragmentStateManager0 = (FragmentStateManager)object0;
            if(fragmentStateManager0 == null) {
                ((ArrayList)list0).add(null);
            }
            else {
                ((ArrayList)list0).add(fragmentStateManager0.getFragment());
            }
        }
        return list0;
    }

    ArrayList getAllSavedState() {
        return new ArrayList(this.mSavedState.values());
    }

    FragmentStateManager getFragmentStateManager(String s) {
        return (FragmentStateManager)this.mActive.get(s);
    }

    List getFragments() {
        if(this.mAdded.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList0 = this.mAdded;
        return new ArrayList(this.mAdded);
    }

    FragmentManagerViewModel getNonConfig() {
        return this.mNonConfig;
    }

    FragmentState getSavedState(String s) {
        return (FragmentState)this.mSavedState.get(s);
    }

    void makeActive(FragmentStateManager fragmentStateManager0) {
        Fragment fragment0 = fragmentStateManager0.getFragment();
        if(!this.containsActiveFragment(fragment0.mWho)) {
            this.mActive.put(fragment0.mWho, fragmentStateManager0);
            if(fragment0.mRetainInstanceChangedWhileDetached) {
                if(fragment0.mRetainInstance) {
                    this.mNonConfig.addRetainedFragment(fragment0);
                }
                else {
                    this.mNonConfig.removeRetainedFragment(fragment0);
                }
                fragment0.mRetainInstanceChangedWhileDetached = false;
            }
            if(FragmentManager.isLoggingEnabled(2)) {
                Log.v("FragmentManager", "Added fragment to active set " + fragment0);
            }
        }
    }

    void makeInactive(FragmentStateManager fragmentStateManager0) {
        Fragment fragment0 = fragmentStateManager0.getFragment();
        if(fragment0.mRetainInstance) {
            this.mNonConfig.removeRetainedFragment(fragment0);
        }
        if(((FragmentStateManager)this.mActive.put(fragment0.mWho, null)) != null && FragmentManager.isLoggingEnabled(2)) {
            Log.v("FragmentManager", "Removed fragment from active set " + fragment0);
        }
    }

    void moveToExpectedState() {
        for(Object object0: this.mAdded) {
            FragmentStateManager fragmentStateManager0 = (FragmentStateManager)this.mActive.get(((Fragment)object0).mWho);
            if(fragmentStateManager0 != null) {
                fragmentStateManager0.moveToExpectedState();
            }
        }
        for(Object object1: this.mActive.values()) {
            FragmentStateManager fragmentStateManager1 = (FragmentStateManager)object1;
            if(fragmentStateManager1 != null) {
                fragmentStateManager1.moveToExpectedState();
                Fragment fragment0 = fragmentStateManager1.getFragment();
                if(fragment0.mRemoving && !fragment0.isInBackStack()) {
                    if(fragment0.mBeingSaved && !this.mSavedState.containsKey(fragment0.mWho)) {
                        fragmentStateManager1.saveState();
                    }
                    this.makeInactive(fragmentStateManager1);
                }
            }
        }
    }

    void removeFragment(Fragment fragment0) {
        synchronized(this.mAdded) {
            this.mAdded.remove(fragment0);
        }
        fragment0.mAdded = false;
    }

    void resetActiveFragments() {
        this.mActive.clear();
    }

    void restoreAddedFragments(List list0) {
        this.mAdded.clear();
        if(list0 != null) {
            for(Object object0: list0) {
                String s = (String)object0;
                Fragment fragment0 = this.findActiveFragment(s);
                if(fragment0 == null) {
                    throw new IllegalStateException("No instantiated fragment for (" + s + ")");
                }
                if(FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "restoreSaveState: added (" + s + "): " + fragment0);
                }
                this.addFragment(fragment0);
            }
        }
    }

    void restoreSaveState(ArrayList arrayList0) {
        this.mSavedState.clear();
        for(Object object0: arrayList0) {
            this.mSavedState.put(((FragmentState)object0).mWho, ((FragmentState)object0));
        }
    }

    ArrayList saveActiveFragments() {
        ArrayList arrayList0 = new ArrayList(this.mActive.size());
        for(Object object0: this.mActive.values()) {
            FragmentStateManager fragmentStateManager0 = (FragmentStateManager)object0;
            if(fragmentStateManager0 != null) {
                Fragment fragment0 = fragmentStateManager0.getFragment();
                fragmentStateManager0.saveState();
                arrayList0.add(fragment0.mWho);
                if(FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "Saved state of " + fragment0 + ": " + fragment0.mSavedFragmentState);
                }
            }
        }
        return arrayList0;
    }

    ArrayList saveAddedFragments() {
        synchronized(this.mAdded) {
            if(this.mAdded.isEmpty()) {
                return null;
            }
            ArrayList arrayList1 = new ArrayList(this.mAdded.size());
            for(Object object0: this.mAdded) {
                Fragment fragment0 = (Fragment)object0;
                arrayList1.add(fragment0.mWho);
                if(FragmentManager.isLoggingEnabled(2)) {
                    Log.v("FragmentManager", "saveAllState: adding fragment (" + fragment0.mWho + "): " + fragment0);
                }
            }
            return arrayList1;
        }
    }

    void setNonConfig(FragmentManagerViewModel fragmentManagerViewModel0) {
        this.mNonConfig = fragmentManagerViewModel0;
    }

    FragmentState setSavedState(String s, FragmentState fragmentState0) {
        return fragmentState0 == null ? ((FragmentState)this.mSavedState.remove(s)) : ((FragmentState)this.mSavedState.put(s, fragmentState0));
    }
}

