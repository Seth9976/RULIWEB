package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;

public final class BuiltinMethodsWithSpecialGenericSignature extends SpecialGenericSignatures {
    public static final BuiltinMethodsWithSpecialGenericSignature INSTANCE;

    static {
        BuiltinMethodsWithSpecialGenericSignature.INSTANCE = new BuiltinMethodsWithSpecialGenericSignature();
    }

    private final boolean getHasErasedValueParametersInJava(CallableMemberDescriptor callableMemberDescriptor0) {
        return CollectionsKt.contains(SpecialGenericSignatures.Companion.getERASED_VALUE_PARAMETERS_SIGNATURES(), MethodSignatureMappingKt.computeJvmSignature(callableMemberDescriptor0));
    }

    @JvmStatic
    public static final FunctionDescriptor getOverriddenBuiltinFunctionWithErasedValueParametersInJava(FunctionDescriptor functionDescriptor0) {
        Intrinsics.checkNotNullParameter(functionDescriptor0, "functionDescriptor");
        Name name0 = functionDescriptor0.getName();
        Intrinsics.checkNotNullExpressionValue(name0, "functionDescriptor.name");
        return BuiltinMethodsWithSpecialGenericSignature.INSTANCE.getSameAsBuiltinMethodWithErasedValueParameters(name0) ? ((FunctionDescriptor)DescriptorUtilsKt.firstOverridden$default(functionDescriptor0, false, kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava.1.INSTANCE, 1, null)) : null;

        final class kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava.1();
            }

            kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava.1() {
                super(1);
            }

            public final Boolean invoke(CallableMemberDescriptor callableMemberDescriptor0) {
                Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "it");
                return Boolean.valueOf(BuiltinMethodsWithSpecialGenericSignature.INSTANCE.getHasErasedValueParametersInJava(callableMemberDescriptor0));
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((CallableMemberDescriptor)object0));
            }
        }

    }

    public final boolean getSameAsBuiltinMethodWithErasedValueParameters(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "<this>");
        return SpecialGenericSignatures.Companion.getERASED_VALUE_PARAMETERS_SHORT_NAMES().contains(name0);
    }

    @JvmStatic
    public static final SpecialSignatureInfo getSpecialSignatureInfo(CallableMemberDescriptor callableMemberDescriptor0) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "<this>");
        if(!SpecialGenericSignatures.Companion.getERASED_VALUE_PARAMETERS_SHORT_NAMES().contains(callableMemberDescriptor0.getName())) {
            return null;
        }
        CallableMemberDescriptor callableMemberDescriptor1 = DescriptorUtilsKt.firstOverridden$default(callableMemberDescriptor0, false, kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature.getSpecialSignatureInfo.builtinSignature.1.INSTANCE, 1, null);
        if(callableMemberDescriptor1 != null) {
            String s = MethodSignatureMappingKt.computeJvmSignature(callableMemberDescriptor1);
            return s == null ? null : SpecialGenericSignatures.Companion.getSpecialSignatureInfo(s);
        }
        return null;

        final class kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature.getSpecialSignatureInfo.builtinSignature.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature.getSpecialSignatureInfo.builtinSignature.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature.getSpecialSignatureInfo.builtinSignature.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature.getSpecialSignatureInfo.builtinSignature.1();
            }

            kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature.getSpecialSignatureInfo.builtinSignature.1() {
                super(1);
            }

            public final Boolean invoke(CallableMemberDescriptor callableMemberDescriptor0) {
                Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "it");
                return !(callableMemberDescriptor0 instanceof FunctionDescriptor) || !BuiltinMethodsWithSpecialGenericSignature.INSTANCE.getHasErasedValueParametersInJava(callableMemberDescriptor0) ? false : true;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((CallableMemberDescriptor)object0));
            }
        }

    }
}

