package androidx.transition;

import android.view.View;
import android.view.ViewGroup;

interface GhostView {
    void reserveEndViewTransition(ViewGroup arg1, View arg2);

    void setVisibility(int arg1);
}

