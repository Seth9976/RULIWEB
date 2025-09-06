package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measure;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure.Measurer;
import java.util.HashSet;

public class VirtualLayout extends HelperWidget {
    protected Measure mMeasure;
    private int mMeasuredHeight;
    private int mMeasuredWidth;
    Measurer mMeasurer;
    private boolean mNeedsCallFromSolver;
    private int mPaddingBottom;
    private int mPaddingEnd;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingStart;
    private int mPaddingTop;
    private int mResolvedPaddingLeft;
    private int mResolvedPaddingRight;

    public VirtualLayout() {
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingStart = 0;
        this.mPaddingEnd = 0;
        this.mResolvedPaddingLeft = 0;
        this.mResolvedPaddingRight = 0;
        this.mNeedsCallFromSolver = false;
        this.mMeasuredWidth = 0;
        this.mMeasuredHeight = 0;
        this.mMeasure = new Measure();
        this.mMeasurer = null;
    }

    public void applyRtl(boolean z) {
        int v = this.mPaddingStart;
        if(v <= 0 && this.mPaddingEnd <= 0) {
            return;
        }
        if(z) {
            this.mResolvedPaddingLeft = this.mPaddingEnd;
            this.mResolvedPaddingRight = v;
            return;
        }
        this.mResolvedPaddingLeft = v;
        this.mResolvedPaddingRight = this.mPaddingEnd;
    }

    public void captureWidgets() {
        for(int v = 0; v < this.mWidgetsCount; ++v) {
            ConstraintWidget constraintWidget0 = this.mWidgets[v];
            if(constraintWidget0 != null) {
                constraintWidget0.setInVirtualLayout(true);
            }
        }
    }

    public boolean contains(HashSet hashSet0) {
        for(int v = 0; v < this.mWidgetsCount; ++v) {
            if(hashSet0.contains(this.mWidgets[v])) {
                return true;
            }
        }
        return false;
    }

    public int getMeasuredHeight() {
        return this.mMeasuredHeight;
    }

    public int getMeasuredWidth() {
        return this.mMeasuredWidth;
    }

    public int getPaddingBottom() {
        return this.mPaddingBottom;
    }

    public int getPaddingLeft() {
        return this.mResolvedPaddingLeft;
    }

    public int getPaddingRight() {
        return this.mResolvedPaddingRight;
    }

    public int getPaddingTop() {
        return this.mPaddingTop;
    }

    public void measure(int v, int v1, int v2, int v3) {
    }

    protected void measure(ConstraintWidget constraintWidget0, DimensionBehaviour constraintWidget$DimensionBehaviour0, int v, DimensionBehaviour constraintWidget$DimensionBehaviour1, int v1) {
        while(this.mMeasurer == null && this.getParent() != null) {
            this.mMeasurer = ((ConstraintWidgetContainer)this.getParent()).getMeasurer();
        }
        this.mMeasure.horizontalBehavior = constraintWidget$DimensionBehaviour0;
        this.mMeasure.verticalBehavior = constraintWidget$DimensionBehaviour1;
        this.mMeasure.horizontalDimension = v;
        this.mMeasure.verticalDimension = v1;
        this.mMeasurer.measure(constraintWidget0, this.mMeasure);
        constraintWidget0.setWidth(this.mMeasure.measuredWidth);
        constraintWidget0.setHeight(this.mMeasure.measuredHeight);
        constraintWidget0.setHasBaseline(this.mMeasure.measuredHasBaseline);
        constraintWidget0.setBaselineDistance(this.mMeasure.measuredBaseline);
    }

    protected boolean measureChildren() {
        Measurer basicMeasure$Measurer0 = this.mParent == null ? null : ((ConstraintWidgetContainer)this.mParent).getMeasurer();
        if(basicMeasure$Measurer0 == null) {
            return false;
        }
        for(int v = 0; v < this.mWidgetsCount; ++v) {
            ConstraintWidget constraintWidget0 = this.mWidgets[v];
            if(constraintWidget0 != null && !(constraintWidget0 instanceof Guideline)) {
                DimensionBehaviour constraintWidget$DimensionBehaviour0 = constraintWidget0.getDimensionBehaviour(0);
                DimensionBehaviour constraintWidget$DimensionBehaviour1 = constraintWidget0.getDimensionBehaviour(1);
                if(constraintWidget$DimensionBehaviour0 != DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget0.mMatchConstraintDefaultWidth == 1 || constraintWidget$DimensionBehaviour1 != DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget0.mMatchConstraintDefaultHeight == 1) {
                    if(constraintWidget$DimensionBehaviour0 == DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget$DimensionBehaviour0 = DimensionBehaviour.WRAP_CONTENT;
                    }
                    if(constraintWidget$DimensionBehaviour1 == DimensionBehaviour.MATCH_CONSTRAINT) {
                        constraintWidget$DimensionBehaviour1 = DimensionBehaviour.WRAP_CONTENT;
                    }
                    this.mMeasure.horizontalBehavior = constraintWidget$DimensionBehaviour0;
                    this.mMeasure.verticalBehavior = constraintWidget$DimensionBehaviour1;
                    this.mMeasure.horizontalDimension = constraintWidget0.getWidth();
                    this.mMeasure.verticalDimension = constraintWidget0.getHeight();
                    basicMeasure$Measurer0.measure(constraintWidget0, this.mMeasure);
                    constraintWidget0.setWidth(this.mMeasure.measuredWidth);
                    constraintWidget0.setHeight(this.mMeasure.measuredHeight);
                    constraintWidget0.setBaselineDistance(this.mMeasure.measuredBaseline);
                }
            }
        }
        return true;
    }

    public boolean needSolverPass() {
        return this.mNeedsCallFromSolver;
    }

    protected void needsCallbackFromSolver(boolean z) {
        this.mNeedsCallFromSolver = z;
    }

    public void setMeasure(int v, int v1) {
        this.mMeasuredWidth = v;
        this.mMeasuredHeight = v1;
    }

    public void setPadding(int v) {
        this.mPaddingLeft = v;
        this.mPaddingTop = v;
        this.mPaddingRight = v;
        this.mPaddingBottom = v;
        this.mPaddingStart = v;
        this.mPaddingEnd = v;
    }

    public void setPaddingBottom(int v) {
        this.mPaddingBottom = v;
    }

    public void setPaddingEnd(int v) {
        this.mPaddingEnd = v;
    }

    public void setPaddingLeft(int v) {
        this.mPaddingLeft = v;
        this.mResolvedPaddingLeft = v;
    }

    public void setPaddingRight(int v) {
        this.mPaddingRight = v;
        this.mResolvedPaddingRight = v;
    }

    public void setPaddingStart(int v) {
        this.mPaddingStart = v;
        this.mResolvedPaddingLeft = v;
        this.mResolvedPaddingRight = v;
    }

    public void setPaddingTop(int v) {
        this.mPaddingTop = v;
    }

    @Override  // androidx.constraintlayout.core.widgets.HelperWidget
    public void updateConstraints(ConstraintWidgetContainer constraintWidgetContainer0) {
        this.captureWidgets();
    }
}

