package androidx.constraintlayout.core.widgets.analyzer;

class DimensionDependency extends DependencyNode {
    public int wrapValue;

    DimensionDependency(WidgetRun widgetRun0) {
        super(widgetRun0);
        if(widgetRun0 instanceof HorizontalWidgetRun) {
            this.mType = Type.HORIZONTAL_DIMENSION;
            return;
        }
        this.mType = Type.VERTICAL_DIMENSION;
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.DependencyNode
    public void resolve(int v) {
        if(!this.resolved) {
            this.resolved = true;
            this.value = v;
            for(Object object0: this.mDependencies) {
                ((Dependency)object0).update(((Dependency)object0));
            }
        }
    }
}

