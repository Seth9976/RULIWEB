package androidx.constraintlayout.core;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.Arrays;
import java.util.HashMap;

public class LinearSystem {
    interface Row {
        void addError(SolverVariable arg1);

        void clear();

        SolverVariable getKey();

        SolverVariable getPivotCandidate(LinearSystem arg1, boolean[] arg2);

        void initFromRow(Row arg1);

        boolean isEmpty();

        void updateFromFinalVariable(LinearSystem arg1, SolverVariable arg2, boolean arg3);

        void updateFromRow(LinearSystem arg1, ArrayRow arg2, boolean arg3);

        void updateFromSystem(LinearSystem arg1);
    }

    static class ValuesRow extends ArrayRow {
        ValuesRow(Cache cache0) {
            this.variables = new SolverVariableValues(this, cache0);
        }
    }

    public static long ARRAY_ROW_CREATION = 0L;
    public static final boolean DEBUG = false;
    private static final boolean DEBUG_CONSTRAINTS = false;
    private static final boolean DO_NOT_USE = false;
    public static final boolean FULL_DEBUG = false;
    public static long OPTIMIZED_ARRAY_ROW_CREATION = 0L;
    public static boolean OPTIMIZED_ENGINE = false;
    public static boolean SIMPLIFY_SYNONYMS = true;
    public static boolean SKIP_COLUMNS = true;
    public static boolean USE_BASIC_SYNONYMS = true;
    public static boolean USE_DEPENDENCY_ORDERING = false;
    public static boolean USE_SYNONYMS = true;
    public boolean graphOptimizer;
    public boolean hasSimpleDefinition;
    private boolean[] mAlreadyTestedCandidates;
    final Cache mCache;
    private Row mGoal;
    private int mMaxColumns;
    private int mMaxRows;
    int mNumColumns;
    int mNumRows;
    private int mPoolSize;
    private SolverVariable[] mPoolVariables;
    private int mPoolVariablesCount;
    ArrayRow[] mRows;
    private int mTableSize;
    private Row mTempGoal;
    private HashMap mVariables;
    int mVariablesID;
    public boolean newgraphOptimizer;
    public static Metrics sMetrics;

    static {
    }

    public LinearSystem() {
        this.mPoolSize = 1000;
        this.hasSimpleDefinition = false;
        this.mVariablesID = 0;
        this.mVariables = null;
        this.mTableSize = 0x20;
        this.mMaxColumns = 0x20;
        this.graphOptimizer = false;
        this.newgraphOptimizer = false;
        this.mAlreadyTestedCandidates = new boolean[0x20];
        this.mNumColumns = 1;
        this.mNumRows = 0;
        this.mMaxRows = 0x20;
        this.mPoolVariables = new SolverVariable[1000];
        this.mPoolVariablesCount = 0;
        this.mRows = new ArrayRow[0x20];
        this.releaseRows();
        Cache cache0 = new Cache();
        this.mCache = cache0;
        this.mGoal = new PriorityGoalRow(cache0);
        if(LinearSystem.OPTIMIZED_ENGINE) {
            this.mTempGoal = new ValuesRow(cache0);
            return;
        }
        this.mTempGoal = new ArrayRow(cache0);
    }

    private SolverVariable acquireSolverVariable(Type solverVariable$Type0, String s) {
        SolverVariable solverVariable0 = (SolverVariable)this.mCache.mSolverVariablePool.acquire();
        if(solverVariable0 == null) {
            solverVariable0 = new SolverVariable(solverVariable$Type0, s);
        }
        else {
            solverVariable0.reset();
        }
        solverVariable0.setType(solverVariable$Type0, s);
        int v = this.mPoolSize;
        if(this.mPoolVariablesCount >= v) {
            this.mPoolSize = v * 2;
            this.mPoolVariables = (SolverVariable[])Arrays.copyOf(this.mPoolVariables, v * 2);
        }
        int v1 = this.mPoolVariablesCount;
        this.mPoolVariablesCount = v1 + 1;
        this.mPoolVariables[v1] = solverVariable0;
        return solverVariable0;
    }

    public void addCenterPoint(ConstraintWidget constraintWidget0, ConstraintWidget constraintWidget1, float f, int v) {
        SolverVariable solverVariable0 = this.createObjectVariable(constraintWidget0.getAnchor(androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.LEFT));
        SolverVariable solverVariable1 = this.createObjectVariable(constraintWidget0.getAnchor(androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.TOP));
        SolverVariable solverVariable2 = this.createObjectVariable(constraintWidget0.getAnchor(androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.RIGHT));
        SolverVariable solverVariable3 = this.createObjectVariable(constraintWidget0.getAnchor(androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.BOTTOM));
        SolverVariable solverVariable4 = this.createObjectVariable(constraintWidget1.getAnchor(androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.LEFT));
        SolverVariable solverVariable5 = this.createObjectVariable(constraintWidget1.getAnchor(androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.TOP));
        SolverVariable solverVariable6 = this.createObjectVariable(constraintWidget1.getAnchor(androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.RIGHT));
        SolverVariable solverVariable7 = this.createObjectVariable(constraintWidget1.getAnchor(androidx.constraintlayout.core.widgets.ConstraintAnchor.Type.BOTTOM));
        ArrayRow arrayRow0 = this.createRow();
        arrayRow0.createRowWithAngle(solverVariable1, solverVariable3, solverVariable5, solverVariable7, ((float)(Math.sin(f) * ((double)v))));
        this.addConstraint(arrayRow0);
        ArrayRow arrayRow1 = this.createRow();
        arrayRow1.createRowWithAngle(solverVariable0, solverVariable2, solverVariable4, solverVariable6, ((float)(Math.cos(f) * ((double)v))));
        this.addConstraint(arrayRow1);
    }

