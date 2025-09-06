package kotlin.random;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000E\b\u0000\u0018\u0000 \u00122\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0001\u0012B\u0017\b\u0010\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0005¢\u0006\u0002\u0010\u0007B7\b\u0000\u0012\u0006\u0010\b\u001A\u00020\u0005\u0012\u0006\u0010\t\u001A\u00020\u0005\u0012\u0006\u0010\n\u001A\u00020\u0005\u0012\u0006\u0010\u000B\u001A\u00020\u0005\u0012\u0006\u0010\f\u001A\u00020\u0005\u0012\u0006\u0010\r\u001A\u00020\u0005¢\u0006\u0002\u0010\u000EJ\u0010\u0010\u000F\u001A\u00020\u00052\u0006\u0010\u0010\u001A\u00020\u0005H\u0016J\b\u0010\u0011\u001A\u00020\u0005H\u0016R\u000E\u0010\r\u001A\u00020\u0005X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\u0005X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\u0005X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\u0005X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\u0005X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u0005X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lkotlin/random/XorWowRandom;", "Lkotlin/random/Random;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "seed1", "", "seed2", "(II)V", "x", "y", "z", "w", "v", "addend", "(IIIIII)V", "nextBits", "bitCount", "nextInt", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class XorWowRandom extends Random implements Serializable {
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lkotlin/random/XorWowRandom$Companion;", "", "()V", "serialVersionUID", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    private static final Companion Companion;
    private int addend;
    private static final long serialVersionUID;
    private int v;
    private int w;
    private int x;
    private int y;
    private int z;

    static {
        XorWowRandom.Companion = new Companion(null);
    }

    public XorWowRandom(int v, int v1) {
        this(v, v1, 0, 0, ~v, v << 10 ^ v1 >>> 4);
    }

    public XorWowRandom(int v, int v1, int v2, int v3, int v4, int v5) {
        this.x = v;
        this.y = v1;
        this.z = v2;
        this.w = v3;
        this.v = v4;
        this.addend = v5;
        if((v | v1 | v2 | v3 | v4) == 0) {
            throw new IllegalArgumentException("Initial state must have at least one non-zero element.");
        }
        for(int v6 = 0; v6 < 0x40; ++v6) {
            this.nextInt();
        }
    }

    @Override  // kotlin.random.Random
    public int nextBits(int v) {
        return RandomKt.takeUpperBits(this.nextInt(), v);
    }

    @Override  // kotlin.random.Random
    public int nextInt() {
        int v = this.x ^ this.x >>> 2;
        this.x = this.y;
        this.y = this.z;
        this.z = this.w;
        this.w = this.v;
        int v1 = v ^ v << 1 ^ this.v ^ this.v << 4;
        this.v = v1;
        int v2 = this.addend + 0x587C5;
        this.addend = v2;
        return v1 + v2;
    }
}

