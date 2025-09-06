package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintWidget;

class HelperReferences extends WidgetRun {
    HelperReferences(ConstraintWidget constraintWidget0) {
        super(constraintWidget0);
    }

    private void addDependency(DependencyNode dependencyNode0) {
        this.start.mDependencies.add(dependencyNode0);
        dependencyNode0.mTargets.add(this.start);
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void apply() {
        if(this.mWidget instanceof Barrier) {
            this.start.delegateToWidgetRun = true;
            Barrier barrier0 = (Barrier)this.mWidget;
            boolean z = barrier0.getAllowsGoneWidget();
            int v = 0;
            switch(barrier0.getBarrierType()) {
                case 0: {
                    this.start.mType = Type.LEFT;
                    while(v < barrier0.mWidgetsCount) {
                        ConstraintWidget constraintWidget3 = barrier0.mWidgets[v];
                        if(z || constraintWidget3.getVisibility() != 8) {
                            DependencyNode dependencyNode3 = constraintWidget3.mHorizontalRun.start;
                            dependencyNode3.mDependencies.add(this.start);
                            this.start.mTargets.add(dependencyNode3);
                        }
                        ++v;
                    }
                    this.addDependency(this.mWidget.mHorizontalRun.start);
                    this.addDependency(this.mWidget.mHorizontalRun.end);
                    break;
                }
                case 1: {
                    this.start.mType = Type.RIGHT;
                    while(v < barrier0.mWidgetsCount) {
                        ConstraintWidget constraintWidget0 = barrier0.mWidgets[v];
                        if(z || constraintWidget0.getVisibility() != 8) {
                            DependencyNode dependencyNode0 = constraintWidget0.mHorizontalRun.end;
                            dependencyNode0.mDependencies.add(this.start);
                            this.start.mTargets.add(dependencyNode0);
                        }
                        ++v;
                    }
                    this.addDependency(this.mWidget.mHorizontalRun.start);
                    this.addDependency(this.mWidget.mHorizontalRun.end);
                    return;
                }
                case 2: {
                    this.start.mType = Type.TOP;
                    while(v < barrier0.mWidgetsCount) {
                        ConstraintWidget constraintWidget1 = barrier0.mWidgets[v];
                        if(z || constraintWidget1.getVisibility() != 8) {
                            DependencyNode dependencyNode1 = constraintWidget1.mVerticalRun.start;
                            dependencyNode1.mDependencies.add(this.start);
                            this.start.mTargets.add(dependencyNode1);
                        }
                        ++v;
                    }
                    this.addDependency(this.mWidget.mVerticalRun.start);
                    this.addDependency(this.mWidget.mVerticalRun.end);
                    return;
                }
                case 3: {
                    this.start.mType = Type.BOTTOM;
                    while(v < barrier0.mWidgetsCount) {
                        ConstraintWidget constraintWidget2 = barrier0.mWidgets[v];
                        if(z || constraintWidget2.getVisibility() != 8) {
                            DependencyNode dependencyNode2 = constraintWidget2.mVerticalRun.end;
                            dependencyNode2.mDependencies.add(this.start);
                            this.start.mTargets.add(dependencyNode2);
                        }
                        ++v;
                    }
                    this.addDependency(this.mWidget.mVerticalRun.start);
                    this.addDependency(this.mWidget.mVerticalRun.end);
                }
            }
        }
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void applyToWidget() {
        if(this.mWidget instanceof Barrier) {
            switch(((Barrier)this.mWidget).getBarrierType()) {
                case 0: 
                case 1: {
                    this.mWidget.setX(this.start.value);
                    break;
                }
                default: {
                    this.mWidget.setY(this.start.value);
                }
            }
        }
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void clear() {
        this.mRunGroup = null;
        this.start.clear();
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    void reset() {
        this.start.resolved = false;
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    boolean supportsWrapComputation() {
        return false;
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public void update(Dependency dependency0) {
        Barrier barrier0 = (Barrier)this.mWidget;
        int v = barrier0.getBarrierType();
        int v1 = 0;
        int v2 = -1;
        for(Object object0: this.start.mTargets) {
            int v3 = ((DependencyNode)object0).value;
            if(v2 == -1 || v3 < v2) {
                v2 = v3;
            }
            if(v1 < v3) {
                v1 = v3;
            }
        }
        if(v != 0 && v != 2) {
            this.start.resolve(v1 + barrier0.getMargin());
            return;
        }
        this.start.resolve(v2 + barrier0.getMargin());
    }
}

