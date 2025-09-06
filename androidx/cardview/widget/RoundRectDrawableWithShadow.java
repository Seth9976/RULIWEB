package androidx.cardview.widget;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
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
import androidx.cardview.R.color;
import androidx.cardview.R.dimen;

class RoundRectDrawableWithShadow extends Drawable {
    interface RoundRectHelper {
        void drawRoundRect(Canvas arg1, RectF arg2, float arg3, Paint arg4);
    }

    private static final double COS_45 = 0.0;
    private static final float SHADOW_MULTIPLIER = 1.5f;
    private boolean mAddPaddingForCorners;
    private ColorStateList mBackground;
    private final RectF mCardBounds;
    private float mCornerRadius;
    private Paint mCornerShadowPaint;
    private Path mCornerShadowPath;
    private boolean mDirty;
    private Paint mEdgeShadowPaint;
    private final int mInsetShadow;
    private Paint mPaint;
    private boolean mPrintedShadowClipWarning;
    private float mRawMaxShadowSize;
    private float mRawShadowSize;
    private final int mShadowEndColor;
    private float mShadowSize;
    private final int mShadowStartColor;
    static RoundRectHelper sRoundRectHelper;

    static {
        RoundRectDrawableWithShadow.COS_45 = 0.707107;
    }

    RoundRectDrawableWithShadow(Resources resources0, ColorStateList colorStateList0, float f, float f1, float f2) {
        this.mDirty = true;
        this.mAddPaddingForCorners = true;
        this.mPrintedShadowClipWarning = false;
        this.mShadowStartColor = resources0.getColor(color.cardview_shadow_start_color);
        this.mShadowEndColor = resources0.getColor(color.cardview_shadow_end_color);
        this.mInsetShadow = resources0.getDimensionPixelSize(dimen.cardview_compat_inset_shadow);
        this.mPaint = new Paint(5);
        this.setBackground(colorStateList0);
        Paint paint0 = new Paint(5);
        this.mCornerShadowPaint = paint0;
        paint0.setStyle(Paint.Style.FILL);
        this.mCornerRadius = (float)(((int)(f + 0.5f)));
        this.mCardBounds = new RectF();
        Paint paint1 = new Paint(this.mCornerShadowPaint);
        this.mEdgeShadowPaint = paint1;
        paint1.setAntiAlias(false);
        this.setShadowSize(f1, f2);
    }

    private void buildComponents(Rect rect0) {
        this.mCardBounds.set(((float)rect0.left) + this.mRawMaxShadowSize, ((float)rect0.top) + this.mRawMaxShadowSize * 1.5f, ((float)rect0.right) - this.mRawMaxShadowSize, ((float)rect0.bottom) - this.mRawMaxShadowSize * 1.5f);
        this.buildShadowCorners();
    }

    private void buildShadowCorners() {
        RectF rectF0 = new RectF(-this.mCornerRadius, -this.mCornerRadius, this.mCornerRadius, this.mCornerRadius);
        RectF rectF1 = new RectF(rectF0);
        rectF1.inset(-this.mShadowSize, -this.mShadowSize);
        Path path0 = this.mCornerShadowPath;
        if(path0 == null) {
            this.mCornerShadowPath = new Path();
        }
        else {
            path0.reset();
        }
        this.mCornerShadowPath.setFillType(Path.FillType.EVEN_ODD);
        this.mCornerShadowPath.moveTo(-this.mCornerRadius, 0.0f);
        this.mCornerShadowPath.rLineTo(-this.mShadowSize, 0.0f);
        this.mCornerShadowPath.arcTo(rectF1, 180.0f, 90.0f, false);
        this.mCornerShadowPath.arcTo(rectF0, 270.0f, -90.0f, false);
        this.mCornerShadowPath.close();
        this.mCornerShadowPaint.setShader(new RadialGradient(0.0f, 0.0f, this.mCornerRadius + this.mShadowSize, new int[]{this.mShadowStartColor, this.mShadowStartColor, this.mShadowEndColor}, new float[]{0.0f, this.mCornerRadius / (this.mShadowSize + this.mCornerRadius), 1.0f}, Shader.TileMode.CLAMP));
        this.mEdgeShadowPaint.setShader(new LinearGradient(0.0f, this.mShadowSize - this.mCornerRadius, 0.0f, -this.mCornerRadius - this.mShadowSize, new int[]{this.mShadowStartColor, this.mShadowStartColor, this.mShadowEndColor}, new float[]{0.0f, 0.5f, 1.0f}, Shader.TileMode.CLAMP));
        this.mEdgeShadowPaint.setAntiAlias(false);
    }

