package com.google.android.material.shadow;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.graphics.Path.FillType;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import androidx.appcompat.graphics.drawable.DrawableWrapperCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.R.color;

@Deprecated
public class ShadowDrawableWrapper extends DrawableWrapperCompat {
    static final double COS_45 = 0.0;
    static final float SHADOW_BOTTOM_SCALE = 1.0f;
    static final float SHADOW_HORIZ_SCALE = 0.5f;
    static final float SHADOW_MULTIPLIER = 1.5f;
    static final float SHADOW_TOP_SCALE = 0.25f;
    private boolean addPaddingForCorners;
    final RectF contentBounds;
    float cornerRadius;
    final Paint cornerShadowPaint;
    Path cornerShadowPath;
    private boolean dirty;
    final Paint edgeShadowPaint;
    float maxShadowSize;
    private boolean printedShadowClipWarning;
    float rawMaxShadowSize;
    float rawShadowSize;
    private float rotation;
    private final int shadowEndColor;
    private final int shadowMiddleColor;
    float shadowSize;
    private final int shadowStartColor;

    static {
        ShadowDrawableWrapper.COS_45 = 0.707107;
    }

    public ShadowDrawableWrapper(Context context0, Drawable drawable0, float f, float f1, float f2) {
        super(drawable0);
        this.dirty = true;
        this.addPaddingForCorners = true;
        this.printedShadowClipWarning = false;
        this.shadowStartColor = ContextCompat.getColor(context0, color.design_fab_shadow_start_color);
        this.shadowMiddleColor = ContextCompat.getColor(context0, color.design_fab_shadow_mid_color);
        this.shadowEndColor = ContextCompat.getColor(context0, color.design_fab_shadow_end_color);
        Paint paint0 = new Paint(5);
        this.cornerShadowPaint = paint0;
        paint0.setStyle(Paint.Style.FILL);
        this.cornerRadius = (float)Math.round(f);
        this.contentBounds = new RectF();
        Paint paint1 = new Paint(paint0);
        this.edgeShadowPaint = paint1;
        paint1.setAntiAlias(false);
        this.setShadowSize(f1, f2);
    }

    private void buildComponents(Rect rect0) {
        this.contentBounds.set(((float)rect0.left) + this.rawMaxShadowSize, ((float)rect0.top) + this.rawMaxShadowSize * 1.5f, ((float)rect0.right) - this.rawMaxShadowSize, ((float)rect0.bottom) - this.rawMaxShadowSize * 1.5f);
        this.getDrawable().setBounds(((int)this.contentBounds.left), ((int)this.contentBounds.top), ((int)this.contentBounds.right), ((int)this.contentBounds.bottom));
        this.buildShadowCorners();
    }

    private void buildShadowCorners() {
        RectF rectF0 = new RectF(-this.cornerRadius, -this.cornerRadius, this.cornerRadius, this.cornerRadius);
        RectF rectF1 = new RectF(rectF0);
        rectF1.inset(-this.shadowSize, -this.shadowSize);
        Path path0 = this.cornerShadowPath;
        if(path0 == null) {
            this.cornerShadowPath = new Path();
        }
        else {
            path0.reset();
        }
        this.cornerShadowPath.setFillType(Path.FillType.EVEN_ODD);
        this.cornerShadowPath.moveTo(-this.cornerRadius, 0.0f);
        this.cornerShadowPath.rLineTo(-this.shadowSize, 0.0f);
        this.cornerShadowPath.arcTo(rectF1, 180.0f, 90.0f, false);
        this.cornerShadowPath.arcTo(rectF0, 270.0f, -90.0f, false);
        this.cornerShadowPath.close();
        float f = -rectF1.top;
        if(f > 0.0f) {
            float f1 = this.cornerRadius / f;
            RadialGradient radialGradient0 = new RadialGradient(0.0f, 0.0f, f, new int[]{0, this.shadowStartColor, this.shadowMiddleColor, this.shadowEndColor}, new float[]{0.0f, f1, (1.0f - f1) / 2.0f + f1, 1.0f}, Shader.TileMode.CLAMP);
            this.cornerShadowPaint.setShader(radialGradient0);
        }
        LinearGradient linearGradient0 = new LinearGradient(0.0f, rectF0.top, 0.0f, rectF1.top, new int[]{this.shadowStartColor, this.shadowMiddleColor, this.shadowEndColor}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP);
        this.edgeShadowPaint.setShader(linearGradient0);
        this.edgeShadowPaint.setAntiAlias(false);
    }

