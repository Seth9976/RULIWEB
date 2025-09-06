package com.google.android.material.internal;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ToolbarUtils {
    private static final Comparator VIEW_TOP_COMPARATOR;

    static {
        ToolbarUtils.VIEW_TOP_COMPARATOR = new Comparator() {
            public int compare(View view0, View view1) {
                return view0.getTop() - view1.getTop();
            }

            @Override
            public int compare(Object object0, Object object1) {
                return this.compare(((View)object0), ((View)object1));
            }
        };
    }

    public static ActionMenuItemView getActionMenuItemView(Toolbar toolbar0, int v) {
        ActionMenuView actionMenuView0 = ToolbarUtils.getActionMenuView(toolbar0);
        if(actionMenuView0 != null) {
            for(int v1 = 0; v1 < actionMenuView0.getChildCount(); ++v1) {
                View view0 = actionMenuView0.getChildAt(v1);
                if(view0 instanceof ActionMenuItemView && ((ActionMenuItemView)view0).getItemData().getItemId() == v) {
                    return (ActionMenuItemView)view0;
                }
            }
        }
        return null;
    }

    public static ActionMenuView getActionMenuView(Toolbar toolbar0) {
        for(int v = 0; v < toolbar0.getChildCount(); ++v) {
            View view0 = toolbar0.getChildAt(v);
            if(view0 instanceof ActionMenuView) {
                return (ActionMenuView)view0;
            }
        }
        return null;
    }

    private static ImageView getImageView(Toolbar toolbar0, Drawable drawable0) {
        if(drawable0 == null) {
            return null;
        }
        for(int v = 0; v < toolbar0.getChildCount(); ++v) {
            View view0 = toolbar0.getChildAt(v);
            if(view0 instanceof ImageView) {
                Drawable drawable1 = ((ImageView)view0).getDrawable();
                if(drawable1 != null && drawable1.getConstantState() != null && drawable1.getConstantState().equals(drawable0.getConstantState())) {
                    return (ImageView)view0;
                }
            }
        }
        return null;
    }

    public static ImageView getLogoImageView(Toolbar toolbar0) {
        return ToolbarUtils.getImageView(toolbar0, toolbar0.getLogo());
    }

    public static ImageButton getNavigationIconButton(Toolbar toolbar0) {
        Drawable drawable0 = toolbar0.getNavigationIcon();
        if(drawable0 == null) {
            return null;
        }
        for(int v = 0; v < toolbar0.getChildCount(); ++v) {
            View view0 = toolbar0.getChildAt(v);
            if(view0 instanceof ImageButton && ((ImageButton)view0).getDrawable() == drawable0) {
                return (ImageButton)view0;
            }
        }
        return null;
    }

    public static View getSecondaryActionMenuItemView(Toolbar toolbar0) {
        ActionMenuView actionMenuView0 = ToolbarUtils.getActionMenuView(toolbar0);
        return actionMenuView0 == null || actionMenuView0.getChildCount() <= 1 ? null : actionMenuView0.getChildAt(0);
    }

    public static TextView getSubtitleTextView(Toolbar toolbar0) {
        List list0 = ToolbarUtils.getTextViewsWithText(toolbar0, toolbar0.getSubtitle());
        return list0.isEmpty() ? null : ((TextView)Collections.max(list0, ToolbarUtils.VIEW_TOP_COMPARATOR));
    }

    private static List getTextViewsWithText(Toolbar toolbar0, CharSequence charSequence0) {
        List list0 = new ArrayList();
        for(int v = 0; v < toolbar0.getChildCount(); ++v) {
            View view0 = toolbar0.getChildAt(v);
            if(view0 instanceof TextView && TextUtils.equals(((TextView)view0).getText(), charSequence0)) {
                list0.add(((TextView)view0));
            }
        }
        return list0;
    }

    public static TextView getTitleTextView(Toolbar toolbar0) {
        List list0 = ToolbarUtils.getTextViewsWithText(toolbar0, toolbar0.getTitle());
        return list0.isEmpty() ? null : ((TextView)Collections.min(list0, ToolbarUtils.VIEW_TOP_COMPARATOR));
    }
}

