package com.google.android.material.badge;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.id;
import com.google.android.material.R.string;
import com.google.android.material.R.style;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.TextDrawableHelper.TextDrawableDelegate;
import com.google.android.material.internal.TextDrawableHelper;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.text.NumberFormat;
import java.util.Locale;

public class BadgeDrawable extends Drawable implements TextDrawableDelegate {
    @Retention(RetentionPolicy.SOURCE)
    public @interface BadgeGravity {
    }

    public static final int BADGE_CONTENT_NOT_TRUNCATED = -2;
    static final int BADGE_RADIUS_NOT_SPECIFIED = -1;
    @Deprecated
    public static final int BOTTOM_END = 0x800055;
    @Deprecated
    public static final int BOTTOM_START = 0x800053;
    static final String DEFAULT_EXCEED_MAX_BADGE_NUMBER_SUFFIX = "+";
    static final String DEFAULT_EXCEED_MAX_BADGE_TEXT_SUFFIX = "…";
    private static final int DEFAULT_STYLE = 0;
    private static final int DEFAULT_THEME_ATTR = 0;
    private static final float FONT_SCALE_THRESHOLD = 0.3f;
    static final int OFFSET_ALIGNMENT_MODE_EDGE = 0;
    static final int OFFSET_ALIGNMENT_MODE_LEGACY = 1;
    private static final String TAG = "Badge";
    public static final int TOP_END = 0x800035;
    public static final int TOP_START = 0x800033;
    private WeakReference anchorViewRef;
    private final Rect badgeBounds;
    private float badgeCenterX;
    private float badgeCenterY;
    private final WeakReference contextRef;
    private float cornerRadius;
    private WeakReference customBadgeParentRef;
    private float halfBadgeHeight;
    private float halfBadgeWidth;
    private int maxBadgeNumber;
    private final MaterialShapeDrawable shapeDrawable;
    private final BadgeState state;
    private final TextDrawableHelper textDrawableHelper;

    static {
        BadgeDrawable.DEFAULT_STYLE = style.Widget_MaterialComponents_Badge;
        BadgeDrawable.DEFAULT_THEME_ATTR = attr.badgeStyle;
    }

    private BadgeDrawable(Context context0, int v, int v1, int v2, State badgeState$State0) {
        this.contextRef = new WeakReference(context0);
        ThemeEnforcement.checkMaterialTheme(context0);
        this.badgeBounds = new Rect();
        TextDrawableHelper textDrawableHelper0 = new TextDrawableHelper(this);
        this.textDrawableHelper = textDrawableHelper0;
        textDrawableHelper0.getTextPaint().setTextAlign(Paint.Align.CENTER);
        BadgeState badgeState0 = new BadgeState(context0, v, v1, v2, badgeState$State0);
        this.state = badgeState0;
        this.shapeDrawable = new MaterialShapeDrawable(ShapeAppearanceModel.builder(context0, (this.hasBadgeContent() ? badgeState0.getBadgeWithTextShapeAppearanceResId() : badgeState0.getBadgeShapeAppearanceResId()), (this.hasBadgeContent() ? badgeState0.getBadgeWithTextShapeAppearanceOverlayResId() : badgeState0.getBadgeShapeAppearanceOverlayResId())).build());
        this.restoreState();
    }

