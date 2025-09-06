package androidx.constraintlayout.core;

import java.util.ArrayList;

public class ArrayRow implements Row {
    public interface ArrayRowVariables {
        void add(SolverVariable arg1, float arg2, boolean arg3);

        void clear();

        boolean contains(SolverVariable arg1);

        void display();

        void divideByAmount(float arg1);

        float get(SolverVariable arg1);

        int getCurrentSize();

        SolverVariable getVariable(int arg1);

        float getVariableValue(int arg1);

        int indexOf(SolverVariable arg1);

        void invert();

        void put(SolverVariable arg1, float arg2);

        float remove(SolverVariable arg1, boolean arg2);

        int sizeInBytes();

        float use(ArrayRow arg1, boolean arg2);
    }

    private static final boolean DEBUG = false;
    private static final boolean FULL_NEW_CHECK = false;
    float mConstantValue;
    boolean mIsSimpleDefinition;
    boolean mUsed;
    SolverVariable mVariable;
    ArrayList mVariablesToUpdate;
    public ArrayRowVariables variables;

    public ArrayRow() {
        this.mVariable = null;
        this.mConstantValue = 0.0f;
        this.mUsed = false;
        this.mVariablesToUpdate = new ArrayList();
        this.mIsSimpleDefinition = false;
    }

    public ArrayRow(Cache cache0) {
        this.mVariable = null;
        this.mConstantValue = 0.0f;
        this.mUsed = false;
        this.mVariablesToUpdate = new ArrayList();
        this.mIsSimpleDefinition = false;
        this.variables = new ArrayLinkedVariables(this, cache0);
    }

    public ArrayRow addError(LinearSystem linearSystem0, int v) {
        this.variables.put(linearSystem0.createErrorVariable(v, "ep"), 1.0f);
        this.variables.put(linearSystem0.createErrorVariable(v, "em"), -1.0f);
        return this;
    }

    @Override  // androidx.constraintlayout.core.LinearSystem$Row
    public void addError(SolverVariable solverVariable0) {
        float f = 1.0f;
        switch(solverVariable0.strength) {
            case 2: {
                f = 1000.0f;
                break;
            }
            case 3: {
                f = 1000000.0f;
                break;
            }
            case 4: {
                f = 1000000000.0f;
                break;
            }
            case 5: {
                f = 999999995904.0f;
            }
        }
        this.variables.put(solverVariable0, f);
    }

    ArrayRow addSingleError(SolverVariable solverVariable0, int v) {
        this.variables.put(solverVariable0, ((float)v));
        return this;
    }

    boolean chooseSubject(LinearSystem linearSystem0) {
        boolean z;
        SolverVariable solverVariable0 = this.chooseSubjectInVariables(linearSystem0);
        if(solverVariable0 == null) {
            z = true;
        }
        else {
            this.pivot(solverVariable0);
            z = false;
        }
        if(this.variables.getCurrentSize() == 0) {
            this.mIsSimpleDefinition = true;
        }
        return z;
    }

    SolverVariable chooseSubjectInVariables(LinearSystem linearSystem0) {
        boolean z2;
        boolean z3;
        int v = this.variables.getCurrentSize();
        SolverVariable solverVariable0 = null;
        SolverVariable solverVariable1 = null;
        int v1 = 0;
        boolean z = false;
        boolean z1 = false;
        float f = 0.0f;
        float f1 = 0.0f;
        while(v1 < v) {
            float f2 = this.variables.getVariableValue(v1);
            SolverVariable solverVariable2 = this.variables.getVariable(v1);
            if(solverVariable2.mType != Type.UNRESTRICTED) {
                if(solverVariable0 == null && f2 < 0.0f) {
                    if(solverVariable1 == null) {
                        z3 = this.isNew(solverVariable2, linearSystem0);
                        goto label_32;
                    }
                    else if(f1 > f2) {
                        z3 = this.isNew(solverVariable2, linearSystem0);
                    label_32:
                        z1 = z3;
                        f1 = f2;
                        solverVariable1 = solverVariable2;
                    }
                    else if(!z1 && this.isNew(solverVariable2, linearSystem0)) {
                        f1 = f2;
                        solverVariable1 = solverVariable2;
                        z1 = true;
                    }
                }
            }
            else if(solverVariable0 == null) {
                z2 = this.isNew(solverVariable2, linearSystem0);
                goto label_17;
            }
            else if(f > f2) {
                z2 = this.isNew(solverVariable2, linearSystem0);
            label_17:
                z = z2;
                f = f2;
                solverVariable0 = solverVariable2;
            }
            else if(!z && this.isNew(solverVariable2, linearSystem0)) {
                f = f2;
                solverVariable0 = solverVariable2;
                z = true;
            }
            ++v1;
        }
        return solverVariable0 == null ? solverVariable1 : solverVariable0;
    }

