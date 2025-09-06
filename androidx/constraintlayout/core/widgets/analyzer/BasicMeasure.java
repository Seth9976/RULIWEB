package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintAnchor.Type;
import androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.Helper;
import androidx.constraintlayout.core.widgets.Optimizer;
import androidx.constraintlayout.core.widgets.VirtualLayout;
import java.util.ArrayList;

public class BasicMeasure {
    public static class Measure {
        public static int SELF_DIMENSIONS = 0;
        public static int TRY_GIVEN_DIMENSIONS = 1;
        public static int USE_GIVEN_DIMENSIONS = 2;
        public DimensionBehaviour horizontalBehavior;
        public int horizontalDimension;
        public int measureStrategy;
        public int measuredBaseline;
        public boolean measuredHasBaseline;
        public int measuredHeight;
        public boolean measuredNeedsSolverPass;
        public int measuredWidth;
        public DimensionBehaviour verticalBehavior;
        public int verticalDimension;

        static {
        }
    }

    public interface Measurer {
        void didMeasures();

        void measure(ConstraintWidget arg1, Measure arg2);
    }

    public static final int AT_MOST = 0x80000000;
    private static final boolean DEBUG = false;
    private static final boolean DO_NOT_USE = false;
    public static final int EXACTLY = 0x40000000;
    public static final int FIXED = -3;
    public static final int MATCH_PARENT = -1;
    private static final int MODE_SHIFT = 30;
    public static final int UNSPECIFIED = 0;
    public static final int WRAP_CONTENT = -2;
    private ConstraintWidgetContainer mConstraintWidgetContainer;
    private Measure mMeasure;
    private final ArrayList mVariableDimensionsWidgets;

    public BasicMeasure(ConstraintWidgetContainer constraintWidgetContainer0) {
        this.mVariableDimensionsWidgets = new ArrayList();
        this.mMeasure = new Measure();
        this.mConstraintWidgetContainer = constraintWidgetContainer0;
    }

    private boolean measure(Measurer basicMeasure$Measurer0, ConstraintWidget constraintWidget0, int v) {
        this.mMeasure.horizontalBehavior = constraintWidget0.getHorizontalDimensionBehaviour();
        this.mMeasure.verticalBehavior = constraintWidget0.getVerticalDimensionBehaviour();
        this.mMeasure.horizontalDimension = constraintWidget0.getWidth();
        this.mMeasure.verticalDimension = constraintWidget0.getHeight();
        this.mMeasure.measuredNeedsSolverPass = false;
        this.mMeasure.measureStrategy = v;
        boolean z = this.mMeasure.verticalBehavior == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget0.mDimensionRatio > 0.0f;
        if(this.mMeasure.horizontalBehavior == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget0.mDimensionRatio > 0.0f && constraintWidget0.mResolvedMatchConstraintDefault[0] == 4) {
            this.mMeasure.horizontalBehavior = DimensionBehaviour.FIXED;
        }
        if(z && constraintWidget0.mResolvedMatchConstraintDefault[1] == 4) {
            this.mMeasure.verticalBehavior = DimensionBehaviour.FIXED;
        }
        basicMeasure$Measurer0.measure(constraintWidget0, this.mMeasure);
        constraintWidget0.setWidth(this.mMeasure.measuredWidth);
        constraintWidget0.setHeight(this.mMeasure.measuredHeight);
        constraintWidget0.setHasBaseline(this.mMeasure.measuredHasBaseline);
        constraintWidget0.setBaselineDistance(this.mMeasure.measuredBaseline);
        this.mMeasure.measureStrategy = Measure.SELF_DIMENSIONS;
        return this.mMeasure.measuredNeedsSolverPass;
    }

