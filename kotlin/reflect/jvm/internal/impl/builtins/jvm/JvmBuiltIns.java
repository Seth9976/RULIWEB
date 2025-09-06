package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns;
import kotlin.reflect.jvm.internal.impl.descriptors.ModuleDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.AdditionalClassPartsProvider;
import kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter;
import kotlin.reflect.jvm.internal.impl.descriptors.impl.ModuleDescriptorImpl;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public final class JvmBuiltIns extends KotlinBuiltIns {
    public static enum Kind {
        FROM_DEPENDENCIES,
        FROM_CLASS_LOADER,
        FALLBACK;

        private static final Kind[] $values() [...] // Inlined contents
    }

    public static final class Settings {
        private final boolean isAdditionalBuiltInsFeatureSupported;
        private final ModuleDescriptor ownerModuleDescriptor;

        public Settings(ModuleDescriptor moduleDescriptor0, boolean z) {
            Intrinsics.checkNotNullParameter(moduleDescriptor0, "ownerModuleDescriptor");
            super();
            this.ownerModuleDescriptor = moduleDescriptor0;
            this.isAdditionalBuiltInsFeatureSupported = z;
        }

        public final ModuleDescriptor getOwnerModuleDescriptor() {
            return this.ownerModuleDescriptor;
        }

        public final boolean isAdditionalBuiltInsFeatureSupported() {
            return this.isAdditionalBuiltInsFeatureSupported;
        }
    }

    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[Kind.values().length];
            try {
                arr_v[Kind.FROM_DEPENDENCIES.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Kind.FROM_CLASS_LOADER.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Kind.FALLBACK.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    static final KProperty[] $$delegatedProperties;
    private final NotNullLazyValue customizer$delegate;
    private final Kind kind;
    private Function0 settingsComputation;

    static {
        JvmBuiltIns.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(JvmBuiltIns.class), "customizer", "getCustomizer()Lorg/jetbrains/kotlin/builtins/jvm/JvmBuiltInsCustomizer;"))};
    }

    public JvmBuiltIns(StorageManager storageManager0, Kind jvmBuiltIns$Kind0) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(jvmBuiltIns$Kind0, "kind");
        super(storageManager0);
        this.kind = jvmBuiltIns$Kind0;
        this.customizer$delegate = storageManager0.createLazyValue(new Function0(storageManager0) {
            final StorageManager $storageManager;

            {
                JvmBuiltIns.this = jvmBuiltIns0;
                this.$storageManager = storageManager0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final JvmBuiltInsCustomizer invoke() {
                ModuleDescriptorImpl moduleDescriptorImpl0 = JvmBuiltIns.this.getBuiltInsModule();
                Intrinsics.checkNotNullExpressionValue(moduleDescriptorImpl0, "builtInsModule");
                kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltIns.customizer.2.1 jvmBuiltIns$customizer$2$10 = new Function0() {
                    {
                        JvmBuiltIns.this = jvmBuiltIns0;
                        super(0);
                    }

                    @Override  // kotlin.jvm.functions.Function0
                    public Object invoke() {
                        return this.invoke();
                    }

                    public final Settings invoke() {
                        Function0 function00 = JvmBuiltIns.this.settingsComputation;
                        if(function00 == null) {
                            throw new AssertionError("JvmBuiltins instance has not been initialized properly");
                        }
                        Object object0 = function00.invoke();
                        JvmBuiltIns.access$setSettingsComputation$p(JvmBuiltIns.this, null);
                        return (Settings)object0;
                    }
                };
                return new JvmBuiltInsCustomizer(moduleDescriptorImpl0, this.$storageManager, jvmBuiltIns$customizer$2$10);
            }
        });
        switch(WhenMappings.$EnumSwitchMapping$0[jvmBuiltIns$Kind0.ordinal()]) {
            case 2: {
                this.createBuiltInsModule(false);
                return;
            }
            case 3: {
                this.createBuiltInsModule(true);
            }
        }
    }

    public static final void access$setSettingsComputation$p(JvmBuiltIns jvmBuiltIns0, Function0 function00) {
        jvmBuiltIns0.settingsComputation = function00;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns
    protected AdditionalClassPartsProvider getAdditionalClassPartsProvider() {
        return this.getCustomizer();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns
    public Iterable getClassDescriptorFactories() {
        return this.getClassDescriptorFactories();
    }

    protected List getClassDescriptorFactories() {
        Iterable iterable0 = super.getClassDescriptorFactories();
        Intrinsics.checkNotNullExpressionValue(iterable0, "super.getClassDescriptorFactories()");
        StorageManager storageManager0 = this.getStorageManager();
        Intrinsics.checkNotNullExpressionValue(storageManager0, "storageManager");
        ModuleDescriptorImpl moduleDescriptorImpl0 = this.getBuiltInsModule();
        Intrinsics.checkNotNullExpressionValue(moduleDescriptorImpl0, "builtInsModule");
        return CollectionsKt.plus(iterable0, new JvmBuiltInClassDescriptorFactory(storageManager0, moduleDescriptorImpl0, null, 4, null));
    }

    public final JvmBuiltInsCustomizer getCustomizer() {
        return (JvmBuiltInsCustomizer)StorageKt.getValue(this.customizer$delegate, this, JvmBuiltIns.$$delegatedProperties[0]);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns
    protected PlatformDependentDeclarationFilter getPlatformDependentDeclarationFilter() {
        return this.getCustomizer();
    }

    public final void initialize(ModuleDescriptor moduleDescriptor0, boolean z) {
        Intrinsics.checkNotNullParameter(moduleDescriptor0, "moduleDescriptor");
        this.setPostponedSettingsComputation(new Function0(z) {
            final boolean $isAdditionalBuiltInsFeatureSupported;
            final ModuleDescriptor $moduleDescriptor;

            {
                this.$moduleDescriptor = moduleDescriptor0;
                this.$isAdditionalBuiltInsFeatureSupported = z;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final Settings invoke() {
                return new Settings(this.$moduleDescriptor, this.$isAdditionalBuiltInsFeatureSupported);
            }
        });
    }

    public final void setPostponedSettingsComputation(Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "computation");
        this.settingsComputation = function00;
    }
}

