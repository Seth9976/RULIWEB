package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Helper;

public class HorizontalWidgetRun extends WidgetRun {
    private static int[] sTempDimensions;

    static {
        HorizontalWidgetRun.sTempDimensions = new int[2];
    }

    public HorizontalWidgetRun(ConstraintWidget constraintWidget0) {
        super(constraintWidget0);
        this.start.mType = Type.LEFT;
        this.end.mType = Type.RIGHT;
        this.orientation = 0;
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void apply() {
        if(this.mWidget.measured) {
            this.mDimension.resolve(this.mWidget.getWidth());
        }
        if(!this.mDimension.resolved) {
            this.mDimensionBehavior = this.mWidget.getHorizontalDimensionBehaviour();
            if(this.mDimensionBehavior != DimensionBehaviour.MATCH_CONSTRAINT) {
                if(this.mDimensionBehavior == DimensionBehaviour.MATCH_PARENT) {
                    ConstraintWidget constraintWidget0 = this.mWidget.getParent();
                    if(constraintWidget0 != null && (constraintWidget0.getHorizontalDimensionBehaviour() == DimensionBehaviour.FIXED || constraintWidget0.getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_PARENT)) {
                        int v = constraintWidget0.getWidth();
                        int v1 = this.mWidget.mLeft.getMargin();
                        int v2 = this.mWidget.mRight.getMargin();
                        this.addTarget(this.start, constraintWidget0.mHorizontalRun.start, this.mWidget.mLeft.getMargin());
                        this.addTarget(this.end, constraintWidget0.mHorizontalRun.end, -this.mWidget.mRight.getMargin());
                        this.mDimension.resolve(v - v1 - v2);
                        return;
                    }
                }
                if(this.mDimensionBehavior == DimensionBehaviour.FIXED) {
                    this.mDimension.resolve(this.mWidget.getWidth());
                }
            }
        }
        else if(this.mDimensionBehavior == DimensionBehaviour.MATCH_PARENT) {
            ConstraintWidget constraintWidget1 = this.mWidget.getParent();
            if(constraintWidget1 != null && (constraintWidget1.getHorizontalDimensionBehaviour() == DimensionBehaviour.FIXED || constraintWidget1.getHorizontalDimensionBehaviour() == DimensionBehaviour.MATCH_PARENT)) {
                this.addTarget(this.start, constraintWidget1.mHorizontalRun.start, this.mWidget.mLeft.getMargin());
                this.addTarget(this.end, constraintWidget1.mHorizontalRun.end, -this.mWidget.mRight.getMargin());
                return;
            }
        }
        if(!this.mDimension.resolved || !this.mWidget.measured) {
            if(this.mDimensionBehavior == DimensionBehaviour.MATCH_CONSTRAINT) {
                switch(this.mWidget.mMatchConstraintDefaultWidth) {
                    case 2: {
                        ConstraintWidget constraintWidget2 = this.mWidget.getParent();
                        if(constraintWidget2 != null) {
                            DimensionDependency dimensionDependency0 = constraintWidget2.mVerticalRun.mDimension;
                            this.mDimension.mTargets.add(dimensionDependency0);
                            dimensionDependency0.mDependencies.add(this.mDimension);
                            this.mDimension.delegateToWidgetRun = true;
                            this.mDimension.mDependencies.add(this.start);
                            this.mDimension.mDependencies.add(this.end);
                        }
                        break;
                    }
                    case 3: {
                        if(this.mWidget.mMatchConstraintDefaultHeight == 3) {
                            this.start.updateDelegate = this;
                            this.end.updateDelegate = this;
                            this.mWidget.mVerticalRun.start.updateDelegate = this;
                            this.mWidget.mVerticalRun.end.updateDelegate = this;
                            this.mDimension.updateDelegate = this;
                            if(this.mWidget.isInVerticalChain()) {
                                this.mDimension.mTargets.add(this.mWidget.mVerticalRun.mDimension);
                                this.mWidget.mVerticalRun.mDimension.mDependencies.add(this.mDimension);
                                this.mWidget.mVerticalRun.mDimension.updateDelegate = this;
                                this.mDimension.mTargets.add(this.mWidget.mVerticalRun.start);
                                this.mDimension.mTargets.add(this.mWidget.mVerticalRun.end);
                                this.mWidget.mVerticalRun.start.mDependencies.add(this.mDimension);
                                this.mWidget.mVerticalRun.end.mDependencies.add(this.mDimension);
                            }
                            else if(this.mWidget.isInHorizontalChain()) {
                                this.mWidget.mVerticalRun.mDimension.mTargets.add(this.mDimension);
                                this.mDimension.mDependencies.add(this.mWidget.mVerticalRun.mDimension);
                            }
                            else {
                                this.mWidget.mVerticalRun.mDimension.mTargets.add(this.mDimension);
                            }
                        }
                        else {
                            DimensionDependency dimensionDependency1 = this.mWidget.mVerticalRun.mDimension;
                            this.mDimension.mTargets.add(dimensionDependency1);
                            dimensionDependency1.mDependencies.add(this.mDimension);
                            this.mWidget.mVerticalRun.start.mDependencies.add(this.mDimension);
                            this.mWidget.mVerticalRun.end.mDependencies.add(this.mDimension);
                            this.mDimension.delegateToWidgetRun = true;
                            this.mDimension.mDependencies.add(this.start);
                            this.mDimension.mDependencies.add(this.end);
                            this.start.mTargets.add(this.mDimension);
                            this.end.mTargets.add(this.mDimension);
                        }
                    }
                }
            }
            if(this.mWidget.mListAnchors[0].mTarget != null && this.mWidget.mListAnchors[1].mTarget != null) {
                if(this.mWidget.isInHorizontalChain()) {
                    this.start.mMargin = this.mWidget.mListAnchors[0].getMargin();
                    this.end.mMargin = -this.mWidget.mListAnchors[1].getMargin();
                    return;
                }
                DependencyNode dependencyNode4 = this.getTarget(this.mWidget.mListAnchors[0]);
                DependencyNode dependencyNode5 = this.getTarget(this.mWidget.mListAnchors[1]);
                if(dependencyNode4 != null) {
                    dependencyNode4.addDependency(this);
                }
                if(dependencyNode5 != null) {
                    dependencyNode5.addDependency(this);
                }
                this.mRunType = RunType.CENTER;
                return;
            }
            if(this.mWidget.mListAnchors[0].mTarget != null) {
                DependencyNode dependencyNode6 = this.getTarget(this.mWidget.mListAnchors[0]);
                if(dependencyNode6 != null) {
                    this.addTarget(this.start, dependencyNode6, this.mWidget.mListAnchors[0].getMargin());
                    this.addTarget(this.end, this.start, 1, this.mDimension);
                }
            }
            else if(this.mWidget.mListAnchors[1].mTarget != null) {
                DependencyNode dependencyNode7 = this.getTarget(this.mWidget.mListAnchors[1]);
                if(dependencyNode7 != null) {
                    this.addTarget(this.end, dependencyNode7, -this.mWidget.mListAnchors[1].getMargin());
                    this.addTarget(this.start, this.end, -1, this.mDimension);
                }
            }
            else if(!(this.mWidget instanceof Helper) && this.mWidget.getParent() != null) {
                this.addTarget(this.start, this.mWidget.getParent().mHorizontalRun.start, this.mWidget.getX());
                this.addTarget(this.end, this.start, 1, this.mDimension);
            }
        }
        else {
            if(this.mWidget.mListAnchors[0].mTarget != null && this.mWidget.mListAnchors[1].mTarget != null) {
                if(this.mWidget.isInHorizontalChain()) {
                    this.start.mMargin = this.mWidget.mListAnchors[0].getMargin();
                    this.end.mMargin = -this.mWidget.mListAnchors[1].getMargin();
                    return;
                }
                DependencyNode dependencyNode0 = this.getTarget(this.mWidget.mListAnchors[0]);
                if(dependencyNode0 != null) {
                    this.addTarget(this.start, dependencyNode0, this.mWidget.mListAnchors[0].getMargin());
                }
                DependencyNode dependencyNode1 = this.getTarget(this.mWidget.mListAnchors[1]);
                if(dependencyNode1 != null) {
                    this.addTarget(this.end, dependencyNode1, -this.mWidget.mListAnchors[1].getMargin());
                }
                this.start.delegateToWidgetRun = true;
                this.end.delegateToWidgetRun = true;
                return;
            }
            if(this.mWidget.mListAnchors[0].mTarget != null) {
                DependencyNode dependencyNode2 = this.getTarget(this.mWidget.mListAnchors[0]);
                if(dependencyNode2 != null) {
                    this.addTarget(this.start, dependencyNode2, this.mWidget.mListAnchors[0].getMargin());
                    this.addTarget(this.end, this.start, this.mDimension.value);
                }
            }
            else if(this.mWidget.mListAnchors[1].mTarget != null) {
                DependencyNode dependencyNode3 = this.getTarget(this.mWidget.mListAnchors[1]);
                if(dependencyNode3 != null) {
                    this.addTarget(this.end, dependencyNode3, -this.mWidget.mListAnchors[1].getMargin());
                    this.addTarget(this.start, this.end, -this.mDimension.value);
                }
            }
            else if(!(this.mWidget instanceof Helper) && this.mWidget.getParent() != null && this.mWidget.getAnchor(androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.CENTER).mTarget == null) {
                this.addTarget(this.start, this.mWidget.getParent().mHorizontalRun.start, this.mWidget.getX());
                this.addTarget(this.end, this.start, this.mDimension.value);
            }
        }
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        if(this.start.resolved) {
            this.mWidget.setX(this.start.value);
        }
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void clear() {
        this.mRunGroup = null;
        this.start.clear();
        this.end.clear();
        this.mDimension.clear();
        this.mResolved = false;
    }

    private void computeInsetRatio(int[] arr_v, int v, int v1, int v2, int v3, float f, int v4) {
        int v5 = v1 - v;
        int v6 = v3 - v2;
        switch(v4) {
            case -1: {
                int v7 = (int)(((float)v6) * f + 0.5f);
                int v8 = (int)(((float)v5) / f + 0.5f);
                if(v7 <= v5) {
                    arr_v[0] = v7;
                    arr_v[1] = v6;
                    return;
                }
                if(v8 <= v6) {
                    arr_v[0] = v5;
                    arr_v[1] = v8;
                }
                return;
            }
            case 0: {
                arr_v[0] = (int)(((float)v6) * f + 0.5f);
                arr_v[1] = v6;
                return;
            }
            case 1: {
                arr_v[0] = v5;
                arr_v[1] = (int)(((float)v5) * f + 0.5f);
            }
        }
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void reset() {
        this.mResolved = false;
        this.start.clear();
        this.start.resolved = false;
        this.end.clear();
        this.end.resolved = false;
        this.mDimension.resolved = false;
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean supportsWrapComputation() {
        return this.mDimensionBehavior != DimensionBehaviour.MATCH_CONSTRAINT || this.mWidget.mMatchConstraintDefaultWidth == 0;
    }

    @Override
    public String toString() {
        return "HorizontalRun " + this.mWidget.getDebugName();
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void update(Dependency dependency0) {
        int v;
        if(androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun.1.$SwitchMap$androidx$constraintlayout$core$widgets$analyzer$WidgetRun$RunType[this.mRunType.ordinal()] == 3) {
            this.updateRunCenter(dependency0, this.mWidget.mLeft, this.mWidget.mRight, 0);
            return;
        }
        if(this.mDimension.resolved || this.mDimensionBehavior != DimensionBehaviour.MATCH_CONSTRAINT) {
        label_105:
            if(this.start.readyToSolve && this.end.readyToSolve && (!this.start.resolved || !this.end.resolved || !this.mDimension.resolved)) {
                if(!this.mDimension.resolved && this.mDimensionBehavior == DimensionBehaviour.MATCH_CONSTRAINT && this.mWidget.mMatchConstraintDefaultWidth == 0 && !this.mWidget.isInHorizontalChain()) {
                    DependencyNode dependencyNode2 = (DependencyNode)this.start.mTargets.get(0);
                    DependencyNode dependencyNode3 = (DependencyNode)this.end.mTargets.get(0);
                    int v32 = dependencyNode2.value + this.start.mMargin;
                    int v33 = dependencyNode3.value + this.end.mMargin;
                    this.start.resolve(v32);
                    this.end.resolve(v33);
                    this.mDimension.resolve(v33 - v32);
                    return;
                }
                if(!this.mDimension.resolved && this.mDimensionBehavior == DimensionBehaviour.MATCH_CONSTRAINT && this.matchConstraintsType == 1 && this.start.mTargets.size() > 0 && this.end.mTargets.size() > 0) {
                    DependencyNode dependencyNode4 = (DependencyNode)this.start.mTargets.get(0);
                    int v34 = Math.min(((DependencyNode)this.end.mTargets.get(0)).value + this.end.mMargin - (dependencyNode4.value + this.start.mMargin), this.mDimension.wrapValue);
                    int v35 = this.mWidget.mMatchConstraintMaxWidth;
                    this.mDimension.resolve((v35 <= 0 ? Math.max(this.mWidget.mMatchConstraintMinWidth, v34) : Math.min(v35, Math.max(this.mWidget.mMatchConstraintMinWidth, v34))));
                }
                if(this.mDimension.resolved) {
                    DependencyNode dependencyNode5 = (DependencyNode)this.start.mTargets.get(0);
                    DependencyNode dependencyNode6 = (DependencyNode)this.end.mTargets.get(0);
                    int v36 = dependencyNode5.value + this.start.mMargin;
                    int v37 = dependencyNode6.value + this.end.mMargin;
                    float f3 = this.mWidget.getHorizontalBiasPercent();
                    if(dependencyNode5 == dependencyNode6) {
                        v36 = dependencyNode5.value;
                        v37 = dependencyNode6.value;
                        f3 = 0.5f;
                    }
                    this.start.resolve(((int)(((float)v36) + 0.5f + ((float)(v37 - v36 - this.mDimension.value)) * f3)));
                    this.end.resolve(this.start.value + this.mDimension.value);
                }
            }
        }
        else {
            switch(this.mWidget.mMatchConstraintDefaultWidth) {
                case 2: {
                    ConstraintWidget constraintWidget0 = this.mWidget.getParent();
                    if(constraintWidget0 != null && constraintWidget0.mHorizontalRun.mDimension.resolved) {
                        this.mDimension.resolve(((int)(((float)constraintWidget0.mHorizontalRun.mDimension.value) * this.mWidget.mMatchConstraintPercentWidth + 0.5f)));
                    }
                    goto label_105;
                }
                case 3: {
                    if(this.mWidget.mMatchConstraintDefaultHeight == 0 || this.mWidget.mMatchConstraintDefaultHeight == 3) {
                        DependencyNode dependencyNode0 = this.mWidget.mVerticalRun.start;
                        DependencyNode dependencyNode1 = this.mWidget.mVerticalRun.end;
                        boolean z = this.mWidget.mLeft.mTarget != null;
                        boolean z1 = this.mWidget.mTop.mTarget != null;
                        boolean z2 = this.mWidget.mRight.mTarget != null;
                        boolean z3 = this.mWidget.mBottom.mTarget != null;
                        int v1 = this.mWidget.getDimensionRatioSide();
                        if(z && z1 && z2 && z3) {
                            float f = this.mWidget.getDimensionRatio();
                            if(dependencyNode0.resolved && dependencyNode1.resolved) {
                                if(this.start.readyToSolve && this.end.readyToSolve) {
                                    int v2 = ((DependencyNode)this.start.mTargets.get(0)).value;
                                    int v3 = this.start.mMargin + v2;
                                    int v4 = ((DependencyNode)this.end.mTargets.get(0)).value;
                                    this.computeInsetRatio(HorizontalWidgetRun.sTempDimensions, v3, v4 - this.end.mMargin, dependencyNode0.mMargin + dependencyNode0.value, dependencyNode1.value - dependencyNode1.mMargin, f, v1);
                                    this.mDimension.resolve(HorizontalWidgetRun.sTempDimensions[0]);
                                    this.mWidget.mVerticalRun.mDimension.resolve(HorizontalWidgetRun.sTempDimensions[1]);
                                    return;
                                }
                            }
                            else if(!this.start.resolved || !this.end.resolved) {
                            label_49:
                                if(this.start.readyToSolve && this.end.readyToSolve && dependencyNode0.readyToSolve && dependencyNode1.readyToSolve) {
                                    int v10 = ((DependencyNode)this.start.mTargets.get(0)).value;
                                    int v11 = this.start.mMargin + v10;
                                    int v12 = ((DependencyNode)this.end.mTargets.get(0)).value - this.end.mMargin;
                                    int v13 = ((DependencyNode)dependencyNode0.mTargets.get(0)).value;
                                    int v14 = dependencyNode0.mMargin + v13;
                                    int v15 = ((DependencyNode)dependencyNode1.mTargets.get(0)).value;
                                    this.computeInsetRatio(HorizontalWidgetRun.sTempDimensions, v11, v12, v14, v15 - dependencyNode1.mMargin, f, v1);
                                    this.mDimension.resolve(HorizontalWidgetRun.sTempDimensions[0]);
                                    this.mWidget.mVerticalRun.mDimension.resolve(HorizontalWidgetRun.sTempDimensions[1]);
                                    goto label_105;
                                }
                            }
                            else if(dependencyNode0.readyToSolve && dependencyNode1.readyToSolve) {
                                int v5 = this.start.mMargin + this.start.value;
                                int v6 = this.end.value - this.end.mMargin;
                                int v7 = ((DependencyNode)dependencyNode0.mTargets.get(0)).value;
                                int v8 = dependencyNode0.mMargin + v7;
                                int v9 = ((DependencyNode)dependencyNode1.mTargets.get(0)).value;
                                this.computeInsetRatio(HorizontalWidgetRun.sTempDimensions, v5, v6, v8, v9 - dependencyNode1.mMargin, f, v1);
                                this.mDimension.resolve(HorizontalWidgetRun.sTempDimensions[0]);
                                this.mWidget.mVerticalRun.mDimension.resolve(HorizontalWidgetRun.sTempDimensions[1]);
                                goto label_49;
                            }
                        }
                        else if(!z || !z2) {
                            if(!z1 || !z3) {
                                goto label_105;
                            }
                            else if(dependencyNode0.readyToSolve && dependencyNode1.readyToSolve) {
                                float f2 = this.mWidget.getDimensionRatio();
                                int v24 = ((DependencyNode)dependencyNode0.mTargets.get(0)).value + dependencyNode0.mMargin;
                                int v25 = ((DependencyNode)dependencyNode1.mTargets.get(0)).value - dependencyNode1.mMargin;
                                switch(v1) {
                                    case 0: {
                                        int v29 = this.getLimitedDimension(v25 - v24, 1);
                                        int v30 = (int)(((float)v29) * f2 + 0.5f);
                                        int v31 = this.getLimitedDimension(v30, 0);
                                        if(v30 != v31) {
                                            v29 = (int)(((float)v31) / f2 + 0.5f);
                                        }
                                        this.mDimension.resolve(v31);
                                        this.mWidget.mVerticalRun.mDimension.resolve(v29);
                                        goto label_105;
                                    }
                                    case -1: 
                                    case 1: {
                                        int v26 = this.getLimitedDimension(v25 - v24, 1);
                                        int v27 = (int)(((float)v26) / f2 + 0.5f);
                                        int v28 = this.getLimitedDimension(v27, 0);
                                        if(v27 != v28) {
                                            v26 = (int)(((float)v28) * f2 + 0.5f);
                                        }
                                        this.mDimension.resolve(v28);
                                        this.mWidget.mVerticalRun.mDimension.resolve(v26);
                                        goto label_105;
                                    }
                                    default: {
                                        goto label_105;
                                    }
                                }
                            }
                        }
                        else if(this.start.readyToSolve && this.end.readyToSolve) {
                            float f1 = this.mWidget.getDimensionRatio();
                            int v16 = ((DependencyNode)this.start.mTargets.get(0)).value + this.start.mMargin;
                            int v17 = ((DependencyNode)this.end.mTargets.get(0)).value - this.end.mMargin;
                            switch(v1) {
                                case -1: 
                                case 0: {
                                    int v18 = this.getLimitedDimension(v17 - v16, 0);
                                    int v19 = (int)(((float)v18) * f1 + 0.5f);
                                    int v20 = this.getLimitedDimension(v19, 1);
                                    if(v19 != v20) {
                                        v18 = (int)(((float)v20) / f1 + 0.5f);
                                    }
                                    this.mDimension.resolve(v18);
                                    this.mWidget.mVerticalRun.mDimension.resolve(v20);
                                    goto label_105;
                                }
                                case 1: {
                                    int v21 = this.getLimitedDimension(v17 - v16, 0);
                                    int v22 = (int)(((float)v21) / f1 + 0.5f);
                                    int v23 = this.getLimitedDimension(v22, 1);
                                    if(v22 != v23) {
                                        v21 = (int)(((float)v23) * f1 + 0.5f);
                                    }
                                    this.mDimension.resolve(v21);
                                    this.mWidget.mVerticalRun.mDimension.resolve(v23);
                                    goto label_105;
                                }
                                default: {
                                    goto label_105;
                                }
                            }
                        }
                    }
                    else {
                        switch(this.mWidget.getDimensionRatioSide()) {
                            case -1: {
                                v = (int)(((float)this.mWidget.mVerticalRun.mDimension.value) * this.mWidget.getDimensionRatio() + 0.5f);
                                break;
                            }
                            case 0: {
                                v = (int)(((float)this.mWidget.mVerticalRun.mDimension.value) / this.mWidget.getDimensionRatio() + 0.5f);
                                break;
                            }
                            case 1: {
                                v = (int)(((float)this.mWidget.mVerticalRun.mDimension.value) * this.mWidget.getDimensionRatio() + 0.5f);
                                break;
                            }
                            default: {
                                v = 0;
                            }
                        }
                        this.mDimension.resolve(v);
                        goto label_105;
                    }
                    break;
                }
                default: {
                    goto label_105;
                }
            }
        }
    }
}