    // 去混淆评级： 低(20)
    public static float calculateHorizontalPadding(float f, float f1, boolean z) {
        return z ? ((float)(((double)f) + (1.0 - ShadowDrawableWrapper.COS_45) * ((double)f1))) : f;
    }

    // 去混淆评级： 低(20)
    public static float calculateVerticalPadding(float f, float f1, boolean z) {
        return z ? ((float)(((double)(f * 1.5f)) + (1.0 - ShadowDrawableWrapper.COS_45) * ((double)f1))) : f * 1.5f;
    }

    @Override  // androidx.appcompat.graphics.drawable.DrawableWrapperCompat
    public void draw(Canvas canvas0) {
        if(this.dirty) {
            this.buildComponents(this.getBounds());
            this.dirty = false;
        }
        this.drawShadow(canvas0);
        super.draw(canvas0);
    }

    private void drawShadow(Canvas canvas0) {
        int v = canvas0.save();
        canvas0.rotate(this.rotation, this.contentBounds.centerX(), this.contentBounds.centerY());
        float f = this.cornerRadius;
        float f1 = -f - this.shadowSize;
        boolean z = this.contentBounds.width() - f * 2.0f > 0.0f;
        boolean z1 = this.contentBounds.height() - f * 2.0f > 0.0f;
        float f2 = this.rawShadowSize - 0.25f * this.rawShadowSize;
        float f3 = this.rawShadowSize - this.rawShadowSize * 1.0f;
        float f4 = f / (this.rawShadowSize - 0.5f * this.rawShadowSize + f);
        float f5 = f / (f2 + f);
        float f6 = f / (f3 + f);
        int v1 = canvas0.save();
        canvas0.translate(this.contentBounds.left + f, this.contentBounds.top + f);
        canvas0.scale(f4, f5);
        canvas0.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if(z) {
            canvas0.scale(1.0f / f4, 1.0f);
            canvas0.drawRect(0.0f, f1, this.contentBounds.width() - f * 2.0f, -this.cornerRadius, this.edgeShadowPaint);
        }
        canvas0.restoreToCount(v1);
        int v2 = canvas0.save();
        canvas0.translate(this.contentBounds.right - f, this.contentBounds.bottom - f);
        canvas0.scale(f4, f6);
        canvas0.rotate(180.0f);
        canvas0.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if(z) {
            canvas0.scale(1.0f / f4, 1.0f);
            canvas0.drawRect(0.0f, f1, this.contentBounds.width() - f * 2.0f, this.shadowSize + -this.cornerRadius, this.edgeShadowPaint);
        }
        canvas0.restoreToCount(v2);
        int v3 = canvas0.save();
        canvas0.translate(this.contentBounds.left + f, this.contentBounds.bottom - f);
        canvas0.scale(f4, f6);
        canvas0.rotate(270.0f);
        canvas0.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if(z1) {
            canvas0.scale(1.0f / f6, 1.0f);
            canvas0.drawRect(0.0f, f1, this.contentBounds.height() - f * 2.0f, -this.cornerRadius, this.edgeShadowPaint);
        }
        canvas0.restoreToCount(v3);
        int v4 = canvas0.save();
        canvas0.translate(this.contentBounds.right - f, this.contentBounds.top + f);
        canvas0.scale(f4, f5);
        canvas0.rotate(90.0f);
        canvas0.drawPath(this.cornerShadowPath, this.cornerShadowPaint);
        if(z1) {
            canvas0.scale(1.0f / f5, 1.0f);
            canvas0.drawRect(0.0f, f1, this.contentBounds.height() - f * 2.0f, -this.cornerRadius, this.edgeShadowPaint);
        }
        canvas0.restoreToCount(v4);
        canvas0.restoreToCount(v);
    }

