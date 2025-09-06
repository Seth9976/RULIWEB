package kotlin.reflect.jvm.internal.impl.types.checker;

import java.util.ArrayList;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.DynamicTypesKt;
import kotlin.reflect.jvm.internal.impl.types.FlexibleType;
import kotlin.reflect.jvm.internal.impl.types.FlexibleTypesKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorTypeKind;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;

public final class IntersectionTypeKt {
    public static final UnwrappedType intersectTypes(List list0) {
        SimpleType simpleType0;
        Intrinsics.checkNotNullParameter(list0, "types");
        switch(list0.size()) {
            case 0: {
                throw new IllegalStateException("Expected some types");
            }
            case 1: {
                return (UnwrappedType)CollectionsKt.single(list0);
            }
            default: {
                ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
                boolean z = false;
                boolean z1 = false;
                for(Object object0: list0) {
                    UnwrappedType unwrappedType0 = (UnwrappedType)object0;
                    z = z || KotlinTypeKt.isError(unwrappedType0);
                    if(unwrappedType0 instanceof SimpleType) {
                        simpleType0 = (SimpleType)unwrappedType0;
                    }
                    else {
                        if(!(unwrappedType0 instanceof FlexibleType)) {
                            throw new NoWhenBranchMatchedException();
                        }
                        if(DynamicTypesKt.isDynamic(unwrappedType0)) {
                            return unwrappedType0;
                        }
                        simpleType0 = ((FlexibleType)unwrappedType0).getLowerBound();
                        z1 = true;
                    }
                    arrayList0.add(simpleType0);
                }
                if(z) {
                    return ErrorUtils.createErrorType(ErrorTypeKind.INTERSECTION_OF_ERROR_TYPES, new String[]{list0.toString()});
                }
                if(!z1) {
                    return TypeIntersector.INSTANCE.intersectTypes$descriptors(arrayList0);
                }
                ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
                for(Object object1: list0) {
                    arrayList1.add(FlexibleTypesKt.upperIfFlexible(((UnwrappedType)object1)));
                }
                return KotlinTypeFactory.flexibleType(TypeIntersector.INSTANCE.intersectTypes$descriptors(arrayList0), TypeIntersector.INSTANCE.intersectTypes$descriptors(arrayList1));
            }
        }
    }
}

