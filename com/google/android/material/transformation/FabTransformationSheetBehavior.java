package com.google.android.material.transformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import androidx.coordinatorlayout.widget.CoordinatorLayout.LayoutParams;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.R.animator;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.animation.Positioning;
import java.util.HashMap;
import java.util.Map;

@Deprecated
public class FabTransformationSheetBehavior extends FabTransformationBehavior {
    private Map importantForAccessibilityMap;

    public FabTransformationSheetBehavior() {
    }

    public FabTransformationSheetBehavior(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
    }

    @Override  // com.google.android.material.transformation.FabTransformationBehavior
    protected FabTransformationSpec onCreateMotionSpec(Context context0, boolean z) {
        FabTransformationSpec fabTransformationBehavior$FabTransformationSpec0 = new FabTransformationSpec();
        fabTransformationBehavior$FabTransformationSpec0.timings = MotionSpec.createFromResource(context0, (z ? animator.mtrl_fab_transformation_sheet_expand_spec : animator.mtrl_fab_transformation_sheet_collapse_spec));
        fabTransformationBehavior$FabTransformationSpec0.positioning = new Positioning(17, 0.0f, 0.0f);
        return fabTransformationBehavior$FabTransformationSpec0;
    }

    @Override  // com.google.android.material.transformation.ExpandableTransformationBehavior
    protected boolean onExpandedStateChange(View view0, View view1, boolean z, boolean z1) {
        this.updateImportantForAccessibility(view1, z);
        return super.onExpandedStateChange(view0, view1, z, z1);
    }

    private void updateImportantForAccessibility(View view0, boolean z) {
        ViewParent viewParent0 = view0.getParent();
        if(viewParent0 instanceof CoordinatorLayout) {
            int v = ((CoordinatorLayout)viewParent0).getChildCount();
            if(z) {
                this.importantForAccessibilityMap = new HashMap(v);
            }
            for(int v1 = 0; v1 < v; ++v1) {
                View view1 = ((CoordinatorLayout)viewParent0).getChildAt(v1);
                if(view1 != view0 && (!(view1.getLayoutParams() instanceof LayoutParams) || !(((LayoutParams)view1.getLayoutParams()).getBehavior() instanceof FabTransformationScrimBehavior))) {
                    if(z) {
                        this.importantForAccessibilityMap.put(view1, view1.getImportantForAccessibility());
                        ViewCompat.setImportantForAccessibility(view1, 4);
                    }
                    else if(this.importantForAccessibilityMap != null && this.importantForAccessibilityMap.containsKey(view1)) {
                        ViewCompat.setImportantForAccessibility(view1, ((int)(((Integer)this.importantForAccessibilityMap.get(view1)))));
                    }
                }
            }
            if(!z) {
                this.importantForAccessibilityMap = null;
            }
        }
    }
}

