package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.Cache;
import java.util.ArrayList;

public class WidgetContainer extends ConstraintWidget {
    public ArrayList mChildren;

    public WidgetContainer() {
        this.mChildren = new ArrayList();
    }

    public WidgetContainer(int v, int v1) {
        super(v, v1);
        this.mChildren = new ArrayList();
    }

    public WidgetContainer(int v, int v1, int v2, int v3) {
        super(v, v1, v2, v3);
        this.mChildren = new ArrayList();
    }

    public void add(ConstraintWidget constraintWidget0) {
        this.mChildren.add(constraintWidget0);
        if(constraintWidget0.getParent() != null) {
            ((WidgetContainer)constraintWidget0.getParent()).remove(constraintWidget0);
        }
        constraintWidget0.setParent(this);
    }

    public void add(ConstraintWidget[] arr_constraintWidget) {
        for(int v = 0; v < arr_constraintWidget.length; ++v) {
            this.add(arr_constraintWidget[v]);
        }
    }

    public ArrayList getChildren() {
        return this.mChildren;
    }

    public ConstraintWidgetContainer getRootConstraintContainer() {
        ConstraintWidget constraintWidget0 = this.getParent();
        ConstraintWidgetContainer constraintWidgetContainer0 = this instanceof ConstraintWidgetContainer ? ((ConstraintWidgetContainer)this) : null;
        while(constraintWidget0 != null) {
            ConstraintWidget constraintWidget1 = constraintWidget0.getParent();
            if(constraintWidget0 instanceof ConstraintWidgetContainer) {
                constraintWidgetContainer0 = (ConstraintWidgetContainer)constraintWidget0;
            }
            constraintWidget0 = constraintWidget1;
        }
        return constraintWidgetContainer0;
    }

    public void layout() {
        ArrayList arrayList0 = this.mChildren;
        if(arrayList0 != null) {
            int v = arrayList0.size();
            for(int v1 = 0; v1 < v; ++v1) {
                ConstraintWidget constraintWidget0 = (ConstraintWidget)this.mChildren.get(v1);
                if(constraintWidget0 instanceof WidgetContainer) {
                    ((WidgetContainer)constraintWidget0).layout();
                }
            }
        }
    }

    public void remove(ConstraintWidget constraintWidget0) {
        this.mChildren.remove(constraintWidget0);
        constraintWidget0.reset();
    }

    public void removeAllChildren() {
        this.mChildren.clear();
    }

    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void reset() {
        this.mChildren.clear();
        super.reset();
    }

    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void resetSolverVariables(Cache cache0) {
        super.resetSolverVariables(cache0);
        int v = this.mChildren.size();
        for(int v1 = 0; v1 < v; ++v1) {
            ((ConstraintWidget)this.mChildren.get(v1)).resetSolverVariables(cache0);
        }
    }

    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void setOffset(int v, int v1) {
        super.setOffset(v, v1);
        int v2 = this.mChildren.size();
        for(int v3 = 0; v3 < v2; ++v3) {
            ((ConstraintWidget)this.mChildren.get(v3)).setOffset(this.getRootX(), this.getRootY());
        }
    }
}

