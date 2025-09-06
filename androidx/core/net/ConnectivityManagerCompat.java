package androidx.core.net;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import androidx.annotation.ReplaceWith;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ConnectivityManagerCompat {
    static class Api24Impl {
        static int getRestrictBackgroundStatus(ConnectivityManager connectivityManager0) {
            return connectivityManager0.getRestrictBackgroundStatus();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface RestrictBackgroundStatus {
    }

    public static final int RESTRICT_BACKGROUND_STATUS_DISABLED = 1;
    public static final int RESTRICT_BACKGROUND_STATUS_ENABLED = 3;
    public static final int RESTRICT_BACKGROUND_STATUS_WHITELISTED = 2;

    public static NetworkInfo getNetworkInfoFromBroadcast(ConnectivityManager connectivityManager0, Intent intent0) {
        NetworkInfo networkInfo0 = (NetworkInfo)intent0.getParcelableExtra("networkInfo");
        return networkInfo0 == null ? null : connectivityManager0.getNetworkInfo(networkInfo0.getType());
    }

    public static int getRestrictBackgroundStatus(ConnectivityManager connectivityManager0) {
        return Build.VERSION.SDK_INT < 24 ? 3 : Api24Impl.getRestrictBackgroundStatus(connectivityManager0);
    }

    @ReplaceWith(expression = "cm.isActiveNetworkMetered()")
    @Deprecated
    public static boolean isActiveNetworkMetered(ConnectivityManager connectivityManager0) {
        return connectivityManager0.isActiveNetworkMetered();
    }
}

