package androidx.recyclerview.widget;

interface ThreadUtil {
    public interface BackgroundCallback {
        void loadTile(int arg1, int arg2);

        void recycleTile(Tile arg1);

        void refresh(int arg1);

        void updateRange(int arg1, int arg2, int arg3, int arg4, int arg5);
    }

    public interface MainThreadCallback {
        void addTile(int arg1, Tile arg2);

        void removeTile(int arg1, int arg2);

        void updateItemCount(int arg1, int arg2);
    }

    BackgroundCallback getBackgroundProxy(BackgroundCallback arg1);

    MainThreadCallback getMainThreadProxy(MainThreadCallback arg1);
}

