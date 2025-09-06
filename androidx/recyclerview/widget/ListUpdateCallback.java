package androidx.recyclerview.widget;

public interface ListUpdateCallback {
    void onChanged(int arg1, int arg2, Object arg3);

    void onInserted(int arg1, int arg2);

    void onMoved(int arg1, int arg2);

    void onRemoved(int arg1, int arg2);
}

