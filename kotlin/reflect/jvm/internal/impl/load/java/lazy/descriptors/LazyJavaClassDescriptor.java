package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.IntIterator;
import kotlin.collections.SetsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.builtins.jvm.MappingUtilKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassConstructorDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibility;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.NotFoundClasses.MockClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ScopesHolderForClass;
import kotlin.reflect.jvm.internal.impl.descriptors.SupertypeLoopChecker;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterUtilsKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ValueClassRepresentation;
import kotlin.reflect.jvm.internal.impl.descriptors.Visibility;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorBase;
import kotlin.reflect.jvm.internal.impl.incremental.components.NoLookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.FakePureImplementationsProvider;
import kotlin.reflect.jvm.internal.impl.load.java.JavaDescriptorVisibilities;
import kotlin.reflect.jvm.internal.impl.load.java.JvmAnnotationNames;
import kotlin.reflect.jvm.internal.impl.load.java.UtilsKt;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.JavaResolverComponents;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotationsKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributes;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.types.JavaTypeAttributesKt;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClassifierType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaType;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNamesUtilKt;
import kotlin.reflect.jvm.internal.impl.resolve.constants.StringValue;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.InnerClassesScopeWrapper;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.KotlinTypeFactory;
import kotlin.reflect.jvm.internal.impl.types.TypeAttributes;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.TypeProjectionImpl;
import kotlin.reflect.jvm.internal.impl.types.TypeUsage;
import kotlin.reflect.jvm.internal.impl.types.Variance;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;

public final class LazyJavaClassDescriptor extends ClassDescriptorBase implements JavaClassDescriptor {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    final class LazyJavaClassTypeConstructor extends AbstractClassTypeConstructor {
        private final NotNullLazyValue parameters;

