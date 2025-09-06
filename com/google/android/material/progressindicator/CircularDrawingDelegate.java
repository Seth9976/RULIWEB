package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import com.google.android.material.color.MaterialColors;

final class CircularDrawingDelegate extends DrawingDelegate {
    private static final float ROUND_CAP_RAMP_DOWN_THRESHHOLD = 0.01f;
    private float adjustedRadius;
    private float displayedCornerRadius;
    private float displayedTrackThickness;
    private float totalTrackLengthFraction;
    private boolean useStrokeCap;

    CircularDrawingDelegate(CircularProgressIndicatorSpec circularProgressIndicatorSpec0) {
        super(circularProgressIndicatorSpec0);
    }

    @Override  // com.google.android.material.progressindicator.DrawingDelegate
    void adjustCanvas(Canvas canvas0, Rect rect0, float f, boolean z, boolean z1) {
        float f1 = ((float)rect0.width()) / ((float)this.getPreferredWidth());
        float f2 = ((float)rect0.height()) / ((float)this.getPreferredHeight());
        float f3 = ((float)((CircularProgressIndicatorSpec)this.spec).indicatorSize) / 2.0f + ((float)((CircularProgressIndicatorSpec)this.spec).indicatorInset);
        canvas0.translate(f3 * f1 + ((float)rect0.left), f3 * f2 + ((float)rect0.top));
        canvas0.rotate(-90.0f);
        canvas0.scale(f1, f2);
        if(((CircularProgressIndicatorSpec)this.spec).indicatorDirection != 0) {
            canvas0.scale(1.0f, -1.0f);
        }
        canvas0.clipRect(-f3, -f3, f3, f3);
        this.useStrokeCap = ((CircularProgressIndicatorSpec)this.spec).trackThickness / 2 <= ((CircularProgressIndicatorSpec)this.spec).trackCornerRadius;
        this.displayedTrackThickness = ((float)((CircularProgressIndicatorSpec)this.spec).trackThickness) * f;
        this.displayedCornerRadius = ((float)Math.min(((CircularProgressIndicatorSpec)this.spec).trackThickness / 2, ((CircularProgressIndicatorSpec)this.spec).trackCornerRadius)) * f;
        this.adjustedRadius = ((float)(((CircularProgressIndicatorSpec)this.spec).indicatorSize - ((CircularProgressIndicatorSpec)this.spec).trackThickness)) / 2.0f;
        if(z || z1) {
            if(z && ((CircularProgressIndicatorSpec)this.spec).showAnimationBehavior == 2 || z1 && ((CircularProgressIndicatorSpec)this.spec).hideAnimationBehavior == 1) {
                this.adjustedRadius += (1.0f - f) * ((float)((CircularProgressIndicatorSpec)this.spec).trackThickness) / 2.0f;
            }
            else if(z && ((CircularProgressIndicatorSpec)this.spec).showAnimationBehavior == 1 || z1 && ((CircularProgressIndicatorSpec)this.spec).hideAnimationBehavior == 2) {
                this.adjustedRadius -= (1.0f - f) * ((float)((CircularProgressIndicatorSpec)this.spec).trackThickness) / 2.0f;
            }
        }
        if(z1 && ((CircularProgressIndicatorSpec)this.spec).hideAnimationBehavior == 3) {
            this.totalTrackLengthFraction = f;
            return;
        }
        this.totalTrackLengthFraction = 1.0f;
    }

