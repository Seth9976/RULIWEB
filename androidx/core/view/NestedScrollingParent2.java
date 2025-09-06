package androidx.core.view;

import android.view.View;

public interface NestedScrollingParent2 extends NestedScrollingParent {
    void onNestedPreScroll(View arg1, int arg2, int arg3, int[] arg4, int arg5);

    void onNestedScroll(View arg1, int arg2, int arg3, int arg4, int arg5, int arg6);

    void onNestedScrollAccepted(View arg1, View arg2, int arg3, int arg4);

    boolean onStartNestedScroll(View arg1, View arg2, int arg3, int arg4);

    void onStopNestedScroll(View arg1, int arg2);
}

