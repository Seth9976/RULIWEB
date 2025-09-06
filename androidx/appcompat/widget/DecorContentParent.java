package androidx.appcompat.widget;

import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.Menu;
import android.view.Window.Callback;
import androidx.appcompat.view.menu.MenuPresenter.Callback;

public interface DecorContentParent {
    boolean canShowOverflowMenu();

    void dismissPopups();

    CharSequence getTitle();

    boolean hasIcon();

    boolean hasLogo();

    boolean hideOverflowMenu();

    void initFeature(int arg1);

    boolean isOverflowMenuShowPending();

    boolean isOverflowMenuShowing();

    void restoreToolbarHierarchyState(SparseArray arg1);

    void saveToolbarHierarchyState(SparseArray arg1);

    void setIcon(int arg1);

    void setIcon(Drawable arg1);

    void setLogo(int arg1);

    void setMenu(Menu arg1, Callback arg2);

    void setMenuPrepared();

    void setUiOptions(int arg1);

    void setWindowCallback(Window.Callback arg1);

    void setWindowTitle(CharSequence arg1);

    boolean showOverflowMenu();
}

