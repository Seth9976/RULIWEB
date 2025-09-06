package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor.CopyBuilder;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyGetterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassConstructorDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.EnumEntrySyntheticClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyGetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertySetterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.SimpleFunctionDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithDifferentJvmName;
import kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature;
import kotlin.reflect.jvm.internal.impl.load.java.ClassicBuiltinSpecialProperties;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder.Request;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder;
import kotlin.reflect.jvm.internal.impl.load.java.JavaDescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.load.java.JavaIncompatibilityRulesOverridabilityCondition;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAbi;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.java.PropertiesConventionUtilKt;
import kotlin.reflect.jvm.internal.impl.load.java.SpecialBuiltinMembers;
import kotlin.reflect.jvm.internal.impl.load.java.SpecialGenericSignatures;
import kotlin.reflect.jvm.internal.impl.load.java.UtilsKt;
import kotlin.reflect.jvm.internal.impl.load.java.components.DescriptorResolverUtils;
import kotlin.reflect.jvm.internal.impl.load.java.components.SignaturePropagator.PropagatedSignature;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaForKotlinOverridePropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.UtilKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotationsKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributes;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributesKt;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElementFactory;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaConstructor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMember;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaRecordComponent;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.load.java.typeEnhancement.SignatureEnhancement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil.OverrideCompatibilityInfo.Result;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtil;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeProjection;
import kotlin.reflect.jvm.internal.impl.types.TypeUsage;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker;
import kotlin.reflect.jvm.internal.impl.utils.SmartSet;

public final class LazyJavaClassMemberScope extends LazyJavaScope {
    private final NotNullLazyValue constructors;
    private final NotNullLazyValue enumEntryIndex;
    private final NotNullLazyValue generatedNestedClassNames;
    private final JavaClass jClass;
    private final NotNullLazyValue nestedClassIndex;
    private final MemoizedFunctionToNullable nestedClasses;
    private final ClassDescriptor ownerDescriptor;
    private final boolean skipRefinement;

