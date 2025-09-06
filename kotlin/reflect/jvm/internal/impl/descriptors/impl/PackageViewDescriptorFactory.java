package kotlin.reflect.jvm.internal.impl.descriptors.impl;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleCapability;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageViewDescriptor;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public interface PackageViewDescriptorFactory {
    public static final class Companion {
        static final Companion $$INSTANCE;
        private static final ModuleCapability CAPABILITY;

        static {
            Companion.$$INSTANCE = new Companion();
            Companion.CAPABILITY = new ModuleCapability("PackageViewDescriptorFactory");
        }

        public final ModuleCapability getCAPABILITY() {
            return Companion.CAPABILITY;
        }
    }

    public static final class Default implements PackageViewDescriptorFactory {
        public static final Default INSTANCE;

        static {
            Default.INSTANCE = new Default();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.impl.PackageViewDescriptorFactory
        public PackageViewDescriptor compute(ModuleDescriptorImpl moduleDescriptorImpl0, FqName fqName0, StorageManager storageManager0) {
            Intrinsics.checkNotNullParameter(moduleDescriptorImpl0, "module");
            Intrinsics.checkNotNullParameter(fqName0, "fqName");
            Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
            return new LazyPackageViewDescriptorImpl(moduleDescriptorImpl0, fqName0, storageManager0);
        }
    }

    public static final Companion Companion;

    static {
        PackageViewDescriptorFactory.Companion = Companion.$$INSTANCE;
    }

    PackageViewDescriptor compute(ModuleDescriptorImpl arg1, FqName arg2, StorageManager arg3);
}

