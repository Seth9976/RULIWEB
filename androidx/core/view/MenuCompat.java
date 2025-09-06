package androidx.core.view;

import android.os.Build.VERSION;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.ReplaceWith;
import androidx.core.internal.view.SupportMenu;

public final class MenuCompat {
    static class Api28Impl {
        static void setGroupDividerEnabled(Menu menu0, boolean z) {
            menu0.setGroupDividerEnabled(z);
        }
    }

    public static void setGroupDividerEnabled(Menu menu0, boolean z) {
        if(menu0 instanceof SupportMenu) {
            ((SupportMenu)menu0).setGroupDividerEnabled(z);
            return;
        }
        if(Build.VERSION.SDK_INT >= 28) {
            Api28Impl.setGroupDividerEnabled(menu0, z);
        }
    }

    @ReplaceWith(expression = "item.setShowAsAction(actionEnum)")
    @Deprecated
    public static void setShowAsAction(MenuItem menuItem0, int v) {
        menuItem0.setShowAsAction(v);
    }
}