    public void addCentering(SolverVariable solverVariable0, SolverVariable solverVariable1, int v, float f, SolverVariable solverVariable2, SolverVariable solverVariable3, int v1, int v2) {
        ArrayRow arrayRow0 = this.createRow();
        arrayRow0.createRowCentering(solverVariable0, solverVariable1, v, f, solverVariable2, solverVariable3, v1);
        if(v2 != 8) {
            arrayRow0.addError(this, v2);
        }
        this.addConstraint(arrayRow0);
    }

    public void addConstraint(ArrayRow arrayRow0) {
        if(arrayRow0 != null) {
            Metrics metrics0 = LinearSystem.sMetrics;
            if(metrics0 != null) {
                ++metrics0.constraints;
                if(arrayRow0.mIsSimpleDefinition) {
                    ++LinearSystem.sMetrics.simpleconstraints;
                }
            }
            int v = 1;
            if(this.mNumRows + 1 >= this.mMaxRows || this.mNumColumns + 1 >= this.mMaxColumns) {
                this.increaseTableSize();
            }
            int v1 = 0;
            if(arrayRow0.mIsSimpleDefinition) {
            label_43:
                if(v1 == 0) {
                    this.addRow(arrayRow0);
                }
            }
            else {
                arrayRow0.updateFromSystem(this);
                if(!arrayRow0.isEmpty()) {
                    arrayRow0.ensurePositiveConstant();
                    if(arrayRow0.chooseSubject(this)) {
                        SolverVariable solverVariable0 = this.createExtraVariable();
                        arrayRow0.mVariable = solverVariable0;
                        int v2 = this.mNumRows;
                        this.addRow(arrayRow0);
                        if(this.mNumRows == v2 + 1) {
                            this.mTempGoal.initFromRow(arrayRow0);
                            this.optimize(this.mTempGoal, true);
                            if(solverVariable0.mDefinitionId == -1) {
                                if(arrayRow0.mVariable == solverVariable0) {
                                    SolverVariable solverVariable1 = arrayRow0.pickPivot(solverVariable0);
                                    if(solverVariable1 != null) {
                                        Metrics metrics1 = LinearSystem.sMetrics;
                                        if(metrics1 != null) {
                                            ++metrics1.pivots;
                                        }
                                        arrayRow0.pivot(solverVariable1);
                                    }
                                }
                                if(!arrayRow0.mIsSimpleDefinition) {
                                    arrayRow0.mVariable.updateReferencesWithNewDefinition(this, arrayRow0);
                                }
                                if(LinearSystem.OPTIMIZED_ENGINE) {
                                    this.mCache.mOptimizedArrayRowPool.release(arrayRow0);
                                }
                                else {
                                    this.mCache.mArrayRowPool.release(arrayRow0);
                                }
                                --this.mNumRows;
                            }
                        }
                        else {
                            v = 0;
                        }
                    }
                    else {
                        v = 0;
                    }
                    if(arrayRow0.hasKeyVariable()) {
                        v1 = v;
                        goto label_43;
                    }
                }
            }
        }
    }

    public ArrayRow addEquality(SolverVariable solverVariable0, SolverVariable solverVariable1, int v, int v1) {
        Metrics metrics0 = LinearSystem.sMetrics;
        if(metrics0 != null) {
            ++metrics0.mSimpleEquations;
        }
        if(LinearSystem.USE_BASIC_SYNONYMS && v1 == 8 && solverVariable1.isFinalValue && solverVariable0.mDefinitionId == -1) {
            solverVariable0.setFinalValue(this, solverVariable1.computedValue + ((float)v));
            return null;
        }
        ArrayRow arrayRow0 = this.createRow();
        arrayRow0.createRowEquals(solverVariable0, solverVariable1, v);
        if(v1 != 8) {
            arrayRow0.addError(this, v1);
        }
        this.addConstraint(arrayRow0);
        return arrayRow0;
    }

