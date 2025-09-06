package kotlin.reflect.jvm.internal.impl.types.checker;

import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;

public interface TypeCheckingProcedureCallbacks {
    boolean assertEqualTypeConstructors(TypeConstructor arg1, TypeConstructor arg2);
}

