package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget.DimensionBehaviour;
import androidx.constraintlayout.core.widgets.ConstraintWidget;

public abstract class WidgetRun implements Dependency {
    static enum RunType {
        NONE,
        START,
        END,
        CENTER;

        private static RunType[] $values() [...] // Inlined contents
    }

    public DependencyNode end;
    DimensionDependency mDimension;
    protected DimensionBehaviour mDimensionBehavior;
    boolean mResolved;
    RunGroup mRunGroup;
    protected RunType mRunType;
    ConstraintWidget mWidget;
    public int matchConstraintsType;
    public int orientation;
    public DependencyNode start;

    public WidgetRun(ConstraintWidget constraintWidget0) {
        this.mDimension = new DimensionDependency(this);
        this.orientation = 0;
        this.mResolved = false;
        this.start = new DependencyNode(this);
        this.end = new DependencyNode(this);
        this.mRunType = RunType.NONE;
        this.mWidget = constraintWidget0;
    }

    protected final void addTarget(DependencyNode dependencyNode0, DependencyNode dependencyNode1, int v) {
        dependencyNode0.mTargets.add(dependencyNode1);
        dependencyNode0.mMargin = v;
        dependencyNode1.mDependencies.add(dependencyNode0);
    }

    protected final void addTarget(DependencyNode dependencyNode0, DependencyNode dependencyNode1, int v, DimensionDependency dimensionDependency0) {
        dependencyNode0.mTargets.add(dependencyNode1);
        dependencyNode0.mTargets.add(this.mDimension);
        dependencyNode0.mMarginFactor = v;
        dependencyNode0.mMarginDependency = dimensionDependency0;
        dependencyNode1.mDependencies.add(dependencyNode0);
        dimensionDependency0.mDependencies.add(dependencyNode0);
    }

    abstract void apply();

    abstract void applyToWidget();

    abstract void clear();

    protected final int getLimitedDimension(int v, int v1) {
        if(v1 == 0) {
            int v2 = this.mWidget.mMatchConstraintMaxWidth;
            int v3 = v2 <= 0 ? Math.max(this.mWidget.mMatchConstraintMinWidth, v) : Math.min(v2, v);
            return v3 == v ? v : v3;
        }
        int v4 = this.mWidget.mMatchConstraintMaxHeight;
        int v5 = v4 <= 0 ? Math.max(this.mWidget.mMatchConstraintMinHeight, v) : Math.min(v4, v);
        return v5 == v ? v : v5;
    }

    protected final DependencyNode getTarget(ConstraintAnchor constraintAnchor0) {
        if(constraintAnchor0.mTarget == null) {
            return null;
        }
        ConstraintWidget constraintWidget0 = constraintAnchor0.mTarget.mOwner;
        switch(constraintAnchor0.mTarget.mType) {
            case 1: {
                return constraintWidget0.mHorizontalRun.start;
            }
            case 2: {
                return constraintWidget0.mHorizontalRun.end;
            }
            case 3: {
                return constraintWidget0.mVerticalRun.start;
            }
            case 4: {
                return constraintWidget0.mVerticalRun.baseline;
            }
            case 5: {
                return constraintWidget0.mVerticalRun.end;
            }
            default: {
                return null;
            }
        }
    }

    protected final DependencyNode getTarget(ConstraintAnchor constraintAnchor0, int v) {
        if(constraintAnchor0.mTarget == null) {
            return null;
        }
        ConstraintWidget constraintWidget0 = constraintAnchor0.mTarget.mOwner;
        HorizontalWidgetRun horizontalWidgetRun0 = v == 0 ? constraintWidget0.mHorizontalRun : constraintWidget0.mVerticalRun;
        switch(constraintAnchor0.mTarget.mType) {
            case 1: 
            case 3: {
                return horizontalWidgetRun0.start;
            }
            case 2: 
            case 5: {
                return horizontalWidgetRun0.end;
            }
            default: {
                return null;
            }
        }
    }

    // 去混淆评级： 低(20)
    public long getWrapDimension() {
        return this.mDimension.resolved ? ((long)this.mDimension.value) : 0L;
    }