    public void addEquality(SolverVariable solverVariable0, int v) {
        Metrics metrics0 = LinearSystem.sMetrics;
        if(metrics0 != null) {
            ++metrics0.mSimpleEquations;
        }
        if(LinearSystem.USE_BASIC_SYNONYMS && solverVariable0.mDefinitionId == -1) {
            solverVariable0.setFinalValue(this, ((float)v));
            for(int v1 = 0; v1 < this.mVariablesID + 1; ++v1) {
                SolverVariable solverVariable1 = this.mCache.mIndexedVariables[v1];
                if(solverVariable1 != null && solverVariable1.mIsSynonym && solverVariable1.mSynonym == solverVariable0.id) {
                    solverVariable1.setFinalValue(this, solverVariable1.mSynonymDelta + ((float)v));
                }
            }
            return;
        }
        int v2 = solverVariable0.mDefinitionId;
        if(solverVariable0.mDefinitionId != -1) {
            ArrayRow arrayRow0 = this.mRows[v2];
            if(arrayRow0.mIsSimpleDefinition) {
                arrayRow0.mConstantValue = (float)v;
                return;
            }
            if(arrayRow0.variables.getCurrentSize() == 0) {
                arrayRow0.mIsSimpleDefinition = true;
                arrayRow0.mConstantValue = (float)v;
                return;
            }
            ArrayRow arrayRow1 = this.createRow();
            arrayRow1.createRowEquals(solverVariable0, v);
            this.addConstraint(arrayRow1);
            return;
        }
        ArrayRow arrayRow2 = this.createRow();
        arrayRow2.createRowDefinition(solverVariable0, v);
        this.addConstraint(arrayRow2);
    }

    public void addGreaterBarrier(SolverVariable solverVariable0, SolverVariable solverVariable1, int v, boolean z) {
        ArrayRow arrayRow0 = this.createRow();
        SolverVariable solverVariable2 = this.createSlackVariable();
        solverVariable2.strength = 0;
        arrayRow0.createRowGreaterThan(solverVariable0, solverVariable1, solverVariable2, v);
        this.addConstraint(arrayRow0);
    }

    public void addGreaterThan(SolverVariable solverVariable0, SolverVariable solverVariable1, int v, int v1) {
        ArrayRow arrayRow0 = this.createRow();
        SolverVariable solverVariable2 = this.createSlackVariable();
        solverVariable2.strength = 0;
        arrayRow0.createRowGreaterThan(solverVariable0, solverVariable1, solverVariable2, v);
        if(v1 != 8) {
            this.addSingleError(arrayRow0, ((int)(arrayRow0.variables.get(solverVariable2) * -1.0f)), v1);
        }
        this.addConstraint(arrayRow0);
    }

    public void addLowerBarrier(SolverVariable solverVariable0, SolverVariable solverVariable1, int v, boolean z) {
        ArrayRow arrayRow0 = this.createRow();
        SolverVariable solverVariable2 = this.createSlackVariable();
        solverVariable2.strength = 0;
        arrayRow0.createRowLowerThan(solverVariable0, solverVariable1, solverVariable2, v);
        this.addConstraint(arrayRow0);
    }

    public void addLowerThan(SolverVariable solverVariable0, SolverVariable solverVariable1, int v, int v1) {
        ArrayRow arrayRow0 = this.createRow();
        SolverVariable solverVariable2 = this.createSlackVariable();
        solverVariable2.strength = 0;
        arrayRow0.createRowLowerThan(solverVariable0, solverVariable1, solverVariable2, v);
        if(v1 != 8) {
            this.addSingleError(arrayRow0, ((int)(arrayRow0.variables.get(solverVariable2) * -1.0f)), v1);
        }
        this.addConstraint(arrayRow0);
    }

    public void addRatio(SolverVariable solverVariable0, SolverVariable solverVariable1, SolverVariable solverVariable2, SolverVariable solverVariable3, float f, int v) {
        ArrayRow arrayRow0 = this.createRow();
        arrayRow0.createRowDimensionRatio(solverVariable0, solverVariable1, solverVariable2, solverVariable3, f);
        if(v != 8) {
            arrayRow0.addError(this, v);
        }
        this.addConstraint(arrayRow0);
    }

    private void addRow(ArrayRow arrayRow0) {
        int v3;
        if(!LinearSystem.SIMPLIFY_SYNONYMS || !arrayRow0.mIsSimpleDefinition) {
            this.mRows[this.mNumRows] = arrayRow0;
            arrayRow0.mVariable.mDefinitionId = this.mNumRows;
            ++this.mNumRows;
            arrayRow0.mVariable.updateReferencesWithNewDefinition(this, arrayRow0);
        }
        else {
            arrayRow0.mVariable.setFinalValue(this, arrayRow0.mConstantValue);
        }
        if(LinearSystem.SIMPLIFY_SYNONYMS && this.hasSimpleDefinition) {
            for(int v = 0; v < this.mNumRows; ++v) {
                if(this.mRows[v] == null) {
                    System.out.println("WTF");
                }
                ArrayRow arrayRow1 = this.mRows[v];
                if(arrayRow1 != null && arrayRow1.mIsSimpleDefinition) {
                    ArrayRow arrayRow2 = this.mRows[v];
                    arrayRow2.mVariable.setFinalValue(this, arrayRow2.mConstantValue);
                    if(LinearSystem.OPTIMIZED_ENGINE) {
                        this.mCache.mOptimizedArrayRowPool.release(arrayRow2);
                    }
                    else {
                        this.mCache.mArrayRowPool.release(arrayRow2);
                    }
                    this.mRows[v] = null;
                    int v1 = v + 1;
                    int v2 = v1;
                    while(true) {
                        v3 = this.mNumRows;
                        if(v1 >= v3) {
                            break;
                        }
                        ArrayRow[] arr_arrayRow = this.mRows;
                        ArrayRow arrayRow3 = arr_arrayRow[v1];
                        arr_arrayRow[v1 - 1] = arrayRow3;
                        if(arrayRow3.mVariable.mDefinitionId == v1) {
                            this.mRows[v1 - 1].mVariable.mDefinitionId = v1 - 1;
                        }
                        v2 = v1;
                        ++v1;
                    }
                    if(v2 < v3) {
                        this.mRows[v2] = null;
                    }
                    this.mNumRows = v3 - 1;
                    --v;
                }
            }
            this.hasSimpleDefinition = false;
        }
    }

