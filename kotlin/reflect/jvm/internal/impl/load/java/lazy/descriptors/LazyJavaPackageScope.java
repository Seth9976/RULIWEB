package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassifierDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.incremental.components.LookupLocation;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder.Request;
import kotlin.reflect.jvm.internal.impl.load.java.JavaClassFinder;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.load.java.structure.LightClassOriginKind;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder.Result.ClassFileContent;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinder.Result;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinderKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader.Kind;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.name.SpecialNames;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.DescriptorKindFilter;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.NullableLazyValue;
import kotlin.reflect.jvm.internal.impl.utils.DeserializationHelpersKt;
import kotlin.reflect.jvm.internal.impl.utils.FunctionsKt;

public final class LazyJavaPackageScope extends LazyJavaStaticScope {
    static final class FindClassRequest {
        private final JavaClass javaClass;
        private final Name name;

        public FindClassRequest(Name name0, JavaClass javaClass0) {
            Intrinsics.checkNotNullParameter(name0, "name");
            super();
            this.name = name0;
            this.javaClass = javaClass0;
        }

        // 去混淆评级： 低(20)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof FindClassRequest && Intrinsics.areEqual(this.name, ((FindClassRequest)object0).name);
        }

        public final JavaClass getJavaClass() {
            return this.javaClass;
        }

        public final Name getName() {
            return this.name;
        }

