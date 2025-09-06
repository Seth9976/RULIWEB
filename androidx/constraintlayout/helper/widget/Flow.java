package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View.MeasureSpec;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams;
import androidx.constraintlayout.widget.ConstraintSet.Constraint;
import androidx.constraintlayout.widget.R.styleable;
import androidx.constraintlayout.widget.VirtualLayout;

public class Flow extends VirtualLayout {
    public static final int CHAIN_PACKED = 2;
    public static final int CHAIN_SPREAD = 0;
    public static final int CHAIN_SPREAD_INSIDE = 1;
    public static final int HORIZONTAL = 0;
    public static final int HORIZONTAL_ALIGN_CENTER = 2;
    public static final int HORIZONTAL_ALIGN_END = 1;
    public static final int HORIZONTAL_ALIGN_START = 0;
    private static final String TAG = "Flow";
    public static final int VERTICAL = 1;
    public static final int VERTICAL_ALIGN_BASELINE = 3;
    public static final int VERTICAL_ALIGN_BOTTOM = 1;
    public static final int VERTICAL_ALIGN_CENTER = 2;
    public static final int VERTICAL_ALIGN_TOP = 0;
    public static final int WRAP_ALIGNED = 2;
    public static final int WRAP_CHAIN = 1;
    public static final int WRAP_NONE;
    private androidx.constraintlayout.core.widgets.Flow mFlow;

    public Flow(Context context0) {
        super(context0);
    }

    public Flow(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
    }

    public Flow(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
    }

