package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Helper;

public class VerticalWidgetRun extends WidgetRun {
    private static final boolean FORCE_USE = true;
    public DependencyNode baseline;
    DimensionDependency mBaselineDimension;

    public VerticalWidgetRun(ConstraintWidget constraintWidget0) {
        super(constraintWidget0);
        this.baseline = new DependencyNode(this);
        this.mBaselineDimension = null;
        this.start.mType = Type.TOP;
        this.end.mType = Type.BOTTOM;
        this.baseline.mType = Type.BASELINE;
        this.orientation = 1;
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void apply() {
        if(this.mWidget.measured) {
            this.mDimension.resolve(this.mWidget.getHeight());
        }
        if(!this.mDimension.resolved) {
            this.mDimensionBehavior = this.mWidget.getVerticalDimensionBehaviour();
            if(this.mWidget.hasBaseline()) {
                this.mBaselineDimension = new BaselineDimensionDependency(this);
            }
            if(this.mDimensionBehavior != DimensionBehaviour.MATCH_CONSTRAINT) {
                if(this.mDimensionBehavior == DimensionBehaviour.MATCH_PARENT) {
                    ConstraintWidget constraintWidget0 = this.mWidget.getParent();
                    if(constraintWidget0 != null && constraintWidget0.getVerticalDimensionBehaviour() == DimensionBehaviour.FIXED) {
                        int v = constraintWidget0.getHeight();
                        int v1 = this.mWidget.mTop.getMargin();
                        int v2 = this.mWidget.mBottom.getMargin();
                        this.addTarget(this.start, constraintWidget0.mVerticalRun.start, this.mWidget.mTop.getMargin());
                        this.addTarget(this.end, constraintWidget0.mVerticalRun.end, -this.mWidget.mBottom.getMargin());
                        this.mDimension.resolve(v - v1 - v2);
                        return;
                    }
                }
                if(this.mDimensionBehavior == DimensionBehaviour.FIXED) {
                    this.mDimension.resolve(this.mWidget.getHeight());
                }
            }
        }
        else if(this.mDimensionBehavior == DimensionBehaviour.MATCH_PARENT) {
            ConstraintWidget constraintWidget1 = this.mWidget.getParent();
            if(constraintWidget1 != null && constraintWidget1.getVerticalDimensionBehaviour() == DimensionBehaviour.FIXED) {
                this.addTarget(this.start, constraintWidget1.mVerticalRun.start, this.mWidget.mTop.getMargin());
                this.addTarget(this.end, constraintWidget1.mVerticalRun.end, -this.mWidget.mBottom.getMargin());
                return;
            }
        }
        if(!this.mDimension.resolved || !this.mWidget.measured) {
            if(this.mDimension.resolved || this.mDimensionBehavior != DimensionBehaviour.MATCH_CONSTRAINT) {
                this.mDimension.addDependency(this);
            }
            else {
                switch(this.mWidget.mMatchConstraintDefaultHeight) {
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
                        if(!this.mWidget.isInVerticalChain() && this.mWidget.mMatchConstraintDefaultWidth != 3) {
                            DimensionDependency dimensionDependency1 = this.mWidget.mHorizontalRun.mDimension;
                            this.mDimension.mTargets.add(dimensionDependency1);
                            dimensionDependency1.mDependencies.add(this.mDimension);
                            this.mDimension.delegateToWidgetRun = true;
                            this.mDimension.mDependencies.add(this.start);
                            this.mDimension.mDependencies.add(this.end);
                        }
                    }
                }
            }
            if(this.mWidget.mListAnchors[2].mTarget != null && this.mWidget.mListAnchors[3].mTarget != null) {
                if(this.mWidget.isInVerticalChain()) {
                    this.start.mMargin = this.mWidget.mListAnchors[2].getMargin();
                    this.end.mMargin = -this.mWidget.mListAnchors[3].getMargin();
                }
                else {
                    DependencyNode dependencyNode5 = this.getTarget(this.mWidget.mListAnchors[2]);
                    DependencyNode dependencyNode6 = this.getTarget(this.mWidget.mListAnchors[3]);
                    if(dependencyNode5 != null) {
                        dependencyNode5.addDependency(this);
                    }
                    if(dependencyNode6 != null) {
                        dependencyNode6.addDependency(this);
                    }
                    this.mRunType = RunType.CENTER;
                }
                if(this.mWidget.hasBaseline()) {
                    this.addTarget(this.baseline, this.start, 1, this.mBaselineDimension);
                }
            }
            else if(this.mWidget.mListAnchors[2].mTarget != null) {
                DependencyNode dependencyNode7 = this.getTarget(this.mWidget.mListAnchors[2]);
                if(dependencyNode7 != null) {
                    this.addTarget(this.start, dependencyNode7, this.mWidget.mListAnchors[2].getMargin());
                    this.addTarget(this.end, this.start, 1, this.mDimension);
                    if(this.mWidget.hasBaseline()) {
                        this.addTarget(this.baseline, this.start, 1, this.mBaselineDimension);
                    }
                    if(this.mDimensionBehavior == DimensionBehaviour.MATCH_CONSTRAINT && this.mWidget.getDimensionRatio() > 0.0f && this.mWidget.mHorizontalRun.mDimensionBehavior == DimensionBehaviour.MATCH_CONSTRAINT) {
                        this.mWidget.mHorizontalRun.mDimension.mDependencies.add(this.mDimension);
                        this.mDimension.mTargets.add(this.mWidget.mHorizontalRun.mDimension);
                        this.mDimension.updateDelegate = this;
                    }
                }
            }
            else if(this.mWidget.mListAnchors[3].mTarget != null) {
                DependencyNode dependencyNode8 = this.getTarget(this.mWidget.mListAnchors[3]);
                if(dependencyNode8 != null) {
                    this.addTarget(this.end, dependencyNode8, -this.mWidget.mListAnchors[3].getMargin());
                    this.addTarget(this.start, this.end, -1, this.mDimension);
                    if(this.mWidget.hasBaseline()) {
                        this.addTarget(this.baseline, this.start, 1, this.mBaselineDimension);
                    }
                }
            }
            else if(this.mWidget.mListAnchors[4].mTarget != null) {
                DependencyNode dependencyNode9 = this.getTarget(this.mWidget.mListAnchors[4]);
                if(dependencyNode9 != null) {
                    this.addTarget(this.baseline, dependencyNode9, 0);
                    this.addTarget(this.start, this.baseline, -1, this.mBaselineDimension);
                    this.addTarget(this.end, this.start, 1, this.mDimension);
                }
            }
            else if(!(this.mWidget instanceof Helper) && this.mWidget.getParent() != null) {
                this.addTarget(this.start, this.mWidget.getParent().mVerticalRun.start, this.mWidget.getY());
                this.addTarget(this.end, this.start, 1, this.mDimension);
                if(this.mWidget.hasBaseline()) {
                    this.addTarget(this.baseline, this.start, 1, this.mBaselineDimension);
                }
                if(this.mDimensionBehavior == DimensionBehaviour.MATCH_CONSTRAINT && this.mWidget.getDimensionRatio() > 0.0f && this.mWidget.mHorizontalRun.mDimensionBehavior == DimensionBehaviour.MATCH_CONSTRAINT) {
                    this.mWidget.mHorizontalRun.mDimension.mDependencies.add(this.mDimension);
                    this.mDimension.mTargets.add(this.mWidget.mHorizontalRun.mDimension);
                    this.mDimension.updateDelegate = this;
                }
            }
            if(this.mDimension.mTargets.size() == 0) {
                this.mDimension.readyToSolve = true;
            }
        }
        else if(this.mWidget.mListAnchors[2].mTarget != null && this.mWidget.mListAnchors[3].mTarget != null) {
            if(this.mWidget.isInVerticalChain()) {
                this.start.mMargin = this.mWidget.mListAnchors[2].getMargin();
                this.end.mMargin = -this.mWidget.mListAnchors[3].getMargin();
            }
            else {
                DependencyNode dependencyNode0 = this.getTarget(this.mWidget.mListAnchors[2]);
                if(dependencyNode0 != null) {
                    this.addTarget(this.start, dependencyNode0, this.mWidget.mListAnchors[2].getMargin());
                }
                DependencyNode dependencyNode1 = this.getTarget(this.mWidget.mListAnchors[3]);
                if(dependencyNode1 != null) {
                    this.addTarget(this.end, dependencyNode1, -this.mWidget.mListAnchors[3].getMargin());
                }
                this.start.delegateToWidgetRun = true;
                this.end.delegateToWidgetRun = true;
            }
            if(this.mWidget.hasBaseline()) {
                this.addTarget(this.baseline, this.start, this.mWidget.getBaselineDistance());
            }
        }
        else if(this.mWidget.mListAnchors[2].mTarget != null) {
            DependencyNode dependencyNode2 = this.getTarget(this.mWidget.mListAnchors[2]);
            if(dependencyNode2 != null) {
                this.addTarget(this.start, dependencyNode2, this.mWidget.mListAnchors[2].getMargin());
                this.addTarget(this.end, this.start, this.mDimension.value);
                if(this.mWidget.hasBaseline()) {
                    this.addTarget(this.baseline, this.start, this.mWidget.getBaselineDistance());
                }
            }
        }
        else if(this.mWidget.mListAnchors[3].mTarget != null) {
            DependencyNode dependencyNode3 = this.getTarget(this.mWidget.mListAnchors[3]);
            if(dependencyNode3 != null) {
                this.addTarget(this.end, dependencyNode3, -this.mWidget.mListAnchors[3].getMargin());
                this.addTarget(this.start, this.end, -this.mDimension.value);
            }
            if(this.mWidget.hasBaseline()) {
                this.addTarget(this.baseline, this.start, this.mWidget.getBaselineDistance());
            }
        }
        else if(this.mWidget.mListAnchors[4].mTarget != null) {
            DependencyNode dependencyNode4 = this.getTarget(this.mWidget.mListAnchors[4]);
            if(dependencyNode4 != null) {
                this.addTarget(this.baseline, dependencyNode4, 0);
                this.addTarget(this.start, this.baseline, -this.mWidget.getBaselineDistance());
                this.addTarget(this.end, this.start, this.mDimension.value);
            }
        }
        else if(!(this.mWidget instanceof Helper) && this.mWidget.getParent() != null && this.mWidget.getAnchor(androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.CENTER).mTarget == null) {
            this.addTarget(this.start, this.mWidget.getParent().mVerticalRun.start, this.mWidget.getY());
            this.addTarget(this.end, this.start, this.mDimension.value);
            if(this.mWidget.hasBaseline()) {
                this.addTarget(this.baseline, this.start, this.mWidget.getBaselineDistance());
            }
        }
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        if(this.start.resolved) {
            this.mWidget.setY(this.start.value);
        }
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void clear() {
        this.mRunGroup = null;
        this.start.clear();
        this.end.clear();
        this.baseline.clear();
        this.mDimension.clear();
        this.mResolved = false;
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void reset() {
        this.mResolved = false;
        this.start.clear();
        this.start.resolved = false;
        this.end.clear();
        this.end.resolved = false;
        this.baseline.clear();
        this.baseline.resolved = false;
        this.mDimension.resolved = false;
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean supportsWrapComputation() {
        return this.mDimensionBehavior != DimensionBehaviour.MATCH_CONSTRAINT || this.mWidget.mMatchConstraintDefaultHeight == 0;
    }

    @Override
    public String toString() {
        return "VerticalRun " + this.mWidget.getDebugName();
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void update(Dependency dependency0) {
        int v;
        if(androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun.1.$SwitchMap$androidx$constraintlayout$core$widgets$analyzer$WidgetRun$RunType[this.mRunType.ordinal()] == 3) {
            this.updateRunCenter(dependency0, this.mWidget.mTop, this.mWidget.mBottom, 1);
            return;
        }
        if(this.mDimension.readyToSolve && !this.mDimension.resolved && this.mDimensionBehavior == DimensionBehaviour.MATCH_CONSTRAINT) {
            switch(this.mWidget.mMatchConstraintDefaultHeight) {
                case 2: {
                    ConstraintWidget constraintWidget0 = this.mWidget.getParent();
                    if(constraintWidget0 != null && constraintWidget0.mVerticalRun.mDimension.resolved) {
                        this.mDimension.resolve(((int)(((float)constraintWidget0.mVerticalRun.mDimension.value) * this.mWidget.mMatchConstraintPercentHeight + 0.5f)));
                    }
                    break;
                }
                case 3: {
                    if(this.mWidget.mHorizontalRun.mDimension.resolved) {
                        switch(this.mWidget.getDimensionRatioSide()) {
                            case -1: {
                                v = (int)(((float)this.mWidget.mHorizontalRun.mDimension.value) / this.mWidget.getDimensionRatio() + 0.5f);
                                break;
                            }
                            case 0: {
                                v = (int)(((float)this.mWidget.mHorizontalRun.mDimension.value) * this.mWidget.getDimensionRatio() + 0.5f);
                                break;
                            }
                            case 1: {
                                v = (int)(((float)this.mWidget.mHorizontalRun.mDimension.value) / this.mWidget.getDimensionRatio() + 0.5f);
                                break;
                            }
                            default: {
                                v = 0;
                            }
                        }
                        this.mDimension.resolve(v);
                    }
                }
            }
        }
        if(this.start.readyToSolve && this.end.readyToSolve && (!this.start.resolved || !this.end.resolved || !this.mDimension.resolved)) {
            if(!this.mDimension.resolved && this.mDimensionBehavior == DimensionBehaviour.MATCH_CONSTRAINT && this.mWidget.mMatchConstraintDefaultWidth == 0 && !this.mWidget.isInVerticalChain()) {
                DependencyNode dependencyNode0 = (DependencyNode)this.start.mTargets.get(0);
                DependencyNode dependencyNode1 = (DependencyNode)this.end.mTargets.get(0);
                int v1 = dependencyNode0.value + this.start.mMargin;
                int v2 = dependencyNode1.value + this.end.mMargin;
                this.start.resolve(v1);
                this.end.resolve(v2);
                this.mDimension.resolve(v2 - v1);
                return;
            }
            if(!this.mDimension.resolved && this.mDimensionBehavior == DimensionBehaviour.MATCH_CONSTRAINT && this.matchConstraintsType == 1 && this.start.mTargets.size() > 0 && this.end.mTargets.size() > 0) {
                DependencyNode dependencyNode2 = (DependencyNode)this.start.mTargets.get(0);
                int v3 = ((DependencyNode)this.end.mTargets.get(0)).value + this.end.mMargin - (dependencyNode2.value + this.start.mMargin);
                if(v3 < this.mDimension.wrapValue) {
                    this.mDimension.resolve(v3);
                }
                else {
                    this.mDimension.resolve(this.mDimension.wrapValue);
                }
            }
            if(this.mDimension.resolved && this.start.mTargets.size() > 0 && this.end.mTargets.size() > 0) {
                DependencyNode dependencyNode3 = (DependencyNode)this.start.mTargets.get(0);
                DependencyNode dependencyNode4 = (DependencyNode)this.end.mTargets.get(0);
                int v4 = dependencyNode3.value + this.start.mMargin;
                int v5 = dependencyNode4.value + this.end.mMargin;
                float f = this.mWidget.getVerticalBiasPercent();
                if(dependencyNode3 == dependencyNode4) {
                    v4 = dependencyNode3.value;
                    v5 = dependencyNode4.value;
                    f = 0.5f;
                }
                this.start.resolve(((int)(((float)v4) + 0.5f + ((float)(v5 - v4 - this.mDimension.value)) * f)));
                this.end.resolve(this.start.value + this.mDimension.value);
            }
        }
    }
}

