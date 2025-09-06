package com.google.android.material.chip;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build.VERSION;
import android.text.TextUtils.TruncateAt;
import android.text.TextUtils;
import android.util.AttributeSet;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.graphics.drawable.TintAwareDrawable;
import androidx.core.text.BidiFormatter;
import com.google.android.material.R.attr;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.canvas.CanvasCompat;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import java.lang.ref.WeakReference;
import java.util.Arrays;

public class ChipDrawable extends MaterialShapeDrawable implements Drawable.Callback, TintAwareDrawable, TextDrawableDelegate {
    public interface Delegate {
        void onChipDrawableSizeChange();
    }

    private static final boolean DEBUG = false;
    private static final int[] DEFAULT_STATE = null;
    private static final int MAX_CHIP_ICON_HEIGHT = 24;
    private static final String NAMESPACE_APP = "http://schemas.android.com/apk/res-auto";
    private int alpha;
    private boolean checkable;
    private Drawable checkedIcon;
    private ColorStateList checkedIconTint;
    private boolean checkedIconVisible;
    private ColorStateList chipBackgroundColor;
    private float chipCornerRadius;
    private float chipEndPadding;
    private Drawable chipIcon;
    private float chipIconSize;
    private ColorStateList chipIconTint;
    private boolean chipIconVisible;
    private float chipMinHeight;
    private final Paint chipPaint;
    private float chipStartPadding;
    private ColorStateList chipStrokeColor;
    private float chipStrokeWidth;
    private ColorStateList chipSurfaceColor;
    private Drawable closeIcon;
    private CharSequence closeIconContentDescription;
    private float closeIconEndPadding;
    private Drawable closeIconRipple;
    private static final ShapeDrawable closeIconRippleMask;
    private float closeIconSize;
    private float closeIconStartPadding;
    private int[] closeIconStateSet;
    private ColorStateList closeIconTint;
    private boolean closeIconVisible;
    private ColorFilter colorFilter;
    private ColorStateList compatRippleColor;
    private final Context context;
    private boolean currentChecked;
    private int currentChipBackgroundColor;
    private int currentChipStrokeColor;
    private int currentChipSurfaceColor;
    private int currentCompatRippleColor;
    private int currentCompositeSurfaceBackgroundColor;
    private int currentTextColor;
    private int currentTint;
    private final Paint debugPaint;
    private WeakReference delegate;
    private final Paint.FontMetrics fontMetrics;
    private boolean hasChipIconTint;
    private MotionSpec hideMotionSpec;
    private float iconEndPadding;
    private float iconStartPadding;
    private boolean isShapeThemingEnabled;
    private int maxWidth;
    private final PointF pointF;
    private final RectF rectF;
    private ColorStateList rippleColor;
    private final Path shapePath;
    private boolean shouldDrawText;
    private MotionSpec showMotionSpec;
    private CharSequence text;
    private final TextDrawableHelper textDrawableHelper;
    private float textEndPadding;
    private float textStartPadding;
    private ColorStateList tint;
    private PorterDuffColorFilter tintFilter;
    private PorterDuff.Mode tintMode;
    private TextUtils.TruncateAt truncateAt;
    private boolean useCompatRipple;

    static {
        ChipDrawable.DEFAULT_STATE = new int[]{0x101009E};
        ChipDrawable.closeIconRippleMask = new ShapeDrawable(new OvalShape());
    }

    private ChipDrawable(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        this.chipCornerRadius = -1.0f;
        this.chipPaint = new Paint(1);
        this.fontMetrics = new Paint.FontMetrics();
        this.rectF = new RectF();
        this.pointF = new PointF();
        this.shapePath = new Path();
        this.alpha = 0xFF;
        this.tintMode = PorterDuff.Mode.SRC_IN;
        this.delegate = new WeakReference(null);
        this.initializeElevationOverlay(context0);
        this.context = context0;
        TextDrawableHelper textDrawableHelper0 = new TextDrawableHelper(this);
        this.textDrawableHelper = textDrawableHelper0;
        this.text = "";
        textDrawableHelper0.getTextPaint().density = context0.getResources().getDisplayMetrics().density;
        this.debugPaint = null;
        this.setState(ChipDrawable.DEFAULT_STATE);
        this.setCloseIconState(ChipDrawable.DEFAULT_STATE);
        this.shouldDrawText = true;
        if(RippleUtils.USE_FRAMEWORK_RIPPLE) {
            ChipDrawable.closeIconRippleMask.setTint(-1);
        }
    }

    private void applyChildDrawable(Drawable drawable0) {
        if(drawable0 != null) {
            drawable0.setCallback(this);
            DrawableCompat.setLayoutDirection(drawable0, DrawableCompat.getLayoutDirection(this));
            drawable0.setLevel(this.getLevel());
            drawable0.setVisible(this.isVisible(), false);
            if(drawable0 == this.closeIcon) {
                if(drawable0.isStateful()) {
                    drawable0.setState(this.getCloseIconState());
                }
                DrawableCompat.setTintList(drawable0, this.closeIconTint);
                return;
            }
            Drawable drawable1 = this.chipIcon;
            if(drawable0 == drawable1 && this.hasChipIconTint) {
                DrawableCompat.setTintList(drawable1, this.chipIconTint);
            }
            if(drawable0.isStateful()) {
                drawable0.setState(this.getState());
            }
        }
    }

    private void calculateChipIconBounds(Rect rect0, RectF rectF0) {
        rectF0.setEmpty();
        if(!this.showsChipIcon() && !this.showsCheckedIcon()) {
            return;
        }
        float f = this.chipStartPadding + this.iconStartPadding;
        float f1 = this.getCurrentChipIconWidth();
        if(DrawableCompat.getLayoutDirection(this) == 0) {
            rectF0.left = ((float)rect0.left) + f;
            rectF0.right = rectF0.left + f1;
        }
        else {
            rectF0.right = ((float)rect0.right) - f;
            rectF0.left = rectF0.right - f1;
        }
        float f2 = this.getCurrentChipIconHeight();
        rectF0.top = rect0.exactCenterY() - f2 / 2.0f;
        rectF0.bottom = rectF0.top + f2;
    }

    // 去混淆评级： 低(20)
    float calculateChipIconWidth() {
        return this.showsChipIcon() || this.showsCheckedIcon() ? this.iconStartPadding + this.getCurrentChipIconWidth() + this.iconEndPadding : 0.0f;
    }

    private void calculateChipTouchBounds(Rect rect0, RectF rectF0) {
        rectF0.set(rect0);
        if(this.showsCloseIcon()) {
            float f = this.chipEndPadding + this.closeIconEndPadding + this.closeIconSize + this.closeIconStartPadding + this.textEndPadding;
            if(DrawableCompat.getLayoutDirection(this) == 0) {
                rectF0.right = ((float)rect0.right) - f;
                return;
            }
            rectF0.left = ((float)rect0.left) + f;
        }
    }

    private void calculateCloseIconBounds(Rect rect0, RectF rectF0) {
        rectF0.setEmpty();
        if(this.showsCloseIcon()) {
            float f = this.chipEndPadding + this.closeIconEndPadding;
            if(DrawableCompat.getLayoutDirection(this) == 0) {
                rectF0.right = ((float)rect0.right) - f;
                rectF0.left = rectF0.right - this.closeIconSize;
            }
            else {
                rectF0.left = ((float)rect0.left) + f;
                rectF0.right = rectF0.left + this.closeIconSize;
            }
            rectF0.top = rect0.exactCenterY() - this.closeIconSize / 2.0f;
            rectF0.bottom = rectF0.top + this.closeIconSize;
        }
    }

