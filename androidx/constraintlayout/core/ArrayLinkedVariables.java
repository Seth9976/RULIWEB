package androidx.constraintlayout.core;

import java.util.Arrays;

public class ArrayLinkedVariables implements ArrayRowVariables {
    private static final boolean DEBUG = false;
    static final int NONE = -1;
    private int[] mArrayIndices;
    private int[] mArrayNextIndices;
    private float[] mArrayValues;
    protected final Cache mCache;
    private SolverVariable mCandidate;
    int mCurrentSize;
    private boolean mDidFillOnce;
    private int mHead;
    private int mLast;
    private final ArrayRow mRow;
    private int mRowSize;
    private static float sEpsilon = 0.001f;

    static {
    }

    ArrayLinkedVariables(ArrayRow arrayRow0, Cache cache0) {
        this.mCurrentSize = 0;
        this.mRowSize = 8;
        this.mCandidate = null;
        this.mArrayIndices = new int[8];
        this.mArrayNextIndices = new int[8];
        this.mArrayValues = new float[8];
        this.mHead = -1;
        this.mLast = -1;
        this.mDidFillOnce = false;
        this.mRow = arrayRow0;
        this.mCache = cache0;
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public void add(SolverVariable solverVariable0, float f, boolean z) {
        if(f <= -ArrayLinkedVariables.sEpsilon || f >= ArrayLinkedVariables.sEpsilon) {
            int v = this.mHead;
            if(v == -1) {
                this.mHead = 0;
                this.mArrayValues[0] = f;
                this.mArrayIndices[0] = solverVariable0.id;
                this.mArrayNextIndices[0] = -1;
                ++solverVariable0.usageInRowCount;
                solverVariable0.addToRow(this.mRow);
                ++this.mCurrentSize;
                if(!this.mDidFillOnce) {
                    int v1 = this.mLast + 1;
                    this.mLast = v1;
                    int[] arr_v = this.mArrayIndices;
                    if(v1 >= arr_v.length) {
                        this.mDidFillOnce = true;
                        this.mLast = arr_v.length - 1;
                    }
                }
            }
            else {
                int v3 = -1;
                for(int v2 = 0; true; ++v2) {
                    if(v == -1 || v2 >= this.mCurrentSize) {
                        int v4 = this.mLast;
                        if(this.mDidFillOnce) {
                            int[] arr_v1 = this.mArrayIndices;
                            if(arr_v1[v4] != -1) {
                                v4 = arr_v1.length;
                            }
                        }
                        else {
                            ++v4;
                        }
                        if(v4 >= this.mArrayIndices.length && this.mCurrentSize < this.mArrayIndices.length) {
                            for(int v5 = 0; true; ++v5) {
                                int[] arr_v2 = this.mArrayIndices;
                                if(v5 >= arr_v2.length) {
                                    break;
                                }
                                if(arr_v2[v5] == -1) {
                                    v4 = v5;
                                    break;
                                }
                            }
                        }
                        int[] arr_v3 = this.mArrayIndices;
                        if(v4 >= arr_v3.length) {
                            v4 = arr_v3.length;
                            int v6 = this.mRowSize * 2;
                            this.mRowSize = v6;
                            this.mDidFillOnce = false;
                            this.mLast = v4 - 1;
                            this.mArrayValues = Arrays.copyOf(this.mArrayValues, v6);
                            this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.mRowSize);
                            this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.mRowSize);
                        }
                        this.mArrayIndices[v4] = solverVariable0.id;
                        this.mArrayValues[v4] = f;
                        if(v3 == -1) {
                            this.mArrayNextIndices[v4] = this.mHead;
                            this.mHead = v4;
                        }
                        else {
                            int[] arr_v4 = this.mArrayNextIndices;
                            arr_v4[v4] = arr_v4[v3];
                            arr_v4[v3] = v4;
                        }
                        ++solverVariable0.usageInRowCount;
                        solverVariable0.addToRow(this.mRow);
                        ++this.mCurrentSize;
                        if(!this.mDidFillOnce) {
                            ++this.mLast;
                        }
                        int[] arr_v5 = this.mArrayIndices;
                        if(this.mLast < arr_v5.length) {
                            break;
                        }
                        this.mDidFillOnce = true;
                        this.mLast = arr_v5.length - 1;
                        break;
                    }
                    if(this.mArrayIndices[v] == solverVariable0.id) {
                        float[] arr_f = this.mArrayValues;
                        float f1 = arr_f[v] + f;
                        if(f1 > -ArrayLinkedVariables.sEpsilon && f1 < ArrayLinkedVariables.sEpsilon) {
                            f1 = 0.0f;
                        }
                        arr_f[v] = f1;
                        if(f1 != 0.0f) {
                            break;
                        }
                        if(v == this.mHead) {
                            this.mHead = this.mArrayNextIndices[v];
                        }
                        else {
                            this.mArrayNextIndices[v3] = this.mArrayNextIndices[v];
                        }
                        if(z) {
                            solverVariable0.removeFromRow(this.mRow);
                        }
                        if(this.mDidFillOnce) {
                            this.mLast = v;
                        }
                        --solverVariable0.usageInRowCount;
                        --this.mCurrentSize;
                        return;
                    }
                    if(this.mArrayIndices[v] < solverVariable0.id) {
                        v3 = v;
                    }
                    v = this.mArrayNextIndices[v];
                }
            }
        }
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public final void clear() {
        int v = this.mHead;
        for(int v1 = 0; v != -1 && v1 < this.mCurrentSize; ++v1) {
            SolverVariable solverVariable0 = this.mCache.mIndexedVariables[this.mArrayIndices[v]];
            if(solverVariable0 != null) {
                solverVariable0.removeFromRow(this.mRow);
            }
            v = this.mArrayNextIndices[v];
        }
        this.mHead = -1;
        this.mLast = -1;
        this.mDidFillOnce = false;
        this.mCurrentSize = 0;
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public boolean contains(SolverVariable solverVariable0) {
        int v = this.mHead;
        if(v == -1) {
            return false;
        }
        for(int v1 = 0; v != -1 && v1 < this.mCurrentSize; ++v1) {
            if(this.mArrayIndices[v] == solverVariable0.id) {
                return true;
            }
            v = this.mArrayNextIndices[v];
        }
        return false;
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public void display() {
        int v = this.mCurrentSize;
        System.out.print("{ ");
        for(int v1 = 0; v1 < v; ++v1) {
            SolverVariable solverVariable0 = this.getVariable(v1);
            if(solverVariable0 != null) {
                System.out.print(solverVariable0 + " = " + this.getVariableValue(v1) + " ");
            }
        }
        System.out.println(" }");
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public void divideByAmount(float f) {
        int v = this.mHead;
        for(int v1 = 0; v != -1 && v1 < this.mCurrentSize; ++v1) {
            this.mArrayValues[v] /= f;
            v = this.mArrayNextIndices[v];
        }
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public final float get(SolverVariable solverVariable0) {
        int v = this.mHead;
        for(int v1 = 0; v != -1 && v1 < this.mCurrentSize; ++v1) {
            if(this.mArrayIndices[v] == solverVariable0.id) {
                return this.mArrayValues[v];
            }
            v = this.mArrayNextIndices[v];
        }
        return 0.0f;
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public int getCurrentSize() {
        return this.mCurrentSize;
    }

    public int getHead() {
        return this.mHead;
    }

    public final int getId(int v) {
        return this.mArrayIndices[v];
    }

    public final int getNextIndice(int v) {
        return this.mArrayNextIndices[v];
    }

    SolverVariable getPivotCandidate() {
        SolverVariable solverVariable0 = this.mCandidate;
        if(solverVariable0 == null) {
            int v = this.mHead;
            SolverVariable solverVariable1 = null;
            for(int v1 = 0; v != -1 && v1 < this.mCurrentSize; ++v1) {
                if(this.mArrayValues[v] < 0.0f) {
                    SolverVariable solverVariable2 = this.mCache.mIndexedVariables[this.mArrayIndices[v]];
                    if(solverVariable1 == null || solverVariable1.strength < solverVariable2.strength) {
                        solverVariable1 = solverVariable2;
                    }
                }
                v = this.mArrayNextIndices[v];
            }
            return solverVariable1;
        }
        return solverVariable0;
    }

    public final float getValue(int v) {
        return this.mArrayValues[v];
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public SolverVariable getVariable(int v) {
        int v1 = this.mHead;
        for(int v2 = 0; v1 != -1 && v2 < this.mCurrentSize; ++v2) {
            if(v2 == v) {
                return this.mCache.mIndexedVariables[this.mArrayIndices[v1]];
            }
            v1 = this.mArrayNextIndices[v1];
        }
        return null;
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public float getVariableValue(int v) {
        int v1 = this.mHead;
        for(int v2 = 0; v1 != -1 && v2 < this.mCurrentSize; ++v2) {
            if(v2 == v) {
                return this.mArrayValues[v1];
            }
            v1 = this.mArrayNextIndices[v1];
        }
        return 0.0f;
    }

    boolean hasAtLeastOnePositiveVariable() {
        int v = this.mHead;
        for(int v1 = 0; v != -1 && v1 < this.mCurrentSize; ++v1) {
            if(this.mArrayValues[v] > 0.0f) {
                return true;
            }
            v = this.mArrayNextIndices[v];
        }
        return false;
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public int indexOf(SolverVariable solverVariable0) {
        int v = this.mHead;
        if(v == -1) {
            return -1;
        }
        for(int v1 = 0; v != -1 && v1 < this.mCurrentSize; ++v1) {
            if(this.mArrayIndices[v] == solverVariable0.id) {
                return v;
            }
            v = this.mArrayNextIndices[v];
        }
        return -1;
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public void invert() {
        int v = this.mHead;
        for(int v1 = 0; v != -1 && v1 < this.mCurrentSize; ++v1) {
            this.mArrayValues[v] *= -1.0f;
            v = this.mArrayNextIndices[v];
        }
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public final void put(SolverVariable solverVariable0, float f) {
        if(f == 0.0f) {
            this.remove(solverVariable0, true);
            return;
        }
        int v = this.mHead;
        if(v == -1) {
            this.mHead = 0;
            this.mArrayValues[0] = f;
            this.mArrayIndices[0] = solverVariable0.id;
            this.mArrayNextIndices[0] = -1;
            ++solverVariable0.usageInRowCount;
            solverVariable0.addToRow(this.mRow);
            ++this.mCurrentSize;
            if(!this.mDidFillOnce) {
                int v1 = this.mLast + 1;
                this.mLast = v1;
                int[] arr_v = this.mArrayIndices;
                if(v1 >= arr_v.length) {
                    this.mDidFillOnce = true;
                    this.mLast = arr_v.length - 1;
                }
            }
        }
        else {
            int v3 = -1;
            for(int v2 = 0; v != -1 && v2 < this.mCurrentSize; ++v2) {
                if(this.mArrayIndices[v] == solverVariable0.id) {
                    this.mArrayValues[v] = f;
                    return;
                }
                if(this.mArrayIndices[v] < solverVariable0.id) {
                    v3 = v;
                }
                v = this.mArrayNextIndices[v];
            }
            int v4 = this.mLast;
            if(this.mDidFillOnce) {
                int[] arr_v1 = this.mArrayIndices;
                if(arr_v1[v4] != -1) {
                    v4 = arr_v1.length;
                }
            }
            else {
                ++v4;
            }
            if(v4 >= this.mArrayIndices.length && this.mCurrentSize < this.mArrayIndices.length) {
                for(int v5 = 0; true; ++v5) {
                    int[] arr_v2 = this.mArrayIndices;
                    if(v5 >= arr_v2.length) {
                        break;
                    }
                    if(arr_v2[v5] == -1) {
                        v4 = v5;
                        break;
                    }
                }
            }
            int[] arr_v3 = this.mArrayIndices;
            if(v4 >= arr_v3.length) {
                v4 = arr_v3.length;
                int v6 = this.mRowSize * 2;
                this.mRowSize = v6;
                this.mDidFillOnce = false;
                this.mLast = v4 - 1;
                this.mArrayValues = Arrays.copyOf(this.mArrayValues, v6);
                this.mArrayIndices = Arrays.copyOf(this.mArrayIndices, this.mRowSize);
                this.mArrayNextIndices = Arrays.copyOf(this.mArrayNextIndices, this.mRowSize);
            }
            this.mArrayIndices[v4] = solverVariable0.id;
            this.mArrayValues[v4] = f;
            if(v3 == -1) {
                this.mArrayNextIndices[v4] = this.mHead;
                this.mHead = v4;
            }
            else {
                int[] arr_v4 = this.mArrayNextIndices;
                arr_v4[v4] = arr_v4[v3];
                arr_v4[v3] = v4;
            }
            ++solverVariable0.usageInRowCount;
            solverVariable0.addToRow(this.mRow);
            int v7 = this.mCurrentSize + 1;
            this.mCurrentSize = v7;
            if(!this.mDidFillOnce) {
                ++this.mLast;
            }
            int[] arr_v5 = this.mArrayIndices;
            if(v7 >= arr_v5.length) {
                this.mDidFillOnce = true;
            }
            if(this.mLast >= arr_v5.length) {
                this.mDidFillOnce = true;
                this.mLast = arr_v5.length - 1;
            }
        }
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public final float remove(SolverVariable solverVariable0, boolean z) {
        if(this.mCandidate == solverVariable0) {
            this.mCandidate = null;
        }
        int v = this.mHead;
        if(v == -1) {
            return 0.0f;
        }
        int v1 = 0;
        int v2 = -1;
        while(v != -1 && v1 < this.mCurrentSize) {
            if(this.mArrayIndices[v] == solverVariable0.id) {
                if(v == this.mHead) {
                    this.mHead = this.mArrayNextIndices[v];
                }
                else {
                    this.mArrayNextIndices[v2] = this.mArrayNextIndices[v];
                }
                if(z) {
                    solverVariable0.removeFromRow(this.mRow);
                }
                --solverVariable0.usageInRowCount;
                --this.mCurrentSize;
                this.mArrayIndices[v] = -1;
                if(this.mDidFillOnce) {
                    this.mLast = v;
                }
                return this.mArrayValues[v];
            }
            ++v1;
            v2 = v;
            v = this.mArrayNextIndices[v];
        }
        return 0.0f;
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public int sizeInBytes() {
        return this.mArrayIndices.length * 12 + 36;
    }

    @Override
    public String toString() {
        int v = this.mHead;
        String s = "";
        for(int v1 = 0; v != -1 && v1 < this.mCurrentSize; ++v1) {
            s = s + " -> " + this.mArrayValues[v] + " : " + this.mCache.mIndexedVariables[this.mArrayIndices[v]];
            v = this.mArrayNextIndices[v];
        }
        return s;
    }

    @Override  // androidx.constraintlayout.core.ArrayRow$ArrayRowVariables
    public float use(ArrayRow arrayRow0, boolean z) {
        float f = this.get(arrayRow0.mVariable);
        this.remove(arrayRow0.mVariable, z);
        ArrayRowVariables arrayRow$ArrayRowVariables0 = arrayRow0.variables;
        int v = arrayRow$ArrayRowVariables0.getCurrentSize();
        for(int v1 = 0; v1 < v; ++v1) {
            SolverVariable solverVariable0 = arrayRow$ArrayRowVariables0.getVariable(v1);
            this.add(solverVariable0, arrayRow$ArrayRowVariables0.get(solverVariable0) * f, z);
        }
        return f;
    }
}

