package androidx.cardview.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

class RoundRectDrawable extends Drawable {
    private ColorStateList mBackground;
    private final RectF mBoundsF;
    private final Rect mBoundsI;
    private boolean mInsetForPadding;
    private boolean mInsetForRadius;
    private float mPadding;
    private final Paint mPaint;
    private float mRadius;
    private ColorStateList mTint;
    private PorterDuffColorFilter mTintFilter;
    private PorterDuff.Mode mTintMode;

    RoundRectDrawable(ColorStateList colorStateList0, float f) {
        this.mInsetForPadding = false;
        this.mInsetForRadius = true;
        this.mTintMode = PorterDuff.Mode.SRC_IN;
        this.mRadius = f;
        this.mPaint = new Paint(5);
        this.setBackground(colorStateList0);
        this.mBoundsF = new RectF();
        this.mBoundsI = new Rect();
    }

    private PorterDuffColorFilter createTintFilter(ColorStateList colorStateList0, PorterDuff.Mode porterDuff$Mode0) {
        return colorStateList0 == null || porterDuff$Mode0 == null ? null : new PorterDuffColorFilter(colorStateList0.getColorForState(this.getState(), 0), porterDuff$Mode0);
    }

    @Override  // android.graphics.drawable.Drawable
    public void draw(Canvas canvas0) {
        boolean z;
        Paint paint0 = this.mPaint;
        if(this.mTintFilter == null || paint0.getColorFilter() != null) {
            z = false;
        }
        else {
            paint0.setColorFilter(this.mTintFilter);
            z = true;
        }
        canvas0.drawRoundRect(this.mBoundsF, this.mRadius, this.mRadius, paint0);
        if(z) {
            paint0.setColorFilter(null);
        }
    }

    public ColorStateList getColor() {
        return this.mBackground;
    }

    @Override  // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override  // android.graphics.drawable.Drawable
    public void getOutline(Outline outline0) {
        outline0.setRoundRect(this.mBoundsI, this.mRadius);
    }

    float getPadding() {
        return this.mPadding;
    }

    public float getRadius() {
        return this.mRadius;
    }

    // 去混淆评级： 中等(50)
    @Override  // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return this.mTint != null && this.mTint.isStateful() || (this.mBackground != null && this.mBackground.isStateful() || super.isStateful());
    }

    @Override  // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect0) {
        super.onBoundsChange(rect0);
        this.updateBounds(rect0);
    }

    @Override  // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] arr_v) {
        int v = this.mBackground.getColorForState(arr_v, this.mBackground.getDefaultColor());
        boolean z = v != this.mPaint.getColor();
        if(z) {
            this.mPaint.setColor(v);
        }
        ColorStateList colorStateList0 = this.mTint;
        if(colorStateList0 != null) {
            PorterDuff.Mode porterDuff$Mode0 = this.mTintMode;
            if(porterDuff$Mode0 != null) {
                this.mTintFilter = this.createTintFilter(colorStateList0, porterDuff$Mode0);
                return true;
            }
        }
        return z;
    }

    @Override  // android.graphics.drawable.Drawable
    public void setAlpha(int v) {
        this.mPaint.setAlpha(v);
    }

    private void setBackground(ColorStateList colorStateList0) {
        if(colorStateList0 == null) {
            colorStateList0 = ColorStateList.valueOf(0);
        }
        this.mBackground = colorStateList0;
        int v = colorStateList0.getColorForState(this.getState(), this.mBackground.getDefaultColor());
        this.mPaint.setColor(v);
    }

    public void setColor(ColorStateList colorStateList0) {
        this.setBackground(colorStateList0);
        this.invalidateSelf();
    }

    @Override  // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter0) {
        this.mPaint.setColorFilter(colorFilter0);
    }

    void setPadding(float f, boolean z, boolean z1) {
        if(f == this.mPadding && this.mInsetForPadding == z && this.mInsetForRadius == z1) {
            return;
        }
        this.mPadding = f;
        this.mInsetForPadding = z;
        this.mInsetForRadius = z1;
        this.updateBounds(null);
        this.invalidateSelf();
    }

    void setRadius(float f) {
        if(f == this.mRadius) {
            return;
        }
        this.mRadius = f;
        this.updateBounds(null);
        this.invalidateSelf();
    }

    @Override  // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList0) {
        this.mTint = colorStateList0;
        this.mTintFilter = this.createTintFilter(colorStateList0, this.mTintMode);
        this.invalidateSelf();
    }

    @Override  // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode porterDuff$Mode0) {
        this.mTintMode = porterDuff$Mode0;
        this.mTintFilter = this.createTintFilter(this.mTint, porterDuff$Mode0);
        this.invalidateSelf();
    }

    private void updateBounds(Rect rect0) {
        if(rect0 == null) {
            rect0 = this.getBounds();
        }
        this.mBoundsF.set(((float)rect0.left), ((float)rect0.top), ((float)rect0.right), ((float)rect0.bottom));
        this.mBoundsI.set(rect0);
        if(this.mInsetForPadding) {
            float f = RoundRectDrawableWithShadow.calculateVerticalPadding(this.mPadding, this.mRadius, this.mInsetForRadius);
            int v = (int)Math.ceil(RoundRectDrawableWithShadow.calculateHorizontalPadding(this.mPadding, this.mRadius, this.mInsetForRadius));
            this.mBoundsI.inset(v, ((int)Math.ceil(f)));
            this.mBoundsF.set(this.mBoundsI);
        }
    }
}

