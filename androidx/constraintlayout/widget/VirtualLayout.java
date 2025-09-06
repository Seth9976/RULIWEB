package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;

public abstract class VirtualLayout extends ConstraintHelper {
    private boolean mApplyElevationOnAttach;
    private boolean mApplyVisibilityOnAttach;

    public VirtualLayout(Context context0) {
        super(context0);
    }

    public VirtualLayout(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
    }

    public VirtualLayout(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
    }

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    protected void applyLayoutFeaturesInConstraintSet(ConstraintLayout constraintLayout0) {
        this.applyLayoutFeatures(constraintLayout0);
    }

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    protected void init(AttributeSet attributeSet0) {
        super.init(attributeSet0);
        if(attributeSet0 != null) {
            TypedArray typedArray0 = this.getContext().obtainStyledAttributes(attributeSet0, styleable.ConstraintLayout_Layout);
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                if(v2 == styleable.ConstraintLayout_Layout_android_visibility) {
                    this.mApplyVisibilityOnAttach = true;
                }
                else if(v2 == styleable.ConstraintLayout_Layout_android_elevation) {
                    this.mApplyElevationOnAttach = true;
                }
            }
            typedArray0.recycle();
        }
    }

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if(this.mApplyVisibilityOnAttach || this.mApplyElevationOnAttach) {
            ViewParent viewParent0 = this.getParent();
            if(viewParent0 instanceof ConstraintLayout) {
                int v = this.getVisibility();
                float f = this.getElevation();
                for(int v1 = 0; v1 < this.mCount; ++v1) {
                    View view0 = ((ConstraintLayout)viewParent0).getViewById(this.mIds[v1]);
                    if(view0 != null) {
                        if(this.mApplyVisibilityOnAttach) {
                            view0.setVisibility(v);
                        }
                        if(this.mApplyElevationOnAttach && f > 0.0f) {
                            view0.setTranslationZ(view0.getTranslationZ() + f);
                        }
                    }
                }
            }
        }
    }

    public void onMeasure(androidx.constraintlayout.core.widgets.VirtualLayout virtualLayout0, int v, int v1) {
    }

    @Override  // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        this.applyLayoutFeatures();
    }

    @Override  // android.view.View
    public void setVisibility(int v) {
        super.setVisibility(v);
        this.applyLayoutFeatures();
    }
}

