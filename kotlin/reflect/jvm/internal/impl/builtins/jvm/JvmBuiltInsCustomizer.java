package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor.Kind;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.FindClassInModuleKt;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModalityUtilsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilterKt;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureBuildingUtilsKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.SignatureBuildingComponents;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.Function;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.OverrideCompatibilityInfo.Result;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.NameResolverUtilKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedClassDescriptor;
import kotlin.reflect.jvm.internal.impl.storage.CacheWithNotNullValues;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.LazyWrappedType;
import kotlin.reflect.jvm.internal.impl.types.SimpleType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;
import kotlin.reflect.jvm.internal.impl.utils.DFS.AbstractNodeHandler;
import kotlin.reflect.jvm.internal.impl.utils.DFS;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet.Companion;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;

public final class JvmBuiltInsCustomizer implements AdditionalClassPartsProvider, PlatformDependentDeclarationFilter {
    static enum JDKMemberStatus {
        HIDDEN,
        VISIBLE,
        NOT_CONSIDERED,
        DROP;

        private static final JDKMemberStatus[] $values() [...] // Inlined contents
    }

    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[JDKMemberStatus.values().length];
            try {
                arr_v[JDKMemberStatus.HIDDEN.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[JDKMemberStatus.NOT_CONSIDERED.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[JDKMemberStatus.DROP.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[JDKMemberStatus.VISIBLE.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    static final KProperty[] $$delegatedProperties;
    private final NotNullLazyValue cloneableType$delegate;
    private final JavaToKotlinClassMapper j2kClassMapper;
    private final CacheWithNotNullValues javaAnalogueClassesWithCustomSupertypeCache;
    private final KotlinType mockSerializableType;
    private final ModuleDescriptor moduleDescriptor;
    private final NotNullLazyValue notConsideredDeprecation$delegate;
    private final NotNullLazyValue settings$delegate;

    static {
        JvmBuiltInsCustomizer.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmBuiltInsCustomizer.class), "settings", "getSettings()Lorg/jetbrains/kotlin/builtins/jvm/JvmBuiltIns$Settings;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmBuiltInsCustomizer.class), "cloneableType", "getCloneableType()Lorg/jetbrains/kotlin/types/SimpleType;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmBuiltInsCustomizer.class), "notConsideredDeprecation", "getNotConsideredDeprecation()Lorg/jetbrains/kotlin/descriptors/annotations/Annotations;"))};
    }

