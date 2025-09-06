package com.google.android.material.navigation;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuBuilder.Callback;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R.dimen;
import com.google.android.material.R.styleable;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class NavigationBarView extends FrameLayout {
    @Retention(RetentionPolicy.SOURCE)
    public @interface LabelVisibility {
    }

    public interface OnItemReselectedListener {
        void onNavigationItemReselected(MenuItem arg1);
    }

    public interface OnItemSelectedListener {
        boolean onNavigationItemSelected(MenuItem arg1);
    }

    static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR;
        Bundle menuPresenterState;

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
            if(classLoader0 == null) {
                classLoader0 = this.getClass().getClassLoader();
            }
            this.readFromParcel(parcel0, classLoader0);
        }

        public SavedState(Parcelable parcelable0) {
            super(parcelable0);
        }

        private void readFromParcel(Parcel parcel0, ClassLoader classLoader0) {
            this.menuPresenterState = parcel0.readBundle(classLoader0);
        }

        @Override  // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel0, int v) {
            super.writeToParcel(parcel0, v);
            parcel0.writeBundle(this.menuPresenterState);
        }
    }

    public static final int LABEL_VISIBILITY_AUTO = -1;
    public static final int LABEL_VISIBILITY_LABELED = 1;
    public static final int LABEL_VISIBILITY_SELECTED = 0;
    public static final int LABEL_VISIBILITY_UNLABELED = 2;
    private static final int MENU_PRESENTER_ID = 1;
    private final NavigationBarMenu menu;
    private MenuInflater menuInflater;
    private final NavigationBarMenuView menuView;
    private final NavigationBarPresenter presenter;
    private OnItemReselectedListener reselectedListener;
    private OnItemSelectedListener selectedListener;

    public NavigationBarView(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(MaterialThemeOverlay.wrap(context0, attributeSet0, v, v1), attributeSet0, v);
        NavigationBarPresenter navigationBarPresenter0 = new NavigationBarPresenter();
        this.presenter = navigationBarPresenter0;
        Context context1 = this.getContext();
        TintTypedArray tintTypedArray0 = ThemeEnforcement.obtainTintedStyledAttributes(context1, attributeSet0, styleable.NavigationBarView, v, v1, new int[]{styleable.NavigationBarView_itemTextAppearanceInactive, styleable.NavigationBarView_itemTextAppearanceActive});
        NavigationBarMenu navigationBarMenu0 = new NavigationBarMenu(context1, this.getClass(), this.getMaxItemCount());
        this.menu = navigationBarMenu0;
        NavigationBarMenuView navigationBarMenuView0 = this.createNavigationBarMenuView(context1);
        this.menuView = navigationBarMenuView0;
        navigationBarPresenter0.setMenuView(navigationBarMenuView0);
        navigationBarPresenter0.setId(1);
        navigationBarMenuView0.setPresenter(navigationBarPresenter0);
        navigationBarMenu0.addMenuPresenter(navigationBarPresenter0);
        navigationBarPresenter0.initForMenu(this.getContext(), navigationBarMenu0);
        if(tintTypedArray0.hasValue(styleable.NavigationBarView_itemIconTint)) {
            navigationBarMenuView0.setIconTintList(tintTypedArray0.getColorStateList(styleable.NavigationBarView_itemIconTint));
        }
        else {
            navigationBarMenuView0.setIconTintList(navigationBarMenuView0.createDefaultColorStateList(0x1010038));
        }
        int v2 = this.getResources().getDimensionPixelSize(dimen.mtrl_navigation_bar_item_default_icon_size);
        this.setItemIconSize(tintTypedArray0.getDimensionPixelSize(styleable.NavigationBarView_itemIconSize, v2));
        if(tintTypedArray0.hasValue(styleable.NavigationBarView_itemTextAppearanceInactive)) {
            this.setItemTextAppearanceInactive(tintTypedArray0.getResourceId(styleable.NavigationBarView_itemTextAppearanceInactive, 0));
        }
        if(tintTypedArray0.hasValue(styleable.NavigationBarView_itemTextAppearanceActive)) {
            this.setItemTextAppearanceActive(tintTypedArray0.getResourceId(styleable.NavigationBarView_itemTextAppearanceActive, 0));
        }
        this.setItemTextAppearanceActiveBoldEnabled(tintTypedArray0.getBoolean(styleable.NavigationBarView_itemTextAppearanceActiveBoldEnabled, true));
        if(tintTypedArray0.hasValue(styleable.NavigationBarView_itemTextColor)) {
            this.setItemTextColor(tintTypedArray0.getColorStateList(styleable.NavigationBarView_itemTextColor));
        }
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
        if(tintTypedArray0.hasValue(styleable.NavigationBarView_itemPaddingTop)) {
            this.setItemPaddingTop(tintTypedArray0.getDimensionPixelSize(styleable.NavigationBarView_itemPaddingTop, 0));
        }
        if(tintTypedArray0.hasValue(styleable.NavigationBarView_itemPaddingBottom)) {
            this.setItemPaddingBottom(tintTypedArray0.getDimensionPixelSize(styleable.NavigationBarView_itemPaddingBottom, 0));
        }
        if(tintTypedArray0.hasValue(styleable.NavigationBarView_activeIndicatorLabelPadding)) {
            this.setActiveIndicatorLabelPadding(tintTypedArray0.getDimensionPixelSize(styleable.NavigationBarView_activeIndicatorLabelPadding, 0));
        }
        if(tintTypedArray0.hasValue(styleable.NavigationBarView_elevation)) {
            this.setElevation(((float)tintTypedArray0.getDimensionPixelSize(styleable.NavigationBarView_elevation, 0)));
        }
        ColorStateList colorStateList1 = MaterialResources.getColorStateList(context1, tintTypedArray0, styleable.NavigationBarView_backgroundTint);
        DrawableCompat.setTintList(this.getBackground().mutate(), colorStateList1);
        this.setLabelVisibilityMode(tintTypedArray0.getInteger(styleable.NavigationBarView_labelVisibilityMode, -1));
        int v3 = tintTypedArray0.getResourceId(styleable.NavigationBarView_itemBackground, 0);
        if(v3 == 0) {
            this.setItemRippleColor(MaterialResources.getColorStateList(context1, tintTypedArray0, styleable.NavigationBarView_itemRippleColor));
        }
        else {
            navigationBarMenuView0.setItemBackgroundRes(v3);
        }
        int v4 = tintTypedArray0.getResourceId(styleable.NavigationBarView_itemActiveIndicatorStyle, 0);
        if(v4 != 0) {
            this.setItemActiveIndicatorEnabled(true);
            TypedArray typedArray0 = context1.obtainStyledAttributes(v4, styleable.NavigationBarActiveIndicator);
            this.setItemActiveIndicatorWidth(typedArray0.getDimensionPixelSize(styleable.NavigationBarActiveIndicator_android_width, 0));
            this.setItemActiveIndicatorHeight(typedArray0.getDimensionPixelSize(styleable.NavigationBarActiveIndicator_android_height, 0));
            this.setItemActiveIndicatorMarginHorizontal(typedArray0.getDimensionPixelOffset(styleable.NavigationBarActiveIndicator_marginHorizontal, 0));
            this.setItemActiveIndicatorColor(MaterialResources.getColorStateList(context1, typedArray0, styleable.NavigationBarActiveIndicator_android_color));
            this.setItemActiveIndicatorShapeAppearance(ShapeAppearanceModel.builder(context1, typedArray0.getResourceId(styleable.NavigationBarActiveIndicator_shapeAppearance, 0), 0).build());
            typedArray0.recycle();
        }
        if(tintTypedArray0.hasValue(styleable.NavigationBarView_menu)) {
            this.inflateMenu(tintTypedArray0.getResourceId(styleable.NavigationBarView_menu, 0));
        }
        tintTypedArray0.recycle();
        this.addView(navigationBarMenuView0);
        navigationBarMenu0.setCallback(new Callback() {
            @Override  // androidx.appcompat.view.menu.MenuBuilder$Callback
            public boolean onMenuItemSelected(MenuBuilder menuBuilder0, MenuItem menuItem0) {
                if(NavigationBarView.this.reselectedListener != null && menuItem0.getItemId() == NavigationBarView.this.getSelectedItemId()) {
                    NavigationBarView.this.reselectedListener.onNavigationItemReselected(menuItem0);
                    return true;
                }
                return NavigationBarView.this.selectedListener != null && !NavigationBarView.this.selectedListener.onNavigationItemSelected(menuItem0);
            }

            @Override  // androidx.appcompat.view.menu.MenuBuilder$Callback
            public void onMenuModeChange(MenuBuilder menuBuilder0) {
            }
        });
    }

    protected abstract NavigationBarMenuView createNavigationBarMenuView(Context arg1);

    public int getActiveIndicatorLabelPadding() {
        return this.menuView.getActiveIndicatorLabelPadding();
    }

    public BadgeDrawable getBadge(int v) {
        return this.menuView.getBadge(v);
    }

    public ColorStateList getItemActiveIndicatorColor() {
        return this.menuView.getItemActiveIndicatorColor();
    }

    public int getItemActiveIndicatorHeight() {
        return this.menuView.getItemActiveIndicatorHeight();
    }

    public int getItemActiveIndicatorMarginHorizontal() {
        return this.menuView.getItemActiveIndicatorMarginHorizontal();
    }

    public ShapeAppearanceModel getItemActiveIndicatorShapeAppearance() {
        return this.menuView.getItemActiveIndicatorShapeAppearance();
    }

    public int getItemActiveIndicatorWidth() {
        return this.menuView.getItemActiveIndicatorWidth();
    }

    public Drawable getItemBackground() {
        return this.menuView.getItemBackground();
    }

    @Deprecated
    public int getItemBackgroundResource() {
        return this.menuView.getItemBackgroundRes();
    }

    public int getItemIconSize() {
        return this.menuView.getItemIconSize();
    }

    public ColorStateList getItemIconTintList() {
        return this.menuView.getIconTintList();
    }

    public int getItemPaddingBottom() {
        return this.menuView.getItemPaddingBottom();
    }

    public int getItemPaddingTop() {
        return this.menuView.getItemPaddingTop();
    }

    public ColorStateList getItemRippleColor() {
        return this.menuView.getItemRippleColor();
    }

    public int getItemTextAppearanceActive() {
        return this.menuView.getItemTextAppearanceActive();
    }

    public int getItemTextAppearanceInactive() {
        return this.menuView.getItemTextAppearanceInactive();
    }

    public ColorStateList getItemTextColor() {
        return this.menuView.getItemTextColor();
    }

    public int getLabelVisibilityMode() {
        return this.menuView.getLabelVisibilityMode();
    }

    public abstract int getMaxItemCount();

    public Menu getMenu() {
        return this.menu;
    }

    private MenuInflater getMenuInflater() {
        if(this.menuInflater == null) {
            this.menuInflater = new SupportMenuInflater(this.getContext());
        }
        return this.menuInflater;
    }

    public MenuView getMenuView() {
        return this.menuView;
    }

    public BadgeDrawable getOrCreateBadge(int v) {
        return this.menuView.getOrCreateBadge(v);
    }

    public NavigationBarPresenter getPresenter() {
        return this.presenter;
    }

    public int getSelectedItemId() {
        return this.menuView.getSelectedItemId();
    }

    public void inflateMenu(int v) {
        this.presenter.setUpdateSuspended(true);
        this.getMenuInflater().inflate(v, this.menu);
        this.presenter.setUpdateSuspended(false);
        this.presenter.updateMenuView(true);
    }

    public boolean isItemActiveIndicatorEnabled() {
        return this.menuView.getItemActiveIndicatorEnabled();
    }

    @Override  // android.view.ViewGroup
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
    }

    @Override  // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable0) {
        if(!(parcelable0 instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable0);
            return;
        }
        super.onRestoreInstanceState(((SavedState)parcelable0).getSuperState());
        this.menu.restorePresenterStates(((SavedState)parcelable0).menuPresenterState);
    }

    @Override  // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable parcelable0 = new SavedState(super.onSaveInstanceState());
        parcelable0.menuPresenterState = new Bundle();
        this.menu.savePresenterStates(parcelable0.menuPresenterState);
        return parcelable0;
    }

    public void removeBadge(int v) {
        this.menuView.removeBadge(v);
    }

    public void setActiveIndicatorLabelPadding(int v) {
        this.menuView.setActiveIndicatorLabelPadding(v);
    }

    @Override  // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        MaterialShapeUtils.setElevation(this, f);
    }

    public void setItemActiveIndicatorColor(ColorStateList colorStateList0) {
        this.menuView.setItemActiveIndicatorColor(colorStateList0);
    }

    public void setItemActiveIndicatorEnabled(boolean z) {
        this.menuView.setItemActiveIndicatorEnabled(z);
    }

    public void setItemActiveIndicatorHeight(int v) {
        this.menuView.setItemActiveIndicatorHeight(v);
    }

    public void setItemActiveIndicatorMarginHorizontal(int v) {
        this.menuView.setItemActiveIndicatorMarginHorizontal(v);
    }

    public void setItemActiveIndicatorShapeAppearance(ShapeAppearanceModel shapeAppearanceModel0) {
        this.menuView.setItemActiveIndicatorShapeAppearance(shapeAppearanceModel0);
    }

    public void setItemActiveIndicatorWidth(int v) {
        this.menuView.setItemActiveIndicatorWidth(v);
    }

    public void setItemBackground(Drawable drawable0) {
        this.menuView.setItemBackground(drawable0);
    }

    public void setItemBackgroundResource(int v) {
        this.menuView.setItemBackgroundRes(v);
    }

    public void setItemIconSize(int v) {
        this.menuView.setItemIconSize(v);
    }

    public void setItemIconSizeRes(int v) {
        this.setItemIconSize(this.getResources().getDimensionPixelSize(v));
    }

    public void setItemIconTintList(ColorStateList colorStateList0) {
        this.menuView.setIconTintList(colorStateList0);
    }

    public void setItemOnTouchListener(int v, View.OnTouchListener view$OnTouchListener0) {
        this.menuView.setItemOnTouchListener(v, view$OnTouchListener0);
    }

    public void setItemPaddingBottom(int v) {
        this.menuView.setItemPaddingBottom(v);
    }

    public void setItemPaddingTop(int v) {
        this.menuView.setItemPaddingTop(v);
    }

    public void setItemRippleColor(ColorStateList colorStateList0) {
        this.menuView.setItemRippleColor(colorStateList0);
    }

    public void setItemTextAppearanceActive(int v) {
        this.menuView.setItemTextAppearanceActive(v);
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z) {
        this.menuView.setItemTextAppearanceActiveBoldEnabled(z);
    }

    public void setItemTextAppearanceInactive(int v) {
        this.menuView.setItemTextAppearanceInactive(v);
    }

    public void setItemTextColor(ColorStateList colorStateList0) {
        this.menuView.setItemTextColor(colorStateList0);
    }

    public void setLabelVisibilityMode(int v) {
        if(this.menuView.getLabelVisibilityMode() != v) {
            this.menuView.setLabelVisibilityMode(v);
            this.presenter.updateMenuView(false);
        }
    }

    public void setOnItemReselectedListener(OnItemReselectedListener navigationBarView$OnItemReselectedListener0) {
        this.reselectedListener = navigationBarView$OnItemReselectedListener0;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener navigationBarView$OnItemSelectedListener0) {
        this.selectedListener = navigationBarView$OnItemSelectedListener0;
    }

    public void setSelectedItemId(int v) {
        MenuItem menuItem0 = this.menu.findItem(v);
        if(menuItem0 != null && !this.menu.performItemAction(menuItem0, this.presenter, 0)) {
            menuItem0.setChecked(true);
        }
    }
}