    private void measureChildren(ConstraintWidgetContainer constraintWidgetContainer0) {
        int v = constraintWidgetContainer0.mChildren.size();
        boolean z = constraintWidgetContainer0.optimizeFor(0x40);
        Measurer basicMeasure$Measurer0 = constraintWidgetContainer0.getMeasurer();
        for(int v1 = 0; v1 < v; ++v1) {
            ConstraintWidget constraintWidget0 = (ConstraintWidget)constraintWidgetContainer0.mChildren.get(v1);
            if(!(constraintWidget0 instanceof Guideline) && !(constraintWidget0 instanceof Barrier) && !constraintWidget0.isInVirtualLayout() && (!z || constraintWidget0.mHorizontalRun == null || constraintWidget0.mVerticalRun == null || !constraintWidget0.mHorizontalRun.mDimension.resolved || !constraintWidget0.mVerticalRun.mDimension.resolved)) {
                DimensionBehaviour constraintWidget$DimensionBehaviour0 = constraintWidget0.getDimensionBehaviour(0);
                int v2 = 1;
                DimensionBehaviour constraintWidget$DimensionBehaviour1 = constraintWidget0.getDimensionBehaviour(1);
                int v3 = constraintWidget$DimensionBehaviour0 != DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget0.mMatchConstraintDefaultWidth == 1 || constraintWidget$DimensionBehaviour1 != DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget0.mMatchConstraintDefaultHeight == 1 ? 0 : 1;
                if(v3 != 0 || !constraintWidgetContainer0.optimizeFor(1) || constraintWidget0 instanceof VirtualLayout) {
                    v2 = v3;
                }
                else {
                    if(constraintWidget$DimensionBehaviour0 == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget0.mMatchConstraintDefaultWidth == 0 && constraintWidget$DimensionBehaviour1 != DimensionBehaviour.MATCH_CONSTRAINT && !constraintWidget0.isInHorizontalChain()) {
                        v3 = 1;
                    }
                    if(constraintWidget$DimensionBehaviour1 == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget0.mMatchConstraintDefaultHeight == 0 && constraintWidget$DimensionBehaviour0 != DimensionBehaviour.MATCH_CONSTRAINT && !constraintWidget0.isInHorizontalChain()) {
                        v3 = 1;
                    }
                    if(constraintWidget$DimensionBehaviour0 != DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget$DimensionBehaviour1 != DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget0.mDimensionRatio <= 0.0f) {
                        v2 = v3;
                    }
                }
                if(v2 == 0) {
                    this.measure(basicMeasure$Measurer0, constraintWidget0, Measure.SELF_DIMENSIONS);
                    if(constraintWidgetContainer0.mMetrics != null) {
                        ++constraintWidgetContainer0.mMetrics.measuredWidgets;
                    }
                }
            }
        }
        basicMeasure$Measurer0.didMeasures();
    }

    private void solveLinearSystem(ConstraintWidgetContainer constraintWidgetContainer0, String s, int v, int v1, int v2) {
        long v3 = constraintWidgetContainer0.mMetrics == null ? 0L : System.nanoTime();
        int v4 = constraintWidgetContainer0.getMinWidth();
        int v5 = constraintWidgetContainer0.getMinHeight();
        constraintWidgetContainer0.setMinWidth(0);
        constraintWidgetContainer0.setMinHeight(0);
        constraintWidgetContainer0.setWidth(v1);
        constraintWidgetContainer0.setHeight(v2);
        constraintWidgetContainer0.setMinWidth(v4);
        constraintWidgetContainer0.setMinHeight(v5);
        this.mConstraintWidgetContainer.setPass(v);
        this.mConstraintWidgetContainer.layout();
        if(constraintWidgetContainer0.mMetrics != null) {
            ++constraintWidgetContainer0.mMetrics.mSolverPasses;
            constraintWidgetContainer0.mMetrics.measuresLayoutDuration += System.nanoTime() - v3;
        }
    }

