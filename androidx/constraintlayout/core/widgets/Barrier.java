package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import java.util.HashMap;

public class Barrier extends HelperWidget {
    public static final int BOTTOM = 3;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int TOP = 2;
    private static final boolean USE_RELAX_GONE = false;
    private static final boolean USE_RESOLUTION = true;
    private boolean mAllowsGoneWidget;
    private int mBarrierType;
    private int mMargin;
    boolean mResolved;

    public Barrier() {
        this.mBarrierType = 0;
        this.mAllowsGoneWidget = true;
        this.mMargin = 0;
        this.mResolved = false;
    }

    public Barrier(String s) {
        this.mBarrierType = 0;
        this.mAllowsGoneWidget = true;
        this.mMargin = 0;
        this.mResolved = false;
        this.setDebugName(s);
    }

    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void addToSolver(LinearSystem linearSystem0, boolean z) {
        boolean z1;
        this.mListAnchors[0] = this.mLeft;
        this.mListAnchors[2] = this.mTop;
        this.mListAnchors[1] = this.mRight;
        this.mListAnchors[3] = this.mBottom;
        for(int v = 0; v < this.mListAnchors.length; ++v) {
            ConstraintAnchor constraintAnchor0 = this.mListAnchors[v];
            constraintAnchor0.mSolverVariable = linearSystem0.createObjectVariable(this.mListAnchors[v]);
        }
        if(this.mBarrierType >= 0 && this.mBarrierType < 4) {
            ConstraintAnchor constraintAnchor1 = this.mListAnchors[this.mBarrierType];
            if(!this.mResolved) {
                this.allSolved();
            }
            if(this.mResolved) {
                this.mResolved = false;
                int v1 = this.mBarrierType;
                if(v1 == 0 || v1 == 1) {
                    linearSystem0.addEquality(this.mLeft.mSolverVariable, this.mX);
                    linearSystem0.addEquality(this.mRight.mSolverVariable, this.mX);
                }
                else if(v1 == 2 || v1 == 3) {
                    linearSystem0.addEquality(this.mTop.mSolverVariable, this.mY);
                    linearSystem0.addEquality(this.mBottom.mSolverVariable, this.mY);
                }
            }
            else {
                for(int v2 = 0; true; ++v2) {
                    z1 = false;
                    if(v2 >= this.mWidgetsCount) {
                        break;
                    }
                    ConstraintWidget constraintWidget0 = this.mWidgets[v2];
                    if((this.mAllowsGoneWidget || constraintWidget0.allowedInBarrier()) && ((this.mBarrierType == 0 || this.mBarrierType == 1) && constraintWidget0.getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget0.mLeft.mTarget != null && constraintWidget0.mRight.mTarget != null || (this.mBarrierType == 2 || this.mBarrierType == 3) && constraintWidget0.getVerticalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget0.mTop.mTarget != null && constraintWidget0.mBottom.mTarget != null)) {
                        z1 = true;
                        break;
                    }
                }
                boolean z2 = this.mLeft.hasCenteredDependents() || this.mRight.hasCenteredDependents();
                boolean z3 = this.mTop.hasCenteredDependents() || this.mBottom.hasCenteredDependents();
                int v3 = z1 || (this.mBarrierType != 0 || !z2) && (this.mBarrierType != 2 || !z3) && (this.mBarrierType != 1 || !z2) && (this.mBarrierType != 3 || !z3) ? 4 : 5;
                for(int v4 = 0; v4 < this.mWidgetsCount; ++v4) {
                    ConstraintWidget constraintWidget1 = this.mWidgets[v4];
                    if(this.mAllowsGoneWidget || constraintWidget1.allowedInBarrier()) {
                        SolverVariable solverVariable0 = linearSystem0.createObjectVariable(constraintWidget1.mListAnchors[this.mBarrierType]);
                        constraintWidget1.mListAnchors[this.mBarrierType].mSolverVariable = solverVariable0;
                        int v5 = constraintWidget1.mListAnchors[this.mBarrierType].mTarget == null || constraintWidget1.mListAnchors[this.mBarrierType].mTarget.mOwner != this ? 0 : constraintWidget1.mListAnchors[this.mBarrierType].mMargin;
                        if(this.mBarrierType == 0 || this.mBarrierType == 2) {
                            linearSystem0.addLowerBarrier(constraintAnchor1.mSolverVariable, solverVariable0, this.mMargin - v5, z1);
                        }
                        else {
                            linearSystem0.addGreaterBarrier(constraintAnchor1.mSolverVariable, solverVariable0, this.mMargin + v5, z1);
                        }
                        linearSystem0.addEquality(constraintAnchor1.mSolverVariable, solverVariable0, this.mMargin + v5, v3);
                    }
                }
                int v6 = this.mBarrierType;
                if(v6 == 0) {
                    linearSystem0.addEquality(this.mRight.mSolverVariable, this.mLeft.mSolverVariable, 0, 8);
                    linearSystem0.addEquality(this.mLeft.mSolverVariable, this.mParent.mRight.mSolverVariable, 0, 4);
                    linearSystem0.addEquality(this.mLeft.mSolverVariable, this.mParent.mLeft.mSolverVariable, 0, 0);
                    return;
                }
                if(v6 == 1) {
                    linearSystem0.addEquality(this.mLeft.mSolverVariable, this.mRight.mSolverVariable, 0, 8);
                    linearSystem0.addEquality(this.mLeft.mSolverVariable, this.mParent.mLeft.mSolverVariable, 0, 4);
                    linearSystem0.addEquality(this.mLeft.mSolverVariable, this.mParent.mRight.mSolverVariable, 0, 0);
                    return;
                }
                if(v6 == 2) {
                    linearSystem0.addEquality(this.mBottom.mSolverVariable, this.mTop.mSolverVariable, 0, 8);
                    linearSystem0.addEquality(this.mTop.mSolverVariable, this.mParent.mBottom.mSolverVariable, 0, 4);
                    linearSystem0.addEquality(this.mTop.mSolverVariable, this.mParent.mTop.mSolverVariable, 0, 0);
                    return;
                }
                if(v6 == 3) {
                    linearSystem0.addEquality(this.mTop.mSolverVariable, this.mBottom.mSolverVariable, 0, 8);
                    linearSystem0.addEquality(this.mTop.mSolverVariable, this.mParent.mTop.mSolverVariable, 0, 4);
                    linearSystem0.addEquality(this.mTop.mSolverVariable, this.mParent.mBottom.mSolverVariable, 0, 0);
                }
            }
        }
    }

