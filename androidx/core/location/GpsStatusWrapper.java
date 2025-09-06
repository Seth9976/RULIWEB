package androidx.core.location;

import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.os.Build.VERSION;
import androidx.core.util.Preconditions;
import java.util.Iterator;

class GpsStatusWrapper extends GnssStatusCompat {
    private static final int BEIDOU_PRN_COUNT = 35;
    private static final int BEIDOU_PRN_OFFSET = 200;
    private static final int GLONASS_PRN_COUNT = 24;
    private static final int GLONASS_PRN_OFFSET = 0x40;
    private static final int GPS_PRN_COUNT = 0x20;
    private static final int GPS_PRN_OFFSET = 0;
    private static final int QZSS_SVID_MAX = 200;
    private static final int QZSS_SVID_MIN = 0xC1;
    private static final int SBAS_PRN_MAX = 0x40;
    private static final int SBAS_PRN_MIN = 33;
    private static final int SBAS_PRN_OFFSET = -87;
    private Iterator mCachedIterator;
    private int mCachedIteratorPosition;
    private GpsSatellite mCachedSatellite;
    private int mCachedSatelliteCount;
    private final GpsStatus mWrapped;

    GpsStatusWrapper(GpsStatus gpsStatus0) {
        GpsStatus gpsStatus1 = (GpsStatus)Preconditions.checkNotNull(gpsStatus0);
        this.mWrapped = gpsStatus1;
        this.mCachedSatelliteCount = -1;
        this.mCachedIterator = gpsStatus1.getSatellites().iterator();
        this.mCachedIteratorPosition = -1;
        this.mCachedSatellite = null;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof GpsStatusWrapper ? this.mWrapped.equals(((GpsStatusWrapper)object0).mWrapped) : false;
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public float getAzimuthDegrees(int v) {
        return this.getSatellite(v).getAzimuth();
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public float getBasebandCn0DbHz(int v) {
        throw new UnsupportedOperationException();
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public float getCarrierFrequencyHz(int v) {
        throw new UnsupportedOperationException();
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public float getCn0DbHz(int v) {
        return this.getSatellite(v).getSnr();
    }

    private static int getConstellationFromPrn(int v) {
        if(v > 0 && v <= 0x20) {
            return 1;
        }
        if(v >= 33 && v <= 0x40) {
            return 2;
        }
        if(v > 0x40 && v <= 88) {
            return 3;
        }
        if(v > 200 && v <= 0xEB) {
            return 5;
        }
        return v < 0xC1 || v > 200 ? 0 : 4;
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public int getConstellationType(int v) {
        return Build.VERSION.SDK_INT >= 24 ? GpsStatusWrapper.getConstellationFromPrn(this.getSatellite(v).getPrn()) : 1;
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public float getElevationDegrees(int v) {
        return this.getSatellite(v).getElevation();
    }

    private GpsSatellite getSatellite(int v) {
        synchronized(this.mWrapped) {
            if(v < this.mCachedIteratorPosition) {
                this.mCachedIterator = this.mWrapped.getSatellites().iterator();
                this.mCachedIteratorPosition = -1;
            }
            int v2;
            while((v2 = this.mCachedIteratorPosition) < v) {
                this.mCachedIteratorPosition = v2 + 1;
                if(!this.mCachedIterator.hasNext()) {
                    this.mCachedSatellite = null;
                    break;
                }
                Object object0 = this.mCachedIterator.next();
                this.mCachedSatellite = (GpsSatellite)object0;
            }
        }
        return (GpsSatellite)Preconditions.checkNotNull(this.mCachedSatellite);
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public int getSatelliteCount() {
        synchronized(this.mWrapped) {
            if(this.mCachedSatelliteCount == -1) {
                for(Object object0: this.mWrapped.getSatellites()) {
                    GpsSatellite gpsSatellite0 = (GpsSatellite)object0;
                    ++this.mCachedSatelliteCount;
                }
                ++this.mCachedSatelliteCount;
            }
            return this.mCachedSatelliteCount;
        }
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public int getSvid(int v) {
        return Build.VERSION.SDK_INT >= 24 ? GpsStatusWrapper.getSvidFromPrn(this.getSatellite(v).getPrn()) : this.getSatellite(v).getPrn();
    }

    private static int getSvidFromPrn(int v) {
        switch(GpsStatusWrapper.getConstellationFromPrn(v)) {
            case 2: {
                return v + 87;
            }
            case 3: {
                return v - 0x40;
            }
            case 5: {
                return v - 200;
            }
            default: {
                return v;
            }
        }
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public boolean hasAlmanacData(int v) {
        return this.getSatellite(v).hasAlmanac();
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public boolean hasBasebandCn0DbHz(int v) {
        return false;
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public boolean hasCarrierFrequencyHz(int v) {
        return false;
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public boolean hasEphemerisData(int v) {
        return this.getSatellite(v).hasEphemeris();
    }

    @Override
    public int hashCode() {
        return this.mWrapped.hashCode();
    }

    @Override  // androidx.core.location.GnssStatusCompat
    public boolean usedInFix(int v) {
        return this.getSatellite(v).usedInFix();
    }
}