    private void calculateCloseIconTouchBounds(Rect rect0, RectF rectF0) {
        rectF0.setEmpty();
        if(this.showsCloseIcon()) {
            float f = this.chipEndPadding + this.closeIconEndPadding + this.closeIconSize + this.closeIconStartPadding + this.textEndPadding;
            if(DrawableCompat.getLayoutDirection(this) == 0) {
                rectF0.right = (float)rect0.right;
                rectF0.left = rectF0.right - f;
            }
            else {
                rectF0.left = (float)rect0.left;
                rectF0.right = ((float)rect0.left) + f;
            }
            rectF0.top = (float)rect0.top;
            rectF0.bottom = (float)rect0.bottom;
        }
    }

    // 去混淆评级： 低(20)
    float calculateCloseIconWidth() {
        return this.showsCloseIcon() ? this.closeIconStartPadding + this.closeIconSize + this.closeIconEndPadding : 0.0f;
    }

    private void calculateTextBounds(Rect rect0, RectF rectF0) {
        rectF0.setEmpty();
        if(this.text != null) {
            float f = this.chipStartPadding + this.calculateChipIconWidth() + this.textStartPadding;
            float f1 = this.chipEndPadding + this.calculateCloseIconWidth() + this.textEndPadding;
            if(DrawableCompat.getLayoutDirection(this) == 0) {
                rectF0.left = ((float)rect0.left) + f;
                rectF0.right = ((float)rect0.right) - f1;
            }
            else {
                rectF0.left = ((float)rect0.left) + f1;
                rectF0.right = ((float)rect0.right) - f;
            }
            rectF0.top = (float)rect0.top;
            rectF0.bottom = (float)rect0.bottom;
        }
    }

    private float calculateTextCenterFromBaseline() {
        this.textDrawableHelper.getTextPaint().getFontMetrics(this.fontMetrics);
        return (this.fontMetrics.descent + this.fontMetrics.ascent) / 2.0f;
    }

    Paint.Align calculateTextOriginAndAlignment(Rect rect0, PointF pointF0) {
        pointF0.set(0.0f, 0.0f);
        Paint.Align paint$Align0 = Paint.Align.LEFT;
        if(this.text != null) {
            float f = this.chipStartPadding + this.calculateChipIconWidth() + this.textStartPadding;
            if(DrawableCompat.getLayoutDirection(this) == 0) {
                pointF0.x = ((float)rect0.left) + f;
                paint$Align0 = Paint.Align.LEFT;
            }
            else {
                pointF0.x = ((float)rect0.right) - f;
                paint$Align0 = Paint.Align.RIGHT;
            }
            pointF0.y = ((float)rect0.centerY()) - this.calculateTextCenterFromBaseline();
        }
        return paint$Align0;
    }

    // 去混淆评级： 低(20)
    private boolean canShowCheckedIcon() {
        return this.checkedIconVisible && this.checkedIcon != null && this.checkable;
    }

    public static ChipDrawable createFromAttributes(Context context0, AttributeSet attributeSet0, int v, int v1) {
        ChipDrawable chipDrawable0 = new ChipDrawable(context0, attributeSet0, v, v1);
        chipDrawable0.loadFromAttributes(attributeSet0, v, v1);
        return chipDrawable0;
    }

    public static ChipDrawable createFromResource(Context context0, int v) {
        AttributeSet attributeSet0 = DrawableUtils.parseDrawableXml(context0, v, "chip");
        int v1 = attributeSet0.getStyleAttribute();
        if(v1 == 0) {
            v1 = style.Widget_MaterialComponents_Chip_Entry;
        }
        return ChipDrawable.createFromAttributes(context0, attributeSet0, attr.chipStandaloneStyle, v1);
    }

    @Override  // com.google.android.material.shape.MaterialShapeDrawable
    public void draw(Canvas canvas0) {
        Rect rect0 = this.getBounds();
        if(!rect0.isEmpty() && this.getAlpha() != 0) {
            int v = this.alpha >= 0xFF ? 0 : CanvasCompat.saveLayerAlpha(canvas0, ((float)rect0.left), ((float)rect0.top), ((float)rect0.right), ((float)rect0.bottom), this.alpha);
            this.drawChipSurface(canvas0, rect0);
            this.drawChipBackground(canvas0, rect0);
            if(this.isShapeThemingEnabled) {
                super.draw(canvas0);
            }
            this.drawChipStroke(canvas0, rect0);
            this.drawCompatRipple(canvas0, rect0);
            this.drawChipIcon(canvas0, rect0);
            this.drawCheckedIcon(canvas0, rect0);
            if(this.shouldDrawText) {
                this.drawText(canvas0, rect0);
            }
            this.drawCloseIcon(canvas0, rect0);
            this.drawDebug(canvas0, rect0);
            if(this.alpha < 0xFF) {
                canvas0.restoreToCount(v);
            }
        }
    }

    private void drawCheckedIcon(Canvas canvas0, Rect rect0) {
        if(this.showsCheckedIcon()) {
            this.calculateChipIconBounds(rect0, this.rectF);
            float f = this.rectF.left;
            float f1 = this.rectF.top;
            canvas0.translate(f, f1);
            this.checkedIcon.setBounds(0, 0, ((int)this.rectF.width()), ((int)this.rectF.height()));
            this.checkedIcon.draw(canvas0);
            canvas0.translate(-f, -f1);
        }
    }

    private void drawChipBackground(Canvas canvas0, Rect rect0) {
        if(!this.isShapeThemingEnabled) {
            this.chipPaint.setColor(this.currentChipBackgroundColor);
            this.chipPaint.setStyle(Paint.Style.FILL);
            ColorFilter colorFilter0 = this.getTintColorFilter();
            this.chipPaint.setColorFilter(colorFilter0);
            this.rectF.set(rect0);
            float f = this.getChipCornerRadius();
            float f1 = this.getChipCornerRadius();
            canvas0.drawRoundRect(this.rectF, f, f1, this.chipPaint);
        }
    }

    private void drawChipIcon(Canvas canvas0, Rect rect0) {
        if(this.showsChipIcon()) {
            this.calculateChipIconBounds(rect0, this.rectF);
            float f = this.rectF.left;
            float f1 = this.rectF.top;
            canvas0.translate(f, f1);
            this.chipIcon.setBounds(0, 0, ((int)this.rectF.width()), ((int)this.rectF.height()));
            this.chipIcon.draw(canvas0);
            canvas0.translate(-f, -f1);
        }
    }

    private void drawChipStroke(Canvas canvas0, Rect rect0) {
        if(this.chipStrokeWidth > 0.0f && !this.isShapeThemingEnabled) {
            this.chipPaint.setColor(this.currentChipStrokeColor);
            this.chipPaint.setStyle(Paint.Style.STROKE);
            if(!this.isShapeThemingEnabled) {
                ColorFilter colorFilter0 = this.getTintColorFilter();
                this.chipPaint.setColorFilter(colorFilter0);
            }
            this.rectF.set(((float)rect0.left) + this.chipStrokeWidth / 2.0f, ((float)rect0.top) + this.chipStrokeWidth / 2.0f, ((float)rect0.right) - this.chipStrokeWidth / 2.0f, ((float)rect0.bottom) - this.chipStrokeWidth / 2.0f);
            float f = this.chipCornerRadius - this.chipStrokeWidth / 2.0f;
            canvas0.drawRoundRect(this.rectF, f, f, this.chipPaint);
        }
    }

