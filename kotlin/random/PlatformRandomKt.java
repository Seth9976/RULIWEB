package kotlin.random;

import java.util.Random;
import kotlin.Metadata;
import kotlin.internal.PlatformImplementationsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\t\u0010\u0000\u001A\u00020\u0001H\u0081\b\u001A\u0018\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u0005H\u0000\u001A\f\u0010\u0007\u001A\u00020\b*\u00020\u0001H\u0007\u001A\f\u0010\t\u001A\u00020\u0001*\u00020\bH\u0007¨\u0006\n"}, d2 = {"defaultPlatformRandom", "Lkotlin/random/Random;", "doubleFromParts", "", "hi26", "", "low27", "asJavaRandom", "Ljava/util/Random;", "asKotlinRandom", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class PlatformRandomKt {
    public static final Random asJavaRandom(kotlin.random.Random random0) {
        Intrinsics.checkNotNullParameter(random0, "<this>");
        AbstractPlatformRandom abstractPlatformRandom0 = random0 instanceof AbstractPlatformRandom ? ((AbstractPlatformRandom)random0) : null;
        if(abstractPlatformRandom0 != null) {
            Random random1 = abstractPlatformRandom0.getImpl();
            if(random1 != null) {
                return random1;
            }
        }
        return new KotlinRandom(random0);
    }

    public static final kotlin.random.Random asKotlinRandom(Random random0) {
        Intrinsics.checkNotNullParameter(random0, "<this>");
        KotlinRandom kotlinRandom0 = random0 instanceof KotlinRandom ? ((KotlinRandom)random0) : null;
        if(kotlinRandom0 != null) {
            kotlin.random.Random random1 = kotlinRandom0.getImpl();
            if(random1 != null) {
                return random1;
            }
        }
        return new PlatformRandom(random0);
    }

    private static final kotlin.random.Random defaultPlatformRandom() {
        return PlatformImplementationsKt.IMPLEMENTATIONS.defaultPlatformRandom();
    }

    public static final double doubleFromParts(int v, int v1) {
        return ((double)((((long)v) << 27) + ((long)v1))) / 9007199254740992.0;
    }
}