    @Override  // androidx.constraintlayout.core.LinearSystem$Row
    public void clear() {
        this.variables.clear();
        this.mVariable = null;
        this.mConstantValue = 0.0f;
    }

    ArrayRow createRowCentering(SolverVariable solverVariable0, SolverVariable solverVariable1, int v, float f, SolverVariable solverVariable2, SolverVariable solverVariable3, int v1) {
        if(solverVariable1 == solverVariable2) {
            this.variables.put(solverVariable0, 1.0f);
            this.variables.put(solverVariable3, 1.0f);
            this.variables.put(solverVariable1, -2.0f);
            return this;
        }
        if(f == 0.5f) {
            this.variables.put(solverVariable0, 1.0f);
            this.variables.put(solverVariable1, -1.0f);
            this.variables.put(solverVariable2, -1.0f);
            this.variables.put(solverVariable3, 1.0f);
            if(v <= 0 && v1 <= 0) {
                return this;
            }
            this.mConstantValue = (float)(v1 - v);
            return this;
        }
        if(f <= 0.0f) {
            this.variables.put(solverVariable0, -1.0f);
            this.variables.put(solverVariable1, 1.0f);
            this.mConstantValue = (float)v;
            return this;
        }
        if(f >= 1.0f) {
            this.variables.put(solverVariable3, -1.0f);
            this.variables.put(solverVariable2, 1.0f);
            this.mConstantValue = (float)(-v1);
            return this;
        }
        this.variables.put(solverVariable0, (1.0f - f) * 1.0f);
        this.variables.put(solverVariable1, (1.0f - f) * -1.0f);
        this.variables.put(solverVariable2, -1.0f * f);
        this.variables.put(solverVariable3, 1.0f * f);
        if(v <= 0 && v1 <= 0) {
            return this;
        }
        this.mConstantValue = ((float)(-v)) * (1.0f - f) + ((float)v1) * f;
        return this;
    }

    ArrayRow createRowDefinition(SolverVariable solverVariable0, int v) {
        this.mVariable = solverVariable0;
        solverVariable0.computedValue = (float)v;
        this.mConstantValue = (float)v;
        this.mIsSimpleDefinition = true;
        return this;
    }

    ArrayRow createRowDimensionPercent(SolverVariable solverVariable0, SolverVariable solverVariable1, float f) {
        this.variables.put(solverVariable0, -1.0f);
        this.variables.put(solverVariable1, f);
        return this;
    }

    public ArrayRow createRowDimensionRatio(SolverVariable solverVariable0, SolverVariable solverVariable1, SolverVariable solverVariable2, SolverVariable solverVariable3, float f) {
        this.variables.put(solverVariable0, -1.0f);
        this.variables.put(solverVariable1, 1.0f);
        this.variables.put(solverVariable2, f);
        this.variables.put(solverVariable3, -f);
        return this;
    }

    public ArrayRow createRowEqualDimension(float f, float f1, float f2, SolverVariable solverVariable0, int v, SolverVariable solverVariable1, int v1, SolverVariable solverVariable2, int v2, SolverVariable solverVariable3, int v3) {
        if(f1 != 0.0f && f != f2) {
            float f3 = f / f1 / (f2 / f1);
            this.mConstantValue = ((float)(-v - v1)) + ((float)v2) * f3 + ((float)v3) * f3;
            this.variables.put(solverVariable0, 1.0f);
            this.variables.put(solverVariable1, -1.0f);
            this.variables.put(solverVariable3, f3);
            this.variables.put(solverVariable2, -f3);
            return this;
        }
        this.mConstantValue = (float)(-v - v1 + v2 + v3);
        this.variables.put(solverVariable0, 1.0f);
        this.variables.put(solverVariable1, -1.0f);
        this.variables.put(solverVariable3, 1.0f);
        this.variables.put(solverVariable2, -1.0f);
        return this;
    }