    private void autoAdjustWithinGrandparentBounds(View view0) {
        float f2;
        float f1;
        View view1 = this.getCustomBadgeParent();
        if(view1 != null) {
            if(!this.isAnchorViewWrappedInCompatParent()) {
                f2 = 0.0f;
                f1 = 0.0f;
            label_16:
                float f3 = this.getTopCutOff(view1, f2);
                float f4 = this.getLeftCutOff(view1, f1);
                float f5 = this.getBottomCutOff(view1, f2);
                float f6 = this.getRightCutoff(view1, f1);
                if(f3 < 0.0f) {
                    this.badgeCenterY += Math.abs(f3);
                }
                if(f4 < 0.0f) {
                    this.badgeCenterX += Math.abs(f4);
                }
                if(f5 > 0.0f) {
                    this.badgeCenterY -= Math.abs(f5);
                }
                if(f6 > 0.0f) {
                    this.badgeCenterX -= Math.abs(f6);
                }
            }
            else if(view1.getParent() instanceof View) {
                f2 = view1.getY();
                f1 = view1.getX();
                view1 = (View)view1.getParent();
                goto label_16;
            }
        }
        else if(view0.getParent() instanceof View) {
            float f = view0.getY();
            f1 = view0.getX();
            view1 = (View)view0.getParent();
            f2 = f;
            goto label_16;
        }
    }

    private void calculateCenterAndBounds(Rect rect0, View view0) {
        float f = this.hasBadgeContent() ? this.state.badgeWithTextRadius : this.state.badgeRadius;
        this.cornerRadius = f;
        if(f == -1.0f) {
            this.halfBadgeWidth = (float)Math.round((this.hasBadgeContent() ? this.state.badgeWithTextWidth : this.state.badgeWidth) / 2.0f);
            this.halfBadgeHeight = (float)Math.round((this.hasBadgeContent() ? this.state.badgeWithTextHeight : this.state.badgeHeight) / 2.0f);
        }
        else {
            this.halfBadgeWidth = f;
            this.halfBadgeHeight = f;
        }
        if(this.hasBadgeContent()) {
            String s = this.getBadgeContent();
            this.halfBadgeWidth = Math.max(this.halfBadgeWidth, this.textDrawableHelper.getTextWidth(s) / 2.0f + ((float)this.state.getBadgeHorizontalPadding()));
            float f1 = Math.max(this.halfBadgeHeight, this.textDrawableHelper.getTextHeight(s) / 2.0f + ((float)this.state.getBadgeVerticalPadding()));
            this.halfBadgeHeight = f1;
            this.halfBadgeWidth = Math.max(this.halfBadgeWidth, f1);
        }
        int v = this.getTotalVerticalOffsetForState();
        switch(this.state.getBadgeGravity()) {
            case 0x800053: 
            case 0x800055: {
                this.badgeCenterY = (float)(rect0.bottom - v);
                break;
            }
            default: {
                this.badgeCenterY = (float)(rect0.top + v);
            }
        }
        int v1 = this.getTotalHorizontalOffsetForState();
        switch(this.state.getBadgeGravity()) {
            case 0x800033: 
            case 0x800053: {
                this.badgeCenterX = ViewCompat.getLayoutDirection(view0) == 0 ? ((float)rect0.left) - this.halfBadgeWidth + ((float)v1) : ((float)rect0.right) + this.halfBadgeWidth - ((float)v1);
                break;
            }
            default: {
                this.badgeCenterX = ViewCompat.getLayoutDirection(view0) == 0 ? ((float)rect0.right) + this.halfBadgeWidth - ((float)v1) : ((float)rect0.left) - this.halfBadgeWidth + ((float)v1);
            }
        }
        if(this.state.isAutoAdjustedToGrandparentBounds()) {
            this.autoAdjustWithinGrandparentBounds(view0);
        }
    }

    public void clearNumber() {
        if(this.state.hasNumber()) {
            this.state.clearNumber();
            this.onNumberUpdated();
        }
    }

    public void clearText() {
        if(this.state.hasText()) {
            this.state.clearText();
            this.onTextUpdated();
        }
    }

    public static BadgeDrawable create(Context context0) {
        return new BadgeDrawable(context0, 0, BadgeDrawable.DEFAULT_THEME_ATTR, BadgeDrawable.DEFAULT_STYLE, null);
    }

    public static BadgeDrawable createFromResource(Context context0, int v) {
        return new BadgeDrawable(context0, v, BadgeDrawable.DEFAULT_THEME_ATTR, BadgeDrawable.DEFAULT_STYLE, null);
    }

