package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorScope;
import kotlin.reflect.jvm.internal.impl.types.error.ThrowingScope;

final class SimpleTypeImpl extends SimpleType {
    private final List arguments;
    private final TypeConstructor constructor;
    private final boolean isMarkedNullable;
    private final MemberScope memberScope;
    private final Function1 refinedTypeFactory;

    public SimpleTypeImpl(TypeConstructor typeConstructor0, List list0, boolean z, MemberScope memberScope0, Function1 function10) {
        Intrinsics.checkNotNullParameter(typeConstructor0, "constructor");
        Intrinsics.checkNotNullParameter(list0, "arguments");
        Intrinsics.checkNotNullParameter(memberScope0, "memberScope");
        Intrinsics.checkNotNullParameter(function10, "refinedTypeFactory");
        super();
        this.constructor = typeConstructor0;
        this.arguments = list0;
        this.isMarkedNullable = z;
        this.memberScope = memberScope0;
        this.refinedTypeFactory = function10;
        if(this.getMemberScope() instanceof ErrorScope && !(this.getMemberScope() instanceof ThrowingScope)) {
            throw new IllegalStateException("SimpleTypeImpl should not be created for error type: " + this.getMemberScope() + '\n' + this.getConstructor());
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public List getArguments() {
        return this.arguments;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public TypeAttributes getAttributes() {
        return TypeAttributes.Companion.getEmpty();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public TypeConstructor getConstructor() {
        return this.constructor;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public MemberScope getMemberScope() {
        return this.memberScope;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public boolean isMarkedNullable() {
        return this.isMarkedNullable;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public SimpleType makeNullableAsSpecified(boolean z) {
        if(z == this.isMarkedNullable()) {
            return this;
        }
        return z ? new NullableSimpleType(this) : new NotNullSimpleType(this);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType makeNullableAsSpecified(boolean z) {
        return this.makeNullableAsSpecified(z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public KotlinType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    public SimpleType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        SimpleType simpleType0 = (SimpleType)this.refinedTypeFactory.invoke(kotlinTypeRefiner0);
        return simpleType0 == null ? this : simpleType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public SimpleType replaceAttributes(TypeAttributes typeAttributes0) {
        Intrinsics.checkNotNullParameter(typeAttributes0, "newAttributes");
        return typeAttributes0.isEmpty() ? this : new SimpleTypeWithAttributes(this, typeAttributes0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType replaceAttributes(TypeAttributes typeAttributes0) {
        return this.replaceAttributes(typeAttributes0);
    }
}

