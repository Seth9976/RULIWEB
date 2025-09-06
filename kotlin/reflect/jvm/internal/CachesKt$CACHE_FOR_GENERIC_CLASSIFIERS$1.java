package kotlin.reflect.jvm.internal;

import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A$\u0012\u001A\u0012\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0004\u0012\u00020\u00050\u0002j\u0002`\u0006\u0012\u0004\u0012\u00020\u00070\u00012\n\u0010\b\u001A\u0006\u0012\u0002\b\u00030\tH\n¢\u0006\u0002\b\n"}, d2 = {"<anonymous>", "Ljava/util/concurrent/ConcurrentHashMap;", "Lkotlin/Pair;", "", "Lkotlin/reflect/KTypeProjection;", "", "Lkotlin/reflect/jvm/internal/Key;", "Lkotlin/reflect/KType;", "it", "Ljava/lang/Class;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
final class CachesKt.CACHE_FOR_GENERIC_CLASSIFIERS.1 extends Lambda implements Function1 {
    public static final CachesKt.CACHE_FOR_GENERIC_CLASSIFIERS.1 INSTANCE;

    static {
        CachesKt.CACHE_FOR_GENERIC_CLASSIFIERS.1.INSTANCE = new CachesKt.CACHE_FOR_GENERIC_CLASSIFIERS.1();
    }

    CachesKt.CACHE_FOR_GENERIC_CLASSIFIERS.1() {
        super(1);
    }

    @Override  // kotlin.jvm.functions.Function1
    public Object invoke(Object object0) {
        return this.invoke(((Class)object0));
    }

    public final ConcurrentHashMap invoke(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "it");
        return new ConcurrentHashMap();
    }
}

