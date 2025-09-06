package androidx.constraintlayout.core;

import java.util.Arrays;

public class SolverVariableValues implements ArrayRowVariables {
    private static final boolean DEBUG = false;
    private static final boolean HASH = true;
    protected final Cache mCache;
    int mCount;
    private int mHashSize;
    int mHead;
    int[] mKeys;
    int[] mNext;
    int[] mNextKeys;
    private final int mNone;
    int[] mPrevious;
    private final ArrayRow mRow;
    private int mSize;
    float[] mValues;
    int[] mVariables;
    private static float sEpsilon = 0.001f;

    static {
    }

    SolverVariableValues(ArrayRow arrayRow0, Cache cache0) {
        this.mNone = -1;
        this.mSize = 16;
        this.mHashSize = 16;
        this.mKeys = new int[16];
        this.mNextKeys = new int[16];
        this.mVariables = new int[16];
        this.mValues = new float[16];
        this.mPrevious = new int[16];
        this.mNext = new int[16];
        this.mCount = 0;
        this.mHead = -1;
        this.mRow = arrayRow0;
        this.mCache = cache0;
        this.clear();
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public void add(SolverVariable solverVariable0, float f, boolean z) {
        if(f <= -SolverVariableValues.sEpsilon || f >= SolverVariableValues.sEpsilon) {
            int v = this.indexOf(solverVariable0);
            if(v == -1) {
                this.put(solverVariable0, f);
                return;
            }
            float[] arr_f = this.mValues;
            float f1 = arr_f[v] + f;
            arr_f[v] = f1;
            if(f1 > -SolverVariableValues.sEpsilon && f1 < SolverVariableValues.sEpsilon) {
                arr_f[v] = 0.0f;
                this.remove(solverVariable0, z);
            }
        }
    }

    private void addToHashMap(SolverVariable solverVariable0, int v) {
        int[] arr_v1;
        int v1 = solverVariable0.id % this.mHashSize;
        int[] arr_v = this.mKeys;
        int v2 = arr_v[v1];
        if(v2 == -1) {
            arr_v[v1] = v;
        }
        else {
            while(true) {
                arr_v1 = this.mNextKeys;
                int v3 = arr_v1[v2];
                if(v3 == -1) {
                    break;
                }
                v2 = v3;
            }
            arr_v1[v2] = v;
        }
        this.mNextKeys[v] = -1;
    }

    private void addVariable(int v, SolverVariable solverVariable0, float f) {
        this.mVariables[v] = solverVariable0.id;
        this.mValues[v] = f;
        this.mPrevious[v] = -1;
        this.mNext[v] = -1;
        solverVariable0.addToRow(this.mRow);
        ++solverVariable0.usageInRowCount;
        ++this.mCount;
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public void clear() {
        int v = this.mCount;
        for(int v1 = 0; v1 < v; ++v1) {
            SolverVariable solverVariable0 = this.getVariable(v1);
            if(solverVariable0 != null) {
                solverVariable0.removeFromRow(this.mRow);
            }
        }
        for(int v2 = 0; v2 < this.mSize; ++v2) {
            this.mVariables[v2] = -1;
            this.mNextKeys[v2] = -1;
        }
        for(int v3 = 0; v3 < this.mHashSize; ++v3) {
            this.mKeys[v3] = -1;
        }
        this.mCount = 0;
        this.mHead = -1;
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public boolean contains(SolverVariable solverVariable0) {
        return this.indexOf(solverVariable0) != -1;
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public void display() {
        int v = this.mCount;
        System.out.print("{ ");
        for(int v1 = 0; v1 < v; ++v1) {
            SolverVariable solverVariable0 = this.getVariable(v1);
            if(solverVariable0 != null) {
                System.out.print(solverVariable0 + " = " + this.getVariableValue(v1) + " ");
            }
        }
        System.out.println(" }");
    }

    private void displayHash() {
        for(int v = 0; v < this.mHashSize; ++v) {
            if(this.mKeys[v] != -1) {
                String s = this.hashCode() + " hash [" + v + "] => ";
                int v1 = this.mKeys[v];
                boolean z = false;
                while(!z) {
                    s = s + " " + this.mVariables[v1];
                    int v2 = this.mNextKeys[v1];
                    if(v2 == -1) {
                        z = true;
                    }
                    else {
                        v1 = v2;
                    }
                }
                System.out.println(s);
            }
        }
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public void divideByAmount(float f) {
        int v = this.mCount;
        int v1 = this.mHead;
        for(int v2 = 0; v2 < v; ++v2) {
            this.mValues[v1] /= f;
            v1 = this.mNext[v1];
            if(v1 == -1) {
                break;
            }
        }
    }

    private int findEmptySlot() {
        for(int v = 0; v < this.mSize; ++v) {
            if(this.mVariables[v] == -1) {
                return v;
            }
        }
        return -1;
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public float get(SolverVariable solverVariable0) {
        int v = this.indexOf(solverVariable0);
        return v == -1 ? 0.0f : this.mValues[v];
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public int getCurrentSize() {
        return this.mCount;
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public SolverVariable getVariable(int v) {
        int v1 = this.mCount;
        if(v1 == 0) {
            return null;
        }
        int v2 = this.mHead;
        for(int v3 = 0; v3 < v1; ++v3) {
            if(v3 == v && v2 != -1) {
                return this.mCache.mIndexedVariables[this.mVariables[v2]];
            }
            v2 = this.mNext[v2];
            if(v2 == -1) {
                break;
            }
        }
        return null;
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public float getVariableValue(int v) {
        int v1 = this.mCount;
        int v2 = this.mHead;
        for(int v3 = 0; v3 < v1; ++v3) {
            if(v3 == v) {
                return this.mValues[v2];
            }
            v2 = this.mNext[v2];
            if(v2 == -1) {
                break;
            }
        }
        return 0.0f;
    }

    private void increaseSize() {
        int v = this.mSize * 2;
        this.mVariables = Arrays.copyOf(this.mVariables, v);
        this.mValues = Arrays.copyOf(this.mValues, v);
        this.mPrevious = Arrays.copyOf(this.mPrevious, v);
        this.mNext = Arrays.copyOf(this.mNext, v);
        this.mNextKeys = Arrays.copyOf(this.mNextKeys, v);
        for(int v1 = this.mSize; v1 < v; ++v1) {
            this.mVariables[v1] = -1;
            this.mNextKeys[v1] = -1;
        }
        this.mSize = v;
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public int indexOf(SolverVariable solverVariable0) {
        if(this.mCount != 0 && solverVariable0 != null) {
            int v = solverVariable0.id;
            int v1 = this.mKeys[v % this.mHashSize];
            if(v1 == -1) {
                return -1;
            }
            if(this.mVariables[v1] == v) {
                return v1;
            }
            do {
                v1 = this.mNextKeys[v1];
            }
            while(v1 != -1 && this.mVariables[v1] != v);
            if(v1 == -1) {
                return -1;
            }
            return this.mVariables[v1] == v ? v1 : -1;
        }
        return -1;
    }

    private void insertVariable(int v, SolverVariable solverVariable0, float f) {
        int v1 = this.findEmptySlot();
        this.addVariable(v1, solverVariable0, f);
        if(v == -1) {
            this.mPrevious[v1] = -1;
            if(this.mCount > 0) {
                this.mNext[v1] = this.mHead;
                this.mHead = v1;
            }
            else {
                this.mNext[v1] = -1;
            }
        }
        else {
            this.mPrevious[v1] = v;
            int[] arr_v = this.mNext;
            arr_v[v1] = arr_v[v];
            arr_v[v] = v1;
        }
        int v2 = this.mNext[v1];
        if(v2 != -1) {
            this.mPrevious[v2] = v1;
        }
        this.addToHashMap(solverVariable0, v1);
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public void invert() {
        int v = this.mCount;
        int v1 = this.mHead;
        for(int v2 = 0; v2 < v; ++v2) {
            this.mValues[v1] *= -1.0f;
            v1 = this.mNext[v1];
            if(v1 == -1) {
                break;
            }
        }
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public void put(SolverVariable solverVariable0, float f) {
        if(f > -SolverVariableValues.sEpsilon && f < SolverVariableValues.sEpsilon) {
            this.remove(solverVariable0, true);
            return;
        }
        if(this.mCount == 0) {
            this.addVariable(0, solverVariable0, f);
            this.addToHashMap(solverVariable0, 0);
            this.mHead = 0;
            return;
        }
        int v1 = this.indexOf(solverVariable0);
        if(v1 != -1) {
            this.mValues[v1] = f;
            return;
        }
        if(this.mCount + 1 >= this.mSize) {
            this.increaseSize();
        }
        int v2 = this.mCount;
        int v3 = this.mHead;
        int v4 = -1;
        for(int v = 0; v < v2; ++v) {
            if(this.mVariables[v3] == solverVariable0.id) {
                this.mValues[v3] = f;
                return;
            }
            if(this.mVariables[v3] < solverVariable0.id) {
                v4 = v3;
            }
            v3 = this.mNext[v3];
            if(v3 == -1) {
                break;
            }
        }
        this.insertVariable(v4, solverVariable0, f);
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public float remove(SolverVariable solverVariable0, boolean z) {
        int v = this.indexOf(solverVariable0);
        if(v == -1) {
            return 0.0f;
        }
        this.removeFromHashMap(solverVariable0);
        float f = this.mValues[v];
        if(this.mHead == v) {
            this.mHead = this.mNext[v];
        }
        this.mVariables[v] = -1;
        int[] arr_v = this.mPrevious;
        int v1 = arr_v[v];
        if(v1 != -1) {
            this.mNext[v1] = this.mNext[v];
        }
        int v2 = this.mNext[v];
        if(v2 != -1) {
            arr_v[v2] = arr_v[v];
        }
        --this.mCount;
        --solverVariable0.usageInRowCount;
        if(z) {
            solverVariable0.removeFromRow(this.mRow);
        }
        return f;
    }

    private void removeFromHashMap(SolverVariable solverVariable0) {
        int v3;
        int[] arr_v1;
        int v = solverVariable0.id % this.mHashSize;
        int v1 = this.mKeys[v];
        if(v1 != -1) {
            int v2 = solverVariable0.id;
            if(this.mVariables[v1] == v2) {
                int[] arr_v = this.mNextKeys;
                this.mKeys[v] = arr_v[v1];
                arr_v[v1] = -1;
                return;
            }
            while(true) {
                arr_v1 = this.mNextKeys;
                v3 = arr_v1[v1];
                if(v3 == -1 || this.mVariables[v3] == v2) {
                    break;
                }
                v1 = v3;
            }
            if(v3 != -1 && this.mVariables[v3] == v2) {
                arr_v1[v1] = arr_v1[v3];
                arr_v1[v3] = -1;
            }
        }
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public int sizeInBytes() {
        return 0;
    }

    @Override
    public String toString() {
        String s = this.hashCode() + " { ";
        int v = this.mCount;
        for(int v1 = 0; v1 < v; ++v1) {
            SolverVariable solverVariable0 = this.getVariable(v1);
            if(solverVariable0 != null) {
                int v2 = this.indexOf(solverVariable0);
                String s1 = s + solverVariable0 + " = " + this.getVariableValue(v1) + " " + "[p: ";
                String s2 = this.mPrevious[v2] == -1 ? s1 + "none" : s1 + this.mCache.mIndexedVariables[this.mVariables[this.mPrevious[v2]]];
                s = (this.mNext[v2] == -1 ? s2 + ", n: " + "none" : s2 + ", n: " + this.mCache.mIndexedVariables[this.mVariables[this.mNext[v2]]]) + "]";
            }
        }
        return s + " }";
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public float use(ArrayRow arrayRow0, boolean z) {
        float f = this.get(arrayRow0.mVariable);
        this.remove(arrayRow0.mVariable, z);
        SolverVariableValues solverVariableValues0 = (SolverVariableValues)arrayRow0.variables;
        int v = solverVariableValues0.getCurrentSize();
        int v1 = 0;
        for(int v2 = 0; v1 < v; ++v2) {
            if(solverVariableValues0.mVariables[v2] != -1) {
                this.add(this.mCache.mIndexedVariables[solverVariableValues0.mVariables[v2]], solverVariableValues0.mValues[v2] * f, z);
                ++v1;
            }
        }
        return f;
    }
}

