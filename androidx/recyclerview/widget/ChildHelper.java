package androidx.recyclerview.widget;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;
import java.util.List;

class ChildHelper {
    static class Bucket {
        static final int BITS_PER_WORD = 0x40;
        static final long LAST_BIT = 0x8000000000000000L;
        long mData;
        Bucket mNext;

        Bucket() {
            this.mData = 0L;
        }

        void clear(int v) {
            if(v >= 0x40) {
                Bucket childHelper$Bucket0 = this.mNext;
                if(childHelper$Bucket0 != null) {
                    childHelper$Bucket0.clear(v - 0x40);
                }
                return;
            }
            this.mData &= ~(1L << v);
        }

        int countOnesBefore(int v) {
            Bucket childHelper$Bucket0 = this.mNext;
            if(childHelper$Bucket0 == null) {
                return v < 0x40 ? Long.bitCount(this.mData & (1L << v) - 1L) : Long.bitCount(this.mData);
            }
            return v >= 0x40 ? childHelper$Bucket0.countOnesBefore(v - 0x40) + Long.bitCount(this.mData) : Long.bitCount(this.mData & (1L << v) - 1L);
        }

        private void ensureNext() {
            if(this.mNext == null) {
                this.mNext = new Bucket();
            }
        }

        boolean get(int v) {
            if(v >= 0x40) {
                this.ensureNext();
                return this.mNext.get(v - 0x40);
            }
            return (this.mData & 1L << v) != 0L;
        }

        void insert(int v, boolean z) {
            if(v >= 0x40) {
                this.ensureNext();
                this.mNext.insert(v - 0x40, z);
                return;
            }
            boolean z1 = (0x8000000000000000L & this.mData) != 0L;
            long v1 = (1L << v) - 1L;
            this.mData = (this.mData & ~v1) << 1 | this.mData & v1;
            if(z) {
                this.set(v);
            }
            else {
                this.clear(v);
            }
            if(!z1 && this.mNext == null) {
                return;
            }
            this.ensureNext();
            this.mNext.insert(0, z1);
        }

        boolean remove(int v) {
            if(v >= 0x40) {
                this.ensureNext();
                return this.mNext.remove(v - 0x40);
            }
            long v1 = this.mData;
            long v2 = v1 & ~(1L << v);
            long v3 = (1L << v) - 1L;
            this.mData = v2 & v3 | Long.rotateRight(~v3 & v2, 1);
            Bucket childHelper$Bucket0 = this.mNext;
            if(childHelper$Bucket0 != null) {
                if(childHelper$Bucket0.get(0)) {
                    this.set(0x3F);
                }
                this.mNext.remove(0);
            }
            return (v1 & 1L << v) != 0L;
        }

        void reset() {
            this.mData = 0L;
            Bucket childHelper$Bucket0 = this.mNext;
            if(childHelper$Bucket0 != null) {
                childHelper$Bucket0.reset();
            }
        }

        void set(int v) {
            if(v >= 0x40) {
                this.ensureNext();
                this.mNext.set(v - 0x40);
                return;
            }
            this.mData |= 1L << v;
        }

        // 去混淆评级： 中等(50)
        @Override
        public String toString() [...] // 潜在的解密器
    }

    interface Callback {
        void addView(View arg1, int arg2);

        void attachViewToParent(View arg1, int arg2, ViewGroup.LayoutParams arg3);

        void detachViewFromParent(int arg1);

        View getChildAt(int arg1);

        int getChildCount();

        ViewHolder getChildViewHolder(View arg1);

        int indexOfChild(View arg1);

        void onEnteredHiddenState(View arg1);

        void onLeftHiddenState(View arg1);

        void removeAllViews();

        void removeViewAt(int arg1);
    }

    private static final boolean DEBUG = false;
    private static final int REMOVE_STATUS_IN_REMOVE = 1;
    private static final int REMOVE_STATUS_IN_REMOVE_IF_HIDDEN = 2;
    private static final int REMOVE_STATUS_NONE = 0;
    private static final String TAG = "ChildrenHelper";
    final Bucket mBucket;
    final Callback mCallback;
    final List mHiddenViews;
    private int mRemoveStatus;
    private View mViewInRemoveView;

    ChildHelper(Callback childHelper$Callback0) {
        this.mRemoveStatus = 0;
        this.mCallback = childHelper$Callback0;
        this.mBucket = new Bucket();
        this.mHiddenViews = new ArrayList();
    }

    void addView(View view0, int v, boolean z) {
        int v1 = v >= 0 ? this.getOffset(v) : this.mCallback.getChildCount();
        this.mBucket.insert(v1, z);
        if(z) {
            this.hideViewInternal(view0);
        }
        this.mCallback.addView(view0, v1);
    }

    void addView(View view0, boolean z) {
        this.addView(view0, -1, z);
    }

    void attachViewToParent(View view0, int v, ViewGroup.LayoutParams viewGroup$LayoutParams0, boolean z) {
        int v1 = v >= 0 ? this.getOffset(v) : this.mCallback.getChildCount();
        this.mBucket.insert(v1, z);
        if(z) {
            this.hideViewInternal(view0);
        }
        this.mCallback.attachViewToParent(view0, v1, viewGroup$LayoutParams0);
    }

    void detachViewFromParent(int v) {
        int v1 = this.getOffset(v);
        this.mBucket.remove(v1);
        this.mCallback.detachViewFromParent(v1);
    }

