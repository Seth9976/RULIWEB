package androidx.recyclerview.widget;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

class MessageThreadUtil implements ThreadUtil {
    static class MessageQueue {
        private final Object mLock;
        private SyncQueueItem mRoot;

        MessageQueue() {
            this.mLock = new Object();
        }

        SyncQueueItem next() {
            SyncQueueItem messageThreadUtil$SyncQueueItem0;
            synchronized(this.mLock) {
                messageThreadUtil$SyncQueueItem0 = this.mRoot;
                if(messageThreadUtil$SyncQueueItem0 == null) {
                    return null;
                }
                this.mRoot = messageThreadUtil$SyncQueueItem0.next;
            }
            return messageThreadUtil$SyncQueueItem0;
        }

        void removeMessages(int v) {
            synchronized(this.mLock) {
                while(this.mRoot != null && this.mRoot.what == v) {
                    SyncQueueItem messageThreadUtil$SyncQueueItem0 = this.mRoot;
                    this.mRoot = messageThreadUtil$SyncQueueItem0.next;
                    messageThreadUtil$SyncQueueItem0.recycle();
                }
                SyncQueueItem messageThreadUtil$SyncQueueItem1 = this.mRoot;
                if(messageThreadUtil$SyncQueueItem1 != null) {
                    for(SyncQueueItem messageThreadUtil$SyncQueueItem2 = messageThreadUtil$SyncQueueItem1.next; messageThreadUtil$SyncQueueItem2 != null; messageThreadUtil$SyncQueueItem2 = messageThreadUtil$SyncQueueItem3) {
                        SyncQueueItem messageThreadUtil$SyncQueueItem3 = messageThreadUtil$SyncQueueItem2.next;
                        if(messageThreadUtil$SyncQueueItem2.what == v) {
                            messageThreadUtil$SyncQueueItem1.next = messageThreadUtil$SyncQueueItem3;
                            messageThreadUtil$SyncQueueItem2.recycle();
                        }
                        else {
                            messageThreadUtil$SyncQueueItem1 = messageThreadUtil$SyncQueueItem2;
                        }
                    }
                }
            }
        }

        void sendMessage(SyncQueueItem messageThreadUtil$SyncQueueItem0) {
            synchronized(this.mLock) {
                SyncQueueItem messageThreadUtil$SyncQueueItem1 = this.mRoot;
                if(messageThreadUtil$SyncQueueItem1 == null) {
                    this.mRoot = messageThreadUtil$SyncQueueItem0;
                    return;
                }
                while(messageThreadUtil$SyncQueueItem1.next != null) {
                    messageThreadUtil$SyncQueueItem1 = messageThreadUtil$SyncQueueItem1.next;
                }
                messageThreadUtil$SyncQueueItem1.next = messageThreadUtil$SyncQueueItem0;
            }
        }

        void sendMessageAtFrontOfQueue(SyncQueueItem messageThreadUtil$SyncQueueItem0) {
            synchronized(this.mLock) {
                messageThreadUtil$SyncQueueItem0.next = this.mRoot;
                this.mRoot = messageThreadUtil$SyncQueueItem0;
            }
        }
    }

    static class SyncQueueItem {
        public int arg1;
        public int arg2;
        public int arg3;
        public int arg4;
        public int arg5;
        public Object data;
        SyncQueueItem next;
        private static SyncQueueItem sPool;
        private static final Object sPoolLock;
        public int what;

        static {
            SyncQueueItem.sPoolLock = new Object();
        }

        static SyncQueueItem obtainMessage(int v, int v1, int v2) {
            return SyncQueueItem.obtainMessage(v, v1, v2, 0, 0, 0, null);
        }

        static SyncQueueItem obtainMessage(int v, int v1, int v2, int v3, int v4, int v5, Object object0) {
            synchronized(SyncQueueItem.sPoolLock) {
                SyncQueueItem messageThreadUtil$SyncQueueItem0 = SyncQueueItem.sPool;
                if(messageThreadUtil$SyncQueueItem0 == null) {
                    messageThreadUtil$SyncQueueItem0 = new SyncQueueItem();
                }
                else {
                    SyncQueueItem.sPool = messageThreadUtil$SyncQueueItem0.next;
                    messageThreadUtil$SyncQueueItem0.next = null;
                }
                messageThreadUtil$SyncQueueItem0.what = v;
                messageThreadUtil$SyncQueueItem0.arg1 = v1;
                messageThreadUtil$SyncQueueItem0.arg2 = v2;
                messageThreadUtil$SyncQueueItem0.arg3 = v3;
                messageThreadUtil$SyncQueueItem0.arg4 = v4;
                messageThreadUtil$SyncQueueItem0.arg5 = v5;
                messageThreadUtil$SyncQueueItem0.data = object0;
                return messageThreadUtil$SyncQueueItem0;
            }
        }

