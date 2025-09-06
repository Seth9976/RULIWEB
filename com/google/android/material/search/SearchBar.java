package com.google.android.material.search;

import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityManagerCompat.TouchExplorationStateChangeListener;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import androidx.core.widget.TextViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.id;
import com.google.android.material.R.layout;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.appbar.AppBarLayout.LayoutParams;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ToolbarUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

public class SearchBar extends Toolbar {
    public static abstract class OnLoadAnimationCallback {
        public void onAnimationEnd() {
        }

        public void onAnimationStart() {
        }
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR;
        String text;

        static {
            SavedState.CREATOR = new Parcelable.ClassLoaderCreator() {
                public SavedState createFromParcel(Parcel parcel0) {
                    return new SavedState(parcel0);
                }

                public SavedState createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                    return new SavedState(parcel0, classLoader0);
                }

                @Override  // android.os.Parcelable$Creator
                public Object createFromParcel(Parcel parcel0) {
                    return this.createFromParcel(parcel0);
                }

                @Override  // android.os.Parcelable$ClassLoaderCreator
                public Object createFromParcel(Parcel parcel0, ClassLoader classLoader0) {
                    return this.createFromParcel(parcel0, classLoader0);
                }

                public SavedState[] newArray(int v) {
                    return new SavedState[v];
                }

                @Override  // android.os.Parcelable$Creator
                public Object[] newArray(int v) {
                    return this.newArray(v);
                }
            };
        }

        public SavedState(Parcel parcel0) {
            this(parcel0, null);
        }

        public SavedState(Parcel parcel0, ClassLoader classLoader0) {
            super(parcel0, classLoader0);
            this.text = parcel0.readString();
        }

