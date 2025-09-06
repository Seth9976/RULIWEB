package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.PropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ReceiverParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PropertyDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ValueParameterDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.UtilsKt;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaMethodDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaPropertyDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotationsKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributes;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributesKt;
import kotlin.reflect.jvm.internal.impl.load.java.sources.JavaSourceElement;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaArrayType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaField;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaMethod;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaValueParameter;
import kotlin.reflect.jvm.internal.impl.load.kotlin.MethodSignatureMappingKt;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorFactory;
import kotlin.reflect.jvm.internal.impl.resolve.DescriptorUtils;
import kotlin.reflect.jvm.internal.impl.resolve.OverridingUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.constants.ConstantValue;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindExclude.NonExtensions;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeUsage;
import kotlin.reflect.jvm.internal.impl.types.TypeUtils;

public abstract class LazyJavaScope extends MemberScopeImpl {
    public static final class MethodSignatureData {
        private final List errors;
        private final boolean hasStableParameterNames;
        private final KotlinType receiverType;
        private final KotlinType returnType;
        private final List typeParameters;
        private final List valueParameters;

        public MethodSignatureData(KotlinType kotlinType0, KotlinType kotlinType1, List list0, List list1, boolean z, List list2) {
            Intrinsics.checkNotNullParameter(kotlinType0, "returnType");
            Intrinsics.checkNotNullParameter(list0, "valueParameters");
            Intrinsics.checkNotNullParameter(list1, "typeParameters");
            Intrinsics.checkNotNullParameter(list2, "errors");
            super();
            this.returnType = kotlinType0;
            this.receiverType = kotlinType1;
            this.valueParameters = list0;
            this.typeParameters = list1;
            this.hasStableParameterNames = z;
            this.errors = list2;
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            if(!(object0 instanceof MethodSignatureData)) {
                return false;
            }
            if(!Intrinsics.areEqual(this.returnType, ((MethodSignatureData)object0).returnType)) {
                return false;
            }
            if(!Intrinsics.areEqual(this.receiverType, ((MethodSignatureData)object0).receiverType)) {
                return false;
            }
            if(!Intrinsics.areEqual(this.valueParameters, ((MethodSignatureData)object0).valueParameters)) {
                return false;
            }
            if(!Intrinsics.areEqual(this.typeParameters, ((MethodSignatureData)object0).typeParameters)) {
                return false;
            }
            return this.hasStableParameterNames == ((MethodSignatureData)object0).hasStableParameterNames ? Intrinsics.areEqual(this.errors, ((MethodSignatureData)object0).errors) : false;
        }

        public final List getErrors() {
            return this.errors;
        }

        public final boolean getHasStableParameterNames() {
            return this.hasStableParameterNames;
        }

        public final KotlinType getReceiverType() {
            return this.receiverType;
        }

        public final KotlinType getReturnType() {
            return this.returnType;
        }

        public final List getTypeParameters() {
            return this.typeParameters;
        }

        public final List getValueParameters() {
            return this.valueParameters;
        }

        @Override
        public int hashCode() {
            int v = this.returnType.hashCode();
            int v1 = this.receiverType == null ? 0 : this.receiverType.hashCode();
            int v2 = this.valueParameters.hashCode();
            int v3 = this.typeParameters.hashCode();
            int v4 = this.hasStableParameterNames;
            if(v4) {
                v4 = 1;
            }
            return ((((v * 0x1F + v1) * 0x1F + v2) * 0x1F + v3) * 0x1F + v4) * 0x1F + this.errors.hashCode();
        }

        @Override
        public String toString() {
            return "MethodSignatureData(returnType=" + this.returnType + ", receiverType=" + this.receiverType + ", valueParameters=" + this.valueParameters + ", typeParameters=" + this.typeParameters + ", hasStableParameterNames=" + this.hasStableParameterNames + ", errors=" + this.errors + ')';
        }
    }

