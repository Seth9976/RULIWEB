package kotlin.reflect.jvm.internal.impl.builtins;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.functions.FunctionClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.BuiltInAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.IntValue;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributesKt;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public final class FunctionTypesKt {
    public static final int contextFunctionTypeParamsCount(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        AnnotationDescriptor annotationDescriptor0 = kotlinType0.getAnnotations().findAnnotation(FqNames.contextFunctionTypeParams);
        if(annotationDescriptor0 == null) {
            return 0;
        }
        ConstantValue constantValue0 = (ConstantValue)MapsKt.getValue(annotationDescriptor0.getAllValueArguments(), StandardNames.CONTEXT_FUNCTION_TYPE_PARAMETER_COUNT_NAME);
        Intrinsics.checkNotNull(constantValue0, "null cannot be cast to non-null type org.jetbrains.kotlin.resolve.constants.IntValue");
        return ((Number)((IntValue)constantValue0).getValue()).intValue();
    }

    public static final SimpleType createFunctionType(KotlinBuiltIns kotlinBuiltIns0, Annotations annotations0, KotlinType kotlinType0, List list0, List list1, List list2, KotlinType kotlinType1, boolean z) {
        Intrinsics.checkNotNullParameter(kotlinBuiltIns0, "builtIns");
        Intrinsics.checkNotNullParameter(annotations0, "annotations");
        Intrinsics.checkNotNullParameter(list0, "contextReceiverTypes");
        Intrinsics.checkNotNullParameter(list1, "parameterTypes");
        Intrinsics.checkNotNullParameter(kotlinType1, "returnType");
        List list3 = FunctionTypesKt.getFunctionTypeArgumentProjections(kotlinType0, list0, list1, list2, kotlinType1, kotlinBuiltIns0);
        ClassDescriptor classDescriptor0 = FunctionTypesKt.getFunctionDescriptor(kotlinBuiltIns0, list1.size() + list0.size() + (kotlinType0 == null ? 0 : 1), z);
        if(kotlinType0 != null) {
            annotations0 = FunctionTypesKt.withExtensionFunctionAnnotation(annotations0, kotlinBuiltIns0);
        }
        if(!list0.isEmpty()) {
            annotations0 = FunctionTypesKt.withContextReceiversFunctionAnnotation(annotations0, kotlinBuiltIns0, list0.size());
        }
        return KotlinTypeFactory.simpleNotNullType(TypeAttributesKt.toDefaultAttributes(annotations0), classDescriptor0, list3);
    }

    public static SimpleType createFunctionType$default(KotlinBuiltIns kotlinBuiltIns0, Annotations annotations0, KotlinType kotlinType0, List list0, List list1, List list2, KotlinType kotlinType1, boolean z, int v, Object object0) {
        return (v & 0x80) == 0 ? FunctionTypesKt.createFunctionType(kotlinBuiltIns0, annotations0, kotlinType0, list0, list1, list2, kotlinType1, z) : FunctionTypesKt.createFunctionType(kotlinBuiltIns0, annotations0, kotlinType0, list0, list1, list2, kotlinType1, false);
    }

    public static final Name extractParameterNameFromFunctionTypeArgument(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        AnnotationDescriptor annotationDescriptor0 = kotlinType0.getAnnotations().findAnnotation(FqNames.parameterName);
        if(annotationDescriptor0 == null) {
            return null;
        }
        Object object0 = CollectionsKt.singleOrNull(annotationDescriptor0.getAllValueArguments().values());
        StringValue stringValue0 = object0 instanceof StringValue ? ((StringValue)object0) : null;
        if(stringValue0 != null) {
            String s = (String)stringValue0.getValue();
            if(s != null) {
                if(!Name.isValidIdentifier(s)) {
                    s = null;
                }
                return s == null ? null : Name.identifier(s);
            }
        }
        return null;
    }

    public static final List getContextReceiverTypesFromFunctionType(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        FunctionTypesKt.isBuiltinFunctionalType(kotlinType0);
        int v = FunctionTypesKt.contextFunctionTypeParamsCount(kotlinType0);
        if(v == 0) {
            return CollectionsKt.emptyList();
        }
        Iterable iterable0 = kotlinType0.getArguments().subList(0, v);
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            KotlinType kotlinType1 = ((TypeProjection)object0).getType();
            Intrinsics.checkNotNullExpressionValue(kotlinType1, "it.type");
            arrayList0.add(kotlinType1);
        }
        return arrayList0;
    }

    public static final ClassDescriptor getFunctionDescriptor(KotlinBuiltIns kotlinBuiltIns0, int v, boolean z) {
        Intrinsics.checkNotNullParameter(kotlinBuiltIns0, "builtIns");
        ClassDescriptor classDescriptor0 = z ? kotlinBuiltIns0.getSuspendFunction(v) : kotlinBuiltIns0.getFunction(v);
        Intrinsics.checkNotNullExpressionValue(classDescriptor0, "if (isSuspendFunction) b…tFunction(parameterCount)");
        return classDescriptor0;
    }

    public static final List getFunctionTypeArgumentProjections(KotlinType kotlinType0, List list0, List list1, List list2, KotlinType kotlinType1, KotlinBuiltIns kotlinBuiltIns0) {
        Name name1;
        Intrinsics.checkNotNullParameter(list0, "contextReceiverTypes");
        Intrinsics.checkNotNullParameter(list1, "parameterTypes");
        Intrinsics.checkNotNullParameter(kotlinType1, "returnType");
        Intrinsics.checkNotNullParameter(kotlinBuiltIns0, "builtIns");
        int v = 0;
        ArrayList arrayList0 = new ArrayList(list1.size() + list0.size() + (kotlinType0 == null ? 0 : 1) + 1);
        ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            arrayList1.add(TypeUtilsKt.asTypeProjection(((KotlinType)object0)));
        }
        arrayList0.addAll(arrayList1);
        kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(arrayList0, (kotlinType0 == null ? null : TypeUtilsKt.asTypeProjection(kotlinType0)));
        for(Object object1: list1) {
            if(v < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            KotlinType kotlinType2 = (KotlinType)object1;
            if(list2 == null) {
                name1 = null;
            }
            else {
                Name name0 = (Name)list2.get(v);
                if(name0 != null && !name0.isSpecial()) {
                    name1 = name0;
                }
            }
            if(name1 != null) {
                Name name2 = Name.identifier("name");
                String s = name1.asString();
                Intrinsics.checkNotNullExpressionValue(s, "name.asString()");
                Map map0 = MapsKt.mapOf(TuplesKt.to(name2, new StringValue(s)));
                BuiltInAnnotationDescriptor builtInAnnotationDescriptor0 = new BuiltInAnnotationDescriptor(kotlinBuiltIns0, FqNames.parameterName, map0);
                List list3 = CollectionsKt.plus(kotlinType2.getAnnotations(), builtInAnnotationDescriptor0);
                kotlinType2 = TypeUtilsKt.replaceAnnotations(kotlinType2, Annotations.Companion.create(list3));
            }
            arrayList0.add(TypeUtilsKt.asTypeProjection(kotlinType2));
            ++v;
        }
        arrayList0.add(TypeUtilsKt.asTypeProjection(kotlinType1));
        return arrayList0;
    }

    public static final FunctionClassKind getFunctionalClassKind(DeclarationDescriptor declarationDescriptor0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "<this>");
        if(!(declarationDescriptor0 instanceof ClassDescriptor)) {
            return null;
        }
        return KotlinBuiltIns.isUnderKotlinPackage(declarationDescriptor0) ? FunctionTypesKt.getFunctionalClassKind(DescriptorUtilsKt.getFqNameUnsafe(declarationDescriptor0)) : null;
    }

    private static final FunctionClassKind getFunctionalClassKind(FqNameUnsafe fqNameUnsafe0) {
        if(fqNameUnsafe0.isSafe() && !fqNameUnsafe0.isRoot()) {
            String s = fqNameUnsafe0.shortName().asString();
            Intrinsics.checkNotNullExpressionValue(s, "shortName().asString()");
            FqName fqName0 = fqNameUnsafe0.toSafe().parent();
            Intrinsics.checkNotNullExpressionValue(fqName0, "toSafe().parent()");
            return FunctionClassKind.Companion.getFunctionalClassKind(s, fqName0);
        }
        return null;
    }

    public static final KotlinType getReceiverTypeFromFunctionType(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        FunctionTypesKt.isBuiltinFunctionalType(kotlinType0);
        if(!FunctionTypesKt.isTypeAnnotatedWithExtensionFunctionType(kotlinType0)) {
            return null;
        }
        int v = FunctionTypesKt.contextFunctionTypeParamsCount(kotlinType0);
        return ((TypeProjection)kotlinType0.getArguments().get(v)).getType();
    }

    public static final KotlinType getReturnTypeFromFunctionType(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        FunctionTypesKt.isBuiltinFunctionalType(kotlinType0);
        KotlinType kotlinType1 = ((TypeProjection)CollectionsKt.last(kotlinType0.getArguments())).getType();
        Intrinsics.checkNotNullExpressionValue(kotlinType1, "arguments.last().type");
        return kotlinType1;
    }

    public static final List getValueParameterTypesFromFunctionType(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        FunctionTypesKt.isBuiltinFunctionalType(kotlinType0);
        List list0 = kotlinType0.getArguments();
        return list0.subList(FunctionTypesKt.contextFunctionTypeParamsCount(kotlinType0) + FunctionTypesKt.isBuiltinExtensionFunctionalType(kotlinType0), list0.size() - 1);
    }

    public static final boolean isBuiltinExtensionFunctionalType(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        return FunctionTypesKt.isBuiltinFunctionalType(kotlinType0) && FunctionTypesKt.isTypeAnnotatedWithExtensionFunctionType(kotlinType0);
    }

    public static final boolean isBuiltinFunctionalClassDescriptor(DeclarationDescriptor declarationDescriptor0) {
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "<this>");
        FunctionClassKind functionClassKind0 = FunctionTypesKt.getFunctionalClassKind(declarationDescriptor0);
        return functionClassKind0 == FunctionClassKind.Function || functionClassKind0 == FunctionClassKind.SuspendFunction;
    }

    public static final boolean isBuiltinFunctionalType(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        ClassifierDescriptor classifierDescriptor0 = kotlinType0.getConstructor().getDeclarationDescriptor();
        return classifierDescriptor0 != null && FunctionTypesKt.isBuiltinFunctionalClassDescriptor(classifierDescriptor0);
    }

    public static final boolean isFunctionType(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        ClassifierDescriptor classifierDescriptor0 = kotlinType0.getConstructor().getDeclarationDescriptor();
        return (classifierDescriptor0 == null ? null : FunctionTypesKt.getFunctionalClassKind(classifierDescriptor0)) == FunctionClassKind.Function;
    }

    public static final boolean isSuspendFunctionType(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        ClassifierDescriptor classifierDescriptor0 = kotlinType0.getConstructor().getDeclarationDescriptor();
        return (classifierDescriptor0 == null ? null : FunctionTypesKt.getFunctionalClassKind(classifierDescriptor0)) == FunctionClassKind.SuspendFunction;
    }

    private static final boolean isTypeAnnotatedWithExtensionFunctionType(KotlinType kotlinType0) {
        return kotlinType0.getAnnotations().findAnnotation(FqNames.extensionFunctionType) != null;
    }

    public static final Annotations withContextReceiversFunctionAnnotation(Annotations annotations0, KotlinBuiltIns kotlinBuiltIns0, int v) {
        Intrinsics.checkNotNullParameter(annotations0, "<this>");
        Intrinsics.checkNotNullParameter(kotlinBuiltIns0, "builtIns");
        if(annotations0.hasAnnotation(FqNames.contextFunctionTypeParams)) {
            return annotations0;
        }
        IntValue intValue0 = new IntValue(v);
        Map map0 = MapsKt.mapOf(TuplesKt.to(StandardNames.CONTEXT_FUNCTION_TYPE_PARAMETER_COUNT_NAME, intValue0));
        List list0 = CollectionsKt.plus(annotations0, new BuiltInAnnotationDescriptor(kotlinBuiltIns0, FqNames.contextFunctionTypeParams, map0));
        return Annotations.Companion.create(list0);
    }

    public static final Annotations withExtensionFunctionAnnotation(Annotations annotations0, KotlinBuiltIns kotlinBuiltIns0) {
        Intrinsics.checkNotNullParameter(annotations0, "<this>");
        Intrinsics.checkNotNullParameter(kotlinBuiltIns0, "builtIns");
        if(annotations0.hasAnnotation(FqNames.extensionFunctionType)) {
            return annotations0;
        }
        Map map0 = MapsKt.emptyMap();
        List list0 = CollectionsKt.plus(annotations0, new BuiltInAnnotationDescriptor(kotlinBuiltIns0, FqNames.extensionFunctionType, map0));
        return Annotations.Companion.create(list0);
    }
}

