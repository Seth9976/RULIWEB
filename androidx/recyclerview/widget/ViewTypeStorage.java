package androidx.recyclerview.widget;

import android.util.SparseArray;
import android.util.SparseIntArray;
import java.util.ArrayList;
import java.util.List;

interface ViewTypeStorage {
    public static class IsolatedViewTypeStorage implements ViewTypeStorage {
        class WrapperViewTypeLookup implements ViewTypeLookup {
            private SparseIntArray mGlobalToLocalMapping;
            private SparseIntArray mLocalToGlobalMapping;
            final NestedAdapterWrapper mWrapper;

            WrapperViewTypeLookup(NestedAdapterWrapper nestedAdapterWrapper0) {
                this.mLocalToGlobalMapping = new SparseIntArray(1);
                this.mGlobalToLocalMapping = new SparseIntArray(1);
                this.mWrapper = nestedAdapterWrapper0;
            }

            @Override  // androidx.recyclerview.widget.ViewTypeStorage$ViewTypeLookup
            public void dispose() {
                IsolatedViewTypeStorage.this.removeWrapper(this.mWrapper);
            }

            @Override  // androidx.recyclerview.widget.ViewTypeStorage$ViewTypeLookup
            public int globalToLocal(int v) {
                int v1 = this.mGlobalToLocalMapping.indexOfKey(v);
                if(v1 < 0) {
                    throw new IllegalStateException("requested global type " + v + " does not belong to the adapter:" + this.mWrapper.adapter);
                }
                return this.mGlobalToLocalMapping.valueAt(v1);
            }

            @Override  // androidx.recyclerview.widget.ViewTypeStorage$ViewTypeLookup
            public int localToGlobal(int v) {
                int v1 = this.mLocalToGlobalMapping.indexOfKey(v);
                if(v1 > -1) {
                    return this.mLocalToGlobalMapping.valueAt(v1);
                }
                int v2 = IsolatedViewTypeStorage.this.obtainViewType(this.mWrapper);
                this.mLocalToGlobalMapping.put(v, v2);
                this.mGlobalToLocalMapping.put(v2, v);
                return v2;
            }
        }

        SparseArray mGlobalTypeToWrapper;
        int mNextViewType;

        public IsolatedViewTypeStorage() {
            this.mGlobalTypeToWrapper = new SparseArray();
            this.mNextViewType = 0;
        }

        @Override  // androidx.recyclerview.widget.ViewTypeStorage
        public ViewTypeLookup createViewTypeWrapper(NestedAdapterWrapper nestedAdapterWrapper0) {
            return new WrapperViewTypeLookup(this, nestedAdapterWrapper0);
        }

        @Override  // androidx.recyclerview.widget.ViewTypeStorage
        public NestedAdapterWrapper getWrapperForGlobalType(int v) {
            NestedAdapterWrapper nestedAdapterWrapper0 = (NestedAdapterWrapper)this.mGlobalTypeToWrapper.get(v);
            if(nestedAdapterWrapper0 == null) {
                throw new IllegalArgumentException("Cannot find the wrapper for global view type " + v);
            }
            return nestedAdapterWrapper0;
        }

        int obtainViewType(NestedAdapterWrapper nestedAdapterWrapper0) {
            int v = this.mNextViewType;
            this.mNextViewType = v + 1;
            this.mGlobalTypeToWrapper.put(v, nestedAdapterWrapper0);
            return v;
        }

        void removeWrapper(NestedAdapterWrapper nestedAdapterWrapper0) {
            for(int v = this.mGlobalTypeToWrapper.size() - 1; v >= 0; --v) {
                if(((NestedAdapterWrapper)this.mGlobalTypeToWrapper.valueAt(v)) == nestedAdapterWrapper0) {
                    this.mGlobalTypeToWrapper.removeAt(v);
                }
            }
        }
    }

    public static class SharedIdRangeViewTypeStorage implements ViewTypeStorage {
        class androidx.recyclerview.widget.ViewTypeStorage.SharedIdRangeViewTypeStorage.WrapperViewTypeLookup implements ViewTypeLookup {
            final NestedAdapterWrapper mWrapper;

            androidx.recyclerview.widget.ViewTypeStorage.SharedIdRangeViewTypeStorage.WrapperViewTypeLookup(NestedAdapterWrapper nestedAdapterWrapper0) {
                this.mWrapper = nestedAdapterWrapper0;
            }

            @Override  // androidx.recyclerview.widget.ViewTypeStorage$ViewTypeLookup
            public void dispose() {
                SharedIdRangeViewTypeStorage.this.removeWrapper(this.mWrapper);
            }

            @Override  // androidx.recyclerview.widget.ViewTypeStorage$ViewTypeLookup
            public int globalToLocal(int v) {
                return v;
            }

            @Override  // androidx.recyclerview.widget.ViewTypeStorage$ViewTypeLookup
            public int localToGlobal(int v) {
                List list0 = (List)SharedIdRangeViewTypeStorage.this.mGlobalTypeToWrapper.get(v);
                if(list0 == null) {
                    list0 = new ArrayList();
                    SharedIdRangeViewTypeStorage.this.mGlobalTypeToWrapper.put(v, list0);
                }
                if(!list0.contains(this.mWrapper)) {
                    list0.add(this.mWrapper);
                }
                return v;
            }
        }

        SparseArray mGlobalTypeToWrapper;

        public SharedIdRangeViewTypeStorage() {
            this.mGlobalTypeToWrapper = new SparseArray();
        }

        @Override  // androidx.recyclerview.widget.ViewTypeStorage
        public ViewTypeLookup createViewTypeWrapper(NestedAdapterWrapper nestedAdapterWrapper0) {
            return new androidx.recyclerview.widget.ViewTypeStorage.SharedIdRangeViewTypeStorage.WrapperViewTypeLookup(this, nestedAdapterWrapper0);
        }

        @Override  // androidx.recyclerview.widget.ViewTypeStorage
        public NestedAdapterWrapper getWrapperForGlobalType(int v) {
            List list0 = (List)this.mGlobalTypeToWrapper.get(v);
            if(list0 == null || list0.isEmpty()) {
                throw new IllegalArgumentException("Cannot find the wrapper for global view type " + v);
            }
            return (NestedAdapterWrapper)list0.get(0);
        }

        void removeWrapper(NestedAdapterWrapper nestedAdapterWrapper0) {
            for(int v = this.mGlobalTypeToWrapper.size() - 1; v >= 0; --v) {
                List list0 = (List)this.mGlobalTypeToWrapper.valueAt(v);
                if(list0.remove(nestedAdapterWrapper0) && list0.isEmpty()) {
                    this.mGlobalTypeToWrapper.removeAt(v);
                }
            }
        }
    }

    public interface ViewTypeLookup {
        void dispose();

        int globalToLocal(int arg1);

        int localToGlobal(int arg1);
    }

    ViewTypeLookup createViewTypeWrapper(NestedAdapterWrapper arg1);

    NestedAdapterWrapper getWrapperForGlobalType(int arg1);
}

