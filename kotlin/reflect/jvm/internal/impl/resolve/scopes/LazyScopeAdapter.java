package kotlin.reflect.jvm.internal.impl.resolve.scopes;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.storage.NotNullLazyValue;
import kotlin.reflect.jvm.internal.impl.storage.StorageManager;

public final class LazyScopeAdapter extends AbstractScopeAdapter {
    private final NotNullLazyValue lazyScope;

    public LazyScopeAdapter(Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "getScope");
        this(null, function00, 1, null);
    }

    public LazyScopeAdapter(StorageManager storageManager0, Function0 function00) {
        Intrinsics.checkNotNullParameter(storageManager0, "storageManager");
        Intrinsics.checkNotNullParameter(function00, "getScope");
        super();
        this.lazyScope = storageManager0.createLazyValue(new Function0() {
            final Function0 $getScope;

            {
                this.$getScope = function00;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final MemberScope invoke() {
                MemberScope memberScope0 = (MemberScope)this.$getScope.invoke();
                return memberScope0 instanceof AbstractScopeAdapter ? ((AbstractScopeAdapter)memberScope0).getActualScope() : memberScope0;
            }
        });
    }

    public LazyScopeAdapter(StorageManager storageManager0, Function0 function00, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            storageManager0 = LockBasedStorageManager.NO_LOCKS;
            Intrinsics.checkNotNullExpressionValue(storageManager0, "NO_LOCKS");
        }
        this(storageManager0, function00);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.scopes.AbstractScopeAdapter
    protected MemberScope getWorkerScope() {
        return (MemberScope)this.lazyScope.invoke();
    }
}