    void addSingleError(ArrayRow arrayRow0, int v, int v1) {
        arrayRow0.addSingleError(this.createErrorVariable(v1, null), v);
    }

    public void addSynonym(SolverVariable solverVariable0, SolverVariable solverVariable1, int v) {
        if(solverVariable0.mDefinitionId == -1 && v == 0) {
            if(solverVariable1.mIsSynonym) {
                solverVariable1 = this.mCache.mIndexedVariables[solverVariable1.mSynonym];
            }
            if(solverVariable0.mIsSynonym) {
                SolverVariable solverVariable2 = this.mCache.mIndexedVariables[solverVariable0.mSynonym];
                return;
            }
            solverVariable0.setSynonym(this, solverVariable1, 0.0f);
            return;
        }
        this.addEquality(solverVariable0, solverVariable1, v, 8);
    }

    final void cleanupRows() {
        int v2;
        for(int v = 0; v < this.mNumRows; ++v) {
            ArrayRow arrayRow0 = this.mRows[v];
            if(arrayRow0.variables.getCurrentSize() == 0) {
                arrayRow0.mIsSimpleDefinition = true;
            }
            if(arrayRow0.mIsSimpleDefinition) {
                arrayRow0.mVariable.computedValue = arrayRow0.mConstantValue;
                arrayRow0.mVariable.removeFromRow(arrayRow0);
                for(int v1 = v; true; ++v1) {
                    v2 = this.mNumRows;
                    if(v1 >= v2 - 1) {
                        break;
                    }
                    this.mRows[v1] = this.mRows[v1 + 1];
                }
                this.mRows[v2 - 1] = null;
                this.mNumRows = v2 - 1;
                --v;
                if(LinearSystem.OPTIMIZED_ENGINE) {
                    this.mCache.mOptimizedArrayRowPool.release(arrayRow0);
                }
                else {
                    this.mCache.mArrayRowPool.release(arrayRow0);
                }
            }
        }
    }

    private void computeValues() {
        for(int v = 0; v < this.mNumRows; ++v) {
            ArrayRow arrayRow0 = this.mRows[v];
            arrayRow0.mVariable.computedValue = arrayRow0.mConstantValue;
        }
    }

    public SolverVariable createErrorVariable(int v, String s) {
        Metrics metrics0 = LinearSystem.sMetrics;
        if(metrics0 != null) {
            ++metrics0.errors;
        }
        if(this.mNumColumns + 1 >= this.mMaxColumns) {
            this.increaseTableSize();
        }
        SolverVariable solverVariable0 = this.acquireSolverVariable(Type.ERROR, s);
        int v1 = this.mVariablesID + 1;
        this.mVariablesID = v1;
        ++this.mNumColumns;
        solverVariable0.id = v1;
        solverVariable0.strength = v;
        this.mCache.mIndexedVariables[this.mVariablesID] = solverVariable0;
        this.mGoal.addError(solverVariable0);
        return solverVariable0;
    }

    public SolverVariable createExtraVariable() {
        Metrics metrics0 = LinearSystem.sMetrics;
        if(metrics0 != null) {
            ++metrics0.extravariables;
        }
        if(this.mNumColumns + 1 >= this.mMaxColumns) {
            this.increaseTableSize();
        }
        SolverVariable solverVariable0 = this.acquireSolverVariable(Type.SLACK, null);
        int v = this.mVariablesID + 1;
        this.mVariablesID = v;
        ++this.mNumColumns;
        solverVariable0.id = v;
        this.mCache.mIndexedVariables[this.mVariablesID] = solverVariable0;
        return solverVariable0;
    }

