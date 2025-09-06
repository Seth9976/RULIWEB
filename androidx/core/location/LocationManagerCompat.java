package androidx.core.location;

import android.location.GnssMeasurementsEvent.Callback;
import android.location.GnssMeasurementsEvent;
import android.location.GnssStatus.Callback;
import android.location.GnssStatus;
import android.location.GpsStatus.Listener;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.collection.SimpleArrayMap;
import androidx.core.os.ExecutorCompat;
import androidx.core.util.Consumer;
import androidx.core.util.HalfKt..ExternalSyntheticApiModelOutline0;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class LocationManagerCompat {
    static class Api19Impl {
        private static Class sLocationRequestClass;
        private static Method sRequestLocationUpdatesLooperMethod;

        static boolean tryRequestLocationUpdates(LocationManager locationManager0, String s, LocationRequestCompat locationRequestCompat0, LocationListenerCompat locationListenerCompat0, Looper looper0) {
            try {
                if(Api19Impl.sLocationRequestClass == null) {
                    Api19Impl.sLocationRequestClass = LocationRequest.class;
                }
                if(Api19Impl.sRequestLocationUpdatesLooperMethod == null) {
                    Method method0 = LocationManager.class.getDeclaredMethod("requestLocationUpdates", Api19Impl.sLocationRequestClass, LocationListener.class, Looper.class);
                    Api19Impl.sRequestLocationUpdatesLooperMethod = method0;
                    method0.setAccessible(true);
                }
                LocationRequest locationRequest0 = locationRequestCompat0.toLocationRequest(s);
                if(locationRequest0 != null) {
                    Api19Impl.sRequestLocationUpdatesLooperMethod.invoke(locationManager0, locationRequest0, locationListenerCompat0, looper0);
                    return true;
                }
            }
            catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException | UnsupportedOperationException unused_ex) {
            }
            return false;
        }

        static boolean tryRequestLocationUpdates(LocationManager locationManager0, String s, LocationRequestCompat locationRequestCompat0, LocationListenerTransport locationManagerCompat$LocationListenerTransport0) {
            try {
                if(Api19Impl.sLocationRequestClass == null) {
                    Api19Impl.sLocationRequestClass = LocationRequest.class;
                }
                if(Api19Impl.sRequestLocationUpdatesLooperMethod == null) {
                    Method method0 = LocationManager.class.getDeclaredMethod("requestLocationUpdates", Api19Impl.sLocationRequestClass, LocationListener.class, Looper.class);
                    Api19Impl.sRequestLocationUpdatesLooperMethod = method0;
                    method0.setAccessible(true);
                }
                LocationRequest locationRequest0 = locationRequestCompat0.toLocationRequest(s);
                if(locationRequest0 != null) {
                    synchronized(LocationManagerCompat.sLocationListeners) {
                        Api19Impl.sRequestLocationUpdatesLooperMethod.invoke(locationManager0, locationRequest0, locationManagerCompat$LocationListenerTransport0, Looper.getMainLooper());
                        LocationManagerCompat.registerLocationListenerTransport(locationManager0, locationManagerCompat$LocationListenerTransport0);
                        return true;
                    }
                }
            }
            catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException | UnsupportedOperationException unused_ex) {
            }
            return false;
        }
    }

    static class Api24Impl {
        static boolean registerGnssMeasurementsCallback(LocationManager locationManager0, GnssMeasurementsEvent.Callback gnssMeasurementsEvent$Callback0) {
            return locationManager0.registerGnssMeasurementsCallback(gnssMeasurementsEvent$Callback0);
        }

        static boolean registerGnssMeasurementsCallback(LocationManager locationManager0, GnssMeasurementsEvent.Callback gnssMeasurementsEvent$Callback0, Handler handler0) {
            return locationManager0.registerGnssMeasurementsCallback(gnssMeasurementsEvent$Callback0, handler0);
        }

        static boolean registerGnssStatusCallback(LocationManager locationManager0, Handler handler0, Executor executor0, Callback gnssStatusCompat$Callback0) {
            Preconditions.checkArgument(handler0 != null);
            synchronized(GnssListenersHolder.sGnssStatusListeners) {
                PreRGnssStatusTransport locationManagerCompat$PreRGnssStatusTransport0 = (PreRGnssStatusTransport)GnssListenersHolder.sGnssStatusListeners.get(gnssStatusCompat$Callback0);
                if(locationManagerCompat$PreRGnssStatusTransport0 == null) {
                    locationManagerCompat$PreRGnssStatusTransport0 = new PreRGnssStatusTransport(gnssStatusCompat$Callback0);
                }
                else {
                    locationManagerCompat$PreRGnssStatusTransport0.unregister();
                }
                locationManagerCompat$PreRGnssStatusTransport0.register(executor0);
                if(locationManager0.registerGnssStatusCallback(locationManagerCompat$PreRGnssStatusTransport0, handler0)) {
                    GnssListenersHolder.sGnssStatusListeners.put(gnssStatusCompat$Callback0, locationManagerCompat$PreRGnssStatusTransport0);
                    return true;
                }
                return false;
            }
        }

        static void unregisterGnssMeasurementsCallback(LocationManager locationManager0, GnssMeasurementsEvent.Callback gnssMeasurementsEvent$Callback0) {
            locationManager0.unregisterGnssMeasurementsCallback(gnssMeasurementsEvent$Callback0);
        }

        static void unregisterGnssStatusCallback(LocationManager locationManager0, Object object0) {
            if(object0 instanceof PreRGnssStatusTransport) {
                ((PreRGnssStatusTransport)object0).unregister();
            }
            locationManager0.unregisterGnssStatusCallback(((GnssStatus.Callback)object0));
        }
    }

    static class Api28Impl {
        static String getGnssHardwareModelName(LocationManager locationManager0) {
            return locationManager0.getGnssHardwareModelName();
        }

        static int getGnssYearOfHardware(LocationManager locationManager0) {
            return locationManager0.getGnssYearOfHardware();
        }

        static boolean isLocationEnabled(LocationManager locationManager0) {
            return locationManager0.isLocationEnabled();
        }
    }

    static class Api30Impl {
        private static Class sLocationRequestClass;
        private static Method sRequestLocationUpdatesExecutorMethod;

        static void getCurrentLocation(LocationManager locationManager0, String s, CancellationSignal cancellationSignal0, Executor executor0, Consumer consumer0) {
            Objects.requireNonNull(consumer0);
            locationManager0.getCurrentLocation(s, cancellationSignal0, executor0, new LocationManagerCompat.Api30Impl..ExternalSyntheticLambda0(consumer0));
        }

        public static boolean registerGnssStatusCallback(LocationManager locationManager0, Handler handler0, Executor executor0, Callback gnssStatusCompat$Callback0) {
            synchronized(GnssListenersHolder.sGnssStatusListeners) {
                GnssStatusTransport locationManagerCompat$GnssStatusTransport0 = (GnssStatusTransport)GnssListenersHolder.sGnssStatusListeners.get(gnssStatusCompat$Callback0);
                if(locationManagerCompat$GnssStatusTransport0 == null) {
                    locationManagerCompat$GnssStatusTransport0 = new GnssStatusTransport(gnssStatusCompat$Callback0);
                }
                if(locationManager0.registerGnssStatusCallback(executor0, locationManagerCompat$GnssStatusTransport0)) {
                    GnssListenersHolder.sGnssStatusListeners.put(gnssStatusCompat$Callback0, locationManagerCompat$GnssStatusTransport0);
                    return true;
                }
                return false;
            }
        }

        public static boolean tryRequestLocationUpdates(LocationManager locationManager0, String s, LocationRequestCompat locationRequestCompat0, Executor executor0, LocationListenerCompat locationListenerCompat0) {
            if(Build.VERSION.SDK_INT >= 30) {
                try {
                    if(Api30Impl.sLocationRequestClass == null) {
                        Api30Impl.sLocationRequestClass = LocationRequest.class;
                    }
                    if(Api30Impl.sRequestLocationUpdatesExecutorMethod == null) {
                        Method method0 = LocationManager.class.getDeclaredMethod("requestLocationUpdates", Api30Impl.sLocationRequestClass, Executor.class, LocationListener.class);
                        Api30Impl.sRequestLocationUpdatesExecutorMethod = method0;
                        method0.setAccessible(true);
                    }
                    LocationRequest locationRequest0 = locationRequestCompat0.toLocationRequest(s);
                    if(locationRequest0 != null) {
                        Api30Impl.sRequestLocationUpdatesExecutorMethod.invoke(locationManager0, locationRequest0, executor0, locationListenerCompat0);
                        return true;
                    }
                    return false;
                }
                catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException | UnsupportedOperationException unused_ex) {
                }
            }
            return false;
        }
    }

    static class Api31Impl {
        static boolean hasProvider(LocationManager locationManager0, String s) {
            return locationManager0.hasProvider(s);
        }

        static boolean registerGnssMeasurementsCallback(LocationManager locationManager0, Executor executor0, GnssMeasurementsEvent.Callback gnssMeasurementsEvent$Callback0) {
            return locationManager0.registerGnssMeasurementsCallback(executor0, gnssMeasurementsEvent$Callback0);
        }

        static void requestLocationUpdates(LocationManager locationManager0, String s, LocationRequest locationRequest0, Executor executor0, LocationListener locationListener0) {
            locationManager0.requestLocationUpdates(s, locationRequest0, executor0, locationListener0);
        }
    }

    static final class CancellableLocationListener implements LocationListener {
        private Consumer mConsumer;
        private final Executor mExecutor;
        private final LocationManager mLocationManager;
        private final Handler mTimeoutHandler;
        Runnable mTimeoutRunnable;
        private boolean mTriggered;

        CancellableLocationListener(LocationManager locationManager0, Executor executor0, Consumer consumer0) {
            this.mLocationManager = locationManager0;
            this.mExecutor = executor0;
            this.mTimeoutHandler = new Handler(Looper.getMainLooper());
            this.mConsumer = consumer0;
        }

        public void cancel() {
            synchronized(this) {
                if(this.mTriggered) {
                    return;
                }
                this.mTriggered = true;
            }
            this.cleanup();
        }

        private void cleanup() {
            this.mConsumer = null;
            this.mLocationManager.removeUpdates(this);
            Runnable runnable0 = this.mTimeoutRunnable;
            if(runnable0 != null) {
                this.mTimeoutHandler.removeCallbacks(runnable0);
                this.mTimeoutRunnable = null;
            }
        }

        // 检测为 Lambda 实现
        void lambda$startTimeout$0$androidx-core-location-LocationManagerCompat$CancellableLocationListener() [...]

        @Override  // android.location.LocationListener
        public void onLocationChanged(Location location0) {
            synchronized(this) {
                if(this.mTriggered) {
                    return;
                }
                this.mTriggered = true;
            }
            LocationManagerCompat.CancellableLocationListener..ExternalSyntheticLambda1 locationManagerCompat$CancellableLocationListener$$ExternalSyntheticLambda10 = new LocationManagerCompat.CancellableLocationListener..ExternalSyntheticLambda1(this.mConsumer, location0);
            this.mExecutor.execute(locationManagerCompat$CancellableLocationListener$$ExternalSyntheticLambda10);
            this.cleanup();
        }

        @Override  // android.location.LocationListener
        public void onProviderDisabled(String s) {
            this.onLocationChanged(null);
        }

        @Override  // android.location.LocationListener
        public void onProviderEnabled(String s) {
        }

        @Override  // android.location.LocationListener
        public void onStatusChanged(String s, int v, Bundle bundle0) {
        }

        public void startTimeout(long v) {
            synchronized(this) {
                if(this.mTriggered) {
                    return;
                }
                LocationManagerCompat.CancellableLocationListener..ExternalSyntheticLambda0 locationManagerCompat$CancellableLocationListener$$ExternalSyntheticLambda00 = () -> {
                    this.mTimeoutRunnable = null;
                    this.onLocationChanged(null);
                };
                this.mTimeoutRunnable = locationManagerCompat$CancellableLocationListener$$ExternalSyntheticLambda00;
                this.mTimeoutHandler.postDelayed(locationManagerCompat$CancellableLocationListener$$ExternalSyntheticLambda00, v);
            }
        }
    }

    static class GnssListenersHolder {
        static final SimpleArrayMap sGnssMeasurementListeners;
        static final SimpleArrayMap sGnssStatusListeners;

        static {
            GnssListenersHolder.sGnssStatusListeners = new SimpleArrayMap();
            GnssListenersHolder.sGnssMeasurementListeners = new SimpleArrayMap();
        }
    }

    static class GnssMeasurementsTransport extends GnssMeasurementsEvent.Callback {
        final GnssMeasurementsEvent.Callback mCallback;
        volatile Executor mExecutor;

        GnssMeasurementsTransport(GnssMeasurementsEvent.Callback gnssMeasurementsEvent$Callback0, Executor executor0) {
            this.mCallback = gnssMeasurementsEvent$Callback0;
            this.mExecutor = executor0;
        }

        // 检测为 Lambda 实现
        void lambda$onGnssMeasurementsReceived$0$androidx-core-location-LocationManagerCompat$GnssMeasurementsTransport(Executor executor0, GnssMeasurementsEvent gnssMeasurementsEvent0) [...]

        // 检测为 Lambda 实现
        void lambda$onStatusChanged$1$androidx-core-location-LocationManagerCompat$GnssMeasurementsTransport(Executor executor0, int v) [...]

        @Override  // android.location.GnssMeasurementsEvent$Callback
        public void onGnssMeasurementsReceived(GnssMeasurementsEvent gnssMeasurementsEvent0) {
            Executor executor0 = this.mExecutor;
            if(executor0 == null) {
                return;
            }
            executor0.execute(() -> {
                if(this.mExecutor != executor0) {
                    return;
                }
                this.mCallback.onGnssMeasurementsReceived(gnssMeasurementsEvent0);
            });
        }

        @Override  // android.location.GnssMeasurementsEvent$Callback
        public void onStatusChanged(int v) {
            Executor executor0 = this.mExecutor;
            if(executor0 == null) {
                return;
            }
            executor0.execute(() -> {
                if(this.mExecutor != executor0) {
                    return;
                }
                this.mCallback.onStatusChanged(v);
            });
        }

        public void unregister() {
            this.mExecutor = null;
        }
    }

    static class GnssStatusTransport extends GnssStatus.Callback {
        final Callback mCallback;

        GnssStatusTransport(Callback gnssStatusCompat$Callback0) {
            Preconditions.checkArgument(gnssStatusCompat$Callback0 != null, "invalid null callback");
            this.mCallback = gnssStatusCompat$Callback0;
        }

        @Override  // android.location.GnssStatus$Callback
        public void onFirstFix(int v) {
        }

        @Override  // android.location.GnssStatus$Callback
        public void onSatelliteStatusChanged(GnssStatus gnssStatus0) {
            GnssStatusCompat.wrap(gnssStatus0);
        }

        @Override  // android.location.GnssStatus$Callback
        public void onStarted() {
        }

        @Override  // android.location.GnssStatus$Callback
        public void onStopped() {
        }
    }

    static class GpsStatusTransport implements GpsStatus.Listener {
        final Callback mCallback;
        volatile Executor mExecutor;
        private final LocationManager mLocationManager;

        GpsStatusTransport(LocationManager locationManager0, Callback gnssStatusCompat$Callback0) {
            Preconditions.checkArgument(gnssStatusCompat$Callback0 != null, "invalid null callback");
            this.mLocationManager = locationManager0;
            this.mCallback = gnssStatusCompat$Callback0;
        }

        void lambda$onGpsStatusChanged$0$androidx-core-location-LocationManagerCompat$GpsStatusTransport(Executor executor0) {
            if(this.mExecutor != executor0) {
            }
        }

        void lambda$onGpsStatusChanged$1$androidx-core-location-LocationManagerCompat$GpsStatusTransport(Executor executor0) {
            if(this.mExecutor != executor0) {
            }
        }

        void lambda$onGpsStatusChanged$2$androidx-core-location-LocationManagerCompat$GpsStatusTransport(Executor executor0, int v) {
            if(this.mExecutor != executor0) {
            }
        }

        void lambda$onGpsStatusChanged$3$androidx-core-location-LocationManagerCompat$GpsStatusTransport(Executor executor0, GnssStatusCompat gnssStatusCompat0) {
            if(this.mExecutor != executor0) {
            }
        }

        @Override  // android.location.GpsStatus$Listener
        public void onGpsStatusChanged(int v) {
            Executor executor0 = this.mExecutor;
            if(executor0 != null) {
                switch(v) {
                    case 1: {
                        executor0.execute(new LocationManagerCompat.GpsStatusTransport..ExternalSyntheticLambda0(this, executor0));
                        return;
                    }
                    case 2: {
                        executor0.execute(new LocationManagerCompat.GpsStatusTransport..ExternalSyntheticLambda1(this, executor0));
                        return;
                    }
                    case 3: {
                        GpsStatus gpsStatus0 = this.mLocationManager.getGpsStatus(null);
                        if(gpsStatus0 != null) {
                            executor0.execute(new LocationManagerCompat.GpsStatusTransport..ExternalSyntheticLambda2(this, executor0, gpsStatus0.getTimeToFirstFix()));
                            return;
                        }
                        break;
                    }
                    case 4: {
                        GpsStatus gpsStatus1 = this.mLocationManager.getGpsStatus(null);
                        if(gpsStatus1 != null) {
                            executor0.execute(new LocationManagerCompat.GpsStatusTransport..ExternalSyntheticLambda3(this, executor0, GnssStatusCompat.wrap(gpsStatus1)));
                            return;
                        }
                        break;
                    }
                }
            }
        }

        public void register(Executor executor0) {
            Preconditions.checkState(this.mExecutor == null);
            this.mExecutor = executor0;
        }

        public void unregister() {
            this.mExecutor = null;
        }
    }

    static final class InlineHandlerExecutor implements Executor {
        private final Handler mHandler;

        InlineHandlerExecutor(Handler handler0) {
            this.mHandler = (Handler)Preconditions.checkNotNull(handler0);
        }

        @Override
        public void execute(Runnable runnable0) {
            if(Looper.myLooper() == this.mHandler.getLooper()) {
                runnable0.run();
                return;
            }
            Runnable runnable1 = (Runnable)Preconditions.checkNotNull(runnable0);
            if(!this.mHandler.post(runnable1)) {
                throw new RejectedExecutionException(this.mHandler + " is shutting down");
            }
        }
    }

    static class LocationListenerKey {
        final LocationListenerCompat mListener;
        final String mProvider;

        LocationListenerKey(String s, LocationListenerCompat locationListenerCompat0) {
            this.mProvider = (String)ObjectsCompat.requireNonNull(s, "invalid null provider");
            this.mListener = (LocationListenerCompat)ObjectsCompat.requireNonNull(locationListenerCompat0, "invalid null listener");
        }

        // 去混淆评级： 低(30)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof LocationListenerKey ? this.mProvider.equals(((LocationListenerKey)object0).mProvider) && this.mListener.equals(((LocationListenerKey)object0).mListener) : false;
        }

        @Override
        public int hashCode() {
            return ObjectsCompat.hash(new Object[]{this.mProvider, this.mListener});
        }
    }

    static class LocationListenerTransport implements LocationListener {
        final Executor mExecutor;
        volatile LocationListenerKey mKey;

        LocationListenerTransport(LocationListenerKey locationManagerCompat$LocationListenerKey0, Executor executor0) {
            this.mKey = locationManagerCompat$LocationListenerKey0;
            this.mExecutor = executor0;
        }

        public LocationListenerKey getKey() {
            return (LocationListenerKey)ObjectsCompat.requireNonNull(this.mKey);
        }

        // 检测为 Lambda 实现
        void lambda$onFlushComplete$2$androidx-core-location-LocationManagerCompat$LocationListenerTransport(int v) [...]

        // 检测为 Lambda 实现
        void lambda$onLocationChanged$0$androidx-core-location-LocationManagerCompat$LocationListenerTransport(Location location0) [...]

        // 检测为 Lambda 实现
        void lambda$onLocationChanged$1$androidx-core-location-LocationManagerCompat$LocationListenerTransport(List list0) [...]

        // 检测为 Lambda 实现
        void lambda$onProviderDisabled$5$androidx-core-location-LocationManagerCompat$LocationListenerTransport(String s) [...]

        // 检测为 Lambda 实现
        void lambda$onProviderEnabled$4$androidx-core-location-LocationManagerCompat$LocationListenerTransport(String s) [...]

        // 检测为 Lambda 实现
        void lambda$onStatusChanged$3$androidx-core-location-LocationManagerCompat$LocationListenerTransport(String s, int v, Bundle bundle0) [...]

        @Override  // android.location.LocationListener
        public void onFlushComplete(int v) {
            if(this.mKey == null) {
                return;
            }
            LocationManagerCompat.LocationListenerTransport..ExternalSyntheticLambda4 locationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda40 = () -> {
                LocationListenerKey locationManagerCompat$LocationListenerKey0 = this.mKey;
                if(locationManagerCompat$LocationListenerKey0 == null) {
                    return;
                }
                locationManagerCompat$LocationListenerKey0.mListener.onFlushComplete(v);
            };
            this.mExecutor.execute(locationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda40);
        }

        @Override  // android.location.LocationListener
        public void onLocationChanged(Location location0) {
            if(this.mKey == null) {
                return;
            }
            LocationManagerCompat.LocationListenerTransport..ExternalSyntheticLambda3 locationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda30 = () -> {
                LocationListenerKey locationManagerCompat$LocationListenerKey0 = this.mKey;
                if(locationManagerCompat$LocationListenerKey0 == null) {
                    return;
                }
                locationManagerCompat$LocationListenerKey0.mListener.onLocationChanged(location0);
            };
            this.mExecutor.execute(locationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda30);
        }

        @Override  // android.location.LocationListener
        public void onLocationChanged(List list0) {
            if(this.mKey == null) {
                return;
            }
            LocationManagerCompat.LocationListenerTransport..ExternalSyntheticLambda2 locationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda20 = () -> {
                LocationListenerKey locationManagerCompat$LocationListenerKey0 = this.mKey;
                if(locationManagerCompat$LocationListenerKey0 == null) {
                    return;
                }
                locationManagerCompat$LocationListenerKey0.mListener.onLocationChanged(list0);
            };
            this.mExecutor.execute(locationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda20);
        }

        @Override  // android.location.LocationListener
        public void onProviderDisabled(String s) {
            if(this.mKey == null) {
                return;
            }
            LocationManagerCompat.LocationListenerTransport..ExternalSyntheticLambda1 locationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda10 = () -> {
                LocationListenerKey locationManagerCompat$LocationListenerKey0 = this.mKey;
                if(locationManagerCompat$LocationListenerKey0 == null) {
                    return;
                }
                locationManagerCompat$LocationListenerKey0.mListener.onProviderDisabled(s);
            };
            this.mExecutor.execute(locationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda10);
        }

        @Override  // android.location.LocationListener
        public void onProviderEnabled(String s) {
            if(this.mKey == null) {
                return;
            }
            LocationManagerCompat.LocationListenerTransport..ExternalSyntheticLambda0 locationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda00 = () -> {
                LocationListenerKey locationManagerCompat$LocationListenerKey0 = this.mKey;
                if(locationManagerCompat$LocationListenerKey0 == null) {
                    return;
                }
                locationManagerCompat$LocationListenerKey0.mListener.onProviderEnabled(s);
            };
            this.mExecutor.execute(locationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda00);
        }

        @Override  // android.location.LocationListener
        public void onStatusChanged(String s, int v, Bundle bundle0) {
            if(this.mKey == null) {
                return;
            }
            LocationManagerCompat.LocationListenerTransport..ExternalSyntheticLambda5 locationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda50 = () -> {
                LocationListenerKey locationManagerCompat$LocationListenerKey0 = this.mKey;
                if(locationManagerCompat$LocationListenerKey0 == null) {
                    return;
                }
                locationManagerCompat$LocationListenerKey0.mListener.onStatusChanged(s, v, bundle0);
            };
            this.mExecutor.execute(locationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda50);
        }

        public void unregister() {
            this.mKey = null;
        }
    }

    static class PreRGnssStatusTransport extends GnssStatus.Callback {
        final Callback mCallback;
        volatile Executor mExecutor;

        PreRGnssStatusTransport(Callback gnssStatusCompat$Callback0) {
            Preconditions.checkArgument(gnssStatusCompat$Callback0 != null, "invalid null callback");
            this.mCallback = gnssStatusCompat$Callback0;
        }

        void lambda$onFirstFix$2$androidx-core-location-LocationManagerCompat$PreRGnssStatusTransport(Executor executor0, int v) {
            if(this.mExecutor != executor0) {
            }
        }

        // 检测为 Lambda 实现
        void lambda$onSatelliteStatusChanged$3$androidx-core-location-LocationManagerCompat$PreRGnssStatusTransport(Executor executor0, GnssStatus gnssStatus0) [...]

        void lambda$onStarted$0$androidx-core-location-LocationManagerCompat$PreRGnssStatusTransport(Executor executor0) {
            if(this.mExecutor != executor0) {
            }
        }

        void lambda$onStopped$1$androidx-core-location-LocationManagerCompat$PreRGnssStatusTransport(Executor executor0) {
            if(this.mExecutor != executor0) {
            }
        }

        @Override  // android.location.GnssStatus$Callback
        public void onFirstFix(int v) {
            Executor executor0 = this.mExecutor;
            if(executor0 == null) {
                return;
            }
            executor0.execute(new LocationManagerCompat.PreRGnssStatusTransport..ExternalSyntheticLambda0(this, executor0, v));
        }

        @Override  // android.location.GnssStatus$Callback
        public void onSatelliteStatusChanged(GnssStatus gnssStatus0) {
            Executor executor0 = this.mExecutor;
            if(executor0 == null) {
                return;
            }
            executor0.execute(() -> {
                if(this.mExecutor != executor0) {
                    return;
                }
                GnssStatusCompat.wrap(gnssStatus0);
            });
        }

        @Override  // android.location.GnssStatus$Callback
        public void onStarted() {
            Executor executor0 = this.mExecutor;
            if(executor0 == null) {
                return;
            }
            executor0.execute(new LocationManagerCompat.PreRGnssStatusTransport..ExternalSyntheticLambda3(this, executor0));
        }

        @Override  // android.location.GnssStatus$Callback
        public void onStopped() {
            Executor executor0 = this.mExecutor;
            if(executor0 == null) {
                return;
            }
            executor0.execute(new LocationManagerCompat.PreRGnssStatusTransport..ExternalSyntheticLambda2(this, executor0));
        }

        public void register(Executor executor0) {
            boolean z = true;
            Preconditions.checkArgument(executor0 != null, "invalid null executor");
            if(this.mExecutor != null) {
                z = false;
            }
            Preconditions.checkState(z);
            this.mExecutor = executor0;
        }

        public void unregister() {
            this.mExecutor = null;
        }
    }

    private static final long GET_CURRENT_LOCATION_TIMEOUT_MS = 30000L;
    private static final long MAX_CURRENT_LOCATION_AGE_MS = 10000L;
    private static final long PRE_N_LOOPER_TIMEOUT_S = 5L;
    private static Field sContextField;
    private static Method sGnssRequestBuilderBuildMethod;
    private static Class sGnssRequestBuilderClass;
    static final WeakHashMap sLocationListeners;
    private static Method sRegisterGnssMeasurementsCallbackMethod;

    static {
        LocationManagerCompat.sLocationListeners = new WeakHashMap();
    }

    public static void getCurrentLocation(LocationManager locationManager0, String s, CancellationSignal cancellationSignal0, Executor executor0, Consumer consumer0) {
        if(Build.VERSION.SDK_INT >= 30) {
            Api30Impl.getCurrentLocation(locationManager0, s, cancellationSignal0, executor0, consumer0);
            return;
        }
        if(cancellationSignal0 != null) {
            cancellationSignal0.throwIfCanceled();
        }
        Location location0 = locationManager0.getLastKnownLocation(s);
        if(location0 != null && SystemClock.elapsedRealtime() - LocationCompat.getElapsedRealtimeMillis(location0) < 10000L) {
            executor0.execute(new LocationManagerCompat..ExternalSyntheticLambda2(consumer0, location0));
            return;
        }
        CancellableLocationListener locationManagerCompat$CancellableLocationListener0 = new CancellableLocationListener(locationManager0, executor0, consumer0);
        locationManager0.requestLocationUpdates(s, 0L, 0.0f, locationManagerCompat$CancellableLocationListener0, Looper.getMainLooper());
        if(cancellationSignal0 != null) {
            cancellationSignal0.setOnCancelListener(new LocationManagerCompat..ExternalSyntheticLambda3(locationManagerCompat$CancellableLocationListener0));
        }
        locationManagerCompat$CancellableLocationListener0.startTimeout(30000L);
    }

    @Deprecated
    public static void getCurrentLocation(LocationManager locationManager0, String s, androidx.core.os.CancellationSignal cancellationSignal0, Executor executor0, Consumer consumer0) {
        LocationManagerCompat.getCurrentLocation(locationManager0, s, (cancellationSignal0 == null ? null : ((CancellationSignal)cancellationSignal0.getCancellationSignalObject())), executor0, consumer0);
    }

    public static String getGnssHardwareModelName(LocationManager locationManager0) {
        return Build.VERSION.SDK_INT < 28 ? null : Api28Impl.getGnssHardwareModelName(locationManager0);
    }

    public static int getGnssYearOfHardware(LocationManager locationManager0) {
        return Build.VERSION.SDK_INT < 28 ? 0 : Api28Impl.getGnssYearOfHardware(locationManager0);
    }

    public static boolean hasProvider(LocationManager locationManager0, String s) {
        if(Build.VERSION.SDK_INT >= 0x1F) {
            return Api31Impl.hasProvider(locationManager0, s);
        }
        if(locationManager0.getAllProviders().contains(s)) {
            return true;
        }
        try {
            if(locationManager0.getProvider(s) != null) {
                return true;
            }
        }
        catch(SecurityException unused_ex) {
        }
        return false;
    }

    // 去混淆评级： 低(20)
    public static boolean isLocationEnabled(LocationManager locationManager0) {
        return Build.VERSION.SDK_INT < 28 ? locationManager0.isProviderEnabled("network") || locationManager0.isProviderEnabled("gps") : Api28Impl.isLocationEnabled(locationManager0);
    }

    // 检测为 Lambda 实现
    static Boolean lambda$registerGnssStatusCallback$1(LocationManager locationManager0, GpsStatusTransport locationManagerCompat$GpsStatusTransport0) throws Exception [...]

    public static boolean registerGnssMeasurementsCallback(LocationManager locationManager0, GnssMeasurementsEvent.Callback gnssMeasurementsEvent$Callback0, Handler handler0) {
        if(Build.VERSION.SDK_INT > 30) {
            return Api24Impl.registerGnssMeasurementsCallback(locationManager0, gnssMeasurementsEvent$Callback0, handler0);
        }
        if(Build.VERSION.SDK_INT == 30) {
            return LocationManagerCompat.registerGnssMeasurementsCallbackOnR(locationManager0, ExecutorCompat.create(handler0), gnssMeasurementsEvent$Callback0);
        }
        synchronized(GnssListenersHolder.sGnssMeasurementListeners) {
            LocationManagerCompat.unregisterGnssMeasurementsCallback(locationManager0, gnssMeasurementsEvent$Callback0);
            if(Api24Impl.registerGnssMeasurementsCallback(locationManager0, gnssMeasurementsEvent$Callback0, handler0)) {
                GnssListenersHolder.sGnssMeasurementListeners.put(gnssMeasurementsEvent$Callback0, gnssMeasurementsEvent$Callback0);
                return true;
            }
            return false;
        }
    }

    public static boolean registerGnssMeasurementsCallback(LocationManager locationManager0, Executor executor0, GnssMeasurementsEvent.Callback gnssMeasurementsEvent$Callback0) {
        if(Build.VERSION.SDK_INT > 30) {
            return Api31Impl.registerGnssMeasurementsCallback(locationManager0, executor0, gnssMeasurementsEvent$Callback0);
        }
        if(Build.VERSION.SDK_INT == 30) {
            return LocationManagerCompat.registerGnssMeasurementsCallbackOnR(locationManager0, executor0, gnssMeasurementsEvent$Callback0);
        }
        synchronized(GnssListenersHolder.sGnssMeasurementListeners) {
            GnssMeasurementsTransport locationManagerCompat$GnssMeasurementsTransport0 = new GnssMeasurementsTransport(gnssMeasurementsEvent$Callback0, executor0);
            LocationManagerCompat.unregisterGnssMeasurementsCallback(locationManager0, gnssMeasurementsEvent$Callback0);
            if(Api24Impl.registerGnssMeasurementsCallback(locationManager0, locationManagerCompat$GnssMeasurementsTransport0)) {
                GnssListenersHolder.sGnssMeasurementListeners.put(gnssMeasurementsEvent$Callback0, locationManagerCompat$GnssMeasurementsTransport0);
                return true;
            }
            return false;
        }
    }

    private static boolean registerGnssMeasurementsCallbackOnR(LocationManager locationManager0, Executor executor0, GnssMeasurementsEvent.Callback gnssMeasurementsEvent$Callback0) {
        if(Build.VERSION.SDK_INT != 30) {
            throw new IllegalStateException();
        }
        try {
            if(LocationManagerCompat.sGnssRequestBuilderClass == null) {
                LocationManagerCompat.sGnssRequestBuilderClass = Class.forName("android.location.GnssRequest$Builder");
            }
            if(LocationManagerCompat.sGnssRequestBuilderBuildMethod == null) {
                Method method0 = LocationManagerCompat.sGnssRequestBuilderClass.getDeclaredMethod("build", null);
                LocationManagerCompat.sGnssRequestBuilderBuildMethod = method0;
                method0.setAccessible(true);
            }
            if(LocationManagerCompat.sRegisterGnssMeasurementsCallbackMethod == null) {
                Class[] arr_class = {Class.forName("android.location.GnssRequest"), Executor.class, GnssMeasurementsEvent.Callback.class};
                Method method1 = LocationManager.class.getDeclaredMethod("registerGnssMeasurementsCallback", arr_class);
                LocationManagerCompat.sRegisterGnssMeasurementsCallbackMethod = method1;
                method1.setAccessible(true);
            }
            Object object0 = LocationManagerCompat.sRegisterGnssMeasurementsCallbackMethod.invoke(locationManager0, LocationManagerCompat.sGnssRequestBuilderBuildMethod.invoke(LocationManagerCompat.sGnssRequestBuilderClass.getDeclaredConstructor(null).newInstance(null), null), executor0, gnssMeasurementsEvent$Callback0);
            if(object0 != null && ((Boolean)object0).booleanValue()) {
                return true;
            }
        }
        catch(ClassNotFoundException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException unused_ex) {
        }
        return false;
    }

    private static boolean registerGnssStatusCallback(LocationManager locationManager0, Handler handler0, Executor executor0, Callback gnssStatusCompat$Callback0) {
        int v4;
        int v3;
        long v2;
        long v1;
        if(Build.VERSION.SDK_INT >= 30) {
            return Api30Impl.registerGnssStatusCallback(locationManager0, handler0, executor0, gnssStatusCompat$Callback0);
        }
        if(Build.VERSION.SDK_INT >= 24) {
            return Api24Impl.registerGnssStatusCallback(locationManager0, handler0, executor0, gnssStatusCompat$Callback0);
        }
        Preconditions.checkArgument(handler0 != null);
        synchronized(GnssListenersHolder.sGnssStatusListeners) {
            GpsStatusTransport locationManagerCompat$GpsStatusTransport0 = (GpsStatusTransport)GnssListenersHolder.sGnssStatusListeners.get(gnssStatusCompat$Callback0);
            if(locationManagerCompat$GpsStatusTransport0 == null) {
                locationManagerCompat$GpsStatusTransport0 = new GpsStatusTransport(locationManager0, gnssStatusCompat$Callback0);
            }
            else {
                locationManagerCompat$GpsStatusTransport0.unregister();
            }
            locationManagerCompat$GpsStatusTransport0.register(executor0);
            FutureTask futureTask0 = new FutureTask(() -> Boolean.valueOf(locationManager0.addGpsStatusListener(locationManagerCompat$GpsStatusTransport0)));
            if(Looper.myLooper() == handler0.getLooper()) {
                futureTask0.run();
                goto label_19;
            }
            if(handler0.post(futureTask0)) {
                try {
                label_19:
                    v1 = TimeUnit.SECONDS.toNanos(5L);
                    v2 = System.nanoTime() + v1;
                    v3 = 0;
                }
                catch(ExecutionException executionException0) {
                    try {
                        v4 = 0;
                        goto label_40;
                    }
                    catch(Throwable throwable0) {
                        goto label_49;
                    }
                }
                catch(TimeoutException timeoutException0) {
                    v4 = 0;
                    throw new IllegalStateException(handler0 + " appears to be blocked, please run registerGnssStatusCallback() directly on a Looper thread or ensure the main Looper is not blocked by this thread", timeoutException0);
                }
                catch(Throwable throwable0) {
                    v4 = 0;
                    goto label_52;
                }
                while(true) {
                    try {
                    label_32:
                        if(!((Boolean)futureTask0.get(v1, TimeUnit.NANOSECONDS)).booleanValue()) {
                            break;
                        }
                        GnssListenersHolder.sGnssStatusListeners.put(gnssStatusCompat$Callback0, locationManagerCompat$GpsStatusTransport0);
                        return true;
                    }
                    catch(InterruptedException unused_ex) {
                    }
                    catch(ExecutionException executionException0) {
                        goto label_39;
                    }
                    catch(TimeoutException timeoutException0) {
                        goto label_46;
                    }
                    catch(Throwable throwable0) {
                        goto label_51;
                    }
                    v1 = v2 - System.nanoTime();
                    v3 = 1;
                    goto label_32;
                label_39:
                    v4 = v3;
                    try {
                    label_40:
                        if(executionException0.getCause() instanceof RuntimeException) {
                            throw (RuntimeException)executionException0.getCause();
                        }
                        if(!(executionException0.getCause() instanceof Error)) {
                            throw new IllegalStateException(executionException0);
                        }
                        throw (Error)executionException0.getCause();
                    label_46:
                        v4 = v3;
                        throw new IllegalStateException(handler0 + " appears to be blocked, please run registerGnssStatusCallback() directly on a Looper thread or ensure the main Looper is not blocked by this thread", timeoutException0);
                    }
                    catch(Throwable throwable0) {
                    label_49:
                        goto label_52;
                    }
                label_51:
                    v4 = v3;
                label_52:
                    if(v4 != 0) {
                        Thread.currentThread().interrupt();
                    }
                    throw throwable0;
                }
                return false;
            }
        }
        throw new IllegalStateException(handler0 + " is shutting down");
    }

    public static boolean registerGnssStatusCallback(LocationManager locationManager0, Callback gnssStatusCompat$Callback0, Handler handler0) {
        return Build.VERSION.SDK_INT < 30 ? LocationManagerCompat.registerGnssStatusCallback(locationManager0, new InlineHandlerExecutor(handler0), gnssStatusCompat$Callback0) : LocationManagerCompat.registerGnssStatusCallback(locationManager0, ExecutorCompat.create(handler0), gnssStatusCompat$Callback0);
    }

    public static boolean registerGnssStatusCallback(LocationManager locationManager0, Executor executor0, Callback gnssStatusCompat$Callback0) {
        if(Build.VERSION.SDK_INT >= 30) {
            return LocationManagerCompat.registerGnssStatusCallback(locationManager0, null, executor0, gnssStatusCompat$Callback0);
        }
        Looper looper0 = Looper.myLooper();
        if(looper0 == null) {
            looper0 = Looper.getMainLooper();
        }
        return LocationManagerCompat.registerGnssStatusCallback(locationManager0, new Handler(looper0), executor0, gnssStatusCompat$Callback0);
    }

    static void registerLocationListenerTransport(LocationManager locationManager0, LocationListenerTransport locationManagerCompat$LocationListenerTransport0) {
        LocationListenerKey locationManagerCompat$LocationListenerKey0 = locationManagerCompat$LocationListenerTransport0.getKey();
        WeakReference weakReference0 = new WeakReference(locationManagerCompat$LocationListenerTransport0);
        WeakReference weakReference1 = (WeakReference)LocationManagerCompat.sLocationListeners.put(locationManagerCompat$LocationListenerKey0, weakReference0);
        LocationListenerTransport locationManagerCompat$LocationListenerTransport1 = weakReference1 == null ? null : ((LocationListenerTransport)weakReference1.get());
        if(locationManagerCompat$LocationListenerTransport1 != null) {
            locationManagerCompat$LocationListenerTransport1.unregister();
            locationManager0.removeUpdates(locationManagerCompat$LocationListenerTransport1);
        }
    }

    public static void removeUpdates(LocationManager locationManager0, LocationListenerCompat locationListenerCompat0) {
        WeakHashMap weakHashMap0 = LocationManagerCompat.sLocationListeners;
        synchronized(weakHashMap0) {
            ArrayList arrayList0 = null;
            for(Object object0: weakHashMap0.values()) {
                LocationListenerTransport locationManagerCompat$LocationListenerTransport0 = (LocationListenerTransport)((WeakReference)object0).get();
                if(locationManagerCompat$LocationListenerTransport0 != null) {
                    LocationListenerKey locationManagerCompat$LocationListenerKey0 = locationManagerCompat$LocationListenerTransport0.getKey();
                    if(locationManagerCompat$LocationListenerKey0.mListener == locationListenerCompat0) {
                        if(arrayList0 == null) {
                            arrayList0 = new ArrayList();
                        }
                        arrayList0.add(locationManagerCompat$LocationListenerKey0);
                        locationManagerCompat$LocationListenerTransport0.unregister();
                        locationManager0.removeUpdates(locationManagerCompat$LocationListenerTransport0);
                    }
                }
            }
            if(arrayList0 != null) {
                for(Object object1: arrayList0) {
                    LocationManagerCompat.sLocationListeners.remove(((LocationListenerKey)object1));
                }
            }
        }
        locationManager0.removeUpdates(locationListenerCompat0);
    }

    public static void requestLocationUpdates(LocationManager locationManager0, String s, LocationRequestCompat locationRequestCompat0, LocationListenerCompat locationListenerCompat0, Looper looper0) {
        if(Build.VERSION.SDK_INT >= 0x1F) {
            Api31Impl.requestLocationUpdates(locationManager0, s, locationRequestCompat0.toLocationRequest(), ExecutorCompat.create(new Handler(looper0)), locationListenerCompat0);
            return;
        }
        if(Api19Impl.tryRequestLocationUpdates(locationManager0, s, locationRequestCompat0, locationListenerCompat0, looper0)) {
            return;
        }
        locationManager0.requestLocationUpdates(s, locationRequestCompat0.getIntervalMillis(), locationRequestCompat0.getMinUpdateDistanceMeters(), locationListenerCompat0, looper0);
    }

    public static void requestLocationUpdates(LocationManager locationManager0, String s, LocationRequestCompat locationRequestCompat0, Executor executor0, LocationListenerCompat locationListenerCompat0) {
        if(Build.VERSION.SDK_INT >= 0x1F) {
            Api31Impl.requestLocationUpdates(locationManager0, s, locationRequestCompat0.toLocationRequest(), executor0, locationListenerCompat0);
            return;
        }
        if(Build.VERSION.SDK_INT < 30 || !Api30Impl.tryRequestLocationUpdates(locationManager0, s, locationRequestCompat0, executor0, locationListenerCompat0)) {
            LocationListenerTransport locationManagerCompat$LocationListenerTransport0 = new LocationListenerTransport(new LocationListenerKey(s, locationListenerCompat0), executor0);
            if(!Api19Impl.tryRequestLocationUpdates(locationManager0, s, locationRequestCompat0, locationManagerCompat$LocationListenerTransport0)) {
                synchronized(LocationManagerCompat.sLocationListeners) {
                    locationManager0.requestLocationUpdates(s, locationRequestCompat0.getIntervalMillis(), locationRequestCompat0.getMinUpdateDistanceMeters(), locationManagerCompat$LocationListenerTransport0, Looper.getMainLooper());
                    LocationManagerCompat.registerLocationListenerTransport(locationManager0, locationManagerCompat$LocationListenerTransport0);
                }
            }
        }
    }

    public static void unregisterGnssMeasurementsCallback(LocationManager locationManager0, GnssMeasurementsEvent.Callback gnssMeasurementsEvent$Callback0) {
        if(Build.VERSION.SDK_INT >= 30) {
            Api24Impl.unregisterGnssMeasurementsCallback(locationManager0, gnssMeasurementsEvent$Callback0);
            return;
        }
        synchronized(GnssListenersHolder.sGnssMeasurementListeners) {
            GnssMeasurementsEvent.Callback gnssMeasurementsEvent$Callback1 = HalfKt..ExternalSyntheticApiModelOutline0.m(GnssListenersHolder.sGnssMeasurementListeners.remove(gnssMeasurementsEvent$Callback0));
            if(gnssMeasurementsEvent$Callback1 != null) {
                if(gnssMeasurementsEvent$Callback1 instanceof GnssMeasurementsTransport) {
                    ((GnssMeasurementsTransport)gnssMeasurementsEvent$Callback1).unregister();
                }
                Api24Impl.unregisterGnssMeasurementsCallback(locationManager0, gnssMeasurementsEvent$Callback1);
            }
        }
    }

    public static void unregisterGnssStatusCallback(LocationManager locationManager0, Callback gnssStatusCompat$Callback0) {
        if(Build.VERSION.SDK_INT >= 24) {
            SimpleArrayMap simpleArrayMap0 = GnssListenersHolder.sGnssStatusListeners;
            synchronized(simpleArrayMap0) {
                Object object0 = GnssListenersHolder.sGnssStatusListeners.remove(gnssStatusCompat$Callback0);
                if(object0 != null) {
                    Api24Impl.unregisterGnssStatusCallback(locationManager0, object0);
                }
            }
            return;
        }
        synchronized(GnssListenersHolder.sGnssStatusListeners) {
            GpsStatusTransport locationManagerCompat$GpsStatusTransport0 = (GpsStatusTransport)GnssListenersHolder.sGnssStatusListeners.remove(gnssStatusCompat$Callback0);
            if(locationManagerCompat$GpsStatusTransport0 != null) {
                locationManagerCompat$GpsStatusTransport0.unregister();
                locationManager0.removeGpsStatusListener(locationManagerCompat$GpsStatusTransport0);
            }
        }
    }
}

