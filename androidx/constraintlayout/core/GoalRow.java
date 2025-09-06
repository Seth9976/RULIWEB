package androidx.constraintlayout.core;

public class GoalRow extends ArrayRow {
    public GoalRow(Cache cache0) {
        super(cache0);
    }

    @Override  // androidx.constraintlayout.core.ArrayRow
    public void addError(SolverVariable solverVariable0) {
        super.addError(solverVariable0);
        --solverVariable0.usageInRowCount;
    }
}