    public boolean allSolved() {
        boolean z = true;
        for(int v1 = 0; v1 < this.mWidgetsCount; ++v1) {
            ConstraintWidget constraintWidget0 = this.mWidgets[v1];
            if(this.mAllowsGoneWidget || constraintWidget0.allowedInBarrier()) {
                if((this.mBarrierType == 0 || this.mBarrierType == 1) && !constraintWidget0.isResolvedHorizontally()) {
                    z = false;
                }
                else if((this.mBarrierType == 2 || this.mBarrierType == 3) && !constraintWidget0.isResolvedVertically()) {
                    z = false;
                }
            }
        }
        if(z && this.mWidgetsCount > 0) {
            int v2 = 0;
            boolean z1 = false;
            for(int v = 0; v < this.mWidgetsCount; ++v) {
                ConstraintWidget constraintWidget1 = this.mWidgets[v];
                if(this.mAllowsGoneWidget || constraintWidget1.allowedInBarrier()) {
                    if(!z1) {
                        int v3 = this.mBarrierType;
                        if(v3 == 0) {
                            v2 = constraintWidget1.getAnchor(Type.LEFT).getFinalValue();
                        }
                        else if(v3 == 1) {
                            v2 = constraintWidget1.getAnchor(Type.RIGHT).getFinalValue();
                        }
                        else if(v3 == 2) {
                            v2 = constraintWidget1.getAnchor(Type.TOP).getFinalValue();
                        }
                        else if(v3 == 3) {
                            v2 = constraintWidget1.getAnchor(Type.BOTTOM).getFinalValue();
                        }
                        z1 = true;
                    }
                    int v4 = this.mBarrierType;
                    if(v4 == 0) {
                        v2 = Math.min(v2, constraintWidget1.getAnchor(Type.LEFT).getFinalValue());
                    }
                    else if(v4 == 1) {
                        v2 = Math.max(v2, constraintWidget1.getAnchor(Type.RIGHT).getFinalValue());
                    }
                    else if(v4 == 2) {
                        v2 = Math.min(v2, constraintWidget1.getAnchor(Type.TOP).getFinalValue());
                    }
                    else if(v4 == 3) {
                        v2 = Math.max(v2, constraintWidget1.getAnchor(Type.BOTTOM).getFinalValue());
                    }
                }
            }
            int v5 = v2 + this.mMargin;
            if(this.mBarrierType == 0 || this.mBarrierType == 1) {
                this.setFinalHorizontal(v5, v5);
            }
            else {
                this.setFinalVertical(v5, v5);
            }
            this.mResolved = true;
            return true;
        }
        return false;
    }

    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean allowedInBarrier() {
        return true;
    }

