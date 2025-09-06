package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.NotNullTypeParameter;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.TypeWithEnhancementKt;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public final class NotNullTypeParameterImpl extends DelegatingSimpleType implements NotNullTypeParameter {
    private final SimpleType delegate;

    public NotNullTypeParameterImpl(SimpleType simpleType0) {
        Intrinsics.checkNotNullParameter(simpleType0, "delegate");
        super();
        this.delegate = simpleType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    protected SimpleType getDelegate() {
        return this.delegate;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    public boolean isMarkedNullable() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.CustomTypeParameter
    public boolean isTypeParameter() {
        return true;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public SimpleType makeNullableAsSpecified(boolean z) {
        return z ? this.getDelegate().makeNullableAsSpecified(true) : this;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType makeNullableAsSpecified(boolean z) {
        return this.makeNullableAsSpecified(z);
    }

    private final SimpleType prepareReplacement(SimpleType simpleType0) {
        SimpleType simpleType1 = simpleType0.makeNullableAsSpecified(false);
        return !TypeUtilsKt.isTypeParameter(simpleType0) ? simpleType1 : new NotNullTypeParameterImpl(simpleType1);
    }

    public NotNullTypeParameterImpl replaceAttributes(TypeAttributes typeAttributes0) {
        Intrinsics.checkNotNullParameter(typeAttributes0, "newAttributes");
        return new NotNullTypeParameterImpl(this.getDelegate().replaceAttributes(typeAttributes0));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.SimpleType
    public SimpleType replaceAttributes(TypeAttributes typeAttributes0) {
        return this.replaceAttributes(typeAttributes0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.UnwrappedType
    public UnwrappedType replaceAttributes(TypeAttributes typeAttributes0) {
        return this.replaceAttributes(typeAttributes0);
    }

    public NotNullTypeParameterImpl replaceDelegate(SimpleType simpleType0) {
        Intrinsics.checkNotNullParameter(simpleType0, "delegate");
        return new NotNullTypeParameterImpl(simpleType0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.DelegatingSimpleType
    public DelegatingSimpleType replaceDelegate(SimpleType simpleType0) {
        return this.replaceDelegate(simpleType0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.CustomTypeParameter
    public KotlinType substitutionResult(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "replacement");
        UnwrappedType unwrappedType0 = kotlinType0.unwrap();
        if(!TypeUtilsKt.isTypeParameter(unwrappedType0) && !TypeUtils.isNullableType(unwrappedType0)) {
            return unwrappedType0;
        }
        if(unwrappedType0 instanceof SimpleType) {
            return this.prepareReplacement(((SimpleType)unwrappedType0));
        }
        if(!(unwrappedType0 instanceof FlexibleType)) {
            throw new IllegalStateException(("Incorrect type: " + unwrappedType0).toString());
        }
        return TypeWithEnhancementKt.wrapEnhancement(KotlinTypeFactory.flexibleType(this.prepareReplacement(((FlexibleType)unwrappedType0).getLowerBound()), this.prepareReplacement(((FlexibleType)unwrappedType0).getUpperBound())), TypeWithEnhancementKt.getEnhancement(unwrappedType0));
    }
}

