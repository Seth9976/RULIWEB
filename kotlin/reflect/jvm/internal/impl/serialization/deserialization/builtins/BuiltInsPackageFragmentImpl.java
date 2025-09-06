package kotlin.reflect.jvm.internal.impl.serialization.deserialization.builtins;

import java.io.InputStream;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.builtins.BuiltInsPackageFragment;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.PackageFragment;
import kotlin.reflect.jvm.internal.impl.metadata.builtins.BuiltInsBinaryVersion;
import kotlin.reflect.jvm.internal.impl.metadata.builtins.ReadPackageFragmentKt;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.DeserializedPackageFragmentImpl;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public final class BuiltInsPackageFragmentImpl extends DeserializedPackageFragmentImpl implements BuiltInsPackageFragment {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final BuiltInsPackageFragmentImpl create(FqName fqName0, StorageManager storageManager0, ModuleDescriptor moduleDescriptor0, InputStream inputStream0, boolean z) {
            Intrinsics.checkNotNullParameter(fqName0, "fqName");
            Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
            Intrinsics.checkNotNullParameter(moduleDescriptor0, "module");
            Intrinsics.checkNotNullParameter(inputStream0, "inputStream");
            Pair pair0 = ReadPackageFragmentKt.readBuiltinsPackageFragment(inputStream0);
            Object object0 = pair0.component1();
            Object object1 = pair0.component2();
            if(((PackageFragment)object0) == null) {
                throw new UnsupportedOperationException("Kotlin built-in definition format version is not supported: expected " + BuiltInsBinaryVersion.INSTANCE + ", actual " + ((BuiltInsBinaryVersion)object1) + ". Please update Kotlin");
            }
            return new BuiltInsPackageFragmentImpl(fqName0, storageManager0, moduleDescriptor0, ((PackageFragment)object0), ((BuiltInsBinaryVersion)object1), z, null);
        }
    }

    public static final Companion Companion;
    private final boolean isFallback;

    static {
        BuiltInsPackageFragmentImpl.Companion = new Companion(null);
    }

    private BuiltInsPackageFragmentImpl(FqName fqName0, StorageManager storageManager0, ModuleDescriptor moduleDescriptor0, PackageFragment protoBuf$PackageFragment0, BuiltInsBinaryVersion builtInsBinaryVersion0, boolean z) {
        super(fqName0, storageManager0, moduleDescriptor0, protoBuf$PackageFragment0, builtInsBinaryVersion0, null);
        this.isFallback = z;
    }

    public BuiltInsPackageFragmentImpl(FqName fqName0, StorageManager storageManager0, ModuleDescriptor moduleDescriptor0, PackageFragment protoBuf$PackageFragment0, BuiltInsBinaryVersion builtInsBinaryVersion0, boolean z, DefaultConstructorMarker defaultConstructorMarker0) {
        this(fqName0, storageManager0, moduleDescriptor0, protoBuf$PackageFragment0, builtInsBinaryVersion0, z);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageFragmentDescriptorImpl
    public String toString() {
        return "builtins package fragment for " + this.getFqName() + " from " + DescriptorUtilsKt.getModule(this);
    }
}

