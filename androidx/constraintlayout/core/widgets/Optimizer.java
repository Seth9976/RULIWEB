package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;

public class Optimizer {
    static final int FLAG_CHAIN_DANGLING = 1;
    static final int FLAG_RECOMPUTE_BOUNDS = 2;
    static final int FLAG_USE_OPTIMIZE = 0;
    public static final int OPTIMIZATION_BARRIER = 2;
    public static final int OPTIMIZATION_CACHE_MEASURES = 0x100;
    public static final int OPTIMIZATION_CHAIN = 4;
    public static final int OPTIMIZATION_DEPENDENCY_ORDERING = 0x200;
    public static final int OPTIMIZATION_DIMENSIONS = 8;
    public static final int OPTIMIZATION_DIRECT = 1;
    public static final int OPTIMIZATION_GRAPH = 0x40;
    public static final int OPTIMIZATION_GRAPH_WRAP = 0x80;
    public static final int OPTIMIZATION_GROUPING = 0x400;
    public static final int OPTIMIZATION_GROUPS = 0x20;
    public static final int OPTIMIZATION_NONE = 0;
    public static final int OPTIMIZATION_RATIO = 16;
    public static final int OPTIMIZATION_STANDARD = 0x101;
    static boolean[] sFlags;

    static {
        Optimizer.sFlags = new boolean[3];
    }

    static void checkMatchParent(ConstraintWidgetContainer constraintWidgetContainer0, LinearSystem linearSystem0, ConstraintWidget constraintWidget0) {
        constraintWidget0.mHorizontalResolution = -1;
        constraintWidget0.mVerticalResolution = -1;
        if(constraintWidgetContainer0.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT && constraintWidget0.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_PARENT) {
            int v = constraintWidget0.mLeft.mMargin;
            int v1 = constraintWidgetContainer0.getWidth() - constraintWidget0.mRight.mMargin;
            ConstraintAnchor constraintAnchor0 = constraintWidget0.mLeft;
            constraintAnchor0.mSolverVariable = linearSystem0.createObjectVariable(constraintWidget0.mLeft);
            ConstraintAnchor constraintAnchor1 = constraintWidget0.mRight;
            constraintAnchor1.mSolverVariable = linearSystem0.createObjectVariable(constraintWidget0.mRight);
            linearSystem0.addEquality(constraintWidget0.mLeft.mSolverVariable, v);
            linearSystem0.addEquality(constraintWidget0.mRight.mSolverVariable, v1);
            constraintWidget0.mHorizontalResolution = 2;
            constraintWidget0.setHorizontalDimension(v, v1);
        }
        if(constraintWidgetContainer0.mListDimensionBehaviors[1] != DimensionBehaviour.WRAP_CONTENT && constraintWidget0.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_PARENT) {
            int v2 = constraintWidget0.mTop.mMargin;
            int v3 = constraintWidgetContainer0.getHeight() - constraintWidget0.mBottom.mMargin;
            ConstraintAnchor constraintAnchor2 = constraintWidget0.mTop;
            constraintAnchor2.mSolverVariable = linearSystem0.createObjectVariable(constraintWidget0.mTop);
            ConstraintAnchor constraintAnchor3 = constraintWidget0.mBottom;
            constraintAnchor3.mSolverVariable = linearSystem0.createObjectVariable(constraintWidget0.mBottom);
            linearSystem0.addEquality(constraintWidget0.mTop.mSolverVariable, v2);
            linearSystem0.addEquality(constraintWidget0.mBottom.mSolverVariable, v3);
            if(constraintWidget0.mBaselineDistance > 0 || constraintWidget0.getVisibility() == 8) {
                ConstraintAnchor constraintAnchor4 = constraintWidget0.mBaseline;
                constraintAnchor4.mSolverVariable = linearSystem0.createObjectVariable(constraintWidget0.mBaseline);
                linearSystem0.addEquality(constraintWidget0.mBaseline.mSolverVariable, constraintWidget0.mBaselineDistance + v2);
            }
            constraintWidget0.mVerticalResolution = 2;
            constraintWidget0.setVerticalDimension(v2, v3);
        }
    }

    public static final boolean enabled(int v, int v1) {
        return (v & v1) == v1;
    }
}

