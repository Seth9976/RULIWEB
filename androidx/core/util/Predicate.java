package androidx.core.util;

public interface Predicate {
    Predicate and(Predicate arg1);

    Predicate negate();

    Predicate or(Predicate arg1);

    boolean test(Object arg1);
}

