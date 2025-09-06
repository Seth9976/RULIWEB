package com.google.android.gms.internal.measurement;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

public interface zzcc extends IInterface {
    void beginAdUnitExposure(String arg1, long arg2) throws RemoteException;

    void clearConditionalUserProperty(String arg1, String arg2, Bundle arg3) throws RemoteException;

    void clearMeasurementEnabled(long arg1) throws RemoteException;

    void endAdUnitExposure(String arg1, long arg2) throws RemoteException;

    void generateEventId(zzcf arg1) throws RemoteException;

    void getAppInstanceId(zzcf arg1) throws RemoteException;

    void getCachedAppInstanceId(zzcf arg1) throws RemoteException;

    void getConditionalUserProperties(String arg1, String arg2, zzcf arg3) throws RemoteException;

    void getCurrentScreenClass(zzcf arg1) throws RemoteException;

    void getCurrentScreenName(zzcf arg1) throws RemoteException;

    void getGmpAppId(zzcf arg1) throws RemoteException;

    void getMaxUserProperties(String arg1, zzcf arg2) throws RemoteException;

    void getSessionId(zzcf arg1) throws RemoteException;

    void getTestFlag(zzcf arg1, int arg2) throws RemoteException;

    void getUserProperties(String arg1, String arg2, boolean arg3, zzcf arg4) throws RemoteException;

    void initForTests(Map arg1) throws RemoteException;

    void initialize(IObjectWrapper arg1, zzcl arg2, long arg3) throws RemoteException;

    void isDataCollectionEnabled(zzcf arg1) throws RemoteException;

    void logEvent(String arg1, String arg2, Bundle arg3, boolean arg4, boolean arg5, long arg6) throws RemoteException;

    void logEventAndBundle(String arg1, String arg2, Bundle arg3, zzcf arg4, long arg5) throws RemoteException;

    void logHealthData(int arg1, String arg2, IObjectWrapper arg3, IObjectWrapper arg4, IObjectWrapper arg5) throws RemoteException;

    void onActivityCreated(IObjectWrapper arg1, Bundle arg2, long arg3) throws RemoteException;

    void onActivityDestroyed(IObjectWrapper arg1, long arg2) throws RemoteException;

    void onActivityPaused(IObjectWrapper arg1, long arg2) throws RemoteException;

    void onActivityResumed(IObjectWrapper arg1, long arg2) throws RemoteException;

    void onActivitySaveInstanceState(IObjectWrapper arg1, zzcf arg2, long arg3) throws RemoteException;

    void onActivityStarted(IObjectWrapper arg1, long arg2) throws RemoteException;

    void onActivityStopped(IObjectWrapper arg1, long arg2) throws RemoteException;

    void performAction(Bundle arg1, zzcf arg2, long arg3) throws RemoteException;

    void registerOnMeasurementEventListener(zzci arg1) throws RemoteException;

    void resetAnalyticsData(long arg1) throws RemoteException;

    void setConditionalUserProperty(Bundle arg1, long arg2) throws RemoteException;

    void setConsent(Bundle arg1, long arg2) throws RemoteException;

    void setConsentThirdParty(Bundle arg1, long arg2) throws RemoteException;

    void setCurrentScreen(IObjectWrapper arg1, String arg2, String arg3, long arg4) throws RemoteException;

    void setDataCollectionEnabled(boolean arg1) throws RemoteException;

    void setDefaultEventParameters(Bundle arg1) throws RemoteException;

    void setEventInterceptor(zzci arg1) throws RemoteException;

    void setInstanceIdProvider(zzck arg1) throws RemoteException;

    void setMeasurementEnabled(boolean arg1, long arg2) throws RemoteException;

    void setMinimumSessionDuration(long arg1) throws RemoteException;

    void setSessionTimeoutDuration(long arg1) throws RemoteException;

    void setUserId(String arg1, long arg2) throws RemoteException;

    void setUserProperty(String arg1, String arg2, IObjectWrapper arg3, boolean arg4, long arg5) throws RemoteException;

    void unregisterOnMeasurementEventListener(zzci arg1) throws RemoteException;
}

