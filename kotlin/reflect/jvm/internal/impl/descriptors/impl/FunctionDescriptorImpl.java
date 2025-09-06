package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorNonRoot;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithSource;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationsKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ExtensionReceiver;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ImplicitContextReceiver;
import kotlin.reflect.jvm.internal.impl.types.DescriptorSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitution;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.utils.SmartList;

public abstract class FunctionDescriptorImpl extends DeclarationDescriptorNonRootImpl implements FunctionDescriptor {
    public class CopyConfiguration implements CopyBuilder {
        private Annotations additionalAnnotations;
        protected boolean copyOverrides;
        protected ReceiverParameterDescriptor dispatchReceiverParameter;
        protected boolean dropOriginalInContainingParts;
        private boolean isHiddenForResolutionEverywhereBesideSupercalls;
        private boolean isHiddenToOvercomeSignatureClash;
        protected boolean justForTypeSubstitution;
        protected Kind kind;
        protected Name name;
        protected List newContextReceiverParameters;
        protected ReceiverParameterDescriptor newExtensionReceiverParameter;
        private Boolean newHasSynthesizedParameterNames;
        protected Modality newModality;
        protected DeclarationDescriptor newOwner;
        protected KotlinType newReturnType;
        private List newTypeParameters;
        protected List newValueParameterDescriptors;
        protected DescriptorVisibility newVisibility;
        protected FunctionDescriptor original;
        protected boolean preserveSourceElement;
        protected boolean signatureChange;
        protected TypeSubstitution substitution;
        private Map userDataMap;

        private static void $$$reportNull$$$0(int v) {
            IllegalArgumentException illegalArgumentException0;
            int v1;
            String s;
            switch(v) {
                case 9: 
                case 11: 
                case 13: 
                case 15: 
                case 16: 
                case 18: 
                case 20: 
                case 22: 
                case 24: 
                case 26: 
                case 27: 
                case 28: 
                case 29: 
                case 30: 
                case 0x1F: 
                case 0x20: 
                case 33: 
                case 34: 
                case 36: 
                case 38: 
                case 40: 
                case 41: 
                case 42: {
                    s = "@NotNull method %s.%s must not return null";
                    break;
                }
                default: {
                    s = "Argument for @NotNull parameter \'%s\' of %s.%s must not be null";
                }
            }
            switch(v) {
                case 9: 
                case 11: 
                case 13: 
                case 15: 
                case 16: 
                case 18: 
                case 20: 
                case 22: 
                case 24: 
                case 26: 
                case 27: 
                case 28: 
                case 29: 
                case 30: 
                case 0x1F: 
                case 0x20: 
                case 33: 
                case 34: 
                case 36: 
                case 38: 
                case 40: 
                case 41: 
                case 42: {
                    v1 = 2;
                    break;
                }
                default: {
                    v1 = 3;
                }
            }
            Object[] arr_object = new Object[v1];
            switch(v) {
                case 1: {
                    arr_object[0] = "newOwner";
                    break;
                }
                case 2: {
                    arr_object[0] = "newModality";
                    break;
                }
                case 3: {
                    arr_object[0] = "newVisibility";
                    break;
                }
                case 5: {
                    arr_object[0] = "newValueParameterDescriptors";
                    break;
                }
                case 6: {
                    arr_object[0] = "newContextReceiverParameters";
                    break;
                }
                case 7: {
                    arr_object[0] = "newReturnType";
                    break;
                }
                case 8: {
                    arr_object[0] = "owner";
                    break;
                }
                case 10: {
                    arr_object[0] = "modality";
                    break;
                }
                case 12: {
                    arr_object[0] = "visibility";
                    break;
                }
                case 4: 
                case 14: {
                    arr_object[0] = "kind";
                    break;
                }
                case 17: {
                    arr_object[0] = "name";
                    break;
                }
                case 19: 
                case 21: {
                    arr_object[0] = "parameters";
                    break;
                }
                case 23: {
                    arr_object[0] = "type";
                    break;
                }
                case 25: {
                    arr_object[0] = "contextReceiverParameters";
                    break;
                }
                case 35: {
                    arr_object[0] = "additionalAnnotations";
                    break;
                }
                case 39: {
                    arr_object[0] = "userDataKey";
                    break;
                }
                case 9: 
                case 11: 
                case 13: 
                case 15: 
                case 16: 
                case 18: 
                case 20: 
                case 22: 
                case 24: 
                case 26: 
                case 27: 
                case 28: 
                case 29: 
                case 30: 
                case 0x1F: 
                case 0x20: 
                case 33: 
                case 34: 
                case 36: 
                case 38: 
                case 40: 
                case 41: 
                case 42: {
                    arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/FunctionDescriptorImpl$CopyConfiguration";
                    break;
                }
                default: {
                    arr_object[0] = "substitution";
                }
            }
            switch(v) {
                case 9: {
                    arr_object[1] = "setOwner";
                    break;
                }
                case 11: {
                    arr_object[1] = "setModality";
                    break;
                }
                case 13: {
                    arr_object[1] = "setVisibility";
                    break;
                }
                case 15: {
                    arr_object[1] = "setKind";
                    break;
                }
                case 16: {
                    arr_object[1] = "setCopyOverrides";
                    break;
                }
                case 18: {
                    arr_object[1] = "setName";
                    break;
                }
                case 20: {
                    arr_object[1] = "setValueParameters";
                    break;
                }
                case 22: {
                    arr_object[1] = "setTypeParameters";
                    break;
                }
                case 24: {
                    arr_object[1] = "setReturnType";
                    break;
                }
                case 26: {
                    arr_object[1] = "setContextReceiverParameters";
                    break;
                }
                case 27: {
                    arr_object[1] = "setExtensionReceiverParameter";
                    break;
                }
                case 28: {
                    arr_object[1] = "setDispatchReceiverParameter";
                    break;
                }
                case 29: {
                    arr_object[1] = "setOriginal";
                    break;
                }
                case 30: {
                    arr_object[1] = "setSignatureChange";
                    break;
                }
                case 0x1F: {
                    arr_object[1] = "setPreserveSourceElement";
                    break;
                }
                case 0x20: {
                    arr_object[1] = "setDropOriginalInContainingParts";
                    break;
                }
                case 33: {
                    arr_object[1] = "setHiddenToOvercomeSignatureClash";
                    break;
                }
                case 34: {
                    arr_object[1] = "setHiddenForResolutionEverywhereBesideSupercalls";
                    break;
                }
                case 36: {
                    arr_object[1] = "setAdditionalAnnotations";
                    break;
                }
                case 38: {
                    arr_object[1] = "setSubstitution";
                    break;
                }
                case 40: {
                    arr_object[1] = "putUserData";
                    break;
                }
                case 41: {
                    arr_object[1] = "getSubstitution";
                    break;
                }
                case 42: {
                    arr_object[1] = "setJustForTypeSubstitution";
                    break;
                }
                default: {
                    arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/FunctionDescriptorImpl$CopyConfiguration";
                }
            }
            switch(v) {
                case 8: {
                    arr_object[2] = "setOwner";
                    break;
                }
                case 10: {
                    arr_object[2] = "setModality";
                    break;
                }
                case 12: {
                    arr_object[2] = "setVisibility";
                    break;
                }
                case 14: {
                    arr_object[2] = "setKind";
                    break;
                }
                case 17: {
                    arr_object[2] = "setName";
                    break;
                }
                case 19: {
                    arr_object[2] = "setValueParameters";
                    break;
                }
                case 21: {
                    arr_object[2] = "setTypeParameters";
                    break;
                }
                case 23: {
                    arr_object[2] = "setReturnType";
                    break;
                }
                case 25: {
                    arr_object[2] = "setContextReceiverParameters";
                    break;
                }
                case 35: {
                    arr_object[2] = "setAdditionalAnnotations";
                    break;
                }
                case 37: {
                    arr_object[2] = "setSubstitution";
                    break;
                }
                case 39: {
                    arr_object[2] = "putUserData";
                    break;
                }
                case 9: 
                case 11: 
                case 13: 
                case 15: 
                case 16: 
                case 18: 
                case 20: 
                case 22: 
                case 24: 
                case 26: 
                case 27: 
                case 28: 
                case 29: 
                case 30: 
                case 0x1F: 
                case 0x20: 
                case 33: 
                case 34: 
                case 36: 
                case 38: 
                case 40: 
                case 41: 
                case 42: {
                    break;
                }
                default: {
                    arr_object[2] = "<init>";
                }
            }
            String s1 = String.format(s, arr_object);
            switch(v) {
                case 9: 
                case 11: 
                case 13: 
                case 15: 
                case 16: 
                case 18: 
                case 20: 
                case 22: 
                case 24: 
                case 26: 
                case 27: 
                case 28: 
                case 29: 
                case 30: 
                case 0x1F: 
                case 0x20: 
                case 33: 
                case 34: 
                case 36: 
                case 38: 
                case 40: 
                case 41: 
                case 42: {
                    illegalArgumentException0 = new IllegalStateException(s1);
                    break;
                }
                default: {
                    illegalArgumentException0 = new IllegalArgumentException(s1);
                }
            }
            throw illegalArgumentException0;
        }

