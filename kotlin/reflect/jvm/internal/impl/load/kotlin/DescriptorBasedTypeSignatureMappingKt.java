package kotlin.reflect.jvm.internal.impl.load.kotlin;

import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.FunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.SuspendFunctionTypesKt;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeAliasDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.InlineClassesUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.ExpandedTypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.IntersectionTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.SimpleClassicTypeSystemContext;
import kotlin.reflect.jvm.internal.impl.types.error.ErrorUtils;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;
import kotlin.reflect.jvm.internal.impl.utils.FunctionsKt;
import kotlin.text.StringsKt;

public final class DescriptorBasedTypeSignatureMappingKt {
    public static final String computeInternalName(ClassDescriptor classDescriptor0, TypeMappingConfiguration typeMappingConfiguration0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "klass");
        Intrinsics.checkNotNullParameter(typeMappingConfiguration0, "typeMappingConfiguration");
        String s = typeMappingConfiguration0.getPredefinedFullInternalNameForClass(classDescriptor0);
        if(s != null) {
            return s;
        }
        DeclarationDescriptor declarationDescriptor0 = classDescriptor0.getContainingDeclaration();
        Intrinsics.checkNotNullExpressionValue(declarationDescriptor0, "klass.containingDeclaration");
        String s1 = SpecialNames.safeIdentifier(classDescriptor0.getName()).getIdentifier();
        Intrinsics.checkNotNullExpressionValue(s1, "safeIdentifier(klass.name).identifier");
        if(declarationDescriptor0 instanceof PackageFragmentDescriptor) {
            FqName fqName0 = ((PackageFragmentDescriptor)declarationDescriptor0).getFqName();
            if(fqName0.isRoot()) {
                return s1;
            }
            String s2 = fqName0.asString();
            Intrinsics.checkNotNullExpressionValue(s2, "fqName.asString()");
            return StringsKt.replace$default(s2, '.', '/', false, 4, null) + '/' + s1;
        }
        ClassDescriptor classDescriptor1 = declarationDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)declarationDescriptor0) : null;
        if(classDescriptor1 == null) {
            throw new IllegalArgumentException("Unexpected container: " + declarationDescriptor0 + " for " + classDescriptor0);
        }
        String s3 = typeMappingConfiguration0.getPredefinedInternalNameForClass(classDescriptor1);
        if(s3 == null) {
            s3 = DescriptorBasedTypeSignatureMappingKt.computeInternalName(classDescriptor1, typeMappingConfiguration0);
        }
        return s3 + '$' + s1;
    }

    public static String computeInternalName$default(ClassDescriptor classDescriptor0, TypeMappingConfiguration typeMappingConfiguration0, int v, Object object0) {
        if((v & 2) != 0) {
            typeMappingConfiguration0 = TypeMappingConfigurationImpl.INSTANCE;
        }
        return DescriptorBasedTypeSignatureMappingKt.computeInternalName(classDescriptor0, typeMappingConfiguration0);
    }

    public static final boolean hasVoidReturnType(CallableDescriptor callableDescriptor0) {
        Intrinsics.checkNotNullParameter(callableDescriptor0, "descriptor");
        if(callableDescriptor0 instanceof ConstructorDescriptor) {
            return true;
        }
        KotlinType kotlinType0 = callableDescriptor0.getReturnType();
        Intrinsics.checkNotNull(kotlinType0);
        if(KotlinBuiltIns.isUnit(kotlinType0)) {
            KotlinType kotlinType1 = callableDescriptor0.getReturnType();
            Intrinsics.checkNotNull(kotlinType1);
            return !TypeUtils.isNullableType(kotlinType1) && !(callableDescriptor0 instanceof PropertyGetterDescriptor);
        }
        return false;
    }

    public static final Object mapType(KotlinType kotlinType0, JvmTypeFactory jvmTypeFactory0, TypeMappingMode typeMappingMode0, TypeMappingConfiguration typeMappingConfiguration0, JvmDescriptorTypeWriter jvmDescriptorTypeWriter0, Function3 function30) {
        Object object4;
        Object object3;
        Intrinsics.checkNotNullParameter(kotlinType0, "kotlinType");
        Intrinsics.checkNotNullParameter(jvmTypeFactory0, "factory");
        Intrinsics.checkNotNullParameter(typeMappingMode0, "mode");
        Intrinsics.checkNotNullParameter(typeMappingConfiguration0, "typeMappingConfiguration");
        Intrinsics.checkNotNullParameter(function30, "writeGenericType");
        KotlinType kotlinType1 = typeMappingConfiguration0.preprocessType(kotlinType0);
        if(kotlinType1 != null) {
            return DescriptorBasedTypeSignatureMappingKt.mapType(kotlinType1, jvmTypeFactory0, typeMappingMode0, typeMappingConfiguration0, jvmDescriptorTypeWriter0, function30);
        }
        if(FunctionTypesKt.isSuspendFunctionType(kotlinType0)) {
            return DescriptorBasedTypeSignatureMappingKt.mapType(SuspendFunctionTypesKt.transformSuspendFunctionToRuntimeFunctionType(kotlinType0), jvmTypeFactory0, typeMappingMode0, typeMappingConfiguration0, jvmDescriptorTypeWriter0, function30);
        }
        Object object0 = TypeSignatureMappingKt.mapBuiltInType(SimpleClassicTypeSystemContext.INSTANCE, kotlinType0, jvmTypeFactory0, typeMappingMode0);
        if(object0 != null) {
            Object object1 = TypeSignatureMappingKt.boxTypeIfNeeded(jvmTypeFactory0, object0, typeMappingMode0.getNeedPrimitiveBoxing());
            function30.invoke(kotlinType0, object1, typeMappingMode0);
            return object1;
        }
        TypeConstructor typeConstructor0 = kotlinType0.getConstructor();
        if(typeConstructor0 instanceof IntersectionTypeConstructor) {
            KotlinType kotlinType2 = ((IntersectionTypeConstructor)typeConstructor0).getAlternativeType();
            if(kotlinType2 == null) {
                kotlinType2 = typeMappingConfiguration0.commonSupertype(((IntersectionTypeConstructor)typeConstructor0).getSupertypes());
            }
            return DescriptorBasedTypeSignatureMappingKt.mapType(TypeUtilsKt.replaceArgumentsWithStarProjections(kotlinType2), jvmTypeFactory0, typeMappingMode0, typeMappingConfiguration0, jvmDescriptorTypeWriter0, function30);
        }
        ClassifierDescriptor classifierDescriptor0 = typeConstructor0.getDeclarationDescriptor();
        if(classifierDescriptor0 == null) {
            throw new UnsupportedOperationException("no descriptor for type constructor of " + kotlinType0);
        }
        if(ErrorUtils.isError(classifierDescriptor0)) {
            Object object2 = jvmTypeFactory0.createObjectType("error/NonExistentClass");
            typeMappingConfiguration0.processErrorType(kotlinType0, ((ClassDescriptor)classifierDescriptor0));
            if(jvmDescriptorTypeWriter0 != null) {
                jvmDescriptorTypeWriter0.writeClass(object2);
            }
            return object2;
        }
        if(classifierDescriptor0 instanceof ClassDescriptor && KotlinBuiltIns.isArray(kotlinType0)) {
            if(kotlinType0.getArguments().size() != 1) {
                throw new UnsupportedOperationException("arrays must have one type argument");
            }
            TypeProjection typeProjection0 = (TypeProjection)kotlinType0.getArguments().get(0);
            KotlinType kotlinType3 = typeProjection0.getType();
            Intrinsics.checkNotNullExpressionValue(kotlinType3, "memberProjection.type");
            if(typeProjection0.getProjectionKind() == Variance.IN_VARIANCE) {
                object3 = jvmTypeFactory0.createObjectType("java/lang/Object");
                if(jvmDescriptorTypeWriter0 != null) {
                    jvmDescriptorTypeWriter0.writeArrayType();
                    jvmDescriptorTypeWriter0.writeClass(object3);
                    return jvmTypeFactory0.createFromString("[" + jvmTypeFactory0.toString(object3));
                }
            }
            else {
                if(jvmDescriptorTypeWriter0 != null) {
                    jvmDescriptorTypeWriter0.writeArrayType();
                }
                Variance variance0 = typeProjection0.getProjectionKind();
                Intrinsics.checkNotNullExpressionValue(variance0, "memberProjection.projectionKind");
                object3 = DescriptorBasedTypeSignatureMappingKt.mapType(kotlinType3, jvmTypeFactory0, typeMappingMode0.toGenericArgumentMode(variance0, true), typeMappingConfiguration0, jvmDescriptorTypeWriter0, function30);
            }
            return jvmTypeFactory0.createFromString("[" + jvmTypeFactory0.toString(object3));
        }
        if(classifierDescriptor0 instanceof ClassDescriptor) {
            if(InlineClassesUtilsKt.isInlineClass(classifierDescriptor0) && !typeMappingMode0.getNeedInlineClassWrapping()) {
                KotlinTypeMarker kotlinTypeMarker0 = ExpandedTypeUtilsKt.computeExpandedTypeForInlineClass(SimpleClassicTypeSystemContext.INSTANCE, kotlinType0);
                if(((KotlinType)kotlinTypeMarker0) != null) {
                    return DescriptorBasedTypeSignatureMappingKt.mapType(((KotlinType)kotlinTypeMarker0), jvmTypeFactory0, typeMappingMode0.wrapInlineClassesMode(), typeMappingConfiguration0, jvmDescriptorTypeWriter0, function30);
                }
            }
            if(!typeMappingMode0.isForAnnotationParameter() || !KotlinBuiltIns.isKClass(((ClassDescriptor)classifierDescriptor0))) {
                ClassDescriptor classDescriptor0 = (ClassDescriptor)classifierDescriptor0;
                ClassDescriptor classDescriptor1 = classDescriptor0.getOriginal();
                Intrinsics.checkNotNullExpressionValue(classDescriptor1, "descriptor.original");
                Object object5 = typeMappingConfiguration0.getPredefinedTypeForClass(classDescriptor1);
                if(object5 == null) {
                    if(classDescriptor0.getKind() == ClassKind.ENUM_ENTRY) {
                        DeclarationDescriptor declarationDescriptor0 = classDescriptor0.getContainingDeclaration();
                        Intrinsics.checkNotNull(declarationDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                        classDescriptor0 = (ClassDescriptor)declarationDescriptor0;
                    }
                    ClassDescriptor classDescriptor2 = classDescriptor0.getOriginal();
                    Intrinsics.checkNotNullExpressionValue(classDescriptor2, "enumClassIfEnumEntry.original");
                    object4 = jvmTypeFactory0.createObjectType(DescriptorBasedTypeSignatureMappingKt.computeInternalName(classDescriptor2, typeMappingConfiguration0));
                }
                else {
                    object4 = object5;
                }
            }
            else {
                object4 = jvmTypeFactory0.getJavaLangClassType();
            }
            function30.invoke(kotlinType0, object4, typeMappingMode0);
            return object4;
        }
        if(classifierDescriptor0 instanceof TypeParameterDescriptor) {
            KotlinType kotlinType4 = TypeUtilsKt.getRepresentativeUpperBound(((TypeParameterDescriptor)classifierDescriptor0));
            if(kotlinType0.isMarkedNullable()) {
                kotlinType4 = TypeUtilsKt.makeNullable(kotlinType4);
            }
            Object object6 = DescriptorBasedTypeSignatureMappingKt.mapType(kotlinType4, jvmTypeFactory0, typeMappingMode0, typeMappingConfiguration0, null, FunctionsKt.getDO_NOTHING_3());
            if(jvmDescriptorTypeWriter0 != null) {
                Name name0 = classifierDescriptor0.getName();
                Intrinsics.checkNotNullExpressionValue(name0, "descriptor.getName()");
                jvmDescriptorTypeWriter0.writeTypeVariable(name0, object6);
            }
            return object6;
        }
        if(!(classifierDescriptor0 instanceof TypeAliasDescriptor) || !typeMappingMode0.getMapTypeAliases()) {
            throw new UnsupportedOperationException("Unknown type " + kotlinType0);
        }
        return DescriptorBasedTypeSignatureMappingKt.mapType(((TypeAliasDescriptor)classifierDescriptor0).getExpandedType(), jvmTypeFactory0, typeMappingMode0, typeMappingConfiguration0, jvmDescriptorTypeWriter0, function30);
    }

    public static Object mapType$default(KotlinType kotlinType0, JvmTypeFactory jvmTypeFactory0, TypeMappingMode typeMappingMode0, TypeMappingConfiguration typeMappingConfiguration0, JvmDescriptorTypeWriter jvmDescriptorTypeWriter0, Function3 function30, int v, Object object0) {
        if((v & 0x20) != 0) {
            function30 = FunctionsKt.getDO_NOTHING_3();
        }
        return DescriptorBasedTypeSignatureMappingKt.mapType(kotlinType0, jvmTypeFactory0, typeMappingMode0, typeMappingConfiguration0, jvmDescriptorTypeWriter0, function30);
    }
}

