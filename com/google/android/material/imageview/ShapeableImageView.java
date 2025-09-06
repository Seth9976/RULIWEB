package com.google.android.material.imageview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.graphics.Path.Direction;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeAppearancePathProvider;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

public class ShapeableImageView extends AppCompatImageView implements Shapeable {
    class OutlineProvider extends ViewOutlineProvider {
        private final Rect rect;

        OutlineProvider() {
            this.rect = new Rect();
        }

        @Override  // android.view.ViewOutlineProvider
        public void getOutline(View view0, Outline outline0) {
            if(ShapeableImageView.this.shapeAppearanceModel == null) {
                return;
            }
            if(ShapeableImageView.this.shadowDrawable == null) {
                MaterialShapeDrawable materialShapeDrawable0 = new MaterialShapeDrawable(ShapeableImageView.this.shapeAppearanceModel);
                ShapeableImageView.this.shadowDrawable = materialShapeDrawable0;
            }
            ShapeableImageView.this.destination.round(this.rect);
            ShapeableImageView.this.shadowDrawable.setBounds(this.rect);
            ShapeableImageView.this.shadowDrawable.getOutline(outline0);
        }
    }

    private static final int DEF_STYLE_RES = 0;
    private static final int UNDEFINED_PADDING = 0x80000000;
    private final Paint borderPaint;
    private int bottomContentPadding;
    private final Paint clearPaint;
    private final RectF destination;
    private int endContentPadding;
    private boolean hasAdjustedPaddingAfterLayoutDirectionResolved;
    private int leftContentPadding;
    private Path maskPath;
    private final RectF maskRect;
    private final Path path;
    private final ShapeAppearancePathProvider pathProvider;
    private int rightContentPadding;
    private MaterialShapeDrawable shadowDrawable;
    private ShapeAppearanceModel shapeAppearanceModel;
    private int startContentPadding;
    private ColorStateList strokeColor;
    private float strokeWidth;
    private int topContentPadding;

    static {
        ShapeableImageView.DEF_STYLE_RES = style.Widget_MaterialComponents_ShapeableImageView;
    }

    public ShapeableImageView(Context context0) {
        this(context0, null, 0);
    }

