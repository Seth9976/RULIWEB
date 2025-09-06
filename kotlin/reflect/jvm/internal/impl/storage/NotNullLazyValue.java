package kotlin.reflect.jvm.internal.impl.storage;

import kotlin.jvm.functions.Function0;

public interface NotNullLazyValue extends Function0 {
    boolean isComputed();
}

