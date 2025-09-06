package com.google.android.material.floatingactionbutton;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Outline;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.Shader;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;

class BorderDrawable extends Drawable {
    class BorderState extends Drawable.ConstantState {
        private BorderState() {
        }

        BorderState(com.google.android.material.floatingactionbutton.BorderDrawable.1 borderDrawable$10) {
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public Drawable newDrawable() {
            return BorderDrawable.this;
        }
    }

    private static final float DRAW_STROKE_WIDTH_MULTIPLE = 1.3333f;
    private ColorStateList borderTint;
    float borderWidth;
    private int bottomInnerStrokeColor;
    private int bottomOuterStrokeColor;
    private final RectF boundsRectF;
    private int currentBorderTintColor;
    private boolean invalidateShader;
    private final Paint paint;
    private final ShapeAppearancePathProvider pathProvider;
    private final Rect rect;
    private final RectF rectF;
    private ShapeAppearanceModel shapeAppearanceModel;
    private final Path shapePath;
    private final BorderState state;
    private int topInnerStrokeColor;
    private int topOuterStrokeColor;

    BorderDrawable(ShapeAppearanceModel shapeAppearanceModel0) {
        this.pathProvider = ShapeAppearancePathProvider.getInstance();
        this.shapePath = new Path();
        this.rect = new Rect();
        this.rectF = new RectF();
        this.boundsRectF = new RectF();
        this.state = new BorderState(this, null);
        this.invalidateShader = true;
        this.shapeAppearanceModel = shapeAppearanceModel0;
        Paint paint0 = new Paint(1);
        this.paint = paint0;
        paint0.setStyle(Paint.Style.STROKE);
    }

    private Shader createGradientShader() {
        this.copyBounds(this.rect);
        float f = this.borderWidth / ((float)this.rect.height());
        int[] arr_v = {ColorUtils.compositeColors(this.topOuterStrokeColor, this.currentBorderTintColor), ColorUtils.compositeColors(this.topInnerStrokeColor, this.currentBorderTintColor), ColorUtils.compositeColors(ColorUtils.setAlphaComponent(this.topInnerStrokeColor, 0), this.currentBorderTintColor), ColorUtils.compositeColors(ColorUtils.setAlphaComponent(this.bottomInnerStrokeColor, 0), this.currentBorderTintColor), ColorUtils.compositeColors(this.bottomInnerStrokeColor, this.currentBorderTintColor), ColorUtils.compositeColors(this.bottomOuterStrokeColor, this.currentBorderTintColor)};
        return new LinearGradient(0.0f, ((float)this.rect.top), 0.0f, ((float)this.rect.bottom), arr_v, new float[]{0.0f, f, 0.5f, 0.5f, 1.0f - f, 1.0f}, Shader.TileMode.CLAMP);
    }

    @Override  // android.graphics.drawable.Drawable
    public void draw(Canvas canvas0) {
        if(this.invalidateShader) {
            Shader shader0 = this.createGradientShader();
            this.paint.setShader(shader0);
            this.invalidateShader = false;
        }
        float f = this.paint.getStrokeWidth();
        this.copyBounds(this.rect);
        this.rectF.set(this.rect);
        float f1 = Math.min(this.shapeAppearanceModel.getTopLeftCornerSize().getCornerSize(this.getBoundsAsRectF()), this.rectF.width() / 2.0f);
        if(this.shapeAppearanceModel.isRoundRect(this.getBoundsAsRectF())) {
            this.rectF.inset(f / 2.0f, f / 2.0f);
            canvas0.drawRoundRect(this.rectF, f1, f1, this.paint);
        }
    }

    protected RectF getBoundsAsRectF() {
        Rect rect0 = this.getBounds();
        this.boundsRectF.set(rect0);
        return this.boundsRectF;
    }

    @Override  // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.state;
    }

    @Override  // android.graphics.drawable.Drawable
    public int getOpacity() {
        return this.borderWidth > 0.0f ? -3 : -2;
    }

    @Override  // android.graphics.drawable.Drawable
    public void getOutline(Outline outline0) {
        if(this.shapeAppearanceModel.isRoundRect(this.getBoundsAsRectF())) {
            float f = this.shapeAppearanceModel.getTopLeftCornerSize().getCornerSize(this.getBoundsAsRectF());
            outline0.setRoundRect(this.getBounds(), f);
            return;
        }
        this.copyBounds(this.rect);
        this.rectF.set(this.rect);
        this.pathProvider.calculatePath(this.shapeAppearanceModel, 1.0f, this.rectF, this.shapePath);
        DrawableUtils.setOutlineToPath(outline0, this.shapePath);
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect0) {
        if(this.shapeAppearanceModel.isRoundRect(this.getBoundsAsRectF())) {
            int v = Math.round(this.borderWidth);
            rect0.set(v, v, v, v);
        }
        return true;
    }

    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.shapeAppearanceModel;
    }

    // 去混淆评级： 低(30)
    @Override  // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return this.borderTint != null && this.borderTint.isStateful() || super.isStateful();
    }

    @Override  // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect0) {
        this.invalidateShader = true;
    }

    @Override  // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] arr_v) {
        ColorStateList colorStateList0 = this.borderTint;
        if(colorStateList0 != null) {
            int v = colorStateList0.getColorForState(arr_v, this.currentBorderTintColor);
            if(v != this.currentBorderTintColor) {
                this.invalidateShader = true;
                this.currentBorderTintColor = v;
            }
        }
        if(this.invalidateShader) {
            this.invalidateSelf();
        }
        return this.invalidateShader;
    }

    @Override  // android.graphics.drawable.Drawable
    public void setAlpha(int v) {
        this.paint.setAlpha(v);
        this.invalidateSelf();
    }

    void setBorderTint(ColorStateList colorStateList0) {
        if(colorStateList0 != null) {
            this.currentBorderTintColor = colorStateList0.getColorForState(this.getState(), this.currentBorderTintColor);
        }
        this.borderTint = colorStateList0;
        this.invalidateShader = true;
        this.invalidateSelf();
    }

    public void setBorderWidth(float f) {
        if(this.borderWidth != f) {
            this.borderWidth = f;
            this.paint.setStrokeWidth(f * 1.3333f);
            this.invalidateShader = true;
            this.invalidateSelf();
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter0) {
        this.paint.setColorFilter(colorFilter0);
        this.invalidateSelf();
    }

    void setGradientColors(int v, int v1, int v2, int v3) {
        this.topOuterStrokeColor = v;
        this.topInnerStrokeColor = v1;
        this.bottomOuterStrokeColor = v2;
        this.bottomInnerStrokeColor = v3;
    }

    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel0) {
        this.shapeAppearanceModel = shapeAppearanceModel0;
        this.invalidateSelf();
    }

    class com.google.android.material.floatingactionbutton.BorderDrawable.1 {
    }

}