    public JvmBuiltInsCustomizer(ModuleDescriptor moduleDescriptor0, StorageManager storageManager0, Function0 function00) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "moduleDescriptor");
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(function00, "settingsComputation");
        super();
        this.moduleDescriptor = moduleDescriptor0;
        this.j2kClassMapper = JavaToKotlinClassMapper.INSTANCE;
        this.settings$delegate = storageManager0.createLazyValue(function00);
        this.mockSerializableType = this.createMockJavaIoSerializableType(storageManager0);
        this.cloneableType$delegate = storageManager0.createLazyValue(new Function0(storageManager0) {
            final StorageManager $storageManager;

            {
                JvmBuiltInsCustomizer.this = jvmBuiltInsCustomizer0;
                this.$storageManager = storageManager0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final SimpleType invoke() {
                ModuleDescriptor moduleDescriptor0 = JvmBuiltInsCustomizer.access$getSettings(JvmBuiltInsCustomizer.this).getOwnerModuleDescriptor();
                ClassId classId0 = JvmBuiltInClassDescriptorFactory.Companion.getCLONEABLE_CLASS_ID();
                ModuleDescriptor moduleDescriptor1 = JvmBuiltInsCustomizer.access$getSettings(JvmBuiltInsCustomizer.this).getOwnerModuleDescriptor();
                return FindClassInModuleKt.findNonGenericClassAcrossDependencies(moduleDescriptor0, classId0, new NotFoundClasses(this.$storageManager, moduleDescriptor1)).getDefaultType();
            }
        });
        this.javaAnalogueClassesWithCustomSupertypeCache = storageManager0.createCacheWithNotNullValues();
        this.notConsideredDeprecation$delegate = storageManager0.createLazyValue(new Function0() {
            {
                JvmBuiltInsCustomizer.this = jvmBuiltInsCustomizer0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Annotations invoke() {
                List list0 = CollectionsKt.listOf(AnnotationUtilKt.createDeprecatedAnnotation$default(JvmBuiltInsCustomizer.access$getModuleDescriptor$p(JvmBuiltInsCustomizer.this).getBuiltIns(), "This member is not fully supported by Kotlin compiler, so it may be absent or have different signature in next major version", null, null, 6, null));
                return Annotations.Companion.create(list0);
            }
        });
    }

    public static final Settings access$getSettings(JvmBuiltInsCustomizer jvmBuiltInsCustomizer0) {
        return jvmBuiltInsCustomizer0.getSettings();
    }

    // 检测为 Lambda 实现
    static Iterable accessor$JvmBuiltInsCustomizer$lambda0(CallableMemberDescriptor callableMemberDescriptor0) [...]

    // 检测为 Lambda 实现
    static Iterable accessor$JvmBuiltInsCustomizer$lambda1(JvmBuiltInsCustomizer jvmBuiltInsCustomizer0, ClassDescriptor classDescriptor0) [...]

    private final SimpleFunctionDescriptor createCloneForArray(DeserializedClassDescriptor deserializedClassDescriptor0, SimpleFunctionDescriptor simpleFunctionDescriptor0) {
        CopyBuilder functionDescriptor$CopyBuilder0 = simpleFunctionDescriptor0.newCopyBuilder();
        functionDescriptor$CopyBuilder0.setOwner(deserializedClassDescriptor0);
        functionDescriptor$CopyBuilder0.setVisibility(DescriptorVisibilities.PUBLIC);
        functionDescriptor$CopyBuilder0.setReturnType(deserializedClassDescriptor0.getDefaultType());
        functionDescriptor$CopyBuilder0.setDispatchReceiverParameter(deserializedClassDescriptor0.getThisAsReceiverParameter());
        FunctionDescriptor functionDescriptor0 = functionDescriptor$CopyBuilder0.build();
        Intrinsics.checkNotNull(functionDescriptor0);
        return (SimpleFunctionDescriptor)functionDescriptor0;
    }

    private final KotlinType createMockJavaIoSerializableType(StorageManager storageManager0) {
        FqName fqName0 = new FqName("java.io");
        kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsCustomizer.createMockJavaIoSerializableType.mockJavaIoPackageFragment.1 jvmBuiltInsCustomizer$createMockJavaIoSerializableType$mockJavaIoPackageFragment$10 = new PackageFragmentDescriptorImpl(fqName0/*ERROR_MISSING_ARG_1/*) {
            public Empty getMemberScope() {
                return Empty.INSTANCE;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor
            public MemberScope getMemberScope() {
                return this.getMemberScope();
            }
        };
        List list0 = CollectionsKt.listOf(new LazyWrappedType(storageManager0, new Function0() {
            {
                JvmBuiltInsCustomizer.this = jvmBuiltInsCustomizer0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final KotlinType invoke() {
                SimpleType simpleType0 = JvmBuiltInsCustomizer.this.moduleDescriptor.getBuiltIns().getAnyType();
                Intrinsics.checkNotNullExpressionValue(simpleType0, "moduleDescriptor.builtIns.anyType");
                return simpleType0;
            }
        }));
        ClassDescriptorImpl classDescriptorImpl0 = new ClassDescriptorImpl(jvmBuiltInsCustomizer$createMockJavaIoSerializableType$mockJavaIoPackageFragment$10, Name.identifier("Serializable"), Modality.ABSTRACT, ClassKind.INTERFACE, list0, SourceElement.NO_SOURCE, false, storageManager0);
        Set set0 = SetsKt.emptySet();
        classDescriptorImpl0.initialize(Empty.INSTANCE, set0, null);
        SimpleType simpleType0 = classDescriptorImpl0.getDefaultType();
        Intrinsics.checkNotNullExpressionValue(simpleType0, "mockSerializableClass.defaultType");
        return simpleType0;
    }

    private final Collection getAdditionalFunctions(ClassDescriptor classDescriptor0, Function1 function10) {
        LazyJavaClassDescriptor lazyJavaClassDescriptor0 = this.getJavaAnalogue(classDescriptor0);
        if(lazyJavaClassDescriptor0 == null) {
            return CollectionsKt.emptyList();
        }
        FqName fqName0 = DescriptorUtilsKt.getFqNameSafe(lazyJavaClassDescriptor0);
        Iterable iterable0 = this.j2kClassMapper.mapPlatformClass(fqName0, FallbackBuiltIns.Companion.getInstance());
        ClassDescriptor classDescriptor1 = (ClassDescriptor)CollectionsKt.lastOrNull(iterable0);
        if(classDescriptor1 == null) {
            return CollectionsKt.emptyList();
        }
        Companion smartSet$Companion0 = SmartSet.Companion;
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            arrayList0.add(DescriptorUtilsKt.getFqNameSafe(((ClassDescriptor)object0)));
        }
        SmartSet smartSet0 = smartSet$Companion0.create(arrayList0);
        boolean z = this.j2kClassMapper.isMutable(classDescriptor0);
        FqName fqName1 = DescriptorUtilsKt.getFqNameSafe(lazyJavaClassDescriptor0);
        Function0 function00 = new Function0(classDescriptor1) {
            final LazyJavaClassDescriptor $javaAnalogueDescriptor;
            final ClassDescriptor $kotlinMutableClassIfContainer;

            {
                this.$javaAnalogueDescriptor = lazyJavaClassDescriptor0;
                this.$kotlinMutableClassIfContainer = classDescriptor0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final ClassDescriptor invoke() {
                Intrinsics.checkNotNullExpressionValue(JavaResolverCache.EMPTY, "EMPTY");
                return this.$javaAnalogueDescriptor.copy$descriptors_jvm(JavaResolverCache.EMPTY, this.$kotlinMutableClassIfContainer);
            }
        };
        MemberScope memberScope0 = ((ClassDescriptor)this.javaAnalogueClassesWithCustomSupertypeCache.computeIfAbsent(fqName1, function00)).getUnsubstitutedMemberScope();
        Intrinsics.checkNotNullExpressionValue(memberScope0, "fakeJavaClassDescriptor.unsubstitutedMemberScope");
        Iterable iterable1 = (Iterable)function10.invoke(memberScope0);
        Collection collection0 = new ArrayList();
        Iterator iterator1 = iterable1.iterator();
    label_24:
        while(iterator1.hasNext()) {
            Object object1 = iterator1.next();
            SimpleFunctionDescriptor simpleFunctionDescriptor0 = (SimpleFunctionDescriptor)object1;
            if(simpleFunctionDescriptor0.getKind() == Kind.DECLARATION && simpleFunctionDescriptor0.getVisibility().isPublicAPI() && !KotlinBuiltIns.isDeprecated(simpleFunctionDescriptor0)) {
                Collection collection1 = simpleFunctionDescriptor0.getOverriddenDescriptors();
                Intrinsics.checkNotNullExpressionValue(collection1, "analogueMember.overriddenDescriptors");
                Iterable iterable2 = collection1;
                if(!(iterable2 instanceof Collection) || !((Collection)iterable2).isEmpty()) {
                    for(Object object2: iterable2) {
                        DeclarationDescriptor declarationDescriptor0 = ((FunctionDescriptor)object2).getContainingDeclaration();
                        Intrinsics.checkNotNullExpressionValue(declarationDescriptor0, "it.containingDeclaration");
                        if(smartSet0.contains(DescriptorUtilsKt.getFqNameSafe(declarationDescriptor0))) {
                            continue label_24;
                        }
                        if(false) {
                            break;
                        }
                    }
                }
                if(!this.isMutabilityViolation(simpleFunctionDescriptor0, z)) {
                    collection0.add(object1);
                }
            }
        }
        return (List)collection0;
    }

    private final SimpleType getCloneableType() {
        return (SimpleType)StorageKt.getValue(this.cloneableType$delegate, this, JvmBuiltInsCustomizer.$$delegatedProperties[1]);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider
    public Collection getConstructors(ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "classDescriptor");
        if(classDescriptor0.getKind() == ClassKind.CLASS && this.getSettings().isAdditionalBuiltInsFeatureSupported()) {
            LazyJavaClassDescriptor lazyJavaClassDescriptor0 = this.getJavaAnalogue(classDescriptor0);
            if(lazyJavaClassDescriptor0 == null) {
                return CollectionsKt.emptyList();
            }
            FqName fqName0 = DescriptorUtilsKt.getFqNameSafe(lazyJavaClassDescriptor0);
            ClassDescriptor classDescriptor1 = JavaToKotlinClassMapper.mapJavaToKotlin$default(this.j2kClassMapper, fqName0, FallbackBuiltIns.Companion.getInstance(), null, 4, null);
            if(classDescriptor1 == null) {
                return CollectionsKt.emptyList();
            }
            TypeSubstitutor typeSubstitutor0 = MappingUtilKt.createMappedTypeParametersSubstitution(classDescriptor1, lazyJavaClassDescriptor0).buildSubstitutor();
            Iterable iterable0 = lazyJavaClassDescriptor0.getConstructors();
            Collection collection0 = new ArrayList();
            Iterator iterator0 = iterable0.iterator();
        label_13:
            while(iterator0.hasNext()) {
                Object object0 = iterator0.next();
                ClassConstructorDescriptor classConstructorDescriptor0 = (ClassConstructorDescriptor)object0;
                if(classConstructorDescriptor0.getVisibility().isPublicAPI()) {
                    Collection collection1 = classDescriptor1.getConstructors();
                    Intrinsics.checkNotNullExpressionValue(collection1, "defaultKotlinVersion.constructors");
                    Iterable iterable1 = collection1;
                    if(!(iterable1 instanceof Collection) || !((Collection)iterable1).isEmpty()) {
                        for(Object object1: iterable1) {
                            Intrinsics.checkNotNullExpressionValue(((ClassConstructorDescriptor)object1), "it");
                            if(JvmBuiltInsCustomizer.getConstructors$isEffectivelyTheSameAs(((ClassConstructorDescriptor)object1), typeSubstitutor0, classConstructorDescriptor0)) {
                                continue label_13;
                            }
                            if(false) {
                                break;
                            }
                        }
                    }
                    if(!this.isTrivialCopyConstructorFor(classConstructorDescriptor0, classDescriptor0) && !KotlinBuiltIns.isDeprecated(classConstructorDescriptor0)) {
                        String s = MethodSignatureMappingKt.computeJvmDescriptor$default(classConstructorDescriptor0, false, false, 3, null);
                        if(!JvmBuiltInsSignatures.INSTANCE.getHIDDEN_CONSTRUCTOR_SIGNATURES().contains(MethodSignatureBuildingUtilsKt.signature(SignatureBuildingComponents.INSTANCE, lazyJavaClassDescriptor0, s))) {
                            collection0.add(object0);
                        }
                    }
                }
            }
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(((List)collection0), 10));
            for(Object object2: ((List)collection0)) {
                CopyBuilder functionDescriptor$CopyBuilder0 = ((ClassConstructorDescriptor)object2).newCopyBuilder();
                functionDescriptor$CopyBuilder0.setOwner(classDescriptor0);
                functionDescriptor$CopyBuilder0.setReturnType(classDescriptor0.getDefaultType());
                functionDescriptor$CopyBuilder0.setPreserveSourceElement();
                functionDescriptor$CopyBuilder0.setSubstitution(typeSubstitutor0.getSubstitution());
                String s1 = MethodSignatureMappingKt.computeJvmDescriptor$default(((ClassConstructorDescriptor)object2), false, false, 3, null);
                if(!JvmBuiltInsSignatures.INSTANCE.getVISIBLE_CONSTRUCTOR_SIGNATURES().contains(MethodSignatureBuildingUtilsKt.signature(SignatureBuildingComponents.INSTANCE, lazyJavaClassDescriptor0, s1))) {
                    functionDescriptor$CopyBuilder0.setAdditionalAnnotations(this.getNotConsideredDeprecation());
                }
                FunctionDescriptor functionDescriptor0 = functionDescriptor$CopyBuilder0.build();
                Intrinsics.checkNotNull(functionDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassConstructorDescriptor");
                arrayList0.add(((ClassConstructorDescriptor)functionDescriptor0));
            }
            return arrayList0;
        }
        return CollectionsKt.emptyList();
    }

    private static final boolean getConstructors$isEffectivelyTheSameAs(ConstructorDescriptor constructorDescriptor0, TypeSubstitutor typeSubstitutor0, ConstructorDescriptor constructorDescriptor1) {
        return OverridingUtil.getBothWaysOverridability(constructorDescriptor0, constructorDescriptor1.substitute(typeSubstitutor0)) == Result.OVERRIDABLE;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider
    public Collection getFunctions(Name name0, ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(classDescriptor0, "classDescriptor");
        if(Intrinsics.areEqual(name0, CloneableClassScope.Companion.getCLONE_NAME()) && classDescriptor0 instanceof DeserializedClassDescriptor && KotlinBuiltIns.isArrayOrPrimitiveArray(classDescriptor0)) {
            List list0 = ((DeserializedClassDescriptor)classDescriptor0).getClassProto().getFunctionList();
            Intrinsics.checkNotNullExpressionValue(list0, "classDescriptor.classProto.functionList");
            if(!(list0 instanceof Collection) || !list0.isEmpty()) {
                for(Object object0: list0) {
                    if(Intrinsics.areEqual(NameResolverUtilKt.getName(((DeserializedClassDescriptor)classDescriptor0).getC().getNameResolver(), ((Function)object0).getName()), CloneableClassScope.Companion.getCLONE_NAME())) {
                        return CollectionsKt.emptyList();
                    }
                    if(false) {
                        break;
                    }
                }
            }
            return CollectionsKt.listOf(this.createCloneForArray(((DeserializedClassDescriptor)classDescriptor0), ((SimpleFunctionDescriptor)CollectionsKt.single(this.getCloneableType().getMemberScope().getContributedFunctions(name0, NoLookupLocation.FROM_BUILTINS)))));
        }
        if(!this.getSettings().isAdditionalBuiltInsFeatureSupported()) {
            return CollectionsKt.emptyList();
        }
        Iterable iterable0 = this.getAdditionalFunctions(classDescriptor0, new Function1() {
            final Name $name;

            {
                this.$name = name0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((MemberScope)object0));
            }

            public final Collection invoke(MemberScope memberScope0) {
                Intrinsics.checkNotNullParameter(memberScope0, "it");
                return memberScope0.getContributedFunctions(this.$name, NoLookupLocation.FROM_BUILTINS);
            }
        });
        Collection collection0 = new ArrayList();
        for(Object object1: iterable0) {
            DeclarationDescriptor declarationDescriptor0 = ((SimpleFunctionDescriptor)object1).getContainingDeclaration();
            Intrinsics.checkNotNull(declarationDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
            FunctionDescriptor functionDescriptor0 = ((SimpleFunctionDescriptor)object1).substitute(MappingUtilKt.createMappedTypeParametersSubstitution(((ClassDescriptor)declarationDescriptor0), classDescriptor0).buildSubstitutor());
            Intrinsics.checkNotNull(functionDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor");
            CopyBuilder functionDescriptor$CopyBuilder0 = ((SimpleFunctionDescriptor)functionDescriptor0).newCopyBuilder();
            functionDescriptor$CopyBuilder0.setOwner(classDescriptor0);
            functionDescriptor$CopyBuilder0.setDispatchReceiverParameter(classDescriptor0.getThisAsReceiverParameter());
            functionDescriptor$CopyBuilder0.setPreserveSourceElement();
            SimpleFunctionDescriptor simpleFunctionDescriptor0 = null;
            switch(WhenMappings.$EnumSwitchMapping$0[this.getJdkMethodStatus(((SimpleFunctionDescriptor)object1)).ordinal()]) {
                case 1: {
                    if(!ModalityUtilsKt.isFinalClass(classDescriptor0)) {
                        functionDescriptor$CopyBuilder0.setHiddenForResolutionEverywhereBesideSupercalls();
                        goto label_35;
                    }
                    break;
                }
                case 2: {
                    functionDescriptor$CopyBuilder0.setAdditionalAnnotations(this.getNotConsideredDeprecation());
                label_35:
                    FunctionDescriptor functionDescriptor1 = functionDescriptor$CopyBuilder0.build();
                    Intrinsics.checkNotNull(functionDescriptor1);
                    simpleFunctionDescriptor0 = (SimpleFunctionDescriptor)functionDescriptor1;
                    break;
                }
                case 3: {
                    break;
                }
                default: {
                    goto label_35;
                }
            }
            if(simpleFunctionDescriptor0 != null) {
                collection0.add(simpleFunctionDescriptor0);
            }
        }
        return (List)collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider
    public Collection getFunctionsNames(ClassDescriptor classDescriptor0) {
        return this.getFunctionsNames(classDescriptor0);
    }

    public Set getFunctionsNames(ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "classDescriptor");
        if(!this.getSettings().isAdditionalBuiltInsFeatureSupported()) {
            return SetsKt.emptySet();
        }
        LazyJavaClassDescriptor lazyJavaClassDescriptor0 = this.getJavaAnalogue(classDescriptor0);
        if(lazyJavaClassDescriptor0 != null) {
            LazyJavaClassMemberScope lazyJavaClassMemberScope0 = lazyJavaClassDescriptor0.getUnsubstitutedMemberScope();
            if(lazyJavaClassMemberScope0 != null) {
                Set set0 = lazyJavaClassMemberScope0.getFunctionNames();
                return set0 == null ? SetsKt.emptySet() : set0;
            }
        }
        return SetsKt.emptySet();
    }

    private final LazyJavaClassDescriptor getJavaAnalogue(ClassDescriptor classDescriptor0) {
        if(KotlinBuiltIns.isAny(classDescriptor0)) {
            return null;
        }
        if(!KotlinBuiltIns.isUnderKotlinPackage(classDescriptor0)) {
            return null;
        }
        FqNameUnsafe fqNameUnsafe0 = DescriptorUtilsKt.getFqNameUnsafe(classDescriptor0);
        if(!fqNameUnsafe0.isSafe()) {
            return null;
        }
        ClassId classId0 = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(fqNameUnsafe0);
        if(classId0 != null) {
            FqName fqName0 = classId0.asSingleFqName();
            if(fqName0 != null) {
                ClassDescriptor classDescriptor1 = DescriptorUtilKt.resolveClassByFqName(this.getSettings().getOwnerModuleDescriptor(), fqName0, NoLookupLocation.FROM_BUILTINS);
                return classDescriptor1 instanceof LazyJavaClassDescriptor ? ((LazyJavaClassDescriptor)classDescriptor1) : null;
            }
        }
        return null;
    }

    private final JDKMemberStatus getJdkMethodStatus(FunctionDescriptor functionDescriptor0) {
        DeclarationDescriptor declarationDescriptor0 = functionDescriptor0.getContainingDeclaration();
        Intrinsics.checkNotNull(declarationDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        String s = MethodSignatureMappingKt.computeJvmDescriptor$default(functionDescriptor0, false, false, 3, null);
        ObjectRef ref$ObjectRef0 = new ObjectRef();
        Object object0 = DFS.dfs(CollectionsKt.listOf(((ClassDescriptor)declarationDescriptor0)), (ClassDescriptor classDescriptor0) -> JvmBuiltInsCustomizer.getJdkMethodStatus$lambda$9(this, classDescriptor0), new AbstractNodeHandler() {
            @Override  // kotlin.reflect.jvm.internal.impl.utils.DFS$AbstractNodeHandler
            public boolean beforeChildren(Object object0) {
                return this.beforeChildren(((ClassDescriptor)object0));
            }

            public boolean beforeChildren(ClassDescriptor classDescriptor0) {
                Intrinsics.checkNotNullParameter(classDescriptor0, "javaClassDescriptor");
                String s = MethodSignatureBuildingUtilsKt.signature(SignatureBuildingComponents.INSTANCE, classDescriptor0, ref$ObjectRef0);
                if(JvmBuiltInsSignatures.INSTANCE.getHIDDEN_METHOD_SIGNATURES().contains(s)) {
                    this.$result.element = JDKMemberStatus.HIDDEN;
                    return this.$result.element == null;
                }
                if(JvmBuiltInsSignatures.INSTANCE.getVISIBLE_METHOD_SIGNATURES().contains(s)) {
                    this.$result.element = JDKMemberStatus.VISIBLE;
                    return this.$result.element == null;
                }
                if(JvmBuiltInsSignatures.INSTANCE.getDROP_LIST_METHOD_SIGNATURES().contains(s)) {
                    this.$result.element = JDKMemberStatus.DROP;
                }
                return this.$result.element == null;
            }

            @Override  // kotlin.reflect.jvm.internal.impl.utils.DFS$NodeHandler
            public Object result() {
                return this.result();
            }

            public JDKMemberStatus result() {
                JDKMemberStatus jvmBuiltInsCustomizer$JDKMemberStatus0 = (JDKMemberStatus)this.$result.element;
                return jvmBuiltInsCustomizer$JDKMemberStatus0 == null ? JDKMemberStatus.NOT_CONSIDERED : jvmBuiltInsCustomizer$JDKMemberStatus0;
            }
        });
        Intrinsics.checkNotNullExpressionValue(object0, "jvmDescriptor = computeJ…CONSIDERED\n            })");
        return (JDKMemberStatus)object0;
    }

    private static final Iterable getJdkMethodStatus$lambda$9(JvmBuiltInsCustomizer jvmBuiltInsCustomizer0, ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(jvmBuiltInsCustomizer0, "this$0");
        Collection collection0 = classDescriptor0.getTypeConstructor().getSupertypes();
        Intrinsics.checkNotNullExpressionValue(collection0, "it.typeConstructor.supertypes");
        Collection collection1 = new ArrayList();
        for(Object object0: collection0) {
            ClassifierDescriptor classifierDescriptor0 = ((KotlinType)object0).getConstructor().getDeclarationDescriptor();
            LazyJavaClassDescriptor lazyJavaClassDescriptor0 = null;
            ClassifierDescriptor classifierDescriptor1 = classifierDescriptor0 == null ? null : classifierDescriptor0.getOriginal();
            ClassDescriptor classDescriptor1 = classifierDescriptor1 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor1) : null;
            if(classDescriptor1 != null) {
                lazyJavaClassDescriptor0 = jvmBuiltInsCustomizer0.getJavaAnalogue(classDescriptor1);
            }
            if(lazyJavaClassDescriptor0 != null) {
                collection1.add(lazyJavaClassDescriptor0);
            }
        }
        return (List)collection1;
    }

    private final Annotations getNotConsideredDeprecation() {
        return (Annotations)StorageKt.getValue(this.notConsideredDeprecation$delegate, this, JvmBuiltInsCustomizer.$$delegatedProperties[2]);
    }

    private final Settings getSettings() {
        return (Settings)StorageKt.getValue(this.settings$delegate, this, JvmBuiltInsCustomizer.$$delegatedProperties[0]);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider
    public Collection getSupertypes(ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "classDescriptor");
        FqNameUnsafe fqNameUnsafe0 = DescriptorUtilsKt.getFqNameUnsafe(classDescriptor0);
        if(JvmBuiltInsSignatures.INSTANCE.isArrayOrPrimitiveArray(fqNameUnsafe0)) {
            KotlinType[] arr_kotlinType = new KotlinType[2];
            SimpleType simpleType0 = this.getCloneableType();
            Intrinsics.checkNotNullExpressionValue(simpleType0, "cloneableType");
            arr_kotlinType[0] = simpleType0;
            arr_kotlinType[1] = this.mockSerializableType;
            return CollectionsKt.listOf(arr_kotlinType);
        }
        return JvmBuiltInsSignatures.INSTANCE.isSerializableInJava(fqNameUnsafe0) ? CollectionsKt.listOf(this.mockSerializableType) : CollectionsKt.emptyList();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter
    public boolean isFunctionAvailable(ClassDescriptor classDescriptor0, SimpleFunctionDescriptor simpleFunctionDescriptor0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "classDescriptor");
        Intrinsics.checkNotNullParameter(simpleFunctionDescriptor0, "functionDescriptor");
        LazyJavaClassDescriptor lazyJavaClassDescriptor0 = this.getJavaAnalogue(classDescriptor0);
        if(lazyJavaClassDescriptor0 == null) {
            return true;
        }
        if(!simpleFunctionDescriptor0.getAnnotations().hasAnnotation(PlatformDependentDeclarationFilterKt.getPLATFORM_DEPENDENT_ANNOTATION_FQ_NAME())) {
            return true;
        }
        if(!this.getSettings().isAdditionalBuiltInsFeatureSupported()) {
            return false;
        }
        String s = MethodSignatureMappingKt.computeJvmDescriptor$default(simpleFunctionDescriptor0, false, false, 3, null);
        LazyJavaClassMemberScope lazyJavaClassMemberScope0 = lazyJavaClassDescriptor0.getUnsubstitutedMemberScope();
        Name name0 = simpleFunctionDescriptor0.getName();
        Intrinsics.checkNotNullExpressionValue(name0, "functionDescriptor.name");
        Iterable iterable0 = lazyJavaClassMemberScope0.getContributedFunctions(name0, NoLookupLocation.FROM_BUILTINS);
        if(iterable0 instanceof Collection && ((Collection)iterable0).isEmpty()) {
            return false;
        }
        for(Object object0: iterable0) {
            if(Intrinsics.areEqual(MethodSignatureMappingKt.computeJvmDescriptor$default(((SimpleFunctionDescriptor)object0), false, false, 3, null), s)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    private final boolean isMutabilityViolation(SimpleFunctionDescriptor simpleFunctionDescriptor0, boolean z) {
        DeclarationDescriptor declarationDescriptor0 = simpleFunctionDescriptor0.getContainingDeclaration();
        Intrinsics.checkNotNull(declarationDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        String s = MethodSignatureMappingKt.computeJvmDescriptor$default(simpleFunctionDescriptor0, false, false, 3, null);
        if((z ^ JvmBuiltInsSignatures.INSTANCE.getMUTABLE_METHOD_SIGNATURES().contains(MethodSignatureBuildingUtilsKt.signature(SignatureBuildingComponents.INSTANCE, ((ClassDescriptor)declarationDescriptor0), s))) != 0) {
            return true;
        }
        Collection collection0 = CollectionsKt.listOf(simpleFunctionDescriptor0);
        Function1 function10 = new Function1() {
            {
                JvmBuiltInsCustomizer.this = jvmBuiltInsCustomizer0;
                super(1);
            }

            public final Boolean invoke(CallableMemberDescriptor callableMemberDescriptor0) {
                if(callableMemberDescriptor0.getKind() == Kind.DECLARATION) {
                    DeclarationDescriptor declarationDescriptor0 = callableMemberDescriptor0.getContainingDeclaration();
                    Intrinsics.checkNotNull(declarationDescriptor0, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
                    return JvmBuiltInsCustomizer.this.j2kClassMapper.isMutable(((ClassDescriptor)declarationDescriptor0));
                }
                return false;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((CallableMemberDescriptor)object0));
            }
        };
        Boolean boolean0 = DFS.ifAny(collection0, (CallableMemberDescriptor callableMemberDescriptor0) -> JvmBuiltInsCustomizer.isMutabilityViolation$lambda$7(callableMemberDescriptor0), function10);
        Intrinsics.checkNotNullExpressionValue(boolean0, "private fun SimpleFuncti…scriptor)\n        }\n    }");
        return boolean0.booleanValue();
    }

    private static final Iterable isMutabilityViolation$lambda$7(CallableMemberDescriptor callableMemberDescriptor0) {
        return callableMemberDescriptor0.getOriginal().getOverriddenDescriptors();
    }

    private final boolean isTrivialCopyConstructorFor(ConstructorDescriptor constructorDescriptor0, ClassDescriptor classDescriptor0) {
        if(constructorDescriptor0.getValueParameters().size() == 1) {
            List list0 = constructorDescriptor0.getValueParameters();
            Intrinsics.checkNotNullExpressionValue(list0, "valueParameters");
            ClassifierDescriptor classifierDescriptor0 = ((ValueParameterDescriptor)CollectionsKt.single(list0)).getType().getConstructor().getDeclarationDescriptor();
            return Intrinsics.areEqual((classifierDescriptor0 == null ? null : DescriptorUtilsKt.getFqNameUnsafe(classifierDescriptor0)), DescriptorUtilsKt.getFqNameUnsafe(classDescriptor0));
        }
        return false;
    }
}

