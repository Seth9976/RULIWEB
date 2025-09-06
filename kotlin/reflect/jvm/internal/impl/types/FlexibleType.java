package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.types.model.FlexibleTypeMarker;

public abstract class FlexibleType extends UnwrappedType implements FlexibleTypeMarker {
    private final SimpleType lowerBound;
    private final SimpleType upperBound;

    public FlexibleType(SimpleType simpleType0, SimpleType simpleType1) {
        Intrinsics.checkNotNullParameter(simpleType0, "lowerBound");
        Intrinsics.checkNotNullParameter(simpleType1, "upperBound");
        super(null);
        this.lowerBound = simpleType0;
        this.upperBound = simpleType1;
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

    public abstract SimpleType getDelegate();

    public final SimpleType getLowerBound() {
        return this.lowerBound;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public MemberScope getMemberScope() {
        return this.getDelegate().getMemberScope();
    }

    public final SimpleType getUpperBound() {
        return this.upperBound;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public boolean isMarkedNullable() {
        return this.getDelegate().isMarkedNullable();
    }

    public abstract String render(DescriptorRenderer arg1, DescriptorRendererOptions arg2);

    @Override
    public String toString() {
        return DescriptorRenderer.DEBUG_TEXT.renderType(this);
    }
}

