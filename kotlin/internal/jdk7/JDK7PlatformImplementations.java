package kotlin.internal.jdk7;

import android.os.Build.VERSION;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.internal.PlatformImplementations;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0001\u000EB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u0006H\u0016J\u0016\u0010\b\u001A\b\u0012\u0004\u0012\u00020\u00060\t2\u0006\u0010\u0007\u001A\u00020\u0006H\u0016J\u0010\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00020\rH\u0002¨\u0006\u000F"}, d2 = {"Lkotlin/internal/jdk7/JDK7PlatformImplementations;", "Lkotlin/internal/PlatformImplementations;", "()V", "addSuppressed", "", "cause", "", "exception", "getSuppressed", "", "sdkIsNullOrAtLeast", "", "version", "", "ReflectSdkVersion", "kotlin-stdlib-jdk7"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public class JDK7PlatformImplementations extends PlatformImplementations {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001A\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkotlin/internal/jdk7/JDK7PlatformImplementations$ReflectSdkVersion;", "", "()V", "sdkVersion", "", "Ljava/lang/Integer;", "kotlin-stdlib-jdk7"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    static final class ReflectSdkVersion {
        public static final ReflectSdkVersion INSTANCE;
        public static final Integer sdkVersion;

        static {
            ReflectSdkVersion.INSTANCE = new ReflectSdkVersion();
            Integer integer0 = null;
            Integer integer1 = Build.VERSION.SDK_INT;
            Integer integer2 = integer1 instanceof Integer ? integer1 : null;
            if(integer2 != null && integer2.intValue() > 0) {
                integer0 = integer2;
            }
            ReflectSdkVersion.sdkVersion = integer0;
        }
    }

    @Override  // kotlin.internal.PlatformImplementations
    public void addSuppressed(Throwable throwable0, Throwable throwable1) {
        Intrinsics.checkNotNullParameter(throwable0, "cause");
        Intrinsics.checkNotNullParameter(throwable1, "exception");
        throwable0.addSuppressed(throwable1);
    }

    @Override  // kotlin.internal.PlatformImplementations
    public List getSuppressed(Throwable throwable0) {
        Intrinsics.checkNotNullParameter(throwable0, "exception");
        Throwable[] arr_throwable = throwable0.getSuppressed();
        Intrinsics.checkNotNullExpressionValue(arr_throwable, "exception.suppressed");
        return ArraysKt.asList(arr_throwable);
    }

    private final boolean sdkIsNullOrAtLeast(int v) [...] // 潜在的解密器
}

