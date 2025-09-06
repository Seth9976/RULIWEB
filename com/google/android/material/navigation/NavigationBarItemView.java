package com.google.android.material.navigation;

import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View.OnLayoutChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuView.ItemView;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.PointerIconCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionItemInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.id;
import com.google.android.material.R.integer;
import com.google.android.material.R.string;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.badge.BadgeUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

public abstract class NavigationBarItemView extends FrameLayout implements ItemView {
    static class ActiveIndicatorTransform {
        private static final float ALPHA_FRACTION = 0.2f;
        private static final float SCALE_X_HIDDEN = 0.4f;
        private static final float SCALE_X_SHOWN = 1.0f;

        private ActiveIndicatorTransform() {
        }

        ActiveIndicatorTransform(com.google.android.material.navigation.NavigationBarItemView.1 navigationBarItemView$10) {
        }

        protected float calculateAlpha(float f, float f1) {
            int v = Float.compare(f1, 0.0f);
            float f2 = v == 0 ? 0.8f : 0.0f;
            return v == 0 ? AnimationUtils.lerp(0.0f, 1.0f, f2, 1.0f, f) : AnimationUtils.lerp(0.0f, 1.0f, f2, 0.2f, f);
        }

        protected float calculateScaleX(float f, float f1) {
            return 0.4f + f * 0.6f;
        }

        protected float calculateScaleY(float f, float f1) {
            return 1.0f;
        }

        public void updateForProgress(float f, float f1, View view0) {
            view0.setScaleX(this.calculateScaleX(f, f1));
            view0.setScaleY(this.calculateScaleY(f, f1));
            view0.setAlpha(this.calculateAlpha(f, f1));
        }
    }

    static class ActiveIndicatorUnlabeledTransform extends ActiveIndicatorTransform {
        private ActiveIndicatorUnlabeledTransform() {
            super(null);
        }

        ActiveIndicatorUnlabeledTransform(com.google.android.material.navigation.NavigationBarItemView.1 navigationBarItemView$10) {
        }

        @Override  // com.google.android.material.navigation.NavigationBarItemView$ActiveIndicatorTransform
        protected float calculateScaleY(float f, float f1) {
            return this.calculateScaleX(f, f1);
        }
    }

    private static final ActiveIndicatorTransform ACTIVE_INDICATOR_LABELED_TRANSFORM = null;
    private static final ActiveIndicatorTransform ACTIVE_INDICATOR_UNLABELED_TRANSFORM = null;
    private static final int[] CHECKED_STATE_SET = null;
    private static final int INVALID_ITEM_POSITION = -1;
    private ValueAnimator activeIndicatorAnimator;
    private int activeIndicatorDesiredHeight;
    private int activeIndicatorDesiredWidth;
    private boolean activeIndicatorEnabled;
    private int activeIndicatorLabelPadding;
    private int activeIndicatorMarginHorizontal;
    private float activeIndicatorProgress;
    private boolean activeIndicatorResizeable;
    private ActiveIndicatorTransform activeIndicatorTransform;
    private final View activeIndicatorView;
    private int activeTextAppearance;
    private BadgeDrawable badgeDrawable;
    private final ImageView icon;
    private final FrameLayout iconContainer;
    private ColorStateList iconTint;
    private boolean initialized;
    private boolean isShifting;
    Drawable itemBackground;
    private MenuItemImpl itemData;
    private int itemPaddingBottom;
    private int itemPaddingTop;
    private int itemPosition;
    private ColorStateList itemRippleColor;
    private final ViewGroup labelGroup;
    private int labelVisibilityMode;
    private final TextView largeLabel;
    private Drawable originalIconDrawable;
    private float scaleDownFactor;
    private float scaleUpFactor;
    private float shiftAmount;
    private final TextView smallLabel;
    private Drawable wrappedIconDrawable;

    static {
        NavigationBarItemView.CHECKED_STATE_SET = new int[]{0x10100A0};
        NavigationBarItemView.ACTIVE_INDICATOR_LABELED_TRANSFORM = new ActiveIndicatorTransform(null);
        NavigationBarItemView.ACTIVE_INDICATOR_UNLABELED_TRANSFORM = new ActiveIndicatorUnlabeledTransform(null);
    }