    @Deprecated
    public boolean allowsGoneWidget() {
        return this.mAllowsGoneWidget;
    }

    @Override  // androidx.constraintlayout.core.widgets.HelperWidget
    public void copy(ConstraintWidget constraintWidget0, HashMap hashMap0) {
        super.copy(constraintWidget0, hashMap0);
        this.mBarrierType = ((Barrier)constraintWidget0).mBarrierType;
        this.mAllowsGoneWidget = ((Barrier)constraintWidget0).mAllowsGoneWidget;
        this.mMargin = ((Barrier)constraintWidget0).mMargin;
    }

    public boolean getAllowsGoneWidget() {
        return this.mAllowsGoneWidget;
    }

    public int getBarrierType() {
        return this.mBarrierType;
    }

    public int getMargin() {
        return this.mMargin;
    }

    public int getOrientation() {
        switch(this.mBarrierType) {
            case 0: 
            case 1: {
                return 0;
            }
            case 2: 
            case 3: {
                return 1;
            }
            default: {
                return -1;
            }
        }
    }

    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean isResolvedHorizontally() {
        return this.mResolved;
    }

    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public boolean isResolvedVertically() {
        return this.mResolved;
    }

    protected void markWidgets() {
        for(int v = 0; v < this.mWidgetsCount; ++v) {
            ConstraintWidget constraintWidget0 = this.mWidgets[v];
            if(this.mAllowsGoneWidget || constraintWidget0.allowedInBarrier()) {
                int v1 = this.mBarrierType;
                if(v1 == 0 || v1 == 1) {
                    constraintWidget0.setInBarrier(0, true);
                }
                else if(v1 == 2 || v1 == 3) {
                    constraintWidget0.setInBarrier(1, true);
                }
            }
        }
    }

    public void setAllowsGoneWidget(boolean z) {
        this.mAllowsGoneWidget = z;
    }

    public void setBarrierType(int v) {
        this.mBarrierType = v;
    }

    public void setMargin(int v) {
        this.mMargin = v;
    }

    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public String toString() {
        String s = "[Barrier] " + this.getDebugName() + " {";
        for(int v = 0; v < this.mWidgetsCount; ++v) {
            ConstraintWidget constraintWidget0 = this.mWidgets[v];
            if(v > 0) {
                s = s + ", ";
            }
            s = s + constraintWidget0.getDebugName();
        }
        return s + "}";
    }
}