    public SolverVariable createObjectVariable(Object object0) {
        SolverVariable solverVariable0 = null;
        if(object0 == null) {
            return null;
        }
        if(this.mNumColumns + 1 >= this.mMaxColumns) {
            this.increaseTableSize();
        }
        if(object0 instanceof ConstraintAnchor) {
            solverVariable0 = ((ConstraintAnchor)object0).getSolverVariable();
            if(solverVariable0 == null) {
                ((ConstraintAnchor)object0).resetSolverVariable(this.mCache);
                solverVariable0 = ((ConstraintAnchor)object0).getSolverVariable();
            }
            if(solverVariable0.id != -1 && solverVariable0.id <= this.mVariablesID && this.mCache.mIndexedVariables[solverVariable0.id] != null) {
                return solverVariable0;
            }
            if(solverVariable0.id != -1) {
                solverVariable0.reset();
            }
            int v = this.mVariablesID + 1;
            this.mVariablesID = v;
            ++this.mNumColumns;
            solverVariable0.id = v;
            solverVariable0.mType = Type.UNRESTRICTED;
            this.mCache.mIndexedVariables[this.mVariablesID] = solverVariable0;
        }
        return solverVariable0;
    }

    public ArrayRow createRow() {
        ArrayRow arrayRow0;
        if(LinearSystem.OPTIMIZED_ENGINE) {
            arrayRow0 = (ArrayRow)this.mCache.mOptimizedArrayRowPool.acquire();
            if(arrayRow0 == null) {
                arrayRow0 = new ValuesRow(this.mCache);
                ++LinearSystem.OPTIMIZED_ARRAY_ROW_CREATION;
            }
            else {
                arrayRow0.reset();
            }
        }
        else {
            arrayRow0 = (ArrayRow)this.mCache.mArrayRowPool.acquire();
            if(arrayRow0 == null) {
                arrayRow0 = new ArrayRow(this.mCache);
                ++LinearSystem.ARRAY_ROW_CREATION;
            }
            else {
                arrayRow0.reset();
            }
        }
        SolverVariable.increaseErrorId();
        return arrayRow0;
    }

    public static ArrayRow createRowDimensionPercent(LinearSystem linearSystem0, SolverVariable solverVariable0, SolverVariable solverVariable1, float f) {
        return linearSystem0.createRow().createRowDimensionPercent(solverVariable0, solverVariable1, f);
    }

    public SolverVariable createSlackVariable() {
        Metrics metrics0 = LinearSystem.sMetrics;
        if(metrics0 != null) {
            ++metrics0.slackvariables;
        }
        if(this.mNumColumns + 1 >= this.mMaxColumns) {
            this.increaseTableSize();
        }
        SolverVariable solverVariable0 = this.acquireSolverVariable(Type.SLACK, null);
        int v = this.mVariablesID + 1;
        this.mVariablesID = v;
        ++this.mNumColumns;
        solverVariable0.id = v;
        this.mCache.mIndexedVariables[this.mVariablesID] = solverVariable0;
        return solverVariable0;
    }

    private SolverVariable createVariable(String s, Type solverVariable$Type0) {
        Metrics metrics0 = LinearSystem.sMetrics;
        if(metrics0 != null) {
            ++metrics0.variables;
        }
        if(this.mNumColumns + 1 >= this.mMaxColumns) {
            this.increaseTableSize();
        }
        SolverVariable solverVariable0 = this.acquireSolverVariable(solverVariable$Type0, null);
        solverVariable0.setName(s);
        int v = this.mVariablesID + 1;
        this.mVariablesID = v;
        ++this.mNumColumns;
        solverVariable0.id = v;
        if(this.mVariables == null) {
            this.mVariables = new HashMap();
        }
        this.mVariables.put(s, solverVariable0);
        this.mCache.mIndexedVariables[this.mVariablesID] = solverVariable0;
        return solverVariable0;
    }

    public void displayReadableRows() {
        this.displaySolverVariables();
        String s = " num vars " + this.mVariablesID + "\n";
        for(int v1 = 0; v1 < this.mVariablesID + 1; ++v1) {
            SolverVariable solverVariable0 = this.mCache.mIndexedVariables[v1];
            if(solverVariable0 != null && solverVariable0.isFinalValue) {
                s = s + " $[" + v1 + "] => " + solverVariable0 + " = " + solverVariable0.computedValue + "\n";
            }
        }
        String s1 = s + "\n";
        for(int v2 = 0; v2 < this.mVariablesID + 1; ++v2) {
            SolverVariable solverVariable1 = this.mCache.mIndexedVariables[v2];
            if(solverVariable1 != null && solverVariable1.mIsSynonym) {
                s1 = s1 + " ~[" + v2 + "] => " + solverVariable1 + " = " + this.mCache.mIndexedVariables[solverVariable1.mSynonym] + " + " + solverVariable1.mSynonymDelta + "\n";
            }
        }
        String s2 = s1 + "\n\n #  ";
        for(int v = 0; v < this.mNumRows; ++v) {
            s2 = s2 + this.mRows[v].toReadableString() + "\n #  ";
        }
        if(this.mGoal != null) {
            s2 = s2 + "Goal: " + this.mGoal + "\n";
        }
        System.out.println(s2);
    }

    private void displayRows() {
        this.displaySolverVariables();
        String s = "";
        for(int v = 0; v < this.mNumRows; ++v) {
            s = s + this.mRows[v] + "\n";
        }
        System.out.println(s + this.mGoal + "\n");
    }

