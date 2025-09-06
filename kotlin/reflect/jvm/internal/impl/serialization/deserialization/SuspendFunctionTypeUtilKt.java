package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.name.CallableId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;

public final class SuspendFunctionTypeUtilKt {
    public static final FqName KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME;
    public static final CallableId KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME_CALLABLE_ID;

    static {
        SuspendFunctionTypeUtilKt.KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME = new FqName("kotlin.suspend");
        Name name0 = Name.identifier("suspend");
        Intrinsics.checkNotNullExpressionValue(name0, "identifier(\"suspend\")");
        SuspendFunctionTypeUtilKt.KOTLIN_SUSPEND_BUILT_IN_FUNCTION_FQ_NAME_CALLABLE_ID = new CallableId(StandardNames.BUILT_INS_PACKAGE_FQ_NAME, name0);
    }
}

