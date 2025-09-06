package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnPreDrawListener;
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior;
import androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.expandable.ExpandableWidget;
import java.util.List;

@Deprecated
public abstract class ExpandableBehavior extends Behavior {
    private static final int STATE_COLLAPSED = 2;
    private static final int STATE_EXPANDED = 1;
    private static final int STATE_UNINITIALIZED;
    private int currentState;

    public ExpandableBehavior() {
        this.currentState = 0;
    }

    public ExpandableBehavior(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.currentState = 0;
    }

    // 去混淆评级： 低(20)
    private boolean didStateChange(boolean z) {
        return z ? this.currentState == 0 || this.currentState == 2 : this.currentState == 1;
    }

    protected ExpandableWidget findExpandableWidget(CoordinatorLayout coordinatorLayout0, View view0) {
        List list0 = coordinatorLayout0.getDependencies(view0);
        int v = list0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            View view1 = (View)list0.get(v1);
            if(this.layoutDependsOn(coordinatorLayout0, view0, view1)) {
                return (ExpandableWidget)view1;
            }
        }
        return null;
    }

    public static ExpandableBehavior from(View view0, Class class0) {
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
        if(!(viewGroup$LayoutParams0 instanceof LayoutParams)) {
            throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
        }
        Behavior coordinatorLayout$Behavior0 = ((LayoutParams)viewGroup$LayoutParams0).getBehavior();
        if(!(coordinatorLayout$Behavior0 instanceof ExpandableBehavior)) {
            throw new IllegalArgumentException("The view is not associated with ExpandableBehavior");
        }
        return (ExpandableBehavior)class0.cast(coordinatorLayout$Behavior0);
    }

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public abstract boolean layoutDependsOn(CoordinatorLayout arg1, View arg2, View arg3);

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout0, View view0, View view1) {
        if(this.didStateChange(((ExpandableWidget)view1).isExpanded())) {
            this.currentState = ((ExpandableWidget)view1).isExpanded() ? 1 : 2;
            return this.onExpandedStateChange(((View)(((ExpandableWidget)view1))), view0, ((ExpandableWidget)view1).isExpanded(), true);
        }
        return false;
    }

    protected abstract boolean onExpandedStateChange(View arg1, View arg2, boolean arg3, boolean arg4);

    @Override  // androidx.coordinatorlayout.widget.CoordinatorLayout$Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout0, View view0, int v) {
        if(!ViewCompat.isLaidOut(view0)) {
            ExpandableWidget expandableWidget0 = this.findExpandableWidget(coordinatorLayout0, view0);
            if(expandableWidget0 != null && this.didStateChange(expandableWidget0.isExpanded())) {
                int v1 = expandableWidget0.isExpanded() ? 1 : 2;
                this.currentState = v1;
                view0.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override  // android.view.ViewTreeObserver$OnPreDrawListener
                    public boolean onPreDraw() {
                        view0.getViewTreeObserver().removeOnPreDrawListener(this);
                        if(ExpandableBehavior.this.currentState == v1) {
                            boolean z = expandableWidget0.isExpanded();
                            ExpandableBehavior.this.onExpandedStateChange(((View)expandableWidget0), view0, z, false);
                        }
                        return false;
                    }
                });
            }
        }
        return false;
    }
}