        public SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        @Override  // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeString(this.text);
        }
    }

    public static class ScrollingViewBehavior extends com.google.android.material.appbar.AppBarLayout.ScrollingViewBehavior {
        private boolean initialized;

        public ScrollingViewBehavior() {
            this.initialized = false;
        }

        public ScrollingViewBehavior(Context context0, AttributeSet attributeSet0) {
            super(context0, attributeSet0);
            this.initialized = false;
        }

        @Override  // com.google.android.material.appbar.AppBarLayout$ScrollingViewBehavior
        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout0, View view0, View view1) {
            boolean z = super.onDependentViewChanged(coordinatorLayout0, view0, view1);
            if(!this.initialized && view1 instanceof AppBarLayout) {
                this.initialized = true;
                this.setAppBarLayoutTransparent(((AppBarLayout)view1));
            }
            return z;
        }

        private void setAppBarLayoutTransparent(AppBarLayout appBarLayout0) {
            appBarLayout0.setBackgroundColor(0);
            if(Build.VERSION.SDK_INT == 21) {
                appBarLayout0.setOutlineProvider(null);
                return;
            }
            appBarLayout0.setTargetElevation(0.0f);
        }

        @Override  // com.google.android.material.appbar.HeaderScrollingViewBehavior
        protected boolean shouldHeaderOverlapScrollingChild() {
            return true;
        }
    }

    private static final int DEFAULT_SCROLL_FLAGS = 53;
    private static final int DEF_STYLE_RES = 0;
    private static final String NAMESPACE_APP = "http://schemas.android.com/apk/res-auto";
    private final AccessibilityManager accessibilityManager;
    private MaterialShapeDrawable backgroundShape;
    private View centerView;
    private final boolean defaultMarginsEnabled;
    private final Drawable defaultNavigationIcon;
    private boolean defaultScrollFlagsEnabled;
    private final boolean forceDefaultNavigationOnClickListener;
    private final boolean layoutInflated;
    private int menuResId;
    private Integer navigationIconTint;
    private Drawable originalNavigationIconBackground;
    private final SearchBarAnimationHelper searchBarAnimationHelper;
    private final TextView textView;
    private final boolean tintNavigationIcon;
    private final TouchExplorationStateChangeListener touchExplorationStateChangeListener;

    static {
        SearchBar.DEF_STYLE_RES = style.Widget_Material3_SearchBar;
    }

    public SearchBar(Context context0) {
        this(context0, null);
    }

    public SearchBar(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.materialSearchBarStyle);
    }

    public SearchBar(Context context0, AttributeSet attributeSet0, int v) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, SearchBar.DEF_STYLE_RES), attributeSet0, v);
        this.menuResId = -1;
        this.touchExplorationStateChangeListener = (boolean z) -> this.setFocusableInTouchMode(z);
        Context context1 = this.getContext();
        this.validateAttributes(attributeSet0);
        this.defaultNavigationIcon = AppCompatResources.getDrawable(context1, 0x7F0800C2);  // drawable:ic_search_black_24
        this.searchBarAnimationHelper = new SearchBarAnimationHelper();
        TypedArray typedArray0 = ThemeEnforcement.obtainStyledAttributes(context1, attributeSet0, styleable.SearchBar, v, SearchBar.DEF_STYLE_RES, new int[0]);
        ShapeAppearanceModel shapeAppearanceModel0 = ShapeAppearanceModel.builder(context1, attributeSet0, v, SearchBar.DEF_STYLE_RES).build();
        int v1 = typedArray0.getColor(styleable.SearchBar_backgroundTint, 0);
        float f = typedArray0.getDimension(styleable.SearchBar_elevation, 0.0f);
        this.defaultMarginsEnabled = typedArray0.getBoolean(styleable.SearchBar_defaultMarginsEnabled, true);
        this.defaultScrollFlagsEnabled = typedArray0.getBoolean(styleable.SearchBar_defaultScrollFlagsEnabled, true);
        boolean z = typedArray0.getBoolean(styleable.SearchBar_hideNavigationIcon, false);
        this.forceDefaultNavigationOnClickListener = typedArray0.getBoolean(styleable.SearchBar_forceDefaultNavigationOnClickListener, false);
        this.tintNavigationIcon = typedArray0.getBoolean(styleable.SearchBar_tintNavigationIcon, true);
        if(typedArray0.hasValue(styleable.SearchBar_navigationIconTint)) {
            this.navigationIconTint = typedArray0.getColor(styleable.SearchBar_navigationIconTint, -1);
        }
        int v2 = typedArray0.getResourceId(styleable.SearchBar_android_textAppearance, -1);
        String s = typedArray0.getString(styleable.SearchBar_android_text);
        String s1 = typedArray0.getString(styleable.SearchBar_android_hint);
        float f1 = typedArray0.getDimension(styleable.SearchBar_strokeWidth, -1.0f);
        int v3 = typedArray0.getColor(styleable.SearchBar_strokeColor, 0);
        typedArray0.recycle();
        if(!z) {
            this.initNavigationIcon();
        }
        this.setClickable(true);
        this.setFocusable(true);
        LayoutInflater.from(context1).inflate(layout.mtrl_search_bar, this);
        this.layoutInflated = true;
        this.textView = (TextView)this.findViewById(id.open_search_bar_text_view);
        ViewCompat.setElevation(this, f);
        this.initTextView(v2, s, s1);
        this.initBackground(shapeAppearanceModel0, v1, f, f1, v3);
        this.accessibilityManager = (AccessibilityManager)this.getContext().getSystemService("accessibility");
        this.setupTouchExplorationStateChangeListener();
    }

    public void addCollapseAnimationListener(AnimatorListenerAdapter animatorListenerAdapter0) {
        this.searchBarAnimationHelper.addCollapseAnimationListener(animatorListenerAdapter0);
    }

    public void addExpandAnimationListener(AnimatorListenerAdapter animatorListenerAdapter0) {
        this.searchBarAnimationHelper.addExpandAnimationListener(animatorListenerAdapter0);
    }

    public void addOnLoadAnimationCallback(OnLoadAnimationCallback searchBar$OnLoadAnimationCallback0) {
        this.searchBarAnimationHelper.addOnLoadAnimationCallback(searchBar$OnLoadAnimationCallback0);
    }

    @Override  // android.view.ViewGroup
    public void addView(View view0, int v, ViewGroup.LayoutParams viewGroup$LayoutParams0) {
        if(this.layoutInflated && this.centerView == null && !(view0 instanceof ActionMenuView)) {
            this.centerView = view0;
            view0.setAlpha(0.0f);
        }
        super.addView(view0, v, viewGroup$LayoutParams0);
    }

    public void clearText() {
        this.textView.setText("");
    }

    public boolean collapse(View view0) {
        return this.collapse(view0, null);
    }

    public boolean collapse(View view0, AppBarLayout appBarLayout0) {
        return this.collapse(view0, appBarLayout0, false);
    }

    public boolean collapse(View view0, AppBarLayout appBarLayout0, boolean z) {
        if(view0.getVisibility() == 0 && !this.isCollapsing() || this.isExpanding()) {
            this.searchBarAnimationHelper.startCollapseAnimation(this, view0, appBarLayout0, z);
            return true;
        }
        return false;
    }

    private int defaultIfZero(int v, int v1) {
        return v == 0 ? v1 : v;
    }

    public boolean expand(View view0) {
        return this.expand(view0, null);
    }

    public boolean expand(View view0, AppBarLayout appBarLayout0) {
        return this.expand(view0, appBarLayout0, false);
    }

    public boolean expand(View view0, AppBarLayout appBarLayout0, boolean z) {
        if(view0.getVisibility() != 0 && !this.isExpanding() || this.isCollapsing()) {
            this.searchBarAnimationHelper.startExpandAnimation(this, view0, appBarLayout0, z);
            return true;
        }
        return false;
    }

    public View getCenterView() {
        return this.centerView;
    }

    private ColorStateList getCompatBackgroundColorStateList(int v, int v1) {
        int v2 = MaterialColors.layer(v, v1);
        return new ColorStateList(new int[][]{new int[]{0x10100A7}, new int[]{0x101009C}, new int[0]}, new int[]{v2, v2, v});
    }

    float getCompatElevation() {
        MaterialShapeDrawable materialShapeDrawable0 = this.backgroundShape;
        return materialShapeDrawable0 == null ? ViewCompat.getElevation(this) : materialShapeDrawable0.getElevation();
    }

    public float getCornerSize() {
        return this.backgroundShape.getTopLeftCornerResolvedSize();
    }

    protected int getDefaultMarginVerticalResource() [...] // 潜在的解密器

    protected int getDefaultNavigationIconResource() [...] // 潜在的解密器

    public CharSequence getHint() {
        return this.textView.getHint();
    }

    int getMenuResId() {
        return this.menuResId;
    }

    public int getStrokeColor() {
        return this.backgroundShape.getStrokeColor().getDefaultColor();
    }

    public float getStrokeWidth() {
        return this.backgroundShape.getStrokeWidth();
    }

    public CharSequence getText() {
        return this.textView.getText();
    }

    public TextView getTextView() {
        return this.textView;
    }

    @Override  // androidx.appcompat.widget.Toolbar
    public void inflateMenu(int v) {
        Menu menu0 = this.getMenu();
        if(menu0 instanceof MenuBuilder) {
            ((MenuBuilder)menu0).stopDispatchingItemsChanged();
        }
        super.inflateMenu(v);
        this.menuResId = v;
        if(menu0 instanceof MenuBuilder) {
            ((MenuBuilder)menu0).startDispatchingItemsChanged();
        }
    }

    private void initBackground(ShapeAppearanceModel shapeAppearanceModel0, int v, float f, float f1, int v1) {
        MaterialShapeDrawable materialShapeDrawable0 = new MaterialShapeDrawable(shapeAppearanceModel0);
        this.backgroundShape = materialShapeDrawable0;
        materialShapeDrawable0.initializeElevationOverlay(this.getContext());
        this.backgroundShape.setElevation(f);
        if(f1 >= 0.0f) {
            this.backgroundShape.setStroke(f1, v1);
        }
        int v2 = MaterialColors.getColor(this, attr.colorControlHighlight);
        this.backgroundShape.setFillColor(ColorStateList.valueOf(v));
        ViewCompat.setBackground(this, new RippleDrawable(ColorStateList.valueOf(v2), this.backgroundShape, this.backgroundShape));
    }

    private void initNavigationIcon() {
        this.setNavigationIcon((this.getNavigationIcon() == null ? this.defaultNavigationIcon : this.getNavigationIcon()));
        this.setNavigationIconDecorative(true);
    }

    private void initTextView(int v, String s, String s1) {
        if(v != -1) {
            TextViewCompat.setTextAppearance(this.textView, v);
        }
        this.setText(s);
        this.setHint(s1);
        if(this.getNavigationIcon() == null) {
            MarginLayoutParamsCompat.setMarginStart(((ViewGroup.MarginLayoutParams)this.textView.getLayoutParams()), this.getResources().getDimensionPixelSize(dimen.m3_searchbar_text_margin_start_no_navigation_icon));
        }
    }

    public boolean isCollapsing() {
        return this.searchBarAnimationHelper.isCollapsing();
    }

    public boolean isDefaultScrollFlagsEnabled() {
        return this.defaultScrollFlagsEnabled;
    }

    public boolean isExpanding() {
        return this.searchBarAnimationHelper.isExpanding();
    }

    public boolean isOnLoadAnimationFadeInEnabled() {
        return this.searchBarAnimationHelper.isOnLoadAnimationFadeInEnabled();
    }

    // 检测为 Lambda 实现
    void lambda$new$0$com-google-android-material-search-SearchBar(boolean z) [...]

    // 检测为 Lambda 实现
    void lambda$startOnLoadAnimation$1$com-google-android-material-search-SearchBar() [...]

    private void layoutCenterView() {
        View view0 = this.centerView;
        if(view0 == null) {
            return;
        }
        int v = view0.getMeasuredWidth();
        int v1 = this.getMeasuredWidth() / 2 - v / 2;
        int v2 = this.centerView.getMeasuredHeight();
        int v3 = this.getMeasuredHeight() / 2 - v2 / 2;
        this.layoutChild(this.centerView, v1, v3, v1 + v, v3 + v2);
    }

    private void layoutChild(View view0, int v, int v1, int v2, int v3) {
        if(ViewCompat.getLayoutDirection(this) == 1) {
            view0.layout(this.getMeasuredWidth() - v2, v1, this.getMeasuredWidth() - v, v3);
            return;
        }
        view0.layout(v, v1, v2, v3);
    }

    private Drawable maybeTintNavigationIcon(Drawable drawable0) {
        if(this.tintNavigationIcon && drawable0 != null) {
            Integer integer0 = this.navigationIconTint;
            int v = integer0 == null ? MaterialColors.getColor(this, (drawable0 == this.defaultNavigationIcon ? attr.colorOnSurfaceVariant : attr.colorOnSurface)) : ((int)integer0);
            drawable0 = DrawableCompat.wrap(drawable0.mutate());
            DrawableCompat.setTint(drawable0, v);
        }
        return drawable0;
    }

    private void measureCenterView(int v, int v1) {
        View view0 = this.centerView;
        if(view0 != null) {
            view0.measure(v, v1);
        }
    }

    @Override  // androidx.appcompat.widget.Toolbar
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this, this.backgroundShape);
        this.setDefaultMargins();
        this.setOrClearDefaultScrollFlags();
    }

    @Override  // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo0) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo0);
        accessibilityNodeInfo0.setClassName("android.widget.EditText");
        accessibilityNodeInfo0.setEditable(this.isEnabled());
        CharSequence charSequence0 = this.getText();
        boolean z = TextUtils.isEmpty(charSequence0);
        if(Build.VERSION.SDK_INT >= 26) {
            accessibilityNodeInfo0.setHintText(this.getHint());
            accessibilityNodeInfo0.setShowingHintText(z);
        }
        if(z) {
            charSequence0 = this.getHint();
        }
        accessibilityNodeInfo0.setText(charSequence0);
    }

    @Override  // androidx.appcompat.widget.Toolbar
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        super.onLayout(z, v, v1, v2, v3);
        this.layoutCenterView();
        this.setHandwritingBoundsInsets();
    }

    @Override  // androidx.appcompat.widget.Toolbar
    protected void onMeasure(int v, int v1) {
        super.onMeasure(v, v1);
        this.measureCenterView(v, v1);
    }

    @Override  // androidx.appcompat.widget.Toolbar
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(!(parcelable0 instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable0);
            return;
        }
        super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
        this.setText(((SavedState)parcelable0).text);
    }

    @Override  // androidx.appcompat.widget.Toolbar
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState(super.onSaveInstanceState());
        CharSequence charSequence0 = this.getText();
        parcelable0.text = charSequence0 == null ? null : charSequence0.toString();
        return parcelable0;
    }

    public boolean removeCollapseAnimationListener(AnimatorListenerAdapter animatorListenerAdapter0) {
        return this.searchBarAnimationHelper.removeCollapseAnimationListener(animatorListenerAdapter0);
    }

    public boolean removeExpandAnimationListener(AnimatorListenerAdapter animatorListenerAdapter0) {
        return this.searchBarAnimationHelper.removeExpandAnimationListener(animatorListenerAdapter0);
    }

    public boolean removeOnLoadAnimationCallback(OnLoadAnimationCallback searchBar$OnLoadAnimationCallback0) {
        return this.searchBarAnimationHelper.removeOnLoadAnimationCallback(searchBar$OnLoadAnimationCallback0);
    }

    public void setCenterView(View view0) {
        View view1 = this.centerView;
        if(view1 != null) {
            this.removeView(view1);
            this.centerView = null;
        }
        if(view0 != null) {
            this.addView(view0);
        }
    }

    private void setDefaultMargins() {
        if(this.defaultMarginsEnabled && this.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            Resources resources0 = this.getResources();
            int v = resources0.getDimensionPixelSize(dimen.m3_searchbar_margin_horizontal);
            int v1 = resources0.getDimensionPixelSize(0x7F0701E1);  // dimen:m3_searchbar_margin_vertical
            ViewGroup.MarginLayoutParams viewGroup$MarginLayoutParams0 = (ViewGroup.MarginLayoutParams)this.getLayoutParams();
            viewGroup$MarginLayoutParams0.leftMargin = this.defaultIfZero(viewGroup$MarginLayoutParams0.leftMargin, v);
            viewGroup$MarginLayoutParams0.topMargin = this.defaultIfZero(viewGroup$MarginLayoutParams0.topMargin, v1);
            viewGroup$MarginLayoutParams0.rightMargin = this.defaultIfZero(viewGroup$MarginLayoutParams0.rightMargin, v);
            viewGroup$MarginLayoutParams0.bottomMargin = this.defaultIfZero(viewGroup$MarginLayoutParams0.bottomMargin, v1);
        }
    }

    public void setDefaultScrollFlagsEnabled(boolean z) {
        this.defaultScrollFlagsEnabled = z;
        this.setOrClearDefaultScrollFlags();
    }

    @Override  // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        MaterialShapeDrawable materialShapeDrawable0 = this.backgroundShape;
        if(materialShapeDrawable0 != null) {
            materialShapeDrawable0.setElevation(f);
        }
    }

    private void setHandwritingBoundsInsets() {
        int v1;
        if(Build.VERSION.SDK_INT < 34) {
            return;
        }
        boolean z = true;
        int v = 0;
        if(this.getLayoutDirection() != 1) {
            z = false;
        }
        ImageButton imageButton0 = ToolbarUtils.getNavigationIconButton(this);
        if(imageButton0 == null || !imageButton0.isClickable()) {
            v1 = 0;
        }
        else if(z) {
            v1 = this.getWidth() - imageButton0.getLeft();
        }
        else {
            v1 = imageButton0.getRight();
        }
        ActionMenuView actionMenuView0 = ToolbarUtils.getActionMenuView(this);
        if(actionMenuView0 != null) {
            v = z ? actionMenuView0.getRight() : this.getWidth() - actionMenuView0.getLeft();
        }
        int v2 = z ? v : v1;
        if(!z) {
            v1 = v;
        }
        LinkFollowing..ExternalSyntheticApiModelOutline0.m(this, ((float)(-v2)), 0.0f, ((float)(-v1)), 0.0f);
    }

    public void setHint(int v) {
        this.textView.setHint(v);
    }

    public void setHint(CharSequence charSequence0) {
        this.textView.setHint(charSequence0);
    }

    @Override  // androidx.appcompat.widget.Toolbar
    public void setNavigationIcon(Drawable drawable0) {
        super.setNavigationIcon(this.maybeTintNavigationIcon(drawable0));
    }

    private void setNavigationIconDecorative(boolean z) {
        ImageButton imageButton0 = ToolbarUtils.getNavigationIconButton(this);
        if(imageButton0 == null) {
            return;
        }
        imageButton0.setClickable(!z);
        imageButton0.setFocusable(!z);
        Drawable drawable0 = imageButton0.getBackground();
        if(drawable0 != null) {
            this.originalNavigationIconBackground = drawable0;
        }
        imageButton0.setBackgroundDrawable((z ? null : this.originalNavigationIconBackground));
        this.setHandwritingBoundsInsets();
    }

    @Override  // androidx.appcompat.widget.Toolbar
    public void setNavigationOnClickListener(View.OnClickListener view$OnClickListener0) {
        if(this.forceDefaultNavigationOnClickListener) {
            return;
        }
        super.setNavigationOnClickListener(view$OnClickListener0);
        this.setNavigationIconDecorative(view$OnClickListener0 == null);
    }

    public void setOnLoadAnimationFadeInEnabled(boolean z) {
        this.searchBarAnimationHelper.setOnLoadAnimationFadeInEnabled(z);
    }

    private void setOrClearDefaultScrollFlags() {
        if(this.getLayoutParams() instanceof LayoutParams) {
            LayoutParams appBarLayout$LayoutParams0 = (LayoutParams)this.getLayoutParams();
            if(this.defaultScrollFlagsEnabled) {
                if(appBarLayout$LayoutParams0.getScrollFlags() == 0) {
                    appBarLayout$LayoutParams0.setScrollFlags(53);
                }
            }
            else if(appBarLayout$LayoutParams0.getScrollFlags() == 53) {
                appBarLayout$LayoutParams0.setScrollFlags(0);
            }
        }
    }

    public void setStrokeColor(int v) {
        if(this.getStrokeColor() != v) {
            this.backgroundShape.setStrokeColor(ColorStateList.valueOf(v));
        }
    }

    public void setStrokeWidth(float f) {
        if(this.getStrokeWidth() != f) {
            this.backgroundShape.setStrokeWidth(f);
        }
    }

    @Override  // androidx.appcompat.widget.Toolbar
    public void setSubtitle(CharSequence charSequence0) {
    }

    public void setText(int v) {
        this.textView.setText(v);
    }

    public void setText(CharSequence charSequence0) {
        this.textView.setText(charSequence0);
    }

    @Override  // androidx.appcompat.widget.Toolbar
    public void setTitle(CharSequence charSequence0) {
    }

    private void setupTouchExplorationStateChangeListener() {
        AccessibilityManager accessibilityManager0 = this.accessibilityManager;
        if(accessibilityManager0 != null) {
            if(accessibilityManager0.isEnabled() && this.accessibilityManager.isTouchExplorationEnabled()) {
                this.setFocusableInTouchMode(true);
            }
            this.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override  // android.view.View$OnAttachStateChangeListener
                public void onViewAttachedToWindow(View view0) {
                    AccessibilityManagerCompat.addTouchExplorationStateChangeListener(SearchBar.this.accessibilityManager, SearchBar.this.touchExplorationStateChangeListener);
                }

                @Override  // android.view.View$OnAttachStateChangeListener
                public void onViewDetachedFromWindow(View view0) {
                    AccessibilityManagerCompat.removeTouchExplorationStateChangeListener(SearchBar.this.accessibilityManager, SearchBar.this.touchExplorationStateChangeListener);
                }
            });
        }
    }

    public void startOnLoadAnimation() {
        this.post(() -> this.searchBarAnimationHelper.startOnLoadAnimation(this));
    }

    public void stopOnLoadAnimation() {
        this.searchBarAnimationHelper.stopOnLoadAnimation(this);
    }

    private void validateAttributes(AttributeSet attributeSet0) {
        if(attributeSet0 != null) {
            if(attributeSet0.getAttributeValue("http://schemas.android.com/apk/res-auto", "title") != null) {
                throw new UnsupportedOperationException("SearchBar does not support title. Use hint or text instead.");
            }
            if(attributeSet0.getAttributeValue("http://schemas.android.com/apk/res-auto", "subtitle") != null) {
                throw new UnsupportedOperationException("SearchBar does not support subtitle. Use hint or text instead.");
            }
        }
    }
}

