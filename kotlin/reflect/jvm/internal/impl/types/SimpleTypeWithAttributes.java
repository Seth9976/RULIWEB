package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;

final class SimpleTypeWithAttributes extends DelegatingSimpleTypeImpl {
    private final TypeAttributes attributes;

    public SimpleTypeWithAttributes(SimpleType simpleType0, TypeAttributes typeAttributes0) {
        Intrinsics.checkNotNullParameter(simpleType0, "delegate");
        Intrinsics.checkNotNullParameter(typeAttributes0, "attributes");
        super(simpleType0);
        this.attributes = typeAttributes0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    public TypeAttributes getAttributes() {
        return this.attributes;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    public DelegatingSimpleType replaceDelegate(SimpleType simpleType0) {
        return this.replaceDelegate(simpleType0);
    }

    public SimpleTypeWithAttributes replaceDelegate(SimpleType simpleType0) {
        Intrinsics.checkNotNullParameter(simpleType0, "delegate");
        return new SimpleTypeWithAttributes(simpleType0, this.getAttributes());
    }
}

