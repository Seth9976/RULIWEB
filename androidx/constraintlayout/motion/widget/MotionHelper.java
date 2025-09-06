package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintHelper;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.R.styleable;
import java.util.HashMap;

public class MotionHelper extends ConstraintHelper implements MotionHelperInterface {
    private float mProgress;
    private boolean mUseOnHide;
    private boolean mUseOnShow;
    protected View[] views;

    public MotionHelper(Context context0) {
        super(context0);
        this.mUseOnShow = false;
        this.mUseOnHide = false;
    }

    public MotionHelper(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
        this.mUseOnShow = false;
        this.mUseOnHide = false;
        this.init(attributeSet0);
    }

    public MotionHelper(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.mUseOnShow = false;
        this.mUseOnHide = false;
        this.init(attributeSet0);
    }

    @Override  // androidx.constraintlayout.motion.widget.Animatable
    public float getProgress() {
        return this.mProgress;
    }

    @Override  // androidx.constraintlayout.widget.ConstraintHelper
    protected void init(AttributeSet attributeSet0) {
        super.init(attributeSet0);
        if(attributeSet0 != null) {
            TypedArray typedArray0 = this.getContext().obtainStyledAttributes(attributeSet0, styleable.MotionHelper);
            int v = typedArray0.getIndexCount();
            for(int v1 = 0; v1 < v; ++v1) {
                int v2 = typedArray0.getIndex(v1);
                if(v2 == styleable.MotionHelper_onShow) {
                    this.mUseOnShow = typedArray0.getBoolean(v2, this.mUseOnShow);
                }
                else if(v2 == styleable.MotionHelper_onHide) {
                    this.mUseOnHide = typedArray0.getBoolean(v2, this.mUseOnHide);
                }
            }
            typedArray0.recycle();
        }
    }

    @Override  // androidx.constraintlayout.motion.widget.MotionHelperInterface
    public boolean isDecorator() {
        return false;
    }

    @Override  // androidx.constraintlayout.motion.widget.MotionHelperInterface
    public boolean isUseOnHide() {
        return this.mUseOnHide;
    }

    @Override  // androidx.constraintlayout.motion.widget.MotionHelperInterface
    public boolean isUsedOnShow() {
        return this.mUseOnShow;
    }

    @Override  // androidx.constraintlayout.motion.widget.MotionHelperInterface
    public void onFinishedMotionScene(MotionLayout motionLayout0) {
    }

    @Override  // androidx.constraintlayout.motion.widget.MotionHelperInterface
    public void onPostDraw(Canvas canvas0) {
    }

    @Override  // androidx.constraintlayout.motion.widget.MotionHelperInterface
    public void onPreDraw(Canvas canvas0) {
    }

    @Override  // androidx.constraintlayout.motion.widget.MotionHelperInterface
    public void onPreSetup(MotionLayout motionLayout0, HashMap hashMap0) {
    }

    @Override  // androidx.constraintlayout.motion.widget.MotionLayout$TransitionListener
    public void onTransitionChange(MotionLayout motionLayout0, int v, int v1, float f) {
    }

    @Override  // androidx.constraintlayout.motion.widget.MotionLayout$TransitionListener
    public void onTransitionCompleted(MotionLayout motionLayout0, int v) {
    }

    @Override  // androidx.constraintlayout.motion.widget.MotionLayout$TransitionListener
    public void onTransitionStarted(MotionLayout motionLayout0, int v, int v1) {
    }

    @Override  // androidx.constraintlayout.motion.widget.MotionLayout$TransitionListener
    public void onTransitionTrigger(MotionLayout motionLayout0, int v, boolean z, float f) {
    }

    @Override  // androidx.constraintlayout.motion.widget.Animatable
    public void setProgress(float f) {
        this.mProgress = f;
        int v = 0;
        if(this.mCount > 0) {
            this.views = this.getViews(((ConstraintLayout)this.getParent()));
            while(v < this.mCount) {
                ++v;
            }
            return;
        }
        ViewGroup viewGroup0 = (ViewGroup)this.getParent();
        int v1 = viewGroup0.getChildCount();
        while(v < v1) {
            boolean z = viewGroup0.getChildAt(v) instanceof MotionHelper;
            ++v;
        }
    }

    public void setProgress(View view0, float f) {
    }
}

