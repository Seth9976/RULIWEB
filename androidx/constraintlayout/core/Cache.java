package androidx.constraintlayout.core;

public class Cache {
    Pool mArrayRowPool;
    SolverVariable[] mIndexedVariables;
    Pool mOptimizedArrayRowPool;
    Pool mSolverVariablePool;

    public Cache() {
        this.mOptimizedArrayRowPool = new SimplePool(0x100);
        this.mArrayRowPool = new SimplePool(0x100);
        this.mSolverVariablePool = new SimplePool(0x100);
        this.mIndexedVariables = new SolverVariable[0x20];
    }
}

