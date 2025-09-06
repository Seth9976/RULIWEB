package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public final class SimpleTypeWithEnhancement extends DelegatingSimpleType implements TypeWithEnhancement {
    private final SimpleType delegate;
    private final KotlinType enhancement;

    public SimpleTypeWithEnhancement(SimpleType simpleType0, KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(simpleType0, "delegate");
        Intrinsics.checkNotNullParameter(kotlinType0, "enhancement");
        super();
        this.delegate = simpleType0;
        this.enhancement = kotlinType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    protected SimpleType getDelegate() {
        return this.delegate;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancement
    public KotlinType getEnhancement() {
        return this.enhancement;
    }

    public SimpleType getOrigin() {
        return this.getDelegate();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancement
    public UnwrappedType getOrigin() {
        return this.getOrigin();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public SimpleType makeNullableAsSpecified(boolean z) {
        UnwrappedType unwrappedType0 = TypeWithEnhancementKt.wrapEnhancement(this.getOrigin().makeNullableAsSpecified(z), this.getEnhancement().unwrap().makeNullableAsSpecified(z));
        Intrinsics.checkNotNull(unwrappedType0, "null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        return (SimpleType)unwrappedType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType makeNullableAsSpecified(boolean z) {
        return this.makeNullableAsSpecified(z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    public KotlinType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    public SimpleType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    public SimpleTypeWithEnhancement refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        KotlinType kotlinType0 = kotlinTypeRefiner0.refineType(this.getDelegate());
        Intrinsics.checkNotNull(kotlinType0, "null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        return new SimpleTypeWithEnhancement(((SimpleType)kotlinType0), kotlinTypeRefiner0.refineType(this.getEnhancement()));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    public UnwrappedType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public SimpleType replaceAttributes(TypeAttributes typeAttributes0) {
        Intrinsics.checkNotNullParameter(typeAttributes0, "newAttributes");
        UnwrappedType unwrappedType0 = TypeWithEnhancementKt.wrapEnhancement(this.getOrigin().replaceAttributes(typeAttributes0), this.getEnhancement());
        Intrinsics.checkNotNull(unwrappedType0, "null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        return (SimpleType)unwrappedType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType replaceAttributes(TypeAttributes typeAttributes0) {
        return this.replaceAttributes(typeAttributes0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    public DelegatingSimpleType replaceDelegate(SimpleType simpleType0) {
        return this.replaceDelegate(simpleType0);
    }

    public SimpleTypeWithEnhancement replaceDelegate(SimpleType simpleType0) {
        Intrinsics.checkNotNullParameter(simpleType0, "delegate");
        return new SimpleTypeWithEnhancement(simpleType0, this.getEnhancement());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public String toString() {
        return "[@EnhancedForWarnings(" + this.getEnhancement() + ")] " + this.getOrigin();
    }
}

