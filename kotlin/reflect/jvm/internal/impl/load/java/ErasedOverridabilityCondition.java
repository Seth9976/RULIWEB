package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawSubstitution;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.RawTypeImpl;
import kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition.Contract;
import kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.OverrideCompatibilityInfo.Result;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.OverrideCompatibilityInfo;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

public final class ErasedOverridabilityCondition implements ExternalOverridabilityCondition {
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[Result.values().length];
            try {
                arr_v[Result.OVERRIDABLE.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition
    public Contract getContract() {
        return Contract.SUCCESS_ONLY;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition
    public kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition.Result isOverridable(CallableDescriptor callableDescriptor0, CallableDescriptor callableDescriptor1, ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(callableDescriptor0, "superDescriptor");
        Intrinsics.checkNotNullParameter(callableDescriptor1, "subDescriptor");
        if(callableDescriptor1 instanceof JavaMethodDescriptor) {
            List list0 = ((JavaMethodDescriptor)callableDescriptor1).getTypeParameters();
            Intrinsics.checkNotNullExpressionValue(list0, "subDescriptor.typeParameters");
            if(list0.isEmpty()) {
                OverrideCompatibilityInfo overridingUtil$OverrideCompatibilityInfo0 = OverridingUtil.getBasicOverridabilityProblem(callableDescriptor0, callableDescriptor1);
                if((overridingUtil$OverrideCompatibilityInfo0 == null ? null : overridingUtil$OverrideCompatibilityInfo0.getResult()) != null) {
                    return kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition.Result.UNKNOWN;
                }
                List list1 = ((JavaMethodDescriptor)callableDescriptor1).getValueParameters();
                Intrinsics.checkNotNullExpressionValue(list1, "subDescriptor.valueParameters");
                Sequence sequence0 = SequencesKt.map(CollectionsKt.asSequence(list1), kotlin.reflect.jvm.internal.impl.load.java.ErasedOverridabilityCondition.isOverridable.signatureTypes.1.INSTANCE);
                KotlinType kotlinType0 = ((JavaMethodDescriptor)callableDescriptor1).getReturnType();
                Intrinsics.checkNotNull(kotlinType0);
                Sequence sequence1 = SequencesKt.plus(sequence0, kotlinType0);
                ReceiverParameterDescriptor receiverParameterDescriptor0 = ((JavaMethodDescriptor)callableDescriptor1).getExtensionReceiverParameter();
                for(Object object0: SequencesKt.plus(sequence1, CollectionsKt.listOfNotNull((receiverParameterDescriptor0 == null ? null : receiverParameterDescriptor0.getType())))) {
                    if(!((KotlinType)object0).getArguments().isEmpty() && !(((KotlinType)object0).unwrap() instanceof RawTypeImpl)) {
                        return kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition.Result.UNKNOWN;
                    }
                    if(false) {
                        break;
                    }
                }
                CallableDescriptor callableDescriptor2 = (CallableDescriptor)callableDescriptor0.substitute(new RawSubstitution(null, 1, null).buildSubstitutor());
                if(callableDescriptor2 == null) {
                    return kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition.Result.UNKNOWN;
                }
                if(callableDescriptor2 instanceof SimpleFunctionDescriptor) {
                    List list2 = ((SimpleFunctionDescriptor)callableDescriptor2).getTypeParameters();
                    Intrinsics.checkNotNullExpressionValue(list2, "erasedSuper.typeParameters");
                    if(!list2.isEmpty()) {
                        FunctionDescriptor functionDescriptor0 = ((SimpleFunctionDescriptor)callableDescriptor2).newCopyBuilder().setTypeParameters(CollectionsKt.emptyList()).build();
                        Intrinsics.checkNotNull(functionDescriptor0);
                        callableDescriptor2 = functionDescriptor0;
                    }
                }
                Result overridingUtil$OverrideCompatibilityInfo$Result0 = OverridingUtil.DEFAULT.isOverridableByWithoutExternalConditions(callableDescriptor2, callableDescriptor1, false).getResult();
                Intrinsics.checkNotNullExpressionValue(overridingUtil$OverrideCompatibilityInfo$Result0, "DEFAULT.isOverridableByW…Descriptor, false).result");
                return WhenMappings.$EnumSwitchMapping$0[overridingUtil$OverrideCompatibilityInfo$Result0.ordinal()] == 1 ? kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition.Result.OVERRIDABLE : kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition.Result.UNKNOWN;
            }
        }
        return kotlin.reflect.jvm.internal.impl.resolve.ExternalOverridabilityCondition.Result.UNKNOWN;

        final class kotlin.reflect.jvm.internal.impl.load.java.ErasedOverridabilityCondition.isOverridable.signatureTypes.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.load.java.ErasedOverridabilityCondition.isOverridable.signatureTypes.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.java.ErasedOverridabilityCondition.isOverridable.signatureTypes.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.java.ErasedOverridabilityCondition.isOverridable.signatureTypes.1();
            }

            kotlin.reflect.jvm.internal.impl.load.java.ErasedOverridabilityCondition.isOverridable.signatureTypes.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((ValueParameterDescriptor)object0));
            }

            public final KotlinType invoke(ValueParameterDescriptor valueParameterDescriptor0) {
                return valueParameterDescriptor0.getType();
            }
        }

    }
}