        public LazyJavaClassTypeConstructor() {
            super(lazyJavaClassDescriptor0.c.getStorageManager());
            this.parameters = lazyJavaClassDescriptor0.c.getStorageManager().createLazyValue(new Function0() {
                {
                    LazyJavaClassDescriptor.this = lazyJavaClassDescriptor0;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public Object invoke() {
                    return this.invoke();
                }

                public final List invoke() {
                    return TypeParameterUtilsKt.computeConstructorTypeParameters(LazyJavaClassDescriptor.this);
                }
            });
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        protected Collection computeSupertypes() {
            KotlinType kotlinType1;
            Collection collection0 = LazyJavaClassDescriptor.this.getJClass().getSupertypes();
            ArrayList arrayList0 = new ArrayList(collection0.size());
            ArrayList arrayList1 = new ArrayList(0);
            KotlinType kotlinType0 = this.getPurelyImplementedSupertype();
            Iterator iterator0 = collection0.iterator();
            while(true) {
                kotlinType1 = null;
                if(!iterator0.hasNext()) {
                    break;
                }
                Object object0 = iterator0.next();
                JavaClassifierType javaClassifierType0 = (JavaClassifierType)object0;
                JavaTypeAttributes javaTypeAttributes0 = JavaTypeAttributesKt.toAttributes$default(TypeUsage.SUPERTYPE, false, false, null, 7, null);
                KotlinType kotlinType2 = LazyJavaClassDescriptor.this.c.getTypeResolver().transformJavaType(javaClassifierType0, javaTypeAttributes0);
                KotlinType kotlinType3 = LazyJavaClassDescriptor.this.c.getComponents().getSignatureEnhancement().enhanceSuperType(kotlinType2, LazyJavaClassDescriptor.this.c);
                if(kotlinType3.getConstructor().getDeclarationDescriptor() instanceof MockClassDescriptor) {
                    arrayList1.add(javaClassifierType0);
                }
                TypeConstructor typeConstructor0 = kotlinType3.getConstructor();
                if(kotlinType0 != null) {
                    kotlinType1 = kotlinType0.getConstructor();
                }
                if(!Intrinsics.areEqual(typeConstructor0, kotlinType1) && !KotlinBuiltIns.isAnyOrNullableAny(kotlinType3)) {
                    arrayList0.add(kotlinType3);
                }
            }
            ClassDescriptor classDescriptor0 = LazyJavaClassDescriptor.this.additionalSupertypeClassDescriptor;
            if(classDescriptor0 != null) {
                kotlinType1 = MappingUtilKt.createMappedTypeParametersSubstitution(classDescriptor0, LazyJavaClassDescriptor.this).buildSubstitutor().substitute(classDescriptor0.getDefaultType(), Variance.INVARIANT);
            }
            CollectionsKt.addIfNotNull(arrayList0, kotlinType1);
            CollectionsKt.addIfNotNull(arrayList0, kotlinType0);
            if(!arrayList1.isEmpty()) {
                ErrorReporter errorReporter0 = LazyJavaClassDescriptor.this.c.getComponents().getErrorReporter();
                ClassDescriptor classDescriptor1 = this.getDeclarationDescriptor();
                ArrayList arrayList2 = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(arrayList1, 10));
                for(Object object1: arrayList1) {
                    Intrinsics.checkNotNull(((JavaType)object1), "null cannot be cast to non-null type org.jetbrains.kotlin.load.java.structure.JavaClassifierType");
                    arrayList2.add(((JavaClassifierType)(((JavaType)object1))).getPresentableText());
                }
                errorReporter0.reportIncompleteHierarchy(classDescriptor1, arrayList2);
            }
            return arrayList0.isEmpty() ? kotlin.collections.CollectionsKt.listOf(LazyJavaClassDescriptor.this.c.getModule().getBuiltIns().getAnyType()) : kotlin.collections.CollectionsKt.toList(arrayList0);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor
        public ClassDescriptor getDeclarationDescriptor() {
            return LazyJavaClassDescriptor.this;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractClassTypeConstructor
        public ClassifierDescriptor getDeclarationDescriptor() {
            return this.getDeclarationDescriptor();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public List getParameters() {
            return (List)this.parameters.invoke();
        }

        private final KotlinType getPurelyImplementedSupertype() {
            FqName fqName2;
            FqName fqName0 = this.getPurelyImplementsFqNameFromAnnotation();
            if(fqName0 == null || fqName0.isRoot() || !fqName0.startsWith(StandardNames.BUILT_INS_PACKAGE_NAME)) {
                fqName0 = null;
            }
            if(fqName0 == null) {
                FqName fqName1 = DescriptorUtilsKt.getFqNameSafe(LazyJavaClassDescriptor.this);
                fqName2 = FakePureImplementationsProvider.INSTANCE.getPurelyImplementedInterface(fqName1);
                if(fqName2 == null) {
                    return null;
                }
            }
            else {
                fqName2 = fqName0;
            }
            ClassDescriptor classDescriptor0 = DescriptorUtilsKt.resolveTopLevelClass(LazyJavaClassDescriptor.this.c.getModule(), fqName2, NoLookupLocation.FROM_JAVA_LOADER);
            if(classDescriptor0 == null) {
                return null;
            }
            int v = classDescriptor0.getTypeConstructor().getParameters().size();
            List list0 = LazyJavaClassDescriptor.this.getTypeConstructor().getParameters();
            Intrinsics.checkNotNullExpressionValue(list0, "getTypeConstructor().parameters");
            int v1 = list0.size();
            if(v1 == v) {
                ArrayList arrayList0 = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(list0, 10));
                for(Object object0: list0) {
                    KotlinType kotlinType0 = ((TypeParameterDescriptor)object0).getDefaultType();
                    arrayList0.add(new TypeProjectionImpl(Variance.INVARIANT, kotlinType0));
                }
                return KotlinTypeFactory.simpleNotNullType(TypeAttributes.Companion.getEmpty(), classDescriptor0, arrayList0);
            }
            if(v1 == 1 && v > 1 && fqName0 == null) {
                KotlinType kotlinType1 = ((TypeParameterDescriptor)kotlin.collections.CollectionsKt.single(list0)).getDefaultType();
                TypeProjectionImpl typeProjectionImpl0 = new TypeProjectionImpl(Variance.INVARIANT, kotlinType1);
                Iterable iterable0 = new IntRange(1, v);
                ArrayList arrayList1 = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(iterable0, 10));
                Iterator iterator1 = iterable0.iterator();
                while(iterator1.hasNext()) {
                    ((IntIterator)iterator1).nextInt();
                    arrayList1.add(typeProjectionImpl0);
                }
                return KotlinTypeFactory.simpleNotNullType(TypeAttributes.Companion.getEmpty(), classDescriptor0, arrayList1);
            }
            return null;
        }

        private final FqName getPurelyImplementsFqNameFromAnnotation() {
            Intrinsics.checkNotNullExpressionValue(JvmAnnotationNames.PURELY_IMPLEMENTS_ANNOTATION, "PURELY_IMPLEMENTS_ANNOTATION");
            AnnotationDescriptor annotationDescriptor0 = LazyJavaClassDescriptor.this.getAnnotations().findAnnotation(JvmAnnotationNames.PURELY_IMPLEMENTS_ANNOTATION);
            if(annotationDescriptor0 == null) {
                return null;
            }
            Object object0 = kotlin.collections.CollectionsKt.singleOrNull(annotationDescriptor0.getAllValueArguments().values());
            StringValue stringValue0 = object0 instanceof StringValue ? ((StringValue)object0) : null;
            if(stringValue0 != null) {
                String s = (String)stringValue0.getValue();
                return s != null && FqNamesUtilKt.isValidJavaFqName(s) ? new FqName(s) : null;
            }
            return null;
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.AbstractTypeConstructor
        protected SupertypeLoopChecker getSupertypeLoopChecker() {
            return LazyJavaClassDescriptor.this.c.getComponents().getSupertypeLoopChecker();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.types.TypeConstructor
        public boolean isDenotable() {
            return true;
        }

        @Override
        public String toString() {
            String s = LazyJavaClassDescriptor.this.getName().asString();
            Intrinsics.checkNotNullExpressionValue(s, "name.asString()");
            return s;
        }
    }

    public static final Companion Companion;
    private static final Set PUBLIC_METHOD_NAMES_IN_OBJECT;
    private final ClassDescriptor additionalSupertypeClassDescriptor;
    private final Annotations annotations;
    private final LazyJavaResolverContext c;
    private final NotNullLazyValue declaredParameters;
    private final InnerClassesScopeWrapper innerClassesScope;
    private final boolean isInner;
    private final JavaClass jClass;
    private final ClassKind kind;
    private final Modality modality;
    private final Lazy moduleAnnotations$delegate;
    private final LazyJavaResolverContext outerContext;
    private final ScopesHolderForClass scopeHolder;
    private final LazyJavaStaticClassScope staticScope;
    private final LazyJavaClassTypeConstructor typeConstructor;
    private final LazyJavaClassMemberScope unsubstitutedMemberScope;
    private final Visibility visibility;

    static {
        LazyJavaClassDescriptor.Companion = new Companion(null);
        LazyJavaClassDescriptor.PUBLIC_METHOD_NAMES_IN_OBJECT = SetsKt.setOf(new String[]{"equals", "hashCode", "getClass", "wait", "notify", "notifyAll", "toString"});
    }

    public LazyJavaClassDescriptor(LazyJavaResolverContext lazyJavaResolverContext0, DeclarationDescriptor declarationDescriptor0, JavaClass javaClass0, ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "outerContext");
        Modality modality0;
        ClassKind classKind0;
        Intrinsics.checkNotNullParameter(declarationDescriptor0, "containingDeclaration");
        Intrinsics.checkNotNullParameter(javaClass0, "jClass");
        super(lazyJavaResolverContext0.getStorageManager(), declarationDescriptor0, javaClass0.getName(), lazyJavaResolverContext0.getComponents().getSourceElementFactory().source(javaClass0), false);
        this.outerContext = lazyJavaResolverContext0;
        this.jClass = javaClass0;
        this.additionalSupertypeClassDescriptor = classDescriptor0;
        LazyJavaResolverContext lazyJavaResolverContext1 = ContextKt.childForClassOrPackage$default(lazyJavaResolverContext0, this, javaClass0, 0, 4, null);
        this.c = lazyJavaResolverContext1;
        lazyJavaResolverContext1.getComponents().getJavaResolverCache().recordClass(javaClass0, this);
        javaClass0.getLightClassOriginKind();
        this.moduleAnnotations$delegate = LazyKt.lazy(new Function0() {
            {
                LazyJavaClassDescriptor.this = lazyJavaClassDescriptor0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                ClassId classId0 = DescriptorUtilsKt.getClassId(LazyJavaClassDescriptor.this);
                return classId0 == null ? null : LazyJavaClassDescriptor.this.getOuterContext().getComponents().getJavaModuleResolver().getAnnotationsForModuleOwnerOfClass(classId0);
            }
        });
        if(javaClass0.isAnnotationType()) {
            classKind0 = ClassKind.ANNOTATION_CLASS;
        }
        else if(javaClass0.isInterface()) {
            classKind0 = ClassKind.INTERFACE;
        }
        else {
            classKind0 = javaClass0.isEnum() ? ClassKind.ENUM_CLASS : ClassKind.CLASS;
        }
        this.kind = classKind0;
        boolean z = true;
        if(javaClass0.isAnnotationType() || javaClass0.isEnum()) {
            modality0 = Modality.FINAL;
        }
        else {
            boolean z1 = javaClass0.isSealed();
            boolean z2 = javaClass0.isSealed() || javaClass0.isAbstract() || javaClass0.isInterface();
            modality0 = Modality.Companion.convertFromFlags(z1, z2, !javaClass0.isFinal());
        }
        this.modality = modality0;
        this.visibility = javaClass0.getVisibility();
        this.isInner = javaClass0.getOuterClass() != null && !javaClass0.isStatic();
        this.typeConstructor = new LazyJavaClassTypeConstructor(this);
        if(classDescriptor0 == null) {
            z = false;
        }
        LazyJavaClassMemberScope lazyJavaClassMemberScope0 = new LazyJavaClassMemberScope(lazyJavaResolverContext1, this, javaClass0, z, null, 16, null);
        this.unsubstitutedMemberScope = lazyJavaClassMemberScope0;
        StorageManager storageManager0 = lazyJavaResolverContext1.getStorageManager();
        KotlinTypeRefiner kotlinTypeRefiner0 = lazyJavaResolverContext1.getComponents().getKotlinTypeChecker().getKotlinTypeRefiner();
        Function1 function10 = new Function1() {
            {
                LazyJavaClassDescriptor.this = lazyJavaClassDescriptor0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((KotlinTypeRefiner)object0));
            }

            public final LazyJavaClassMemberScope invoke(KotlinTypeRefiner kotlinTypeRefiner0) {
                Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "it");
                LazyJavaResolverContext lazyJavaResolverContext0 = LazyJavaClassDescriptor.this.c;
                JavaClass javaClass0 = LazyJavaClassDescriptor.this.getJClass();
                return LazyJavaClassDescriptor.this.additionalSupertypeClassDescriptor == null ? new LazyJavaClassMemberScope(lazyJavaResolverContext0, LazyJavaClassDescriptor.this, javaClass0, false, LazyJavaClassDescriptor.this.unsubstitutedMemberScope) : new LazyJavaClassMemberScope(lazyJavaResolverContext0, LazyJavaClassDescriptor.this, javaClass0, true, LazyJavaClassDescriptor.this.unsubstitutedMemberScope);
            }
        };
        this.scopeHolder = ScopesHolderForClass.Companion.create(this, storageManager0, kotlinTypeRefiner0, function10);
        this.innerClassesScope = new InnerClassesScopeWrapper(lazyJavaClassMemberScope0);
        this.staticScope = new LazyJavaStaticClassScope(lazyJavaResolverContext1, javaClass0, this);
        this.annotations = LazyJavaAnnotationsKt.resolveAnnotations(lazyJavaResolverContext1, javaClass0);
        this.declaredParameters = lazyJavaResolverContext1.getStorageManager().createLazyValue(new Function0() {
            {
                LazyJavaClassDescriptor.this = lazyJavaClassDescriptor0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                Iterable iterable0 = LazyJavaClassDescriptor.this.getJClass().getTypeParameters();
                LazyJavaClassDescriptor lazyJavaClassDescriptor0 = LazyJavaClassDescriptor.this;
                ArrayList arrayList0 = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(iterable0, 10));
                for(Object object0: iterable0) {
                    JavaTypeParameter javaTypeParameter0 = (JavaTypeParameter)object0;
                    TypeParameterDescriptor typeParameterDescriptor0 = LazyJavaClassDescriptor.access$getC$p(lazyJavaClassDescriptor0).getTypeParameterResolver().resolveTypeParameter(javaTypeParameter0);
                    if(typeParameterDescriptor0 == null) {
                        throw new AssertionError("Parameter " + javaTypeParameter0 + " surely belongs to class " + lazyJavaClassDescriptor0.getJClass() + ", so it must be resolved");
                    }
                    arrayList0.add(typeParameterDescriptor0);
                }
                return arrayList0;
            }
        });
    }

