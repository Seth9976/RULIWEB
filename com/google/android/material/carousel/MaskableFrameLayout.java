package com.google.android.material.carousel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import androidx.core.math.MathUtils;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.shape.AbsoluteCornerSize;
import com.google.android.material.shape.ClampedCornerSize;
import com.google.android.material.shape.CornerSize;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.shape.ShapeableDelegate;

public class MaskableFrameLayout extends FrameLayout implements Maskable, Shapeable {
    private static final int NOT_SET = -1;
    private final RectF maskRect;
    private float maskXPercentage;
    private OnMaskChangedListener onMaskChangedListener;
    private Boolean savedForceCompatClippingEnabled;
    private ShapeAppearanceModel shapeAppearanceModel;
    private final ShapeableDelegate shapeableDelegate;

    public MaskableFrameLayout(Context context0) {
        this(context0, null);
    }

    public MaskableFrameLayout(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public MaskableFrameLayout(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.maskXPercentage = -1.0f;
        this.maskRect = new RectF();
        this.shapeableDelegate = ShapeableDelegate.create(this);
        this.savedForceCompatClippingEnabled = null;
        this.setShapeAppearanceModel(ShapeAppearanceModel.builder(context0, attributeSet0, v, 0, 0).build());
    }

    @Override  // android.view.ViewGroup
    protected void dispatchDraw(Canvas canvas0) {
        MaskableFrameLayout..ExternalSyntheticLambda1 maskableFrameLayout$$ExternalSyntheticLambda10 = (Canvas canvas0) -> super.dispatchDraw(canvas0);
        this.shapeableDelegate.maybeClip(canvas0, maskableFrameLayout$$ExternalSyntheticLambda10);
    }

    @Override  // android.view.View
    public void getFocusedRect(Rect rect0) {
        rect0.set(((int)this.maskRect.left), ((int)this.maskRect.top), ((int)this.maskRect.right), ((int)this.maskRect.bottom));
    }

    @Override  // com.google.android.material.carousel.Maskable
    public RectF getMaskRectF() {
        return this.maskRect;
    }

    @Override  // com.google.android.material.carousel.Maskable
    @Deprecated
    public float getMaskXPercentage() {
        return this.maskXPercentage;
    }

    @Override  // com.google.android.material.shape.Shapeable
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.shapeAppearanceModel;
    }

    // 检测为 Lambda 实现
    void lambda$dispatchDraw$1$com-google-android-material-carousel-MaskableFrameLayout(Canvas canvas0) [...]

    // 检测为 Lambda 实现
    static CornerSize lambda$setShapeAppearanceModel$0(CornerSize cornerSize0) [...]

    @Override  // android.view.ViewGroup
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Boolean boolean0 = this.savedForceCompatClippingEnabled;
        if(boolean0 != null) {
            this.shapeableDelegate.setForceCompatClippingEnabled(this, boolean0.booleanValue());
        }
    }

    @Override  // android.view.ViewGroup
    protected void onDetachedFromWindow() {
        this.savedForceCompatClippingEnabled = Boolean.valueOf(this.shapeableDelegate.isForceCompatClippingEnabled());
        this.shapeableDelegate.setForceCompatClippingEnabled(this, true);
        super.onDetachedFromWindow();
    }

    private void onMaskChanged() {
        this.shapeableDelegate.onMaskChanged(this, this.maskRect);
        OnMaskChangedListener onMaskChangedListener0 = this.onMaskChangedListener;
        if(onMaskChangedListener0 != null) {
            onMaskChangedListener0.onMaskChanged(this.maskRect);
        }
    }

    @Override  // android.view.View
    protected void onSizeChanged(int v, int v1, int v2, int v3) {
        super.onSizeChanged(v, v1, v2, v3);
        if(this.maskXPercentage != -1.0f) {
            this.updateMaskRectForMaskXPercentage();
        }
    }

    @Override  // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        if(!this.maskRect.isEmpty() && motionEvent0.getAction() == 0) {
            float f = motionEvent0.getX();
            float f1 = motionEvent0.getY();
            return this.maskRect.contains(f, f1) ? super.onTouchEvent(motionEvent0) : false;
        }
        return super.onTouchEvent(motionEvent0);
    }

    public void setForceCompatClipping(boolean z) {
        this.shapeableDelegate.setForceCompatClippingEnabled(this, z);
    }

    @Override  // com.google.android.material.carousel.Maskable
    public void setMaskRectF(RectF rectF0) {
        this.maskRect.set(rectF0);
        this.onMaskChanged();
    }

    @Override  // com.google.android.material.carousel.Maskable
    @Deprecated
    public void setMaskXPercentage(float f) {
        float f1 = MathUtils.clamp(f, 0.0f, 1.0f);
        if(this.maskXPercentage != f1) {
            this.maskXPercentage = f1;
            this.updateMaskRectForMaskXPercentage();
        }
    }

    @Override  // com.google.android.material.carousel.Maskable
    public void setOnMaskChangedListener(OnMaskChangedListener onMaskChangedListener0) {
        this.onMaskChangedListener = onMaskChangedListener0;
    }

    @Override  // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel0) {
        ShapeAppearanceModel shapeAppearanceModel1 = shapeAppearanceModel0.withTransformedCornerSizes((CornerSize cornerSize0) -> (cornerSize0 instanceof AbsoluteCornerSize ? ClampedCornerSize.createFromCornerSize(((AbsoluteCornerSize)cornerSize0)) : cornerSize0));
        this.shapeAppearanceModel = shapeAppearanceModel1;
        this.shapeableDelegate.onShapeAppearanceChanged(this, shapeAppearanceModel1);
    }

    private void updateMaskRectForMaskXPercentage() {
        if(this.maskXPercentage != -1.0f) {
            float f = AnimationUtils.lerp(0.0f, ((float)this.getWidth()) / 2.0f, 0.0f, 1.0f, this.maskXPercentage);
            this.setMaskRectF(new RectF(f, 0.0f, ((float)this.getWidth()) - f, ((float)this.getHeight())));
        }
    }
}

