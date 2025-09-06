package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;

final class NullableSimpleType extends DelegatingSimpleTypeImpl {
    public NullableSimpleType(SimpleType simpleType0) {
        Intrinsics.checkNotNullParameter(simpleType0, "delegate");
        super(simpleType0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    public boolean isMarkedNullable() {
        return true;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    public DelegatingSimpleType replaceDelegate(SimpleType simpleType0) {
        return this.replaceDelegate(simpleType0);
    }

    public NullableSimpleType replaceDelegate(SimpleType simpleType0) {
        Intrinsics.checkNotNullParameter(simpleType0, "delegate");
        return new NullableSimpleType(simpleType0);
    }
}

