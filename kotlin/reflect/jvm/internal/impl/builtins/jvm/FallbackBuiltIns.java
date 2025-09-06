package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter.All;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;

final class FallbackBuiltIns extends KotlinBuiltIns {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final KotlinBuiltIns getInstance() {
            return FallbackBuiltIns.Instance;
        }
    }

    public static final Companion Companion;
    private static final KotlinBuiltIns Instance;

    static {
        FallbackBuiltIns.Companion = new Companion(null);
        FallbackBuiltIns.Instance = new FallbackBuiltIns();
    }

    private FallbackBuiltIns() {
        super(new LockBasedStorageManager("FallbackBuiltIns"));
        this.createBuiltInsModule(true);
    }

    protected All getPlatformDependentDeclarationFilter() {
        return All.INSTANCE;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns
    public PlatformDependentDeclarationFilter getPlatformDependentDeclarationFilter() {
        return this.getPlatformDependentDeclarationFilter();
    }
}

