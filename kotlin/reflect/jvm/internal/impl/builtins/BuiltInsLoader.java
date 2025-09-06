package kotlin.reflect.jvm.internal.impl.builtins;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.PackageFragmentProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public interface BuiltInsLoader {
    public static final class Companion {
        static final Companion $$INSTANCE;
        private static final Lazy Instance$delegate;

        static {
            Companion.$$INSTANCE = new Companion();
            Companion.Instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, BuiltInsLoader.Companion.Instance.2.INSTANCE);
        }

        public final BuiltInsLoader getInstance() {
            return (BuiltInsLoader)Companion.Instance$delegate.getValue();
        }
    }

    public static final Companion Companion;

    static {
        BuiltInsLoader.Companion = Companion.$$INSTANCE;
    }

    PackageFragmentProvider createPackageFragmentProvider(StorageManager arg1, ModuleDescriptor arg2, Iterable arg3, PlatformDependentDeclarationFilter arg4, AdditionalClassPartsProvider arg5, boolean arg6);
}

