package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup;

public class Constraints extends ViewGroup {
    public static class LayoutParams extends androidx.constraintlayout.widget.ConstraintLayout.LayoutParams {
        public float alpha;
        public boolean applyElevation;
        public float elevation;
        public float rotation;
        public float rotationX;
        public float rotationY;
        public float scaleX;
        public float scaleY;
        public float transformPivotX;
        public float transformPivotY;
        public float translationX;
        public float translationY;
        public float translationZ;

        public LayoutParams(int v, int v1) {
            super(v, v1);
            this.alpha = 1.0f;
            this.applyElevation = false;
            this.elevation = 0.0f;
            this.rotation = 0.0f;
            this.rotationX = 0.0f;
            this.rotationY = 0.0f;
            this.scaleX = 1.0f;
            this.scaleY = 1.0f;
            this.transformPivotX = 0.0f;
            this.transformPivotY = 0.0f;
            this.translationX = 0.0f;
            this.translationY = 0.0f;
            this.translationZ = 0.0f;
        }

        public LayoutParams(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
            this.alpha = 1.0f;
            this.applyElevation = false;
            this.elevation = 0.0f;
            this.rotation = 0.0f;
            this.rotationX = 0.0f;
            this.rotationY = 0.0f;
            this.scaleX = 1.0f;
            this.scaleY = 1.0f;
            this.transformPivotX = 0.0f;
            this.transformPivotY = 0.0f;
            this.translationX = 0.0f;
            this.translationY = 0.0f;
            this.translationZ = 0.0f;
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.ConstraintSet);
            int v1 = typedArray0.getIndexCount();
            for(int v = 0; v < v1; ++v) {
                int v2 = typedArray0.getIndex(v);
                if(v2 == styleable.ConstraintSet_android_alpha) {
                    this.alpha = typedArray0.getFloat(v2, this.alpha);
                }
                else if(v2 == styleable.ConstraintSet_android_elevation) {
                    this.elevation = typedArray0.getFloat(v2, this.elevation);
                    this.applyElevation = true;
                }
                else if(v2 == styleable.ConstraintSet_android_rotationX) {
                    this.rotationX = typedArray0.getFloat(v2, this.rotationX);
                }
                else if(v2 == styleable.ConstraintSet_android_rotationY) {
                    this.rotationY = typedArray0.getFloat(v2, this.rotationY);
                }
                else if(v2 == styleable.ConstraintSet_android_rotation) {
                    this.rotation = typedArray0.getFloat(v2, this.rotation);
                }
                else if(v2 == styleable.ConstraintSet_android_scaleX) {
                    this.scaleX = typedArray0.getFloat(v2, this.scaleX);
                }
                else if(v2 == styleable.ConstraintSet_android_scaleY) {
                    this.scaleY = typedArray0.getFloat(v2, this.scaleY);
                }
                else if(v2 == styleable.ConstraintSet_android_transformPivotX) {
                    this.transformPivotX = typedArray0.getFloat(v2, this.transformPivotX);
                }
                else if(v2 == styleable.ConstraintSet_android_transformPivotY) {
                    this.transformPivotY = typedArray0.getFloat(v2, this.transformPivotY);
                }
                else if(v2 == styleable.ConstraintSet_android_translationX) {
                    this.translationX = typedArray0.getFloat(v2, this.translationX);
                }
                else if(v2 == styleable.ConstraintSet_android_translationY) {
                    this.translationY = typedArray0.getFloat(v2, this.translationY);
                }
                else if(v2 == styleable.ConstraintSet_android_translationZ) {
                    this.translationZ = typedArray0.getFloat(v2, this.translationZ);
                }
            }
            typedArray0.recycle();
        }

        public LayoutParams(LayoutParams constraints$LayoutParams0) {
            super(constraints$LayoutParams0);
            this.alpha = 1.0f;
            this.applyElevation = false;
            this.elevation = 0.0f;
            this.rotation = 0.0f;
            this.rotationX = 0.0f;
            this.rotationY = 0.0f;
            this.scaleX = 1.0f;
            this.scaleY = 1.0f;
            this.transformPivotX = 0.0f;
            this.transformPivotY = 0.0f;
            this.translationX = 0.0f;
            this.translationY = 0.0f;
            this.translationZ = 0.0f;
        }
    }

    public static final String TAG = "Constraints";
    ConstraintSet mConstraintSet;

    public Constraints(Context context0) {
        super(context0);
        super.setVisibility(8);
    }

    public Constraints(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.init();
        super.setVisibility(8);
    }

    public Constraints(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.init();
        super.setVisibility(8);
    }

    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return this.generateDefaultLayoutParams();
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override  // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return this.generateLayoutParams(attributeSet0);
    }

    @Override  // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return new androidx.constraintlayout.widget.ConstraintLayout.LayoutParams(viewGroup$LayoutParams0);
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return new LayoutParams(this.getContext(), attributeSet0);
    }

    public ConstraintSet getConstraintSet() {
        if(this.mConstraintSet == null) {
            this.mConstraintSet = new ConstraintSet();
        }
        this.mConstraintSet.clone(this);
        return this.mConstraintSet;
    }

    private void init() {
        Log.v("Constraints", " ################# init");
    }

    @Override  // android.view.ViewGroup
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
    }
}