    @Override  // androidx.constraintlayout.widget.VirtualLayout
    protected void init(AttributeSet attributeSet0) {
        super.init(attributeSet0);
        this.mFlow = new androidx.constraintlayout.core.widgets.Flow();
        if(attributeSet0 != null) {
            TypedArray typedArray0 = this.getContext().obtainStyledAttributes(attributeSet0, styleable.ConstraintLayout_Layout);
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                if(v2 == styleable.ConstraintLayout_Layout_android_orientation) {
                    this.mFlow.setOrientation(typedArray0.getInt(v2, 0));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_android_padding) {
                    this.mFlow.setPadding(typedArray0.getDimensionPixelSize(v2, 0));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_android_paddingStart) {
                    this.mFlow.setPaddingStart(typedArray0.getDimensionPixelSize(v2, 0));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_android_paddingEnd) {
                    this.mFlow.setPaddingEnd(typedArray0.getDimensionPixelSize(v2, 0));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_android_paddingLeft) {
                    this.mFlow.setPaddingLeft(typedArray0.getDimensionPixelSize(v2, 0));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_android_paddingTop) {
                    this.mFlow.setPaddingTop(typedArray0.getDimensionPixelSize(v2, 0));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_android_paddingRight) {
                    this.mFlow.setPaddingRight(typedArray0.getDimensionPixelSize(v2, 0));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_android_paddingBottom) {
                    this.mFlow.setPaddingBottom(typedArray0.getDimensionPixelSize(v2, 0));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_flow_wrapMode) {
                    this.mFlow.setWrapMode(typedArray0.getInt(v2, 0));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_flow_horizontalStyle) {
                    this.mFlow.setHorizontalStyle(typedArray0.getInt(v2, 0));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_flow_verticalStyle) {
                    this.mFlow.setVerticalStyle(typedArray0.getInt(v2, 0));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_flow_firstHorizontalStyle) {
                    this.mFlow.setFirstHorizontalStyle(typedArray0.getInt(v2, 0));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_flow_lastHorizontalStyle) {
                    this.mFlow.setLastHorizontalStyle(typedArray0.getInt(v2, 0));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_flow_firstVerticalStyle) {
                    this.mFlow.setFirstVerticalStyle(typedArray0.getInt(v2, 0));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_flow_lastVerticalStyle) {
                    this.mFlow.setLastVerticalStyle(typedArray0.getInt(v2, 0));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_flow_horizontalBias) {
                    this.mFlow.setHorizontalBias(typedArray0.getFloat(v2, 0.5f));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_flow_firstHorizontalBias) {
                    this.mFlow.setFirstHorizontalBias(typedArray0.getFloat(v2, 0.5f));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_flow_lastHorizontalBias) {
                    this.mFlow.setLastHorizontalBias(typedArray0.getFloat(v2, 0.5f));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_flow_firstVerticalBias) {
                    this.mFlow.setFirstVerticalBias(typedArray0.getFloat(v2, 0.5f));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_flow_lastVerticalBias) {
                    this.mFlow.setLastVerticalBias(typedArray0.getFloat(v2, 0.5f));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_flow_verticalBias) {
                    this.mFlow.setVerticalBias(typedArray0.getFloat(v2, 0.5f));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_flow_horizontalAlign) {
                    this.mFlow.setHorizontalAlign(typedArray0.getInt(v2, 2));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_flow_verticalAlign) {
                    this.mFlow.setVerticalAlign(typedArray0.getInt(v2, 2));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_flow_horizontalGap) {
                    this.mFlow.setHorizontalGap(typedArray0.getDimensionPixelSize(v2, 0));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_flow_verticalGap) {
                    this.mFlow.setVerticalGap(typedArray0.getDimensionPixelSize(v2, 0));
                }
                else if(v2 == styleable.ConstraintLayout_Layout_flow_maxElementsWrap) {
                    this.mFlow.setMaxElementsWrap(typedArray0.getInt(v2, -1));
                }
            }
            typedArray0.recycle();
        }
        this.mHelperWidget = this.mFlow;
        this.validateParams();
    }

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    public void loadParameters(Constraint constraintSet$Constraint0, HelperWidget helperWidget0, LayoutParams constraintLayout$LayoutParams0, SparseArray sparseArray0) {
        super.loadParameters(constraintSet$Constraint0, helperWidget0, constraintLayout$LayoutParams0, sparseArray0);
        if(helperWidget0 instanceof androidx.constraintlayout.core.widgets.Flow && constraintLayout$LayoutParams0.orientation != -1) {
            ((androidx.constraintlayout.core.widgets.Flow)helperWidget0).setOrientation(constraintLayout$LayoutParams0.orientation);
        }
    }

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    protected void onMeasure(int v, int v1) {
        this.onMeasure(this.mFlow, v, v1);
    }

    @Override  // androidx.constraintlayout.widget.VirtualLayout
    public void onMeasure(androidx.constraintlayout.core.widgets.VirtualLayout virtualLayout0, int v, int v1) {
        int v2 = View.MeasureSpec.getMode(v);
        int v3 = View.MeasureSpec.getSize(v);
        int v4 = View.MeasureSpec.getMode(v1);
        int v5 = View.MeasureSpec.getSize(v1);
        if(virtualLayout0 != null) {
            virtualLayout0.measure(v2, v3, v4, v5);
            this.setMeasuredDimension(virtualLayout0.getMeasuredWidth(), virtualLayout0.getMeasuredHeight());
            return;
        }
        this.setMeasuredDimension(0, 0);
    }

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    public void resolveRtl(ConstraintWidget constraintWidget0, boolean z) {
        this.mFlow.applyRtl(z);
    }

    public void setFirstHorizontalBias(float f) {
        this.mFlow.setFirstHorizontalBias(f);
        this.requestLayout();
    }

    public void setFirstHorizontalStyle(int v) {
        this.mFlow.setFirstHorizontalStyle(v);
        this.requestLayout();
    }

    public void setFirstVerticalBias(float f) {
        this.mFlow.setFirstVerticalBias(f);
        this.requestLayout();
    }

    public void setFirstVerticalStyle(int v) {
        this.mFlow.setFirstVerticalStyle(v);
        this.requestLayout();
    }

    public void setHorizontalAlign(int v) {
        this.mFlow.setHorizontalAlign(v);
        this.requestLayout();
    }

    public void setHorizontalBias(float f) {
        this.mFlow.setHorizontalBias(f);
        this.requestLayout();
    }

    public void setHorizontalGap(int v) {
        this.mFlow.setHorizontalGap(v);
        this.requestLayout();
    }

    public void setHorizontalStyle(int v) {
        this.mFlow.setHorizontalStyle(v);
        this.requestLayout();
    }

    public void setLastHorizontalBias(float f) {
        this.mFlow.setLastHorizontalBias(f);
        this.requestLayout();
    }

    public void setLastHorizontalStyle(int v) {
        this.mFlow.setLastHorizontalStyle(v);
        this.requestLayout();
    }

    public void setLastVerticalBias(float f) {
        this.mFlow.setLastVerticalBias(f);
        this.requestLayout();
    }

    public void setLastVerticalStyle(int v) {
        this.mFlow.setLastVerticalStyle(v);
        this.requestLayout();
    }

    public void setMaxElementsWrap(int v) {
        this.mFlow.setMaxElementsWrap(v);
        this.requestLayout();
    }

    public void setOrientation(int v) {
        this.mFlow.setOrientation(v);
        this.requestLayout();
    }

    public void setPadding(int v) {
        this.mFlow.setPadding(v);
        this.requestLayout();
    }

    public void setPaddingBottom(int v) {
        this.mFlow.setPaddingBottom(v);
        this.requestLayout();
    }

    public void setPaddingLeft(int v) {
        this.mFlow.setPaddingLeft(v);
        this.requestLayout();
    }

    public void setPaddingRight(int v) {
        this.mFlow.setPaddingRight(v);
        this.requestLayout();
    }

    public void setPaddingTop(int v) {
        this.mFlow.setPaddingTop(v);
        this.requestLayout();
    }

    public void setVerticalAlign(int v) {
        this.mFlow.setVerticalAlign(v);
        this.requestLayout();
    }

    public void setVerticalBias(float f) {
        this.mFlow.setVerticalBias(f);
        this.requestLayout();
    }

    public void setVerticalGap(int v) {
        this.mFlow.setVerticalGap(v);
        this.requestLayout();
    }

    public void setVerticalStyle(int v) {
        this.mFlow.setVerticalStyle(v);
        this.requestLayout();
    }

    public void setWrapMode(int v) {
        this.mFlow.setWrapMode(v);
        this.requestLayout();
    }
}

