package kotlin.reflect.jvm.internal.impl.types;

import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.FilteredAnnotations;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorType;

public final class TypeSubstitutionKt {
    public static final SimpleType asSimpleType(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        UnwrappedType unwrappedType0 = kotlinType0.unwrap();
        SimpleType simpleType0 = unwrappedType0 instanceof SimpleType ? ((SimpleType)unwrappedType0) : null;
        if(simpleType0 == null) {
            throw new IllegalStateException(("This is should be simple type: " + kotlinType0).toString());
        }
        return simpleType0;
    }

    public static final KotlinType replace(KotlinType kotlinType0, List list0, Annotations annotations0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        Intrinsics.checkNotNullParameter(list0, "newArguments");
        Intrinsics.checkNotNullParameter(annotations0, "newAnnotations");
        return TypeSubstitutionKt.replace$default(kotlinType0, list0, annotations0, null, 4, null);
    }

    public static final KotlinType replace(KotlinType kotlinType0, List list0, Annotations annotations0, List list1) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        Intrinsics.checkNotNullParameter(list0, "newArguments");
        Intrinsics.checkNotNullParameter(annotations0, "newAnnotations");
        Intrinsics.checkNotNullParameter(list1, "newArgumentsForUpperBound");
        if((list0.isEmpty() || list0 == kotlinType0.getArguments()) && annotations0 == kotlinType0.getAnnotations()) {
            return kotlinType0;
        }
        TypeAttributes typeAttributes0 = kotlinType0.getAttributes();
        if(annotations0 instanceof FilteredAnnotations && annotations0.isEmpty()) {
            annotations0 = Annotations.Companion.getEMPTY();
        }
        TypeAttributes typeAttributes1 = TypeAttributesKt.replaceAnnotations(typeAttributes0, annotations0);
        UnwrappedType unwrappedType0 = kotlinType0.unwrap();
        if(unwrappedType0 instanceof FlexibleType) {
            return KotlinTypeFactory.flexibleType(TypeSubstitutionKt.replace(((FlexibleType)unwrappedType0).getLowerBound(), list0, typeAttributes1), TypeSubstitutionKt.replace(((FlexibleType)unwrappedType0).getUpperBound(), list1, typeAttributes1));
        }
        if(!(unwrappedType0 instanceof SimpleType)) {
            throw new NoWhenBranchMatchedException();
        }
        return TypeSubstitutionKt.replace(((SimpleType)unwrappedType0), list0, typeAttributes1);
    }

    public static final SimpleType replace(SimpleType simpleType0, List list0, TypeAttributes typeAttributes0) {
        Intrinsics.checkNotNullParameter(simpleType0, "<this>");
        Intrinsics.checkNotNullParameter(list0, "newArguments");
        Intrinsics.checkNotNullParameter(typeAttributes0, "newAttributes");
        if(list0.isEmpty() && typeAttributes0 == simpleType0.getAttributes()) {
            return simpleType0;
        }
        if(list0.isEmpty()) {
            return simpleType0.replaceAttributes(typeAttributes0);
        }
        return simpleType0 instanceof ErrorType ? ((ErrorType)simpleType0).replaceArguments(list0) : KotlinTypeFactory.simpleType$default(typeAttributes0, simpleType0.getConstructor(), list0, simpleType0.isMarkedNullable(), null, 16, null);
    }

    public static KotlinType replace$default(KotlinType kotlinType0, List list0, Annotations annotations0, List list1, int v, Object object0) {
        if((v & 1) != 0) {
            list0 = kotlinType0.getArguments();
        }
        if((v & 2) != 0) {
            annotations0 = kotlinType0.getAnnotations();
        }
        if((v & 4) != 0) {
            list1 = list0;
        }
        return TypeSubstitutionKt.replace(kotlinType0, list0, annotations0, list1);
    }

    public static SimpleType replace$default(SimpleType simpleType0, List list0, TypeAttributes typeAttributes0, int v, Object object0) {
        if((v & 1) != 0) {
            list0 = simpleType0.getArguments();
        }
        if((v & 2) != 0) {
            typeAttributes0 = simpleType0.getAttributes();
        }
        return TypeSubstitutionKt.replace(simpleType0, list0, typeAttributes0);
    }
}

