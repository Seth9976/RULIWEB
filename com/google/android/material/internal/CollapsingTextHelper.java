package com.google.android.material.internal;

import android.animation.TimeInterpolator;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap.Config;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils.TruncateAt;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import androidx.core.math.MathUtils;
import androidx.core.text.TextDirectionHeuristicsCompat;
import androidx.core.util.Preconditions;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.resources.CancelableFontCallback.ApplyFont;
import com.google.android.material.resources.CancelableFontCallback;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.resources.TypefaceUtils;

public final class CollapsingTextHelper {
    private static final boolean DEBUG_DRAW = false;
    private static final Paint DEBUG_DRAW_PAINT = null;
    private static final String ELLIPSIS_NORMAL = "…";
    private static final float FADE_MODE_THRESHOLD_FRACTION_RELATIVE = 0.5f;
    private static final String TAG = "CollapsingTextHelper";
    private static final boolean USE_SCALING_TEXTURE;
    private boolean boundsChanged;
    private final Rect collapsedBounds;
    private float collapsedDrawX;
    private float collapsedDrawY;
    private CancelableFontCallback collapsedFontCallback;
    private float collapsedLetterSpacing;
    private ColorStateList collapsedShadowColor;
    private float collapsedShadowDx;
    private float collapsedShadowDy;
    private float collapsedShadowRadius;
    private float collapsedTextBlend;
    private ColorStateList collapsedTextColor;
    private int collapsedTextGravity;
    private float collapsedTextSize;
    private float collapsedTextWidth;
    private Typeface collapsedTypeface;
    private Typeface collapsedTypefaceBold;
    private Typeface collapsedTypefaceDefault;
    private final RectF currentBounds;
    private float currentDrawX;
    private float currentDrawY;
    private float currentLetterSpacing;
    private int currentOffsetY;
    private int currentShadowColor;
    private float currentShadowDx;
    private float currentShadowDy;
    private float currentShadowRadius;
    private float currentTextSize;
    private Typeface currentTypeface;
    private final Rect expandedBounds;
    private float expandedDrawX;
    private float expandedDrawY;
    private CancelableFontCallback expandedFontCallback;
    private float expandedFraction;
    private float expandedLetterSpacing;
    private int expandedLineCount;
    private ColorStateList expandedShadowColor;
    private float expandedShadowDx;
    private float expandedShadowDy;
    private float expandedShadowRadius;
    private float expandedTextBlend;
    private ColorStateList expandedTextColor;
    private int expandedTextGravity;
    private float expandedTextSize;
    private Bitmap expandedTitleTexture;
    private Typeface expandedTypeface;
    private Typeface expandedTypefaceBold;
    private Typeface expandedTypefaceDefault;
    private boolean fadeModeEnabled;
    private float fadeModeStartFraction;
    private float fadeModeThresholdFraction;
    private int hyphenationFrequency;
    private boolean isRtl;
    private boolean isRtlTextDirectionHeuristicsEnabled;
    private float lineSpacingAdd;
    private float lineSpacingMultiplier;
    private int maxLines;
    private TimeInterpolator positionInterpolator;
    private float scale;
    private int[] state;
    private StaticLayoutBuilderConfigurer staticLayoutBuilderConfigurer;
    private CharSequence text;
    private StaticLayout textLayout;
    private final TextPaint textPaint;
    private TimeInterpolator textSizeInterpolator;
    private CharSequence textToDraw;
    private CharSequence textToDrawCollapsed;
    private Paint texturePaint;
    private TextUtils.TruncateAt titleTextEllipsize;
    private final TextPaint tmpPaint;
    private boolean useTexture;
    private final View view;

    static {
        CollapsingTextHelper.USE_SCALING_TEXTURE = false;
        CollapsingTextHelper.DEBUG_DRAW_PAINT = null;
    }

    public CollapsingTextHelper(View view0) {
        this.expandedTextGravity = 16;
        this.collapsedTextGravity = 16;
        this.expandedTextSize = 15.0f;
        this.collapsedTextSize = 15.0f;
        this.titleTextEllipsize = TextUtils.TruncateAt.END;
        this.isRtlTextDirectionHeuristicsEnabled = true;
        this.maxLines = 1;
        this.lineSpacingAdd = 0.0f;
        this.lineSpacingMultiplier = 1.0f;
        this.hyphenationFrequency = StaticLayoutBuilderCompat.DEFAULT_HYPHENATION_FREQUENCY;
        this.view = view0;
        TextPaint textPaint0 = new TextPaint(0x81);
        this.textPaint = textPaint0;
        this.tmpPaint = new TextPaint(textPaint0);
        this.collapsedBounds = new Rect();
        this.expandedBounds = new Rect();
        this.currentBounds = new RectF();
        this.fadeModeThresholdFraction = this.calculateFadeModeThresholdFraction();
        this.maybeUpdateFontWeightAdjustment(view0.getContext().getResources().getConfiguration());
    }

    private static int blendARGB(int v, int v1, float f) {
        return Color.argb(Math.round(((float)Color.alpha(v)) * (1.0f - f) + ((float)Color.alpha(v1)) * f), Math.round(((float)Color.red(v)) * (1.0f - f) + ((float)Color.red(v1)) * f), Math.round(((float)Color.green(v)) * (1.0f - f) + ((float)Color.green(v1)) * f), Math.round(((float)Color.blue(v)) * (1.0f - f) + ((float)Color.blue(v1)) * f));
    }

