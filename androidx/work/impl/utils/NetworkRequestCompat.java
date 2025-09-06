package androidx.work.impl.utils;

import android.net.NetworkRequest;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0080\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0011\u0012\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0003J\u000B\u0010\n\u001A\u0004\u0018\u00010\u0001HÆ\u0003J\u0015\u0010\u000B\u001A\u00020\u00002\n\b\u0002\u0010\u0002\u001A\u0004\u0018\u00010\u0001HÆ\u0001J\u0013\u0010\f\u001A\u00020\r2\b\u0010\u000E\u001A\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000F\u001A\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001A\u00020\u0012HÖ\u0001R\u0013\u0010\u0004\u001A\u0004\u0018\u00010\u00058G¢\u0006\u0006\u001A\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0002\u001A\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\t¨\u0006\u0014"}, d2 = {"Landroidx/work/impl/utils/NetworkRequestCompat;", "", "wrapped", "(Ljava/lang/Object;)V", "networkRequest", "Landroid/net/NetworkRequest;", "getNetworkRequest", "()Landroid/net/NetworkRequest;", "getWrapped", "()Ljava/lang/Object;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class NetworkRequestCompat {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/work/impl/utils/NetworkRequestCompat$Companion;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        // 去混淆评级： 低(20)
        public final String getTAG() [...] // 潜在的解密器
    }

    public static final Companion Companion;
    private static final String TAG;
    private final Object wrapped;

    static {
        NetworkRequestCompat.Companion = new Companion(null);
        Intrinsics.checkNotNullExpressionValue("WM-NetworkRequestCompat", "tagWithPrefix(\"NetworkRequestCompat\")");
        NetworkRequestCompat.TAG = "WM-NetworkRequestCompat";
    }

    public NetworkRequestCompat() {
        this(null, 1, null);
    }

    public NetworkRequestCompat(Object object0) {
        this.wrapped = object0;
    }

    public NetworkRequestCompat(Object object0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            object0 = null;
        }
        this(object0);
    }

    // 去混淆评级： 低(20)
    public static final String access$getTAG$cp() [...] // 潜在的解密器

    public final Object component1() {
        return this.wrapped;
    }

    public final NetworkRequestCompat copy(Object object0) {
        return new NetworkRequestCompat(object0);
    }

    public static NetworkRequestCompat copy$default(NetworkRequestCompat networkRequestCompat0, Object object0, int v, Object object1) {
        if((v & 1) != 0) {
            object0 = networkRequestCompat0.wrapped;
        }
        return networkRequestCompat0.copy(object0);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof NetworkRequestCompat ? Intrinsics.areEqual(this.wrapped, ((NetworkRequestCompat)object0).wrapped) : false;
    }

    public final NetworkRequest getNetworkRequest() {
        return (NetworkRequest)this.wrapped;
    }

    public final Object getWrapped() {
        return this.wrapped;
    }

    @Override
    public int hashCode() {
        return this.wrapped == null ? 0 : this.wrapped.hashCode();
    }

    @Override
    public String toString() {
        return "NetworkRequestCompat(wrapped=" + this.wrapped + ')';
    }
}

