package com.google.android.material.bottomnavigation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.R.attr;
import com.google.android.material.R.color;
import com.google.android.material.R.dimen;
import com.google.android.material.R.style;
import com.google.android.material.R.styleable;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils.OnApplyWindowInsetsListener;
import com.google.android.material.internal.ViewUtils.RelativePadding;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.navigation.NavigationBarMenuView;
import com.google.android.material.navigation.NavigationBarView.OnItemReselectedListener;
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener;
import com.google.android.material.navigation.NavigationBarView;

public class BottomNavigationView extends NavigationBarView {
    @Deprecated
    public interface OnNavigationItemReselectedListener extends OnItemReselectedListener {
    }

    @Deprecated
    public interface OnNavigationItemSelectedListener extends OnItemSelectedListener {
    }

    private static final int MAX_ITEM_COUNT = 5;

    public BottomNavigationView(Context context0) {
        this(context0, null);
    }

    public BottomNavigationView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, attr.bottomNavigationStyle);
    }

    public BottomNavigationView(Context context0, AttributeSet attributeSet0, int v) {
        this(context0, attributeSet0, v, style.Widget_Design_BottomNavigationView);
    }

    public BottomNavigationView(Context context0, AttributeSet attributeSet0, int v, int v1) {
        super(context0, attributeSet0, v, v1);
        TintTypedArray tintTypedArray0 = ThemeEnforcement.obtainTintedStyledAttributes(this.getContext(), attributeSet0, styleable.BottomNavigationView, v, v1, new int[0]);
        this.setItemHorizontalTranslationEnabled(tintTypedArray0.getBoolean(styleable.BottomNavigationView_itemHorizontalTranslationEnabled, true));
        if(tintTypedArray0.hasValue(styleable.BottomNavigationView_android_minHeight)) {
            this.setMinimumHeight(tintTypedArray0.getDimensionPixelSize(styleable.BottomNavigationView_android_minHeight, 0));
        }
        boolean z = !tintTypedArray0.getBoolean(styleable.BottomNavigationView_compatShadowEnabled, true);
        tintTypedArray0.recycle();
        this.applyWindowInsets();
    }

    private void addCompatibilityTopDivider(Context context0) {
        View view0 = new View(context0);
        view0.setBackgroundColor(ContextCompat.getColor(context0, color.design_bottom_navigation_shadow_color));
        view0.setLayoutParams(new FrameLayout.LayoutParams(-1, this.getResources().getDimensionPixelSize(dimen.design_bottom_navigation_shadow_height)));
        this.addView(view0);
    }

    private void applyWindowInsets() {
        ViewUtils.doOnApplyWindowInsets(this, new OnApplyWindowInsetsListener() {
            @Override  // com.google.android.material.internal.ViewUtils$OnApplyWindowInsetsListener
            public WindowInsetsCompat onApplyWindowInsets(View view0, WindowInsetsCompat windowInsetsCompat0, RelativePadding viewUtils$RelativePadding0) {
                viewUtils$RelativePadding0.bottom += windowInsetsCompat0.getSystemWindowInsetBottom();
                boolean z = ViewCompat.getLayoutDirection(view0) == 1;
                int v = windowInsetsCompat0.getSystemWindowInsetLeft();
                int v1 = windowInsetsCompat0.getSystemWindowInsetRight();
                viewUtils$RelativePadding0.start += (z ? v1 : v);
                int v2 = viewUtils$RelativePadding0.end;
                if(!z) {
                    v = v1;
                }
                viewUtils$RelativePadding0.end = v2 + v;
                viewUtils$RelativePadding0.applyToView(view0);
                return windowInsetsCompat0;
            }
        });
    }

    @Override  // com.google.android.material.navigation.NavigationBarView
    protected NavigationBarMenuView createNavigationBarMenuView(Context context0) {
        return new BottomNavigationMenuView(context0);
    }

    @Override  // com.google.android.material.navigation.NavigationBarView
    public int getMaxItemCount() {
        return 5;
    }

    public boolean isItemHorizontalTranslationEnabled() {
        return ((BottomNavigationMenuView)this.getMenuView()).isItemHorizontalTranslationEnabled();
    }

    private int makeMinHeightSpec(int v) {
        int v1 = this.getSuggestedMinimumHeight();
        if(View.MeasureSpec.getMode(v) != 0x40000000 && v1 > 0) {
            int v2 = this.getPaddingTop();
            int v3 = this.getPaddingBottom();
            return View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(v), v1 + (v2 + v3)), 0x40000000);
        }
        return v;
    }

    @Override  // android.widget.FrameLayout
    protected void onMeasure(int v, int v1) {
        super.onMeasure(v, this.makeMinHeightSpec(v1));
    }

    public void setItemHorizontalTranslationEnabled(boolean z) {
        BottomNavigationMenuView bottomNavigationMenuView0 = (BottomNavigationMenuView)this.getMenuView();
        if(bottomNavigationMenuView0.isItemHorizontalTranslationEnabled() != z) {
            bottomNavigationMenuView0.setItemHorizontalTranslationEnabled(z);
            this.getPresenter().updateMenuView(false);
        }
    }

    @Deprecated
    public void setOnNavigationItemReselectedListener(OnNavigationItemReselectedListener bottomNavigationView$OnNavigationItemReselectedListener0) {
        this.setOnItemReselectedListener(bottomNavigationView$OnNavigationItemReselectedListener0);
    }

    @Deprecated
    public void setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener bottomNavigationView$OnNavigationItemSelectedListener0) {
        this.setOnItemSelectedListener(bottomNavigationView$OnNavigationItemSelectedListener0);
    }

    private boolean shouldDrawCompatibilityTopDivider() [...] // Inlined contents
}

