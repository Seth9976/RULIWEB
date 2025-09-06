package androidx.appcompat.view;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import androidx.appcompat.R.attr;
import androidx.appcompat.R.bool;
import androidx.appcompat.R.dimen;
import androidx.appcompat.R.styleable;

public class ActionBarPolicy {
    private Context mContext;

    private ActionBarPolicy(Context context0) {
        this.mContext = context0;
    }

    public boolean enableHomeButtonByDefault() {
        return false;
    }

    public static ActionBarPolicy get(Context context0) {
        return new ActionBarPolicy(context0);
    }

    public int getEmbeddedMenuWidthLimit() {
        return this.mContext.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public int getMaxActionButtons() {
        Configuration configuration0 = this.mContext.getResources().getConfiguration();
        int v = configuration0.screenWidthDp;
        int v1 = configuration0.screenHeightDp;
        if(configuration0.smallestScreenWidthDp <= 600 && v <= 600 && (v <= 960 || v1 <= 720) && (v <= 720 || v1 <= 960)) {
            if(v < 500 && (v <= 640 || v1 <= 480) && (v <= 480 || v1 <= 640)) {
                return v < 360 ? 2 : 3;
            }
            return 4;
        }
        return 5;
    }

    public int getStackedTabMaxWidth() {
        return this.mContext.getResources().getDimensionPixelSize(dimen.abc_action_bar_stacked_tab_max_width);
    }

    public int getTabContainerHeight() {
        TypedArray typedArray0 = this.mContext.obtainStyledAttributes(null, styleable.ActionBar, attr.actionBarStyle, 0);
        int v = typedArray0.getLayoutDimension(styleable.ActionBar_height, 0);
        Resources resources0 = this.mContext.getResources();
        if(!this.hasEmbeddedTabs()) {
            v = Math.min(v, resources0.getDimensionPixelSize(dimen.abc_action_bar_stacked_max_height));
        }
        typedArray0.recycle();
        return v;
    }

    public boolean hasEmbeddedTabs() {
        return this.mContext.getResources().getBoolean(bool.abc_action_bar_embed_tabs);
    }

    public boolean showsOverflowMenuButton() [...] // Inlined contents
}

