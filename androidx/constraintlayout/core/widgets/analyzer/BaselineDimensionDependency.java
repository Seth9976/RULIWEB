package androidx.constraintlayout.core.widgets.analyzer;

class BaselineDimensionDependency extends DimensionDependency {
    BaselineDimensionDependency(WidgetRun widgetRun0) {
        super(widgetRun0);
    }

    public void update(DependencyNode dependencyNode0) {
        ((VerticalWidgetRun)this.mRun).baseline.mMargin = this.mRun.mWidget.getBaselineDistance();
        this.resolved = true;
    }
}