        public CopyConfiguration(TypeSubstitution typeSubstitution0, DeclarationDescriptor declarationDescriptor0, Modality modality0, DescriptorVisibility descriptorVisibility0, Kind callableMemberDescriptor$Kind0, List list0, List list1, ReceiverParameterDescriptor receiverParameterDescriptor0, KotlinType kotlinType0, Name name0) {
            if(typeSubstitution0 == null) {
                CopyConfiguration.$$$reportNull$$$0(0);
            }
            if(declarationDescriptor0 == null) {
                CopyConfiguration.$$$reportNull$$$0(1);
            }
            if(modality0 == null) {
                CopyConfiguration.$$$reportNull$$$0(2);
            }
            if(descriptorVisibility0 == null) {
                CopyConfiguration.$$$reportNull$$$0(3);
            }
            if(callableMemberDescriptor$Kind0 == null) {
                CopyConfiguration.$$$reportNull$$$0(4);
            }
            if(list0 == null) {
                CopyConfiguration.$$$reportNull$$$0(5);
            }
            if(list1 == null) {
                CopyConfiguration.$$$reportNull$$$0(6);
            }
            if(kotlinType0 == null) {
                CopyConfiguration.$$$reportNull$$$0(7);
            }
            FunctionDescriptorImpl.this = functionDescriptorImpl0;
            super();
            this.original = null;
            this.dispatchReceiverParameter = functionDescriptorImpl0.dispatchReceiverParameter;
            this.copyOverrides = true;
            this.signatureChange = false;
            this.preserveSourceElement = false;
            this.dropOriginalInContainingParts = false;
            this.isHiddenToOvercomeSignatureClash = functionDescriptorImpl0.isHiddenToOvercomeSignatureClash();
            this.newTypeParameters = null;
            this.additionalAnnotations = null;
            this.isHiddenForResolutionEverywhereBesideSupercalls = functionDescriptorImpl0.isHiddenForResolutionEverywhereBesideSupercalls();
            this.userDataMap = new LinkedHashMap();
            this.newHasSynthesizedParameterNames = null;
            this.justForTypeSubstitution = false;
            this.substitution = typeSubstitution0;
            this.newOwner = declarationDescriptor0;
            this.newModality = modality0;
            this.newVisibility = descriptorVisibility0;
            this.kind = callableMemberDescriptor$Kind0;
            this.newValueParameterDescriptors = list0;
            this.newContextReceiverParameters = list1;
            this.newExtensionReceiverParameter = receiverParameterDescriptor0;
            this.newReturnType = kotlinType0;
            this.name = name0;
        }

        static Annotations access$100(CopyConfiguration functionDescriptorImpl$CopyConfiguration0) {
            return functionDescriptorImpl$CopyConfiguration0.additionalAnnotations;
        }

        static List access$200(CopyConfiguration functionDescriptorImpl$CopyConfiguration0) {
            return functionDescriptorImpl$CopyConfiguration0.newTypeParameters;
        }

        static boolean access$300(CopyConfiguration functionDescriptorImpl$CopyConfiguration0) {
            return functionDescriptorImpl$CopyConfiguration0.isHiddenToOvercomeSignatureClash;
        }

        static boolean access$400(CopyConfiguration functionDescriptorImpl$CopyConfiguration0) {
            return functionDescriptorImpl$CopyConfiguration0.isHiddenForResolutionEverywhereBesideSupercalls;
        }

