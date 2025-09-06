package androidx.core.view;

public interface NestedScrollingChild2 extends NestedScrollingChild {
    boolean dispatchNestedPreScroll(int arg1, int arg2, int[] arg3, int[] arg4, int arg5);

    boolean dispatchNestedScroll(int arg1, int arg2, int arg3, int arg4, int[] arg5, int arg6);

    boolean hasNestedScrollingParent(int arg1);

    boolean startNestedScroll(int arg1, int arg2);

    void stopNestedScroll(int arg1);
}

