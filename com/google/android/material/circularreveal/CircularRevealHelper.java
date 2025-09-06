package com.google.android.material.circularreveal;

import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path.Direction;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.material.math.MathUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CircularRevealHelper {
    public interface Delegate {
        void actualDraw(Canvas arg1);

        boolean actualIsOpaque();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Strategy {
    }

    public static final int BITMAP_SHADER = 0;
    public static final int CLIP_PATH = 1;
    private static final boolean DEBUG = false;
    public static final int REVEAL_ANIMATOR = 2;
    public static final int STRATEGY;
    private boolean buildingCircularRevealCache;
    private Paint debugPaint;
    private final Delegate delegate;
    private boolean hasCircularRevealCache;
    private Drawable overlayDrawable;
    private RevealInfo revealInfo;
    private final Paint revealPaint;
    private final Path revealPath;
    private final Paint scrimPaint;
    private final View view;

    static {
        CircularRevealHelper.STRATEGY = 2;
    }

    public CircularRevealHelper(Delegate circularRevealHelper$Delegate0) {
        this.delegate = circularRevealHelper$Delegate0;
        this.view = (View)circularRevealHelper$Delegate0;
        ((View)circularRevealHelper$Delegate0).setWillNotDraw(false);
        this.revealPath = new Path();
        this.revealPaint = new Paint(7);
        Paint paint0 = new Paint(1);
        this.scrimPaint = paint0;
        paint0.setColor(0);
    }

    public void buildCircularRevealCache() {
        if(CircularRevealHelper.STRATEGY == 0) {
            this.buildingCircularRevealCache = true;
            this.hasCircularRevealCache = false;
            this.view.buildDrawingCache();
            Bitmap bitmap0 = this.view.getDrawingCache();
            if(bitmap0 == null && this.view.getWidth() != 0 && this.view.getHeight() != 0) {
                bitmap0 = Bitmap.createBitmap(this.view.getWidth(), this.view.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas0 = new Canvas(bitmap0);
                this.view.draw(canvas0);
            }
            if(bitmap0 != null) {
                BitmapShader bitmapShader0 = new BitmapShader(bitmap0, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                this.revealPaint.setShader(bitmapShader0);
            }
            this.buildingCircularRevealCache = false;
            this.hasCircularRevealCache = true;
        }
    }

    public void destroyCircularRevealCache() {
        if(CircularRevealHelper.STRATEGY == 0) {
            this.hasCircularRevealCache = false;
            this.view.destroyDrawingCache();
            this.revealPaint.setShader(null);
            this.view.invalidate();
        }
    }

    public void draw(Canvas canvas0) {
        if(this.shouldDrawCircularReveal()) {
            int v = CircularRevealHelper.STRATEGY;
            switch(v) {
                case 0: {
                    canvas0.drawCircle(this.revealInfo.centerX, this.revealInfo.centerY, this.revealInfo.radius, this.revealPaint);
                    if(this.shouldDrawScrim()) {
                        canvas0.drawCircle(this.revealInfo.centerX, this.revealInfo.centerY, this.revealInfo.radius, this.scrimPaint);
                    }
                    break;
                }
                case 1: {
                    int v1 = canvas0.save();
                    canvas0.clipPath(this.revealPath);
                    this.delegate.actualDraw(canvas0);
                    if(this.shouldDrawScrim()) {
                        canvas0.drawRect(0.0f, 0.0f, ((float)this.view.getWidth()), ((float)this.view.getHeight()), this.scrimPaint);
                    }
                    canvas0.restoreToCount(v1);
                    break;
                }
                case 2: {
                    this.delegate.actualDraw(canvas0);
                    if(this.shouldDrawScrim()) {
                        canvas0.drawRect(0.0f, 0.0f, ((float)this.view.getWidth()), ((float)this.view.getHeight()), this.scrimPaint);
                    }
                    break;
                }
                default: {
                    throw new IllegalStateException("Unsupported strategy " + v);
                }
            }
        }
        else {
            this.delegate.actualDraw(canvas0);
            if(this.shouldDrawScrim()) {
                canvas0.drawRect(0.0f, 0.0f, ((float)this.view.getWidth()), ((float)this.view.getHeight()), this.scrimPaint);
            }
        }
        this.drawOverlayDrawable(canvas0);
    }

    private void drawDebugCircle(Canvas canvas0, int v, float f) {
        this.debugPaint.setColor(v);
        this.debugPaint.setStrokeWidth(f);
        canvas0.drawCircle(this.revealInfo.centerX, this.revealInfo.centerY, this.revealInfo.radius - f / 2.0f, this.debugPaint);
    }

    private void drawDebugMode(Canvas canvas0) {
        this.delegate.actualDraw(canvas0);
        if(this.shouldDrawScrim()) {
            canvas0.drawCircle(this.revealInfo.centerX, this.revealInfo.centerY, this.revealInfo.radius, this.scrimPaint);
        }
        if(this.shouldDrawCircularReveal()) {
            this.drawDebugCircle(canvas0, 0xFF000000, 10.0f);
            this.drawDebugCircle(canvas0, 0xFFFF0000, 5.0f);
        }
        this.drawOverlayDrawable(canvas0);
    }

    private void drawOverlayDrawable(Canvas canvas0) {
        if(this.shouldDrawOverlayDrawable()) {
            Rect rect0 = this.overlayDrawable.getBounds();
            float f = this.revealInfo.centerX - ((float)rect0.width()) / 2.0f;
            float f1 = this.revealInfo.centerY - ((float)rect0.height()) / 2.0f;
            canvas0.translate(f, f1);
            this.overlayDrawable.draw(canvas0);
            canvas0.translate(-f, -f1);
        }
    }

    public Drawable getCircularRevealOverlayDrawable() {
        return this.overlayDrawable;
    }

    public int getCircularRevealScrimColor() {
        return this.scrimPaint.getColor();
    }

    private float getDistanceToFurthestCorner(RevealInfo circularRevealWidget$RevealInfo0) {
        return MathUtils.distanceToFurthestCorner(circularRevealWidget$RevealInfo0.centerX, circularRevealWidget$RevealInfo0.centerY, 0.0f, 0.0f, this.view.getWidth(), this.view.getHeight());
    }

    public RevealInfo getRevealInfo() {
        if(this.revealInfo == null) {
            return null;
        }
        RevealInfo circularRevealWidget$RevealInfo0 = new RevealInfo(this.revealInfo);
        if(circularRevealWidget$RevealInfo0.isInvalid()) {
            circularRevealWidget$RevealInfo0.radius = this.getDistanceToFurthestCorner(circularRevealWidget$RevealInfo0);
        }
        return circularRevealWidget$RevealInfo0;
    }

    private void invalidateRevealInfo() {
        if(CircularRevealHelper.STRATEGY == 1) {
            this.revealPath.rewind();
            RevealInfo circularRevealWidget$RevealInfo0 = this.revealInfo;
            if(circularRevealWidget$RevealInfo0 != null) {
                this.revealPath.addCircle(circularRevealWidget$RevealInfo0.centerX, this.revealInfo.centerY, this.revealInfo.radius, Path.Direction.CW);
            }
        }
        this.view.invalidate();
    }

    // 去混淆评级： 低(20)
    public boolean isOpaque() {
        return this.delegate.actualIsOpaque() && !this.shouldDrawCircularReveal();
    }

    public void setCircularRevealOverlayDrawable(Drawable drawable0) {
        this.overlayDrawable = drawable0;
        this.view.invalidate();
    }

    public void setCircularRevealScrimColor(int v) {
        this.scrimPaint.setColor(v);
        this.view.invalidate();
    }

    public void setRevealInfo(RevealInfo circularRevealWidget$RevealInfo0) {
        if(circularRevealWidget$RevealInfo0 == null) {
            this.revealInfo = null;
        }
        else {
            RevealInfo circularRevealWidget$RevealInfo1 = this.revealInfo;
            if(circularRevealWidget$RevealInfo1 == null) {
                this.revealInfo = new RevealInfo(circularRevealWidget$RevealInfo0);
            }
            else {
                circularRevealWidget$RevealInfo1.set(circularRevealWidget$RevealInfo0);
            }
            if(MathUtils.geq(circularRevealWidget$RevealInfo0.radius, this.getDistanceToFurthestCorner(circularRevealWidget$RevealInfo0), 0.0001f)) {
                this.revealInfo.radius = 3.402823E+38f;
            }
        }
        this.invalidateRevealInfo();
    }

    private boolean shouldDrawCircularReveal() {
        int v = this.revealInfo == null || this.revealInfo.isInvalid() ? 1 : 0;
        return CircularRevealHelper.STRATEGY == 0 ? v == 0 && this.hasCircularRevealCache : v ^ 1;
    }

    private boolean shouldDrawOverlayDrawable() {
        return !this.buildingCircularRevealCache && this.overlayDrawable != null && this.revealInfo != null;
    }

    private boolean shouldDrawScrim() {
        return !this.buildingCircularRevealCache && Color.alpha(this.scrimPaint.getColor()) != 0;
    }
}

