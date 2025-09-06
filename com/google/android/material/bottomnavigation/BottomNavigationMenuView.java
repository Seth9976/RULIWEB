package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.content.res.Resources;
import android.view.View.MeasureSpec;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout.LayoutParams;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.view.ViewCompat;
import com.google.android.material.R.dimen;
import com.google.android.material.navigation.NavigationBarItemView;
import com.google.android.material.navigation.NavigationBarMenuView;
import java.util.ArrayList;
import java.util.List;

public class BottomNavigationMenuView extends NavigationBarMenuView {
    private final int activeItemMaxWidth;
    private final int activeItemMinWidth;
    private final int inactiveItemMaxWidth;
    private final int inactiveItemMinWidth;
    private boolean itemHorizontalTranslationEnabled;
    private final List tempChildWidths;

    public BottomNavigationMenuView(Context context0) {
        super(context0);
        this.tempChildWidths = new ArrayList();
        FrameLayout.LayoutParams frameLayout$LayoutParams0 = new FrameLayout.LayoutParams(-2, -2);
        frameLayout$LayoutParams0.gravity = 17;
        this.setLayoutParams(frameLayout$LayoutParams0);
        Resources resources0 = this.getResources();
        this.inactiveItemMaxWidth = resources0.getDimensionPixelSize(dimen.design_bottom_navigation_item_max_width);
        this.inactiveItemMinWidth = resources0.getDimensionPixelSize(dimen.design_bottom_navigation_item_min_width);
        this.activeItemMaxWidth = resources0.getDimensionPixelSize(dimen.design_bottom_navigation_active_item_max_width);
        this.activeItemMinWidth = resources0.getDimensionPixelSize(dimen.design_bottom_navigation_active_item_min_width);
    }

    @Override  // com.google.android.material.navigation.NavigationBarMenuView
    protected NavigationBarItemView createNavigationBarItemView(Context context0) {
        return new BottomNavigationItemView(context0);
    }

    public boolean isItemHorizontalTranslationEnabled() {
        return this.itemHorizontalTranslationEnabled;
    }

    @Override  // android.view.ViewGroup
    protected void onLayout(boolean z, int v, int v1, int v2, int v3) {
        int v4 = this.getChildCount();
        int v5 = v3 - v1;
        int v7 = 0;
        for(int v6 = 0; v6 < v4; ++v6) {
            View view0 = this.getChildAt(v6);
            if(view0.getVisibility() != 8) {
                if(ViewCompat.getLayoutDirection(this) == 1) {
                    int v8 = v2 - v - v7;
                    view0.layout(v8 - view0.getMeasuredWidth(), 0, v8, v5);
                }
                else {
                    view0.layout(v7, 0, view0.getMeasuredWidth() + v7, v5);
                }
                v7 += view0.getMeasuredWidth();
            }
        }
    }

    @Override  // android.view.View
    protected void onMeasure(int v, int v1) {
        int v16;
        int v20;
        MenuBuilder menuBuilder0 = this.getMenu();
        int v2 = View.MeasureSpec.getSize(v);
        int v3 = menuBuilder0.getVisibleItems().size();
        int v4 = this.getChildCount();
        this.tempChildWidths.clear();
        int v5 = View.MeasureSpec.getSize(v1);
        int v6 = View.MeasureSpec.makeMeasureSpec(v5, 0x40000000);
        int v8 = 1;
        if(!this.isShifting(this.getLabelVisibilityMode(), v3) || !this.isItemHorizontalTranslationEnabled()) {
            if(v3 != 0) {
                v8 = v3;
            }
            int v17 = Math.min(v2 / v8, this.activeItemMaxWidth);
            int v18 = v2 - v3 * v17;
            for(int v19 = 0; v19 < v4; ++v19) {
                if(this.getChildAt(v19).getVisibility() == 8) {
                    v20 = 0;
                }
                else if(v18 > 0) {
                    v20 = v17 + 1;
                    --v18;
                }
                else {
                    v20 = v17;
                }
                this.tempChildWidths.add(v20);
            }
        }
        else {
            View view0 = this.getChildAt(this.getSelectedItemPosition());
            int v9 = this.activeItemMinWidth;
            if(view0.getVisibility() != 8) {
                view0.measure(View.MeasureSpec.makeMeasureSpec(this.activeItemMaxWidth, 0x80000000), v6);
                v9 = Math.max(v9, view0.getMeasuredWidth());
            }
            int v10 = v3 - (view0.getVisibility() == 8 ? 0 : 1);
            int v11 = Math.min(v2 - this.inactiveItemMinWidth * v10, Math.min(v9, this.activeItemMaxWidth));
            int v12 = v2 - v11;
            if(v10 != 0) {
                v8 = v10;
            }
            int v13 = Math.min(v12 / v8, this.inactiveItemMaxWidth);
            int v14 = v12 - v10 * v13;
            for(int v15 = 0; v15 < v4; ++v15) {
                if(this.getChildAt(v15).getVisibility() == 8) {
                    v16 = 0;
                }
                else {
                    v16 = v15 == this.getSelectedItemPosition() ? v11 : v13;
                    if(v14 > 0) {
                        ++v16;
                        --v14;
                    }
                }
                this.tempChildWidths.add(v16);
            }
        }
        int v21 = 0;
        for(int v7 = 0; v7 < v4; ++v7) {
            View view1 = this.getChildAt(v7);
            if(view1.getVisibility() != 8) {
                view1.measure(View.MeasureSpec.makeMeasureSpec(((int)(((Integer)this.tempChildWidths.get(v7)))), 0x40000000), v6);
                ViewGroup.LayoutParams viewGroup$LayoutParams0 = view1.getLayoutParams();
                viewGroup$LayoutParams0.width = view1.getMeasuredWidth();
                v21 += view1.getMeasuredWidth();
            }
        }
        this.setMeasuredDimension(v21, v5);
    }

    public void setItemHorizontalTranslationEnabled(boolean z) {
        this.itemHorizontalTranslationEnabled = z;
    }
}

