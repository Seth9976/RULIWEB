package androidx.core.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.ReplaceWith;
import androidx.core.internal.view.SupportMenuItem;

public final class MenuItemCompat {
    static class Api26Impl {
        static int getAlphabeticModifiers(MenuItem menuItem0) {
            return menuItem0.getAlphabeticModifiers();
        }

        static CharSequence getContentDescription(MenuItem menuItem0) {
            return menuItem0.getContentDescription();
        }

        static ColorStateList getIconTintList(MenuItem menuItem0) {
            return menuItem0.getIconTintList();
        }

        static PorterDuff.Mode getIconTintMode(MenuItem menuItem0) {
            return menuItem0.getIconTintMode();
        }

        static int getNumericModifiers(MenuItem menuItem0) {
            return menuItem0.getNumericModifiers();
        }

        static CharSequence getTooltipText(MenuItem menuItem0) {
            return menuItem0.getTooltipText();
        }

        static MenuItem setAlphabeticShortcut(MenuItem menuItem0, char c, int v) {
            return menuItem0.setAlphabeticShortcut(c, v);
        }

        static MenuItem setContentDescription(MenuItem menuItem0, CharSequence charSequence0) {
            return menuItem0.setContentDescription(charSequence0);
        }

        static MenuItem setIconTintList(MenuItem menuItem0, ColorStateList colorStateList0) {
            return menuItem0.setIconTintList(colorStateList0);
        }

        static MenuItem setIconTintMode(MenuItem menuItem0, PorterDuff.Mode porterDuff$Mode0) {
            return menuItem0.setIconTintMode(porterDuff$Mode0);
        }

        static MenuItem setNumericShortcut(MenuItem menuItem0, char c, int v) {
            return menuItem0.setNumericShortcut(c, v);
        }

        static MenuItem setShortcut(MenuItem menuItem0, char c, char c1, int v, int v1) {
            return menuItem0.setShortcut(c, c1, v, v1);
        }

        static MenuItem setTooltipText(MenuItem menuItem0, CharSequence charSequence0) {
            return menuItem0.setTooltipText(charSequence0);
        }
    }

    @Deprecated
    public interface OnActionExpandListener {
        boolean onMenuItemActionCollapse(MenuItem arg1);

        boolean onMenuItemActionExpand(MenuItem arg1);
    }

    @Deprecated
    public static final int SHOW_AS_ACTION_ALWAYS = 2;
    @Deprecated
    public static final int SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW = 8;
    @Deprecated
    public static final int SHOW_AS_ACTION_IF_ROOM = 1;
    @Deprecated
    public static final int SHOW_AS_ACTION_NEVER = 0;
    @Deprecated
    public static final int SHOW_AS_ACTION_WITH_TEXT = 4;
    private static final String TAG = "MenuItemCompat";

    @ReplaceWith(expression = "item.collapseActionView()")
    @Deprecated
    public static boolean collapseActionView(MenuItem menuItem0) {
        return menuItem0.collapseActionView();
    }

    @ReplaceWith(expression = "item.expandActionView()")
    @Deprecated
    public static boolean expandActionView(MenuItem menuItem0) {
        return menuItem0.expandActionView();
    }

