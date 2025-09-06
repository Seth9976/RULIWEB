package kotlin.reflect.jvm.internal.impl.platform;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

public final class PlatformUtilKt {
    public static final String getPresentableDescription(TargetPlatform targetPlatform0) {
        Intrinsics.checkNotNullParameter(targetPlatform0, "<this>");
        return CollectionsKt.joinToString$default(targetPlatform0.getComponentPlatforms(), "/", null, null, 0, null, null, 62, null);
    }
}

