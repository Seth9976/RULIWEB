package com.google.android.material.bottomnavigation;

import android.content.Context;
import com.google.android.material.R.dimen;
import com.google.android.material.R.layout;
import com.google.android.material.navigation.NavigationBarItemView;

public class BottomNavigationItemView extends NavigationBarItemView {
    public BottomNavigationItemView(Context context0) {
        super(context0);
    }

    @Override  // com.google.android.material.navigation.NavigationBarItemView
    protected int getItemDefaultMarginResId() {
        return dimen.design_bottom_navigation_margin;
    }

    @Override  // com.google.android.material.navigation.NavigationBarItemView
    protected int getItemLayoutResId() {
        return layout.design_bottom_navigation_item;
    }
}

