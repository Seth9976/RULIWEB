package androidx.constraintlayout.core;

final class Pools {
    interface Pool {
        Object acquire();

        boolean release(Object arg1);

        void releaseAll(Object[] arg1, int arg2);
    }

    static class SimplePool implements Pool {
        private final Object[] mPool;
        private int mPoolSize;

        SimplePool(int v) {
            if(v <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }
            this.mPool = new Object[v];
        }

        @Override  // androidx.constraintlayout.core.Pools$Pool
        public Object acquire() {
            int v = this.mPoolSize;
            if(v > 0) {
                Object object0 = this.mPool[v - 1];
                this.mPool[v - 1] = null;
                this.mPoolSize = v - 1;
                return object0;
            }
            return null;
        }

        private boolean isInPool(Object object0) {
            for(int v = 0; v < this.mPoolSize; ++v) {
                if(this.mPool[v] == object0) {
                    return true;
                }
            }
            return false;
        }

        @Override  // androidx.constraintlayout.core.Pools$Pool
        public boolean release(Object object0) {
            int v = this.mPoolSize;
            Object[] arr_object = this.mPool;
            if(v < arr_object.length) {
                arr_object[v] = object0;
                this.mPoolSize = v + 1;
                return true;
            }
            return false;
        }

        @Override  // androidx.constraintlayout.core.Pools$Pool
        public void releaseAll(Object[] arr_object, int v) {
            if(v > arr_object.length) {
                v = arr_object.length;
            }
            for(int v1 = 0; v1 < v; ++v1) {
                Object object0 = arr_object[v1];
                int v2 = this.mPoolSize;
                Object[] arr_object1 = this.mPool;
                if(v2 < arr_object1.length) {
                    arr_object1[v2] = object0;
                    this.mPoolSize = v2 + 1;
                }
            }
        }
    }

    private static final boolean DEBUG = false;

}

