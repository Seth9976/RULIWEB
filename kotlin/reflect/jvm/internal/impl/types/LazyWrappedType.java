package kotlin.reflect.jvm.internal.impl.types;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeRefiner;
import kotlin.reflect.jvm.internal.impl.types.model.KotlinTypeMarker;

public final class LazyWrappedType extends WrappedType {
    private final Function0 computation;
    private final NotNullLazyValue lazyValue;
    private final StorageManager storageManager;

    public LazyWrappedType(StorageManager storageManager0, Function0 function00) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(function00, "computation");
        super();
        this.storageManager = storageManager0;
        this.computation = function00;
        this.lazyValue = storageManager0.createLazyValue(function00);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.WrappedType
    protected KotlinType getDelegate() {
        return (KotlinType)this.lazyValue.invoke();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.WrappedType
    public boolean isComputed() {
        return this.lazyValue.isComputed();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.KotlinType
    public KotlinType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        return this.refine(kotlinTypeRefiner0);
    }

    public LazyWrappedType refine(KotlinTypeRefiner kotlinTypeRefiner0) {
        Intrinsics.checkNotNullParameter(kotlinTypeRefiner0, "kotlinTypeRefiner");
        Function0 function00 = new Function0(this) {
            final KotlinTypeRefiner $kotlinTypeRefiner;

            {
                this.$kotlinTypeRefiner = kotlinTypeRefiner0;
                LazyWrappedType.this = lazyWrappedType0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final KotlinType invoke() {
                KotlinTypeMarker kotlinTypeMarker0 = (KotlinTypeMarker)LazyWrappedType.this.computation.invoke();
                return this.$kotlinTypeRefiner.refineType(kotlinTypeMarker0);
            }
        };
        return new LazyWrappedType(this.storageManager, function00);
    }
}

