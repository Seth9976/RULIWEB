package kotlin.reflect.jvm.internal.impl.types;

import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRenderer;
import kotlin.reflect.jvm.internal.impl.renderer.DescriptorRendererOptions;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public final class FlexibleTypeImpl extends FlexibleType implements CustomTypeParameter {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion;
    public static boolean RUN_SLOW_ASSERTIONS;
    private boolean assertionsDone;

    static {
        FlexibleTypeImpl.Companion = new Companion(null);
    }

    public FlexibleTypeImpl(SimpleType simpleType0, SimpleType simpleType1) {
        Intrinsics.checkNotNullParameter(simpleType0, "lowerBound");
        Intrinsics.checkNotNullParameter(simpleType1, "upperBound");
        super(simpleType0, simpleType1);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    public SimpleType getDelegate() {
        this.runAssertions();
        return this.getLowerBound();
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.reflect.jvm.internal.impl.types.CustomTypeParameter
    public boolean isTypeParameter() {
        return this.getLowerBound().getConstructor().getDeclarationDescriptor() instanceof TypeParameterDescriptor && Intrinsics.areEqual(this.getLowerBound().getConstructor(), this.getUpperBound().getConstructor());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType makeNullableAsSpecified(boolean z) {
        return KotlinTypeFactory.flexibleType(this.getLowerBound().makeNullableAsSpecified(z), this.getUpperBound().makeNullableAsSpecified(z));
    }

    public FlexibleType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        KotlinType kotlinType0 = kotlinTypeRefiner0.refineType(this.getLowerBound());
        Intrinsics.checkNotNull(kotlinType0, "null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        KotlinType kotlinType1 = kotlinTypeRefiner0.refineType(this.getUpperBound());
        Intrinsics.checkNotNull(kotlinType1, "null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        return new FlexibleTypeImpl(((SimpleType)kotlinType0), ((SimpleType)kotlinType1));
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
        return descriptorRendererOptions0.getDebugMode() ? "(" + descriptorRenderer0.renderType(this.getLowerBound()) + ".." + descriptorRenderer0.renderType(this.getUpperBound()) + ')' : descriptorRenderer0.renderFlexibleType(descriptorRenderer0.renderType(this.getLowerBound()), descriptorRenderer0.renderType(this.getUpperBound()), TypeUtilsKt.getBuiltIns(this));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType replaceAttributes(TypeAttributes typeAttributes0) {
        Intrinsics.checkNotNullParameter(typeAttributes0, "newAttributes");
        return KotlinTypeFactory.flexibleType(this.getLowerBound().replaceAttributes(typeAttributes0), this.getUpperBound().replaceAttributes(typeAttributes0));
    }

    private final void runAssertions() {
        if(FlexibleTypeImpl.RUN_SLOW_ASSERTIONS && !this.assertionsDone) {
            this.assertionsDone = true;
            FlexibleTypesKt.isFlexible(this.getLowerBound());
            FlexibleTypesKt.isFlexible(this.getUpperBound());
            Intrinsics.areEqual(this.getLowerBound(), this.getUpperBound());
            KotlinType kotlinType0 = this.getLowerBound();
            KotlinType kotlinType1 = this.getUpperBound();
            KotlinTypeChecker.DEFAULT.isSubtypeOf(kotlinType0, kotlinType1);
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.CustomTypeParameter
    public KotlinType substitutionResult(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "replacement");
        UnwrappedType unwrappedType0 = kotlinType0.unwrap();
        if(unwrappedType0 instanceof FlexibleType) {
            return TypeWithEnhancementKt.inheritEnhancement(unwrappedType0, unwrappedType0);
        }
        if(!(unwrappedType0 instanceof SimpleType)) {
            throw new NoWhenBranchMatchedException();
        }
        return TypeWithEnhancementKt.inheritEnhancement(KotlinTypeFactory.flexibleType(((SimpleType)unwrappedType0), ((SimpleType)unwrappedType0).makeNullableAsSpecified(true)), unwrappedType0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.FlexibleType
    public String toString() {
        return "(" + this.getLowerBound() + ".." + this.getUpperBound() + ')';
    }
}

