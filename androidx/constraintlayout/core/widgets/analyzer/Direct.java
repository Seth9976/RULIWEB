package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ChainHead;
import androidx.constraintlayout.core.widgets.ConstraintAnchor.Type;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import java.util.ArrayList;

public class Direct {
    private static final boolean APPLY_MATCH_PARENT = false;
    private static final boolean DEBUG = false;
    private static final boolean EARLY_TERMINATION = true;
    private static int sHcount;
    private static Measure sMeasure;
    private static int sVcount;

    static {
        Direct.sMeasure = new Measure();
        Direct.sHcount = 0;
        Direct.sVcount = 0;
    }

    private static boolean canMeasure(int v, ConstraintWidget constraintWidget0) {
        DimensionBehaviour constraintWidget$DimensionBehaviour0 = constraintWidget0.getHorizontalDimensionBehaviour();
        DimensionBehaviour constraintWidget$DimensionBehaviour1 = constraintWidget0.getVerticalDimensionBehaviour();
        if(constraintWidget0.getParent() != null) {
            ConstraintWidgetContainer constraintWidgetContainer0 = (ConstraintWidgetContainer)constraintWidget0.getParent();
        }
        boolean z = constraintWidget$DimensionBehaviour0 == DimensionBehaviour.FIXED || constraintWidget0.isResolvedHorizontally() || constraintWidget$DimensionBehaviour0 == DimensionBehaviour.WRAP_CONTENT || constraintWidget$DimensionBehaviour0 == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget0.mMatchConstraintDefaultWidth == 0 && constraintWidget0.mDimensionRatio == 0.0f && constraintWidget0.hasDanglingDimension(0) || constraintWidget$DimensionBehaviour0 == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget0.mMatchConstraintDefaultWidth == 1 && constraintWidget0.hasResolvedTargets(0, constraintWidget0.getWidth());
        boolean z1 = constraintWidget$DimensionBehaviour1 == DimensionBehaviour.FIXED || constraintWidget0.isResolvedVertically() || constraintWidget$DimensionBehaviour1 == DimensionBehaviour.WRAP_CONTENT || constraintWidget$DimensionBehaviour1 == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget0.mMatchConstraintDefaultHeight == 0 && constraintWidget0.mDimensionRatio == 0.0f && constraintWidget0.hasDanglingDimension(1) || constraintWidget$DimensionBehaviour1 == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget0.mMatchConstraintDefaultHeight == 1 && constraintWidget0.hasResolvedTargets(1, constraintWidget0.getHeight());
        return constraintWidget0.mDimensionRatio <= 0.0f || !z && !z1 ? z && z1 : true;
    }