    private void drawArc(Canvas canvas0, Paint paint0, float f, float f1, int v, int v1, int v2) {
        float f2 = f1 >= f ? f1 - f : f1 + 1.0f - f;
        if(this.totalTrackLengthFraction < 1.0f) {
            float f3 = f % 1.0f + f2;
            if(f3 > 1.0f) {
                this.drawArc(canvas0, paint0, f % 1.0f, 1.0f, v, v1, 0);
                this.drawArc(canvas0, paint0, 1.0f, f3, v, 0, v2);
                return;
            }
        }
        float f4 = (float)Math.toDegrees(this.displayedCornerRadius / this.adjustedRadius);
        if(f % 1.0f == 0.0f && f2 >= 0.99f) {
            f2 += (f2 - 0.99f) * (f4 * 2.0f / 360.0f) / 0.01f;
        }
        float f5 = (1.0f - f % 1.0f) * (1.0f - this.totalTrackLengthFraction) + f % 1.0f * 1.0f;
        float f6 = (1.0f - f2) * 0.0f + f2 * this.totalTrackLengthFraction;
        float f7 = (float)Math.toDegrees(((float)v1) / this.adjustedRadius);
        float f8 = f6 * 360.0f - f7 - ((float)Math.toDegrees(((float)v2) / this.adjustedRadius));
        float f9 = f5 * 360.0f + f7;
        if(f8 > 0.0f) {
            paint0.setAntiAlias(true);
            paint0.setColor(v);
            paint0.setStrokeWidth(this.displayedTrackThickness);
            if(f8 < f4 * 2.0f) {
                float f10 = f8 / (f4 * 2.0f);
                paint0.setStyle(Paint.Style.FILL);
                this.drawRoundedBlock(canvas0, paint0, f9 + f4 * f10, this.displayedCornerRadius * 2.0f, this.displayedTrackThickness, f10);
                return;
            }
            RectF rectF0 = new RectF(-this.adjustedRadius, -this.adjustedRadius, this.adjustedRadius, this.adjustedRadius);
            paint0.setStyle(Paint.Style.STROKE);
            paint0.setStrokeCap((this.useStrokeCap ? Paint.Cap.ROUND : Paint.Cap.BUTT));
            float f11 = f9 + f4;
            canvas0.drawArc(rectF0, f11, f8 - f4 * 2.0f, false, paint0);
            if(!this.useStrokeCap && this.displayedCornerRadius > 0.0f) {
                paint0.setStyle(Paint.Style.FILL);
                this.drawRoundedBlock(canvas0, paint0, f11, this.displayedCornerRadius * 2.0f, this.displayedTrackThickness);
                this.drawRoundedBlock(canvas0, paint0, f9 + f8 - f4, this.displayedCornerRadius * 2.0f, this.displayedTrackThickness);
            }
        }
    }

    private void drawRoundedBlock(Canvas canvas0, Paint paint0, float f, float f1, float f2) {
        this.drawRoundedBlock(canvas0, paint0, f, f1, f2, 1.0f);
    }

    private void drawRoundedBlock(Canvas canvas0, Paint paint0, float f, float f1, float f2, float f3) {
        float f4 = (float)(((int)Math.min(f2, this.displayedTrackThickness)));
        float f5 = Math.min(f1 / 2.0f, this.displayedCornerRadius * f4 / this.displayedTrackThickness);
        RectF rectF0 = new RectF(-f4 / 2.0f, -f1 / 2.0f, f4 / 2.0f, f1 / 2.0f);
        canvas0.save();
        canvas0.translate(((float)(((double)this.adjustedRadius) * Math.cos(Math.toRadians(f)))), ((float)(((double)this.adjustedRadius) * Math.sin(Math.toRadians(f)))));
        canvas0.rotate(f);
        canvas0.scale(f3, f3);
        canvas0.drawRoundRect(rectF0, f5, f5, paint0);
        canvas0.restore();
    }

    @Override  // com.google.android.material.progressindicator.DrawingDelegate
    void drawStopIndicator(Canvas canvas0, Paint paint0, int v, int v1) {
    }

    @Override  // com.google.android.material.progressindicator.DrawingDelegate
    void fillIndicator(Canvas canvas0, Paint paint0, ActiveIndicator drawingDelegate$ActiveIndicator0, int v) {
        int v1 = MaterialColors.compositeARGBWithAlpha(drawingDelegate$ActiveIndicator0.color, v);
        this.drawArc(canvas0, paint0, drawingDelegate$ActiveIndicator0.startFraction, drawingDelegate$ActiveIndicator0.endFraction, v1, drawingDelegate$ActiveIndicator0.gapSize, drawingDelegate$ActiveIndicator0.gapSize);
    }

    @Override  // com.google.android.material.progressindicator.DrawingDelegate
    void fillTrack(Canvas canvas0, Paint paint0, float f, float f1, int v, int v1, int v2) {
        this.drawArc(canvas0, paint0, f, f1, MaterialColors.compositeARGBWithAlpha(v, v1), v2, v2);
    }

    @Override  // com.google.android.material.progressindicator.DrawingDelegate
    int getPreferredHeight() {
        return this.getSize();
    }

    @Override  // com.google.android.material.progressindicator.DrawingDelegate
    int getPreferredWidth() {
        return this.getSize();
    }

    private int getSize() {
        return ((CircularProgressIndicatorSpec)this.spec).indicatorSize + ((CircularProgressIndicatorSpec)this.spec).indicatorInset * 2;
    }
}