        static Boolean access$500(CopyConfiguration functionDescriptorImpl$CopyConfiguration0) {
            return functionDescriptorImpl$CopyConfiguration0.newHasSynthesizedParameterNames;
        }

        static Map access$600(CopyConfiguration functionDescriptorImpl$CopyConfiguration0) {
            return functionDescriptorImpl$CopyConfiguration0.userDataMap;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
        public FunctionDescriptor build() {
            return FunctionDescriptorImpl.this.doSubstitute(this);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
        public CopyBuilder putUserData(UserDataKey callableDescriptor$UserDataKey0, Object object0) {
            if(callableDescriptor$UserDataKey0 == null) {
                CopyConfiguration.$$$reportNull$$$0(39);
            }
            this.userDataMap.put(callableDescriptor$UserDataKey0, object0);
            return this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
        public CopyBuilder setAdditionalAnnotations(Annotations annotations0) {
            return this.setAdditionalAnnotations(annotations0);
        }

        public CopyConfiguration setAdditionalAnnotations(Annotations annotations0) {
            if(annotations0 == null) {
                CopyConfiguration.$$$reportNull$$$0(35);
            }
            this.additionalAnnotations = annotations0;
            return this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
        public CopyBuilder setCopyOverrides(boolean z) {
            return this.setCopyOverrides(z);
        }

        public CopyConfiguration setCopyOverrides(boolean z) {
            this.copyOverrides = z;
            return this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
        public CopyBuilder setDispatchReceiverParameter(ReceiverParameterDescriptor receiverParameterDescriptor0) {
            return this.setDispatchReceiverParameter(receiverParameterDescriptor0);
        }

        public CopyConfiguration setDispatchReceiverParameter(ReceiverParameterDescriptor receiverParameterDescriptor0) {
            this.dispatchReceiverParameter = receiverParameterDescriptor0;
            return this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
        public CopyBuilder setDropOriginalInContainingParts() {
            return this.setDropOriginalInContainingParts();
        }

        public CopyConfiguration setDropOriginalInContainingParts() {
            this.dropOriginalInContainingParts = true;
            return this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
        public CopyBuilder setExtensionReceiverParameter(ReceiverParameterDescriptor receiverParameterDescriptor0) {
            return this.setExtensionReceiverParameter(receiverParameterDescriptor0);
        }

        public CopyConfiguration setExtensionReceiverParameter(ReceiverParameterDescriptor receiverParameterDescriptor0) {
            this.newExtensionReceiverParameter = receiverParameterDescriptor0;
            return this;
        }

        public CopyConfiguration setHasSynthesizedParameterNames(boolean z) {
            this.newHasSynthesizedParameterNames = Boolean.valueOf(z);
            return this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
        public CopyBuilder setHiddenForResolutionEverywhereBesideSupercalls() {
            return this.setHiddenForResolutionEverywhereBesideSupercalls();
        }

        public CopyConfiguration setHiddenForResolutionEverywhereBesideSupercalls() {
            this.isHiddenForResolutionEverywhereBesideSupercalls = true;
            return this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
        public CopyBuilder setHiddenToOvercomeSignatureClash() {
            return this.setHiddenToOvercomeSignatureClash();
        }

        public CopyConfiguration setHiddenToOvercomeSignatureClash() {
            this.isHiddenToOvercomeSignatureClash = true;
            return this;
        }

        public CopyConfiguration setJustForTypeSubstitution(boolean z) {
            this.justForTypeSubstitution = z;
            return this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
        public CopyBuilder setKind(Kind callableMemberDescriptor$Kind0) {
            return this.setKind(callableMemberDescriptor$Kind0);
        }

        public CopyConfiguration setKind(Kind callableMemberDescriptor$Kind0) {
            if(callableMemberDescriptor$Kind0 == null) {
                CopyConfiguration.$$$reportNull$$$0(14);
            }
            this.kind = callableMemberDescriptor$Kind0;
            return this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
        public CopyBuilder setModality(Modality modality0) {
            return this.setModality(modality0);
        }

        public CopyConfiguration setModality(Modality modality0) {
            if(modality0 == null) {
                CopyConfiguration.$$$reportNull$$$0(10);
            }
            this.newModality = modality0;
            return this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
        public CopyBuilder setName(Name name0) {
            return this.setName(name0);
        }

        public CopyConfiguration setName(Name name0) {
            if(name0 == null) {
                CopyConfiguration.$$$reportNull$$$0(17);
            }
            this.name = name0;
            return this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
        public CopyBuilder setOriginal(CallableMemberDescriptor callableMemberDescriptor0) {
            return this.setOriginal(callableMemberDescriptor0);
        }

        public CopyConfiguration setOriginal(CallableMemberDescriptor callableMemberDescriptor0) {
            this.original = (FunctionDescriptor)callableMemberDescriptor0;
            return this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
        public CopyBuilder setOwner(DeclarationDescriptor declarationDescriptor0) {
            return this.setOwner(declarationDescriptor0);
        }

        public CopyConfiguration setOwner(DeclarationDescriptor declarationDescriptor0) {
            if(declarationDescriptor0 == null) {
                CopyConfiguration.$$$reportNull$$$0(8);
            }
            this.newOwner = declarationDescriptor0;
            return this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
        public CopyBuilder setPreserveSourceElement() {
            return this.setPreserveSourceElement();
        }

        public CopyConfiguration setPreserveSourceElement() {
            this.preserveSourceElement = true;
            return this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
        public CopyBuilder setReturnType(KotlinType kotlinType0) {
            return this.setReturnType(kotlinType0);
        }

        public CopyConfiguration setReturnType(KotlinType kotlinType0) {
            if(kotlinType0 == null) {
                CopyConfiguration.$$$reportNull$$$0(23);
            }
            this.newReturnType = kotlinType0;
            return this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
        public CopyBuilder setSignatureChange() {
            return this.setSignatureChange();
        }

        public CopyConfiguration setSignatureChange() {
            this.signatureChange = true;
            return this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
        public CopyBuilder setSubstitution(TypeSubstitution typeSubstitution0) {
            return this.setSubstitution(typeSubstitution0);
        }

        public CopyConfiguration setSubstitution(TypeSubstitution typeSubstitution0) {
            if(typeSubstitution0 == null) {
                CopyConfiguration.$$$reportNull$$$0(37);
            }
            this.substitution = typeSubstitution0;
            return this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
        public CopyBuilder setTypeParameters(List list0) {
            return this.setTypeParameters(list0);
        }

        public CopyConfiguration setTypeParameters(List list0) {
            if(list0 == null) {
                CopyConfiguration.$$$reportNull$$$0(21);
            }
            this.newTypeParameters = list0;
            return this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
        public CopyBuilder setValueParameters(List list0) {
            return this.setValueParameters(list0);
        }

        public CopyConfiguration setValueParameters(List list0) {
            if(list0 == null) {
                CopyConfiguration.$$$reportNull$$$0(19);
            }
            this.newValueParameterDescriptors = list0;
            return this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor$CopyBuilder
        public CopyBuilder setVisibility(DescriptorVisibility descriptorVisibility0) {
            return this.setVisibility(descriptorVisibility0);
        }

        public CopyConfiguration setVisibility(DescriptorVisibility descriptorVisibility0) {
            if(descriptorVisibility0 == null) {
                CopyConfiguration.$$$reportNull$$$0(12);
            }
            this.newVisibility = descriptorVisibility0;
            return this;
        }
    }

    private List contextReceiverParameters;
    private ReceiverParameterDescriptor dispatchReceiverParameter;
    private ReceiverParameterDescriptor extensionReceiverParameter;
    private boolean hasStableParameterNames;
    private boolean hasSynthesizedParameterNames;
    private FunctionDescriptor initialSignatureDescriptor;
    private boolean isActual;
    private boolean isExpect;
    private boolean isExternal;
    private boolean isHiddenForResolutionEverywhereBesideSupercalls;
    private boolean isHiddenToOvercomeSignatureClash;
    private boolean isInfix;
    private boolean isInline;
    private boolean isOperator;
    private boolean isSuspend;
    private boolean isTailrec;
    private final Kind kind;
    private volatile Function0 lazyOverriddenFunctionsTask;
    private Modality modality;
    private final FunctionDescriptor original;
    private Collection overriddenFunctions;
    private List typeParameters;
    private KotlinType unsubstitutedReturnType;
    private List unsubstitutedValueParameters;
    protected Map userDataMap;
    private DescriptorVisibility visibility;

    private static void $$$reportNull$$$0(int v) {
        IllegalArgumentException illegalArgumentException0;
        int v1;
        String s;
        switch(v) {
            case 9: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 18: 
            case 19: 
            case 20: 
            case 21: 
            case 23: 
            case 26: 
            case 27: {
                s = "@NotNull method %s.%s must not return null";
                break;
            }
            default: {
                s = "Argument for @NotNull parameter \'%s\' of %s.%s must not be null";
            }
        }
        switch(v) {
            case 9: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 18: 
            case 19: 
            case 20: 
            case 21: 
            case 23: 
            case 26: 
            case 27: {
                v1 = 2;
                break;
            }
            default: {
                v1 = 3;
            }
        }
        Object[] arr_object = new Object[v1];
        switch(v) {
            case 1: {
                arr_object[0] = "annotations";
                break;
            }
            case 2: {
                arr_object[0] = "name";
                break;
            }
            case 3: {
                arr_object[0] = "kind";
                break;
            }
            case 4: {
                arr_object[0] = "source";
                break;
            }
            case 5: {
                arr_object[0] = "contextReceiverParameters";
                break;
            }
            case 6: {
                arr_object[0] = "typeParameters";
                break;
            }
            case 8: 
            case 10: {
                arr_object[0] = "visibility";
                break;
            }
            case 11: {
                arr_object[0] = "unsubstitutedReturnType";
                break;
            }
            case 12: {
                arr_object[0] = "extensionReceiverParameter";
                break;
            }
            case 17: {
                arr_object[0] = "overriddenDescriptors";
                break;
            }
            case 22: {
                arr_object[0] = "originalSubstitutor";
                break;
            }
            case 25: {
                arr_object[0] = "configuration";
                break;
            }
            case 9: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 18: 
            case 19: 
            case 20: 
            case 21: 
            case 23: 
            case 26: 
            case 27: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/FunctionDescriptorImpl";
                break;
            }
            case 7: 
            case 28: 
            case 30: {
                arr_object[0] = "unsubstitutedValueParameters";
                break;
            }
            case 24: 
            case 29: 
            case 0x1F: {
                arr_object[0] = "substitutor";
                break;
            }
            default: {
                arr_object[0] = "containingDeclaration";
            }
        }
        switch(v) {
            case 9: {
                arr_object[1] = "initialize";
                break;
            }
            case 13: {
                arr_object[1] = "getContextReceiverParameters";
                break;
            }
            case 14: {
                arr_object[1] = "getOverriddenDescriptors";
                break;
            }
            case 15: {
                arr_object[1] = "getModality";
                break;
            }
            case 16: {
                arr_object[1] = "getVisibility";
                break;
            }
            case 18: {
                arr_object[1] = "getTypeParameters";
                break;
            }
            case 19: {
                arr_object[1] = "getValueParameters";
                break;
            }
            case 20: {
                arr_object[1] = "getOriginal";
                break;
            }
            case 21: {
                arr_object[1] = "getKind";
                break;
            }
            case 23: {
                arr_object[1] = "newCopyBuilder";
                break;
            }
            case 26: {
                arr_object[1] = "copy";
                break;
            }
            case 27: {
                arr_object[1] = "getSourceToUseForCopy";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/descriptors/impl/FunctionDescriptorImpl";
            }
        }
        switch(v) {
            case 5: 
            case 6: 
            case 7: 
            case 8: {
                arr_object[2] = "initialize";
                break;
            }
            case 10: {
                arr_object[2] = "setVisibility";
                break;
            }
            case 11: {
                arr_object[2] = "setReturnType";
                break;
            }
            case 12: {
                arr_object[2] = "setExtensionReceiverParameter";
                break;
            }
            case 17: {
                arr_object[2] = "setOverriddenDescriptors";
                break;
            }
            case 22: {
                arr_object[2] = "substitute";
                break;
            }
            case 24: {
                arr_object[2] = "newCopyBuilder";
                break;
            }
            case 25: {
                arr_object[2] = "doSubstitute";
                break;
            }
            case 9: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 18: 
            case 19: 
            case 20: 
            case 21: 
            case 23: 
            case 26: 
            case 27: {
                break;
            }
            case 28: 
            case 29: 
            case 30: 
            case 0x1F: {
                arr_object[2] = "getSubstitutedValueParameters";
                break;
            }
            default: {
                arr_object[2] = "<init>";
            }
        }
        String s1 = String.format(s, arr_object);
        switch(v) {
            case 9: 
            case 13: 
            case 14: 
            case 15: 
            case 16: 
            case 18: 
            case 19: 
            case 20: 
            case 21: 
            case 23: 
            case 26: 
            case 27: {
                illegalArgumentException0 = new IllegalStateException(s1);
                break;
            }
            default: {
                illegalArgumentException0 = new IllegalArgumentException(s1);
            }
        }
        throw illegalArgumentException0;
    }

    protected FunctionDescriptorImpl(DeclarationDescriptor declarationDescriptor0, FunctionDescriptor functionDescriptor0, Annotations annotations0, Name name0, Kind callableMemberDescriptor$Kind0, SourceElement sourceElement0) {
        if(declarationDescriptor0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(0);
        }
        if(annotations0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(1);
        }
        if(name0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(2);
        }
        if(callableMemberDescriptor$Kind0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(3);
        }
        if(sourceElement0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(4);
        }
        super(declarationDescriptor0, annotations0, name0, sourceElement0);
        this.visibility = DescriptorVisibilities.UNKNOWN;
        this.isOperator = false;
        this.isInfix = false;
        this.isExternal = false;
        this.isInline = false;
        this.isTailrec = false;
        this.isExpect = false;
        this.isActual = false;
        this.isHiddenToOvercomeSignatureClash = false;
        this.isHiddenForResolutionEverywhereBesideSupercalls = false;
        this.isSuspend = false;
        this.hasStableParameterNames = true;
        this.hasSynthesizedParameterNames = false;
        this.overriddenFunctions = null;
        this.lazyOverriddenFunctionsTask = null;
        this.initialSignatureDescriptor = null;
        this.userDataMap = null;
        if(functionDescriptor0 == null) {
            functionDescriptor0 = this;
        }
        this.original = functionDescriptor0;
        this.kind = callableMemberDescriptor$Kind0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public Object accept(DeclarationDescriptorVisitor declarationDescriptorVisitor0, Object object0) {
        return declarationDescriptorVisitor0.visitFunctionDescriptor(this, object0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public CallableMemberDescriptor copy(DeclarationDescriptor declarationDescriptor0, Modality modality0, DescriptorVisibility descriptorVisibility0, Kind callableMemberDescriptor$Kind0, boolean z) {
        return this.copy(declarationDescriptor0, modality0, descriptorVisibility0, callableMemberDescriptor$Kind0, z);
    }

    public FunctionDescriptor copy(DeclarationDescriptor declarationDescriptor0, Modality modality0, DescriptorVisibility descriptorVisibility0, Kind callableMemberDescriptor$Kind0, boolean z) {
        FunctionDescriptor functionDescriptor0 = this.newCopyBuilder().setOwner(declarationDescriptor0).setModality(modality0).setVisibility(descriptorVisibility0).setKind(callableMemberDescriptor$Kind0).setCopyOverrides(z).build();
        if(functionDescriptor0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(26);
        }
        return functionDescriptor0;
    }

    protected abstract FunctionDescriptorImpl createSubstitutedCopy(DeclarationDescriptor arg1, FunctionDescriptor arg2, Kind arg3, Name arg4, Annotations arg5, SourceElement arg6);

    protected FunctionDescriptor doSubstitute(CopyConfiguration functionDescriptorImpl$CopyConfiguration0) {
        ReceiverParameterDescriptorImpl receiverParameterDescriptorImpl1;
        if(functionDescriptorImpl$CopyConfiguration0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(25);
        }
        boolean[] arr_z = new boolean[1];
        Annotations annotations0 = CopyConfiguration.access$100(functionDescriptorImpl$CopyConfiguration0) == null ? this.getAnnotations() : AnnotationsKt.composeAnnotations(this.getAnnotations(), CopyConfiguration.access$100(functionDescriptorImpl$CopyConfiguration0));
        FunctionDescriptorImpl functionDescriptorImpl0 = this.createSubstitutedCopy(functionDescriptorImpl$CopyConfiguration0.newOwner, functionDescriptorImpl$CopyConfiguration0.original, functionDescriptorImpl$CopyConfiguration0.kind, functionDescriptorImpl$CopyConfiguration0.name, annotations0, this.getSourceToUseForCopy(functionDescriptorImpl$CopyConfiguration0.preserveSourceElement, functionDescriptorImpl$CopyConfiguration0.original));
        List list0 = CopyConfiguration.access$200(functionDescriptorImpl$CopyConfiguration0) == null ? this.getTypeParameters() : CopyConfiguration.access$200(functionDescriptorImpl$CopyConfiguration0);
        arr_z[0] |= !list0.isEmpty();
        ArrayList arrayList0 = new ArrayList(list0.size());
        TypeSubstitutor typeSubstitutor0 = DescriptorSubstitutor.substituteTypeParameters(list0, functionDescriptorImpl$CopyConfiguration0.substitution, functionDescriptorImpl0, arrayList0, arr_z);
        ReceiverParameterDescriptor receiverParameterDescriptor0 = null;
        if(typeSubstitutor0 == null) {
            return null;
        }
        ArrayList arrayList1 = new ArrayList();
        if(!functionDescriptorImpl$CopyConfiguration0.newContextReceiverParameters.isEmpty()) {
            int v = 0;
            for(Object object0: functionDescriptorImpl$CopyConfiguration0.newContextReceiverParameters) {
                KotlinType kotlinType0 = typeSubstitutor0.substitute(((ReceiverParameterDescriptor)object0).getType(), Variance.IN_VARIANCE);
                if(kotlinType0 == null) {
                    return null;
                }
                arrayList1.add(DescriptorFactory.createContextReceiverParameterForCallable(functionDescriptorImpl0, kotlinType0, ((ImplicitContextReceiver)((ReceiverParameterDescriptor)object0).getValue()).getCustomLabelName(), ((ReceiverParameterDescriptor)object0).getAnnotations(), v));
                arr_z[0] |= kotlinType0 != ((ReceiverParameterDescriptor)object0).getType();
                ++v;
            }
        }
        if(functionDescriptorImpl$CopyConfiguration0.newExtensionReceiverParameter == null) {
            receiverParameterDescriptorImpl1 = null;
        }
        else {
            KotlinType kotlinType1 = typeSubstitutor0.substitute(functionDescriptorImpl$CopyConfiguration0.newExtensionReceiverParameter.getType(), Variance.IN_VARIANCE);
            if(kotlinType1 == null) {
                return null;
            }
            ReceiverParameterDescriptorImpl receiverParameterDescriptorImpl0 = new ReceiverParameterDescriptorImpl(functionDescriptorImpl0, new ExtensionReceiver(functionDescriptorImpl0, kotlinType1, functionDescriptorImpl$CopyConfiguration0.newExtensionReceiverParameter.getValue()), functionDescriptorImpl$CopyConfiguration0.newExtensionReceiverParameter.getAnnotations());
            boolean z = arr_z[0];
            arr_z[0] = kotlinType1 != functionDescriptorImpl$CopyConfiguration0.newExtensionReceiverParameter.getType() | z;
            receiverParameterDescriptorImpl1 = receiverParameterDescriptorImpl0;
        }
        if(functionDescriptorImpl$CopyConfiguration0.dispatchReceiverParameter != null) {
            ReceiverParameterDescriptor receiverParameterDescriptor1 = functionDescriptorImpl$CopyConfiguration0.dispatchReceiverParameter.substitute(typeSubstitutor0);
            if(receiverParameterDescriptor1 == null) {
                return null;
            }
            arr_z[0] |= receiverParameterDescriptor1 != functionDescriptorImpl$CopyConfiguration0.dispatchReceiverParameter;
            receiverParameterDescriptor0 = receiverParameterDescriptor1;
        }
        List list1 = FunctionDescriptorImpl.getSubstitutedValueParameters(functionDescriptorImpl0, functionDescriptorImpl$CopyConfiguration0.newValueParameterDescriptors, typeSubstitutor0, functionDescriptorImpl$CopyConfiguration0.dropOriginalInContainingParts, functionDescriptorImpl$CopyConfiguration0.preserveSourceElement, arr_z);
        if(list1 == null) {
            return null;
        }
        KotlinType kotlinType2 = typeSubstitutor0.substitute(functionDescriptorImpl$CopyConfiguration0.newReturnType, Variance.OUT_VARIANCE);
        if(kotlinType2 == null) {
            return null;
        }
        int v1 = arr_z[0] | kotlinType2 != functionDescriptorImpl$CopyConfiguration0.newReturnType;
        arr_z[0] = v1;
        if(v1 == 0 && functionDescriptorImpl$CopyConfiguration0.justForTypeSubstitution) {
            return this;
        }
        functionDescriptorImpl0.initialize(receiverParameterDescriptorImpl1, receiverParameterDescriptor0, arrayList1, arrayList0, list1, kotlinType2, functionDescriptorImpl$CopyConfiguration0.newModality, functionDescriptorImpl$CopyConfiguration0.newVisibility);
        functionDescriptorImpl0.setOperator(this.isOperator);
        functionDescriptorImpl0.setInfix(this.isInfix);
        functionDescriptorImpl0.setExternal(this.isExternal);
        functionDescriptorImpl0.setInline(this.isInline);
        functionDescriptorImpl0.setTailrec(this.isTailrec);
        functionDescriptorImpl0.setSuspend(this.isSuspend);
        functionDescriptorImpl0.setExpect(this.isExpect);
        functionDescriptorImpl0.setActual(this.isActual);
        functionDescriptorImpl0.setHasStableParameterNames(this.hasStableParameterNames);
        functionDescriptorImpl0.setHiddenToOvercomeSignatureClash(CopyConfiguration.access$300(functionDescriptorImpl$CopyConfiguration0));
        functionDescriptorImpl0.setHiddenForResolutionEverywhereBesideSupercalls(CopyConfiguration.access$400(functionDescriptorImpl$CopyConfiguration0));
        functionDescriptorImpl0.setHasSynthesizedParameterNames((CopyConfiguration.access$500(functionDescriptorImpl$CopyConfiguration0) == null ? this.hasSynthesizedParameterNames : CopyConfiguration.access$500(functionDescriptorImpl$CopyConfiguration0).booleanValue()));
        if(!CopyConfiguration.access$600(functionDescriptorImpl$CopyConfiguration0).isEmpty() || this.userDataMap != null) {
            Map map0 = CopyConfiguration.access$600(functionDescriptorImpl$CopyConfiguration0);
            Map map1 = this.userDataMap;
            if(map1 != null) {
                for(Object object1: map1.entrySet()) {
                    Map.Entry map$Entry0 = (Map.Entry)object1;
                    if(!map0.containsKey(map$Entry0.getKey())) {
                        map0.put(map$Entry0.getKey(), map$Entry0.getValue());
                    }
                }
            }
            if(map0.size() == 1) {
                Object object2 = map0.keySet().iterator().next();
                Object object3 = map0.values().iterator().next();
                functionDescriptorImpl0.userDataMap = Collections.singletonMap(object2, object3);
            }
            else {
                functionDescriptorImpl0.userDataMap = map0;
            }
        }
        if(functionDescriptorImpl$CopyConfiguration0.signatureChange || this.getInitialSignatureDescriptor() != null) {
            FunctionDescriptor functionDescriptor0 = this.getInitialSignatureDescriptor() == null ? this : this.getInitialSignatureDescriptor();
            functionDescriptorImpl0.setInitialSignatureDescriptor(functionDescriptor0.substitute(typeSubstitutor0));
        }
        if(functionDescriptorImpl$CopyConfiguration0.copyOverrides && !this.getOriginal().getOverriddenDescriptors().isEmpty()) {
            if(functionDescriptorImpl$CopyConfiguration0.substitution.isEmpty()) {
                Function0 function00 = this.lazyOverriddenFunctionsTask;
                if(function00 != null) {
                    functionDescriptorImpl0.lazyOverriddenFunctionsTask = function00;
                    return functionDescriptorImpl0;
                }
                functionDescriptorImpl0.setOverriddenDescriptors(this.getOverriddenDescriptors());
                return functionDescriptorImpl0;
            }
            functionDescriptorImpl0.lazyOverriddenFunctionsTask = new Function0() {
                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public Collection invoke() {
                    Collection collection0 = new SmartList();
                    for(Object object0: FunctionDescriptorImpl.this.getOverriddenDescriptors()) {
                        collection0.add(((FunctionDescriptor)object0).substitute(typeSubstitutor0));
                    }
                    return collection0;
                }
            };
        }
        return functionDescriptorImpl0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public List getContextReceiverParameters() {
        List list0 = this.contextReceiverParameters;
        if(list0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(13);
        }
        return list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public ReceiverParameterDescriptor getDispatchReceiverParameter() {
        return this.dispatchReceiverParameter;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public ReceiverParameterDescriptor getExtensionReceiverParameter() {
        return this.extensionReceiverParameter;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public FunctionDescriptor getInitialSignatureDescriptor() {
        return this.initialSignatureDescriptor;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public Kind getKind() {
        Kind callableMemberDescriptor$Kind0 = this.kind;
        if(callableMemberDescriptor$Kind0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(21);
        }
        return callableMemberDescriptor$Kind0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public Modality getModality() {
        Modality modality0 = this.modality;
        if(modality0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(15);
        }
        return modality0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public CallableDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public CallableMemberDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public DeclarationDescriptor getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorNonRootImpl
    public DeclarationDescriptorWithSource getOriginal() {
        return this.getOriginal();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public FunctionDescriptor getOriginal() {
        FunctionDescriptor functionDescriptor0 = this.original;
        FunctionDescriptor functionDescriptor1 = functionDescriptor0 == this ? this : functionDescriptor0.getOriginal();
        if(functionDescriptor1 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(20);
        }
        return functionDescriptor1;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public Collection getOverriddenDescriptors() {
        this.performOverriddenLazyCalculationIfNeeded();
        Collection collection0 = this.overriddenFunctions;
        if(collection0 == null) {
            collection0 = Collections.EMPTY_LIST;
        }
        if(collection0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(14);
        }
        return collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public KotlinType getReturnType() {
        return this.unsubstitutedReturnType;
    }

    private SourceElement getSourceToUseForCopy(boolean z, FunctionDescriptor functionDescriptor0) {
        SourceElement sourceElement0;
        if(z) {
            if(functionDescriptor0 == null) {
                functionDescriptor0 = this.getOriginal();
            }
            sourceElement0 = functionDescriptor0.getSource();
        }
        else {
            sourceElement0 = SourceElement.NO_SOURCE;
        }
        if(sourceElement0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(27);
        }
        return sourceElement0;
    }

    public static List getSubstitutedValueParameters(FunctionDescriptor functionDescriptor0, List list0, TypeSubstitutor typeSubstitutor0) {
        if(list0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(28);
        }
        if(typeSubstitutor0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(29);
        }
        return FunctionDescriptorImpl.getSubstitutedValueParameters(functionDescriptor0, list0, typeSubstitutor0, false, false, null);
    }

    public static List getSubstitutedValueParameters(FunctionDescriptor functionDescriptor0, List list0, TypeSubstitutor typeSubstitutor0, boolean z, boolean z1, boolean[] arr_z) {
        if(list0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(30);
        }
        if(typeSubstitutor0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(0x1F);
        }
        List list1 = new ArrayList(list0.size());
        for(Object object0: list0) {
            ValueParameterDescriptor valueParameterDescriptor0 = (ValueParameterDescriptor)object0;
            KotlinType kotlinType0 = typeSubstitutor0.substitute(valueParameterDescriptor0.getType(), Variance.IN_VARIANCE);
            KotlinType kotlinType1 = valueParameterDescriptor0.getVarargElementType();
            KotlinType kotlinType2 = kotlinType1 == null ? null : typeSubstitutor0.substitute(kotlinType1, Variance.IN_VARIANCE);
            if(kotlinType0 == null) {
                return null;
            }
            if((kotlinType0 != valueParameterDescriptor0.getType() || kotlinType1 != kotlinType2) && arr_z != null) {
                arr_z[0] = true;
            }
            kotlin.reflect.jvm.internal.impl.descriptors.impl.FunctionDescriptorImpl.2 functionDescriptorImpl$20 = valueParameterDescriptor0 instanceof WithDestructuringDeclaration ? new Function0() {
                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public List invoke() {
                    return ((WithDestructuringDeclaration)valueParameterDescriptor0).getDestructuringVariables();
                }
            } : null;
            list1.add(ValueParameterDescriptorImpl.createWithDestructuringDeclarations(functionDescriptor0, (z ? null : valueParameterDescriptor0), valueParameterDescriptor0.getIndex(), valueParameterDescriptor0.getAnnotations(), valueParameterDescriptor0.getName(), kotlinType0, valueParameterDescriptor0.declaresDefaultValue(), valueParameterDescriptor0.isCrossinline(), valueParameterDescriptor0.isNoinline(), kotlinType2, (z1 ? valueParameterDescriptor0.getSource() : SourceElement.NO_SOURCE), functionDescriptorImpl$20));
        }
        return list1;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public List getTypeParameters() {
        List list0 = this.typeParameters;
        if(list0 == null) {
            throw new IllegalStateException("typeParameters == null for " + this);
        }
        if(list0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(18);
        }
        return list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public Object getUserData(UserDataKey callableDescriptor$UserDataKey0) {
        return this.userDataMap == null ? null : this.userDataMap.get(callableDescriptor$UserDataKey0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public List getValueParameters() {
        List list0 = this.unsubstitutedValueParameters;
        if(list0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(19);
        }
        return list0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility
    public DescriptorVisibility getVisibility() {
        DescriptorVisibility descriptorVisibility0 = this.visibility;
        if(descriptorVisibility0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(16);
        }
        return descriptorVisibility0;
    }

    public boolean hasStableParameterNames() {
        return this.hasStableParameterNames;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    public boolean hasSynthesizedParameterNames() {
        return this.hasSynthesizedParameterNames;
    }

    public FunctionDescriptorImpl initialize(ReceiverParameterDescriptor receiverParameterDescriptor0, ReceiverParameterDescriptor receiverParameterDescriptor1, List list0, List list1, List list2, KotlinType kotlinType0, Modality modality0, DescriptorVisibility descriptorVisibility0) {
        if(list0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(5);
        }
        if(list1 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(6);
        }
        if(list2 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(7);
        }
        if(descriptorVisibility0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(8);
        }
        this.typeParameters = CollectionsKt.toList(list1);
        this.unsubstitutedValueParameters = CollectionsKt.toList(list2);
        this.unsubstitutedReturnType = kotlinType0;
        this.modality = modality0;
        this.visibility = descriptorVisibility0;
        this.extensionReceiverParameter = receiverParameterDescriptor0;
        this.dispatchReceiverParameter = receiverParameterDescriptor1;
        this.contextReceiverParameters = list0;
        for(int v1 = 0; v1 < list1.size(); ++v1) {
            TypeParameterDescriptor typeParameterDescriptor0 = (TypeParameterDescriptor)list1.get(v1);
            if(typeParameterDescriptor0.getIndex() != v1) {
                throw new IllegalStateException(typeParameterDescriptor0 + " index is " + typeParameterDescriptor0.getIndex() + " but position is " + v1);
            }
        }
        for(int v = 0; v < list2.size(); ++v) {
            ValueParameterDescriptor valueParameterDescriptor0 = (ValueParameterDescriptor)list2.get(v);
            if(valueParameterDescriptor0.getIndex() != v) {
                throw new IllegalStateException(valueParameterDescriptor0 + "index is " + valueParameterDescriptor0.getIndex() + " but position is " + v);
            }
        }
        return this;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isActual() {
        return this.isActual;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExpect() {
        return this.isExpect;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExternal() {
        return this.isExternal;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isHiddenForResolutionEverywhereBesideSupercalls() {
        return this.isHiddenForResolutionEverywhereBesideSupercalls;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isHiddenToOvercomeSignatureClash() {
        return this.isHiddenToOvercomeSignatureClash;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isInfix() {
        if(this.isInfix) {
            return true;
        }
        for(Object object0: this.getOriginal().getOverriddenDescriptors()) {
            if(((FunctionDescriptor)object0).isInfix()) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isInline() {
        return this.isInline;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isOperator() {
        if(this.isOperator) {
            return true;
        }
        for(Object object0: this.getOriginal().getOverriddenDescriptors()) {
            if(((FunctionDescriptor)object0).isOperator()) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isSuspend() {
        return this.isSuspend;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public boolean isTailrec() {
        return this.isTailrec;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public CopyBuilder newCopyBuilder() {
        CopyBuilder functionDescriptor$CopyBuilder0 = this.newCopyBuilder(TypeSubstitutor.EMPTY);
        if(functionDescriptor$CopyBuilder0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(23);
        }
        return functionDescriptor$CopyBuilder0;
    }

    protected CopyConfiguration newCopyBuilder(TypeSubstitutor typeSubstitutor0) {
        if(typeSubstitutor0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(24);
        }
        return new CopyConfiguration(this, typeSubstitutor0.getSubstitution(), this.getContainingDeclaration(), this.getModality(), this.getVisibility(), this.getKind(), this.getValueParameters(), this.getContextReceiverParameters(), this.getExtensionReceiverParameter(), this.getReturnType(), null);
    }

    private void performOverriddenLazyCalculationIfNeeded() {
        Function0 function00 = this.lazyOverriddenFunctionsTask;
        if(function00 != null) {
            this.overriddenFunctions = (Collection)function00.invoke();
            this.lazyOverriddenFunctionsTask = null;
        }
    }

    public void putInUserDataMap(UserDataKey callableDescriptor$UserDataKey0, Object object0) {
        if(this.userDataMap == null) {
            this.userDataMap = new LinkedHashMap();
        }
        this.userDataMap.put(callableDescriptor$UserDataKey0, object0);
    }

    public void setActual(boolean z) {
        this.isActual = z;
    }

    public void setExpect(boolean z) {
        this.isExpect = z;
    }

    public void setExternal(boolean z) {
        this.isExternal = z;
    }

    public void setHasStableParameterNames(boolean z) {
        this.hasStableParameterNames = z;
    }

    public void setHasSynthesizedParameterNames(boolean z) {
        this.hasSynthesizedParameterNames = z;
    }

    private void setHiddenForResolutionEverywhereBesideSupercalls(boolean z) {
        this.isHiddenForResolutionEverywhereBesideSupercalls = z;
    }

    private void setHiddenToOvercomeSignatureClash(boolean z) {
        this.isHiddenToOvercomeSignatureClash = z;
    }

    public void setInfix(boolean z) {
        this.isInfix = z;
    }

    private void setInitialSignatureDescriptor(FunctionDescriptor functionDescriptor0) {
        this.initialSignatureDescriptor = functionDescriptor0;
    }

    public void setInline(boolean z) {
        this.isInline = z;
    }

    public void setOperator(boolean z) {
        this.isOperator = z;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor
    public void setOverriddenDescriptors(Collection collection0) {
        if(collection0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(17);
        }
        this.overriddenFunctions = collection0;
        for(Object object0: collection0) {
            if(((FunctionDescriptor)object0).isHiddenForResolutionEverywhereBesideSupercalls()) {
                this.isHiddenForResolutionEverywhereBesideSupercalls = true;
                return;
            }
            if(false) {
                break;
            }
        }
    }

    public void setReturnType(KotlinType kotlinType0) {
        if(kotlinType0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(11);
        }
        this.unsubstitutedReturnType = kotlinType0;
    }

    public void setSuspend(boolean z) {
        this.isSuspend = z;
    }

    public void setTailrec(boolean z) {
        this.isTailrec = z;
    }

    public void setVisibility(DescriptorVisibility descriptorVisibility0) {
        if(descriptorVisibility0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(10);
        }
        this.visibility = descriptorVisibility0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.Substitutable
    public DeclarationDescriptorNonRoot substitute(TypeSubstitutor typeSubstitutor0) {
        return this.substitute(typeSubstitutor0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor
    public FunctionDescriptor substitute(TypeSubstitutor typeSubstitutor0) {
        if(typeSubstitutor0 == null) {
            FunctionDescriptorImpl.$$$reportNull$$$0(22);
        }
        return typeSubstitutor0.isEmpty() ? this : this.newCopyBuilder(typeSubstitutor0).setOriginal(this.getOriginal()).setPreserveSourceElement().setJustForTypeSubstitution(true).build();
    }
}

