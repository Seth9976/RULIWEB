package androidx.constraintlayout.core.motion.utils;

import androidx.constraintlayout.core.motion.CustomAttribute;
import androidx.constraintlayout.core.motion.CustomVariable;
import java.util.Arrays;

public class KeyFrameArray {
    public static class CustomArray {
        private static final int EMPTY = 999;
        int mCount;
        int[] mKeys;
        CustomAttribute[] mValues;

        public CustomArray() {
            this.mKeys = new int[101];
            this.mValues = new CustomAttribute[101];
            this.clear();
        }

        public void append(int v, CustomAttribute customAttribute0) {
            if(this.mValues[v] != null) {
                this.remove(v);
            }
            this.mValues[v] = customAttribute0;
            int[] arr_v = this.mKeys;
            int v1 = this.mCount;
            this.mCount = v1 + 1;
            arr_v[v1] = v;
            Arrays.sort(arr_v);
        }

        public void clear() {
            Arrays.fill(this.mKeys, 999);
            Arrays.fill(this.mValues, null);
            this.mCount = 0;
        }

        public void dump() {
            System.out.println("V: " + Arrays.toString(Arrays.copyOf(this.mKeys, this.mCount)));
            System.out.print("K: [");
            for(int v = 0; v < this.mCount; ++v) {
                System.out.print((v == 0 ? "" : ", ") + this.valueAt(v));
            }
            System.out.println("]");
        }

        public int keyAt(int v) {
            return this.mKeys[v];
        }

        public void remove(int v) {
            int v3;
            this.mValues[v] = null;
            int v2 = 0;
            for(int v1 = 0; true; ++v1) {
                v3 = this.mCount;
                if(v1 >= v3) {
                    break;
                }
                int[] arr_v = this.mKeys;
                if(v == arr_v[v1]) {
                    arr_v[v1] = 999;
                    ++v2;
                }
                if(v1 != v2) {
                    arr_v[v1] = arr_v[v2];
                }
                ++v2;
            }
            this.mCount = v3 - 1;
        }

        public int size() {
            return this.mCount;
        }

        public CustomAttribute valueAt(int v) {
            return this.mValues[this.mKeys[v]];
        }
    }

    public static class CustomVar {
        private static final int EMPTY = 999;
        int mCount;
        int[] mKeys;
        CustomVariable[] mValues;

        public CustomVar() {
            this.mKeys = new int[101];
            this.mValues = new CustomVariable[101];
            this.clear();
        }

        public void append(int v, CustomVariable customVariable0) {
            if(this.mValues[v] != null) {
                this.remove(v);
            }
            this.mValues[v] = customVariable0;
            int[] arr_v = this.mKeys;
            int v1 = this.mCount;
            this.mCount = v1 + 1;
            arr_v[v1] = v;
            Arrays.sort(arr_v);
        }

        public void clear() {
            Arrays.fill(this.mKeys, 999);
            Arrays.fill(this.mValues, null);
            this.mCount = 0;
        }

        public void dump() {
            System.out.println("V: " + Arrays.toString(Arrays.copyOf(this.mKeys, this.mCount)));
            System.out.print("K: [");
            for(int v = 0; v < this.mCount; ++v) {
                System.out.print((v == 0 ? "" : ", ") + this.valueAt(v));
            }
            System.out.println("]");
        }

        public int keyAt(int v) {
            return this.mKeys[v];
        }

        public void remove(int v) {
            int v3;
            this.mValues[v] = null;
            int v2 = 0;
            for(int v1 = 0; true; ++v1) {
                v3 = this.mCount;
                if(v1 >= v3) {
                    break;
                }
                int[] arr_v = this.mKeys;
                if(v == arr_v[v1]) {
                    arr_v[v1] = 999;
                    ++v2;
                }
                if(v1 != v2) {
                    arr_v[v1] = arr_v[v2];
                }
                ++v2;
            }
            this.mCount = v3 - 1;
        }

        public int size() {
            return this.mCount;
        }

        public CustomVariable valueAt(int v) {
            return this.mValues[this.mKeys[v]];
        }
    }

    static class FloatArray {
        private static final int EMPTY = 999;
        int mCount;
        int[] mKeys;
        float[][] mValues;

        FloatArray() {
            this.mKeys = new int[101];
            this.mValues = new float[101][];
            this.clear();
        }

        public void append(int v, float[] arr_f) {
            if(this.mValues[v] != null) {
                this.remove(v);
            }
            this.mValues[v] = arr_f;
            int[] arr_v = this.mKeys;
            int v1 = this.mCount;
            this.mCount = v1 + 1;
            arr_v[v1] = v;
            Arrays.sort(arr_v);
        }

        public void clear() {
            Arrays.fill(this.mKeys, 999);
            Arrays.fill(this.mValues, null);
            this.mCount = 0;
        }

        public void dump() {
            System.out.println("V: " + Arrays.toString(Arrays.copyOf(this.mKeys, this.mCount)));
            System.out.print("K: [");
            for(int v = 0; v < this.mCount; ++v) {
                System.out.print((v == 0 ? "" : ", ") + Arrays.toString(this.valueAt(v)));
            }
            System.out.println("]");
        }

        public int keyAt(int v) {
            return this.mKeys[v];
        }

        public void remove(int v) {
            int v3;
            this.mValues[v] = null;
            int v2 = 0;
            for(int v1 = 0; true; ++v1) {
                v3 = this.mCount;
                if(v1 >= v3) {
                    break;
                }
                int[] arr_v = this.mKeys;
                if(v == arr_v[v1]) {
                    arr_v[v1] = 999;
                    ++v2;
                }
                if(v1 != v2) {
                    arr_v[v1] = arr_v[v2];
                }
                ++v2;
            }
            this.mCount = v3 - 1;
        }

        public int size() {
            return this.mCount;
        }

        public float[] valueAt(int v) {
            return this.mValues[this.mKeys[v]];
        }
    }

}

