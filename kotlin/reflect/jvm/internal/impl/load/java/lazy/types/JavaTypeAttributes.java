package kotlin.reflect.jvm.internal.impl.load.java.lazy.types;

import java.util.Set;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.ErasureTypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeUsage;

public final class JavaTypeAttributes extends ErasureTypeAttributes {
    private final SimpleType defaultType;
    private final JavaTypeFlexibility flexibility;
    private final TypeUsage howThisTypeIsUsed;
    private final boolean isForAnnotationParameter;
    private final boolean isRaw;
    private final Set visitedTypeParameters;

    public JavaTypeAttributes(TypeUsage typeUsage0, JavaTypeFlexibility javaTypeFlexibility0, boolean z, boolean z1, Set set0, SimpleType simpleType0) {
        Intrinsics.checkNotNullParameter(typeUsage0, "howThisTypeIsUsed");
        Intrinsics.checkNotNullParameter(javaTypeFlexibility0, "flexibility");
        super(typeUsage0, set0, simpleType0);
        this.howThisTypeIsUsed = typeUsage0;
        this.flexibility = javaTypeFlexibility0;
        this.isRaw = z;
        this.isForAnnotationParameter = z1;
        this.visitedTypeParameters = set0;
        this.defaultType = simpleType0;
    }

    public JavaTypeAttributes(TypeUsage typeUsage0, JavaTypeFlexibility javaTypeFlexibility0, boolean z, boolean z1, Set set0, SimpleType simpleType0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            javaTypeFlexibility0 = JavaTypeFlexibility.INFLEXIBLE;
        }
        this(typeUsage0, javaTypeFlexibility0, ((v & 4) == 0 ? z : false), ((v & 8) == 0 ? z1 : false), ((v & 16) == 0 ? set0 : null), ((v & 0x20) == 0 ? simpleType0 : null));
    }

    public final JavaTypeAttributes copy(TypeUsage typeUsage0, JavaTypeFlexibility javaTypeFlexibility0, boolean z, boolean z1, Set set0, SimpleType simpleType0) {
        Intrinsics.checkNotNullParameter(typeUsage0, "howThisTypeIsUsed");
        Intrinsics.checkNotNullParameter(javaTypeFlexibility0, "flexibility");
        return new JavaTypeAttributes(typeUsage0, javaTypeFlexibility0, z, z1, set0, simpleType0);
    }

    public static JavaTypeAttributes copy$default(JavaTypeAttributes javaTypeAttributes0, TypeUsage typeUsage0, JavaTypeFlexibility javaTypeFlexibility0, boolean z, boolean z1, Set set0, SimpleType simpleType0, int v, Object object0) {
        if((v & 1) != 0) {
            typeUsage0 = javaTypeAttributes0.howThisTypeIsUsed;
        }
        if((v & 2) != 0) {
            javaTypeFlexibility0 = javaTypeAttributes0.flexibility;
        }
        if((v & 4) != 0) {
            z = javaTypeAttributes0.isRaw;
        }
        if((v & 8) != 0) {
            z1 = javaTypeAttributes0.isForAnnotationParameter;
        }
        if((v & 16) != 0) {
            set0 = javaTypeAttributes0.visitedTypeParameters;
        }
        if((v & 0x20) != 0) {
            simpleType0 = javaTypeAttributes0.defaultType;
        }
        return javaTypeAttributes0.copy(typeUsage0, javaTypeFlexibility0, z, z1, set0, simpleType0);
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.reflect.jvm.internal.impl.types.ErasureTypeAttributes
    public boolean equals(Object object0) {
        return object0 instanceof JavaTypeAttributes ? Intrinsics.areEqual(((JavaTypeAttributes)object0).getDefaultType(), this.getDefaultType()) && ((JavaTypeAttributes)object0).getHowThisTypeIsUsed() == this.getHowThisTypeIsUsed() && ((JavaTypeAttributes)object0).flexibility == this.flexibility && ((JavaTypeAttributes)object0).isRaw == this.isRaw && ((JavaTypeAttributes)object0).isForAnnotationParameter == this.isForAnnotationParameter : false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.ErasureTypeAttributes
    public SimpleType getDefaultType() {
        return this.defaultType;
    }

    public final JavaTypeFlexibility getFlexibility() {
        return this.flexibility;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.ErasureTypeAttributes
    public TypeUsage getHowThisTypeIsUsed() {
        return this.howThisTypeIsUsed;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.ErasureTypeAttributes
    public Set getVisitedTypeParameters() {
        return this.visitedTypeParameters;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.ErasureTypeAttributes
    public int hashCode() {
        SimpleType simpleType0 = this.getDefaultType();
        int v = simpleType0 == null ? 0 : simpleType0.hashCode();
        int v1 = v + (v * 0x1F + this.getHowThisTypeIsUsed().hashCode());
        int v2 = 0x400 * v + this.flexibility.hashCode() + 0x20 * this.getHowThisTypeIsUsed().hashCode();
        int v3 = v2 + (v2 * 0x1F + this.isRaw);
        return v3 + (v3 * 0x1F + this.isForAnnotationParameter);
    }

    public final boolean isForAnnotationParameter() {
        return this.isForAnnotationParameter;
    }

    public final boolean isRaw() {
        return this.isRaw;
    }

    public final JavaTypeAttributes markIsRaw(boolean z) {
        return JavaTypeAttributes.copy$default(this, null, null, z, false, null, null, 59, null);
    }

    @Override
    public String toString() {
        return "JavaTypeAttributes(howThisTypeIsUsed=" + this.howThisTypeIsUsed + ", flexibility=" + this.flexibility + ", isRaw=" + this.isRaw + ", isForAnnotationParameter=" + this.isForAnnotationParameter + ", visitedTypeParameters=" + this.visitedTypeParameters + ", defaultType=" + this.defaultType + ')';
    }

    public JavaTypeAttributes withDefaultType(SimpleType simpleType0) {
        return JavaTypeAttributes.copy$default(this, null, null, false, false, null, simpleType0, 0x1F, null);
    }

    public final JavaTypeAttributes withFlexibility(JavaTypeFlexibility javaTypeFlexibility0) {
        Intrinsics.checkNotNullParameter(javaTypeFlexibility0, "flexibility");
        return JavaTypeAttributes.copy$default(this, null, javaTypeFlexibility0, false, false, null, null, 61, null);
    }

    public JavaTypeAttributes withNewVisitedTypeParameter(TypeParameterDescriptor typeParameterDescriptor0) {
        Intrinsics.checkNotNullParameter(typeParameterDescriptor0, "typeParameter");
        return this.getVisitedTypeParameters() == null ? JavaTypeAttributes.copy$default(this, null, null, false, false, SetsKt.setOf(typeParameterDescriptor0), null, 0x2F, null) : JavaTypeAttributes.copy$default(this, null, null, false, false, SetsKt.plus(this.getVisitedTypeParameters(), typeParameterDescriptor0), null, 0x2F, null);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.ErasureTypeAttributes
    public ErasureTypeAttributes withNewVisitedTypeParameter(TypeParameterDescriptor typeParameterDescriptor0) {
        return this.withNewVisitedTypeParameter(typeParameterDescriptor0);
    }
}

