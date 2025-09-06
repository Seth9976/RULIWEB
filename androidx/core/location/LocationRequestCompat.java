package androidx.core.location;

import android.location.LocationRequest.Builder;
import android.location.LocationRequest;
import android.os.Build.VERSION;
import androidx.core.util.HalfKt..ExternalSyntheticApiModelOutline0;
import androidx.core.util.Preconditions;
import androidx.core.util.TimeUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class LocationRequestCompat {
    static class Api19Impl {
        private static Method sCreateFromDeprecatedProviderMethod;
        private static Class sLocationRequestClass;
        private static Method sSetExpireInMethod;
        private static Method sSetFastestIntervalMethod;
        private static Method sSetNumUpdatesMethod;
        private static Method sSetQualityMethod;

        public static Object toLocationRequest(LocationRequestCompat locationRequestCompat0, String s) {
            try {
                if(Api19Impl.sLocationRequestClass == null) {
                    Api19Impl.sLocationRequestClass = LocationRequest.class;
                }
                if(Api19Impl.sCreateFromDeprecatedProviderMethod == null) {
                    Method method0 = Api19Impl.sLocationRequestClass.getDeclaredMethod("createFromDeprecatedProvider", String.class, Long.TYPE, Float.TYPE, Boolean.TYPE);
                    Api19Impl.sCreateFromDeprecatedProviderMethod = method0;
                    method0.setAccessible(true);
                }
                Object object0 = Api19Impl.sCreateFromDeprecatedProviderMethod.invoke(null, s, locationRequestCompat0.getIntervalMillis(), locationRequestCompat0.getMinUpdateDistanceMeters(), Boolean.FALSE);
                if(object0 == null) {
                    return null;
                }
                if(Api19Impl.sSetQualityMethod == null) {
                    Method method1 = Api19Impl.sLocationRequestClass.getDeclaredMethod("setQuality", Integer.TYPE);
                    Api19Impl.sSetQualityMethod = method1;
                    method1.setAccessible(true);
                }
                Api19Impl.sSetQualityMethod.invoke(object0, locationRequestCompat0.getQuality());
                if(Api19Impl.sSetFastestIntervalMethod == null) {
                    Method method2 = Api19Impl.sLocationRequestClass.getDeclaredMethod("setFastestInterval", Long.TYPE);
                    Api19Impl.sSetFastestIntervalMethod = method2;
                    method2.setAccessible(true);
                }
                Api19Impl.sSetFastestIntervalMethod.invoke(object0, locationRequestCompat0.getMinUpdateIntervalMillis());
                if(locationRequestCompat0.getMaxUpdates() < 0x7FFFFFFF) {
                    if(Api19Impl.sSetNumUpdatesMethod == null) {
                        Method method3 = Api19Impl.sLocationRequestClass.getDeclaredMethod("setNumUpdates", Integer.TYPE);
                        Api19Impl.sSetNumUpdatesMethod = method3;
                        method3.setAccessible(true);
                    }
                    Api19Impl.sSetNumUpdatesMethod.invoke(object0, locationRequestCompat0.getMaxUpdates());
                }
                if(locationRequestCompat0.getDurationMillis() < 0x7FFFFFFFFFFFFFFFL) {
                    if(Api19Impl.sSetExpireInMethod == null) {
                        Method method4 = Api19Impl.sLocationRequestClass.getDeclaredMethod("setExpireIn", Long.TYPE);
                        Api19Impl.sSetExpireInMethod = method4;
                        method4.setAccessible(true);
                    }
                    Api19Impl.sSetExpireInMethod.invoke(object0, locationRequestCompat0.getDurationMillis());
                }
                return object0;
            }
            catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException unused_ex) {
                return null;
            }
        }
    }

    static class Api31Impl {
        public static LocationRequest toLocationRequest(LocationRequestCompat locationRequestCompat0) {
            return new LocationRequest.Builder(locationRequestCompat0.getIntervalMillis()).setQuality(locationRequestCompat0.getQuality()).setMinUpdateIntervalMillis(locationRequestCompat0.getMinUpdateIntervalMillis()).setDurationMillis(locationRequestCompat0.getDurationMillis()).setMaxUpdates(locationRequestCompat0.getMaxUpdates()).setMinUpdateDistanceMeters(locationRequestCompat0.getMinUpdateDistanceMeters()).setMaxUpdateDelayMillis(locationRequestCompat0.getMaxUpdateDelayMillis()).build();
        }
    }

    public static final class Builder {
        private long mDurationMillis;
        private long mIntervalMillis;
        private long mMaxUpdateDelayMillis;
        private int mMaxUpdates;
        private float mMinUpdateDistanceMeters;
        private long mMinUpdateIntervalMillis;
        private int mQuality;

        public Builder(long v) {
            this.setIntervalMillis(v);
            this.mQuality = 102;
            this.mDurationMillis = 0x7FFFFFFFFFFFFFFFL;
            this.mMaxUpdates = 0x7FFFFFFF;
            this.mMinUpdateIntervalMillis = -1L;
            this.mMinUpdateDistanceMeters = 0.0f;
            this.mMaxUpdateDelayMillis = 0L;
        }

        public Builder(LocationRequestCompat locationRequestCompat0) {
            this.mIntervalMillis = locationRequestCompat0.mIntervalMillis;
            this.mQuality = locationRequestCompat0.mQuality;
            this.mDurationMillis = locationRequestCompat0.mDurationMillis;
            this.mMaxUpdates = locationRequestCompat0.mMaxUpdates;
            this.mMinUpdateIntervalMillis = locationRequestCompat0.mMinUpdateIntervalMillis;
            this.mMinUpdateDistanceMeters = locationRequestCompat0.mMinUpdateDistanceMeters;
            this.mMaxUpdateDelayMillis = locationRequestCompat0.mMaxUpdateDelayMillis;
        }

        public LocationRequestCompat build() {
            Preconditions.checkState(this.mIntervalMillis != 0x7FFFFFFFFFFFFFFFL || this.mMinUpdateIntervalMillis != -1L, "passive location requests must have an explicit minimum update interval");
            return new LocationRequestCompat(this.mIntervalMillis, this.mQuality, this.mDurationMillis, this.mMaxUpdates, Math.min(this.mMinUpdateIntervalMillis, this.mIntervalMillis), this.mMinUpdateDistanceMeters, this.mMaxUpdateDelayMillis);
        }

        public Builder clearMinUpdateIntervalMillis() {
            this.mMinUpdateIntervalMillis = -1L;
            return this;
        }

        public Builder setDurationMillis(long v) {
            this.mDurationMillis = Preconditions.checkArgumentInRange(v, 1L, 0x7FFFFFFFFFFFFFFFL, "durationMillis");
            return this;
        }

        public Builder setIntervalMillis(long v) {
            this.mIntervalMillis = Preconditions.checkArgumentInRange(v, 0L, 0x7FFFFFFFFFFFFFFFL, "intervalMillis");
            return this;
        }

        public Builder setMaxUpdateDelayMillis(long v) {
            this.mMaxUpdateDelayMillis = Preconditions.checkArgumentInRange(v, 0L, 0x7FFFFFFFFFFFFFFFL, "maxUpdateDelayMillis");
            return this;
        }

        public Builder setMaxUpdates(int v) {
            this.mMaxUpdates = Preconditions.checkArgumentInRange(v, 1, 0x7FFFFFFF, "maxUpdates");
            return this;
        }

        public Builder setMinUpdateDistanceMeters(float f) {
            this.mMinUpdateDistanceMeters = Preconditions.checkArgumentInRange(f, 0.0f, 3.402823E+38f, "minUpdateDistanceMeters");
            return this;
        }

        public Builder setMinUpdateIntervalMillis(long v) {
            this.mMinUpdateIntervalMillis = Preconditions.checkArgumentInRange(v, 0L, 0x7FFFFFFFFFFFFFFFL, "minUpdateIntervalMillis");
            return this;
        }

        public Builder setQuality(int v) {
            Preconditions.checkArgument(v == 100 || v == 102 || v == 104, "quality must be a defined QUALITY constant, not %d", new Object[]{v});
            this.mQuality = v;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Quality {
    }

    private static final long IMPLICIT_MIN_UPDATE_INTERVAL = -1L;
    public static final long PASSIVE_INTERVAL = 0x7FFFFFFFFFFFFFFFL;
    public static final int QUALITY_BALANCED_POWER_ACCURACY = 102;
    public static final int QUALITY_HIGH_ACCURACY = 100;
    public static final int QUALITY_LOW_POWER = 104;
    final long mDurationMillis;
    final long mIntervalMillis;
    final long mMaxUpdateDelayMillis;
    final int mMaxUpdates;
    final float mMinUpdateDistanceMeters;
    final long mMinUpdateIntervalMillis;
    final int mQuality;

    LocationRequestCompat(long v, int v1, long v2, int v3, long v4, float f, long v5) {
        this.mIntervalMillis = v;
        this.mQuality = v1;
        this.mMinUpdateIntervalMillis = v4;
        this.mDurationMillis = v2;
        this.mMaxUpdates = v3;
        this.mMinUpdateDistanceMeters = f;
        this.mMaxUpdateDelayMillis = v5;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof LocationRequestCompat ? this.mQuality == ((LocationRequestCompat)object0).mQuality && this.mIntervalMillis == ((LocationRequestCompat)object0).mIntervalMillis && this.mMinUpdateIntervalMillis == ((LocationRequestCompat)object0).mMinUpdateIntervalMillis && this.mDurationMillis == ((LocationRequestCompat)object0).mDurationMillis && this.mMaxUpdates == ((LocationRequestCompat)object0).mMaxUpdates && Float.compare(((LocationRequestCompat)object0).mMinUpdateDistanceMeters, this.mMinUpdateDistanceMeters) == 0 && this.mMaxUpdateDelayMillis == ((LocationRequestCompat)object0).mMaxUpdateDelayMillis : false;
    }

    public long getDurationMillis() {
        return this.mDurationMillis;
    }

    public long getIntervalMillis() {
        return this.mIntervalMillis;
    }

    public long getMaxUpdateDelayMillis() {
        return this.mMaxUpdateDelayMillis;
    }

    public int getMaxUpdates() {
        return this.mMaxUpdates;
    }

    public float getMinUpdateDistanceMeters() {
        return this.mMinUpdateDistanceMeters;
    }

    public long getMinUpdateIntervalMillis() {
        return this.mMinUpdateIntervalMillis == -1L ? this.mIntervalMillis : this.mMinUpdateIntervalMillis;
    }

    public int getQuality() {
        return this.mQuality;
    }

    @Override
    public int hashCode() {
        return (this.mQuality * 0x1F + ((int)(this.mIntervalMillis ^ this.mIntervalMillis >>> 0x20))) * 0x1F + ((int)(this.mMinUpdateIntervalMillis ^ this.mMinUpdateIntervalMillis >>> 0x20));
    }

    public LocationRequest toLocationRequest() {
        return Api31Impl.toLocationRequest(this);
    }

    public LocationRequest toLocationRequest(String s) {
        return Build.VERSION.SDK_INT < 0x1F ? HalfKt..ExternalSyntheticApiModelOutline0.m(Api19Impl.toLocationRequest(this, s)) : this.toLocationRequest();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("Request[");
        if(this.mIntervalMillis == 0x7FFFFFFFFFFFFFFFL) {
            stringBuilder0.append("PASSIVE");
        }
        else {
            stringBuilder0.append("@");
            TimeUtils.formatDuration(this.mIntervalMillis, stringBuilder0);
            switch(this.mQuality) {
                case 100: {
                    stringBuilder0.append(" HIGH_ACCURACY");
                    break;
                }
                case 102: {
                    stringBuilder0.append(" BALANCED");
                    break;
                }
                case 104: {
                    stringBuilder0.append(" LOW_POWER");
                }
            }
        }
        if(this.mDurationMillis != 0x7FFFFFFFFFFFFFFFL) {
            stringBuilder0.append(", duration=");
            TimeUtils.formatDuration(this.mDurationMillis, stringBuilder0);
        }
        if(this.mMaxUpdates != 0x7FFFFFFF) {
            stringBuilder0.append(", maxUpdates=");
            stringBuilder0.append(this.mMaxUpdates);
        }
        if(this.mMinUpdateIntervalMillis != -1L && this.mMinUpdateIntervalMillis < this.mIntervalMillis) {
            stringBuilder0.append(", minUpdateInterval=");
            TimeUtils.formatDuration(this.mMinUpdateIntervalMillis, stringBuilder0);
        }
        if(((double)this.mMinUpdateDistanceMeters) > 0.0) {
            stringBuilder0.append(", minUpdateDistance=");
            stringBuilder0.append(this.mMinUpdateDistanceMeters);
        }
        if(this.mMaxUpdateDelayMillis / 2L > this.mIntervalMillis) {
            stringBuilder0.append(", maxUpdateDelay=");
            TimeUtils.formatDuration(this.mMaxUpdateDelayMillis, stringBuilder0);
        }
        stringBuilder0.append(']');
        return stringBuilder0.toString();
    }
}

