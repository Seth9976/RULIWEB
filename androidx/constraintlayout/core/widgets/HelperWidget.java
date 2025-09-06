package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.widgets.analyzer.Grouping;
import androidx.constraintlayout.core.widgets.analyzer.WidgetGroup;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class HelperWidget extends ConstraintWidget implements Helper {
    public ConstraintWidget[] mWidgets;
    public int mWidgetsCount;

    public HelperWidget() {
        this.mWidgets = new ConstraintWidget[4];
        this.mWidgetsCount = 0;
    }

    @Override  // androidx.constraintlayout.core.widgets.Helper
    public void add(ConstraintWidget constraintWidget0) {
        if(constraintWidget0 != this && constraintWidget0 != null) {
            ConstraintWidget[] arr_constraintWidget = this.mWidgets;
            if(this.mWidgetsCount + 1 > arr_constraintWidget.length) {
                this.mWidgets = (ConstraintWidget[])Arrays.copyOf(arr_constraintWidget, arr_constraintWidget.length * 2);
            }
            int v = this.mWidgetsCount;
            this.mWidgets[v] = constraintWidget0;
            this.mWidgetsCount = v + 1;
        }
    }

    public void addDependents(ArrayList arrayList0, int v, WidgetGroup widgetGroup0) {
        for(int v2 = 0; v2 < this.mWidgetsCount; ++v2) {
            widgetGroup0.add(this.mWidgets[v2]);
        }
        for(int v1 = 0; v1 < this.mWidgetsCount; ++v1) {
            Grouping.findDependents(this.mWidgets[v1], v, arrayList0, widgetGroup0);
        }
    }

    @Override  // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void copy(ConstraintWidget constraintWidget0, HashMap hashMap0) {
        super.copy(constraintWidget0, hashMap0);
        this.mWidgetsCount = 0;
        int v1 = ((HelperWidget)constraintWidget0).mWidgetsCount;
        for(int v = 0; v < v1; ++v) {
            this.add(((ConstraintWidget)hashMap0.get(((HelperWidget)constraintWidget0).mWidgets[v])));
        }
    }

    public int findGroupInDependents(int v) {
        for(int v1 = 0; v1 < this.mWidgetsCount; ++v1) {
            ConstraintWidget constraintWidget0 = this.mWidgets[v1];
            if(v == 0 && constraintWidget0.horizontalGroup != -1) {
                return constraintWidget0.horizontalGroup;
            }
            if(v == 1 && constraintWidget0.verticalGroup != -1) {
                return constraintWidget0.verticalGroup;
            }
        }
        return -1;
    }

    @Override  // androidx.constraintlayout.core.widgets.Helper
    public void removeAllIds() {
        this.mWidgetsCount = 0;
        Arrays.fill(this.mWidgets, null);
    }

    @Override  // androidx.constraintlayout.core.widgets.Helper
    public void updateConstraints(ConstraintWidgetContainer constraintWidgetContainer0) {
    }
}