    private void drawChipSurface(Canvas canvas0, Rect rect0) {
        if(!this.isShapeThemingEnabled) {
            this.chipPaint.setColor(this.currentChipSurfaceColor);
            this.chipPaint.setStyle(Paint.Style.FILL);
            this.rectF.set(rect0);
            float f = this.getChipCornerRadius();
            float f1 = this.getChipCornerRadius();
            canvas0.drawRoundRect(this.rectF, f, f1, this.chipPaint);
        }
    }

    private void drawCloseIcon(Canvas canvas0, Rect rect0) {
        if(this.showsCloseIcon()) {
            this.calculateCloseIconBounds(rect0, this.rectF);
            float f = this.rectF.left;
            float f1 = this.rectF.top;
            canvas0.translate(f, f1);
            this.closeIcon.setBounds(0, 0, ((int)this.rectF.width()), ((int)this.rectF.height()));
            if(RippleUtils.USE_FRAMEWORK_RIPPLE) {
                this.closeIconRipple.setBounds(this.closeIcon.getBounds());
                this.closeIconRipple.jumpToCurrentState();
                this.closeIconRipple.draw(canvas0);
            }
            else {
                this.closeIcon.draw(canvas0);
            }
            canvas0.translate(-f, -f1);
        }
    }

    private void drawCompatRipple(Canvas canvas0, Rect rect0) {
        this.chipPaint.setColor(this.currentCompatRippleColor);
        this.chipPaint.setStyle(Paint.Style.FILL);
        this.rectF.set(rect0);
        if(!this.isShapeThemingEnabled) {
            float f = this.getChipCornerRadius();
            float f1 = this.getChipCornerRadius();
            canvas0.drawRoundRect(this.rectF, f, f1, this.chipPaint);
            return;
        }
        this.calculatePathForSize(new RectF(rect0), this.shapePath);
        RectF rectF0 = this.getBoundsAsRectF();
        super.drawShape(canvas0, this.chipPaint, this.shapePath, rectF0);
    }

    private void drawDebug(Canvas canvas0, Rect rect0) {
        Paint paint0 = this.debugPaint;
        if(paint0 != null) {
            paint0.setColor(ColorUtils.setAlphaComponent(0xFF000000, 0x7F));
            canvas0.drawRect(rect0, this.debugPaint);
            if(this.showsChipIcon() || this.showsCheckedIcon()) {
                this.calculateChipIconBounds(rect0, this.rectF);
                canvas0.drawRect(this.rectF, this.debugPaint);
            }
            if(this.text != null) {
                canvas0.drawLine(((float)rect0.left), rect0.exactCenterY(), ((float)rect0.right), rect0.exactCenterY(), this.debugPaint);
            }
            if(this.showsCloseIcon()) {
                this.calculateCloseIconBounds(rect0, this.rectF);
                canvas0.drawRect(this.rectF, this.debugPaint);
            }
            int v = ColorUtils.setAlphaComponent(0xFFFF0000, 0x7F);
            this.debugPaint.setColor(v);
            this.calculateChipTouchBounds(rect0, this.rectF);
            canvas0.drawRect(this.rectF, this.debugPaint);
            int v1 = ColorUtils.setAlphaComponent(0xFF00FF00, 0x7F);
            this.debugPaint.setColor(v1);
            this.calculateCloseIconTouchBounds(rect0, this.rectF);
            canvas0.drawRect(this.rectF, this.debugPaint);
        }
    }

    private void drawText(Canvas canvas0, Rect rect0) {
        if(this.text != null) {
            Paint.Align paint$Align0 = this.calculateTextOriginAndAlignment(rect0, this.pointF);
            this.calculateTextBounds(rect0, this.rectF);
            if(this.textDrawableHelper.getTextAppearance() != null) {
                this.textDrawableHelper.getTextPaint().drawableState = this.getState();
                this.textDrawableHelper.updateTextPaintDrawState(this.context);
            }
            this.textDrawableHelper.getTextPaint().setTextAlign(paint$Align0);
            int v = 0;
            boolean z = Math.round(this.textDrawableHelper.getTextWidth(this.getText().toString())) > Math.round(this.rectF.width());
            if(z) {
                v = canvas0.save();
                canvas0.clipRect(this.rectF);
            }
            CharSequence charSequence0 = this.text;
            if(z && this.truncateAt != null) {
                float f = this.rectF.width();
                charSequence0 = TextUtils.ellipsize(charSequence0, this.textDrawableHelper.getTextPaint(), f, this.truncateAt);
            }
            canvas0.drawText(charSequence0, 0, charSequence0.length(), this.pointF.x, this.pointF.y, this.textDrawableHelper.getTextPaint());
            if(z) {
                canvas0.restoreToCount(v);
            }
        }
    }

    @Override  // com.google.android.material.shape.MaterialShapeDrawable
    public int getAlpha() {
        return this.alpha;
    }

    public Drawable getCheckedIcon() {
        return this.checkedIcon;
    }

    public ColorStateList getCheckedIconTint() {
        return this.checkedIconTint;
    }

    public ColorStateList getChipBackgroundColor() {
        return this.chipBackgroundColor;
    }

    // 去混淆评级： 低(20)
    public float getChipCornerRadius() {
        return this.isShapeThemingEnabled ? this.getTopLeftCornerResolvedSize() : this.chipCornerRadius;
    }

    public float getChipEndPadding() {
        return this.chipEndPadding;
    }

    public Drawable getChipIcon() {
        return this.chipIcon == null ? null : DrawableCompat.unwrap(this.chipIcon);
    }

    public float getChipIconSize() {
        return this.chipIconSize;
    }

    public ColorStateList getChipIconTint() {
        return this.chipIconTint;
    }

    public float getChipMinHeight() {
        return this.chipMinHeight;
    }

    public float getChipStartPadding() {
        return this.chipStartPadding;
    }

    public ColorStateList getChipStrokeColor() {
        return this.chipStrokeColor;
    }

    public float getChipStrokeWidth() {
        return this.chipStrokeWidth;
    }

    public void getChipTouchBounds(RectF rectF0) {
        this.calculateChipTouchBounds(this.getBounds(), rectF0);
    }

    public Drawable getCloseIcon() {
        return this.closeIcon == null ? null : DrawableCompat.unwrap(this.closeIcon);
    }

    public CharSequence getCloseIconContentDescription() {
        return this.closeIconContentDescription;
    }

    public float getCloseIconEndPadding() {
        return this.closeIconEndPadding;
    }

    public float getCloseIconSize() {
        return this.closeIconSize;
    }

    public float getCloseIconStartPadding() {
        return this.closeIconStartPadding;
    }

    public int[] getCloseIconState() {
        return this.closeIconStateSet;
    }

    public ColorStateList getCloseIconTint() {
        return this.closeIconTint;
    }

    public void getCloseIconTouchBounds(RectF rectF0) {
        this.calculateCloseIconTouchBounds(this.getBounds(), rectF0);
    }

