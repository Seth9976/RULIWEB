package androidx.core.location;

import android.location.GnssStatus;
import android.location.GpsStatus;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract class GnssStatusCompat {
    public static abstract class Callback {
        public void onFirstFix(int v) {
        }

        public void onSatelliteStatusChanged(GnssStatusCompat gnssStatusCompat0) {
        }

        public void onStarted() {
        }

        public void onStopped() {
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ConstellationType {
    }

    public static final int CONSTELLATION_BEIDOU = 5;
    public static final int CONSTELLATION_GALILEO = 6;
    public static final int CONSTELLATION_GLONASS = 3;
    public static final int CONSTELLATION_GPS = 1;
    public static final int CONSTELLATION_IRNSS = 7;
    public static final int CONSTELLATION_QZSS = 4;
    public static final int CONSTELLATION_SBAS = 2;
    public static final int CONSTELLATION_UNKNOWN;

    public abstract float getAzimuthDegrees(int arg1);

    public abstract float getBasebandCn0DbHz(int arg1);

    public abstract float getCarrierFrequencyHz(int arg1);

    public abstract float getCn0DbHz(int arg1);

    public abstract int getConstellationType(int arg1);

    public abstract float getElevationDegrees(int arg1);

    public abstract int getSatelliteCount();

    public abstract int getSvid(int arg1);

    public abstract boolean hasAlmanacData(int arg1);

    public abstract boolean hasBasebandCn0DbHz(int arg1);

    public abstract boolean hasCarrierFrequencyHz(int arg1);

    public abstract boolean hasEphemerisData(int arg1);

    public abstract boolean usedInFix(int arg1);

    public static GnssStatusCompat wrap(GnssStatus gnssStatus0) {
        return new GnssStatusWrapper(gnssStatus0);
    }

    public static GnssStatusCompat wrap(GpsStatus gpsStatus0) {
        return new GpsStatusWrapper(gpsStatus0);
    }
}

