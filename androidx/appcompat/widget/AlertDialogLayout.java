package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R.id;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;

public class AlertDialogLayout extends LinearLayoutCompat {
    public AlertDialogLayout(Context context0) {
        super(context0);
    }

    public AlertDialogLayout(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
    }

    private void forceUniformWidth(int v, int v1) {
        int v2 = View.MeasureSpec.makeMeasureSpec(this.getMeasuredWidth(), 0x40000000);
        for(int v3 = 0; v3 < v; ++v3) {
            View view0 = this.getChildAt(v3);
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

    @Override  // androidx.appcompat.widget.LinearLayoutCompat
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        int v16;
        int v11;
        int v4 = this.getPaddingLeft();
        int v5 = v2 - v;
        int v6 = this.getPaddingRight();
        int v7 = this.getPaddingRight();
        int v8 = this.getMeasuredHeight();
        int v9 = this.getChildCount();
        int v10 = this.getGravity();
        switch(v10 & 0x70) {
            case 16: {
                v11 = this.getPaddingTop() + (v3 - v1 - v8) / 2;
                break;
            }
            case 80: {
                v11 = this.getPaddingTop() + v3 - v1 - v8;
                break;
            }
            default: {
                v11 = this.getPaddingTop();
            }
        }
        Drawable drawable0 = this.getDividerDrawable();
        int v13 = drawable0 == null ? 0 : drawable0.getIntrinsicHeight();
        for(int v12 = 0; v12 < v9; ++v12) {
            View view0 = this.getChildAt(v12);
            if(view0 != null && view0.getVisibility() != 8) {
                int v14 = view0.getMeasuredWidth();
                int v15 = view0.getMeasuredHeight();
                LayoutParams linearLayoutCompat$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                switch(GravityCompat.getAbsoluteGravity((linearLayoutCompat$LayoutParams0.gravity >= 0 ? linearLayoutCompat$LayoutParams0.gravity : v10 & 0x800007), this.getLayoutDirection()) & 7) {
                    case 1: {
                        v16 = (v5 - v4 - v7 - v14) / 2 + v4 + linearLayoutCompat$LayoutParams0.leftMargin - linearLayoutCompat$LayoutParams0.rightMargin;
                        break;
                    }
                    case 5: {
                        v16 = v5 - v6 - v14 - linearLayoutCompat$LayoutParams0.rightMargin;
                        break;
                    }
                    default: {
                        v16 = linearLayoutCompat$LayoutParams0.leftMargin + v4;
                    }
                }
                if(this.hasDividerBeforeChildAt(v12)) {
                    v11 += v13;
                }
                int v17 = v11 + linearLayoutCompat$LayoutParams0.topMargin;
                this.setChildFrame(view0, v16, v17, v14, v15);
                v11 = v17 + (v15 + linearLayoutCompat$LayoutParams0.bottomMargin);
            }
        }
    }

    @Override  // androidx.appcompat.widget.LinearLayoutCompat
    protected void onMeasure(int v, int v1) {
        if(!this.tryOnMeasure(v, v1)) {
            super.onMeasure(v, v1);
        }
    }

    private static int resolveMinimumHeight(View view0) {
        int v = ViewCompat.getMinimumHeight(view0);
        if(v > 0) {
            return v;
        }
        return !(view0 instanceof ViewGroup) || ((ViewGroup)view0).getChildCount() != 1 ? 0 : AlertDialogLayout.resolveMinimumHeight(((ViewGroup)view0).getChildAt(0));
    }

    private void setChildFrame(View view0, int v, int v1, int v2, int v3) {
        view0.layout(v, v1, v2 + v, v3 + v1);
    }

    private boolean tryOnMeasure(int v, int v1) {
        int v12;
        int v11;
        int v10;
        int v9;
        int v2 = this.getChildCount();
        View view0 = null;
        View view1 = null;
        View view2 = null;
        for(int v3 = 0; v3 < v2; ++v3) {
            View view3 = this.getChildAt(v3);
            if(view3.getVisibility() != 8) {
                int v4 = view3.getId();
                if(v4 == id.topPanel) {
                    view0 = view3;
                }
                else if(v4 == id.buttonPanel) {
                    view1 = view3;
                }
                else {
                    if(v4 != id.contentPanel && v4 != id.customPanel) {
                        return false;
                    }
                    if(view2 != null) {
                        return false;
                    }
                    view2 = view3;
                }
            }
        }
        int v5 = View.MeasureSpec.getMode(v1);
        int v6 = View.MeasureSpec.getSize(v1);
        int v7 = View.MeasureSpec.getMode(v);
        int v8 = this.getPaddingTop() + this.getPaddingBottom();
        if(view0 == null) {
            v9 = 0;
        }
        else {
            view0.measure(v, 0);
            v8 += view0.getMeasuredHeight();
            v9 = View.combineMeasuredStates(0, view0.getMeasuredState());
        }
        if(view1 == null) {
            v10 = 0;
            v11 = 0;
        }
        else {
            view1.measure(v, 0);
            v10 = AlertDialogLayout.resolveMinimumHeight(view1);
            v11 = view1.getMeasuredHeight() - v10;
            v8 += v10;
            v9 = View.combineMeasuredStates(v9, view1.getMeasuredState());
        }
        if(view2 == null) {
            v12 = 0;
        }
        else {
            view2.measure(v, (v5 == 0 ? 0 : View.MeasureSpec.makeMeasureSpec(Math.max(0, v6 - v8), v5)));
            v12 = view2.getMeasuredHeight();
            v8 += v12;
            v9 = View.combineMeasuredStates(v9, view2.getMeasuredState());
        }
        int v13 = v6 - v8;
        if(view1 != null) {
            int v14 = v8 - v10;
            int v15 = Math.min(v13, v11);
            if(v15 > 0) {
                v13 -= v15;
                v10 += v15;
            }
            view1.measure(v, View.MeasureSpec.makeMeasureSpec(v10, 0x40000000));
            v8 = v14 + view1.getMeasuredHeight();
            v9 = View.combineMeasuredStates(v9, view1.getMeasuredState());
        }
        if(view2 != null && v13 > 0) {
            view2.measure(v, View.MeasureSpec.makeMeasureSpec(v12 + v13, v5));
            v8 = v8 - v12 + view2.getMeasuredHeight();
            v9 = View.combineMeasuredStates(v9, view2.getMeasuredState());
        }
        int v17 = 0;
        for(int v16 = 0; v16 < v2; ++v16) {
            View view4 = this.getChildAt(v16);
            if(view4.getVisibility() != 8) {
                v17 = Math.max(v17, view4.getMeasuredWidth());
            }
        }
        this.setMeasuredDimension(View.resolveSizeAndState(v17 + (this.getPaddingLeft() + this.getPaddingRight()), v, v9), View.resolveSizeAndState(v8, v1, 0));
        if(v7 != 0x40000000) {
            this.forceUniformWidth(v2, v1);
        }
        return true;
    }
}