    public LazyJavaClassDescriptor(LazyJavaResolverContext lazyJavaResolverContext0, DeclarationDescriptor declarationDescriptor0, JavaClass javaClass0, ClassDescriptor classDescriptor0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 8) != 0) {
            classDescriptor0 = null;
        }
        this(lazyJavaResolverContext0, declarationDescriptor0, javaClass0, classDescriptor0);
    }

    public final LazyJavaClassDescriptor copy$descriptors_jvm(JavaResolverCache javaResolverCache0, ClassDescriptor classDescriptor0) {
        Intrinsics.checkNotNullParameter(javaResolverCache0, "javaResolverCache");
        JavaResolverComponents javaResolverComponents0 = this.c.getComponents().replace(javaResolverCache0);
        LazyJavaResolverContext lazyJavaResolverContext0 = ContextKt.replaceComponents(this.c, javaResolverComponents0);
        DeclarationDescriptor declarationDescriptor0 = this.getContainingDeclaration();
        Intrinsics.checkNotNullExpressionValue(declarationDescriptor0, "containingDeclaration");
        return new LazyJavaClassDescriptor(lazyJavaResolverContext0, declarationDescriptor0, this.jClass, classDescriptor0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    public Annotations getAnnotations() {
        return this.annotations;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassDescriptor getCompanionObjectDescriptor() {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Collection getConstructors() {
        return this.getConstructors();
    }

    public List getConstructors() {
        return (List)this.unsubstitutedMemberScope.getConstructors$descriptors_jvm().invoke();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public List getDeclaredTypeParameters() {
        return (List)this.declaredParameters.invoke();
    }

    public final JavaClass getJClass() {
        return this.jClass;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassKind getKind() {
        return this.kind;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Modality getModality() {
        return this.modality;
    }

    public final List getModuleAnnotations() {
        return (List)this.moduleAnnotations$delegate.getValue();
    }

    public final LazyJavaResolverContext getOuterContext() {
        return this.outerContext;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public Collection getSealedSubclasses() {
        if(this.modality == Modality.SEALED) {
            JavaTypeAttributes javaTypeAttributes0 = JavaTypeAttributesKt.toAttributes$default(TypeUsage.COMMON, false, false, null, 7, null);
            Iterable iterable0 = this.jClass.getPermittedTypes();
            Collection collection0 = new ArrayList();
            for(Object object0: iterable0) {
                ClassifierDescriptor classifierDescriptor0 = this.c.getTypeResolver().transformJavaType(((JavaClassifierType)object0), javaTypeAttributes0).getConstructor().getDeclarationDescriptor();
                ClassDescriptor classDescriptor0 = classifierDescriptor0 instanceof ClassDescriptor ? ((ClassDescriptor)classifierDescriptor0) : null;
                if(classDescriptor0 != null) {
                    collection0.add(classDescriptor0);
                }
            }
            return kotlin.collections.CollectionsKt.sortedWith(((List)collection0), new kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor.getSealedSubclasses..inlined.sortedBy.1());
        }
        return kotlin.collections.CollectionsKt.emptyList();

        public final class kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor.getSealedSubclasses..inlined.sortedBy.1 implements Comparator {
            @Override
            public final int compare(Object object0, Object object1) {
                return ComparisonsKt.compareValues(DescriptorUtilsKt.getFqNameSafe(((ClassDescriptor)object0)).asString(), DescriptorUtilsKt.getFqNameSafe(((ClassDescriptor)object1)).asString());
            }
        }

    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public MemberScope getStaticScope() {
        return this.staticScope;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor
    public TypeConstructor getTypeConstructor() {
        return this.typeConstructor;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public MemberScope getUnsubstitutedInnerClassesScope() {
        return this.innerClassesScope;
    }

    public LazyJavaClassMemberScope getUnsubstitutedMemberScope() {
        MemberScope memberScope0 = super.getUnsubstitutedMemberScope();
        Intrinsics.checkNotNull(memberScope0, "null cannot be cast to non-null type org.jetbrains.kotlin.load.java.lazy.descriptors.LazyJavaClassMemberScope");
        return (LazyJavaClassMemberScope)memberScope0;
    }

    protected LazyJavaClassMemberScope getUnsubstitutedMemberScope(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        return (LazyJavaClassMemberScope)this.scopeHolder.getScope(kotlinTypeRefiner0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.AbstractClassDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public MemberScope getUnsubstitutedMemberScope() {
        return this.getUnsubstitutedMemberScope();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleAwareClassDescriptor
    public MemberScope getUnsubstitutedMemberScope(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.getUnsubstitutedMemberScope(kotlinTypeRefiner0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ClassConstructorDescriptor getUnsubstitutedPrimaryConstructor() {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public ValueClassRepresentation getValueClassRepresentation() {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public DescriptorVisibility getVisibility() {
        if(Intrinsics.areEqual(this.visibility, DescriptorVisibilities.PRIVATE) && this.jClass.getOuterClass() == null) {
            Intrinsics.checkNotNullExpressionValue(JavaDescriptorVisibilities.PACKAGE_VISIBILITY, "{\n            JavaDescri…KAGE_VISIBILITY\n        }");
            return JavaDescriptorVisibilities.PACKAGE_VISIBILITY;
        }
        return UtilsKt.toDescriptorVisibility(this.visibility);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isActual() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isCompanionObject() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isData() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.MemberDescriptor
    public boolean isExpect() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isFun() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isInline() {
        return false;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptorWithTypeParameters
    public boolean isInner() {
        return this.isInner;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor
    public boolean isValue() {
        return false;
    }

    @Override
    public String toString() {
        return "Lazy Java class " + DescriptorUtilsKt.getFqNameUnsafe(this);
    }
}

