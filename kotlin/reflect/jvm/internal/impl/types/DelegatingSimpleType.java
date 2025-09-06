package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public abstract class DelegatingSimpleType extends SimpleType {
    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public List getArguments() {
        return this.getDelegate().getArguments();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public TypeAttributes getAttributes() {
        return this.getDelegate().getAttributes();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public TypeConstructor getConstructor() {
        return this.getDelegate().getConstructor();
    }

    protected abstract SimpleType getDelegate();

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public MemberScope getMemberScope() {
        return this.getDelegate().getMemberScope();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public boolean isMarkedNullable() {
        return this.getDelegate().isMarkedNullable();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public KotlinType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    public SimpleType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        KotlinType kotlinType0 = kotlinTypeRefiner0.refineType(this.getDelegate());
        Intrinsics.checkNotNull(kotlinType0, "null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        return this.replaceDelegate(((SimpleType)kotlinType0));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    public abstract DelegatingSimpleType replaceDelegate(SimpleType arg1);
}

