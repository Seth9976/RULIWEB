package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorVisitor;
import kotlin.reflect.jvm.internal.impl.descriptors.InvalidModuleExceptionKt;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleCapability;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor.DefaultImpls;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.annotations.Annotations;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.platform.TargetPlatform;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNotNull;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public final class ModuleDescriptorImpl extends DeclarationDescriptorImpl implements ModuleDescriptor {
    private final KotlinBuiltIns builtIns;
    private final Map capabilities;
    private ModuleDependencies dependencies;
    private boolean isValid;
    private PackageFragmentProvider packageFragmentProviderForModuleContent;
    private final Lazy packageFragmentProviderForWholeModuleWithDependencies$delegate;
    private final PackageViewDescriptorFactory packageViewDescriptorFactory;
    private final MemoizedFunctionToNotNull packages;
    private final TargetPlatform platform;
    private final Name stableName;
    private final StorageManager storageManager;

    public ModuleDescriptorImpl(Name name0, StorageManager storageManager0, KotlinBuiltIns kotlinBuiltIns0, TargetPlatform targetPlatform0) {
        Intrinsics.checkNotNullParameter(name0, "moduleName");
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(kotlinBuiltIns0, "builtIns");
        this(name0, storageManager0, kotlinBuiltIns0, targetPlatform0, null, null, 0x30, null);
    }

    public ModuleDescriptorImpl(Name name0, StorageManager storageManager0, KotlinBuiltIns kotlinBuiltIns0, TargetPlatform targetPlatform0, Map map0, Name name1) {
        Intrinsics.checkNotNullParameter(name0, "moduleName");
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(kotlinBuiltIns0, "builtIns");
        Intrinsics.checkNotNullParameter(map0, "capabilities");
        super(Annotations.Companion.getEMPTY(), name0);
        this.storageManager = storageManager0;
        this.builtIns = kotlinBuiltIns0;
        this.platform = targetPlatform0;
        this.stableName = name1;
        if(!name0.isSpecial()) {
            throw new IllegalArgumentException("Module name must be special: " + name0);
        }
        this.capabilities = map0;
        PackageViewDescriptorFactory packageViewDescriptorFactory0 = (PackageViewDescriptorFactory)this.getCapability(PackageViewDescriptorFactory.Companion.getCAPABILITY());
        if(packageViewDescriptorFactory0 == null) {
            packageViewDescriptorFactory0 = Default.INSTANCE;
        }
        this.packageViewDescriptorFactory = packageViewDescriptorFactory0;
        this.isValid = true;
        this.packages = storageManager0.createMemoizedFunction(new Function1() {
            {
                ModuleDescriptorImpl.this = moduleDescriptorImpl0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((FqName)object0));
            }

            public final PackageViewDescriptor invoke(FqName fqName0) {
                Intrinsics.checkNotNullParameter(fqName0, "fqName");
                PackageViewDescriptorFactory packageViewDescriptorFactory0 = ModuleDescriptorImpl.access$getPackageViewDescriptorFactory$p(ModuleDescriptorImpl.this);
                StorageManager storageManager0 = ModuleDescriptorImpl.access$getStorageManager$p(ModuleDescriptorImpl.this);
                return packageViewDescriptorFactory0.compute(ModuleDescriptorImpl.this, fqName0, storageManager0);
            }
        });
        this.packageFragmentProviderForWholeModuleWithDependencies$delegate = LazyKt.lazy(new Function0() {
            {
                ModuleDescriptorImpl.this = moduleDescriptorImpl0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final CompositePackageFragmentProvider invoke() {
                ModuleDependencies moduleDependencies0 = ModuleDescriptorImpl.access$getDependencies$p(ModuleDescriptorImpl.this);
                ModuleDescriptorImpl moduleDescriptorImpl0 = ModuleDescriptorImpl.this;
                if(moduleDependencies0 == null) {
                    throw new AssertionError("Dependencies of module " + moduleDescriptorImpl0.getId() + " were not set before querying module content");
                }
                List list0 = moduleDependencies0.getAllDependencies();
                ModuleDescriptorImpl.this.assertValid();
                list0.contains(ModuleDescriptorImpl.this);
                Iterator iterator0 = list0.iterator();
                while(iterator0.hasNext()) {
                    iterator0.next();
                }
                ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
                for(Object object0: list0) {
                    PackageFragmentProvider packageFragmentProvider0 = ModuleDescriptorImpl.access$getPackageFragmentProviderForModuleContent$p(((ModuleDescriptorImpl)object0));
                    Intrinsics.checkNotNull(packageFragmentProvider0);
                    arrayList0.add(packageFragmentProvider0);
                }
                return new CompositePackageFragmentProvider(arrayList0, "CompositeProvider@ModuleDescriptor for " + ModuleDescriptorImpl.this.getName());
            }
        });
    }

    public ModuleDescriptorImpl(Name name0, StorageManager storageManager0, KotlinBuiltIns kotlinBuiltIns0, TargetPlatform targetPlatform0, Map map0, Name name1, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 8) != 0) {
            targetPlatform0 = null;
        }
        if((v & 16) != 0) {
            map0 = MapsKt.emptyMap();
        }
        this(name0, storageManager0, kotlinBuiltIns0, targetPlatform0, map0, ((v & 0x20) == 0 ? name1 : null));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public Object accept(DeclarationDescriptorVisitor declarationDescriptorVisitor0, Object object0) {
        return DefaultImpls.accept(this, declarationDescriptorVisitor0, object0);
    }

    public static final ModuleDependencies access$getDependencies$p(ModuleDescriptorImpl moduleDescriptorImpl0) {
        return moduleDescriptorImpl0.dependencies;
    }

    public static final PackageFragmentProvider access$getPackageFragmentProviderForModuleContent$p(ModuleDescriptorImpl moduleDescriptorImpl0) {
        return moduleDescriptorImpl0.packageFragmentProviderForModuleContent;
    }

    public static final PackageViewDescriptorFactory access$getPackageViewDescriptorFactory$p(ModuleDescriptorImpl moduleDescriptorImpl0) {
        return moduleDescriptorImpl0.packageViewDescriptorFactory;
    }

    public static final StorageManager access$getStorageManager$p(ModuleDescriptorImpl moduleDescriptorImpl0) {
        return moduleDescriptorImpl0.storageManager;
    }

    public static final boolean access$isInitialized(ModuleDescriptorImpl moduleDescriptorImpl0) {
        return moduleDescriptorImpl0.isInitialized();
    }

    public void assertValid() {
        if(!this.isValid()) {
            InvalidModuleExceptionKt.moduleInvalidated(this);
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
    public KotlinBuiltIns getBuiltIns() {
        return this.builtIns;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
    public Object getCapability(ModuleCapability moduleCapability0) {
        Intrinsics.checkNotNullParameter(moduleCapability0, "capability");
        Object object0 = this.capabilities.get(moduleCapability0);
        return object0 == null ? null : object0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor
    public DeclarationDescriptor getContainingDeclaration() {
        return null;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
    public List getExpectedByModules() {
        ModuleDependencies moduleDependencies0 = this.dependencies;
        if(moduleDependencies0 == null) {
            throw new AssertionError("Dependencies of module " + this.getId() + " were not set");
        }
        return moduleDependencies0.getDirectExpectedByDependencies();
    }

    private final String getId() {
        String s = this.getName().toString();
        Intrinsics.checkNotNullExpressionValue(s, "name.toString()");
        return s;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
    public PackageViewDescriptor getPackage(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        this.assertValid();
        return (PackageViewDescriptor)this.packages.invoke(fqName0);
    }

    public final PackageFragmentProvider getPackageFragmentProvider() {
        this.assertValid();
        return this.getPackageFragmentProviderForWholeModuleWithDependencies();
    }

    private final CompositePackageFragmentProvider getPackageFragmentProviderForWholeModuleWithDependencies() {
        return (CompositePackageFragmentProvider)this.packageFragmentProviderForWholeModuleWithDependencies$delegate.getValue();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
    public Collection getSubPackagesOf(FqName fqName0, Function1 function10) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
        this.assertValid();
        return this.getPackageFragmentProvider().getSubPackagesOf(fqName0, function10);
    }

    public final void initialize(PackageFragmentProvider packageFragmentProvider0) {
        Intrinsics.checkNotNullParameter(packageFragmentProvider0, "providerForModuleContent");
        this.packageFragmentProviderForModuleContent = packageFragmentProvider0;
    }

    private final boolean isInitialized() {
        return this.packageFragmentProviderForModuleContent != null;
    }

    public boolean isValid() {
        return this.isValid;
    }

    public final void setDependencies(List list0) {
        Intrinsics.checkNotNullParameter(list0, "descriptors");
        this.setDependencies(list0, SetsKt.emptySet());
    }

    public final void setDependencies(List list0, Set set0) {
        Intrinsics.checkNotNullParameter(list0, "descriptors");
        Intrinsics.checkNotNullParameter(set0, "friends");
        this.setDependencies(new ModuleDependenciesImpl(list0, set0, CollectionsKt.emptyList(), SetsKt.emptySet()));
    }

    public final void setDependencies(ModuleDependencies moduleDependencies0) {
        Intrinsics.checkNotNullParameter(moduleDependencies0, "dependencies");
        this.dependencies = moduleDependencies0;
    }

    public final void setDependencies(ModuleDescriptorImpl[] arr_moduleDescriptorImpl) {
        Intrinsics.checkNotNullParameter(arr_moduleDescriptorImpl, "descriptors");
        this.setDependencies(ArraysKt.toList(arr_moduleDescriptorImpl));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor
    public boolean shouldSeeInternalsOf(ModuleDescriptor moduleDescriptor0) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "targetModule");
        if(Intrinsics.areEqual(this, moduleDescriptor0)) {
            return true;
        }
        ModuleDependencies moduleDependencies0 = this.dependencies;
        Intrinsics.checkNotNull(moduleDependencies0);
        if(CollectionsKt.contains(moduleDependencies0.getModulesWhoseInternalsAreVisible(), moduleDescriptor0)) {
            return true;
        }
        return this.getExpectedByModules().contains(moduleDescriptor0) ? true : moduleDescriptor0.getExpectedByModules().contains(this);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.DeclarationDescriptorImpl
    public String toString() {
        String s = super.toString();
        Intrinsics.checkNotNullExpressionValue(s, "super.toString()");
        return this.isValid() ? s : s + " !isValid";
    }
}

