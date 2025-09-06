package com.google.android.material.navigation;

import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import androidx.activity.BackEventCompat;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder.Callback;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.customview.view.AbsSavedState;
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener;
import androidx.drawerlayout.widget.DrawerLayout.LayoutParams;
import androidx.drawerlayout.widget.DrawerLayout.SimpleDrawerListener;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.R.attr;
import com.google.android.material.R.dimen;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ContextUtils;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.internal.NavigationMenuPresenter;
import com.google.android.material.internal.ScrimInsetsFrameLayout;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.WindowUtils;
import com.google.android.material.motion.MaterialBackHandler;
import com.google.android.material.motion.MaterialBackOrchestrator;
import com.google.android.material.motion.MaterialSideContainerBackHelper;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel.Builder;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeableDelegate;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.Objects;

public class NavigationView extends ScrimInsetsFrameLayout implements MaterialBackHandler {
    public interface OnNavigationItemSelectedListener {
        boolean onNavigationItemSelected(MenuItem arg1);
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR;
        public Bundle menuState;

        static {
            SavedState.CREATOR = new Parcelable.ClassLoaderCreator() {
                public SavedState createFromParcel(Parcel parcel0) {
                    return new SavedState(parcel0, null);
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

        public SavedState(Parcel parcel0, ClassLoader classLoader0) {
            super(parcel0, classLoader0);
            this.menuState = parcel0.readBundle(classLoader0);
        }

        public SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        @Override  // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeBundle(this.menuState);
        }
    }

    private static final int[] CHECKED_STATE_SET = null;
    private static final int DEF_STYLE_RES = 0;
    private static final int[] DISABLED_STATE_SET = null;
    private static final int PRESENTER_NAVIGATION_VIEW_ID = 1;
    private final DrawerListener backDrawerListener;
    private final MaterialBackOrchestrator backOrchestrator;
    private boolean bottomInsetScrimEnabled;
    private int drawerLayoutCornerSize;
    private final boolean drawerLayoutCornerSizeBackAnimationEnabled;
    private final int drawerLayoutCornerSizeBackAnimationMax;
    OnNavigationItemSelectedListener listener;
    private final int maxWidth;
    private final NavigationMenu menu;
    private MenuInflater menuInflater;
    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
    private final NavigationMenuPresenter presenter;
    private final ShapeableDelegate shapeableDelegate;
    private final MaterialSideContainerBackHelper sideContainerBackHelper;
    private final int[] tmpLocation;
    private boolean topInsetScrimEnabled;

    static {
        NavigationView.CHECKED_STATE_SET = new int[]{0x10100A0};
        NavigationView.DISABLED_STATE_SET = new int[]{0xFEFEFF62};
        NavigationView.DEF_STYLE_RES = style.Widget_Design_NavigationView;
    }

    public NavigationView(Context context0) {
        this(context0, null);
    }

    public NavigationView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.navigationViewStyle);
    }

