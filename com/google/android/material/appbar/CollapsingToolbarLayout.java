package com.google.android.material.appbar;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region.Op;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.TextUtils.TruncateAt;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.math.MathUtils;
import androidx.core.util.ObjectsCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.id;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CollapsingToolbarLayout extends FrameLayout {
    public static class LayoutParams extends FrameLayout.LayoutParams {
        public static final int COLLAPSE_MODE_OFF = 0;
        public static final int COLLAPSE_MODE_PARALLAX = 2;
        public static final int COLLAPSE_MODE_PIN = 1;
        private static final float DEFAULT_PARALLAX_MULTIPLIER = 0.5f;
        int collapseMode;
        float parallaxMult;

        public LayoutParams(int v, int v1) {
            super(v, v1);
            this.collapseMode = 0;
            this.parallaxMult = 0.5f;
        }

        public LayoutParams(int v, int v1, int v2) {
            super(v, v1, v2);
            this.collapseMode = 0;
            this.parallaxMult = 0.5f;
        }

        public LayoutParams(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
            this.collapseMode = 0;
            this.parallaxMult = 0.5f;
            TypedArray typedArray0 = context0.obtainStyledAttributes(attributeSet0, styleable.CollapsingToolbarLayout_Layout);
            this.collapseMode = typedArray0.getInt(styleable.CollapsingToolbarLayout_Layout_layout_collapseMode, 0);
            this.setParallaxMultiplier(typedArray0.getFloat(styleable.CollapsingToolbarLayout_Layout_layout_collapseParallaxMultiplier, 0.5f));
            typedArray0.recycle();
        }

        public LayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
            super(viewGroup$LayoutParams0);
            this.collapseMode = 0;
            this.parallaxMult = 0.5f;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0) {
            super(viewGroup$MarginLayoutParams0);
            this.collapseMode = 0;
            this.parallaxMult = 0.5f;
        }

        public LayoutParams(FrameLayout.LayoutParams frameLayout$LayoutParams0) {
            super(frameLayout$LayoutParams0);
            this.collapseMode = 0;
            this.parallaxMult = 0.5f;
        }

        public LayoutParams(LayoutParams collapsingToolbarLayout$LayoutParams0) {
            super(collapsingToolbarLayout$LayoutParams0);
            this.collapseMode = collapsingToolbarLayout$LayoutParams0.collapseMode;
            this.parallaxMult = collapsingToolbarLayout$LayoutParams0.parallaxMult;
        }

        public int getCollapseMode() {
            return this.collapseMode;
        }

        public float getParallaxMultiplier() {
            return this.parallaxMult;
        }

        public void setCollapseMode(int v) {
            this.collapseMode = v;
        }

        public void setParallaxMultiplier(float f) {
            this.parallaxMult = f;
        }
    }

    class OffsetUpdateListener implements OnOffsetChangedListener {
        @Override  // com.google.android.material.appbar.AppBarLayout$OnOffsetChangedListener
        public void onOffsetChanged(AppBarLayout appBarLayout0, int v) {
            CollapsingToolbarLayout.this.currentOffset = v;
            int v1 = CollapsingToolbarLayout.this.lastInsets == null ? 0 : CollapsingToolbarLayout.this.lastInsets.getSystemWindowInsetTop();
            int v2 = CollapsingToolbarLayout.this.getChildCount();
            for(int v3 = 0; v3 < v2; ++v3) {
                View view0 = CollapsingToolbarLayout.this.getChildAt(v3);
                LayoutParams collapsingToolbarLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
                ViewOffsetHelper viewOffsetHelper0 = CollapsingToolbarLayout.getViewOffsetHelper(view0);
                switch(collapsingToolbarLayout$LayoutParams0.collapseMode) {
                    case 1: {
                        viewOffsetHelper0.setTopAndBottomOffset(MathUtils.clamp(-v, 0, CollapsingToolbarLayout.this.getMaxOffsetForPinChild(view0)));
                        break;
                    }
                    case 2: {
                        viewOffsetHelper0.setTopAndBottomOffset(Math.round(((float)(-v)) * collapsingToolbarLayout$LayoutParams0.parallaxMult));
                    }
                }
            }
            CollapsingToolbarLayout.this.updateScrimVisibility();
            if(CollapsingToolbarLayout.this.statusBarScrim != null && v1 > 0) {
                ViewCompat.postInvalidateOnAnimation(CollapsingToolbarLayout.this);
            }
            int v4 = CollapsingToolbarLayout.this.getHeight();
            int v5 = v4 - ViewCompat.getMinimumHeight(CollapsingToolbarLayout.this) - v1;
            float f = Math.min(1.0f, ((float)(v4 - CollapsingToolbarLayout.this.getScrimVisibleHeightTrigger())) / ((float)v5));
            CollapsingToolbarLayout.this.collapsingTextHelper.setFadeModeStartFraction(f);
            CollapsingToolbarLayout.this.collapsingTextHelper.setCurrentOffsetY(CollapsingToolbarLayout.this.currentOffset + v5);
            CollapsingToolbarLayout.this.collapsingTextHelper.setExpansionFraction(((float)Math.abs(v)) / ((float)v5));
        }
    }

    public interface StaticLayoutBuilderConfigurer extends com.google.android.material.internal.StaticLayoutBuilderConfigurer {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TitleCollapseMode {
    }

    private static final int DEFAULT_SCRIM_ANIMATION_DURATION = 600;
    private static final int DEF_STYLE_RES = 0;
    public static final int TITLE_COLLAPSE_MODE_FADE = 1;
    public static final int TITLE_COLLAPSE_MODE_SCALE;
    final CollapsingTextHelper collapsingTextHelper;
    private boolean collapsingTitleEnabled;
    private Drawable contentScrim;
    int currentOffset;
    private boolean drawCollapsingTitle;
    private View dummyView;
    final ElevationOverlayProvider elevationOverlayProvider;
    private int expandedMarginBottom;
    private int expandedMarginEnd;
    private int expandedMarginStart;
    private int expandedMarginTop;
    private int extraMultilineHeight;
    private boolean extraMultilineHeightEnabled;
    private boolean forceApplySystemWindowInsetTop;
    WindowInsetsCompat lastInsets;
    private OnOffsetChangedListener onOffsetChangedListener;
    private boolean refreshToolbar;
    private int scrimAlpha;
    private long scrimAnimationDuration;
    private final TimeInterpolator scrimAnimationFadeInInterpolator;
    private final TimeInterpolator scrimAnimationFadeOutInterpolator;
    private ValueAnimator scrimAnimator;
    private int scrimVisibleHeightTrigger;
    private boolean scrimsAreShown;
    Drawable statusBarScrim;
    private int titleCollapseMode;
    private final Rect tmpRect;
    private ViewGroup toolbar;
    private View toolbarDirectChild;
    private int toolbarId;
    private int topInsetApplied;

    static {
        CollapsingToolbarLayout.DEF_STYLE_RES = style.Widget_Design_CollapsingToolbar;
    }

    public CollapsingToolbarLayout(Context context0) {
        this(context0, null);
    }

    public CollapsingToolbarLayout(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.collapsingToolbarLayoutStyle);
    }

    public CollapsingToolbarLayout(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, CollapsingToolbarLayout.DEF_STYLE_RES), attributeSet0, v);
        this.refreshToolbar = true;
        this.tmpRect = new Rect();
        this.scrimVisibleHeightTrigger = -1;
        this.topInsetApplied = 0;
        this.extraMultilineHeight = 0;
        Context context1 = this.getContext();
        CollapsingTextHelper collapsingTextHelper0 = new CollapsingTextHelper(this);
        this.collapsingTextHelper = collapsingTextHelper0;
        collapsingTextHelper0.setTextSizeInterpolator(AnimationUtils.DECELERATE_INTERPOLATOR);
        collapsingTextHelper0.setRtlTextDirectionHeuristicsEnabled(false);
        this.elevationOverlayProvider = new ElevationOverlayProvider(context1);
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context1, attributeSet0, styleable.CollapsingToolbarLayout, v, CollapsingToolbarLayout.DEF_STYLE_RES, new int[0]);
        collapsingTextHelper0.setExpandedTextGravity(typedArray0.getInt(styleable.CollapsingToolbarLayout_expandedTitleGravity, 0x800053));
        collapsingTextHelper0.setCollapsedTextGravity(typedArray0.getInt(styleable.CollapsingToolbarLayout_collapsedTitleGravity, 0x800013));
        int v1 = typedArray0.getDimensionPixelSize(styleable.CollapsingToolbarLayout_expandedTitleMargin, 0);
        this.expandedMarginBottom = v1;
        this.expandedMarginEnd = v1;
        this.expandedMarginTop = v1;
        this.expandedMarginStart = v1;
        if(typedArray0.hasValue(styleable.CollapsingToolbarLayout_expandedTitleMarginStart)) {
            this.expandedMarginStart = typedArray0.getDimensionPixelSize(styleable.CollapsingToolbarLayout_expandedTitleMarginStart, 0);
        }
        if(typedArray0.hasValue(styleable.CollapsingToolbarLayout_expandedTitleMarginEnd)) {
            this.expandedMarginEnd = typedArray0.getDimensionPixelSize(styleable.CollapsingToolbarLayout_expandedTitleMarginEnd, 0);
        }
        if(typedArray0.hasValue(styleable.CollapsingToolbarLayout_expandedTitleMarginTop)) {
            this.expandedMarginTop = typedArray0.getDimensionPixelSize(styleable.CollapsingToolbarLayout_expandedTitleMarginTop, 0);
        }
        if(typedArray0.hasValue(styleable.CollapsingToolbarLayout_expandedTitleMarginBottom)) {
            this.expandedMarginBottom = typedArray0.getDimensionPixelSize(styleable.CollapsingToolbarLayout_expandedTitleMarginBottom, 0);
        }
        this.collapsingTitleEnabled = typedArray0.getBoolean(styleable.CollapsingToolbarLayout_titleEnabled, true);
        this.setTitle(typedArray0.getText(styleable.CollapsingToolbarLayout_title));
        collapsingTextHelper0.setExpandedTextAppearance(style.TextAppearance_Design_CollapsingToolbar_Expanded);
        collapsingTextHelper0.setCollapsedTextAppearance(androidx.appcompat.R.style.TextAppearance_AppCompat_Widget_ActionBar_Title);
        if(typedArray0.hasValue(styleable.CollapsingToolbarLayout_expandedTitleTextAppearance)) {
            collapsingTextHelper0.setExpandedTextAppearance(typedArray0.getResourceId(styleable.CollapsingToolbarLayout_expandedTitleTextAppearance, 0));
        }
        if(typedArray0.hasValue(styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance)) {
            collapsingTextHelper0.setCollapsedTextAppearance(typedArray0.getResourceId(styleable.CollapsingToolbarLayout_collapsedTitleTextAppearance, 0));
        }
        if(typedArray0.hasValue(styleable.CollapsingToolbarLayout_titleTextEllipsize)) {
            this.setTitleEllipsize(this.convertEllipsizeToTruncateAt(typedArray0.getInt(styleable.CollapsingToolbarLayout_titleTextEllipsize, -1)));
        }
        if(typedArray0.hasValue(styleable.CollapsingToolbarLayout_expandedTitleTextColor)) {
            collapsingTextHelper0.setExpandedTextColor(MaterialResources.getColorStateList(context1, typedArray0, styleable.CollapsingToolbarLayout_expandedTitleTextColor));
        }
        if(typedArray0.hasValue(styleable.CollapsingToolbarLayout_collapsedTitleTextColor)) {
            collapsingTextHelper0.setCollapsedTextColor(MaterialResources.getColorStateList(context1, typedArray0, styleable.CollapsingToolbarLayout_collapsedTitleTextColor));
        }
        this.scrimVisibleHeightTrigger = typedArray0.getDimensionPixelSize(styleable.CollapsingToolbarLayout_scrimVisibleHeightTrigger, -1);
        if(typedArray0.hasValue(styleable.CollapsingToolbarLayout_maxLines)) {
            collapsingTextHelper0.setMaxLines(typedArray0.getInt(styleable.CollapsingToolbarLayout_maxLines, 1));
        }
        if(typedArray0.hasValue(styleable.CollapsingToolbarLayout_titlePositionInterpolator)) {
            collapsingTextHelper0.setPositionInterpolator(android.view.animation.AnimationUtils.loadInterpolator(context1, typedArray0.getResourceId(styleable.CollapsingToolbarLayout_titlePositionInterpolator, 0)));
        }
        this.scrimAnimationDuration = (long)typedArray0.getInt(styleable.CollapsingToolbarLayout_scrimAnimationDuration, 600);
        this.scrimAnimationFadeInInterpolator = MotionUtils.resolveThemeInterpolator(context1, attr.motionEasingStandardInterpolator, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        this.scrimAnimationFadeOutInterpolator = MotionUtils.resolveThemeInterpolator(context1, attr.motionEasingStandardInterpolator, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        this.setContentScrim(typedArray0.getDrawable(styleable.CollapsingToolbarLayout_contentScrim));
        this.setStatusBarScrim(typedArray0.getDrawable(styleable.CollapsingToolbarLayout_statusBarScrim));
        this.setTitleCollapseMode(typedArray0.getInt(styleable.CollapsingToolbarLayout_titleCollapseMode, 0));
        this.toolbarId = typedArray0.getResourceId(styleable.CollapsingToolbarLayout_toolbarId, -1);
        this.forceApplySystemWindowInsetTop = typedArray0.getBoolean(styleable.CollapsingToolbarLayout_forceApplySystemWindowInsetTop, false);
        this.extraMultilineHeightEnabled = typedArray0.getBoolean(styleable.CollapsingToolbarLayout_extraMultilineHeightEnabled, false);
        typedArray0.recycle();
        this.setWillNotDraw(false);
        ViewCompat.setOnApplyWindowInsetsListener(this, (/* 缺少LAMBDA参数 */, WindowInsetsCompat windowInsetsCompat0) -> {
            WindowInsetsCompat windowInsetsCompat1 = ViewCompat.getFitsSystemWindows(CollapsingToolbarLayout.this) ? windowInsetsCompat0 : null;
            if(!ObjectsCompat.equals(CollapsingToolbarLayout.this.lastInsets, windowInsetsCompat1)) {
                CollapsingToolbarLayout.this.lastInsets = windowInsetsCompat1;
                CollapsingToolbarLayout.this.requestLayout();
            }
            return windowInsetsCompat0.consumeSystemWindowInsets();
        });

        class com.google.android.material.appbar.CollapsingToolbarLayout.1 implements OnApplyWindowInsetsListener {
            @Override  // androidx.core.view.OnApplyWindowInsetsListener
            public WindowInsetsCompat onApplyWindowInsets(View view0, WindowInsetsCompat windowInsetsCompat0) {
                return CollapsingToolbarLayout.this.onWindowInsetChanged(windowInsetsCompat0);
            }
        }

    }

    private void animateScrim(int v) {
        this.ensureToolbar();
        ValueAnimator valueAnimator0 = this.scrimAnimator;
        if(valueAnimator0 == null) {
            ValueAnimator valueAnimator1 = new ValueAnimator();
            this.scrimAnimator = valueAnimator1;
            valueAnimator1.setInterpolator((v <= this.scrimAlpha ? this.scrimAnimationFadeOutInterpolator : this.scrimAnimationFadeInInterpolator));
            this.scrimAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override  // android.animation.ValueAnimator$AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator0) {
                    int v = (int)(((Integer)valueAnimator0.getAnimatedValue()));
                    CollapsingToolbarLayout.this.setScrimAlpha(v);
                }
            });
        }
        else if(valueAnimator0.isRunning()) {
            this.scrimAnimator.cancel();
        }
        this.scrimAnimator.setDuration(this.scrimAnimationDuration);
        this.scrimAnimator.setIntValues(new int[]{this.scrimAlpha, v});
        this.scrimAnimator.start();
    }

    @Override  // android.widget.FrameLayout
    protected boolean checkLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return viewGroup$LayoutParams0 instanceof LayoutParams;
    }

    private TextUtils.TruncateAt convertEllipsizeToTruncateAt(int v) {
        switch(v) {
            case 0: {
                return TextUtils.TruncateAt.START;
            }
            case 1: {
                return TextUtils.TruncateAt.MIDDLE;
            }
            case 3: {
                return TextUtils.TruncateAt.MARQUEE;
            }
            default: {
                return TextUtils.TruncateAt.END;
            }
        }
    }

    private void disableLiftOnScrollIfNeeded(AppBarLayout appBarLayout0) {
        if(this.isTitleCollapseFadeMode()) {
            appBarLayout0.setLiftOnScroll(false);
        }
    }

    @Override  // android.view.View
    public void draw(Canvas canvas0) {
        super.draw(canvas0);
        this.ensureToolbar();
        if(this.toolbar == null) {
            Drawable drawable0 = this.contentScrim;
            if(drawable0 != null && this.scrimAlpha > 0) {
                drawable0.mutate().setAlpha(this.scrimAlpha);
                this.contentScrim.draw(canvas0);
            }
        }
        if(this.collapsingTitleEnabled && this.drawCollapsingTitle) {
            if(this.toolbar == null || this.contentScrim == null || this.scrimAlpha <= 0 || !this.isTitleCollapseFadeMode() || this.collapsingTextHelper.getExpansionFraction() >= this.collapsingTextHelper.getFadeModeThresholdFraction()) {
                this.collapsingTextHelper.draw(canvas0);
            }
            else {
                int v = canvas0.save();
                canvas0.clipRect(this.contentScrim.getBounds(), Region.Op.DIFFERENCE);
                this.collapsingTextHelper.draw(canvas0);
                canvas0.restoreToCount(v);
            }
        }
        if(this.statusBarScrim != null && this.scrimAlpha > 0) {
            int v1 = this.lastInsets == null ? 0 : this.lastInsets.getSystemWindowInsetTop();
            if(v1 > 0) {
                this.statusBarScrim.setBounds(0, -this.currentOffset, this.getWidth(), v1 - this.currentOffset);
                this.statusBarScrim.mutate().setAlpha(this.scrimAlpha);
                this.statusBarScrim.draw(canvas0);
            }
        }
    }

    @Override  // android.view.ViewGroup
    protected boolean drawChild(Canvas canvas0, View view0, long v) {
        if(this.contentScrim != null && this.scrimAlpha > 0 && this.isToolbarChild(view0)) {
            this.updateContentScrimBounds(this.contentScrim, view0, this.getWidth(), this.getHeight());
            this.contentScrim.mutate().setAlpha(this.scrimAlpha);
            this.contentScrim.draw(canvas0);
            return super.drawChild(canvas0, view0, v) || true;
        }
        return super.drawChild(canvas0, view0, v);
    }

    @Override  // android.view.ViewGroup
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        int[] arr_v = this.getDrawableState();
        Drawable drawable0 = this.statusBarScrim;
        boolean z = drawable0 == null || !drawable0.isStateful() ? false : drawable0.setState(arr_v);
        Drawable drawable1 = this.contentScrim;
        if(drawable1 != null && drawable1.isStateful()) {
            z |= drawable1.setState(arr_v);
        }
        CollapsingTextHelper collapsingTextHelper0 = this.collapsingTextHelper;
        if(collapsingTextHelper0 != null) {
            z |= collapsingTextHelper0.setState(arr_v);
        }
        if(z) {
            this.invalidate();
        }
    }

    private void ensureToolbar() {
        if(!this.refreshToolbar) {
            return;
        }
        ViewGroup viewGroup0 = null;
        this.toolbar = null;
        this.toolbarDirectChild = null;
        int v = this.toolbarId;
        if(v != -1) {
            ViewGroup viewGroup1 = (ViewGroup)this.findViewById(v);
            this.toolbar = viewGroup1;
            if(viewGroup1 != null) {
                this.toolbarDirectChild = this.findDirectChild(viewGroup1);
            }
        }
        if(this.toolbar == null) {
            int v1 = this.getChildCount();
            for(int v2 = 0; v2 < v1; ++v2) {
                View view0 = this.getChildAt(v2);
                if(CollapsingToolbarLayout.isToolbar(view0)) {
                    viewGroup0 = (ViewGroup)view0;
                    break;
                }
            }
            this.toolbar = viewGroup0;
        }
        this.updateDummyView();
        this.refreshToolbar = false;
    }

    private View findDirectChild(View view0) {
        for(ViewParent viewParent0 = view0.getParent(); viewParent0 != this && viewParent0 != null; viewParent0 = viewParent0.getParent()) {
            if(viewParent0 instanceof View) {
                view0 = (View)viewParent0;
            }
        }
        return view0;
    }

    @Override  // android.widget.FrameLayout
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return this.generateDefaultLayoutParams();
    }

    @Override  // android.widget.FrameLayout
    protected FrameLayout.LayoutParams generateDefaultLayoutParams() {
        return this.generateDefaultLayoutParams();
    }

    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-1, -1);
    }

    @Override  // android.widget.FrameLayout
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return this.generateLayoutParams(attributeSet0);
    }

    @Override  // android.widget.FrameLayout
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return this.generateLayoutParams(viewGroup$LayoutParams0);
    }

    @Override  // android.widget.FrameLayout
    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet0) {
        return new LayoutParams(this.getContext(), attributeSet0);
    }

    protected FrameLayout.LayoutParams generateLayoutParams(ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        return new LayoutParams(viewGroup$LayoutParams0);
    }

    public int getCollapsedTitleGravity() {
        return this.collapsingTextHelper.getCollapsedTextGravity();
    }

    public float getCollapsedTitleTextSize() {
        return this.collapsingTextHelper.getCollapsedTextSize();
    }

    public Typeface getCollapsedTitleTypeface() {
        return this.collapsingTextHelper.getCollapsedTypeface();
    }

    public Drawable getContentScrim() {
        return this.contentScrim;
    }

    private int getDefaultContentScrimColorForTitleCollapseFadeMode() {
        ColorStateList colorStateList0 = MaterialColors.getColorStateListOrNull(this.getContext(), attr.colorSurfaceContainer);
        if(colorStateList0 != null) {
            return colorStateList0.getDefaultColor();
        }
        float f = this.getResources().getDimension(dimen.design_appbar_elevation);
        return this.elevationOverlayProvider.compositeOverlayWithThemeSurfaceColorIfNeeded(f);
    }

    public int getExpandedTitleGravity() {
        return this.collapsingTextHelper.getExpandedTextGravity();
    }

    public int getExpandedTitleMarginBottom() {
        return this.expandedMarginBottom;
    }

    public int getExpandedTitleMarginEnd() {
        return this.expandedMarginEnd;
    }

    public int getExpandedTitleMarginStart() {
        return this.expandedMarginStart;
    }

    public int getExpandedTitleMarginTop() {
        return this.expandedMarginTop;
    }

    public float getExpandedTitleTextSize() {
        return this.collapsingTextHelper.getExpandedTextSize();
    }

    public Typeface getExpandedTitleTypeface() {
        return this.collapsingTextHelper.getExpandedTypeface();
    }

    private static int getHeightWithMargins(View view0) {
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = view0.getLayoutParams();
        return viewGroup$LayoutParams0 instanceof ViewGroup.MarginLayoutParams ? view0.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0).topMargin + ((ViewGroup.MarginLayoutParams)viewGroup$LayoutParams0).bottomMargin : view0.getMeasuredHeight();
    }

    public int getHyphenationFrequency() {
        return this.collapsingTextHelper.getHyphenationFrequency();
    }

    public int getLineCount() {
        return this.collapsingTextHelper.getLineCount();
    }

    public float getLineSpacingAdd() {
        return this.collapsingTextHelper.getLineSpacingAdd();
    }

    public float getLineSpacingMultiplier() {
        return this.collapsingTextHelper.getLineSpacingMultiplier();
    }

    public int getMaxLines() {
        return this.collapsingTextHelper.getMaxLines();
    }

    final int getMaxOffsetForPinChild(View view0) {
        ViewOffsetHelper viewOffsetHelper0 = CollapsingToolbarLayout.getViewOffsetHelper(view0);
        LayoutParams collapsingToolbarLayout$LayoutParams0 = (LayoutParams)view0.getLayoutParams();
        return this.getHeight() - viewOffsetHelper0.getLayoutTop() - view0.getHeight() - collapsingToolbarLayout$LayoutParams0.bottomMargin;
    }

    int getScrimAlpha() {
        return this.scrimAlpha;
    }

    public long getScrimAnimationDuration() {
        return this.scrimAnimationDuration;
    }

    public int getScrimVisibleHeightTrigger() {
        int v = this.scrimVisibleHeightTrigger;
        if(v >= 0) {
            return v + this.topInsetApplied + this.extraMultilineHeight;
        }
        int v1 = this.lastInsets == null ? 0 : this.lastInsets.getSystemWindowInsetTop();
        int v2 = ViewCompat.getMinimumHeight(this);
        return v2 <= 0 ? this.getHeight() / 3 : Math.min(v2 * 2 + v1, this.getHeight());
    }

    public Drawable getStatusBarScrim() {
        return this.statusBarScrim;
    }

    // 去混淆评级： 低(20)
    public CharSequence getTitle() {
        return this.collapsingTitleEnabled ? this.collapsingTextHelper.getText() : null;
    }

    public int getTitleCollapseMode() {
        return this.titleCollapseMode;
    }

    public TimeInterpolator getTitlePositionInterpolator() {
        return this.collapsingTextHelper.getPositionInterpolator();
    }

    public TextUtils.TruncateAt getTitleTextEllipsize() {
        return this.collapsingTextHelper.getTitleTextEllipsize();
    }

    private static CharSequence getToolbarTitle(View view0) {
        if(view0 instanceof Toolbar) {
            return ((Toolbar)view0).getTitle();
        }
        return view0 instanceof android.widget.Toolbar ? ((android.widget.Toolbar)view0).getTitle() : null;
    }

    static ViewOffsetHelper getViewOffsetHelper(View view0) {
        ViewOffsetHelper viewOffsetHelper0 = (ViewOffsetHelper)view0.getTag(id.view_offset_helper);
        if(viewOffsetHelper0 == null) {
            viewOffsetHelper0 = new ViewOffsetHelper(view0);
            view0.setTag(id.view_offset_helper, viewOffsetHelper0);
        }
        return viewOffsetHelper0;
    }

    public boolean isExtraMultilineHeightEnabled() {
        return this.extraMultilineHeightEnabled;
    }

    public boolean isForceApplySystemWindowInsetTop() {
        return this.forceApplySystemWindowInsetTop;
    }

    public boolean isRtlTextDirectionHeuristicsEnabled() {
        return this.collapsingTextHelper.isRtlTextDirectionHeuristicsEnabled();
    }

    private boolean isTitleCollapseFadeMode() {
        return this.titleCollapseMode == 1;
    }

    public boolean isTitleEnabled() {
        return this.collapsingTitleEnabled;
    }

    // 去混淆评级： 低(20)
    private static boolean isToolbar(View view0) {
        return view0 instanceof Toolbar || view0 instanceof android.widget.Toolbar;
    }

    private boolean isToolbarChild(View view0) {
        return this.toolbarDirectChild == null || this.toolbarDirectChild == this ? view0 == this.toolbar : view0 == this.toolbarDirectChild;
    }

    @Override  // android.view.ViewGroup
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewParent viewParent0 = this.getParent();
        if(viewParent0 instanceof AppBarLayout) {
            this.disableLiftOnScrollIfNeeded(((AppBarLayout)viewParent0));
            ViewCompat.setFitsSystemWindows(this, ViewCompat.getFitsSystemWindows(((AppBarLayout)viewParent0)));
            if(this.onOffsetChangedListener == null) {
                this.onOffsetChangedListener = new OffsetUpdateListener(this);
            }
            ((AppBarLayout)viewParent0).addOnOffsetChangedListener(this.onOffsetChangedListener);
            ViewCompat.requestApplyInsets(this);
        }
    }

    @Override  // android.view.View
    protected void onConfigurationChanged(Configuration configuration0) {
        super.onConfigurationChanged(configuration0);
        this.collapsingTextHelper.maybeUpdateFontWeightAdjustment(configuration0);
    }

    @Override  // android.view.ViewGroup
    protected void onDetachedFromWindow() {
        ViewParent viewParent0 = this.getParent();
        OnOffsetChangedListener appBarLayout$OnOffsetChangedListener0 = this.onOffsetChangedListener;
        if(appBarLayout$OnOffsetChangedListener0 != null && viewParent0 instanceof AppBarLayout) {
            ((AppBarLayout)viewParent0).removeOnOffsetChangedListener(appBarLayout$OnOffsetChangedListener0);
        }
        super.onDetachedFromWindow();
    }

    @Override  // android.widget.FrameLayout
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        super.onLayout(z, v, v1, v2, v3);
        WindowInsetsCompat windowInsetsCompat0 = this.lastInsets;
        if(windowInsetsCompat0 != null) {
            int v5 = windowInsetsCompat0.getSystemWindowInsetTop();
            int v6 = this.getChildCount();
            for(int v7 = 0; v7 < v6; ++v7) {
                View view0 = this.getChildAt(v7);
                if(!ViewCompat.getFitsSystemWindows(view0) && view0.getTop() < v5) {
                    ViewCompat.offsetTopAndBottom(view0, v5);
                }
            }
        }
        int v8 = this.getChildCount();
        for(int v9 = 0; v9 < v8; ++v9) {
            CollapsingToolbarLayout.getViewOffsetHelper(this.getChildAt(v9)).onViewLayout();
        }
        this.updateTextBounds(v, v1, v2, v3, false);
        this.updateTitleFromToolbarIfNeeded();
        this.updateScrimVisibility();
        int v10 = this.getChildCount();
        for(int v4 = 0; v4 < v10; ++v4) {
            CollapsingToolbarLayout.getViewOffsetHelper(this.getChildAt(v4)).applyOffsets();
        }
    }

    @Override  // android.widget.FrameLayout
    protected void onMeasure(int v, int v1) {
        this.ensureToolbar();
        super.onMeasure(v, v1);
        int v2 = View.MeasureSpec.getMode(v1);
        int v3 = this.lastInsets == null ? 0 : this.lastInsets.getSystemWindowInsetTop();
        if((v2 == 0 || this.forceApplySystemWindowInsetTop) && v3 > 0) {
            this.topInsetApplied = v3;
            super.onMeasure(v, View.MeasureSpec.makeMeasureSpec(this.getMeasuredHeight() + v3, 0x40000000));
        }
        if(this.extraMultilineHeightEnabled && this.collapsingTextHelper.getMaxLines() > 1) {
            this.updateTitleFromToolbarIfNeeded();
            this.updateTextBounds(0, 0, this.getMeasuredWidth(), this.getMeasuredHeight(), true);
            int v4 = this.collapsingTextHelper.getExpandedLineCount();
            if(v4 > 1) {
                this.extraMultilineHeight = Math.round(this.collapsingTextHelper.getExpandedTextFullHeight()) * (v4 - 1);
                super.onMeasure(v, View.MeasureSpec.makeMeasureSpec(this.getMeasuredHeight() + this.extraMultilineHeight, 0x40000000));
            }
        }
        ViewGroup viewGroup0 = this.toolbar;
        if(viewGroup0 != null) {
            View view0 = this.toolbarDirectChild;
            if(view0 != null && view0 != this) {
                this.setMinimumHeight(CollapsingToolbarLayout.getHeightWithMargins(view0));
                return;
            }
            this.setMinimumHeight(CollapsingToolbarLayout.getHeightWithMargins(viewGroup0));
        }
    }

    @Override  // android.view.View
    protected void onSizeChanged(int v, int v1, int v2, int v3) {
        super.onSizeChanged(v, v1, v2, v3);
        Drawable drawable0 = this.contentScrim;
        if(drawable0 != null) {
            this.updateContentScrimBounds(drawable0, v, v1);
        }
    }

    // 检测为 Lambda 实现
    WindowInsetsCompat onWindowInsetChanged(WindowInsetsCompat windowInsetsCompat0) [...]

    public void setCollapsedTitleGravity(int v) {
        this.collapsingTextHelper.setCollapsedTextGravity(v);
    }

    public void setCollapsedTitleTextAppearance(int v) {
        this.collapsingTextHelper.setCollapsedTextAppearance(v);
    }

    public void setCollapsedTitleTextColor(int v) {
        this.setCollapsedTitleTextColor(ColorStateList.valueOf(v));
    }

    public void setCollapsedTitleTextColor(ColorStateList colorStateList0) {
        this.collapsingTextHelper.setCollapsedTextColor(colorStateList0);
    }

    public void setCollapsedTitleTextSize(float f) {
        this.collapsingTextHelper.setCollapsedTextSize(f);
    }

    public void setCollapsedTitleTypeface(Typeface typeface0) {
        this.collapsingTextHelper.setCollapsedTypeface(typeface0);
    }

    public void setContentScrim(Drawable drawable0) {
        Drawable drawable1 = null;
        Drawable drawable2 = this.contentScrim;
        if(drawable2 != drawable0) {
            if(drawable2 != null) {
                drawable2.setCallback(null);
            }
            if(drawable0 != null) {
                drawable1 = drawable0.mutate();
            }
            this.contentScrim = drawable1;
            if(drawable1 != null) {
                this.updateContentScrimBounds(drawable1, this.getWidth(), this.getHeight());
                this.contentScrim.setCallback(this);
                this.contentScrim.setAlpha(this.scrimAlpha);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setContentScrimColor(int v) {
        this.setContentScrim(new ColorDrawable(v));
    }

    public void setContentScrimResource(int v) {
        this.setContentScrim(ContextCompat.getDrawable(this.getContext(), v));
    }

    public void setExpandedTitleColor(int v) {
        this.setExpandedTitleTextColor(ColorStateList.valueOf(v));
    }

    public void setExpandedTitleGravity(int v) {
        this.collapsingTextHelper.setExpandedTextGravity(v);
    }

    public void setExpandedTitleMargin(int v, int v1, int v2, int v3) {
        this.expandedMarginStart = v;
        this.expandedMarginTop = v1;
        this.expandedMarginEnd = v2;
        this.expandedMarginBottom = v3;
        this.requestLayout();
    }

    public void setExpandedTitleMarginBottom(int v) {
        this.expandedMarginBottom = v;
        this.requestLayout();
    }

    public void setExpandedTitleMarginEnd(int v) {
        this.expandedMarginEnd = v;
        this.requestLayout();
    }

    public void setExpandedTitleMarginStart(int v) {
        this.expandedMarginStart = v;
        this.requestLayout();
    }

    public void setExpandedTitleMarginTop(int v) {
        this.expandedMarginTop = v;
        this.requestLayout();
    }

    public void setExpandedTitleTextAppearance(int v) {
        this.collapsingTextHelper.setExpandedTextAppearance(v);
    }

    public void setExpandedTitleTextColor(ColorStateList colorStateList0) {
        this.collapsingTextHelper.setExpandedTextColor(colorStateList0);
    }

    public void setExpandedTitleTextSize(float f) {
        this.collapsingTextHelper.setExpandedTextSize(f);
    }

    public void setExpandedTitleTypeface(Typeface typeface0) {
        this.collapsingTextHelper.setExpandedTypeface(typeface0);
    }

    public void setExtraMultilineHeightEnabled(boolean z) {
        this.extraMultilineHeightEnabled = z;
    }

    public void setForceApplySystemWindowInsetTop(boolean z) {
        this.forceApplySystemWindowInsetTop = z;
    }

    public void setHyphenationFrequency(int v) {
        this.collapsingTextHelper.setHyphenationFrequency(v);
    }

    public void setLineSpacingAdd(float f) {
        this.collapsingTextHelper.setLineSpacingAdd(f);
    }

    public void setLineSpacingMultiplier(float f) {
        this.collapsingTextHelper.setLineSpacingMultiplier(f);
    }

    public void setMaxLines(int v) {
        this.collapsingTextHelper.setMaxLines(v);
    }

    public void setRtlTextDirectionHeuristicsEnabled(boolean z) {
        this.collapsingTextHelper.setRtlTextDirectionHeuristicsEnabled(z);
    }

    void setScrimAlpha(int v) {
        if(v != this.scrimAlpha) {
            if(this.contentScrim != null) {
                ViewGroup viewGroup0 = this.toolbar;
                if(viewGroup0 != null) {
                    ViewCompat.postInvalidateOnAnimation(viewGroup0);
                }
            }
            this.scrimAlpha = v;
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setScrimAnimationDuration(long v) {
        this.scrimAnimationDuration = v;
    }

    public void setScrimVisibleHeightTrigger(int v) {
        if(this.scrimVisibleHeightTrigger != v) {
            this.scrimVisibleHeightTrigger = v;
            this.updateScrimVisibility();
        }
    }

    public void setScrimsShown(boolean z) {
        this.setScrimsShown(z, ViewCompat.isLaidOut(this) && !this.isInEditMode());
    }

    public void setScrimsShown(boolean z, boolean z1) {
        int v = 0xFF;
        if(this.scrimsAreShown != z) {
            if(z1) {
                if(!z) {
                    v = 0;
                }
                this.animateScrim(v);
            }
            else {
                if(!z) {
                    v = 0;
                }
                this.setScrimAlpha(v);
            }
            this.scrimsAreShown = z;
        }
    }

    public void setStaticLayoutBuilderConfigurer(StaticLayoutBuilderConfigurer collapsingToolbarLayout$StaticLayoutBuilderConfigurer0) {
        this.collapsingTextHelper.setStaticLayoutBuilderConfigurer(collapsingToolbarLayout$StaticLayoutBuilderConfigurer0);
    }

    public void setStatusBarScrim(Drawable drawable0) {
        Drawable drawable1 = null;
        Drawable drawable2 = this.statusBarScrim;
        if(drawable2 != drawable0) {
            if(drawable2 != null) {
                drawable2.setCallback(null);
            }
            if(drawable0 != null) {
                drawable1 = drawable0.mutate();
            }
            this.statusBarScrim = drawable1;
            if(drawable1 != null) {
                if(drawable1.isStateful()) {
                    this.statusBarScrim.setState(this.getDrawableState());
                }
                DrawableCompat.setLayoutDirection(this.statusBarScrim, ViewCompat.getLayoutDirection(this));
                this.statusBarScrim.setVisible(this.getVisibility() == 0, false);
                this.statusBarScrim.setCallback(this);
                this.statusBarScrim.setAlpha(this.scrimAlpha);
            }
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }

    public void setStatusBarScrimColor(int v) {
        this.setStatusBarScrim(new ColorDrawable(v));
    }

    public void setStatusBarScrimResource(int v) {
        this.setStatusBarScrim(ContextCompat.getDrawable(this.getContext(), v));
    }

    public void setTitle(CharSequence charSequence0) {
        this.collapsingTextHelper.setText(charSequence0);
        this.updateContentDescriptionFromTitle();
    }

    public void setTitleCollapseMode(int v) {
        this.titleCollapseMode = v;
        boolean z = this.isTitleCollapseFadeMode();
        this.collapsingTextHelper.setFadeModeEnabled(z);
        ViewParent viewParent0 = this.getParent();
        if(viewParent0 instanceof AppBarLayout) {
            this.disableLiftOnScrollIfNeeded(((AppBarLayout)viewParent0));
        }
        if(z && this.contentScrim == null) {
            this.setContentScrimColor(this.getDefaultContentScrimColorForTitleCollapseFadeMode());
        }
    }

    public void setTitleEllipsize(TextUtils.TruncateAt textUtils$TruncateAt0) {
        this.collapsingTextHelper.setTitleTextEllipsize(textUtils$TruncateAt0);
    }

    public void setTitleEnabled(boolean z) {
        if(z != this.collapsingTitleEnabled) {
            this.collapsingTitleEnabled = z;
            this.updateContentDescriptionFromTitle();
            this.updateDummyView();
            this.requestLayout();
        }
    }

    public void setTitlePositionInterpolator(TimeInterpolator timeInterpolator0) {
        this.collapsingTextHelper.setPositionInterpolator(timeInterpolator0);
    }

    @Override  // android.view.View
    public void setVisibility(int v) {
        super.setVisibility(v);
        if(this.statusBarScrim != null && this.statusBarScrim.isVisible() != (v == 0)) {
            this.statusBarScrim.setVisible(v == 0, false);
        }
        if(this.contentScrim != null && this.contentScrim.isVisible() != (v == 0)) {
            this.contentScrim.setVisible(v == 0, false);
        }
    }

    private void updateCollapsedBounds(boolean z) {
        int v4;
        int v3;
        int v2;
        int v1;
        View view0 = this.toolbarDirectChild;
        if(view0 == null) {
            view0 = this.toolbar;
        }
        int v = this.getMaxOffsetForPinChild(view0);
        DescendantOffsetUtils.getDescendantRect(this, this.dummyView, this.tmpRect);
        ViewGroup viewGroup0 = this.toolbar;
        if(viewGroup0 instanceof Toolbar) {
            v1 = ((Toolbar)viewGroup0).getTitleMarginStart();
            v2 = ((Toolbar)viewGroup0).getTitleMarginEnd();
            v3 = ((Toolbar)viewGroup0).getTitleMarginTop();
            v4 = ((Toolbar)viewGroup0).getTitleMarginBottom();
        }
        else if(Build.VERSION.SDK_INT >= 24) {
            ViewGroup viewGroup1 = this.toolbar;
            if(viewGroup1 instanceof android.widget.Toolbar) {
                v1 = ((android.widget.Toolbar)viewGroup1).getTitleMarginStart();
                v2 = ((android.widget.Toolbar)viewGroup1).getTitleMarginEnd();
                v3 = ((android.widget.Toolbar)viewGroup1).getTitleMarginTop();
                v4 = ((android.widget.Toolbar)viewGroup1).getTitleMarginBottom();
                goto label_24;
            }
            goto label_20;
        }
        else {
        label_20:
            v1 = 0;
            v4 = 0;
            v2 = 0;
            v3 = 0;
        }
    label_24:
        CollapsingTextHelper collapsingTextHelper0 = this.collapsingTextHelper;
        int v5 = this.tmpRect.left;
        int v6 = z ? v2 : v1;
        int v7 = this.tmpRect.top + v + v3;
        int v8 = this.tmpRect.right;
        if(!z) {
            v1 = v2;
        }
        collapsingTextHelper0.setCollapsedBounds(v5 + v6, v7, v8 - v1, this.tmpRect.bottom + v - v4);
    }

    private void updateContentDescriptionFromTitle() {
        this.setContentDescription(this.getTitle());
    }

    private void updateContentScrimBounds(Drawable drawable0, int v, int v1) {
        this.updateContentScrimBounds(drawable0, this.toolbar, v, v1);
    }

    private void updateContentScrimBounds(Drawable drawable0, View view0, int v, int v1) {
        if(this.isTitleCollapseFadeMode() && view0 != null && this.collapsingTitleEnabled) {
            v1 = view0.getBottom();
        }
        drawable0.setBounds(0, 0, v, v1);
    }

    private void updateDummyView() {
        if(!this.collapsingTitleEnabled) {
            View view0 = this.dummyView;
            if(view0 != null) {
                ViewParent viewParent0 = view0.getParent();
                if(viewParent0 instanceof ViewGroup) {
                    ((ViewGroup)viewParent0).removeView(this.dummyView);
                }
            }
        }
        if(this.collapsingTitleEnabled && this.toolbar != null) {
            if(this.dummyView == null) {
                this.dummyView = new View(this.getContext());
            }
            if(this.dummyView.getParent() == null) {
                this.toolbar.addView(this.dummyView, -1, -1);
            }
        }
    }

    final void updateScrimVisibility() {
        if(this.contentScrim == null && this.statusBarScrim == null) {
            return;
        }
        this.setScrimsShown(this.getHeight() + this.currentOffset < this.getScrimVisibleHeightTrigger());
    }

    private void updateTextBounds(int v, int v1, int v2, int v3, boolean z) {
        if(this.collapsingTitleEnabled) {
            boolean z1 = false;
            View view0 = this.dummyView;
            if(view0 != null) {
                boolean z2 = ViewCompat.isAttachedToWindow(view0) && this.dummyView.getVisibility() == 0;
                this.drawCollapsingTitle = z2;
                if(z2 || z) {
                    if(ViewCompat.getLayoutDirection(this) == 1) {
                        z1 = true;
                    }
                    this.updateCollapsedBounds(z1);
                    this.collapsingTextHelper.setExpandedBounds((z1 ? this.expandedMarginEnd : this.expandedMarginStart), this.tmpRect.top + this.expandedMarginTop, v2 - v - (z1 ? this.expandedMarginStart : this.expandedMarginEnd), v3 - v1 - this.expandedMarginBottom);
                    this.collapsingTextHelper.recalculate(z);
                }
            }
        }
    }

    private void updateTitleFromToolbarIfNeeded() {
        if(this.toolbar != null && this.collapsingTitleEnabled && TextUtils.isEmpty(this.collapsingTextHelper.getText())) {
            this.setTitle(CollapsingToolbarLayout.getToolbarTitle(this.toolbar));
        }
    }

    @Override  // android.view.View
    protected boolean verifyDrawable(Drawable drawable0) {
        return super.verifyDrawable(drawable0) || drawable0 == this.contentScrim || drawable0 == this.statusBarScrim;
    }
}

