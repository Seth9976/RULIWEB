package com.google.android.material.chip;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Outline;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.TextUtils.TruncateAt;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.PointerIcon;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.TextView.BufferType;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.google.android.material.R.attr;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.internal.MaterialCheckable.OnCheckedChangeListener;
import com.google.android.material.internal.MaterialCheckable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import com.google.android.material.resources.TextAppearanceFontCallback;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.List;

public class Chip extends AppCompatCheckBox implements Delegate, MaterialCheckable, Shapeable {
    class ChipTouchHelper extends ExploreByTouchHelper {
        ChipTouchHelper(Chip chip1) {
            super(chip1);
        }

        // 去混淆评级： 低(20)
        @Override  // androidx.customview.widget.ExploreByTouchHelper
        protected int getVirtualViewAt(float f, float f1) {
            return !Chip.this.hasCloseIcon() || !Chip.this.getCloseIconTouchBounds().contains(f, f1) ? 0 : 1;
        }

        @Override  // androidx.customview.widget.ExploreByTouchHelper
        protected void getVisibleVirtualViews(List list0) {
            list0.add(0);
            if(Chip.this.hasCloseIcon() && Chip.this.isCloseIconVisible() && Chip.this.onCloseIconClickListener != null) {
                list0.add(1);
            }
        }

        @Override  // androidx.customview.widget.ExploreByTouchHelper
        protected boolean onPerformActionForVirtualView(int v, int v1, Bundle bundle0) {
            if(v1 == 16) {
                if(v == 0) {
                    return Chip.this.performClick();
                }
                return v == 1 ? Chip.this.performCloseIconClick() : false;
            }
            return false;
        }

        @Override  // androidx.customview.widget.ExploreByTouchHelper
        protected void onPopulateNodeForHost(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            accessibilityNodeInfoCompat0.setCheckable(Chip.this.isCheckable());
            accessibilityNodeInfoCompat0.setClickable(Chip.this.isClickable());
            accessibilityNodeInfoCompat0.setClassName(Chip.this.getAccessibilityClassName());
            CharSequence charSequence0 = Chip.this.getText();
            if(Build.VERSION.SDK_INT >= 23) {
                accessibilityNodeInfoCompat0.setText(charSequence0);
                return;
            }
            accessibilityNodeInfoCompat0.setContentDescription(charSequence0);
        }

        @Override  // androidx.customview.widget.ExploreByTouchHelper
        protected void onPopulateNodeForVirtualView(int v, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
            CharSequence charSequence0 = "";
            if(v == 1) {
                CharSequence charSequence1 = Chip.this.getCloseIconContentDescription();
                if(charSequence1 == null) {
                    CharSequence charSequence2 = Chip.this.getText();
                    Context context0 = Chip.this.getContext();
                    if(!TextUtils.isEmpty(charSequence2)) {
                        charSequence0 = charSequence2;
                    }
                    accessibilityNodeInfoCompat0.setContentDescription(context0.getString(0x7F12009C, new Object[]{charSequence0}).trim());  // string:mtrl_chip_close_icon_content_description "Remove %1$s"
                }
                else {
                    accessibilityNodeInfoCompat0.setContentDescription(charSequence1);
                }
                accessibilityNodeInfoCompat0.setBoundsInParent(Chip.this.getCloseIconTouchBoundsInt());
                accessibilityNodeInfoCompat0.addAction(AccessibilityActionCompat.ACTION_CLICK);
                accessibilityNodeInfoCompat0.setEnabled(Chip.this.isEnabled());
                return;
            }
            accessibilityNodeInfoCompat0.setContentDescription("");
            accessibilityNodeInfoCompat0.setBoundsInParent(Chip.EMPTY_BOUNDS);
        }

        @Override  // androidx.customview.widget.ExploreByTouchHelper
        protected void onVirtualViewKeyboardFocusChanged(int v, boolean z) {
            if(v == 1) {
                Chip.this.closeIconFocused = z;
                Chip.this.refreshDrawableState();
            }
        }
    }

    private static final String BUTTON_ACCESSIBILITY_CLASS_NAME = "android.widget.Button";
    private static final int[] CHECKABLE_STATE_SET = null;
    private static final int CHIP_BODY_VIRTUAL_ID = 0;
    private static final int CLOSE_ICON_VIRTUAL_ID = 1;
    private static final int DEF_STYLE_RES = 0;
    private static final Rect EMPTY_BOUNDS = null;
    private static final String GENERIC_VIEW_ACCESSIBILITY_CLASS_NAME = "android.view.View";
    private static final int MIN_TOUCH_TARGET_DP = 0x30;
    private static final String NAMESPACE_ANDROID = "http://schemas.android.com/apk/res/android";
    private static final String RADIO_BUTTON_ACCESSIBILITY_CLASS_NAME = "android.widget.RadioButton";
    private static final int[] SELECTED_STATE = null;
    private static final String TAG = "Chip";
    private CharSequence accessibilityClassName;
    private ChipDrawable chipDrawable;
    private boolean closeIconFocused;
    private boolean closeIconHovered;
    private boolean closeIconPressed;
    private boolean deferredCheckedValue;
    private boolean ensureMinTouchTargetSize;
    private final TextAppearanceFontCallback fontCallback;
    private InsetDrawable insetBackgroundDrawable;
    private int lastLayoutDirection;
    private int minTouchTargetSize;
    private CompoundButton.OnCheckedChangeListener onCheckedChangeListener;
    private OnCheckedChangeListener onCheckedChangeListenerInternal;
    private View.OnClickListener onCloseIconClickListener;
    private final Rect rect;
    private final RectF rectF;
    private RippleDrawable ripple;
    private final ChipTouchHelper touchHelper;
    private boolean touchHelperEnabled;

