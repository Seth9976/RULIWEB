package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import androidx.core.net.ConnectivityManagerCompat;
import androidx.work.Logger;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.utils.NetworkApi21;
import androidx.work.impl.utils.NetworkApi23;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\u001E\u0010\u000E\u001A\b\u0012\u0004\u0012\u00020\u00030\u000F2\u0006\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0012\u001A\u00020\u0013H\u0007\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001E\u0010\u0002\u001A\u00020\u0003*\u00020\u00048@X\u0080\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0006\u001A\u0004\b\u0007\u0010\b\"\u0018\u0010\u0002\u001A\u00020\u0003*\u00020\t8AX\u0080\u0004¢\u0006\u0006\u001A\u0004\b\u0007\u0010\n\"\u0018\u0010\u000B\u001A\u00020\f*\u00020\u00048@X\u0080\u0004¢\u0006\u0006\u001A\u0004\b\u000B\u0010\r¨\u0006\u0014"}, d2 = {"TAG", "", "activeNetworkState", "Landroidx/work/impl/constraints/NetworkState;", "Landroid/net/ConnectivityManager;", "getActiveNetworkState$annotations", "(Landroid/net/ConnectivityManager;)V", "getActiveNetworkState", "(Landroid/net/ConnectivityManager;)Landroidx/work/impl/constraints/NetworkState;", "Landroid/net/NetworkCapabilities;", "(Landroid/net/NetworkCapabilities;)Landroidx/work/impl/constraints/NetworkState;", "isActiveNetworkValidated", "", "(Landroid/net/ConnectivityManager;)Z", "NetworkStateTracker", "Landroidx/work/impl/constraints/trackers/ConstraintTracker;", "context", "Landroid/content/Context;", "taskExecutor", "Landroidx/work/impl/utils/taskexecutor/TaskExecutor;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class NetworkStateTrackerKt {
    private static final String TAG;

    // 去混淆评级： 低(20)
    static {
        Intrinsics.checkNotNullExpressionValue("WM-NetworkStateTracker", "tagWithPrefix(\"NetworkStateTracker\")");
        NetworkStateTrackerKt.TAG = "WM-NetworkStateTracker";
    }

    public static final ConstraintTracker NetworkStateTracker(Context context0, TaskExecutor taskExecutor0) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(taskExecutor0, "taskExecutor");
        return Build.VERSION.SDK_INT >= 24 ? new NetworkStateTracker24(context0, taskExecutor0) : new NetworkStateTrackerPre24(context0, taskExecutor0);
    }

    public static final String access$getTAG$p() [...] // 潜在的解密器

    public static final NetworkState getActiveNetworkState(ConnectivityManager connectivityManager0) {
        Intrinsics.checkNotNullParameter(connectivityManager0, "<this>");
        NetworkInfo networkInfo0 = connectivityManager0.getActiveNetworkInfo();
        boolean z = true;
        boolean z1 = networkInfo0 != null && networkInfo0.isConnected();
        boolean z2 = NetworkStateTrackerKt.isActiveNetworkValidated(connectivityManager0);
        boolean z3 = ConnectivityManagerCompat.isActiveNetworkMetered(connectivityManager0);
        if(networkInfo0 == null || networkInfo0.isRoaming()) {
            z = false;
        }
        return new NetworkState(z1, z2, z3, z);
    }

    public static final NetworkState getActiveNetworkState(NetworkCapabilities networkCapabilities0) {
        Intrinsics.checkNotNullParameter(networkCapabilities0, "<this>");
        return new NetworkState(networkCapabilities0.hasCapability(12), networkCapabilities0.hasCapability(16), !networkCapabilities0.hasCapability(11), networkCapabilities0.hasCapability(18));
    }

    public static void getActiveNetworkState$annotations(ConnectivityManager connectivityManager0) {
    }

    public static final boolean isActiveNetworkValidated(ConnectivityManager connectivityManager0) {
        Intrinsics.checkNotNullParameter(connectivityManager0, "<this>");
        if(Build.VERSION.SDK_INT < 23) {
            return false;
        }
        try {
            NetworkCapabilities networkCapabilities0 = NetworkApi21.getNetworkCapabilitiesCompat(connectivityManager0, NetworkApi23.getActiveNetworkCompat(connectivityManager0));
            if(networkCapabilities0 != null) {
                return NetworkApi21.hasCapabilityCompat(networkCapabilities0, 16);
            }
        }
        catch(SecurityException securityException0) {
            Logger.get().error("WM-NetworkStateTracker", "Unable to validate active network", securityException0);
        }
        return false;
    }
}

