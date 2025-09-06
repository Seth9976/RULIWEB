package kotlin.random.jdk8;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.AbstractPlatformRandom;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\bH\u0016J\u0018\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\u000B2\u0006\u0010\t\u001A\u00020\u000BH\u0016J\u0010\u0010\r\u001A\u00020\u000E2\u0006\u0010\t\u001A\u00020\u000EH\u0016J\u0018\u0010\r\u001A\u00020\u000E2\u0006\u0010\f\u001A\u00020\u000E2\u0006\u0010\t\u001A\u00020\u000EH\u0016R\u0014\u0010\u0003\u001A\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u000F"}, d2 = {"Lkotlin/random/jdk8/PlatformThreadLocalRandom;", "Lkotlin/random/AbstractPlatformRandom;", "()V", "impl", "Ljava/util/Random;", "getImpl", "()Ljava/util/Random;", "nextDouble", "", "until", "nextInt", "", "from", "nextLong", "", "kotlin-stdlib-jdk8"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class PlatformThreadLocalRandom extends AbstractPlatformRandom {
    @Override  // kotlin.random.AbstractPlatformRandom
    public Random getImpl() {
        ThreadLocalRandom threadLocalRandom0 = ThreadLocalRandom.current();
        Intrinsics.checkNotNullExpressionValue(threadLocalRandom0, "current()");
        return threadLocalRandom0;
    }

    @Override  // kotlin.random.Random
    public double nextDouble(double f) {
        return ThreadLocalRandom.current().nextDouble(f);
    }

    @Override  // kotlin.random.Random
    public int nextInt(int v, int v1) {
        return ThreadLocalRandom.current().nextInt(v, v1);
    }

    @Override  // kotlin.random.Random
    public long nextLong(long v) {
        return ThreadLocalRandom.current().nextLong(v);
    }

    @Override  // kotlin.random.Random
    public long nextLong(long v, long v1) {
        return ThreadLocalRandom.current().nextLong(v, v1);
    }
}