    public static final class ResolvedValueParameters {
        private final List descriptors;
        private final boolean hasSynthesizedNames;

        public ResolvedValueParameters(List list0, boolean z) {
            Intrinsics.checkNotNullParameter(list0, "descriptors");
            super();
            this.descriptors = list0;
            this.hasSynthesizedNames = z;
        }

        public final List getDescriptors() {
            return this.descriptors;
        }

        public final boolean getHasSynthesizedNames() {
            return this.hasSynthesizedNames;
        }
    }

    static final KProperty[] $$delegatedProperties;
    private final NotNullLazyValue allDescriptors;
    private final LazyJavaResolverContext c;
    private final NotNullLazyValue classNamesLazy$delegate;
    private final MemoizedFunctionToNullable declaredField;
    private final MemoizedFunctionToNotNull declaredFunctions;
    private final NotNullLazyValue declaredMemberIndex;
    private final NotNullLazyValue functionNamesLazy$delegate;
    private final MemoizedFunctionToNotNull functions;
    private final LazyJavaScope mainScope;
    private final MemoizedFunctionToNotNull properties;
    private final NotNullLazyValue propertyNamesLazy$delegate;

    static {
        LazyJavaScope.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaScope.class), "functionNamesLazy", "getFunctionNamesLazy()Ljava/util/Set;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaScope.class), "propertyNamesLazy", "getPropertyNamesLazy()Ljava/util/Set;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaScope.class), "classNamesLazy", "getClassNamesLazy()Ljava/util/Set;"))};
    }

    public LazyJavaScope(LazyJavaResolverContext lazyJavaResolverContext0, LazyJavaScope lazyJavaScope0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "c");
        super();
        this.c = lazyJavaResolverContext0;
        this.mainScope = lazyJavaScope0;
        this.allDescriptors = lazyJavaResolverContext0.getStorageManager().createRecursionTolerantLazyValue(new Function0() {
            {
                LazyJavaScope.this = lazyJavaScope0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Collection invoke() {
                return LazyJavaScope.this.computeDescriptors(DescriptorKindFilter.ALL, MemberScope.Companion.getALL_NAME_FILTER());
            }
        }, CollectionsKt.emptyList());
        this.declaredMemberIndex = lazyJavaResolverContext0.getStorageManager().createLazyValue(new Function0() {
            {
                LazyJavaScope.this = lazyJavaScope0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final DeclaredMemberIndex invoke() {
                return LazyJavaScope.this.computeMemberIndex();
            }
        });
        this.declaredFunctions = lazyJavaResolverContext0.getStorageManager().createMemoizedFunction(new Function1() {
            {
                LazyJavaScope.this = lazyJavaScope0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Name)object0));
            }

            public final Collection invoke(Name name0) {
                Intrinsics.checkNotNullParameter(name0, "name");
                if(LazyJavaScope.this.getMainScope() != null) {
                    return (Collection)LazyJavaScope.access$getDeclaredFunctions$p(LazyJavaScope.this.getMainScope()).invoke(name0);
                }
                List list0 = new ArrayList();
                for(Object object0: ((DeclaredMemberIndex)LazyJavaScope.this.getDeclaredMemberIndex().invoke()).findMethodsByName(name0)) {
                    JavaMethod javaMethod0 = (JavaMethod)object0;
                    JavaMethodDescriptor javaMethodDescriptor0 = LazyJavaScope.this.resolveMethodToFunctionDescriptor(javaMethod0);
                    if(LazyJavaScope.this.isVisibleAsFunction(javaMethodDescriptor0)) {
                        LazyJavaScope.this.getC().getComponents().getJavaResolverCache().recordMethod(javaMethod0, javaMethodDescriptor0);
                        list0.add(javaMethodDescriptor0);
                    }
                }
                LazyJavaScope.this.computeImplicitlyDeclaredFunctions(list0, name0);
                return list0;
            }
        });
        this.declaredField = lazyJavaResolverContext0.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1() {
            {
                LazyJavaScope.this = lazyJavaScope0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Name)object0));
            }

            public final PropertyDescriptor invoke(Name name0) {
                Intrinsics.checkNotNullParameter(name0, "name");
                if(LazyJavaScope.this.getMainScope() != null) {
                    return (PropertyDescriptor)LazyJavaScope.access$getDeclaredField$p(LazyJavaScope.this.getMainScope()).invoke(name0);
                }
                JavaField javaField0 = ((DeclaredMemberIndex)LazyJavaScope.this.getDeclaredMemberIndex().invoke()).findFieldByName(name0);
                return javaField0 == null || javaField0.isEnumEntry() ? null : LazyJavaScope.access$resolveProperty(LazyJavaScope.this, javaField0);
            }
        });
        this.functions = lazyJavaResolverContext0.getStorageManager().createMemoizedFunction(new Function1() {
            {
                LazyJavaScope.this = lazyJavaScope0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Name)object0));
            }

            public final Collection invoke(Name name0) {
                Intrinsics.checkNotNullParameter(name0, "name");
                LinkedHashSet linkedHashSet0 = new LinkedHashSet(((Collection)LazyJavaScope.access$getDeclaredFunctions$p(LazyJavaScope.this).invoke(name0)));
                LazyJavaScope.access$retainMostSpecificMethods(LazyJavaScope.this, linkedHashSet0);
                LazyJavaScope.this.computeNonDeclaredFunctions(linkedHashSet0, name0);
                return CollectionsKt.toList(LazyJavaScope.this.getC().getComponents().getSignatureEnhancement().enhanceSignatures(LazyJavaScope.this.getC(), linkedHashSet0));
            }
        });
        this.functionNamesLazy$delegate = lazyJavaResolverContext0.getStorageManager().createLazyValue(new Function0() {
            {
                LazyJavaScope.this = lazyJavaScope0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Set invoke() {
                return LazyJavaScope.this.computeFunctionNames(DescriptorKindFilter.FUNCTIONS, null);
            }
        });
        this.propertyNamesLazy$delegate = lazyJavaResolverContext0.getStorageManager().createLazyValue(new Function0() {
            {
                LazyJavaScope.this = lazyJavaScope0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Set invoke() {
                return LazyJavaScope.this.computePropertyNames(DescriptorKindFilter.VARIABLES, null);
            }
        });
        this.classNamesLazy$delegate = lazyJavaResolverContext0.getStorageManager().createLazyValue(new Function0() {
            {
                LazyJavaScope.this = lazyJavaScope0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Set invoke() {
                return LazyJavaScope.this.computeClassNames(DescriptorKindFilter.CLASSIFIERS, null);
            }
        });
        this.properties = lazyJavaResolverContext0.getStorageManager().createMemoizedFunction(new Function1() {
            {
                LazyJavaScope.this = lazyJavaScope0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Name)object0));
            }

            public final List invoke(Name name0) {
                Intrinsics.checkNotNullParameter(name0, "name");
                ArrayList arrayList0 = new ArrayList();
                kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(arrayList0, LazyJavaScope.access$getDeclaredField$p(LazyJavaScope.this).invoke(name0));
                LazyJavaScope.this.computeNonDeclaredProperties(name0, arrayList0);
                return DescriptorUtils.isAnnotationClass(LazyJavaScope.this.getOwnerDescriptor()) ? CollectionsKt.toList(arrayList0) : CollectionsKt.toList(LazyJavaScope.this.getC().getComponents().getSignatureEnhancement().enhanceSignatures(LazyJavaScope.this.getC(), arrayList0));
            }
        });
    }

    public LazyJavaScope(LazyJavaResolverContext lazyJavaResolverContext0, LazyJavaScope lazyJavaScope0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            lazyJavaScope0 = null;
        }
        this(lazyJavaResolverContext0, lazyJavaScope0);
    }

    public static final MemoizedFunctionToNullable access$getDeclaredField$p(LazyJavaScope lazyJavaScope0) {
        return lazyJavaScope0.declaredField;
    }

    public static final MemoizedFunctionToNotNull access$getDeclaredFunctions$p(LazyJavaScope lazyJavaScope0) {
        return lazyJavaScope0.declaredFunctions;
    }

    public static final PropertyDescriptor access$resolveProperty(LazyJavaScope lazyJavaScope0, JavaField javaField0) {
        return lazyJavaScope0.resolveProperty(javaField0);
    }

    public static final void access$retainMostSpecificMethods(LazyJavaScope lazyJavaScope0, Set set0) {
        lazyJavaScope0.retainMostSpecificMethods(set0);
    }

    protected abstract Set computeClassNames(DescriptorKindFilter arg1, Function1 arg2);

    protected final List computeDescriptors(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
        NoLookupLocation noLookupLocation0 = NoLookupLocation.WHEN_GET_ALL_DESCRIPTORS;
        LinkedHashSet linkedHashSet0 = new LinkedHashSet();
        if(descriptorKindFilter0.acceptsKinds(7)) {
            for(Object object0: this.computeClassNames(descriptorKindFilter0, function10)) {
                Name name0 = (Name)object0;
                if(((Boolean)function10.invoke(name0)).booleanValue()) {
                    kotlin.reflect.jvm.internal.impl.utils.CollectionsKt.addIfNotNull(linkedHashSet0, this.getContributedClassifier(name0, noLookupLocation0));
                }
            }
        }
        if(descriptorKindFilter0.acceptsKinds(16) && !descriptorKindFilter0.getExcludes().contains(NonExtensions.INSTANCE)) {
            for(Object object1: this.computeFunctionNames(descriptorKindFilter0, function10)) {
                Name name1 = (Name)object1;
                if(((Boolean)function10.invoke(name1)).booleanValue()) {
                    linkedHashSet0.addAll(this.getContributedFunctions(name1, noLookupLocation0));
                }
            }
        }
        if(descriptorKindFilter0.acceptsKinds(0x20) && !descriptorKindFilter0.getExcludes().contains(NonExtensions.INSTANCE)) {
            for(Object object2: this.computePropertyNames(descriptorKindFilter0, function10)) {
                Name name2 = (Name)object2;
                if(((Boolean)function10.invoke(name2)).booleanValue()) {
                    linkedHashSet0.addAll(this.getContributedVariables(name2, noLookupLocation0));
                }
            }
        }
        return CollectionsKt.toList(linkedHashSet0);
    }

    protected abstract Set computeFunctionNames(DescriptorKindFilter arg1, Function1 arg2);

    protected void computeImplicitlyDeclaredFunctions(Collection collection0, Name name0) {
        Intrinsics.checkNotNullParameter(collection0, "result");
        Intrinsics.checkNotNullParameter(name0, "name");
    }

    protected abstract DeclaredMemberIndex computeMemberIndex();

    protected final KotlinType computeMethodReturnType(JavaMethod javaMethod0, LazyJavaResolverContext lazyJavaResolverContext0) {
        Intrinsics.checkNotNullParameter(javaMethod0, "method");
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "c");
        boolean z = javaMethod0.getContainingClass().isAnnotationType();
        JavaTypeAttributes javaTypeAttributes0 = JavaTypeAttributesKt.toAttributes$default(TypeUsage.COMMON, z, false, null, 6, null);
        return lazyJavaResolverContext0.getTypeResolver().transformJavaType(javaMethod0.getReturnType(), javaTypeAttributes0);
    }

    protected abstract void computeNonDeclaredFunctions(Collection arg1, Name arg2);

    protected abstract void computeNonDeclaredProperties(Name arg1, Collection arg2);

    protected abstract Set computePropertyNames(DescriptorKindFilter arg1, Function1 arg2);

    private final PropertyDescriptorImpl createPropertyDescriptor(JavaField javaField0) {
        boolean z = javaField0.isFinal();
        Annotations annotations0 = LazyJavaAnnotationsKt.resolveAnnotations(this.c, javaField0);
        DeclarationDescriptor declarationDescriptor0 = this.getOwnerDescriptor();
        DescriptorVisibility descriptorVisibility0 = UtilsKt.toDescriptorVisibility(javaField0.getVisibility());
        Name name0 = javaField0.getName();
        JavaSourceElement javaSourceElement0 = this.c.getComponents().getSourceElementFactory().source(javaField0);
        boolean z1 = this.isFinalStatic(javaField0);
        JavaPropertyDescriptor javaPropertyDescriptor0 = JavaPropertyDescriptor.create(declarationDescriptor0, annotations0, Modality.FINAL, descriptorVisibility0, !z, name0, javaSourceElement0, z1);
        Intrinsics.checkNotNullExpressionValue(javaPropertyDescriptor0, "create(\n            owne…d.isFinalStatic\n        )");
        return javaPropertyDescriptor0;
    }

    protected final NotNullLazyValue getAllDescriptors() {
        return this.allDescriptors;
    }

    protected final LazyJavaResolverContext getC() {
        return this.c;
    }

    private final Set getClassNamesLazy() {
        return (Set)StorageKt.getValue(this.classNamesLazy$delegate, this, LazyJavaScope.$$delegatedProperties[2]);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Set getClassifierNames() {
        return this.getClassNamesLazy();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Collection getContributedDescriptors(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
        return (Collection)this.allDescriptors.invoke();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Collection getContributedFunctions(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        return !this.getFunctionNames().contains(name0) ? CollectionsKt.emptyList() : ((Collection)this.functions.invoke(name0));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Collection getContributedVariables(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        return !this.getVariableNames().contains(name0) ? CollectionsKt.emptyList() : ((Collection)this.properties.invoke(name0));
    }

    protected final NotNullLazyValue getDeclaredMemberIndex() {
        return this.declaredMemberIndex;
    }

    protected abstract ReceiverParameterDescriptor getDispatchReceiverParameter();

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Set getFunctionNames() {
        return this.getFunctionNamesLazy();
    }

    private final Set getFunctionNamesLazy() {
        return (Set)StorageKt.getValue(this.functionNamesLazy$delegate, this, LazyJavaScope.$$delegatedProperties[0]);
    }

    protected final LazyJavaScope getMainScope() {
        return this.mainScope;
    }

    protected abstract DeclarationDescriptor getOwnerDescriptor();

    private final Set getPropertyNamesLazy() {
        return (Set)StorageKt.getValue(this.propertyNamesLazy$delegate, this, LazyJavaScope.$$delegatedProperties[1]);
    }

    private final KotlinType getPropertyType(JavaField javaField0) {
        JavaType javaType0 = javaField0.getType();
        JavaTypeAttributes javaTypeAttributes0 = JavaTypeAttributesKt.toAttributes$default(TypeUsage.COMMON, false, false, null, 7, null);
        KotlinType kotlinType0 = this.c.getTypeResolver().transformJavaType(javaType0, javaTypeAttributes0);
        if((KotlinBuiltIns.isPrimitiveType(kotlinType0) || KotlinBuiltIns.isString(kotlinType0)) && this.isFinalStatic(javaField0) && javaField0.getHasConstantNotNullInitializer()) {
            KotlinType kotlinType1 = TypeUtils.makeNotNullable(kotlinType0);
            Intrinsics.checkNotNullExpressionValue(kotlinType1, "makeNotNullable(propertyType)");
            return kotlinType1;
        }
        return kotlinType0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public Set getVariableNames() {
        return this.getPropertyNamesLazy();
    }

    // 去混淆评级： 低(20)
    private final boolean isFinalStatic(JavaField javaField0) {
        return javaField0.isFinal() && javaField0.isStatic();
    }

    protected boolean isVisibleAsFunction(JavaMethodDescriptor javaMethodDescriptor0) {
        Intrinsics.checkNotNullParameter(javaMethodDescriptor0, "<this>");
        return true;
    }

    protected abstract MethodSignatureData resolveMethodSignature(JavaMethod arg1, List arg2, KotlinType arg3, List arg4);

    protected final JavaMethodDescriptor resolveMethodToFunctionDescriptor(JavaMethod javaMethod0) {
        Map map0;
        Intrinsics.checkNotNullParameter(javaMethod0, "method");
        Annotations annotations0 = LazyJavaAnnotationsKt.resolveAnnotations(this.c, javaMethod0);
        JavaMethodDescriptor javaMethodDescriptor0 = JavaMethodDescriptor.createJavaMethod(this.getOwnerDescriptor(), annotations0, javaMethod0.getName(), this.c.getComponents().getSourceElementFactory().source(javaMethod0), ((DeclaredMemberIndex)this.declaredMemberIndex.invoke()).findRecordComponentByName(javaMethod0.getName()) != null && javaMethod0.getValueParameters().isEmpty());
        Intrinsics.checkNotNullExpressionValue(javaMethodDescriptor0, "createJavaMethod(\n      …eters.isEmpty()\n        )");
        LazyJavaResolverContext lazyJavaResolverContext0 = ContextKt.childForMethod$default(this.c, javaMethodDescriptor0, javaMethod0, 0, 4, null);
        Iterable iterable0 = javaMethod0.getTypeParameters();
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object0: iterable0) {
            TypeParameterDescriptor typeParameterDescriptor0 = lazyJavaResolverContext0.getTypeParameterResolver().resolveTypeParameter(((JavaTypeParameter)object0));
            Intrinsics.checkNotNull(typeParameterDescriptor0);
            arrayList0.add(typeParameterDescriptor0);
        }
        ResolvedValueParameters lazyJavaScope$ResolvedValueParameters0 = this.resolveValueParameters(lazyJavaResolverContext0, javaMethodDescriptor0, javaMethod0.getValueParameters());
        MethodSignatureData lazyJavaScope$MethodSignatureData0 = this.resolveMethodSignature(javaMethod0, arrayList0, this.computeMethodReturnType(javaMethod0, lazyJavaResolverContext0), lazyJavaScope$ResolvedValueParameters0.getDescriptors());
        KotlinType kotlinType0 = lazyJavaScope$MethodSignatureData0.getReceiverType();
        ReceiverParameterDescriptor receiverParameterDescriptor0 = kotlinType0 == null ? null : DescriptorFactory.createExtensionReceiverParameterForCallable(javaMethodDescriptor0, kotlinType0, Annotations.Companion.getEMPTY());
        ReceiverParameterDescriptor receiverParameterDescriptor1 = this.getDispatchReceiverParameter();
        List list0 = CollectionsKt.emptyList();
        List list1 = lazyJavaScope$MethodSignatureData0.getTypeParameters();
        List list2 = lazyJavaScope$MethodSignatureData0.getValueParameters();
        KotlinType kotlinType1 = lazyJavaScope$MethodSignatureData0.getReturnType();
        boolean z = javaMethod0.isAbstract();
        boolean z1 = javaMethod0.isFinal();
        Modality modality0 = Modality.Companion.convertFromFlags(false, z, !z1);
        DescriptorVisibility descriptorVisibility0 = UtilsKt.toDescriptorVisibility(javaMethod0.getVisibility());
        if(lazyJavaScope$MethodSignatureData0.getReceiverType() == null) {
            map0 = MapsKt.emptyMap();
        }
        else {
            Object object1 = CollectionsKt.first(lazyJavaScope$ResolvedValueParameters0.getDescriptors());
            map0 = MapsKt.mapOf(TuplesKt.to(JavaMethodDescriptor.ORIGINAL_VALUE_PARAMETER_FOR_EXTENSION_RECEIVER, object1));
        }
        javaMethodDescriptor0.initialize(receiverParameterDescriptor0, receiverParameterDescriptor1, list0, list1, list2, kotlinType1, modality0, descriptorVisibility0, map0);
        javaMethodDescriptor0.setParameterNamesStatus(lazyJavaScope$MethodSignatureData0.getHasStableParameterNames(), lazyJavaScope$ResolvedValueParameters0.getHasSynthesizedNames());
        if(!lazyJavaScope$MethodSignatureData0.getErrors().isEmpty()) {
            lazyJavaResolverContext0.getComponents().getSignaturePropagator().reportSignatureErrors(javaMethodDescriptor0, lazyJavaScope$MethodSignatureData0.getErrors());
        }
        return javaMethodDescriptor0;
    }

    private final PropertyDescriptor resolveProperty(JavaField javaField0) {
        PropertyDescriptorImpl propertyDescriptorImpl0 = this.createPropertyDescriptor(javaField0);
        propertyDescriptorImpl0.initialize(null, null, null, null);
        propertyDescriptorImpl0.setType(this.getPropertyType(javaField0), CollectionsKt.emptyList(), this.getDispatchReceiverParameter(), null, CollectionsKt.emptyList());
        if(DescriptorUtils.shouldRecordInitializerForProperty(propertyDescriptorImpl0, propertyDescriptorImpl0.getType())) {
            propertyDescriptorImpl0.setCompileTimeInitializerFactory(new Function0(javaField0, propertyDescriptorImpl0) {
                final JavaField $field;
                final PropertyDescriptorImpl $propertyDescriptor;

                {
                    LazyJavaScope.this = lazyJavaScope0;
                    this.$field = javaField0;
                    this.$propertyDescriptor = propertyDescriptorImpl0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final NullableLazyValue invoke() {
                    return LazyJavaScope.this.getC().getStorageManager().createNullableLazyValue(new Function0(this.$field, this.$propertyDescriptor) {
                        final JavaField $field;
                        final PropertyDescriptorImpl $propertyDescriptor;

                        {
                            LazyJavaScope.this = lazyJavaScope0;
                            this.$field = javaField0;
                            this.$propertyDescriptor = propertyDescriptorImpl0;
                            super(0);
                        }

                        @Override  // kotlin.jvm.functions.Function0
                        public Object invoke() {
                            return this.invoke();
                        }

                        public final ConstantValue invoke() {
                            return LazyJavaScope.this.getC().getComponents().getJavaPropertyInitializerEvaluator().getInitializerConstant(this.$field, this.$propertyDescriptor);
                        }
                    });
                }
            });
        }
        this.c.getComponents().getJavaResolverCache().recordField(javaField0, propertyDescriptorImpl0);
        return propertyDescriptorImpl0;
    }

    protected final ResolvedValueParameters resolveValueParameters(LazyJavaResolverContext lazyJavaResolverContext0, FunctionDescriptor functionDescriptor0, List list0) {
        Name name0;
        Pair pair0;
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "c");
        Intrinsics.checkNotNullParameter(functionDescriptor0, "function");
        Intrinsics.checkNotNullParameter(list0, "jValueParameters");
        Iterable iterable0 = CollectionsKt.withIndex(list0);
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        boolean z = false;
        for(Object object0: iterable0) {
            int v = ((IndexedValue)object0).component1();
            JavaValueParameter javaValueParameter0 = (JavaValueParameter)((IndexedValue)object0).component2();
            Annotations annotations0 = LazyJavaAnnotationsKt.resolveAnnotations(lazyJavaResolverContext0, javaValueParameter0);
            JavaTypeAttributes javaTypeAttributes0 = JavaTypeAttributesKt.toAttributes$default(TypeUsage.COMMON, false, false, null, 7, null);
            JavaArrayType javaArrayType0 = null;
            if(javaValueParameter0.isVararg()) {
                JavaType javaType0 = javaValueParameter0.getType();
                if(javaType0 instanceof JavaArrayType) {
                    javaArrayType0 = (JavaArrayType)javaType0;
                }
                if(javaArrayType0 == null) {
                    throw new AssertionError("Vararg parameter should be an array: " + javaValueParameter0);
                }
                KotlinType kotlinType0 = lazyJavaResolverContext0.getTypeResolver().transformArrayType(javaArrayType0, javaTypeAttributes0, true);
                pair0 = TuplesKt.to(kotlinType0, lazyJavaResolverContext0.getModule().getBuiltIns().getArrayElementType(kotlinType0));
            }
            else {
                pair0 = TuplesKt.to(lazyJavaResolverContext0.getTypeResolver().transformJavaType(javaValueParameter0.getType(), javaTypeAttributes0), null);
            }
            KotlinType kotlinType1 = (KotlinType)pair0.component1();
            Object object1 = pair0.component2();
            if(!Intrinsics.areEqual(functionDescriptor0.getName().asString(), "equals") || list0.size() != 1 || !Intrinsics.areEqual(lazyJavaResolverContext0.getModule().getBuiltIns().getNullableAnyType(), kotlinType1)) {
                name0 = javaValueParameter0.getName();
                if(name0 == null) {
                    z = true;
                }
                if(name0 == null) {
                    name0 = Name.identifier(("p" + v));
                    Intrinsics.checkNotNullExpressionValue(name0, "identifier(\"p$index\")");
                }
            }
            else {
                name0 = Name.identifier("other");
            }
            Intrinsics.checkNotNullExpressionValue(name0, "if (function.name.asStri…(\"p$index\")\n            }");
            arrayList0.add(new ValueParameterDescriptorImpl(functionDescriptor0, null, v, annotations0, name0, kotlinType1, false, false, false, ((KotlinType)object1), lazyJavaResolverContext0.getComponents().getSourceElementFactory().source(javaValueParameter0)));
        }
        return new ResolvedValueParameters(CollectionsKt.toList(arrayList0), z);
    }

    private final void retainMostSpecificMethods(Set set0) {
        Map map0 = new LinkedHashMap();
        for(Object object0: set0) {
            String s = MethodSignatureMappingKt.computeJvmDescriptor$default(((SimpleFunctionDescriptor)object0), false, false, 2, null);
            ArrayList arrayList0 = map0.get(s);
            if(arrayList0 == null) {
                arrayList0 = new ArrayList();
                map0.put(s, arrayList0);
            }
            arrayList0.add(object0);
        }
        for(Object object1: map0.values()) {
            List list0 = (List)object1;
            if(list0.size() != 1) {
                Collection collection0 = OverridingUtilsKt.selectMostSpecificInEachOverridableGroup(list0, kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope.retainMostSpecificMethods.mostSpecificMethods.1.INSTANCE);
                set0.removeAll(list0);
                set0.addAll(collection0);
            }
        }

        final class kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope.retainMostSpecificMethods.mostSpecificMethods.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope.retainMostSpecificMethods.mostSpecificMethods.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope.retainMostSpecificMethods.mostSpecificMethods.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope.retainMostSpecificMethods.mostSpecificMethods.1();
            }

            kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope.retainMostSpecificMethods.mostSpecificMethods.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((SimpleFunctionDescriptor)object0));
            }

            public final CallableDescriptor invoke(SimpleFunctionDescriptor simpleFunctionDescriptor0) {
                Intrinsics.checkNotNullParameter(simpleFunctionDescriptor0, "$this$selectMostSpecificInEachOverridableGroup");
                return simpleFunctionDescriptor0;
            }
        }

    }

    @Override
    public String toString() {
        return "Lazy scope for " + this.getOwnerDescriptor();
    }
}

