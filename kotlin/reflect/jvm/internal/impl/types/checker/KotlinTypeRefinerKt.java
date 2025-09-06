package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleCapability;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public final class KotlinTypeRefinerKt {
    private static final ModuleCapability REFINER_CAPABILITY;

    static {
        KotlinTypeRefinerKt.REFINER_CAPABILITY = new ModuleCapability("KotlinTypeRefiner");
    }

    public static final ModuleCapability getREFINER_CAPABILITY() {
        return KotlinTypeRefinerKt.REFINER_CAPABILITY;
    }

    public static final List refineTypes(KotlinTypeRefiner kotlinTypeRefiner0, Iterable iterable0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "<this>");
        Intrinsics.checkNotNullParameter(iterable0, "types");
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            arrayList0.add(kotlinTypeRefiner0.refineType(((KotlinType)object0)));
        }
        return arrayList0;
    }
}

