package androidx.recyclerview.widget;

import android.view.View;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

class ViewBoundsCheck {
    static class BoundFlags {
        int mBoundFlags;
        int mChildEnd;
        int mChildStart;
        int mRvEnd;
        int mRvStart;

        BoundFlags() {
            this.mBoundFlags = 0;
        }

        void addFlags(int v) {
            this.mBoundFlags |= v;
        }

        boolean boundsMatch() {
            if((this.mBoundFlags & 7) != 0 && (this.mBoundFlags & this.compare(this.mChildStart, this.mRvStart)) == 0) {
                return false;
            }
            if((this.mBoundFlags & 0x70) != 0 && (this.mBoundFlags & this.compare(this.mChildStart, this.mRvEnd) << 4) == 0) {
                return false;
            }
            return (this.mBoundFlags & 0x700) == 0 || (this.mBoundFlags & this.compare(this.mChildEnd, this.mRvStart) << 8) != 0 ? (this.mBoundFlags & 0x7000) == 0 || (this.mBoundFlags & this.compare(this.mChildEnd, this.mRvEnd) << 12) != 0 : false;
        }

        int compare(int v, int v1) {
            if(v > v1) {
                return 1;
            }
            return v == v1 ? 2 : 4;
        }

        void resetFlags() {
            this.mBoundFlags = 0;
        }

        void setBounds(int v, int v1, int v2, int v3) {
            this.mRvStart = v;
            this.mRvEnd = v1;
            this.mChildStart = v2;
            this.mChildEnd = v3;
        }
    }

    interface Callback {
        View getChildAt(int arg1);

        int getChildEnd(View arg1);

        int getChildStart(View arg1);

        int getParentEnd();

        int getParentStart();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ViewBounds {
    }

    static final int CVE_PVE_POS = 12;
    static final int CVE_PVS_POS = 8;
    static final int CVS_PVE_POS = 4;
    static final int CVS_PVS_POS = 0;
    static final int EQ = 2;
    static final int FLAG_CVE_EQ_PVE = 0x2000;
    static final int FLAG_CVE_EQ_PVS = 0x200;
    static final int FLAG_CVE_GT_PVE = 0x1000;
    static final int FLAG_CVE_GT_PVS = 0x100;
    static final int FLAG_CVE_LT_PVE = 0x4000;
    static final int FLAG_CVE_LT_PVS = 0x400;
    static final int FLAG_CVS_EQ_PVE = 0x20;
    static final int FLAG_CVS_EQ_PVS = 2;
    static final int FLAG_CVS_GT_PVE = 16;
    static final int FLAG_CVS_GT_PVS = 1;
    static final int FLAG_CVS_LT_PVE = 0x40;
    static final int FLAG_CVS_LT_PVS = 4;
    static final int GT = 1;
    static final int LT = 4;
    static final int MASK = 7;
    BoundFlags mBoundFlags;
    final Callback mCallback;

    ViewBoundsCheck(Callback viewBoundsCheck$Callback0) {
        this.mCallback = viewBoundsCheck$Callback0;
        this.mBoundFlags = new BoundFlags();
    }

    View findOneViewWithinBoundFlags(int v, int v1, int v2, int v3) {
        int v4 = this.mCallback.getParentStart();
        int v5 = this.mCallback.getParentEnd();
        int v6 = v1 <= v ? -1 : 1;
        View view0 = null;
        while(v != v1) {
            View view1 = this.mCallback.getChildAt(v);
            int v7 = this.mCallback.getChildStart(view1);
            int v8 = this.mCallback.getChildEnd(view1);
            this.mBoundFlags.setBounds(v4, v5, v7, v8);
            if(v2 != 0) {
                this.mBoundFlags.resetFlags();
                this.mBoundFlags.addFlags(v2);
                if(this.mBoundFlags.boundsMatch()) {
                    return view1;
                }
            }
            if(v3 != 0) {
                this.mBoundFlags.resetFlags();
                this.mBoundFlags.addFlags(v3);
                if(this.mBoundFlags.boundsMatch()) {
                    view0 = view1;
                }
            }
            v += v6;
        }
        return view0;
    }

    boolean isViewWithinBoundFlags(View view0, int v) {
        this.mBoundFlags.setBounds(this.mCallback.getParentStart(), this.mCallback.getParentEnd(), this.mCallback.getChildStart(view0), this.mCallback.getChildEnd(view0));
        if(v != 0) {
            this.mBoundFlags.resetFlags();
            this.mBoundFlags.addFlags(v);
            return this.mBoundFlags.boundsMatch();
        }
        return false;
    }
}

