package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature;
import kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmPrimitiveType;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public final class MethodSignatureMappingKt {
    private static final void appendErasedType(StringBuilder stringBuilder0, KotlinType kotlinType0) {
        stringBuilder0.append(MethodSignatureMappingKt.mapToJvmType(kotlinType0));
    }

    public static final String computeJvmDescriptor(FunctionDescriptor functionDescriptor0, boolean z, boolean z1) {
        String s;
        Intrinsics.checkNotNullParameter(functionDescriptor0, "<this>");
        StringBuilder stringBuilder0 = new StringBuilder();
        if(z1) {
            if(functionDescriptor0 instanceof ConstructorDescriptor) {
                s = "<init>";
            }
            else {
                s = functionDescriptor0.getName().asString();
                Intrinsics.checkNotNullExpressionValue(s, "name.asString()");
            }
            stringBuilder0.append(s);
        }
        stringBuilder0.append("(");
        ReceiverParameterDescriptor receiverParameterDescriptor0 = functionDescriptor0.getExtensionReceiverParameter();
        if(receiverParameterDescriptor0 != null) {
            KotlinType kotlinType0 = receiverParameterDescriptor0.getType();
            Intrinsics.checkNotNullExpressionValue(kotlinType0, "it.type");
            MethodSignatureMappingKt.appendErasedType(stringBuilder0, kotlinType0);
        }
        for(Object object0: functionDescriptor0.getValueParameters()) {
            KotlinType kotlinType1 = ((ValueParameterDescriptor)object0).getType();
            Intrinsics.checkNotNullExpressionValue(kotlinType1, "parameter.type");
            MethodSignatureMappingKt.appendErasedType(stringBuilder0, kotlinType1);
        }
        stringBuilder0.append(")");
        if(z) {
            if(DescriptorBasedTypeSignatureMappingKt.hasVoidReturnType(functionDescriptor0)) {
                stringBuilder0.append("V");
            }
            else {
                KotlinType kotlinType2 = functionDescriptor0.getReturnType();
                Intrinsics.checkNotNull(kotlinType2);
                MethodSignatureMappingKt.appendErasedType(stringBuilder0, kotlinType2);
            }
        }
        String s1 = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s1, "StringBuilder().apply(builderAction).toString()");
        return s1;
    }

    public static String computeJvmDescriptor$default(FunctionDescriptor functionDescriptor0, boolean z, boolean z1, int v, Object object0) {
        if((v & 1) != 0) {
            z = true;
        }
        if((v & 2) != 0) {
            z1 = true;
        }
        return MethodSignatureMappingKt.computeJvmDescriptor(functionDescriptor0, z, z1);
    }

    public static final String computeJvmSignature(CallableDescriptor callableDescriptor0) {
        Intrinsics.checkNotNullParameter(callableDescriptor0, "<this>");
        SignatureBuildingComponents signatureBuildingComponents0 = SignatureBuildingComponents.INSTANCE;
        if(DescriptorUtils.isLocal(callableDescriptor0)) {
            return null;
        }
        DeclarationDescriptor declarationDescriptor0 = callableDescriptor0.getContainingDeclaration();
        ClassDescriptor classDescriptor0 = declarationDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)declarationDescriptor0) : null;
        if(classDescriptor0 == null) {
            return null;
        }
        if(classDescriptor0.getName().isSpecial()) {
            return null;
        }
        CallableDescriptor callableDescriptor1 = callableDescriptor0.getOriginal();
        SimpleFunctionDescriptor simpleFunctionDescriptor0 = callableDescriptor1 instanceof SimpleFunctionDescriptor ? ((SimpleFunctionDescriptor)callableDescriptor1) : null;
        return simpleFunctionDescriptor0 == null ? null : MethodSignatureBuildingUtilsKt.signature(signatureBuildingComponents0, classDescriptor0, MethodSignatureMappingKt.computeJvmDescriptor$default(simpleFunctionDescriptor0, false, false, 3, null));
    }

    public static final boolean forceSingleValueParameterBoxing(CallableDescriptor callableDescriptor0) {
        Intrinsics.checkNotNullParameter(callableDescriptor0, "f");
        if(!(callableDescriptor0 instanceof FunctionDescriptor)) {
            return false;
        }
        if(Intrinsics.areEqual(((FunctionDescriptor)callableDescriptor0).getName().asString(), "remove") && ((FunctionDescriptor)callableDescriptor0).getValueParameters().size() == 1 && !SpecialBuiltinMembers.isFromJavaOrBuiltins(((CallableMemberDescriptor)callableDescriptor0))) {
            List list0 = ((FunctionDescriptor)callableDescriptor0).getOriginal().getValueParameters();
            Intrinsics.checkNotNullExpressionValue(list0, "f.original.valueParameters");
            KotlinType kotlinType0 = ((ValueParameterDescriptor)CollectionsKt.single(list0)).getType();
            Intrinsics.checkNotNullExpressionValue(kotlinType0, "f.original.valueParameters.single().type");
            JvmType jvmType0 = MethodSignatureMappingKt.mapToJvmType(kotlinType0);
            JvmPrimitiveType jvmPrimitiveType0 = null;
            Primitive jvmType$Primitive0 = jvmType0 instanceof Primitive ? ((Primitive)jvmType0) : null;
            if(jvmType$Primitive0 != null) {
                jvmPrimitiveType0 = jvmType$Primitive0.getJvmPrimitiveType();
            }
            if(jvmPrimitiveType0 != JvmPrimitiveType.INT) {
                return false;
            }
            FunctionDescriptor functionDescriptor0 = BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava(((FunctionDescriptor)callableDescriptor0));
            if(functionDescriptor0 == null) {
                return false;
            }
            List list1 = functionDescriptor0.getOriginal().getValueParameters();
            Intrinsics.checkNotNullExpressionValue(list1, "overridden.original.valueParameters");
            KotlinType kotlinType1 = ((ValueParameterDescriptor)CollectionsKt.single(list1)).getType();
            Intrinsics.checkNotNullExpressionValue(kotlinType1, "overridden.original.valueParameters.single().type");
            JvmType jvmType1 = MethodSignatureMappingKt.mapToJvmType(kotlinType1);
            DeclarationDescriptor declarationDescriptor0 = functionDescriptor0.getContainingDeclaration();
            Intrinsics.checkNotNullExpressionValue(declarationDescriptor0, "overridden.containingDeclaration");
            return Intrinsics.areEqual(DescriptorUtilsKt.getFqNameUnsafe(declarationDescriptor0), FqNames.mutableCollection.toUnsafe()) && jvmType1 instanceof kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Object && Intrinsics.areEqual(((kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Object)jvmType1).getInternalName(), "java/lang/Object");
        }
        return false;
    }

    public static final String getInternalName(ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "<this>");
        FqNameUnsafe fqNameUnsafe0 = DescriptorUtilsKt.getFqNameSafe(classDescriptor0).toUnsafe();
        Intrinsics.checkNotNullExpressionValue(fqNameUnsafe0, "fqNameSafe.toUnsafe()");
        ClassId classId0 = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(fqNameUnsafe0);
        if(classId0 != null) {
            String s = JvmClassName.byClassId(classId0).getInternalName();
            Intrinsics.checkNotNullExpressionValue(s, "byClassId(it).internalName");
            return s;
        }
        return DescriptorBasedTypeSignatureMappingKt.computeInternalName$default(classDescriptor0, null, 2, null);
    }

    public static final JvmType mapToJvmType(KotlinType kotlinType0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "<this>");
        return (JvmType)DescriptorBasedTypeSignatureMappingKt.mapType$default(kotlinType0, JvmTypeFactoryImpl.INSTANCE, TypeMappingMode.DEFAULT, TypeMappingConfigurationImpl.INSTANCE, null, null, 0x20, null);
    }
}