        static SyncQueueItem obtainMessage(int v, int v1, Object object0) {
            return SyncQueueItem.obtainMessage(v, v1, 0, 0, 0, 0, object0);
        }

        void recycle() {
            this.next = null;
            this.arg5 = 0;
            this.arg4 = 0;
            this.arg3 = 0;
            this.arg2 = 0;
            this.arg1 = 0;
            this.what = 0;
            this.data = null;
            synchronized(SyncQueueItem.sPoolLock) {
                SyncQueueItem messageThreadUtil$SyncQueueItem0 = SyncQueueItem.sPool;
                if(messageThreadUtil$SyncQueueItem0 != null) {
                    this.next = messageThreadUtil$SyncQueueItem0;
                }
                SyncQueueItem.sPool = this;
            }
        }
    }

    @Override  // androidx.recyclerview.widget.ThreadUtil
    public BackgroundCallback getBackgroundProxy(BackgroundCallback threadUtil$BackgroundCallback0) {
        return new BackgroundCallback() {
            static final int LOAD_TILE = 3;
            static final int RECYCLE_TILE = 4;
            static final int REFRESH = 1;
            static final int UPDATE_RANGE = 2;
            private Runnable mBackgroundRunnable;
            AtomicBoolean mBackgroundRunning;
            private final Executor mExecutor;
            final MessageQueue mQueue;

            {
                BackgroundCallback threadUtil$BackgroundCallback0 = threadUtil$BackgroundCallback0;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                this.mQueue = new MessageQueue();
                this.mExecutor = AsyncTask.THREAD_POOL_EXECUTOR;
                this.mBackgroundRunning = new AtomicBoolean(false);
                this.mBackgroundRunnable = new Runnable() {
                    @Override
                    public void run() {
                        SyncQueueItem messageThreadUtil$SyncQueueItem0;
                        while((messageThreadUtil$SyncQueueItem0 = androidx.recyclerview.widget.MessageThreadUtil.2.this.mQueue.next()) != null) {
                            int v = messageThreadUtil$SyncQueueItem0.what;
                            if(v == 1) {
                                androidx.recyclerview.widget.MessageThreadUtil.2.this.mQueue.removeMessages(1);
                                androidx.recyclerview.widget.MessageThreadUtil.2.this.val$callback.refresh(messageThreadUtil$SyncQueueItem0.arg1);
                            }
                            else {
                                switch(v) {
                                    case 2: {
                                        androidx.recyclerview.widget.MessageThreadUtil.2.this.mQueue.removeMessages(2);
                                        androidx.recyclerview.widget.MessageThreadUtil.2.this.mQueue.removeMessages(3);
                                        androidx.recyclerview.widget.MessageThreadUtil.2.this.val$callback.updateRange(messageThreadUtil$SyncQueueItem0.arg1, messageThreadUtil$SyncQueueItem0.arg2, messageThreadUtil$SyncQueueItem0.arg3, messageThreadUtil$SyncQueueItem0.arg4, messageThreadUtil$SyncQueueItem0.arg5);
                                        break;
                                    }
                                    case 3: {
                                        androidx.recyclerview.widget.MessageThreadUtil.2.this.val$callback.loadTile(messageThreadUtil$SyncQueueItem0.arg1, messageThreadUtil$SyncQueueItem0.arg2);
                                        break;
                                    }
                                    default: {
                                        if(v == 4) {
                                            androidx.recyclerview.widget.MessageThreadUtil.2.this.val$callback.recycleTile(((Tile)messageThreadUtil$SyncQueueItem0.data));
                                        }
                                        else {
                                            Log.e("ThreadUtil", "Unsupported message, what=" + messageThreadUtil$SyncQueueItem0.what);
                                        }
                                        break;
                                    }
                                }
                            }
                        }
                        androidx.recyclerview.widget.MessageThreadUtil.2.this.mBackgroundRunning.set(false);
                    }
                };
            }

            @Override  // androidx.recyclerview.widget.ThreadUtil$BackgroundCallback
            public void loadTile(int v, int v1) {
                this.sendMessage(SyncQueueItem.obtainMessage(3, v, v1));
            }

            private void maybeExecuteBackgroundRunnable() {
                if(this.mBackgroundRunning.compareAndSet(false, true)) {
                    this.mExecutor.execute(this.mBackgroundRunnable);
                }
            }

            @Override  // androidx.recyclerview.widget.ThreadUtil$BackgroundCallback
            public void recycleTile(Tile tileList$Tile0) {
                this.sendMessage(SyncQueueItem.obtainMessage(4, 0, tileList$Tile0));
            }

            @Override  // androidx.recyclerview.widget.ThreadUtil$BackgroundCallback
            public void refresh(int v) {
                this.sendMessageAtFrontOfQueue(SyncQueueItem.obtainMessage(1, v, null));
            }

            private void sendMessage(SyncQueueItem messageThreadUtil$SyncQueueItem0) {
                this.mQueue.sendMessage(messageThreadUtil$SyncQueueItem0);
                this.maybeExecuteBackgroundRunnable();
            }

            private void sendMessageAtFrontOfQueue(SyncQueueItem messageThreadUtil$SyncQueueItem0) {
                this.mQueue.sendMessageAtFrontOfQueue(messageThreadUtil$SyncQueueItem0);
                this.maybeExecuteBackgroundRunnable();
            }

            @Override  // androidx.recyclerview.widget.ThreadUtil$BackgroundCallback
            public void updateRange(int v, int v1, int v2, int v3, int v4) {
                this.sendMessageAtFrontOfQueue(SyncQueueItem.obtainMessage(2, v, v1, v2, v3, v4, null));
            }
        };
    }

