package kotlin.reflect.jvm.internal.impl.builtins;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;

public final class DefaultBuiltIns extends KotlinBuiltIns {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final DefaultBuiltIns getInstance() {
            return DefaultBuiltIns.Instance;
        }
    }

    public static final Companion Companion;
    private static final DefaultBuiltIns Instance;

    static {
        DefaultBuiltIns.Companion = new Companion(null);
        DefaultBuiltIns.Instance = new DefaultBuiltIns(false, 1, null);
    }

    public DefaultBuiltIns() {
        this(false, 1, null);
    }

    public DefaultBuiltIns(boolean z) {
        super(new LockBasedStorageManager("DefaultBuiltIns"));
        if(z) {
            this.createBuiltInsModule(false);
        }
    }

    public DefaultBuiltIns(boolean z, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            z = true;
        }
        this(z);
    }
}