    @Override  // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        return this.colorFilter;
    }

    private float getCurrentChipIconHeight() {
        Drawable drawable0 = this.currentChecked ? this.checkedIcon : this.chipIcon;
        float f = this.chipIconSize;
        if(f <= 0.0f && drawable0 != null) {
            f = (float)Math.ceil(ViewUtils.dpToPx(this.context, 24));
            return ((float)drawable0.getIntrinsicHeight()) <= f ? ((float)drawable0.getIntrinsicHeight()) : f;
        }
        return f;
    }

    private float getCurrentChipIconWidth() {
        Drawable drawable0 = this.currentChecked ? this.checkedIcon : this.chipIcon;
        return this.chipIconSize > 0.0f || drawable0 == null ? this.chipIconSize : ((float)drawable0.getIntrinsicWidth());
    }

    public TextUtils.TruncateAt getEllipsize() {
        return this.truncateAt;
    }

    public MotionSpec getHideMotionSpec() {
        return this.hideMotionSpec;
    }

    public float getIconEndPadding() {
        return this.iconEndPadding;
    }

    public float getIconStartPadding() {
        return this.iconStartPadding;
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int)this.chipMinHeight;
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return Math.min(Math.round(this.chipStartPadding + this.calculateChipIconWidth() + this.textStartPadding + this.textDrawableHelper.getTextWidth(this.getText().toString()) + this.textEndPadding + this.calculateCloseIconWidth() + this.chipEndPadding), this.maxWidth);
    }

    public int getMaxWidth() {
        return this.maxWidth;
    }

    @Override  // com.google.android.material.shape.MaterialShapeDrawable
    public int getOpacity() {
        return -3;
    }

    @Override  // com.google.android.material.shape.MaterialShapeDrawable
    public void getOutline(Outline outline0) {
        if(this.isShapeThemingEnabled) {
            super.getOutline(outline0);
            return;
        }
        Rect rect0 = this.getBounds();
        if(rect0.isEmpty()) {
            outline0.setRoundRect(0, 0, this.getIntrinsicWidth(), this.getIntrinsicHeight(), this.chipCornerRadius);
        }
        else {
            outline0.setRoundRect(rect0, this.chipCornerRadius);
        }
        outline0.setAlpha(((float)this.getAlpha()) / 255.0f);
    }

    public ColorStateList getRippleColor() {
        return this.rippleColor;
    }

    public MotionSpec getShowMotionSpec() {
        return this.showMotionSpec;
    }

    public CharSequence getText() {
        return this.text;
    }

    public TextAppearance getTextAppearance() {
        return this.textDrawableHelper.getTextAppearance();
    }

    public float getTextEndPadding() {
        return this.textEndPadding;
    }

    public float getTextStartPadding() {
        return this.textStartPadding;
    }

    private ColorFilter getTintColorFilter() {
        ColorFilter colorFilter0 = this.colorFilter;
        return colorFilter0 != null ? colorFilter0 : this.tintFilter;
    }

    public boolean getUseCompatRipple() {
        return this.useCompatRipple;
    }

    private static boolean hasState(int[] arr_v, int v) {
        if(arr_v == null) {
            return false;
        }
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            if(arr_v[v1] == v) {
                return true;
            }
        }
        return false;
    }

    @Override  // android.graphics.drawable.Drawable$Callback
    public void invalidateDrawable(Drawable drawable0) {
        Drawable.Callback drawable$Callback0 = this.getCallback();
        if(drawable$Callback0 != null) {
            drawable$Callback0.invalidateDrawable(this);
        }
    }

    public boolean isCheckable() {
        return this.checkable;
    }

    @Deprecated
    public boolean isCheckedIconEnabled() {
        return this.isCheckedIconVisible();
    }

    public boolean isCheckedIconVisible() {
        return this.checkedIconVisible;
    }

    @Deprecated
    public boolean isChipIconEnabled() {
        return this.isChipIconVisible();
    }

    public boolean isChipIconVisible() {
        return this.chipIconVisible;
    }

    @Deprecated
    public boolean isCloseIconEnabled() {
        return this.isCloseIconVisible();
    }

    public boolean isCloseIconStateful() {
        return ChipDrawable.isStateful(this.closeIcon);
    }

    public boolean isCloseIconVisible() {
        return this.closeIconVisible;
    }

    boolean isShapeThemingEnabled() {
        return this.isShapeThemingEnabled;
    }

    private static boolean isStateful(ColorStateList colorStateList0) {
        return colorStateList0 != null && colorStateList0.isStateful();
    }

    private static boolean isStateful(Drawable drawable0) {
        return drawable0 != null && drawable0.isStateful();
    }

    private static boolean isStateful(TextAppearance textAppearance0) {
        return textAppearance0 != null && textAppearance0.getTextColor() != null && textAppearance0.getTextColor().isStateful();
    }

    // 去混淆评级： 中等(100)
    @Override  // com.google.android.material.shape.MaterialShapeDrawable
    public boolean isStateful() {
        return ChipDrawable.isStateful(this.chipSurfaceColor) || ChipDrawable.isStateful(this.chipBackgroundColor) || ChipDrawable.isStateful(this.chipStrokeColor) || this.useCompatRipple && ChipDrawable.isStateful(this.compatRippleColor) || ChipDrawable.isStateful(this.textDrawableHelper.getTextAppearance()) || this.canShowCheckedIcon() || ChipDrawable.isStateful(this.chipIcon) || ChipDrawable.isStateful(this.checkedIcon) || ChipDrawable.isStateful(this.tint);
    }

    private void loadFromAttributes(AttributeSet attributeSet0, int v, int v1) {
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(this.context, attributeSet0, styleable.Chip, v, v1, new int[0]);
        this.isShapeThemingEnabled = typedArray0.hasValue(styleable.Chip_shapeAppearance);
        this.setChipSurfaceColor(MaterialResources.getColorStateList(this.context, typedArray0, styleable.Chip_chipSurfaceColor));
        this.setChipBackgroundColor(MaterialResources.getColorStateList(this.context, typedArray0, styleable.Chip_chipBackgroundColor));
        this.setChipMinHeight(typedArray0.getDimension(styleable.Chip_chipMinHeight, 0.0f));
        if(typedArray0.hasValue(styleable.Chip_chipCornerRadius)) {
            this.setChipCornerRadius(typedArray0.getDimension(styleable.Chip_chipCornerRadius, 0.0f));
        }
        this.setChipStrokeColor(MaterialResources.getColorStateList(this.context, typedArray0, styleable.Chip_chipStrokeColor));
        this.setChipStrokeWidth(typedArray0.getDimension(styleable.Chip_chipStrokeWidth, 0.0f));
        this.setRippleColor(MaterialResources.getColorStateList(this.context, typedArray0, styleable.Chip_rippleColor));
        this.setText(typedArray0.getText(styleable.Chip_android_text));
        TextAppearance textAppearance0 = MaterialResources.getTextAppearance(this.context, typedArray0, styleable.Chip_android_textAppearance);
        textAppearance0.setTextSize(typedArray0.getDimension(styleable.Chip_android_textSize, textAppearance0.getTextSize()));
        if(Build.VERSION.SDK_INT < 23) {
            textAppearance0.setTextColor(MaterialResources.getColorStateList(this.context, typedArray0, styleable.Chip_android_textColor));
        }
        this.setTextAppearance(textAppearance0);
        switch(typedArray0.getInt(styleable.Chip_android_ellipsize, 0)) {
            case 1: {
                this.setEllipsize(TextUtils.TruncateAt.START);
                break;
            }
            case 2: {
                this.setEllipsize(TextUtils.TruncateAt.MIDDLE);
                break;
            }
            case 3: {
                this.setEllipsize(TextUtils.TruncateAt.END);
            }
        }
        this.setChipIconVisible(typedArray0.getBoolean(styleable.Chip_chipIconVisible, false));
        if(attributeSet0 != null && attributeSet0.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconEnabled") != null && attributeSet0.getAttributeValue("http://schemas.android.com/apk/res-auto", "chipIconVisible") == null) {
            this.setChipIconVisible(typedArray0.getBoolean(styleable.Chip_chipIconEnabled, false));
        }
        this.setChipIcon(MaterialResources.getDrawable(this.context, typedArray0, styleable.Chip_chipIcon));
        if(typedArray0.hasValue(styleable.Chip_chipIconTint)) {
            this.setChipIconTint(MaterialResources.getColorStateList(this.context, typedArray0, styleable.Chip_chipIconTint));
        }
        this.setChipIconSize(typedArray0.getDimension(styleable.Chip_chipIconSize, -1.0f));
        this.setCloseIconVisible(typedArray0.getBoolean(styleable.Chip_closeIconVisible, false));
        if(attributeSet0 != null && attributeSet0.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconEnabled") != null && attributeSet0.getAttributeValue("http://schemas.android.com/apk/res-auto", "closeIconVisible") == null) {
            this.setCloseIconVisible(typedArray0.getBoolean(styleable.Chip_closeIconEnabled, false));
        }
        this.setCloseIcon(MaterialResources.getDrawable(this.context, typedArray0, styleable.Chip_closeIcon));
        this.setCloseIconTint(MaterialResources.getColorStateList(this.context, typedArray0, styleable.Chip_closeIconTint));
        this.setCloseIconSize(typedArray0.getDimension(styleable.Chip_closeIconSize, 0.0f));
        this.setCheckable(typedArray0.getBoolean(styleable.Chip_android_checkable, false));
        this.setCheckedIconVisible(typedArray0.getBoolean(styleable.Chip_checkedIconVisible, false));
        if(attributeSet0 != null && attributeSet0.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconEnabled") != null && attributeSet0.getAttributeValue("http://schemas.android.com/apk/res-auto", "checkedIconVisible") == null) {
            this.setCheckedIconVisible(typedArray0.getBoolean(styleable.Chip_checkedIconEnabled, false));
        }
        this.setCheckedIcon(MaterialResources.getDrawable(this.context, typedArray0, styleable.Chip_checkedIcon));
        if(typedArray0.hasValue(styleable.Chip_checkedIconTint)) {
            this.setCheckedIconTint(MaterialResources.getColorStateList(this.context, typedArray0, styleable.Chip_checkedIconTint));
        }
        this.setShowMotionSpec(MotionSpec.createFromAttribute(this.context, typedArray0, styleable.Chip_showMotionSpec));
        this.setHideMotionSpec(MotionSpec.createFromAttribute(this.context, typedArray0, styleable.Chip_hideMotionSpec));
        this.setChipStartPadding(typedArray0.getDimension(styleable.Chip_chipStartPadding, 0.0f));
        this.setIconStartPadding(typedArray0.getDimension(styleable.Chip_iconStartPadding, 0.0f));
        this.setIconEndPadding(typedArray0.getDimension(styleable.Chip_iconEndPadding, 0.0f));
        this.setTextStartPadding(typedArray0.getDimension(styleable.Chip_textStartPadding, 0.0f));
        this.setTextEndPadding(typedArray0.getDimension(styleable.Chip_textEndPadding, 0.0f));
        this.setCloseIconStartPadding(typedArray0.getDimension(styleable.Chip_closeIconStartPadding, 0.0f));
        this.setCloseIconEndPadding(typedArray0.getDimension(styleable.Chip_closeIconEndPadding, 0.0f));
        this.setChipEndPadding(typedArray0.getDimension(styleable.Chip_chipEndPadding, 0.0f));
        this.setMaxWidth(typedArray0.getDimensionPixelSize(styleable.Chip_android_maxWidth, 0x7FFFFFFF));
        typedArray0.recycle();
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean onLayoutDirectionChanged(int v) {
        boolean z = super.onLayoutDirectionChanged(v);
        if(this.showsChipIcon()) {
            z |= DrawableCompat.setLayoutDirection(this.chipIcon, v);
        }
        if(this.showsCheckedIcon()) {
            z |= DrawableCompat.setLayoutDirection(this.checkedIcon, v);
        }
        if(this.showsCloseIcon()) {
            z |= DrawableCompat.setLayoutDirection(this.closeIcon, v);
        }
        if(z) {
            this.invalidateSelf();
        }
        return true;
    }

    @Override  // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int v) {
        boolean z = super.onLevelChange(v);
        if(this.showsChipIcon()) {
            z |= this.chipIcon.setLevel(v);
        }
        if(this.showsCheckedIcon()) {
            z |= this.checkedIcon.setLevel(v);
        }
        if(this.showsCloseIcon()) {
            z |= this.closeIcon.setLevel(v);
        }
        if(z) {
            this.invalidateSelf();
        }
        return z;
    }

    protected void onSizeChange() {
        Delegate chipDrawable$Delegate0 = (Delegate)this.delegate.get();
        if(chipDrawable$Delegate0 != null) {
            chipDrawable$Delegate0.onChipDrawableSizeChange();
        }
    }

    private boolean onStateChange(int[] arr_v, int[] arr_v1) {
        boolean z3;
        boolean z = super.onStateChange(arr_v);
        int v = this.compositeElevationOverlayIfNeeded((this.chipSurfaceColor == null ? 0 : this.chipSurfaceColor.getColorForState(arr_v, this.currentChipSurfaceColor)));
        boolean z1 = true;
        if(this.currentChipSurfaceColor != v) {
            this.currentChipSurfaceColor = v;
            z = true;
        }
        int v1 = this.compositeElevationOverlayIfNeeded((this.chipBackgroundColor == null ? 0 : this.chipBackgroundColor.getColorForState(arr_v, this.currentChipBackgroundColor)));
        if(this.currentChipBackgroundColor != v1) {
            this.currentChipBackgroundColor = v1;
            z = true;
        }
        int v2 = MaterialColors.layer(v, v1);
        if(((this.currentCompositeSurfaceBackgroundColor == v2 ? 0 : 1) | (this.getFillColor() == null ? 1 : 0)) != 0) {
            this.currentCompositeSurfaceBackgroundColor = v2;
            this.setFillColor(ColorStateList.valueOf(v2));
            z = true;
        }
        int v3 = this.chipStrokeColor == null ? 0 : this.chipStrokeColor.getColorForState(arr_v, this.currentChipStrokeColor);
        if(this.currentChipStrokeColor != v3) {
            this.currentChipStrokeColor = v3;
            z = true;
        }
        int v4 = this.compatRippleColor == null || !RippleUtils.shouldDrawRippleCompat(arr_v) ? 0 : this.compatRippleColor.getColorForState(arr_v, this.currentCompatRippleColor);
        if(this.currentCompatRippleColor != v4) {
            this.currentCompatRippleColor = v4;
            if(this.useCompatRipple) {
                z = true;
            }
        }
        int v5 = this.textDrawableHelper.getTextAppearance() == null || this.textDrawableHelper.getTextAppearance().getTextColor() == null ? 0 : this.textDrawableHelper.getTextAppearance().getTextColor().getColorForState(arr_v, this.currentTextColor);
        if(this.currentTextColor != v5) {
            this.currentTextColor = v5;
            z = true;
        }
        boolean z2 = ChipDrawable.hasState(this.getState(), 0x10100A0) && this.checkable;
        if(this.currentChecked == z2 || this.checkedIcon == null) {
            z3 = false;
        }
        else {
            float f = this.calculateChipIconWidth();
            this.currentChecked = z2;
            if(f == this.calculateChipIconWidth()) {
                z = true;
                z3 = false;
            }
            else {
                z = true;
                z3 = true;
            }
        }
        int v6 = this.tint == null ? 0 : this.tint.getColorForState(arr_v, this.currentTint);
        if(this.currentTint == v6) {
            z1 = z;
        }
        else {
            this.currentTint = v6;
            this.tintFilter = DrawableUtils.updateTintFilter(this, this.tint, this.tintMode);
        }
        if(ChipDrawable.isStateful(this.chipIcon)) {
            z1 |= this.chipIcon.setState(arr_v);
        }
        if(ChipDrawable.isStateful(this.checkedIcon)) {
            z1 |= this.checkedIcon.setState(arr_v);
        }
        if(ChipDrawable.isStateful(this.closeIcon)) {
            int[] arr_v2 = new int[arr_v.length + arr_v1.length];
            System.arraycopy(arr_v, 0, arr_v2, 0, arr_v.length);
            System.arraycopy(arr_v1, 0, arr_v2, arr_v.length, arr_v1.length);
            z1 |= this.closeIcon.setState(arr_v2);
        }
        if(RippleUtils.USE_FRAMEWORK_RIPPLE && ChipDrawable.isStateful(this.closeIconRipple)) {
            z1 |= this.closeIconRipple.setState(arr_v1);
        }
        if(z1) {
            this.invalidateSelf();
        }
        if(z3) {
            this.onSizeChange();
        }
        return z1;
    }

    @Override  // com.google.android.material.shape.MaterialShapeDrawable, com.google.android.material.internal.TextDrawableHelper$TextDrawableDelegate
    public boolean onStateChange(int[] arr_v) {
        if(this.isShapeThemingEnabled) {
            super.onStateChange(arr_v);
        }
        return this.onStateChange(arr_v, this.getCloseIconState());
    }

    @Override  // com.google.android.material.internal.TextDrawableHelper$TextDrawableDelegate
    public void onTextSizeChange() {
        this.onSizeChange();
        this.invalidateSelf();
    }

    @Override  // android.graphics.drawable.Drawable$Callback
    public void scheduleDrawable(Drawable drawable0, Runnable runnable0, long v) {
        Drawable.Callback drawable$Callback0 = this.getCallback();
        if(drawable$Callback0 != null) {
            drawable$Callback0.scheduleDrawable(this, runnable0, v);
        }
    }

    @Override  // com.google.android.material.shape.MaterialShapeDrawable
    public void setAlpha(int v) {
        if(this.alpha != v) {
            this.alpha = v;
            this.invalidateSelf();
        }
    }

    public void setCheckable(boolean z) {
        if(this.checkable != z) {
            this.checkable = z;
            float f = this.calculateChipIconWidth();
            if(!z && this.currentChecked) {
                this.currentChecked = false;
            }
            float f1 = this.calculateChipIconWidth();
            this.invalidateSelf();
            if(f != f1) {
                this.onSizeChange();
            }
        }
    }

    public void setCheckableResource(int v) {
        this.setCheckable(this.context.getResources().getBoolean(v));
    }

    public void setCheckedIcon(Drawable drawable0) {
        if(this.checkedIcon != drawable0) {
            float f = this.calculateChipIconWidth();
            this.checkedIcon = drawable0;
            float f1 = this.calculateChipIconWidth();
            this.unapplyChildDrawable(this.checkedIcon);
            this.applyChildDrawable(this.checkedIcon);
            this.invalidateSelf();
            if(f != f1) {
                this.onSizeChange();
            }
        }
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z) {
        this.setCheckedIconVisible(z);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(int v) {
        this.setCheckedIconVisible(this.context.getResources().getBoolean(v));
    }

    public void setCheckedIconResource(int v) {
        this.setCheckedIcon(AppCompatResources.getDrawable(this.context, v));
    }

    public void setCheckedIconTint(ColorStateList colorStateList0) {
        if(this.checkedIconTint != colorStateList0) {
            this.checkedIconTint = colorStateList0;
            if(this.canShowCheckedIcon()) {
                DrawableCompat.setTintList(this.checkedIcon, colorStateList0);
            }
            this.onStateChange(this.getState());
        }
    }

    public void setCheckedIconTintResource(int v) {
        this.setCheckedIconTint(AppCompatResources.getColorStateList(this.context, v));
    }

    public void setCheckedIconVisible(int v) {
        this.setCheckedIconVisible(this.context.getResources().getBoolean(v));
    }

    public void setCheckedIconVisible(boolean z) {
        if(this.checkedIconVisible != z) {
            boolean z1 = this.showsCheckedIcon();
            this.checkedIconVisible = z;
            boolean z2 = this.showsCheckedIcon();
            if(z1 != z2) {
                if(z2) {
                    this.applyChildDrawable(this.checkedIcon);
                }
                else {
                    this.unapplyChildDrawable(this.checkedIcon);
                }
                this.invalidateSelf();
                this.onSizeChange();
            }
        }
    }

    public void setChipBackgroundColor(ColorStateList colorStateList0) {
        if(this.chipBackgroundColor != colorStateList0) {
            this.chipBackgroundColor = colorStateList0;
            this.onStateChange(this.getState());
        }
    }

    public void setChipBackgroundColorResource(int v) {
        this.setChipBackgroundColor(AppCompatResources.getColorStateList(this.context, v));
    }

    @Deprecated
    public void setChipCornerRadius(float f) {
        if(this.chipCornerRadius != f) {
            this.chipCornerRadius = f;
            this.setShapeAppearanceModel(this.getShapeAppearanceModel().withCornerSize(f));
        }
    }

    @Deprecated
    public void setChipCornerRadiusResource(int v) {
        this.setChipCornerRadius(this.context.getResources().getDimension(v));
    }

    public void setChipEndPadding(float f) {
        if(this.chipEndPadding != f) {
            this.chipEndPadding = f;
            this.invalidateSelf();
            this.onSizeChange();
        }
    }

    public void setChipEndPaddingResource(int v) {
        this.setChipEndPadding(this.context.getResources().getDimension(v));
    }

    public void setChipIcon(Drawable drawable0) {
        Drawable drawable1 = this.getChipIcon();
        if(drawable1 != drawable0) {
            float f = this.calculateChipIconWidth();
            this.chipIcon = drawable0 == null ? null : DrawableCompat.wrap(drawable0).mutate();
            float f1 = this.calculateChipIconWidth();
            this.unapplyChildDrawable(drawable1);
            if(this.showsChipIcon()) {
                this.applyChildDrawable(this.chipIcon);
            }
            this.invalidateSelf();
            if(f != f1) {
                this.onSizeChange();
            }
        }
    }

    @Deprecated
    public void setChipIconEnabled(boolean z) {
        this.setChipIconVisible(z);
    }

    @Deprecated
    public void setChipIconEnabledResource(int v) {
        this.setChipIconVisible(v);
    }

    public void setChipIconResource(int v) {
        this.setChipIcon(AppCompatResources.getDrawable(this.context, v));
    }

    public void setChipIconSize(float f) {
        if(this.chipIconSize != f) {
            float f1 = this.calculateChipIconWidth();
            this.chipIconSize = f;
            float f2 = this.calculateChipIconWidth();
            this.invalidateSelf();
            if(f1 != f2) {
                this.onSizeChange();
            }
        }
    }

    public void setChipIconSizeResource(int v) {
        this.setChipIconSize(this.context.getResources().getDimension(v));
    }

    public void setChipIconTint(ColorStateList colorStateList0) {
        this.hasChipIconTint = true;
        if(this.chipIconTint != colorStateList0) {
            this.chipIconTint = colorStateList0;
            if(this.showsChipIcon()) {
                DrawableCompat.setTintList(this.chipIcon, colorStateList0);
            }
            this.onStateChange(this.getState());
        }
    }

    public void setChipIconTintResource(int v) {
        this.setChipIconTint(AppCompatResources.getColorStateList(this.context, v));
    }

    public void setChipIconVisible(int v) {
        this.setChipIconVisible(this.context.getResources().getBoolean(v));
    }

    public void setChipIconVisible(boolean z) {
        if(this.chipIconVisible != z) {
            boolean z1 = this.showsChipIcon();
            this.chipIconVisible = z;
            boolean z2 = this.showsChipIcon();
            if(z1 != z2) {
                if(z2) {
                    this.applyChildDrawable(this.chipIcon);
                }
                else {
                    this.unapplyChildDrawable(this.chipIcon);
                }
                this.invalidateSelf();
                this.onSizeChange();
            }
        }
    }

    public void setChipMinHeight(float f) {
        if(this.chipMinHeight != f) {
            this.chipMinHeight = f;
            this.invalidateSelf();
            this.onSizeChange();
        }
    }

    public void setChipMinHeightResource(int v) {
        this.setChipMinHeight(this.context.getResources().getDimension(v));
    }

    public void setChipStartPadding(float f) {
        if(this.chipStartPadding != f) {
            this.chipStartPadding = f;
            this.invalidateSelf();
            this.onSizeChange();
        }
    }

    public void setChipStartPaddingResource(int v) {
        this.setChipStartPadding(this.context.getResources().getDimension(v));
    }

    public void setChipStrokeColor(ColorStateList colorStateList0) {
        if(this.chipStrokeColor != colorStateList0) {
            this.chipStrokeColor = colorStateList0;
            if(this.isShapeThemingEnabled) {
                this.setStrokeColor(colorStateList0);
            }
            this.onStateChange(this.getState());
        }
    }

    public void setChipStrokeColorResource(int v) {
        this.setChipStrokeColor(AppCompatResources.getColorStateList(this.context, v));
    }

    public void setChipStrokeWidth(float f) {
        if(this.chipStrokeWidth != f) {
            this.chipStrokeWidth = f;
            this.chipPaint.setStrokeWidth(f);
            if(this.isShapeThemingEnabled) {
                super.setStrokeWidth(f);
            }
            this.invalidateSelf();
        }
    }

    public void setChipStrokeWidthResource(int v) {
        this.setChipStrokeWidth(this.context.getResources().getDimension(v));
    }

    private void setChipSurfaceColor(ColorStateList colorStateList0) {
        if(this.chipSurfaceColor != colorStateList0) {
            this.chipSurfaceColor = colorStateList0;
            this.onStateChange(this.getState());
        }
    }

    public void setCloseIcon(Drawable drawable0) {
        Drawable drawable1 = this.getCloseIcon();
        if(drawable1 != drawable0) {
            float f = this.calculateCloseIconWidth();
            this.closeIcon = drawable0 == null ? null : DrawableCompat.wrap(drawable0).mutate();
            if(RippleUtils.USE_FRAMEWORK_RIPPLE) {
                this.updateFrameworkCloseIconRipple();
            }
            float f1 = this.calculateCloseIconWidth();
            this.unapplyChildDrawable(drawable1);
            if(this.showsCloseIcon()) {
                this.applyChildDrawable(this.closeIcon);
            }
            this.invalidateSelf();
            if(f != f1) {
                this.onSizeChange();
            }
        }
    }

    public void setCloseIconContentDescription(CharSequence charSequence0) {
        if(this.closeIconContentDescription != charSequence0) {
            this.closeIconContentDescription = BidiFormatter.getInstance().unicodeWrap(charSequence0);
            this.invalidateSelf();
        }
    }

    @Deprecated
    public void setCloseIconEnabled(boolean z) {
        this.setCloseIconVisible(z);
    }

    @Deprecated
    public void setCloseIconEnabledResource(int v) {
        this.setCloseIconVisible(v);
    }

    public void setCloseIconEndPadding(float f) {
        if(this.closeIconEndPadding != f) {
            this.closeIconEndPadding = f;
            this.invalidateSelf();
            if(this.showsCloseIcon()) {
                this.onSizeChange();
            }
        }
    }

    public void setCloseIconEndPaddingResource(int v) {
        this.setCloseIconEndPadding(this.context.getResources().getDimension(v));
    }

    public void setCloseIconResource(int v) {
        this.setCloseIcon(AppCompatResources.getDrawable(this.context, v));
    }

    public void setCloseIconSize(float f) {
        if(this.closeIconSize != f) {
            this.closeIconSize = f;
            this.invalidateSelf();
            if(this.showsCloseIcon()) {
                this.onSizeChange();
            }
        }
    }

    public void setCloseIconSizeResource(int v) {
        this.setCloseIconSize(this.context.getResources().getDimension(v));
    }

    public void setCloseIconStartPadding(float f) {
        if(this.closeIconStartPadding != f) {
            this.closeIconStartPadding = f;
            this.invalidateSelf();
            if(this.showsCloseIcon()) {
                this.onSizeChange();
            }
        }
    }

    public void setCloseIconStartPaddingResource(int v) {
        this.setCloseIconStartPadding(this.context.getResources().getDimension(v));
    }

    public boolean setCloseIconState(int[] arr_v) {
        if(!Arrays.equals(this.closeIconStateSet, arr_v)) {
            this.closeIconStateSet = arr_v;
            return this.showsCloseIcon() ? this.onStateChange(this.getState(), arr_v) : false;
        }
        return false;
    }

    public void setCloseIconTint(ColorStateList colorStateList0) {
        if(this.closeIconTint != colorStateList0) {
            this.closeIconTint = colorStateList0;
            if(this.showsCloseIcon()) {
                DrawableCompat.setTintList(this.closeIcon, colorStateList0);
            }
            this.onStateChange(this.getState());
        }
    }

    public void setCloseIconTintResource(int v) {
        this.setCloseIconTint(AppCompatResources.getColorStateList(this.context, v));
    }

    public void setCloseIconVisible(int v) {
        this.setCloseIconVisible(this.context.getResources().getBoolean(v));
    }

    public void setCloseIconVisible(boolean z) {
        if(this.closeIconVisible != z) {
            boolean z1 = this.showsCloseIcon();
            this.closeIconVisible = z;
            boolean z2 = this.showsCloseIcon();
            if(z1 != z2) {
                if(z2) {
                    this.applyChildDrawable(this.closeIcon);
                }
                else {
                    this.unapplyChildDrawable(this.closeIcon);
                }
                this.invalidateSelf();
                this.onSizeChange();
            }
        }
    }

    @Override  // com.google.android.material.shape.MaterialShapeDrawable
    public void setColorFilter(ColorFilter colorFilter0) {
        if(this.colorFilter != colorFilter0) {
            this.colorFilter = colorFilter0;
            this.invalidateSelf();
        }
    }

    public void setDelegate(Delegate chipDrawable$Delegate0) {
        this.delegate = new WeakReference(chipDrawable$Delegate0);
    }

    public void setEllipsize(TextUtils.TruncateAt textUtils$TruncateAt0) {
        this.truncateAt = textUtils$TruncateAt0;
    }

    public void setHideMotionSpec(MotionSpec motionSpec0) {
        this.hideMotionSpec = motionSpec0;
    }

    public void setHideMotionSpecResource(int v) {
        this.setHideMotionSpec(MotionSpec.createFromResource(this.context, v));
    }

    public void setIconEndPadding(float f) {
        if(this.iconEndPadding != f) {
            float f1 = this.calculateChipIconWidth();
            this.iconEndPadding = f;
            float f2 = this.calculateChipIconWidth();
            this.invalidateSelf();
            if(f1 != f2) {
                this.onSizeChange();
            }
        }
    }

    public void setIconEndPaddingResource(int v) {
        this.setIconEndPadding(this.context.getResources().getDimension(v));
    }

    public void setIconStartPadding(float f) {
        if(this.iconStartPadding != f) {
            float f1 = this.calculateChipIconWidth();
            this.iconStartPadding = f;
            float f2 = this.calculateChipIconWidth();
            this.invalidateSelf();
            if(f1 != f2) {
                this.onSizeChange();
            }
        }
    }

    public void setIconStartPaddingResource(int v) {
        this.setIconStartPadding(this.context.getResources().getDimension(v));
    }

    public void setMaxWidth(int v) {
        this.maxWidth = v;
    }

    public void setRippleColor(ColorStateList colorStateList0) {
        if(this.rippleColor != colorStateList0) {
            this.rippleColor = colorStateList0;
            this.updateCompatRippleColor();
            this.onStateChange(this.getState());
        }
    }

    public void setRippleColorResource(int v) {
        this.setRippleColor(AppCompatResources.getColorStateList(this.context, v));
    }

    void setShouldDrawText(boolean z) {
        this.shouldDrawText = z;
    }

    public void setShowMotionSpec(MotionSpec motionSpec0) {
        this.showMotionSpec = motionSpec0;
    }

    public void setShowMotionSpecResource(int v) {
        this.setShowMotionSpec(MotionSpec.createFromResource(this.context, v));
    }

    public void setText(CharSequence charSequence0) {
        if(charSequence0 == null) {
            charSequence0 = "";
        }
        if(!TextUtils.equals(this.text, charSequence0)) {
            this.text = charSequence0;
            this.textDrawableHelper.setTextWidthDirty(true);
            this.invalidateSelf();
            this.onSizeChange();
        }
    }

    public void setTextAppearance(TextAppearance textAppearance0) {
        this.textDrawableHelper.setTextAppearance(textAppearance0, this.context);
    }

    public void setTextAppearanceResource(int v) {
        this.setTextAppearance(new TextAppearance(this.context, v));
    }

    public void setTextColor(int v) {
        this.setTextColor(ColorStateList.valueOf(v));
    }

    public void setTextColor(ColorStateList colorStateList0) {
        TextAppearance textAppearance0 = this.getTextAppearance();
        if(textAppearance0 != null) {
            textAppearance0.setTextColor(colorStateList0);
            this.invalidateSelf();
        }
    }

    public void setTextEndPadding(float f) {
        if(this.textEndPadding != f) {
            this.textEndPadding = f;
            this.invalidateSelf();
            this.onSizeChange();
        }
    }

    public void setTextEndPaddingResource(int v) {
        this.setTextEndPadding(this.context.getResources().getDimension(v));
    }

    public void setTextResource(int v) {
        this.setText(this.context.getResources().getString(v));
    }

    public void setTextSize(float f) {
        TextAppearance textAppearance0 = this.getTextAppearance();
        if(textAppearance0 != null) {
            textAppearance0.setTextSize(f);
            this.textDrawableHelper.getTextPaint().setTextSize(f);
            this.onTextSizeChange();
        }
    }

    public void setTextStartPadding(float f) {
        if(this.textStartPadding != f) {
            this.textStartPadding = f;
            this.invalidateSelf();
            this.onSizeChange();
        }
    }

    public void setTextStartPaddingResource(int v) {
        this.setTextStartPadding(this.context.getResources().getDimension(v));
    }

    @Override  // com.google.android.material.shape.MaterialShapeDrawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintList(ColorStateList colorStateList0) {
        if(this.tint != colorStateList0) {
            this.tint = colorStateList0;
            this.onStateChange(this.getState());
        }
    }

    @Override  // com.google.android.material.shape.MaterialShapeDrawable, androidx.core.graphics.drawable.TintAwareDrawable
    public void setTintMode(PorterDuff.Mode porterDuff$Mode0) {
        if(this.tintMode != porterDuff$Mode0) {
            this.tintMode = porterDuff$Mode0;
            this.tintFilter = DrawableUtils.updateTintFilter(this, this.tint, porterDuff$Mode0);
            this.invalidateSelf();
        }
    }

    public void setUseCompatRipple(boolean z) {
        if(this.useCompatRipple != z) {
            this.useCompatRipple = z;
            this.updateCompatRippleColor();
            this.onStateChange(this.getState());
        }
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z1) {
        boolean z2 = super.setVisible(z, z1);
        if(this.showsChipIcon()) {
            z2 |= this.chipIcon.setVisible(z, z1);
        }
        if(this.showsCheckedIcon()) {
            z2 |= this.checkedIcon.setVisible(z, z1);
        }
        if(this.showsCloseIcon()) {
            z2 |= this.closeIcon.setVisible(z, z1);
        }
        if(z2) {
            this.invalidateSelf();
        }
        return z2;
    }

    boolean shouldDrawText() {
        return this.shouldDrawText;
    }

    // 去混淆评级： 低(20)
    private boolean showsCheckedIcon() {
        return this.checkedIconVisible && this.checkedIcon != null && this.currentChecked;
    }

    private boolean showsChipIcon() {
        return this.chipIconVisible && this.chipIcon != null;
    }

    private boolean showsCloseIcon() {
        return this.closeIconVisible && this.closeIcon != null;
    }

    private void unapplyChildDrawable(Drawable drawable0) {
        if(drawable0 != null) {
            drawable0.setCallback(null);
        }
    }

    @Override  // android.graphics.drawable.Drawable$Callback
    public void unscheduleDrawable(Drawable drawable0, Runnable runnable0) {
        Drawable.Callback drawable$Callback0 = this.getCallback();
        if(drawable$Callback0 != null) {
            drawable$Callback0.unscheduleDrawable(this, runnable0);
        }
    }

    private void updateCompatRippleColor() {
        this.compatRippleColor = this.useCompatRipple ? RippleUtils.sanitizeRippleDrawableColor(this.rippleColor) : null;
    }

    private void updateFrameworkCloseIconRipple() {
        this.closeIconRipple = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(this.getRippleColor()), this.closeIcon, ChipDrawable.closeIconRippleMask);
    }
}

