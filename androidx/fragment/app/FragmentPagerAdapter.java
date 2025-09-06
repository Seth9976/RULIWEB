package androidx.fragment.app;

import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Lifecycle.State;
import androidx.viewpager.widget.PagerAdapter;

@Deprecated
public abstract class FragmentPagerAdapter extends PagerAdapter {
    public static final int BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT = 1;
    @Deprecated
    public static final int BEHAVIOR_SET_USER_VISIBLE_HINT = 0;
    private static final boolean DEBUG = false;
    private static final String TAG = "FragmentPagerAdapter";
    private final int mBehavior;
    private FragmentTransaction mCurTransaction;
    private Fragment mCurrentPrimaryItem;
    private boolean mExecutingFinishUpdate;
    private final FragmentManager mFragmentManager;

    @Deprecated
    public FragmentPagerAdapter(FragmentManager fragmentManager0) {
        this(fragmentManager0, 0);
    }

    public FragmentPagerAdapter(FragmentManager fragmentManager0, int v) {
        this.mCurTransaction = null;
        this.mCurrentPrimaryItem = null;
        this.mFragmentManager = fragmentManager0;
        this.mBehavior = v;
    }

    @Override  // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup0, int v, Object object0) {
        if(this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }
        this.mCurTransaction.detach(((Fragment)object0));
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

    public long getItemId(int v) [...] // Inlined contents

    @Override  // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup0, int v) {
        if(this.mCurTransaction == null) {
            this.mCurTransaction = this.mFragmentManager.beginTransaction();
        }
        String s = FragmentPagerAdapter.makeFragmentName(viewGroup0.getId(), ((long)v));
        Fragment fragment0 = this.mFragmentManager.findFragmentByTag(s);
        if(fragment0 == null) {
            fragment0 = this.getItem(v);
            this.mCurTransaction.add(viewGroup0.getId(), fragment0, FragmentPagerAdapter.makeFragmentName(viewGroup0.getId(), ((long)v)));
        }
        else {
            this.mCurTransaction.attach(fragment0);
        }
        if(fragment0 != this.mCurrentPrimaryItem) {
            fragment0.setMenuVisibility(false);
            if(this.mBehavior == 1) {
                this.mCurTransaction.setMaxLifecycle(fragment0, State.STARTED);
                return fragment0;
            }
            fragment0.setUserVisibleHint(false);
        }
        return fragment0;
    }

    @Override  // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view0, Object object0) {
        return ((Fragment)object0).getView() == view0;
    }

    private static String makeFragmentName(int v, long v1) {
        return "android:switcher:" + v + ":" + v1;
    }

    @Override  // androidx.viewpager.widget.PagerAdapter
    public void restoreState(Parcelable parcelable0, ClassLoader classLoader0) {
    }

    @Override  // androidx.viewpager.widget.PagerAdapter
    public Parcelable saveState() {
        return null;
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