    public NavigationView(Context context0, AttributeSet attributeSet0, int v) {
        int v1 = NavigationView.DEF_STYLE_RES;
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, v1), attributeSet0, v);
        NavigationMenuPresenter navigationMenuPresenter0 = new NavigationMenuPresenter();
        this.presenter = navigationMenuPresenter0;
        this.tmpLocation = new int[2];
        this.topInsetScrimEnabled = true;
        this.bottomInsetScrimEnabled = true;
        this.drawerLayoutCornerSize = 0;
        this.shapeableDelegate = ShapeableDelegate.create(this);
        this.sideContainerBackHelper = new MaterialSideContainerBackHelper(this);
        this.backOrchestrator = new MaterialBackOrchestrator(this);
        this.backDrawerListener = new SimpleDrawerListener() {
            @Override  // androidx.drawerlayout.widget.DrawerLayout$SimpleDrawerListener
            public void onDrawerClosed(View view0) {
                NavigationView navigationView0 = NavigationView.this;
                if(view0 == navigationView0) {
                    navigationView0.backOrchestrator.stopListeningForBackCallbacks();
                    NavigationView.this.maybeClearCornerSizeAnimationForDrawerLayout();
                }
            }

            @Override  // androidx.drawerlayout.widget.DrawerLayout$SimpleDrawerListener
            public void onDrawerOpened(View view0) {
                NavigationView navigationView0 = NavigationView.this;
                if(view0 == navigationView0) {
                    MaterialBackOrchestrator materialBackOrchestrator0 = navigationView0.backOrchestrator;
                    Objects.requireNonNull(materialBackOrchestrator0);
                    view0.post(new NavigationView.1..ExternalSyntheticLambda0(materialBackOrchestrator0));
                }
            }
        };
        Context context1 = this.getContext();
        NavigationMenu navigationMenu0 = new NavigationMenu(context1);
        this.menu = navigationMenu0;
        TintTypedArray tintTypedArray0 = ThemeEnforcement.obtainTintedStyledAttributes(context1, attributeSet0, styleable.NavigationView, v, v1, new int[0]);
        if(tintTypedArray0.hasValue(styleable.NavigationView_android_background)) {
            ViewCompat.setBackground(this, tintTypedArray0.getDrawable(styleable.NavigationView_android_background));
        }
        int v2 = tintTypedArray0.getDimensionPixelSize(styleable.NavigationView_drawerLayoutCornerSize, 0);
        this.drawerLayoutCornerSize = v2;
        this.drawerLayoutCornerSizeBackAnimationEnabled = v2 == 0;
        this.drawerLayoutCornerSizeBackAnimationMax = this.getResources().getDimensionPixelSize(dimen.m3_navigation_drawer_layout_corner_size);
        Drawable drawable0 = this.getBackground();
        ColorStateList colorStateList0 = DrawableUtils.getColorStateListOrNull(drawable0);
        if(drawable0 == null || colorStateList0 != null) {
            MaterialShapeDrawable materialShapeDrawable0 = new MaterialShapeDrawable(ShapeAppearanceModel.builder(context1, attributeSet0, v, v1).build());
            if(colorStateList0 != null) {
                materialShapeDrawable0.setFillColor(colorStateList0);
            }
            materialShapeDrawable0.initializeElevationOverlay(context1);
            ViewCompat.setBackground(this, materialShapeDrawable0);
        }
        if(tintTypedArray0.hasValue(styleable.NavigationView_elevation)) {
            this.setElevation(((float)tintTypedArray0.getDimensionPixelSize(styleable.NavigationView_elevation, 0)));
        }
        this.setFitsSystemWindows(tintTypedArray0.getBoolean(styleable.NavigationView_android_fitsSystemWindows, false));
        this.maxWidth = tintTypedArray0.getDimensionPixelSize(styleable.NavigationView_android_maxWidth, 0);
        ColorStateList colorStateList1 = tintTypedArray0.hasValue(styleable.NavigationView_subheaderColor) ? tintTypedArray0.getColorStateList(styleable.NavigationView_subheaderColor) : null;
        int v3 = tintTypedArray0.hasValue(styleable.NavigationView_subheaderTextAppearance) ? tintTypedArray0.getResourceId(styleable.NavigationView_subheaderTextAppearance, 0) : 0;
        if(v3 == 0 && colorStateList1 == null) {
            colorStateList1 = this.createDefaultColorStateList(0x1010038);
        }
        ColorStateList colorStateList2 = tintTypedArray0.hasValue(styleable.NavigationView_itemIconTint) ? tintTypedArray0.getColorStateList(styleable.NavigationView_itemIconTint) : this.createDefaultColorStateList(0x1010038);
        int v4 = tintTypedArray0.hasValue(styleable.NavigationView_itemTextAppearance) ? tintTypedArray0.getResourceId(styleable.NavigationView_itemTextAppearance, 0) : 0;
        boolean z = tintTypedArray0.getBoolean(styleable.NavigationView_itemTextAppearanceActiveBoldEnabled, true);
        if(tintTypedArray0.hasValue(styleable.NavigationView_itemIconSize)) {
            this.setItemIconSize(tintTypedArray0.getDimensionPixelSize(styleable.NavigationView_itemIconSize, 0));
        }
        ColorStateList colorStateList3 = tintTypedArray0.hasValue(styleable.NavigationView_itemTextColor) ? tintTypedArray0.getColorStateList(styleable.NavigationView_itemTextColor) : null;
        if(v4 == 0 && colorStateList3 == null) {
            colorStateList3 = this.createDefaultColorStateList(0x1010036);
        }
        Drawable drawable1 = tintTypedArray0.getDrawable(styleable.NavigationView_itemBackground);
        if(drawable1 == null && this.hasShapeAppearance(tintTypedArray0)) {
            drawable1 = this.createDefaultItemBackground(tintTypedArray0);
            ColorStateList colorStateList4 = MaterialResources.getColorStateList(context1, tintTypedArray0, styleable.NavigationView_itemRippleColor);
            if(colorStateList4 != null) {
                Drawable drawable2 = this.createDefaultItemDrawable(tintTypedArray0, null);
                navigationMenuPresenter0.setItemForeground(new RippleDrawable(RippleUtils.sanitizeRippleDrawableColor(colorStateList4), null, drawable2));
            }
        }
        if(tintTypedArray0.hasValue(styleable.NavigationView_itemHorizontalPadding)) {
            this.setItemHorizontalPadding(tintTypedArray0.getDimensionPixelSize(styleable.NavigationView_itemHorizontalPadding, 0));
        }
        if(tintTypedArray0.hasValue(styleable.NavigationView_itemVerticalPadding)) {
            this.setItemVerticalPadding(tintTypedArray0.getDimensionPixelSize(styleable.NavigationView_itemVerticalPadding, 0));
        }
        this.setDividerInsetStart(tintTypedArray0.getDimensionPixelSize(styleable.NavigationView_dividerInsetStart, 0));
        this.setDividerInsetEnd(tintTypedArray0.getDimensionPixelSize(styleable.NavigationView_dividerInsetEnd, 0));
        this.setSubheaderInsetStart(tintTypedArray0.getDimensionPixelSize(styleable.NavigationView_subheaderInsetStart, 0));
        this.setSubheaderInsetEnd(tintTypedArray0.getDimensionPixelSize(styleable.NavigationView_subheaderInsetEnd, 0));
        this.setTopInsetScrimEnabled(tintTypedArray0.getBoolean(styleable.NavigationView_topInsetScrimEnabled, true));
        this.setBottomInsetScrimEnabled(tintTypedArray0.getBoolean(styleable.NavigationView_bottomInsetScrimEnabled, true));
        int v5 = tintTypedArray0.getDimensionPixelSize(styleable.NavigationView_itemIconPadding, 0);
        this.setItemMaxLines(tintTypedArray0.getInt(styleable.NavigationView_itemMaxLines, 1));
        navigationMenu0.setCallback(new Callback() {
            @Override  // androidx.appcompat.view.menu.MenuBuilder$Callback
            public boolean onMenuItemSelected(MenuBuilder menuBuilder0, MenuItem menuItem0) {
                return NavigationView.this.listener != null && NavigationView.this.listener.onNavigationItemSelected(menuItem0);
            }

            @Override  // androidx.appcompat.view.menu.MenuBuilder$Callback
            public void onMenuModeChange(MenuBuilder menuBuilder0) {
            }
        });
        navigationMenuPresenter0.setId(1);
        navigationMenuPresenter0.initForMenu(context1, navigationMenu0);
        if(v3 != 0) {
            navigationMenuPresenter0.setSubheaderTextAppearance(v3);
        }
        navigationMenuPresenter0.setSubheaderColor(colorStateList1);
        navigationMenuPresenter0.setItemIconTintList(colorStateList2);
        navigationMenuPresenter0.setOverScrollMode(this.getOverScrollMode());
        if(v4 != 0) {
            navigationMenuPresenter0.setItemTextAppearance(v4);
        }
        navigationMenuPresenter0.setItemTextAppearanceActiveBoldEnabled(z);
        navigationMenuPresenter0.setItemTextColor(colorStateList3);
        navigationMenuPresenter0.setItemBackground(drawable1);
        navigationMenuPresenter0.setItemIconPadding(v5);
        navigationMenu0.addMenuPresenter(navigationMenuPresenter0);
        this.addView(((View)navigationMenuPresenter0.getMenuView(this)));
        if(tintTypedArray0.hasValue(styleable.NavigationView_menu)) {
            this.inflateMenu(tintTypedArray0.getResourceId(styleable.NavigationView_menu, 0));
        }
        if(tintTypedArray0.hasValue(styleable.NavigationView_headerLayout)) {
            this.inflateHeaderView(tintTypedArray0.getResourceId(styleable.NavigationView_headerLayout, 0));
        }
        tintTypedArray0.recycle();
        this.setupInsetScrimsListener();
    }

    public void addHeaderView(View view0) {
        this.presenter.addHeaderView(view0);
    }

    @Override  // com.google.android.material.motion.MaterialBackHandler
    public void cancelBackProgress() {
        this.requireDrawerLayoutParent();
        this.sideContainerBackHelper.cancelBackProgress();
        this.maybeClearCornerSizeAnimationForDrawerLayout();
    }

    private ColorStateList createDefaultColorStateList(int v) {
        TypedValue typedValue0 = new TypedValue();
        if(!this.getContext().getTheme().resolveAttribute(v, typedValue0, true)) {
            return null;
        }
        ColorStateList colorStateList0 = AppCompatResources.getColorStateList(this.getContext(), typedValue0.resourceId);
        if(!this.getContext().getTheme().resolveAttribute(androidx.appcompat.R.attr.colorPrimary, typedValue0, true)) {
            return null;
        }
        int v1 = typedValue0.data;
        int v2 = colorStateList0.getDefaultColor();
        return new ColorStateList(new int[][]{NavigationView.DISABLED_STATE_SET, NavigationView.CHECKED_STATE_SET, NavigationView.EMPTY_STATE_SET}, new int[]{colorStateList0.getColorForState(NavigationView.DISABLED_STATE_SET, v2), v1, v2});
    }

    private Drawable createDefaultItemBackground(TintTypedArray tintTypedArray0) {
        return this.createDefaultItemDrawable(tintTypedArray0, MaterialResources.getColorStateList(this.getContext(), tintTypedArray0, styleable.NavigationView_itemShapeFillColor));
    }

    private Drawable createDefaultItemDrawable(TintTypedArray tintTypedArray0, ColorStateList colorStateList0) {
        int v = tintTypedArray0.getResourceId(styleable.NavigationView_itemShapeAppearance, 0);
        int v1 = tintTypedArray0.getResourceId(styleable.NavigationView_itemShapeAppearanceOverlay, 0);
        MaterialShapeDrawable materialShapeDrawable0 = new MaterialShapeDrawable(ShapeAppearanceModel.builder(this.getContext(), v, v1).build());
        materialShapeDrawable0.setFillColor(colorStateList0);
        return new InsetDrawable(materialShapeDrawable0, tintTypedArray0.getDimensionPixelSize(styleable.NavigationView_itemShapeInsetStart, 0), tintTypedArray0.getDimensionPixelSize(styleable.NavigationView_itemShapeInsetTop, 0), tintTypedArray0.getDimensionPixelSize(styleable.NavigationView_itemShapeInsetEnd, 0), tintTypedArray0.getDimensionPixelSize(styleable.NavigationView_itemShapeInsetBottom, 0));
    }

    @Override  // android.view.ViewGroup
    protected void dispatchDraw(Canvas canvas0) {
        NavigationView..ExternalSyntheticLambda0 navigationView$$ExternalSyntheticLambda00 = (Canvas canvas0) -> super.dispatchDraw(canvas0);
        this.shapeableDelegate.maybeClip(canvas0, navigationView$$ExternalSyntheticLambda00);
    }

    MaterialSideContainerBackHelper getBackHelper() {
        return this.sideContainerBackHelper;
    }

    public MenuItem getCheckedItem() {
        return this.presenter.getCheckedItem();
    }

    public int getDividerInsetEnd() {
        return this.presenter.getDividerInsetEnd();
    }

    public int getDividerInsetStart() {
        return this.presenter.getDividerInsetStart();
    }

    public int getHeaderCount() {
        return this.presenter.getHeaderCount();
    }

    public View getHeaderView(int v) {
        return this.presenter.getHeaderView(v);
    }

    public Drawable getItemBackground() {
        return this.presenter.getItemBackground();
    }

    public int getItemHorizontalPadding() {
        return this.presenter.getItemHorizontalPadding();
    }

    public int getItemIconPadding() {
        return this.presenter.getItemIconPadding();
    }

    public ColorStateList getItemIconTintList() {
        return this.presenter.getItemTintList();
    }

    public int getItemMaxLines() {
        return this.presenter.getItemMaxLines();
    }

    public ColorStateList getItemTextColor() {
        return this.presenter.getItemTextColor();
    }

    public int getItemVerticalPadding() {
        return this.presenter.getItemVerticalPadding();
    }

    public Menu getMenu() {
        return this.menu;
    }

    private MenuInflater getMenuInflater() {
        if(this.menuInflater == null) {
            this.menuInflater = new SupportMenuInflater(this.getContext());
        }
        return this.menuInflater;
    }

    public int getSubheaderInsetEnd() {
        return this.presenter.getSubheaderInsetEnd();
    }

    public int getSubheaderInsetStart() {
        return this.presenter.getSubheaderInsetStart();
    }

    @Override  // com.google.android.material.motion.MaterialBackHandler
    public void handleBackInvoked() {
        Pair pair0 = this.requireDrawerLayoutParent();
        DrawerLayout drawerLayout0 = (DrawerLayout)pair0.first;
        BackEventCompat backEventCompat0 = this.sideContainerBackHelper.onHandleBackInvoked();
        if(backEventCompat0 != null && Build.VERSION.SDK_INT >= 34) {
            int v = ((LayoutParams)pair0.second).gravity;
            Animator.AnimatorListener animator$AnimatorListener0 = DrawerLayoutUtils.getScrimCloseAnimatorListener(drawerLayout0, this);
            ValueAnimator.AnimatorUpdateListener valueAnimator$AnimatorUpdateListener0 = DrawerLayoutUtils.getScrimCloseAnimatorUpdateListener(drawerLayout0);
            this.sideContainerBackHelper.finishBackProgress(backEventCompat0, v, animator$AnimatorListener0, valueAnimator$AnimatorUpdateListener0);
            return;
        }
        drawerLayout0.closeDrawer(this);
    }

    // 去混淆评级： 低(20)
    private boolean hasShapeAppearance(TintTypedArray tintTypedArray0) {
        return tintTypedArray0.hasValue(styleable.NavigationView_itemShapeAppearance) || tintTypedArray0.hasValue(styleable.NavigationView_itemShapeAppearanceOverlay);
    }

    public View inflateHeaderView(int v) {
        return this.presenter.inflateHeaderView(v);
    }

    public void inflateMenu(int v) {
        this.presenter.setUpdateSuspended(true);
        this.getMenuInflater().inflate(v, this.menu);
        this.presenter.setUpdateSuspended(false);
        this.presenter.updateMenuView(false);
    }

    public boolean isBottomInsetScrimEnabled() {
        return this.bottomInsetScrimEnabled;
    }

    public boolean isTopInsetScrimEnabled() {
        return this.topInsetScrimEnabled;
    }

    // 检测为 Lambda 实现
    void lambda$dispatchDraw$0$com-google-android-material-navigation-NavigationView(Canvas canvas0) [...]

    private void maybeClearCornerSizeAnimationForDrawerLayout() {
        if(this.drawerLayoutCornerSizeBackAnimationEnabled && this.drawerLayoutCornerSize != 0) {
            this.drawerLayoutCornerSize = 0;
            this.maybeUpdateCornerSizeForDrawerLayout(this.getWidth(), this.getHeight());
        }
    }

    private void maybeUpdateCornerSizeForDrawerLayout(int v, int v1) {
        if(this.getParent() instanceof DrawerLayout && this.getLayoutParams() instanceof LayoutParams && (this.drawerLayoutCornerSize > 0 || this.drawerLayoutCornerSizeBackAnimationEnabled) && this.getBackground() instanceof MaterialShapeDrawable) {
            boolean z = GravityCompat.getAbsoluteGravity(((LayoutParams)this.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(this)) == 3;
            MaterialShapeDrawable materialShapeDrawable0 = (MaterialShapeDrawable)this.getBackground();
            Builder shapeAppearanceModel$Builder0 = materialShapeDrawable0.getShapeAppearanceModel().toBuilder().setAllCornerSizes(((float)this.drawerLayoutCornerSize));
            if(z) {
                shapeAppearanceModel$Builder0.setTopLeftCornerSize(0.0f);
                shapeAppearanceModel$Builder0.setBottomLeftCornerSize(0.0f);
            }
            else {
                shapeAppearanceModel$Builder0.setTopRightCornerSize(0.0f);
                shapeAppearanceModel$Builder0.setBottomRightCornerSize(0.0f);
            }
            ShapeAppearanceModel shapeAppearanceModel0 = shapeAppearanceModel$Builder0.build();
            materialShapeDrawable0.setShapeAppearanceModel(shapeAppearanceModel0);
            this.shapeableDelegate.onShapeAppearanceChanged(this, shapeAppearanceModel0);
            RectF rectF0 = new RectF(0.0f, 0.0f, ((float)v), ((float)v1));
            this.shapeableDelegate.onMaskChanged(this, rectF0);
            this.shapeableDelegate.setOffsetZeroCornerEdgeBoundsEnabled(this, true);
        }
    }

    @Override  // com.google.android.material.internal.ScrimInsetsFrameLayout
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
        ViewParent viewParent0 = this.getParent();
        if(viewParent0 instanceof DrawerLayout && this.backOrchestrator.shouldListenForBackCallbacks()) {
            ((DrawerLayout)viewParent0).removeDrawerListener(this.backDrawerListener);
            ((DrawerLayout)viewParent0).addDrawerListener(this.backDrawerListener);
            if(((DrawerLayout)viewParent0).isDrawerOpen(this)) {
                this.backOrchestrator.startListeningForBackCallbacksWithPriorityOverlay();
            }
        }
    }

    @Override  // com.google.android.material.internal.ScrimInsetsFrameLayout
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.getViewTreeObserver().removeOnGlobalLayoutListener(this.onGlobalLayoutListener);
        ViewParent viewParent0 = this.getParent();
        if(viewParent0 instanceof DrawerLayout) {
            ((DrawerLayout)viewParent0).removeDrawerListener(this.backDrawerListener);
        }
    }

    @Override  // com.google.android.material.internal.ScrimInsetsFrameLayout
    protected void onInsetsChanged(WindowInsetsCompat windowInsetsCompat0) {
        this.presenter.dispatchApplyWindowInsets(windowInsetsCompat0);
    }

    @Override  // android.widget.FrameLayout
    protected void onMeasure(int v, int v1) {
        switch(View.MeasureSpec.getMode(v)) {
            case 0x80000000: {
                v = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(v), this.maxWidth), 0x40000000);
                break;
            }
            case 0: {
                v = View.MeasureSpec.makeMeasureSpec(this.maxWidth, 0x40000000);
            }
        }
        super.onMeasure(v, v1);
    }

    @Override  // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(!(parcelable0 instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable0);
            return;
        }
        super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
        this.menu.restorePresenterStates(((SavedState)parcelable0).menuState);
    }

    @Override  // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState(super.onSaveInstanceState());
        parcelable0.menuState = new Bundle();
        this.menu.savePresenterStates(parcelable0.menuState);
        return parcelable0;
    }

    @Override  // android.view.View
    protected void onSizeChanged(int v, int v1, int v2, int v3) {
        super.onSizeChanged(v, v1, v2, v3);
        this.maybeUpdateCornerSizeForDrawerLayout(v, v1);
    }

    public void removeHeaderView(View view0) {
        this.presenter.removeHeaderView(view0);
    }

    private Pair requireDrawerLayoutParent() {
        ViewParent viewParent0 = this.getParent();
        ViewGroup.LayoutParams viewGroup$LayoutParams0 = this.getLayoutParams();
        if(!(viewParent0 instanceof DrawerLayout) || !(viewGroup$LayoutParams0 instanceof LayoutParams)) {
            throw new IllegalStateException("NavigationView back progress requires the direct parent view to be a DrawerLayout.");
        }
        return new Pair(((DrawerLayout)viewParent0), ((LayoutParams)viewGroup$LayoutParams0));
    }

    public void setBottomInsetScrimEnabled(boolean z) {
        this.bottomInsetScrimEnabled = z;
    }

    public void setCheckedItem(int v) {
        MenuItem menuItem0 = this.menu.findItem(v);
        if(menuItem0 != null) {
            this.presenter.setCheckedItem(((MenuItemImpl)menuItem0));
        }
    }

    public void setCheckedItem(MenuItem menuItem0) {
        int v = menuItem0.getItemId();
        MenuItem menuItem1 = this.menu.findItem(v);
        if(menuItem1 == null) {
            throw new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
        }
        this.presenter.setCheckedItem(((MenuItemImpl)menuItem1));
    }

    public void setDividerInsetEnd(int v) {
        this.presenter.setDividerInsetEnd(v);
    }

    public void setDividerInsetStart(int v) {
        this.presenter.setDividerInsetStart(v);
    }

    @Override  // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        MaterialShapeUtils.setElevation(this, f);
    }

    public void setForceCompatClippingEnabled(boolean z) {
        this.shapeableDelegate.setForceCompatClippingEnabled(this, z);
    }

    public void setItemBackground(Drawable drawable0) {
        this.presenter.setItemBackground(drawable0);
    }

    public void setItemBackgroundResource(int v) {
        this.setItemBackground(ContextCompat.getDrawable(this.getContext(), v));
    }

    public void setItemHorizontalPadding(int v) {
        this.presenter.setItemHorizontalPadding(v);
    }

    public void setItemHorizontalPaddingResource(int v) {
        int v1 = this.getResources().getDimensionPixelSize(v);
        this.presenter.setItemHorizontalPadding(v1);
    }

    public void setItemIconPadding(int v) {
        this.presenter.setItemIconPadding(v);
    }

    public void setItemIconPaddingResource(int v) {
        int v1 = this.getResources().getDimensionPixelSize(v);
        this.presenter.setItemIconPadding(v1);
    }

    public void setItemIconSize(int v) {
        this.presenter.setItemIconSize(v);
    }

    public void setItemIconTintList(ColorStateList colorStateList0) {
        this.presenter.setItemIconTintList(colorStateList0);
    }

    public void setItemMaxLines(int v) {
        this.presenter.setItemMaxLines(v);
    }

    public void setItemTextAppearance(int v) {
        this.presenter.setItemTextAppearance(v);
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z) {
        this.presenter.setItemTextAppearanceActiveBoldEnabled(z);
    }

    public void setItemTextColor(ColorStateList colorStateList0) {
        this.presenter.setItemTextColor(colorStateList0);
    }

    public void setItemVerticalPadding(int v) {
        this.presenter.setItemVerticalPadding(v);
    }

    public void setItemVerticalPaddingResource(int v) {
        int v1 = this.getResources().getDimensionPixelSize(v);
        this.presenter.setItemVerticalPadding(v1);
    }

    public void setNavigationItemSelectedListener(OnNavigationItemSelectedListener navigationView$OnNavigationItemSelectedListener0) {
        this.listener = navigationView$OnNavigationItemSelectedListener0;
    }

    @Override  // android.view.View
    public void setOverScrollMode(int v) {
        super.setOverScrollMode(v);
        NavigationMenuPresenter navigationMenuPresenter0 = this.presenter;
        if(navigationMenuPresenter0 != null) {
            navigationMenuPresenter0.setOverScrollMode(v);
        }
    }

    public void setSubheaderInsetEnd(int v) {
        this.presenter.setSubheaderInsetEnd(v);
    }

    public void setSubheaderInsetStart(int v) {
        this.presenter.setSubheaderInsetStart(v);
    }

    public void setTopInsetScrimEnabled(boolean z) {
        this.topInsetScrimEnabled = z;
    }

    private void setupInsetScrimsListener() {
        this.onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override  // android.view.ViewTreeObserver$OnGlobalLayoutListener
            public void onGlobalLayout() {
                NavigationView.this.getLocationOnScreen(NavigationView.this.tmpLocation);
                boolean z = true;
                boolean z1 = NavigationView.this.tmpLocation[1] == 0;
                NavigationView.this.presenter.setBehindStatusBar(z1);
                NavigationView.this.setDrawTopInsetForeground(z1 && NavigationView.this.isTopInsetScrimEnabled());
                boolean z2 = NavigationView.this.tmpLocation[0] == 0 || NavigationView.this.tmpLocation[0] + NavigationView.this.getWidth() == 0;
                NavigationView.this.setDrawLeftInsetForeground(z2);
                Activity activity0 = ContextUtils.getActivity(NavigationView.this.getContext());
                if(activity0 != null) {
                    Rect rect0 = WindowUtils.getCurrentWindowBounds(activity0);
                    boolean z3 = rect0.height() - NavigationView.this.getHeight() == NavigationView.this.tmpLocation[1];
                    boolean z4 = Color.alpha(activity0.getWindow().getNavigationBarColor()) != 0;
                    NavigationView.this.setDrawBottomInsetForeground(z3 && z4 && NavigationView.this.isBottomInsetScrimEnabled());
                    if(rect0.width() != NavigationView.this.tmpLocation[0] && rect0.width() - NavigationView.this.getWidth() != NavigationView.this.tmpLocation[0]) {
                        z = false;
                    }
                    NavigationView.this.setDrawRightInsetForeground(z);
                }
            }
        };
        this.getViewTreeObserver().addOnGlobalLayoutListener(this.onGlobalLayoutListener);
    }

    @Override  // com.google.android.material.motion.MaterialBackHandler
    public void startBackProgress(BackEventCompat backEventCompat0) {
        this.requireDrawerLayoutParent();
        this.sideContainerBackHelper.startBackProgress(backEventCompat0);
    }

    @Override  // com.google.android.material.motion.MaterialBackHandler
    public void updateBackProgress(BackEventCompat backEventCompat0) {
        Pair pair0 = this.requireDrawerLayoutParent();
        this.sideContainerBackHelper.updateBackProgress(backEventCompat0, ((LayoutParams)pair0.second).gravity);
        if(this.drawerLayoutCornerSizeBackAnimationEnabled) {
            float f = this.sideContainerBackHelper.interpolateProgress(backEventCompat0.getProgress());
            this.drawerLayoutCornerSize = AnimationUtils.lerp(0, this.drawerLayoutCornerSizeBackAnimationMax, f);
            this.maybeUpdateCornerSizeForDrawerLayout(this.getWidth(), this.getHeight());
        }
    }
}