    public NavigationBarItemView(Context context0) {
        super(context0);
        this.initialized = false;
        this.itemPosition = -1;
        this.activeTextAppearance = 0;
        this.activeIndicatorTransform = NavigationBarItemView.ACTIVE_INDICATOR_LABELED_TRANSFORM;
        this.activeIndicatorProgress = 0.0f;
        this.activeIndicatorEnabled = false;
        this.activeIndicatorDesiredWidth = 0;
        this.activeIndicatorDesiredHeight = 0;
        this.activeIndicatorResizeable = false;
        this.activeIndicatorMarginHorizontal = 0;
        LayoutInflater.from(context0).inflate(this.getItemLayoutResId(), this, true);
        this.iconContainer = (FrameLayout)this.findViewById(id.navigation_bar_item_icon_container);
        this.activeIndicatorView = this.findViewById(id.navigation_bar_item_active_indicator_view);
        ImageView imageView0 = (ImageView)this.findViewById(id.navigation_bar_item_icon_view);
        this.icon = imageView0;
        ViewGroup viewGroup0 = (ViewGroup)this.findViewById(id.navigation_bar_item_labels_group);
        this.labelGroup = viewGroup0;
        TextView textView0 = (TextView)this.findViewById(id.navigation_bar_item_small_label_view);
        this.smallLabel = textView0;
        TextView textView1 = (TextView)this.findViewById(id.navigation_bar_item_large_label_view);
        this.largeLabel = textView1;
        this.setBackgroundResource(0x7F0800F1);  // drawable:mtrl_navigation_bar_item_background
        this.itemPaddingTop = this.getResources().getDimensionPixelSize(this.getItemDefaultMarginResId());
        this.itemPaddingBottom = viewGroup0.getPaddingBottom();
        this.activeIndicatorLabelPadding = this.getResources().getDimensionPixelSize(dimen.m3_navigation_item_active_indicator_label_padding);
        ViewCompat.setImportantForAccessibility(textView0, 2);
        ViewCompat.setImportantForAccessibility(textView1, 2);
        this.setFocusable(true);
        this.calculateTextScaleFactors(textView0.getTextSize(), textView1.getTextSize());
        if(imageView0 != null) {
            imageView0.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                @Override  // android.view.View$OnLayoutChangeListener
                public void onLayoutChange(View view0, int v, int v1, int v2, int v3, int v4, int v5, int v6, int v7) {
                    if(NavigationBarItemView.this.icon.getVisibility() == 0) {
                        ImageView imageView0 = NavigationBarItemView.this.icon;
                        NavigationBarItemView.this.tryUpdateBadgeBounds(imageView0);
                    }
                }
            });
        }
    }

    private void calculateTextScaleFactors(float f, float f1) {
        this.shiftAmount = f - f1;
        this.scaleUpFactor = f1 * 1.0f / f;
        this.scaleDownFactor = f * 1.0f / f1;
    }

    void clear() {
        this.removeBadge();
        this.itemData = null;
        this.activeIndicatorProgress = 0.0f;
        this.initialized = false;
    }

    private static Drawable createItemBackgroundCompat(ColorStateList colorStateList0) {
        return new RippleDrawable(RippleUtils.convertToRippleDrawableColor(colorStateList0), null, null);
    }

    @Override  // android.view.ViewGroup
    public boolean dispatchTouchEvent(MotionEvent motionEvent0) {
        FrameLayout frameLayout0 = this.iconContainer;
        if(frameLayout0 != null && this.activeIndicatorEnabled) {
            frameLayout0.dispatchTouchEvent(motionEvent0);
        }
        return super.dispatchTouchEvent(motionEvent0);
    }

    public Drawable getActiveIndicatorDrawable() {
        return this.activeIndicatorView == null ? null : this.activeIndicatorView.getBackground();
    }

    public BadgeDrawable getBadge() {
        return this.badgeDrawable;
    }

    private FrameLayout getCustomParentForBadge(View view0) {
        return view0 != this.icon || !BadgeUtils.USE_COMPAT_PARENT ? null : ((FrameLayout)this.icon.getParent());
    }

    private View getIconOrContainer() {
        View view0 = this.iconContainer;
        return view0 != null ? view0 : this.icon;
    }

    protected int getItemBackgroundResId() [...] // 潜在的解密器

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public MenuItemImpl getItemData() {
        return this.itemData;
    }

    protected int getItemDefaultMarginResId() {
        return dimen.mtrl_navigation_bar_item_default_margin;
    }

    protected abstract int getItemLayoutResId();

    public int getItemPosition() {
        return this.itemPosition;
    }

    private int getItemVisiblePosition() {
        ViewGroup viewGroup0 = (ViewGroup)this.getParent();
        int v = viewGroup0.indexOfChild(this);
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            View view0 = viewGroup0.getChildAt(v1);
            if(view0 instanceof NavigationBarItemView && view0.getVisibility() == 0) {
                ++v2;
            }
        }
        return v2;
    }

    private int getSuggestedIconHeight() {
        return ((FrameLayout.LayoutParams)this.getIconOrContainer().getLayoutParams()).topMargin + this.getIconOrContainer().getMeasuredHeight();
    }

    private int getSuggestedIconWidth() {
        int v = this.badgeDrawable == null ? 0 : this.badgeDrawable.getMinimumWidth() - this.badgeDrawable.getHorizontalOffset();
        FrameLayout.LayoutParams frameLayout$LayoutParams0 = (FrameLayout.LayoutParams)this.getIconOrContainer().getLayoutParams();
        return Math.max(v, frameLayout$LayoutParams0.leftMargin) + this.icon.getMeasuredWidth() + Math.max(v, frameLayout$LayoutParams0.rightMargin);
    }

    @Override  // android.view.View
    protected int getSuggestedMinimumHeight() {
        FrameLayout.LayoutParams frameLayout$LayoutParams0 = (FrameLayout.LayoutParams)this.labelGroup.getLayoutParams();
        int v = this.getSuggestedIconHeight();
        return this.labelGroup.getVisibility() == 0 ? v + this.activeIndicatorLabelPadding + frameLayout$LayoutParams0.topMargin + this.labelGroup.getMeasuredHeight() + frameLayout$LayoutParams0.bottomMargin : v + frameLayout$LayoutParams0.topMargin + this.labelGroup.getMeasuredHeight() + frameLayout$LayoutParams0.bottomMargin;
    }

    @Override  // android.view.View
    protected int getSuggestedMinimumWidth() {
        FrameLayout.LayoutParams frameLayout$LayoutParams0 = (FrameLayout.LayoutParams)this.labelGroup.getLayoutParams();
        int v = frameLayout$LayoutParams0.leftMargin + this.labelGroup.getMeasuredWidth() + frameLayout$LayoutParams0.rightMargin;
        return Math.max(this.getSuggestedIconWidth(), v);
    }

    private boolean hasBadge() {
        return this.badgeDrawable != null;
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void initialize(MenuItemImpl menuItemImpl0, int v) {
        this.itemData = menuItemImpl0;
        this.setCheckable(menuItemImpl0.isCheckable());
        this.setChecked(menuItemImpl0.isChecked());
        this.setEnabled(menuItemImpl0.isEnabled());
        this.setIcon(menuItemImpl0.getIcon());
        this.setTitle(menuItemImpl0.getTitle());
        this.setId(menuItemImpl0.getItemId());
        if(!TextUtils.isEmpty(menuItemImpl0.getContentDescription())) {
            this.setContentDescription(menuItemImpl0.getContentDescription());
        }
        CharSequence charSequence0 = TextUtils.isEmpty(menuItemImpl0.getTooltipText()) ? menuItemImpl0.getTitle() : menuItemImpl0.getTooltipText();
        if(Build.VERSION.SDK_INT > 23) {
            TooltipCompat.setTooltipText(this, charSequence0);
        }
        this.setVisibility((menuItemImpl0.isVisible() ? 0 : 8));
        this.initialized = true;
    }

    private boolean isActiveIndicatorResizeableAndUnlabeled() {
        return this.activeIndicatorResizeable && this.labelVisibilityMode == 2;
    }

    private void maybeAnimateActiveIndicatorToProgress(float f) {
        if(this.activeIndicatorEnabled && this.initialized && ViewCompat.isAttachedToWindow(this)) {
            ValueAnimator valueAnimator0 = this.activeIndicatorAnimator;
            if(valueAnimator0 != null) {
                valueAnimator0.cancel();
                this.activeIndicatorAnimator = null;
            }
            ValueAnimator valueAnimator1 = ValueAnimator.ofFloat(new float[]{this.activeIndicatorProgress, f});
            this.activeIndicatorAnimator = valueAnimator1;
            valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator0) {
                    float f = (float)(((Float)valueAnimator0.getAnimatedValue()));
                    NavigationBarItemView.this.setActiveIndicatorProgress(f, f);
                }
            });
            this.activeIndicatorAnimator.setInterpolator(MotionUtils.resolveThemeInterpolator(this.getContext(), attr.motionEasingEmphasizedInterpolator, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
            ValueAnimator valueAnimator2 = this.activeIndicatorAnimator;
            Context context0 = this.getContext();
            int v = this.getResources().getInteger(integer.material_motion_duration_long_1);
            valueAnimator2.setDuration(((long)MotionUtils.resolveThemeDuration(context0, attr.motionDurationLong2, v)));
            this.activeIndicatorAnimator.start();
            return;
        }
        this.setActiveIndicatorProgress(f, f);
    }

    @Override  // android.view.ViewGroup
    public int[] onCreateDrawableState(int v) {
        int[] arr_v = super.onCreateDrawableState(v + 1);
        if(this.itemData != null && this.itemData.isCheckable() && this.itemData.isChecked()) {
            NavigationBarItemView.mergeDrawableStates(arr_v, NavigationBarItemView.CHECKED_STATE_SET);
        }
        return arr_v;
    }

    @Override  // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo0) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo0);
        if(this.badgeDrawable != null && this.badgeDrawable.isVisible()) {
            CharSequence charSequence0 = this.itemData.getTitle();
            if(!TextUtils.isEmpty(this.itemData.getContentDescription())) {
                charSequence0 = this.itemData.getContentDescription();
            }
            accessibilityNodeInfo0.setContentDescription(charSequence0 + ", " + this.badgeDrawable.getContentDescription());
        }
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0 = AccessibilityNodeInfoCompat.wrap(accessibilityNodeInfo0);
        accessibilityNodeInfoCompat0.setCollectionItemInfo(CollectionItemInfoCompat.obtain(0, 1, this.getItemVisiblePosition(), 1, false, this.isSelected()));
        if(this.isSelected()) {
            accessibilityNodeInfoCompat0.setClickable(false);
            accessibilityNodeInfoCompat0.removeAction(AccessibilityActionCompat.ACTION_CLICK);
        }
        accessibilityNodeInfoCompat0.setRoleDescription(this.getResources().getString(string.item_view_role_description));
    }

    @Override  // android.view.View
    protected void onSizeChanged(int v, int v1, int v2, int v3) {
        super.onSizeChanged(v, v1, v2, v3);
        this.post(() -> if(NavigationBarItemView.this.activeIndicatorView != null && this.val$width > 0) {
            int v1 = Math.min(NavigationBarItemView.this.activeIndicatorDesiredWidth, this.val$width - NavigationBarItemView.this.activeIndicatorMarginHorizontal * 2);
            FrameLayout.LayoutParams frameLayout$LayoutParams0 = (FrameLayout.LayoutParams)NavigationBarItemView.this.activeIndicatorView.getLayoutParams();
            frameLayout$LayoutParams0.height = NavigationBarItemView.this.isActiveIndicatorResizeableAndUnlabeled() ? v1 : NavigationBarItemView.this.activeIndicatorDesiredHeight;
            frameLayout$LayoutParams0.width = v1;
            NavigationBarItemView.this.activeIndicatorView.setLayoutParams(frameLayout$LayoutParams0);
        });

        class com.google.android.material.navigation.NavigationBarItemView.2 implements Runnable {
            com.google.android.material.navigation.NavigationBarItemView.2(int v) {
            }

            @Override
            public void run() {
                NavigationBarItemView.this.updateActiveIndicatorLayoutParams(this.val$width);
            }
        }

    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public boolean prefersCondensedTitle() {
        return false;
    }

    private void refreshChecked() {
        MenuItemImpl menuItemImpl0 = this.itemData;
        if(menuItemImpl0 != null) {
            this.setChecked(menuItemImpl0.isChecked());
        }
    }

    private void refreshItemBackground() {
        Drawable drawable0 = this.itemBackground;
        Drawable drawable1 = null;
        boolean z = true;
        if(this.itemRippleColor != null) {
            Drawable drawable2 = this.getActiveIndicatorDrawable();
            if(this.activeIndicatorEnabled && this.getActiveIndicatorDrawable() != null && this.iconContainer != null && drawable2 != null) {
                drawable1 = new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(this.itemRippleColor), null, drawable2);
                z = false;
            }
            else if(drawable0 == null) {
                drawable0 = NavigationBarItemView.createItemBackgroundCompat(this.itemRippleColor);
            }
        }
        FrameLayout frameLayout0 = this.iconContainer;
        if(frameLayout0 != null) {
            frameLayout0.setPadding(0, 0, 0, 0);
            this.iconContainer.setForeground(drawable1);
        }
        ViewCompat.setBackground(this, drawable0);
        if(Build.VERSION.SDK_INT >= 26) {
            LinkFollowing..ExternalSyntheticApiModelOutline0.m(this, z);
        }
    }

    void removeBadge() {
        this.tryRemoveBadgeFromAnchor(this.icon);
    }

    public void setActiveIndicatorDrawable(Drawable drawable0) {
        View view0 = this.activeIndicatorView;
        if(view0 == null) {
            return;
        }
        view0.setBackgroundDrawable(drawable0);
        this.refreshItemBackground();
    }

    public void setActiveIndicatorEnabled(boolean z) {
        this.activeIndicatorEnabled = z;
        this.refreshItemBackground();
        View view0 = this.activeIndicatorView;
        if(view0 != null) {
            view0.setVisibility((z ? 0 : 8));
            this.requestLayout();
        }
    }

    public void setActiveIndicatorHeight(int v) {
        this.activeIndicatorDesiredHeight = v;
        this.updateActiveIndicatorLayoutParams(this.getWidth());
    }

    public void setActiveIndicatorLabelPadding(int v) {
        if(this.activeIndicatorLabelPadding != v) {
            this.activeIndicatorLabelPadding = v;
            this.refreshChecked();
        }
    }

    public void setActiveIndicatorMarginHorizontal(int v) {
        this.activeIndicatorMarginHorizontal = v;
        this.updateActiveIndicatorLayoutParams(this.getWidth());
    }

    private void setActiveIndicatorProgress(float f, float f1) {
        View view0 = this.activeIndicatorView;
        if(view0 != null) {
            this.activeIndicatorTransform.updateForProgress(f, f1, view0);
        }
        this.activeIndicatorProgress = f;
    }

    public void setActiveIndicatorResizeable(boolean z) {
        this.activeIndicatorResizeable = z;
    }

    public void setActiveIndicatorWidth(int v) {
        this.activeIndicatorDesiredWidth = v;
        this.updateActiveIndicatorLayoutParams(this.getWidth());
    }

    void setBadge(BadgeDrawable badgeDrawable0) {
        if(this.badgeDrawable != badgeDrawable0) {
            if(this.hasBadge() && this.icon != null) {
                Log.w("NavigationBar", "Multiple badges shouldn\'t be attached to one item.");
                this.tryRemoveBadgeFromAnchor(this.icon);
            }
            this.badgeDrawable = badgeDrawable0;
            ImageView imageView0 = this.icon;
            if(imageView0 != null) {
                this.tryAttachBadgeToAnchor(imageView0);
            }
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void setCheckable(boolean z) {
        this.refreshDrawableState();
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void setChecked(boolean z) {
        int v = this.largeLabel.getWidth();
        this.largeLabel.setPivotX(((float)(v / 2)));
        float f = (float)this.largeLabel.getBaseline();
        this.largeLabel.setPivotY(f);
        int v1 = this.smallLabel.getWidth();
        this.smallLabel.setPivotX(((float)(v1 / 2)));
        float f1 = (float)this.smallLabel.getBaseline();
        this.smallLabel.setPivotY(f1);
        this.maybeAnimateActiveIndicatorToProgress((z ? 1.0f : 0.0f));
        switch(this.labelVisibilityMode) {
            case -1: {
                if(!this.isShifting) {
                    NavigationBarItemView.updateViewPaddingBottom(this.labelGroup, this.itemPaddingBottom);
                    if(z) {
                        NavigationBarItemView.setViewTopMarginAndGravity(this.getIconOrContainer(), ((int)(((float)this.itemPaddingTop) + this.shiftAmount)), 49);
                        NavigationBarItemView.setViewScaleValues(this.largeLabel, 1.0f, 1.0f, 0);
                        NavigationBarItemView.setViewScaleValues(this.smallLabel, this.scaleUpFactor, this.scaleUpFactor, 4);
                    }
                    else {
                        NavigationBarItemView.setViewTopMarginAndGravity(this.getIconOrContainer(), this.itemPaddingTop, 49);
                        NavigationBarItemView.setViewScaleValues(this.largeLabel, this.scaleDownFactor, this.scaleDownFactor, 4);
                        NavigationBarItemView.setViewScaleValues(this.smallLabel, 1.0f, 1.0f, 0);
                    }
                }
                else if(z) {
                    NavigationBarItemView.setViewTopMarginAndGravity(this.getIconOrContainer(), this.itemPaddingTop, 49);
                    NavigationBarItemView.updateViewPaddingBottom(this.labelGroup, this.itemPaddingBottom);
                    this.largeLabel.setVisibility(0);
                    this.smallLabel.setVisibility(4);
                }
                else {
                    NavigationBarItemView.setViewTopMarginAndGravity(this.getIconOrContainer(), this.itemPaddingTop, 17);
                    NavigationBarItemView.updateViewPaddingBottom(this.labelGroup, 0);
                    this.largeLabel.setVisibility(4);
                    this.smallLabel.setVisibility(4);
                }
                break;
            }
            case 0: {
                if(z) {
                    NavigationBarItemView.setViewTopMarginAndGravity(this.getIconOrContainer(), this.itemPaddingTop, 49);
                    NavigationBarItemView.updateViewPaddingBottom(this.labelGroup, this.itemPaddingBottom);
                    this.largeLabel.setVisibility(0);
                }
                else {
                    NavigationBarItemView.setViewTopMarginAndGravity(this.getIconOrContainer(), this.itemPaddingTop, 17);
                    NavigationBarItemView.updateViewPaddingBottom(this.labelGroup, 0);
                    this.largeLabel.setVisibility(4);
                }
                this.smallLabel.setVisibility(4);
                break;
            }
            case 1: {
                NavigationBarItemView.updateViewPaddingBottom(this.labelGroup, this.itemPaddingBottom);
                if(z) {
                    NavigationBarItemView.setViewTopMarginAndGravity(this.getIconOrContainer(), ((int)(((float)this.itemPaddingTop) + this.shiftAmount)), 49);
                    NavigationBarItemView.setViewScaleValues(this.largeLabel, 1.0f, 1.0f, 0);
                    NavigationBarItemView.setViewScaleValues(this.smallLabel, this.scaleUpFactor, this.scaleUpFactor, 4);
                }
                else {
                    NavigationBarItemView.setViewTopMarginAndGravity(this.getIconOrContainer(), this.itemPaddingTop, 49);
                    NavigationBarItemView.setViewScaleValues(this.largeLabel, this.scaleDownFactor, this.scaleDownFactor, 4);
                    NavigationBarItemView.setViewScaleValues(this.smallLabel, 1.0f, 1.0f, 0);
                }
                break;
            }
            case 2: {
                NavigationBarItemView.setViewTopMarginAndGravity(this.getIconOrContainer(), this.itemPaddingTop, 17);
                this.largeLabel.setVisibility(8);
                this.smallLabel.setVisibility(8);
            }
        }
        this.refreshDrawableState();
        this.setSelected(z);
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView, android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.smallLabel.setEnabled(z);
        this.largeLabel.setEnabled(z);
        this.icon.setEnabled(z);
        if(z) {
            ViewCompat.setPointerIcon(this, PointerIconCompat.getSystemIcon(this.getContext(), 1002));
            return;
        }
        ViewCompat.setPointerIcon(this, null);
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void setIcon(Drawable drawable0) {
        if(drawable0 == this.originalIconDrawable) {
            return;
        }
        this.originalIconDrawable = drawable0;
        if(drawable0 != null) {
            Drawable.ConstantState drawable$ConstantState0 = drawable0.getConstantState();
            if(drawable$ConstantState0 != null) {
                drawable0 = drawable$ConstantState0.newDrawable();
            }
            drawable0 = DrawableCompat.wrap(drawable0).mutate();
            this.wrappedIconDrawable = drawable0;
            ColorStateList colorStateList0 = this.iconTint;
            if(colorStateList0 != null) {
                DrawableCompat.setTintList(drawable0, colorStateList0);
            }
        }
        this.icon.setImageDrawable(drawable0);
    }

    public void setIconSize(int v) {
        FrameLayout.LayoutParams frameLayout$LayoutParams0 = (FrameLayout.LayoutParams)this.icon.getLayoutParams();
        frameLayout$LayoutParams0.width = v;
        frameLayout$LayoutParams0.height = v;
        this.icon.setLayoutParams(frameLayout$LayoutParams0);
    }

    public void setIconTintList(ColorStateList colorStateList0) {
        this.iconTint = colorStateList0;
        if(this.itemData != null) {
            Drawable drawable0 = this.wrappedIconDrawable;
            if(drawable0 != null) {
                DrawableCompat.setTintList(drawable0, colorStateList0);
                this.wrappedIconDrawable.invalidateSelf();
            }
        }
    }

    public void setItemBackground(int v) {
        this.setItemBackground((v == 0 ? null : ContextCompat.getDrawable(this.getContext(), v)));
    }

    public void setItemBackground(Drawable drawable0) {
        if(drawable0 != null && drawable0.getConstantState() != null) {
            drawable0 = drawable0.getConstantState().newDrawable().mutate();
        }
        this.itemBackground = drawable0;
        this.refreshItemBackground();
    }

    public void setItemPaddingBottom(int v) {
        if(this.itemPaddingBottom != v) {
            this.itemPaddingBottom = v;
            this.refreshChecked();
        }
    }

    public void setItemPaddingTop(int v) {
        if(this.itemPaddingTop != v) {
            this.itemPaddingTop = v;
            this.refreshChecked();
        }
    }

    public void setItemPosition(int v) {
        this.itemPosition = v;
    }

    public void setItemRippleColor(ColorStateList colorStateList0) {
        this.itemRippleColor = colorStateList0;
        this.refreshItemBackground();
    }

    public void setLabelVisibilityMode(int v) {
        if(this.labelVisibilityMode != v) {
            this.labelVisibilityMode = v;
            this.updateActiveIndicatorTransform();
            this.updateActiveIndicatorLayoutParams(this.getWidth());
            this.refreshChecked();
        }
    }

    public void setShifting(boolean z) {
        if(this.isShifting != z) {
            this.isShifting = z;
            this.refreshChecked();
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void setShortcut(boolean z, char c) {
    }

    public void setTextAppearanceActive(int v) {
        this.activeTextAppearance = v;
        NavigationBarItemView.setTextAppearanceWithoutFontScaling(this.largeLabel, v);
        this.calculateTextScaleFactors(this.smallLabel.getTextSize(), this.largeLabel.getTextSize());
    }

    public void setTextAppearanceActiveBoldEnabled(boolean z) {
        this.setTextAppearanceActive(this.activeTextAppearance);
        Typeface typeface0 = this.largeLabel.getTypeface();
        this.largeLabel.setTypeface(typeface0, ((int)z));
    }

    public void setTextAppearanceInactive(int v) {
        NavigationBarItemView.setTextAppearanceWithoutFontScaling(this.smallLabel, v);
        this.calculateTextScaleFactors(this.smallLabel.getTextSize(), this.largeLabel.getTextSize());
    }

    private static void setTextAppearanceWithoutFontScaling(TextView textView0, int v) {
        TextViewCompat.setTextAppearance(textView0, v);
        int v1 = MaterialResources.getUnscaledTextSize(textView0.getContext(), v, 0);
        if(v1 != 0) {
            textView0.setTextSize(0, ((float)v1));
        }
    }

    public void setTextColor(ColorStateList colorStateList0) {
        if(colorStateList0 != null) {
            this.smallLabel.setTextColor(colorStateList0);
            this.largeLabel.setTextColor(colorStateList0);
        }
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public void setTitle(CharSequence charSequence0) {
        this.smallLabel.setText(charSequence0);
        this.largeLabel.setText(charSequence0);
        if(this.itemData == null || TextUtils.isEmpty(this.itemData.getContentDescription())) {
            this.setContentDescription(charSequence0);
        }
        if(this.itemData != null && !TextUtils.isEmpty(this.itemData.getTooltipText())) {
            charSequence0 = this.itemData.getTooltipText();
        }
        if(Build.VERSION.SDK_INT > 23) {
            TooltipCompat.setTooltipText(this, charSequence0);
        }
    }

    private static void setViewScaleValues(View view0, float f, float f1, int v) {
        view0.setScaleX(f);
        view0.setScaleY(f1);
        view0.setVisibility(v);
    }

    private static void setViewTopMarginAndGravity(View view0, int v, int v1) {
        FrameLayout.LayoutParams frameLayout$LayoutParams0 = (FrameLayout.LayoutParams)view0.getLayoutParams();
        frameLayout$LayoutParams0.topMargin = v;
        frameLayout$LayoutParams0.bottomMargin = v;
        frameLayout$LayoutParams0.gravity = v1;
        view0.setLayoutParams(frameLayout$LayoutParams0);
    }

    @Override  // androidx.appcompat.view.menu.MenuView$ItemView
    public boolean showsIcon() {
        return true;
    }

    private void tryAttachBadgeToAnchor(View view0) {
        if(this.hasBadge() && view0 != null) {
            this.setClipChildren(false);
            this.setClipToPadding(false);
            BadgeUtils.attachBadgeDrawable(this.badgeDrawable, view0, this.getCustomParentForBadge(view0));
        }
    }

    private void tryRemoveBadgeFromAnchor(View view0) {
        if(!this.hasBadge()) {
            return;
        }
        if(view0 != null) {
            this.setClipChildren(true);
            this.setClipToPadding(true);
            BadgeUtils.detachBadgeDrawable(this.badgeDrawable, view0);
        }
        this.badgeDrawable = null;
    }

    private void tryUpdateBadgeBounds(View view0) {
        if(!this.hasBadge()) {
            return;
        }
        BadgeUtils.setBadgeDrawableBounds(this.badgeDrawable, view0, this.getCustomParentForBadge(view0));
    }

    // 检测为 Lambda 实现
    private void updateActiveIndicatorLayoutParams(int v) [...]

    private void updateActiveIndicatorTransform() {
        if(this.isActiveIndicatorResizeableAndUnlabeled()) {
            this.activeIndicatorTransform = NavigationBarItemView.ACTIVE_INDICATOR_UNLABELED_TRANSFORM;
            return;
        }
        this.activeIndicatorTransform = NavigationBarItemView.ACTIVE_INDICATOR_LABELED_TRANSFORM;
    }

    private static void updateViewPaddingBottom(View view0, int v) {
        view0.setPadding(view0.getPaddingLeft(), view0.getPaddingTop(), view0.getPaddingRight(), v);
    }
}

