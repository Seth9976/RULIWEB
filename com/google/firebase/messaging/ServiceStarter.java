package com.google.firebase.messaging;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.Queue;

public class ServiceStarter {
    static final String ACTION_MESSAGING_EVENT = "com.google.firebase.MESSAGING_EVENT";
    static final int ERROR_ILLEGAL_STATE_EXCEPTION = 402;
    static final int ERROR_ILLEGAL_STATE_EXCEPTION_FALLBACK_TO_BIND = 403;
    static final int ERROR_NOT_FOUND = 404;
    static final int ERROR_SECURITY_EXCEPTION = 401;
    public static final int ERROR_UNKNOWN = 500;
    private static final String EXTRA_WRAPPED_INTENT = "wrapped_intent";
    private static final String PERMISSIONS_MISSING_HINT = "this should normally be included by the manifest merger, but may needed to be manually added to your manifest";
    public static final int SUCCESS = -1;
    private String firebaseMessagingServiceClassName;
    private Boolean hasAccessNetworkStatePermission;
    private Boolean hasWakeLockPermission;
    private static ServiceStarter instance;
    private final Queue messagingEvents;

    private ServiceStarter() {
        this.firebaseMessagingServiceClassName = null;
        this.hasWakeLockPermission = null;
        this.hasAccessNetworkStatePermission = null;
        this.messagingEvents = new ArrayDeque();
    }

    private int doStartService(Context context0, Intent intent0) {
        ComponentName componentName0;
        String s = this.resolveServiceClassName(context0, intent0);
        if(s != null) {
            if(Log.isLoggable("FirebaseMessaging", 3)) {
                Log.d("FirebaseMessaging", "Restricting intent to a specific service: " + s);
            }
            intent0.setClassName("com.ruliweb.www.ruliapp", s);
        }
        try {
            if(this.hasWakeLockPermission(context0)) {
                componentName0 = WakeLockHolder.startWakefulService(context0, intent0);
            }
            else {
                componentName0 = context0.startService(intent0);
                Log.d("FirebaseMessaging", "Missing wake lock permission, service start may be delayed");
            }
            if(componentName0 == null) {
                Log.e("FirebaseMessaging", "Error while delivering the message: ServiceIntent not found.");
                return 404;
            }
            return -1;
        }
        catch(SecurityException securityException0) {
            Log.e("FirebaseMessaging", "Error while delivering the message to the serviceIntent", securityException0);
            return 401;
        }
        catch(IllegalStateException illegalStateException0) {
            Log.e("FirebaseMessaging", "Failed to start service while in background: " + illegalStateException0);
            return 402;
        }
    }

    static ServiceStarter getInstance() {
        synchronized(ServiceStarter.class) {
            if(ServiceStarter.instance == null) {
                ServiceStarter.instance = new ServiceStarter();
            }
            return ServiceStarter.instance;
        }
    }

    Intent getMessagingEvent() {
        return (Intent)this.messagingEvents.poll();
    }

    boolean hasAccessNetworkStatePermission(Context context0) {
        if(this.hasAccessNetworkStatePermission == null) {
            this.hasAccessNetworkStatePermission = Boolean.valueOf(context0.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0);
        }
        if(!this.hasWakeLockPermission.booleanValue() && Log.isLoggable("FirebaseMessaging", 3)) {
            Log.d("FirebaseMessaging", "Missing Permission: android.permission.ACCESS_NETWORK_STATE this should normally be included by the manifest merger, but may needed to be manually added to your manifest");
        }
        return this.hasAccessNetworkStatePermission.booleanValue();
    }

    boolean hasWakeLockPermission(Context context0) {
        if(this.hasWakeLockPermission == null) {
            this.hasWakeLockPermission = Boolean.valueOf(context0.checkCallingOrSelfPermission("android.permission.WAKE_LOCK") == 0);
        }
        if(!this.hasWakeLockPermission.booleanValue() && Log.isLoggable("FirebaseMessaging", 3)) {
            Log.d("FirebaseMessaging", "Missing Permission: android.permission.WAKE_LOCK this should normally be included by the manifest merger, but may needed to be manually added to your manifest");
        }
        return this.hasWakeLockPermission.booleanValue();
    }

    private String resolveServiceClassName(Context context0, Intent intent0) {
        synchronized(this) {
            String s = this.firebaseMessagingServiceClassName;
            if(s != null) {
                return s;
            }
            ResolveInfo resolveInfo0 = context0.getPackageManager().resolveService(intent0, 0);
            if(resolveInfo0 != null && resolveInfo0.serviceInfo != null) {
                ServiceInfo serviceInfo0 = resolveInfo0.serviceInfo;
                if("com.ruliweb.www.ruliapp".equals(serviceInfo0.packageName) && serviceInfo0.name != null) {
                    this.firebaseMessagingServiceClassName = serviceInfo0.name.startsWith(".") ? "com.ruliweb.www.ruliapp" + serviceInfo0.name : serviceInfo0.name;
                    return this.firebaseMessagingServiceClassName;
                }
                Log.e("FirebaseMessaging", "Error resolving target intent service, skipping classname enforcement. Resolved service was: " + serviceInfo0.packageName + "/" + serviceInfo0.name);
                return null;
            }
            Log.e("FirebaseMessaging", "Failed to resolve target intent service, skipping classname enforcement");
            return null;
        }
    }

    public static void setForTesting(ServiceStarter serviceStarter0) {
        ServiceStarter.instance = serviceStarter0;
    }

    public int startMessagingService(Context context0, Intent intent0) {
        if(Log.isLoggable("FirebaseMessaging", 3)) {
            Log.d("FirebaseMessaging", "Starting service");
        }
        this.messagingEvents.offer(intent0);
        Intent intent1 = new Intent("com.google.firebase.MESSAGING_EVENT");
        intent1.setPackage("com.ruliweb.www.ruliapp");
        return this.doStartService(context0, intent1);
    }
}

