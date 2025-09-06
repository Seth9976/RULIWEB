package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;

public final class InvalidModuleException extends IllegalStateException {
    public InvalidModuleException(String s) {
        Intrinsics.checkNotNullParameter(s, "message");
        super(s);
    }
}

