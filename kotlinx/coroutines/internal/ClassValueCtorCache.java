package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import retrofit2.Platform..ExternalSyntheticApiModelOutline0;

@Metadata(d1 = {"\u0000\'\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0004\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u0006\u001A\u0014\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007j\u0002`\t2\u000E\u0010\n\u001A\n\u0012\u0006\b\u0001\u0012\u00020\b0\u000BH\u0016R\u0010\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/internal/ClassValueCtorCache;", "Lkotlinx/coroutines/internal/CtorCache;", "()V", "cache", "kotlinx/coroutines/internal/ClassValueCtorCache$cache$1", "Lkotlinx/coroutines/internal/ClassValueCtorCache$cache$1;", "get", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/internal/Ctor;", "key", "Ljava/lang/Class;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class ClassValueCtorCache extends CtorCache {
    public static final ClassValueCtorCache INSTANCE;
    private static final ClassValueCtorCache.cache.1 cache;

    static {
        ClassValueCtorCache.INSTANCE = new ClassValueCtorCache();
        ClassValueCtorCache.cache = new ClassValueCtorCache.cache.1();
    }

    @Override  // kotlinx.coroutines.internal.CtorCache
    public Function1 get(Class class0) {
        return (Function1)Platform..ExternalSyntheticApiModelOutline0.m(ClassValueCtorCache.cache, class0);
    }
}

