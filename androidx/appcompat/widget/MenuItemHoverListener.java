package androidx.appcompat.widget;

import android.view.MenuItem;
import androidx.appcompat.view.menu.MenuBuilder;

public interface MenuItemHoverListener {
    void onItemHoverEnter(MenuBuilder arg1, MenuItem arg2);

    void onItemHoverExit(MenuBuilder arg1, MenuItem arg2);
}