    public LazyJavaClassMemberScope(LazyJavaResolverContext lazyJavaResolverContext0, ClassDescriptor classDescriptor0, JavaClass javaClass0, boolean z, LazyJavaClassMemberScope lazyJavaClassMemberScope0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "c");
        Intrinsics.checkNotNullParameter(classDescriptor0, "ownerDescriptor");
        Intrinsics.checkNotNullParameter(javaClass0, "jClass");
        super(lazyJavaResolverContext0, lazyJavaClassMemberScope0);
        this.ownerDescriptor = classDescriptor0;
        this.jClass = javaClass0;
        this.skipRefinement = z;
        this.constructors = lazyJavaResolverContext0.getStorageManager().createLazyValue(new Function0(lazyJavaResolverContext0) {
            final LazyJavaResolverContext $c;

            {
                LazyJavaClassMemberScope.this = lazyJavaClassMemberScope0;
                this.$c = lazyJavaResolverContext0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                Collection collection0 = LazyJavaClassMemberScope.access$getJClass$p(LazyJavaClassMemberScope.this).getConstructors();
                ArrayList arrayList0 = new ArrayList(collection0.size());
                for(Object object0: collection0) {
                    arrayList0.add(LazyJavaClassMemberScope.access$resolveConstructor(LazyJavaClassMemberScope.this, ((JavaConstructor)object0)));
                }
                if(LazyJavaClassMemberScope.access$getJClass$p(LazyJavaClassMemberScope.this).isRecord()) {
                    ClassConstructorDescriptor classConstructorDescriptor0 = LazyJavaClassMemberScope.access$createDefaultRecordConstructor(LazyJavaClassMemberScope.this);
                    String s = MethodSignatureMappingKt.computeJvmDescriptor$default(classConstructorDescriptor0, false, false, 2, null);
                    if(!(arrayList0 instanceof Collection) || !arrayList0.isEmpty()) {
                        for(Object object1: arrayList0) {
                            if(!Intrinsics.areEqual(MethodSignatureMappingKt.computeJvmDescriptor$default(((ClassConstructorDescriptor)object1), false, false, 2, null), s)) {
                                continue;
                            }
                            goto label_18;
                        }
                    }
                    arrayList0.add(classConstructorDescriptor0);
                    this.$c.getComponents().getJavaResolverCache().recordConstructor(LazyJavaClassMemberScope.access$getJClass$p(LazyJavaClassMemberScope.this), classConstructorDescriptor0);
                }
            label_18:
                this.$c.getComponents().getSyntheticPartsProvider().generateConstructors(this.$c, LazyJavaClassMemberScope.this.getOwnerDescriptor(), arrayList0);
                SignatureEnhancement signatureEnhancement0 = this.$c.getComponents().getSignatureEnhancement();
                LazyJavaResolverContext lazyJavaResolverContext0 = this.$c;
                Collection collection1 = arrayList0;
                LazyJavaClassMemberScope lazyJavaClassMemberScope0 = LazyJavaClassMemberScope.this;
                if(collection1.isEmpty()) {
                    collection1 = CollectionsKt.listOfNotNull(LazyJavaClassMemberScope.access$createDefaultConstructor(lazyJavaClassMemberScope0));
                }
                return CollectionsKt.toList(signatureEnhancement0.enhanceSignatures(lazyJavaResolverContext0, collection1));
            }
        });
        this.nestedClassIndex = lazyJavaResolverContext0.getStorageManager().createLazyValue(new Function0() {
            {
                LazyJavaClassMemberScope.this = lazyJavaClassMemberScope0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Set invoke() {
                return CollectionsKt.toSet(LazyJavaClassMemberScope.access$getJClass$p(LazyJavaClassMemberScope.this).getInnerClassNames());
            }
        });
        this.generatedNestedClassNames = lazyJavaResolverContext0.getStorageManager().createLazyValue(new Function0(this) {
            final LazyJavaResolverContext $c;

            {
                this.$c = lazyJavaResolverContext0;
                LazyJavaClassMemberScope.this = lazyJavaClassMemberScope0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Set invoke() {
                return CollectionsKt.toSet(this.$c.getComponents().getSyntheticPartsProvider().getNestedClassNames(this.$c, LazyJavaClassMemberScope.this.getOwnerDescriptor()));
            }
        });
        this.enumEntryIndex = lazyJavaResolverContext0.getStorageManager().createLazyValue(new Function0() {
            {
                LazyJavaClassMemberScope.this = lazyJavaClassMemberScope0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Map invoke() {
                Iterable iterable0 = LazyJavaClassMemberScope.access$getJClass$p(LazyJavaClassMemberScope.this).getFields();
                Collection collection0 = new ArrayList();
                for(Object object0: iterable0) {
                    if(((JavaField)object0).isEnumEntry()) {
                        collection0.add(object0);
                    }
                }
                Map map0 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(((List)collection0), 10)), 16));
                for(Object object1: ((List)collection0)) {
                    map0.put(((JavaField)object1).getName(), object1);
                }
                return map0;
            }
        });
        this.nestedClasses = lazyJavaResolverContext0.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1(lazyJavaResolverContext0) {
            final LazyJavaResolverContext $c;

            {
                LazyJavaClassMemberScope.this = lazyJavaClassMemberScope0;
                this.$c = lazyJavaResolverContext0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Name)object0));
            }

            public final ClassDescriptor invoke(Name name0) {
                Intrinsics.checkNotNullParameter(name0, "name");
                if(((Set)LazyJavaClassMemberScope.access$getNestedClassIndex$p(LazyJavaClassMemberScope.this).invoke()).contains(name0)) {
                    JavaClassFinder javaClassFinder0 = this.$c.getComponents().getFinder();
                    ClassId classId0 = DescriptorUtilsKt.getClassId(LazyJavaClassMemberScope.this.getOwnerDescriptor());
                    Intrinsics.checkNotNull(classId0);
                    ClassId classId1 = classId0.createNestedClassId(name0);
                    Intrinsics.checkNotNullExpressionValue(classId1, "ownerDescriptor.classId!…createNestedClassId(name)");
                    JavaClass javaClass0 = javaClassFinder0.findClass(new Request(classId1, null, LazyJavaClassMemberScope.access$getJClass$p(LazyJavaClassMemberScope.this), 2, null));
                    if(javaClass0 != null) {
                        LazyJavaClassDescriptor lazyJavaClassDescriptor0 = new LazyJavaClassDescriptor(this.$c, LazyJavaClassMemberScope.this.getOwnerDescriptor(), javaClass0, null, 8, null);
                        this.$c.getComponents().getJavaClassesTracker().reportClass(lazyJavaClassDescriptor0);
                        return lazyJavaClassDescriptor0;
                    }
                    return null;
                }
                if(((Set)LazyJavaClassMemberScope.access$getGeneratedNestedClassNames$p(LazyJavaClassMemberScope.this).invoke()).contains(name0)) {
                    List list0 = CollectionsKt.createListBuilder();
                    this.$c.getComponents().getSyntheticPartsProvider().generateNestedClass(this.$c, LazyJavaClassMemberScope.this.getOwnerDescriptor(), name0, list0);
                    List list1 = CollectionsKt.build(list0);
                    switch(list1.size()) {
                        case 0: {
                            return null;
                        }
                        case 1: {
                            return (ClassDescriptor)CollectionsKt.single(list1);
                        }
                        default: {
                            throw new IllegalStateException(("Multiple classes with same name are generated: " + list1).toString());
                        }
                    }
                }
                JavaField javaField0 = (JavaField)((Map)LazyJavaClassMemberScope.access$getEnumEntryIndex$p(LazyJavaClassMemberScope.this).invoke()).get(name0);
                if(javaField0 != null) {
                    NotNullLazyValue notNullLazyValue0 = this.$c.getStorageManager().createLazyValue(new Function0() {
                        {
                            LazyJavaClassMemberScope.this = lazyJavaClassMemberScope0;
                            super(0);
                        }

                        @Override  // kotlin.jvm.functions.Function0
                        public Object invoke() {
                            return this.invoke();
                        }

                        public final Set invoke() {
                            return SetsKt.plus(LazyJavaClassMemberScope.this.getFunctionNames(), LazyJavaClassMemberScope.this.getVariableNames());
                        }
                    });
                    StorageManager storageManager0 = this.$c.getStorageManager();
                    Annotations annotations0 = LazyJavaAnnotationsKt.resolveAnnotations(this.$c, javaField0);
                    JavaSourceElement javaSourceElement0 = this.$c.getComponents().getSourceElementFactory().source(javaField0);
                    return EnumEntrySyntheticClassDescriptor.create(storageManager0, LazyJavaClassMemberScope.this.getOwnerDescriptor(), name0, notNullLazyValue0, annotations0, javaSourceElement0);
                }
                return null;
            }
        });
    }

    public LazyJavaClassMemberScope(LazyJavaResolverContext lazyJavaResolverContext0, ClassDescriptor classDescriptor0, JavaClass javaClass0, boolean z, LazyJavaClassMemberScope lazyJavaClassMemberScope0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 16) != 0) {
            lazyJavaClassMemberScope0 = null;
        }
        this(lazyJavaResolverContext0, classDescriptor0, javaClass0, z, lazyJavaClassMemberScope0);
    }

    public static final ClassConstructorDescriptor access$createDefaultConstructor(LazyJavaClassMemberScope lazyJavaClassMemberScope0) {
        return lazyJavaClassMemberScope0.createDefaultConstructor();
    }

    public static final ClassConstructorDescriptor access$createDefaultRecordConstructor(LazyJavaClassMemberScope lazyJavaClassMemberScope0) {
        return lazyJavaClassMemberScope0.createDefaultRecordConstructor();
    }

    public static final NotNullLazyValue access$getEnumEntryIndex$p(LazyJavaClassMemberScope lazyJavaClassMemberScope0) {
        return lazyJavaClassMemberScope0.enumEntryIndex;
    }

    public static final NotNullLazyValue access$getGeneratedNestedClassNames$p(LazyJavaClassMemberScope lazyJavaClassMemberScope0) {
        return lazyJavaClassMemberScope0.generatedNestedClassNames;
    }

    public static final JavaClass access$getJClass$p(LazyJavaClassMemberScope lazyJavaClassMemberScope0) {
        return lazyJavaClassMemberScope0.jClass;
    }

    public static final NotNullLazyValue access$getNestedClassIndex$p(LazyJavaClassMemberScope lazyJavaClassMemberScope0) {
        return lazyJavaClassMemberScope0.nestedClassIndex;
    }

    public static final JavaClassConstructorDescriptor access$resolveConstructor(LazyJavaClassMemberScope lazyJavaClassMemberScope0, JavaConstructor javaConstructor0) {
        return lazyJavaClassMemberScope0.resolveConstructor(javaConstructor0);
    }

    private final void addAnnotationValueParameter(List list0, ConstructorDescriptor constructorDescriptor0, int v, JavaMethod javaMethod0, KotlinType kotlinType0, KotlinType kotlinType1) {
        Name name0 = javaMethod0.getName();
        KotlinType kotlinType2 = TypeUtils.makeNotNullable(kotlinType0);
        Intrinsics.checkNotNullExpressionValue(kotlinType2, "makeNotNullable(returnType)");
        boolean z = javaMethod0.getHasAnnotationParameterDefaultValue();
        list0.add(new ValueParameterDescriptorImpl(constructorDescriptor0, null, v, Annotations.Companion.getEMPTY(), name0, kotlinType2, z, false, false, (kotlinType1 == null ? null : TypeUtils.makeNotNullable(kotlinType1)), this.getC().getComponents().getSourceElementFactory().source(javaMethod0)));
    }

    private final void addFunctionFromSupertypes(Collection collection0, Name name0, Collection collection1, boolean z) {
        Collection collection2 = DescriptorResolverUtils.resolveOverridesForNonStaticMembers(name0, collection1, collection0, this.getOwnerDescriptor(), this.getC().getComponents().getErrorReporter(), this.getC().getComponents().getKotlinTypeChecker().getOverridingUtil());
        Intrinsics.checkNotNullExpressionValue(collection2, "resolveOverridesForNonSt….overridingUtil\n        )");
        if(!z) {
            collection0.addAll(collection2);
            return;
        }
        List list0 = CollectionsKt.plus(collection0, collection2);
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection2, 10));
        for(Object object0: collection2) {
            SimpleFunctionDescriptor simpleFunctionDescriptor0 = (SimpleFunctionDescriptor)object0;
            SimpleFunctionDescriptor simpleFunctionDescriptor1 = (SimpleFunctionDescriptor)SpecialBuiltinMembers.getOverriddenSpecialBuiltin(simpleFunctionDescriptor0);
            if(simpleFunctionDescriptor1 == null) {
                Intrinsics.checkNotNullExpressionValue(simpleFunctionDescriptor0, "resolvedOverride");
            }
            else {
                Intrinsics.checkNotNullExpressionValue(simpleFunctionDescriptor0, "resolvedOverride");
                simpleFunctionDescriptor0 = this.createHiddenCopyIfBuiltinAlreadyAccidentallyOverridden(simpleFunctionDescriptor0, simpleFunctionDescriptor1, list0);
            }
            arrayList0.add(simpleFunctionDescriptor0);
        }
        collection0.addAll(arrayList0);
    }

    private final void addOverriddenSpecialMethods(Name name0, Collection collection0, Collection collection1, Collection collection2, Function1 function10) {
        for(Object object0: collection1) {
            kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(collection2, this.obtainOverrideForBuiltinWithDifferentJvmName(((SimpleFunctionDescriptor)object0), function10, name0, collection0));
            kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(collection2, this.obtainOverrideForBuiltInWithErasedValueParametersInJava(((SimpleFunctionDescriptor)object0), function10, collection0));
            kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(collection2, this.obtainOverrideForSuspend(((SimpleFunctionDescriptor)object0), function10));
        }
    }

    private final void addPropertyOverrideByMethod(Set set0, Collection collection0, Set set1, Function1 function10) {
        for(Object object0: set0) {
            PropertyDescriptor propertyDescriptor0 = (PropertyDescriptor)object0;
            JavaPropertyDescriptor javaPropertyDescriptor0 = this.createPropertyDescriptorByMethods(propertyDescriptor0, function10);
            if(javaPropertyDescriptor0 != null) {
                collection0.add(javaPropertyDescriptor0);
                if(set1 == null) {
                    break;
                }
                set1.add(propertyDescriptor0);
                return;
            }
            if(false) {
                break;
            }
        }
    }

    private final void computeAnnotationProperties(Name name0, Collection collection0) {
        Object object0 = CollectionsKt.singleOrNull(((DeclaredMemberIndex)this.getDeclaredMemberIndex().invoke()).findMethodsByName(name0));
        if(((JavaMethod)object0) == null) {
            return;
        }
        collection0.add(LazyJavaClassMemberScope.createPropertyDescriptorWithDefaultGetter$default(this, ((JavaMethod)object0), null, Modality.FINAL, 2, null));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected Set computeClassNames(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        return SetsKt.plus(((Set)this.nestedClassIndex.invoke()), ((Map)this.enumEntryIndex.invoke()).keySet());
    }

    protected LinkedHashSet computeFunctionNames(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        Collection collection0 = this.getOwnerDescriptor().getTypeConstructor().getSupertypes();
        Intrinsics.checkNotNullExpressionValue(collection0, "ownerDescriptor.typeConstructor.supertypes");
        Collection collection1 = new LinkedHashSet();
        for(Object object0: collection0) {
            CollectionsKt.addAll(collection1, ((KotlinType)object0).getMemberScope().getFunctionNames());
        }
        ((LinkedHashSet)collection1).addAll(((DeclaredMemberIndex)this.getDeclaredMemberIndex().invoke()).getMethodNames());
        ((LinkedHashSet)collection1).addAll(((DeclaredMemberIndex)this.getDeclaredMemberIndex().invoke()).getRecordComponentNames());
        ((LinkedHashSet)collection1).addAll(this.computeClassNames(descriptorKindFilter0, function10));
        LazyJavaResolverContext lazyJavaResolverContext0 = this.getC();
        ((LinkedHashSet)collection1).addAll(this.getC().getComponents().getSyntheticPartsProvider().getMethodNames(lazyJavaResolverContext0, this.getOwnerDescriptor()));
        return (LinkedHashSet)collection1;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    public Set computeFunctionNames(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        return this.computeFunctionNames(descriptorKindFilter0, function10);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected void computeImplicitlyDeclaredFunctions(Collection collection0, Name name0) {
        Intrinsics.checkNotNullParameter(collection0, "result");
        Intrinsics.checkNotNullParameter(name0, "name");
        if(this.jClass.isRecord() && ((DeclaredMemberIndex)this.getDeclaredMemberIndex().invoke()).findRecordComponentByName(name0) != null) {
            if(!collection0.isEmpty()) {
                for(Object object0: collection0) {
                    if(!((SimpleFunctionDescriptor)object0).getValueParameters().isEmpty()) {
                        continue;
                    }
                    goto label_12;
                }
            }
            JavaRecordComponent javaRecordComponent0 = ((DeclaredMemberIndex)this.getDeclaredMemberIndex().invoke()).findRecordComponentByName(name0);
            Intrinsics.checkNotNull(javaRecordComponent0);
            collection0.add(this.resolveRecordComponentToFunctionDescriptor(javaRecordComponent0));
        }
    label_12:
        LazyJavaResolverContext lazyJavaResolverContext0 = this.getC();
        this.getC().getComponents().getSyntheticPartsProvider().generateMethods(lazyJavaResolverContext0, this.getOwnerDescriptor(), name0, collection0);
    }

    protected ClassDeclaredMemberIndex computeMemberIndex() {
        return new ClassDeclaredMemberIndex(this.jClass, kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.computeMemberIndex.1.INSTANCE);

        final class kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.computeMemberIndex.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.computeMemberIndex.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.computeMemberIndex.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.computeMemberIndex.1();
            }

            kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassMemberScope.computeMemberIndex.1() {
                super(1);
            }

            public final Boolean invoke(JavaMember javaMember0) {
                Intrinsics.checkNotNullParameter(javaMember0, "it");
                return Boolean.valueOf(!javaMember0.isStatic());
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((JavaMember)object0));
            }
        }

    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    public DeclaredMemberIndex computeMemberIndex() {
        return this.computeMemberIndex();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected void computeNonDeclaredFunctions(Collection collection0, Name name0) {
        Intrinsics.checkNotNullParameter(collection0, "result");
        Intrinsics.checkNotNullParameter(name0, "name");
        Set set0 = this.getFunctionsFromSupertypes(name0);
        if(!SpecialGenericSignatures.Companion.getSameAsRenamedInJvmBuiltin(name0) && !BuiltinMethodsWithSpecialGenericSignature.INSTANCE.getSameAsBuiltinMethodWithErasedValueParameters(name0)) {
            if(!(set0 instanceof Collection) || !set0.isEmpty()) {
                for(Object object0: set0) {
                    if(!((FunctionDescriptor)object0).isSuspend()) {
                        continue;
                    }
                    goto label_19;
                }
            }
            Collection collection1 = new ArrayList();
            for(Object object1: set0) {
                if(this.isVisibleAsFunctionInCurrentClass(((SimpleFunctionDescriptor)object1))) {
                    collection1.add(object1);
                }
            }
            this.addFunctionFromSupertypes(collection0, name0, ((List)collection1), false);
            return;
        }
    label_19:
        SmartSet smartSet0 = SmartSet.Companion.create();
        List list0 = CollectionsKt.emptyList();
        OverridingUtil overridingUtil0 = this.getC().getComponents().getKotlinTypeChecker().getOverridingUtil();
        Collection collection2 = DescriptorResolverUtils.resolveOverridesForNonStaticMembers(name0, set0, list0, this.getOwnerDescriptor(), ErrorReporter.DO_NOTHING, overridingUtil0);
        Intrinsics.checkNotNullExpressionValue(collection2, "resolveOverridesForNonSt….overridingUtil\n        )");
        this.addOverriddenSpecialMethods(name0, collection0, collection2, collection0, new Function1() {
            {
                super(1, object0);
            }

            @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "searchMethodsByNameWithoutBuiltinMagic";
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(LazyJavaClassMemberScope.class);
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "searchMethodsByNameWithoutBuiltinMagic(Lorg/jetbrains/kotlin/name/Name;)Ljava/util/Collection;";
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Name)object0));
            }

            public final Collection invoke(Name name0) {
                Intrinsics.checkNotNullParameter(name0, "p0");
                return ((LazyJavaClassMemberScope)this.receiver).searchMethodsByNameWithoutBuiltinMagic(name0);
            }
        });
        this.addOverriddenSpecialMethods(name0, collection0, collection2, smartSet0, new Function1() {
            {
                super(1, object0);
            }

            @Override  // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
            public final String getName() {
                return "searchMethodsInSupertypesWithoutBuiltinMagic";
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final KDeclarationContainer getOwner() {
                return Reflection.getOrCreateKotlinClass(LazyJavaClassMemberScope.class);
            }

            @Override  // kotlin.jvm.internal.CallableReference
            public final String getSignature() {
                return "searchMethodsInSupertypesWithoutBuiltinMagic(Lorg/jetbrains/kotlin/name/Name;)Ljava/util/Collection;";
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Name)object0));
            }

            public final Collection invoke(Name name0) {
                Intrinsics.checkNotNullParameter(name0, "p0");
                return ((LazyJavaClassMemberScope)this.receiver).searchMethodsInSupertypesWithoutBuiltinMagic(name0);
            }
        });
        Collection collection3 = new ArrayList();
        for(Object object2: set0) {
            if(this.isVisibleAsFunctionInCurrentClass(((SimpleFunctionDescriptor)object2))) {
                collection3.add(object2);
            }
        }
        this.addFunctionFromSupertypes(collection0, name0, CollectionsKt.plus(((List)collection3), smartSet0), true);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected void computeNonDeclaredProperties(Name name0, Collection collection0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(collection0, "result");
        if(this.jClass.isAnnotationType()) {
            this.computeAnnotationProperties(name0, collection0);
        }
        Set set0 = this.getPropertiesFromSupertypes(name0);
        if(set0.isEmpty()) {
            return;
        }
        SmartSet smartSet0 = SmartSet.Companion.create();
        SmartSet smartSet1 = SmartSet.Companion.create();
        this.addPropertyOverrideByMethod(set0, collection0, smartSet0, new Function1() {
            {
                LazyJavaClassMemberScope.this = lazyJavaClassMemberScope0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Name)object0));
            }

            public final Collection invoke(Name name0) {
                Intrinsics.checkNotNullParameter(name0, "it");
                return LazyJavaClassMemberScope.this.searchMethodsByNameWithoutBuiltinMagic(name0);
            }
        });
        this.addPropertyOverrideByMethod(SetsKt.minus(set0, smartSet0), smartSet1, null, new Function1() {
            {
                LazyJavaClassMemberScope.this = lazyJavaClassMemberScope0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Name)object0));
            }

            public final Collection invoke(Name name0) {
                Intrinsics.checkNotNullParameter(name0, "it");
                return LazyJavaClassMemberScope.this.searchMethodsInSupertypesWithoutBuiltinMagic(name0);
            }
        });
        Collection collection1 = DescriptorResolverUtils.resolveOverridesForNonStaticMembers(name0, SetsKt.plus(set0, smartSet1), collection0, this.getOwnerDescriptor(), this.getC().getComponents().getErrorReporter(), this.getC().getComponents().getKotlinTypeChecker().getOverridingUtil());
        Intrinsics.checkNotNullExpressionValue(collection1, "resolveOverridesForNonSt…rridingUtil\n            )");
        collection0.addAll(collection1);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected Set computePropertyNames(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        if(this.jClass.isAnnotationType()) {
            return this.getFunctionNames();
        }
        LinkedHashSet linkedHashSet0 = new LinkedHashSet(((DeclaredMemberIndex)this.getDeclaredMemberIndex().invoke()).getFieldNames());
        Collection collection0 = this.getOwnerDescriptor().getTypeConstructor().getSupertypes();
        Intrinsics.checkNotNullExpressionValue(collection0, "ownerDescriptor.typeConstructor.supertypes");
        for(Object object0: collection0) {
            CollectionsKt.addAll(linkedHashSet0, ((KotlinType)object0).getMemberScope().getVariableNames());
        }
        return linkedHashSet0;
    }

    private final Collection computeSupertypes() {
        if(this.skipRefinement) {
            Collection collection0 = this.getOwnerDescriptor().getTypeConstructor().getSupertypes();
            Intrinsics.checkNotNullExpressionValue(collection0, "ownerDescriptor.typeConstructor.supertypes");
            return collection0;
        }
        return this.getC().getComponents().getKotlinTypeChecker().getKotlinTypeRefiner().refineSupertypes(this.getOwnerDescriptor());
    }

    private final List createAnnotationConstructorParameters(ClassConstructorDescriptorImpl classConstructorDescriptorImpl0) {
        Collection collection0 = this.jClass.getMethods();
        ArrayList arrayList0 = new ArrayList(collection0.size());
        JavaTypeAttributes javaTypeAttributes0 = JavaTypeAttributesKt.toAttributes$default(TypeUsage.COMMON, true, false, null, 6, null);
        ArrayList arrayList1 = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for(Object object0: collection0) {
            if(Intrinsics.areEqual(((JavaMethod)object0).getName(), JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME)) {
                arrayList1.add(object0);
            }
            else {
                arrayList2.add(object0);
            }
        }
        Pair pair0 = new Pair(arrayList1, arrayList2);
        List list0 = (List)pair0.component1();
        Object object1 = pair0.component2();
        list0.size();
        Object object2 = CollectionsKt.firstOrNull(list0);
        int v = 1;
        if(((JavaMethod)object2) != null) {
            JavaType javaType0 = ((JavaMethod)object2).getReturnType();
            Pair pair1 = javaType0 instanceof JavaArrayType ? new Pair(this.getC().getTypeResolver().transformArrayType(((JavaArrayType)javaType0), javaTypeAttributes0, true), this.getC().getTypeResolver().transformJavaType(((JavaArrayType)javaType0).getComponentType(), javaTypeAttributes0)) : new Pair(this.getC().getTypeResolver().transformJavaType(javaType0, javaTypeAttributes0), null);
            this.addAnnotationValueParameter(arrayList0, classConstructorDescriptorImpl0, 0, ((JavaMethod)object2), ((KotlinType)pair1.component1()), ((KotlinType)pair1.component2()));
        }
        int v1 = 0;
        if(((JavaMethod)object2) == null) {
            v = 0;
        }
        for(Object object3: ((List)object1)) {
            this.addAnnotationValueParameter(arrayList0, classConstructorDescriptorImpl0, v1 + v, ((JavaMethod)object3), this.getC().getTypeResolver().transformJavaType(((JavaMethod)object3).getReturnType(), javaTypeAttributes0), null);
            ++v1;
        }
        return arrayList0;
    }

    private final ClassConstructorDescriptor createDefaultConstructor() {
        boolean z = this.jClass.isAnnotationType();
        if((this.jClass.isInterface() || !this.jClass.hasDefaultConstructor()) && !z) {
            return null;
        }
        ClassDescriptor classDescriptor0 = this.getOwnerDescriptor();
        JavaSourceElementFactory javaSourceElementFactory0 = this.getC().getComponents().getSourceElementFactory();
        JavaClassConstructorDescriptor javaClassConstructorDescriptor0 = JavaClassConstructorDescriptor.createJavaConstructor(classDescriptor0, Annotations.Companion.getEMPTY(), true, javaSourceElementFactory0.source(this.jClass));
        Intrinsics.checkNotNullExpressionValue(javaClassConstructorDescriptor0, "createJavaConstructor(\n ….source(jClass)\n        )");
        List list0 = z ? this.createAnnotationConstructorParameters(javaClassConstructorDescriptor0) : Collections.EMPTY_LIST;
        javaClassConstructorDescriptor0.setHasSynthesizedParameterNames(false);
        javaClassConstructorDescriptor0.initialize(list0, this.getConstructorVisibility(classDescriptor0));
        javaClassConstructorDescriptor0.setHasStableParameterNames(true);
        javaClassConstructorDescriptor0.setReturnType(classDescriptor0.getDefaultType());
        this.getC().getComponents().getJavaResolverCache().recordConstructor(this.jClass, javaClassConstructorDescriptor0);
        return javaClassConstructorDescriptor0;
    }

    private final ClassConstructorDescriptor createDefaultRecordConstructor() {
        ClassDescriptor classDescriptor0 = this.getOwnerDescriptor();
        JavaSourceElementFactory javaSourceElementFactory0 = this.getC().getComponents().getSourceElementFactory();
        JavaClassConstructorDescriptor javaClassConstructorDescriptor0 = JavaClassConstructorDescriptor.createJavaConstructor(classDescriptor0, Annotations.Companion.getEMPTY(), true, javaSourceElementFactory0.source(this.jClass));
        Intrinsics.checkNotNullExpressionValue(javaClassConstructorDescriptor0, "createJavaConstructor(\n ….source(jClass)\n        )");
        List list0 = this.createRecordConstructorParameters(javaClassConstructorDescriptor0);
        javaClassConstructorDescriptor0.setHasSynthesizedParameterNames(false);
        javaClassConstructorDescriptor0.initialize(list0, this.getConstructorVisibility(classDescriptor0));
        javaClassConstructorDescriptor0.setHasStableParameterNames(false);
        javaClassConstructorDescriptor0.setReturnType(classDescriptor0.getDefaultType());
        return javaClassConstructorDescriptor0;
    }

    private final SimpleFunctionDescriptor createHiddenCopyIfBuiltinAlreadyAccidentallyOverridden(SimpleFunctionDescriptor simpleFunctionDescriptor0, CallableDescriptor callableDescriptor0, Collection collection0) {
        if(collection0 instanceof Collection && collection0.isEmpty()) {
            return simpleFunctionDescriptor0;
        }
        for(Object object0: collection0) {
            SimpleFunctionDescriptor simpleFunctionDescriptor1 = (SimpleFunctionDescriptor)object0;
            if(!Intrinsics.areEqual(simpleFunctionDescriptor0, simpleFunctionDescriptor1) && simpleFunctionDescriptor1.getInitialSignatureDescriptor() == null && this.doesOverride(simpleFunctionDescriptor1, callableDescriptor0)) {
                FunctionDescriptor functionDescriptor0 = simpleFunctionDescriptor0.newCopyBuilder().setHiddenToOvercomeSignatureClash().build();
                Intrinsics.checkNotNull(functionDescriptor0);
                return (SimpleFunctionDescriptor)functionDescriptor0;
            }
            if(false) {
                break;
            }
        }
        return simpleFunctionDescriptor0;
    }

    private final SimpleFunctionDescriptor createOverrideForBuiltinFunctionWithErasedParameterIfNeeded(FunctionDescriptor functionDescriptor0, Function1 function10) {
        Object object0 = null;
        Name name0 = functionDescriptor0.getName();
        Intrinsics.checkNotNullExpressionValue(name0, "overridden.name");
        for(Object object1: ((Iterable)function10.invoke(name0))) {
            if(this.hasSameJvmDescriptorButDoesNotOverride(((SimpleFunctionDescriptor)object1), functionDescriptor0)) {
                object0 = object1;
                break;
            }
        }
        if(((SimpleFunctionDescriptor)object0) != null) {
            CopyBuilder functionDescriptor$CopyBuilder0 = ((SimpleFunctionDescriptor)object0).newCopyBuilder();
            List list0 = functionDescriptor0.getValueParameters();
            Intrinsics.checkNotNullExpressionValue(list0, "overridden.valueParameters");
            ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
            for(Object object2: list0) {
                arrayList0.add(((ValueParameterDescriptor)object2).getType());
            }
            List list1 = ((SimpleFunctionDescriptor)object0).getValueParameters();
            Intrinsics.checkNotNullExpressionValue(list1, "override.valueParameters");
            functionDescriptor$CopyBuilder0.setValueParameters(UtilKt.copyValueParameters(arrayList0, list1, functionDescriptor0));
            functionDescriptor$CopyBuilder0.setSignatureChange();
            functionDescriptor$CopyBuilder0.setPreserveSourceElement();
            functionDescriptor$CopyBuilder0.putUserData(JavaMethodDescriptor.HAS_ERASED_VALUE_PARAMETERS, Boolean.TRUE);
            return (SimpleFunctionDescriptor)functionDescriptor$CopyBuilder0.build();
        }
        return null;
    }

    private final JavaPropertyDescriptor createPropertyDescriptorByMethods(PropertyDescriptor propertyDescriptor0, Function1 function10) {
        SimpleFunctionDescriptor simpleFunctionDescriptor1;
        PropertySetterDescriptorImpl propertySetterDescriptorImpl0 = null;
        if(!this.doesClassOverridesProperty(propertyDescriptor0, function10)) {
            return null;
        }
        SimpleFunctionDescriptor simpleFunctionDescriptor0 = this.findGetterOverride(propertyDescriptor0, function10);
        Intrinsics.checkNotNull(simpleFunctionDescriptor0);
        if(propertyDescriptor0.isVar()) {
            simpleFunctionDescriptor1 = this.findSetterOverride(propertyDescriptor0, function10);
            Intrinsics.checkNotNull(simpleFunctionDescriptor1);
        }
        else {
            simpleFunctionDescriptor1 = null;
        }
        if(simpleFunctionDescriptor1 != null) {
            simpleFunctionDescriptor1.getModality();
            simpleFunctionDescriptor0.getModality();
        }
        JavaForKotlinOverridePropertyDescriptor javaForKotlinOverridePropertyDescriptor0 = new JavaForKotlinOverridePropertyDescriptor(this.getOwnerDescriptor(), simpleFunctionDescriptor0, simpleFunctionDescriptor1, propertyDescriptor0);
        KotlinType kotlinType0 = simpleFunctionDescriptor0.getReturnType();
        Intrinsics.checkNotNull(kotlinType0);
        javaForKotlinOverridePropertyDescriptor0.setType(kotlinType0, CollectionsKt.emptyList(), this.getDispatchReceiverParameter(), null, CollectionsKt.emptyList());
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl0 = DescriptorFactory.createGetter(javaForKotlinOverridePropertyDescriptor0, simpleFunctionDescriptor0.getAnnotations(), false, false, false, simpleFunctionDescriptor0.getSource());
        propertyGetterDescriptorImpl0.setInitialSignatureDescriptor(simpleFunctionDescriptor0);
        propertyGetterDescriptorImpl0.initialize(javaForKotlinOverridePropertyDescriptor0.getType());
        Intrinsics.checkNotNullExpressionValue(propertyGetterDescriptorImpl0, "createGetter(\n          …escriptor.type)\n        }");
        if(simpleFunctionDescriptor1 != null) {
            List list0 = simpleFunctionDescriptor1.getValueParameters();
            Intrinsics.checkNotNullExpressionValue(list0, "setterMethod.valueParameters");
            ValueParameterDescriptor valueParameterDescriptor0 = (ValueParameterDescriptor)CollectionsKt.firstOrNull(list0);
            if(valueParameterDescriptor0 == null) {
                throw new AssertionError("No parameter found for " + simpleFunctionDescriptor1);
            }
            propertySetterDescriptorImpl0 = DescriptorFactory.createSetter(javaForKotlinOverridePropertyDescriptor0, simpleFunctionDescriptor1.getAnnotations(), valueParameterDescriptor0.getAnnotations(), false, false, false, simpleFunctionDescriptor1.getVisibility(), simpleFunctionDescriptor1.getSource());
            propertySetterDescriptorImpl0.setInitialSignatureDescriptor(simpleFunctionDescriptor1);
        }
        javaForKotlinOverridePropertyDescriptor0.initialize(propertyGetterDescriptorImpl0, propertySetterDescriptorImpl0);
        return javaForKotlinOverridePropertyDescriptor0;
    }

    private final JavaPropertyDescriptor createPropertyDescriptorWithDefaultGetter(JavaMethod javaMethod0, KotlinType kotlinType0, Modality modality0) {
        JavaPropertyDescriptor javaPropertyDescriptor0 = JavaPropertyDescriptor.create(this.getOwnerDescriptor(), LazyJavaAnnotationsKt.resolveAnnotations(this.getC(), javaMethod0), modality0, UtilsKt.toDescriptorVisibility(javaMethod0.getVisibility()), false, javaMethod0.getName(), this.getC().getComponents().getSourceElementFactory().source(javaMethod0), false);
        Intrinsics.checkNotNullExpressionValue(javaPropertyDescriptor0, "create(\n            owne…inal = */ false\n        )");
        PropertyGetterDescriptorImpl propertyGetterDescriptorImpl0 = DescriptorFactory.createDefaultGetter(javaPropertyDescriptor0, Annotations.Companion.getEMPTY());
        Intrinsics.checkNotNullExpressionValue(propertyGetterDescriptorImpl0, "createDefaultGetter(prop…iptor, Annotations.EMPTY)");
        javaPropertyDescriptor0.initialize(propertyGetterDescriptorImpl0, null);
        KotlinType kotlinType1 = kotlinType0 == null ? this.computeMethodReturnType(javaMethod0, ContextKt.childForMethod$default(this.getC(), javaPropertyDescriptor0, javaMethod0, 0, 4, null)) : kotlinType0;
        javaPropertyDescriptor0.setType(kotlinType1, CollectionsKt.emptyList(), this.getDispatchReceiverParameter(), null, CollectionsKt.emptyList());
        propertyGetterDescriptorImpl0.initialize(kotlinType1);
        return javaPropertyDescriptor0;
    }

    static JavaPropertyDescriptor createPropertyDescriptorWithDefaultGetter$default(LazyJavaClassMemberScope lazyJavaClassMemberScope0, JavaMethod javaMethod0, KotlinType kotlinType0, Modality modality0, int v, Object object0) {
        if((v & 2) != 0) {
            kotlinType0 = null;
        }
        return lazyJavaClassMemberScope0.createPropertyDescriptorWithDefaultGetter(javaMethod0, kotlinType0, modality0);
    }

    private final List createRecordConstructorParameters(ClassConstructorDescriptorImpl classConstructorDescriptorImpl0) {
        Collection collection0 = this.jClass.getRecordComponents();
        ArrayList arrayList0 = new ArrayList(collection0.size());
        JavaTypeAttributes javaTypeAttributes0 = JavaTypeAttributesKt.toAttributes$default(TypeUsage.COMMON, false, false, null, 6, null);
        int v = 0;
        for(Object object0: collection0) {
            KotlinType kotlinType0 = this.getC().getTypeResolver().transformJavaType(((JavaRecordComponent)object0).getType(), javaTypeAttributes0);
            KotlinType kotlinType1 = ((JavaRecordComponent)object0).isVararg() ? this.getC().getComponents().getModule().getBuiltIns().getArrayElementType(kotlinType0) : null;
            Name name0 = ((JavaRecordComponent)object0).getName();
            JavaSourceElement javaSourceElement0 = this.getC().getComponents().getSourceElementFactory().source(((JavaRecordComponent)object0));
            arrayList0.add(new ValueParameterDescriptorImpl(classConstructorDescriptorImpl0, null, v, Annotations.Companion.getEMPTY(), name0, kotlinType0, false, false, false, kotlinType1, javaSourceElement0));
            ++v;
        }
        return arrayList0;
    }

    private final SimpleFunctionDescriptor createRenamedCopy(SimpleFunctionDescriptor simpleFunctionDescriptor0, Name name0) {
        CopyBuilder functionDescriptor$CopyBuilder0 = simpleFunctionDescriptor0.newCopyBuilder();
        functionDescriptor$CopyBuilder0.setName(name0);
        functionDescriptor$CopyBuilder0.setSignatureChange();
        functionDescriptor$CopyBuilder0.setPreserveSourceElement();
        FunctionDescriptor functionDescriptor0 = functionDescriptor$CopyBuilder0.build();
        Intrinsics.checkNotNull(functionDescriptor0);
        return (SimpleFunctionDescriptor)functionDescriptor0;
    }

    private final SimpleFunctionDescriptor createSuspendView(SimpleFunctionDescriptor simpleFunctionDescriptor0) {
        FqName fqName0;
        List list0 = simpleFunctionDescriptor0.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(list0, "valueParameters");
        ValueParameterDescriptor valueParameterDescriptor0 = (ValueParameterDescriptor)CollectionsKt.lastOrNull(list0);
        if(valueParameterDescriptor0 != null) {
            ClassifierDescriptor classifierDescriptor0 = valueParameterDescriptor0.getType().getConstructor().getDeclarationDescriptor();
            if(classifierDescriptor0 == null) {
                fqName0 = null;
            }
            else {
                FqNameUnsafe fqNameUnsafe0 = DescriptorUtilsKt.getFqNameUnsafe(classifierDescriptor0);
                if(fqNameUnsafe0 == null) {
                    fqName0 = null;
                }
                else {
                    if(!fqNameUnsafe0.isSafe()) {
                        fqNameUnsafe0 = null;
                    }
                    fqName0 = fqNameUnsafe0 == null ? null : fqNameUnsafe0.toSafe();
                }
            }
            if(!Intrinsics.areEqual(fqName0, StandardNames.CONTINUATION_INTERFACE_FQ_NAME)) {
                valueParameterDescriptor0 = null;
            }
            if(valueParameterDescriptor0 != null) {
                CopyBuilder functionDescriptor$CopyBuilder0 = simpleFunctionDescriptor0.newCopyBuilder();
                List list1 = simpleFunctionDescriptor0.getValueParameters();
                Intrinsics.checkNotNullExpressionValue(list1, "valueParameters");
                SimpleFunctionDescriptor simpleFunctionDescriptor1 = (SimpleFunctionDescriptor)functionDescriptor$CopyBuilder0.setValueParameters(CollectionsKt.dropLast(list1, 1)).setReturnType(((TypeProjection)valueParameterDescriptor0.getType().getArguments().get(0)).getType()).build();
                if(((SimpleFunctionDescriptorImpl)simpleFunctionDescriptor1) == null) {
                    return simpleFunctionDescriptor1;
                }
                ((SimpleFunctionDescriptorImpl)simpleFunctionDescriptor1).setSuspend(true);
                return simpleFunctionDescriptor1;
            }
        }
        return null;
    }

    private final boolean doesClassOverridesProperty(PropertyDescriptor propertyDescriptor0, Function1 function10) {
        if(JavaDescriptorUtilKt.isJavaField(propertyDescriptor0)) {
            return false;
        }
        SimpleFunctionDescriptor simpleFunctionDescriptor0 = this.findGetterOverride(propertyDescriptor0, function10);
        SimpleFunctionDescriptor simpleFunctionDescriptor1 = this.findSetterOverride(propertyDescriptor0, function10);
        if(simpleFunctionDescriptor0 == null) {
            return false;
        }
        return propertyDescriptor0.isVar() ? simpleFunctionDescriptor1 != null && simpleFunctionDescriptor1.getModality() == simpleFunctionDescriptor0.getModality() : true;
    }

    private final boolean doesOverride(CallableDescriptor callableDescriptor0, CallableDescriptor callableDescriptor1) {
        Result overridingUtil$OverrideCompatibilityInfo$Result0 = OverridingUtil.DEFAULT.isOverridableByWithoutExternalConditions(callableDescriptor1, callableDescriptor0, true).getResult();
        Intrinsics.checkNotNullExpressionValue(overridingUtil$OverrideCompatibilityInfo$Result0, "DEFAULT.isOverridableByW…iptor, this, true).result");
        return overridingUtil$OverrideCompatibilityInfo$Result0 == Result.OVERRIDABLE && !JavaIncompatibilityRulesOverridabilityCondition.Companion.doesJavaOverrideHaveIncompatibleValueParameterKinds(callableDescriptor1, callableDescriptor0);
    }

    private final boolean doesOverrideRenamedBuiltins(SimpleFunctionDescriptor simpleFunctionDescriptor0) {
        Name name0 = simpleFunctionDescriptor0.getName();
        Intrinsics.checkNotNullExpressionValue(name0, "name");
        Name name1 = SpecialGenericSignatures.Companion.getBuiltinFunctionNamesByJvmName(name0);
        if(name1 == null) {
            return false;
        }
        Iterable iterable0 = this.getFunctionsFromSupertypes(name1);
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            if(SpecialBuiltinMembers.doesOverrideBuiltinWithDifferentJvmName(((SimpleFunctionDescriptor)object0))) {
                collection0.add(object0);
            }
        }
        if(((List)collection0).isEmpty()) {
            return false;
        }
        SimpleFunctionDescriptor simpleFunctionDescriptor1 = this.createRenamedCopy(simpleFunctionDescriptor0, name1);
        if(((List)collection0) instanceof Collection && ((List)collection0).isEmpty()) {
            return false;
        }
        for(Object object1: ((List)collection0)) {
            if(this.doesOverrideRenamedDescriptor(((SimpleFunctionDescriptor)object1), simpleFunctionDescriptor1)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    private final boolean doesOverrideRenamedDescriptor(SimpleFunctionDescriptor simpleFunctionDescriptor0, FunctionDescriptor functionDescriptor0) {
        if(BuiltinMethodsWithDifferentJvmName.INSTANCE.isRemoveAtByIndex(simpleFunctionDescriptor0)) {
            functionDescriptor0 = functionDescriptor0.getOriginal();
        }
        Intrinsics.checkNotNullExpressionValue(functionDescriptor0, "if (superDescriptor.isRe…iginal else subDescriptor");
        return this.doesOverride(functionDescriptor0, simpleFunctionDescriptor0);
    }

    private final boolean doesOverrideSuspendFunction(SimpleFunctionDescriptor simpleFunctionDescriptor0) {
        SimpleFunctionDescriptor simpleFunctionDescriptor1 = this.createSuspendView(simpleFunctionDescriptor0);
        if(simpleFunctionDescriptor1 == null) {
            return false;
        }
        Name name0 = simpleFunctionDescriptor0.getName();
        Intrinsics.checkNotNullExpressionValue(name0, "name");
        Iterable iterable0 = this.getFunctionsFromSupertypes(name0);
        if(iterable0 instanceof Collection && ((Collection)iterable0).isEmpty()) {
            return false;
        }
        for(Object object0: iterable0) {
            if(((SimpleFunctionDescriptor)object0).isSuspend() && this.doesOverride(simpleFunctionDescriptor1, ((SimpleFunctionDescriptor)object0))) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    private final SimpleFunctionDescriptor findGetterByName(PropertyDescriptor propertyDescriptor0, String s, Function1 function10) {
        SimpleFunctionDescriptor simpleFunctionDescriptor0;
        Name name0 = Name.identifier(s);
        Intrinsics.checkNotNullExpressionValue(name0, "identifier(getterName)");
        Iterator iterator0 = ((Iterable)function10.invoke(name0)).iterator();
        do {
            simpleFunctionDescriptor0 = null;
            if(!iterator0.hasNext()) {
                break;
            }
            Object object0 = iterator0.next();
            SimpleFunctionDescriptor simpleFunctionDescriptor1 = (SimpleFunctionDescriptor)object0;
            if(simpleFunctionDescriptor1.getValueParameters().size() == 0) {
                KotlinType kotlinType0 = simpleFunctionDescriptor1.getReturnType();
                if((kotlinType0 == null ? false : KotlinTypeChecker.DEFAULT.isSubtypeOf(kotlinType0, propertyDescriptor0.getType()))) {
                    simpleFunctionDescriptor0 = simpleFunctionDescriptor1;
                }
            }
        }
        while(simpleFunctionDescriptor0 == null);
        return simpleFunctionDescriptor0;
    }

    private final SimpleFunctionDescriptor findGetterOverride(PropertyDescriptor propertyDescriptor0, Function1 function10) {
        PropertyGetterDescriptor propertyGetterDescriptor0 = propertyDescriptor0.getGetter();
        String s = null;
        PropertyGetterDescriptor propertyGetterDescriptor1 = propertyGetterDescriptor0 == null ? null : ((PropertyGetterDescriptor)SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName(propertyGetterDescriptor0));
        if(propertyGetterDescriptor1 != null) {
            s = ClassicBuiltinSpecialProperties.INSTANCE.getBuiltinSpecialPropertyGetterName(propertyGetterDescriptor1);
        }
        if(s != null && !SpecialBuiltinMembers.hasRealKotlinSuperClassWithOverrideOf(this.getOwnerDescriptor(), propertyGetterDescriptor1)) {
            return this.findGetterByName(propertyDescriptor0, s, function10);
        }
        String s1 = propertyDescriptor0.getName().asString();
        Intrinsics.checkNotNullExpressionValue(s1, "name.asString()");
        return this.findGetterByName(propertyDescriptor0, JvmAbi.getterName(s1), function10);
    }

    private final SimpleFunctionDescriptor findSetterOverride(PropertyDescriptor propertyDescriptor0, Function1 function10) {
        SimpleFunctionDescriptor simpleFunctionDescriptor0;
        String s = propertyDescriptor0.getName().asString();
        Intrinsics.checkNotNullExpressionValue(s, "name.asString()");
        Name name0 = Name.identifier(JvmAbi.setterName(s));
        Intrinsics.checkNotNullExpressionValue(name0, "identifier(JvmAbi.setterName(name.asString()))");
        Iterator iterator0 = ((Iterable)function10.invoke(name0)).iterator();
        do {
            simpleFunctionDescriptor0 = null;
            if(!iterator0.hasNext()) {
                break;
            }
            Object object0 = iterator0.next();
            SimpleFunctionDescriptor simpleFunctionDescriptor1 = (SimpleFunctionDescriptor)object0;
            if(simpleFunctionDescriptor1.getValueParameters().size() == 1) {
                KotlinType kotlinType0 = simpleFunctionDescriptor1.getReturnType();
                if(kotlinType0 != null && KotlinBuiltIns.isUnit(kotlinType0)) {
                    List list0 = simpleFunctionDescriptor1.getValueParameters();
                    Intrinsics.checkNotNullExpressionValue(list0, "descriptor.valueParameters");
                    KotlinType kotlinType1 = ((ValueParameterDescriptor)CollectionsKt.single(list0)).getType();
                    KotlinType kotlinType2 = propertyDescriptor0.getType();
                    if(KotlinTypeChecker.DEFAULT.equalTypes(kotlinType1, kotlinType2)) {
                        simpleFunctionDescriptor0 = simpleFunctionDescriptor1;
                    }
                }
            }
        }
        while(simpleFunctionDescriptor0 == null);
        return simpleFunctionDescriptor0;
    }

    private final DescriptorVisibility getConstructorVisibility(ClassDescriptor classDescriptor0) {
        DescriptorVisibility descriptorVisibility0 = classDescriptor0.getVisibility();
        Intrinsics.checkNotNullExpressionValue(descriptorVisibility0, "classDescriptor.visibility");
        if(Intrinsics.areEqual(descriptorVisibility0, JavaDescriptorVisibilities.PROTECTED_STATIC_VISIBILITY)) {
            descriptorVisibility0 = JavaDescriptorVisibilities.PROTECTED_AND_PACKAGE;
            Intrinsics.checkNotNullExpressionValue(descriptorVisibility0, "PROTECTED_AND_PACKAGE");
        }
        return descriptorVisibility0;
    }

    public final NotNullLazyValue getConstructors$descriptors_jvm() {
        return this.constructors;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public ClassifierDescriptor getContributedClassifier(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        this.recordLookup(name0, lookupLocation0);
        LazyJavaClassMemberScope lazyJavaClassMemberScope0 = (LazyJavaClassMemberScope)this.getMainScope();
        if(lazyJavaClassMemberScope0 != null) {
            MemoizedFunctionToNullable memoizedFunctionToNullable0 = lazyJavaClassMemberScope0.nestedClasses;
            if(memoizedFunctionToNullable0 != null) {
                ClassDescriptor classDescriptor0 = (ClassDescriptor)memoizedFunctionToNullable0.invoke(name0);
                if(classDescriptor0 != null) {
                    return classDescriptor0;
                }
            }
        }
        return (ClassifierDescriptor)this.nestedClasses.invoke(name0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    public Collection getContributedFunctions(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        this.recordLookup(name0, lookupLocation0);
        return super.getContributedFunctions(name0, lookupLocation0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    public Collection getContributedVariables(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        this.recordLookup(name0, lookupLocation0);
        return super.getContributedVariables(name0, lookupLocation0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected ReceiverParameterDescriptor getDispatchReceiverParameter() {
        return DescriptorUtils.getDispatchReceiverParameterIfNeeded(this.getOwnerDescriptor());
    }

    private final Set getFunctionsFromSupertypes(Name name0) {
        Iterable iterable0 = this.computeSupertypes();
        Collection collection0 = new LinkedHashSet();
        for(Object object0: iterable0) {
            CollectionsKt.addAll(collection0, ((KotlinType)object0).getMemberScope().getContributedFunctions(name0, NoLookupLocation.WHEN_GET_SUPER_MEMBERS));
        }
        return (Set)collection0;
    }

    protected ClassDescriptor getOwnerDescriptor() {
        return this.ownerDescriptor;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    public DeclarationDescriptor getOwnerDescriptor() {
        return this.getOwnerDescriptor();
    }

    private final Set getPropertiesFromSupertypes(Name name0) {
        Iterable iterable0 = this.computeSupertypes();
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            Iterable iterable1 = ((KotlinType)object0).getMemberScope().getContributedVariables(name0, NoLookupLocation.WHEN_GET_SUPER_MEMBERS);
            Collection collection1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable1, 10));
            for(Object object1: iterable1) {
                collection1.add(((PropertyDescriptor)object1));
            }
            CollectionsKt.addAll(collection0, ((List)collection1));
        }
        return CollectionsKt.toSet(((List)collection0));
    }

    private final boolean hasSameJvmDescriptorButDoesNotOverride(SimpleFunctionDescriptor simpleFunctionDescriptor0, FunctionDescriptor functionDescriptor0) {
        String s = MethodSignatureMappingKt.computeJvmDescriptor$default(simpleFunctionDescriptor0, false, false, 2, null);
        FunctionDescriptor functionDescriptor1 = functionDescriptor0.getOriginal();
        Intrinsics.checkNotNullExpressionValue(functionDescriptor1, "builtinWithErasedParameters.original");
        return Intrinsics.areEqual(s, MethodSignatureMappingKt.computeJvmDescriptor$default(functionDescriptor1, false, false, 2, null)) && !this.doesOverride(simpleFunctionDescriptor0, functionDescriptor0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected boolean isVisibleAsFunction(JavaMethodDescriptor javaMethodDescriptor0) {
        Intrinsics.checkNotNullParameter(javaMethodDescriptor0, "<this>");
        return this.jClass.isAnnotationType() ? false : this.isVisibleAsFunctionInCurrentClass(javaMethodDescriptor0);
    }

    private final boolean isVisibleAsFunctionInCurrentClass(SimpleFunctionDescriptor simpleFunctionDescriptor0) {
        Name name0 = simpleFunctionDescriptor0.getName();
        Intrinsics.checkNotNullExpressionValue(name0, "function.name");
        Iterable iterable0 = PropertiesConventionUtilKt.getPropertyNamesCandidatesByAccessorName(name0);
        if(!(iterable0 instanceof Collection) || !((Collection)iterable0).isEmpty()) {
            for(Object object0: iterable0) {
                Iterable iterable1 = this.getPropertiesFromSupertypes(((Name)object0));
                if(!(iterable1 instanceof Collection) || !((Collection)iterable1).isEmpty()) {
                    for(Object object1: iterable1) {
                        PropertyDescriptor propertyDescriptor0 = (PropertyDescriptor)object1;
                        if(!this.doesClassOverridesProperty(propertyDescriptor0, new Function1(this) {
                            final SimpleFunctionDescriptor $function;

                            {
                                this.$function = simpleFunctionDescriptor0;
                                LazyJavaClassMemberScope.this = lazyJavaClassMemberScope0;
                                super(1);
                            }

                            @Override  // kotlin.jvm.functions.Function1
                            public Object invoke(Object object0) {
                                return this.invoke(((Name)object0));
                            }

                            public final Collection invoke(Name name0) {
                                Intrinsics.checkNotNullParameter(name0, "accessorName");
                                return Intrinsics.areEqual(this.$function.getName(), name0) ? CollectionsKt.listOf(this.$function) : CollectionsKt.plus(LazyJavaClassMemberScope.this.searchMethodsByNameWithoutBuiltinMagic(name0), LazyJavaClassMemberScope.this.searchMethodsInSupertypesWithoutBuiltinMagic(name0));
                            }
                        })) {
                            continue;
                        }
                        if(!propertyDescriptor0.isVar()) {
                            String s = simpleFunctionDescriptor0.getName().asString();
                            Intrinsics.checkNotNullExpressionValue(s, "function.name.asString()");
                            if(!JvmAbi.isSetterName(s)) {
                                return false;
                            }
                            continue;
                        }
                        return false;
                    }
                    if(false) {
                        break;
                    }
                }
            }
        }
        return !this.doesOverrideRenamedBuiltins(simpleFunctionDescriptor0) && !this.shouldBeVisibleAsOverrideOfBuiltInWithErasedValueParameters(simpleFunctionDescriptor0) && !this.doesOverrideSuspendFunction(simpleFunctionDescriptor0);
    }

    private final SimpleFunctionDescriptor obtainOverrideForBuiltInWithErasedValueParametersInJava(SimpleFunctionDescriptor simpleFunctionDescriptor0, Function1 function10, Collection collection0) {
        FunctionDescriptor functionDescriptor0 = BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava(simpleFunctionDescriptor0);
        if(functionDescriptor0 == null) {
            return null;
        }
        SimpleFunctionDescriptor simpleFunctionDescriptor1 = this.createOverrideForBuiltinFunctionWithErasedParameterIfNeeded(functionDescriptor0, function10);
        if(simpleFunctionDescriptor1 != null) {
            if(!this.isVisibleAsFunctionInCurrentClass(simpleFunctionDescriptor1)) {
                simpleFunctionDescriptor1 = null;
            }
            return simpleFunctionDescriptor1 == null ? null : this.createHiddenCopyIfBuiltinAlreadyAccidentallyOverridden(simpleFunctionDescriptor1, functionDescriptor0, collection0);
        }
        return null;
    }

    private final SimpleFunctionDescriptor obtainOverrideForBuiltinWithDifferentJvmName(SimpleFunctionDescriptor simpleFunctionDescriptor0, Function1 function10, Name name0, Collection collection0) {
        SimpleFunctionDescriptor simpleFunctionDescriptor1 = (SimpleFunctionDescriptor)SpecialBuiltinMembers.getOverriddenBuiltinWithDifferentJvmName(simpleFunctionDescriptor0);
        if(simpleFunctionDescriptor1 == null) {
            return null;
        }
        String s = SpecialBuiltinMembers.getJvmMethodNameIfSpecial(simpleFunctionDescriptor1);
        Intrinsics.checkNotNull(s);
        Name name1 = Name.identifier(s);
        Intrinsics.checkNotNullExpressionValue(name1, "identifier(nameInJava)");
        for(Object object0: ((Collection)function10.invoke(name1))) {
            SimpleFunctionDescriptor simpleFunctionDescriptor2 = this.createRenamedCopy(((SimpleFunctionDescriptor)object0), name0);
            if(this.doesOverrideRenamedDescriptor(simpleFunctionDescriptor1, simpleFunctionDescriptor2)) {
                return this.createHiddenCopyIfBuiltinAlreadyAccidentallyOverridden(simpleFunctionDescriptor2, simpleFunctionDescriptor1, collection0);
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    private final SimpleFunctionDescriptor obtainOverrideForSuspend(SimpleFunctionDescriptor simpleFunctionDescriptor0, Function1 function10) {
        if(!simpleFunctionDescriptor0.isSuspend()) {
            return null;
        }
        Name name0 = simpleFunctionDescriptor0.getName();
        Intrinsics.checkNotNullExpressionValue(name0, "descriptor.name");
        for(Object object0: ((Iterable)function10.invoke(name0))) {
            SimpleFunctionDescriptor simpleFunctionDescriptor1 = this.createSuspendView(((SimpleFunctionDescriptor)object0));
            if(simpleFunctionDescriptor1 == null || !this.doesOverride(simpleFunctionDescriptor1, simpleFunctionDescriptor0)) {
                simpleFunctionDescriptor1 = null;
            }
            if(simpleFunctionDescriptor1 != null) {
                return simpleFunctionDescriptor1;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public void recordLookup(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        kotlin.reflect.jvm.internal.impl.incremental.UtilsKt.record(this.getC().getComponents().getLookupTracker(), lookupLocation0, this.getOwnerDescriptor(), name0);
    }

    private final JavaClassConstructorDescriptor resolveConstructor(JavaConstructor javaConstructor0) {
        ClassDescriptor classDescriptor0 = this.getOwnerDescriptor();
        JavaClassConstructorDescriptor javaClassConstructorDescriptor0 = JavaClassConstructorDescriptor.createJavaConstructor(classDescriptor0, LazyJavaAnnotationsKt.resolveAnnotations(this.getC(), javaConstructor0), false, this.getC().getComponents().getSourceElementFactory().source(javaConstructor0));
        Intrinsics.checkNotNullExpressionValue(javaClassConstructorDescriptor0, "createJavaConstructor(\n …ce(constructor)\n        )");
        LazyJavaResolverContext lazyJavaResolverContext0 = ContextKt.childForMethod(this.getC(), javaClassConstructorDescriptor0, javaConstructor0, classDescriptor0.getDeclaredTypeParameters().size());
        ResolvedValueParameters lazyJavaScope$ResolvedValueParameters0 = this.resolveValueParameters(lazyJavaResolverContext0, javaClassConstructorDescriptor0, javaConstructor0.getValueParameters());
        List list0 = classDescriptor0.getDeclaredTypeParameters();
        Intrinsics.checkNotNullExpressionValue(list0, "classDescriptor.declaredTypeParameters");
        Iterable iterable0 = javaConstructor0.getTypeParameters();
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            TypeParameterDescriptor typeParameterDescriptor0 = lazyJavaResolverContext0.getTypeParameterResolver().resolveTypeParameter(((JavaTypeParameter)object0));
            Intrinsics.checkNotNull(typeParameterDescriptor0);
            arrayList0.add(typeParameterDescriptor0);
        }
        List list1 = CollectionsKt.plus(list0, arrayList0);
        javaClassConstructorDescriptor0.initialize(lazyJavaScope$ResolvedValueParameters0.getDescriptors(), UtilsKt.toDescriptorVisibility(javaConstructor0.getVisibility()), list1);
        javaClassConstructorDescriptor0.setHasStableParameterNames(false);
        javaClassConstructorDescriptor0.setHasSynthesizedParameterNames(lazyJavaScope$ResolvedValueParameters0.getHasSynthesizedNames());
        javaClassConstructorDescriptor0.setReturnType(classDescriptor0.getDefaultType());
        lazyJavaResolverContext0.getComponents().getJavaResolverCache().recordConstructor(javaConstructor0, javaClassConstructorDescriptor0);
        return javaClassConstructorDescriptor0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected MethodSignatureData resolveMethodSignature(JavaMethod javaMethod0, List list0, KotlinType kotlinType0, List list1) {
        Intrinsics.checkNotNullParameter(javaMethod0, "method");
        Intrinsics.checkNotNullParameter(list0, "methodTypeParameters");
        Intrinsics.checkNotNullParameter(kotlinType0, "returnType");
        Intrinsics.checkNotNullParameter(list1, "valueParameters");
        PropagatedSignature signaturePropagator$PropagatedSignature0 = this.getC().getComponents().getSignaturePropagator().resolvePropagatedSignature(javaMethod0, this.getOwnerDescriptor(), kotlinType0, null, list1, list0);
        Intrinsics.checkNotNullExpressionValue(signaturePropagator$PropagatedSignature0, "c.components.signaturePr…dTypeParameters\n        )");
        KotlinType kotlinType1 = signaturePropagator$PropagatedSignature0.getReturnType();
        Intrinsics.checkNotNullExpressionValue(kotlinType1, "propagated.returnType");
        List list2 = signaturePropagator$PropagatedSignature0.getValueParameters();
        Intrinsics.checkNotNullExpressionValue(list2, "propagated.valueParameters");
        List list3 = signaturePropagator$PropagatedSignature0.getTypeParameters();
        Intrinsics.checkNotNullExpressionValue(list3, "propagated.typeParameters");
        List list4 = signaturePropagator$PropagatedSignature0.getErrors();
        Intrinsics.checkNotNullExpressionValue(list4, "propagated.errors");
        return new MethodSignatureData(kotlinType1, signaturePropagator$PropagatedSignature0.getReceiverType(), list2, list3, signaturePropagator$PropagatedSignature0.hasStableParameterNames(), list4);
    }

    private final JavaMethodDescriptor resolveRecordComponentToFunctionDescriptor(JavaRecordComponent javaRecordComponent0) {
        JavaMethodDescriptor javaMethodDescriptor0 = JavaMethodDescriptor.createJavaMethod(this.getOwnerDescriptor(), LazyJavaAnnotationsKt.resolveAnnotations(this.getC(), javaRecordComponent0), javaRecordComponent0.getName(), this.getC().getComponents().getSourceElementFactory().source(javaRecordComponent0), true);
        Intrinsics.checkNotNullExpressionValue(javaMethodDescriptor0, "createJavaMethod(\n      …omponent), true\n        )");
        JavaTypeAttributes javaTypeAttributes0 = JavaTypeAttributesKt.toAttributes$default(TypeUsage.COMMON, false, false, null, 6, null);
        KotlinType kotlinType0 = this.getC().getTypeResolver().transformJavaType(javaRecordComponent0.getType(), javaTypeAttributes0);
        javaMethodDescriptor0.initialize(null, this.getDispatchReceiverParameter(), CollectionsKt.emptyList(), CollectionsKt.emptyList(), CollectionsKt.emptyList(), kotlinType0, Modality.Companion.convertFromFlags(false, false, true), DescriptorVisibilities.PUBLIC, null);
        javaMethodDescriptor0.setParameterNamesStatus(false, false);
        this.getC().getComponents().getJavaResolverCache().recordMethod(javaRecordComponent0, javaMethodDescriptor0);
        return javaMethodDescriptor0;
    }

    private final Collection searchMethodsByNameWithoutBuiltinMagic(Name name0) {
        Iterable iterable0 = ((DeclaredMemberIndex)this.getDeclaredMemberIndex().invoke()).findMethodsByName(name0);
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            arrayList0.add(this.resolveMethodToFunctionDescriptor(((JavaMethod)object0)));
        }
        return arrayList0;
    }

    private final Collection searchMethodsInSupertypesWithoutBuiltinMagic(Name name0) {
        Iterable iterable0 = this.getFunctionsFromSupertypes(name0);
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            if(!SpecialBuiltinMembers.doesOverrideBuiltinWithDifferentJvmName(((SimpleFunctionDescriptor)object0)) && BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava(((SimpleFunctionDescriptor)object0)) == null) {
                collection0.add(object0);
            }
        }
        return (List)collection0;
    }

    private final boolean shouldBeVisibleAsOverrideOfBuiltInWithErasedValueParameters(SimpleFunctionDescriptor simpleFunctionDescriptor0) {
        Name name0 = simpleFunctionDescriptor0.getName();
        Intrinsics.checkNotNullExpressionValue(name0, "name");
        if(!BuiltinMethodsWithSpecialGenericSignature.INSTANCE.getSameAsBuiltinMethodWithErasedValueParameters(name0)) {
            return false;
        }
        Name name1 = simpleFunctionDescriptor0.getName();
        Intrinsics.checkNotNullExpressionValue(name1, "name");
        Iterable iterable0 = this.getFunctionsFromSupertypes(name1);
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            FunctionDescriptor functionDescriptor0 = BuiltinMethodsWithSpecialGenericSignature.getOverriddenBuiltinFunctionWithErasedValueParametersInJava(((SimpleFunctionDescriptor)object0));
            if(functionDescriptor0 != null) {
                collection0.add(functionDescriptor0);
            }
        }
        if(((List)collection0) instanceof Collection && ((List)collection0).isEmpty()) {
            return false;
        }
        for(Object object1: ((List)collection0)) {
            if(this.hasSameJvmDescriptorButDoesNotOverride(simpleFunctionDescriptor0, ((FunctionDescriptor)object1))) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    public String toString() {
        return "Lazy Java member scope for " + this.jClass.getFqName();
    }
}

