package androidx.appcompat.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import androidx.core.content.PermissionChecker;
import java.util.Calendar;

class TwilightManager {
    static class TwilightState {
        boolean isNight;
        long nextUpdate;

    }

    private static final int SUNRISE = 6;
    private static final int SUNSET = 22;
    private static final String TAG = "TwilightManager";
    private final Context mContext;
    private final LocationManager mLocationManager;
    private final TwilightState mTwilightState;
    private static TwilightManager sInstance;

    TwilightManager(Context context0, LocationManager locationManager0) {
        this.mTwilightState = new TwilightState();
        this.mContext = context0;
        this.mLocationManager = locationManager0;
    }

    static TwilightManager getInstance(Context context0) {
        if(TwilightManager.sInstance == null) {
            Context context1 = context0.getApplicationContext();
            TwilightManager.sInstance = new TwilightManager(context1, ((LocationManager)context1.getSystemService("location")));
        }
        return TwilightManager.sInstance;
    }

    private Location getLastKnownLocation() {
        Location location0 = null;
        Location location1 = PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0 ? this.getLastKnownLocationForProvider("network") : null;
        if(PermissionChecker.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") == 0) {
            location0 = this.getLastKnownLocationForProvider("gps");
        }
        if(location0 != null && location1 != null) {
            return location0.getTime() <= location1.getTime() ? location1 : location0;
        }
        return location0 == null ? location1 : location0;
    }

    private Location getLastKnownLocationForProvider(String s) {
        try {
            if(this.mLocationManager.isProviderEnabled(s)) {
                return this.mLocationManager.getLastKnownLocation(s);
            }
        }
        catch(Exception exception0) {
            Log.d("TwilightManager", "Failed to get last known location", exception0);
        }
        return null;
    }

    boolean isNight() {
        TwilightState twilightManager$TwilightState0 = this.mTwilightState;
        if(this.isStateValid()) {
            return twilightManager$TwilightState0.isNight;
        }
        Location location0 = this.getLastKnownLocation();
        if(location0 != null) {
            this.updateState(location0);
            return twilightManager$TwilightState0.isNight;
        }
        Log.i("TwilightManager", "Could not get last known location. This is probably because the app does not have any location permissions. Falling back to hardcoded sunrise/sunset values.");
        int v = Calendar.getInstance().get(11);
        return v < 6 || v >= 22;
    }

    private boolean isStateValid() {
        return this.mTwilightState.nextUpdate > System.currentTimeMillis();
    }

    static void setInstance(TwilightManager twilightManager0) {
        TwilightManager.sInstance = twilightManager0;
    }

    private void updateState(Location location0) {
        long v4;
        TwilightState twilightManager$TwilightState0 = this.mTwilightState;
        long v = System.currentTimeMillis();
        TwilightCalculator twilightCalculator0 = TwilightCalculator.getInstance();
        twilightCalculator0.calculateTwilight(v - 86400000L, location0.getLatitude(), location0.getLongitude());
        twilightCalculator0.calculateTwilight(v, location0.getLatitude(), location0.getLongitude());
        boolean z = twilightCalculator0.state == 1;
        long v1 = twilightCalculator0.sunrise;
        long v2 = twilightCalculator0.sunset;
        twilightCalculator0.calculateTwilight(v + 86400000L, location0.getLatitude(), location0.getLongitude());
        long v3 = twilightCalculator0.sunrise;
        if(v1 == -1L || v2 == -1L) {
            v4 = v + 43200000L;
        }
        else {
            if(v > v2) {
                v1 = v3;
            }
            else if(v > v1) {
                v1 = v2;
            }
            v4 = v1 + 60000L;
        }
        twilightManager$TwilightState0.isNight = z;
        twilightManager$TwilightState0.nextUpdate = v4;
    }
}

