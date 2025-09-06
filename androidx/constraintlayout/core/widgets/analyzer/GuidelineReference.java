package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Guideline;

class GuidelineReference extends WidgetRun {
    GuidelineReference(ConstraintWidget constraintWidget0) {
        super(constraintWidget0);
        constraintWidget0.mHorizontalRun.clear();
        constraintWidget0.mVerticalRun.clear();
        this.orientation = ((Guideline)constraintWidget0).getOrientation();
    }

    private void addDependency(DependencyNode dependencyNode0) {
        this.start.mDependencies.add(dependencyNode0);
        dependencyNode0.mTargets.add(this.start);
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void apply() {
        Guideline guideline0 = (Guideline)this.mWidget;
        int v = guideline0.getRelativeBegin();
        int v1 = guideline0.getRelativeEnd();
        if(guideline0.getOrientation() == 1) {
            if(v != -1) {
                this.start.mTargets.add(this.mWidget.mParent.mHorizontalRun.start);
                this.mWidget.mParent.mHorizontalRun.start.mDependencies.add(this.start);
                this.start.mMargin = v;
            }
            else if(v1 == -1) {
                this.start.delegateToWidgetRun = true;
                this.start.mTargets.add(this.mWidget.mParent.mHorizontalRun.end);
                this.mWidget.mParent.mHorizontalRun.end.mDependencies.add(this.start);
            }
            else {
                this.start.mTargets.add(this.mWidget.mParent.mHorizontalRun.end);
                this.mWidget.mParent.mHorizontalRun.end.mDependencies.add(this.start);
                this.start.mMargin = -v1;
            }
            this.addDependency(this.mWidget.mHorizontalRun.start);
            this.addDependency(this.mWidget.mHorizontalRun.end);
            return;
        }
        if(v != -1) {
            this.start.mTargets.add(this.mWidget.mParent.mVerticalRun.start);
            this.mWidget.mParent.mVerticalRun.start.mDependencies.add(this.start);
            this.start.mMargin = v;
        }
        else if(v1 == -1) {
            this.start.delegateToWidgetRun = true;
            this.start.mTargets.add(this.mWidget.mParent.mVerticalRun.end);
            this.mWidget.mParent.mVerticalRun.end.mDependencies.add(this.start);
        }
        else {
            this.start.mTargets.add(this.mWidget.mParent.mVerticalRun.end);
            this.mWidget.mParent.mVerticalRun.end.mDependencies.add(this.start);
            this.start.mMargin = -v1;
        }
        this.addDependency(this.mWidget.mVerticalRun.start);
        this.addDependency(this.mWidget.mVerticalRun.end);
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        if(((Guideline)this.mWidget).getOrientation() == 1) {
            this.mWidget.setX(this.start.value);
            return;
        }
        this.mWidget.setY(this.start.value);
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void clear() {
        this.start.clear();
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void reset() {
        this.start.resolved = false;
        this.end.resolved = false;
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean supportsWrapComputation() {
        return false;
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void update(Dependency dependency0) {
        if(!this.start.readyToSolve || this.start.resolved) {
            return;
        }
        float f = (float)((DependencyNode)this.start.mTargets.get(0)).value;
        this.start.resolve(((int)(f * ((Guideline)this.mWidget).getRelativePercent() + 0.5f)));
    }
}

