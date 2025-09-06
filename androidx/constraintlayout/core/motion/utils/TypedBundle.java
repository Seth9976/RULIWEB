package androidx.constraintlayout.core.motion.utils;

import java.util.Arrays;

public class TypedBundle {
    private static final int INITIAL_BOOLEAN = 4;
    private static final int INITIAL_FLOAT = 10;
    private static final int INITIAL_INT = 10;
    private static final int INITIAL_STRING = 5;
    int mCountBoolean;
    int mCountFloat;
    int mCountInt;
    int mCountString;
    int[] mTypeBoolean;
    int[] mTypeFloat;
    int[] mTypeInt;
    int[] mTypeString;
    boolean[] mValueBoolean;
    float[] mValueFloat;
    int[] mValueInt;
    String[] mValueString;

    public TypedBundle() {
        this.mTypeInt = new int[10];
        this.mValueInt = new int[10];
        this.mCountInt = 0;
        this.mTypeFloat = new int[10];
        this.mValueFloat = new float[10];
        this.mCountFloat = 0;
        this.mTypeString = new int[5];
        this.mValueString = new String[5];
        this.mCountString = 0;
        this.mTypeBoolean = new int[4];
        this.mValueBoolean = new boolean[4];
        this.mCountBoolean = 0;
    }

    public void add(int v, float f) {
        int[] arr_v = this.mTypeFloat;
        if(this.mCountFloat >= arr_v.length) {
            this.mTypeFloat = Arrays.copyOf(arr_v, arr_v.length * 2);
            this.mValueFloat = Arrays.copyOf(this.mValueFloat, this.mValueFloat.length * 2);
        }
        int v1 = this.mCountFloat;
        this.mTypeFloat[v1] = v;
        this.mCountFloat = v1 + 1;
        this.mValueFloat[v1] = f;
    }

    public void add(int v, int v1) {
        int[] arr_v = this.mTypeInt;
        if(this.mCountInt >= arr_v.length) {
            this.mTypeInt = Arrays.copyOf(arr_v, arr_v.length * 2);
            this.mValueInt = Arrays.copyOf(this.mValueInt, this.mValueInt.length * 2);
        }
        int v2 = this.mCountInt;
        this.mTypeInt[v2] = v;
        this.mCountInt = v2 + 1;
        this.mValueInt[v2] = v1;
    }

    public void add(int v, String s) {
        int[] arr_v = this.mTypeString;
        if(this.mCountString >= arr_v.length) {
            this.mTypeString = Arrays.copyOf(arr_v, arr_v.length * 2);
            this.mValueString = (String[])Arrays.copyOf(this.mValueString, this.mValueString.length * 2);
        }
        int v1 = this.mCountString;
        this.mTypeString[v1] = v;
        this.mCountString = v1 + 1;
        this.mValueString[v1] = s;
    }

    public void add(int v, boolean z) {
        int[] arr_v = this.mTypeBoolean;
        if(this.mCountBoolean >= arr_v.length) {
            this.mTypeBoolean = Arrays.copyOf(arr_v, arr_v.length * 2);
            this.mValueBoolean = Arrays.copyOf(this.mValueBoolean, this.mValueBoolean.length * 2);
        }
        int v1 = this.mCountBoolean;
        this.mTypeBoolean[v1] = v;
        this.mCountBoolean = v1 + 1;
        this.mValueBoolean[v1] = z;
    }

    public void addIfNotNull(int v, String s) {
        if(s != null) {
            this.add(v, s);
        }
    }

    public void applyDelta(TypedBundle typedBundle0) {
        for(int v1 = 0; v1 < this.mCountInt; ++v1) {
            typedBundle0.add(this.mTypeInt[v1], this.mValueInt[v1]);
        }
        for(int v2 = 0; v2 < this.mCountFloat; ++v2) {
            typedBundle0.add(this.mTypeFloat[v2], this.mValueFloat[v2]);
        }
        for(int v3 = 0; v3 < this.mCountString; ++v3) {
            typedBundle0.add(this.mTypeString[v3], this.mValueString[v3]);
        }
        for(int v = 0; v < this.mCountBoolean; ++v) {
            typedBundle0.add(this.mTypeBoolean[v], this.mValueBoolean[v]);
        }
    }

    public void applyDelta(TypedValues typedValues0) {
        for(int v1 = 0; v1 < this.mCountInt; ++v1) {
            typedValues0.setValue(this.mTypeInt[v1], this.mValueInt[v1]);
        }
        for(int v2 = 0; v2 < this.mCountFloat; ++v2) {
            typedValues0.setValue(this.mTypeFloat[v2], this.mValueFloat[v2]);
        }
        for(int v3 = 0; v3 < this.mCountString; ++v3) {
            typedValues0.setValue(this.mTypeString[v3], this.mValueString[v3]);
        }
        for(int v = 0; v < this.mCountBoolean; ++v) {
            typedValues0.setValue(this.mTypeBoolean[v], this.mValueBoolean[v]);
        }
    }

    public void clear() {
        this.mCountBoolean = 0;
        this.mCountString = 0;
        this.mCountFloat = 0;
        this.mCountInt = 0;
    }

    public int getInteger(int v) {
        for(int v1 = 0; v1 < this.mCountInt; ++v1) {
            if(this.mTypeInt[v1] == v) {
                return this.mValueInt[v1];
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "TypedBundle{mCountInt=" + this.mCountInt + ", mCountFloat=" + this.mCountFloat + ", mCountString=" + this.mCountString + ", mCountBoolean=" + this.mCountBoolean + '}';
    }
}

