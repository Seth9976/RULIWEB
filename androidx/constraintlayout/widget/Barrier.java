package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.HelperWidget;

public class Barrier extends ConstraintHelper {
    public static final int BOTTOM = 3;
    public static final int END = 6;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int START = 5;
    public static final int TOP = 2;
    private androidx.constraintlayout.core.widgets.Barrier mBarrier;
    private int mIndicatedType;
    private int mResolvedType;

    public Barrier(Context context0) {
        super(context0);
        super.setVisibility(8);
    }

    public Barrier(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        super.setVisibility(8);
    }

    public Barrier(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        super.setVisibility(8);
    }

    @Deprecated
    public boolean allowsGoneWidget() {
        return this.mBarrier.getAllowsGoneWidget();
    }

    public boolean getAllowsGoneWidget() {
        return this.mBarrier.getAllowsGoneWidget();
    }

    public int getMargin() {
        return this.mBarrier.getMargin();
    }

    public int getType() {
        return this.mIndicatedType;
    }

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    protected void init(AttributeSet attributeSet0) {
        super.init(attributeSet0);
        this.mBarrier = new androidx.constraintlayout.core.widgets.Barrier();
        if(attributeSet0 != null) {
            TypedArray typedArray0 = this.getContext().obtainStyledAttributes(attributeSet0, styleable.ConstraintLayout_Layout);
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                if(v2 == styleable.ConstraintLayout_Layout_barrierDirection) {
                    this.setType(typedArray0.getInt(v2, 0));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_barrierAllowsGoneWidgets) {
                    this.mBarrier.setAllowsGoneWidget(typedArray0.getBoolean(v2, true));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_barrierMargin) {
                    int v3 = typedArray0.getDimensionPixelSize(v2, 0);
                    this.mBarrier.setMargin(v3);
                }
            }
            typedArray0.recycle();
        }
        this.mHelperWidget = this.mBarrier;
        this.validateParams();
    }

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    public void loadParameters(Constraint constraintSet$Constraint0, HelperWidget helperWidget0, LayoutParams constraintLayout$LayoutParams0, SparseArray sparseArray0) {
        super.loadParameters(constraintSet$Constraint0, helperWidget0, constraintLayout$LayoutParams0, sparseArray0);
        if(helperWidget0 instanceof androidx.constraintlayout.core.widgets.Barrier) {
            boolean z = ((ConstraintWidgetContainer)helperWidget0.getParent()).isRtl();
            this.updateType(((androidx.constraintlayout.core.widgets.Barrier)helperWidget0), constraintSet$Constraint0.layout.mBarrierDirection, z);
            ((androidx.constraintlayout.core.widgets.Barrier)helperWidget0).setAllowsGoneWidget(constraintSet$Constraint0.layout.mBarrierAllowsGoneWidgets);
            ((androidx.constraintlayout.core.widgets.Barrier)helperWidget0).setMargin(constraintSet$Constraint0.layout.mBarrierMargin);
        }
    }

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    public void resolveRtl(ConstraintWidget constraintWidget0, boolean z) {
        this.updateType(constraintWidget0, this.mIndicatedType, z);
    }

    public void setAllowsGoneWidget(boolean z) {
        this.mBarrier.setAllowsGoneWidget(z);
    }

    public void setDpMargin(int v) {
        DisplayMetrics displayMetrics0 = this.getResources().getDisplayMetrics();
        this.mBarrier.setMargin(((int)(((float)v) * displayMetrics0.density + 0.5f)));
    }

    public void setMargin(int v) {
        this.mBarrier.setMargin(v);
    }

    public void setType(int v) {
        this.mIndicatedType = v;
    }

    private void updateType(ConstraintWidget constraintWidget0, int v, boolean z) {
        this.mResolvedType = v;
        if(z) {
            int v1 = this.mIndicatedType;
            if(v1 == 5) {
                this.mResolvedType = 1;
            }
            else if(v1 == 6) {
                this.mResolvedType = 0;
            }
        }
        else {
            int v2 = this.mIndicatedType;
            if(v2 == 5) {
                this.mResolvedType = 0;
            }
            else if(v2 == 6) {
                this.mResolvedType = 1;
            }
        }
        if(constraintWidget0 instanceof androidx.constraintlayout.core.widgets.Barrier) {
            ((androidx.constraintlayout.core.widgets.Barrier)constraintWidget0).setBarrierType(this.mResolvedType);
        }
    }
}

