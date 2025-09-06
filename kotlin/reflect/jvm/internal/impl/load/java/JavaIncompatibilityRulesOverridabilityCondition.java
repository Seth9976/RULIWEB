package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.List;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.Primitive;
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition.Contract;
import kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition.Result;
import kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public final class JavaIncompatibilityRulesOverridabilityCondition implements ExternalOverridabilityCondition {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final boolean doesJavaOverrideHaveIncompatibleValueParameterKinds(CallableDescriptor callableDescriptor0, CallableDescriptor callableDescriptor1) {
            Intrinsics.checkNotNullParameter(callableDescriptor0, "superDescriptor");
            Intrinsics.checkNotNullParameter(callableDescriptor1, "subDescriptor");
            if(callableDescriptor1 instanceof JavaMethodDescriptor && callableDescriptor0 instanceof FunctionDescriptor) {
                ((JavaMethodDescriptor)callableDescriptor1).getValueParameters().size();
                ((FunctionDescriptor)callableDescriptor0).getValueParameters().size();
                List list0 = ((JavaMethodDescriptor)callableDescriptor1).getOriginal().getValueParameters();
                Intrinsics.checkNotNullExpressionValue(list0, "subDescriptor.original.valueParameters");
                List list1 = ((FunctionDescriptor)callableDescriptor0).getOriginal().getValueParameters();
                Intrinsics.checkNotNullExpressionValue(list1, "superDescriptor.original.valueParameters");
                for(Object object0: CollectionsKt.zip(list0, list1)) {
                    ValueParameterDescriptor valueParameterDescriptor0 = (ValueParameterDescriptor)((Pair)object0).component1();
                    ValueParameterDescriptor valueParameterDescriptor1 = (ValueParameterDescriptor)((Pair)object0).component2();
                    Intrinsics.checkNotNullExpressionValue(valueParameterDescriptor0, "subParameter");
                    JvmType jvmType0 = this.mapValueParameterType(((FunctionDescriptor)callableDescriptor1), valueParameterDescriptor0);
                    Intrinsics.checkNotNullExpressionValue(valueParameterDescriptor1, "superParameter");
                    if(jvmType0 instanceof Primitive != this.mapValueParameterType(((FunctionDescriptor)callableDescriptor0), valueParameterDescriptor1) instanceof Primitive) {
                        return true;
                    }
                    if(false) {
                        break;
                    }
                }
            }
            return false;
        }

        private final boolean isPrimitiveCompareTo(FunctionDescriptor functionDescriptor0) {
            if(functionDescriptor0.getValueParameters().size() != 1) {
                return false;
            }
            DeclarationDescriptor declarationDescriptor0 = functionDescriptor0.getContainingDeclaration();
            ClassDescriptor classDescriptor0 = null;
            ClassDescriptor classDescriptor1 = declarationDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)declarationDescriptor0) : null;
            if(classDescriptor1 == null) {
                return false;
            }
            List list0 = functionDescriptor0.getValueParameters();
            Intrinsics.checkNotNullExpressionValue(list0, "f.valueParameters");
            ClassifierDescriptor classifierDescriptor0 = ((ValueParameterDescriptor)CollectionsKt.single(list0)).getType().getConstructor().getDeclarationDescriptor();
            if(classifierDescriptor0 instanceof ClassDescriptor) {
                classDescriptor0 = (ClassDescriptor)classifierDescriptor0;
            }
            return classDescriptor0 == null ? false : KotlinBuiltIns.isPrimitiveClass(classDescriptor1) && Intrinsics.areEqual(DescriptorUtilsKt.getFqNameSafe(classDescriptor1), DescriptorUtilsKt.getFqNameSafe(classDescriptor0));
        }

        private final JvmType mapValueParameterType(FunctionDescriptor functionDescriptor0, ValueParameterDescriptor valueParameterDescriptor0) {
            if(!MethodSignatureMappingKt.forceSingleValueParameterBoxing(functionDescriptor0) && !this.isPrimitiveCompareTo(functionDescriptor0)) {
                KotlinType kotlinType0 = valueParameterDescriptor0.getType();
                Intrinsics.checkNotNullExpressionValue(kotlinType0, "valueParameterDescriptor.type");
                return MethodSignatureMappingKt.mapToJvmType(kotlinType0);
            }
            KotlinType kotlinType1 = valueParameterDescriptor0.getType();
            Intrinsics.checkNotNullExpressionValue(kotlinType1, "valueParameterDescriptor.type");
            return MethodSignatureMappingKt.mapToJvmType(TypeUtilsKt.makeNullable(kotlinType1));
        }
    }

    public static final Companion Companion;

    static {
        JavaIncompatibilityRulesOverridabilityCondition.Companion = new Companion(null);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition
    public Contract getContract() {
        return Contract.CONFLICTS_ONLY;
    }

    private final boolean isIncompatibleInAccordanceWithBuiltInOverridabilityRules(CallableDescriptor callableDescriptor0, CallableDescriptor callableDescriptor1, ClassDescriptor classDescriptor0) {
        if(callableDescriptor0 instanceof CallableMemberDescriptor && callableDescriptor1 instanceof FunctionDescriptor && !KotlinBuiltIns.isBuiltIn(callableDescriptor1)) {
            Name name0 = ((FunctionDescriptor)callableDescriptor1).getName();
            Intrinsics.checkNotNullExpressionValue(name0, "subDescriptor.name");
            if(!BuiltinMethodsWithSpecialGenericSignature.INSTANCE.getSameAsBuiltinMethodWithErasedValueParameters(name0)) {
                Name name1 = ((FunctionDescriptor)callableDescriptor1).getName();
                Intrinsics.checkNotNullExpressionValue(name1, "subDescriptor.name");
                if(!SpecialGenericSignatures.Companion.getSameAsRenamedInJvmBuiltin(name1)) {
                    return false;
                }
            }
            CallableMemberDescriptor callableMemberDescriptor0 = SpecialBuiltinMembers.getOverriddenSpecialBuiltin(((CallableMemberDescriptor)callableDescriptor0));
            FunctionDescriptor functionDescriptor0 = callableDescriptor0 instanceof FunctionDescriptor ? ((FunctionDescriptor)callableDescriptor0) : null;
            if((functionDescriptor0 == null || ((FunctionDescriptor)callableDescriptor1).isHiddenToOvercomeSignatureClash() != functionDescriptor0.isHiddenToOvercomeSignatureClash()) && (callableMemberDescriptor0 == null || !((FunctionDescriptor)callableDescriptor1).isHiddenToOvercomeSignatureClash())) {
                return true;
            }
            if(classDescriptor0 instanceof JavaClassDescriptor && ((FunctionDescriptor)callableDescriptor1).getInitialSignatureDescriptor() == null && callableMemberDescriptor0 != null && !SpecialBuiltinMembers.hasRealKotlinSuperClassWithOverrideOf(classDescriptor0, callableMemberDescriptor0)) {
                if(callableMemberDescriptor0 instanceof FunctionDescriptor && callableDescriptor0 instanceof FunctionDescriptor && BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava(((FunctionDescriptor)callableMemberDescriptor0)) != null) {
                    String s = MethodSignatureMappingKt.computeJvmDescriptor$default(((FunctionDescriptor)callableDescriptor1), false, false, 2, null);
                    FunctionDescriptor functionDescriptor1 = ((FunctionDescriptor)callableDescriptor0).getOriginal();
                    Intrinsics.checkNotNullExpressionValue(functionDescriptor1, "superDescriptor.original");
                    return !Intrinsics.areEqual(s, MethodSignatureMappingKt.computeJvmDescriptor$default(functionDescriptor1, false, false, 2, null));
                }
                return true;
            }
        }
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition
    public Result isOverridable(CallableDescriptor callableDescriptor0, CallableDescriptor callableDescriptor1, ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(callableDescriptor0, "superDescriptor");
        Intrinsics.checkNotNullParameter(callableDescriptor1, "subDescriptor");
        if(this.isIncompatibleInAccordanceWithBuiltInOverridabilityRules(callableDescriptor0, callableDescriptor1, classDescriptor0)) {
            return Result.INCOMPATIBLE;
        }
        return JavaIncompatibilityRulesOverridabilityCondition.Companion.doesJavaOverrideHaveIncompatibleValueParameterKinds(callableDescriptor0, callableDescriptor1) ? Result.INCOMPATIBLE : Result.UNKNOWN;
    }
}

