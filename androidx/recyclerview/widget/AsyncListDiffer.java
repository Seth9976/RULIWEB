package androidx.recyclerview.widget;

import android.os.Handler;
import android.os.Looper;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

public class AsyncListDiffer {
    public interface ListListener {
        void onCurrentListChanged(List arg1, List arg2);
    }

    static class MainThreadExecutor implements Executor {
        final Handler mHandler;

        MainThreadExecutor() {
            this.mHandler = new Handler(Looper.getMainLooper());
        }

        @Override
        public void execute(Runnable runnable0) {
            this.mHandler.post(runnable0);
        }
    }

    final AsyncDifferConfig mConfig;
    private List mList;
    private final List mListeners;
    Executor mMainThreadExecutor;
    int mMaxScheduledGeneration;
    private List mReadOnlyList;
    private final ListUpdateCallback mUpdateCallback;
    private static final Executor sMainThreadExecutor;

    static {
        AsyncListDiffer.sMainThreadExecutor = new MainThreadExecutor();
    }

    public AsyncListDiffer(ListUpdateCallback listUpdateCallback0, AsyncDifferConfig asyncDifferConfig0) {
        this.mListeners = new CopyOnWriteArrayList();
        this.mReadOnlyList = Collections.EMPTY_LIST;
        this.mUpdateCallback = listUpdateCallback0;
        this.mConfig = asyncDifferConfig0;
        if(asyncDifferConfig0.getMainThreadExecutor() != null) {
            this.mMainThreadExecutor = asyncDifferConfig0.getMainThreadExecutor();
            return;
        }
        this.mMainThreadExecutor = AsyncListDiffer.sMainThreadExecutor;
    }

    public AsyncListDiffer(Adapter recyclerView$Adapter0, ItemCallback diffUtil$ItemCallback0) {
        this(new AdapterListUpdateCallback(recyclerView$Adapter0), new Builder(diffUtil$ItemCallback0).build());
    }

    public void addListListener(ListListener asyncListDiffer$ListListener0) {
        this.mListeners.add(asyncListDiffer$ListListener0);
    }

    public List getCurrentList() {
        return this.mReadOnlyList;
    }

    void latchList(List list0, DiffResult diffUtil$DiffResult0, Runnable runnable0) {
        List list1 = this.mReadOnlyList;
        this.mList = list0;
        this.mReadOnlyList = Collections.unmodifiableList(list0);
        diffUtil$DiffResult0.dispatchUpdatesTo(this.mUpdateCallback);
        this.onCurrentListChanged(list1, runnable0);
    }

    private void onCurrentListChanged(List list0, Runnable runnable0) {
        for(Object object0: this.mListeners) {
            ((ListListener)object0).onCurrentListChanged(list0, this.mReadOnlyList);
        }
        if(runnable0 != null) {
            runnable0.run();
        }
    }

    public void removeListListener(ListListener asyncListDiffer$ListListener0) {
        this.mListeners.remove(asyncListDiffer$ListListener0);
    }

    public void submitList(List list0) {
        this.submitList(list0, null);
    }

    public void submitList(List list0, Runnable runnable0) {
        int v = this.mMaxScheduledGeneration + 1;
        this.mMaxScheduledGeneration = v;
        List list1 = this.mList;
        if(list0 == list1) {
            if(runnable0 != null) {
                runnable0.run();
            }
            return;
        }
        List list2 = this.mReadOnlyList;
        if(list0 == null) {
            this.mList = null;
            this.mReadOnlyList = Collections.EMPTY_LIST;
            this.mUpdateCallback.onRemoved(0, list1.size());
            this.onCurrentListChanged(list2, runnable0);
            return;
        }
        if(list1 == null) {
            this.mList = list0;
            this.mReadOnlyList = Collections.unmodifiableList(list0);
            this.mUpdateCallback.onInserted(0, list0.size());
            this.onCurrentListChanged(list2, runnable0);
            return;
        }
        this.mConfig.getBackgroundThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                DiffResult diffUtil$DiffResult0 = DiffUtil.calculateDiff(new Callback() {
                    @Override  // androidx.recyclerview.widget.DiffUtil$Callback
                    public boolean areContentsTheSame(int v, int v1) {
                        Object object0 = androidx.recyclerview.widget.AsyncListDiffer.1.this.val$oldList.get(v);
                        Object object1 = androidx.recyclerview.widget.AsyncListDiffer.1.this.val$newList.get(v1);
                        if(object0 != null && object1 != null) {
                            return AsyncListDiffer.this.mConfig.getDiffCallback().areContentsTheSame(object0, object1);
                        }
                        if(object0 != null || object1 != null) {
                            throw new AssertionError();
                        }
                        return true;
                    }

                    @Override  // androidx.recyclerview.widget.DiffUtil$Callback
                    public boolean areItemsTheSame(int v, int v1) {
                        Object object0 = androidx.recyclerview.widget.AsyncListDiffer.1.this.val$oldList.get(v);
                        Object object1 = androidx.recyclerview.widget.AsyncListDiffer.1.this.val$newList.get(v1);
                        return object0 == null || object1 == null ? object0 == null && object1 == null : AsyncListDiffer.this.mConfig.getDiffCallback().areItemsTheSame(object0, object1);
                    }

                    @Override  // androidx.recyclerview.widget.DiffUtil$Callback
                    public Object getChangePayload(int v, int v1) {
                        Object object0 = androidx.recyclerview.widget.AsyncListDiffer.1.this.val$oldList.get(v);
                        Object object1 = androidx.recyclerview.widget.AsyncListDiffer.1.this.val$newList.get(v1);
                        if(object0 == null || object1 == null) {
                            throw new AssertionError();
                        }
                        return AsyncListDiffer.this.mConfig.getDiffCallback().getChangePayload(object0, object1);
                    }

                    @Override  // androidx.recyclerview.widget.DiffUtil$Callback
                    public int getNewListSize() {
                        return androidx.recyclerview.widget.AsyncListDiffer.1.this.val$newList.size();
                    }

                    @Override  // androidx.recyclerview.widget.DiffUtil$Callback
                    public int getOldListSize() {
                        return androidx.recyclerview.widget.AsyncListDiffer.1.this.val$oldList.size();
                    }
                });
                AsyncListDiffer.this.mMainThreadExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        if(AsyncListDiffer.this.mMaxScheduledGeneration == androidx.recyclerview.widget.AsyncListDiffer.1.this.val$runGeneration) {
                            AsyncListDiffer.this.latchList(androidx.recyclerview.widget.AsyncListDiffer.1.this.val$newList, diffUtil$DiffResult0, androidx.recyclerview.widget.AsyncListDiffer.1.this.val$commitCallback);
                        }
                    }
                });
            }
        });
    }
}

