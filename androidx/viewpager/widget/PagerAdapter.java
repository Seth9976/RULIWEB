package androidx.viewpager.widget;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;

public abstract class PagerAdapter {
    public static final int POSITION_NONE = -2;
    public static final int POSITION_UNCHANGED = -1;
    private final DataSetObservable mObservable;
    private DataSetObserver mViewPagerObserver;

    public PagerAdapter() {
        this.mObservable = new DataSetObservable();
    }

    @Deprecated
    public void destroyItem(View view0, int v, Object object0) {
        throw new UnsupportedOperationException("Required method destroyItem was not overridden");
    }

    public void destroyItem(ViewGroup viewGroup0, int v, Object object0) {
        this.destroyItem(viewGroup0, v, object0);
    }

    @Deprecated
    public void finishUpdate(View view0) {
    }

    public void finishUpdate(ViewGroup viewGroup0) {
    }

    public abstract int getCount();

    public int getItemPosition(Object object0) [...] // Inlined contents

    public CharSequence getPageTitle(int v) [...] // Inlined contents

    public float getPageWidth(int v) [...] // Inlined contents

    @Deprecated
    public Object instantiateItem(View view0, int v) {
        throw new UnsupportedOperationException("Required method instantiateItem was not overridden");
    }

    public Object instantiateItem(ViewGroup viewGroup0, int v) {
        return this.instantiateItem(viewGroup0, v);
    }

    public abstract boolean isViewFromObject(View arg1, Object arg2);

    public void notifyDataSetChanged() {
        synchronized(this) {
            DataSetObserver dataSetObserver0 = this.mViewPagerObserver;
            if(dataSetObserver0 != null) {
                dataSetObserver0.onChanged();
            }
        }
        this.mObservable.notifyChanged();
    }

    public void registerDataSetObserver(DataSetObserver dataSetObserver0) {
        this.mObservable.registerObserver(dataSetObserver0);
    }

    public void restoreState(Parcelable parcelable0, ClassLoader classLoader0) {
    }

    public Parcelable saveState() {
        return null;
    }

    @Deprecated
    public void setPrimaryItem(View view0, int v, Object object0) {
    }

    public void setPrimaryItem(ViewGroup viewGroup0, int v, Object object0) {
    }

    void setViewPagerObserver(DataSetObserver dataSetObserver0) {
        synchronized(this) {
            this.mViewPagerObserver = dataSetObserver0;
        }
    }

    @Deprecated
    public void startUpdate(View view0) {
    }

    public void startUpdate(ViewGroup viewGroup0) {
    }

    public void unregisterDataSetObserver(DataSetObserver dataSetObserver0) {
        this.mObservable.unregisterObserver(dataSetObserver0);
    }
}

