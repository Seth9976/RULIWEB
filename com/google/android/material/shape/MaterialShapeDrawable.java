package com.google.android.material.shape;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.graphics.Region;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import androidx.core.graphics.drawable.TintAwareDrawable;
import androidx.core.util.ObjectsCompat;
import com.google.android.material.R.attr;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.shadow.ShadowRenderer;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.BitSet;

public class MaterialShapeDrawable extends Drawable implements TintAwareDrawable, Shapeable {
    @Retention(RetentionPolicy.SOURCE)
    public @interface CompatibilityShadowMode {
    }

    public static class MaterialShapeDrawableState extends Drawable.ConstantState {
        int alpha;
        ColorFilter colorFilter;
        float elevation;
        ElevationOverlayProvider elevationOverlayProvider;
        ColorStateList fillColor;
        float interpolation;
        Rect padding;
        Paint.Style paintStyle;
        float parentAbsoluteElevation;
        float scale;
        int shadowCompatMode;
        int shadowCompatOffset;
        int shadowCompatRadius;
        int shadowCompatRotation;
        ShapeAppearanceModel shapeAppearanceModel;
        ColorStateList strokeColor;
        ColorStateList strokeTintList;
        float strokeWidth;
        ColorStateList tintList;
        PorterDuff.Mode tintMode;
        float translationZ;
        boolean useTintColorForShadow;

        public MaterialShapeDrawableState(MaterialShapeDrawableState materialShapeDrawable$MaterialShapeDrawableState0) {
            this.padding = null;
            this.shapeAppearanceModel = materialShapeDrawable$MaterialShapeDrawableState0.shapeAppearanceModel;
            this.elevationOverlayProvider = materialShapeDrawable$MaterialShapeDrawableState0.elevationOverlayProvider;
            this.strokeWidth = materialShapeDrawable$MaterialShapeDrawableState0.strokeWidth;
            this.colorFilter = materialShapeDrawable$MaterialShapeDrawableState0.colorFilter;
            this.fillColor = materialShapeDrawable$MaterialShapeDrawableState0.fillColor;
            this.strokeColor = materialShapeDrawable$MaterialShapeDrawableState0.strokeColor;
            this.tintMode = materialShapeDrawable$MaterialShapeDrawableState0.tintMode;
            this.tintList = materialShapeDrawable$MaterialShapeDrawableState0.tintList;
            this.alpha = materialShapeDrawable$MaterialShapeDrawableState0.alpha;
            this.scale = materialShapeDrawable$MaterialShapeDrawableState0.scale;
            this.shadowCompatOffset = materialShapeDrawable$MaterialShapeDrawableState0.shadowCompatOffset;
            this.shadowCompatMode = materialShapeDrawable$MaterialShapeDrawableState0.shadowCompatMode;
            this.useTintColorForShadow = materialShapeDrawable$MaterialShapeDrawableState0.useTintColorForShadow;
            this.interpolation = materialShapeDrawable$MaterialShapeDrawableState0.interpolation;
            this.parentAbsoluteElevation = materialShapeDrawable$MaterialShapeDrawableState0.parentAbsoluteElevation;
            this.elevation = materialShapeDrawable$MaterialShapeDrawableState0.elevation;
            this.translationZ = materialShapeDrawable$MaterialShapeDrawableState0.translationZ;
            this.shadowCompatRadius = materialShapeDrawable$MaterialShapeDrawableState0.shadowCompatRadius;
            this.shadowCompatRotation = materialShapeDrawable$MaterialShapeDrawableState0.shadowCompatRotation;
            this.strokeTintList = materialShapeDrawable$MaterialShapeDrawableState0.strokeTintList;
            this.paintStyle = materialShapeDrawable$MaterialShapeDrawableState0.paintStyle;
            if(materialShapeDrawable$MaterialShapeDrawableState0.padding != null) {
                this.padding = new Rect(materialShapeDrawable$MaterialShapeDrawableState0.padding);
            }
        }

