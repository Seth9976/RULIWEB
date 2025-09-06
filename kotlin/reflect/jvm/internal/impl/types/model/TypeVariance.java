package kotlin.reflect.jvm.internal.impl.types.model;

public enum TypeVariance {
    IN("in"),
    OUT("out"),
    INV("");

    private final String presentation;

    private static final TypeVariance[] $values() [...] // Inlined contents

    private TypeVariance(String s1) {
        this.presentation = s1;
    }

    @Override
    public String toString() {
        return this.presentation;
    }
}

