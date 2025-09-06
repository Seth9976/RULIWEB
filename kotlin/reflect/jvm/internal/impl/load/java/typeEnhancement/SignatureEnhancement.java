package kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.AnnotationQualifierApplicabilityType;
import kotlin.reflect.jvm.internal.impl.load.java.DeprecationCausedByFunctionNInfo;
import kotlin.reflect.jvm.internal.impl.load.java.UtilsKt;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaCallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.JavaDescriptorUtilKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaAnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureBuildingUtilsKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.resolve.deprecation.DescriptorBasedDeprecationInfoKt;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.RawType;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.UnwrappedType;
import kotlin.reflect.jvm.internal.impl.types.typeUtil.TypeUtilsKt;

public final class SignatureEnhancement {
    private final JavaTypeEnhancement typeEnhancement;

    public SignatureEnhancement(JavaTypeEnhancement javaTypeEnhancement0) {
        Intrinsics.checkNotNullParameter(javaTypeEnhancement0, "typeEnhancement");
        super();
        this.typeEnhancement = javaTypeEnhancement0;
    }

    private final boolean containsFunctionN(KotlinType kotlinType0) {
        return TypeUtils.contains(kotlinType0, kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.containsFunctionN.1.INSTANCE);

        final class kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.containsFunctionN.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.containsFunctionN.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.containsFunctionN.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.containsFunctionN.1();
            }

            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.containsFunctionN.1() {
                super(1);
            }

