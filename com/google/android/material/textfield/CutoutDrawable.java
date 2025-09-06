package com.google.android.material.textfield;

import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;
import com.google.android.material.shape.MaterialShapeDrawable.MaterialShapeDrawableState;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

class CutoutDrawable extends MaterialShapeDrawable {
    static final class CutoutDrawableState extends MaterialShapeDrawableState {
        private final RectF cutoutBounds;

        private CutoutDrawableState(ShapeAppearanceModel shapeAppearanceModel0, RectF rectF0) {
            super(shapeAppearanceModel0, null);
            this.cutoutBounds = rectF0;
        }

        CutoutDrawableState(ShapeAppearanceModel shapeAppearanceModel0, RectF rectF0, com.google.android.material.textfield.CutoutDrawable.1 cutoutDrawable$10) {
            this(shapeAppearanceModel0, rectF0);
        }

        private CutoutDrawableState(CutoutDrawableState cutoutDrawable$CutoutDrawableState0) {
            super(cutoutDrawable$CutoutDrawableState0);
            this.cutoutBounds = cutoutDrawable$CutoutDrawableState0.cutoutBounds;
        }

        CutoutDrawableState(CutoutDrawableState cutoutDrawable$CutoutDrawableState0, com.google.android.material.textfield.CutoutDrawable.1 cutoutDrawable$10) {
            this(cutoutDrawable$CutoutDrawableState0);
        }

        @Override  // com.google.android.material.shape.MaterialShapeDrawable$MaterialShapeDrawableState
        public Drawable newDrawable() {
            Drawable drawable0 = CutoutDrawable.create(this);
            ((CutoutDrawable)drawable0).invalidateSelf();
            return drawable0;
        }
    }

    static class ImplApi14 extends CutoutDrawable {
        private Paint cutoutPaint;
        private int savedLayer;

        ImplApi14(CutoutDrawableState cutoutDrawable$CutoutDrawableState0) {
            super(cutoutDrawable$CutoutDrawableState0, null);
        }

        @Override  // com.google.android.material.shape.MaterialShapeDrawable
        public void draw(Canvas canvas0) {
            this.preDraw(canvas0);
            super.draw(canvas0);
            this.postDraw(canvas0);
        }

        @Override  // com.google.android.material.shape.MaterialShapeDrawable
        protected void drawStrokeShape(Canvas canvas0) {
            super.drawStrokeShape(canvas0);
            canvas0.drawRect(this.drawableState.cutoutBounds, this.getCutoutPaint());
        }

        private Paint getCutoutPaint() {
            if(this.cutoutPaint == null) {
                Paint paint0 = new Paint(1);
                this.cutoutPaint = paint0;
                paint0.setStyle(Paint.Style.FILL_AND_STROKE);
                this.cutoutPaint.setColor(-1);
                this.cutoutPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
            }
            return this.cutoutPaint;
        }

        private void postDraw(Canvas canvas0) {
            if(!this.useHardwareLayer(this.getCallback())) {
                canvas0.restoreToCount(this.savedLayer);
            }
        }

        private void preDraw(Canvas canvas0) {
            Drawable.Callback drawable$Callback0 = this.getCallback();
            if(this.useHardwareLayer(drawable$Callback0)) {
                if(((View)drawable$Callback0).getLayerType() != 2) {
                    ((View)drawable$Callback0).setLayerType(2, null);
                }
                return;
            }
            this.saveCanvasLayer(canvas0);
        }

        private void saveCanvasLayer(Canvas canvas0) {
            this.savedLayer = canvas0.saveLayer(0.0f, 0.0f, ((float)canvas0.getWidth()), ((float)canvas0.getHeight()), null);
        }

        private boolean useHardwareLayer(Drawable.Callback drawable$Callback0) {
            return drawable$Callback0 instanceof View;
        }
    }

    static class ImplApi18 extends CutoutDrawable {
        ImplApi18(CutoutDrawableState cutoutDrawable$CutoutDrawableState0) {
            super(cutoutDrawable$CutoutDrawableState0, null);
        }

        @Override  // com.google.android.material.shape.MaterialShapeDrawable
        protected void drawStrokeShape(Canvas canvas0) {
            if(this.drawableState.cutoutBounds.isEmpty()) {
                super.drawStrokeShape(canvas0);
                return;
            }
            canvas0.save();
            if(Build.VERSION.SDK_INT >= 26) {
                LinkFollowing..ExternalSyntheticApiModelOutline0.m(canvas0, this.drawableState.cutoutBounds);
            }
            else {
                canvas0.clipRect(this.drawableState.cutoutBounds, Region.Op.DIFFERENCE);
            }
            super.drawStrokeShape(canvas0);
            canvas0.restore();
        }
    }

    CutoutDrawableState drawableState;

    private CutoutDrawable(CutoutDrawableState cutoutDrawable$CutoutDrawableState0) {
        super(cutoutDrawable$CutoutDrawableState0);
        this.drawableState = cutoutDrawable$CutoutDrawableState0;
    }

    CutoutDrawable(CutoutDrawableState cutoutDrawable$CutoutDrawableState0, com.google.android.material.textfield.CutoutDrawable.1 cutoutDrawable$10) {
        this(cutoutDrawable$CutoutDrawableState0);
    }

    static CutoutDrawable create(ShapeAppearanceModel shapeAppearanceModel0) {
        if(shapeAppearanceModel0 == null) {
            shapeAppearanceModel0 = new ShapeAppearanceModel();
        }
        return CutoutDrawable.create(new CutoutDrawableState(shapeAppearanceModel0, new RectF(), null));
    }

    private static CutoutDrawable create(CutoutDrawableState cutoutDrawable$CutoutDrawableState0) {
        return new ImplApi18(cutoutDrawable$CutoutDrawableState0);
    }

    boolean hasCutout() {
        return !CutoutDrawableState.access$200(this.drawableState).isEmpty();
    }

    @Override  // com.google.android.material.shape.MaterialShapeDrawable
    public Drawable mutate() {
        this.drawableState = new CutoutDrawableState(this.drawableState, null);
        return this;
    }

    void removeCutout() {
        this.setCutout(0.0f, 0.0f, 0.0f, 0.0f);
    }

    void setCutout(float f, float f1, float f2, float f3) {
        if(f == this.drawableState.cutoutBounds.left && f1 == this.drawableState.cutoutBounds.top && f2 == this.drawableState.cutoutBounds.right && f3 == this.drawableState.cutoutBounds.bottom) {
            return;
        }
        this.drawableState.cutoutBounds.set(f, f1, f2, f3);
        this.invalidateSelf();
    }

    void setCutout(RectF rectF0) {
        this.setCutout(rectF0.left, rectF0.top, rectF0.right, rectF0.bottom);
    }

    class com.google.android.material.textfield.CutoutDrawable.1 {
    }

}

