package androidx.constraintlayout.helper.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout.LayoutParams;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R.styleable;

public class Layer extends ConstraintHelper {
    private static final String TAG = "Layer";
    private boolean mApplyElevationOnAttach;
    private boolean mApplyVisibilityOnAttach;
    protected float mComputedCenterX;
    protected float mComputedCenterY;
    protected float mComputedMaxX;
    protected float mComputedMaxY;
    protected float mComputedMinX;
    protected float mComputedMinY;
    ConstraintLayout mContainer;
    private float mGroupRotateAngle;
    boolean mNeedBounds;
    private float mRotationCenterX;
    private float mRotationCenterY;
    private float mScaleX;
    private float mScaleY;
    private float mShiftX;
    private float mShiftY;
    View[] mViews;

    public Layer(Context context0) {
        super(context0);
        this.mRotationCenterX = NaNf;
        this.mRotationCenterY = NaNf;
        this.mGroupRotateAngle = NaNf;
        this.mScaleX = 1.0f;
        this.mScaleY = 1.0f;
        this.mComputedCenterX = NaNf;
        this.mComputedCenterY = NaNf;
        this.mComputedMaxX = NaNf;
        this.mComputedMaxY = NaNf;
        this.mComputedMinX = NaNf;
        this.mComputedMinY = NaNf;
        this.mNeedBounds = true;
        this.mViews = null;
        this.mShiftX = 0.0f;
        this.mShiftY = 0.0f;
    }

    public Layer(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mRotationCenterX = NaNf;
        this.mRotationCenterY = NaNf;
        this.mGroupRotateAngle = NaNf;
        this.mScaleX = 1.0f;
        this.mScaleY = 1.0f;
        this.mComputedCenterX = NaNf;
        this.mComputedCenterY = NaNf;
        this.mComputedMaxX = NaNf;
        this.mComputedMaxY = NaNf;
        this.mComputedMinX = NaNf;
        this.mComputedMinY = NaNf;
        this.mNeedBounds = true;
        this.mViews = null;
        this.mShiftX = 0.0f;
        this.mShiftY = 0.0f;
    }

    public Layer(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mRotationCenterX = NaNf;
        this.mRotationCenterY = NaNf;
        this.mGroupRotateAngle = NaNf;
        this.mScaleX = 1.0f;
        this.mScaleY = 1.0f;
        this.mComputedCenterX = NaNf;
        this.mComputedCenterY = NaNf;
        this.mComputedMaxX = NaNf;
        this.mComputedMaxY = NaNf;
        this.mComputedMinX = NaNf;
        this.mComputedMinY = NaNf;
        this.mNeedBounds = true;
        this.mViews = null;
        this.mShiftX = 0.0f;
        this.mShiftY = 0.0f;
    }

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    protected void applyLayoutFeaturesInConstraintSet(ConstraintLayout constraintLayout0) {
        this.applyLayoutFeatures(constraintLayout0);
    }