    private void calculateBaseOffsets(boolean z) {
        this.calculateUsingTextSize(1.0f, z);
        CharSequence charSequence0 = this.textToDraw;
        if(charSequence0 != null) {
            StaticLayout staticLayout0 = this.textLayout;
            if(staticLayout0 != null) {
                float f = (float)staticLayout0.getWidth();
                this.textToDrawCollapsed = TextUtils.ellipsize(charSequence0, this.textPaint, f, this.titleTextEllipsize);
            }
        }
        CharSequence charSequence1 = this.textToDrawCollapsed;
        float f1 = 0.0f;
        this.collapsedTextWidth = charSequence1 == null ? 0.0f : this.measureTextWidth(this.textPaint, charSequence1);
        int v = GravityCompat.getAbsoluteGravity(this.collapsedTextGravity, ((int)this.isRtl));
        switch(v & 0x70) {
            case 0x30: {
                this.collapsedDrawY = (float)this.collapsedBounds.top;
                break;
            }
            case 80: {
                this.collapsedDrawY = ((float)this.collapsedBounds.bottom) + this.textPaint.ascent();
                break;
            }
            default: {
                float f2 = this.textPaint.descent();
                float f3 = this.textPaint.ascent();
                this.collapsedDrawY = ((float)this.collapsedBounds.centerY()) - (f2 - f3) / 2.0f;
            }
        }
        switch(v & 0x800007) {
            case 1: {
                this.collapsedDrawX = ((float)this.collapsedBounds.centerX()) - this.collapsedTextWidth / 2.0f;
                break;
            }
            case 5: {
                this.collapsedDrawX = ((float)this.collapsedBounds.right) - this.collapsedTextWidth;
                break;
            }
            default: {
                this.collapsedDrawX = (float)this.collapsedBounds.left;
            }
        }
        this.calculateUsingTextSize(0.0f, z);
        float f4 = this.textLayout == null ? 0.0f : ((float)this.textLayout.getHeight());
        StaticLayout staticLayout1 = this.textLayout;
        if(staticLayout1 == null || this.maxLines <= 1) {
            CharSequence charSequence2 = this.textToDraw;
            if(charSequence2 != null) {
                f1 = this.measureTextWidth(this.textPaint, charSequence2);
            }
        }
        else {
            f1 = (float)staticLayout1.getWidth();
        }
        this.expandedLineCount = this.textLayout == null ? 0 : this.textLayout.getLineCount();
        int v1 = GravityCompat.getAbsoluteGravity(this.expandedTextGravity, ((int)this.isRtl));
        switch(v1 & 0x70) {
            case 0x30: {
                this.expandedDrawY = (float)this.expandedBounds.top;
                break;
            }
            case 80: {
                this.expandedDrawY = ((float)this.expandedBounds.bottom) - f4 + this.textPaint.descent();
                break;
            }
            default: {
                this.expandedDrawY = ((float)this.expandedBounds.centerY()) - f4 / 2.0f;
            }
        }
        switch(v1 & 0x800007) {
            case 1: {
                this.expandedDrawX = ((float)this.expandedBounds.centerX()) - f1 / 2.0f;
                break;
            }
            case 5: {
                this.expandedDrawX = ((float)this.expandedBounds.right) - f1;
                break;
            }
            default: {
                this.expandedDrawX = (float)this.expandedBounds.left;
            }
        }
        this.clearTexture();
        this.setInterpolatedTextSize(this.expandedFraction);
    }

    private void calculateCurrentOffsets() {
        this.calculateOffsets(this.expandedFraction);
    }

    private float calculateFadeModeTextAlpha(float f) {
        return f <= this.fadeModeThresholdFraction ? AnimationUtils.lerp(1.0f, 0.0f, this.fadeModeStartFraction, this.fadeModeThresholdFraction, f) : AnimationUtils.lerp(0.0f, 1.0f, this.fadeModeThresholdFraction, 1.0f, f);
    }

    private float calculateFadeModeThresholdFraction() {
        return this.fadeModeStartFraction + (1.0f - this.fadeModeStartFraction) * 0.5f;
    }

    private boolean calculateIsRtl(CharSequence charSequence0) {
        boolean z = this.isDefaultIsRtl();
        return this.isRtlTextDirectionHeuristicsEnabled ? this.isTextDirectionHeuristicsIsRtl(charSequence0, z) : z;
    }

