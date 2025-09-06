package kotlin.reflect.jvm.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KType;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u001A\u0010\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0001H\u0000\u001A\u0010\u0010\u0003\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0001H\u0000\u001A\u0018\u0010\u0004\u001A\u00020\u00012\u0006\u0010\u0005\u001A\u00020\u00012\u0006\u0010\u0006\u001A\u00020\u0001H\u0000\u001A\f\u0010\u0007\u001A\u00020\b*\u00020\bH\u0002¨\u0006\t"}, d2 = {"createMutableCollectionKType", "Lkotlin/reflect/KType;", "type", "createNothingType", "createPlatformKType", "lowerBound", "upperBound", "readOnlyToMutable", "Lkotlin/reflect/jvm/internal/impl/descriptors/ClassDescriptor;", "kotlin-reflection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class TypeOfImplKt {
    public static final KType createMutableCollectionKType(KType kType0) {
        Intrinsics.checkNotNullParameter(kType0, "type");
        KotlinType kotlinType0 = ((KTypeImpl)kType0).getType();
        if(!(kotlinType0 instanceof SimpleType)) {
            throw new IllegalArgumentException(("Non-simple type cannot be a mutable collection type: " + kType0).toString());
        }
        ClassifierDescriptor classifierDescriptor0 = kotlinType0.getConstructor().getDeclarationDescriptor();
        ClassDescriptor classDescriptor0 = classifierDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor0) : null;
        if(classDescriptor0 == null) {
            throw new IllegalArgumentException("Non-class type cannot be a mutable collection type: " + kType0);
        }
        TypeConstructor typeConstructor0 = TypeOfImplKt.readOnlyToMutable(classDescriptor0).getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor0, "classifier.readOnlyToMutable().typeConstructor");
        return new KTypeImpl(KotlinTypeFactory.simpleType$default(((SimpleType)kotlinType0), null, typeConstructor0, null, false, 26, null), null, 2, null);
    }

    public static final KType createNothingType(KType kType0) {
        Intrinsics.checkNotNullParameter(kType0, "type");
        KotlinType kotlinType0 = ((KTypeImpl)kType0).getType();
        if(!(kotlinType0 instanceof SimpleType)) {
            throw new IllegalArgumentException(("Non-simple type cannot be a Nothing type: " + kType0).toString());
        }
        TypeConstructor typeConstructor0 = TypeUtilsKt.getBuiltIns(kotlinType0).getNothing().getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor0, "kotlinType.builtIns.nothing.typeConstructor");
        return new KTypeImpl(KotlinTypeFactory.simpleType$default(((SimpleType)kotlinType0), null, typeConstructor0, null, false, 26, null), null, 2, null);
    }

    public static final KType createPlatformKType(KType kType0, KType kType1) {
        Intrinsics.checkNotNullParameter(kType0, "lowerBound");
        Intrinsics.checkNotNullParameter(kType1, "upperBound");
        KotlinType kotlinType0 = ((KTypeImpl)kType0).getType();
        Intrinsics.checkNotNull(kotlinType0, "null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        KotlinType kotlinType1 = ((KTypeImpl)kType1).getType();
        Intrinsics.checkNotNull(kotlinType1, "null cannot be cast to non-null type org.jetbrains.kotlin.types.SimpleType");
        return new KTypeImpl(KotlinTypeFactory.flexibleType(((SimpleType)kotlinType0), ((SimpleType)kotlinType1)), null, 2, null);
    }

    private static final ClassDescriptor readOnlyToMutable(ClassDescriptor classDescriptor0) {
        FqNameUnsafe fqNameUnsafe0 = DescriptorUtilsKt.getFqNameUnsafe(classDescriptor0);
        FqName fqName0 = JavaToKotlinClassMap.INSTANCE.readOnlyToMutable(fqNameUnsafe0);
        if(fqName0 == null) {
            throw new IllegalArgumentException("Not a readonly collection: " + classDescriptor0);
        }
        ClassDescriptor classDescriptor1 = DescriptorUtilsKt.getBuiltIns(classDescriptor0).getBuiltInClassByFqName(fqName0);
        Intrinsics.checkNotNullExpressionValue(classDescriptor1, "builtIns.getBuiltInClassByFqName(fqName)");
        return classDescriptor1;
    }
}