    private static void horizontalSolvingPass(int v, ConstraintWidget constraintWidget0, Measurer basicMeasure$Measurer0, boolean z) {
        if(!constraintWidget0.isHorizontalSolvingPassDone()) {
            ++Direct.sHcount;
            if(!(constraintWidget0 instanceof ConstraintWidgetContainer) && constraintWidget0.isMeasureRequested() && Direct.canMeasure(v + 1, constraintWidget0)) {
                ConstraintWidgetContainer.measure(v + 1, constraintWidget0, basicMeasure$Measurer0, new Measure(), Measure.SELF_DIMENSIONS);
            }
            ConstraintAnchor constraintAnchor0 = constraintWidget0.getAnchor(Type.LEFT);
            ConstraintAnchor constraintAnchor1 = constraintWidget0.getAnchor(Type.RIGHT);
            int v1 = constraintAnchor0.getFinalValue();
            int v2 = constraintAnchor1.getFinalValue();
            if(constraintAnchor0.getDependents() != null && constraintAnchor0.hasFinalValue()) {
                for(Object object0: constraintAnchor0.getDependents()) {
                    ConstraintAnchor constraintAnchor2 = (ConstraintAnchor)object0;
                    ConstraintWidget constraintWidget1 = constraintAnchor2.mOwner;
                    boolean z1 = Direct.canMeasure(v + 1, constraintWidget1);
                    if(constraintWidget1.isMeasureRequested() && z1) {
                        ConstraintWidgetContainer.measure(v + 1, constraintWidget1, basicMeasure$Measurer0, new Measure(), Measure.SELF_DIMENSIONS);
                    }
                    boolean z2 = constraintAnchor2 == constraintWidget1.mLeft && constraintWidget1.mRight.mTarget != null && constraintWidget1.mRight.mTarget.hasFinalValue() || constraintAnchor2 == constraintWidget1.mRight && constraintWidget1.mLeft.mTarget != null && constraintWidget1.mLeft.mTarget.hasFinalValue();
                    if(constraintWidget1.getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT && !z1) {
                        if(constraintWidget1.getHorizontalDimensionBehaviour() != DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget1.mMatchConstraintMaxWidth < 0 || constraintWidget1.mMatchConstraintMinWidth < 0 || constraintWidget1.getVisibility() != 8 && (constraintWidget1.mMatchConstraintDefaultWidth != 0 || constraintWidget1.getDimensionRatio() != 0.0f) || constraintWidget1.isInHorizontalChain() || constraintWidget1.isInVirtualLayout() || !z2 || constraintWidget1.isInHorizontalChain()) {
                            continue;
                        }
                        Direct.solveHorizontalMatchConstraint(v + 1, constraintWidget0, basicMeasure$Measurer0, constraintWidget1, z);
                    }
                    else if(constraintWidget1.isMeasureRequested()) {
                    }
                    else if(constraintAnchor2 == constraintWidget1.mLeft && constraintWidget1.mRight.mTarget == null) {
                        int v3 = constraintWidget1.mLeft.getMargin();
                        constraintWidget1.setFinalHorizontal(v3 + v1, constraintWidget1.getWidth() + (v3 + v1));
                        Direct.horizontalSolvingPass(v + 1, constraintWidget1, basicMeasure$Measurer0, z);
                    }
                    else if(constraintAnchor2 == constraintWidget1.mRight && constraintWidget1.mLeft.mTarget == null) {
                        int v4 = constraintWidget1.mRight.getMargin();
                        constraintWidget1.setFinalHorizontal(v1 - v4 - constraintWidget1.getWidth(), v1 - v4);
                        Direct.horizontalSolvingPass(v + 1, constraintWidget1, basicMeasure$Measurer0, z);
                    }
                    else if(z2 && !constraintWidget1.isInHorizontalChain()) {
                        Direct.solveHorizontalCenterConstraints(v + 1, basicMeasure$Measurer0, constraintWidget1, z);
                    }
                }
            }
            if(!(constraintWidget0 instanceof Guideline)) {
                if(constraintAnchor1.getDependents() != null && constraintAnchor1.hasFinalValue()) {
                    for(Object object1: constraintAnchor1.getDependents()) {
                        ConstraintAnchor constraintAnchor3 = (ConstraintAnchor)object1;
                        ConstraintWidget constraintWidget2 = constraintAnchor3.mOwner;
                        boolean z3 = Direct.canMeasure(v + 1, constraintWidget2);
                        if(constraintWidget2.isMeasureRequested() && z3) {
                            ConstraintWidgetContainer.measure(v + 1, constraintWidget2, basicMeasure$Measurer0, new Measure(), Measure.SELF_DIMENSIONS);
                        }
                        boolean z4 = constraintAnchor3 == constraintWidget2.mLeft && constraintWidget2.mRight.mTarget != null && constraintWidget2.mRight.mTarget.hasFinalValue() || constraintAnchor3 == constraintWidget2.mRight && constraintWidget2.mLeft.mTarget != null && constraintWidget2.mLeft.mTarget.hasFinalValue();
                        if(constraintWidget2.getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT && !z3) {
                            if(constraintWidget2.getHorizontalDimensionBehaviour() != DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget2.mMatchConstraintMaxWidth < 0 || constraintWidget2.mMatchConstraintMinWidth < 0 || constraintWidget2.getVisibility() != 8 && (constraintWidget2.mMatchConstraintDefaultWidth != 0 || constraintWidget2.getDimensionRatio() != 0.0f) || constraintWidget2.isInHorizontalChain() || constraintWidget2.isInVirtualLayout() || !z4 || constraintWidget2.isInHorizontalChain()) {
                                continue;
                            }
                            Direct.solveHorizontalMatchConstraint(v + 1, constraintWidget0, basicMeasure$Measurer0, constraintWidget2, z);
                        }
                        else if(constraintWidget2.isMeasureRequested()) {
                        }
                        else if(constraintAnchor3 == constraintWidget2.mLeft && constraintWidget2.mRight.mTarget == null) {
                            int v5 = constraintWidget2.mLeft.getMargin();
                            constraintWidget2.setFinalHorizontal(v5 + v2, constraintWidget2.getWidth() + (v5 + v2));
                            Direct.horizontalSolvingPass(v + 1, constraintWidget2, basicMeasure$Measurer0, z);
                        }
                        else if(constraintAnchor3 == constraintWidget2.mRight && constraintWidget2.mLeft.mTarget == null) {
                            int v6 = constraintWidget2.mRight.getMargin();
                            constraintWidget2.setFinalHorizontal(v2 - v6 - constraintWidget2.getWidth(), v2 - v6);
                            Direct.horizontalSolvingPass(v + 1, constraintWidget2, basicMeasure$Measurer0, z);
                        }
                        else if(z4 && !constraintWidget2.isInHorizontalChain()) {
                            Direct.solveHorizontalCenterConstraints(v + 1, basicMeasure$Measurer0, constraintWidget2, z);
                        }
                    }
                }
                constraintWidget0.markHorizontalSolvingPassDone();
            }
        }
    }

    public static String ls(int v) {
        StringBuilder stringBuilder0 = new StringBuilder();
        for(int v1 = 0; v1 < v; ++v1) {
            stringBuilder0.append("  ");
        }
        stringBuilder0.append("+-(" + v + ") ");
        return stringBuilder0.toString();
    }

