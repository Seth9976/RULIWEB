package androidx.core.location;

import android.location.Location;
import android.os.Build.VERSION;
import android.os.Bundle;
import androidx.annotation.ReplaceWith;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public final class LocationCompat {
    static class Api26Impl {
        static float getBearingAccuracyDegrees(Location location0) {
            return location0.getBearingAccuracyDegrees();
        }

        static float getSpeedAccuracyMetersPerSecond(Location location0) {
            return location0.getSpeedAccuracyMetersPerSecond();
        }

        static float getVerticalAccuracyMeters(Location location0) {
            return location0.getVerticalAccuracyMeters();
        }

        static boolean hasBearingAccuracy(Location location0) {
            return location0.hasBearingAccuracy();
        }

        static boolean hasSpeedAccuracy(Location location0) {
            return location0.hasSpeedAccuracy();
        }

        static boolean hasVerticalAccuracy(Location location0) {
            return location0.hasVerticalAccuracy();
        }

        static void removeBearingAccuracy(Location location0) {
            try {
                int v = LocationCompat.getFieldsMaskField().getByte(location0);
                int v1 = LocationCompat.getHasBearingAccuracyMask();
                LocationCompat.getFieldsMaskField().setByte(location0, ((byte)(v & ~v1)));
            }
            catch(NoSuchFieldException noSuchFieldException0) {
                NoSuchFieldError noSuchFieldError0 = new NoSuchFieldError();
                noSuchFieldError0.initCause(noSuchFieldException0);
                throw noSuchFieldError0;
            }
            catch(IllegalAccessException illegalAccessException0) {
                IllegalAccessError illegalAccessError0 = new IllegalAccessError();
                illegalAccessError0.initCause(illegalAccessException0);
                throw illegalAccessError0;
            }
        }

        static void removeSpeedAccuracy(Location location0) {
            try {
                int v = LocationCompat.getFieldsMaskField().getByte(location0);
                int v1 = LocationCompat.getHasSpeedAccuracyMask();
                LocationCompat.getFieldsMaskField().setByte(location0, ((byte)(v & ~v1)));
            }
            catch(NoSuchFieldException noSuchFieldException0) {
                NoSuchFieldError noSuchFieldError0 = new NoSuchFieldError();
                noSuchFieldError0.initCause(noSuchFieldException0);
                throw noSuchFieldError0;
            }
            catch(IllegalAccessException illegalAccessException0) {
                IllegalAccessError illegalAccessError0 = new IllegalAccessError();
                illegalAccessError0.initCause(illegalAccessException0);
                throw illegalAccessError0;
            }
        }

        static void removeVerticalAccuracy(Location location0) {
            try {
                int v = LocationCompat.getFieldsMaskField().getByte(location0);
                int v1 = LocationCompat.getHasVerticalAccuracyMask();
                LocationCompat.getFieldsMaskField().setByte(location0, ((byte)(v & ~v1)));
            }
            catch(NoSuchFieldException | IllegalAccessException exception0) {
                IllegalAccessError illegalAccessError0 = new IllegalAccessError();
                illegalAccessError0.initCause(exception0);
                throw illegalAccessError0;
            }
        }

        static void setBearingAccuracyDegrees(Location location0, float f) {
            location0.setBearingAccuracyDegrees(f);
        }

        static void setSpeedAccuracyMetersPerSecond(Location location0, float f) {
            location0.setSpeedAccuracyMetersPerSecond(f);
        }

        static void setVerticalAccuracyMeters(Location location0, float f) {
            location0.setVerticalAccuracyMeters(f);
        }
    }

    static class Api28Impl {
        static void removeBearingAccuracy(Location location0) {
            if(location0.hasBearingAccuracy()) {
                String s = location0.getProvider();
                long v = location0.getTime();
                long v1 = location0.getElapsedRealtimeNanos();
                double f = location0.getLatitude();
                double f1 = location0.getLongitude();
                boolean z = location0.hasAltitude();
                double f2 = location0.getAltitude();
                boolean z1 = location0.hasSpeed();
                float f3 = location0.getSpeed();
                boolean z2 = location0.hasBearing();
                float f4 = location0.getBearing();
                boolean z3 = location0.hasAccuracy();
                float f5 = location0.getAccuracy();
                boolean z4 = location0.hasVerticalAccuracy();
                float f6 = location0.getVerticalAccuracyMeters();
                boolean z5 = location0.hasSpeedAccuracy();
                float f7 = location0.getSpeedAccuracyMetersPerSecond();
                Bundle bundle0 = location0.getExtras();
                location0.reset();
                location0.setProvider(s);
                location0.setTime(v);
                location0.setElapsedRealtimeNanos(v1);
                location0.setLatitude(f);
                location0.setLongitude(f1);
                if(z) {
                    location0.setAltitude(f2);
                }
                if(z1) {
                    location0.setSpeed(f3);
                }
                if(z2) {
                    location0.setBearing(f4);
                }
                if(z3) {
                    location0.setAccuracy(f5);
                }
                if(z4) {
                    location0.setVerticalAccuracyMeters(f6);
                }
                if(z5) {
                    location0.setBearingAccuracyDegrees(f7);
                }
                if(bundle0 != null) {
                    location0.setExtras(bundle0);
                }
            }
        }

        static void removeSpeedAccuracy(Location location0) {
            if(location0.hasSpeedAccuracy()) {
                String s = location0.getProvider();
                long v = location0.getTime();
                long v1 = location0.getElapsedRealtimeNanos();
                double f = location0.getLatitude();
                double f1 = location0.getLongitude();
                boolean z = location0.hasAltitude();
                double f2 = location0.getAltitude();
                boolean z1 = location0.hasSpeed();
                float f3 = location0.getSpeed();
                boolean z2 = location0.hasBearing();
                float f4 = location0.getBearing();
                boolean z3 = location0.hasAccuracy();
                float f5 = location0.getAccuracy();
                boolean z4 = location0.hasVerticalAccuracy();
                float f6 = location0.getVerticalAccuracyMeters();
                boolean z5 = location0.hasBearingAccuracy();
                float f7 = location0.getBearingAccuracyDegrees();
                Bundle bundle0 = location0.getExtras();
                location0.reset();
                location0.setProvider(s);
                location0.setTime(v);
                location0.setElapsedRealtimeNanos(v1);
                location0.setLatitude(f);
                location0.setLongitude(f1);
                if(z) {
                    location0.setAltitude(f2);
                }
                if(z1) {
                    location0.setSpeed(f3);
                }
                if(z2) {
                    location0.setBearing(f4);
                }
                if(z3) {
                    location0.setAccuracy(f5);
                }
                if(z4) {
                    location0.setVerticalAccuracyMeters(f6);
                }
                if(z5) {
                    location0.setBearingAccuracyDegrees(f7);
                }
                if(bundle0 != null) {
                    location0.setExtras(bundle0);
                }
            }
        }

        static void removeVerticalAccuracy(Location location0) {
            if(location0.hasVerticalAccuracy()) {
                String s = location0.getProvider();
                long v = location0.getTime();
                long v1 = location0.getElapsedRealtimeNanos();
                double f = location0.getLatitude();
                double f1 = location0.getLongitude();
                boolean z = location0.hasAltitude();
                double f2 = location0.getAltitude();
                boolean z1 = location0.hasSpeed();
                float f3 = location0.getSpeed();
                boolean z2 = location0.hasBearing();
                float f4 = location0.getBearing();
                boolean z3 = location0.hasAccuracy();
                float f5 = location0.getAccuracy();
                boolean z4 = location0.hasSpeedAccuracy();
                float f6 = location0.getSpeedAccuracyMetersPerSecond();
                boolean z5 = location0.hasBearingAccuracy();
                float f7 = location0.getBearingAccuracyDegrees();
                Bundle bundle0 = location0.getExtras();
                location0.reset();
                location0.setProvider(s);
                location0.setTime(v);
                location0.setElapsedRealtimeNanos(v1);
                location0.setLatitude(f);
                location0.setLongitude(f1);
                if(z) {
                    location0.setAltitude(f2);
                }
                if(z1) {
                    location0.setSpeed(f3);
                }
                if(z2) {
                    location0.setBearing(f4);
                }
                if(z3) {
                    location0.setAccuracy(f5);
                }
                if(z4) {
                    location0.setSpeedAccuracyMetersPerSecond(f6);
                }
                if(z5) {
                    location0.setBearingAccuracyDegrees(f7);
                }
                if(bundle0 != null) {
                    location0.setExtras(bundle0);
                }
            }
        }
    }

    static class Api29Impl {
        static void removeBearingAccuracy(Location location0) {
            if(!location0.hasBearingAccuracy()) {
                return;
            }
            double f = location0.getElapsedRealtimeUncertaintyNanos();
            Api28Impl.removeBearingAccuracy(location0);
            location0.setElapsedRealtimeUncertaintyNanos(f);
        }

        static void removeSpeedAccuracy(Location location0) {
            if(!location0.hasSpeedAccuracy()) {
                return;
            }
            double f = location0.getElapsedRealtimeUncertaintyNanos();
            Api28Impl.removeSpeedAccuracy(location0);
            location0.setElapsedRealtimeUncertaintyNanos(f);
        }

        static void removeVerticalAccuracy(Location location0) {
            if(!location0.hasVerticalAccuracy()) {
                return;
            }
            double f = location0.getElapsedRealtimeUncertaintyNanos();
            Api28Impl.removeVerticalAccuracy(location0);
            location0.setElapsedRealtimeUncertaintyNanos(f);
        }
    }

    static class Api31Impl {
        static boolean isMock(Location location0) {
            return location0.isMock();
        }
    }

    static class Api33Impl {
        static void removeBearingAccuracy(Location location0) {
            location0.removeBearingAccuracy();
        }

        static void removeSpeedAccuracy(Location location0) {
            location0.removeSpeedAccuracy();
        }

        static void removeVerticalAccuracy(Location location0) {
            location0.removeVerticalAccuracy();
        }
    }

    static class Api34Impl {
        static float getMslAltitudeAccuracyMeters(Location location0) {
            return location0.getMslAltitudeAccuracyMeters();
        }

        static double getMslAltitudeMeters(Location location0) {
            return location0.getMslAltitudeMeters();
        }

        static boolean hasMslAltitude(Location location0) {
            return location0.hasMslAltitude();
        }

        static boolean hasMslAltitudeAccuracy(Location location0) {
            return location0.hasMslAltitudeAccuracy();
        }

        static void removeMslAltitude(Location location0) {
            location0.removeMslAltitude();
        }

        static void removeMslAltitudeAccuracy(Location location0) {
            location0.removeMslAltitudeAccuracy();
        }

        static void setMslAltitudeAccuracyMeters(Location location0, float f) {
            location0.setMslAltitudeAccuracyMeters(f);
        }

        static void setMslAltitudeMeters(Location location0, double f) {
            location0.setMslAltitudeMeters(f);
        }
    }

    public static final String EXTRA_BEARING_ACCURACY = "bearingAccuracy";
    public static final String EXTRA_IS_MOCK = "mockLocation";
    public static final String EXTRA_MSL_ALTITUDE = "androidx.core.location.extra.MSL_ALTITUDE";
    public static final String EXTRA_MSL_ALTITUDE_ACCURACY = "androidx.core.location.extra.MSL_ALTITUDE_ACCURACY";
    public static final String EXTRA_SPEED_ACCURACY = "speedAccuracy";
    public static final String EXTRA_VERTICAL_ACCURACY = "verticalAccuracy";
    private static Field sFieldsMaskField;
    private static Integer sHasBearingAccuracyMask;
    private static Integer sHasSpeedAccuracyMask;
    private static Integer sHasVerticalAccuracyMask;
    private static Method sSetIsFromMockProviderMethod;

    private static boolean containsExtra(Location location0, String s) {
        Bundle bundle0 = location0.getExtras();
        return bundle0 != null && bundle0.containsKey(s);
    }

    public static float getBearingAccuracyDegrees(Location location0) {
        if(Build.VERSION.SDK_INT >= 26) {
            return Api26Impl.getBearingAccuracyDegrees(location0);
        }
        Bundle bundle0 = location0.getExtras();
        return bundle0 == null ? 0.0f : bundle0.getFloat("bearingAccuracy", 0.0f);
    }

    public static long getElapsedRealtimeMillis(Location location0) {
        return TimeUnit.NANOSECONDS.toMillis(location0.getElapsedRealtimeNanos());
    }

    @ReplaceWith(expression = "location.getElapsedRealtimeNanos()")
    @Deprecated
    public static long getElapsedRealtimeNanos(Location location0) {
        return location0.getElapsedRealtimeNanos();
    }

    static Field getFieldsMaskField() throws NoSuchFieldException {
        if(LocationCompat.sFieldsMaskField == null) {
            Field field0 = Location.class.getDeclaredField("mFieldsMask");
            LocationCompat.sFieldsMaskField = field0;
            field0.setAccessible(true);
        }
        return LocationCompat.sFieldsMaskField;
    }

    static int getHasBearingAccuracyMask() throws NoSuchFieldException, IllegalAccessException {
        if(LocationCompat.sHasBearingAccuracyMask == null) {
            Field field0 = Location.class.getDeclaredField("HAS_BEARING_ACCURACY_MASK");
            field0.setAccessible(true);
            LocationCompat.sHasBearingAccuracyMask = field0.getInt(null);
        }
        return (int)LocationCompat.sHasBearingAccuracyMask;
    }

    static int getHasSpeedAccuracyMask() throws NoSuchFieldException, IllegalAccessException {
        if(LocationCompat.sHasSpeedAccuracyMask == null) {
            Field field0 = Location.class.getDeclaredField("HAS_SPEED_ACCURACY_MASK");
            field0.setAccessible(true);
            LocationCompat.sHasSpeedAccuracyMask = field0.getInt(null);
        }
        return (int)LocationCompat.sHasSpeedAccuracyMask;
    }

    static int getHasVerticalAccuracyMask() throws NoSuchFieldException, IllegalAccessException {
        if(LocationCompat.sHasVerticalAccuracyMask == null) {
            Field field0 = Location.class.getDeclaredField("HAS_VERTICAL_ACCURACY_MASK");
            field0.setAccessible(true);
            LocationCompat.sHasVerticalAccuracyMask = field0.getInt(null);
        }
        return (int)LocationCompat.sHasVerticalAccuracyMask;
    }

    public static float getMslAltitudeAccuracyMeters(Location location0) {
        return Build.VERSION.SDK_INT < 34 ? LocationCompat.getOrCreateExtras(location0).getFloat("androidx.core.location.extra.MSL_ALTITUDE_ACCURACY") : Api34Impl.getMslAltitudeAccuracyMeters(location0);
    }

    public static double getMslAltitudeMeters(Location location0) {
        return Build.VERSION.SDK_INT < 34 ? LocationCompat.getOrCreateExtras(location0).getDouble("androidx.core.location.extra.MSL_ALTITUDE") : Api34Impl.getMslAltitudeMeters(location0);
    }

    private static Bundle getOrCreateExtras(Location location0) {
        Bundle bundle0 = location0.getExtras();
        if(bundle0 == null) {
            location0.setExtras(new Bundle());
            return location0.getExtras();
        }
        return bundle0;
    }

    private static Method getSetIsFromMockProviderMethod() throws NoSuchMethodException {
        if(LocationCompat.sSetIsFromMockProviderMethod == null) {
            Method method0 = Location.class.getDeclaredMethod("setIsFromMockProvider", Boolean.TYPE);
            LocationCompat.sSetIsFromMockProviderMethod = method0;
            method0.setAccessible(true);
        }
        return LocationCompat.sSetIsFromMockProviderMethod;
    }

    public static float getSpeedAccuracyMetersPerSecond(Location location0) {
        if(Build.VERSION.SDK_INT >= 26) {
            return Api26Impl.getSpeedAccuracyMetersPerSecond(location0);
        }
        Bundle bundle0 = location0.getExtras();
        return bundle0 == null ? 0.0f : bundle0.getFloat("speedAccuracy", 0.0f);
    }

    public static float getVerticalAccuracyMeters(Location location0) {
        if(Build.VERSION.SDK_INT >= 26) {
            return Api26Impl.getVerticalAccuracyMeters(location0);
        }
        Bundle bundle0 = location0.getExtras();
        return bundle0 == null ? 0.0f : bundle0.getFloat("verticalAccuracy", 0.0f);
    }

    public static boolean hasBearingAccuracy(Location location0) {
        return Build.VERSION.SDK_INT < 26 ? LocationCompat.containsExtra(location0, "bearingAccuracy") : Api26Impl.hasBearingAccuracy(location0);
    }

    public static boolean hasMslAltitude(Location location0) {
        return Build.VERSION.SDK_INT < 34 ? LocationCompat.containsExtra(location0, "androidx.core.location.extra.MSL_ALTITUDE") : Api34Impl.hasMslAltitude(location0);
    }

    public static boolean hasMslAltitudeAccuracy(Location location0) {
        return Build.VERSION.SDK_INT < 34 ? LocationCompat.containsExtra(location0, "androidx.core.location.extra.MSL_ALTITUDE_ACCURACY") : Api34Impl.hasMslAltitudeAccuracy(location0);
    }

    public static boolean hasSpeedAccuracy(Location location0) {
        return Build.VERSION.SDK_INT < 26 ? LocationCompat.containsExtra(location0, "speedAccuracy") : Api26Impl.hasSpeedAccuracy(location0);
    }

    public static boolean hasVerticalAccuracy(Location location0) {
        return Build.VERSION.SDK_INT < 26 ? LocationCompat.containsExtra(location0, "verticalAccuracy") : Api26Impl.hasVerticalAccuracy(location0);
    }

    public static boolean isMock(Location location0) {
        return Build.VERSION.SDK_INT < 0x1F ? location0.isFromMockProvider() : Api31Impl.isMock(location0);
    }

    public static void removeBearingAccuracy(Location location0) {
        if(Build.VERSION.SDK_INT >= 33) {
            Api33Impl.removeBearingAccuracy(location0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 29) {
            Api29Impl.removeBearingAccuracy(location0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 28) {
            Api28Impl.removeBearingAccuracy(location0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.removeBearingAccuracy(location0);
            return;
        }
        LocationCompat.removeExtra(location0, "bearingAccuracy");
    }

    private static void removeExtra(Location location0, String s) {
        Bundle bundle0 = location0.getExtras();
        if(bundle0 != null) {
            bundle0.remove(s);
            if(bundle0.isEmpty()) {
                location0.setExtras(null);
            }
        }
    }

    public static void removeMslAltitude(Location location0) {
        if(Build.VERSION.SDK_INT >= 34) {
            Api34Impl.removeMslAltitude(location0);
            return;
        }
        LocationCompat.removeExtra(location0, "androidx.core.location.extra.MSL_ALTITUDE");
    }

    public static void removeMslAltitudeAccuracy(Location location0) {
        if(Build.VERSION.SDK_INT >= 34) {
            Api34Impl.removeMslAltitudeAccuracy(location0);
            return;
        }
        LocationCompat.removeExtra(location0, "androidx.core.location.extra.MSL_ALTITUDE_ACCURACY");
    }

    public static void removeSpeedAccuracy(Location location0) {
        if(Build.VERSION.SDK_INT >= 33) {
            Api33Impl.removeSpeedAccuracy(location0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 29) {
            Api29Impl.removeSpeedAccuracy(location0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 28) {
            Api28Impl.removeSpeedAccuracy(location0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.removeSpeedAccuracy(location0);
            return;
        }
        LocationCompat.removeExtra(location0, "speedAccuracy");
    }

    public static void removeVerticalAccuracy(Location location0) {
        if(Build.VERSION.SDK_INT >= 33) {
            Api33Impl.removeVerticalAccuracy(location0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 29) {
            Api29Impl.removeVerticalAccuracy(location0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 28) {
            Api28Impl.removeVerticalAccuracy(location0);
            return;
        }
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.removeVerticalAccuracy(location0);
            return;
        }
        LocationCompat.removeExtra(location0, "verticalAccuracy");
    }

    public static void setBearingAccuracyDegrees(Location location0, float f) {
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.setBearingAccuracyDegrees(location0, f);
            return;
        }
        LocationCompat.getOrCreateExtras(location0).putFloat("bearingAccuracy", f);
    }

    public static void setMock(Location location0, boolean z) {
        try {
            LocationCompat.getSetIsFromMockProviderMethod().invoke(location0, Boolean.valueOf(z));
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            NoSuchMethodError noSuchMethodError0 = new NoSuchMethodError();
            noSuchMethodError0.initCause(noSuchMethodException0);
            throw noSuchMethodError0;
        }
        catch(IllegalAccessException illegalAccessException0) {
            IllegalAccessError illegalAccessError0 = new IllegalAccessError();
            illegalAccessError0.initCause(illegalAccessException0);
            throw illegalAccessError0;
        }
        catch(InvocationTargetException invocationTargetException0) {
            throw new RuntimeException(invocationTargetException0);
        }
    }

    public static void setMslAltitudeAccuracyMeters(Location location0, float f) {
        if(Build.VERSION.SDK_INT >= 34) {
            Api34Impl.setMslAltitudeAccuracyMeters(location0, f);
            return;
        }
        LocationCompat.getOrCreateExtras(location0).putFloat("androidx.core.location.extra.MSL_ALTITUDE_ACCURACY", f);
    }

    public static void setMslAltitudeMeters(Location location0, double f) {
        if(Build.VERSION.SDK_INT >= 34) {
            Api34Impl.setMslAltitudeMeters(location0, f);
            return;
        }
        LocationCompat.getOrCreateExtras(location0).putDouble("androidx.core.location.extra.MSL_ALTITUDE", f);
    }

    public static void setSpeedAccuracyMetersPerSecond(Location location0, float f) {
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.setSpeedAccuracyMetersPerSecond(location0, f);
            return;
        }
        LocationCompat.getOrCreateExtras(location0).putFloat("speedAccuracy", f);
    }

    public static void setVerticalAccuracyMeters(Location location0, float f) {
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.setVerticalAccuracyMeters(location0, f);
            return;
        }
        LocationCompat.getOrCreateExtras(location0).putFloat("verticalAccuracy", f);
    }
}

