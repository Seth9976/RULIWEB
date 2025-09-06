package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.Collection;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;

public final class JavaToKotlinClassMapper {
    public static final JavaToKotlinClassMapper INSTANCE;

    static {
        JavaToKotlinClassMapper.INSTANCE = new JavaToKotlinClassMapper();
    }

    public final ClassDescriptor convertMutableToReadOnly(ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "mutable");
        FqNameUnsafe fqNameUnsafe0 = DescriptorUtils.getFqName(classDescriptor0);
        FqName fqName0 = JavaToKotlinClassMap.INSTANCE.mutableToReadOnly(fqNameUnsafe0);
        if(fqName0 == null) {
            throw new IllegalArgumentException("Given class " + classDescriptor0 + " is not a mutable collection");
        }
        ClassDescriptor classDescriptor1 = DescriptorUtilsKt.getBuiltIns(classDescriptor0).getBuiltInClassByFqName(fqName0);
        Intrinsics.checkNotNullExpressionValue(classDescriptor1, "descriptor.builtIns.getB…Name(oppositeClassFqName)");
        return classDescriptor1;
    }

    public final ClassDescriptor convertReadOnlyToMutable(ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "readOnly");
        FqNameUnsafe fqNameUnsafe0 = DescriptorUtils.getFqName(classDescriptor0);
        FqName fqName0 = JavaToKotlinClassMap.INSTANCE.readOnlyToMutable(fqNameUnsafe0);
        if(fqName0 == null) {
            throw new IllegalArgumentException("Given class " + classDescriptor0 + " is not a read-only collection");
        }
        ClassDescriptor classDescriptor1 = DescriptorUtilsKt.getBuiltIns(classDescriptor0).getBuiltInClassByFqName(fqName0);
        Intrinsics.checkNotNullExpressionValue(classDescriptor1, "descriptor.builtIns.getB…Name(oppositeClassFqName)");
        return classDescriptor1;
    }

    public final boolean isMutable(ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "mutable");
        FqNameUnsafe fqNameUnsafe0 = DescriptorUtils.getFqName(classDescriptor0);
        return JavaToKotlinClassMap.INSTANCE.isMutable(fqNameUnsafe0);
    }

    public final boolean isReadOnly(ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "readOnly");
        FqNameUnsafe fqNameUnsafe0 = DescriptorUtils.getFqName(classDescriptor0);
        return JavaToKotlinClassMap.INSTANCE.isReadOnly(fqNameUnsafe0);
    }

    public final ClassDescriptor mapJavaToKotlin(FqName fqName0, KotlinBuiltIns kotlinBuiltIns0, Integer integer0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Intrinsics.checkNotNullParameter(kotlinBuiltIns0, "builtIns");
        ClassId classId0 = integer0 == null || !Intrinsics.areEqual(fqName0, JavaToKotlinClassMap.INSTANCE.getFUNCTION_N_FQ_NAME()) ? JavaToKotlinClassMap.INSTANCE.mapJavaToKotlin(fqName0) : StandardNames.getFunctionClassId(((int)integer0));
        return classId0 == null ? null : kotlinBuiltIns0.getBuiltInClassByFqName(classId0.asSingleFqName());
    }

    public static ClassDescriptor mapJavaToKotlin$default(JavaToKotlinClassMapper javaToKotlinClassMapper0, FqName fqName0, KotlinBuiltIns kotlinBuiltIns0, Integer integer0, int v, Object object0) {
        if((v & 4) != 0) {
            integer0 = null;
        }
        return javaToKotlinClassMapper0.mapJavaToKotlin(fqName0, kotlinBuiltIns0, integer0);
    }

    public final Collection mapPlatformClass(FqName fqName0, KotlinBuiltIns kotlinBuiltIns0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Intrinsics.checkNotNullParameter(kotlinBuiltIns0, "builtIns");
        ClassDescriptor classDescriptor0 = JavaToKotlinClassMapper.mapJavaToKotlin$default(this, fqName0, kotlinBuiltIns0, null, 4, null);
        if(classDescriptor0 == null) {
            return SetsKt.emptySet();
        }
        FqNameUnsafe fqNameUnsafe0 = DescriptorUtilsKt.getFqNameUnsafe(classDescriptor0);
        FqName fqName1 = JavaToKotlinClassMap.INSTANCE.readOnlyToMutable(fqNameUnsafe0);
        if(fqName1 == null) {
            return SetsKt.setOf(classDescriptor0);
        }
        ClassDescriptor[] arr_classDescriptor = new ClassDescriptor[2];
        arr_classDescriptor[0] = classDescriptor0;
        ClassDescriptor classDescriptor1 = kotlinBuiltIns0.getBuiltInClassByFqName(fqName1);
        Intrinsics.checkNotNullExpressionValue(classDescriptor1, "builtIns.getBuiltInClass…otlinMutableAnalogFqName)");
        arr_classDescriptor[1] = classDescriptor1;
        return CollectionsKt.listOf(arr_classDescriptor);
    }
}

