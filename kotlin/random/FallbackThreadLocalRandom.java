package kotlin.random;

import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003*\u0001\b\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001A\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001A\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\t¨\u0006\n"}, d2 = {"Lkotlin/random/FallbackThreadLocalRandom;", "Lkotlin/random/AbstractPlatformRandom;", "()V", "impl", "Ljava/util/Random;", "getImpl", "()Ljava/util/Random;", "implStorage", "kotlin/random/FallbackThreadLocalRandom$implStorage$1", "Lkotlin/random/FallbackThreadLocalRandom$implStorage$1;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class FallbackThreadLocalRandom extends AbstractPlatformRandom {
    private final kotlin.random.FallbackThreadLocalRandom.implStorage.1 implStorage;

    public FallbackThreadLocalRandom() {
        this.implStorage = new kotlin.random.FallbackThreadLocalRandom.implStorage.1();

        @Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001A\u00020\u0002H\u0014¨\u0006\u0004"}, d2 = {"kotlin/random/FallbackThreadLocalRandom$implStorage$1", "Ljava/lang/ThreadLocal;", "Ljava/util/Random;", "initialValue", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
        public final class kotlin.random.FallbackThreadLocalRandom.implStorage.1 extends ThreadLocal {
            @Override
            public Object initialValue() {
                return this.initialValue();
            }

            protected Random initialValue() {
                return new Random();
            }
        }

    }

    @Override  // kotlin.random.AbstractPlatformRandom
    public Random getImpl() {
        Object object0 = this.implStorage.get();
        Intrinsics.checkNotNullExpressionValue(object0, "implStorage.get()");
        return (Random)object0;
    }
}

