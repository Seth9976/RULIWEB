package kotlin.reflect.jvm.internal.impl.types;

public enum Variance {
    INVARIANT("", true, true, 0),
    IN_VARIANCE("in", true, false, -1),
    OUT_VARIANCE("out", false, true, 1);

    private final boolean allowsInPosition;
    private final boolean allowsOutPosition;
    private final String label;
    private final int superpositionFactor;

    private static final Variance[] $values() [...] // Inlined contents

    private Variance(String s1, boolean z, boolean z1, int v1) {
        this.label = s1;
        this.allowsInPosition = z;
        this.allowsOutPosition = z1;
        this.superpositionFactor = v1;
    }

    public final boolean getAllowsOutPosition() {
        return this.allowsOutPosition;
    }

    public final String getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}

