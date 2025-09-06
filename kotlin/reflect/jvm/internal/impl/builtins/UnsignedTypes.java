package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;

public final class UnsignedTypes {
    public static final UnsignedTypes INSTANCE;
    private static final HashMap arrayClassIdToUnsignedClassId;
    private static final Set arrayClassesShortNames;
    private static final Set unsignedArrayTypeNames;
    private static final HashMap unsignedArrayTypeToArrayCall;
    private static final HashMap unsignedClassIdToArrayClassId;
    private static final Set unsignedTypeNames;

    static {
        UnsignedTypes.INSTANCE = new UnsignedTypes();
        UnsignedType[] arr_unsignedType = UnsignedType.values();
        ArrayList arrayList0 = new ArrayList(arr_unsignedType.length);
        for(int v1 = 0; v1 < arr_unsignedType.length; ++v1) {
            arrayList0.add(arr_unsignedType[v1].getTypeName());
        }
        UnsignedTypes.unsignedTypeNames = CollectionsKt.toSet(arrayList0);
        UnsignedArrayType[] arr_unsignedArrayType = UnsignedArrayType.values();
        ArrayList arrayList1 = new ArrayList(arr_unsignedArrayType.length);
        for(int v2 = 0; v2 < arr_unsignedArrayType.length; ++v2) {
            arrayList1.add(arr_unsignedArrayType[v2].getTypeName());
        }
        UnsignedTypes.unsignedArrayTypeNames = CollectionsKt.toSet(arrayList1);
        UnsignedTypes.arrayClassIdToUnsignedClassId = new HashMap();
        UnsignedTypes.unsignedClassIdToArrayClassId = new HashMap();
        Pair[] arr_pair = new Pair[4];
        Name name0 = Name.identifier("ubyteArrayOf");
        arr_pair[0] = TuplesKt.to(UnsignedArrayType.UBYTEARRAY, name0);
        Name name1 = Name.identifier("ushortArrayOf");
        arr_pair[1] = TuplesKt.to(UnsignedArrayType.USHORTARRAY, name1);
        Name name2 = Name.identifier("uintArrayOf");
        arr_pair[2] = TuplesKt.to(UnsignedArrayType.UINTARRAY, name2);
        Name name3 = Name.identifier("ulongArrayOf");
        arr_pair[3] = TuplesKt.to(UnsignedArrayType.ULONGARRAY, name3);
        UnsignedTypes.unsignedArrayTypeToArrayCall = MapsKt.hashMapOf(arr_pair);
        UnsignedType[] arr_unsignedType1 = UnsignedType.values();
        Collection collection0 = new LinkedHashSet();
        for(int v3 = 0; v3 < arr_unsignedType1.length; ++v3) {
            collection0.add(arr_unsignedType1[v3].getArrayClassId().getShortClassName());
        }
        UnsignedTypes.arrayClassesShortNames = (Set)collection0;
        UnsignedType[] arr_unsignedType2 = UnsignedType.values();
        for(int v = 0; v < arr_unsignedType2.length; ++v) {
            UnsignedType unsignedType0 = arr_unsignedType2[v];
            UnsignedTypes.arrayClassIdToUnsignedClassId.put(unsignedType0.getArrayClassId(), unsignedType0.getClassId());
            UnsignedTypes.unsignedClassIdToArrayClassId.put(unsignedType0.getClassId(), unsignedType0.getArrayClassId());
        }
    }

    public final ClassId getUnsignedClassIdByArrayClassId(ClassId classId0) {
        Intrinsics.checkNotNullParameter(classId0, "arrayClassId");
        return (ClassId)UnsignedTypes.arrayClassIdToUnsignedClassId.get(classId0);
    }

    public final boolean isShortNameOfUnsignedArray(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        return UnsignedTypes.arrayClassesShortNames.contains(name0);
    }

    public final boolean isUnsignedClass(DeclarationDescriptor declarationDescriptor0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "descriptor");
        DeclarationDescriptor declarationDescriptor1 = declarationDescriptor0.getContainingDeclaration();
        if(declarationDescriptor1 instanceof PackageFragmentDescriptor && Intrinsics.areEqual(((PackageFragmentDescriptor)declarationDescriptor1).getFqName(), StandardNames.BUILT_INS_PACKAGE_FQ_NAME)) {
            Name name0 = declarationDescriptor0.getName();
            return UnsignedTypes.unsignedTypeNames.contains(name0);
        }
        return false;
    }

    @JvmStatic
    public static final boolean isUnsignedType(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "type");
        if(TypeUtils.noExpectedType(kotlinType0)) {
            return false;
        }
        ClassifierDescriptor classifierDescriptor0 = kotlinType0.getConstructor().getDeclarationDescriptor();
        return classifierDescriptor0 == null ? false : UnsignedTypes.INSTANCE.isUnsignedClass(classifierDescriptor0);
    }
}

