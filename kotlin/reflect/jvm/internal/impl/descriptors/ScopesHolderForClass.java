package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil.DescriptorUtilsKt;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.MemberScope;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageKt;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;

public final class ScopesHolderForClass {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final ScopesHolderForClass create(ClassDescriptor classDescriptor0, StorageManager storageManager0, KotlinTypeRefiner kotlinTypeRefiner0, Function1 function10) {
            Intrinsics.checkNotNullParameter(classDescriptor0, "classDescriptor");
            Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
            Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefinerForOwnerModule");
            Intrinsics.checkNotNullParameter(function10, "scopeFactory");
            return new ScopesHolderForClass(classDescriptor0, storageManager0, function10, kotlinTypeRefiner0, null);
        }
    }

    static final KProperty[] $$delegatedProperties;
    public static final Companion Companion;
    private final ClassDescriptor classDescriptor;
    private final KotlinTypeRefiner kotlinTypeRefinerForOwnerModule;
    private final Function1 scopeFactory;
    private final NotNullLazyValue scopeForOwnerModule$delegate;

    static {
        ScopesHolderForClass.$$delegatedProperties = new KProperty[]{Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(ScopesHolderForClass.class), "scopeForOwnerModule", "getScopeForOwnerModule()Lorg/jetbrains/kotlin/resolve/scopes/MemberScope;"))};
        ScopesHolderForClass.Companion = new Companion(null);
    }

    private ScopesHolderForClass(ClassDescriptor classDescriptor0, StorageManager storageManager0, Function1 function10, KotlinTypeRefiner kotlinTypeRefiner0) {
        this.classDescriptor = classDescriptor0;
        this.scopeFactory = function10;
        this.kotlinTypeRefinerForOwnerModule = kotlinTypeRefiner0;
        this.scopeForOwnerModule$delegate = storageManager0.createLazyValue(new Function0() {
            {
                ScopesHolderForClass.this = scopesHolderForClass0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final MemberScope invoke() {
                return (MemberScope)ScopesHolderForClass.access$getScopeFactory$p(ScopesHolderForClass.this).invoke(ScopesHolderForClass.this.kotlinTypeRefinerForOwnerModule);
            }
        });
    }

    public ScopesHolderForClass(ClassDescriptor classDescriptor0, StorageManager storageManager0, Function1 function10, KotlinTypeRefiner kotlinTypeRefiner0, DefaultConstructorMarker defaultConstructorMarker0) {
        this(classDescriptor0, storageManager0, function10, kotlinTypeRefiner0);
    }

    public final MemberScope getScope(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        if(!kotlinTypeRefiner0.isRefinementNeededForModule(DescriptorUtilsKt.getModule(this.classDescriptor))) {
            return this.getScopeForOwnerModule();
        }
        TypeConstructor typeConstructor0 = this.classDescriptor.getTypeConstructor();
        Intrinsics.checkNotNullExpressionValue(typeConstructor0, "classDescriptor.typeConstructor");
        if(!kotlinTypeRefiner0.isRefinementNeededForTypeConstructor(typeConstructor0)) {
            return this.getScopeForOwnerModule();
        }
        Function0 function00 = new Function0(kotlinTypeRefiner0) {
            final KotlinTypeRefiner $kotlinTypeRefiner;

            {
                ScopesHolderForClass.this = scopesHolderForClass0;
                this.$kotlinTypeRefiner = kotlinTypeRefiner0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final MemberScope invoke() {
                return (MemberScope)ScopesHolderForClass.this.scopeFactory.invoke(this.$kotlinTypeRefiner);
            }
        };
        return kotlinTypeRefiner0.getOrPutScopeForClass(this.classDescriptor, function00);
    }

    private final MemberScope getScopeForOwnerModule() {
        return (MemberScope)StorageKt.getValue(this.scopeForOwnerModule$delegate, this, ScopesHolderForClass.$$delegatedProperties[0]);
    }
}

