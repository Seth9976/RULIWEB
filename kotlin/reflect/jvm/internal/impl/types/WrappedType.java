package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;

public abstract class WrappedType extends KotlinType {
    public WrappedType() {
        super(null);
    }

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

    protected abstract KotlinType getDelegate();

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public MemberScope getMemberScope() {
        return this.getDelegate().getMemberScope();
    }

    public boolean isComputed() {
        return true;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public boolean isMarkedNullable() {
        return this.getDelegate().isMarkedNullable();
    }

    // 去混淆评级： 低(20)
    @Override
    public String toString() {
        return this.isComputed() ? this.getDelegate().toString() : "<Not computed yet>";
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public final UnwrappedType unwrap() {
        KotlinType kotlinType0;
        for(kotlinType0 = this.getDelegate(); kotlinType0 instanceof WrappedType; kotlinType0 = ((WrappedType)kotlinType0).getDelegate()) {
        }
        Intrinsics.checkNotNull(kotlinType0, "null cannot be cast to non-null type org.jetbrains.kotlin.types.UnwrappedType");
        return (UnwrappedType)kotlinType0;
    }
}

