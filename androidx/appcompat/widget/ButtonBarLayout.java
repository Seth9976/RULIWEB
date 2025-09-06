package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import android.widget.LinearLayout;
import androidx.appcompat.R.id;
import androidx.appcompat.R.styleable;
import androidx.core.view.ViewCompat;

public class ButtonBarLayout extends LinearLayout {
    private static final int PEEK_BUTTON_DP = 16;
    private boolean mAllowStacking;
    private int mLastWidthSize;
    private boolean mStacked;

    public ButtonBarLayout(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mLastWidthSize = -1;
        TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.ButtonBarLayout);
        ViewCompat.saveAttributeDataForStyleable(this, context0, styleable.ButtonBarLayout, attributeSet0, typedArray0, 0, 0);
        this.mAllowStacking = typedArray0.getBoolean(styleable.ButtonBarLayout_allowStacking, true);
        typedArray0.recycle();
        if(this.getOrientation() == 1) {
            this.setStacked(this.mAllowStacking);
        }
    }

    private int getNextVisibleChildIndex(int v) {
        int v1 = this.getChildCount();
        while(v < v1) {
            if(this.getChildAt(v).getVisibility() == 0) {
                return v;
            }
            ++v;
        }
        return -1;
    }

    private boolean isStacked() {
        return this.mStacked;
    }

    @Override  // android.widget.LinearLayout
    protected void onMeasure(int v, int v1) {
        boolean z;
        int v4;
        int v2 = View.MeasureSpec.getSize(v);
        int v3 = 0;
        if(this.mAllowStacking) {
            if(v2 > this.mLastWidthSize && this.isStacked()) {
                this.setStacked(false);
            }
            this.mLastWidthSize = v2;
        }
        if(this.isStacked() || View.MeasureSpec.getMode(v) != 0x40000000) {
            v4 = v;
            z = false;
        }
        else {
            v4 = View.MeasureSpec.makeMeasureSpec(v2, 0x80000000);
            z = true;
        }
        super.onMeasure(v4, v1);
        if(this.mAllowStacking && !this.isStacked() && (this.getMeasuredWidthAndState() & 0xFF000000) == 0x1000000) {
            this.setStacked(true);
            z = true;
        }
        if(z) {
            super.onMeasure(v, v1);
        }
        int v5 = this.getNextVisibleChildIndex(0);
        if(v5 >= 0) {
            View view0 = this.getChildAt(v5);
            LinearLayout.LayoutParams linearLayout$LayoutParams0 = (LinearLayout.LayoutParams)view0.getLayoutParams();
            int v6 = this.getPaddingTop() + view0.getMeasuredHeight() + linearLayout$LayoutParams0.topMargin + linearLayout$LayoutParams0.bottomMargin;
            if(this.isStacked()) {
                int v7 = this.getNextVisibleChildIndex(v5 + 1);
                if(v7 >= 0) {
                    v6 += this.getChildAt(v7).getPaddingTop() + ((int)(this.getResources().getDisplayMetrics().density * 16.0f));
                }
                v3 = v6;
            }
            else {
                v3 = v6 + this.getPaddingBottom();
            }
        }
        if(ViewCompat.getMinimumHeight(this) != v3) {
            this.setMinimumHeight(v3);
            if(v1 == 0) {
                super.onMeasure(v, 0);
            }
        }
    }

    public void setAllowStacking(boolean z) {
        if(this.mAllowStacking != z) {
            this.mAllowStacking = z;
            if(!z && this.isStacked()) {
                this.setStacked(false);
            }
            this.requestLayout();
        }
    }

    private void setStacked(boolean z) {
        if(this.mStacked != z && (!z || this.mAllowStacking)) {
            this.mStacked = z;
            this.setOrientation(((int)z));
            this.setGravity((z ? 0x800005 : 80));
            View view0 = this.findViewById(id.spacer);
            if(view0 != null) {
                view0.setVisibility((z ? 8 : 4));
            }
            for(int v = this.getChildCount() - 2; v >= 0; --v) {
                this.bringChildToFront(this.getChildAt(v));
            }
        }
    }
}