    private void displaySolverVariables() {
        System.out.println("Display Rows (" + this.mNumRows + "x" + this.mNumColumns + ")\n");
    }

    void displaySystemInformation() {
        int v1 = 0;
        for(int v = 0; v < this.mTableSize; ++v) {
            ArrayRow arrayRow0 = this.mRows[v];
            if(arrayRow0 != null) {
                v1 += arrayRow0.sizeInBytes();
            }
        }
        int v3 = 0;
        for(int v2 = 0; v2 < this.mNumRows; ++v2) {
            ArrayRow arrayRow1 = this.mRows[v2];
            if(arrayRow1 != null) {
                v3 += arrayRow1.sizeInBytes();
            }
        }
        System.out.println("Linear System -> Table size: " + this.mTableSize + " (" + this.getDisplaySize(this.mTableSize * this.mTableSize) + ") -- row sizes: " + this.getDisplaySize(v1) + ", actual size: " + this.getDisplaySize(v3) + " rows: " + this.mNumRows + "/" + this.mMaxRows + " cols: " + this.mNumColumns + "/" + this.mMaxColumns + " 0 occupied cells, " + "0 bytes");
    }

    public void displayVariablesReadableRows() {
        this.displaySolverVariables();
        String s = "";
        for(int v = 0; v < this.mNumRows; ++v) {
            if(this.mRows[v].mVariable.mType == Type.UNRESTRICTED) {
                s = s + this.mRows[v].toReadableString() + "\n";
            }
        }
        System.out.println(s + this.mGoal + "\n");
    }