        public MaterialShapeDrawableState(ShapeAppearanceModel shapeAppearanceModel0, ElevationOverlayProvider elevationOverlayProvider0) {
            this.fillColor = null;
            this.strokeColor = null;
            this.strokeTintList = null;
            this.tintList = null;
            this.tintMode = PorterDuff.Mode.SRC_IN;
            this.padding = null;
            this.scale = 1.0f;
            this.interpolation = 1.0f;
            this.alpha = 0xFF;
            this.parentAbsoluteElevation = 0.0f;
            this.elevation = 0.0f;
            this.translationZ = 0.0f;
            this.shadowCompatMode = 0;
            this.shadowCompatRadius = 0;
            this.shadowCompatOffset = 0;
            this.shadowCompatRotation = 0;
            this.useTintColorForShadow = false;
            this.paintStyle = Paint.Style.FILL_AND_STROKE;
            this.shapeAppearanceModel = shapeAppearanceModel0;
            this.elevationOverlayProvider = elevationOverlayProvider0;
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        @Override  // android.graphics.drawable.Drawable$ConstantState
        public Drawable newDrawable() {
            Drawable drawable0 = new MaterialShapeDrawable(this);
            ((MaterialShapeDrawable)drawable0).pathDirty = true;
            return drawable0;
        }
    }

    public static final int SHADOW_COMPAT_MODE_ALWAYS = 2;
    public static final int SHADOW_COMPAT_MODE_DEFAULT = 0;
    public static final int SHADOW_COMPAT_MODE_NEVER = 1;
    private static final float SHADOW_OFFSET_MULTIPLIER = 0.25f;
    private static final float SHADOW_RADIUS_MULTIPLIER = 0.75f;
    private static final String TAG = "MaterialShapeDrawable";
    private static final Paint clearPaint;
    private final BitSet containsIncompatibleShadowOp;
    private final ShadowCompatOperation[] cornerShadowOperation;
    private MaterialShapeDrawableState drawableState;
    private final ShadowCompatOperation[] edgeShadowOperation;
    private final Paint fillPaint;
    private final RectF insetRectF;
    private final Matrix matrix;
    private final Path path;
    private final RectF pathBounds;
    private boolean pathDirty;
    private final Path pathInsetByStroke;
    private final ShapeAppearancePathProvider pathProvider;
    private final PathListener pathShadowListener;
    private final RectF rectF;
    private int resolvedTintColor;
    private final Region scratchRegion;
    private boolean shadowBitmapDrawingEnable;
    private final ShadowRenderer shadowRenderer;
    private final Paint strokePaint;
    private ShapeAppearanceModel strokeShapeAppearance;
    private PorterDuffColorFilter strokeTintFilter;
    private PorterDuffColorFilter tintFilter;
    private final Region transparentRegion;

