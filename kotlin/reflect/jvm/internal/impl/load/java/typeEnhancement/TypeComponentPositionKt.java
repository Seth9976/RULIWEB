package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.internal.Intrinsics;

public final class TypeComponentPositionKt {
    public static final boolean shouldEnhance(TypeComponentPosition typeComponentPosition0) {
        Intrinsics.checkNotNullParameter(typeComponentPosition0, "<this>");
        return typeComponentPosition0 != TypeComponentPosition.INFLEXIBLE;
    }
}