    public ArrayRow createRowEqualMatchDimensions(float f, float f1, float f2, SolverVariable solverVariable0, SolverVariable solverVariable1, SolverVariable solverVariable2, SolverVariable solverVariable3) {
        this.mConstantValue = 0.0f;
        if(f1 != 0.0f && f != f2) {
            if(f == 0.0f) {
                this.variables.put(solverVariable0, 1.0f);
                this.variables.put(solverVariable1, -1.0f);
                return this;
            }
            if(f2 == 0.0f) {
                this.variables.put(solverVariable2, 1.0f);
                this.variables.put(solverVariable3, -1.0f);
                return this;
            }
            float f3 = f / f1 / (f2 / f1);
            this.variables.put(solverVariable0, 1.0f);
            this.variables.put(solverVariable1, -1.0f);
            this.variables.put(solverVariable3, f3);
            this.variables.put(solverVariable2, -f3);
            return this;
        }
        this.variables.put(solverVariable0, 1.0f);
        this.variables.put(solverVariable1, -1.0f);
        this.variables.put(solverVariable3, 1.0f);
        this.variables.put(solverVariable2, -1.0f);
        return this;
    }

    public ArrayRow createRowEquals(SolverVariable solverVariable0, int v) {
        if(v < 0) {
            this.mConstantValue = (float)(-v);
            this.variables.put(solverVariable0, 1.0f);
            return this;
        }
        this.mConstantValue = (float)v;
        this.variables.put(solverVariable0, -1.0f);
        return this;
    }

    public ArrayRow createRowEquals(SolverVariable solverVariable0, SolverVariable solverVariable1, int v) {
        boolean z = false;
        if(v != 0) {
            if(v < 0) {
                v = -v;
                z = true;
            }
            this.mConstantValue = (float)v;
        }
        if(!z) {
            this.variables.put(solverVariable0, -1.0f);
            this.variables.put(solverVariable1, 1.0f);
            return this;
        }
        this.variables.put(solverVariable0, 1.0f);
        this.variables.put(solverVariable1, -1.0f);
        return this;
    }

    public ArrayRow createRowGreaterThan(SolverVariable solverVariable0, int v, SolverVariable solverVariable1) {
        this.mConstantValue = (float)v;
        this.variables.put(solverVariable0, -1.0f);
        return this;
    }

    public ArrayRow createRowGreaterThan(SolverVariable solverVariable0, SolverVariable solverVariable1, SolverVariable solverVariable2, int v) {
        boolean z = false;
        if(v != 0) {
            if(v < 0) {
                v = -v;
                z = true;
            }
            this.mConstantValue = (float)v;
        }
        if(!z) {
            this.variables.put(solverVariable0, -1.0f);
            this.variables.put(solverVariable1, 1.0f);
            this.variables.put(solverVariable2, 1.0f);
            return this;
        }
        this.variables.put(solverVariable0, 1.0f);
        this.variables.put(solverVariable1, -1.0f);
        this.variables.put(solverVariable2, -1.0f);
        return this;
    }

    public ArrayRow createRowLowerThan(SolverVariable solverVariable0, SolverVariable solverVariable1, SolverVariable solverVariable2, int v) {
        boolean z = false;
        if(v != 0) {
            if(v < 0) {
                v = -v;
                z = true;
            }
            this.mConstantValue = (float)v;
        }
        if(!z) {
            this.variables.put(solverVariable0, -1.0f);
            this.variables.put(solverVariable1, 1.0f);
            this.variables.put(solverVariable2, -1.0f);
            return this;
        }
        this.variables.put(solverVariable0, 1.0f);
        this.variables.put(solverVariable1, -1.0f);
        this.variables.put(solverVariable2, 1.0f);
        return this;
    }

    public ArrayRow createRowWithAngle(SolverVariable solverVariable0, SolverVariable solverVariable1, SolverVariable solverVariable2, SolverVariable solverVariable3, float f) {
        this.variables.put(solverVariable2, 0.5f);
        this.variables.put(solverVariable3, 0.5f);
        this.variables.put(solverVariable0, -0.5f);
        this.variables.put(solverVariable1, -0.5f);
        this.mConstantValue = -f;
        return this;
    }

    void ensurePositiveConstant() {
        float f = this.mConstantValue;
        if(f < 0.0f) {
            this.mConstantValue = f * -1.0f;
            this.variables.invert();
        }
    }

