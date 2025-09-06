package kotlin.random;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b \u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\bH\u0016J\b\u0010\n\u001A\u00020\u000BH\u0016J\u0010\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\rH\u0016J\b\u0010\u000F\u001A\u00020\u0010H\u0016J\b\u0010\u0011\u001A\u00020\u0012H\u0016J\b\u0010\u0013\u001A\u00020\bH\u0016J\u0010\u0010\u0013\u001A\u00020\b2\u0006\u0010\u0014\u001A\u00020\bH\u0016J\b\u0010\u0015\u001A\u00020\u0016H\u0016R\u0012\u0010\u0003\u001A\u00020\u0004X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0017"}, d2 = {"Lkotlin/random/AbstractPlatformRandom;", "Lkotlin/random/Random;", "()V", "impl", "Ljava/util/Random;", "getImpl", "()Ljava/util/Random;", "nextBits", "", "bitCount", "nextBoolean", "", "nextBytes", "", "array", "nextDouble", "", "nextFloat", "", "nextInt", "until", "nextLong", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public abstract class AbstractPlatformRandom extends Random {
    public abstract java.util.Random getImpl();

    @Override  // kotlin.random.Random
    public int nextBits(int v) {
        return RandomKt.takeUpperBits(this.getImpl().nextInt(), v);
    }

    @Override  // kotlin.random.Random
    public boolean nextBoolean() {
        return this.getImpl().nextBoolean();
    }

    @Override  // kotlin.random.Random
    public byte[] nextBytes(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "array");
        this.getImpl().nextBytes(arr_b);
        return arr_b;
    }

    @Override  // kotlin.random.Random
    public double nextDouble() {
        return this.getImpl().nextDouble();
    }

    @Override  // kotlin.random.Random
    public float nextFloat() {
        return this.getImpl().nextFloat();
    }

    @Override  // kotlin.random.Random
    public int nextInt() {
        return this.getImpl().nextInt();
    }

    @Override  // kotlin.random.Random
    public int nextInt(int v) {
        return this.getImpl().nextInt(v);
    }

    @Override  // kotlin.random.Random
    public long nextLong() {
        return this.getImpl().nextLong();
    }
}