    public ShapeableImageView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public ShapeableImageView(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, ShapeableImageView.DEF_STYLE_RES), attributeSet0, v);
        this.pathProvider = ShapeAppearancePathProvider.getInstance();
        this.path = new Path();
        this.hasAdjustedPaddingAfterLayoutDirectionResolved = false;
        Context context1 = this.getContext();
        Paint paint0 = new Paint();
        this.clearPaint = paint0;
        paint0.setAntiAlias(true);
        paint0.setColor(-1);
        paint0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
        this.destination = new RectF();
        this.maskRect = new RectF();
        this.maskPath = new Path();
        TypedArray typedArray0 = context1.obtainStyledAttributes(attributeSet0, styleable.ShapeableImageView, v, ShapeableImageView.DEF_STYLE_RES);
        this.setLayerType(2, null);
        this.strokeColor = MaterialResources.getColorStateList(context1, typedArray0, styleable.ShapeableImageView_strokeColor);
        this.strokeWidth = (float)typedArray0.getDimensionPixelSize(styleable.ShapeableImageView_strokeWidth, 0);
        int v1 = typedArray0.getDimensionPixelSize(styleable.ShapeableImageView_contentPadding, 0);
        this.topContentPadding = v1;
        this.rightContentPadding = v1;
        this.bottomContentPadding = v1;
        this.leftContentPadding = typedArray0.getDimensionPixelSize(styleable.ShapeableImageView_contentPaddingLeft, v1);
        this.topContentPadding = typedArray0.getDimensionPixelSize(styleable.ShapeableImageView_contentPaddingTop, v1);
        this.rightContentPadding = typedArray0.getDimensionPixelSize(styleable.ShapeableImageView_contentPaddingRight, v1);
        this.bottomContentPadding = typedArray0.getDimensionPixelSize(styleable.ShapeableImageView_contentPaddingBottom, v1);
        this.startContentPadding = typedArray0.getDimensionPixelSize(styleable.ShapeableImageView_contentPaddingStart, 0x80000000);
        this.endContentPadding = typedArray0.getDimensionPixelSize(styleable.ShapeableImageView_contentPaddingEnd, 0x80000000);
        typedArray0.recycle();
        Paint paint1 = new Paint();
        this.borderPaint = paint1;
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setAntiAlias(true);
        this.shapeAppearanceModel = ShapeAppearanceModel.builder(context1, attributeSet0, v, ShapeableImageView.DEF_STYLE_RES).build();
        this.setOutlineProvider(new OutlineProvider(this));
    }

    private void drawStroke(Canvas canvas0) {
        if(this.strokeColor != null) {
            this.borderPaint.setStrokeWidth(this.strokeWidth);
            int v = this.strokeColor.getColorForState(this.getDrawableState(), this.strokeColor.getDefaultColor());
            if(this.strokeWidth > 0.0f && v != 0) {
                this.borderPaint.setColor(v);
                canvas0.drawPath(this.path, this.borderPaint);
            }
        }
    }

    public int getContentPaddingBottom() {
        return this.bottomContentPadding;
    }

    public final int getContentPaddingEnd() {
        int v = this.endContentPadding;
        if(v != 0x80000000) {
            return v;
        }
        return this.isRtl() ? this.leftContentPadding : this.rightContentPadding;
    }

    public int getContentPaddingLeft() {
        if(this.isContentPaddingRelative()) {
            if(this.isRtl()) {
                int v = this.endContentPadding;
                if(v != 0x80000000) {
                    return v;
                }
            }
            if(!this.isRtl()) {
                return this.startContentPadding == 0x80000000 ? this.leftContentPadding : this.startContentPadding;
            }
        }
        return this.leftContentPadding;
    }

    public int getContentPaddingRight() {
        if(this.isContentPaddingRelative()) {
            if(this.isRtl()) {
                int v = this.startContentPadding;
                if(v != 0x80000000) {
                    return v;
                }
            }
            if(!this.isRtl()) {
                return this.endContentPadding == 0x80000000 ? this.rightContentPadding : this.endContentPadding;
            }
        }
        return this.rightContentPadding;
    }

    public final int getContentPaddingStart() {
        int v = this.startContentPadding;
        if(v != 0x80000000) {
            return v;
        }
        return this.isRtl() ? this.rightContentPadding : this.leftContentPadding;
    }

    public int getContentPaddingTop() {
        return this.topContentPadding;
    }

    @Override  // android.view.View
    public int getPaddingBottom() {
        return super.getPaddingBottom() - this.getContentPaddingBottom();
    }

    @Override  // android.view.View
    public int getPaddingEnd() {
        return super.getPaddingEnd() - this.getContentPaddingEnd();
    }

    @Override  // android.view.View
    public int getPaddingLeft() {
        return super.getPaddingLeft() - this.getContentPaddingLeft();
    }

    @Override  // android.view.View
    public int getPaddingRight() {
        return super.getPaddingRight() - this.getContentPaddingRight();
    }

    @Override  // android.view.View
    public int getPaddingStart() {
        return super.getPaddingStart() - this.getContentPaddingStart();
    }

    @Override  // android.view.View
    public int getPaddingTop() {
        return super.getPaddingTop() - this.getContentPaddingTop();
    }

    @Override  // com.google.android.material.shape.Shapeable
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.shapeAppearanceModel;
    }

    public ColorStateList getStrokeColor() {
        return this.strokeColor;
    }

    public float getStrokeWidth() {
        return this.strokeWidth;
    }

    private boolean isContentPaddingRelative() {
        return this.startContentPadding != 0x80000000 || this.endContentPadding != 0x80000000;
    }

    private boolean isRtl() {
        return this.getLayoutDirection() == 1;
    }

    @Override  // android.widget.ImageView
    protected void onDraw(Canvas canvas0) {
        super.onDraw(canvas0);
        canvas0.drawPath(this.maskPath, this.clearPaint);
        this.drawStroke(canvas0);
    }

    @Override  // android.widget.ImageView
    protected void onMeasure(int v, int v1) {
        super.onMeasure(v, v1);
        if(this.hasAdjustedPaddingAfterLayoutDirectionResolved || !this.isLayoutDirectionResolved()) {
            return;
        }
        this.hasAdjustedPaddingAfterLayoutDirectionResolved = true;
        if(!this.isPaddingRelative() && !this.isContentPaddingRelative()) {
            this.setPadding(super.getPaddingLeft(), super.getPaddingTop(), super.getPaddingRight(), super.getPaddingBottom());
            return;
        }
        this.setPaddingRelative(super.getPaddingStart(), super.getPaddingTop(), super.getPaddingEnd(), super.getPaddingBottom());
    }

    @Override  // android.view.View
    protected void onSizeChanged(int v, int v1, int v2, int v3) {
        super.onSizeChanged(v, v1, v2, v3);
        this.updateShapeMask(v, v1);
    }

    public void setContentPadding(int v, int v1, int v2, int v3) {
        this.startContentPadding = 0x80000000;
        this.endContentPadding = 0x80000000;
        super.setPadding(super.getPaddingLeft() - this.leftContentPadding + v, super.getPaddingTop() - this.topContentPadding + v1, super.getPaddingRight() - this.rightContentPadding + v2, super.getPaddingBottom() - this.bottomContentPadding + v3);
        this.leftContentPadding = v;
        this.topContentPadding = v1;
        this.rightContentPadding = v2;
        this.bottomContentPadding = v3;
    }

    public void setContentPaddingRelative(int v, int v1, int v2, int v3) {
        super.setPaddingRelative(super.getPaddingStart() - this.getContentPaddingStart() + v, super.getPaddingTop() - this.topContentPadding + v1, super.getPaddingEnd() - this.getContentPaddingEnd() + v2, super.getPaddingBottom() - this.bottomContentPadding + v3);
        this.leftContentPadding = this.isRtl() ? v2 : v;
        this.topContentPadding = v1;
        if(!this.isRtl()) {
            v = v2;
        }
        this.rightContentPadding = v;
        this.bottomContentPadding = v3;
    }

    @Override  // android.view.View
    public void setPadding(int v, int v1, int v2, int v3) {
        super.setPadding(v + this.getContentPaddingLeft(), v1 + this.getContentPaddingTop(), v2 + this.getContentPaddingRight(), v3 + this.getContentPaddingBottom());
    }

    @Override  // android.view.View
    public void setPaddingRelative(int v, int v1, int v2, int v3) {
        super.setPaddingRelative(v + this.getContentPaddingStart(), v1 + this.getContentPaddingTop(), v2 + this.getContentPaddingEnd(), v3 + this.getContentPaddingBottom());
    }

    @Override  // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel0) {
        this.shapeAppearanceModel = shapeAppearanceModel0;
        MaterialShapeDrawable materialShapeDrawable0 = this.shadowDrawable;
        if(materialShapeDrawable0 != null) {
            materialShapeDrawable0.setShapeAppearanceModel(shapeAppearanceModel0);
        }
        this.updateShapeMask(this.getWidth(), this.getHeight());
        this.invalidate();
        this.invalidateOutline();
    }

    public void setStrokeColor(ColorStateList colorStateList0) {
        this.strokeColor = colorStateList0;
        this.invalidate();
    }

    public void setStrokeColorResource(int v) {
        this.setStrokeColor(AppCompatResources.getColorStateList(this.getContext(), v));
    }

    public void setStrokeWidth(float f) {
        if(this.strokeWidth != f) {
            this.strokeWidth = f;
            this.invalidate();
        }
    }

    public void setStrokeWidthResource(int v) {
        this.setStrokeWidth(((float)this.getResources().getDimensionPixelSize(v)));
    }

    private void updateShapeMask(int v, int v1) {
        float f = (float)this.getPaddingLeft();
        float f1 = (float)this.getPaddingTop();
        int v2 = this.getPaddingRight();
        int v3 = this.getPaddingBottom();
        this.destination.set(f, f1, ((float)(v - v2)), ((float)(v1 - v3)));
        this.pathProvider.calculatePath(this.shapeAppearanceModel, 1.0f, this.destination, this.path);
        this.maskPath.rewind();
        this.maskPath.addPath(this.path);
        this.maskRect.set(0.0f, 0.0f, ((float)v), ((float)v1));
        this.maskPath.addRect(this.maskRect, Path.Direction.CCW);
    }
}

