package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderKt;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.ChainedMemberScope;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.LazyScopeAdapter;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope.Empty;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public class LazyPackageViewDescriptorImpl extends DeclarationDescriptorImpl implements PackageViewDescriptor {
    static final KProperty[] $$delegatedProperties;
    private final NotNullLazyValue empty$delegate;
    private final FqName fqName;
    private final NotNullLazyValue fragments$delegate;
    private final MemberScope memberScope;
    private final ModuleDescriptorImpl module;

    static {
        LazyPackageViewDescriptorImpl.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyPackageViewDescriptorImpl.class), "fragments", "getFragments()Ljava/util/List;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(LazyPackageViewDescriptorImpl.class), "empty", "getEmpty()Z"))};
    }

    public LazyPackageViewDescriptorImpl(ModuleDescriptorImpl moduleDescriptorImpl0, FqName fqName0, StorageManager storageManager0) {
        Intrinsics.checkNotNullParameter(moduleDescriptorImpl0, "module");
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Name name0 = fqName0.shortNameOrSpecial();
        super(Annotations.Companion.getEMPTY(), name0);
        this.module = moduleDescriptorImpl0;
        this.fqName = fqName0;
        this.fragments$delegate = storageManager0.createLazyValue(new Function0() {
            {
                LazyPackageViewDescriptorImpl.this = lazyPackageViewDescriptorImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                return PackageFragmentProviderKt.packageFragments(LazyPackageViewDescriptorImpl.this.getModule().getPackageFragmentProvider(), LazyPackageViewDescriptorImpl.this.getFqName());
            }
        });
        this.empty$delegate = storageManager0.createLazyValue(new Function0() {
            {
                LazyPackageViewDescriptorImpl.this = lazyPackageViewDescriptorImpl0;
                super(0);
            }

            public final Boolean invoke() {
                return Boolean.valueOf(PackageFragmentProviderKt.isEmpty(LazyPackageViewDescriptorImpl.this.getModule().getPackageFragmentProvider(), LazyPackageViewDescriptorImpl.this.getFqName()));
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        });
        this.memberScope = new LazyScopeAdapter(storageManager0, new Function0() {
            {
                LazyPackageViewDescriptorImpl.this = lazyPackageViewDescriptorImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final MemberScope invoke() {
                if(LazyPackageViewDescriptorImpl.this.isEmpty()) {
                    return Empty.INSTANCE;
                }
                Iterable iterable0 = LazyPackageViewDescriptorImpl.this.getFragments();
                ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
                for(Object object0: iterable0) {
                    arrayList0.add(((PackageFragmentDescriptor)object0).getMemberScope());
                }
                List list0 = CollectionsKt.plus(arrayList0, new SubpackagesScope(LazyPackageViewDescriptorImpl.this.getModule(), LazyPackageViewDescriptorImpl.this.getFqName()));
                return ChainedMemberScope.Companion.create("package view scope for " + LazyPackageViewDescriptorImpl.this.getFqName() + " in " + LazyPackageViewDescriptorImpl.this.getModule().getName(), list0);
            }
        });
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public Object accept(DeclarationDescriptorVisitor declarationDescriptorVisitor0, Object object0) {
        Intrinsics.checkNotNullParameter(declarationDescriptorVisitor0, "visitor");
        return declarationDescriptorVisitor0.visitPackageViewDescriptor(this, object0);
    }

    // 去混淆评级： 低(25)
    @Override
    public boolean equals(Object object0) {
        PackageViewDescriptor packageViewDescriptor0 = object0 instanceof PackageViewDescriptor ? ((PackageViewDescriptor)object0) : null;
        return packageViewDescriptor0 == null ? false : Intrinsics.areEqual(this.getFqName(), packageViewDescriptor0.getFqName()) && Intrinsics.areEqual(this.getModule(), packageViewDescriptor0.getModule());
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public DeclarationDescriptor getContainingDeclaration() {
        return this.getContainingDeclaration();
    }

    public PackageViewDescriptor getContainingDeclaration() {
        if(this.getFqName().isRoot()) {
            return null;
        }
        FqName fqName0 = this.getFqName().parent();
        Intrinsics.checkNotNullExpressionValue(fqName0, "fqName.parent()");
        return this.getModule().getPackage(fqName0);
    }

    protected final boolean getEmpty() {
        return ((Boolean)StorageKt.getValue(this.empty$delegate, this, LazyPackageViewDescriptorImpl.$$delegatedProperties[1])).booleanValue();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor
    public FqName getFqName() {
        return this.fqName;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor
    public List getFragments() {
        return (List)StorageKt.getValue(this.fragments$delegate, this, LazyPackageViewDescriptorImpl.$$delegatedProperties[0]);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor
    public MemberScope getMemberScope() {
        return this.memberScope;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor
    public ModuleDescriptor getModule() {
        return this.getModule();
    }

    public ModuleDescriptorImpl getModule() {
        return this.module;
    }

    @Override
    public int hashCode() {
        return this.getModule().hashCode() * 0x1F + this.getFqName().hashCode();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor
    public boolean isEmpty() {
        return this.getEmpty();
    }
}

