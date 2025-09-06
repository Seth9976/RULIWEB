package com.google.android.material.shadow;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.graphics.Shader.TileMode;
import androidx.core.graphics.ColorUtils;

public class ShadowRenderer {
    private static final int COLOR_ALPHA_END = 0;
    private static final int COLOR_ALPHA_MIDDLE = 20;
    private static final int COLOR_ALPHA_START = 68;
    private static final int[] cornerColors;
    private static final float[] cornerPositions;
    private final Paint cornerShadowPaint;
    private static final int[] edgeColors;
    private static final float[] edgePositions;
    private final Paint edgeShadowPaint;
    private final Path scratch;
    private int shadowEndColor;
    private int shadowMiddleColor;
    private final Paint shadowPaint;
    private int shadowStartColor;
    private final Paint transparentPaint;

    static {
        ShadowRenderer.edgeColors = new int[3];
        ShadowRenderer.edgePositions = new float[]{0.0f, 0.5f, 1.0f};
        ShadowRenderer.cornerColors = new int[4];
        ShadowRenderer.cornerPositions = new float[]{0.0f, 0.0f, 0.5f, 1.0f};
    }

    public ShadowRenderer() {
        this(0xFF000000);
    }

    public ShadowRenderer(int v) {
        this.scratch = new Path();
        Paint paint0 = new Paint();
        this.transparentPaint = paint0;
        this.shadowPaint = new Paint();
        this.setShadowColor(v);
        paint0.setColor(0);
        Paint paint1 = new Paint(4);
        this.cornerShadowPaint = paint1;
        paint1.setStyle(Paint.Style.FILL);
        this.edgeShadowPaint = new Paint(paint1);
    }

    public void drawCornerShadow(Canvas canvas0, Matrix matrix0, RectF rectF0, int v, float f, float f1) {
        Path path0 = this.scratch;
        if(f1 < 0.0f) {
            ShadowRenderer.cornerColors[0] = 0;
            ShadowRenderer.cornerColors[1] = this.shadowEndColor;
            ShadowRenderer.cornerColors[2] = this.shadowMiddleColor;
            ShadowRenderer.cornerColors[3] = this.shadowStartColor;
        }
        else {
            path0.rewind();
            path0.moveTo(rectF0.centerX(), rectF0.centerY());
            path0.arcTo(rectF0, f, f1);
            path0.close();
            float f2 = (float)(-v);
            rectF0.inset(f2, f2);
            ShadowRenderer.cornerColors[0] = 0;
            ShadowRenderer.cornerColors[1] = this.shadowStartColor;
            ShadowRenderer.cornerColors[2] = this.shadowMiddleColor;
            ShadowRenderer.cornerColors[3] = this.shadowEndColor;
        }
        float f3 = rectF0.width();
        if(f3 / 2.0f <= 0.0f) {
            return;
        }
        float f4 = 1.0f - ((float)v) / (f3 / 2.0f);
        ShadowRenderer.cornerPositions[1] = f4;
        ShadowRenderer.cornerPositions[2] = (1.0f - f4) / 2.0f + f4;
        RadialGradient radialGradient0 = new RadialGradient(rectF0.centerX(), rectF0.centerY(), f3 / 2.0f, ShadowRenderer.cornerColors, ShadowRenderer.cornerPositions, Shader.TileMode.CLAMP);
        this.cornerShadowPaint.setShader(radialGradient0);
        canvas0.save();
        canvas0.concat(matrix0);
        canvas0.scale(1.0f, rectF0.height() / rectF0.width());
        if(f1 >= 0.0f) {
            canvas0.clipPath(path0, Region.Op.DIFFERENCE);
            canvas0.drawPath(path0, this.transparentPaint);
        }
        canvas0.drawArc(rectF0, f, f1, true, this.cornerShadowPaint);
        canvas0.restore();
    }

    public void drawEdgeShadow(Canvas canvas0, Matrix matrix0, RectF rectF0, int v) {
        rectF0.bottom += (float)v;
        rectF0.offset(0.0f, ((float)(-v)));
        ShadowRenderer.edgeColors[0] = this.shadowEndColor;
        ShadowRenderer.edgeColors[1] = this.shadowMiddleColor;
        ShadowRenderer.edgeColors[2] = this.shadowStartColor;
        LinearGradient linearGradient0 = new LinearGradient(rectF0.left, rectF0.top, rectF0.left, rectF0.bottom, ShadowRenderer.edgeColors, ShadowRenderer.edgePositions, Shader.TileMode.CLAMP);
        this.edgeShadowPaint.setShader(linearGradient0);
        canvas0.save();
        canvas0.concat(matrix0);
        canvas0.drawRect(rectF0, this.edgeShadowPaint);
        canvas0.restore();
    }

    public void drawInnerCornerShadow(Canvas canvas0, Matrix matrix0, RectF rectF0, int v, float f, float f1, float[] arr_f) {
        if(f1 > 0.0f) {
            f += f1;
            f1 = -f1;
        }
        this.drawCornerShadow(canvas0, matrix0, rectF0, v, f, f1);
        this.scratch.rewind();
        this.scratch.moveTo(arr_f[0], arr_f[1]);
        this.scratch.arcTo(rectF0, f, f1);
        this.scratch.close();
        canvas0.save();
        canvas0.concat(matrix0);
        canvas0.scale(1.0f, rectF0.height() / rectF0.width());
        canvas0.drawPath(this.scratch, this.transparentPaint);
        canvas0.drawPath(this.scratch, this.shadowPaint);
        canvas0.restore();
    }

    public Paint getShadowPaint() {
        return this.shadowPaint;
    }

    public void setShadowColor(int v) {
        this.shadowStartColor = ColorUtils.setAlphaComponent(v, 68);
        this.shadowMiddleColor = ColorUtils.setAlphaComponent(v, 20);
        this.shadowEndColor = ColorUtils.setAlphaComponent(v, 0);
        this.shadowPaint.setColor(this.shadowStartColor);
    }
}

