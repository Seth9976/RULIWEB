package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.TypeParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedType;
import kotlin.reflect.jvm.internal.impl.types.checker.NewTypeVariableConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.NullabilityChecker;
import kotlin.reflect.jvm.internal.impl.types.model.DefinitelyNotNullTypeMarker;

public final class DefinitelyNotNullType extends DelegatingSimpleType implements CustomTypeParameter, DefinitelyNotNullTypeMarker {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        // 去混淆评级： 低(40)
        private final boolean canHaveUndefinedNullability(UnwrappedType unwrappedType0) {
            return unwrappedType0.getConstructor() instanceof NewTypeVariableConstructor || unwrappedType0.getConstructor().getDeclarationDescriptor() instanceof TypeParameterDescriptor || unwrappedType0 instanceof NewCapturedType || unwrappedType0 instanceof StubTypeForBuilderInference;
        }

        public final DefinitelyNotNullType makeDefinitelyNotNull(UnwrappedType unwrappedType0, boolean z, boolean z1) {
            Intrinsics.checkNotNullParameter(unwrappedType0, "type");
            if(unwrappedType0 instanceof DefinitelyNotNullType) {
                return (DefinitelyNotNullType)unwrappedType0;
            }
            if(!z1 && !this.makesSenseToBeDefinitelyNotNull(unwrappedType0, z)) {
                return null;
            }
            if(unwrappedType0 instanceof FlexibleType) {
                Intrinsics.areEqual(((FlexibleType)unwrappedType0).getLowerBound().getConstructor(), ((FlexibleType)unwrappedType0).getUpperBound().getConstructor());
            }
            return new DefinitelyNotNullType(FlexibleTypesKt.lowerIfFlexible(unwrappedType0).makeNullableAsSpecified(false), z, null);
        }

        public static DefinitelyNotNullType makeDefinitelyNotNull$default(Companion definitelyNotNullType$Companion0, UnwrappedType unwrappedType0, boolean z, boolean z1, int v, Object object0) {
            if((v & 2) != 0) {
                z = false;
            }
            if((v & 4) != 0) {
                z1 = false;
            }
            return definitelyNotNullType$Companion0.makeDefinitelyNotNull(unwrappedType0, z, z1);
        }

        private final boolean makesSenseToBeDefinitelyNotNull(UnwrappedType unwrappedType0, boolean z) {
            if(!this.canHaveUndefinedNullability(unwrappedType0)) {
                return false;
            }
            if(unwrappedType0 instanceof StubTypeForBuilderInference) {
                return TypeUtils.isNullableType(unwrappedType0);
            }
            ClassifierDescriptor classifierDescriptor0 = unwrappedType0.getConstructor().getDeclarationDescriptor();
            TypeParameterDescriptorImpl typeParameterDescriptorImpl0 = classifierDescriptor0 instanceof TypeParameterDescriptorImpl ? ((TypeParameterDescriptorImpl)classifierDescriptor0) : null;
            if(typeParameterDescriptorImpl0 != null && !typeParameterDescriptorImpl0.isInitialized()) {
                return true;
            }
            return !z || !(unwrappedType0.getConstructor().getDeclarationDescriptor() instanceof TypeParameterDescriptor) ? !NullabilityChecker.INSTANCE.isSubtypeOfAny(unwrappedType0) : TypeUtils.isNullableType(unwrappedType0);
        }
    }

    public static final Companion Companion;
    private final SimpleType original;
    private final boolean useCorrectedNullabilityForTypeParameters;

    static {
        DefinitelyNotNullType.Companion = new Companion(null);
    }

    private DefinitelyNotNullType(SimpleType simpleType0, boolean z) {
        this.original = simpleType0;
        this.useCorrectedNullabilityForTypeParameters = z;
    }

    public DefinitelyNotNullType(SimpleType simpleType0, boolean z, DefaultConstructorMarker defaultConstructorMarker0) {
        this(simpleType0, z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    protected SimpleType getDelegate() {
        return this.original;
    }

    public final SimpleType getOriginal() {
        return this.original;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    public boolean isMarkedNullable() {
        return false;
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.reflect.jvm.internal.impl.types.CustomTypeParameter
    public boolean isTypeParameter() {
        return this.getDelegate().getConstructor() instanceof NewTypeVariableConstructor || this.getDelegate().getConstructor().getDeclarationDescriptor() instanceof TypeParameterDescriptor;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public SimpleType makeNullableAsSpecified(boolean z) {
        return z ? this.getDelegate().makeNullableAsSpecified(true) : this;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType makeNullableAsSpecified(boolean z) {
        return this.makeNullableAsSpecified(z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public SimpleType replaceAttributes(TypeAttributes typeAttributes0) {
        Intrinsics.checkNotNullParameter(typeAttributes0, "newAttributes");
        return new DefinitelyNotNullType(this.getDelegate().replaceAttributes(typeAttributes0), this.useCorrectedNullabilityForTypeParameters);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType replaceAttributes(TypeAttributes typeAttributes0) {
        return this.replaceAttributes(typeAttributes0);
    }

    public DefinitelyNotNullType replaceDelegate(SimpleType simpleType0) {
        Intrinsics.checkNotNullParameter(simpleType0, "delegate");
        return new DefinitelyNotNullType(simpleType0, this.useCorrectedNullabilityForTypeParameters);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    public DelegatingSimpleType replaceDelegate(SimpleType simpleType0) {
        return this.replaceDelegate(simpleType0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.CustomTypeParameter
    public KotlinType substitutionResult(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "replacement");
        return SpecialTypesKt.makeDefinitelyNotNullOrNotNull(kotlinType0.unwrap(), this.useCorrectedNullabilityForTypeParameters);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public String toString() {
        return this.getDelegate() + " & Any";
    }
}