    static {
        Chip.DEF_STYLE_RES = style.Widget_MaterialComponents_Chip_Action;
        Chip.EMPTY_BOUNDS = new Rect();
        Chip.SELECTED_STATE = new int[]{0x10100A1};
        Chip.CHECKABLE_STATE_SET = new int[]{0x101009F};
    }

    public Chip(Context context0) {
        this(context0, null);
    }

    public Chip(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.chipStyle);
    }

    public Chip(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, Chip.DEF_STYLE_RES), attributeSet0, v);
        this.rect = new Rect();
        this.rectF = new RectF();
        this.fontCallback = new TextAppearanceFontCallback() {
            @Override  // com.google.android.material.resources.TextAppearanceFontCallback
            public void onFontRetrievalFailed(int v) {
            }

            @Override  // com.google.android.material.resources.TextAppearanceFontCallback
            public void onFontRetrieved(Typeface typeface0, boolean z) {
                CharSequence charSequence0 = Chip.this.chipDrawable.shouldDrawText() ? Chip.this.chipDrawable.getText() : Chip.this.getText();
                Chip.this.setText(charSequence0);
                Chip.this.requestLayout();
                Chip.this.invalidate();
            }
        };
        Context context1 = this.getContext();
        this.validateAttributes(attributeSet0);
        ChipDrawable chipDrawable0 = ChipDrawable.createFromAttributes(context1, attributeSet0, v, Chip.DEF_STYLE_RES);
        this.initMinTouchTarget(context1, attributeSet0, v);
        this.setChipDrawable(chipDrawable0);
        chipDrawable0.setElevation(ViewCompat.getElevation(this));
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context1, attributeSet0, styleable.Chip, v, Chip.DEF_STYLE_RES, new int[0]);
        if(Build.VERSION.SDK_INT < 23) {
            this.setTextColor(MaterialResources.getColorStateList(context1, typedArray0, styleable.Chip_android_textColor));
        }
        boolean z = typedArray0.hasValue(styleable.Chip_shapeAppearance);
        typedArray0.recycle();
        this.touchHelper = new ChipTouchHelper(this, this);
        this.updateAccessibilityDelegate();
        if(!z) {
            this.initOutlineProvider();
        }
        this.setChecked(this.deferredCheckedValue);
        this.setText(chipDrawable0.getText());
        this.setEllipsize(chipDrawable0.getEllipsize());
        this.updateTextPaintDrawState();
        if(!this.chipDrawable.shouldDrawText()) {
            this.setLines(1);
            this.setHorizontallyScrolling(true);
        }
        this.setGravity(0x800013);
        this.updatePaddingInternal();
        if(this.shouldEnsureMinTouchTargetSize()) {
            this.setMinHeight(this.minTouchTargetSize);
        }
        this.lastLayoutDirection = ViewCompat.getLayoutDirection(this);
        super.setOnCheckedChangeListener((CompoundButton compoundButton0, boolean z) -> {
            OnCheckedChangeListener materialCheckable$OnCheckedChangeListener0 = this.onCheckedChangeListenerInternal;
            if(materialCheckable$OnCheckedChangeListener0 != null) {
                materialCheckable$OnCheckedChangeListener0.onCheckedChanged(this, z);
            }
            CompoundButton.OnCheckedChangeListener compoundButton$OnCheckedChangeListener0 = this.onCheckedChangeListener;
            if(compoundButton$OnCheckedChangeListener0 != null) {
                compoundButton$OnCheckedChangeListener0.onCheckedChanged(compoundButton0, z);
            }
        });
    }

    private void applyChipDrawable(ChipDrawable chipDrawable0) {
        chipDrawable0.setDelegate(this);
    }

    private int[] createCloseIconDrawableState() {
        int v = this.isEnabled();
        if(this.closeIconFocused) {
            ++v;
        }
        if(this.closeIconHovered) {
            ++v;
        }
        if(this.closeIconPressed) {
            ++v;
        }
        if(this.isChecked()) {
            ++v;
        }
        int[] arr_v = new int[v];
        int v1 = 0;
        if(this.isEnabled()) {
            arr_v[0] = 0x101009E;
            v1 = 1;
        }
        if(this.closeIconFocused) {
            arr_v[v1] = 0x101009C;
            ++v1;
        }
        if(this.closeIconHovered) {
            arr_v[v1] = 0x1010367;
            ++v1;
        }
        if(this.closeIconPressed) {
            arr_v[v1] = 0x10100A7;
            ++v1;
        }
        if(this.isChecked()) {
            arr_v[v1] = 0x10100A1;
        }
        return arr_v;
    }

    // 去混淆评级： 低(30)
    @Override  // android.view.View
    protected boolean dispatchHoverEvent(MotionEvent motionEvent0) {
        return this.touchHelperEnabled ? this.touchHelper.dispatchHoverEvent(motionEvent0) || super.dispatchHoverEvent(motionEvent0) : super.dispatchHoverEvent(motionEvent0);
    }

    @Override  // android.view.View
    public boolean dispatchKeyEvent(KeyEvent keyEvent0) {
        if(!this.touchHelperEnabled) {
            return super.dispatchKeyEvent(keyEvent0);
        }
        return !this.touchHelper.dispatchKeyEvent(keyEvent0) || this.touchHelper.getKeyboardFocusedVirtualViewId() == 0x80000000 ? super.dispatchKeyEvent(keyEvent0) : true;
    }

    @Override  // androidx.appcompat.widget.AppCompatCheckBox
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if((this.chipDrawable == null || !this.chipDrawable.isCloseIconStateful() ? false : this.chipDrawable.setCloseIconState(this.createCloseIconDrawableState()))) {
            this.invalidate();
        }
    }

    public boolean ensureAccessibleTouchTarget(int v) {
        this.minTouchTargetSize = v;
        int v1 = 0;
        if(!this.shouldEnsureMinTouchTargetSize()) {
            if(this.insetBackgroundDrawable != null) {
                this.removeBackgroundInset();
                return false;
            }
            this.updateBackgroundDrawable();
            return false;
        }
        int v2 = Math.max(0, v - this.chipDrawable.getIntrinsicHeight());
        int v3 = Math.max(0, v - this.chipDrawable.getIntrinsicWidth());
        if(v3 <= 0 && v2 <= 0) {
            if(this.insetBackgroundDrawable != null) {
                this.removeBackgroundInset();
                return false;
            }
            this.updateBackgroundDrawable();
            return false;
        }
        int v4 = v3 <= 0 ? 0 : v3 / 2;
        if(v2 > 0) {
            v1 = v2 / 2;
        }
        if(this.insetBackgroundDrawable != null) {
            Rect rect0 = new Rect();
            this.insetBackgroundDrawable.getPadding(rect0);
            if(rect0.top == v1 && rect0.bottom == v1 && rect0.left == v4 && rect0.right == v4) {
                this.updateBackgroundDrawable();
                return true;
            }
        }
        if(this.getMinHeight() != v) {
            this.setMinHeight(v);
        }
        if(this.getMinWidth() != v) {
            this.setMinWidth(v);
        }
        this.insetChipBackgroundDrawable(v4, v1, v4, v1);
        this.updateBackgroundDrawable();
        return true;
    }

    private void ensureChipDrawableHasCallback() {
        if(this.getBackgroundDrawable() == this.insetBackgroundDrawable && this.chipDrawable.getCallback() == null) {
            this.chipDrawable.setCallback(this.insetBackgroundDrawable);
        }
    }

    @Override  // android.widget.CheckBox
    public CharSequence getAccessibilityClassName() {
        if(!TextUtils.isEmpty(this.accessibilityClassName)) {
            return this.accessibilityClassName;
        }
        if(this.isCheckable()) {
            ViewParent viewParent0 = this.getParent();
            return !(viewParent0 instanceof ChipGroup) || !((ChipGroup)viewParent0).isSingleSelection() ? "android.widget.Button" : "android.widget.RadioButton";
        }
        return this.isClickable() ? "android.widget.Button" : "android.view.View";
    }

    public Drawable getBackgroundDrawable() {
        Drawable drawable0 = this.insetBackgroundDrawable;
        return drawable0 == null ? this.chipDrawable : drawable0;
    }

    public Drawable getCheckedIcon() {
        return this.chipDrawable == null ? null : this.chipDrawable.getCheckedIcon();
    }

    public ColorStateList getCheckedIconTint() {
        return this.chipDrawable == null ? null : this.chipDrawable.getCheckedIconTint();
    }

    public ColorStateList getChipBackgroundColor() {
        return this.chipDrawable == null ? null : this.chipDrawable.getChipBackgroundColor();
    }

    public float getChipCornerRadius() {
        return this.chipDrawable == null ? 0.0f : Math.max(0.0f, this.chipDrawable.getChipCornerRadius());
    }

    public Drawable getChipDrawable() {
        return this.chipDrawable;
    }

    public float getChipEndPadding() {
        return this.chipDrawable == null ? 0.0f : this.chipDrawable.getChipEndPadding();
    }

    public Drawable getChipIcon() {
        return this.chipDrawable == null ? null : this.chipDrawable.getChipIcon();
    }

    public float getChipIconSize() {
        return this.chipDrawable == null ? 0.0f : this.chipDrawable.getChipIconSize();
    }

    public ColorStateList getChipIconTint() {
        return this.chipDrawable == null ? null : this.chipDrawable.getChipIconTint();
    }

    public float getChipMinHeight() {
        return this.chipDrawable == null ? 0.0f : this.chipDrawable.getChipMinHeight();
    }

    public float getChipStartPadding() {
        return this.chipDrawable == null ? 0.0f : this.chipDrawable.getChipStartPadding();
    }

    public ColorStateList getChipStrokeColor() {
        return this.chipDrawable == null ? null : this.chipDrawable.getChipStrokeColor();
    }

    public float getChipStrokeWidth() {
        return this.chipDrawable == null ? 0.0f : this.chipDrawable.getChipStrokeWidth();
    }

    @Deprecated
    public CharSequence getChipText() {
        return this.getText();
    }

    public Drawable getCloseIcon() {
        return this.chipDrawable == null ? null : this.chipDrawable.getCloseIcon();
    }

    public CharSequence getCloseIconContentDescription() {
        return this.chipDrawable == null ? null : this.chipDrawable.getCloseIconContentDescription();
    }

    public float getCloseIconEndPadding() {
        return this.chipDrawable == null ? 0.0f : this.chipDrawable.getCloseIconEndPadding();
    }

    public float getCloseIconSize() {
        return this.chipDrawable == null ? 0.0f : this.chipDrawable.getCloseIconSize();
    }

    public float getCloseIconStartPadding() {
        return this.chipDrawable == null ? 0.0f : this.chipDrawable.getCloseIconStartPadding();
    }

    public ColorStateList getCloseIconTint() {
        return this.chipDrawable == null ? null : this.chipDrawable.getCloseIconTint();
    }

    private RectF getCloseIconTouchBounds() {
        this.rectF.setEmpty();
        if(this.hasCloseIcon() && this.onCloseIconClickListener != null) {
            this.chipDrawable.getCloseIconTouchBounds(this.rectF);
        }
        return this.rectF;
    }

    private Rect getCloseIconTouchBoundsInt() {
        RectF rectF0 = this.getCloseIconTouchBounds();
        this.rect.set(((int)rectF0.left), ((int)rectF0.top), ((int)rectF0.right), ((int)rectF0.bottom));
        return this.rect;
    }

    @Override  // android.widget.TextView
    public TextUtils.TruncateAt getEllipsize() {
        return this.chipDrawable == null ? null : this.chipDrawable.getEllipsize();
    }

    @Override  // android.widget.TextView
    public void getFocusedRect(Rect rect0) {
        if(this.touchHelperEnabled && (this.touchHelper.getKeyboardFocusedVirtualViewId() == 1 || this.touchHelper.getAccessibilityFocusedVirtualViewId() == 1)) {
            rect0.set(this.getCloseIconTouchBoundsInt());
            return;
        }
        super.getFocusedRect(rect0);
    }

    public MotionSpec getHideMotionSpec() {
        return this.chipDrawable == null ? null : this.chipDrawable.getHideMotionSpec();
    }

    public float getIconEndPadding() {
        return this.chipDrawable == null ? 0.0f : this.chipDrawable.getIconEndPadding();
    }

    public float getIconStartPadding() {
        return this.chipDrawable == null ? 0.0f : this.chipDrawable.getIconStartPadding();
    }

    public ColorStateList getRippleColor() {
        return this.chipDrawable == null ? null : this.chipDrawable.getRippleColor();
    }

    @Override  // com.google.android.material.shape.Shapeable
    public ShapeAppearanceModel getShapeAppearanceModel() {
        return this.chipDrawable.getShapeAppearanceModel();
    }

    public MotionSpec getShowMotionSpec() {
        return this.chipDrawable == null ? null : this.chipDrawable.getShowMotionSpec();
    }

    private TextAppearance getTextAppearance() {
        return this.chipDrawable == null ? null : this.chipDrawable.getTextAppearance();
    }

    public float getTextEndPadding() {
        return this.chipDrawable == null ? 0.0f : this.chipDrawable.getTextEndPadding();
    }

    public float getTextStartPadding() {
        return this.chipDrawable == null ? 0.0f : this.chipDrawable.getTextStartPadding();
    }

    private boolean hasCloseIcon() {
        return this.chipDrawable != null && this.chipDrawable.getCloseIcon() != null;
    }

    private void initMinTouchTarget(Context context0, AttributeSet attributeSet0, int v) {
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context0, attributeSet0, styleable.Chip, v, Chip.DEF_STYLE_RES, new int[0]);
        this.ensureMinTouchTargetSize = typedArray0.getBoolean(styleable.Chip_ensureMinTouchTargetSize, false);
        float f = (float)Math.ceil(ViewUtils.dpToPx(this.getContext(), 0x30));
        this.minTouchTargetSize = (int)Math.ceil(typedArray0.getDimension(styleable.Chip_chipMinTouchTargetSize, f));
        typedArray0.recycle();
    }

    private void initOutlineProvider() {
        this.setOutlineProvider(new ViewOutlineProvider() {
            @Override  // android.view.ViewOutlineProvider
            public void getOutline(View view0, Outline outline0) {
                if(Chip.this.chipDrawable != null) {
                    Chip.this.chipDrawable.getOutline(outline0);
                    return;
                }
                outline0.setAlpha(0.0f);
            }
        });
    }

    private void insetChipBackgroundDrawable(int v, int v1, int v2, int v3) {
        this.insetBackgroundDrawable = new InsetDrawable(this.chipDrawable, v, v1, v2, v3);
    }

    public boolean isCheckable() {
        return this.chipDrawable != null && this.chipDrawable.isCheckable();
    }

    @Deprecated
    public boolean isCheckedIconEnabled() {
        return this.isCheckedIconVisible();
    }

    public boolean isCheckedIconVisible() {
        return this.chipDrawable != null && this.chipDrawable.isCheckedIconVisible();
    }

    @Deprecated
    public boolean isChipIconEnabled() {
        return this.isChipIconVisible();
    }

    public boolean isChipIconVisible() {
        return this.chipDrawable != null && this.chipDrawable.isChipIconVisible();
    }

    @Deprecated
    public boolean isCloseIconEnabled() {
        return this.isCloseIconVisible();
    }

    public boolean isCloseIconVisible() {
        return this.chipDrawable != null && this.chipDrawable.isCloseIconVisible();
    }

    // 检测为 Lambda 实现
    void lambda$new$0$com-google-android-material-chip-Chip(CompoundButton compoundButton0, boolean z) [...]

    @Override  // android.widget.TextView
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this, this.chipDrawable);
    }

    @Override  // com.google.android.material.chip.ChipDrawable$Delegate
    public void onChipDrawableSizeChange() {
        this.ensureAccessibleTouchTarget(this.minTouchTargetSize);
        this.requestLayout();
        this.invalidateOutline();
    }

    @Override  // android.widget.CompoundButton
    protected int[] onCreateDrawableState(int v) {
        int[] arr_v = super.onCreateDrawableState(v + 2);
        if(this.isChecked()) {
            Chip.mergeDrawableStates(arr_v, Chip.SELECTED_STATE);
        }
        if(this.isCheckable()) {
            Chip.mergeDrawableStates(arr_v, Chip.CHECKABLE_STATE_SET);
        }
        return arr_v;
    }

    @Override  // android.widget.TextView
    protected void onFocusChanged(boolean z, int v, Rect rect0) {
        super.onFocusChanged(z, v, rect0);
        if(this.touchHelperEnabled) {
            this.touchHelper.onFocusChanged(z, v, rect0);
        }
    }

    @Override  // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent0) {
        switch(motionEvent0.getActionMasked()) {
            case 7: {
                this.setCloseIconHovered(this.getCloseIconTouchBounds().contains(motionEvent0.getX(), motionEvent0.getY()));
                return super.onHoverEvent(motionEvent0);
            }
            case 10: {
                this.setCloseIconHovered(false);
                return super.onHoverEvent(motionEvent0);
            }
            default: {
                return super.onHoverEvent(motionEvent0);
            }
        }
    }

    @Override  // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo0) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo0);
        accessibilityNodeInfo0.setClassName(this.getAccessibilityClassName());
        accessibilityNodeInfo0.setCheckable(this.isCheckable());
        accessibilityNodeInfo0.setClickable(this.isClickable());
        if(this.getParent() instanceof ChipGroup) {
            ChipGroup chipGroup0 = (ChipGroup)this.getParent();
            AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0 = AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo0);
            int v = chipGroup0.isSingleLine() ? chipGroup0.getIndexOfChip(this) : -1;
            accessibilityNodeInfoCompat0.setCollectionItemInfo(CollectionItemInfoCompat.obtain(chipGroup0.getRowIndex(this), 1, v, 1, false, this.isChecked()));
        }
    }

    // 去混淆评级： 低(20)
    @Override  // android.widget.Button
    public PointerIcon onResolvePointerIcon(MotionEvent motionEvent0, int v) {
        return !this.getCloseIconTouchBounds().contains(motionEvent0.getX(), motionEvent0.getY()) || !this.isEnabled() ? super.onResolvePointerIcon(motionEvent0, v) : PointerIcon.getSystemIcon(this.getContext(), 1002);
    }

    @Override  // android.widget.TextView
    public void onRtlPropertiesChanged(int v) {
        super.onRtlPropertiesChanged(v);
        if(this.lastLayoutDirection != v) {
            this.lastLayoutDirection = v;
            this.updatePaddingInternal();
        }
    }

    @Override  // android.widget.TextView
    public boolean onTouchEvent(MotionEvent motionEvent0) {
        boolean z1;
        int v = motionEvent0.getActionMasked();
        boolean z = this.getCloseIconTouchBounds().contains(motionEvent0.getX(), motionEvent0.getY());
        switch(v) {
            case 0: {
                if(!z) {
                    return super.onTouchEvent(motionEvent0);
                }
                this.setCloseIconPressed(true);
                return true;
            }
            case 1: {
                if(this.closeIconPressed) {
                    this.performCloseIconClick();
                    z1 = true;
                }
                else {
                    z1 = false;
                }
                break;
            }
            case 2: {
                if(this.closeIconPressed) {
                    if(!z) {
                        this.setCloseIconPressed(false);
                        return true;
                    }
                    return true;
                }
                return super.onTouchEvent(motionEvent0);
            }
            case 3: {
                z1 = false;
                break;
            }
            default: {
                return super.onTouchEvent(motionEvent0);
            }
        }
        this.setCloseIconPressed(false);
        return z1 || super.onTouchEvent(motionEvent0);
    }

    public boolean performCloseIconClick() {
        boolean z = false;
        this.playSoundEffect(0);
        View.OnClickListener view$OnClickListener0 = this.onCloseIconClickListener;
        if(view$OnClickListener0 != null) {
            view$OnClickListener0.onClick(this);
            z = true;
        }
        if(this.touchHelperEnabled) {
            this.touchHelper.sendEventForVirtualView(1, 1);
        }
        return z;
    }

    private void removeBackgroundInset() {
        if(this.insetBackgroundDrawable != null) {
            this.insetBackgroundDrawable = null;
            this.setMinWidth(0);
            this.setMinHeight(((int)this.getChipMinHeight()));
            this.updateBackgroundDrawable();
        }
    }

    public void setAccessibilityClassName(CharSequence charSequence0) {
        this.accessibilityClassName = charSequence0;
    }

    @Override  // android.view.View
    public void setBackground(Drawable drawable0) {
        if(drawable0 != this.getBackgroundDrawable() && drawable0 != this.ripple) {
            Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
            return;
        }
        super.setBackground(drawable0);
    }

    @Override  // android.view.View
    public void setBackgroundColor(int v) {
        Log.w("Chip", "Do not set the background color; Chip manages its own background drawable.");
    }

    @Override  // androidx.appcompat.widget.AppCompatCheckBox
    public void setBackgroundDrawable(Drawable drawable0) {
        if(drawable0 != this.getBackgroundDrawable() && drawable0 != this.ripple) {
            Log.w("Chip", "Do not set the background drawable; Chip manages its own background drawable.");
            return;
        }
        super.setBackgroundDrawable(drawable0);
    }

    @Override  // androidx.appcompat.widget.AppCompatCheckBox
    public void setBackgroundResource(int v) {
        Log.w("Chip", "Do not set the background resource; Chip manages its own background drawable.");
    }

    @Override  // android.view.View
    public void setBackgroundTintList(ColorStateList colorStateList0) {
        Log.w("Chip", "Do not set the background tint list; Chip manages its own background drawable.");
    }

    @Override  // android.view.View
    public void setBackgroundTintMode(PorterDuff.Mode porterDuff$Mode0) {
        Log.w("Chip", "Do not set the background tint mode; Chip manages its own background drawable.");
    }

    public void setCheckable(boolean z) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setCheckable(z);
        }
    }

    public void setCheckableResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setCheckableResource(v);
        }
    }

    @Override  // android.widget.Checkable, android.widget.CompoundButton
    public void setChecked(boolean z) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 == null) {
            this.deferredCheckedValue = z;
            return;
        }
        if(chipDrawable0.isCheckable()) {
            super.setChecked(z);
        }
    }

    public void setCheckedIcon(Drawable drawable0) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setCheckedIcon(drawable0);
        }
    }

    @Deprecated
    public void setCheckedIconEnabled(boolean z) {
        this.setCheckedIconVisible(z);
    }

    @Deprecated
    public void setCheckedIconEnabledResource(int v) {
        this.setCheckedIconVisible(v);
    }

    public void setCheckedIconResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setCheckedIconResource(v);
        }
    }

    public void setCheckedIconTint(ColorStateList colorStateList0) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setCheckedIconTint(colorStateList0);
        }
    }

    public void setCheckedIconTintResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setCheckedIconTintResource(v);
        }
    }

    public void setCheckedIconVisible(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setCheckedIconVisible(v);
        }
    }

    public void setCheckedIconVisible(boolean z) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setCheckedIconVisible(z);
        }
    }

    public void setChipBackgroundColor(ColorStateList colorStateList0) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setChipBackgroundColor(colorStateList0);
        }
    }

    public void setChipBackgroundColorResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setChipBackgroundColorResource(v);
        }
    }

    @Deprecated
    public void setChipCornerRadius(float f) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setChipCornerRadius(f);
        }
    }

    @Deprecated
    public void setChipCornerRadiusResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setChipCornerRadiusResource(v);
        }
    }

    public void setChipDrawable(ChipDrawable chipDrawable0) {
        ChipDrawable chipDrawable1 = this.chipDrawable;
        if(chipDrawable1 != chipDrawable0) {
            this.unapplyChipDrawable(chipDrawable1);
            this.chipDrawable = chipDrawable0;
            chipDrawable0.setShouldDrawText(false);
            this.applyChipDrawable(this.chipDrawable);
            this.ensureAccessibleTouchTarget(this.minTouchTargetSize);
        }
    }

    public void setChipEndPadding(float f) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setChipEndPadding(f);
        }
    }

    public void setChipEndPaddingResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setChipEndPaddingResource(v);
        }
    }

    public void setChipIcon(Drawable drawable0) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setChipIcon(drawable0);
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
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setChipIconResource(v);
        }
    }

    public void setChipIconSize(float f) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setChipIconSize(f);
        }
    }

    public void setChipIconSizeResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setChipIconSizeResource(v);
        }
    }

    public void setChipIconTint(ColorStateList colorStateList0) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setChipIconTint(colorStateList0);
        }
    }

    public void setChipIconTintResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setChipIconTintResource(v);
        }
    }

    public void setChipIconVisible(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setChipIconVisible(v);
        }
    }

    public void setChipIconVisible(boolean z) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setChipIconVisible(z);
        }
    }

    public void setChipMinHeight(float f) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setChipMinHeight(f);
        }
    }

    public void setChipMinHeightResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setChipMinHeightResource(v);
        }
    }

    public void setChipStartPadding(float f) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setChipStartPadding(f);
        }
    }

    public void setChipStartPaddingResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setChipStartPaddingResource(v);
        }
    }

    public void setChipStrokeColor(ColorStateList colorStateList0) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setChipStrokeColor(colorStateList0);
        }
    }

    public void setChipStrokeColorResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setChipStrokeColorResource(v);
        }
    }

    public void setChipStrokeWidth(float f) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setChipStrokeWidth(f);
        }
    }

    public void setChipStrokeWidthResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setChipStrokeWidthResource(v);
        }
    }

    @Deprecated
    public void setChipText(CharSequence charSequence0) {
        this.setText(charSequence0);
    }

    @Deprecated
    public void setChipTextResource(int v) {
        this.setText(this.getResources().getString(v));
    }

    public void setCloseIcon(Drawable drawable0) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setCloseIcon(drawable0);
        }
        this.updateAccessibilityDelegate();
    }

    public void setCloseIconContentDescription(CharSequence charSequence0) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setCloseIconContentDescription(charSequence0);
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
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setCloseIconEndPadding(f);
        }
    }

    public void setCloseIconEndPaddingResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setCloseIconEndPaddingResource(v);
        }
    }

    private void setCloseIconHovered(boolean z) {
        if(this.closeIconHovered != z) {
            this.closeIconHovered = z;
            this.refreshDrawableState();
        }
    }

    private void setCloseIconPressed(boolean z) {
        if(this.closeIconPressed != z) {
            this.closeIconPressed = z;
            this.refreshDrawableState();
        }
    }

    public void setCloseIconResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setCloseIconResource(v);
        }
        this.updateAccessibilityDelegate();
    }

    public void setCloseIconSize(float f) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setCloseIconSize(f);
        }
    }

    public void setCloseIconSizeResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setCloseIconSizeResource(v);
        }
    }

    public void setCloseIconStartPadding(float f) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setCloseIconStartPadding(f);
        }
    }

    public void setCloseIconStartPaddingResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setCloseIconStartPaddingResource(v);
        }
    }

    public void setCloseIconTint(ColorStateList colorStateList0) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setCloseIconTint(colorStateList0);
        }
    }

    public void setCloseIconTintResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setCloseIconTintResource(v);
        }
    }

    public void setCloseIconVisible(int v) {
        this.setCloseIconVisible(this.getResources().getBoolean(v));
    }

    public void setCloseIconVisible(boolean z) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setCloseIconVisible(z);
        }
        this.updateAccessibilityDelegate();
    }

    @Override  // androidx.appcompat.widget.AppCompatCheckBox
    public void setCompoundDrawables(Drawable drawable0, Drawable drawable1, Drawable drawable2, Drawable drawable3) {
        if(drawable0 != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if(drawable2 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawables(null, drawable1, null, drawable3);
    }

    @Override  // androidx.appcompat.widget.AppCompatCheckBox
    public void setCompoundDrawablesRelative(Drawable drawable0, Drawable drawable1, Drawable drawable2, Drawable drawable3) {
        if(drawable0 != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if(drawable2 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelative(null, drawable1, null, drawable3);
    }

    @Override  // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int v, int v1, int v2, int v3) {
        if(v != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if(v2 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(0, v1, 0, v3);
    }

    @Override  // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable0, Drawable drawable1, Drawable drawable2, Drawable drawable3) {
        if(drawable0 != null) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if(drawable2 != null) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(null, drawable1, null, drawable3);
    }

    @Override  // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int v, int v1, int v2, int v3) {
        if(v != 0) {
            throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
        }
        if(v2 != 0) {
            throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesWithIntrinsicBounds(0, v1, 0, v3);
    }

    @Override  // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable0, Drawable drawable1, Drawable drawable2, Drawable drawable3) {
        if(drawable0 != null) {
            throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
        }
        if(drawable2 != null) {
            throw new UnsupportedOperationException("Please set right drawable using R.attr#closeIcon.");
        }
        super.setCompoundDrawablesWithIntrinsicBounds(null, drawable1, null, drawable3);
    }

    @Override  // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setElevation(f);
        }
    }

    @Override  // android.widget.TextView
    public void setEllipsize(TextUtils.TruncateAt textUtils$TruncateAt0) {
        if(this.chipDrawable != null) {
            if(textUtils$TruncateAt0 == TextUtils.TruncateAt.MARQUEE) {
                throw new UnsupportedOperationException("Text within a chip are not allowed to scroll.");
            }
            super.setEllipsize(textUtils$TruncateAt0);
            ChipDrawable chipDrawable0 = this.chipDrawable;
            if(chipDrawable0 != null) {
                chipDrawable0.setEllipsize(textUtils$TruncateAt0);
            }
        }
    }

    public void setEnsureMinTouchTargetSize(boolean z) {
        this.ensureMinTouchTargetSize = z;
        this.ensureAccessibleTouchTarget(this.minTouchTargetSize);
    }

    @Override  // android.widget.TextView
    public void setGravity(int v) {
        if(v != 0x800013) {
            Log.w("Chip", "Chip text must be vertically center and start aligned");
            return;
        }
        super.setGravity(0x800013);
    }

    public void setHideMotionSpec(MotionSpec motionSpec0) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setHideMotionSpec(motionSpec0);
        }
    }

    public void setHideMotionSpecResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setHideMotionSpecResource(v);
        }
    }

    public void setIconEndPadding(float f) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setIconEndPadding(f);
        }
    }

    public void setIconEndPaddingResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setIconEndPaddingResource(v);
        }
    }

    public void setIconStartPadding(float f) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setIconStartPadding(f);
        }
    }

    public void setIconStartPaddingResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setIconStartPaddingResource(v);
        }
    }

    @Override  // com.google.android.material.internal.MaterialCheckable
    public void setInternalOnCheckedChangeListener(OnCheckedChangeListener materialCheckable$OnCheckedChangeListener0) {
        this.onCheckedChangeListenerInternal = materialCheckable$OnCheckedChangeListener0;
    }

    @Override  // android.view.View
    public void setLayoutDirection(int v) {
        if(this.chipDrawable == null) {
            return;
        }
        super.setLayoutDirection(v);
    }

    @Override  // android.widget.TextView
    public void setLines(int v) {
        if(v > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setLines(v);
    }

    @Override  // android.widget.TextView
    public void setMaxLines(int v) {
        if(v > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMaxLines(v);
    }

    @Override  // android.widget.TextView
    public void setMaxWidth(int v) {
        super.setMaxWidth(v);
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setMaxWidth(v);
        }
    }

    @Override  // android.widget.TextView
    public void setMinLines(int v) {
        if(v > 1) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setMinLines(v);
    }

    @Override  // android.widget.CompoundButton
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener compoundButton$OnCheckedChangeListener0) {
        this.onCheckedChangeListener = compoundButton$OnCheckedChangeListener0;
    }

    public void setOnCloseIconClickListener(View.OnClickListener view$OnClickListener0) {
        this.onCloseIconClickListener = view$OnClickListener0;
        this.updateAccessibilityDelegate();
    }

    public void setRippleColor(ColorStateList colorStateList0) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setRippleColor(colorStateList0);
        }
        if(!this.chipDrawable.getUseCompatRipple()) {
            this.updateFrameworkRippleBackground();
        }
    }

    public void setRippleColorResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setRippleColorResource(v);
            if(!this.chipDrawable.getUseCompatRipple()) {
                this.updateFrameworkRippleBackground();
            }
        }
    }

    @Override  // com.google.android.material.shape.Shapeable
    public void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel0) {
        this.chipDrawable.setShapeAppearanceModel(shapeAppearanceModel0);
    }

    public void setShowMotionSpec(MotionSpec motionSpec0) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setShowMotionSpec(motionSpec0);
        }
    }

    public void setShowMotionSpecResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setShowMotionSpecResource(v);
        }
    }

    @Override  // android.widget.TextView
    public void setSingleLine(boolean z) {
        if(!z) {
            throw new UnsupportedOperationException("Chip does not support multi-line text");
        }
        super.setSingleLine(true);
    }

    @Override  // android.widget.TextView
    public void setText(CharSequence charSequence0, TextView.BufferType textView$BufferType0) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            if(charSequence0 == null) {
                charSequence0 = "";
            }
            super.setText((chipDrawable0.shouldDrawText() ? null : charSequence0), textView$BufferType0);
            ChipDrawable chipDrawable1 = this.chipDrawable;
            if(chipDrawable1 != null) {
                chipDrawable1.setText(charSequence0);
            }
        }
    }

    @Override  // android.widget.TextView
    public void setTextAppearance(int v) {
        super.setTextAppearance(v);
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setTextAppearanceResource(v);
        }
        this.updateTextPaintDrawState();
    }

    @Override  // android.widget.TextView
    public void setTextAppearance(Context context0, int v) {
        super.setTextAppearance(context0, v);
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setTextAppearanceResource(v);
        }
        this.updateTextPaintDrawState();
    }

    public void setTextAppearance(TextAppearance textAppearance0) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setTextAppearance(textAppearance0);
        }
        this.updateTextPaintDrawState();
    }

    public void setTextAppearanceResource(int v) {
        this.setTextAppearance(this.getContext(), v);
    }

    public void setTextEndPadding(float f) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setTextEndPadding(f);
        }
    }

    public void setTextEndPaddingResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setTextEndPaddingResource(v);
        }
    }

    @Override  // android.widget.TextView
    public void setTextSize(int v, float f) {
        super.setTextSize(v, f);
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setTextSize(TypedValue.applyDimension(v, f, this.getResources().getDisplayMetrics()));
        }
        this.updateTextPaintDrawState();
    }

    public void setTextStartPadding(float f) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setTextStartPadding(f);
        }
    }

    public void setTextStartPaddingResource(int v) {
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            chipDrawable0.setTextStartPaddingResource(v);
        }
    }

    public boolean shouldEnsureMinTouchTargetSize() {
        return this.ensureMinTouchTargetSize;
    }

    private void unapplyChipDrawable(ChipDrawable chipDrawable0) {
        if(chipDrawable0 != null) {
            chipDrawable0.setDelegate(null);
        }
    }

    private void updateAccessibilityDelegate() {
        if(this.hasCloseIcon() && this.isCloseIconVisible() && this.onCloseIconClickListener != null) {
            ViewCompat.setAccessibilityDelegate(this, this.touchHelper);
            this.touchHelperEnabled = true;
            return;
        }
        ViewCompat.setAccessibilityDelegate(this, null);
        this.touchHelperEnabled = false;
    }

    private void updateBackgroundDrawable() {
        if(RippleUtils.USE_FRAMEWORK_RIPPLE) {
            this.updateFrameworkRippleBackground();
            return;
        }
        this.chipDrawable.setUseCompatRipple(true);
        ViewCompat.setBackground(this, this.getBackgroundDrawable());
        this.updatePaddingInternal();
        this.ensureChipDrawableHasCallback();
    }

    private void updateFrameworkRippleBackground() {
        this.ripple = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(this.chipDrawable.getRippleColor()), this.getBackgroundDrawable(), null);
        this.chipDrawable.setUseCompatRipple(false);
        ViewCompat.setBackground(this, this.ripple);
        this.updatePaddingInternal();
    }

    private void updatePaddingInternal() {
        if(!TextUtils.isEmpty(this.getText())) {
            ChipDrawable chipDrawable0 = this.chipDrawable;
            if(chipDrawable0 != null) {
                int v = (int)(chipDrawable0.getChipEndPadding() + this.chipDrawable.getTextEndPadding() + this.chipDrawable.calculateCloseIconWidth());
                int v1 = (int)(this.chipDrawable.getChipStartPadding() + this.chipDrawable.getTextStartPadding() + this.chipDrawable.calculateChipIconWidth());
                if(this.insetBackgroundDrawable != null) {
                    Rect rect0 = new Rect();
                    this.insetBackgroundDrawable.getPadding(rect0);
                    v1 += rect0.left;
                    v += rect0.right;
                }
                ViewCompat.setPaddingRelative(this, v1, this.getPaddingTop(), v, this.getPaddingBottom());
            }
        }
    }

    private void updateTextPaintDrawState() {
        TextPaint textPaint0 = this.getPaint();
        ChipDrawable chipDrawable0 = this.chipDrawable;
        if(chipDrawable0 != null) {
            textPaint0.drawableState = chipDrawable0.getState();
        }
        TextAppearance textAppearance0 = this.getTextAppearance();
        if(textAppearance0 != null) {
            textAppearance0.updateDrawState(this.getContext(), textPaint0, this.fontCallback);
        }
    }

    private void validateAttributes(AttributeSet attributeSet0) {
        if(attributeSet0 != null) {
            if(attributeSet0.getAttributeValue("http://schemas.android.com/apk/res/android", "background") != null) {
                Log.w("Chip", "Do not set the background; Chip manages its own background drawable.");
            }
            if(attributeSet0.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableLeft") != null) {
                throw new UnsupportedOperationException("Please set left drawable using R.attr#chipIcon.");
            }
            if(attributeSet0.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableStart") != null) {
                throw new UnsupportedOperationException("Please set start drawable using R.attr#chipIcon.");
            }
            if(attributeSet0.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableEnd") != null || attributeSet0.getAttributeValue("http://schemas.android.com/apk/res/android", "drawableRight") != null) {
                throw new UnsupportedOperationException("Please set end drawable using R.attr#closeIcon.");
            }
            if(!attributeSet0.getAttributeBooleanValue("http://schemas.android.com/apk/res/android", "singleLine", true) || attributeSet0.getAttributeIntValue("http://schemas.android.com/apk/res/android", "lines", 1) != 1 || attributeSet0.getAttributeIntValue("http://schemas.android.com/apk/res/android", "minLines", 1) != 1 || attributeSet0.getAttributeIntValue("http://schemas.android.com/apk/res/android", "maxLines", 1) != 1) {
                throw new UnsupportedOperationException("Chip does not support multi-line text");
            }
            if(attributeSet0.getAttributeIntValue("http://schemas.android.com/apk/res/android", "gravity", 0x800013) != 0x800013) {
                Log.w("Chip", "Chip text must be vertically center and start aligned");
            }
        }
    }
}

