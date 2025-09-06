package kotlin.reflect.jvm.internal.impl.load.java;

import java.util.Map;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqName;
import kotlin.reflect.jvm.internal.impl.name.FqNamesUtilKt;
import kotlin.reflect.jvm.internal.impl.storage.LockBasedStorageManager;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;

public final class NullabilityAnnotationStatesImpl implements NullabilityAnnotationStates {
    private final MemoizedFunctionToNullable cache;
    private final Map states;
    private final LockBasedStorageManager storageManager;

    public NullabilityAnnotationStatesImpl(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "states");
        super();
        this.states = map0;
        LockBasedStorageManager lockBasedStorageManager0 = new LockBasedStorageManager("Java nullability annotation states");
        this.storageManager = lockBasedStorageManager0;
        MemoizedFunctionToNullable memoizedFunctionToNullable0 = lockBasedStorageManager0.createMemoizedFunctionWithNullableValues(new Function1() {
            {
                NullabilityAnnotationStatesImpl.this = nullabilityAnnotationStatesImpl0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((FqName)object0));
            }

            public final Object invoke(FqName fqName0) {
                Intrinsics.checkNotNullExpressionValue(fqName0, "it");
                return FqNamesUtilKt.findValueForMostSpecificFqname(fqName0, NullabilityAnnotationStatesImpl.this.getStates());
            }
        });
        Intrinsics.checkNotNullExpressionValue(memoizedFunctionToNullable0, "storageManager.createMem…cificFqname(states)\n    }");
        this.cache = memoizedFunctionToNullable0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.load.java.NullabilityAnnotationStates
    public Object get(FqName fqName0) {
        Intrinsics.checkNotNullParameter(fqName0, "fqName");
        return this.cache.invoke(fqName0);
    }

    public final Map getStates() {
        return this.states;
    }
}

