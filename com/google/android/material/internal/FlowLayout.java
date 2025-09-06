package com.google.android.material.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.R.id;
import com.google.android.material.R.styleable;

public class FlowLayout extends ViewGroup {
    private int itemSpacing;
    private int lineSpacing;
    private int rowCount;
    private boolean singleLine;

    public FlowLayout(Context context0) {
        this(context0, null);
    }

    public FlowLayout(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public FlowLayout(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.singleLine = false;
        this.loadFromAttributes(context0, attributeSet0);
    }

    public FlowLayout(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        this.singleLine = false;
        this.loadFromAttributes(context0, attributeSet0);
    }

    protected int getItemSpacing() {
        return this.itemSpacing;
    }

    protected int getLineSpacing() {
        return this.lineSpacing;
    }

    private static int getMeasuredDimension(int v, int v1, int v2) {
        switch(v1) {
            case 0x80000000: {
                return Math.min(v2, v);
            }
            case 0x40000000: {
                return v;
            }
            default: {
                return v2;
            }
        }
    }

    protected int getRowCount() {
        return this.rowCount;
    }

    public int getRowIndex(View view0) {
        Object object0 = view0.getTag(id.row_index_key);
        return object0 instanceof Integer ? ((int)(((Integer)object0))) : -1;
    }

    public boolean isSingleLine() {
        return this.singleLine;
    }

    private void loadFromAttributes(Context context0, AttributeSet attributeSet0) {
        TypedArray typedArray0 = context0.getTheme().obtainStyledAttributes(attributeSet0, styleable.FlowLayout, 0, 0);
        this.lineSpacing = typedArray0.getDimensionPixelSize(styleable.FlowLayout_lineSpacing, 0);
        this.itemSpacing = typedArray0.getDimensionPixelSize(styleable.FlowLayout_itemSpacing, 0);
        typedArray0.recycle();
    }

    @Override  // android.view.ViewGroup
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        int v12;
        int v11;
        if(this.getChildCount() == 0) {
            this.rowCount = 0;
            return;
        }
        this.rowCount = 1;
        boolean z1 = ViewCompat.getLayoutDirection(this) == 1;
        int v4 = z1 ? this.getPaddingRight() : this.getPaddingLeft();
        int v5 = z1 ? this.getPaddingLeft() : this.getPaddingRight();
        int v6 = this.getPaddingTop();
        int v7 = v2 - v - v5;
        int v8 = v4;
        int v9 = v6;
        for(int v10 = 0; v10 < this.getChildCount(); ++v10) {
            View view0 = this.getChildAt(v10);
            if(view0.getVisibility() == 8) {
                view0.setTag(id.row_index_key, -1);
            }
            else {
                ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
                if(viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams) {
                    v11 = MarginLayoutParamsCompat.getMarginStart(((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0));
                    v12 = MarginLayoutParamsCompat.getMarginEnd(((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0));
                }
                else {
                    v12 = 0;
                    v11 = 0;
                }
                int v13 = v8 + v11 + view0.getMeasuredWidth();
                if(!this.singleLine && v13 > v7) {
                    v9 = this.lineSpacing + v6;
                    ++this.rowCount;
                    v8 = v4;
                }
                view0.setTag(id.row_index_key, ((int)(this.rowCount - 1)));
                int v14 = v8 + v11;
                int v15 = view0.getMeasuredWidth() + v14;
                int v16 = view0.getMeasuredHeight() + v9;
                if(z1) {
                    view0.layout(v7 - v15, v9, v7 - v8 - v11, v16);
                }
                else {
                    view0.layout(v14, v9, v15, v16);
                }
                v8 += v11 + v12 + view0.getMeasuredWidth() + this.itemSpacing;
                v6 = v16;
            }
        }
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        int v14;
        int v13;
        int v12;
        int v2 = View.MeasureSpec.getSize(v);
        int v3 = View.MeasureSpec.getMode(v);
        int v4 = View.MeasureSpec.getSize(v1);
        int v5 = View.MeasureSpec.getMode(v1);
        int v6 = this.getPaddingLeft();
        int v7 = this.getPaddingTop();
        int v8 = this.getPaddingRight();
        int v9 = v7;
        int v11 = 0;
        for(int v10 = 0; v10 < this.getChildCount(); ++v10) {
            View view0 = this.getChildAt(v10);
            if(view0.getVisibility() != 8) {
                this.measureChild(view0, v, v1);
                ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
                if(viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams) {
                    v12 = ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0).leftMargin;
                    v13 = ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0).rightMargin;
                }
                else {
                    v12 = 0;
                    v13 = 0;
                }
                if(v6 + v12 + view0.getMeasuredWidth() <= (v3 == 0x80000000 || v3 == 0x40000000 ? v2 : 0x7FFFFFFF) - v8 || this.isSingleLine()) {
                    v14 = v6;
                }
                else {
                    v14 = this.getPaddingLeft();
                    v9 = this.lineSpacing + v7;
                }
                int v15 = v14 + v12 + view0.getMeasuredWidth();
                int v16 = view0.getMeasuredHeight();
                if(v15 > v11) {
                    v11 = v15;
                }
                v6 = v14 + (v12 + v13 + view0.getMeasuredWidth() + this.itemSpacing);
                if(v10 == this.getChildCount() - 1) {
                    v11 += v13;
                }
                v7 = v9 + v16;
            }
        }
        this.setMeasuredDimension(FlowLayout.getMeasuredDimension(v2, v3, v11 + this.getPaddingRight()), FlowLayout.getMeasuredDimension(v4, v5, v7 + this.getPaddingBottom()));
    }

    protected void setItemSpacing(int v) {
        this.itemSpacing = v;
    }

    protected void setLineSpacing(int v) {
        this.lineSpacing = v;
    }

    public void setSingleLine(boolean z) {
        this.singleLine = z;
    }
}

