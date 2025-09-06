package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;

public abstract class DelegatingSimpleTypeImpl extends DelegatingSimpleType {
    private final SimpleType delegate;

    public DelegatingSimpleTypeImpl(SimpleType simpleType0) {
        Intrinsics.checkNotNullParameter(simpleType0, "delegate");
        super();
        this.delegate = simpleType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    protected SimpleType getDelegate() {
        return this.delegate;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public SimpleType makeNullableAsSpecified(boolean z) {
        return z == this.isMarkedNullable() ? this : this.getDelegate().makeNullableAsSpecified(z).replaceAttributes(this.getAttributes());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType makeNullableAsSpecified(boolean z) {
        return this.makeNullableAsSpecified(z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public SimpleType replaceAttributes(TypeAttributes typeAttributes0) {
        Intrinsics.checkNotNullParameter(typeAttributes0, "newAttributes");
        return typeAttributes0 != this.getAttributes() ? new SimpleTypeWithAttributes(this, typeAttributes0) : this;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType replaceAttributes(TypeAttributes typeAttributes0) {
        return this.replaceAttributes(typeAttributes0);
    }
}