    public long solverMeasure(ConstraintWidgetContainer constraintWidgetContainer0, int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7, int v8) {
        int v38;
        long v35;
        int v28;
        int v27;
        long v26;
        int v34;
        int v22;
        int v21;
        int v17;
        boolean z2;
        Measurer basicMeasure$Measurer0 = constraintWidgetContainer0.getMeasurer();
        int v9 = constraintWidgetContainer0.mChildren.size();
        int v10 = constraintWidgetContainer0.getWidth();
        int v11 = constraintWidgetContainer0.getHeight();
        boolean z = Optimizer.enabled(v, 0x80);
        int v12 = z || Optimizer.enabled(v, 0x40) ? 1 : 0;
        if(v12 != 0) {
            int v13 = 0;
            while(v13 < v9) {
                ConstraintWidget constraintWidget0 = (ConstraintWidget)constraintWidgetContainer0.mChildren.get(v13);
                boolean z1 = constraintWidget0.getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget0.getVerticalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT && constraintWidget0.getDimensionRatio() > 0.0f;
                if((!constraintWidget0.isInHorizontalChain() || !z1) && ((!constraintWidget0.isInVerticalChain() || !z1) && !(constraintWidget0 instanceof VirtualLayout) && !constraintWidget0.isInHorizontalChain() && !constraintWidget0.isInVerticalChain())) {
                    ++v13;
                }
                else {
                    v12 = 0;
                    if(true) {
                        break;
                    }
                }
            }
        }
        if(v12 != 0 && LinearSystem.sMetrics != null) {
            ++LinearSystem.sMetrics.measures;
        }
        int v14 = v12 & ((v3 != 0x40000000 || v5 != 0x40000000) && !z ? 0 : 1);
        if(v14 == 0) {
            z2 = false;
            v17 = 0;
        }
        else {
            int v15 = Math.min(constraintWidgetContainer0.getMaxWidth(), v4);
            int v16 = Math.min(constraintWidgetContainer0.getMaxHeight(), v6);
            if(v3 == 0x40000000 && constraintWidgetContainer0.getWidth() != v15) {
                constraintWidgetContainer0.setWidth(v15);
                constraintWidgetContainer0.invalidateGraph();
            }
            if(v5 == 0x40000000 && constraintWidgetContainer0.getHeight() != v16) {
                constraintWidgetContainer0.setHeight(v16);
                constraintWidgetContainer0.invalidateGraph();
            }
            if(v3 != 0x40000000 || v5 != 0x40000000) {
                boolean z3 = constraintWidgetContainer0.directMeasureSetup(z);
                if(v3 == 0x40000000) {
                    z3 &= constraintWidgetContainer0.directMeasureWithOrientation(z, 0);
                    v17 = 1;
                }
                else {
                    v17 = 0;
                }
                if(v5 == 0x40000000) {
                    z2 = constraintWidgetContainer0.directMeasureWithOrientation(z, 1) & z3;
                    ++v17;
                }
                else {
                    z2 = z3;
                }
            }
            else {
                z2 = constraintWidgetContainer0.directMeasure(z);
                v17 = 2;
            }
            if(z2) {
                constraintWidgetContainer0.updateFromRuns(v3 == 0x40000000, v5 == 0x40000000);
            }
        }
        long v18 = 0L;
        if(!z2 || v17 != 2) {
            int v19 = constraintWidgetContainer0.getOptimizationLevel();
            if(v9 > 0) {
                this.measureChildren(constraintWidgetContainer0);
            }
            if(constraintWidgetContainer0.mMetrics != null) {
                v18 = System.nanoTime();
            }
            this.updateHierarchy(constraintWidgetContainer0);
            int v20 = this.mVariableDimensionsWidgets.size();
            if(v9 > 0) {
                this.solveLinearSystem(constraintWidgetContainer0, "First pass", 0, v10, v11);
            }
            v21 = v10;
            v22 = v11;
            if(v20 > 0) {
                boolean z4 = constraintWidgetContainer0.getHorizontalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT;
                boolean z5 = constraintWidgetContainer0.getVerticalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT;
                int v23 = Math.max(constraintWidgetContainer0.getWidth(), this.mConstraintWidgetContainer.getMinWidth());
                int v24 = Math.max(constraintWidgetContainer0.getHeight(), this.mConstraintWidgetContainer.getMinHeight());
                int v25 = 0;
                boolean z6 = false;
                while(v25 < v20) {
                    ConstraintWidget constraintWidget1 = (ConstraintWidget)this.mVariableDimensionsWidgets.get(v25);
                    if(constraintWidget1 instanceof VirtualLayout) {
                        int v29 = constraintWidget1.getWidth();
                        v26 = v18;
                        int v30 = constraintWidget1.getHeight();
                        int v31 = z6 | this.measure(basicMeasure$Measurer0, constraintWidget1, Measure.TRY_GIVEN_DIMENSIONS);
                        if(constraintWidgetContainer0.mMetrics == null) {
                            v27 = v21;
                            v28 = v22;
                        }
                        else {
                            v27 = v21;
                            v28 = v22;
                            ++constraintWidgetContainer0.mMetrics.measuredMatchWidgets;
                        }
                        int v32 = constraintWidget1.getWidth();
                        int v33 = constraintWidget1.getHeight();
                        if(v32 == v29) {
                            v34 = v31;
                        }
                        else {
                            constraintWidget1.setWidth(v32);
                            if(z4 && constraintWidget1.getRight() > v23) {
                                v23 = Math.max(v23, constraintWidget1.getRight() + constraintWidget1.getAnchor(Type.RIGHT).getMargin());
                            }
                            v34 = 1;
                        }
                        if(v33 != v30) {
                            constraintWidget1.setHeight(v33);
                            if(z5 && constraintWidget1.getBottom() > v24) {
                                v24 = Math.max(v24, constraintWidget1.getBottom() + constraintWidget1.getAnchor(Type.BOTTOM).getMargin());
                            }
                            v34 = 1;
                        }
                        z6 = v34 | ((VirtualLayout)constraintWidget1).needSolverPass();
                    }
                    else {
                        v26 = v18;
                        v27 = v21;
                        v28 = v22;
                    }
                    ++v25;
                    v21 = v27;
                    v22 = v28;
                    v18 = v26;
                }
                v35 = v18;
                int v36 = 0;
                while(v36 < 2) {
                    int v37 = 0;
                    while(v37 < v20) {
                        ConstraintWidget constraintWidget2 = (ConstraintWidget)this.mVariableDimensionsWidgets.get(v37);
                        if((!(constraintWidget2 instanceof Helper) || constraintWidget2 instanceof VirtualLayout) && !(constraintWidget2 instanceof Guideline) && constraintWidget2.getVisibility() != 8 && (v14 == 0 || !constraintWidget2.mHorizontalRun.mDimension.resolved || !constraintWidget2.mVerticalRun.mDimension.resolved) && !(constraintWidget2 instanceof VirtualLayout)) {
                            int v39 = constraintWidget2.getWidth();
                            int v40 = constraintWidget2.getHeight();
                            int v41 = constraintWidget2.getBaselineDistance();
                            int v42 = z6 | this.measure(basicMeasure$Measurer0, constraintWidget2, (v36 == 1 ? Measure.USE_GIVEN_DIMENSIONS : Measure.TRY_GIVEN_DIMENSIONS));
                            if(constraintWidgetContainer0.mMetrics == null) {
                                v38 = v36;
                            }
                            else {
                                v38 = v36;
                                ++constraintWidgetContainer0.mMetrics.measuredMatchWidgets;
                            }
                            int v43 = constraintWidget2.getWidth();
                            int v44 = constraintWidget2.getHeight();
                            if(v43 != v39) {
                                constraintWidget2.setWidth(v43);
                                if(z4 && constraintWidget2.getRight() > v23) {
                                    v23 = Math.max(v23, constraintWidget2.getRight() + constraintWidget2.getAnchor(Type.RIGHT).getMargin());
                                }
                                v42 = 1;
                            }
                            if(v44 != v40) {
                                constraintWidget2.setHeight(v44);
                                if(z5 && constraintWidget2.getBottom() > v24) {
                                    v24 = Math.max(v24, constraintWidget2.getBottom() + constraintWidget2.getAnchor(Type.BOTTOM).getMargin());
                                }
                                v42 = 1;
                            }
                            z6 = !constraintWidget2.hasBaseline() || v41 == constraintWidget2.getBaselineDistance() ? v42 : true;
                        }
                        else {
                            v38 = v36;
                        }
                        ++v37;
                        v36 = v38;
                    }
                    if(!z6) {
                        break;
                    }
                    ++v36;
                    this.solveLinearSystem(constraintWidgetContainer0, "intermediate pass", v36, v21, v22);
                    z6 = false;
                }
            }
            else {
                v35 = v18;
            }
            constraintWidgetContainer0.setOptimizationLevel(v19);
            v18 = v35;
        }
        return constraintWidgetContainer0.mMetrics == null ? v18 : System.nanoTime() - v18;
    }

    public void updateHierarchy(ConstraintWidgetContainer constraintWidgetContainer0) {
        this.mVariableDimensionsWidgets.clear();
        int v = constraintWidgetContainer0.mChildren.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ConstraintWidget constraintWidget0 = (ConstraintWidget)constraintWidgetContainer0.mChildren.get(v1);
            if(constraintWidget0.getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT || constraintWidget0.getVerticalDimensionBehaviour() == DimensionBehaviour.MATCH_CONSTRAINT) {
                this.mVariableDimensionsWidgets.add(constraintWidget0);
            }
        }
        constraintWidgetContainer0.invalidateGraph();
    }
}

