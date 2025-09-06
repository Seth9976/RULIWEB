package androidx.constraintlayout.core.widgets.analyzer;

import java.util.ArrayList;
import java.util.List;

public class DependencyNode implements Dependency {
    static enum Type {
        UNKNOWN,
        HORIZONTAL_DIMENSION,
        VERTICAL_DIMENSION,
        LEFT,
        RIGHT,
        TOP,
        BOTTOM,
        BASELINE;

        private static Type[] $values() [...] // Inlined contents
    }

    public boolean delegateToWidgetRun;
    List mDependencies;
    int mMargin;
    DimensionDependency mMarginDependency;
    int mMarginFactor;
    WidgetRun mRun;
    List mTargets;
    Type mType;
    public boolean readyToSolve;
    public boolean resolved;
    public Dependency updateDelegate;
    public int value;

    public DependencyNode(WidgetRun widgetRun0) {
        this.updateDelegate = null;
        this.delegateToWidgetRun = false;
        this.readyToSolve = false;
        this.mType = Type.UNKNOWN;
        this.mMarginFactor = 1;
        this.mMarginDependency = null;
        this.resolved = false;
        this.mDependencies = new ArrayList();
        this.mTargets = new ArrayList();
        this.mRun = widgetRun0;
    }

    public void addDependency(Dependency dependency0) {
        this.mDependencies.add(dependency0);
        if(this.resolved) {
            dependency0.update(dependency0);
        }
    }

    public void clear() {
        this.mTargets.clear();
        this.mDependencies.clear();
        this.resolved = false;
        this.value = 0;
        this.readyToSolve = false;
        this.delegateToWidgetRun = false;
    }

    public String name() {
        String s = this.mRun.mWidget.getDebugName();
        return this.mType == Type.LEFT || this.mType == Type.RIGHT ? s + "_HORIZONTAL" + ":" + this.mType.name() : s + "_VERTICAL" + ":" + this.mType.name();
    }

    public void resolve(int v) {
        if(!this.resolved) {
            this.resolved = true;
            this.value = v;
            for(Object object0: this.mDependencies) {
                ((Dependency)object0).update(((Dependency)object0));
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(this.mRun.mWidget.getDebugName());
        stringBuilder0.append(":");
        stringBuilder0.append(this.mType);
        stringBuilder0.append("(");
        Integer integer0 = this.resolved ? this.value : "unresolved";
        stringBuilder0.append(integer0);
        stringBuilder0.append(") <t=");
        stringBuilder0.append(this.mTargets.size());
        stringBuilder0.append(":d=");
        stringBuilder0.append(this.mDependencies.size());
        stringBuilder0.append(">");
        return stringBuilder0.toString();
    }

    @Override  // androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency dependency0) {
        for(Object object0: this.mTargets) {
            if(!((DependencyNode)object0).resolved) {
                return;
            }
            if(false) {
                break;
            }
        }
        this.readyToSolve = true;
        Dependency dependency1 = this.updateDelegate;
        if(dependency1 != null) {
            dependency1.update(this);
        }
        if(this.delegateToWidgetRun) {
            this.mRun.update(this);
            return;
        }
        DependencyNode dependencyNode0 = null;
        int v = 0;
        for(Object object1: this.mTargets) {
            DependencyNode dependencyNode1 = (DependencyNode)object1;
            if(!(dependencyNode1 instanceof DimensionDependency)) {
                ++v;
                dependencyNode0 = dependencyNode1;
            }
        }
        if(dependencyNode0 == null || v != 1 || !dependencyNode0.resolved) {
        label_31:
            Dependency dependency2 = this.updateDelegate;
            if(dependency2 != null) {
                dependency2.update(this);
            }
        }
        else {
            DimensionDependency dimensionDependency0 = this.mMarginDependency;
            if(dimensionDependency0 == null) {
                this.resolve(dependencyNode0.value + this.mMargin);
                goto label_31;
            }
            else if(dimensionDependency0.resolved) {
                this.mMargin = this.mMarginFactor * this.mMarginDependency.value;
                this.resolve(dependencyNode0.value + this.mMargin);
                goto label_31;
            }
        }
    }
}

