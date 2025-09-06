package com.google.android.material.progressindicator;

import android.graphics.Canvas;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.graphics.Path.Direction;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.core.math.MathUtils;
import com.google.android.material.color.MaterialColors;

final class LinearDrawingDelegate extends DrawingDelegate {
    private float displayedCornerRadius;
    private float displayedTrackThickness;
    private float totalTrackLengthFraction;
    private float trackLength;
    private boolean useStrokeCap;

    LinearDrawingDelegate(LinearProgressIndicatorSpec linearProgressIndicatorSpec0) {
        super(linearProgressIndicatorSpec0);
        this.trackLength = 300.0f;
    }

    @Override  // com.google.android.material.progressindicator.DrawingDelegate
    void adjustCanvas(Canvas canvas0, Rect rect0, float f, boolean z, boolean z1) {
        this.trackLength = (float)rect0.width();
        float f1 = (float)((LinearProgressIndicatorSpec)this.spec).trackThickness;
        canvas0.translate(((float)rect0.left) + ((float)rect0.width()) / 2.0f, ((float)rect0.top) + ((float)rect0.height()) / 2.0f + Math.max(0.0f, (((float)rect0.height()) - f1) / 2.0f));
        if(((LinearProgressIndicatorSpec)this.spec).drawHorizontallyInverse) {
            canvas0.scale(-1.0f, 1.0f);
        }
        canvas0.clipRect(-(this.trackLength / 2.0f), -(f1 / 2.0f), this.trackLength / 2.0f, f1 / 2.0f);
        this.useStrokeCap = ((LinearProgressIndicatorSpec)this.spec).trackThickness / 2 == ((LinearProgressIndicatorSpec)this.spec).trackCornerRadius;
        this.displayedTrackThickness = ((float)((LinearProgressIndicatorSpec)this.spec).trackThickness) * f;
        this.displayedCornerRadius = ((float)Math.min(((LinearProgressIndicatorSpec)this.spec).trackThickness / 2, ((LinearProgressIndicatorSpec)this.spec).trackCornerRadius)) * f;
        if(z || z1) {
            if(z && ((LinearProgressIndicatorSpec)this.spec).showAnimationBehavior == 2 || z1 && ((LinearProgressIndicatorSpec)this.spec).hideAnimationBehavior == 1) {
                canvas0.scale(1.0f, -1.0f);
            }
            if(z || z1 && ((LinearProgressIndicatorSpec)this.spec).hideAnimationBehavior != 3) {
                canvas0.translate(0.0f, ((float)((LinearProgressIndicatorSpec)this.spec).trackThickness) * (1.0f - f) / 2.0f);
            }
        }
        if(z1 && ((LinearProgressIndicatorSpec)this.spec).hideAnimationBehavior == 3) {
            this.totalTrackLengthFraction = f;
            return;
        }
        this.totalTrackLengthFraction = 1.0f;
    }

    private void drawLine(Canvas canvas0, Paint paint0, float f, float f1, int v, int v1, int v2) {
        float f10;
        float f2 = (1.0f - MathUtils.clamp(f, 0.0f, 1.0f)) * (1.0f - this.totalTrackLengthFraction) + MathUtils.clamp(f, 0.0f, 1.0f) * 1.0f;
        float f3 = (1.0f - MathUtils.clamp(f1, 0.0f, 1.0f)) * (1.0f - this.totalTrackLengthFraction) + MathUtils.clamp(f1, 0.0f, 1.0f) * 1.0f;
        int v3 = (int)(f2 * this.trackLength + ((float)(((int)(((float)v1) * MathUtils.clamp(f2, 0.0f, 0.01f) / 0.01f)))));
        int v4 = (int)(f3 * this.trackLength - ((float)(((int)(((float)v2) * (1.0f - MathUtils.clamp(f3, 0.99f, 1.0f)) / 0.01f)))));
        float f4 = -this.trackLength / 2.0f;
        if(v3 <= v4) {
            float f5 = ((float)v3) + this.displayedCornerRadius;
            float f6 = ((float)v4) - this.displayedCornerRadius;
            float f7 = this.displayedCornerRadius * 2.0f;
            paint0.setColor(v);
            paint0.setAntiAlias(true);
            paint0.setStrokeWidth(this.displayedTrackThickness);
            if(f5 >= f6) {
                this.drawRoundedBlock(canvas0, paint0, new PointF(f5 + f4, 0.0f), new PointF(f6 + f4, 0.0f), f7, this.displayedTrackThickness);
                return;
            }
            paint0.setStyle(Paint.Style.STROKE);
            paint0.setStrokeCap((this.useStrokeCap ? Paint.Cap.ROUND : Paint.Cap.BUTT));
            float f8 = f5 + f4;
            float f9 = f6 + f4;
            canvas0.drawLine(f8, 0.0f, f9, 0.0f, paint0);
            if(!this.useStrokeCap && this.displayedCornerRadius > 0.0f) {
                paint0.setStyle(Paint.Style.FILL);
                if(f5 > 0.0f) {
                    f10 = f7;
                    this.drawRoundedBlock(canvas0, paint0, new PointF(f8, 0.0f), f10, this.displayedTrackThickness);
                }
                else {
                    f10 = f7;
                }
                if(f6 < this.trackLength) {
                    this.drawRoundedBlock(canvas0, paint0, new PointF(f9, 0.0f), f10, this.displayedTrackThickness);
                }
            }
        }
    }

