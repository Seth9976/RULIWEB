package kotlin.reflect.jvm.internal.impl.storage;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

public interface StorageManager {
    Object compute(Function0 arg1);

    CacheWithNotNullValues createCacheWithNotNullValues();

    CacheWithNullableValues createCacheWithNullableValues();

    NotNullLazyValue createLazyValue(Function0 arg1);

    NotNullLazyValue createLazyValueWithPostCompute(Function0 arg1, Function1 arg2, Function1 arg3);

    MemoizedFunctionToNotNull createMemoizedFunction(Function1 arg1);

    MemoizedFunctionToNullable createMemoizedFunctionWithNullableValues(Function1 arg1);

    NullableLazyValue createNullableLazyValue(Function0 arg1);

    NotNullLazyValue createRecursionTolerantLazyValue(Function0 arg1, Object arg2);
}