    static BadgeDrawable createFromSavedState(Context context0, State badgeState$State0) {
        return new BadgeDrawable(context0, 0, BadgeDrawable.DEFAULT_THEME_ATTR, BadgeDrawable.DEFAULT_STYLE, badgeState$State0);
    }

    @Override  // android.graphics.drawable.Drawable
    public void draw(Canvas canvas0) {
        if(!this.getBounds().isEmpty() && this.getAlpha() != 0 && this.isVisible()) {
            this.shapeDrawable.draw(canvas0);
            if(this.hasBadgeContent()) {
                this.drawBadgeContent(canvas0);
            }
        }
    }

    private void drawBadgeContent(Canvas canvas0) {
        String s = this.getBadgeContent();
        if(s != null) {
            Rect rect0 = new Rect();
            this.textDrawableHelper.getTextPaint().getTextBounds(s, 0, s.length(), rect0);
            float f = this.badgeCenterY - rect0.exactCenterY();
            canvas0.drawText(s, this.badgeCenterX, ((float)(rect0.bottom > 0 ? Math.round(f) : ((int)f))), this.textDrawableHelper.getTextPaint());
        }
    }

    int getAdditionalHorizontalOffset() {
        return this.state.getAdditionalHorizontalOffset();
    }

    int getAdditionalVerticalOffset() {
        return this.state.getAdditionalVerticalOffset();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.state.getAlpha();
    }

    public int getBackgroundColor() {
        return this.shapeDrawable.getFillColor().getDefaultColor();
    }

    private String getBadgeContent() {
        if(this.hasText()) {
            return this.getTextBadgeText();
        }
        return this.hasNumber() ? this.getNumberBadgeText() : null;
    }

    public int getBadgeGravity() {
        return this.state.getBadgeGravity();
    }

    public Locale getBadgeNumberLocale() {
        return this.state.getNumberLocale();
    }

    public int getBadgeTextColor() {
        return this.textDrawableHelper.getTextPaint().getColor();
    }

    private float getBottomCutOff(View view0, float f) {
        if(view0.getParent() instanceof View) {
            View view1 = (View)view0.getParent();
            return this.badgeCenterY + this.halfBadgeHeight - (((float)view1.getHeight()) - view0.getY()) + f;
        }
        return 0.0f;
    }

    public CharSequence getContentDescription() {
        if(!this.isVisible()) {
            return null;
        }
        if(this.hasText()) {
            return this.getTextContentDescription();
        }
        return this.hasNumber() ? this.getNumberContentDescription() : this.getEmptyContentDescription();
    }

    public FrameLayout getCustomBadgeParent() {
        return this.customBadgeParentRef == null ? null : ((FrameLayout)this.customBadgeParentRef.get());
    }

    private CharSequence getEmptyContentDescription() {
        return this.state.getContentDescriptionNumberless();
    }

    public int getHorizontalOffset() {
        return this.state.getHorizontalOffsetWithoutText();
    }

    public int getHorizontalOffsetWithText() {
        return this.state.getHorizontalOffsetWithText();
    }

    public int getHorizontalOffsetWithoutText() {
        return this.state.getHorizontalOffsetWithoutText();
    }

    public int getHorizontalPadding() {
        return this.state.getBadgeHorizontalPadding();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.badgeBounds.height();
    }