    private void calculateOffsets(float f) {
        float f1;
        this.interpolateBounds(f);
        if(!this.fadeModeEnabled) {
            this.currentDrawX = CollapsingTextHelper.lerp(this.expandedDrawX, this.collapsedDrawX, f, this.positionInterpolator);
            this.currentDrawY = CollapsingTextHelper.lerp(this.expandedDrawY, this.collapsedDrawY, f, this.positionInterpolator);
            this.setInterpolatedTextSize(f);
            f1 = f;
        }
        else if(f < this.fadeModeThresholdFraction) {
            this.currentDrawX = this.expandedDrawX;
            this.currentDrawY = this.expandedDrawY;
            this.setInterpolatedTextSize(0.0f);
            f1 = 0.0f;
        }
        else {
            this.currentDrawX = this.collapsedDrawX;
            this.currentDrawY = this.collapsedDrawY - ((float)Math.max(0, this.currentOffsetY));
            this.setInterpolatedTextSize(1.0f);
            f1 = 1.0f;
        }
        this.setCollapsedTextBlend(1.0f - CollapsingTextHelper.lerp(0.0f, 1.0f, 1.0f - f, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        this.setExpandedTextBlend(CollapsingTextHelper.lerp(1.0f, 0.0f, f, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        if(this.collapsedTextColor == this.expandedTextColor) {
            int v1 = this.getCurrentCollapsedTextColor();
            this.textPaint.setColor(v1);
        }
        else {
            int v = CollapsingTextHelper.blendARGB(this.getCurrentExpandedTextColor(), this.getCurrentCollapsedTextColor(), f1);
            this.textPaint.setColor(v);
        }
        float f2 = this.collapsedLetterSpacing;
        float f3 = this.expandedLetterSpacing;
        if(f2 == f3) {
            this.textPaint.setLetterSpacing(f2);
        }
        else {
            float f4 = CollapsingTextHelper.lerp(f3, f2, f, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            this.textPaint.setLetterSpacing(f4);
        }
        this.currentShadowRadius = CollapsingTextHelper.lerp(this.expandedShadowRadius, this.collapsedShadowRadius, f, null);
        this.currentShadowDx = CollapsingTextHelper.lerp(this.expandedShadowDx, this.collapsedShadowDx, f, null);
        this.currentShadowDy = CollapsingTextHelper.lerp(this.expandedShadowDy, this.collapsedShadowDy, f, null);
        int v2 = CollapsingTextHelper.blendARGB(this.getCurrentColor(this.expandedShadowColor), this.getCurrentColor(this.collapsedShadowColor), f);
        this.currentShadowColor = v2;
        this.textPaint.setShadowLayer(this.currentShadowRadius, this.currentShadowDx, this.currentShadowDy, v2);
        if(this.fadeModeEnabled) {
            int v3 = this.textPaint.getAlpha();
            float f5 = this.calculateFadeModeTextAlpha(f);
            this.textPaint.setAlpha(((int)(f5 * ((float)v3))));
            if(Build.VERSION.SDK_INT >= 0x1F) {
                float f6 = this.currentShadowRadius;
                float f7 = this.currentShadowDx;
                float f8 = this.currentShadowDy;
                int v4 = MaterialColors.compositeARGBWithAlpha(this.currentShadowColor, this.textPaint.getAlpha());
                this.textPaint.setShadowLayer(f6, f7, f8, v4);
            }
        }
        ViewCompat.postInvalidateOnAnimation(this.view);
    }

    private void calculateUsingTextSize(float f) {
        this.calculateUsingTextSize(f, false);
    }

    private void calculateUsingTextSize(float f, boolean z) {
        Typeface typeface0;
        float f4;
        float f3;
        if(this.text != null) {
            float f1 = (float)this.collapsedBounds.width();
            float f2 = (float)this.expandedBounds.width();
            if(CollapsingTextHelper.isClose(f, 1.0f)) {
                f3 = this.collapsedTextSize;
                f4 = this.collapsedLetterSpacing;
                this.scale = 1.0f;
                typeface0 = this.collapsedTypeface;
            }
            else {
                float f5 = this.expandedTextSize;
                float f6 = this.expandedLetterSpacing;
                Typeface typeface1 = this.expandedTypeface;
                this.scale = CollapsingTextHelper.isClose(f, 0.0f) ? 1.0f : CollapsingTextHelper.lerp(this.expandedTextSize, this.collapsedTextSize, f, this.textSizeInterpolator) / this.expandedTextSize;
                float f7 = this.collapsedTextSize / this.expandedTextSize;
                f1 = z || this.fadeModeEnabled || f2 * f7 <= f1 ? f2 : Math.min(f1 / f7, f2);
                f3 = f5;
                f4 = f6;
                typeface0 = typeface1;
            }
            int v = 1;
            boolean z1 = false;
            if(f1 > 0.0f) {
                boolean z2 = this.currentTypeface != typeface0;
                boolean z3 = this.currentTextSize != f3 || this.currentLetterSpacing != f4 || this.textLayout != null && f1 != ((float)this.textLayout.getWidth()) || z2 || this.boundsChanged;
                this.currentTextSize = f3;
                this.currentLetterSpacing = f4;
                this.currentTypeface = typeface0;
                this.boundsChanged = false;
                TextPaint textPaint0 = this.textPaint;
                if(this.scale != 1.0f) {
                    z1 = true;
                }
                textPaint0.setLinearText(z1);
                z1 = z3;
            }
            if(this.textToDraw == null || z1) {
                this.textPaint.setTextSize(this.currentTextSize);
                this.textPaint.setTypeface(this.currentTypeface);
                this.textPaint.setLetterSpacing(this.currentLetterSpacing);
                this.isRtl = this.calculateIsRtl(this.text);
                if(this.shouldDrawMultiline()) {
                    v = this.maxLines;
                }
                StaticLayout staticLayout0 = this.createStaticLayout(v, f1, this.isRtl);
                this.textLayout = staticLayout0;
                this.textToDraw = staticLayout0.getText();
            }
        }
    }

    private void clearTexture() {
        Bitmap bitmap0 = this.expandedTitleTexture;
        if(bitmap0 != null) {
            bitmap0.recycle();
            this.expandedTitleTexture = null;
        }
    }

    private StaticLayout createStaticLayout(int v, float f, boolean z) {
        try {
            Layout.Alignment layout$Alignment0 = v == 1 ? Layout.Alignment.ALIGN_NORMAL : this.getMultilineTextLayoutAlignment();
            return (StaticLayout)Preconditions.checkNotNull(StaticLayoutBuilderCompat.obtain(this.text, this.textPaint, ((int)f)).setEllipsize(this.titleTextEllipsize).setIsRtl(z).setAlignment(layout$Alignment0).setIncludePad(false).setMaxLines(v).setLineSpacing(this.lineSpacingAdd, this.lineSpacingMultiplier).setHyphenationFrequency(this.hyphenationFrequency).setStaticLayoutBuilderConfigurer(this.staticLayoutBuilderConfigurer).build());
        }
        catch(StaticLayoutBuilderCompatException staticLayoutBuilderCompat$StaticLayoutBuilderCompatException0) {
            Log.e("CollapsingTextHelper", staticLayoutBuilderCompat$StaticLayoutBuilderCompatException0.getCause().getMessage(), staticLayoutBuilderCompat$StaticLayoutBuilderCompatException0);
            return (StaticLayout)Preconditions.checkNotNull(null);
        }
    }

    public void draw(Canvas canvas0) {
        int v = canvas0.save();
        if(this.textToDraw != null && this.currentBounds.width() > 0.0f && this.currentBounds.height() > 0.0f) {
            this.textPaint.setTextSize(this.currentTextSize);
            float f = this.currentDrawX;
            float f1 = this.currentDrawY;
            boolean z = this.useTexture && this.expandedTitleTexture != null;
            float f2 = this.scale;
            if(f2 != 1.0f && !this.fadeModeEnabled) {
                canvas0.scale(f2, f2, f, f1);
            }
            if(z) {
                canvas0.drawBitmap(this.expandedTitleTexture, f, f1, this.texturePaint);
                canvas0.restoreToCount(v);
                return;
            }
            if(!this.shouldDrawMultiline() || this.fadeModeEnabled && this.expandedFraction <= this.fadeModeThresholdFraction) {
                canvas0.translate(f, f1);
                this.textLayout.draw(canvas0);
            }
            else {
                this.drawMultilineTransition(canvas0, this.currentDrawX - ((float)this.textLayout.getLineStart(0)), f1);
            }
            canvas0.restoreToCount(v);
        }
    }

    private void drawMultilineTransition(Canvas canvas0, float f, float f1) {
        int v = this.textPaint.getAlpha();
        canvas0.translate(f, f1);
        if(!this.fadeModeEnabled) {
            this.textPaint.setAlpha(((int)(this.expandedTextBlend * ((float)v))));
            if(Build.VERSION.SDK_INT >= 0x1F) {
                float f2 = this.currentShadowRadius;
                float f3 = this.currentShadowDx;
                float f4 = this.currentShadowDy;
                int v1 = MaterialColors.compositeARGBWithAlpha(this.currentShadowColor, this.textPaint.getAlpha());
                this.textPaint.setShadowLayer(f2, f3, f4, v1);
            }
            this.textLayout.draw(canvas0);
        }
        if(!this.fadeModeEnabled) {
            this.textPaint.setAlpha(((int)(this.collapsedTextBlend * ((float)v))));
        }
        if(Build.VERSION.SDK_INT >= 0x1F) {
            float f5 = this.currentShadowRadius;
            float f6 = this.currentShadowDx;
            float f7 = this.currentShadowDy;
            int v2 = MaterialColors.compositeARGBWithAlpha(this.currentShadowColor, this.textPaint.getAlpha());
            this.textPaint.setShadowLayer(f5, f6, f7, v2);
        }
        int v3 = this.textLayout.getLineBaseline(0);
        canvas0.drawText(this.textToDrawCollapsed, 0, this.textToDrawCollapsed.length(), 0.0f, ((float)v3), this.textPaint);
        if(Build.VERSION.SDK_INT >= 0x1F) {
            this.textPaint.setShadowLayer(this.currentShadowRadius, this.currentShadowDx, this.currentShadowDy, this.currentShadowColor);
        }
        if(!this.fadeModeEnabled) {
            String s = this.textToDrawCollapsed.toString().trim();
            if(s.endsWith("…")) {
                s = s.substring(0, s.length() - 1);
            }
            this.textPaint.setAlpha(v);
            canvas0.drawText(s, 0, Math.min(this.textLayout.getLineEnd(0), s.length()), 0.0f, ((float)v3), this.textPaint);
        }
    }

    private void ensureExpandedTexture() {
        if(this.expandedTitleTexture == null && !this.expandedBounds.isEmpty() && !TextUtils.isEmpty(this.textToDraw)) {
            this.calculateOffsets(0.0f);
            int v = this.textLayout.getWidth();
            int v1 = this.textLayout.getHeight();
            if(v > 0 && v1 > 0) {
                this.expandedTitleTexture = Bitmap.createBitmap(v, v1, Bitmap.Config.ARGB_8888);
                Canvas canvas0 = new Canvas(this.expandedTitleTexture);
                this.textLayout.draw(canvas0);
                if(this.texturePaint == null) {
                    this.texturePaint = new Paint(3);
                }
            }
        }
    }

    public void getCollapsedTextActualBounds(RectF rectF0, int v, int v1) {
        this.isRtl = this.calculateIsRtl(this.text);
        rectF0.left = Math.max(this.getCollapsedTextLeftBound(v, v1), this.collapsedBounds.left);
        rectF0.top = (float)this.collapsedBounds.top;
        rectF0.right = Math.min(this.getCollapsedTextRightBound(rectF0, v, v1), this.collapsedBounds.right);
        rectF0.bottom = ((float)this.collapsedBounds.top) + this.getCollapsedTextHeight();
    }

    public ColorStateList getCollapsedTextColor() {
        return this.collapsedTextColor;
    }

    public int getCollapsedTextGravity() {
        return this.collapsedTextGravity;
    }

    public float getCollapsedTextHeight() {
        this.getTextPaintCollapsed(this.tmpPaint);
        return -this.tmpPaint.ascent();
    }

    private float getCollapsedTextLeftBound(int v, int v1) {
        if(v1 != 17 && (v1 & 7) != 1) {
            if((v1 & 0x800005) != 0x800005 && (v1 & 5) != 5) {
                return this.isRtl ? ((float)this.collapsedBounds.right) - this.collapsedTextWidth : ((float)this.collapsedBounds.left);
            }
            return this.isRtl ? ((float)this.collapsedBounds.left) : ((float)this.collapsedBounds.right) - this.collapsedTextWidth;
        }
        return ((float)v) / 2.0f - this.collapsedTextWidth / 2.0f;
    }

    private float getCollapsedTextRightBound(RectF rectF0, int v, int v1) {
        if(v1 != 17 && (v1 & 7) != 1) {
            if((v1 & 0x800005) != 0x800005 && (v1 & 5) != 5) {
                return this.isRtl ? ((float)this.collapsedBounds.right) : rectF0.left + this.collapsedTextWidth;
            }
            return this.isRtl ? rectF0.left + this.collapsedTextWidth : ((float)this.collapsedBounds.right);
        }
        return ((float)v) / 2.0f + this.collapsedTextWidth / 2.0f;
    }

    public float getCollapsedTextSize() {
        return this.collapsedTextSize;
    }

    public Typeface getCollapsedTypeface() {
        return this.collapsedTypeface == null ? Typeface.DEFAULT : this.collapsedTypeface;
    }

    public int getCurrentCollapsedTextColor() {
        return this.getCurrentColor(this.collapsedTextColor);
    }

    private int getCurrentColor(ColorStateList colorStateList0) {
        if(colorStateList0 == null) {
            return 0;
        }
        int[] arr_v = this.state;
        return arr_v == null ? colorStateList0.getDefaultColor() : colorStateList0.getColorForState(arr_v, 0);
    }

    private int getCurrentExpandedTextColor() {
        return this.getCurrentColor(this.expandedTextColor);
    }

    public int getExpandedLineCount() {
        return this.expandedLineCount;
    }

    public ColorStateList getExpandedTextColor() {
        return this.expandedTextColor;
    }

    public float getExpandedTextFullHeight() {
        this.getTextPaintExpanded(this.tmpPaint);
        return -this.tmpPaint.ascent() + this.tmpPaint.descent();
    }

    public int getExpandedTextGravity() {
        return this.expandedTextGravity;
    }

    public float getExpandedTextHeight() {
        this.getTextPaintExpanded(this.tmpPaint);
        return -this.tmpPaint.ascent();
    }

    public float getExpandedTextSize() {
        return this.expandedTextSize;
    }

    public Typeface getExpandedTypeface() {
        return this.expandedTypeface == null ? Typeface.DEFAULT : this.expandedTypeface;
    }

    public float getExpansionFraction() {
        return this.expandedFraction;
    }

    public float getFadeModeThresholdFraction() {
        return this.fadeModeThresholdFraction;
    }

    public int getHyphenationFrequency() {
        return this.hyphenationFrequency;
    }

    public int getLineCount() {
        return this.textLayout == null ? 0 : this.textLayout.getLineCount();
    }

    public float getLineSpacingAdd() {
        return this.textLayout.getSpacingAdd();
    }

    public float getLineSpacingMultiplier() {
        return this.textLayout.getSpacingMultiplier();
    }

    public int getMaxLines() {
        return this.maxLines;
    }

    private Layout.Alignment getMultilineTextLayoutAlignment() {
        switch(GravityCompat.getAbsoluteGravity(this.expandedTextGravity, ((int)this.isRtl)) & 7) {
            case 1: {
                return Layout.Alignment.ALIGN_CENTER;
            }
            case 5: {
                return this.isRtl ? Layout.Alignment.ALIGN_NORMAL : Layout.Alignment.ALIGN_OPPOSITE;
            }
            default: {
                return this.isRtl ? Layout.Alignment.ALIGN_OPPOSITE : Layout.Alignment.ALIGN_NORMAL;
            }
        }
    }

    public TimeInterpolator getPositionInterpolator() {
        return this.positionInterpolator;
    }

    public CharSequence getText() {
        return this.text;
    }

    private void getTextPaintCollapsed(TextPaint textPaint0) {
        textPaint0.setTextSize(this.collapsedTextSize);
        textPaint0.setTypeface(this.collapsedTypeface);
        textPaint0.setLetterSpacing(this.collapsedLetterSpacing);
    }

    private void getTextPaintExpanded(TextPaint textPaint0) {
        textPaint0.setTextSize(this.expandedTextSize);
        textPaint0.setTypeface(this.expandedTypeface);
        textPaint0.setLetterSpacing(this.expandedLetterSpacing);
    }

    public TextUtils.TruncateAt getTitleTextEllipsize() {
        return this.titleTextEllipsize;
    }

    private void interpolateBounds(float f) {
        if(this.fadeModeEnabled) {
            this.currentBounds.set((f < this.fadeModeThresholdFraction ? this.expandedBounds : this.collapsedBounds));
            return;
        }
        this.currentBounds.left = CollapsingTextHelper.lerp(this.expandedBounds.left, this.collapsedBounds.left, f, this.positionInterpolator);
        this.currentBounds.top = CollapsingTextHelper.lerp(this.expandedDrawY, this.collapsedDrawY, f, this.positionInterpolator);
        this.currentBounds.right = CollapsingTextHelper.lerp(this.expandedBounds.right, this.collapsedBounds.right, f, this.positionInterpolator);
        this.currentBounds.bottom = CollapsingTextHelper.lerp(this.expandedBounds.bottom, this.collapsedBounds.bottom, f, this.positionInterpolator);
    }

    private static boolean isClose(float f, float f1) {
        return Math.abs(f - f1) < 0.00001f;
    }

    private boolean isDefaultIsRtl() {
        return ViewCompat.getLayoutDirection(this.view) == 1;
    }

    public boolean isRtlTextDirectionHeuristicsEnabled() {
        return this.isRtlTextDirectionHeuristicsEnabled;
    }

    // 去混淆评级： 低(30)
    public final boolean isStateful() {
        return this.collapsedTextColor != null && this.collapsedTextColor.isStateful() || this.expandedTextColor != null && this.expandedTextColor.isStateful();
    }

    // 去混淆评级： 低(20)
    private boolean isTextDirectionHeuristicsIsRtl(CharSequence charSequence0, boolean z) {
        return z ? TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL.isRtl(charSequence0, 0, charSequence0.length()) : TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR.isRtl(charSequence0, 0, charSequence0.length());
    }

    private static float lerp(float f, float f1, float f2, TimeInterpolator timeInterpolator0) {
        if(timeInterpolator0 != null) {
            f2 = timeInterpolator0.getInterpolation(f2);
        }
        return f + f2 * (f1 - f);
    }

    public void maybeUpdateFontWeightAdjustment(Configuration configuration0) {
        if(Build.VERSION.SDK_INT >= 0x1F) {
            Typeface typeface0 = this.collapsedTypefaceDefault;
            if(typeface0 != null) {
                this.collapsedTypefaceBold = TypefaceUtils.maybeCopyWithFontWeightAdjustment(configuration0, typeface0);
            }
            Typeface typeface1 = this.expandedTypefaceDefault;
            if(typeface1 != null) {
                this.expandedTypefaceBold = TypefaceUtils.maybeCopyWithFontWeightAdjustment(configuration0, typeface1);
            }
            this.collapsedTypeface = this.collapsedTypefaceBold == null ? this.collapsedTypefaceDefault : this.collapsedTypefaceBold;
            this.expandedTypeface = this.expandedTypefaceBold == null ? this.expandedTypefaceDefault : this.expandedTypefaceBold;
            this.recalculate(true);
        }
    }

    private float measureTextWidth(TextPaint textPaint0, CharSequence charSequence0) {
        return textPaint0.measureText(charSequence0, 0, charSequence0.length());
    }

    public void recalculate() {
        this.recalculate(false);
    }

    public void recalculate(boolean z) {
        if(this.view.getHeight() > 0 && this.view.getWidth() > 0 || z) {
            this.calculateBaseOffsets(z);
            this.calculateCurrentOffsets();
        }
    }

    private static boolean rectEquals(Rect rect0, int v, int v1, int v2, int v3) {
        return rect0.left == v && rect0.top == v1 && rect0.right == v2 && rect0.bottom == v3;
    }

    public void setCollapsedAndExpandedTextColor(ColorStateList colorStateList0) {
        if(this.collapsedTextColor == colorStateList0 && this.expandedTextColor == colorStateList0) {
            return;
        }
        this.collapsedTextColor = colorStateList0;
        this.expandedTextColor = colorStateList0;
        this.recalculate();
    }

    public void setCollapsedBounds(int v, int v1, int v2, int v3) {
        if(!CollapsingTextHelper.rectEquals(this.collapsedBounds, v, v1, v2, v3)) {
            this.collapsedBounds.set(v, v1, v2, v3);
            this.boundsChanged = true;
        }
    }

    public void setCollapsedBounds(Rect rect0) {
        this.setCollapsedBounds(rect0.left, rect0.top, rect0.right, rect0.bottom);
    }

    public void setCollapsedTextAppearance(int v) {
        TextAppearance textAppearance0 = new TextAppearance(this.view.getContext(), v);
        if(textAppearance0.getTextColor() != null) {
            this.collapsedTextColor = textAppearance0.getTextColor();
        }
        if(textAppearance0.getTextSize() != 0.0f) {
            this.collapsedTextSize = textAppearance0.getTextSize();
        }
        if(textAppearance0.shadowColor != null) {
            this.collapsedShadowColor = textAppearance0.shadowColor;
        }
        this.collapsedShadowDx = textAppearance0.shadowDx;
        this.collapsedShadowDy = textAppearance0.shadowDy;
        this.collapsedShadowRadius = textAppearance0.shadowRadius;
        this.collapsedLetterSpacing = textAppearance0.letterSpacing;
        CancelableFontCallback cancelableFontCallback0 = this.collapsedFontCallback;
        if(cancelableFontCallback0 != null) {
            cancelableFontCallback0.cancel();
        }
        this.collapsedFontCallback = new CancelableFontCallback((Typeface typeface0) -> if(CollapsingTextHelper.this.setCollapsedTypefaceInternal(typeface0)) {
            CollapsingTextHelper.this.recalculate();
        }, textAppearance0.getFallbackFont());
        textAppearance0.getFontAsync(this.view.getContext(), this.collapsedFontCallback);
        this.recalculate();

        class com.google.android.material.internal.CollapsingTextHelper.1 implements ApplyFont {
            @Override  // com.google.android.material.resources.CancelableFontCallback$ApplyFont
            public void apply(Typeface typeface0) {
                CollapsingTextHelper.this.setCollapsedTypeface(typeface0);
            }
        }

    }

    private void setCollapsedTextBlend(float f) {
        this.collapsedTextBlend = f;
        ViewCompat.postInvalidateOnAnimation(this.view);
    }

    public void setCollapsedTextColor(ColorStateList colorStateList0) {
        if(this.collapsedTextColor != colorStateList0) {
            this.collapsedTextColor = colorStateList0;
            this.recalculate();
        }
    }

    public void setCollapsedTextGravity(int v) {
        if(this.collapsedTextGravity != v) {
            this.collapsedTextGravity = v;
            this.recalculate();
        }
    }

    public void setCollapsedTextSize(float f) {
        if(this.collapsedTextSize != f) {
            this.collapsedTextSize = f;
            this.recalculate();
        }
    }

    // 检测为 Lambda 实现
    public void setCollapsedTypeface(Typeface typeface0) [...]

    private boolean setCollapsedTypefaceInternal(Typeface typeface0) {
        CancelableFontCallback cancelableFontCallback0 = this.collapsedFontCallback;
        if(cancelableFontCallback0 != null) {
            cancelableFontCallback0.cancel();
        }
        if(this.collapsedTypefaceDefault != typeface0) {
            this.collapsedTypefaceDefault = typeface0;
            Typeface typeface1 = TypefaceUtils.maybeCopyWithFontWeightAdjustment(this.view.getContext().getResources().getConfiguration(), typeface0);
            this.collapsedTypefaceBold = typeface1;
            if(typeface1 == null) {
                typeface1 = this.collapsedTypefaceDefault;
            }
            this.collapsedTypeface = typeface1;
            return true;
        }
        return false;
    }

    public void setCurrentOffsetY(int v) {
        this.currentOffsetY = v;
    }

    public void setExpandedBounds(int v, int v1, int v2, int v3) {
        if(!CollapsingTextHelper.rectEquals(this.expandedBounds, v, v1, v2, v3)) {
            this.expandedBounds.set(v, v1, v2, v3);
            this.boundsChanged = true;
        }
    }

    public void setExpandedBounds(Rect rect0) {
        this.setExpandedBounds(rect0.left, rect0.top, rect0.right, rect0.bottom);
    }

    public void setExpandedLetterSpacing(float f) {
        if(this.expandedLetterSpacing != f) {
            this.expandedLetterSpacing = f;
            this.recalculate();
        }
    }

    public void setExpandedTextAppearance(int v) {
        TextAppearance textAppearance0 = new TextAppearance(this.view.getContext(), v);
        if(textAppearance0.getTextColor() != null) {
            this.expandedTextColor = textAppearance0.getTextColor();
        }
        if(textAppearance0.getTextSize() != 0.0f) {
            this.expandedTextSize = textAppearance0.getTextSize();
        }
        if(textAppearance0.shadowColor != null) {
            this.expandedShadowColor = textAppearance0.shadowColor;
        }
        this.expandedShadowDx = textAppearance0.shadowDx;
        this.expandedShadowDy = textAppearance0.shadowDy;
        this.expandedShadowRadius = textAppearance0.shadowRadius;
        this.expandedLetterSpacing = textAppearance0.letterSpacing;
        CancelableFontCallback cancelableFontCallback0 = this.expandedFontCallback;
        if(cancelableFontCallback0 != null) {
            cancelableFontCallback0.cancel();
        }
        this.expandedFontCallback = new CancelableFontCallback((Typeface typeface0) -> if(CollapsingTextHelper.this.setExpandedTypefaceInternal(typeface0)) {
            CollapsingTextHelper.this.recalculate();
        }, textAppearance0.getFallbackFont());
        textAppearance0.getFontAsync(this.view.getContext(), this.expandedFontCallback);
        this.recalculate();

        class com.google.android.material.internal.CollapsingTextHelper.2 implements ApplyFont {
            @Override  // com.google.android.material.resources.CancelableFontCallback$ApplyFont
            public void apply(Typeface typeface0) {
                CollapsingTextHelper.this.setExpandedTypeface(typeface0);
            }
        }

    }

    private void setExpandedTextBlend(float f) {
        this.expandedTextBlend = f;
        ViewCompat.postInvalidateOnAnimation(this.view);
    }

    public void setExpandedTextColor(ColorStateList colorStateList0) {
        if(this.expandedTextColor != colorStateList0) {
            this.expandedTextColor = colorStateList0;
            this.recalculate();
        }
    }

    public void setExpandedTextGravity(int v) {
        if(this.expandedTextGravity != v) {
            this.expandedTextGravity = v;
            this.recalculate();
        }
    }

    public void setExpandedTextSize(float f) {
        if(this.expandedTextSize != f) {
            this.expandedTextSize = f;
            this.recalculate();
        }
    }

    // 检测为 Lambda 实现
    public void setExpandedTypeface(Typeface typeface0) [...]

    private boolean setExpandedTypefaceInternal(Typeface typeface0) {
        CancelableFontCallback cancelableFontCallback0 = this.expandedFontCallback;
        if(cancelableFontCallback0 != null) {
            cancelableFontCallback0.cancel();
        }
        if(this.expandedTypefaceDefault != typeface0) {
            this.expandedTypefaceDefault = typeface0;
            Typeface typeface1 = TypefaceUtils.maybeCopyWithFontWeightAdjustment(this.view.getContext().getResources().getConfiguration(), typeface0);
            this.expandedTypefaceBold = typeface1;
            if(typeface1 == null) {
                typeface1 = this.expandedTypefaceDefault;
            }
            this.expandedTypeface = typeface1;
            return true;
        }
        return false;
    }

    public void setExpansionFraction(float f) {
        float f1 = MathUtils.clamp(f, 0.0f, 1.0f);
        if(f1 != this.expandedFraction) {
            this.expandedFraction = f1;
            this.calculateCurrentOffsets();
        }
    }

    public void setFadeModeEnabled(boolean z) {
        this.fadeModeEnabled = z;
    }

    public void setFadeModeStartFraction(float f) {
        this.fadeModeStartFraction = f;
        this.fadeModeThresholdFraction = this.calculateFadeModeThresholdFraction();
    }

    public void setHyphenationFrequency(int v) {
        this.hyphenationFrequency = v;
    }

    private void setInterpolatedTextSize(float f) {
        this.calculateUsingTextSize(f);
        boolean z = CollapsingTextHelper.USE_SCALING_TEXTURE && this.scale != 1.0f;
        this.useTexture = z;
        if(z) {
            this.ensureExpandedTexture();
        }
        ViewCompat.postInvalidateOnAnimation(this.view);
    }

    public void setLineSpacingAdd(float f) {
        this.lineSpacingAdd = f;
    }

    public void setLineSpacingMultiplier(float f) {
        this.lineSpacingMultiplier = f;
    }

    public void setMaxLines(int v) {
        if(v != this.maxLines) {
            this.maxLines = v;
            this.clearTexture();
            this.recalculate();
        }
    }

    public void setPositionInterpolator(TimeInterpolator timeInterpolator0) {
        this.positionInterpolator = timeInterpolator0;
        this.recalculate();
    }

    public void setRtlTextDirectionHeuristicsEnabled(boolean z) {
        this.isRtlTextDirectionHeuristicsEnabled = z;
    }

    public final boolean setState(int[] arr_v) {
        this.state = arr_v;
        if(this.isStateful()) {
            this.recalculate();
            return true;
        }
        return false;
    }

    public void setStaticLayoutBuilderConfigurer(StaticLayoutBuilderConfigurer staticLayoutBuilderConfigurer0) {
        if(this.staticLayoutBuilderConfigurer != staticLayoutBuilderConfigurer0) {
            this.staticLayoutBuilderConfigurer = staticLayoutBuilderConfigurer0;
            this.recalculate(true);
        }
    }

    public void setText(CharSequence charSequence0) {
        if(charSequence0 != null && TextUtils.equals(this.text, charSequence0)) {
            return;
        }
        this.text = charSequence0;
        this.textToDraw = null;
        this.clearTexture();
        this.recalculate();
    }

    public void setTextSizeInterpolator(TimeInterpolator timeInterpolator0) {
        this.textSizeInterpolator = timeInterpolator0;
        this.recalculate();
    }

    public void setTitleTextEllipsize(TextUtils.TruncateAt textUtils$TruncateAt0) {
        this.titleTextEllipsize = textUtils$TruncateAt0;
        this.recalculate();
    }

    public void setTypefaces(Typeface typeface0) {
        if(!this.setCollapsedTypefaceInternal(typeface0) && !this.setExpandedTypefaceInternal(typeface0)) {
            return;
        }
        this.recalculate();
    }

    // 去混淆评级： 低(30)
    private boolean shouldDrawMultiline() {
        return this.maxLines > 1 && (!this.isRtl || this.fadeModeEnabled) && !this.useTexture;
    }
}

