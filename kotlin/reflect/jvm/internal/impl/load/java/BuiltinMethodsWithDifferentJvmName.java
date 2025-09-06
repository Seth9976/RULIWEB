package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;

public final class BuiltinMethodsWithDifferentJvmName extends SpecialGenericSignatures {
    public static final BuiltinMethodsWithDifferentJvmName INSTANCE;

    static {
        BuiltinMethodsWithDifferentJvmName.INSTANCE = new BuiltinMethodsWithDifferentJvmName();
    }

    public final Name getJvmName(SimpleFunctionDescriptor simpleFunctionDescriptor0) {
        Intrinsics.checkNotNullParameter(simpleFunctionDescriptor0, "functionDescriptor");
        Map map0 = SpecialGenericSignatures.Companion.getSIGNATURE_TO_JVM_REPRESENTATION_NAME();
        String s = MethodSignatureMappingKt.computeJvmSignature(simpleFunctionDescriptor0);
        return s == null ? null : ((Name)map0.get(s));
    }

    public final boolean isBuiltinFunctionWithDifferentNameInJvm(SimpleFunctionDescriptor simpleFunctionDescriptor0) {
        Intrinsics.checkNotNullParameter(simpleFunctionDescriptor0, "functionDescriptor");
        return KotlinBuiltIns.isBuiltIn(simpleFunctionDescriptor0) && DescriptorUtilsKt.firstOverridden$default(simpleFunctionDescriptor0, false, new Function1() {
            final SimpleFunctionDescriptor $functionDescriptor;

            {
                this.$functionDescriptor = simpleFunctionDescriptor0;
                super(1);
            }

            public final Boolean invoke(CallableMemberDescriptor callableMemberDescriptor0) {
                Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "it");
                return Boolean.valueOf(SpecialGenericSignatures.Companion.getSIGNATURE_TO_JVM_REPRESENTATION_NAME().containsKey(MethodSignatureMappingKt.computeJvmSignature(this.$functionDescriptor)));
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((CallableMemberDescriptor)object0));
            }
        }, 1, null) != null;
    }

    public final boolean isRemoveAtByIndex(SimpleFunctionDescriptor simpleFunctionDescriptor0) {
        Intrinsics.checkNotNullParameter(simpleFunctionDescriptor0, "<this>");
        return Intrinsics.areEqual(simpleFunctionDescriptor0.getName().asString(), "removeAt") && Intrinsics.areEqual(MethodSignatureMappingKt.computeJvmSignature(simpleFunctionDescriptor0), SpecialGenericSignatures.Companion.getREMOVE_AT_NAME_AND_SIGNATURE().getSignature());
    }
}

