package androidx.recyclerview.widget;

import androidx.collection.LongSparseArray;

interface StableIdStorage {
    public static class IsolatedStableIdStorage implements StableIdStorage {
        class WrapperStableIdLookup implements StableIdLookup {
            private final LongSparseArray mLocalToGlobalLookup;

            WrapperStableIdLookup() {
                this.mLocalToGlobalLookup = new LongSparseArray();
            }

            @Override  // androidx.recyclerview.widget.StableIdStorage$StableIdLookup
            public long localToGlobal(long v) {
                Long long0 = (Long)this.mLocalToGlobalLookup.get(v);
                if(long0 == null) {
                    long0 = IsolatedStableIdStorage.this.obtainId();
                    this.mLocalToGlobalLookup.put(v, long0);
                }
                return (long)long0;
            }
        }

        long mNextStableId;

        public IsolatedStableIdStorage() {
            this.mNextStableId = 0L;
        }

        @Override  // androidx.recyclerview.widget.StableIdStorage
        public StableIdLookup createStableIdLookup() {
            return new WrapperStableIdLookup(this);
        }

        long obtainId() {
            long v = this.mNextStableId;
            this.mNextStableId = v + 1L;
            return v;
        }
    }

    public static class NoStableIdStorage implements StableIdStorage {
        private final StableIdLookup mNoIdLookup;

        public NoStableIdStorage() {
            this.mNoIdLookup = new StableIdLookup() {
                @Override  // androidx.recyclerview.widget.StableIdStorage$StableIdLookup
                public long localToGlobal(long v) {
                    return -1L;
                }
            };
        }

        @Override  // androidx.recyclerview.widget.StableIdStorage
        public StableIdLookup createStableIdLookup() {
            return this.mNoIdLookup;
        }
    }

    public static class SharedPoolStableIdStorage implements StableIdStorage {
        private final StableIdLookup mSameIdLookup;

        public SharedPoolStableIdStorage() {
            this.mSameIdLookup = new StableIdLookup() {
                @Override  // androidx.recyclerview.widget.StableIdStorage$StableIdLookup
                public long localToGlobal(long v) {
                    return v;
                }
            };
        }

        @Override  // androidx.recyclerview.widget.StableIdStorage
        public StableIdLookup createStableIdLookup() {
            return this.mSameIdLookup;
        }
    }

    public interface StableIdLookup {
        long localToGlobal(long arg1);
    }

    StableIdLookup createStableIdLookup();
}

