package kotlin.reflect.jvm.internal.impl.util;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

public abstract class TypeRegistry {
    private final AtomicInteger idCounter;
    private final ConcurrentHashMap idPerType;

    public TypeRegistry() {
        this.idPerType = new ConcurrentHashMap();
        this.idCounter = new AtomicInteger(0);
    }

    public abstract int customComputeIfAbsent(ConcurrentHashMap arg1, String arg2, Function1 arg3);

    public final NullableArrayMapAccessor generateNullableAccessor(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "kClass");
        return new NullableArrayMapAccessor(kClass0, this.getId(kClass0));
    }

    public final int getId(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "kClass");
        String s = kClass0.getQualifiedName();
        Intrinsics.checkNotNull(s);
        Function1 function10 = new Function1() {
            {
                TypeRegistry.this = typeRegistry0;
                super(1);
            }

            public final Integer invoke(String s) {
                Intrinsics.checkNotNullParameter(s, "it");
                return TypeRegistry.this.idCounter.getAndIncrement();
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((String)object0));
            }
        };
        return this.customComputeIfAbsent(this.idPerType, s, function10);
    }

    protected final Collection getIndices() {
        Collection collection0 = this.idPerType.values();
        Intrinsics.checkNotNullExpressionValue(collection0, "idPerType.values");
        return collection0;
    }
}

