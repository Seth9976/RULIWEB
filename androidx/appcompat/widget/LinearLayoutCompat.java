package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout.LayoutParams;
import androidx.appcompat.R.styleable;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LinearLayoutCompat extends ViewGroup {
    @Retention(RetentionPolicy.SOURCE)
    public @interface DividerMode {
    }

    public static class LayoutParams extends LinearLayout.LayoutParams {
        public LayoutParams(int v, int v1) {
            super(v, v1);
        }

        public LayoutParams(int v, int v1, float f) {
            super(v, v1, f);
        }

        public LayoutParams(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
        }

        public LayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
            super(viewGroup$LayoutParams0);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
            super(viewGroup$MarginLayoutParams0);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OrientationMode {
    }

    private static final String ACCESSIBILITY_CLASS_NAME = "androidx.appcompat.widget.LinearLayoutCompat";
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    public LinearLayoutCompat(Context context0) {
        this(context0, null);
    }

    public LinearLayoutCompat(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public LinearLayoutCompat(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 0x800033;
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(context0, attributeSet0, styleable.LinearLayoutCompat, v, 0);
        ViewCompat.saveAttributeDataForStyleable(this, context0, styleable.LinearLayoutCompat, attributeSet0, tintTypedArray0.getWrappedTypeArray(), v, 0);
        int v1 = tintTypedArray0.getInt(styleable.LinearLayoutCompat_android_orientation, -1);
        if(v1 >= 0) {
            this.setOrientation(v1);
        }
        int v2 = tintTypedArray0.getInt(styleable.LinearLayoutCompat_android_gravity, -1);
        if(v2 >= 0) {
            this.setGravity(v2);
        }
        if(!tintTypedArray0.getBoolean(styleable.LinearLayoutCompat_android_baselineAligned, true)) {
            this.setBaselineAligned(false);
        }
        this.mWeightSum = tintTypedArray0.getFloat(styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = tintTypedArray0.getInt(styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = tintTypedArray0.getBoolean(styleable.LinearLayoutCompat_measureWithLargestChild, false);
        this.setDividerDrawable(tintTypedArray0.getDrawable(styleable.LinearLayoutCompat_divider));
        this.mShowDividers = tintTypedArray0.getInt(styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = tintTypedArray0.getDimensionPixelSize(styleable.LinearLayoutCompat_dividerPadding, 0);
        tintTypedArray0.recycle();
    }

    @Override  // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return viewGroup$LayoutParams0 instanceof LayoutParams;
    }

    void drawDividersHorizontal(Canvas canvas0) {
        int v2;
        int v = this.getVirtualChildCount();
        boolean z = ViewUtils.isLayoutRtl(this);
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = this.getVirtualChildAt(v1);
            if(view0 != null && view0.getVisibility() != 8 && this.hasDividerBeforeChildAt(v1)) {
                LayoutParams linearLayoutCompat$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                this.drawVerticalDivider(canvas0, (z ? view0.getRight() + linearLayoutCompat$LayoutParams0.rightMargin : view0.getLeft() - linearLayoutCompat$LayoutParams0.leftMargin - this.mDividerWidth));
            }
        }
        if(this.hasDividerBeforeChildAt(v)) {
            View view1 = this.getVirtualChildAt(v - 1);
            if(view1 != null) {
                LayoutParams linearLayoutCompat$LayoutParams1 = (LayoutParams)view1.getLayoutParams();
                v2 = z ? view1.getLeft() - linearLayoutCompat$LayoutParams1.leftMargin - this.mDividerWidth : view1.getRight() + linearLayoutCompat$LayoutParams1.rightMargin;
            }
            else if(z) {
                v2 = this.getPaddingLeft();
            }
            else {
                v2 = this.getWidth() - this.getPaddingRight() - this.mDividerWidth;
            }
            this.drawVerticalDivider(canvas0, v2);
        }
    }

    void drawDividersVertical(Canvas canvas0) {
        int v2;
        int v = this.getVirtualChildCount();
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = this.getVirtualChildAt(v1);
            if(view0 != null && view0.getVisibility() != 8 && this.hasDividerBeforeChildAt(v1)) {
                LayoutParams linearLayoutCompat$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                this.drawHorizontalDivider(canvas0, view0.getTop() - linearLayoutCompat$LayoutParams0.topMargin - this.mDividerHeight);
            }
        }
        if(this.hasDividerBeforeChildAt(v)) {
            View view1 = this.getVirtualChildAt(v - 1);
            if(view1 == null) {
                v2 = this.getHeight() - this.getPaddingBottom() - this.mDividerHeight;
            }
            else {
                LayoutParams linearLayoutCompat$LayoutParams1 = (LayoutParams)view1.getLayoutParams();
                v2 = view1.getBottom() + linearLayoutCompat$LayoutParams1.bottomMargin;
            }
            this.drawHorizontalDivider(canvas0, v2);
        }
    }

    void drawHorizontalDivider(Canvas canvas0, int v) {
        this.mDivider.setBounds(this.getPaddingLeft() + this.mDividerPadding, v, this.getWidth() - this.getPaddingRight() - this.mDividerPadding, this.mDividerHeight + v);
        this.mDivider.draw(canvas0);
    }

    void drawVerticalDivider(Canvas canvas0, int v) {
        this.mDivider.setBounds(v, this.getPaddingTop() + this.mDividerPadding, this.mDividerWidth + v, this.getHeight() - this.getPaddingBottom() - this.mDividerPadding);
        this.mDivider.draw(canvas0);
    }

    private void forceUniformHeight(int v, int v1) {
        int v2 = View.MeasureSpec.makeMeasureSpec(this.getMeasuredHeight(), 0x40000000);
        for(int v3 = 0; v3 < v; ++v3) {
            View view0 = this.getVirtualChildAt(v3);
            if(view0.getVisibility() != 8) {
                LayoutParams linearLayoutCompat$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                if(linearLayoutCompat$LayoutParams0.height == -1) {
                    int v4 = linearLayoutCompat$LayoutParams0.width;
                    linearLayoutCompat$LayoutParams0.width = view0.getMeasuredWidth();
                    this.measureChildWithMargins(view0, v1, 0, v2, 0);
                    linearLayoutCompat$LayoutParams0.width = v4;
                }
            }
        }
    }

    private void forceUniformWidth(int v, int v1) {
        int v2 = View.MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 0x40000000);
        for(int v3 = 0; v3 < v; ++v3) {
            View view0 = this.getVirtualChildAt(v3);
            if(view0.getVisibility() != 8) {
                LayoutParams linearLayoutCompat$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                if(linearLayoutCompat$LayoutParams0.width == -1) {
                    int v4 = linearLayoutCompat$LayoutParams0.height;
                    linearLayoutCompat$LayoutParams0.height = view0.getMeasuredHeight();
                    this.measureChildWithMargins(view0, v2, 0, v1, 0);
                    linearLayoutCompat$LayoutParams0.height = v4;
                }
            }
        }
    }

    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return this.generateDefaultLayoutParams();
    }

    protected LayoutParams generateDefaultLayoutParams() {
        int v = this.mOrientation;
        if(v == 0) {
            return new LayoutParams(-2, -2);
        }
        return v == 1 ? new LayoutParams(-1, -2) : null;
    }

    @Override  // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return this.generateLayoutParams(attributeSet0);
    }

    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return this.generateLayoutParams(viewGroup$LayoutParams0);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return new LayoutParams(this.getContext(), attributeSet0);
    }

    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        if(viewGroup$LayoutParams0 instanceof LayoutParams) {
            return new LayoutParams(((LayoutParams)viewGroup$LayoutParams0));
        }
        return viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams ? new LayoutParams(((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0)) : new LayoutParams(viewGroup$LayoutParams0);
    }

    @Override  // android.view.View
    public int getBaseline() {
        if(this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        int v = this.getChildCount();
        int v1 = this.mBaselineAlignedChildIndex;
        if(v <= v1) {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
        View view0 = this.getChildAt(v1);
        int v2 = view0.getBaseline();
        if(v2 == -1) {
            if(this.mBaselineAlignedChildIndex != 0) {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn\'t know how to get its baseline.");
            }
            return -1;
        }
        int v3 = this.mBaselineChildTop;
        if(this.mOrientation == 1) {
            switch(this.mGravity & 0x70) {
                case 16: {
                    v3 += (this.getBottom() - this.getTop() - this.getPaddingTop() - this.getPaddingBottom() - this.mTotalLength) / 2;
                    break;
                }
                case 80: {
                    return this.getBottom() - this.getTop() - this.getPaddingBottom() - this.mTotalLength + ((LayoutParams)view0.getLayoutParams()).topMargin + v2;
                }
                default: {
                    return v3 + ((LayoutParams)view0.getLayoutParams()).topMargin + v2;
                }
            }
        }
        return v3 + ((LayoutParams)view0.getLayoutParams()).topMargin + v2;
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    int getChildrenSkipCount(View view0, int v) [...] // Inlined contents

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    public int getGravity() {
        return this.mGravity;
    }

    int getLocationOffset(View view0) [...] // Inlined contents

    int getNextLocationOffset(View view0) [...] // Inlined contents

    public int getOrientation() {
        return this.mOrientation;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    View getVirtualChildAt(int v) {
        return this.getChildAt(v);
    }

    int getVirtualChildCount() {
        return this.getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    protected boolean hasDividerBeforeChildAt(int v) {
        if(v == 0) {
            return (this.mShowDividers & 1) != 0;
        }
        if(v == this.getChildCount()) {
            return (this.mShowDividers & 4) != 0;
        }
        if((this.mShowDividers & 2) != 0) {
            for(int v1 = v - 1; v1 >= 0; --v1) {
                if(this.getChildAt(v1).getVisibility() != 8) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    void layoutHorizontal(int v, int v1, int v2, int v3) {
        int v20;
        int v19;
        int v18;
        int v15;
        int v12;
        int v11;
        int v10;
        boolean z = ViewUtils.isLayoutRtl(this);
        int v4 = this.getPaddingTop();
        int v5 = v3 - v1;
        int v6 = this.getPaddingBottom();
        int v7 = this.getPaddingBottom();
        int v8 = this.getVirtualChildCount();
        int v9 = this.mGravity & 0x70;
        boolean z1 = this.mBaselineAligned;
        int[] arr_v = this.mMaxAscent;
        int[] arr_v1 = this.mMaxDescent;
        switch(GravityCompat.getAbsoluteGravity(0x800007 & this.mGravity, this.getLayoutDirection())) {
            case 1: {
                v10 = this.getPaddingLeft() + (v2 - v - this.mTotalLength) / 2;
                break;
            }
            case 5: {
                v10 = this.getPaddingLeft() + v2 - v - this.mTotalLength;
                break;
            }
            default: {
                v10 = this.getPaddingLeft();
            }
        }
        if(z) {
            v11 = v8 - 1;
            v12 = -1;
        }
        else {
            v11 = 0;
            v12 = 1;
        }
        int v13 = 0;
        while(v13 < v8) {
            int v14 = v11 + v12 * v13;
            View view0 = this.getVirtualChildAt(v14);
            if(view0 == null) {
                v15 = v13;
            }
            else if(view0.getVisibility() == 8) {
                v15 = v13;
            }
            else {
                int v16 = view0.getMeasuredWidth();
                int v17 = view0.getMeasuredHeight();
                LayoutParams linearLayoutCompat$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                if(z1) {
                    v18 = v17;
                    if(linearLayoutCompat$LayoutParams0.height != -1) {
                        v19 = view0.getBaseline();
                        goto label_40;
                    }
                }
                else {
                    v18 = v17;
                }
                v19 = -1;
            label_40:
                switch((linearLayoutCompat$LayoutParams0.gravity >= 0 ? linearLayoutCompat$LayoutParams0.gravity : v9) & 0x70) {
                    case 16: {
                        v20 = v4 + (v5 - v4 - v7 - v18) / 2 + linearLayoutCompat$LayoutParams0.topMargin - linearLayoutCompat$LayoutParams0.bottomMargin;
                        break;
                    }
                    case 0x30: {
                        v20 = v4 + linearLayoutCompat$LayoutParams0.topMargin;
                        if(v19 != -1) {
                            v20 += arr_v[1] - v19;
                        }
                        break;
                    }
                    case 80: {
                        v20 = v5 - v6 - v18 - linearLayoutCompat$LayoutParams0.bottomMargin;
                        if(v19 != -1) {
                            int v21 = view0.getMeasuredHeight();
                            v20 -= arr_v1[2] - (v21 - v19);
                        }
                        break;
                    }
                    default: {
                        v20 = v4;
                    }
                }
                int v22 = this.hasDividerBeforeChildAt(v14) ? v10 + this.mDividerWidth : v10;
                int v23 = linearLayoutCompat$LayoutParams0.leftMargin + v22;
                this.setChildFrame(view0, v23, v20, v16, v18);
                v15 = v13;
                v10 = v23 + (linearLayoutCompat$LayoutParams0.rightMargin + v16);
            }
            v13 = v15 + 1;
        }
    }

    void layoutVertical(int v, int v1, int v2, int v3) {
        int v14;
        int v10;
        int v4 = this.getPaddingLeft();
        int v5 = v2 - v;
        int v6 = this.getPaddingRight();
        int v7 = this.getPaddingRight();
        int v8 = this.getVirtualChildCount();
        int v9 = this.mGravity & 0x800007;
        switch(this.mGravity & 0x70) {
            case 16: {
                v10 = this.getPaddingTop() + (v3 - v1 - this.mTotalLength) / 2;
                break;
            }
            case 80: {
                v10 = this.getPaddingTop() + v3 - v1 - this.mTotalLength;
                break;
            }
            default: {
                v10 = this.getPaddingTop();
            }
        }
        for(int v11 = 0; v11 < v8; ++v11) {
            View view0 = this.getVirtualChildAt(v11);
            if(view0 != null && view0.getVisibility() != 8) {
                int v12 = view0.getMeasuredWidth();
                int v13 = view0.getMeasuredHeight();
                LayoutParams linearLayoutCompat$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                switch(GravityCompat.getAbsoluteGravity((linearLayoutCompat$LayoutParams0.gravity >= 0 ? linearLayoutCompat$LayoutParams0.gravity : v9), this.getLayoutDirection()) & 7) {
                    case 1: {
                        v14 = (v5 - v4 - v7 - v12) / 2 + v4 + linearLayoutCompat$LayoutParams0.leftMargin - linearLayoutCompat$LayoutParams0.rightMargin;
                        break;
                    }
                    case 5: {
                        v14 = v5 - v6 - v12 - linearLayoutCompat$LayoutParams0.rightMargin;
                        break;
                    }
                    default: {
                        v14 = linearLayoutCompat$LayoutParams0.leftMargin + v4;
                    }
                }
                if(this.hasDividerBeforeChildAt(v11)) {
                    v10 += this.mDividerHeight;
                }
                int v15 = v10 + linearLayoutCompat$LayoutParams0.topMargin;
                this.setChildFrame(view0, v14, v15, v12, v13);
                v10 = v15 + (v13 + linearLayoutCompat$LayoutParams0.bottomMargin);
            }
        }
    }

    void measureChildBeforeLayout(View view0, int v, int v1, int v2, int v3, int v4) {
        this.measureChildWithMargins(view0, v1, v2, v3, v4);
    }

    void measureHorizontal(int v, int v1) {
        int v38;
        int v37;
        int v35;
        int v43;
        float f3;
        int v30;
        float f2;
        int v28;
        int v25;
        int v23;
        int v18;
        View view1;
        boolean z5;
        int v14;
        int[] arr_v3;
        int[] arr_v2;
        int v13;
        int v12;
        int v16;
        boolean z6;
        int v15;
        this.mTotalLength = 0;
        int v2 = this.getVirtualChildCount();
        int v3 = View.MeasureSpec.getMode(v);
        int v4 = View.MeasureSpec.getMode(v1);
        if(this.mMaxAscent == null || this.mMaxDescent == null) {
            this.mMaxAscent = new int[4];
            this.mMaxDescent = new int[4];
        }
        int[] arr_v = this.mMaxAscent;
        int[] arr_v1 = this.mMaxDescent;
        arr_v[3] = -1;
        arr_v[2] = -1;
        arr_v[1] = -1;
        arr_v[0] = -1;
        arr_v1[3] = -1;
        arr_v1[2] = -1;
        arr_v1[1] = -1;
        arr_v1[0] = -1;
        boolean z = this.mBaselineAligned;
        boolean z1 = v3 == 0x40000000;
        boolean z2 = this.mUseLargestChild;
        int v5 = 0;
        float f = 0.0f;
        int v6 = 0;
        int v7 = 0;
        int v8 = 0;
        int v9 = 0;
        boolean z3 = false;
        int v10 = 0;
        int v11 = 1;
        boolean z4 = false;
        while(v5 < v2) {
            View view0 = this.getVirtualChildAt(v5);
            if(view0 == null) {
                this.mTotalLength = this.mTotalLength;
            }
            else if(view0.getVisibility() != 8) {
                if(this.hasDividerBeforeChildAt(v5)) {
                    this.mTotalLength += this.mDividerWidth;
                }
                LayoutParams linearLayoutCompat$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                float f1 = f + linearLayoutCompat$LayoutParams0.weight;
                if(v3 != 0x40000000 || linearLayoutCompat$LayoutParams0.width != 0 || linearLayoutCompat$LayoutParams0.weight <= 0.0f) {
                    if(linearLayoutCompat$LayoutParams0.width != 0 || linearLayoutCompat$LayoutParams0.weight <= 0.0f) {
                        v15 = 0x80000000;
                    }
                    else {
                        linearLayoutCompat$LayoutParams0.width = -2;
                        v15 = 0;
                    }
                    if(f1 == 0.0f) {
                        z6 = z2;
                        v16 = this.mTotalLength;
                    }
                    else {
                        z6 = z2;
                        v16 = 0;
                    }
                    v12 = v2;
                    v13 = v3;
                    arr_v2 = arr_v;
                    arr_v3 = arr_v1;
                    v14 = v6;
                    z5 = z6;
                    this.measureChildBeforeLayout(view0, v5, v, v16, v1, 0);
                    view1 = view0;
                    if(v15 != 0x80000000) {
                        linearLayoutCompat$LayoutParams0.width = v15;
                    }
                    int v17 = view1.getMeasuredWidth();
                    if(z1) {
                        this.mTotalLength += linearLayoutCompat$LayoutParams0.leftMargin + v17 + linearLayoutCompat$LayoutParams0.rightMargin;
                    }
                    else {
                        this.mTotalLength = Math.max(this.mTotalLength, this.mTotalLength + v17 + linearLayoutCompat$LayoutParams0.leftMargin + linearLayoutCompat$LayoutParams0.rightMargin);
                    }
                    if(z5) {
                        v9 = Math.max(v17, v9);
                    }
                }
                else {
                    if(z1) {
                        this.mTotalLength += linearLayoutCompat$LayoutParams0.leftMargin + linearLayoutCompat$LayoutParams0.rightMargin;
                    }
                    else {
                        this.mTotalLength = Math.max(this.mTotalLength, linearLayoutCompat$LayoutParams0.leftMargin + this.mTotalLength + linearLayoutCompat$LayoutParams0.rightMargin);
                    }
                    if(z) {
                        view0.measure(0, 0);
                        v12 = v2;
                        v13 = 0x40000000;
                        arr_v2 = arr_v;
                        arr_v3 = arr_v1;
                        v14 = v6;
                    }
                    else {
                        v12 = v2;
                        v13 = 0x40000000;
                        arr_v2 = arr_v;
                        arr_v3 = arr_v1;
                        v14 = v6;
                        z3 = true;
                    }
                    z5 = z2;
                    view1 = view0;
                }
                if(v4 == 0x40000000 || linearLayoutCompat$LayoutParams0.height != -1) {
                    v18 = 0;
                }
                else {
                    v18 = 1;
                    z4 = true;
                }
                int v19 = linearLayoutCompat$LayoutParams0.topMargin + linearLayoutCompat$LayoutParams0.bottomMargin;
                int v20 = view1.getMeasuredHeight() + v19;
                v10 = View.combineMeasuredStates(v10, view1.getMeasuredState());
                if(z) {
                    int v21 = view1.getBaseline();
                    if(v21 != -1) {
                        int v22 = (((linearLayoutCompat$LayoutParams0.gravity >= 0 ? linearLayoutCompat$LayoutParams0.gravity : this.mGravity) & 0x70) >> 4 & -2) >> 1;
                        v23 = v18;
                        arr_v2[v22] = Math.max(arr_v2[v22], v21);
                        arr_v3[v22] = Math.max(arr_v3[v22], v20 - v21);
                    }
                }
                else {
                    v23 = v18;
                }
                int v24 = Math.max(v14, v20);
                v11 = v11 == 0 || linearLayoutCompat$LayoutParams0.height != -1 ? 0 : 1;
                if(linearLayoutCompat$LayoutParams0.weight > 0.0f) {
                    if(v23 == 0) {
                        v19 = v20;
                    }
                    v8 = Math.max(v8, v19);
                }
                else {
                    if(v23 == 0) {
                        v19 = v20;
                    }
                    v7 = Math.max(v7, v19);
                }
                v6 = v24;
                f = f1;
                goto label_125;
            }
            v12 = v2;
            v13 = v3;
            arr_v2 = arr_v;
            arr_v3 = arr_v1;
            z5 = z2;
        label_125:
            ++v5;
            z2 = z5;
            arr_v1 = arr_v3;
            v3 = v13;
            arr_v = arr_v2;
            v2 = v12;
        }
        if(this.mTotalLength > 0) {
            v25 = v2;
            if(this.hasDividerBeforeChildAt(v25)) {
                this.mTotalLength += this.mDividerWidth;
            }
        }
        else {
            v25 = v2;
        }
        int v26 = arr_v[1];
        int v27 = v26 != -1 || arr_v[0] != -1 || arr_v[2] != -1 || arr_v[3] != -1 ? Math.max(v6, Math.max(arr_v[3], Math.max(arr_v[0], Math.max(v26, arr_v[2]))) + Math.max(arr_v1[3], Math.max(arr_v1[0], Math.max(arr_v1[1], arr_v1[2])))) : v6;
        if(z2) {
            v28 = v3;
            if(v28 == 0x80000000 || v28 == 0) {
                this.mTotalLength = 0;
                int v29 = 0;
                while(v29 < v25) {
                    View view2 = this.getVirtualChildAt(v29);
                    if(view2 == null) {
                        this.mTotalLength = this.mTotalLength;
                        f2 = f;
                        goto label_153;
                    }
                    else if(view2.getVisibility() == 8) {
                        f2 = f;
                    label_153:
                        v30 = v27;
                    }
                    else {
                        LayoutParams linearLayoutCompat$LayoutParams1 = (LayoutParams)view2.getLayoutParams();
                        if(z1) {
                            f2 = f;
                            this.mTotalLength += linearLayoutCompat$LayoutParams1.leftMargin + v9 + linearLayoutCompat$LayoutParams1.rightMargin;
                            v30 = v27;
                        }
                        else {
                            f2 = f;
                            v30 = v27;
                            this.mTotalLength = Math.max(this.mTotalLength, this.mTotalLength + v9 + linearLayoutCompat$LayoutParams1.leftMargin + linearLayoutCompat$LayoutParams1.rightMargin);
                        }
                    }
                    ++v29;
                    f = f2;
                    v27 = v30;
                }
            }
            f3 = f;
        }
        else {
            f3 = f;
            v28 = v3;
        }
        int v31 = v27;
        int v32 = this.mTotalLength + (this.getPaddingLeft() + this.getPaddingRight());
        this.mTotalLength = v32;
        int v33 = View.resolveSizeAndState(Math.max(v32, this.getSuggestedMinimumWidth()), v, 0);
        int v34 = (0xFFFFFF & v33) - this.mTotalLength;
        if(z3 || v34 != 0 && f3 > 0.0f) {
            float f4 = this.mWeightSum;
            if(f4 > 0.0f) {
                f3 = f4;
            }
            arr_v[3] = -1;
            arr_v[2] = -1;
            arr_v[1] = -1;
            arr_v[0] = -1;
            arr_v1[3] = -1;
            arr_v1[2] = -1;
            arr_v1[1] = -1;
            arr_v1[0] = -1;
            this.mTotalLength = 0;
            int v39 = v10;
            int v40 = -1;
            int v41 = 0;
            while(v41 < v25) {
                View view4 = this.getVirtualChildAt(v41);
                if(view4 == null || view4.getVisibility() == 8) {
                    v43 = v33;
                }
                else {
                    LayoutParams linearLayoutCompat$LayoutParams2 = (LayoutParams)view4.getLayoutParams();
                    float f5 = linearLayoutCompat$LayoutParams2.weight;
                    if(f5 > 0.0f) {
                        int v42 = (int)(((float)v34) * f5 / f3);
                        f3 -= f5;
                        v34 -= v42;
                        v43 = v33;
                        int v44 = LinearLayoutCompat.getChildMeasureSpec(v1, this.getPaddingTop() + this.getPaddingBottom() + linearLayoutCompat$LayoutParams2.topMargin + linearLayoutCompat$LayoutParams2.bottomMargin, linearLayoutCompat$LayoutParams2.height);
                        if(linearLayoutCompat$LayoutParams2.width != 0 || v28 != 0x40000000) {
                            int v45 = view4.getMeasuredWidth() + v42;
                            if(v45 < 0) {
                                v45 = 0;
                            }
                            view4.measure(View.MeasureSpec.makeMeasureSpec(v45, 0x40000000), v44);
                        }
                        else {
                            if(v42 <= 0) {
                                v42 = 0;
                            }
                            view4.measure(View.MeasureSpec.makeMeasureSpec(v42, 0x40000000), v44);
                        }
                        v39 = View.combineMeasuredStates(v39, view4.getMeasuredState() & 0xFF000000);
                    }
                    else {
                        v43 = v33;
                    }
                    if(z1) {
                        this.mTotalLength += view4.getMeasuredWidth() + linearLayoutCompat$LayoutParams2.leftMargin + linearLayoutCompat$LayoutParams2.rightMargin;
                    }
                    else {
                        int v46 = this.mTotalLength;
                        this.mTotalLength = Math.max(v46, view4.getMeasuredWidth() + v46 + linearLayoutCompat$LayoutParams2.leftMargin + linearLayoutCompat$LayoutParams2.rightMargin);
                    }
                    boolean z7 = v4 != 0x40000000 && linearLayoutCompat$LayoutParams2.height == -1;
                    int v47 = linearLayoutCompat$LayoutParams2.topMargin + linearLayoutCompat$LayoutParams2.bottomMargin;
                    int v48 = view4.getMeasuredHeight() + v47;
                    v40 = Math.max(v40, v48);
                    if(!z7) {
                        v47 = v48;
                    }
                    int v49 = Math.max(v7, v47);
                    int v50 = v11 == 0 || linearLayoutCompat$LayoutParams2.height != -1 ? 0 : 1;
                    if(z) {
                        int v51 = view4.getBaseline();
                        if(v51 != -1) {
                            int v52 = (((linearLayoutCompat$LayoutParams2.gravity >= 0 ? linearLayoutCompat$LayoutParams2.gravity : this.mGravity) & 0x70) >> 4 & -2) >> 1;
                            arr_v[v52] = Math.max(arr_v[v52], v51);
                            arr_v1[v52] = Math.max(arr_v1[v52], v48 - v51);
                        }
                    }
                    v7 = v49;
                    v11 = v50;
                }
                ++v41;
                v33 = v43;
            }
            v37 = v33;
            this.mTotalLength += this.getPaddingLeft() + this.getPaddingRight();
            int v53 = arr_v[1];
            v31 = v53 != -1 || arr_v[0] != -1 || arr_v[2] != -1 || arr_v[3] != -1 ? Math.max(v40, Math.max(arr_v[3], Math.max(arr_v[0], Math.max(v53, arr_v[2]))) + Math.max(arr_v1[3], Math.max(arr_v1[0], Math.max(arr_v1[1], arr_v1[2])))) : v40;
            v38 = v39;
            v35 = v7;
        }
        else {
            v35 = Math.max(v7, v8);
            if(z2 && v28 != 0x40000000) {
                for(int v36 = 0; v36 < v25; ++v36) {
                    View view3 = this.getVirtualChildAt(v36);
                    if(view3 != null && view3.getVisibility() != 8 && ((LayoutParams)view3.getLayoutParams()).weight > 0.0f) {
                        view3.measure(View.MeasureSpec.makeMeasureSpec(v9, 0x40000000), View.MeasureSpec.makeMeasureSpec(view3.getMeasuredHeight(), 0x40000000));
                    }
                }
            }
            v37 = v33;
            v38 = v10;
        }
        if(v11 != 0 || v4 == 0x40000000) {
            v35 = v31;
        }
        this.setMeasuredDimension(v37 | v38 & 0xFF000000, View.resolveSizeAndState(Math.max(v35 + (this.getPaddingTop() + this.getPaddingBottom()), this.getSuggestedMinimumHeight()), v1, v38 << 16));
        if(z4) {
            this.forceUniformHeight(v25, v);
        }
    }

    int measureNullChild(int v) [...] // Inlined contents

    void measureVertical(int v, int v1) {
        int v37;
        int v42;
        int v41;
        int v31;
        int v28;
        int v27;
        int v25;
        int v24;
        boolean z3;
        int v17;
        int v16;
        int v15;
        int v14;
        View view1;
        float f2;
        int v13;
        int v20;
        int v19;
        int v18;
        this.mTotalLength = 0;
        int v2 = this.getVirtualChildCount();
        int v3 = View.MeasureSpec.getMode(v);
        int v4 = View.MeasureSpec.getMode(v1);
        int v5 = this.mBaselineAlignedChildIndex;
        boolean z = this.mUseLargestChild;
        float f = 0.0f;
        int v6 = 0;
        int v7 = 0;
        int v8 = 0;
        int v9 = 0;
        int v10 = 0;
        int v11 = 0;
        boolean z1 = false;
        int v12 = 1;
        boolean z2 = false;
        while(v6 < v2) {
            View view0 = this.getVirtualChildAt(v6);
            if(view0 == null) {
                this.mTotalLength = this.mTotalLength;
            }
            else if(view0.getVisibility() != 8) {
                if(this.hasDividerBeforeChildAt(v6)) {
                    this.mTotalLength += this.mDividerHeight;
                }
                LayoutParams linearLayoutCompat$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                float f1 = f + linearLayoutCompat$LayoutParams0.weight;
                if(v4 != 0x40000000 || linearLayoutCompat$LayoutParams0.height != 0 || linearLayoutCompat$LayoutParams0.weight <= 0.0f) {
                    if(linearLayoutCompat$LayoutParams0.height != 0 || linearLayoutCompat$LayoutParams0.weight <= 0.0f) {
                        v18 = 0x80000000;
                    }
                    else {
                        linearLayoutCompat$LayoutParams0.height = -2;
                        v18 = 0;
                    }
                    if(f1 == 0.0f) {
                        v19 = this.mTotalLength;
                        v20 = v10;
                    }
                    else {
                        v20 = v10;
                        v19 = 0;
                    }
                    f2 = f1;
                    v13 = v2;
                    v15 = v20;
                    v14 = v7;
                    view1 = view0;
                    v16 = v4;
                    v17 = v9;
                    this.measureChildBeforeLayout(view1, v6, v, 0, v1, v19);
                    if(v18 != 0x80000000) {
                        linearLayoutCompat$LayoutParams0.height = v18;
                    }
                    int v21 = view1.getMeasuredHeight();
                    this.mTotalLength = Math.max(this.mTotalLength, this.mTotalLength + v21 + linearLayoutCompat$LayoutParams0.topMargin + linearLayoutCompat$LayoutParams0.bottomMargin);
                    if(z) {
                        v8 = Math.max(v21, v8);
                    }
                }
                else {
                    this.mTotalLength = Math.max(this.mTotalLength, linearLayoutCompat$LayoutParams0.topMargin + this.mTotalLength + linearLayoutCompat$LayoutParams0.bottomMargin);
                    v13 = v2;
                    f2 = f1;
                    view1 = view0;
                    z1 = true;
                    v14 = v7;
                    v15 = v10;
                    v16 = 0x40000000;
                    v17 = v9;
                }
                if(v5 >= 0 && v5 == v6 + 1) {
                    this.mBaselineChildTop = this.mTotalLength;
                }
                if(v6 < v5 && linearLayoutCompat$LayoutParams0.weight > 0.0f) {
                    throw new RuntimeException("A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won\'t work.  Either remove the weight, or don\'t set mBaselineAlignedChildIndex.");
                }
                if(v3 == 0x40000000 || linearLayoutCompat$LayoutParams0.width != -1) {
                    z3 = false;
                }
                else {
                    z3 = true;
                    z2 = true;
                }
                int v22 = linearLayoutCompat$LayoutParams0.leftMargin + linearLayoutCompat$LayoutParams0.rightMargin;
                int v23 = view1.getMeasuredWidth() + v22;
                v24 = Math.max(v17, v23);
                v25 = v8;
                int v26 = View.combineMeasuredStates(v15, view1.getMeasuredState());
                if(v12 == 0) {
                    v27 = v26;
                }
                else {
                    v27 = v26;
                    if(linearLayoutCompat$LayoutParams0.width == -1) {
                        v12 = 1;
                        goto label_83;
                    }
                }
                v12 = 0;
            label_83:
                if(linearLayoutCompat$LayoutParams0.weight > 0.0f) {
                    if(!z3) {
                        v22 = v23;
                    }
                    v28 = Math.max(v14, v22);
                }
                else {
                    if(!z3) {
                        v22 = v23;
                    }
                    v11 = Math.max(v11, v22);
                    v28 = v14;
                }
                f = f2;
                v10 = v27;
                goto label_100;
            }
            v25 = v8;
            v13 = v2;
            v16 = v4;
            v28 = v7;
            v24 = v9;
        label_100:
            ++v6;
            v7 = v28;
            v9 = v24;
            v8 = v25;
            v4 = v16;
            v2 = v13;
        }
        float f3 = f;
        int v29 = v9;
        int v30 = v10;
        if(this.mTotalLength > 0) {
            v31 = v2;
            if(this.hasDividerBeforeChildAt(v31)) {
                this.mTotalLength += this.mDividerHeight;
            }
        }
        else {
            v31 = v2;
        }
        int v32 = v4;
        if(z && (v32 == 0x80000000 || v32 == 0)) {
            this.mTotalLength = 0;
            for(int v33 = 0; v33 < v31; ++v33) {
                View view2 = this.getVirtualChildAt(v33);
                if(view2 == null) {
                    this.mTotalLength = this.mTotalLength;
                }
                else if(view2.getVisibility() != 8) {
                    LayoutParams linearLayoutCompat$LayoutParams1 = (LayoutParams)view2.getLayoutParams();
                    this.mTotalLength = Math.max(this.mTotalLength, this.mTotalLength + v8 + linearLayoutCompat$LayoutParams1.topMargin + linearLayoutCompat$LayoutParams1.bottomMargin);
                }
            }
        }
        int v34 = this.mTotalLength + (this.getPaddingTop() + this.getPaddingBottom());
        this.mTotalLength = v34;
        int v35 = View.resolveSizeAndState(Math.max(v34, this.getSuggestedMinimumHeight()), v1, 0);
        int v36 = (0xFFFFFF & v35) - this.mTotalLength;
        if(z1 || v36 != 0 && f3 > 0.0f) {
            float f4 = this.mWeightSum;
            if(f4 > 0.0f) {
                f3 = f4;
            }
            this.mTotalLength = 0;
            int v39 = v36;
            int v40 = 0;
            while(v40 < v31) {
                View view4 = this.getVirtualChildAt(v40);
                if(view4.getVisibility() == 8) {
                    v41 = v32;
                    v42 = v40;
                }
                else {
                    LayoutParams linearLayoutCompat$LayoutParams2 = (LayoutParams)view4.getLayoutParams();
                    float f5 = linearLayoutCompat$LayoutParams2.weight;
                    if(f5 > 0.0f) {
                        int v43 = (int)(((float)v39) * f5 / f3);
                        f3 -= f5;
                        v39 -= v43;
                        v42 = v40;
                        int v44 = LinearLayoutCompat.getChildMeasureSpec(v, this.getPaddingLeft() + this.getPaddingRight() + linearLayoutCompat$LayoutParams2.leftMargin + linearLayoutCompat$LayoutParams2.rightMargin, linearLayoutCompat$LayoutParams2.width);
                        if(linearLayoutCompat$LayoutParams2.height != 0 || v32 != 0x40000000) {
                            int v45 = view4.getMeasuredHeight() + v43;
                            if(v45 < 0) {
                                v45 = 0;
                            }
                            view4.measure(v44, View.MeasureSpec.makeMeasureSpec(v45, 0x40000000));
                        }
                        else {
                            if(v43 <= 0) {
                                v43 = 0;
                            }
                            view4.measure(v44, View.MeasureSpec.makeMeasureSpec(v43, 0x40000000));
                        }
                        v30 = View.combineMeasuredStates(v30, view4.getMeasuredState() & 0xFFFFFF00);
                    }
                    else {
                        v42 = v40;
                    }
                    int v46 = linearLayoutCompat$LayoutParams2.leftMargin + linearLayoutCompat$LayoutParams2.rightMargin;
                    int v47 = view4.getMeasuredWidth() + v46;
                    v29 = Math.max(v29, v47);
                    if(v3 == 0x40000000) {
                        v41 = v32;
                    }
                    else {
                        v41 = v32;
                        if(linearLayoutCompat$LayoutParams2.width == -1) {
                            goto label_186;
                        }
                    }
                    v46 = v47;
                label_186:
                    int v48 = v12 == 0 || linearLayoutCompat$LayoutParams2.width != -1 ? 0 : 1;
                    int v49 = this.mTotalLength;
                    this.mTotalLength = Math.max(v49, view4.getMeasuredHeight() + v49 + linearLayoutCompat$LayoutParams2.topMargin + linearLayoutCompat$LayoutParams2.bottomMargin);
                    v11 = Math.max(v11, v46);
                    v12 = v48;
                }
                v40 = v42 + 1;
                v32 = v41;
            }
            this.mTotalLength += this.getPaddingTop() + this.getPaddingBottom();
            v37 = v11;
        }
        else {
            v37 = Math.max(v11, v7);
            if(z && v32 != 0x40000000) {
                for(int v38 = 0; v38 < v31; ++v38) {
                    View view3 = this.getVirtualChildAt(v38);
                    if(view3 != null && view3.getVisibility() != 8 && ((LayoutParams)view3.getLayoutParams()).weight > 0.0f) {
                        view3.measure(View.MeasureSpec.makeMeasureSpec(view3.getMeasuredWidth(), 0x40000000), View.MeasureSpec.makeMeasureSpec(v8, 0x40000000));
                    }
                }
            }
        }
        if(v12 == 0 && v3 != 0x40000000) {
            v29 = v37;
        }
        this.setMeasuredDimension(View.resolveSizeAndState(Math.max(v29 + (this.getPaddingLeft() + this.getPaddingRight()), this.getSuggestedMinimumWidth()), v, v30), v35);
        if(z2) {
            this.forceUniformWidth(v31, v1);
        }
    }

    @Override  // android.view.View
    protected void onDraw(Canvas canvas0) {
        if(this.mDivider == null) {
            return;
        }
        if(this.mOrientation == 1) {
            this.drawDividersVertical(canvas0);
            return;
        }
        this.drawDividersHorizontal(canvas0);
    }

    @Override  // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent0) {
        super.onInitializeAccessibilityEvent(accessibilityEvent0);
        accessibilityEvent0.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    @Override  // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo0) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo0);
        accessibilityNodeInfo0.setClassName("androidx.appcompat.widget.LinearLayoutCompat");
    }

    @Override  // android.view.ViewGroup
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        if(this.mOrientation == 1) {
            this.layoutVertical(v, v1, v2, v3);
            return;
        }
        this.layoutHorizontal(v, v1, v2, v3);
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        if(this.mOrientation == 1) {
            this.measureVertical(v, v1);
            return;
        }
        this.measureHorizontal(v, v1);
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    public void setBaselineAlignedChildIndex(int v) {
        if(v < 0 || v >= this.getChildCount()) {
            throw new IllegalArgumentException("base aligned child index out of range (0, " + this.getChildCount() + ")");
        }
        this.mBaselineAlignedChildIndex = v;
    }

    private void setChildFrame(View view0, int v, int v1, int v2, int v3) {
        view0.layout(v, v1, v2 + v, v3 + v1);
    }

    public void setDividerDrawable(Drawable drawable0) {
        if(drawable0 == this.mDivider) {
            return;
        }
        this.mDivider = drawable0;
        boolean z = false;
        if(drawable0 == null) {
            this.mDividerWidth = 0;
            this.mDividerHeight = 0;
        }
        else {
            this.mDividerWidth = drawable0.getIntrinsicWidth();
            this.mDividerHeight = drawable0.getIntrinsicHeight();
        }
        if(drawable0 == null) {
            z = true;
        }
        this.setWillNotDraw(z);
        this.requestLayout();
    }

    public void setDividerPadding(int v) {
        this.mDividerPadding = v;
    }

    public void setGravity(int v) {
        if(this.mGravity != v) {
            if((0x800007 & v) == 0) {
                v |= 0x800003;
            }
            if((v & 0x70) == 0) {
                v |= 0x30;
            }
            this.mGravity = v;
            this.requestLayout();
        }
    }

    public void setHorizontalGravity(int v) {
        int v1 = this.mGravity;
        if((0x800007 & v1) != (v & 0x800007)) {
            this.mGravity = v & 0x800007 | 0xFF7FFFF8 & v1;
            this.requestLayout();
        }
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.mUseLargestChild = z;
    }

    public void setOrientation(int v) {
        if(this.mOrientation != v) {
            this.mOrientation = v;
            this.requestLayout();
        }
    }

    public void setShowDividers(int v) {
        if(v != this.mShowDividers) {
            this.requestLayout();
        }
        this.mShowDividers = v;
    }

    public void setVerticalGravity(int v) {
        int v1 = this.mGravity;
        if((v1 & 0x70) != (v & 0x70)) {
            this.mGravity = v & 0x70 | v1 & 0xFFFFFF8F;
            this.requestLayout();
        }
    }

    public void setWeightSum(float f) {
        this.mWeightSum = Math.max(0.0f, f);
    }

    @Override  // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}

