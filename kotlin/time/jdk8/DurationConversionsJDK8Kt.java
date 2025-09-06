package kotlin.time.jdk8;

import java.time.Duration;
import kotlin.Metadata;
import kotlin.io.path.PathTreeWalk..ExternalSyntheticApiModelOutline0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001A\u001A\u0010\u0000\u001A\u00020\u0001*\u00020\u0002H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0003\u0010\u0004\u001A\u0015\u0010\u0005\u001A\u00020\u0002*\u00020\u0001H\u0087\bø\u0001\u0001¢\u0006\u0002\u0010\u0006\u0082\u0002\u000B\n\u0005\b¡\u001E0\u0001\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"toJavaDuration", "Ljava/time/Duration;", "Lkotlin/time/Duration;", "toJavaDuration-LRDsOJo", "(J)Ljava/time/Duration;", "toKotlinDuration", "(Ljava/time/Duration;)J", "kotlin-stdlib-jdk8"}, k = 2, mv = {1, 9, 0}, pn = "kotlin.time", xi = 0x30)
public final class DurationConversionsJDK8Kt {
    private static final Duration toJavaDuration-LRDsOJo(long v) {
        Duration duration0 = PathTreeWalk..ExternalSyntheticApiModelOutline0.m(kotlin.time.Duration.getInWholeSeconds-impl(v), kotlin.time.Duration.getNanosecondsComponent-impl(v));
        Intrinsics.checkNotNullExpressionValue(duration0, "toJavaDuration-LRDsOJo");
        return duration0;
    }

    private static final long toKotlinDuration(Duration duration0) {
        Intrinsics.checkNotNullParameter(duration0, "<this>");
        return kotlin.time.Duration.plus-LRDsOJo(DurationKt.toDuration(PathTreeWalk..ExternalSyntheticApiModelOutline0.m(duration0), DurationUnit.SECONDS), DurationKt.toDuration(PathTreeWalk..ExternalSyntheticApiModelOutline0.m(duration0), DurationUnit.NANOSECONDS));
    }
}