    protected void calcCenters() {
        if(this.mContainer == null || !this.mNeedBounds && !Float.isNaN(this.mComputedCenterX) && !Float.isNaN(this.mComputedCenterY)) {
            return;
        }
        if(!Float.isNaN(this.mRotationCenterX) && !Float.isNaN(this.mRotationCenterY)) {
            this.mComputedCenterY = this.mRotationCenterY;
            this.mComputedCenterX = this.mRotationCenterX;
            return;
        }
        View[] arr_view = this.getViews(this.mContainer);
        int v1 = arr_view[0].getLeft();
        int v2 = arr_view[0].getTop();
        int v3 = arr_view[0].getRight();
        int v4 = arr_view[0].getBottom();
        for(int v = 0; v < this.mCount; ++v) {
            View view0 = arr_view[v];
            v1 = Math.min(v1, view0.getLeft());
            v2 = Math.min(v2, view0.getTop());
            v3 = Math.max(v3, view0.getRight());
            v4 = Math.max(v4, view0.getBottom());
        }
        this.mComputedMaxX = (float)v3;
        this.mComputedMaxY = (float)v4;
        this.mComputedMinX = (float)v1;
        this.mComputedMinY = (float)v2;
        this.mComputedCenterX = Float.isNaN(this.mRotationCenterX) ? ((float)((v1 + v3) / 2)) : this.mRotationCenterX;
        if(Float.isNaN(this.mRotationCenterY)) {
            this.mComputedCenterY = (float)((v2 + v4) / 2);
            return;
        }
        this.mComputedCenterY = this.mRotationCenterY;
    }

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    protected void init(AttributeSet attributeSet0) {
        super.init(attributeSet0);
        this.mUseViewMeasure = false;
        if(attributeSet0 != null) {
            TypedArray typedArray0 = this.getContext().obtainStyledAttributes(attributeSet0, styleable.ConstraintLayout_Layout);
            int v1 = typedArray0.getIndexCount();
            for(int v = 0; v < v1; ++v) {
                int v2 = typedArray0.getIndex(v);
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
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mContainer = (ConstraintLayout)this.getParent();
        if(this.mApplyVisibilityOnAttach || this.mApplyElevationOnAttach) {
            int v = this.getVisibility();
            float f = this.getElevation();
            for(int v1 = 0; v1 < this.mCount; ++v1) {
                View view0 = this.mContainer.getViewById(this.mIds[v1]);
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

    private void reCacheViews() {
        if(this.mContainer != null && this.mCount != 0) {
            if(this.mViews == null || this.mViews.length != this.mCount) {
                this.mViews = new View[this.mCount];
            }
            for(int v = 0; v < this.mCount; ++v) {
                View[] arr_view = this.mViews;
                arr_view[v] = this.mContainer.getViewById(this.mIds[v]);
            }
        }
    }

    @Override  // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        this.applyLayoutFeatures();
    }

    @Override  // android.view.View
    public void setPivotX(float f) {
        this.mRotationCenterX = f;
        this.transform();
    }

    @Override  // android.view.View
    public void setPivotY(float f) {
        this.mRotationCenterY = f;
        this.transform();
    }

    @Override  // android.view.View
    public void setRotation(float f) {
        this.mGroupRotateAngle = f;
        this.transform();
    }

    @Override  // android.view.View
    public void setScaleX(float f) {
        this.mScaleX = f;
        this.transform();
    }

    @Override  // android.view.View
    public void setScaleY(float f) {
        this.mScaleY = f;
        this.transform();
    }

    @Override  // android.view.View
    public void setTranslationX(float f) {
        this.mShiftX = f;
        this.transform();
    }

    @Override  // android.view.View
    public void setTranslationY(float f) {
        this.mShiftY = f;
        this.transform();
    }

    @Override  // android.view.View
    public void setVisibility(int v) {
        super.setVisibility(v);
        this.applyLayoutFeatures();
    }

    private void transform() {
        if(this.mContainer != null) {
            if(this.mViews == null) {
                this.reCacheViews();
            }
            this.calcCenters();
            double f = Float.isNaN(this.mGroupRotateAngle) ? 0.0 : Math.toRadians(this.mGroupRotateAngle);
            float f1 = (float)Math.sin(f);
            float f2 = (float)Math.cos(f);
            float f3 = this.mScaleX * f2;
            float f4 = -this.mScaleY * f1;
            float f5 = this.mScaleX * f1;
            float f6 = this.mScaleY * f2;
            for(int v = 0; v < this.mCount; ++v) {
                View view0 = this.mViews[v];
                int v1 = view0.getLeft();
                int v2 = view0.getRight();
                int v3 = view0.getTop();
                int v4 = view0.getBottom();
                float f7 = ((float)((v1 + v2) / 2)) - this.mComputedCenterX;
                float f8 = ((float)((v3 + v4) / 2)) - this.mComputedCenterY;
                float f9 = f7 * f5 + f6 * f8 - f8 + this.mShiftY;
                view0.setTranslationX(f3 * f7 + f4 * f8 - f7 + this.mShiftX);
                view0.setTranslationY(f9);
                view0.setScaleY(this.mScaleY);
                view0.setScaleX(this.mScaleX);
                if(!Float.isNaN(this.mGroupRotateAngle)) {
                    view0.setRotation(this.mGroupRotateAngle);
                }
            }
        }
    }

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    public void updatePostLayout(ConstraintLayout constraintLayout0) {
        this.reCacheViews();
        this.mComputedCenterX = NaNf;
        this.mComputedCenterY = NaNf;
        ConstraintWidget constraintWidget0 = ((LayoutParams)this.getLayoutParams()).getConstraintWidget();
        constraintWidget0.setWidth(0);
        constraintWidget0.setHeight(0);
        this.calcCenters();
        this.layout(((int)this.mComputedMinX) - this.getPaddingLeft(), ((int)this.mComputedMinY) - this.getPaddingTop(), ((int)this.mComputedMaxX) + this.getPaddingRight(), ((int)this.mComputedMaxY) + this.getPaddingBottom());
        this.transform();
    }

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    public void updatePreDraw(ConstraintLayout constraintLayout0) {
        this.mContainer = constraintLayout0;
        float f = this.getRotation();
        if(f == 0.0f) {
            if(!Float.isNaN(this.mGroupRotateAngle)) {
                this.mGroupRotateAngle = 0.0f;
            }
            return;
        }
        this.mGroupRotateAngle = f;
    }
}

