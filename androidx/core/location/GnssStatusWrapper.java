package androidx.core.location;

import android.location.GnssStatus;
import android.os.Build.VERSION;
import androidx.core.graphics.ColorKt..ExternalSyntheticApiModelOutline0;
import androidx.core.util.Preconditions;

class GnssStatusWrapper extends GnssStatusCompat {
    static class Api26Impl {
        static float getCarrierFrequencyHz(GnssStatus gnssStatus0, int v) {
            return gnssStatus0.getCarrierFrequencyHz(v);
        }

        static boolean hasCarrierFrequencyHz(GnssStatus gnssStatus0, int v) {
            return gnssStatus0.hasCarrierFrequencyHz(v);
        }
    }

    static class Api30Impl {
        static float getBasebandCn0DbHz(GnssStatus gnssStatus0, int v) {
            return gnssStatus0.getBasebandCn0DbHz(v);
        }

        static boolean hasBasebandCn0DbHz(GnssStatus gnssStatus0, int v) {
            return gnssStatus0.hasBasebandCn0DbHz(v);
        }
    }

    private final GnssStatus mWrapped;

    GnssStatusWrapper(Object object0) {
        this.mWrapped = ColorKt..ExternalSyntheticApiModelOutline0.m(Preconditions.checkNotNull(((GnssStatus)object0)));
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof GnssStatusWrapper ? this.mWrapped.equals(((GnssStatusWrapper)object0).mWrapped) : false;
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public float getAzimuthDegrees(int v) {
        return this.mWrapped.getAzimuthDegrees(v);
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public float getBasebandCn0DbHz(int v) {
        if(Build.VERSION.SDK_INT < 30) {
            throw new UnsupportedOperationException();
        }
        return Api30Impl.getBasebandCn0DbHz(this.mWrapped, v);
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public float getCarrierFrequencyHz(int v) {
        if(Build.VERSION.SDK_INT < 26) {
            throw new UnsupportedOperationException();
        }
        return Api26Impl.getCarrierFrequencyHz(this.mWrapped, v);
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public float getCn0DbHz(int v) {
        return this.mWrapped.getCn0DbHz(v);
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public int getConstellationType(int v) {
        return this.mWrapped.getConstellationType(v);
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public float getElevationDegrees(int v) {
        return this.mWrapped.getElevationDegrees(v);
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public int getSatelliteCount() {
        return this.mWrapped.getSatelliteCount();
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public int getSvid(int v) {
        return this.mWrapped.getSvid(v);
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public boolean hasAlmanacData(int v) {
        return this.mWrapped.hasAlmanacData(v);
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public boolean hasBasebandCn0DbHz(int v) {
        return Build.VERSION.SDK_INT < 30 ? false : Api30Impl.hasBasebandCn0DbHz(this.mWrapped, v);
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public boolean hasCarrierFrequencyHz(int v) {
        return Build.VERSION.SDK_INT < 26 ? false : Api26Impl.hasCarrierFrequencyHz(this.mWrapped, v);
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public boolean hasEphemerisData(int v) {
        return this.mWrapped.hasEphemerisData(v);
    }

    @Override
    public int hashCode() {
        return this.mWrapped.hashCode();
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public boolean usedInFix(int v) {
        return this.mWrapped.usedInFix(v);
    }
}