    public boolean isCenterConnection() {
        int v = this.start.mTargets.size();
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            if(((DependencyNode)this.start.mTargets.get(v1)).mRun != this) {
                ++v2;
            }
        }
        int v3 = this.end.mTargets.size();
        for(int v4 = 0; v4 < v3; ++v4) {
            if(((DependencyNode)this.end.mTargets.get(v4)).mRun != this) {
                ++v2;
            }
        }
        return v2 >= 2;
    }

    public boolean isDimensionResolved() {
        return this.mDimension.resolved;
    }

    public boolean isResolved() {
        return this.mResolved;
    }

    abstract void reset();

    private void resolveDimension(int v, int v1) {
        switch(this.matchConstraintsType) {
            case 0: {
                this.mDimension.resolve(this.getLimitedDimension(v1, v));
                return;
            }
            case 1: {
                int v2 = Math.min(this.getLimitedDimension(this.mDimension.wrapValue, v), v1);
                this.mDimension.resolve(v2);
                return;
            }
            case 2: {
                ConstraintWidget constraintWidget0 = this.mWidget.getParent();
                if(constraintWidget0 != null) {
                    HorizontalWidgetRun horizontalWidgetRun0 = v == 0 ? constraintWidget0.mHorizontalRun : constraintWidget0.mVerticalRun;
                    if(horizontalWidgetRun0.mDimension.resolved) {
                        this.mDimension.resolve(this.getLimitedDimension(((int)(((float)horizontalWidgetRun0.mDimension.value) * (v == 0 ? this.mWidget.mMatchConstraintPercentWidth : this.mWidget.mMatchConstraintPercentHeight) + 0.5f)), v));
                        return;
                    }
                }
                break;
            }
            case 3: {
                if(this.mWidget.mHorizontalRun.mDimensionBehavior != DimensionBehaviour.MATCH_CONSTRAINT || this.mWidget.mHorizontalRun.matchConstraintsType != 3 || this.mWidget.mVerticalRun.mDimensionBehavior != DimensionBehaviour.MATCH_CONSTRAINT || this.mWidget.mVerticalRun.matchConstraintsType != 3) {
                    VerticalWidgetRun verticalWidgetRun0 = v == 0 ? this.mWidget.mVerticalRun : this.mWidget.mHorizontalRun;
                    if(verticalWidgetRun0.mDimension.resolved) {
                        float f = this.mWidget.getDimensionRatio();
                        this.mDimension.resolve((v == 1 ? ((int)(((float)verticalWidgetRun0.mDimension.value) / f + 0.5f)) : ((int)(f * ((float)verticalWidgetRun0.mDimension.value) + 0.5f))));
                        return;
                    }
                }
                break;
            }
        }
    }

    abstract boolean supportsWrapComputation();

    @Override  // androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency dependency0) {
    }

    protected void updateRunCenter(Dependency dependency0, ConstraintAnchor constraintAnchor0, ConstraintAnchor constraintAnchor1, int v) {
        DependencyNode dependencyNode0 = this.getTarget(constraintAnchor0);
        DependencyNode dependencyNode1 = this.getTarget(constraintAnchor1);
        if(dependencyNode0.resolved && dependencyNode1.resolved) {
            int v1 = dependencyNode0.value + constraintAnchor0.getMargin();
            int v2 = dependencyNode1.value - constraintAnchor1.getMargin();
            int v3 = v2 - v1;
            if(!this.mDimension.resolved && this.mDimensionBehavior == DimensionBehaviour.MATCH_CONSTRAINT) {
                this.resolveDimension(v, v3);
            }
            if(this.mDimension.resolved) {
                if(this.mDimension.value == v3) {
                    this.start.resolve(v1);
                    this.end.resolve(v2);
                    return;
                }
                float f = v == 0 ? this.mWidget.getHorizontalBiasPercent() : this.mWidget.getVerticalBiasPercent();
                if(dependencyNode0 == dependencyNode1) {
                    v1 = dependencyNode0.value;
                    v2 = dependencyNode1.value;
                    f = 0.5f;
                }
                this.start.resolve(((int)(((float)v1) + 0.5f + ((float)(v2 - v1 - this.mDimension.value)) * f)));
                this.end.resolve(this.start.value + this.mDimension.value);
            }
        }
    }

    protected void updateRunEnd(Dependency dependency0) {
    }

    protected void updateRunStart(Dependency dependency0) {
    }

    public long wrapSize(int v) {
        if(this.mDimension.resolved) {
            long v1 = (long)this.mDimension.value;
            if(this.isCenterConnection()) {
                return v1 + ((long)(this.start.mMargin - this.end.mMargin));
            }
            return v == 0 ? v1 + ((long)this.start.mMargin) : v1 - ((long)this.end.mMargin);
        }
        return 0L;
    }
}

