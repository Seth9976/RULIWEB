package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.checker.NewTypeVariableConstructor;
import kotlin.reflect.jvm.internal.impl.types.model.StubTypeMarker;

public final class StubTypeForBuilderInference extends AbstractStubType implements StubTypeMarker {
    private final TypeConstructor constructor;
    private final MemberScope memberScope;

    public StubTypeForBuilderInference(NewTypeVariableConstructor newTypeVariableConstructor0, boolean z, TypeConstructor typeConstructor0) {
        Intrinsics.checkNotNullParameter(newTypeVariableConstructor0, "originalTypeVariable");
        Intrinsics.checkNotNullParameter(typeConstructor0, "constructor");
        super(newTypeVariableConstructor0, z);
        this.constructor = typeConstructor0;
        this.memberScope = newTypeVariableConstructor0.getBuiltIns().getAnyType().getMemberScope();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public TypeConstructor getConstructor() {
        return this.constructor;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractStubType
    public MemberScope getMemberScope() {
        return this.memberScope;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractStubType
    public AbstractStubType materialize(boolean z) {
        return new StubTypeForBuilderInference(this.getOriginalTypeVariable(), z, this.getConstructor());
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public String toString() {
        return "Stub (BI): " + this.getOriginalTypeVariable() + (this.isMarkedNullable() ? "?" : "");
    }
}

