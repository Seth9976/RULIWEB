package kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.ContextKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaAnnotationsKt;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.LazyJavaResolverContext;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaClass;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaPackage;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinClassFinderKt;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryClass;
import kotlin.reflect.jvm.internal.impl.load.kotlin.KotlinJvmBinaryPackageSourceElement;
import kotlin.reflect.jvm.internal.impl.load.kotlin.PackagePartProvider;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader.Kind;
import kotlin.reflect.jvm.internal.impl.load.kotlin.header.KotlinClassHeader;
import kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization.JvmMetadataVersion;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.resolve.jvm.JvmClassName;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.utils.DeserializationHelpersKt;

public final class LazyJavaPackageFragment extends PackageFragmentDescriptorImpl {
    static final KProperty[] $$delegatedProperties;
    private final Annotations annotations;
    private final NotNullLazyValue binaryClasses$delegate;
    private final LazyJavaResolverContext c;
    private final JavaPackage jPackage;
    private final JvmMetadataVersion jvmMetadataVersion;
    private final NotNullLazyValue partToFacade$delegate;
    private final JvmPackageScope scope;
    private final NotNullLazyValue subPackages;

    static {
        LazyJavaPackageFragment.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaPackageFragment.class), "binaryClasses", "getBinaryClasses$descriptors_jvm()Ljava/util/Map;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyJavaPackageFragment.class), "partToFacade", "getPartToFacade()Ljava/util/HashMap;"))};
    }

    public LazyJavaPackageFragment(LazyJavaResolverContext lazyJavaResolverContext0, JavaPackage javaPackage0) {
        Intrinsics.checkNotNullParameter(lazyJavaResolverContext0, "outerContext");
        Intrinsics.checkNotNullParameter(javaPackage0, "jPackage");
        super(lazyJavaResolverContext0.getModule(), javaPackage0.getFqName());
        this.jPackage = javaPackage0;
        LazyJavaResolverContext lazyJavaResolverContext1 = ContextKt.childForClassOrPackage$default(lazyJavaResolverContext0, this, null, 0, 6, null);
        this.c = lazyJavaResolverContext1;
        this.jvmMetadataVersion = DeserializationHelpersKt.jvmMetadataVersionOrDefault(lazyJavaResolverContext0.getComponents().getDeserializedDescriptorResolver().getComponents().getConfiguration());
        this.binaryClasses$delegate = lazyJavaResolverContext1.getStorageManager().createLazyValue(new Function0() {
            {
                LazyJavaPackageFragment.this = lazyJavaPackageFragment0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Map invoke() {
                PackagePartProvider packagePartProvider0 = LazyJavaPackageFragment.access$getC$p(LazyJavaPackageFragment.this).getComponents().getPackagePartProvider();
                String s = LazyJavaPackageFragment.this.getFqName().asString();
                Intrinsics.checkNotNullExpressionValue(s, "fqName.asString()");
                Iterable iterable0 = packagePartProvider0.findPackageParts(s);
                LazyJavaPackageFragment lazyJavaPackageFragment0 = LazyJavaPackageFragment.this;
                Collection collection0 = new ArrayList();
                for(Object object0: iterable0) {
                    ClassId classId0 = ClassId.topLevel(JvmClassName.byInternalName(((String)object0)).getFqNameForTopLevelClassMaybeWithDollars());
                    Intrinsics.checkNotNullExpressionValue(classId0, "topLevel(JvmClassName.by…velClassMaybeWithDollars)");
                    KotlinJvmBinaryClass kotlinJvmBinaryClass0 = KotlinClassFinderKt.findKotlinClass(LazyJavaPackageFragment.access$getC$p(lazyJavaPackageFragment0).getComponents().getKotlinClassFinder(), classId0, LazyJavaPackageFragment.access$getJvmMetadataVersion$p(lazyJavaPackageFragment0));
                    Pair pair0 = kotlinJvmBinaryClass0 == null ? null : TuplesKt.to(((String)object0), kotlinJvmBinaryClass0);
                    if(pair0 != null) {
                        collection0.add(pair0);
                    }
                }
                return MapsKt.toMap(((List)collection0));
            }
        });
        this.scope = new JvmPackageScope(lazyJavaResolverContext1, javaPackage0, this);
        this.subPackages = lazyJavaResolverContext1.getStorageManager().createRecursionTolerantLazyValue(new Function0() {
            {
                LazyJavaPackageFragment.this = lazyJavaPackageFragment0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                Iterable iterable0 = LazyJavaPackageFragment.this.jPackage.getSubPackages();
                ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
                for(Object object0: iterable0) {
                    arrayList0.add(((JavaPackage)object0).getFqName());
                }
                return arrayList0;
            }
        }, CollectionsKt.emptyList());
        this.annotations = lazyJavaResolverContext1.getComponents().getJavaTypeEnhancementState().getDisabledDefaultAnnotations() ? Annotations.Companion.getEMPTY() : LazyJavaAnnotationsKt.resolveAnnotations(lazyJavaResolverContext1, javaPackage0);
        this.partToFacade$delegate = lazyJavaResolverContext1.getStorageManager().createLazyValue(new Function0() {
            public final class WhenMappings {
                public static final int[] $EnumSwitchMapping$0;

                static {
                    int[] arr_v = new int[Kind.values().length];
                    try {
                        arr_v[Kind.MULTIFILE_CLASS_PART.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError unused_ex) {
                    }
                    try {
                        arr_v[Kind.FILE_FACADE.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError unused_ex) {
                    }
                    WhenMappings.$EnumSwitchMapping$0 = arr_v;
                }
            }

            {
                LazyJavaPackageFragment.this = lazyJavaPackageFragment0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final HashMap invoke() {
                HashMap hashMap0 = new HashMap();
                for(Object object0: LazyJavaPackageFragment.this.getBinaryClasses$descriptors_jvm().entrySet()) {
                    String s = (String)((Map.Entry)object0).getKey();
                    KotlinJvmBinaryClass kotlinJvmBinaryClass0 = (KotlinJvmBinaryClass)((Map.Entry)object0).getValue();
                    JvmClassName jvmClassName0 = JvmClassName.byInternalName(s);
                    Intrinsics.checkNotNullExpressionValue(jvmClassName0, "byInternalName(partInternalName)");
                    KotlinClassHeader kotlinClassHeader0 = kotlinJvmBinaryClass0.getClassHeader();
                    switch(WhenMappings.$EnumSwitchMapping$0[kotlinClassHeader0.getKind().ordinal()]) {
                        case 1: {
                            String s1 = kotlinClassHeader0.getMultifileClassName();
                            if(s1 == null) {
                                continue;
                            }
                            JvmClassName jvmClassName1 = JvmClassName.byInternalName(s1);
                            Intrinsics.checkNotNullExpressionValue(jvmClassName1, "byInternalName(header.mu…: continue@kotlinClasses)");
                            hashMap0.put(jvmClassName0, jvmClassName1);
                            break;
                        }
                        case 2: {
                            hashMap0.put(jvmClassName0, jvmClassName0);
                        }
                    }
                }
                return hashMap0;
            }
        });
    }

    public static final LazyJavaResolverContext access$getC$p(LazyJavaPackageFragment lazyJavaPackageFragment0) {
        return lazyJavaPackageFragment0.c;
    }

    public static final JvmMetadataVersion access$getJvmMetadataVersion$p(LazyJavaPackageFragment lazyJavaPackageFragment0) {
        return lazyJavaPackageFragment0.jvmMetadataVersion;
    }

    public final ClassDescriptor findClassifierByJavaClass$descriptors_jvm(JavaClass javaClass0) {
        Intrinsics.checkNotNullParameter(javaClass0, "jClass");
        return this.scope.getJavaScope$descriptors_jvm().findClassifierByJavaClass$descriptors_jvm(javaClass0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.annotations.AnnotatedImpl, kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotated
    public Annotations getAnnotations() {
        return this.annotations;
    }

    public final Map getBinaryClasses$descriptors_jvm() {
        return (Map)StorageKt.getValue(this.binaryClasses$delegate, this, LazyJavaPackageFragment.$$delegatedProperties[0]);
    }

    public JvmPackageScope getMemberScope() {
        return this.scope;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor
    public MemberScope getMemberScope() {
        return this.getMemberScope();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl
    public SourceElement getSource() {
        return new KotlinJvmBinaryPackageSourceElement(this);
    }

    public final List getSubPackageFqNames$descriptors_jvm() {
        return (List)this.subPackages.invoke();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl
    public String toString() {
        return "Lazy Java package fragment: " + this.getFqName() + " of module " + this.c.getComponents().getModule();
    }
}

