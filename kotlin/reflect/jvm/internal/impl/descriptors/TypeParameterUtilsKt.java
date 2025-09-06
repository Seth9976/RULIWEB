package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

public final class TypeParameterUtilsKt {
    public static final PossiblyInnerType buildPossiblyInnerType(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        ClassifierDescriptor classifierDescriptor0 = kotlinType0.getConstructor().getDeclarationDescriptor();
        return classifierDescriptor0 instanceof ClassifierDescriptorWithTypeParameters ? TypeParameterUtilsKt.buildPossiblyInnerType(kotlinType0, ((ClassifierDescriptorWithTypeParameters)classifierDescriptor0), 0) : TypeParameterUtilsKt.buildPossiblyInnerType(kotlinType0, null, 0);
    }

    private static final PossiblyInnerType buildPossiblyInnerType(KotlinType kotlinType0, ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters0, int v) {
        ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters1 = null;
        if(classifierDescriptorWithTypeParameters0 != null && !ErrorUtils.isError(classifierDescriptorWithTypeParameters0)) {
            int v1 = classifierDescriptorWithTypeParameters0.getDeclaredTypeParameters().size() + v;
            if(!classifierDescriptorWithTypeParameters0.isInner()) {
                if(v1 != kotlinType0.getArguments().size()) {
                    DescriptorUtils.isLocal(classifierDescriptorWithTypeParameters0);
                }
                return new PossiblyInnerType(classifierDescriptorWithTypeParameters0, kotlinType0.getArguments().subList(v, kotlinType0.getArguments().size()), null);
            }
            List list0 = kotlinType0.getArguments().subList(v, v1);
            DeclarationDescriptor declarationDescriptor0 = classifierDescriptorWithTypeParameters0.getContainingDeclaration();
            if(declarationDescriptor0 instanceof ClassifierDescriptorWithTypeParameters) {
                classifierDescriptorWithTypeParameters1 = (ClassifierDescriptorWithTypeParameters)declarationDescriptor0;
            }
            return new PossiblyInnerType(classifierDescriptorWithTypeParameters0, list0, TypeParameterUtilsKt.buildPossiblyInnerType(kotlinType0, classifierDescriptorWithTypeParameters1, v1));
        }
        return null;
    }

    private static final CapturedTypeParameterDescriptor capturedCopyForInnerDeclaration(TypeParameterDescriptor typeParameterDescriptor0, DeclarationDescriptor declarationDescriptor0, int v) {
        return new CapturedTypeParameterDescriptor(typeParameterDescriptor0, declarationDescriptor0, v);
    }

    public static final List computeConstructorTypeParameters(ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters0) {
        List list2;
        Intrinsics.checkNotNullParameter(classifierDescriptorWithTypeParameters0, "<this>");
        List list0 = classifierDescriptorWithTypeParameters0.getDeclaredTypeParameters();
        Intrinsics.checkNotNullExpressionValue(list0, "declaredTypeParameters");
        if(!classifierDescriptorWithTypeParameters0.isInner() && !(classifierDescriptorWithTypeParameters0.getContainingDeclaration() instanceof CallableDescriptor)) {
            return list0;
        }
        Object object0 = null;
        List list1 = SequencesKt.toList(SequencesKt.flatMap(SequencesKt.filter(SequencesKt.takeWhile(DescriptorUtilsKt.getParents(classifierDescriptorWithTypeParameters0), kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt.computeConstructorTypeParameters.parametersFromContainingFunctions.1.INSTANCE), kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt.computeConstructorTypeParameters.parametersFromContainingFunctions.2.INSTANCE), kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt.computeConstructorTypeParameters.parametersFromContainingFunctions.3.INSTANCE));
        Iterator iterator0 = DescriptorUtilsKt.getParents(classifierDescriptorWithTypeParameters0).iterator();
        while(true) {
            list2 = null;
            if(!iterator0.hasNext()) {
                break;
            }
            Object object1 = iterator0.next();
            if(object1 instanceof ClassDescriptor) {
                object0 = object1;
                break;
            }
        }
        if(((ClassDescriptor)object0) != null) {
            TypeConstructor typeConstructor0 = ((ClassDescriptor)object0).getTypeConstructor();
            if(typeConstructor0 != null) {
                list2 = typeConstructor0.getParameters();
            }
        }
        if(list2 == null) {
            list2 = CollectionsKt.emptyList();
        }
        if(list1.isEmpty() && list2.isEmpty()) {
            List list3 = classifierDescriptorWithTypeParameters0.getDeclaredTypeParameters();
            Intrinsics.checkNotNullExpressionValue(list3, "declaredTypeParameters");
            return list3;
        }
        Iterable iterable0 = CollectionsKt.plus(list1, list2);
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object2: iterable0) {
            Intrinsics.checkNotNullExpressionValue(((TypeParameterDescriptor)object2), "it");
            arrayList0.add(TypeParameterUtilsKt.capturedCopyForInnerDeclaration(((TypeParameterDescriptor)object2), classifierDescriptorWithTypeParameters0, list0.size()));
        }
        return CollectionsKt.plus(list0, arrayList0);

        final class kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt.computeConstructorTypeParameters.parametersFromContainingFunctions.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt.computeConstructorTypeParameters.parametersFromContainingFunctions.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt.computeConstructorTypeParameters.parametersFromContainingFunctions.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt.computeConstructorTypeParameters.parametersFromContainingFunctions.1();
            }

            kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt.computeConstructorTypeParameters.parametersFromContainingFunctions.1() {
                super(1);
            }

            public final Boolean invoke(DeclarationDescriptor declarationDescriptor0) {
                Intrinsics.checkNotNullParameter(declarationDescriptor0, "it");
                return Boolean.valueOf(declarationDescriptor0 instanceof CallableDescriptor);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((DeclarationDescriptor)object0));
            }
        }


        final class kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt.computeConstructorTypeParameters.parametersFromContainingFunctions.2 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt.computeConstructorTypeParameters.parametersFromContainingFunctions.2 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt.computeConstructorTypeParameters.parametersFromContainingFunctions.2.INSTANCE = new kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt.computeConstructorTypeParameters.parametersFromContainingFunctions.2();
            }

            kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt.computeConstructorTypeParameters.parametersFromContainingFunctions.2() {
                super(1);
            }

            public final Boolean invoke(DeclarationDescriptor declarationDescriptor0) {
                Intrinsics.checkNotNullParameter(declarationDescriptor0, "it");
                return Boolean.valueOf(!(declarationDescriptor0 instanceof ConstructorDescriptor));
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((DeclarationDescriptor)object0));
            }
        }


        final class kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt.computeConstructorTypeParameters.parametersFromContainingFunctions.3 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt.computeConstructorTypeParameters.parametersFromContainingFunctions.3 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt.computeConstructorTypeParameters.parametersFromContainingFunctions.3.INSTANCE = new kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt.computeConstructorTypeParameters.parametersFromContainingFunctions.3();
            }

            kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt.computeConstructorTypeParameters.parametersFromContainingFunctions.3() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((DeclarationDescriptor)object0));
            }

            public final Sequence invoke(DeclarationDescriptor declarationDescriptor0) {
                Intrinsics.checkNotNullParameter(declarationDescriptor0, "it");
                List list0 = ((CallableDescriptor)declarationDescriptor0).getTypeParameters();
                Intrinsics.checkNotNullExpressionValue(list0, "it as CallableDescriptor).typeParameters");
                return CollectionsKt.asSequence(list0);
            }
        }

    }
}

