package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution.Companion;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public final class MappingUtilKt {
    public static final TypeConstructorSubstitution createMappedTypeParametersSubstitution(ClassDescriptor classDescriptor0, ClassDescriptor classDescriptor1) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "from");
        Intrinsics.checkNotNullParameter(classDescriptor1, "to");
        classDescriptor0.getDeclaredTypeParameters().size();
        classDescriptor1.getDeclaredTypeParameters().size();
        Companion typeConstructorSubstitution$Companion0 = TypeConstructorSubstitution.Companion;
        List list0 = classDescriptor0.getDeclaredTypeParameters();
        Intrinsics.checkNotNullExpressionValue(list0, "from.declaredTypeParameters");
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            arrayList0.add(((TypeParameterDescriptor)object0).getTypeConstructor());
        }
        List list1 = classDescriptor1.getDeclaredTypeParameters();
        Intrinsics.checkNotNullExpressionValue(list1, "to.declaredTypeParameters");
        ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list1, 10));
        for(Object object1: list1) {
            SimpleType simpleType0 = ((TypeParameterDescriptor)object1).getDefaultType();
            Intrinsics.checkNotNullExpressionValue(simpleType0, "it.defaultType");
            arrayList1.add(TypeUtilsKt.asTypeProjection(simpleType0));
        }
        return Companion.createByConstructorsMap$default(typeConstructorSubstitution$Companion0, MapsKt.toMap(CollectionsKt.zip(arrayList0, arrayList1)), false, 2, null);
    }
}

