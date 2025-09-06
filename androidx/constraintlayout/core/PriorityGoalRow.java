package androidx.constraintlayout.core;

import java.util.Arrays;
import java.util.Comparator;

public class PriorityGoalRow extends ArrayRow {
    class GoalVariableAccessor {
        PriorityGoalRow mRow;
        SolverVariable mVariable;

        GoalVariableAccessor(PriorityGoalRow priorityGoalRow1) {
            this.mRow = priorityGoalRow1;
        }

        public void add(SolverVariable solverVariable0) {
            for(int v = 0; v < 9; ++v) {
                float[] arr_f = this.mVariable.mGoalStrengthVector;
                arr_f[v] += solverVariable0.mGoalStrengthVector[v];
                if(Math.abs(this.mVariable.mGoalStrengthVector[v]) < 0.0001f) {
                    this.mVariable.mGoalStrengthVector[v] = 0.0f;
                }
            }
        }

        public boolean addToGoal(SolverVariable solverVariable0, float f) {
            boolean z = true;
            if(this.mVariable.inGoal) {
                for(int v1 = 0; v1 < 9; ++v1) {
                    float[] arr_f = this.mVariable.mGoalStrengthVector;
                    arr_f[v1] += solverVariable0.mGoalStrengthVector[v1] * f;
                    if(Math.abs(this.mVariable.mGoalStrengthVector[v1]) < 0.0001f) {
                        this.mVariable.mGoalStrengthVector[v1] = 0.0f;
                    }
                    else {
                        z = false;
                    }
                }
                if(z) {
                    PriorityGoalRow.this.removeGoal(this.mVariable);
                }
                return false;
            }
            for(int v = 0; v < 9; ++v) {
                float f1 = solverVariable0.mGoalStrengthVector[v];
                if(f1 == 0.0f) {
                    this.mVariable.mGoalStrengthVector[v] = 0.0f;
                }
                else {
                    this.mVariable.mGoalStrengthVector[v] = Math.abs(f1 * f) < 0.0001f ? 0.0f : f1 * f;
                }
            }
            return true;
        }

        public void init(SolverVariable solverVariable0) {
            this.mVariable = solverVariable0;
        }

        public final boolean isNegative() {
            for(int v = 8; v >= 0; --v) {
                float f = this.mVariable.mGoalStrengthVector[v];
                if(f > 0.0f) {
                    return false;
                }
                if(f < 0.0f) {
                    return true;
                }
            }
            return false;
        }

        public final boolean isNull() {
            for(int v = 0; v < 9; ++v) {
                if(this.mVariable.mGoalStrengthVector[v] != 0.0f) {
                    return false;
                }
            }
            return true;
        }

        public final boolean isSmallerThan(SolverVariable solverVariable0) {
            int v = 8;
            while(v >= 0) {
                float f = solverVariable0.mGoalStrengthVector[v];
                float f1 = this.mVariable.mGoalStrengthVector[v];
                if(f1 == f) {
                    --v;
                    continue;
                }
                return f1 < f;
            }
            return false;
        }

        public void reset() {
            Arrays.fill(this.mVariable.mGoalStrengthVector, 0.0f);
        }

        @Override
        public String toString() {
            String s = "[ ";
            if(this.mVariable != null) {
                for(int v = 0; v < 9; ++v) {
                    s = s + this.mVariable.mGoalStrengthVector[v] + " ";
                }
            }
            return s + "] " + this.mVariable;
        }
    }

    private static final boolean DEBUG = false;
    private static final float EPSILON = 0.0001f;
    static final int NOT_FOUND = -1;
    GoalVariableAccessor mAccessor;
    private SolverVariable[] mArrayGoals;
    Cache mCache;
    private int mNumGoals;
    private SolverVariable[] mSortArray;
    private int mTableSize;

    public PriorityGoalRow(Cache cache0) {
        super(cache0);
        this.mTableSize = 0x80;
        this.mArrayGoals = new SolverVariable[0x80];
        this.mSortArray = new SolverVariable[0x80];
        this.mNumGoals = 0;
        this.mAccessor = new GoalVariableAccessor(this, this);
        this.mCache = cache0;
    }

    @Override  // androidx.constraintlayout.core.ArrayRow
    public void addError(SolverVariable solverVariable0) {
        this.mAccessor.init(solverVariable0);
        this.mAccessor.reset();
        solverVariable0.mGoalStrengthVector[solverVariable0.strength] = 1.0f;
        this.addToGoal(solverVariable0);
    }

