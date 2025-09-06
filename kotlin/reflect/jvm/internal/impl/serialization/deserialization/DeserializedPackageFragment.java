package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors.DeserializedMemberScope;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public abstract class DeserializedPackageFragment extends PackageFragmentDescriptorImpl {
    private final StorageManager storageManager;

    public DeserializedPackageFragment(FqName fqName0, StorageManager storageManager0, ModuleDescriptor moduleDescriptor0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
        super(moduleDescriptor0, fqName0);
        this.storageManager = storageManager0;
    }

    public abstract ClassDataFinder getClassDataFinder();

    public boolean hasTopLevelClass(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "name");
        MemberScope memberScope0 = this.getMemberScope();
        return memberScope0 instanceof DeserializedMemberScope && ((DeserializedMemberScope)memberScope0).getClassNames$deserialization().contains(name0);
    }

    public abstract void initialize(DeserializationComponents arg1);
}