    private int enforceBFS(Row linearSystem$Row0) throws Exception {
        for(int v = 0; v < this.mNumRows; ++v) {
            if(this.mRows[v].mVariable.mType != Type.UNRESTRICTED && this.mRows[v].mConstantValue < 0.0f) {
                int v1 = 0;
                boolean z = false;
                while(!z) {
                    Metrics metrics0 = LinearSystem.sMetrics;
                    if(metrics0 != null) {
                        ++metrics0.bfs;
                    }
                    ++v1;
                    float f = 3.402823E+38f;
                    int v3 = -1;
                    int v4 = -1;
                    int v5 = 0;
                    for(int v2 = 0; v2 < this.mNumRows; ++v2) {
                        ArrayRow arrayRow0 = this.mRows[v2];
                        if(arrayRow0.mVariable.mType != Type.UNRESTRICTED && !arrayRow0.mIsSimpleDefinition && arrayRow0.mConstantValue < 0.0f) {
                            if(LinearSystem.SKIP_COLUMNS) {
                                int v7 = arrayRow0.variables.getCurrentSize();
                                for(int v8 = 0; v8 < v7; ++v8) {
                                    SolverVariable solverVariable0 = arrayRow0.variables.getVariable(v8);
                                    float f1 = arrayRow0.variables.get(solverVariable0);
                                    if(f1 > 0.0f) {
                                        for(int v9 = 0; v9 < 9; ++v9) {
                                            float f2 = solverVariable0.mStrengthVector[v9] / f1;
                                            if(f2 < f && v9 == v5 || v9 > v5) {
                                                v4 = solverVariable0.id;
                                                f = f2;
                                                v5 = v9;
                                                v3 = v2;
                                            }
                                        }
                                    }
                                }
                            }
                            else {
                                for(int v6 = 1; v6 < this.mNumColumns; ++v6) {
                                    SolverVariable solverVariable1 = this.mCache.mIndexedVariables[v6];
                                    float f3 = arrayRow0.variables.get(solverVariable1);
                                    if(f3 > 0.0f) {
                                        for(int v10 = 0; v10 < 9; ++v10) {
                                            float f4 = solverVariable1.mStrengthVector[v10] / f3;
                                            if(f4 < f && v10 == v5 || v10 > v5) {
                                                v5 = v10;
                                                f = f4;
                                                v3 = v2;
                                                v4 = v6;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(v3 == -1) {
                        z = true;
                    }
                    else {
                        ArrayRow arrayRow1 = this.mRows[v3];
                        arrayRow1.mVariable.mDefinitionId = -1;
                        Metrics metrics1 = LinearSystem.sMetrics;
                        if(metrics1 != null) {
                            ++metrics1.pivots;
                        }
                        arrayRow1.pivot(this.mCache.mIndexedVariables[v4]);
                        arrayRow1.mVariable.mDefinitionId = v3;
                        arrayRow1.mVariable.updateReferencesWithNewDefinition(this, arrayRow1);
                    }
                    if(v1 > this.mNumColumns / 2) {
                        z = true;
                    }
                }
                return v1;
            }
        }
        return 0;
    }

    public void fillMetrics(Metrics metrics0) {
        LinearSystem.sMetrics = metrics0;
    }

    public Cache getCache() {
        return this.mCache;
    }

    private String getDisplaySize(int v) [...] // 潜在的解密器

    private String getDisplayStrength(int v) {
        switch(v) {
            case 1: {
                return "LOW";
            }
            case 2: {
                return "MEDIUM";
            }
            case 3: {
                return "HIGH";
            }
            case 4: {
                return "HIGHEST";
            }
            case 5: {
                return "EQUALITY";
            }
            case 6: {
                return "BARRIER";
            }
            case 8: {
                return "FIXED";
            }
            default: {
                return "NONE";
            }
        }
    }

    Row getGoal() {
        return this.mGoal;
    }

    public int getMemoryUsed() {
        int v1 = 0;
        for(int v = 0; v < this.mNumRows; ++v) {
            ArrayRow arrayRow0 = this.mRows[v];
            if(arrayRow0 != null) {
                v1 += arrayRow0.sizeInBytes();
            }
        }
        return v1;
    }

    public static Metrics getMetrics() {
        return LinearSystem.sMetrics;
    }

    public int getNumEquations() {
        return this.mNumRows;
    }

    public int getNumVariables() {
        return this.mVariablesID;
    }

    public int getObjectVariableValue(Object object0) {
        SolverVariable solverVariable0 = ((ConstraintAnchor)object0).getSolverVariable();
        return solverVariable0 == null ? 0 : ((int)(solverVariable0.computedValue + 0.5f));
    }

    ArrayRow getRow(int v) {
        return this.mRows[v];
    }

    float getValueFor(String s) {
        SolverVariable solverVariable0 = this.getVariable(s, Type.UNRESTRICTED);
        return solverVariable0 == null ? 0.0f : solverVariable0.computedValue;
    }

    SolverVariable getVariable(String s, Type solverVariable$Type0) {
        if(this.mVariables == null) {
            this.mVariables = new HashMap();
        }
        SolverVariable solverVariable0 = (SolverVariable)this.mVariables.get(s);
        return solverVariable0 == null ? this.createVariable(s, solverVariable$Type0) : solverVariable0;
    }

    private void increaseTableSize() {
        int v = this.mTableSize * 2;
        this.mTableSize = v;
        this.mRows = (ArrayRow[])Arrays.copyOf(this.mRows, v);
        this.mCache.mIndexedVariables = (SolverVariable[])Arrays.copyOf(this.mCache.mIndexedVariables, this.mTableSize);
        int v1 = this.mTableSize;
        this.mAlreadyTestedCandidates = new boolean[v1];
        this.mMaxColumns = v1;
        this.mMaxRows = v1;
        Metrics metrics0 = LinearSystem.sMetrics;
        if(metrics0 != null) {
            ++metrics0.tableSizeIncrease;
            LinearSystem.sMetrics.maxTableSize = Math.max(LinearSystem.sMetrics.maxTableSize, this.mTableSize);
            LinearSystem.sMetrics.lastTableSize = LinearSystem.sMetrics.maxTableSize;
        }
    }

    public void minimize() throws Exception {
        Metrics metrics0 = LinearSystem.sMetrics;
        if(metrics0 != null) {
            ++metrics0.minimize;
        }
        if(this.mGoal.isEmpty()) {
            this.computeValues();
            return;
        }
        if(!this.graphOptimizer && !this.newgraphOptimizer) {
            this.minimizeGoal(this.mGoal);
            return;
        }
        Metrics metrics1 = LinearSystem.sMetrics;
        if(metrics1 != null) {
            ++metrics1.graphOptimizer;
        }
        for(int v = 0; v < this.mNumRows; ++v) {
            if(!this.mRows[v].mIsSimpleDefinition) {
                this.minimizeGoal(this.mGoal);
                return;
            }
        }
        Metrics metrics2 = LinearSystem.sMetrics;
        if(metrics2 != null) {
            ++metrics2.fullySolved;
        }
        this.computeValues();
    }

    void minimizeGoal(Row linearSystem$Row0) throws Exception {
        Metrics metrics0 = LinearSystem.sMetrics;
        if(metrics0 != null) {
            ++metrics0.minimizeGoal;
            LinearSystem.sMetrics.maxVariables = Math.max(LinearSystem.sMetrics.maxVariables, this.mNumColumns);
            LinearSystem.sMetrics.maxRows = Math.max(LinearSystem.sMetrics.maxRows, this.mNumRows);
        }
        this.enforceBFS(linearSystem$Row0);
        this.optimize(linearSystem$Row0, false);
        this.computeValues();
    }

    private int optimize(Row linearSystem$Row0, boolean z) {
        Metrics metrics0 = LinearSystem.sMetrics;
        if(metrics0 != null) {
            ++metrics0.optimize;
        }
        for(int v = 0; v < this.mNumColumns; ++v) {
            this.mAlreadyTestedCandidates[v] = false;
        }
        int v1 = 0;
        boolean z1 = false;
        while(!z1) {
            Metrics metrics1 = LinearSystem.sMetrics;
            if(metrics1 != null) {
                ++metrics1.iterations;
            }
            ++v1;
            if(v1 < this.mNumColumns * 2) {
                if(linearSystem$Row0.getKey() != null) {
                    boolean[] arr_z = this.mAlreadyTestedCandidates;
                    arr_z[linearSystem$Row0.getKey().id] = true;
                }
                SolverVariable solverVariable0 = linearSystem$Row0.getPivotCandidate(this, this.mAlreadyTestedCandidates);
                if(solverVariable0 != null) {
                    if(this.mAlreadyTestedCandidates[solverVariable0.id]) {
                        return v1;
                    }
                    this.mAlreadyTestedCandidates[solverVariable0.id] = true;
                }
                if(solverVariable0 != null) {
                    float f = 3.402823E+38f;
                    int v3 = -1;
                    for(int v2 = 0; v2 < this.mNumRows; ++v2) {
                        ArrayRow arrayRow0 = this.mRows[v2];
                        if(arrayRow0.mVariable.mType != Type.UNRESTRICTED && !arrayRow0.mIsSimpleDefinition && arrayRow0.hasVariable(solverVariable0)) {
                            float f1 = arrayRow0.variables.get(solverVariable0);
                            if(f1 < 0.0f) {
                                float f2 = -arrayRow0.mConstantValue / f1;
                                if(f2 < f) {
                                    v3 = v2;
                                    f = f2;
                                }
                            }
                        }
                    }
                    if(v3 <= -1) {
                        continue;
                    }
                    ArrayRow arrayRow1 = this.mRows[v3];
                    arrayRow1.mVariable.mDefinitionId = -1;
                    Metrics metrics2 = LinearSystem.sMetrics;
                    if(metrics2 != null) {
                        ++metrics2.pivots;
                    }
                    arrayRow1.pivot(solverVariable0);
                    arrayRow1.mVariable.mDefinitionId = v3;
                    arrayRow1.mVariable.updateReferencesWithNewDefinition(this, arrayRow1);
                    continue;
                }
                z1 = true;
                continue;
            }
            return v1;
        }
        return v1;
    }

    private void releaseRows() {
        int v = 0;
        if(LinearSystem.OPTIMIZED_ENGINE) {
            while(v < this.mNumRows) {
                ArrayRow arrayRow0 = this.mRows[v];
                if(arrayRow0 != null) {
                    this.mCache.mOptimizedArrayRowPool.release(arrayRow0);
                }
                this.mRows[v] = null;
                ++v;
            }
            return;
        }
        while(v < this.mNumRows) {
            ArrayRow arrayRow1 = this.mRows[v];
            if(arrayRow1 != null) {
                this.mCache.mArrayRowPool.release(arrayRow1);
            }
            this.mRows[v] = null;
            ++v;
        }
    }

    public void removeRow(ArrayRow arrayRow0) {
        int v1;
        if(arrayRow0.mIsSimpleDefinition && arrayRow0.mVariable != null) {
            if(arrayRow0.mVariable.mDefinitionId != -1) {
                for(int v = arrayRow0.mVariable.mDefinitionId; true; ++v) {
                    v1 = this.mNumRows;
                    if(v >= v1 - 1) {
                        break;
                    }
                    SolverVariable solverVariable0 = this.mRows[v + 1].mVariable;
                    if(solverVariable0.mDefinitionId == v + 1) {
                        solverVariable0.mDefinitionId = v;
                    }
                    this.mRows[v] = this.mRows[v + 1];
                }
                this.mNumRows = v1 - 1;
            }
            if(!arrayRow0.mVariable.isFinalValue) {
                arrayRow0.mVariable.setFinalValue(this, arrayRow0.mConstantValue);
            }
            if(LinearSystem.OPTIMIZED_ENGINE) {
                this.mCache.mOptimizedArrayRowPool.release(arrayRow0);
                return;
            }
            this.mCache.mArrayRowPool.release(arrayRow0);
        }
    }

    public void reset() {
        for(int v = 0; v < this.mCache.mIndexedVariables.length; ++v) {
            SolverVariable solverVariable0 = this.mCache.mIndexedVariables[v];
            if(solverVariable0 != null) {
                solverVariable0.reset();
            }
        }
        this.mCache.mSolverVariablePool.releaseAll(this.mPoolVariables, this.mPoolVariablesCount);
        this.mPoolVariablesCount = 0;
        Arrays.fill(this.mCache.mIndexedVariables, null);
        HashMap hashMap0 = this.mVariables;
        if(hashMap0 != null) {
            hashMap0.clear();
        }
        this.mVariablesID = 0;
        this.mGoal.clear();
        this.mNumColumns = 1;
        for(int v1 = 0; v1 < this.mNumRows; ++v1) {
            ArrayRow arrayRow0 = this.mRows[v1];
            if(arrayRow0 != null) {
                arrayRow0.mUsed = false;
            }
        }
        this.releaseRows();
        this.mNumRows = 0;
        if(LinearSystem.OPTIMIZED_ENGINE) {
            this.mTempGoal = new ValuesRow(this.mCache);
            return;
        }
        this.mTempGoal = new ArrayRow(this.mCache);
    }
}