    private void addToGoal(SolverVariable solverVariable0) {
        int v3;
        SolverVariable[] arr_solverVariable = this.mArrayGoals;
        if(this.mNumGoals + 1 > arr_solverVariable.length) {
            SolverVariable[] arr_solverVariable1 = (SolverVariable[])Arrays.copyOf(arr_solverVariable, arr_solverVariable.length * 2);
            this.mArrayGoals = arr_solverVariable1;
            this.mSortArray = (SolverVariable[])Arrays.copyOf(arr_solverVariable1, arr_solverVariable1.length * 2);
        }
        SolverVariable[] arr_solverVariable2 = this.mArrayGoals;
        int v = this.mNumGoals;
        arr_solverVariable2[v] = solverVariable0;
        this.mNumGoals = v + 1;
        if(v + 1 > 1 && arr_solverVariable2[v].id > solverVariable0.id) {
            for(int v2 = 0; true; ++v2) {
                v3 = this.mNumGoals;
                if(v2 >= v3) {
                    break;
                }
                this.mSortArray[v2] = this.mArrayGoals[v2];
            }
            Arrays.sort(this.mSortArray, 0, v3, new Comparator() {
                public int compare(SolverVariable solverVariable0, SolverVariable solverVariable1) {
                    return solverVariable0.id - solverVariable1.id;
                }

                @Override
                public int compare(Object object0, Object object1) {
                    return this.compare(((SolverVariable)object0), ((SolverVariable)object1));
                }
            });
            for(int v1 = 0; v1 < this.mNumGoals; ++v1) {
                this.mArrayGoals[v1] = this.mSortArray[v1];
            }
        }
        solverVariable0.inGoal = true;
        solverVariable0.addToRow(this);
    }

    @Override  // androidx.constraintlayout.core.ArrayRow
    public void clear() {
        this.mNumGoals = 0;
        this.mConstantValue = 0.0f;
    }

    @Override  // androidx.constraintlayout.core.ArrayRow
    public SolverVariable getPivotCandidate(LinearSystem linearSystem0, boolean[] arr_z) {
        int v1 = -1;
        for(int v = 0; v < this.mNumGoals; ++v) {
            SolverVariable solverVariable0 = this.mArrayGoals[v];
            if(!arr_z[solverVariable0.id]) {
                this.mAccessor.init(solverVariable0);
                if(v1 != -1) {
                    if(this.mAccessor.isSmallerThan(this.mArrayGoals[v1])) {
                        v1 = v;
                    }
                }
                else if(this.mAccessor.isNegative()) {
                    v1 = v;
                }
            }
        }
        return v1 == -1 ? null : this.mArrayGoals[v1];
    }

    @Override  // androidx.constraintlayout.core.ArrayRow
    public boolean isEmpty() {
        return this.mNumGoals == 0;
    }

    private void removeGoal(SolverVariable solverVariable0) {
        int v1;
        for(int v = 0; v < this.mNumGoals; ++v) {
            if(this.mArrayGoals[v] == solverVariable0) {
                while(true) {
                    v1 = this.mNumGoals;
                    if(v >= v1 - 1) {
                        break;
                    }
                    this.mArrayGoals[v] = this.mArrayGoals[v + 1];
                    ++v;
                }
                this.mNumGoals = v1 - 1;
                solverVariable0.inGoal = false;
                return;
            }
        }
    }

    @Override  // androidx.constraintlayout.core.ArrayRow
    public String toString() {
        String s = " goal -> (" + this.mConstantValue + ") : ";
        for(int v = 0; v < this.mNumGoals; ++v) {
            this.mAccessor.init(this.mArrayGoals[v]);
            s = s + this.mAccessor + " ";
        }
        return s;
    }

    @Override  // androidx.constraintlayout.core.ArrayRow
    public void updateFromRow(LinearSystem linearSystem0, ArrayRow arrayRow0, boolean z) {
        SolverVariable solverVariable0 = arrayRow0.mVariable;
        if(solverVariable0 == null) {
            return;
        }
        ArrayRowVariables arrayRow$ArrayRowVariables0 = arrayRow0.variables;
        int v = arrayRow$ArrayRowVariables0.getCurrentSize();
        for(int v1 = 0; v1 < v; ++v1) {
            SolverVariable solverVariable1 = arrayRow$ArrayRowVariables0.getVariable(v1);
            float f = arrayRow$ArrayRowVariables0.getVariableValue(v1);
            this.mAccessor.init(solverVariable1);
            if(this.mAccessor.addToGoal(solverVariable0, f)) {
                this.addToGoal(solverVariable1);
            }
            this.mConstantValue += arrayRow0.mConstantValue * f;
        }
        this.removeGoal(solverVariable0);
    }
}