    private void drawRoundedBlock(Canvas canvas0, Paint paint0, PointF pointF0, float f, float f1) {
        this.drawRoundedBlock(canvas0, paint0, pointF0, null, f, f1);
    }

    private void drawRoundedBlock(Canvas canvas0, Paint paint0, PointF pointF0, PointF pointF1, float f, float f1) {
        float f2 = Math.min(f1, this.displayedTrackThickness);
        float f3 = Math.min(f / 2.0f, this.displayedCornerRadius * f2 / this.displayedTrackThickness);
        RectF rectF0 = new RectF(-f / 2.0f, -f2 / 2.0f, f / 2.0f, f2 / 2.0f);
        paint0.setStyle(Paint.Style.FILL);
        canvas0.save();
        if(pointF1 != null) {
            canvas0.translate(pointF1.x, pointF1.y);
            Path path0 = new Path();
            path0.addRoundRect(rectF0, f3, f3, Path.Direction.CCW);
            canvas0.clipPath(path0);
            canvas0.translate(-pointF1.x, -pointF1.y);
        }
        canvas0.translate(pointF0.x, pointF0.y);
        canvas0.drawRoundRect(rectF0, f3, f3, paint0);
        canvas0.restore();
    }

    @Override  // com.google.android.material.progressindicator.DrawingDelegate
    void drawStopIndicator(Canvas canvas0, Paint paint0, int v, int v1) {
        int v2 = MaterialColors.compositeARGBWithAlpha(v, v1);
        if(((LinearProgressIndicatorSpec)this.spec).trackStopIndicatorSize > 0 && v2 != 0) {
            paint0.setStyle(Paint.Style.FILL);
            paint0.setColor(v2);
            this.drawRoundedBlock(canvas0, paint0, new PointF(this.trackLength / 2.0f - this.displayedTrackThickness / 2.0f, 0.0f), ((float)((LinearProgressIndicatorSpec)this.spec).trackStopIndicatorSize), ((float)((LinearProgressIndicatorSpec)this.spec).trackStopIndicatorSize));
        }
    }

    @Override  // com.google.android.material.progressindicator.DrawingDelegate
    void fillIndicator(Canvas canvas0, Paint paint0, ActiveIndicator drawingDelegate$ActiveIndicator0, int v) {
        int v1 = MaterialColors.compositeARGBWithAlpha(drawingDelegate$ActiveIndicator0.color, v);
        this.drawLine(canvas0, paint0, drawingDelegate$ActiveIndicator0.startFraction, drawingDelegate$ActiveIndicator0.endFraction, v1, drawingDelegate$ActiveIndicator0.gapSize, drawingDelegate$ActiveIndicator0.gapSize);
    }

    @Override  // com.google.android.material.progressindicator.DrawingDelegate
    void fillTrack(Canvas canvas0, Paint paint0, float f, float f1, int v, int v1, int v2) {
        this.drawLine(canvas0, paint0, f, f1, MaterialColors.compositeARGBWithAlpha(v, v1), v2, v2);
    }

    @Override  // com.google.android.material.progressindicator.DrawingDelegate
    int getPreferredHeight() {
        return ((LinearProgressIndicatorSpec)this.spec).trackThickness;
    }

    @Override  // com.google.android.material.progressindicator.DrawingDelegate
    int getPreferredWidth() {
        return -1;
    }
}

