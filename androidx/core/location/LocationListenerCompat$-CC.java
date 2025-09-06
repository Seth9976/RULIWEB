package androidx.core.location;

import android.location.Location;
import android.os.Bundle;
import java.util.List;

public final class LocationListenerCompat.-CC {
    public static void $default$onFlushComplete(LocationListenerCompat _this, int v) {
    }

    public static void $default$onLocationChanged(LocationListenerCompat _this, List list0) {
        int v = list0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            _this.onLocationChanged(((Location)list0.get(v1)));
        }
    }

    public static void $default$onProviderDisabled(LocationListenerCompat _this, String s) {
    }

    public static void $default$onProviderEnabled(LocationListenerCompat _this, String s) {
    }

    public static void $default$onStatusChanged(LocationListenerCompat _this, String s, int v, Bundle bundle0) {
    }
}

