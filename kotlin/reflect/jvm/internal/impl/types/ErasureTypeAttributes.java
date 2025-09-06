package kotlin.reflect.jvm.internal.impl.types;

import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;

public class ErasureTypeAttributes {
    private final SimpleType defaultType;
    private final TypeUsage howThisTypeIsUsed;
    private final Set visitedTypeParameters;

    public ErasureTypeAttributes(TypeUsage typeUsage0, Set set0, SimpleType simpleType0) {
        Intrinsics.checkNotNullParameter(typeUsage0, "howThisTypeIsUsed");
        super();
        this.howThisTypeIsUsed = typeUsage0;
        this.visitedTypeParameters = set0;
        this.defaultType = simpleType0;
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof ErasureTypeAttributes ? Intrinsics.areEqual(((ErasureTypeAttributes)object0).getDefaultType(), this.getDefaultType()) && ((ErasureTypeAttributes)object0).getHowThisTypeIsUsed() == this.getHowThisTypeIsUsed() : false;
    }

    public SimpleType getDefaultType() {
        return this.defaultType;
    }

    public TypeUsage getHowThisTypeIsUsed() {
        return this.howThisTypeIsUsed;
    }

    public Set getVisitedTypeParameters() {
        return this.visitedTypeParameters;
    }

    @Override
    public int hashCode() {
        SimpleType simpleType0 = this.getDefaultType();
        if(simpleType0 != null) {
            int v = simpleType0.hashCode();
            return v + (v * 0x1F + this.getHowThisTypeIsUsed().hashCode());
        }
        return this.getHowThisTypeIsUsed().hashCode();
    }

    public ErasureTypeAttributes withNewVisitedTypeParameter(TypeParameterDescriptor typeParameterDescriptor0) {
        Intrinsics.checkNotNullParameter(typeParameterDescriptor0, "typeParameter");
        TypeUsage typeUsage0 = this.getHowThisTypeIsUsed();
        Set set0 = this.getVisitedTypeParameters();
        if(set0 != null) {
            Set set1 = SetsKt.plus(set0, typeParameterDescriptor0);
            return set1 == null ? new ErasureTypeAttributes(typeUsage0, SetsKt.setOf(typeParameterDescriptor0), this.getDefaultType()) : new ErasureTypeAttributes(typeUsage0, set1, this.getDefaultType());
        }
        return new ErasureTypeAttributes(typeUsage0, SetsKt.setOf(typeParameterDescriptor0), this.getDefaultType());
    }
}

