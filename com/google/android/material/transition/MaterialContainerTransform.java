package com.google.android.material.transition;

import android.animation.Animator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.core.util.Preconditions;
import androidx.core.view.ViewCompat;
import androidx.transition.ArcMotion;
import androidx.transition.PathMotion;
import androidx.transition.Transition;
import androidx.transition.TransitionValues;
import com.google.android.material.R.attr;
import com.google.android.material.R.id;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.canvas.CanvasCompat.CanvasOperation;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class MaterialContainerTransform extends Transition {
    @Retention(RetentionPolicy.SOURCE)
    public @interface FadeMode {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FitMode {
    }

    public static class ProgressThresholds {
        private final float end;
        private final float start;

        public ProgressThresholds(float f, float f1) {
            this.start = f;
            this.end = f1;
        }

        public float getEnd() {
            return this.end;
        }

        public float getStart() {
            return this.start;
        }
    }

    static class ProgressThresholdsGroup {
        private final ProgressThresholds fade;
        private final ProgressThresholds scale;
        private final ProgressThresholds scaleMask;
        private final ProgressThresholds shapeMask;

        private ProgressThresholdsGroup(ProgressThresholds materialContainerTransform$ProgressThresholds0, ProgressThresholds materialContainerTransform$ProgressThresholds1, ProgressThresholds materialContainerTransform$ProgressThresholds2, ProgressThresholds materialContainerTransform$ProgressThresholds3) {
            this.fade = materialContainerTransform$ProgressThresholds0;
            this.scale = materialContainerTransform$ProgressThresholds1;
            this.scaleMask = materialContainerTransform$ProgressThresholds2;
            this.shapeMask = materialContainerTransform$ProgressThresholds3;
        }

        ProgressThresholdsGroup(ProgressThresholds materialContainerTransform$ProgressThresholds0, ProgressThresholds materialContainerTransform$ProgressThresholds1, ProgressThresholds materialContainerTransform$ProgressThresholds2, ProgressThresholds materialContainerTransform$ProgressThresholds3, com.google.android.material.transition.MaterialContainerTransform.1 materialContainerTransform$10) {
            this(materialContainerTransform$ProgressThresholds0, materialContainerTransform$ProgressThresholds1, materialContainerTransform$ProgressThresholds2, materialContainerTransform$ProgressThresholds3);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TransitionDirection {
    }

    static final class TransitionDrawable extends Drawable {
        private static final int COMPAT_SHADOW_COLOR = 0xFF888888;
        private static final int SHADOW_COLOR = 0x2D000000;
        private static final float SHADOW_DX_MULTIPLIER_ADJUSTMENT = 0.3f;
        private static final float SHADOW_DY_MULTIPLIER_ADJUSTMENT = 1.5f;
        private final MaterialShapeDrawable compatShadowDrawable;
        private final Paint containerPaint;
        private float currentElevation;
        private float currentElevationDy;
        private final RectF currentEndBounds;
        private final RectF currentEndBoundsMasked;
        private RectF currentMaskBounds;
        private final RectF currentStartBounds;
        private final RectF currentStartBoundsMasked;
        private final Paint debugPaint;
        private final Path debugPath;
        private final float displayHeight;
        private final float displayWidth;
        private final boolean drawDebugEnabled;
        private final boolean elevationShadowEnabled;
        private final RectF endBounds;
        private final Paint endContainerPaint;
        private final float endElevation;
        private final ShapeAppearanceModel endShapeAppearanceModel;
        private final View endView;
        private final boolean entering;
        private final FadeModeEvaluator fadeModeEvaluator;
        private FadeModeResult fadeModeResult;
        private final FitModeEvaluator fitModeEvaluator;
        private FitModeResult fitModeResult;
        private final MaskEvaluator maskEvaluator;
        private final float motionPathLength;
        private final PathMeasure motionPathMeasure;
        private final float[] motionPathPosition;
        private float progress;
        private final ProgressThresholdsGroup progressThresholds;
        private final Paint scrimPaint;
        private final Paint shadowPaint;
        private final RectF startBounds;
        private final Paint startContainerPaint;
        private final float startElevation;
        private final ShapeAppearanceModel startShapeAppearanceModel;
        private final View startView;

        private TransitionDrawable(PathMotion pathMotion0, View view0, RectF rectF0, ShapeAppearanceModel shapeAppearanceModel0, float f, View view1, RectF rectF1, ShapeAppearanceModel shapeAppearanceModel1, float f1, int v, int v1, int v2, int v3, boolean z, boolean z1, FadeModeEvaluator fadeModeEvaluator0, FitModeEvaluator fitModeEvaluator0, ProgressThresholdsGroup materialContainerTransform$ProgressThresholdsGroup0, boolean z2) {
            Paint paint0 = new Paint();
            this.containerPaint = paint0;
            Paint paint1 = new Paint();
            this.startContainerPaint = paint1;
            Paint paint2 = new Paint();
            this.endContainerPaint = paint2;
            this.shadowPaint = new Paint();
            Paint paint3 = new Paint();
            this.scrimPaint = paint3;
            this.maskEvaluator = new MaskEvaluator();
            float[] arr_f = new float[2];
            this.motionPathPosition = arr_f;
            MaterialShapeDrawable materialShapeDrawable0 = new MaterialShapeDrawable();
            this.compatShadowDrawable = materialShapeDrawable0;
            Paint paint4 = new Paint();
            this.debugPaint = paint4;
            this.debugPath = new Path();
            this.startView = view0;
            this.startBounds = rectF0;
            this.startShapeAppearanceModel = shapeAppearanceModel0;
            this.startElevation = f;
            this.endView = view1;
            this.endBounds = rectF1;
            this.endShapeAppearanceModel = shapeAppearanceModel1;
            this.endElevation = f1;
            this.entering = z;
            this.elevationShadowEnabled = z1;
            this.fadeModeEvaluator = fadeModeEvaluator0;
            this.fitModeEvaluator = fitModeEvaluator0;
            this.progressThresholds = materialContainerTransform$ProgressThresholdsGroup0;
            this.drawDebugEnabled = z2;
            WindowManager windowManager0 = (WindowManager)view0.getContext().getSystemService("window");
            DisplayMetrics displayMetrics0 = new DisplayMetrics();
            windowManager0.getDefaultDisplay().getMetrics(displayMetrics0);
            this.displayWidth = (float)displayMetrics0.widthPixels;
            this.displayHeight = (float)displayMetrics0.heightPixels;
            paint0.setColor(v);
            paint1.setColor(v1);
            paint2.setColor(v2);
            materialShapeDrawable0.setFillColor(ColorStateList.valueOf(0));
            materialShapeDrawable0.setShadowCompatibilityMode(2);
            materialShapeDrawable0.setShadowBitmapDrawingEnable(false);
            materialShapeDrawable0.setShadowColor(0xFF888888);
            RectF rectF2 = new RectF(rectF0);
            this.currentStartBounds = rectF2;
            this.currentStartBoundsMasked = new RectF(rectF2);
            RectF rectF3 = new RectF(rectF2);
            this.currentEndBounds = rectF3;
            this.currentEndBoundsMasked = new RectF(rectF3);
            PointF pointF0 = TransitionDrawable.getMotionPathPoint(rectF0);
            PointF pointF1 = TransitionDrawable.getMotionPathPoint(rectF1);
            PathMeasure pathMeasure0 = new PathMeasure(pathMotion0.getPath(pointF0.x, pointF0.y, pointF1.x, pointF1.y), false);
            this.motionPathMeasure = pathMeasure0;
            this.motionPathLength = pathMeasure0.getLength();
            arr_f[0] = rectF0.centerX();
            arr_f[1] = rectF0.top;
            paint3.setStyle(Paint.Style.FILL);
            paint3.setShader(TransitionUtils.createColorShader(v3));
            paint4.setStyle(Paint.Style.STROKE);
            paint4.setStrokeWidth(10.0f);
            this.updateProgress(0.0f);
        }

        TransitionDrawable(PathMotion pathMotion0, View view0, RectF rectF0, ShapeAppearanceModel shapeAppearanceModel0, float f, View view1, RectF rectF1, ShapeAppearanceModel shapeAppearanceModel1, float f1, int v, int v1, int v2, int v3, boolean z, boolean z1, FadeModeEvaluator fadeModeEvaluator0, FitModeEvaluator fitModeEvaluator0, ProgressThresholdsGroup materialContainerTransform$ProgressThresholdsGroup0, boolean z2, com.google.android.material.transition.MaterialContainerTransform.1 materialContainerTransform$10) {
            this(pathMotion0, view0, rectF0, shapeAppearanceModel0, f, view1, rectF1, shapeAppearanceModel1, f1, v, v1, v2, v3, z, z1, fadeModeEvaluator0, fitModeEvaluator0, materialContainerTransform$ProgressThresholdsGroup0, z2);
        }

        static void access$200(TransitionDrawable materialContainerTransform$TransitionDrawable0, float f) {
            materialContainerTransform$TransitionDrawable0.setProgress(f);
        }

        private static float calculateElevationDxMultiplier(RectF rectF0, float f) {
            return (rectF0.centerX() / (f / 2.0f) - 1.0f) * 0.3f;
        }

        private static float calculateElevationDyMultiplier(RectF rectF0, float f) {
            return rectF0.centerY() / f * 1.5f;
        }

        @Override  // android.graphics.drawable.Drawable
        public void draw(Canvas canvas0) {
            if(this.scrimPaint.getAlpha() > 0) {
                canvas0.drawRect(this.getBounds(), this.scrimPaint);
            }
            int v = this.drawDebugEnabled ? canvas0.save() : -1;
            if(this.elevationShadowEnabled && this.currentElevation > 0.0f) {
                this.drawElevationShadow(canvas0);
            }
            this.maskEvaluator.clip(canvas0);
            this.maybeDrawContainerColor(canvas0, this.containerPaint);
            if(this.fadeModeResult.endOnTop) {
                this.drawStartView(canvas0);
                this.drawEndView(canvas0);
            }
            else {
                this.drawEndView(canvas0);
                this.drawStartView(canvas0);
            }
            if(this.drawDebugEnabled) {
                canvas0.restoreToCount(v);
                this.drawDebugCumulativePath(canvas0, this.currentStartBounds, this.debugPath, 0xFFFF00FF);
                this.drawDebugRect(canvas0, this.currentStartBoundsMasked, 0xFFFFFF00);
                this.drawDebugRect(canvas0, this.currentStartBounds, 0xFF00FF00);
                this.drawDebugRect(canvas0, this.currentEndBoundsMasked, 0xFF00FFFF);
                this.drawDebugRect(canvas0, this.currentEndBounds, 0xFF0000FF);
            }
        }

        private void drawDebugCumulativePath(Canvas canvas0, RectF rectF0, Path path0, int v) {
            PointF pointF0 = TransitionDrawable.getMotionPathPoint(rectF0);
            if(this.progress == 0.0f) {
                path0.reset();
                path0.moveTo(pointF0.x, pointF0.y);
                return;
            }
            path0.lineTo(pointF0.x, pointF0.y);
            this.debugPaint.setColor(v);
            canvas0.drawPath(path0, this.debugPaint);
        }

        private void drawDebugRect(Canvas canvas0, RectF rectF0, int v) {
            this.debugPaint.setColor(v);
            canvas0.drawRect(rectF0, this.debugPaint);
        }

        private void drawElevationShadow(Canvas canvas0) {
            canvas0.save();
            canvas0.clipPath(this.maskEvaluator.getPath(), Region.Op.DIFFERENCE);
            if(Build.VERSION.SDK_INT > 28) {
                this.drawElevationShadowWithPaintShadowLayer(canvas0);
            }
            else {
                this.drawElevationShadowWithMaterialShapeDrawable(canvas0);
            }
            canvas0.restore();
        }

        private void drawElevationShadowWithMaterialShapeDrawable(Canvas canvas0) {
            this.compatShadowDrawable.setBounds(((int)this.currentMaskBounds.left), ((int)this.currentMaskBounds.top), ((int)this.currentMaskBounds.right), ((int)this.currentMaskBounds.bottom));
            this.compatShadowDrawable.setElevation(this.currentElevation);
            this.compatShadowDrawable.setShadowVerticalOffset(((int)this.currentElevationDy));
            ShapeAppearanceModel shapeAppearanceModel0 = this.maskEvaluator.getCurrentShapeAppearanceModel();
            this.compatShadowDrawable.setShapeAppearanceModel(shapeAppearanceModel0);
            this.compatShadowDrawable.draw(canvas0);
        }

        private void drawElevationShadowWithPaintShadowLayer(Canvas canvas0) {
            ShapeAppearanceModel shapeAppearanceModel0 = this.maskEvaluator.getCurrentShapeAppearanceModel();
            if(shapeAppearanceModel0.isRoundRect(this.currentMaskBounds)) {
                float f = shapeAppearanceModel0.getTopLeftCornerSize().getCornerSize(this.currentMaskBounds);
                canvas0.drawRoundRect(this.currentMaskBounds, f, f, this.shadowPaint);
                return;
            }
            canvas0.drawPath(this.maskEvaluator.getPath(), this.shadowPaint);
        }

        private void drawEndView(Canvas canvas0) {
            this.maybeDrawContainerColor(canvas0, this.endContainerPaint);
            TransitionUtils.transform(canvas0, this.getBounds(), this.currentEndBounds.left, this.currentEndBounds.top, this.fitModeResult.endScale, this.fadeModeResult.endAlpha, new CanvasOperation() {
                @Override  // com.google.android.material.canvas.CanvasCompat$CanvasOperation
                public void run(Canvas canvas0) {
                    TransitionDrawable.this.endView.draw(canvas0);
                }
            });
        }

        private void drawStartView(Canvas canvas0) {
            this.maybeDrawContainerColor(canvas0, this.startContainerPaint);
            TransitionUtils.transform(canvas0, this.getBounds(), this.currentStartBounds.left, this.currentStartBounds.top, this.fitModeResult.startScale, this.fadeModeResult.startAlpha, new CanvasOperation() {
                @Override  // com.google.android.material.canvas.CanvasCompat$CanvasOperation
                public void run(Canvas canvas0) {
                    TransitionDrawable.this.startView.draw(canvas0);
                }
            });
        }

        private static PointF getMotionPathPoint(RectF rectF0) {
            return new PointF(rectF0.centerX(), rectF0.top);
        }

        @Override  // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        private void maybeDrawContainerColor(Canvas canvas0, Paint paint0) {
            if(paint0.getColor() != 0 && paint0.getAlpha() > 0) {
                canvas0.drawRect(this.getBounds(), paint0);
            }
        }

        @Override  // android.graphics.drawable.Drawable
        public void setAlpha(int v) {
            throw new UnsupportedOperationException("Setting alpha on is not supported");
        }

        @Override  // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter0) {
            throw new UnsupportedOperationException("Setting a color filter is not supported");
        }

        private void setProgress(float f) {
            if(this.progress != f) {
                this.updateProgress(f);
            }
        }

        private void updateProgress(float f) {
            float f4;
            float f3;
            this.progress = f;
            this.scrimPaint.setAlpha(((int)(this.entering ? 0.0f + f * 255.0f : 255.0f + f * -255.0f)));
            this.motionPathMeasure.getPosTan(this.motionPathLength * f, this.motionPathPosition, null);
            float[] arr_f = this.motionPathPosition;
            float f1 = arr_f[0];
            float f2 = arr_f[1];
            int v = Float.compare(f, 1.0f);
            if(v > 0 || f < 0.0f) {
                if(v > 0) {
                    f3 = (f - 1.0f) / 0.01f;
                    f4 = 0.99f;
                }
                else {
                    f4 = 0.01f;
                    f3 = f / 0.01f * -1.0f;
                }
                this.motionPathMeasure.getPosTan(this.motionPathLength * f4, arr_f, null);
                f1 += (f1 - this.motionPathPosition[0]) * f3;
                f2 += (f2 - this.motionPathPosition[1]) * f3;
            }
            float f5 = (float)(((Float)Preconditions.checkNotNull(this.progressThresholds.scale.start)));
            float f6 = (float)(((Float)Preconditions.checkNotNull(this.progressThresholds.scale.end)));
            float f7 = this.startBounds.width();
            float f8 = this.startBounds.height();
            float f9 = this.endBounds.width();
            float f10 = this.endBounds.height();
            FitModeResult fitModeResult0 = this.fitModeEvaluator.evaluate(f, f5, f6, f7, f8, f9, f10);
            this.fitModeResult = fitModeResult0;
            this.currentStartBounds.set(f1 - fitModeResult0.currentStartWidth / 2.0f, f2, this.fitModeResult.currentStartWidth / 2.0f + f1, this.fitModeResult.currentStartHeight + f2);
            this.currentEndBounds.set(f1 - this.fitModeResult.currentEndWidth / 2.0f, f2, f1 + this.fitModeResult.currentEndWidth / 2.0f, this.fitModeResult.currentEndHeight + f2);
            this.currentStartBoundsMasked.set(this.currentStartBounds);
            this.currentEndBoundsMasked.set(this.currentEndBounds);
            float f11 = (float)(((Float)Preconditions.checkNotNull(this.progressThresholds.scaleMask.start)));
            float f12 = (float)(((Float)Preconditions.checkNotNull(this.progressThresholds.scaleMask.end)));
            boolean z = this.fitModeEvaluator.shouldMaskStartBounds(this.fitModeResult);
            this.fitModeEvaluator.applyMask((z ? this.currentStartBoundsMasked : this.currentEndBoundsMasked), (z ? TransitionUtils.lerp(0.0f, 1.0f, f11, f12, f) : 1.0f - TransitionUtils.lerp(0.0f, 1.0f, f11, f12, f)), this.fitModeResult);
            this.currentMaskBounds = new RectF(Math.min(this.currentStartBoundsMasked.left, this.currentEndBoundsMasked.left), Math.min(this.currentStartBoundsMasked.top, this.currentEndBoundsMasked.top), Math.max(this.currentStartBoundsMasked.right, this.currentEndBoundsMasked.right), Math.max(this.currentStartBoundsMasked.bottom, this.currentEndBoundsMasked.bottom));
            this.maskEvaluator.evaluate(f, this.startShapeAppearanceModel, this.endShapeAppearanceModel, this.currentStartBounds, this.currentStartBoundsMasked, this.currentEndBoundsMasked, this.progressThresholds.shapeMask);
            this.currentElevation = this.startElevation + f * (this.endElevation - this.startElevation);
            float f13 = TransitionDrawable.calculateElevationDxMultiplier(this.currentMaskBounds, this.displayWidth);
            float f14 = (float)(((int)(TransitionDrawable.calculateElevationDyMultiplier(this.currentMaskBounds, this.displayHeight) * this.currentElevation)));
            this.currentElevationDy = f14;
            this.shadowPaint.setShadowLayer(this.currentElevation, ((float)(((int)(f13 * this.currentElevation)))), f14, 0x2D000000);
            float f15 = (float)(((Float)Preconditions.checkNotNull(this.progressThresholds.fade.start)));
            float f16 = (float)(((Float)Preconditions.checkNotNull(this.progressThresholds.fade.end)));
            this.fadeModeResult = this.fadeModeEvaluator.evaluate(f, f15, f16, 0.35f);
            if(this.startContainerPaint.getColor() != 0) {
                this.startContainerPaint.setAlpha(this.fadeModeResult.startAlpha);
            }
            if(this.endContainerPaint.getColor() != 0) {
                this.endContainerPaint.setAlpha(this.fadeModeResult.endAlpha);
            }
            this.invalidateSelf();
        }
    }

    private static final ProgressThresholdsGroup DEFAULT_ENTER_THRESHOLDS = null;
    private static final ProgressThresholdsGroup DEFAULT_ENTER_THRESHOLDS_ARC = null;
    private static final ProgressThresholdsGroup DEFAULT_RETURN_THRESHOLDS = null;
    private static final ProgressThresholdsGroup DEFAULT_RETURN_THRESHOLDS_ARC = null;
    private static final float ELEVATION_NOT_SET = -1.0f;
    public static final int FADE_MODE_CROSS = 2;
    public static final int FADE_MODE_IN = 0;
    public static final int FADE_MODE_OUT = 1;
    public static final int FADE_MODE_THROUGH = 3;
    public static final int FIT_MODE_AUTO = 0;
    public static final int FIT_MODE_HEIGHT = 2;
    public static final int FIT_MODE_WIDTH = 1;
    private static final String PROP_BOUNDS = "materialContainerTransition:bounds";
    private static final String PROP_SHAPE_APPEARANCE = "materialContainerTransition:shapeAppearance";
    private static final String TAG = "MaterialContainerTransform";
    public static final int TRANSITION_DIRECTION_AUTO = 0;
    public static final int TRANSITION_DIRECTION_ENTER = 1;
    public static final int TRANSITION_DIRECTION_RETURN = 2;
    private static final String[] TRANSITION_PROPS;
    private boolean appliedThemeValues;
    private int containerColor;
    private boolean drawDebugEnabled;
    private int drawingViewId;
    private boolean elevationShadowEnabled;
    private int endContainerColor;
    private float endElevation;
    private ShapeAppearanceModel endShapeAppearanceModel;
    private View endView;
    private int endViewId;
    private int fadeMode;
    private ProgressThresholds fadeProgressThresholds;
    private int fitMode;
    private boolean holdAtEndEnabled;
    private boolean pathMotionCustom;
    private ProgressThresholds scaleMaskProgressThresholds;
    private ProgressThresholds scaleProgressThresholds;
    private int scrimColor;
    private ProgressThresholds shapeMaskProgressThresholds;
    private int startContainerColor;
    private float startElevation;
    private ShapeAppearanceModel startShapeAppearanceModel;
    private View startView;
    private int startViewId;
    private int transitionDirection;

    static {
        MaterialContainerTransform.TRANSITION_PROPS = new String[]{"materialContainerTransition:bounds", "materialContainerTransition:shapeAppearance"};
        MaterialContainerTransform.DEFAULT_ENTER_THRESHOLDS = new ProgressThresholdsGroup(new ProgressThresholds(0.0f, 0.25f), new ProgressThresholds(0.0f, 1.0f), new ProgressThresholds(0.0f, 1.0f), new ProgressThresholds(0.0f, 0.75f), null);
        MaterialContainerTransform.DEFAULT_RETURN_THRESHOLDS = new ProgressThresholdsGroup(new ProgressThresholds(0.6f, 0.9f), new ProgressThresholds(0.0f, 1.0f), new ProgressThresholds(0.0f, 0.9f), new ProgressThresholds(0.3f, 0.9f), null);
        MaterialContainerTransform.DEFAULT_ENTER_THRESHOLDS_ARC = new ProgressThresholdsGroup(new ProgressThresholds(0.1f, 0.4f), new ProgressThresholds(0.1f, 1.0f), new ProgressThresholds(0.1f, 1.0f), new ProgressThresholds(0.1f, 0.9f), null);
        MaterialContainerTransform.DEFAULT_RETURN_THRESHOLDS_ARC = new ProgressThresholdsGroup(new ProgressThresholds(0.6f, 0.9f), new ProgressThresholds(0.0f, 0.9f), new ProgressThresholds(0.0f, 0.9f), new ProgressThresholds(0.2f, 0.9f), null);
    }

    public MaterialContainerTransform() {
        boolean z = false;
        this.drawDebugEnabled = false;
        this.holdAtEndEnabled = false;
        this.pathMotionCustom = false;
        this.appliedThemeValues = false;
        this.drawingViewId = 0x1020002;
        this.startViewId = -1;
        this.endViewId = -1;
        this.containerColor = 0;
        this.startContainerColor = 0;
        this.endContainerColor = 0;
        this.scrimColor = 0x52000000;
        this.transitionDirection = 0;
        this.fadeMode = 0;
        this.fitMode = 0;
        if(Build.VERSION.SDK_INT >= 28) {
            z = true;
        }
        this.elevationShadowEnabled = z;
        this.startElevation = -1.0f;
        this.endElevation = -1.0f;
    }

    public MaterialContainerTransform(Context context0, boolean z) {
        boolean z1 = false;
        this.drawDebugEnabled = false;
        this.holdAtEndEnabled = false;
        this.pathMotionCustom = false;
        this.appliedThemeValues = false;
        this.drawingViewId = 0x1020002;
        this.startViewId = -1;
        this.endViewId = -1;
        this.containerColor = 0;
        this.startContainerColor = 0;
        this.endContainerColor = 0;
        this.scrimColor = 0x52000000;
        this.transitionDirection = 0;
        this.fadeMode = 0;
        this.fitMode = 0;
        if(Build.VERSION.SDK_INT >= 28) {
            z1 = true;
        }
        this.elevationShadowEnabled = z1;
        this.startElevation = -1.0f;
        this.endElevation = -1.0f;
        this.maybeApplyThemeValues(context0, z);
        this.appliedThemeValues = true;
    }

    private ProgressThresholdsGroup buildThresholdsGroup(boolean z) {
        PathMotion pathMotion0 = this.getPathMotion();
        return pathMotion0 instanceof ArcMotion || pathMotion0 instanceof MaterialArcMotion ? this.getThresholdsOrDefault(z, MaterialContainerTransform.DEFAULT_ENTER_THRESHOLDS_ARC, MaterialContainerTransform.DEFAULT_RETURN_THRESHOLDS_ARC) : this.getThresholdsOrDefault(z, MaterialContainerTransform.DEFAULT_ENTER_THRESHOLDS, MaterialContainerTransform.DEFAULT_RETURN_THRESHOLDS);
    }

    private static RectF calculateDrawableBounds(View view0, View view1, float f, float f1) {
        if(view1 != null) {
            RectF rectF0 = TransitionUtils.getLocationOnScreen(view1);
            rectF0.offset(f, f1);
            return rectF0;
        }
        return new RectF(0.0f, 0.0f, ((float)view0.getWidth()), ((float)view0.getHeight()));
    }

    @Override  // androidx.transition.Transition
    public void captureEndValues(TransitionValues transitionValues0) {
        MaterialContainerTransform.captureValues(transitionValues0, this.endView, this.endViewId, this.endShapeAppearanceModel);
    }

    private static ShapeAppearanceModel captureShapeAppearance(View view0, RectF rectF0, ShapeAppearanceModel shapeAppearanceModel0) {
        return TransitionUtils.convertToRelativeCornerSizes(MaterialContainerTransform.getShapeAppearance(view0, shapeAppearanceModel0), rectF0);
    }

    @Override  // androidx.transition.Transition
    public void captureStartValues(TransitionValues transitionValues0) {
        MaterialContainerTransform.captureValues(transitionValues0, this.startView, this.startViewId, this.startShapeAppearanceModel);
    }

    private static void captureValues(TransitionValues transitionValues0, View view0, int v, ShapeAppearanceModel shapeAppearanceModel0) {
        if(v != -1) {
            transitionValues0.view = TransitionUtils.findDescendantOrAncestorById(transitionValues0.view, v);
        }
        else if(view0 != null) {
            transitionValues0.view = view0;
        }
        else if(transitionValues0.view.getTag(id.mtrl_motion_snapshot_view) instanceof View) {
            View view1 = (View)transitionValues0.view.getTag(id.mtrl_motion_snapshot_view);
            transitionValues0.view.setTag(id.mtrl_motion_snapshot_view, null);
            transitionValues0.view = view1;
        }
        View view2 = transitionValues0.view;
        if(!ViewCompat.isLaidOut(view2) && view2.getWidth() == 0 && view2.getHeight() == 0) {
            return;
        }
        RectF rectF0 = view2.getParent() == null ? TransitionUtils.getRelativeBounds(view2) : TransitionUtils.getLocationOnScreen(view2);
        transitionValues0.values.put("materialContainerTransition:bounds", rectF0);
        ShapeAppearanceModel shapeAppearanceModel1 = MaterialContainerTransform.captureShapeAppearance(view2, rectF0, shapeAppearanceModel0);
        transitionValues0.values.put("materialContainerTransition:shapeAppearance", shapeAppearanceModel1);
    }

    @Override  // androidx.transition.Transition
    public Animator createAnimator(ViewGroup viewGroup0, TransitionValues transitionValues0, TransitionValues transitionValues1) {
        View view4;
        View view0 = null;
        if(transitionValues0 != null && transitionValues1 != null) {
            Object object0 = transitionValues0.values.get("materialContainerTransition:bounds");
            Object object1 = transitionValues0.values.get("materialContainerTransition:shapeAppearance");
            if(((RectF)object0) != null && ((ShapeAppearanceModel)object1) != null) {
                Object object2 = transitionValues1.values.get("materialContainerTransition:bounds");
                Object object3 = transitionValues1.values.get("materialContainerTransition:shapeAppearance");
                if(((RectF)object2) != null && ((ShapeAppearanceModel)object3) != null) {
                    View view1 = transitionValues0.view;
                    View view2 = transitionValues1.view;
                    View view3 = view2.getParent() == null ? view1 : view2;
                    if(this.drawingViewId == view3.getId()) {
                        view4 = (View)view3.getParent();
                        view0 = view3;
                    }
                    else {
                        view4 = TransitionUtils.findAncestorById(view3, this.drawingViewId);
                    }
                    RectF rectF0 = TransitionUtils.getLocationOnScreen(view4);
                    float f = -rectF0.left;
                    float f1 = -rectF0.top;
                    RectF rectF1 = MaterialContainerTransform.calculateDrawableBounds(view4, view0, f, f1);
                    ((RectF)object0).offset(f, f1);
                    ((RectF)object2).offset(f, f1);
                    boolean z = this.isEntering(((RectF)object0), ((RectF)object2));
                    if(!this.appliedThemeValues) {
                        this.maybeApplyThemeValues(view3.getContext(), z);
                    }
                    TransitionDrawable materialContainerTransform$TransitionDrawable0 = new TransitionDrawable(this.getPathMotion(), view1, ((RectF)object0), ((ShapeAppearanceModel)object1), MaterialContainerTransform.getElevationOrDefault(this.startElevation, view1), view2, ((RectF)object2), ((ShapeAppearanceModel)object3), MaterialContainerTransform.getElevationOrDefault(this.endElevation, view2), this.containerColor, this.startContainerColor, this.endContainerColor, this.scrimColor, z, this.elevationShadowEnabled, FadeModeEvaluators.get(this.fadeMode, z), FitModeEvaluators.get(this.fitMode, z, ((RectF)object0), ((RectF)object2)), this.buildThresholdsGroup(z), this.drawDebugEnabled, null);
                    materialContainerTransform$TransitionDrawable0.setBounds(Math.round(rectF1.left), Math.round(rectF1.top), Math.round(rectF1.right), Math.round(rectF1.bottom));
                    Animator animator0 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
                    ((ValueAnimator)animator0).addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
                        public void onAnimationUpdate(ValueAnimator valueAnimator0) {
                            float f = valueAnimator0.getAnimatedFraction();
                            TransitionDrawable.access$200(materialContainerTransform$TransitionDrawable0, f);
                        }
                    });
                    this.addListener(new TransitionListenerAdapter() {
                        @Override  // com.google.android.material.transition.TransitionListenerAdapter
                        public void onTransitionEnd(Transition transition0) {
                            MaterialContainerTransform.this.removeListener(this);
                            if(MaterialContainerTransform.this.holdAtEndEnabled) {
                                return;
                            }
                            view1.setAlpha(1.0f);
                            view2.setAlpha(1.0f);
                            ViewUtils.getOverlay(view4).remove(materialContainerTransform$TransitionDrawable0);
                        }

                        @Override  // com.google.android.material.transition.TransitionListenerAdapter
                        public void onTransitionStart(Transition transition0) {
                            ViewUtils.getOverlay(view4).add(materialContainerTransform$TransitionDrawable0);
                            view1.setAlpha(0.0f);
                            view2.setAlpha(0.0f);
                        }
                    });
                    return animator0;
                }
                Log.w("MaterialContainerTransform", "Skipping due to null end bounds. Ensure end view is laid out and measured.");
                return null;
            }
            Log.w("MaterialContainerTransform", "Skipping due to null start bounds. Ensure start view is laid out and measured.");
        }
        return null;
    }

    public int getContainerColor() {
        return this.containerColor;
    }

    public int getDrawingViewId() {
        return this.drawingViewId;
    }

    private static float getElevationOrDefault(float f, View view0) {
        return f == -1.0f ? ViewCompat.getElevation(view0) : f;
    }

    public int getEndContainerColor() {
        return this.endContainerColor;
    }

    public float getEndElevation() {
        return this.endElevation;
    }

    public ShapeAppearanceModel getEndShapeAppearanceModel() {
        return this.endShapeAppearanceModel;
    }

    public View getEndView() {
        return this.endView;
    }

    public int getEndViewId() {
        return this.endViewId;
    }

    public int getFadeMode() {
        return this.fadeMode;
    }

    public ProgressThresholds getFadeProgressThresholds() {
        return this.fadeProgressThresholds;
    }

    public int getFitMode() {
        return this.fitMode;
    }

    public ProgressThresholds getScaleMaskProgressThresholds() {
        return this.scaleMaskProgressThresholds;
    }

    public ProgressThresholds getScaleProgressThresholds() {
        return this.scaleProgressThresholds;
    }

    public int getScrimColor() {
        return this.scrimColor;
    }

    private static ShapeAppearanceModel getShapeAppearance(View view0, ShapeAppearanceModel shapeAppearanceModel0) {
        if(shapeAppearanceModel0 != null) {
            return shapeAppearanceModel0;
        }
        if(view0.getTag(id.mtrl_motion_snapshot_view) instanceof ShapeAppearanceModel) {
            return (ShapeAppearanceModel)view0.getTag(id.mtrl_motion_snapshot_view);
        }
        Context context0 = view0.getContext();
        int v = MaterialContainerTransform.getTransitionShapeAppearanceResId(context0);
        if(v != -1) {
            return ShapeAppearanceModel.builder(context0, v, 0).build();
        }
        return view0 instanceof Shapeable ? ((Shapeable)view0).getShapeAppearanceModel() : ShapeAppearanceModel.builder().build();
    }

    public ProgressThresholds getShapeMaskProgressThresholds() {
        return this.shapeMaskProgressThresholds;
    }

    public int getStartContainerColor() {
        return this.startContainerColor;
    }

    public float getStartElevation() {
        return this.startElevation;
    }

    public ShapeAppearanceModel getStartShapeAppearanceModel() {
        return this.startShapeAppearanceModel;
    }

    public View getStartView() {
        return this.startView;
    }

    public int getStartViewId() {
        return this.startViewId;
    }

    private ProgressThresholdsGroup getThresholdsOrDefault(boolean z, ProgressThresholdsGroup materialContainerTransform$ProgressThresholdsGroup0, ProgressThresholdsGroup materialContainerTransform$ProgressThresholdsGroup1) {
        if(!z) {
            materialContainerTransform$ProgressThresholdsGroup0 = materialContainerTransform$ProgressThresholdsGroup1;
        }
        return new ProgressThresholdsGroup(((ProgressThresholds)TransitionUtils.defaultIfNull(this.fadeProgressThresholds, ProgressThresholdsGroup.access$400(materialContainerTransform$ProgressThresholdsGroup0))), ((ProgressThresholds)TransitionUtils.defaultIfNull(this.scaleProgressThresholds, ProgressThresholdsGroup.access$500(materialContainerTransform$ProgressThresholdsGroup0))), ((ProgressThresholds)TransitionUtils.defaultIfNull(this.scaleMaskProgressThresholds, ProgressThresholdsGroup.access$600(materialContainerTransform$ProgressThresholdsGroup0))), ((ProgressThresholds)TransitionUtils.defaultIfNull(this.shapeMaskProgressThresholds, ProgressThresholdsGroup.access$700(materialContainerTransform$ProgressThresholdsGroup0))), null);
    }

    public int getTransitionDirection() {
        return this.transitionDirection;
    }

    @Override  // androidx.transition.Transition
    public String[] getTransitionProperties() {
        return MaterialContainerTransform.TRANSITION_PROPS;
    }

    private static int getTransitionShapeAppearanceResId(Context context0) {
        TypedArray typedArray0 = context0.obtainStyledAttributes(new int[]{attr.transitionShapeAppearance});
        int v = typedArray0.getResourceId(0, -1);
        typedArray0.recycle();
        return v;
    }

    public boolean isDrawDebugEnabled() {
        return this.drawDebugEnabled;
    }

    public boolean isElevationShadowEnabled() {
        return this.elevationShadowEnabled;
    }

    private boolean isEntering(RectF rectF0, RectF rectF1) {
        int v = this.transitionDirection;
        switch(v) {
            case 0: {
                return TransitionUtils.calculateArea(rectF1) > TransitionUtils.calculateArea(rectF0);
            }
            case 1: {
                return true;
            }
            default: {
                if(v != 2) {
                    throw new IllegalArgumentException("Invalid transition direction: " + this.transitionDirection);
                }
                return false;
            }
        }
    }

    public boolean isHoldAtEndEnabled() {
        return this.holdAtEndEnabled;
    }

    private void maybeApplyThemeValues(Context context0, boolean z) {
        TransitionUtils.maybeApplyThemeInterpolator(this, context0, attr.motionEasingEmphasizedInterpolator, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        TransitionUtils.maybeApplyThemeDuration(this, context0, (z ? attr.motionDurationLong2 : attr.motionDurationMedium4));
        if(!this.pathMotionCustom) {
            TransitionUtils.maybeApplyThemePath(this, context0, attr.motionPath);
        }
    }

    public void setAllContainerColors(int v) {
        this.containerColor = v;
        this.startContainerColor = v;
        this.endContainerColor = v;
    }

    public void setContainerColor(int v) {
        this.containerColor = v;
    }

    public void setDrawDebugEnabled(boolean z) {
        this.drawDebugEnabled = z;
    }

    public void setDrawingViewId(int v) {
        this.drawingViewId = v;
    }

    public void setElevationShadowEnabled(boolean z) {
        this.elevationShadowEnabled = z;
    }

    public void setEndContainerColor(int v) {
        this.endContainerColor = v;
    }

    public void setEndElevation(float f) {
        this.endElevation = f;
    }

    public void setEndShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel0) {
        this.endShapeAppearanceModel = shapeAppearanceModel0;
    }

    public void setEndView(View view0) {
        this.endView = view0;
    }

    public void setEndViewId(int v) {
        this.endViewId = v;
    }

    public void setFadeMode(int v) {
        this.fadeMode = v;
    }

    public void setFadeProgressThresholds(ProgressThresholds materialContainerTransform$ProgressThresholds0) {
        this.fadeProgressThresholds = materialContainerTransform$ProgressThresholds0;
    }

    public void setFitMode(int v) {
        this.fitMode = v;
    }

    public void setHoldAtEndEnabled(boolean z) {
        this.holdAtEndEnabled = z;
    }

    @Override  // androidx.transition.Transition
    public void setPathMotion(PathMotion pathMotion0) {
        super.setPathMotion(pathMotion0);
        this.pathMotionCustom = true;
    }

    public void setScaleMaskProgressThresholds(ProgressThresholds materialContainerTransform$ProgressThresholds0) {
        this.scaleMaskProgressThresholds = materialContainerTransform$ProgressThresholds0;
    }

    public void setScaleProgressThresholds(ProgressThresholds materialContainerTransform$ProgressThresholds0) {
        this.scaleProgressThresholds = materialContainerTransform$ProgressThresholds0;
    }

    public void setScrimColor(int v) {
        this.scrimColor = v;
    }

    public void setShapeMaskProgressThresholds(ProgressThresholds materialContainerTransform$ProgressThresholds0) {
        this.shapeMaskProgressThresholds = materialContainerTransform$ProgressThresholds0;
    }

    public void setStartContainerColor(int v) {
        this.startContainerColor = v;
    }

    public void setStartElevation(float f) {
        this.startElevation = f;
    }

    public void setStartShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel0) {
        this.startShapeAppearanceModel = shapeAppearanceModel0;
    }

    public void setStartView(View view0) {
        this.startView = view0;
    }

    public void setStartViewId(int v) {
        this.startViewId = v;
    }

    public void setTransitionDirection(int v) {
        this.transitionDirection = v;
    }
}

