package com.navercorp.nid.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import com.navercorp.nid.NaverIdLoginSDK;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001A\u00020\u0004J\u0006\u0010\u0005\u001A\u00020\u0006J\b\u0010\u0007\u001A\u00020\u0006H\u0002J\u0010\u0010\b\u001A\u00020\u00062\u0006\u0010\t\u001A\u00020\nH\u0002J\u0006\u0010\u000B\u001A\u00020\u0006J\b\u0010\f\u001A\u00020\u0006H\u0002¨\u0006\r"}, d2 = {"Lcom/navercorp/nid/util/NidNetworkUtil;", "", "()V", "getType", "", "isAvailable", "", "isCellularConnected", "isConnected", "connectType", "", "isNotAvailable", "isWifiConnected", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NidNetworkUtil {
    public static final NidNetworkUtil INSTANCE;

    static {
        NidNetworkUtil.INSTANCE = new NidNetworkUtil();
    }

    public final String getType() {
        if(this.isCellularConnected()) {
            return "cell";
        }
        return this.isWifiConnected() ? "wifi" : "other";
    }

    public final boolean isAvailable() {
        if(Build.VERSION.SDK_INT <= 24) {
            Context context0 = NaverIdLoginSDK.INSTANCE.getApplicationContext();
            NetworkInfo networkInfo0 = NidApplicationUtil.INSTANCE.getConnectivityManager(context0).getActiveNetworkInfo();
            return networkInfo0 != null && networkInfo0.isConnected();
        }
        Context context1 = NaverIdLoginSDK.INSTANCE.getApplicationContext();
        ConnectivityManager connectivityManager0 = NidApplicationUtil.INSTANCE.getConnectivityManager(context1);
        return connectivityManager0.getNetworkCapabilities(connectivityManager0.getActiveNetwork()) != null;
    }

    private final boolean isCellularConnected() {
        if(Build.VERSION.SDK_INT <= 24) {
            return this.isConnected(0);
        }
        Context context0 = NaverIdLoginSDK.INSTANCE.getApplicationContext();
        ConnectivityManager connectivityManager0 = NidApplicationUtil.INSTANCE.getConnectivityManager(context0);
        NetworkCapabilities networkCapabilities0 = connectivityManager0.getNetworkCapabilities(connectivityManager0.getActiveNetwork());
        return networkCapabilities0 != null && networkCapabilities0.hasTransport(0);
    }

    private final boolean isConnected(int v) {
        Object object0 = NaverIdLoginSDK.INSTANCE.getApplicationContext().getSystemService("connectivity");
        if(object0 == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.net.ConnectivityManager");
        }
        if(Build.VERSION.SDK_INT < 23) {
            NetworkInfo networkInfo0 = ((ConnectivityManager)object0).getNetworkInfo(v);
            return networkInfo0 != null && networkInfo0.isConnected();
        }
        Network[] arr_network = ((ConnectivityManager)object0).getAllNetworks();
        Intrinsics.checkNotNullExpressionValue(arr_network, "manager.allNetworks");
        for(int v1 = 0; v1 < arr_network.length; ++v1) {
            NetworkInfo networkInfo1 = ((ConnectivityManager)object0).getNetworkInfo(arr_network[v1]);
            if(networkInfo1 != null && networkInfo1.getType() == v && networkInfo1.isConnected()) {
                return true;
            }
        }
        return false;
    }

    public final boolean isNotAvailable() {
        return !this.isAvailable();
    }

    private final boolean isWifiConnected() {
        if(Build.VERSION.SDK_INT <= 24) {
            return this.isConnected(1);
        }
        Context context0 = NaverIdLoginSDK.INSTANCE.getApplicationContext();
        ConnectivityManager connectivityManager0 = NidApplicationUtil.INSTANCE.getConnectivityManager(context0);
        NetworkCapabilities networkCapabilities0 = connectivityManager0.getNetworkCapabilities(connectivityManager0.getActiveNetwork());
        return networkCapabilities0 != null && networkCapabilities0.hasTransport(1);
    }
}

