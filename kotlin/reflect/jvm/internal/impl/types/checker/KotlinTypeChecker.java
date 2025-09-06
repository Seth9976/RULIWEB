package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;

public interface KotlinTypeChecker {
    public interface TypeConstructorEquality {
        boolean equals(TypeConstructor arg1, TypeConstructor arg2);
    }

    public static final KotlinTypeChecker DEFAULT;

    static {
        KotlinTypeChecker.DEFAULT = NewKotlinTypeChecker.Companion.getDefault();
    }

    boolean equalTypes(KotlinType arg1, KotlinType arg2);

    boolean isSubtypeOf(KotlinType arg1, KotlinType arg2);
}