    @Override  // androidx.constraintlayout.core.LinearSystem$Row
    public SolverVariable getKey() {
        return this.mVariable;
    }

    @Override  // androidx.constraintlayout.core.LinearSystem$Row
    public SolverVariable getPivotCandidate(LinearSystem linearSystem0, boolean[] arr_z) {
        return this.pickPivotInVariables(arr_z, null);
    }

    boolean hasKeyVariable() {
        return this.mVariable != null && (this.mVariable.mType == Type.UNRESTRICTED || this.mConstantValue >= 0.0f);
    }

    boolean hasVariable(SolverVariable solverVariable0) {
        return this.variables.contains(solverVariable0);
    }

    @Override  // androidx.constraintlayout.core.LinearSystem$Row
    public void initFromRow(Row linearSystem$Row0) {
        if(linearSystem$Row0 instanceof ArrayRow) {
            this.mVariable = null;
            this.variables.clear();
            for(int v = 0; v < ((ArrayRow)linearSystem$Row0).variables.getCurrentSize(); ++v) {
                SolverVariable solverVariable0 = ((ArrayRow)linearSystem$Row0).variables.getVariable(v);
                float f = ((ArrayRow)linearSystem$Row0).variables.getVariableValue(v);
                this.variables.add(solverVariable0, f, true);
            }
        }
    }

    @Override  // androidx.constraintlayout.core.LinearSystem$Row
    public boolean isEmpty() {
        return this.mVariable == null && this.mConstantValue == 0.0f && this.variables.getCurrentSize() == 0;
    }

    private boolean isNew(SolverVariable solverVariable0, LinearSystem linearSystem0) {
        return solverVariable0.usageInRowCount <= 1;
    }

    public SolverVariable pickPivot(SolverVariable solverVariable0) {
        return this.pickPivotInVariables(null, solverVariable0);
    }

    private SolverVariable pickPivotInVariables(boolean[] arr_z, SolverVariable solverVariable0) {
        int v = this.variables.getCurrentSize();
        SolverVariable solverVariable1 = null;
        float f = 0.0f;
        for(int v1 = 0; v1 < v; ++v1) {
            float f1 = this.variables.getVariableValue(v1);
            if(f1 < 0.0f) {
                SolverVariable solverVariable2 = this.variables.getVariable(v1);
                if((arr_z == null || !arr_z[solverVariable2.id]) && solverVariable2 != solverVariable0 && (solverVariable2.mType == Type.SLACK || solverVariable2.mType == Type.ERROR) && f1 < f) {
                    f = f1;
                    solverVariable1 = solverVariable2;
                }
            }
        }
        return solverVariable1;
    }

    void pivot(SolverVariable solverVariable0) {
        SolverVariable solverVariable1 = this.mVariable;
        if(solverVariable1 != null) {
            this.variables.put(solverVariable1, -1.0f);
            this.mVariable.mDefinitionId = -1;
            this.mVariable = null;
        }
        float f = this.variables.remove(solverVariable0, true);
        this.mVariable = solverVariable0;
        if(f * -1.0f == 1.0f) {
            return;
        }
        this.mConstantValue /= f * -1.0f;
        this.variables.divideByAmount(f * -1.0f);
    }

    public void reset() {
        this.mVariable = null;
        this.variables.clear();
        this.mConstantValue = 0.0f;
        this.mIsSimpleDefinition = false;
    }

    int sizeInBytes() {
        return this.mVariable == null ? this.variables.sizeInBytes() + 8 : this.variables.sizeInBytes() + 12;
    }

    String toReadableString() {
        boolean z;
        String s = (this.mVariable == null ? "0" : "" + this.mVariable) + " = ";
        if(this.mConstantValue == 0.0f) {
            z = false;
        }
        else {
            s = s + this.mConstantValue;
            z = true;
        }
        int v1 = this.variables.getCurrentSize();
        for(int v = 0; v < v1; ++v) {
            SolverVariable solverVariable0 = this.variables.getVariable(v);
            if(solverVariable0 != null) {
                float f = this.variables.getVariableValue(v);
                int v2 = Float.compare(f, 0.0f);
                if(v2 != 0) {
                    String s1 = solverVariable0.toString();
                    if(z) {
                        if(v2 > 0) {
                            s = s + " + ";
                        }
                        else {
                            s = s + " - ";
                            f *= -1.0f;
                        }
                    }
                    else if(f < 0.0f) {
                        s = s + "- ";
                        f *= -1.0f;
                    }
                    s = f == 1.0f ? s + s1 : s + f + " " + s1;
                    z = true;
                }
            }
        }
        return z ? s : s + "0.0";
    }