    public float getCornerRadius() {
        return this.cornerRadius;
    }

    public float getMaxShadowSize() {
        return this.rawMaxShadowSize;
    }

    public float getMinHeight() {
        return Math.max(this.rawMaxShadowSize, this.cornerRadius + this.rawMaxShadowSize * 1.5f / 2.0f) * 2.0f + this.rawMaxShadowSize * 3.0f;
    }

    public float getMinWidth() {
        return Math.max(this.rawMaxShadowSize, this.cornerRadius + this.rawMaxShadowSize / 2.0f) * 2.0f + this.rawMaxShadowSize * 2.0f;
    }

    @Override  // androidx.appcompat.graphics.drawable.DrawableWrapperCompat
    public int getOpacity() {
        return -3;
    }

    @Override  // androidx.appcompat.graphics.drawable.DrawableWrapperCompat
    public boolean getPadding(Rect rect0) {
        int v = (int)Math.ceil(ShadowDrawableWrapper.calculateVerticalPadding(this.rawMaxShadowSize, this.cornerRadius, this.addPaddingForCorners));
        int v1 = (int)Math.ceil(ShadowDrawableWrapper.calculateHorizontalPadding(this.rawMaxShadowSize, this.cornerRadius, this.addPaddingForCorners));
        rect0.set(v1, v, v1, v);
        return true;
    }

    public float getShadowSize() {
        return this.rawShadowSize;
    }

    @Override  // androidx.appcompat.graphics.drawable.DrawableWrapperCompat
    protected void onBoundsChange(Rect rect0) {
        this.dirty = true;
    }

    public void setAddPaddingForCorners(boolean z) {
        this.addPaddingForCorners = z;
        this.invalidateSelf();
    }

    @Override  // androidx.appcompat.graphics.drawable.DrawableWrapperCompat
    public void setAlpha(int v) {
        super.setAlpha(v);
        this.cornerShadowPaint.setAlpha(v);
        this.edgeShadowPaint.setAlpha(v);
    }

    public void setCornerRadius(float f) {
        float f1 = (float)Math.round(f);
        if(this.cornerRadius == f1) {
            return;
        }
        this.cornerRadius = f1;
        this.dirty = true;
        this.invalidateSelf();
    }

    public void setMaxShadowSize(float f) {
        this.setShadowSize(this.rawShadowSize, f);
    }

    public final void setRotation(float f) {
        if(this.rotation != f) {
            this.rotation = f;
            this.invalidateSelf();
        }
    }

    public void setShadowSize(float f) {
        this.setShadowSize(f, this.rawMaxShadowSize);
    }

    public void setShadowSize(float f, float f1) {
        if(f < 0.0f || f1 < 0.0f) {
            throw new IllegalArgumentException("invalid shadow size");
        }
        float f2 = (float)ShadowDrawableWrapper.toEven(f);
        float f3 = (float)ShadowDrawableWrapper.toEven(f1);
        if(f2 > f3) {
            if(!this.printedShadowClipWarning) {
                this.printedShadowClipWarning = true;
            }
            f2 = f3;
        }
        if(this.rawShadowSize == f2 && this.rawMaxShadowSize == f3) {
            return;
        }
        this.rawShadowSize = f2;
        this.rawMaxShadowSize = f3;
        this.shadowSize = (float)Math.round(f2 * 1.5f);
        this.maxShadowSize = f3;
        this.dirty = true;
        this.invalidateSelf();
    }

    private static int toEven(float f) {
        int v = Math.round(f);
        return v % 2 == 1 ? v - 1 : v;
    }
}