    static {
        Paint paint0 = new Paint(1);
        MaterialShapeDrawable.clearPaint = paint0;
        paint0.setColor(-1);
        paint0.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));
    }

    public MaterialShapeDrawable() {
        this(new ShapeAppearanceModel());
    }

    public MaterialShapeDrawable(Context context0, AttributeSet attributeSet0, int v, int v1) {
        this(ShapeAppearanceModel.builder(context0, attributeSet0, v, v1).build());
    }

    protected MaterialShapeDrawable(MaterialShapeDrawableState materialShapeDrawable$MaterialShapeDrawableState0) {
        this.cornerShadowOperation = new ShadowCompatOperation[4];
        this.edgeShadowOperation = new ShadowCompatOperation[4];
        this.containsIncompatibleShadowOp = new BitSet(8);
        this.matrix = new Matrix();
        this.path = new Path();
        this.pathInsetByStroke = new Path();
        this.rectF = new RectF();
        this.insetRectF = new RectF();
        this.transparentRegion = new Region();
        this.scratchRegion = new Region();
        Paint paint0 = new Paint(1);
        this.fillPaint = paint0;
        Paint paint1 = new Paint(1);
        this.strokePaint = paint1;
        this.shadowRenderer = new ShadowRenderer();
        this.pathProvider = Looper.getMainLooper().getThread() == Thread.currentThread() ? ShapeAppearancePathProvider.getInstance() : new ShapeAppearancePathProvider();
        this.pathBounds = new RectF();
        this.shadowBitmapDrawingEnable = true;
        this.drawableState = materialShapeDrawable$MaterialShapeDrawableState0;
        paint1.setStyle(Paint.Style.STROKE);
        paint0.setStyle(Paint.Style.FILL);
        this.updateTintFilter();
        this.updateColorsForState(this.getState());
        this.pathShadowListener = new PathListener() {
            @Override  // com.google.android.material.shape.ShapeAppearancePathProvider$PathListener
            public void onCornerPathCreated(ShapePath shapePath0, Matrix matrix0, int v) {
                MaterialShapeDrawable.this.containsIncompatibleShadowOp.set(v, shapePath0.containsIncompatibleShadowOp());
                MaterialShapeDrawable.this.cornerShadowOperation[v] = shapePath0.createShadowCompatOperation(matrix0);
            }

            @Override  // com.google.android.material.shape.ShapeAppearancePathProvider$PathListener
            public void onEdgePathCreated(ShapePath shapePath0, Matrix matrix0, int v) {
                MaterialShapeDrawable.this.containsIncompatibleShadowOp.set(v + 4, shapePath0.containsIncompatibleShadowOp());
                MaterialShapeDrawable.access$200(MaterialShapeDrawable.this)[v] = shapePath0.createShadowCompatOperation(matrix0);
            }
        };
    }

    public MaterialShapeDrawable(ShapeAppearanceModel shapeAppearanceModel0) {
        this(new MaterialShapeDrawableState(shapeAppearanceModel0, null));
    }

    @Deprecated
    public MaterialShapeDrawable(ShapePathModel shapePathModel0) {
        this(shapePathModel0);
    }

    static ShadowCompatOperation[] access$200(MaterialShapeDrawable materialShapeDrawable0) {
        return materialShapeDrawable0.edgeShadowOperation;
    }

    private PorterDuffColorFilter calculatePaintColorTintFilter(Paint paint0, boolean z) {
        if(z) {
            int v = paint0.getColor();
            int v1 = this.compositeElevationOverlayIfNeeded(v);
            this.resolvedTintColor = v1;
            return v1 == v ? null : new PorterDuffColorFilter(v1, PorterDuff.Mode.SRC_IN);
        }
        return null;
    }

    private void calculatePath(RectF rectF0, Path path0) {
        this.calculatePathForSize(rectF0, path0);
        if(this.drawableState.scale != 1.0f) {
            this.matrix.reset();
            float f = this.drawableState.scale;
            float f1 = this.drawableState.scale;
            float f2 = rectF0.width();
            float f3 = rectF0.height();
            this.matrix.setScale(f, f1, f2 / 2.0f, f3 / 2.0f);
            path0.transform(this.matrix);
        }
        path0.computeBounds(this.pathBounds, true);
    }

    protected final void calculatePathForSize(RectF rectF0, Path path0) {
        this.pathProvider.calculatePath(this.drawableState.shapeAppearanceModel, this.drawableState.interpolation, rectF0, this.pathShadowListener, path0);
    }

    private void calculateStrokePath() {
        ShapeAppearanceModel shapeAppearanceModel0 = this.getShapeAppearanceModel().withTransformedCornerSizes(new CornerSizeUnaryOperator() {
            @Override  // com.google.android.material.shape.ShapeAppearanceModel$CornerSizeUnaryOperator
            public CornerSize apply(CornerSize cornerSize0) {
                return cornerSize0 instanceof RelativeCornerSize ? cornerSize0 : new AdjustedCornerSize(-this.getStrokeInsetLength(), cornerSize0);
            }
        });
        this.strokeShapeAppearance = shapeAppearanceModel0;
        float f = this.drawableState.interpolation;
        RectF rectF0 = this.getBoundsInsetByStroke();
        this.pathProvider.calculatePath(shapeAppearanceModel0, f, rectF0, this.pathInsetByStroke);
    }

    private PorterDuffColorFilter calculateTintColorTintFilter(ColorStateList colorStateList0, PorterDuff.Mode porterDuff$Mode0, boolean z) {
        int v = colorStateList0.getColorForState(this.getState(), 0);
        if(z) {
            v = this.compositeElevationOverlayIfNeeded(v);
        }
        this.resolvedTintColor = v;
        return new PorterDuffColorFilter(v, porterDuff$Mode0);
    }

    private PorterDuffColorFilter calculateTintFilter(ColorStateList colorStateList0, PorterDuff.Mode porterDuff$Mode0, Paint paint0, boolean z) {
        return colorStateList0 == null || porterDuff$Mode0 == null ? this.calculatePaintColorTintFilter(paint0, z) : this.calculateTintColorTintFilter(colorStateList0, porterDuff$Mode0, z);
    }

    protected int compositeElevationOverlayIfNeeded(int v) {
        float f = this.getZ();
        return this.drawableState.elevationOverlayProvider == null ? v : this.drawableState.elevationOverlayProvider.compositeOverlayIfNeeded(v, f + this.getParentAbsoluteElevation());
    }

    public static MaterialShapeDrawable createWithElevationOverlay(Context context0) {
        return MaterialShapeDrawable.createWithElevationOverlay(context0, 0.0f);
    }

    public static MaterialShapeDrawable createWithElevationOverlay(Context context0, float f) {
        return MaterialShapeDrawable.createWithElevationOverlay(context0, f, null);
    }

    public static MaterialShapeDrawable createWithElevationOverlay(Context context0, float f, ColorStateList colorStateList0) {
        if(colorStateList0 == null) {
            colorStateList0 = ColorStateList.valueOf(MaterialColors.getColor(context0, attr.colorSurface, "MaterialShapeDrawable"));
        }
        MaterialShapeDrawable materialShapeDrawable0 = new MaterialShapeDrawable();
        materialShapeDrawable0.initializeElevationOverlay(context0);
        materialShapeDrawable0.setFillColor(colorStateList0);
        materialShapeDrawable0.setElevation(f);
        return materialShapeDrawable0;
    }

    @Override  // android.graphics.drawable.Drawable
    public void draw(Canvas canvas0) {
        this.fillPaint.setColorFilter(this.tintFilter);
        int v = this.fillPaint.getAlpha();
        this.fillPaint.setAlpha(v * (this.drawableState.alpha + (this.drawableState.alpha >>> 7)) >>> 8);
        this.strokePaint.setColorFilter(this.strokeTintFilter);
        this.strokePaint.setStrokeWidth(this.drawableState.strokeWidth);
        int v1 = this.strokePaint.getAlpha();
        this.strokePaint.setAlpha(v1 * (this.drawableState.alpha + (this.drawableState.alpha >>> 7)) >>> 8);
        if(this.pathDirty) {
            this.calculateStrokePath();
            this.calculatePath(this.getBoundsAsRectF(), this.path);
            this.pathDirty = false;
        }
        this.maybeDrawCompatShadow(canvas0);
        if(this.hasFill()) {
            this.drawFillShape(canvas0);
        }
        if(this.hasStroke()) {
            this.drawStrokeShape(canvas0);
        }
        this.fillPaint.setAlpha(v);
        this.strokePaint.setAlpha(v1);
    }

    private void drawCompatShadow(Canvas canvas0) {
        if(this.containsIncompatibleShadowOp.cardinality() > 0) {
            Log.w("MaterialShapeDrawable", "Compatibility shadow requested but can\'t be drawn for all operations in this shape.");
        }
        if(this.drawableState.shadowCompatOffset != 0) {
            canvas0.drawPath(this.path, this.shadowRenderer.getShadowPaint());
        }
        for(int v = 0; v < 4; ++v) {
            this.cornerShadowOperation[v].draw(this.shadowRenderer, this.drawableState.shadowCompatRadius, canvas0);
            this.edgeShadowOperation[v].draw(this.shadowRenderer, this.drawableState.shadowCompatRadius, canvas0);
        }
        if(this.shadowBitmapDrawingEnable) {
            int v1 = this.getShadowOffsetX();
            int v2 = this.getShadowOffsetY();
            canvas0.translate(((float)(-v1)), ((float)(-v2)));
            canvas0.drawPath(this.path, MaterialShapeDrawable.clearPaint);
            canvas0.translate(((float)v1), ((float)v2));
        }
    }

    private void drawFillShape(Canvas canvas0) {
        ShapeAppearanceModel shapeAppearanceModel0 = this.drawableState.shapeAppearanceModel;
        RectF rectF0 = this.getBoundsAsRectF();
        this.drawShape(canvas0, this.fillPaint, this.path, shapeAppearanceModel0, rectF0);
    }

    private void drawShape(Canvas canvas0, Paint paint0, Path path0, ShapeAppearanceModel shapeAppearanceModel0, RectF rectF0) {
        if(shapeAppearanceModel0.isRoundRect(rectF0)) {
            float f = shapeAppearanceModel0.getTopRightCornerSize().getCornerSize(rectF0) * this.drawableState.interpolation;
            canvas0.drawRoundRect(rectF0, f, f, paint0);
            return;
        }
        canvas0.drawPath(path0, paint0);
    }

    protected void drawShape(Canvas canvas0, Paint paint0, Path path0, RectF rectF0) {
        this.drawShape(canvas0, paint0, path0, this.drawableState.shapeAppearanceModel, rectF0);
    }

    protected void drawStrokeShape(Canvas canvas0) {
        ShapeAppearanceModel shapeAppearanceModel0 = this.strokeShapeAppearance;
        RectF rectF0 = this.getBoundsInsetByStroke();
        this.drawShape(canvas0, this.strokePaint, this.pathInsetByStroke, shapeAppearanceModel0, rectF0);
    }

    @Override  // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.drawableState.alpha;
    }

    public float getBottomLeftCornerResolvedSize() {
        return this.drawableState.shapeAppearanceModel.getBottomLeftCornerSize().getCornerSize(this.getBoundsAsRectF());
    }

    public float getBottomRightCornerResolvedSize() {
        return this.drawableState.shapeAppearanceModel.getBottomRightCornerSize().getCornerSize(this.getBoundsAsRectF());
    }

    protected RectF getBoundsAsRectF() {
        Rect rect0 = this.getBounds();
        this.rectF.set(rect0);
        return this.rectF;
    }

    private RectF getBoundsInsetByStroke() {
        RectF rectF0 = this.getBoundsAsRectF();
        this.insetRectF.set(rectF0);
        float f = this.getStrokeInsetLength();
        this.insetRectF.inset(f, f);
        return this.insetRectF;
    }

    @Override  // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.drawableState;
    }

    public float getElevation() {
        return this.drawableState.elevation;
    }

    public ColorStateList getFillColor() {
        return this.drawableState.fillColor;
    }

    public float getInterpolation() {
        return this.drawableState.interpolation;
    }

    @Override  // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override  // android.graphics.drawable.Drawable
    public void getOutline(Outline outline0) {
        if(this.drawableState.shadowCompatMode == 2) {
            return;
        }
        if(this.isRoundRect()) {
            float f = this.getTopLeftCornerResolvedSize() * this.drawableState.interpolation;
            outline0.setRoundRect(this.getBounds(), f);
            return;
        }
        this.calculatePath(this.getBoundsAsRectF(), this.path);
        DrawableUtils.setOutlineToPath(outline0, this.path);
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect0) {
        if(this.drawableState.padding != null) {
            rect0.set(this.drawableState.padding);
            return true;
        }
        return super.getPadding(rect0);
    }

    public Paint.Style getPaintStyle() {
        return this.drawableState.paintStyle;
    }

    public float getParentAbsoluteElevation() {
        return this.drawableState.parentAbsoluteElevation;
    }

    @Deprecated
    public void getPathForSize(int v, int v1, Path path0) {
        this.calculatePathForSize(new RectF(0.0f, 0.0f, ((float)v), ((float)v1)), path0);
    }

    public int getResolvedTintColor() {
        return this.resolvedTintColor;
    }

    public float getScale() {
        return this.drawableState.scale;
    }

    public int getShadowCompatRotation() {
        return this.drawableState.shadowCompatRotation;
    }

    public int getShadowCompatibilityMode() {
        return this.drawableState.shadowCompatMode;
    }

    @Deprecated
    public int getShadowElevation() {
        return (int)this.getElevation();
    }

    public int getShadowOffsetX() {
        return (int)(((double)this.drawableState.shadowCompatOffset) * Math.sin(Math.toRadians(this.drawableState.shadowCompatRotation)));
    }

    public int getShadowOffsetY() {
        return (int)(((double)this.drawableState.shadowCompatOffset) * Math.cos(Math.toRadians(this.drawableState.shadowCompatRotation)));
    }

    public int getShadowRadius() {
        return this.drawableState.shadowCompatRadius;
    }

    public int getShadowVerticalOffset() {
        return this.drawableState.shadowCompatOffset;
    }

    @Override  // com.google.android.material.shape.Shapeable
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.drawableState.shapeAppearanceModel;
    }

    @Deprecated
    public ShapePathModel getShapedViewModel() {
        ShapeAppearanceModel shapeAppearanceModel0 = this.getShapeAppearanceModel();
        return shapeAppearanceModel0 instanceof ShapePathModel ? ((ShapePathModel)shapeAppearanceModel0) : null;
    }

    public ColorStateList getStrokeColor() {
        return this.drawableState.strokeColor;
    }

    // 去混淆评级： 低(20)
    private float getStrokeInsetLength() {
        return this.hasStroke() ? this.strokePaint.getStrokeWidth() / 2.0f : 0.0f;
    }

    public ColorStateList getStrokeTintList() {
        return this.drawableState.strokeTintList;
    }

    public float getStrokeWidth() {
        return this.drawableState.strokeWidth;
    }

    public ColorStateList getTintList() {
        return this.drawableState.tintList;
    }

    public float getTopLeftCornerResolvedSize() {
        return this.drawableState.shapeAppearanceModel.getTopLeftCornerSize().getCornerSize(this.getBoundsAsRectF());
    }

    public float getTopRightCornerResolvedSize() {
        return this.drawableState.shapeAppearanceModel.getTopRightCornerSize().getCornerSize(this.getBoundsAsRectF());
    }

    public float getTranslationZ() {
        return this.drawableState.translationZ;
    }

    @Override  // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        Rect rect0 = this.getBounds();
        this.transparentRegion.set(rect0);
        this.calculatePath(this.getBoundsAsRectF(), this.path);
        this.scratchRegion.setPath(this.path, this.transparentRegion);
        this.transparentRegion.op(this.scratchRegion, Region.Op.DIFFERENCE);
        return this.transparentRegion;
    }

    public float getZ() {
        return this.getElevation() + this.getTranslationZ();
    }

    // 去混淆评级： 低(20)
    private boolean hasCompatShadow() {
        return this.drawableState.shadowCompatMode != 1 && this.drawableState.shadowCompatRadius > 0 && (this.drawableState.shadowCompatMode == 2 || this.requiresCompatShadow());
    }

    private boolean hasFill() {
        return this.drawableState.paintStyle == Paint.Style.FILL_AND_STROKE || this.drawableState.paintStyle == Paint.Style.FILL;
    }

    private boolean hasStroke() {
        return (this.drawableState.paintStyle == Paint.Style.FILL_AND_STROKE || this.drawableState.paintStyle == Paint.Style.STROKE) && this.strokePaint.getStrokeWidth() > 0.0f;
    }

    public void initializeElevationOverlay(Context context0) {
        MaterialShapeDrawableState materialShapeDrawable$MaterialShapeDrawableState0 = this.drawableState;
        materialShapeDrawable$MaterialShapeDrawableState0.elevationOverlayProvider = new ElevationOverlayProvider(context0);
        this.updateZ();
    }

    @Override  // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        this.pathDirty = true;
        super.invalidateSelf();
    }

    private void invalidateSelfIgnoreShape() {
        super.invalidateSelf();
    }

    public boolean isElevationOverlayEnabled() {
        return this.drawableState.elevationOverlayProvider != null && this.drawableState.elevationOverlayProvider.isThemeElevationOverlayEnabled();
    }

    public boolean isElevationOverlayInitialized() {
        return this.drawableState.elevationOverlayProvider != null;
    }

    public boolean isPointInTransparentRegion(int v, int v1) {
        return this.getTransparentRegion().contains(v, v1);
    }

    public boolean isRoundRect() {
        return this.drawableState.shapeAppearanceModel.isRoundRect(this.getBoundsAsRectF());
    }

    @Deprecated
    public boolean isShadowEnabled() {
        return this.drawableState.shadowCompatMode == 0 || this.drawableState.shadowCompatMode == 2;
    }

    // 去混淆评级： 中等(90)
    @Override  // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return super.isStateful() || this.drawableState.tintList != null && this.drawableState.tintList.isStateful() || this.drawableState.strokeTintList != null && this.drawableState.strokeTintList.isStateful() || this.drawableState.strokeColor != null && this.drawableState.strokeColor.isStateful() || this.drawableState.fillColor != null && this.drawableState.fillColor.isStateful();
    }

    private void maybeDrawCompatShadow(Canvas canvas0) {
        if(!this.hasCompatShadow()) {
            return;
        }
        canvas0.save();
        this.prepareCanvasForShadow(canvas0);
        if(!this.shadowBitmapDrawingEnable) {
            this.drawCompatShadow(canvas0);
            canvas0.restore();
            return;
        }
        int v = (int)(this.pathBounds.width() - ((float)this.getBounds().width()));
        int v1 = (int)(this.pathBounds.height() - ((float)this.getBounds().height()));
        if(v < 0 || v1 < 0) {
            throw new IllegalStateException("Invalid shadow bounds. Check that the treatments result in a valid path.");
        }
        Bitmap bitmap0 = Bitmap.createBitmap(((int)this.pathBounds.width()) + this.drawableState.shadowCompatRadius * 2 + v, ((int)this.pathBounds.height()) + this.drawableState.shadowCompatRadius * 2 + v1, Bitmap.Config.ARGB_8888);
        Canvas canvas1 = new Canvas(bitmap0);
        float f = (float)(this.getBounds().left - this.drawableState.shadowCompatRadius - v);
        float f1 = (float)(this.getBounds().top - this.drawableState.shadowCompatRadius - v1);
        canvas1.translate(-f, -f1);
        this.drawCompatShadow(canvas1);
        canvas0.drawBitmap(bitmap0, f, f1, null);
        bitmap0.recycle();
        canvas0.restore();
    }

    private static int modulateAlpha(int v, int v1) [...] // Inlined contents

    @Override  // android.graphics.drawable.Drawable
    public Drawable mutate() {
        this.drawableState = new MaterialShapeDrawableState(this.drawableState);
        return this;
    }

    @Override  // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect0) {
        this.pathDirty = true;
        super.onBoundsChange(rect0);
    }

    @Override  // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] arr_v) {
        boolean z = this.updateColorsForState(arr_v) || this.updateTintFilter();
        if(z) {
            this.invalidateSelf();
        }
        return z;
    }

    private void prepareCanvasForShadow(Canvas canvas0) {
        canvas0.translate(((float)this.getShadowOffsetX()), ((float)this.getShadowOffsetY()));
    }

    // 去混淆评级： 低(20)
    public boolean requiresCompatShadow() {
        return !this.isRoundRect() && !this.path.isConvex() && Build.VERSION.SDK_INT < 29;
    }

    @Override  // android.graphics.drawable.Drawable
    public void setAlpha(int v) {
        if(this.drawableState.alpha != v) {
            this.drawableState.alpha = v;
            this.invalidateSelfIgnoreShape();
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter0) {
        this.drawableState.colorFilter = colorFilter0;
        this.invalidateSelfIgnoreShape();
    }

    public void setCornerSize(float f) {
        this.setShapeAppearanceModel(this.drawableState.shapeAppearanceModel.withCornerSize(f));
    }

    public void setCornerSize(CornerSize cornerSize0) {
        this.setShapeAppearanceModel(this.drawableState.shapeAppearanceModel.withCornerSize(cornerSize0));
    }

    public void setEdgeIntersectionCheckEnable(boolean z) {
        this.pathProvider.setEdgeIntersectionCheckEnable(z);
    }

    public void setElevation(float f) {
        if(this.drawableState.elevation != f) {
            this.drawableState.elevation = f;
            this.updateZ();
        }
    }

    public void setFillColor(ColorStateList colorStateList0) {
        if(this.drawableState.fillColor != colorStateList0) {
            this.drawableState.fillColor = colorStateList0;
            this.onStateChange(this.getState());
        }
    }

    public void setInterpolation(float f) {
        if(this.drawableState.interpolation != f) {
            this.drawableState.interpolation = f;
            this.pathDirty = true;
            this.invalidateSelf();
        }
    }

    public void setPadding(int v, int v1, int v2, int v3) {
        if(this.drawableState.padding == null) {
            MaterialShapeDrawableState materialShapeDrawable$MaterialShapeDrawableState0 = this.drawableState;
            materialShapeDrawable$MaterialShapeDrawableState0.padding = new Rect();
        }
        this.drawableState.padding.set(v, v1, v2, v3);
        this.invalidateSelf();
    }

    public void setPaintStyle(Paint.Style paint$Style0) {
        this.drawableState.paintStyle = paint$Style0;
        this.invalidateSelfIgnoreShape();
    }

    public void setParentAbsoluteElevation(float f) {
        if(this.drawableState.parentAbsoluteElevation != f) {
            this.drawableState.parentAbsoluteElevation = f;
            this.updateZ();
        }
    }

    public void setScale(float f) {
        if(this.drawableState.scale != f) {
            this.drawableState.scale = f;
            this.invalidateSelf();
        }
    }

    public void setShadowBitmapDrawingEnable(boolean z) {
        this.shadowBitmapDrawingEnable = z;
    }

    public void setShadowColor(int v) {
        this.shadowRenderer.setShadowColor(v);
        this.drawableState.useTintColorForShadow = false;
        this.invalidateSelfIgnoreShape();
    }

    public void setShadowCompatRotation(int v) {
        if(this.drawableState.shadowCompatRotation != v) {
            this.drawableState.shadowCompatRotation = v;
            this.invalidateSelfIgnoreShape();
        }
    }

    public void setShadowCompatibilityMode(int v) {
        if(this.drawableState.shadowCompatMode != v) {
            this.drawableState.shadowCompatMode = v;
            this.invalidateSelfIgnoreShape();
        }
    }

    @Deprecated
    public void setShadowElevation(int v) {
        this.setElevation(((float)v));
    }

    @Deprecated
    public void setShadowEnabled(boolean z) {
        this.setShadowCompatibilityMode(!z);
    }

    @Deprecated
    public void setShadowRadius(int v) {
        this.drawableState.shadowCompatRadius = v;
    }

    public void setShadowVerticalOffset(int v) {
        if(this.drawableState.shadowCompatOffset != v) {
            this.drawableState.shadowCompatOffset = v;
            this.invalidateSelfIgnoreShape();
        }
    }

    @Override  // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel0) {
        this.drawableState.shapeAppearanceModel = shapeAppearanceModel0;
        this.invalidateSelf();
    }

    @Deprecated
    public void setShapedViewModel(ShapePathModel shapePathModel0) {
        this.setShapeAppearanceModel(shapePathModel0);
    }

    public void setStroke(float f, int v) {
        this.setStrokeWidth(f);
        this.setStrokeColor(ColorStateList.valueOf(v));
    }

    public void setStroke(float f, ColorStateList colorStateList0) {
        this.setStrokeWidth(f);
        this.setStrokeColor(colorStateList0);
    }

    public void setStrokeColor(ColorStateList colorStateList0) {
        if(this.drawableState.strokeColor != colorStateList0) {
            this.drawableState.strokeColor = colorStateList0;
            this.onStateChange(this.getState());
        }
    }

    public void setStrokeTint(int v) {
        this.setStrokeTint(ColorStateList.valueOf(v));
    }

    public void setStrokeTint(ColorStateList colorStateList0) {
        this.drawableState.strokeTintList = colorStateList0;
        this.updateTintFilter();
        this.invalidateSelfIgnoreShape();
    }

    public void setStrokeWidth(float f) {
        this.drawableState.strokeWidth = f;
        this.invalidateSelf();
    }

    @Override  // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTint(int v) {
        this.setTintList(ColorStateList.valueOf(v));
    }

    @Override  // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintList(ColorStateList colorStateList0) {
        this.drawableState.tintList = colorStateList0;
        this.updateTintFilter();
        this.invalidateSelfIgnoreShape();
    }

    @Override  // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintMode(PorterDuff.Mode porterDuff$Mode0) {
        if(this.drawableState.tintMode != porterDuff$Mode0) {
            this.drawableState.tintMode = porterDuff$Mode0;
            this.updateTintFilter();
            this.invalidateSelfIgnoreShape();
        }
    }

    public void setTranslationZ(float f) {
        if(this.drawableState.translationZ != f) {
            this.drawableState.translationZ = f;
            this.updateZ();
        }
    }

    public void setUseTintColorForShadow(boolean z) {
        if(this.drawableState.useTintColorForShadow != z) {
            this.drawableState.useTintColorForShadow = z;
            this.invalidateSelf();
        }
    }

    public void setZ(float f) {
        this.setTranslationZ(f - this.getElevation());
    }

    private boolean updateColorsForState(int[] arr_v) {
        boolean z;
        if(this.drawableState.fillColor == null) {
            z = false;
        }
        else {
            int v = this.fillPaint.getColor();
            int v1 = this.drawableState.fillColor.getColorForState(arr_v, v);
            if(v == v1) {
                z = false;
            }
            else {
                this.fillPaint.setColor(v1);
                z = true;
            }
        }
        if(this.drawableState.strokeColor != null) {
            int v2 = this.strokePaint.getColor();
            int v3 = this.drawableState.strokeColor.getColorForState(arr_v, v2);
            if(v2 != v3) {
                this.strokePaint.setColor(v3);
                return true;
            }
        }
        return z;
    }

    private boolean updateTintFilter() {
        PorterDuffColorFilter porterDuffColorFilter0 = this.tintFilter;
        PorterDuffColorFilter porterDuffColorFilter1 = this.strokeTintFilter;
        this.tintFilter = this.calculateTintFilter(this.drawableState.tintList, this.drawableState.tintMode, this.fillPaint, true);
        this.strokeTintFilter = this.calculateTintFilter(this.drawableState.strokeTintList, this.drawableState.tintMode, this.strokePaint, false);
        if(this.drawableState.useTintColorForShadow) {
            int v = this.drawableState.tintList.getColorForState(this.getState(), 0);
            this.shadowRenderer.setShadowColor(v);
        }
        return !ObjectsCompat.equals(porterDuffColorFilter0, this.tintFilter) || !ObjectsCompat.equals(porterDuffColorFilter1, this.strokeTintFilter);
    }

    private void updateZ() {
        float f = this.getZ();
        this.drawableState.shadowCompatRadius = (int)Math.ceil(0.75f * f);
        this.drawableState.shadowCompatOffset = (int)Math.ceil(f * 0.25f);
        this.updateTintFilter();
        this.invalidateSelfIgnoreShape();
    }
}