    public static ActionProvider getActionProvider(MenuItem menuItem0) {
        if(menuItem0 instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem0).getSupportActionProvider();
        }
        Log.w("MenuItemCompat", "getActionProvider: item does not implement SupportMenuItem; returning null");
        return null;
    }

    @ReplaceWith(expression = "item.getActionView()")
    @Deprecated
    public static View getActionView(MenuItem menuItem0) {
        return menuItem0.getActionView();
    }

    public static int getAlphabeticModifiers(MenuItem menuItem0) {
        if(menuItem0 instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem0).getAlphabeticModifiers();
        }
        return Build.VERSION.SDK_INT < 26 ? 0 : Api26Impl.getAlphabeticModifiers(menuItem0);
    }

    public static CharSequence getContentDescription(MenuItem menuItem0) {
        if(menuItem0 instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem0).getContentDescription();
        }
        return Build.VERSION.SDK_INT < 26 ? null : Api26Impl.getContentDescription(menuItem0);
    }

    public static ColorStateList getIconTintList(MenuItem menuItem0) {
        if(menuItem0 instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem0).getIconTintList();
        }
        return Build.VERSION.SDK_INT < 26 ? null : Api26Impl.getIconTintList(menuItem0);
    }

    public static PorterDuff.Mode getIconTintMode(MenuItem menuItem0) {
        if(menuItem0 instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem0).getIconTintMode();
        }
        return Build.VERSION.SDK_INT < 26 ? null : Api26Impl.getIconTintMode(menuItem0);
    }

    public static int getNumericModifiers(MenuItem menuItem0) {
        if(menuItem0 instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem0).getNumericModifiers();
        }
        return Build.VERSION.SDK_INT < 26 ? 0 : Api26Impl.getNumericModifiers(menuItem0);
    }

    public static CharSequence getTooltipText(MenuItem menuItem0) {
        if(menuItem0 instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem0).getTooltipText();
        }
        return Build.VERSION.SDK_INT < 26 ? null : Api26Impl.getTooltipText(menuItem0);
    }

    @ReplaceWith(expression = "item.isActionViewExpanded()")
    @Deprecated
    public static boolean isActionViewExpanded(MenuItem menuItem0) {
        return menuItem0.isActionViewExpanded();
    }

    public static MenuItem setActionProvider(MenuItem menuItem0, ActionProvider actionProvider0) {
        if(menuItem0 instanceof SupportMenuItem) {
            return ((SupportMenuItem)menuItem0).setSupportActionProvider(actionProvider0);
        }
        Log.w("MenuItemCompat", "setActionProvider: item does not implement SupportMenuItem; ignoring");
        return menuItem0;
    }

    @ReplaceWith(expression = "item.setActionView(resId)")
    @Deprecated
    public static MenuItem setActionView(MenuItem menuItem0, int v) {
        return menuItem0.setActionView(v);
    }

    @ReplaceWith(expression = "item.setActionView(view)")
    @Deprecated
    public static MenuItem setActionView(MenuItem menuItem0, View view0) {
        return menuItem0.setActionView(view0);
    }

    public static void setAlphabeticShortcut(MenuItem menuItem0, char c, int v) {
        if(menuItem0 instanceof SupportMenuItem) {
            ((SupportMenuItem)menuItem0).setAlphabeticShortcut(c, v);
            return;
        }
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.setAlphabeticShortcut(menuItem0, c, v);
        }
    }

    public static void setContentDescription(MenuItem menuItem0, CharSequence charSequence0) {
        if(menuItem0 instanceof SupportMenuItem) {
            ((SupportMenuItem)menuItem0).setContentDescription(charSequence0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.setContentDescription(menuItem0, charSequence0);
        }
    }

    public static void setIconTintList(MenuItem menuItem0, ColorStateList colorStateList0) {
        if(menuItem0 instanceof SupportMenuItem) {
            ((SupportMenuItem)menuItem0).setIconTintList(colorStateList0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.setIconTintList(menuItem0, colorStateList0);
        }
    }

    public static void setIconTintMode(MenuItem menuItem0, PorterDuff.Mode porterDuff$Mode0) {
        if(menuItem0 instanceof SupportMenuItem) {
            ((SupportMenuItem)menuItem0).setIconTintMode(porterDuff$Mode0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.setIconTintMode(menuItem0, porterDuff$Mode0);
        }
    }

    public static void setNumericShortcut(MenuItem menuItem0, char c, int v) {
        if(menuItem0 instanceof SupportMenuItem) {
            ((SupportMenuItem)menuItem0).setNumericShortcut(c, v);
            return;
        }
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.setNumericShortcut(menuItem0, c, v);
        }
    }

    @Deprecated
    public static MenuItem setOnActionExpandListener(MenuItem menuItem0, OnActionExpandListener menuItemCompat$OnActionExpandListener0) {
        return menuItem0.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override  // android.view.MenuItem$OnActionExpandListener
            public boolean onMenuItemActionCollapse(MenuItem menuItem0) {
                return menuItemCompat$OnActionExpandListener0.onMenuItemActionCollapse(menuItem0);
            }

            @Override  // android.view.MenuItem$OnActionExpandListener
            public boolean onMenuItemActionExpand(MenuItem menuItem0) {
                return menuItemCompat$OnActionExpandListener0.onMenuItemActionExpand(menuItem0);
            }
        });
    }

    public static void setShortcut(MenuItem menuItem0, char c, char c1, int v, int v1) {
        if(menuItem0 instanceof SupportMenuItem) {
            ((SupportMenuItem)menuItem0).setShortcut(c, c1, v, v1);
            return;
        }
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.setShortcut(menuItem0, c, c1, v, v1);
        }
    }

    @ReplaceWith(expression = "item.setShowAsAction(actionEnum)")
    @Deprecated
    public static void setShowAsAction(MenuItem menuItem0, int v) {
        menuItem0.setShowAsAction(v);
    }

    public static void setTooltipText(MenuItem menuItem0, CharSequence charSequence0) {
        if(menuItem0 instanceof SupportMenuItem) {
            ((SupportMenuItem)menuItem0).setTooltipText(charSequence0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.setTooltipText(menuItem0, charSequence0);
        }
    }
}

