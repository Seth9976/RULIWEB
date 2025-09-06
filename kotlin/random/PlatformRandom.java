package kotlin.random;

import java.io.Serializable;
import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u0000 \t2\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0001\tB\r\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0014\u0010\u0004\u001A\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lkotlin/random/PlatformRandom;", "Lkotlin/random/AbstractPlatformRandom;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "impl", "Ljava/util/Random;", "(Ljava/util/Random;)V", "getImpl", "()Ljava/util/Random;", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class PlatformRandom extends AbstractPlatformRandom implements Serializable {
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lkotlin/random/PlatformRandom$Companion;", "", "()V", "serialVersionUID", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    private static final Companion Companion;
    private final Random impl;
    private static final long serialVersionUID;

    static {
        PlatformRandom.Companion = new Companion(null);
    }

    public PlatformRandom(Random random0) {
        Intrinsics.checkNotNullParameter(random0, "impl");
        super();
        this.impl = random0;
    }

    @Override  // kotlin.random.AbstractPlatformRandom
    public Random getImpl() {
        return this.impl;
    }
}

