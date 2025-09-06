package androidx.fragment.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle.State;
import androidx.viewpager.widget.PagerAdapter;
import java.util.ArrayList;

@Deprecated
public abstract class FragmentStatePagerAdapter extends PagerAdapter {
    public static final int BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT = 1;
    @Deprecated
    public static final int BEHAVIOR_SET_USER_VISIBLE_HINT = 0;
    private static final boolean DEBUG = false;
    private static final String TAG = "FragmentStatePagerAdapt";
    private final int mBehavior;
    private FragmentTransaction mCurTransaction;
    private Fragment mCurrentPrimaryItem;
    private boolean mExecutingFinishUpdate;
    private final FragmentManager mFragmentManager;
    private ArrayList mFragments;
    private ArrayList mSavedState;

    @Deprecated
    public FragmentStatePagerAdapter(FragmentManager fragmentManager0) {
        this(fragmentManager0, 0);
    }

    public FragmentStatePagerAdapter(FragmentManager fragmentManager0, int v) {
        this.mCurTransaction = null;
        this.mSavedState = new ArrayList();
        this.mFragments = new ArrayList();
        this.mCurrentPrimaryItem = null;
        this.mFragmentManager = fragmentManager0;
        this.mBehavior = v;
    }

    @Override  // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup0, int v, Object object0) {
        if(this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }
        while(this.mSavedState.size() <= v) {
            this.mSavedState.add(null);
        }
        this.mSavedState.set(v, (((Fragment)object0).isAdded() ? this.mFragmentManager.saveFragmentInstanceState(((Fragment)object0)) : null));
        this.mFragments.set(v, null);
        this.mCurTransaction.remove(((Fragment)object0));
        if(((Fragment)object0).equals(this.mCurrentPrimaryItem)) {
            this.mCurrentPrimaryItem = null;
        }
    }

    @Override  // androidx.viewpager.widget.PagerAdapter
    public void finishUpdate(ViewGroup viewGroup0) {
        FragmentTransaction fragmentTransaction0 = this.mCurTransaction;
        if(fragmentTransaction0 != null) {
            if(!this.mExecutingFinishUpdate) {
                try {
                    this.mExecutingFinishUpdate = true;
                    fragmentTransaction0.commitNowAllowingStateLoss();
                    this.mExecutingFinishUpdate = false;
                }
                catch(Throwable throwable0) {
                    this.mExecutingFinishUpdate = false;
                    throw throwable0;
                }
            }
            this.mCurTransaction = null;
        }
    }

    public abstract Fragment getItem(int arg1);

    @Override  // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup0, int v) {
        if(this.mFragments.size() > v) {
            Fragment fragment0 = (Fragment)this.mFragments.get(v);
            if(fragment0 != null) {
                return fragment0;
            }
        }
        if(this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }
        Fragment fragment1 = this.getItem(v);
        if(this.mSavedState.size() > v) {
            SavedState fragment$SavedState0 = (SavedState)this.mSavedState.get(v);
            if(fragment$SavedState0 != null) {
                fragment1.setInitialSavedState(fragment$SavedState0);
            }
        }
        while(this.mFragments.size() <= v) {
            this.mFragments.add(null);
        }
        fragment1.setMenuVisibility(false);
        if(this.mBehavior == 0) {
            fragment1.setUserVisibleHint(false);
        }
        this.mFragments.set(v, fragment1);
        this.mCurTransaction.add(viewGroup0.getId(), fragment1);
        if(this.mBehavior == 1) {
            this.mCurTransaction.setMaxLifecycle(fragment1, State.STARTED);
        }
        return fragment1;
    }

    @Override  // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view0, Object object0) {
        return ((Fragment)object0).getView() == view0;
    }

    @Override  // androidx.viewpager.widget.PagerAdapter
    public void restoreState(Parcelable parcelable0, ClassLoader classLoader0) {
        if(parcelable0 != null) {
            ((Bundle)parcelable0).setClassLoader(classLoader0);
            Parcelable[] arr_parcelable = ((Bundle)parcelable0).getParcelableArray("states");
            this.mSavedState.clear();
            this.mFragments.clear();
            if(arr_parcelable != null) {
                for(int v = 0; v < arr_parcelable.length; ++v) {
                    this.mSavedState.add(((SavedState)arr_parcelable[v]));
                }
            }
            for(Object object0: ((Bundle)parcelable0).keySet()) {
                String s = (String)object0;
                if(s.startsWith("f")) {
                    int v1 = Integer.parseInt(s.substring(1));
                    Fragment fragment0 = this.mFragmentManager.getFragment(((Bundle)parcelable0), s);
                    if(fragment0 == null) {
                        Log.w("FragmentStatePagerAdapt", "Bad fragment at key " + s);
                    }
                    else {
                        while(this.mFragments.size() <= v1) {
                            this.mFragments.add(null);
                        }
                        fragment0.setMenuVisibility(false);
                        this.mFragments.set(v1, fragment0);
                    }
                }
            }
        }
    }

    @Override  // androidx.viewpager.widget.PagerAdapter
    public Parcelable saveState() {
        Parcelable parcelable0;
        if(this.mSavedState.size() > 0) {
            parcelable0 = new Bundle();
            SavedState[] arr_fragment$SavedState = new SavedState[this.mSavedState.size()];
            this.mSavedState.toArray(arr_fragment$SavedState);
            ((Bundle)parcelable0).putParcelableArray("states", arr_fragment$SavedState);
        }
        else {
            parcelable0 = null;
        }
        for(int v = 0; v < this.mFragments.size(); ++v) {
            Fragment fragment0 = (Fragment)this.mFragments.get(v);
            if(fragment0 != null && fragment0.isAdded()) {
                if(parcelable0 == null) {
                    parcelable0 = new Bundle();
                }
                this.mFragmentManager.putFragment(((Bundle)parcelable0), "f" + v, fragment0);
            }
        }
        return parcelable0;
    }

    @Override  // androidx.viewpager.widget.PagerAdapter
    public void setPrimaryItem(ViewGroup viewGroup0, int v, Object object0) {
        Fragment fragment0 = this.mCurrentPrimaryItem;
        if(((Fragment)object0) != fragment0) {
            if(fragment0 != null) {
                fragment0.setMenuVisibility(false);
                if(this.mBehavior == 1) {
                    if(this.mCurTransaction == null) {
                        this.mCurTransaction = this.mFragmentManager.beginTransaction();
                    }
                    this.mCurTransaction.setMaxLifecycle(this.mCurrentPrimaryItem, State.STARTED);
                }
                else {
                    this.mCurrentPrimaryItem.setUserVisibleHint(false);
                }
            }
            ((Fragment)object0).setMenuVisibility(true);
            if(this.mBehavior == 1) {
                if(this.mCurTransaction == null) {
                    this.mCurTransaction = this.mFragmentManager.beginTransaction();
                }
                this.mCurTransaction.setMaxLifecycle(((Fragment)object0), State.RESUMED);
            }
            else {
                ((Fragment)object0).setUserVisibleHint(true);
            }
            this.mCurrentPrimaryItem = (Fragment)object0;
        }
    }

    @Override  // androidx.viewpager.widget.PagerAdapter
    public void startUpdate(ViewGroup viewGroup0) {
        if(viewGroup0.getId() == -1) {
            throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
        }
    }
}