    @Override  // androidx.recyclerview.widget.ThreadUtil
    public MainThreadCallback getMainThreadProxy(MainThreadCallback threadUtil$MainThreadCallback0) {
        return new MainThreadCallback() {
            static final int ADD_TILE = 2;
            static final int REMOVE_TILE = 3;
            static final int UPDATE_ITEM_COUNT = 1;
            private final Handler mMainThreadHandler;
            private Runnable mMainThreadRunnable;
            final MessageQueue mQueue;

            {
                MainThreadCallback threadUtil$MainThreadCallback0 = threadUtil$MainThreadCallback0;  // 捕获的参数 （可能与外部方法变量命名冲突；考虑手动重命名）
                this.mQueue = new MessageQueue();
                this.mMainThreadHandler = new Handler(Looper.getMainLooper());
                this.mMainThreadRunnable = new Runnable() {
                    @Override
                    public void run() {
                        for(SyncQueueItem messageThreadUtil$SyncQueueItem0 = androidx.recyclerview.widget.MessageThreadUtil.1.this.mQueue.next(); messageThreadUtil$SyncQueueItem0 != null; messageThreadUtil$SyncQueueItem0 = androidx.recyclerview.widget.MessageThreadUtil.1.this.mQueue.next()) {
                            switch(messageThreadUtil$SyncQueueItem0.what) {
                                case 1: {
                                    androidx.recyclerview.widget.MessageThreadUtil.1.this.val$callback.updateItemCount(messageThreadUtil$SyncQueueItem0.arg1, messageThreadUtil$SyncQueueItem0.arg2);
                                    break;
                                }
                                case 2: {
                                    androidx.recyclerview.widget.MessageThreadUtil.1.this.val$callback.addTile(messageThreadUtil$SyncQueueItem0.arg1, ((Tile)messageThreadUtil$SyncQueueItem0.data));
                                    break;
                                }
                                case 3: {
                                    androidx.recyclerview.widget.MessageThreadUtil.1.this.val$callback.removeTile(messageThreadUtil$SyncQueueItem0.arg1, messageThreadUtil$SyncQueueItem0.arg2);
                                    break;
                                }
                                default: {
                                    Log.e("ThreadUtil", "Unsupported message, what=" + messageThreadUtil$SyncQueueItem0.what);
                                }
                            }
                        }
                    }
                };
            }

            @Override  // androidx.recyclerview.widget.ThreadUtil$MainThreadCallback
            public void addTile(int v, Tile tileList$Tile0) {
                this.sendMessage(SyncQueueItem.obtainMessage(2, v, tileList$Tile0));
            }

            @Override  // androidx.recyclerview.widget.ThreadUtil$MainThreadCallback
            public void removeTile(int v, int v1) {
                this.sendMessage(SyncQueueItem.obtainMessage(3, v, v1));
            }

            private void sendMessage(SyncQueueItem messageThreadUtil$SyncQueueItem0) {
                this.mQueue.sendMessage(messageThreadUtil$SyncQueueItem0);
                this.mMainThreadHandler.post(this.mMainThreadRunnable);
            }

            @Override  // androidx.recyclerview.widget.ThreadUtil$MainThreadCallback
            public void updateItemCount(int v, int v1) {
                this.sendMessage(SyncQueueItem.obtainMessage(1, v, v1));
            }
        };
    }
}

