package com.google.android.material.badge;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.material.R.dimen;
import com.google.android.material.internal.ParcelableSparseArray;
import com.google.android.material.internal.ToolbarUtils;

public class BadgeUtils {
    private static final String LOG_TAG = "BadgeUtils";
    public static final boolean USE_COMPAT_PARENT;

    static {
        BadgeUtils.USE_COMPAT_PARENT = false;
    }

    private static void attachBadgeContentDescription(BadgeDrawable badgeDrawable0, View view0) {
        if(Build.VERSION.SDK_INT >= 29 && ViewCompat.hasAccessibilityDelegate(view0)) {
            ViewCompat.setAccessibilityDelegate(view0, new AccessibilityDelegateCompat(view0.getAccessibilityDelegate()) {
                @Override  // androidx.core.view.AccessibilityDelegateCompat
                public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
                    super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                    accessibilityNodeInfoCompat0.setContentDescription(badgeDrawable0.getContentDescription());
                }
            });
            return;
        }
        ViewCompat.setAccessibilityDelegate(view0, new AccessibilityDelegateCompat() {
            @Override  // androidx.core.view.AccessibilityDelegateCompat
            public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
                super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                accessibilityNodeInfoCompat0.setContentDescription(badgeDrawable0.getContentDescription());
            }
        });
    }

    public static void attachBadgeDrawable(BadgeDrawable badgeDrawable0, View view0) {
        BadgeUtils.attachBadgeDrawable(badgeDrawable0, view0, null);
    }

    public static void attachBadgeDrawable(BadgeDrawable badgeDrawable0, View view0, FrameLayout frameLayout0) {
        BadgeUtils.setBadgeDrawableBounds(badgeDrawable0, view0, frameLayout0);
        if(badgeDrawable0.getCustomBadgeParent() != null) {
            badgeDrawable0.getCustomBadgeParent().setForeground(badgeDrawable0);
            return;
        }
        if(BadgeUtils.USE_COMPAT_PARENT) {
            throw new IllegalArgumentException("Trying to reference null customBadgeParent");
        }
        view0.getOverlay().add(badgeDrawable0);
    }

    public static void attachBadgeDrawable(BadgeDrawable badgeDrawable0, Toolbar toolbar0, int v) {
        BadgeUtils.attachBadgeDrawable(badgeDrawable0, toolbar0, v, null);
    }

    public static void attachBadgeDrawable(BadgeDrawable badgeDrawable0, Toolbar toolbar0, int v, FrameLayout frameLayout0) {
        toolbar0.post(new Runnable() {
            @Override
            public void run() {
                ActionMenuItemView actionMenuItemView0 = ToolbarUtils.getActionMenuItemView(toolbar0, v);
                if(actionMenuItemView0 != null) {
                    Resources resources0 = toolbar0.getResources();
                    BadgeUtils.setToolbarOffset(badgeDrawable0, resources0);
                    BadgeUtils.attachBadgeDrawable(badgeDrawable0, actionMenuItemView0, frameLayout0);
                    BadgeUtils.attachBadgeContentDescription(badgeDrawable0, actionMenuItemView0);
                }
            }
        });
    }

    public static SparseArray createBadgeDrawablesFromSavedStates(Context context0, ParcelableSparseArray parcelableSparseArray0) {
        SparseArray sparseArray0 = new SparseArray(parcelableSparseArray0.size());
        for(int v = 0; v < parcelableSparseArray0.size(); ++v) {
            int v1 = parcelableSparseArray0.keyAt(v);
            State badgeState$State0 = (State)parcelableSparseArray0.valueAt(v);
            sparseArray0.put(v1, (badgeState$State0 == null ? null : BadgeDrawable.createFromSavedState(context0, badgeState$State0)));
        }
        return sparseArray0;
    }

    public static ParcelableSparseArray createParcelableBadgeStates(SparseArray sparseArray0) {
        ParcelableSparseArray parcelableSparseArray0 = new ParcelableSparseArray();
        for(int v = 0; v < sparseArray0.size(); ++v) {
            int v1 = sparseArray0.keyAt(v);
            BadgeDrawable badgeDrawable0 = (BadgeDrawable)sparseArray0.valueAt(v);
            parcelableSparseArray0.put(v1, (badgeDrawable0 == null ? null : badgeDrawable0.getSavedState()));
        }
        return parcelableSparseArray0;
    }

    private static void detachBadgeContentDescription(View view0) {
        if(Build.VERSION.SDK_INT >= 29 && ViewCompat.hasAccessibilityDelegate(view0)) {
            ViewCompat.setAccessibilityDelegate(view0, new AccessibilityDelegateCompat(view0.getAccessibilityDelegate()) {
                @Override  // androidx.core.view.AccessibilityDelegateCompat
                public void onInitializeAccessibilityNodeInfo(View view0, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat0) {
                    super.onInitializeAccessibilityNodeInfo(view0, accessibilityNodeInfoCompat0);
                    accessibilityNodeInfoCompat0.setContentDescription(null);
                }
            });
            return;
        }
        ViewCompat.setAccessibilityDelegate(view0, null);
    }

    public static void detachBadgeDrawable(BadgeDrawable badgeDrawable0, View view0) {
        if(badgeDrawable0 == null) {
            return;
        }
        if(!BadgeUtils.USE_COMPAT_PARENT && badgeDrawable0.getCustomBadgeParent() == null) {
            view0.getOverlay().remove(badgeDrawable0);
            return;
        }
        badgeDrawable0.getCustomBadgeParent().setForeground(null);
    }

    public static void detachBadgeDrawable(BadgeDrawable badgeDrawable0, Toolbar toolbar0, int v) {
        if(badgeDrawable0 == null) {
            return;
        }
        ActionMenuItemView actionMenuItemView0 = ToolbarUtils.getActionMenuItemView(toolbar0, v);
        if(actionMenuItemView0 != null) {
            BadgeUtils.removeToolbarOffset(badgeDrawable0);
            BadgeUtils.detachBadgeDrawable(badgeDrawable0, actionMenuItemView0);
            BadgeUtils.detachBadgeContentDescription(actionMenuItemView0);
            return;
        }
        Log.w("BadgeUtils", "Trying to remove badge from a null menuItemView: " + v);
    }

    static void removeToolbarOffset(BadgeDrawable badgeDrawable0) {
        badgeDrawable0.setAdditionalHorizontalOffset(0);
        badgeDrawable0.setAdditionalVerticalOffset(0);
    }

    public static void setBadgeDrawableBounds(BadgeDrawable badgeDrawable0, View view0, FrameLayout frameLayout0) {
        Rect rect0 = new Rect();
        view0.getDrawingRect(rect0);
        badgeDrawable0.setBounds(rect0);
        badgeDrawable0.updateBadgeCoordinates(view0, frameLayout0);
    }

    static void setToolbarOffset(BadgeDrawable badgeDrawable0, Resources resources0) {
        badgeDrawable0.setAdditionalHorizontalOffset(resources0.getDimensionPixelOffset(dimen.mtrl_badge_toolbar_action_menu_item_horizontal_offset));
        badgeDrawable0.setAdditionalVerticalOffset(resources0.getDimensionPixelOffset(dimen.mtrl_badge_toolbar_action_menu_item_vertical_offset));
    }

    public static void updateBadgeBounds(Rect rect0, float f, float f1, float f2, float f3) {
        rect0.set(((int)(f - f2)), ((int)(f1 - f3)), ((int)(f + f2)), ((int)(f1 + f3)));
    }
}

