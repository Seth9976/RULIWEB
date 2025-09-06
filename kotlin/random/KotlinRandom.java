package kotlin.random;

import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\b\u0002\u0018\u0000 \u001B2\u00020\u0001:\u0001\u001BB\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\nH\u0014J\b\u0010\f\u001A\u00020\bH\u0016J\u0010\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u0010H\u0016J\b\u0010\u0011\u001A\u00020\u0012H\u0016J\b\u0010\u0013\u001A\u00020\u0014H\u0016J\b\u0010\u0015\u001A\u00020\nH\u0016J\u0010\u0010\u0015\u001A\u00020\n2\u0006\u0010\u0016\u001A\u00020\nH\u0016J\b\u0010\u0017\u001A\u00020\u0018H\u0016J\u0010\u0010\u0019\u001A\u00020\u000E2\u0006\u0010\u001A\u001A\u00020\u0018H\u0016R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u001C"}, d2 = {"Lkotlin/random/KotlinRandom;", "Ljava/util/Random;", "impl", "Lkotlin/random/Random;", "(Lkotlin/random/Random;)V", "getImpl", "()Lkotlin/random/Random;", "seedInitialized", "", "next", "", "bits", "nextBoolean", "nextBytes", "", "bytes", "", "nextDouble", "", "nextFloat", "", "nextInt", "bound", "nextLong", "", "setSeed", "seed", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class KotlinRandom extends Random {
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lkotlin/random/KotlinRandom$Companion;", "", "()V", "serialVersionUID", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    private static final Companion Companion;
    private final kotlin.random.Random impl;
    private boolean seedInitialized;
    private static final long serialVersionUID;

    static {
        KotlinRandom.Companion = new Companion(null);
    }

    public KotlinRandom(kotlin.random.Random random0) {
        Intrinsics.checkNotNullParameter(random0, "impl");
        super();
        this.impl = random0;
    }

    public final kotlin.random.Random getImpl() {
        return this.impl;
    }

    @Override
    protected int next(int v) {
        return this.impl.nextBits(v);
    }

    @Override
    public boolean nextBoolean() {
        return this.impl.nextBoolean();
    }

    @Override
    public void nextBytes(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "bytes");
        this.impl.nextBytes(arr_b);
    }

    @Override
    public double nextDouble() {
        return this.impl.nextDouble();
    }

    @Override
    public float nextFloat() {
        return this.impl.nextFloat();
    }

    @Override
    public int nextInt() {
        return this.impl.nextInt();
    }

    @Override
    public int nextInt(int v) {
        return this.impl.nextInt(v);
    }

    @Override
    public long nextLong() {
        return this.impl.nextLong();
    }

    @Override
    public void setSeed(long v) {
        if(this.seedInitialized) {
            throw new UnsupportedOperationException("Setting seed is not supported.");
        }
        this.seedInitialized = true;
    }
}