    @Override  // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.badgeBounds.width();
    }

    public int getLargeFontVerticalOffsetAdjustment() {
        return this.state.getLargeFontVerticalOffsetAdjustment();
    }

    private float getLeftCutOff(View view0, float f) {
        return this.badgeCenterX - this.halfBadgeWidth + view0.getX() + f;
    }

    public int getMaxCharacterCount() {
        return this.state.getMaxCharacterCount();
    }

    public int getMaxNumber() {
        return this.state.getMaxNumber();
    }

    // 去混淆评级： 低(20)
    public int getNumber() {
        return this.state.hasNumber() ? this.state.getNumber() : 0;
    }

    private String getNumberBadgeText() {
        if(this.maxBadgeNumber != -2 && this.getNumber() > this.maxBadgeNumber) {
            Context context0 = (Context)this.contextRef.get();
            return context0 == null ? "" : String.format(this.state.getNumberLocale(), context0.getString(string.mtrl_exceed_max_badge_number_suffix), this.maxBadgeNumber, "+");
        }
        return NumberFormat.getInstance(this.state.getNumberLocale()).format(((long)this.getNumber()));
    }

    private String getNumberContentDescription() {
        if(this.state.getContentDescriptionQuantityStrings() != 0) {
            Context context0 = (Context)this.contextRef.get();
            if(context0 == null) {
                return null;
            }
            return this.maxBadgeNumber == -2 || this.getNumber() <= this.maxBadgeNumber ? context0.getResources().getQuantityString(this.state.getContentDescriptionQuantityStrings(), this.getNumber(), new Object[]{this.getNumber()}) : context0.getString(this.state.getContentDescriptionExceedsMaxBadgeNumberStringResource(), new Object[]{this.maxBadgeNumber});
        }
        return null;
    }

    @Override  // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    private float getRightCutoff(View view0, float f) {
        if(view0.getParent() instanceof View) {
            View view1 = (View)view0.getParent();
            return this.badgeCenterX + this.halfBadgeWidth - (((float)view1.getWidth()) - view0.getX()) + f;
        }
        return 0.0f;
    }

    State getSavedState() {
        return this.state.getOverridingState();
    }

    public String getText() {
        return this.state.getText();
    }

    private String getTextBadgeText() {
        String s = this.getText();
        int v = this.getMaxCharacterCount();
        if(v != -2 && s != null && s.length() > v) {
            Context context0 = (Context)this.contextRef.get();
            return context0 == null ? "" : String.format(context0.getString(string.m3_exceed_max_badge_text_suffix), s.substring(0, v - 1), "…");
        }
        return s;
    }

    private CharSequence getTextContentDescription() {
        CharSequence charSequence0 = this.state.getContentDescriptionForText();
        return charSequence0 != null ? charSequence0 : this.getText();
    }

    private float getTopCutOff(View view0, float f) {
        return this.badgeCenterY - this.halfBadgeHeight + view0.getY() + f;
    }

    private int getTotalHorizontalOffsetForState() {
        int v = this.hasBadgeContent() ? this.state.getHorizontalOffsetWithText() : this.state.getHorizontalOffsetWithoutText();
        if(this.state.offsetAlignmentMode == 1) {
            v += (this.hasBadgeContent() ? this.state.horizontalInsetWithText : this.state.horizontalInset);
        }
        return v + this.state.getAdditionalHorizontalOffset();
    }

    private int getTotalVerticalOffsetForState() {
        int v = this.state.getVerticalOffsetWithoutText();
        if(this.hasBadgeContent()) {
            v = this.state.getVerticalOffsetWithText();
            Context context0 = (Context)this.contextRef.get();
            if(context0 != null) {
                float f = AnimationUtils.lerp(0.0f, 1.0f, 0.3f, 1.0f, MaterialResources.getFontScale(context0) - 1.0f);
                v = AnimationUtils.lerp(v, v - this.state.getLargeFontVerticalOffsetAdjustment(), f);
            }
        }
        if(this.state.offsetAlignmentMode == 0) {
            v -= Math.round(this.halfBadgeHeight);
        }
        return v + this.state.getAdditionalVerticalOffset();
    }

    public int getVerticalOffset() {
        return this.state.getVerticalOffsetWithoutText();
    }

    public int getVerticalOffsetWithText() {
        return this.state.getVerticalOffsetWithText();
    }

    public int getVerticalOffsetWithoutText() {
        return this.state.getVerticalOffsetWithoutText();
    }

    public int getVerticalPadding() {
        return this.state.getBadgeVerticalPadding();
    }

    // 去混淆评级： 低(20)
    private boolean hasBadgeContent() {
        return this.hasText() || this.hasNumber();
    }

    // 去混淆评级： 低(20)
    public boolean hasNumber() {
        return !this.state.hasText() && this.state.hasNumber();
    }

    public boolean hasText() {
        return this.state.hasText();
    }

    private boolean isAnchorViewWrappedInCompatParent() {
        FrameLayout frameLayout0 = this.getCustomBadgeParent();
        return frameLayout0 != null && frameLayout0.getId() == id.mtrl_anchor_parent;
    }

    @Override  // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return false;
    }

    private void onAlphaUpdated() {
        int v = this.getAlpha();
        this.textDrawableHelper.getTextPaint().setAlpha(v);
        this.invalidateSelf();
    }

    private void onBackgroundColorUpdated() {
        ColorStateList colorStateList0 = ColorStateList.valueOf(this.state.getBackgroundColor());
        if(this.shapeDrawable.getFillColor() != colorStateList0) {
            this.shapeDrawable.setFillColor(colorStateList0);
            this.invalidateSelf();
        }
    }

    private void onBadgeContentUpdated() {
        this.textDrawableHelper.setTextSizeDirty(true);
        this.onBadgeShapeAppearanceUpdated();
        this.updateCenterAndBounds();
        this.invalidateSelf();
    }

    private void onBadgeGravityUpdated() {
        if(this.anchorViewRef != null && this.anchorViewRef.get() != null) {
            this.updateBadgeCoordinates(((View)this.anchorViewRef.get()), (this.customBadgeParentRef == null ? null : ((FrameLayout)this.customBadgeParentRef.get())));
        }
    }

    private void onBadgeShapeAppearanceUpdated() {
        Context context0 = (Context)this.contextRef.get();
        if(context0 == null) {
            return;
        }
        int v = this.hasBadgeContent() ? this.state.getBadgeWithTextShapeAppearanceResId() : this.state.getBadgeShapeAppearanceResId();
        this.shapeDrawable.setShapeAppearanceModel(ShapeAppearanceModel.builder(context0, v, (this.hasBadgeContent() ? this.state.getBadgeWithTextShapeAppearanceOverlayResId() : this.state.getBadgeShapeAppearanceOverlayResId())).build());
        this.invalidateSelf();
    }

    private void onBadgeTextAppearanceUpdated() {
        Context context0 = (Context)this.contextRef.get();
        if(context0 != null) {
            TextAppearance textAppearance0 = new TextAppearance(context0, this.state.getTextAppearanceResId());
            if(this.textDrawableHelper.getTextAppearance() != textAppearance0) {
                this.textDrawableHelper.setTextAppearance(textAppearance0, context0);
                this.onBadgeTextColorUpdated();
                this.updateCenterAndBounds();
                this.invalidateSelf();
            }
        }
    }

    private void onBadgeTextColorUpdated() {
        int v = this.state.getBadgeTextColor();
        this.textDrawableHelper.getTextPaint().setColor(v);
        this.invalidateSelf();
    }

    private void onMaxBadgeLengthUpdated() {
        this.updateMaxBadgeNumber();
        this.textDrawableHelper.setTextSizeDirty(true);
        this.updateCenterAndBounds();
        this.invalidateSelf();
    }

    private void onNumberUpdated() {
        if(!this.hasText()) {
            this.onBadgeContentUpdated();
        }
    }

    @Override  // android.graphics.drawable.Drawable, com.google.android.material.internal.TextDrawableHelper$TextDrawableDelegate
    public boolean onStateChange(int[] arr_v) {
        return super.onStateChange(arr_v);
    }

    @Override  // com.google.android.material.internal.TextDrawableHelper$TextDrawableDelegate
    public void onTextSizeChange() {
        this.invalidateSelf();
    }

    private void onTextUpdated() {
        this.onBadgeContentUpdated();
    }

    private void onVisibilityUpdated() {
        boolean z = this.state.isVisible();
        this.setVisible(z, false);
        if(BadgeUtils.USE_COMPAT_PARENT && this.getCustomBadgeParent() != null && !z) {
            ((ViewGroup)this.getCustomBadgeParent().getParent()).invalidate();
        }
    }

    private void restoreState() {
        this.onBadgeShapeAppearanceUpdated();
        this.onBadgeTextAppearanceUpdated();
        this.onMaxBadgeLengthUpdated();
        this.onBadgeContentUpdated();
        this.onAlphaUpdated();
        this.onBackgroundColorUpdated();
        this.onBadgeTextColorUpdated();
        this.onBadgeGravityUpdated();
        this.updateCenterAndBounds();
        this.onVisibilityUpdated();
    }

    void setAdditionalHorizontalOffset(int v) {
        this.state.setAdditionalHorizontalOffset(v);
        this.updateCenterAndBounds();
    }

    void setAdditionalVerticalOffset(int v) {
        this.state.setAdditionalVerticalOffset(v);
        this.updateCenterAndBounds();
    }

    @Override  // android.graphics.drawable.Drawable
    public void setAlpha(int v) {
        this.state.setAlpha(v);
        this.onAlphaUpdated();
    }

    public void setAutoAdjustToWithinGrandparentBounds(boolean z) {
        if(this.state.isAutoAdjustedToGrandparentBounds() != z) {
            this.state.setAutoAdjustToGrandparentBounds(z);
            if(this.anchorViewRef != null && this.anchorViewRef.get() != null) {
                this.autoAdjustWithinGrandparentBounds(((View)this.anchorViewRef.get()));
            }
        }
    }

    public void setBackgroundColor(int v) {
        this.state.setBackgroundColor(v);
        this.onBackgroundColorUpdated();
    }

    public void setBadgeGravity(int v) {
        if(v == 0x800053 || v == 0x800055) {
            Log.w("Badge", "Bottom badge gravities are deprecated; please use a top gravity instead.");
        }
        if(this.state.getBadgeGravity() != v) {
            this.state.setBadgeGravity(v);
            this.onBadgeGravityUpdated();
        }
    }

    public void setBadgeNumberLocale(Locale locale0) {
        if(!locale0.equals(this.state.getNumberLocale())) {
            this.state.setNumberLocale(locale0);
            this.invalidateSelf();
        }
    }

    public void setBadgeTextColor(int v) {
        if(this.textDrawableHelper.getTextPaint().getColor() != v) {
            this.state.setBadgeTextColor(v);
            this.onBadgeTextColorUpdated();
        }
    }

    public void setBadgeWithTextShapeAppearance(int v) {
        this.state.setBadgeWithTextShapeAppearanceResId(v);
        this.onBadgeShapeAppearanceUpdated();
    }

    public void setBadgeWithTextShapeAppearanceOverlay(int v) {
        this.state.setBadgeWithTextShapeAppearanceOverlayResId(v);
        this.onBadgeShapeAppearanceUpdated();
    }

    public void setBadgeWithoutTextShapeAppearance(int v) {
        this.state.setBadgeShapeAppearanceResId(v);
        this.onBadgeShapeAppearanceUpdated();
    }

    public void setBadgeWithoutTextShapeAppearanceOverlay(int v) {
        this.state.setBadgeShapeAppearanceOverlayResId(v);
        this.onBadgeShapeAppearanceUpdated();
    }

    @Override  // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter0) {
    }

    public void setContentDescriptionExceedsMaxBadgeNumberStringResource(int v) {
        this.state.setContentDescriptionExceedsMaxBadgeNumberStringResource(v);
    }

    public void setContentDescriptionForText(CharSequence charSequence0) {
        this.state.setContentDescriptionForText(charSequence0);
    }

    public void setContentDescriptionNumberless(CharSequence charSequence0) {
        this.state.setContentDescriptionNumberless(charSequence0);
    }

    public void setContentDescriptionQuantityStringsResource(int v) {
        this.state.setContentDescriptionQuantityStringsResource(v);
    }

    public void setHorizontalOffset(int v) {
        this.setHorizontalOffsetWithoutText(v);
        this.setHorizontalOffsetWithText(v);
    }

    public void setHorizontalOffsetWithText(int v) {
        this.state.setHorizontalOffsetWithText(v);
        this.updateCenterAndBounds();
    }

    public void setHorizontalOffsetWithoutText(int v) {
        this.state.setHorizontalOffsetWithoutText(v);
        this.updateCenterAndBounds();
    }

    public void setHorizontalPadding(int v) {
        if(v != this.state.getBadgeHorizontalPadding()) {
            this.state.setBadgeHorizontalPadding(v);
            this.updateCenterAndBounds();
        }
    }

    public void setLargeFontVerticalOffsetAdjustment(int v) {
        this.state.setLargeFontVerticalOffsetAdjustment(v);
        this.updateCenterAndBounds();
    }

    public void setMaxCharacterCount(int v) {
        if(this.state.getMaxCharacterCount() != v) {
            this.state.setMaxCharacterCount(v);
            this.onMaxBadgeLengthUpdated();
        }
    }

    public void setMaxNumber(int v) {
        if(this.state.getMaxNumber() != v) {
            this.state.setMaxNumber(v);
            this.onMaxBadgeLengthUpdated();
        }
    }

    public void setNumber(int v) {
        int v1 = Math.max(0, v);
        if(this.state.getNumber() != v1) {
            this.state.setNumber(v1);
            this.onNumberUpdated();
        }
    }

    public void setText(String s) {
        if(!TextUtils.equals(this.state.getText(), s)) {
            this.state.setText(s);
            this.onTextUpdated();
        }
    }

    public void setTextAppearance(int v) {
        this.state.setTextAppearanceResId(v);
        this.onBadgeTextAppearanceUpdated();
    }

    public void setVerticalOffset(int v) {
        this.setVerticalOffsetWithoutText(v);
        this.setVerticalOffsetWithText(v);
    }

    public void setVerticalOffsetWithText(int v) {
        this.state.setVerticalOffsetWithText(v);
        this.updateCenterAndBounds();
    }

    public void setVerticalOffsetWithoutText(int v) {
        this.state.setVerticalOffsetWithoutText(v);
        this.updateCenterAndBounds();
    }

    public void setVerticalPadding(int v) {
        if(v != this.state.getBadgeVerticalPadding()) {
            this.state.setBadgeVerticalPadding(v);
            this.updateCenterAndBounds();
        }
    }

    public void setVisible(boolean z) {
        this.state.setVisible(z);
        this.onVisibilityUpdated();
    }

    private void tryWrapAnchorInCompatParent(View view0) {
        ViewGroup viewGroup0 = (ViewGroup)view0.getParent();
        if(viewGroup0 != null && viewGroup0.getId() == id.mtrl_anchor_parent || this.customBadgeParentRef != null && this.customBadgeParentRef.get() == viewGroup0) {
            return;
        }
        BadgeDrawable.updateAnchorParentToNotClip(view0);
        FrameLayout frameLayout0 = new FrameLayout(view0.getContext());
        frameLayout0.setId(id.mtrl_anchor_parent);
        frameLayout0.setClipChildren(false);
        frameLayout0.setClipToPadding(false);
        frameLayout0.setLayoutParams(view0.getLayoutParams());
        frameLayout0.setMinimumWidth(view0.getWidth());
        frameLayout0.setMinimumHeight(view0.getHeight());
        int v = viewGroup0.indexOfChild(view0);
        viewGroup0.removeViewAt(v);
        view0.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        frameLayout0.addView(view0);
        viewGroup0.addView(frameLayout0, v);
        this.customBadgeParentRef = new WeakReference(frameLayout0);
        frameLayout0.post(() -> {
            BadgeDrawable.this.anchorViewRef = new WeakReference(this.val$anchorView);
            if(!BadgeUtils.USE_COMPAT_PARENT || this.val$frameLayout != null) {
                BadgeDrawable.this.customBadgeParentRef = new WeakReference(this.val$frameLayout);
            }
            else {
                BadgeDrawable.this.tryWrapAnchorInCompatParent(this.val$anchorView);
            }
            if(!BadgeUtils.USE_COMPAT_PARENT) {
                BadgeDrawable.updateAnchorParentToNotClip(this.val$anchorView);
            }
            BadgeDrawable.this.updateCenterAndBounds();
            BadgeDrawable.this.invalidateSelf();
        });

        class com.google.android.material.badge.BadgeDrawable.1 implements Runnable {
            com.google.android.material.badge.BadgeDrawable.1(View view0, FrameLayout frameLayout0) {
            }

            @Override
            public void run() {
                BadgeDrawable.this.updateBadgeCoordinates(this.val$anchorView, this.val$frameLayout);
            }
        }

    }

    private static void updateAnchorParentToNotClip(View view0) {
        ViewGroup viewGroup0 = (ViewGroup)view0.getParent();
        viewGroup0.setClipChildren(false);
        viewGroup0.setClipToPadding(false);
    }

    public void updateBadgeCoordinates(View view0) {
        this.updateBadgeCoordinates(view0, null);
    }

    @Deprecated
    public void updateBadgeCoordinates(View view0, ViewGroup viewGroup0) {
        if(!(viewGroup0 instanceof FrameLayout)) {
            throw new IllegalArgumentException("customBadgeParent must be a FrameLayout");
        }
        this.updateBadgeCoordinates(view0, ((FrameLayout)viewGroup0));
    }

    // 检测为 Lambda 实现
    public void updateBadgeCoordinates(View view0, FrameLayout frameLayout0) [...]

    private void updateCenterAndBounds() {
        Context context0 = (Context)this.contextRef.get();
        ViewGroup viewGroup0 = null;
        View view0 = this.anchorViewRef == null ? null : ((View)this.anchorViewRef.get());
        if(context0 != null && view0 != null) {
            Rect rect0 = new Rect();
            rect0.set(this.badgeBounds);
            Rect rect1 = new Rect();
            view0.getDrawingRect(rect1);
            WeakReference weakReference0 = this.customBadgeParentRef;
            if(weakReference0 != null) {
                viewGroup0 = (ViewGroup)weakReference0.get();
            }
            if(viewGroup0 != null || BadgeUtils.USE_COMPAT_PARENT) {
                if(viewGroup0 == null) {
                    viewGroup0 = (ViewGroup)view0.getParent();
                }
                viewGroup0.offsetDescendantRectToMyCoords(view0, rect1);
            }
            this.calculateCenterAndBounds(rect1, view0);
            BadgeUtils.updateBadgeBounds(this.badgeBounds, this.badgeCenterX, this.badgeCenterY, this.halfBadgeWidth, this.halfBadgeHeight);
            float f = this.cornerRadius;
            if(f != -1.0f) {
                this.shapeDrawable.setCornerSize(f);
            }
            if(!rect0.equals(this.badgeBounds)) {
                this.shapeDrawable.setBounds(this.badgeBounds);
            }
        }
    }

    private void updateMaxBadgeNumber() {
        if(this.getMaxCharacterCount() != -2) {
            this.maxBadgeNumber = ((int)Math.pow(10.0, ((double)this.getMaxCharacterCount()) - 1.0)) - 1;
            return;
        }
        this.maxBadgeNumber = this.getMaxNumber();
    }
}