            public final Boolean invoke(UnwrappedType unwrappedType0) {
                ClassifierDescriptor classifierDescriptor0 = unwrappedType0.getConstructor().getDeclarationDescriptor();
                boolean z = false;
                if(classifierDescriptor0 == null) {
                    return false;
                }
                if(Intrinsics.areEqual(classifierDescriptor0.getName(), JavaToKotlinClassMap.INSTANCE.getFUNCTION_N_FQ_NAME().shortName()) && Intrinsics.areEqual(DescriptorUtilsKt.fqNameOrNull(classifierDescriptor0), JavaToKotlinClassMap.INSTANCE.getFUNCTION_N_FQ_NAME())) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((UnwrappedType)object0));
            }
        }

    }

    private final KotlinType enhance(CallableMemberDescriptor callableMemberDescriptor0, Annotated annotated0, boolean z, LazyJavaResolverContext lazyJavaResolverContext0, AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType0, TypeEnhancementInfo typeEnhancementInfo0, boolean z1, Function1 function10) {
        SignatureParts signatureParts0 = new SignatureParts(annotated0, z, lazyJavaResolverContext0, annotationQualifierApplicabilityType0, false, 16, null);
        Object object0 = function10.invoke(callableMemberDescriptor0);
        Collection collection0 = callableMemberDescriptor0.getOverriddenDescriptors();
        Intrinsics.checkNotNullExpressionValue(collection0, "overriddenDescriptors");
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection0, 10));
        for(Object object1: collection0) {
            Intrinsics.checkNotNullExpressionValue(((CallableMemberDescriptor)object1), "it");
            arrayList0.add(((KotlinType)function10.invoke(((CallableMemberDescriptor)object1))));
        }
        return this.enhance(signatureParts0, ((KotlinType)object0), arrayList0, typeEnhancementInfo0, z1);
    }

    private final KotlinType enhance(SignatureParts signatureParts0, KotlinType kotlinType0, List list0, TypeEnhancementInfo typeEnhancementInfo0, boolean z) {
        Function1 function10 = signatureParts0.computeIndexedQualifiers(kotlinType0, list0, typeEnhancementInfo0, z);
        return this.typeEnhancement.enhance(kotlinType0, function10, signatureParts0.getSkipRawTypeArguments());
    }

    static KotlinType enhance$default(SignatureEnhancement signatureEnhancement0, CallableMemberDescriptor callableMemberDescriptor0, Annotated annotated0, boolean z, LazyJavaResolverContext lazyJavaResolverContext0, AnnotationQualifierApplicabilityType annotationQualifierApplicabilityType0, TypeEnhancementInfo typeEnhancementInfo0, boolean z1, Function1 function10, int v, Object object0) {
        return (v & 0x20) == 0 ? signatureEnhancement0.enhance(callableMemberDescriptor0, annotated0, z, lazyJavaResolverContext0, annotationQualifierApplicabilityType0, typeEnhancementInfo0, z1, function10) : signatureEnhancement0.enhance(callableMemberDescriptor0, annotated0, z, lazyJavaResolverContext0, annotationQualifierApplicabilityType0, typeEnhancementInfo0, false, function10);
    }

    static KotlinType enhance$default(SignatureEnhancement signatureEnhancement0, SignatureParts signatureParts0, KotlinType kotlinType0, List list0, TypeEnhancementInfo typeEnhancementInfo0, boolean z, int v, Object object0) {
        if((v & 4) != 0) {
            typeEnhancementInfo0 = null;
        }
        return (v & 8) == 0 ? signatureEnhancement0.enhance(signatureParts0, kotlinType0, list0, typeEnhancementInfo0, z) : signatureEnhancement0.enhance(signatureParts0, kotlinType0, list0, typeEnhancementInfo0, false);
    }

    private final CallableMemberDescriptor enhanceSignature(CallableMemberDescriptor callableMemberDescriptor0, LazyJavaResolverContext lazyJavaResolverContext0) {
        Pair pair0;
        boolean z1;
        TypeEnhancementInfo typeEnhancementInfo0;
        PredefinedFunctionEnhancementInfo predefinedFunctionEnhancementInfo0;
        KotlinType kotlinType1;
        CallableMemberDescriptor callableMemberDescriptor1;
        if(callableMemberDescriptor0 instanceof JavaCallableMemberDescriptor && (((JavaCallableMemberDescriptor)callableMemberDescriptor0).getKind() != Kind.FAKE_OVERRIDE || ((JavaCallableMemberDescriptor)callableMemberDescriptor0).getOriginal().getOverriddenDescriptors().size() != 1)) {
            LazyJavaResolverContext lazyJavaResolverContext1 = ContextKt.copyWithNewDefaultTypeQualifiers(lazyJavaResolverContext0, this.getDefaultAnnotations(callableMemberDescriptor0, lazyJavaResolverContext0));
            if(callableMemberDescriptor0 instanceof JavaPropertyDescriptor) {
                PropertyGetterDescriptorImpl propertyGetterDescriptorImpl0 = ((JavaPropertyDescriptor)callableMemberDescriptor0).getGetter();
                if(propertyGetterDescriptorImpl0 == null || propertyGetterDescriptorImpl0.isDefault()) {
                    callableMemberDescriptor1 = callableMemberDescriptor0;
                }
                else {
                    PropertyGetterDescriptorImpl propertyGetterDescriptorImpl1 = ((JavaPropertyDescriptor)callableMemberDescriptor0).getGetter();
                    Intrinsics.checkNotNull(propertyGetterDescriptorImpl1);
                    callableMemberDescriptor1 = propertyGetterDescriptorImpl1;
                }
            }
            else {
                callableMemberDescriptor1 = callableMemberDescriptor0;
            }
            KotlinType kotlinType0 = null;
            if(((JavaCallableMemberDescriptor)callableMemberDescriptor0).getExtensionReceiverParameter() == null) {
                kotlinType1 = null;
            }
            else {
                FunctionDescriptor functionDescriptor0 = callableMemberDescriptor1 instanceof FunctionDescriptor ? ((FunctionDescriptor)callableMemberDescriptor1) : null;
                kotlinType1 = this.enhanceValueParameter(callableMemberDescriptor0, (functionDescriptor0 == null ? null : ((ValueParameterDescriptor)functionDescriptor0.getUserData(JavaMethodDescriptor.ORIGINAL_VALUE_PARAMETER_FOR_EXTENSION_RECEIVER))), lazyJavaResolverContext1, null, false, kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.enhanceSignature.receiverTypeEnhancement.1.INSTANCE);
            }
            JavaMethodDescriptor javaMethodDescriptor0 = callableMemberDescriptor0 instanceof JavaMethodDescriptor ? ((JavaMethodDescriptor)callableMemberDescriptor0) : null;
            int v = 0;
            if(javaMethodDescriptor0 == null) {
                predefinedFunctionEnhancementInfo0 = null;
            }
            else {
                DeclarationDescriptor declarationDescriptor0 = javaMethodDescriptor0.getContainingDeclaration();
                Intrinsics.checkNotNull(declarationDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                String s = MethodSignatureMappingKt.computeJvmDescriptor$default(javaMethodDescriptor0, false, false, 3, null);
                String s1 = MethodSignatureBuildingUtilsKt.signature(SignatureBuildingComponents.INSTANCE, ((ClassDescriptor)declarationDescriptor0), s);
                predefinedFunctionEnhancementInfo0 = s1 == null ? null : ((PredefinedFunctionEnhancementInfo)PredefinedEnhancementInfoKt.getPREDEFINED_FUNCTION_ENHANCEMENT_INFO_BY_SIGNATURE().get(s1));
            }
            if(predefinedFunctionEnhancementInfo0 != null) {
                predefinedFunctionEnhancementInfo0.getParametersInfo().size();
                ((JavaCallableMemberDescriptor)callableMemberDescriptor0).getValueParameters().size();
            }
            boolean z = (UtilsKt.isJspecifyEnabledInStrictMode(lazyJavaResolverContext0.getComponents().getJavaTypeEnhancementState()) || lazyJavaResolverContext1.getComponents().getSettings().getIgnoreNullabilityForErasedValueParameters()) && UtilsKt.hasErasedValueParameters(callableMemberDescriptor0);
            List list0 = callableMemberDescriptor1.getValueParameters();
            Intrinsics.checkNotNullExpressionValue(list0, "annotationOwnerForMember.valueParameters");
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
            for(Object object0: list0) {
                ValueParameterDescriptor valueParameterDescriptor0 = (ValueParameterDescriptor)object0;
                if(predefinedFunctionEnhancementInfo0 == null) {
                    typeEnhancementInfo0 = null;
                }
                else {
                    List list1 = predefinedFunctionEnhancementInfo0.getParametersInfo();
                    if(list1 != null) {
                        typeEnhancementInfo0 = (TypeEnhancementInfo)CollectionsKt.getOrNull(list1, valueParameterDescriptor0.getIndex());
                    }
                }
                arrayList0.add(this.enhanceValueParameter(callableMemberDescriptor0, valueParameterDescriptor0, lazyJavaResolverContext1, typeEnhancementInfo0, z, new Function1() {
                    final ValueParameterDescriptor $p;

                    {
                        this.$p = valueParameterDescriptor0;
                        super(1);
                    }

                    @Override  // kotlin.jvm.functions.Function1
                    public Object invoke(Object object0) {
                        return this.invoke(((CallableMemberDescriptor)object0));
                    }

                    public final KotlinType invoke(CallableMemberDescriptor callableMemberDescriptor0) {
                        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "it");
                        KotlinType kotlinType0 = ((ValueParameterDescriptor)callableMemberDescriptor0.getValueParameters().get(this.$p.getIndex())).getType();
                        Intrinsics.checkNotNullExpressionValue(kotlinType0, "it.valueParameters[p.index].type");
                        return kotlinType0;
                    }
                }));
            }
            PropertyDescriptor propertyDescriptor0 = callableMemberDescriptor0 instanceof PropertyDescriptor ? ((PropertyDescriptor)callableMemberDescriptor0) : null;
            KotlinType kotlinType2 = SignatureEnhancement.enhance$default(this, callableMemberDescriptor0, callableMemberDescriptor1, true, lazyJavaResolverContext1, (propertyDescriptor0 == null || !JavaDescriptorUtilKt.isJavaField(propertyDescriptor0) ? AnnotationQualifierApplicabilityType.METHOD_RETURN_TYPE : AnnotationQualifierApplicabilityType.FIELD), (predefinedFunctionEnhancementInfo0 == null ? null : predefinedFunctionEnhancementInfo0.getReturnTypeInfo()), false, kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.enhanceSignature.returnTypeEnhancement.1.INSTANCE, 0x20, null);
            KotlinType kotlinType3 = ((JavaCallableMemberDescriptor)callableMemberDescriptor0).getReturnType();
            Intrinsics.checkNotNull(kotlinType3);
            if(this.containsFunctionN(kotlinType3)) {
                pair0 = TuplesKt.to(DescriptorBasedDeprecationInfoKt.getDEPRECATED_FUNCTION_KEY(), new DeprecationCausedByFunctionNInfo(callableMemberDescriptor0));
            }
            else {
                ReceiverParameterDescriptor receiverParameterDescriptor0 = ((JavaCallableMemberDescriptor)callableMemberDescriptor0).getExtensionReceiverParameter();
                if(receiverParameterDescriptor0 == null) {
                    z1 = false;
                }
                else {
                    KotlinType kotlinType4 = receiverParameterDescriptor0.getType();
                    z1 = kotlinType4 == null ? false : this.containsFunctionN(kotlinType4);
                }
                if(z1) {
                    pair0 = TuplesKt.to(DescriptorBasedDeprecationInfoKt.getDEPRECATED_FUNCTION_KEY(), new DeprecationCausedByFunctionNInfo(callableMemberDescriptor0));
                }
                else {
                    boolean z2 = false;
                    List list2 = ((JavaCallableMemberDescriptor)callableMemberDescriptor0).getValueParameters();
                    Intrinsics.checkNotNullExpressionValue(list2, "valueParameters");
                    if(!(list2 instanceof Collection) || !list2.isEmpty()) {
                        for(Object object1: list2) {
                            KotlinType kotlinType5 = ((ValueParameterDescriptor)object1).getType();
                            Intrinsics.checkNotNullExpressionValue(kotlinType5, "it.type");
                            if(this.containsFunctionN(kotlinType5)) {
                                z2 = true;
                                break;
                            }
                        }
                    }
                    pair0 = z2 ? TuplesKt.to(DescriptorBasedDeprecationInfoKt.getDEPRECATED_FUNCTION_KEY(), new DeprecationCausedByFunctionNInfo(callableMemberDescriptor0)) : null;
                }
            }
            boolean z3 = false;
            if(kotlinType1 != null || kotlinType2 != null) {
                goto label_93;
            }
            if(!(arrayList0 instanceof Collection) || !arrayList0.isEmpty()) {
                for(Object object2: arrayList0) {
                    if(((KotlinType)object2) != null) {
                        z3 = true;
                        break;
                    }
                }
            }
            if(z3 || pair0 != null) {
            label_93:
                if(kotlinType1 == null) {
                    ReceiverParameterDescriptor receiverParameterDescriptor1 = ((JavaCallableMemberDescriptor)callableMemberDescriptor0).getExtensionReceiverParameter();
                    if(receiverParameterDescriptor1 != null) {
                        kotlinType0 = receiverParameterDescriptor1.getType();
                    }
                }
                else {
                    kotlinType0 = kotlinType1;
                }
                ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList0, 10));
                for(Object object3: arrayList0) {
                    if(v < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    KotlinType kotlinType6 = (KotlinType)object3;
                    if(kotlinType6 == null) {
                        kotlinType6 = ((ValueParameterDescriptor)((JavaCallableMemberDescriptor)callableMemberDescriptor0).getValueParameters().get(v)).getType();
                        Intrinsics.checkNotNullExpressionValue(kotlinType6, "valueParameters[index].type");
                    }
                    arrayList1.add(kotlinType6);
                    ++v;
                }
                if(kotlinType2 == null) {
                    kotlinType2 = ((JavaCallableMemberDescriptor)callableMemberDescriptor0).getReturnType();
                    Intrinsics.checkNotNull(kotlinType2);
                }
                JavaCallableMemberDescriptor javaCallableMemberDescriptor0 = ((JavaCallableMemberDescriptor)callableMemberDescriptor0).enhance(kotlinType0, arrayList1, kotlinType2, pair0);
                Intrinsics.checkNotNull(javaCallableMemberDescriptor0, "null cannot be cast to non-null type D of org.jetbrains.kotlin.load.java.typeEnhancement.SignatureEnhancement.enhanceSignature");
                return javaCallableMemberDescriptor0;
            }
        }
        return callableMemberDescriptor0;

        final class kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.enhanceSignature.receiverTypeEnhancement.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.enhanceSignature.receiverTypeEnhancement.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.enhanceSignature.receiverTypeEnhancement.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.enhanceSignature.receiverTypeEnhancement.1();
            }

            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.enhanceSignature.receiverTypeEnhancement.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((CallableMemberDescriptor)object0));
            }

            public final KotlinType invoke(CallableMemberDescriptor callableMemberDescriptor0) {
                Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "it");
                ReceiverParameterDescriptor receiverParameterDescriptor0 = callableMemberDescriptor0.getExtensionReceiverParameter();
                Intrinsics.checkNotNull(receiverParameterDescriptor0);
                KotlinType kotlinType0 = receiverParameterDescriptor0.getType();
                Intrinsics.checkNotNullExpressionValue(kotlinType0, "it.extensionReceiverParameter!!.type");
                return kotlinType0;
            }
        }


        final class kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.enhanceSignature.returnTypeEnhancement.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.enhanceSignature.returnTypeEnhancement.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.enhanceSignature.returnTypeEnhancement.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.enhanceSignature.returnTypeEnhancement.1();
            }

            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.enhanceSignature.returnTypeEnhancement.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((CallableMemberDescriptor)object0));
            }

            public final KotlinType invoke(CallableMemberDescriptor callableMemberDescriptor0) {
                Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "it");
                KotlinType kotlinType0 = callableMemberDescriptor0.getReturnType();
                Intrinsics.checkNotNull(kotlinType0);
                return kotlinType0;
            }
        }

    }

    public final Collection enhanceSignatures(LazyJavaResolverContext lazyJavaResolverContext0, Collection collection0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "c");
        Intrinsics.checkNotNullParameter(collection0, "platformSignatures");
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection0, 10));
        for(Object object0: collection0) {
            arrayList0.add(this.enhanceSignature(((CallableMemberDescriptor)object0), lazyJavaResolverContext0));
        }
        return arrayList0;
    }

    public final KotlinType enhanceSuperType(KotlinType kotlinType0, LazyJavaResolverContext lazyJavaResolverContext0) {
        Intrinsics.checkNotNullParameter(kotlinType0, "type");
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "context");
        KotlinType kotlinType1 = SignatureEnhancement.enhance$default(this, new SignatureParts(null, false, lazyJavaResolverContext0, AnnotationQualifierApplicabilityType.TYPE_USE, true), kotlinType0, CollectionsKt.emptyList(), null, false, 12, null);
        return kotlinType1 == null ? kotlinType0 : kotlinType1;
    }

    public final List enhanceTypeParameterBounds(TypeParameterDescriptor typeParameterDescriptor0, List list0, LazyJavaResolverContext lazyJavaResolverContext0) {
        Intrinsics.checkNotNullParameter(typeParameterDescriptor0, "typeParameter");
        Intrinsics.checkNotNullParameter(list0, "bounds");
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "context");
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            KotlinType kotlinType0 = (KotlinType)object0;
            if(!TypeUtilsKt.contains(kotlinType0, kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.enhanceTypeParameterBounds.1.1.INSTANCE)) {
                KotlinType kotlinType1 = SignatureEnhancement.enhance$default(this, new SignatureParts(typeParameterDescriptor0, false, lazyJavaResolverContext0, AnnotationQualifierApplicabilityType.TYPE_PARAMETER_BOUNDS, false, 16, null), kotlinType0, CollectionsKt.emptyList(), null, false, 12, null);
                if(kotlinType1 != null) {
                    kotlinType0 = kotlinType1;
                }
            }
            arrayList0.add(kotlinType0);
        }
        return arrayList0;

        final class kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.enhanceTypeParameterBounds.1.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.enhanceTypeParameterBounds.1.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.enhanceTypeParameterBounds.1.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.enhanceTypeParameterBounds.1.1();
            }

            kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement.enhanceTypeParameterBounds.1.1() {
                super(1);
            }

            public final Boolean invoke(UnwrappedType unwrappedType0) {
                Intrinsics.checkNotNullParameter(unwrappedType0, "it");
                return Boolean.valueOf(unwrappedType0 instanceof RawType);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((UnwrappedType)object0));
            }
        }

    }

    private final KotlinType enhanceValueParameter(CallableMemberDescriptor callableMemberDescriptor0, ValueParameterDescriptor valueParameterDescriptor0, LazyJavaResolverContext lazyJavaResolverContext0, TypeEnhancementInfo typeEnhancementInfo0, boolean z, Function1 function10) {
        if(valueParameterDescriptor0 != null) {
            LazyJavaResolverContext lazyJavaResolverContext1 = ContextKt.copyWithNewDefaultTypeQualifiers(lazyJavaResolverContext0, valueParameterDescriptor0.getAnnotations());
            return lazyJavaResolverContext1 == null ? this.enhance(callableMemberDescriptor0, valueParameterDescriptor0, false, lazyJavaResolverContext0, AnnotationQualifierApplicabilityType.VALUE_PARAMETER, typeEnhancementInfo0, z, function10) : this.enhance(callableMemberDescriptor0, valueParameterDescriptor0, false, lazyJavaResolverContext1, AnnotationQualifierApplicabilityType.VALUE_PARAMETER, typeEnhancementInfo0, z, function10);
        }
        return this.enhance(callableMemberDescriptor0, null, false, lazyJavaResolverContext0, AnnotationQualifierApplicabilityType.VALUE_PARAMETER, typeEnhancementInfo0, z, function10);
    }

    private final Annotations getDefaultAnnotations(CallableMemberDescriptor callableMemberDescriptor0, LazyJavaResolverContext lazyJavaResolverContext0) {
        List list0 = null;
        ClassifierDescriptor classifierDescriptor0 = DescriptorUtilKt.getTopLevelContainingClassifier(callableMemberDescriptor0);
        if(classifierDescriptor0 == null) {
            return callableMemberDescriptor0.getAnnotations();
        }
        LazyJavaClassDescriptor lazyJavaClassDescriptor0 = classifierDescriptor0 instanceof LazyJavaClassDescriptor ? ((LazyJavaClassDescriptor)classifierDescriptor0) : null;
        if(lazyJavaClassDescriptor0 != null) {
            list0 = lazyJavaClassDescriptor0.getModuleAnnotations();
        }
        if(list0 != null && !list0.isEmpty()) {
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
            for(Object object0: list0) {
                arrayList0.add(new LazyJavaAnnotationDescriptor(lazyJavaResolverContext0, ((JavaAnnotation)object0), true));
            }
            List list1 = CollectionsKt.plus(callableMemberDescriptor0.getAnnotations(), arrayList0);
            return Annotations.Companion.create(list1);
        }
        return callableMemberDescriptor0.getAnnotations();
    }
}

