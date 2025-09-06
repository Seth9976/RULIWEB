package com.google.android.material.tooltip;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View.OnLayoutChangeListener;
import android.view.View;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.MarkerEdgeTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.OffsetEdgeTreatment;

public class TooltipDrawable extends MaterialShapeDrawable implements TextDrawableDelegate {
    private static final int DEFAULT_STYLE;
    private static final int DEFAULT_THEME_ATTR;
    private int arrowSize;
    private final View.OnLayoutChangeListener attachedViewLayoutChangeListener;
    private final Context context;
    private final Rect displayFrame;
    private final Paint.FontMetrics fontMetrics;
    private float labelOpacity;
    private int layoutMargin;
    private int locationOnScreenX;
    private int minHeight;
    private int minWidth;
    private int padding;
    private boolean showMarker;
    private CharSequence text;
    private final TextDrawableHelper textDrawableHelper;
    private final float tooltipPivotX;
    private float tooltipPivotY;
    private float tooltipScaleX;
    private float tooltipScaleY;

    static {
        TooltipDrawable.DEFAULT_STYLE = style.Widget_MaterialComponents_Tooltip;
        TooltipDrawable.DEFAULT_THEME_ATTR = attr.tooltipStyle;
    }

    private TooltipDrawable(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        this.fontMetrics = new Paint.FontMetrics();
        TextDrawableHelper textDrawableHelper0 = new TextDrawableHelper(this);
        this.textDrawableHelper = textDrawableHelper0;
        this.attachedViewLayoutChangeListener = (View view0, /* 缺少LAMBDA参数 */, /* 缺少LAMBDA参数 */, /* 缺少LAMBDA参数 */, /* 缺少LAMBDA参数 */, /* 缺少LAMBDA参数 */, /* 缺少LAMBDA参数 */, /* 缺少LAMBDA参数 */, /* 缺少LAMBDA参数 */) -> {
            int[] arr_v = new int[2];
            view0.getLocationOnScreen(arr_v);
            TooltipDrawable.this.locationOnScreenX = arr_v[0];
            view0.getWindowVisibleDisplayFrame(TooltipDrawable.this.displayFrame);
        };
        this.displayFrame = new Rect();
        this.tooltipScaleX = 1.0f;
        this.tooltipScaleY = 1.0f;
        this.tooltipPivotX = 0.5f;
        this.tooltipPivotY = 0.5f;
        this.labelOpacity = 1.0f;
        this.context = context0;
        textDrawableHelper0.getTextPaint().density = context0.getResources().getDisplayMetrics().density;
        textDrawableHelper0.getTextPaint().setTextAlign(Paint.Align.CENTER);
    }

    private float calculatePointerOffset() {
        if(this.displayFrame.right - this.getBounds().right - this.locationOnScreenX - this.layoutMargin < 0) {
            return (float)(this.displayFrame.right - this.getBounds().right - this.locationOnScreenX - this.layoutMargin);
        }
        return this.displayFrame.left - this.getBounds().left - this.locationOnScreenX + this.layoutMargin <= 0 ? 0.0f : ((float)(this.displayFrame.left - this.getBounds().left - this.locationOnScreenX + this.layoutMargin));
    }

    private float calculateTextCenterFromBaseline() {
        this.textDrawableHelper.getTextPaint().getFontMetrics(this.fontMetrics);
        return (this.fontMetrics.descent + this.fontMetrics.ascent) / 2.0f;
    }

    private float calculateTextOriginAndAlignment(Rect rect0) {
        return ((float)rect0.centerY()) - this.calculateTextCenterFromBaseline();
    }

    public static TooltipDrawable create(Context context0) {
        return TooltipDrawable.createFromAttributes(context0, null, TooltipDrawable.DEFAULT_THEME_ATTR, TooltipDrawable.DEFAULT_STYLE);
    }

    public static TooltipDrawable createFromAttributes(Context context0, AttributeSet attributeSet0) {
        return TooltipDrawable.createFromAttributes(context0, attributeSet0, TooltipDrawable.DEFAULT_THEME_ATTR, TooltipDrawable.DEFAULT_STYLE);
    }

