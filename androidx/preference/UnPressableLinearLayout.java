package androidx.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class UnPressableLinearLayout extends LinearLayout {
    public UnPressableLinearLayout(Context context0) {
        this(context0, null);
    }

    public UnPressableLinearLayout(Context context0, AttributeSet attributeSet0) {
        super(context0, attributeSet0);
    }

    @Override  // android.view.ViewGroup
    protected void dispatchSetPressed(boolean z) {
    }
}

