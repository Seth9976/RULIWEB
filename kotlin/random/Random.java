package kotlin.random;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\'\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0004H&J\b\u0010\u0006\u001A\u00020\u0007H\u0016J\u0010\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\tH\u0016J$\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\t2\b\b\u0002\u0010\u000B\u001A\u00020\u00042\b\b\u0002\u0010\f\u001A\u00020\u0004H\u0016J\u0010\u0010\b\u001A\u00020\t2\u0006\u0010\r\u001A\u00020\u0004H\u0016J\b\u0010\u000E\u001A\u00020\u000FH\u0016J\u0010\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u000FH\u0016J\u0018\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0011\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u000FH\u0016J\b\u0010\u0012\u001A\u00020\u0013H\u0016J\b\u0010\u0014\u001A\u00020\u0004H\u0016J\u0010\u0010\u0014\u001A\u00020\u00042\u0006\u0010\u0010\u001A\u00020\u0004H\u0016J\u0018\u0010\u0014\u001A\u00020\u00042\u0006\u0010\u0011\u001A\u00020\u00042\u0006\u0010\u0010\u001A\u00020\u0004H\u0016J\b\u0010\u0015\u001A\u00020\u0016H\u0016J\u0010\u0010\u0015\u001A\u00020\u00162\u0006\u0010\u0010\u001A\u00020\u0016H\u0016J\u0018\u0010\u0015\u001A\u00020\u00162\u0006\u0010\u0011\u001A\u00020\u00162\u0006\u0010\u0010\u001A\u00020\u0016H\u0016¨\u0006\u0018"}, d2 = {"Lkotlin/random/Random;", "", "()V", "nextBits", "", "bitCount", "nextBoolean", "", "nextBytes", "", "array", "fromIndex", "toIndex", "size", "nextDouble", "", "until", "from", "nextFloat", "", "nextInt", "nextLong", "", "Default", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public abstract class Random {
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0001\u001CB\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\u0007H\u0016J\b\u0010\t\u001A\u00020\nH\u0016J\u0010\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\fH\u0016J \u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\f2\u0006\u0010\u000E\u001A\u00020\u00072\u0006\u0010\u000F\u001A\u00020\u0007H\u0016J\u0010\u0010\u000B\u001A\u00020\f2\u0006\u0010\u0010\u001A\u00020\u0007H\u0016J\b\u0010\u0011\u001A\u00020\u0012H\u0016J\u0010\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\u0012H\u0016J\u0018\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0014\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\u0012H\u0016J\b\u0010\u0015\u001A\u00020\u0016H\u0016J\b\u0010\u0017\u001A\u00020\u0007H\u0016J\u0010\u0010\u0017\u001A\u00020\u00072\u0006\u0010\u0013\u001A\u00020\u0007H\u0016J\u0018\u0010\u0017\u001A\u00020\u00072\u0006\u0010\u0014\u001A\u00020\u00072\u0006\u0010\u0013\u001A\u00020\u0007H\u0016J\b\u0010\u0018\u001A\u00020\u0019H\u0016J\u0010\u0010\u0018\u001A\u00020\u00192\u0006\u0010\u0013\u001A\u00020\u0019H\u0016J\u0018\u0010\u0018\u001A\u00020\u00192\u0006\u0010\u0014\u001A\u00020\u00192\u0006\u0010\u0013\u001A\u00020\u0019H\u0016J\b\u0010\u001A\u001A\u00020\u001BH\u0002R\u000E\u0010\u0005\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001D"}, d2 = {"Lkotlin/random/Random$Default;", "Lkotlin/random/Random;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "()V", "defaultRandom", "nextBits", "", "bitCount", "nextBoolean", "", "nextBytes", "", "array", "fromIndex", "toIndex", "size", "nextDouble", "", "until", "from", "nextFloat", "", "nextInt", "nextLong", "", "writeReplace", "", "Serialized", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Default extends Random implements Serializable {
        @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0000\n\u0000\bÂ\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0006\u001A\u00020\u0007H\u0002R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkotlin/random/Random$Default$Serialized;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "()V", "serialVersionUID", "", "readResolve", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
        static final class Serialized implements Serializable {
            public static final Serialized INSTANCE;
            private static final long serialVersionUID;

            static {
                Serialized.INSTANCE = new Serialized();
            }

            private final Object readResolve() {
                return Random.Default;
            }
        }

        private Default() {
        }

        public Default(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @Override  // kotlin.random.Random
        public int nextBits(int v) {
            return Random.defaultRandom.nextBits(v);
        }

        @Override  // kotlin.random.Random
        public boolean nextBoolean() {
            return Random.defaultRandom.nextBoolean();
        }

        @Override  // kotlin.random.Random
        public byte[] nextBytes(int v) {
            return Random.defaultRandom.nextBytes(v);
        }

        @Override  // kotlin.random.Random
        public byte[] nextBytes(byte[] arr_b) {
            Intrinsics.checkNotNullParameter(arr_b, "array");
            return Random.defaultRandom.nextBytes(arr_b);
        }

        @Override  // kotlin.random.Random
        public byte[] nextBytes(byte[] arr_b, int v, int v1) {
            Intrinsics.checkNotNullParameter(arr_b, "array");
            return Random.defaultRandom.nextBytes(arr_b, v, v1);
        }

        @Override  // kotlin.random.Random
        public double nextDouble() {
            return Random.defaultRandom.nextDouble();
        }

        @Override  // kotlin.random.Random
        public double nextDouble(double f) {
            return Random.defaultRandom.nextDouble(f);
        }

        @Override  // kotlin.random.Random
        public double nextDouble(double f, double f1) {
            return Random.defaultRandom.nextDouble(f, f1);
        }

        @Override  // kotlin.random.Random
        public float nextFloat() {
            return Random.defaultRandom.nextFloat();
        }

        @Override  // kotlin.random.Random
        public int nextInt() {
            return Random.defaultRandom.nextInt();
        }

        @Override  // kotlin.random.Random
        public int nextInt(int v) {
            return Random.defaultRandom.nextInt(v);
        }

        @Override  // kotlin.random.Random
        public int nextInt(int v, int v1) {
            return Random.defaultRandom.nextInt(v, v1);
        }

        @Override  // kotlin.random.Random
        public long nextLong() {
            return Random.defaultRandom.nextLong();
        }

        @Override  // kotlin.random.Random
        public long nextLong(long v) {
            return Random.defaultRandom.nextLong(v);
        }

        @Override  // kotlin.random.Random
        public long nextLong(long v, long v1) {
            return Random.defaultRandom.nextLong(v, v1);
        }

        private final Object writeReplace() {
            return Serialized.INSTANCE;
        }
    }

    public static final Default Default;
    private static final Random defaultRandom;

    static {
        Random.Default = new Default(null);
        Random.defaultRandom = PlatformImplementationsKt.IMPLEMENTATIONS.defaultPlatformRandom();
    }

    public abstract int nextBits(int arg1);

    public boolean nextBoolean() {
        return this.nextBits(1) != 0;
    }

    public byte[] nextBytes(int v) {
        return this.nextBytes(new byte[v]);
    }

    public byte[] nextBytes(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "array");
        return this.nextBytes(arr_b, 0, arr_b.length);
    }

    public byte[] nextBytes(byte[] arr_b, int v, int v1) {
        Intrinsics.checkNotNullParameter(arr_b, "array");
        if(!new IntRange(0, arr_b.length).contains(v) || !new IntRange(0, arr_b.length).contains(v1)) {
            throw new IllegalArgumentException(("fromIndex (" + v + ") or toIndex (" + v1 + ") are out of range: 0.." + arr_b.length + '.').toString());
        }
        if(v > v1) {
            throw new IllegalArgumentException(("fromIndex (" + v + ") must be not greater than toIndex (" + v1 + ").").toString());
        }
        int v3 = (v1 - v) / 4;
        for(int v4 = 0; v4 < v3; ++v4) {
            int v5 = this.nextInt();
            arr_b[v] = (byte)v5;
            arr_b[v + 1] = (byte)(v5 >>> 8);
            arr_b[v + 2] = (byte)(v5 >>> 16);
            arr_b[v + 3] = (byte)(v5 >>> 24);
            v += 4;
        }
        int v6 = v1 - v;
        int v7 = this.nextBits(v6 * 8);
        for(int v2 = 0; v2 < v6; ++v2) {
            arr_b[v + v2] = (byte)(v7 >>> v2 * 8);
        }
        return arr_b;
    }

    public static byte[] nextBytes$default(Random random0, byte[] arr_b, int v, int v1, int v2, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: nextBytes");
        }
        if((v2 & 2) != 0) {
            v = 0;
        }
        if((v2 & 4) != 0) {
            v1 = arr_b.length;
        }
        return random0.nextBytes(arr_b, v, v1);
    }

    public double nextDouble() {
        return PlatformRandomKt.doubleFromParts(this.nextBits(26), this.nextBits(27));
    }

    public double nextDouble(double f) {
        return this.nextDouble(0.0, f);
    }

    public double nextDouble(double f, double f1) {
        double f4;
        RandomKt.checkRangeBounds(f, f1);
        double f2 = f1 - f;
        if(Double.isInfinite(f2) && !Double.isInfinite(f) && !Double.isNaN(f) && !Double.isInfinite(f1) && !Double.isNaN(f1)) {
            double f3 = this.nextDouble() * (f1 / 2.0 - f / 2.0);
            f4 = f + f3 + f3;
            return f4 >= f1 ? Math.nextAfter(f1, -Infinity) : f4;
        }
        f4 = f + this.nextDouble() * f2;
        return f4 >= f1 ? Math.nextAfter(f1, -Infinity) : f4;
    }

    public float nextFloat() {
        return ((float)this.nextBits(24)) / 16777216.0f;
    }

    public int nextInt() {
        return this.nextBits(0x20);
    }

    public int nextInt(int v) {
        return this.nextInt(0, v);
    }

    public int nextInt(int v, int v1) {
        int v5;
        int v3;
        RandomKt.checkRangeBounds(v, v1);
        int v2 = v1 - v;
        if(v2 <= 0 && v2 != 0x80000000) {
            do {
                v3 = this.nextInt();
            }
            while(v > v3 || v3 >= v1);
            return v3;
        }
        if((-v2 & v2) == v2) {
            return v + this.nextBits(RandomKt.fastLog2(v2));
        }
        do {
            int v4 = this.nextInt();
            v5 = (v4 >>> 1) % v2;
        }
        while((v4 >>> 1) - v5 + (v2 - 1) < 0);
        return v + v5;
    }

    public long nextLong() {
        return (((long)this.nextInt()) << 0x20) + ((long)this.nextInt());
    }

    public long nextLong(long v) {
        return this.nextLong(0L, v);
    }

    public long nextLong(long v, long v1) {
        long v5;
        long v4;
        RandomKt.checkRangeBounds(v, v1);
        long v2 = v1 - v;
        if(v2 > 0L) {
            if((-v2 & v2) == v2) {
                if(((int)v2) != 0) {
                    return v + (((long)this.nextBits(RandomKt.fastLog2(((int)v2)))) & 0xFFFFFFFFL);
                }
                return ((int)(v2 >>> 0x20)) == 1 ? v + (((long)this.nextInt()) & 0xFFFFFFFFL) : v + ((((long)this.nextBits(RandomKt.fastLog2(((int)(v2 >>> 0x20))))) << 0x20) + (((long)this.nextInt()) & 0xFFFFFFFFL));
            }
            do {
                long v3 = this.nextLong();
                v4 = (v3 >>> 1) % v2;
            }
            while((v3 >>> 1) - v4 + (v2 - 1L) < 0L);
            return v + v4;
        }
        do {
            v5 = this.nextLong();
        }
        while(v > v5 || v5 >= v1);
        return v5;
    }
}