    View findHiddenNonRemovedView(int v) {
        int v1 = this.mHiddenViews.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            View view0 = (View)this.mHiddenViews.get(v2);
            ViewHolder recyclerView$ViewHolder0 = this.mCallback.getChildViewHolder(view0);
            if(recyclerView$ViewHolder0.getLayoutPosition() == v && !recyclerView$ViewHolder0.isInvalid() && !recyclerView$ViewHolder0.isRemoved()) {
                return view0;
            }
        }
        return null;
    }

    View getChildAt(int v) {
        int v1 = this.getOffset(v);
        return this.mCallback.getChildAt(v1);
    }

    int getChildCount() {
        return this.mCallback.getChildCount() - this.mHiddenViews.size();
    }

    private int getOffset(int v) {
        if(v < 0) {
            return -1;
        }
        int v1 = this.mCallback.getChildCount();
        for(int v2 = v; v2 < v1; v2 += v3) {
            int v3 = v - (v2 - this.mBucket.countOnesBefore(v2));
            if(v3 == 0) {
                while(this.mBucket.get(v2)) {
                    ++v2;
                }
                return v2;
            }
        }
        return -1;
    }

    View getUnfilteredChildAt(int v) {
        return this.mCallback.getChildAt(v);
    }

    int getUnfilteredChildCount() {
        return this.mCallback.getChildCount();
    }

    void hide(View view0) {
        int v = this.mCallback.indexOfChild(view0);
        if(v < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view0);
        }
        this.mBucket.set(v);
        this.hideViewInternal(view0);
    }

    private void hideViewInternal(View view0) {
        this.mHiddenViews.add(view0);
        this.mCallback.onEnteredHiddenState(view0);
    }

    int indexOfChild(View view0) {
        int v = this.mCallback.indexOfChild(view0);
        if(v == -1) {
            return -1;
        }
        return this.mBucket.get(v) ? -1 : v - this.mBucket.countOnesBefore(v);
    }

    boolean isHidden(View view0) {
        return this.mHiddenViews.contains(view0);
    }

    void removeAllViewsUnfiltered() {
        this.mBucket.reset();
        for(int v = this.mHiddenViews.size() - 1; v >= 0; --v) {
            View view0 = (View)this.mHiddenViews.get(v);
            this.mCallback.onLeftHiddenState(view0);
            this.mHiddenViews.remove(v);
        }
        this.mCallback.removeAllViews();
    }

    void removeView(View view0) {
        switch(this.mRemoveStatus) {
            case 1: {
                throw new IllegalStateException("Cannot call removeView(At) within removeView(At)");
            }
            case 2: {
                throw new IllegalStateException("Cannot call removeView(At) within removeViewIfHidden");
            }
            default: {
                try {
                    this.mRemoveStatus = 1;
                    this.mViewInRemoveView = view0;
                    int v1 = this.mCallback.indexOfChild(view0);
                    if(v1 >= 0) {
                        if(this.mBucket.remove(v1)) {
                            this.unhideViewInternal(view0);
                        }
                        this.mCallback.removeViewAt(v1);
                    }
                }
                finally {
                    this.mRemoveStatus = 0;
                    this.mViewInRemoveView = null;
                }
            }
        }
    }

    void removeViewAt(int v) {
        switch(this.mRemoveStatus) {
            case 1: {
                throw new IllegalStateException("Cannot call removeView(At) within removeView(At)");
            }
            case 2: {
                throw new IllegalStateException("Cannot call removeView(At) within removeViewIfHidden");
            }
            default: {
                try {
                    int v2 = this.getOffset(v);
                    View view0 = this.mCallback.getChildAt(v2);
                    if(view0 != null) {
                        this.mRemoveStatus = 1;
                        this.mViewInRemoveView = view0;
                        if(this.mBucket.remove(v2)) {
                            this.unhideViewInternal(view0);
                        }
                        this.mCallback.removeViewAt(v2);
                    }
                }
                finally {
                    this.mRemoveStatus = 0;
                    this.mViewInRemoveView = null;
                }
            }
        }
    }

    boolean removeViewIfHidden(View view0) {
        int v = this.mRemoveStatus;
        if(v == 1) {
            if(this.mViewInRemoveView != view0) {
                throw new IllegalStateException("Cannot call removeViewIfHidden within removeView(At) for a different view");
            }
            return false;
        }
        if(v != 2) {
            try {
                this.mRemoveStatus = 2;
                int v1 = this.mCallback.indexOfChild(view0);
                if(v1 == -1) {
                    this.unhideViewInternal(view0);
                    this.mRemoveStatus = 0;
                    return true;
                }
                if(this.mBucket.get(v1)) {
                    this.mBucket.remove(v1);
                    this.unhideViewInternal(view0);
                    this.mCallback.removeViewAt(v1);
                    this.mRemoveStatus = 0;
                    return true;
                }
                this.mRemoveStatus = 0;
                return false;
            }
            catch(Throwable throwable0) {
                this.mRemoveStatus = 0;
                throw throwable0;
            }
        }
        throw new IllegalStateException("Cannot call removeViewIfHidden within removeViewIfHidden");
    }

    // 去混淆评级： 低(40)
    @Override
    public String toString() {
        return "0, hidden list:" + this.mHiddenViews.size();
    }

    void unhide(View view0) {
        int v = this.mCallback.indexOfChild(view0);
        if(v < 0) {
            throw new IllegalArgumentException("view is not a child, cannot hide " + view0);
        }
        if(!this.mBucket.get(v)) {
            throw new RuntimeException("trying to unhide a view that was not hidden" + view0);
        }
        this.mBucket.clear(v);
        this.unhideViewInternal(view0);
    }

    private boolean unhideViewInternal(View view0) {
        if(this.mHiddenViews.remove(view0)) {
            this.mCallback.onLeftHiddenState(view0);
            return true;
        }
        return false;
    }
}

