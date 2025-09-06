package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public final class AbbreviatedType extends DelegatingSimpleType {
    private final SimpleType abbreviation;
    private final SimpleType delegate;

    public AbbreviatedType(SimpleType simpleType0, SimpleType simpleType1) {
        Intrinsics.checkNotNullParameter(simpleType0, "delegate");
        Intrinsics.checkNotNullParameter(simpleType1, "abbreviation");
        super();
        this.delegate = simpleType0;
        this.abbreviation = simpleType1;
    }

    public final SimpleType getAbbreviation() {
        return this.abbreviation;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    protected SimpleType getDelegate() {
        return this.delegate;
    }

    public final SimpleType getExpandedType() {
        return this.getDelegate();
    }

    public AbbreviatedType makeNullableAsSpecified(boolean z) {
        return new AbbreviatedType(this.getDelegate().makeNullableAsSpecified(z), this.abbreviation.makeNullableAsSpecified(z));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public SimpleType makeNullableAsSpecified(boolean z) {
        return this.makeNullableAsSpecified(z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType makeNullableAsSpecified(boolean z) {
        return this.makeNullableAsSpecified(z);
    }

    public AbbreviatedType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        KotlinType kotlinType0 = kotlinTypeRefiner0.refineType(this.getDelegate());
        Intrinsics.checkNotNull(kotlinType0, "null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        KotlinType kotlinType1 = kotlinTypeRefiner0.refineType(this.abbreviation);
        Intrinsics.checkNotNull(kotlinType1, "null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        return new AbbreviatedType(((SimpleType)kotlinType0), ((SimpleType)kotlinType1));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    public KotlinType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    public SimpleType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    public UnwrappedType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public SimpleType replaceAttributes(TypeAttributes typeAttributes0) {
        Intrinsics.checkNotNullParameter(typeAttributes0, "newAttributes");
        return new AbbreviatedType(this.getDelegate().replaceAttributes(typeAttributes0), this.abbreviation);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType replaceAttributes(TypeAttributes typeAttributes0) {
        return this.replaceAttributes(typeAttributes0);
    }

    public AbbreviatedType replaceDelegate(SimpleType simpleType0) {
        Intrinsics.checkNotNullParameter(simpleType0, "delegate");
        return new AbbreviatedType(simpleType0, this.abbreviation);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    public DelegatingSimpleType replaceDelegate(SimpleType simpleType0) {
        return this.replaceDelegate(simpleType0);
    }
}

