package androidx.core.widget;

import android.view.View.OnTouchListener;
import android.widget.PopupMenu;

public final class PopupMenuCompat {
    public static View.OnTouchListener getDragToOpenListener(Object object0) {
        return ((PopupMenu)object0).getDragToOpenListener();
    }
}

