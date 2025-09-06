package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;

public final class CompanionObjectMapping {
    public static final CompanionObjectMapping INSTANCE;
    private static final Set classIds;

    static {
        CompanionObjectMapping.INSTANCE = new CompanionObjectMapping();
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(PrimitiveType.NUMBER_TYPES, 10));
        for(Object object0: PrimitiveType.NUMBER_TYPES) {
            arrayList0.add(StandardNames.getPrimitiveFqName(((PrimitiveType)object0)));
        }
        FqName fqName0 = FqNames.string.toSafe();
        Intrinsics.checkNotNullExpressionValue(fqName0, "string.toSafe()");
        Collection collection0 = CollectionsKt.plus(arrayList0, fqName0);
        FqName fqName1 = FqNames._boolean.toSafe();
        Intrinsics.checkNotNullExpressionValue(fqName1, "_boolean.toSafe()");
        Collection collection1 = CollectionsKt.plus(collection0, fqName1);
        FqName fqName2 = FqNames._enum.toSafe();
        Intrinsics.checkNotNullExpressionValue(fqName2, "_enum.toSafe()");
        Iterable iterable0 = CollectionsKt.plus(collection1, fqName2);
        Collection collection2 = new LinkedHashSet();
        for(Object object1: iterable0) {
            collection2.add(ClassId.topLevel(((FqName)object1)));
        }
        CompanionObjectMapping.classIds = (Set)collection2;
    }

    public final Set allClassesWithIntrinsicCompanions() {
        return CompanionObjectMapping.classIds;
    }

    public final Set getClassIds() {
        return CompanionObjectMapping.classIds;
    }
}

