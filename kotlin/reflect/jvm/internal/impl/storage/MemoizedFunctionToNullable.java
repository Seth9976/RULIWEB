package kotlin.reflect.jvm.internal.impl.storage;

import kotlin.jvm.functions.Function1;

public interface MemoizedFunctionToNullable extends Function1 {
    boolean isComputed(Object arg1);
}

