package androidx.work.impl.utils;

import android.net.NetworkRequest;
import android.net.NetworkSpecifier;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001A\u00020\u0006¨\u0006\u0007"}, d2 = {"Landroidx/work/impl/utils/NetworkRequest30;", "", "()V", "getNetworkSpecifier", "Landroid/net/NetworkSpecifier;", "request", "Landroid/net/NetworkRequest;", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class NetworkRequest30 {
    public static final NetworkRequest30 INSTANCE;

    static {
        NetworkRequest30.INSTANCE = new NetworkRequest30();
    }

    public final NetworkSpecifier getNetworkSpecifier(NetworkRequest networkRequest0) {
        Intrinsics.checkNotNullParameter(networkRequest0, "request");
        return NetworkApi23..ExternalSyntheticApiModelOutline0.m(networkRequest0);
    }
}