    public static TooltipDrawable createFromAttributes(Context context0, AttributeSet attributeSet0, int v, int v1) {
        TooltipDrawable tooltipDrawable0 = new TooltipDrawable(context0, attributeSet0, v, v1);
        tooltipDrawable0.loadFromAttributes(attributeSet0, v, v1);
        return tooltipDrawable0;
    }

    private EdgeTreatment createMarkerEdge() {
        float f = this.calculatePointerOffset();
        float f1 = ((float)(((double)this.getBounds().width()) - ((double)this.arrowSize) * 1.414214)) / 2.0f;
        return new OffsetEdgeTreatment(new MarkerEdgeTreatment(((float)this.arrowSize)), Math.min(Math.max(-f, -f1), f1));
    }

    public void detachView(View view0) {
        if(view0 == null) {
            return;
        }
        view0.removeOnLayoutChangeListener(this.attachedViewLayoutChangeListener);
    }

    @Override  // com.google.android.material.shape.MaterialShapeDrawable
    public void draw(Canvas canvas0) {
        canvas0.save();
        float f = this.calculatePointerOffset();
        float f1 = (float)(-(((double)this.arrowSize) * 1.414214 - ((double)this.arrowSize)));
        canvas0.scale(this.tooltipScaleX, this.tooltipScaleY, ((float)this.getBounds().left) + ((float)this.getBounds().width()) * 0.5f, ((float)this.getBounds().top) + ((float)this.getBounds().height()) * this.tooltipPivotY);
        canvas0.translate(f, f1);
        super.draw(canvas0);
        this.drawText(canvas0);
        canvas0.restore();
    }

