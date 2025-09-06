package androidx.constraintlayout.core;

import java.util.Arrays;
import java.util.HashSet;

public class SolverVariable implements Comparable {
    public static enum Type {
        UNRESTRICTED,
        CONSTANT,
        SLACK,
        ERROR,
        UNKNOWN;

        private static Type[] $values() [...] // Inlined contents
    }

    private static final boolean DO_NOT_USE = false;
    private static final boolean INTERNAL_DEBUG = false;
    static final int MAX_STRENGTH = 9;
    public static final int STRENGTH_BARRIER = 6;
    public static final int STRENGTH_CENTERING = 7;
    public static final int STRENGTH_EQUALITY = 5;
    public static final int STRENGTH_FIXED = 8;
    public static final int STRENGTH_HIGH = 3;
    public static final int STRENGTH_HIGHEST = 4;
    public static final int STRENGTH_LOW = 1;
    public static final int STRENGTH_MEDIUM = 2;
    public static final int STRENGTH_NONE = 0;
    private static final boolean VAR_USE_HASH = false;
    public float computedValue;
    public int id;
    public boolean inGoal;
    public boolean isFinalValue;
    ArrayRow[] mClientEquations;
    int mClientEquationsCount;
    int mDefinitionId;
    float[] mGoalStrengthVector;
    HashSet mInRows;
    boolean mIsSynonym;
    private String mName;
    float[] mStrengthVector;
    int mSynonym;
    float mSynonymDelta;
    Type mType;
    private static int sUniqueConstantId = 1;
    private static int sUniqueErrorId = 1;
    private static int sUniqueId = 1;
    private static int sUniqueSlackId = 1;
    private static int sUniqueUnrestrictedId = 1;
    public int strength;
    public int usageInRowCount;

    static {
    }

    public SolverVariable(Type solverVariable$Type0, String s) {
        this.id = -1;
        this.mDefinitionId = -1;
        this.strength = 0;
        this.isFinalValue = false;
        this.mStrengthVector = new float[9];
        this.mGoalStrengthVector = new float[9];
        this.mClientEquations = new ArrayRow[16];
        this.mClientEquationsCount = 0;
        this.usageInRowCount = 0;
        this.mIsSynonym = false;
        this.mSynonym = -1;
        this.mSynonymDelta = 0.0f;
        this.mInRows = null;
        this.mType = solverVariable$Type0;
    }

    public SolverVariable(String s, Type solverVariable$Type0) {
        this.id = -1;
        this.mDefinitionId = -1;
        this.strength = 0;
        this.isFinalValue = false;
        this.mStrengthVector = new float[9];
        this.mGoalStrengthVector = new float[9];
        this.mClientEquations = new ArrayRow[16];
        this.mClientEquationsCount = 0;
        this.usageInRowCount = 0;
        this.mIsSynonym = false;
        this.mSynonym = -1;
        this.mSynonymDelta = 0.0f;
        this.mInRows = null;
        this.mName = s;
        this.mType = solverVariable$Type0;
    }

    public final void addToRow(ArrayRow arrayRow0) {
        for(int v = 0; true; ++v) {
            int v1 = this.mClientEquationsCount;
            if(v >= v1) {
                break;
            }
            if(this.mClientEquations[v] == arrayRow0) {
                return;
            }
        }
        ArrayRow[] arr_arrayRow = this.mClientEquations;
        if(v1 >= arr_arrayRow.length) {
            this.mClientEquations = (ArrayRow[])Arrays.copyOf(arr_arrayRow, arr_arrayRow.length * 2);
        }
        int v2 = this.mClientEquationsCount;
        this.mClientEquations[v2] = arrayRow0;
        this.mClientEquationsCount = v2 + 1;
    }

    void clearStrengths() {
        for(int v = 0; v < 9; ++v) {
            this.mStrengthVector[v] = 0.0f;
        }
    }

