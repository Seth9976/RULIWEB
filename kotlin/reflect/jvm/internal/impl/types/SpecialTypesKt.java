package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.types.checker.NewCapturedType;

public final class SpecialTypesKt {
    public static final AbbreviatedType getAbbreviatedType(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        UnwrappedType unwrappedType0 = kotlinType0.unwrap();
        return unwrappedType0 instanceof AbbreviatedType ? ((AbbreviatedType)unwrappedType0) : null;
    }

    public static final SimpleType getAbbreviation(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        AbbreviatedType abbreviatedType0 = SpecialTypesKt.getAbbreviatedType(kotlinType0);
        return abbreviatedType0 == null ? null : abbreviatedType0.getAbbreviation();
    }

    public static final boolean isDefinitelyNotNullType(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        return kotlinType0.unwrap() instanceof DefinitelyNotNullType;
    }

    private static final IntersectionTypeConstructor makeDefinitelyNotNullOrNotNull(IntersectionTypeConstructor intersectionTypeConstructor0) {
        KotlinType kotlinType0;
        Iterable iterable0 = intersectionTypeConstructor0.getSupertypes();
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        Iterator iterator0 = iterable0.iterator();
        boolean z = false;
        while(true) {
            kotlinType0 = null;
            if(!iterator0.hasNext()) {
                break;
            }
            Object object0 = iterator0.next();
            KotlinType kotlinType1 = (KotlinType)object0;
            if(TypeUtils.isNullableType(kotlinType1)) {
                kotlinType1 = SpecialTypesKt.makeDefinitelyNotNullOrNotNull$default(kotlinType1.unwrap(), false, 1, null);
                z = true;
            }
            arrayList0.add(kotlinType1);
        }
        if(!z) {
            return null;
        }
        KotlinType kotlinType2 = intersectionTypeConstructor0.getAlternativeType();
        if(kotlinType2 != null) {
            if(TypeUtils.isNullableType(kotlinType2)) {
                kotlinType2 = SpecialTypesKt.makeDefinitelyNotNullOrNotNull$default(kotlinType2.unwrap(), false, 1, null);
            }
            kotlinType0 = kotlinType2;
        }
        return new IntersectionTypeConstructor(arrayList0).setAlternative(kotlinType0);
    }

    public static final UnwrappedType makeDefinitelyNotNullOrNotNull(UnwrappedType unwrappedType0, boolean z) {
        Intrinsics.checkNotNullParameter(unwrappedType0, "<this>");
        DefinitelyNotNullType definitelyNotNullType0 = Companion.makeDefinitelyNotNull$default(DefinitelyNotNullType.Companion, unwrappedType0, z, false, 4, null);
        if(definitelyNotNullType0 != null) {
            return definitelyNotNullType0;
        }
        SimpleType simpleType0 = SpecialTypesKt.makeIntersectionTypeDefinitelyNotNullOrNotNull(unwrappedType0);
        return simpleType0 != null ? simpleType0 : unwrappedType0.makeNullableAsSpecified(false);
    }

    public static UnwrappedType makeDefinitelyNotNullOrNotNull$default(UnwrappedType unwrappedType0, boolean z, int v, Object object0) {
        if((v & 1) != 0) {
            z = false;
        }
        return SpecialTypesKt.makeDefinitelyNotNullOrNotNull(unwrappedType0, z);
    }

    private static final SimpleType makeIntersectionTypeDefinitelyNotNullOrNotNull(KotlinType kotlinType0) {
        TypeConstructor typeConstructor0 = kotlinType0.getConstructor();
        IntersectionTypeConstructor intersectionTypeConstructor0 = typeConstructor0 instanceof IntersectionTypeConstructor ? ((IntersectionTypeConstructor)typeConstructor0) : null;
        if(intersectionTypeConstructor0 == null) {
            return null;
        }
        IntersectionTypeConstructor intersectionTypeConstructor1 = SpecialTypesKt.makeDefinitelyNotNullOrNotNull(intersectionTypeConstructor0);
        return intersectionTypeConstructor1 == null ? null : intersectionTypeConstructor1.createType();
    }

    public static final SimpleType makeSimpleTypeDefinitelyNotNullOrNotNull(SimpleType simpleType0, boolean z) {
        Intrinsics.checkNotNullParameter(simpleType0, "<this>");
        DefinitelyNotNullType definitelyNotNullType0 = Companion.makeDefinitelyNotNull$default(DefinitelyNotNullType.Companion, simpleType0, z, false, 4, null);
        if(definitelyNotNullType0 != null) {
            return definitelyNotNullType0;
        }
        SimpleType simpleType1 = SpecialTypesKt.makeIntersectionTypeDefinitelyNotNullOrNotNull(simpleType0);
        return simpleType1 == null ? simpleType0.makeNullableAsSpecified(false) : simpleType1;
    }

    public static SimpleType makeSimpleTypeDefinitelyNotNullOrNotNull$default(SimpleType simpleType0, boolean z, int v, Object object0) {
        if((v & 1) != 0) {
            z = false;
        }
        return SpecialTypesKt.makeSimpleTypeDefinitelyNotNullOrNotNull(simpleType0, z);
    }

    public static final SimpleType withAbbreviation(SimpleType simpleType0, SimpleType simpleType1) {
        Intrinsics.checkNotNullParameter(simpleType0, "<this>");
        Intrinsics.checkNotNullParameter(simpleType1, "abbreviatedType");
        return KotlinTypeKt.isError(simpleType0) ? simpleType0 : new AbbreviatedType(simpleType0, simpleType1);
    }

    public static final NewCapturedType withNotNullProjection(NewCapturedType newCapturedType0) {
        Intrinsics.checkNotNullParameter(newCapturedType0, "<this>");
        return new NewCapturedType(newCapturedType0.getCaptureStatus(), newCapturedType0.getConstructor(), newCapturedType0.getLowerType(), newCapturedType0.getAttributes(), newCapturedType0.isMarkedNullable(), true);
    }
}

