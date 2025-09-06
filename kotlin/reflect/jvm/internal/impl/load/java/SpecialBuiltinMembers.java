package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyAccessorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.checker.TypeCheckingProcedure;

public final class SpecialBuiltinMembers {
    public static final boolean doesOverrideBuiltinWithDifferentJvmName(CallableMemberDescriptor callableMemberDescriptor0) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "<this>");
        return SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName(callableMemberDescriptor0) != null;
    }

    public static final String getJvmMethodNameIfSpecial(CallableMemberDescriptor callableMemberDescriptor0) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "callableMemberDescriptor");
        CallableMemberDescriptor callableMemberDescriptor1 = SpecialBuiltinMembers.getOverriddenBuiltinThatAffectsJvmName(callableMemberDescriptor0);
        if(callableMemberDescriptor1 != null) {
            CallableMemberDescriptor callableMemberDescriptor2 = DescriptorUtilsKt.getPropertyIfAccessor(callableMemberDescriptor1);
            if(callableMemberDescriptor2 != null) {
                if(callableMemberDescriptor2 instanceof PropertyDescriptor) {
                    return ClassicBuiltinSpecialProperties.INSTANCE.getBuiltinSpecialPropertyGetterName(callableMemberDescriptor2);
                }
                if(callableMemberDescriptor2 instanceof SimpleFunctionDescriptor) {
                    Name name0 = BuiltinMethodsWithDifferentJvmName.INSTANCE.getJvmName(((SimpleFunctionDescriptor)callableMemberDescriptor2));
                    return name0 == null ? null : name0.asString();
                }
            }
        }
        return null;
    }

    // 去混淆评级： 低(20)
    private static final CallableMemberDescriptor getOverriddenBuiltinThatAffectsJvmName(CallableMemberDescriptor callableMemberDescriptor0) {
        return KotlinBuiltIns.isBuiltIn(callableMemberDescriptor0) ? SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName(callableMemberDescriptor0) : null;
    }

    public static final CallableMemberDescriptor getOverriddenBuiltinWithDifferentJvmName(CallableMemberDescriptor callableMemberDescriptor0) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "<this>");
        Name name0 = callableMemberDescriptor0.getName();
        if(!SpecialGenericSignatures.Companion.getORIGINAL_SHORT_NAMES().contains(name0)) {
            Name name1 = DescriptorUtilsKt.getPropertyIfAccessor(callableMemberDescriptor0).getName();
            if(!BuiltinSpecialProperties.INSTANCE.getSPECIAL_SHORT_NAMES().contains(name1)) {
                return null;
            }
        }
        if((callableMemberDescriptor0 instanceof PropertyDescriptor ? true : callableMemberDescriptor0 instanceof PropertyAccessorDescriptor)) {
            return DescriptorUtilsKt.firstOverridden$default(callableMemberDescriptor0, false, kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName.1.INSTANCE, 1, null);
        }
        return callableMemberDescriptor0 instanceof SimpleFunctionDescriptor ? DescriptorUtilsKt.firstOverridden$default(callableMemberDescriptor0, false, kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName.2.INSTANCE, 1, null) : null;

        final class kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName.1();
            }

            kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName.1() {
                super(1);
            }

            public final Boolean invoke(CallableMemberDescriptor callableMemberDescriptor0) {
                Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "it");
                CallableMemberDescriptor callableMemberDescriptor1 = DescriptorUtilsKt.getPropertyIfAccessor(callableMemberDescriptor0);
                return Boolean.valueOf(ClassicBuiltinSpecialProperties.INSTANCE.hasBuiltinSpecialPropertyFqName(callableMemberDescriptor1));
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((CallableMemberDescriptor)object0));
            }
        }


        final class kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName.2 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName.2 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName.2.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName.2();
            }

            kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName.2() {
                super(1);
            }

            public final Boolean invoke(CallableMemberDescriptor callableMemberDescriptor0) {
                Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "it");
                return Boolean.valueOf(BuiltinMethodsWithDifferentJvmName.INSTANCE.isBuiltinFunctionWithDifferentNameInJvm(((SimpleFunctionDescriptor)callableMemberDescriptor0)));
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((CallableMemberDescriptor)object0));
            }
        }

    }

    public static final CallableMemberDescriptor getOverriddenSpecialBuiltin(CallableMemberDescriptor callableMemberDescriptor0) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "<this>");
        CallableMemberDescriptor callableMemberDescriptor1 = SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName(callableMemberDescriptor0);
        if(callableMemberDescriptor1 != null) {
            return callableMemberDescriptor1;
        }
        Name name0 = callableMemberDescriptor0.getName();
        Intrinsics.checkNotNullExpressionValue(name0, "name");
        return BuiltinMethodsWithSpecialGenericSignature.INSTANCE.getSameAsBuiltinMethodWithErasedValueParameters(name0) ? DescriptorUtilsKt.firstOverridden$default(callableMemberDescriptor0, false, kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenSpecialBuiltin.2.INSTANCE, 1, null) : null;

        final class kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenSpecialBuiltin.2 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenSpecialBuiltin.2 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenSpecialBuiltin.2.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenSpecialBuiltin.2();
            }

            kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers.getOverriddenSpecialBuiltin.2() {
                super(1);
            }

            public final Boolean invoke(CallableMemberDescriptor callableMemberDescriptor0) {
                Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "it");
                return !KotlinBuiltIns.isBuiltIn(callableMemberDescriptor0) || BuiltinMethodsWithSpecialGenericSignature.getSpecialSignatureInfo(callableMemberDescriptor0) == null ? false : true;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((CallableMemberDescriptor)object0));
            }
        }

    }

    public static final boolean hasRealKotlinSuperClassWithOverrideOf(ClassDescriptor classDescriptor0, CallableDescriptor callableDescriptor0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "<this>");
        Intrinsics.checkNotNullParameter(callableDescriptor0, "specialCallableDescriptor");
        DeclarationDescriptor declarationDescriptor0 = callableDescriptor0.getContainingDeclaration();
        Intrinsics.checkNotNull(declarationDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        SimpleType simpleType0 = ((ClassDescriptor)declarationDescriptor0).getDefaultType();
        Intrinsics.checkNotNullExpressionValue(simpleType0, "specialCallableDescripto…ssDescriptor).defaultType");
        for(ClassDescriptor classDescriptor1 = DescriptorUtils.getSuperClassDescriptor(classDescriptor0); classDescriptor1 != null; classDescriptor1 = DescriptorUtils.getSuperClassDescriptor(classDescriptor1)) {
            if(!(classDescriptor1 instanceof JavaClassDescriptor) && TypeCheckingProcedure.findCorrespondingSupertype(classDescriptor1.getDefaultType(), simpleType0) != null) {
                return !KotlinBuiltIns.isBuiltIn(classDescriptor1);
            }
        }
        return false;
    }

    public static final boolean isFromJava(CallableMemberDescriptor callableMemberDescriptor0) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "<this>");
        return DescriptorUtilsKt.getPropertyIfAccessor(callableMemberDescriptor0).getContainingDeclaration() instanceof JavaClassDescriptor;
    }

    public static final boolean isFromJavaOrBuiltins(CallableMemberDescriptor callableMemberDescriptor0) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "<this>");
        return SpecialBuiltinMembers.isFromJava(callableMemberDescriptor0) || KotlinBuiltIns.isBuiltIn(callableMemberDescriptor0);
    }
}