    @Override
    public String toString() {
        return this.toReadableString();
    }

    @Override  // androidx.constraintlayout.core.LinearSystem$Row
    public void updateFromFinalVariable(LinearSystem linearSystem0, SolverVariable solverVariable0, boolean z) {
        if(solverVariable0 != null && solverVariable0.isFinalValue) {
            float f = this.variables.get(solverVariable0);
            this.mConstantValue += solverVariable0.computedValue * f;
            this.variables.remove(solverVariable0, z);
            if(z) {
                solverVariable0.removeFromRow(this);
            }
            if(LinearSystem.SIMPLIFY_SYNONYMS && this.variables.getCurrentSize() == 0) {
                this.mIsSimpleDefinition = true;
                linearSystem0.hasSimpleDefinition = true;
            }
        }
    }

    @Override  // androidx.constraintlayout.core.LinearSystem$Row
    public void updateFromRow(LinearSystem linearSystem0, ArrayRow arrayRow0, boolean z) {
        float f = this.variables.use(arrayRow0, z);
        this.mConstantValue += arrayRow0.mConstantValue * f;
        if(z) {
            arrayRow0.mVariable.removeFromRow(this);
        }
        if(LinearSystem.SIMPLIFY_SYNONYMS && this.mVariable != null && this.variables.getCurrentSize() == 0) {
            this.mIsSimpleDefinition = true;
            linearSystem0.hasSimpleDefinition = true;
        }
    }

    public void updateFromSynonymVariable(LinearSystem linearSystem0, SolverVariable solverVariable0, boolean z) {
        if(solverVariable0 != null && solverVariable0.mIsSynonym) {
            float f = this.variables.get(solverVariable0);
            this.mConstantValue += solverVariable0.mSynonymDelta * f;
            this.variables.remove(solverVariable0, z);
            if(z) {
                solverVariable0.removeFromRow(this);
            }
            this.variables.add(linearSystem0.mCache.mIndexedVariables[solverVariable0.mSynonym], f, z);
            if(LinearSystem.SIMPLIFY_SYNONYMS && this.variables.getCurrentSize() == 0) {
                this.mIsSimpleDefinition = true;
                linearSystem0.hasSimpleDefinition = true;
            }
        }
    }

    @Override  // androidx.constraintlayout.core.LinearSystem$Row
    public void updateFromSystem(LinearSystem linearSystem0) {
        if(linearSystem0.mRows.length != 0) {
            boolean z = false;
            while(!z) {
                int v = this.variables.getCurrentSize();
                for(int v1 = 0; v1 < v; ++v1) {
                    SolverVariable solverVariable0 = this.variables.getVariable(v1);
                    if(solverVariable0.mDefinitionId != -1 || solverVariable0.isFinalValue || solverVariable0.mIsSynonym) {
                        this.mVariablesToUpdate.add(solverVariable0);
                    }
                }
                int v2 = this.mVariablesToUpdate.size();
                if(v2 > 0) {
                    for(int v3 = 0; v3 < v2; ++v3) {
                        SolverVariable solverVariable1 = (SolverVariable)this.mVariablesToUpdate.get(v3);
                        if(solverVariable1.isFinalValue) {
                            this.updateFromFinalVariable(linearSystem0, solverVariable1, true);
                        }
                        else if(solverVariable1.mIsSynonym) {
                            this.updateFromSynonymVariable(linearSystem0, solverVariable1, true);
                        }
                        else {
                            this.updateFromRow(linearSystem0, linearSystem0.mRows[solverVariable1.mDefinitionId], true);
                        }
                    }
                    this.mVariablesToUpdate.clear();
                }
                else {
                    z = true;
                }
            }
            if(LinearSystem.SIMPLIFY_SYNONYMS && this.mVariable != null && this.variables.getCurrentSize() == 0) {
                this.mIsSimpleDefinition = true;
                linearSystem0.hasSimpleDefinition = true;
            }
        }
    }
}

