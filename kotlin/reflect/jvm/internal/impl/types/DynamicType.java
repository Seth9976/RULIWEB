package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.model.DynamicTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public final class DynamicType extends FlexibleType implements DynamicTypeMarker {
    private final TypeAttributes attributes;

    public DynamicType(KotlinBuiltIns kotlinBuiltIns0, TypeAttributes typeAttributes0) {
        Intrinsics.checkNotNullParameter(kotlinBuiltIns0, "builtIns");
        Intrinsics.checkNotNullParameter(typeAttributes0, "attributes");
        SimpleType simpleType0 = kotlinBuiltIns0.getNothingType();
        Intrinsics.checkNotNullExpressionValue(simpleType0, "builtIns.nothingType");
        SimpleType simpleType1 = kotlinBuiltIns0.getNullableAnyType();
        Intrinsics.checkNotNullExpressionValue(simpleType1, "builtIns.nullableAnyType");
        super(simpleType0, simpleType1);
        this.attributes = typeAttributes0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    public TypeAttributes getAttributes() {
        return this.attributes;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    public SimpleType getDelegate() {
        return this.getUpperBound();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    public boolean isMarkedNullable() {
        return false;
    }

    public DynamicType makeNullableAsSpecified(boolean z) [...] // Inlined contents

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType makeNullableAsSpecified(boolean z) {
        return this;
    }

    public DynamicType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        return this;
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
        return "dynamic";
    }

    public DynamicType replaceAttributes(TypeAttributes typeAttributes0) {
        Intrinsics.checkNotNullParameter(typeAttributes0, "newAttributes");
        return new DynamicType(TypeUtilsKt.getBuiltIns(this.getDelegate()), typeAttributes0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType replaceAttributes(TypeAttributes typeAttributes0) {
        return this.replaceAttributes(typeAttributes0);
    }
}