        @Override
        public int hashCode() {
            return this.name.hashCode();
        }
    }

    static abstract class KotlinClassLookupResult {
        public static final class Found extends KotlinClassLookupResult {
            private final ClassDescriptor descriptor;

            public Found(ClassDescriptor classDescriptor0) {
                Intrinsics.checkNotNullParameter(classDescriptor0, "descriptor");
                super(null);
                this.descriptor = classDescriptor0;
            }

            public final ClassDescriptor getDescriptor() {
                return this.descriptor;
            }
        }

        public static final class NotFound extends KotlinClassLookupResult {
            public static final NotFound INSTANCE;

            static {
                NotFound.INSTANCE = new NotFound();
            }

            private NotFound() {
                super(null);
            }
        }

        public static final class SyntheticClass extends KotlinClassLookupResult {
            public static final SyntheticClass INSTANCE;

            static {
                SyntheticClass.INSTANCE = new SyntheticClass();
            }

            private SyntheticClass() {
                super(null);
            }
        }

        private KotlinClassLookupResult() {
        }

        public KotlinClassLookupResult(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    private final MemoizedFunctionToNullable classes;
    private final JavaPackage jPackage;
    private final NullableLazyValue knownClassNamesInPackage;
    private final LazyJavaPackageFragment ownerDescriptor;

    public LazyJavaPackageScope(LazyJavaResolverContext lazyJavaResolverContext0, JavaPackage javaPackage0, LazyJavaPackageFragment lazyJavaPackageFragment0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "c");
        Intrinsics.checkNotNullParameter(javaPackage0, "jPackage");
        Intrinsics.checkNotNullParameter(lazyJavaPackageFragment0, "ownerDescriptor");
        super(lazyJavaResolverContext0);
        this.jPackage = javaPackage0;
        this.ownerDescriptor = lazyJavaPackageFragment0;
        this.knownClassNamesInPackage = lazyJavaResolverContext0.getStorageManager().createNullableLazyValue(new Function0(this) {
            final LazyJavaResolverContext $c;

            {
                this.$c = lazyJavaResolverContext0;
                LazyJavaPackageScope.this = lazyJavaPackageScope0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Set invoke() {
                return this.$c.getComponents().getFinder().knownClassNamesInPackage(LazyJavaPackageScope.this.getOwnerDescriptor().getFqName());
            }
        });
        this.classes = lazyJavaResolverContext0.getStorageManager().createMemoizedFunctionWithNullableValues(new Function1(lazyJavaResolverContext0) {
            final LazyJavaResolverContext $c;

            {
                LazyJavaPackageScope.this = lazyJavaPackageScope0;
                this.$c = lazyJavaResolverContext0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((FindClassRequest)object0));
            }

            public final ClassDescriptor invoke(FindClassRequest lazyJavaPackageScope$FindClassRequest0) {
                Intrinsics.checkNotNullParameter(lazyJavaPackageScope$FindClassRequest0, "request");
                ClassId classId0 = new ClassId(LazyJavaPackageScope.this.getOwnerDescriptor().getFqName(), lazyJavaPackageScope$FindClassRequest0.getName());
                Result kotlinClassFinder$Result0 = lazyJavaPackageScope$FindClassRequest0.getJavaClass() == null ? this.$c.getComponents().getKotlinClassFinder().findKotlinClassOrContent(classId0, LazyJavaPackageScope.this.getJvmMetadataVersion()) : this.$c.getComponents().getKotlinClassFinder().findKotlinClassOrContent(lazyJavaPackageScope$FindClassRequest0.getJavaClass(), LazyJavaPackageScope.this.getJvmMetadataVersion());
                KotlinJvmBinaryClass kotlinJvmBinaryClass0 = kotlinClassFinder$Result0 == null ? null : kotlinClassFinder$Result0.toKotlinJvmBinaryClass();
                ClassId classId1 = kotlinJvmBinaryClass0 == null ? null : kotlinJvmBinaryClass0.getClassId();
                if(classId1 != null && (classId1.isNestedClass() || classId1.isLocal())) {
                    return null;
                }
                KotlinClassLookupResult lazyJavaPackageScope$KotlinClassLookupResult0 = LazyJavaPackageScope.this.resolveKotlinBinaryClass(kotlinJvmBinaryClass0);
                if(lazyJavaPackageScope$KotlinClassLookupResult0 instanceof Found) {
                    return ((Found)lazyJavaPackageScope$KotlinClassLookupResult0).getDescriptor();
                }
                if(lazyJavaPackageScope$KotlinClassLookupResult0 instanceof SyntheticClass) {
                    return null;
                }
                if(!(lazyJavaPackageScope$KotlinClassLookupResult0 instanceof NotFound)) {
                    throw new NoWhenBranchMatchedException();
                }
                JavaClass javaClass0 = lazyJavaPackageScope$FindClassRequest0.getJavaClass();
                if(javaClass0 == null) {
                    JavaClassFinder javaClassFinder0 = this.$c.getComponents().getFinder();
                    ClassFileContent kotlinClassFinder$Result$ClassFileContent0 = kotlinClassFinder$Result0 instanceof ClassFileContent ? ((ClassFileContent)kotlinClassFinder$Result0) : null;
                    javaClass0 = javaClassFinder0.findClass(new Request(classId0, (kotlinClassFinder$Result$ClassFileContent0 == null ? null : kotlinClassFinder$Result$ClassFileContent0.getContent()), null, 4, null));
                }
                if((javaClass0 == null ? null : javaClass0.getLightClassOriginKind()) == LightClassOriginKind.BINARY) {
                    throw new IllegalStateException("Couldn\'t find kotlin binary class for light class created by kotlin binary file\nJavaClass: " + javaClass0 + "\nClassId: " + classId0 + "\nfindKotlinClass(JavaClass) = " + KotlinClassFinderKt.findKotlinClass(this.$c.getComponents().getKotlinClassFinder(), javaClass0, LazyJavaPackageScope.this.getJvmMetadataVersion()) + "\nfindKotlinClass(ClassId) = " + KotlinClassFinderKt.findKotlinClass(this.$c.getComponents().getKotlinClassFinder(), classId0, LazyJavaPackageScope.this.getJvmMetadataVersion()) + '\n');
                }
                FqName fqName0 = javaClass0 == null ? null : javaClass0.getFqName();
                if(fqName0 != null && !fqName0.isRoot() && Intrinsics.areEqual(fqName0.parent(), LazyJavaPackageScope.this.getOwnerDescriptor().getFqName())) {
                    LazyJavaClassDescriptor lazyJavaClassDescriptor0 = new LazyJavaClassDescriptor(this.$c, LazyJavaPackageScope.this.getOwnerDescriptor(), javaClass0, null, 8, null);
                    this.$c.getComponents().getJavaClassesTracker().reportClass(lazyJavaClassDescriptor0);
                    return lazyJavaClassDescriptor0;
                }
                return null;
            }
        });
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected Set computeClassNames(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        if(!descriptorKindFilter0.acceptsKinds(1)) {
            return SetsKt.emptySet();
        }
        Set set0 = (Set)this.knownClassNamesInPackage.invoke();
        if(set0 != null) {
            Collection collection0 = new HashSet();
            for(Object object0: set0) {
                collection0.add(Name.identifier(((String)object0)));
            }
            return (Set)collection0;
        }
        JavaPackage javaPackage0 = this.jPackage;
        if(function10 == null) {
            function10 = FunctionsKt.alwaysTrue();
        }
        Iterable iterable0 = javaPackage0.getClasses(function10);
        Collection collection1 = new LinkedHashSet();
        for(Object object1: iterable0) {
            JavaClass javaClass0 = (JavaClass)object1;
            Name name0 = javaClass0.getLightClassOriginKind() == LightClassOriginKind.SOURCE ? null : javaClass0.getName();
            if(name0 != null) {
                collection1.add(name0);
            }
        }
        return (Set)collection1;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected Set computeFunctionNames(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        return SetsKt.emptySet();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected DeclaredMemberIndex computeMemberIndex() {
        return Empty.INSTANCE;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected void computeNonDeclaredFunctions(Collection collection0, Name name0) {
        Intrinsics.checkNotNullParameter(collection0, "result");
        Intrinsics.checkNotNullParameter(name0, "name");
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    protected Set computePropertyNames(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        return SetsKt.emptySet();
    }

    private final ClassDescriptor findClassifier(Name name0, JavaClass javaClass0) {
        if(!SpecialNames.INSTANCE.isSafeIdentifier(name0)) {
            return null;
        }
        Set set0 = (Set)this.knownClassNamesInPackage.invoke();
        if(javaClass0 == null && set0 != null && !set0.contains(name0.asString())) {
            return null;
        }
        FindClassRequest lazyJavaPackageScope$FindClassRequest0 = new FindClassRequest(name0, javaClass0);
        return (ClassDescriptor)this.classes.invoke(lazyJavaPackageScope$FindClassRequest0);
    }

    public final ClassDescriptor findClassifierByJavaClass$descriptors_jvm(JavaClass javaClass0) {
        Intrinsics.checkNotNullParameter(javaClass0, "javaClass");
        return this.findClassifier(javaClass0.getName(), javaClass0);
    }

    public ClassDescriptor getContributedClassifier(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        return this.findClassifier(name0, null);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScopeImpl
    public ClassifierDescriptor getContributedClassifier(Name name0, LookupLocation lookupLocation0) {
        return this.getContributedClassifier(name0, lookupLocation0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    public Collection getContributedDescriptors(DescriptorKindFilter descriptorKindFilter0, Function1 function10) {
        Intrinsics.checkNotNullParameter(descriptorKindFilter0, "kindFilter");
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
        if(!descriptorKindFilter0.acceptsKinds(7)) {
            return CollectionsKt.emptyList();
        }
        Iterable iterable0 = (Iterable)this.getAllDescriptors().invoke();
        Collection collection0 = new ArrayList();
        for(Object object0: iterable0) {
            DeclarationDescriptor declarationDescriptor0 = (DeclarationDescriptor)object0;
            if(declarationDescriptor0 instanceof ClassDescriptor) {
                Name name0 = ((ClassDescriptor)declarationDescriptor0).getName();
                Intrinsics.checkNotNullExpressionValue(name0, "it.name");
                if(((Boolean)function10.invoke(name0)).booleanValue()) {
                    collection0.add(object0);
                }
            }
        }
        return (List)collection0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    public Collection getContributedVariables(Name name0, LookupLocation lookupLocation0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        Intrinsics.checkNotNullParameter(lookupLocation0, "location");
        return CollectionsKt.emptyList();
    }

    private final JvmMetadataVersion getJvmMetadataVersion() {
        return DeserializationHelpersKt.jvmMetadataVersionOrDefault(this.getC().getComponents().getDeserializedDescriptorResolver().getComponents().getConfiguration());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaScope
    public DeclarationDescriptor getOwnerDescriptor() {
        return this.getOwnerDescriptor();
    }

    protected LazyJavaPackageFragment getOwnerDescriptor() {
        return this.ownerDescriptor;
    }

    private final KotlinClassLookupResult resolveKotlinBinaryClass(KotlinJvmBinaryClass kotlinJvmBinaryClass0) {
        if(kotlinJvmBinaryClass0 == null) {
            return NotFound.INSTANCE;
        }
        if(kotlinJvmBinaryClass0.getClassHeader().getKind() == Kind.CLASS) {
            ClassDescriptor classDescriptor0 = this.getC().getComponents().getDeserializedDescriptorResolver().resolveClass(kotlinJvmBinaryClass0);
            return classDescriptor0 != null ? new Found(classDescriptor0) : NotFound.INSTANCE;
        }
        return SyntheticClass.INSTANCE;
    }
}

