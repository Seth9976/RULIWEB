package com.google.android.material.internal;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class NavigationMenuView extends RecyclerView implements MenuView {
    public NavigationMenuView(Context context0) {
        this(context0, null);
    }

    public NavigationMenuView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0);
    }

    public NavigationMenuView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0, v);
        this.setLayoutManager(new LinearLayoutManager(context0, 1, false));
    }

    @Override  // androidx.appcompat.view.menu.MenuView
    public int getWindowAnimations() {
        return 0;
    }

    @Override  // androidx.appcompat.view.menu.MenuView
    public void initialize(MenuBuilder menuBuilder0) {
    }
}