    public int compareTo(SolverVariable solverVariable0) {
        return this.id - solverVariable0.id;
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((SolverVariable)object0));
    }

    public String getName() {
        return this.mName;
    }

    private static String getUniqueName(Type solverVariable$Type0, String s) {
        if(s != null) {
            return s + SolverVariable.sUniqueErrorId;
        }
        switch(solverVariable$Type0.ordinal()) {
            case 0: {
                int v = SolverVariable.sUniqueUnrestrictedId + 1;
                SolverVariable.sUniqueUnrestrictedId = v;
                return "U" + v;
            }
            case 1: {
                int v1 = SolverVariable.sUniqueConstantId + 1;
                SolverVariable.sUniqueConstantId = v1;
                return "C" + v1;
            }
            case 2: {
                int v2 = SolverVariable.sUniqueSlackId + 1;
                SolverVariable.sUniqueSlackId = v2;
                return "S" + v2;
            }
            case 3: {
                int v3 = SolverVariable.sUniqueErrorId + 1;
                SolverVariable.sUniqueErrorId = v3;
                return "e" + v3;
            }
            case 4: {
                int v4 = SolverVariable.sUniqueId + 1;
                SolverVariable.sUniqueId = v4;
                return "V" + v4;
            }
            default: {
                throw new AssertionError(solverVariable$Type0.name());
            }
        }
    }

    static void increaseErrorId() {
        ++SolverVariable.sUniqueErrorId;
    }

    public final void removeFromRow(ArrayRow arrayRow0) {
        int v = this.mClientEquationsCount;
        for(int v1 = 0; v1 < v; ++v1) {
            if(this.mClientEquations[v1] == arrayRow0) {
                while(v1 < v - 1) {
                    this.mClientEquations[v1] = this.mClientEquations[v1 + 1];
                    ++v1;
                }
                --this.mClientEquationsCount;
                return;
            }
        }
    }

    public void reset() {
        this.mName = null;
        this.mType = Type.UNKNOWN;
        this.strength = 0;
        this.id = -1;
        this.mDefinitionId = -1;
        this.computedValue = 0.0f;
        this.isFinalValue = false;
        this.mIsSynonym = false;
        this.mSynonym = -1;
        this.mSynonymDelta = 0.0f;
        int v = this.mClientEquationsCount;
        for(int v1 = 0; v1 < v; ++v1) {
            this.mClientEquations[v1] = null;
        }
        this.mClientEquationsCount = 0;
        this.usageInRowCount = 0;
        this.inGoal = false;
        Arrays.fill(this.mGoalStrengthVector, 0.0f);
    }

    public void setFinalValue(LinearSystem linearSystem0, float f) {
        this.computedValue = f;
        this.isFinalValue = true;
        this.mIsSynonym = false;
        this.mSynonym = -1;
        this.mSynonymDelta = 0.0f;
        int v = this.mClientEquationsCount;
        this.mDefinitionId = -1;
        for(int v1 = 0; v1 < v; ++v1) {
            this.mClientEquations[v1].updateFromFinalVariable(linearSystem0, this, false);
        }
        this.mClientEquationsCount = 0;
    }

    public void setName(String s) {
        this.mName = s;
    }

    public void setSynonym(LinearSystem linearSystem0, SolverVariable solverVariable0, float f) {
        this.mIsSynonym = true;
        this.mSynonym = solverVariable0.id;
        this.mSynonymDelta = f;
        int v = this.mClientEquationsCount;
        this.mDefinitionId = -1;
        for(int v1 = 0; v1 < v; ++v1) {
            this.mClientEquations[v1].updateFromSynonymVariable(linearSystem0, this, false);
        }
        this.mClientEquationsCount = 0;
        linearSystem0.displayReadableRows();
    }

    public void setType(Type solverVariable$Type0, String s) {
        this.mType = solverVariable$Type0;
    }

    String strengthsToString() {
        String s = this + "[";
        boolean z = false;
        boolean z1 = true;
        for(int v = 0; v < this.mStrengthVector.length; ++v) {
            String s1 = s + this.mStrengthVector[v];
            float[] arr_f = this.mStrengthVector;
            float f = arr_f[v];
            if(f > 0.0f) {
                z = false;
            }
            else if(f < 0.0f) {
                z = true;
            }
            if(f != 0.0f) {
                z1 = false;
            }
            s = v >= arr_f.length - 1 ? s1 + "] " : s1 + ", ";
        }
        if(z) {
            s = s + " (-)";
        }
        return z1 ? s + " (*)" : s;
    }

    @Override
    public String toString() {
        return this.mName == null ? "" + this.id : "" + this.mName;
    }

    public final void updateReferencesWithNewDefinition(LinearSystem linearSystem0, ArrayRow arrayRow0) {
        int v = this.mClientEquationsCount;
        for(int v1 = 0; v1 < v; ++v1) {
            this.mClientEquations[v1].updateFromRow(linearSystem0, arrayRow0, false);
        }
        this.mClientEquationsCount = 0;
    }
}

