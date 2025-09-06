package kotlin.reflect.jvm.internal.impl.types;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;

public final class StarProjectionImplKt {
    private static final KotlinType buildStarProjectionTypeByTypeParameters(List list0, List list1, KotlinBuiltIns kotlinBuiltIns0) {
        KotlinType kotlinType0 = TypeSubstitutor.create(new TypeConstructorSubstitution() {
            @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructorSubstitution
            public TypeProjection get(TypeConstructor typeConstructor0) {
                Intrinsics.checkNotNullParameter(typeConstructor0, "key");
                if(list0.contains(typeConstructor0)) {
                    ClassifierDescriptor classifierDescriptor0 = typeConstructor0.getDeclarationDescriptor();
                    Intrinsics.checkNotNull(classifierDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.TypeParameterDescriptor");
                    return TypeUtils.makeStarProjection(((TypeParameterDescriptor)classifierDescriptor0));
                }
                return null;
            }
        }).substitute(((KotlinType)CollectionsKt.first(list1)), Variance.OUT_VARIANCE);
        if(kotlinType0 == null) {
            kotlinType0 = kotlinBuiltIns0.getDefaultBound();
        }
        Intrinsics.checkNotNullExpressionValue(kotlinType0, "typeParameters: List<Typ… ?: builtIns.defaultBound");
        return kotlinType0;
    }

    public static final KotlinType starProjectionType(TypeParameterDescriptor typeParameterDescriptor0) {
        Intrinsics.checkNotNullParameter(typeParameterDescriptor0, "<this>");
        DeclarationDescriptor declarationDescriptor0 = typeParameterDescriptor0.getContainingDeclaration();
        Intrinsics.checkNotNullExpressionValue(declarationDescriptor0, "this.containingDeclaration");
        if(declarationDescriptor0 instanceof ClassifierDescriptorWithTypeParameters) {
            List list0 = ((ClassifierDescriptorWithTypeParameters)declarationDescriptor0).getTypeConstructor().getParameters();
            Intrinsics.checkNotNullExpressionValue(list0, "descriptor.typeConstructor.parameters");
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
            for(Object object0: list0) {
                TypeConstructor typeConstructor0 = ((TypeParameterDescriptor)object0).getTypeConstructor();
                Intrinsics.checkNotNullExpressionValue(typeConstructor0, "it.typeConstructor");
                arrayList0.add(typeConstructor0);
            }
            List list1 = typeParameterDescriptor0.getUpperBounds();
            Intrinsics.checkNotNullExpressionValue(list1, "upperBounds");
            return StarProjectionImplKt.buildStarProjectionTypeByTypeParameters(arrayList0, list1, DescriptorUtilsKt.getBuiltIns(typeParameterDescriptor0));
        }
        if(!(declarationDescriptor0 instanceof FunctionDescriptor)) {
            throw new IllegalArgumentException("Unsupported descriptor type to build star projection type based on type parameters of it");
        }
        List list2 = ((FunctionDescriptor)declarationDescriptor0).getTypeParameters();
        Intrinsics.checkNotNullExpressionValue(list2, "descriptor.typeParameters");
        ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        for(Object object1: list2) {
            TypeConstructor typeConstructor1 = ((TypeParameterDescriptor)object1).getTypeConstructor();
            Intrinsics.checkNotNullExpressionValue(typeConstructor1, "it.typeConstructor");
            arrayList1.add(typeConstructor1);
        }
        List list3 = typeParameterDescriptor0.getUpperBounds();
        Intrinsics.checkNotNullExpressionValue(list3, "upperBounds");
        return StarProjectionImplKt.buildStarProjectionTypeByTypeParameters(arrayList1, list3, DescriptorUtilsKt.getBuiltIns(typeParameterDescriptor0));
    }
}