    private static void solveBarrier(int v, Barrier barrier0, Measurer basicMeasure$Measurer0, int v1, boolean z) {
        if(barrier0.allSolved()) {
            if(v1 == 0) {
                Direct.horizontalSolvingPass(v + 1, barrier0, basicMeasure$Measurer0, z);
                return;
            }
            Direct.verticalSolvingPass(v + 1, barrier0, basicMeasure$Measurer0);
        }
    }

    public static boolean solveChain(ConstraintWidgetContainer constraintWidgetContainer0, LinearSystem linearSystem0, int v, int v1, ChainHead chainHead0, boolean z, boolean z1, boolean z2) {
        ConstraintWidget constraintWidget10;
        int v12;
        ConstraintWidget constraintWidget6;
        if(z2) {
            return false;
        }
        if(v == 0) {
            if(!constraintWidgetContainer0.isResolvedHorizontally()) {
                return false;
            }
        }
        else if(!constraintWidgetContainer0.isResolvedVertically()) {
            return false;
        }
        boolean z3 = constraintWidgetContainer0.isRtl();
        ConstraintWidget constraintWidget0 = chainHead0.getFirst();
        ConstraintWidget constraintWidget1 = chainHead0.getFirstVisibleWidget();
        ConstraintWidget constraintWidget2 = chainHead0.getLastVisibleWidget();
        ConstraintWidget constraintWidget3 = chainHead0.getHead();
        ConstraintAnchor constraintAnchor0 = constraintWidget0.mListAnchors[v1];
        ConstraintAnchor constraintAnchor1 = chainHead0.getLast().mListAnchors[v1 + 1];
        if(constraintAnchor0.mTarget != null && constraintAnchor1.mTarget != null && constraintAnchor0.mTarget.hasFinalValue() && constraintAnchor1.mTarget.hasFinalValue() && constraintWidget1 != null && constraintWidget2 != null) {
            int v2 = constraintAnchor0.mTarget.getFinalValue() + constraintWidget1.mListAnchors[v1].getMargin();
            int v3 = constraintAnchor1.mTarget.getFinalValue() - constraintWidget2.mListAnchors[v1 + 1].getMargin();
            int v4 = v3 - v2;
            if(v4 <= 0) {
                return false;
            }
            Measure basicMeasure$Measure0 = new Measure();
            ConstraintWidget constraintWidget4 = constraintWidget0;
            boolean z4 = false;
            int v5 = 0;
            int v6 = 0;
            int v7 = 0;
            while(true) {
                ConstraintWidget constraintWidget5 = null;
                if(z4) {
                    break;
                }
                if(!Direct.canMeasure(1, constraintWidget4)) {
                    return false;
                }
                if(constraintWidget4.mListDimensionBehaviors[v] == DimensionBehaviour.MATCH_CONSTRAINT) {
                    return false;
                }
                if(constraintWidget4.isMeasureRequested()) {
                    constraintWidget6 = constraintWidget3;
                    ConstraintWidgetContainer.measure(1, constraintWidget4, constraintWidgetContainer0.getMeasurer(), basicMeasure$Measure0, Measure.SELF_DIMENSIONS);
                }
                else {
                    constraintWidget6 = constraintWidget3;
                }
                v7 = v7 + constraintWidget4.mListAnchors[v1].getMargin() + (v == 0 ? constraintWidget4.getWidth() : constraintWidget4.getHeight()) + constraintWidget4.mListAnchors[v1 + 1].getMargin();
                ++v6;
                if(constraintWidget4.getVisibility() != 8) {
                    ++v5;
                }
                ConstraintAnchor constraintAnchor2 = constraintWidget4.mListAnchors[v1 + 1].mTarget;
                if(constraintAnchor2 != null) {
                    ConstraintWidget constraintWidget7 = constraintAnchor2.mOwner;
                    if(constraintWidget7.mListAnchors[v1].mTarget != null && constraintWidget7.mListAnchors[v1].mTarget.mOwner == constraintWidget4) {
                        constraintWidget5 = constraintWidget7;
                    }
                }
                if(constraintWidget5 == null) {
                    z4 = true;
                }
                else {
                    constraintWidget4 = constraintWidget5;
                }
                constraintWidget3 = constraintWidget6;
            }
            if(v5 == 0) {
                return false;
            }
            if(v5 != v6) {
                return false;
            }
            if(v4 < v7) {
                return false;
            }
            int v8 = v4 - v7;
            if(z) {
                v8 /= v5 + 1;
            }
            else if(z1 && v5 > 2) {
                v8 = v8 / v5 - 1;
            }
            if(v5 == 1) {
                int v9 = (int)(((float)v2) + 0.5f + ((float)v8) * (v == 0 ? constraintWidget3.getHorizontalBiasPercent() : constraintWidget3.getVerticalBiasPercent()));
                if(v == 0) {
                    constraintWidget1.setFinalHorizontal(v9, constraintWidget1.getWidth() + v9);
                }
                else {
                    constraintWidget1.setFinalVertical(v9, constraintWidget1.getHeight() + v9);
                }
                Direct.horizontalSolvingPass(1, constraintWidget1, constraintWidgetContainer0.getMeasurer(), z3);
                return true;
            }
            if(z) {
                int v10 = v2 + v8;
                ConstraintWidget constraintWidget8 = constraintWidget0;
                boolean z5 = false;
                while(!z5) {
                    if(constraintWidget8.getVisibility() != 8) {
                        int v11 = v10 + constraintWidget8.mListAnchors[v1].getMargin();
                        if(v == 0) {
                            constraintWidget8.setFinalHorizontal(v11, constraintWidget8.getWidth() + v11);
                            Direct.horizontalSolvingPass(1, constraintWidget8, constraintWidgetContainer0.getMeasurer(), z3);
                            v12 = constraintWidget8.getWidth();
                        }
                        else {
                            constraintWidget8.setFinalVertical(v11, constraintWidget8.getHeight() + v11);
                            Direct.verticalSolvingPass(1, constraintWidget8, constraintWidgetContainer0.getMeasurer());
                            v12 = constraintWidget8.getHeight();
                        }
                        v10 = v11 + v12 + constraintWidget8.mListAnchors[v1 + 1].getMargin() + v8;
                    }
                    else if(v == 0) {
                        constraintWidget8.setFinalHorizontal(v10, v10);
                        Direct.horizontalSolvingPass(1, constraintWidget8, constraintWidgetContainer0.getMeasurer(), z3);
                    }
                    else {
                        constraintWidget8.setFinalVertical(v10, v10);
                        Direct.verticalSolvingPass(1, constraintWidget8, constraintWidgetContainer0.getMeasurer());
                    }
                    constraintWidget8.addToSolver(linearSystem0, false);
                    ConstraintAnchor constraintAnchor3 = constraintWidget8.mListAnchors[v1 + 1].mTarget;
                    if(constraintAnchor3 == null) {
                        constraintWidget10 = null;
                    }
                    else {
                        ConstraintWidget constraintWidget9 = constraintAnchor3.mOwner;
                        if(constraintWidget9.mListAnchors[v1].mTarget != null && constraintWidget9.mListAnchors[v1].mTarget.mOwner == constraintWidget8) {
                            constraintWidget10 = constraintWidget9;
                        }
                    }
                    if(constraintWidget10 == null) {
                        z5 = true;
                    }
                    else {
                        constraintWidget8 = constraintWidget10;
                    }
                }
                return true;
            }
            if(z1) {
                if(v5 == 2) {
                    if(v == 0) {
                        constraintWidget1.setFinalHorizontal(v2, constraintWidget1.getWidth() + v2);
                        constraintWidget2.setFinalHorizontal(v3 - constraintWidget2.getWidth(), v3);
                        Direct.horizontalSolvingPass(1, constraintWidget1, constraintWidgetContainer0.getMeasurer(), z3);
                        Direct.horizontalSolvingPass(1, constraintWidget2, constraintWidgetContainer0.getMeasurer(), z3);
                        return true;
                    }
                    constraintWidget1.setFinalVertical(v2, constraintWidget1.getHeight() + v2);
                    constraintWidget2.setFinalVertical(v3 - constraintWidget2.getHeight(), v3);
                    Direct.verticalSolvingPass(1, constraintWidget1, constraintWidgetContainer0.getMeasurer());
                    Direct.verticalSolvingPass(1, constraintWidget2, constraintWidgetContainer0.getMeasurer());
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;
    }

    private static void solveHorizontalCenterConstraints(int v, Measurer basicMeasure$Measurer0, ConstraintWidget constraintWidget0, boolean z) {
        float f = constraintWidget0.getHorizontalBiasPercent();
        int v1 = constraintWidget0.mLeft.mTarget.getFinalValue();
        int v2 = constraintWidget0.mRight.mTarget.getFinalValue();
        int v3 = constraintWidget0.mLeft.getMargin();
        int v4 = constraintWidget0.mRight.getMargin();
        if(v1 == v2) {
            f = 0.5f;
        }
        else {
            v1 = v3 + v1;
            v2 -= v4;
        }
        int v5 = constraintWidget0.getWidth();
        int v6 = v1 <= v2 ? v2 - v1 - v5 : v1 - v2 - v5;
        int v7 = ((int)(v6 <= 0 ? f * ((float)v6) : f * ((float)v6) + 0.5f)) + v1;
        constraintWidget0.setFinalHorizontal(v7, (v1 <= v2 ? v7 + v5 : v7 - v5));
        Direct.horizontalSolvingPass(v + 1, constraintWidget0, basicMeasure$Measurer0, z);
    }

    private static void solveHorizontalMatchConstraint(int v, ConstraintWidget constraintWidget0, Measurer basicMeasure$Measurer0, ConstraintWidget constraintWidget1, boolean z) {
        float f = constraintWidget1.getHorizontalBiasPercent();
        int v1 = constraintWidget1.mLeft.mTarget.getFinalValue() + constraintWidget1.mLeft.getMargin();
        int v2 = constraintWidget1.mRight.mTarget.getFinalValue() - constraintWidget1.mRight.getMargin();
        if(v2 >= v1) {
            int v3 = constraintWidget1.getWidth();
            if(constraintWidget1.getVisibility() != 8) {
                switch(constraintWidget1.mMatchConstraintDefaultWidth) {
                    case 0: {
                        v3 = v2 - v1;
                        break;
                    }
                    case 2: {
                        v3 = (int)(constraintWidget1.getHorizontalBiasPercent() * 0.5f * ((float)(constraintWidget0 instanceof ConstraintWidgetContainer ? constraintWidget0.getWidth() : constraintWidget0.getParent().getWidth())));
                    }
                }
                v3 = Math.max(constraintWidget1.mMatchConstraintMinWidth, v3);
                if(constraintWidget1.mMatchConstraintMaxWidth > 0) {
                    v3 = Math.min(constraintWidget1.mMatchConstraintMaxWidth, v3);
                }
            }
            int v4 = v1 + ((int)(f * ((float)(v2 - v1 - v3)) + 0.5f));
            constraintWidget1.setFinalHorizontal(v4, v3 + v4);
            Direct.horizontalSolvingPass(v + 1, constraintWidget1, basicMeasure$Measurer0, z);
        }
    }

    private static void solveVerticalCenterConstraints(int v, Measurer basicMeasure$Measurer0, ConstraintWidget constraintWidget0) {
        float f = constraintWidget0.getVerticalBiasPercent();
        int v1 = constraintWidget0.mTop.mTarget.getFinalValue();
        int v2 = constraintWidget0.mBottom.mTarget.getFinalValue();
        int v3 = constraintWidget0.mTop.getMargin();
        int v4 = constraintWidget0.mBottom.getMargin();
        if(v1 == v2) {
            f = 0.5f;
        }
        else {
            v1 = v3 + v1;
            v2 -= v4;
        }
        int v5 = constraintWidget0.getHeight();
        int v6 = v1 <= v2 ? v2 - v1 - v5 : v1 - v2 - v5;
        float f1 = v6 <= 0 ? f * ((float)v6) : f * ((float)v6) + 0.5f;
        int v7 = v1 + ((int)f1);
        int v8 = v7 + v5;
        if(v1 > v2) {
            v7 = v1 - ((int)f1);
            v8 = v7 - v5;
        }
        constraintWidget0.setFinalVertical(v7, v8);
        Direct.verticalSolvingPass(v + 1, constraintWidget0, basicMeasure$Measurer0);
    }

    private static void solveVerticalMatchConstraint(int v, ConstraintWidget constraintWidget0, Measurer basicMeasure$Measurer0, ConstraintWidget constraintWidget1) {
        float f = constraintWidget1.getVerticalBiasPercent();
        int v1 = constraintWidget1.mTop.mTarget.getFinalValue() + constraintWidget1.mTop.getMargin();
        int v2 = constraintWidget1.mBottom.mTarget.getFinalValue() - constraintWidget1.mBottom.getMargin();
        if(v2 >= v1) {
            int v3 = constraintWidget1.getHeight();
            if(constraintWidget1.getVisibility() != 8) {
                switch(constraintWidget1.mMatchConstraintDefaultHeight) {
                    case 0: {
                        v3 = v2 - v1;
                        break;
                    }
                    case 2: {
                        v3 = (int)(f * 0.5f * ((float)(constraintWidget0 instanceof ConstraintWidgetContainer ? constraintWidget0.getHeight() : constraintWidget0.getParent().getHeight())));
                    }
                }
                v3 = Math.max(constraintWidget1.mMatchConstraintMinHeight, v3);
                if(constraintWidget1.mMatchConstraintMaxHeight > 0) {
                    v3 = Math.min(constraintWidget1.mMatchConstraintMaxHeight, v3);
                }
            }
            int v4 = v1 + ((int)(f * ((float)(v2 - v1 - v3)) + 0.5f));
            constraintWidget1.setFinalVertical(v4, v3 + v4);
            Direct.verticalSolvingPass(v + 1, constraintWidget1, basicMeasure$Measurer0);
        }
    }

    public static void solvingPass(ConstraintWidgetContainer constraintWidgetContainer0, Measurer basicMeasure$Measurer0) {
        DimensionBehaviour constraintWidget$DimensionBehaviour0 = constraintWidgetContainer0.getHorizontalDimensionBehaviour();
        DimensionBehaviour constraintWidget$DimensionBehaviour1 = constraintWidgetContainer0.getVerticalDimensionBehaviour();
        Direct.sHcount = 0;
        Direct.sVcount = 0;
        constraintWidgetContainer0.resetFinalResolution();
        ArrayList arrayList0 = constraintWidgetContainer0.getChildren();
        int v = arrayList0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((ConstraintWidget)arrayList0.get(v1)).resetFinalResolution();
        }
        boolean z = constraintWidgetContainer0.isRtl();
        if(constraintWidget$DimensionBehaviour0 == DimensionBehaviour.FIXED) {
            constraintWidgetContainer0.setFinalHorizontal(0, constraintWidgetContainer0.getWidth());
        }
        else {
            constraintWidgetContainer0.setFinalLeft(0);
        }
        boolean z1 = false;
        boolean z2 = false;
        for(int v2 = 0; v2 < v; ++v2) {
            ConstraintWidget constraintWidget0 = (ConstraintWidget)arrayList0.get(v2);
            if(!(constraintWidget0 instanceof Guideline)) {
                if(constraintWidget0 instanceof Barrier && ((Barrier)constraintWidget0).getOrientation() == 0) {
                    z2 = true;
                }
            }
            else if(((Guideline)constraintWidget0).getOrientation() == 1) {
                if(((Guideline)constraintWidget0).getRelativeBegin() != -1) {
                    ((Guideline)constraintWidget0).setFinalValue(((Guideline)constraintWidget0).getRelativeBegin());
                }
                else if(((Guideline)constraintWidget0).getRelativeEnd() != -1 && constraintWidgetContainer0.isResolvedHorizontally()) {
                    ((Guideline)constraintWidget0).setFinalValue(constraintWidgetContainer0.getWidth() - ((Guideline)constraintWidget0).getRelativeEnd());
                }
                else if(constraintWidgetContainer0.isResolvedHorizontally()) {
                    ((Guideline)constraintWidget0).setFinalValue(((int)(((Guideline)constraintWidget0).getRelativePercent() * ((float)constraintWidgetContainer0.getWidth()) + 0.5f)));
                }
                z1 = true;
            }
        }
        if(z1) {
            for(int v3 = 0; v3 < v; ++v3) {
                ConstraintWidget constraintWidget1 = (ConstraintWidget)arrayList0.get(v3);
                if(constraintWidget1 instanceof Guideline && ((Guideline)constraintWidget1).getOrientation() == 1) {
                    Direct.horizontalSolvingPass(0, ((Guideline)constraintWidget1), basicMeasure$Measurer0, z);
                }
            }
        }
        Direct.horizontalSolvingPass(0, constraintWidgetContainer0, basicMeasure$Measurer0, z);
        if(z2) {
            for(int v4 = 0; v4 < v; ++v4) {
                ConstraintWidget constraintWidget2 = (ConstraintWidget)arrayList0.get(v4);
                if(constraintWidget2 instanceof Barrier && ((Barrier)constraintWidget2).getOrientation() == 0) {
                    Direct.solveBarrier(0, ((Barrier)constraintWidget2), basicMeasure$Measurer0, 0, z);
                }
            }
        }
        if(constraintWidget$DimensionBehaviour1 == DimensionBehaviour.FIXED) {
            constraintWidgetContainer0.setFinalVertical(0, constraintWidgetContainer0.getHeight());
        }
        else {
            constraintWidgetContainer0.setFinalTop(0);
        }
        boolean z3 = false;
        boolean z4 = false;
        for(int v5 = 0; v5 < v; ++v5) {
            ConstraintWidget constraintWidget3 = (ConstraintWidget)arrayList0.get(v5);
            if(!(constraintWidget3 instanceof Guideline)) {
                if(constraintWidget3 instanceof Barrier && ((Barrier)constraintWidget3).getOrientation() == 1) {
                    z4 = true;
                }
            }
            else if(((Guideline)constraintWidget3).getOrientation() == 0) {
                if(((Guideline)constraintWidget3).getRelativeBegin() != -1) {
                    ((Guideline)constraintWidget3).setFinalValue(((Guideline)constraintWidget3).getRelativeBegin());
                }
                else if(((Guideline)constraintWidget3).getRelativeEnd() != -1 && constraintWidgetContainer0.isResolvedVertically()) {
                    ((Guideline)constraintWidget3).setFinalValue(constraintWidgetContainer0.getHeight() - ((Guideline)constraintWidget3).getRelativeEnd());
                }
                else if(constraintWidgetContainer0.isResolvedVertically()) {
                    ((Guideline)constraintWidget3).setFinalValue(((int)(((Guideline)constraintWidget3).getRelativePercent() * ((float)constraintWidgetContainer0.getHeight()) + 0.5f)));
                }
                z3 = true;
            }
        }
        if(z3) {
            for(int v6 = 0; v6 < v; ++v6) {
                ConstraintWidget constraintWidget4 = (ConstraintWidget)arrayList0.get(v6);
                if(constraintWidget4 instanceof Guideline && ((Guideline)constraintWidget4).getOrientation() == 0) {
                    Direct.verticalSolvingPass(1, ((Guideline)constraintWidget4), basicMeasure$Measurer0);
                }
            }
        }
        Direct.verticalSolvingPass(0, constraintWidgetContainer0, basicMeasure$Measurer0);
        if(z4) {
            for(int v7 = 0; v7 < v; ++v7) {
                ConstraintWidget constraintWidget5 = (ConstraintWidget)arrayList0.get(v7);
                if(constraintWidget5 instanceof Barrier && ((Barrier)constraintWidget5).getOrientation() == 1) {
                    Direct.solveBarrier(0, ((Barrier)constraintWidget5), basicMeasure$Measurer0, 1, z);
                }
            }
        }
        for(int v8 = 0; v8 < v; ++v8) {
            ConstraintWidget constraintWidget6 = (ConstraintWidget)arrayList0.get(v8);
            if(constraintWidget6.isMeasureRequested() && Direct.canMeasure(0, constraintWidget6)) {
                ConstraintWidgetContainer.measure(0, constraintWidget6, basicMeasure$Measurer0, Direct.sMeasure, Measure.SELF_DIMENSIONS);
                if(!(constraintWidget6 instanceof Guideline)) {
                    Direct.horizontalSolvingPass(0, constraintWidget6, basicMeasure$Measurer0, z);
                    Direct.verticalSolvingPass(0, constraintWidget6, basicMeasure$Measurer0);
                }
                else if(((Guideline)constraintWidget6).getOrientation() == 0) {
                    Direct.verticalSolvingPass(0, constraintWidget6, basicMeasure$Measurer0);
                }
                else {
                    Direct.horizontalSolvingPass(0, constraintWidget6, basicMeasure$Measurer0, z);
                }
            }
        }
    }

    private static void verticalSolvingPass(int v, ConstraintWidget constraintWidget0, Measurer basicMeasure$Measurer0) {
        if(!constraintWidget0.isVerticalSolvingPassDone()) {
            ++Direct.sVcount;
            if(!(constraintWidget0 instanceof ConstraintWidgetContainer) && constraintWidget0.isMeasureRequested() && Direct.canMeasure(v + 1, constraintWidget0)) {
                ConstraintWidgetContainer.measure(v + 1, constraintWidget0, basicMeasure$Measurer0, new Measure(), Measure.SELF_DIMENSIONS);
            }
            ConstraintAnchor constraintAnchor0 = constraintWidget0.getAnchor(Type.TOP);
            ConstraintAnchor constraintAnchor1 = constraintWidget0.getAnchor(Type.BOTTOM);
            int v1 = constraintAnchor0.getFinalValue();
            int v2 = constraintAnchor1.getFinalValue();
            if(constraintAnchor0.getDependents() != null && constraintAnchor0.hasFinalValue()) {
                for(Object object0: constraintAnchor0.getDependents()) {
                    ConstraintAnchor constraintAnchor2 = (ConstraintAnchor)object0;
                    ConstraintWidget constraintWidget1 = constraintAnchor2.mOwner;
                    boolean z = Direct.canMeasure(v + 1, constraintWidget1);
                    if(constraintWidget1.isMeasureRequested() && z) {
                        ConstraintWidgetContainer.measure(v + 1, constraintWidget1, basicMeasure$Measurer0, new Measure(), Measure.SELF_DIMENSIONS);
                    }
                    boolean z1 = constraintAnchor2 == constraintWidget1.mTop && constraintWidget1.mBottom.mTarget != null && constraintWidget1.mBottom.mTarget.hasFinalValue() || constraintAnchor2 == constraintWidget1.mBottom && constraintWidget1.mTop.mTarget != null && constraintWidget1.mTop.mTarget.hasFinalValue();
                    if(constraintWidget1.getVerticalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT && !z) {
                        if(constraintWidget1.getVerticalDimensionBehaviour() != DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget1.mMatchConstraintMaxHeight < 0 || constraintWidget1.mMatchConstraintMinHeight < 0 || constraintWidget1.getVisibility() != 8 && (constraintWidget1.mMatchConstraintDefaultHeight != 0 || constraintWidget1.getDimensionRatio() != 0.0f) || constraintWidget1.isInVerticalChain() || constraintWidget1.isInVirtualLayout() || !z1 || constraintWidget1.isInVerticalChain()) {
                            continue;
                        }
                        Direct.solveVerticalMatchConstraint(v + 1, constraintWidget0, basicMeasure$Measurer0, constraintWidget1);
                    }
                    else if(constraintWidget1.isMeasureRequested()) {
                    }
                    else if(constraintAnchor2 == constraintWidget1.mTop && constraintWidget1.mBottom.mTarget == null) {
                        int v3 = constraintWidget1.mTop.getMargin();
                        constraintWidget1.setFinalVertical(v3 + v1, constraintWidget1.getHeight() + (v3 + v1));
                        Direct.verticalSolvingPass(v + 1, constraintWidget1, basicMeasure$Measurer0);
                    }
                    else if(constraintAnchor2 == constraintWidget1.mBottom && constraintWidget1.mTop.mTarget == null) {
                        int v4 = constraintWidget1.mBottom.getMargin();
                        constraintWidget1.setFinalVertical(v1 - v4 - constraintWidget1.getHeight(), v1 - v4);
                        Direct.verticalSolvingPass(v + 1, constraintWidget1, basicMeasure$Measurer0);
                    }
                    else if(z1 && !constraintWidget1.isInVerticalChain()) {
                        Direct.solveVerticalCenterConstraints(v + 1, basicMeasure$Measurer0, constraintWidget1);
                    }
                }
            }
            if(!(constraintWidget0 instanceof Guideline)) {
                if(constraintAnchor1.getDependents() != null && constraintAnchor1.hasFinalValue()) {
                    for(Object object1: constraintAnchor1.getDependents()) {
                        ConstraintAnchor constraintAnchor3 = (ConstraintAnchor)object1;
                        ConstraintWidget constraintWidget2 = constraintAnchor3.mOwner;
                        boolean z2 = Direct.canMeasure(v + 1, constraintWidget2);
                        if(constraintWidget2.isMeasureRequested() && z2) {
                            ConstraintWidgetContainer.measure(v + 1, constraintWidget2, basicMeasure$Measurer0, new Measure(), Measure.SELF_DIMENSIONS);
                        }
                        boolean z3 = constraintAnchor3 == constraintWidget2.mTop && constraintWidget2.mBottom.mTarget != null && constraintWidget2.mBottom.mTarget.hasFinalValue() || constraintAnchor3 == constraintWidget2.mBottom && constraintWidget2.mTop.mTarget != null && constraintWidget2.mTop.mTarget.hasFinalValue();
                        if(constraintWidget2.getVerticalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT && !z2) {
                            if(constraintWidget2.getVerticalDimensionBehaviour() != DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget2.mMatchConstraintMaxHeight < 0 || constraintWidget2.mMatchConstraintMinHeight < 0 || constraintWidget2.getVisibility() != 8 && (constraintWidget2.mMatchConstraintDefaultHeight != 0 || constraintWidget2.getDimensionRatio() != 0.0f) || constraintWidget2.isInVerticalChain() || constraintWidget2.isInVirtualLayout() || !z3 || constraintWidget2.isInVerticalChain()) {
                                continue;
                            }
                            Direct.solveVerticalMatchConstraint(v + 1, constraintWidget0, basicMeasure$Measurer0, constraintWidget2);
                        }
                        else if(constraintWidget2.isMeasureRequested()) {
                        }
                        else if(constraintAnchor3 == constraintWidget2.mTop && constraintWidget2.mBottom.mTarget == null) {
                            int v5 = constraintWidget2.mTop.getMargin();
                            constraintWidget2.setFinalVertical(v5 + v2, constraintWidget2.getHeight() + (v5 + v2));
                            Direct.verticalSolvingPass(v + 1, constraintWidget2, basicMeasure$Measurer0);
                        }
                        else if(constraintAnchor3 == constraintWidget2.mBottom && constraintWidget2.mTop.mTarget == null) {
                            int v6 = constraintWidget2.mBottom.getMargin();
                            constraintWidget2.setFinalVertical(v2 - v6 - constraintWidget2.getHeight(), v2 - v6);
                            Direct.verticalSolvingPass(v + 1, constraintWidget2, basicMeasure$Measurer0);
                        }
                        else if(z3 && !constraintWidget2.isInVerticalChain()) {
                            Direct.solveVerticalCenterConstraints(v + 1, basicMeasure$Measurer0, constraintWidget2);
                        }
                    }
                }
                ConstraintAnchor constraintAnchor4 = constraintWidget0.getAnchor(Type.BASELINE);
                if(constraintAnchor4.getDependents() != null && constraintAnchor4.hasFinalValue()) {
                    int v7 = constraintAnchor4.getFinalValue();
                    for(Object object2: constraintAnchor4.getDependents()) {
                        ConstraintAnchor constraintAnchor5 = (ConstraintAnchor)object2;
                        ConstraintWidget constraintWidget3 = constraintAnchor5.mOwner;
                        boolean z4 = Direct.canMeasure(v + 1, constraintWidget3);
                        if(constraintWidget3.isMeasureRequested() && z4) {
                            ConstraintWidgetContainer.measure(v + 1, constraintWidget3, basicMeasure$Measurer0, new Measure(), Measure.SELF_DIMENSIONS);
                        }
                        if((constraintWidget3.getVerticalDimensionBehaviour() != DimensionBehaviour.MATCH_CONSTRAINT || z4) && !constraintWidget3.isMeasureRequested() && constraintAnchor5 == constraintWidget3.mBaseline) {
                            constraintWidget3.setFinalBaseline(constraintAnchor5.getMargin() + v7);
                            Direct.verticalSolvingPass(v + 1, constraintWidget3, basicMeasure$Measurer0);
                        }
                    }
                }
                constraintWidget0.markVerticalSolvingPassDone();
            }
        }
    }
}

