package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.Collection;
import java.util.List;
import kotlin.Deprecated;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderOptimized;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;

public abstract class AbstractDeserializedPackageFragmentProvider implements PackageFragmentProviderOptimized {
    protected DeserializationComponents components;
    private final KotlinMetadataFinder finder;
    private final MemoizedFunctionToNullable fragments;
    private final ModuleDescriptor moduleDescriptor;
    private final StorageManager storageManager;

    public AbstractDeserializedPackageFragmentProvider(StorageManager storageManager0, KotlinMetadataFinder kotlinMetadataFinder0, ModuleDescriptor moduleDescriptor0) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(kotlinMetadataFinder0, "finder");
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "moduleDescriptor");
        super();
        this.storageManager = storageManager0;
        this.finder = kotlinMetadataFinder0;
        this.moduleDescriptor = moduleDescriptor0;
        this.fragments = storageManager0.createMemoizedFunctionWithNullableValues(new Function1() {
            {
                AbstractDeserializedPackageFragmentProvider.this = abstractDeserializedPackageFragmentProvider0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((FqName)object0));
            }

            public final PackageFragmentDescriptor invoke(FqName fqName0) {
                Intrinsics.checkNotNullParameter(fqName0, "fqName");
                DeserializedPackageFragment deserializedPackageFragment0 = AbstractDeserializedPackageFragmentProvider.this.findPackage(fqName0);
                if(deserializedPackageFragment0 != null) {
                    deserializedPackageFragment0.initialize(AbstractDeserializedPackageFragmentProvider.this.getComponents());
                    return deserializedPackageFragment0;
                }
                return null;
            }
        });
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderOptimized
    public void collectPackageFragments(FqName fqName0, Collection collection0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Intrinsics.checkNotNullParameter(collection0, "packageFragments");
        CollectionsKt.addIfNotNull(collection0, this.fragments.invoke(fqName0));
    }

    protected abstract DeserializedPackageFragment findPackage(FqName arg1);

    protected final DeserializationComponents getComponents() {
        DeserializationComponents deserializationComponents0 = this.components;
        if(deserializationComponents0 != null) {
            return deserializationComponents0;
        }
        Intrinsics.throwUninitializedPropertyAccessException("components");
        return null;
    }

    protected final KotlinMetadataFinder getFinder() {
        return this.finder;
    }

    protected final ModuleDescriptor getModuleDescriptor() {
        return this.moduleDescriptor;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    @Deprecated(message = "for usages use #packageFragments(FqName) at final point, for impl use #collectPackageFragments(FqName, MutableCollection<PackageFragmentDescriptor>)")
    public List getPackageFragments(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        return kotlin.collections.CollectionsKt.listOfNotNull(this.fragments.invoke(fqName0));
    }

    protected final StorageManager getStorageManager() {
        return this.storageManager;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider
    public Collection getSubPackagesOf(FqName fqName0, Function1 function10) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Intrinsics.checkNotNullParameter(function10, "nameFilter");
        return SetsKt.emptySet();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProviderOptimized
    public boolean isEmpty(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        return this.fragments.isComputed(fqName0) ? ((PackageFragmentDescriptor)this.fragments.invoke(fqName0)) == null : this.findPackage(fqName0) == null;
    }

    protected final void setComponents(DeserializationComponents deserializationComponents0) {
        Intrinsics.checkNotNullParameter(deserializationComponents0, "<set-?>");
        this.components = deserializationComponents0;
    }
}

