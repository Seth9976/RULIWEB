package androidx.recyclerview.widget;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class SortedList {
    public static class BatchedCallback extends Callback {
        private final BatchingListUpdateCallback mBatchingListUpdateCallback;
        final Callback mWrappedCallback;

        public BatchedCallback(Callback sortedList$Callback0) {
            this.mWrappedCallback = sortedList$Callback0;
            this.mBatchingListUpdateCallback = new BatchingListUpdateCallback(sortedList$Callback0);
        }

        @Override  // androidx.recyclerview.widget.SortedList$Callback
        public boolean areContentsTheSame(Object object0, Object object1) {
            return this.mWrappedCallback.areContentsTheSame(object0, object1);
        }

        @Override  // androidx.recyclerview.widget.SortedList$Callback
        public boolean areItemsTheSame(Object object0, Object object1) {
            return this.mWrappedCallback.areItemsTheSame(object0, object1);
        }

        @Override  // androidx.recyclerview.widget.SortedList$Callback
        public int compare(Object object0, Object object1) {
            return this.mWrappedCallback.compare(object0, object1);
        }

        public void dispatchLastEvent() {
            this.mBatchingListUpdateCallback.dispatchLastEvent();
        }

        @Override  // androidx.recyclerview.widget.SortedList$Callback
        public Object getChangePayload(Object object0, Object object1) {
            return this.mWrappedCallback.getChangePayload(object0, object1);
        }

        @Override  // androidx.recyclerview.widget.SortedList$Callback
        public void onChanged(int v, int v1) {
            this.mBatchingListUpdateCallback.onChanged(v, v1, null);
        }

        @Override  // androidx.recyclerview.widget.SortedList$Callback
        public void onChanged(int v, int v1, Object object0) {
            this.mBatchingListUpdateCallback.onChanged(v, v1, object0);
        }

        @Override  // androidx.recyclerview.widget.ListUpdateCallback
        public void onInserted(int v, int v1) {
            this.mBatchingListUpdateCallback.onInserted(v, v1);
        }

        @Override  // androidx.recyclerview.widget.ListUpdateCallback
        public void onMoved(int v, int v1) {
            this.mBatchingListUpdateCallback.onMoved(v, v1);
        }

        @Override  // androidx.recyclerview.widget.ListUpdateCallback
        public void onRemoved(int v, int v1) {
            this.mBatchingListUpdateCallback.onRemoved(v, v1);
        }
    }

    public static abstract class Callback implements ListUpdateCallback, Comparator {
        public abstract boolean areContentsTheSame(Object arg1, Object arg2);

        public abstract boolean areItemsTheSame(Object arg1, Object arg2);

        @Override
        public abstract int compare(Object arg1, Object arg2);

        public Object getChangePayload(Object object0, Object object1) {
            return null;
        }

        public abstract void onChanged(int arg1, int arg2);

        @Override  // androidx.recyclerview.widget.ListUpdateCallback
        public void onChanged(int v, int v1, Object object0) {
            this.onChanged(v, v1);
        }
    }

    private static final int CAPACITY_GROWTH = 10;
    private static final int DELETION = 2;
    private static final int INSERTION = 1;
    public static final int INVALID_POSITION = -1;
    private static final int LOOKUP = 4;
    private static final int MIN_CAPACITY = 10;
    private BatchedCallback mBatchedCallback;
    private Callback mCallback;
    Object[] mData;
    private int mNewDataStart;
    private Object[] mOldData;
    private int mOldDataSize;
    private int mOldDataStart;
    private int mSize;
    private final Class mTClass;

    public SortedList(Class class0, Callback sortedList$Callback0) {
        this(class0, sortedList$Callback0, 10);
    }

    public SortedList(Class class0, Callback sortedList$Callback0, int v) {
        this.mTClass = class0;
        this.mData = (Object[])Array.newInstance(class0, v);
        this.mCallback = sortedList$Callback0;
        this.mSize = 0;
    }

    private int add(Object object0, boolean z) {
        int v = this.findIndexOf(object0, this.mData, 0, this.mSize, 1);
        if(v == -1) {
            v = 0;
        }
        else if(v < this.mSize) {
            Object object1 = this.mData[v];
            if(this.mCallback.areItemsTheSame(object1, object0)) {
                if(this.mCallback.areContentsTheSame(object1, object0)) {
                    this.mData[v] = object0;
                    return v;
                }
                this.mData[v] = object0;
                this.mCallback.onChanged(v, 1, this.mCallback.getChangePayload(object1, object0));
                return v;
            }
        }
        this.addToData(v, object0);
        if(z) {
            this.mCallback.onInserted(v, 1);
        }
        return v;
    }

    public int add(Object object0) {
        this.throwIfInMutationOperation();
        return this.add(object0, true);
    }

    public void addAll(Collection collection0) {
        this.addAll(collection0.toArray(((Object[])Array.newInstance(this.mTClass, collection0.size()))), true);
    }

    public void addAll(Object[] arr_object) {
        this.addAll(arr_object, false);
    }

    public void addAll(Object[] arr_object, boolean z) {
        this.throwIfInMutationOperation();
        if(arr_object.length == 0) {
            return;
        }
        if(z) {
            this.addAllInternal(arr_object);
            return;
        }
        this.addAllInternal(this.copyArray(arr_object));
    }

    private void addAllInternal(Object[] arr_object) {
        if(arr_object.length < 1) {
            return;
        }
        int v = this.sortAndDedup(arr_object);
        if(this.mSize == 0) {
            this.mData = arr_object;
            this.mSize = v;
            this.mCallback.onInserted(0, v);
            return;
        }
        this.merge(arr_object, v);
    }

    private void addToData(int v, Object object0) {
        int v1 = this.mSize;
        if(v > v1) {
            throw new IndexOutOfBoundsException("cannot add item to " + v + " because size is " + this.mSize);
        }
        Object[] arr_object = this.mData;
        if(v1 == arr_object.length) {
            Object[] arr_object1 = (Object[])Array.newInstance(this.mTClass, arr_object.length + 10);
            System.arraycopy(this.mData, 0, arr_object1, 0, v);
            arr_object1[v] = object0;
            System.arraycopy(this.mData, v, arr_object1, v + 1, this.mSize - v);
            this.mData = arr_object1;
        }
        else {
            System.arraycopy(arr_object, v, arr_object, v + 1, v1 - v);
            this.mData[v] = object0;
        }
        ++this.mSize;
    }

    public void beginBatchedUpdates() {
        this.throwIfInMutationOperation();
        if(this.mCallback instanceof BatchedCallback) {
            return;
        }
        if(this.mBatchedCallback == null) {
            this.mBatchedCallback = new BatchedCallback(this.mCallback);
        }
        this.mCallback = this.mBatchedCallback;
    }

    public void clear() {
        this.throwIfInMutationOperation();
        int v = this.mSize;
        if(v == 0) {
            return;
        }
        Arrays.fill(this.mData, 0, v, null);
        this.mSize = 0;
        this.mCallback.onRemoved(0, v);
    }

    private Object[] copyArray(Object[] arr_object) {
        Object[] arr_object1 = (Object[])Array.newInstance(this.mTClass, arr_object.length);
        System.arraycopy(arr_object, 0, arr_object1, 0, arr_object.length);
        return arr_object1;
    }

    public void endBatchedUpdates() {
        this.throwIfInMutationOperation();
        Callback sortedList$Callback0 = this.mCallback;
        if(sortedList$Callback0 instanceof BatchedCallback) {
            ((BatchedCallback)sortedList$Callback0).dispatchLastEvent();
        }
        BatchedCallback sortedList$BatchedCallback0 = this.mBatchedCallback;
        if(this.mCallback == sortedList$BatchedCallback0) {
            this.mCallback = sortedList$BatchedCallback0.mWrappedCallback;
        }
    }

    private int findIndexOf(Object object0, Object[] arr_object, int v, int v1, int v2) {
        while(v < v1) {
            int v3 = (v + v1) / 2;
            Object object1 = arr_object[v3];
            int v4 = this.mCallback.compare(object1, object0);
            if(v4 < 0) {
                v = v3 + 1;
            }
            else {
                if(v4 == 0) {
                    if(!this.mCallback.areItemsTheSame(object1, object0)) {
                        int v5 = this.linearEqualitySearch(object0, v3, v, v1);
                        return v2 != 1 || v5 != -1 ? v5 : v3;
                    }
                    return v3;
                }
                v1 = v3;
            }
        }
        return v2 == 1 ? v : -1;
    }

    private int findSameItem(Object object0, Object[] arr_object, int v, int v1) {
        while(v < v1) {
            if(this.mCallback.areItemsTheSame(arr_object[v], object0)) {
                return v;
            }
            ++v;
        }
        return -1;
    }

    public Object get(int v) throws IndexOutOfBoundsException {
        if(v >= this.mSize || v < 0) {
            throw new IndexOutOfBoundsException("Asked to get item at " + v + " but size is " + this.mSize);
        }
        Object[] arr_object = this.mOldData;
        if(arr_object != null) {
            return v < this.mNewDataStart ? this.mData[v] : arr_object[v - this.mNewDataStart + this.mOldDataStart];
        }
        return this.mData[v];
    }

    public int indexOf(Object object0) {
        if(this.mOldData != null) {
            int v = this.findIndexOf(object0, this.mData, 0, this.mNewDataStart, 4);
            if(v != -1) {
                return v;
            }
            int v1 = this.findIndexOf(object0, this.mOldData, this.mOldDataStart, this.mOldDataSize, 4);
            return v1 == -1 ? -1 : v1 - this.mOldDataStart + this.mNewDataStart;
        }
        return this.findIndexOf(object0, this.mData, 0, this.mSize, 4);
    }

    private int linearEqualitySearch(Object object0, int v, int v1, int v2) {
        for(int v3 = v - 1; v3 >= v1; --v3) {
            Object object1 = this.mData[v3];
            if(this.mCallback.compare(object1, object0) != 0) {
                break;
            }
            if(this.mCallback.areItemsTheSame(object1, object0)) {
                return v3;
            }
        }
        while(true) {
            ++v;
            if(v >= v2) {
                break;
            }
            Object object2 = this.mData[v];
            if(this.mCallback.compare(object2, object0) != 0) {
                break;
            }
            if(this.mCallback.areItemsTheSame(object2, object0)) {
                return v;
            }
        }
        return -1;
    }

    private void merge(Object[] arr_object, int v) {
        boolean z = this.mCallback instanceof BatchedCallback;
        if(!z) {
            this.beginBatchedUpdates();
        }
        this.mOldData = this.mData;
        int v1 = 0;
        this.mOldDataStart = 0;
        this.mOldDataSize = this.mSize;
        this.mData = (Object[])Array.newInstance(this.mTClass, this.mSize + v + 10);
        this.mNewDataStart = 0;
        while(true) {
            int v2 = this.mOldDataStart;
            int v3 = this.mOldDataSize;
            if(v2 >= v3 && v1 >= v) {
                break;
            }
            if(v2 == v3) {
                int v4 = v - v1;
                System.arraycopy(arr_object, v1, this.mData, this.mNewDataStart, v4);
                int v5 = this.mNewDataStart + v4;
                this.mNewDataStart = v5;
                this.mSize += v4;
                this.mCallback.onInserted(v5 - v4, v4);
                break;
            }
            if(v1 == v) {
                int v6 = v3 - v2;
                System.arraycopy(this.mOldData, v2, this.mData, this.mNewDataStart, v6);
                this.mNewDataStart += v6;
                break;
            }
            Object object0 = this.mOldData[v2];
            Object object1 = arr_object[v1];
            int v7 = this.mCallback.compare(object0, object1);
            if(v7 > 0) {
                int v8 = this.mNewDataStart;
                this.mNewDataStart = v8 + 1;
                this.mData[v8] = object1;
                ++this.mSize;
                ++v1;
                this.mCallback.onInserted(v8, 1);
            }
            else if(v7 == 0 && this.mCallback.areItemsTheSame(object0, object1)) {
                int v9 = this.mNewDataStart;
                this.mNewDataStart = v9 + 1;
                this.mData[v9] = object1;
                ++v1;
                ++this.mOldDataStart;
                if(this.mCallback.areContentsTheSame(object0, object1)) {
                    continue;
                }
                this.mCallback.onChanged(this.mNewDataStart - 1, 1, this.mCallback.getChangePayload(object0, object1));
            }
            else {
                int v10 = this.mNewDataStart;
                this.mNewDataStart = v10 + 1;
                this.mData[v10] = object0;
                ++this.mOldDataStart;
            }
        }
        this.mOldData = null;
        if(!z) {
            this.endBatchedUpdates();
        }
    }

    public void recalculatePositionOfItemAt(int v) {
        this.throwIfInMutationOperation();
        Object object0 = this.get(v);
        this.removeItemAtIndex(v, false);
        int v1 = this.add(object0, false);
        if(v != v1) {
            this.mCallback.onMoved(v, v1);
        }
    }

    private boolean remove(Object object0, boolean z) {
        int v = this.findIndexOf(object0, this.mData, 0, this.mSize, 2);
        if(v == -1) {
            return false;
        }
        this.removeItemAtIndex(v, z);
        return true;
    }

    public boolean remove(Object object0) {
        this.throwIfInMutationOperation();
        return this.remove(object0, true);
    }

    public Object removeItemAt(int v) {
        this.throwIfInMutationOperation();
        Object object0 = this.get(v);
        this.removeItemAtIndex(v, true);
        return object0;
    }

    private void removeItemAtIndex(int v, boolean z) {
        System.arraycopy(this.mData, v + 1, this.mData, v, this.mSize - v - 1);
        int v1 = this.mSize - 1;
        this.mSize = v1;
        this.mData[v1] = null;
        if(z) {
            this.mCallback.onRemoved(v, 1);
        }
    }

    public void replaceAll(Collection collection0) {
        this.replaceAll(collection0.toArray(((Object[])Array.newInstance(this.mTClass, collection0.size()))), true);
    }

    public void replaceAll(Object[] arr_object) {
        this.replaceAll(arr_object, false);
    }

    public void replaceAll(Object[] arr_object, boolean z) {
        this.throwIfInMutationOperation();
        if(z) {
            this.replaceAllInternal(arr_object);
            return;
        }
        this.replaceAllInternal(this.copyArray(arr_object));
    }

    private void replaceAllInsert(Object object0) {
        int v = this.mNewDataStart;
        this.mData[v] = object0;
        this.mNewDataStart = v + 1;
        ++this.mSize;
        this.mCallback.onInserted(v, 1);
    }

    private void replaceAllInternal(Object[] arr_object) {
        boolean z = this.mCallback instanceof BatchedCallback;
        if(!z) {
            this.beginBatchedUpdates();
        }
        this.mOldDataStart = 0;
        this.mOldDataSize = this.mSize;
        this.mOldData = this.mData;
        this.mNewDataStart = 0;
        int v = this.sortAndDedup(arr_object);
        this.mData = (Object[])Array.newInstance(this.mTClass, v);
        while(true) {
            int v1 = this.mNewDataStart;
            if(v1 >= v && this.mOldDataStart >= this.mOldDataSize) {
                break;
            }
            int v2 = this.mOldDataStart;
            int v3 = this.mOldDataSize;
            if(v2 >= v3) {
                int v4 = v - v1;
                System.arraycopy(arr_object, v1, this.mData, v1, v4);
                this.mNewDataStart += v4;
                this.mSize += v4;
                this.mCallback.onInserted(v1, v4);
                break;
            }
            if(v1 >= v) {
                int v5 = v3 - v2;
                this.mSize -= v5;
                this.mCallback.onRemoved(v1, v5);
                break;
            }
            Object object0 = this.mOldData[v2];
            Object object1 = arr_object[v1];
            int v6 = this.mCallback.compare(object0, object1);
            if(v6 < 0) {
                this.replaceAllRemove();
            }
            else if(v6 > 0) {
                this.replaceAllInsert(object1);
            }
            else if(this.mCallback.areItemsTheSame(object0, object1)) {
                int v7 = this.mNewDataStart;
                this.mData[v7] = object1;
                ++this.mOldDataStart;
                this.mNewDataStart = v7 + 1;
                if(!this.mCallback.areContentsTheSame(object0, object1)) {
                    this.mCallback.onChanged(this.mNewDataStart - 1, 1, this.mCallback.getChangePayload(object0, object1));
                }
            }
            else {
                this.replaceAllRemove();
                this.replaceAllInsert(object1);
            }
        }
        this.mOldData = null;
        if(!z) {
            this.endBatchedUpdates();
        }
    }

    private void replaceAllRemove() {
        --this.mSize;
        ++this.mOldDataStart;
        this.mCallback.onRemoved(this.mNewDataStart, 1);
    }

    public int size() {
        return this.mSize;
    }

    private int sortAndDedup(Object[] arr_object) {
        if(arr_object.length == 0) {
            return 0;
        }
        Arrays.sort(arr_object, this.mCallback);
        int v1 = 1;
        int v2 = 0;
        for(int v = 1; v < arr_object.length; ++v) {
            Object object0 = arr_object[v];
            if(this.mCallback.compare(arr_object[v2], object0) == 0) {
                int v3 = this.findSameItem(object0, arr_object, v2, v1);
                if(v3 == -1) {
                    if(v1 != v) {
                        arr_object[v1] = object0;
                    }
                    ++v1;
                }
                else {
                    arr_object[v3] = object0;
                }
            }
            else {
                if(v1 != v) {
                    arr_object[v1] = object0;
                }
                v2 = v1;
                ++v1;
            }
        }
        return v1;
    }

    private void throwIfInMutationOperation() {
        if(this.mOldData != null) {
            throw new IllegalStateException("Data cannot be mutated in the middle of a batch update operation such as addAll or replaceAll.");
        }
    }

    public void updateItemAt(int v, Object object0) {
        this.throwIfInMutationOperation();
        Object object1 = this.get(v);
        boolean z = object1 == object0 || !this.mCallback.areContentsTheSame(object1, object0);
        if(object1 == object0 || this.mCallback.compare(object1, object0) != 0) {
            if(z) {
                this.mCallback.onChanged(v, 1, this.mCallback.getChangePayload(object1, object0));
            }
            this.removeItemAtIndex(v, false);
            int v1 = this.add(object0, false);
            if(v != v1) {
                this.mCallback.onMoved(v, v1);
            }
        }
        else {
            this.mData[v] = object0;
            if(z) {
                this.mCallback.onChanged(v, 1, this.mCallback.getChangePayload(object1, object0));
            }
        }
    }
}

