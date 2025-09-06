package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public final class FlexibleTypeWithEnhancement extends FlexibleType implements TypeWithEnhancement {
    private final KotlinType enhancement;
    private final FlexibleType origin;

    public FlexibleTypeWithEnhancement(FlexibleType flexibleType0, KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(flexibleType0, "origin");
        Intrinsics.checkNotNullParameter(kotlinType0, "enhancement");
        super(flexibleType0.getLowerBound(), flexibleType0.getUpperBound());
        this.origin = flexibleType0;
        this.enhancement = kotlinType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    public SimpleType getDelegate() {
        return this.getOrigin().getDelegate();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancement
    public KotlinType getEnhancement() {
        return this.enhancement;
    }

    public FlexibleType getOrigin() {
        return this.origin;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancement
    public UnwrappedType getOrigin() {
        return this.getOrigin();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType makeNullableAsSpecified(boolean z) {
        return TypeWithEnhancementKt.wrapEnhancement(this.getOrigin().makeNullableAsSpecified(z), this.getEnhancement().unwrap().makeNullableAsSpecified(z));
    }

    public FlexibleTypeWithEnhancement refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        KotlinType kotlinType0 = kotlinTypeRefiner0.refineType(this.getOrigin());
        Intrinsics.checkNotNull(kotlinType0, "null cannot be cast to non-null type org.jetbrains.kotlin.types.FlexibleType");
        return new FlexibleTypeWithEnhancement(((FlexibleType)kotlinType0), kotlinTypeRefiner0.refineType(this.getEnhancement()));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public KotlinType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    public String render(DescriptorRenderer descriptorRenderer0, DescriptorRendererOptions descriptorRendererOptions0) {
        Intrinsics.checkNotNullParameter(descriptorRenderer0, "renderer");
        Intrinsics.checkNotNullParameter(descriptorRendererOptions0, "options");
        return descriptorRendererOptions0.getEnhancedTypes() ? descriptorRenderer0.renderType(this.getEnhancement()) : this.getOrigin().render(descriptorRenderer0, descriptorRendererOptions0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType replaceAttributes(TypeAttributes typeAttributes0) {
        Intrinsics.checkNotNullParameter(typeAttributes0, "newAttributes");
        return TypeWithEnhancementKt.wrapEnhancement(this.getOrigin().replaceAttributes(typeAttributes0), this.getEnhancement());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    public String toString() {
        return "[@EnhancedForWarnings(" + this.getEnhancement() + ")] " + this.getOrigin();
    }
}

