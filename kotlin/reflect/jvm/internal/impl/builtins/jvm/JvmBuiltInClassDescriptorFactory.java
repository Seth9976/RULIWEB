package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.BuiltInsPackageFragment;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames.FqNames;
import kotlin.reflect.jvm.internal.impl.builtins.StandardNames;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassKind;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.Modality;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SourceElement;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ClassDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.ClassId;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public final class JvmBuiltInClassDescriptorFactory implements ClassDescriptorFactory {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final ClassId getCLONEABLE_CLASS_ID() {
            return JvmBuiltInClassDescriptorFactory.CLONEABLE_CLASS_ID;
        }
    }

    static final KProperty[] $$delegatedProperties;
    private static final ClassId CLONEABLE_CLASS_ID;
    private static final Name CLONEABLE_NAME;
    public static final Companion Companion;
    private static final FqName KOTLIN_FQ_NAME;
    private final NotNullLazyValue cloneable$delegate;
    private final Function1 computeContainingDeclaration;
    private final ModuleDescriptor moduleDescriptor;

    static {
        JvmBuiltInClassDescriptorFactory.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmBuiltInClassDescriptorFactory.class), "cloneable", "getCloneable()Lorg/jetbrains/kotlin/descriptors/impl/ClassDescriptorImpl;"))};
        JvmBuiltInClassDescriptorFactory.Companion = new Companion(null);
        JvmBuiltInClassDescriptorFactory.KOTLIN_FQ_NAME = StandardNames.BUILT_INS_PACKAGE_FQ_NAME;
        Name name0 = FqNames.cloneable.shortName();
        Intrinsics.checkNotNullExpressionValue(name0, "cloneable.shortName()");
        JvmBuiltInClassDescriptorFactory.CLONEABLE_NAME = name0;
        ClassId classId0 = ClassId.topLevel(FqNames.cloneable.toSafe());
        Intrinsics.checkNotNullExpressionValue(classId0, "topLevel(StandardNames.FqNames.cloneable.toSafe())");
        JvmBuiltInClassDescriptorFactory.CLONEABLE_CLASS_ID = classId0;
    }

    public JvmBuiltInClassDescriptorFactory(StorageManager storageManager0, ModuleDescriptor moduleDescriptor0, Function1 function10) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "moduleDescriptor");
        Intrinsics.checkNotNullParameter(function10, "computeContainingDeclaration");
        super();
        this.moduleDescriptor = moduleDescriptor0;
        this.computeContainingDeclaration = function10;
        this.cloneable$delegate = storageManager0.createLazyValue(new Function0(storageManager0) {
            final StorageManager $storageManager;

            {
                JvmBuiltInClassDescriptorFactory.this = jvmBuiltInClassDescriptorFactory0;
                this.$storageManager = storageManager0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final ClassDescriptorImpl invoke() {
                DeclarationDescriptor declarationDescriptor0 = (DeclarationDescriptor)JvmBuiltInClassDescriptorFactory.access$getComputeContainingDeclaration$p(JvmBuiltInClassDescriptorFactory.this).invoke(JvmBuiltInClassDescriptorFactory.access$getModuleDescriptor$p(JvmBuiltInClassDescriptorFactory.this));
                Collection collection0 = CollectionsKt.listOf(JvmBuiltInClassDescriptorFactory.access$getModuleDescriptor$p(JvmBuiltInClassDescriptorFactory.this).getBuiltIns().getAnyType());
                ClassDescriptorImpl classDescriptorImpl0 = new ClassDescriptorImpl(declarationDescriptor0, JvmBuiltInClassDescriptorFactory.access$getCLONEABLE_NAME$cp(), Modality.ABSTRACT, ClassKind.INTERFACE, collection0, SourceElement.NO_SOURCE, false, this.$storageManager);
                classDescriptorImpl0.initialize(new CloneableClassScope(this.$storageManager, classDescriptorImpl0), SetsKt.emptySet(), null);
                return classDescriptorImpl0;
            }
        });
    }

    public JvmBuiltInClassDescriptorFactory(StorageManager storageManager0, ModuleDescriptor moduleDescriptor0, Function1 function10, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 4) != 0) {
            function10 = kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInClassDescriptorFactory.1.INSTANCE;
        }
        this(storageManager0, moduleDescriptor0, function10);

        final class kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInClassDescriptorFactory.1 extends Lambda implements Function1 {
            public static final kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInClassDescriptorFactory.1 INSTANCE;

            static {
                kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInClassDescriptorFactory.1.INSTANCE = new kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInClassDescriptorFactory.1();
            }

            kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInClassDescriptorFactory.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((ModuleDescriptor)object0));
            }

            public final BuiltInsPackageFragment invoke(ModuleDescriptor moduleDescriptor0) {
                Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
                Iterable iterable0 = moduleDescriptor0.getPackage(JvmBuiltInClassDescriptorFactory.KOTLIN_FQ_NAME).getFragments();
                Collection collection0 = new ArrayList();
                for(Object object0: iterable0) {
                    if(object0 instanceof BuiltInsPackageFragment) {
                        collection0.add(object0);
                    }
                }
                return (BuiltInsPackageFragment)CollectionsKt.first(((List)collection0));
            }
        }

    }

    public static final Name access$getCLONEABLE_NAME$cp() {
        return JvmBuiltInClassDescriptorFactory.CLONEABLE_NAME;
    }

    public static final Function1 access$getComputeContainingDeclaration$p(JvmBuiltInClassDescriptorFactory jvmBuiltInClassDescriptorFactory0) {
        return jvmBuiltInClassDescriptorFactory0.computeContainingDeclaration;
    }

    public static final ModuleDescriptor access$getModuleDescriptor$p(JvmBuiltInClassDescriptorFactory jvmBuiltInClassDescriptorFactory0) {
        return jvmBuiltInClassDescriptorFactory0.moduleDescriptor;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory
    public ClassDescriptor createClass(ClassId classId0) {
        Intrinsics.checkNotNullParameter(classId0, "classId");
        return Intrinsics.areEqual(classId0, JvmBuiltInClassDescriptorFactory.CLONEABLE_CLASS_ID) ? this.getCloneable() : null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory
    public Collection getAllContributedClassesIfPossible(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "packageFqName");
        return Intrinsics.areEqual(fqName0, JvmBuiltInClassDescriptorFactory.KOTLIN_FQ_NAME) ? SetsKt.setOf(this.getCloneable()) : SetsKt.emptySet();
    }

    private final ClassDescriptorImpl getCloneable() {
        return (ClassDescriptorImpl)StorageKt.getValue(this.cloneable$delegate, this, JvmBuiltInClassDescriptorFactory.$$delegatedProperties[0]);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.ClassDescriptorFactory
    public boolean shouldCreateClass(FqName fqName0, Name name0) {
        Intrinsics.checkNotNullParameter(fqName0, "packageFqName");
        Intrinsics.checkNotNullParameter(name0, "name");
        return Intrinsics.areEqual(name0, JvmBuiltInClassDescriptorFactory.CLONEABLE_NAME) && Intrinsics.areEqual(fqName0, JvmBuiltInClassDescriptorFactory.KOTLIN_FQ_NAME);
    }
}

