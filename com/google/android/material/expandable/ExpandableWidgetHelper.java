package com.google.android.material.expandable;

import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

public final class ExpandableWidgetHelper {
    private boolean expanded;
    private int expandedComponentIdHint;
    private final View widget;

    public ExpandableWidgetHelper(ExpandableWidget expandableWidget0) {
        this.expanded = false;
        this.expandedComponentIdHint = 0;
        this.widget = (View)expandableWidget0;
    }

    private void dispatchExpandedStateChanged() {
        ViewParent viewParent0 = this.widget.getParent();
        if(viewParent0 instanceof CoordinatorLayout) {
            ((CoordinatorLayout)viewParent0).dispatchDependentViewsChanged(this.widget);
        }
    }

    public int getExpandedComponentIdHint() {
        return this.expandedComponentIdHint;
    }

    public boolean isExpanded() {
        return this.expanded;
    }

    public void onRestoreInstanceState(Bundle bundle0) {
        this.expanded = bundle0.getBoolean("expanded", false);
        this.expandedComponentIdHint = bundle0.getInt("expandedComponentIdHint", 0);
        if(this.expanded) {
            this.dispatchExpandedStateChanged();
        }
    }

    public Bundle onSaveInstanceState() {
        Bundle bundle0 = new Bundle();
        bundle0.putBoolean("expanded", this.expanded);
        bundle0.putInt("expandedComponentIdHint", this.expandedComponentIdHint);
        return bundle0;
    }

    public boolean setExpanded(boolean z) {
        if(this.expanded != z) {
            this.expanded = z;
            this.dispatchExpandedStateChanged();
            return true;
        }
        return false;
    }

    public void setExpandedComponentIdHint(int v) {
        this.expandedComponentIdHint = v;
    }
}

