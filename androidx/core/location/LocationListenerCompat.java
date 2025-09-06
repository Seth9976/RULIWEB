package androidx.core.location;

import android.location.LocationListener;
import android.os.Bundle;
import java.util.List;

public interface LocationListenerCompat extends LocationListener {
    @Override  // android.location.LocationListener
    void onFlushComplete(int arg1);

    @Override  // android.location.LocationListener
    void onLocationChanged(List arg1);

    @Override  // android.location.LocationListener
    void onProviderDisabled(String arg1);

    @Override  // android.location.LocationListener
    void onProviderEnabled(String arg1);

    @Override  // android.location.LocationListener
    void onStatusChanged(String arg1, int arg2, Bundle arg3);
}

