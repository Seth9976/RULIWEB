package androidx.appcompat.widget;

import android.os.Build.VERSION;
import android.view.View;

public class TooltipCompat {
    static class Api26Impl {
        static void setTooltipText(View view0, CharSequence charSequence0) {
            view0.setTooltipText(charSequence0);
        }
    }

    public static void setTooltipText(View view0, CharSequence charSequence0) {
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.setTooltipText(view0, charSequence0);
            return;
        }
        TooltipCompatHandler.setTooltipText(view0, charSequence0);
    }
}