    private void drawText(Canvas canvas0) {
        if(this.text == null) {
            return;
        }
        Rect rect0 = this.getBounds();
        int v = (int)this.calculateTextOriginAndAlignment(rect0);
        if(this.textDrawableHelper.getTextAppearance() != null) {
            this.textDrawableHelper.getTextPaint().drawableState = this.getState();
            this.textDrawableHelper.updateTextPaintDrawState(this.context);
            this.textDrawableHelper.getTextPaint().setAlpha(((int)(this.labelOpacity * 255.0f)));
        }
        canvas0.drawText(this.text, 0, this.text.length(), ((float)rect0.centerX()), ((float)v), this.textDrawableHelper.getTextPaint());
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int)Math.max(this.textDrawableHelper.getTextPaint().getTextSize(), this.minHeight);
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return (int)Math.max(((float)(this.padding * 2)) + this.getTextWidth(), this.minWidth);
    }

    public int getLayoutMargin() {
        return this.layoutMargin;
    }

    public int getMinHeight() {
        return this.minHeight;
    }

    public int getMinWidth() {
        return this.minWidth;
    }

    public CharSequence getText() {
        return this.text;
    }

    public TextAppearance getTextAppearance() {
        return this.textDrawableHelper.getTextAppearance();
    }

    public int getTextPadding() {
        return this.padding;
    }

    private float getTextWidth() {
        return this.text == null ? 0.0f : this.textDrawableHelper.getTextWidth(this.text.toString());
    }

    private void loadFromAttributes(AttributeSet attributeSet0, int v, int v1) {
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(this.context, attributeSet0, styleable.Tooltip, v, v1, new int[0]);
        this.arrowSize = this.context.getResources().getDimensionPixelSize(dimen.mtrl_tooltip_arrowSize);
        boolean z = typedArray0.getBoolean(styleable.Tooltip_showMarker, true);
        this.showMarker = z;
        if(z) {
            this.setShapeAppearanceModel(this.getShapeAppearanceModel().toBuilder().setBottomEdge(this.createMarkerEdge()).build());
        }
        else {
            this.arrowSize = 0;
        }
        this.setText(typedArray0.getText(styleable.Tooltip_android_text));
        TextAppearance textAppearance0 = MaterialResources.getTextAppearance(this.context, typedArray0, styleable.Tooltip_android_textAppearance);
        if(textAppearance0 != null && typedArray0.hasValue(styleable.Tooltip_android_textColor)) {
            textAppearance0.setTextColor(MaterialResources.getColorStateList(this.context, typedArray0, styleable.Tooltip_android_textColor));
        }
        this.setTextAppearance(textAppearance0);
        int v2 = MaterialColors.getColor(this.context, attr.colorOnBackground, TooltipDrawable.class.getCanonicalName());
        int v3 = MaterialColors.layer(ColorUtils.setAlphaComponent(MaterialColors.getColor(this.context, 0x1010031, TooltipDrawable.class.getCanonicalName()), 0xE5), ColorUtils.setAlphaComponent(v2, 0x99));
        this.setFillColor(ColorStateList.valueOf(typedArray0.getColor(styleable.Tooltip_backgroundTint, v3)));
        this.setStrokeColor(ColorStateList.valueOf(MaterialColors.getColor(this.context, attr.colorSurface, TooltipDrawable.class.getCanonicalName())));
        this.padding = typedArray0.getDimensionPixelSize(styleable.Tooltip_android_padding, 0);
        this.minWidth = typedArray0.getDimensionPixelSize(styleable.Tooltip_android_minWidth, 0);
        this.minHeight = typedArray0.getDimensionPixelSize(styleable.Tooltip_android_minHeight, 0);
        this.layoutMargin = typedArray0.getDimensionPixelSize(styleable.Tooltip_android_layout_margin, 0);
        typedArray0.recycle();
    }

    @Override  // com.google.android.material.shape.MaterialShapeDrawable
    protected void onBoundsChange(Rect rect0) {
        super.onBoundsChange(rect0);
        if(this.showMarker) {
            this.setShapeAppearanceModel(this.getShapeAppearanceModel().toBuilder().setBottomEdge(this.createMarkerEdge()).build());
        }
    }

    @Override  // com.google.android.material.shape.MaterialShapeDrawable, com.google.android.material.internal.TextDrawableHelper$TextDrawableDelegate
    public boolean onStateChange(int[] arr_v) {
        return super.onStateChange(arr_v);
    }

    @Override  // com.google.android.material.internal.TextDrawableHelper$TextDrawableDelegate
    public void onTextSizeChange() {
        this.invalidateSelf();
    }

    public void setLayoutMargin(int v) {
        this.layoutMargin = v;
        this.invalidateSelf();
    }

    public void setMinHeight(int v) {
        this.minHeight = v;
        this.invalidateSelf();
    }

    public void setMinWidth(int v) {
        this.minWidth = v;
        this.invalidateSelf();
    }

    public void setRelativeToView(View view0) {
        if(view0 == null) {
            return;
        }
        this.updateLocationOnScreen(view0);
        view0.addOnLayoutChangeListener(this.attachedViewLayoutChangeListener);
    }

    public void setRevealFraction(float f) {
        this.tooltipPivotY = 1.2f;
        this.tooltipScaleX = f;
        this.tooltipScaleY = f;
        this.labelOpacity = AnimationUtils.lerp(0.0f, 1.0f, 0.19f, 1.0f, f);
        this.invalidateSelf();
    }

    public void setText(CharSequence charSequence0) {
        if(!TextUtils.equals(this.text, charSequence0)) {
            this.text = charSequence0;
            this.textDrawableHelper.setTextWidthDirty(true);
            this.invalidateSelf();
        }
    }

    public void setTextAppearance(TextAppearance textAppearance0) {
        this.textDrawableHelper.setTextAppearance(textAppearance0, this.context);
    }

    public void setTextAppearanceResource(int v) {
        this.setTextAppearance(new TextAppearance(this.context, v));
    }

    public void setTextPadding(int v) {
        this.padding = v;
        this.invalidateSelf();
    }

    public void setTextResource(int v) {
        this.setText(this.context.getResources().getString(v));
    }

    // 检测为 Lambda 实现
    private void updateLocationOnScreen(View view0) [...]

    class com.google.android.material.tooltip.TooltipDrawable.1 implements View.OnLayoutChangeListener {
        @Override  // android.view.View$OnLayoutChangeListener
        public void onLayoutChange(View view0, int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) {
            TooltipDrawable.this.updateLocationOnScreen(view0);
        }
    }

}