    // 去混淆评级： 低(20)
    static float calculateHorizontalPadding(float f, float f1, boolean z) {
        return z ? ((float)(((double)f) + (1.0 - RoundRectDrawableWithShadow.COS_45) * ((double)f1))) : f;
    }

    // 去混淆评级： 低(20)
    static float calculateVerticalPadding(float f, float f1, boolean z) {
        return z ? ((float)(((double)(f * 1.5f)) + (1.0 - RoundRectDrawableWithShadow.COS_45) * ((double)f1))) : f * 1.5f;
    }

    @Override  // android.graphics.drawable.Drawable
    public void draw(Canvas canvas0) {
        if(this.mDirty) {
            this.buildComponents(this.getBounds());
            this.mDirty = false;
        }
        canvas0.translate(0.0f, this.mRawShadowSize / 2.0f);
        this.drawShadow(canvas0);
        canvas0.translate(0.0f, -this.mRawShadowSize / 2.0f);
        RoundRectDrawableWithShadow.sRoundRectHelper.drawRoundRect(canvas0, this.mCardBounds, this.mCornerRadius, this.mPaint);
    }

    private void drawShadow(Canvas canvas0) {
        float f = -this.mCornerRadius - this.mShadowSize;
        float f1 = this.mCornerRadius + ((float)this.mInsetShadow) + this.mRawShadowSize / 2.0f;
        boolean z = this.mCardBounds.width() - 2.0f * f1 > 0.0f;
        boolean z1 = this.mCardBounds.height() - 2.0f * f1 > 0.0f;
        int v = canvas0.save();
        canvas0.translate(this.mCardBounds.left + f1, this.mCardBounds.top + f1);
        canvas0.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if(z) {
            canvas0.drawRect(0.0f, f, this.mCardBounds.width() - 2.0f * f1, -this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas0.restoreToCount(v);
        int v1 = canvas0.save();
        canvas0.translate(this.mCardBounds.right - f1, this.mCardBounds.bottom - f1);
        canvas0.rotate(180.0f);
        canvas0.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if(z) {
            canvas0.drawRect(0.0f, f, this.mCardBounds.width() - 2.0f * f1, this.mShadowSize - this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas0.restoreToCount(v1);
        int v2 = canvas0.save();
        canvas0.translate(this.mCardBounds.left + f1, this.mCardBounds.bottom - f1);
        canvas0.rotate(270.0f);
        canvas0.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if(z1) {
            canvas0.drawRect(0.0f, f, this.mCardBounds.height() - 2.0f * f1, -this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas0.restoreToCount(v2);
        int v3 = canvas0.save();
        canvas0.translate(this.mCardBounds.right - f1, this.mCardBounds.top + f1);
        canvas0.rotate(90.0f);
        canvas0.drawPath(this.mCornerShadowPath, this.mCornerShadowPaint);
        if(z1) {
            canvas0.drawRect(0.0f, f, this.mCardBounds.height() - 2.0f * f1, -this.mCornerRadius, this.mEdgeShadowPaint);
        }
        canvas0.restoreToCount(v3);
    }

    ColorStateList getColor() {
        return this.mBackground;
    }

    float getCornerRadius() {
        return this.mCornerRadius;
    }

    void getMaxShadowAndCornerPadding(Rect rect0) {
        this.getPadding(rect0);
    }

    float getMaxShadowSize() {
        return this.mRawMaxShadowSize;
    }

    float getMinHeight() {
        return Math.max(this.mRawMaxShadowSize, this.mCornerRadius + ((float)this.mInsetShadow) + this.mRawMaxShadowSize * 1.5f / 2.0f) * 2.0f + (this.mRawMaxShadowSize * 1.5f + ((float)this.mInsetShadow)) * 2.0f;
    }

    float getMinWidth() {
        return Math.max(this.mRawMaxShadowSize, this.mCornerRadius + ((float)this.mInsetShadow) + this.mRawMaxShadowSize / 2.0f) * 2.0f + (this.mRawMaxShadowSize + ((float)this.mInsetShadow)) * 2.0f;
    }

    @Override  // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect0) {
        int v = (int)Math.ceil(RoundRectDrawableWithShadow.calculateVerticalPadding(this.mRawMaxShadowSize, this.mCornerRadius, this.mAddPaddingForCorners));
        int v1 = (int)Math.ceil(RoundRectDrawableWithShadow.calculateHorizontalPadding(this.mRawMaxShadowSize, this.mCornerRadius, this.mAddPaddingForCorners));
        rect0.set(v1, v, v1, v);
        return true;
    }

    float getShadowSize() {
        return this.mRawShadowSize;
    }

    // 去混淆评级： 低(30)
    @Override  // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return this.mBackground != null && this.mBackground.isStateful() || super.isStateful();
    }

    @Override  // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect0) {
        super.onBoundsChange(rect0);
        this.mDirty = true;
    }

    @Override  // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] arr_v) {
        int v = this.mBackground.getColorForState(arr_v, this.mBackground.getDefaultColor());
        if(this.mPaint.getColor() == v) {
            return false;
        }
        this.mPaint.setColor(v);
        this.mDirty = true;
        this.invalidateSelf();
        return true;
    }

    void setAddPaddingForCorners(boolean z) {
        this.mAddPaddingForCorners = z;
        this.invalidateSelf();
    }

    @Override  // android.graphics.drawable.Drawable
    public void setAlpha(int v) {
        this.mPaint.setAlpha(v);
        this.mCornerShadowPaint.setAlpha(v);
        this.mEdgeShadowPaint.setAlpha(v);
    }

    private void setBackground(ColorStateList colorStateList0) {
        if(colorStateList0 == null) {
            colorStateList0 = ColorStateList.valueOf(0);
        }
        this.mBackground = colorStateList0;
        this.mPaint.setColor(colorStateList0.getColorForState(this.getState(), this.mBackground.getDefaultColor()));
    }

    void setColor(ColorStateList colorStateList0) {
        this.setBackground(colorStateList0);
        this.invalidateSelf();
    }

    @Override  // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter0) {
        this.mPaint.setColorFilter(colorFilter0);
    }

    void setCornerRadius(float f) {
        if(f < 0.0f) {
            throw new IllegalArgumentException("Invalid radius " + f + ". Must be >= 0");
        }
        float f1 = (float)(((int)(f + 0.5f)));
        if(this.mCornerRadius == f1) {
            return;
        }
        this.mCornerRadius = f1;
        this.mDirty = true;
        this.invalidateSelf();
    }

    void setMaxShadowSize(float f) {
        this.setShadowSize(this.mRawShadowSize, f);
    }

    private void setShadowSize(float f, float f1) {
        if(f < 0.0f) {
            throw new IllegalArgumentException("Invalid shadow size " + f + ". Must be >= 0");
        }
        if(f1 < 0.0f) {
            throw new IllegalArgumentException("Invalid max shadow size " + f1 + ". Must be >= 0");
        }
        float f2 = (float)this.toEven(f);
        float f3 = (float)this.toEven(f1);
        if(f2 > f3) {
            if(!this.mPrintedShadowClipWarning) {
                this.mPrintedShadowClipWarning = true;
            }
            f2 = f3;
        }
        if(this.mRawShadowSize == f2 && this.mRawMaxShadowSize == f3) {
            return;
        }
        this.mRawShadowSize = f2;
        this.mRawMaxShadowSize = f3;
        this.mShadowSize = (float)(((int)(f2 * 1.5f + ((float)this.mInsetShadow) + 0.5f)));
        this.mDirty = true;
        this.invalidateSelf();
    }

    void setShadowSize(float f) {
        this.setShadowSize(f, this.mRawMaxShadowSize);
    }

    private int toEven(float f) {
        return ((int)(f + 0.5f)) % 2 == 1 ? ((int)(f + 0.5f)) - 1 : ((int)(f + 0.5f));
    }
}

