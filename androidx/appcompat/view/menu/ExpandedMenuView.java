package androidx.appcompat.view.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.widget.TintTypedArray;

public final class ExpandedMenuView extends ListView implements AdapterView.OnItemClickListener, ItemInvoker, MenuView {
    private static final int[] TINT_ATTRS;
    private int mAnimations;
    private MenuBuilder mMenu;

    static {
        ExpandedMenuView.TINT_ATTRS = new int[]{0x10100D4, 0x1010129};
    }

    public ExpandedMenuView(Context context0, AttributeSet attributeSet0) {
        this(context0, attributeSet0, 0x1010074);
    }

    public ExpandedMenuView(Context context0, AttributeSet attributeSet0, int v) {
        super(context0, attributeSet0);
        this.setOnItemClickListener(this);
        TintTypedArray tintTypedArray0 = TintTypedArray.obtainStyledAttributes(context0, attributeSet0, ExpandedMenuView.TINT_ATTRS, v, 0);
        if(tintTypedArray0.hasValue(0)) {
            this.setBackgroundDrawable(tintTypedArray0.getDrawable(0));
        }
        if(tintTypedArray0.hasValue(1)) {
            this.setDivider(tintTypedArray0.getDrawable(1));
        }
        tintTypedArray0.recycle();
    }

    @Override  // androidx.appcompat.view.menu.MenuView
    public int getWindowAnimations() {
        return this.mAnimations;
    }

    @Override  // androidx.appcompat.view.menu.MenuView
    public void initialize(MenuBuilder menuBuilder0) {
        this.mMenu = menuBuilder0;
    }

    @Override  // androidx.appcompat.view.menu.MenuBuilder$ItemInvoker
    public boolean invokeItem(MenuItemImpl menuItemImpl0) {
        return this.mMenu.performItemAction(menuItemImpl0, 0);
    }

    @Override  // android.widget.ListView
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.setChildrenDrawingCacheEnabled(false);
    }

    @Override  // android.widget.AdapterView$OnItemClickListener
    public void onItemClick(AdapterView adapterView0, View view0, int v, long v1) {
        this.invokeItem(((MenuItemImpl)this.getAdapter().getItem(v)));
    }
}

