package com.google.android.gms.common.api.internal;

import android.os.SystemClock;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.ConnectionTelemetryConfiguration;
import com.google.android.gms.common.internal.MethodInvocation;
import com.google.android.gms.common.internal.RootTelemetryConfigManager;
import com.google.android.gms.common.internal.RootTelemetryConfiguration;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

final class zacd implements OnCompleteListener {
    private final GoogleApiManager zaa;
    private final int zab;
    private final ApiKey zac;
    private final long zad;
    private final long zae;

    zacd(GoogleApiManager googleApiManager0, int v, ApiKey apiKey0, long v1, long v2, String s, String s1) {
        this.zaa = googleApiManager0;
        this.zab = v;
        this.zac = apiKey0;
        this.zad = v1;
        this.zae = v2;
    }

    @Override  // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task task0) {
        int v5;
        int v4;
        int v3;
        int v12;
        long v11;
        long v10;
        int v7;
        int v6;
        if(this.zaa.zaD()) {
            RootTelemetryConfiguration rootTelemetryConfiguration0 = RootTelemetryConfigManager.getInstance().getConfig();
            if(rootTelemetryConfiguration0 == null || rootTelemetryConfiguration0.getMethodInvocationTelemetryEnabled()) {
                zabq zabq0 = this.zaa.zai(this.zac);
                if(zabq0 != null && zabq0.zaf() instanceof BaseGmsClient) {
                    BaseGmsClient baseGmsClient0 = (BaseGmsClient)zabq0.zaf();
                    boolean z = true;
                    boolean z1 = this.zad > 0L;
                    int v = baseGmsClient0.getGCoreServiceId();
                    if(rootTelemetryConfiguration0 == null) {
                        v3 = 0;
                        v4 = 5000;
                        v5 = 100;
                    label_29:
                        GoogleApiManager googleApiManager0 = this.zaa;
                        if(task0.isSuccessful()) {
                            v6 = 0;
                            v7 = 0;
                        }
                        else if(task0.isCanceled()) {
                            v6 = 100;
                            v7 = -1;
                        }
                        else {
                            Exception exception0 = task0.getException();
                            if(exception0 instanceof ApiException) {
                                Status status0 = ((ApiException)exception0).getStatus();
                                int v8 = status0.getStatusCode();
                                ConnectionResult connectionResult0 = status0.getConnectionResult();
                                if(connectionResult0 == null) {
                                    v6 = v8;
                                    v7 = -1;
                                }
                                else {
                                    v7 = connectionResult0.getErrorCode();
                                    v6 = v8;
                                }
                            }
                            else {
                                v6 = 101;
                                v7 = -1;
                            }
                        }
                        if(z1) {
                            long v9 = SystemClock.elapsedRealtime();
                            v10 = this.zad;
                            v11 = System.currentTimeMillis();
                            v12 = (int)(v9 - this.zae);
                        }
                        else {
                            v10 = 0L;
                            v11 = 0L;
                            v12 = -1;
                        }
                        googleApiManager0.zaw(new MethodInvocation(this.zab, v6, v7, v10, v11, null, null, v, v12), v3, ((long)v4), v5);
                    }
                    else {
                        z1 &= rootTelemetryConfiguration0.getMethodTimingTelemetryEnabled();
                        int v1 = rootTelemetryConfiguration0.getBatchPeriodMillis();
                        int v2 = rootTelemetryConfiguration0.getMaxMethodInvocationsInBatch();
                        v3 = rootTelemetryConfiguration0.getVersion();
                        if(!baseGmsClient0.hasConnectionInfo() || baseGmsClient0.isConnecting()) {
                            v4 = v1;
                            v5 = v2;
                            goto label_29;
                        }
                        else {
                            ConnectionTelemetryConfiguration connectionTelemetryConfiguration0 = zacd.zab(zabq0, baseGmsClient0, this.zab);
                            if(connectionTelemetryConfiguration0 != null) {
                                if(!connectionTelemetryConfiguration0.getMethodTimingTelemetryEnabled() || this.zad <= 0L) {
                                    z = false;
                                }
                                z1 = z;
                                v4 = v1;
                                v5 = connectionTelemetryConfiguration0.getMaxMethodInvocationsLogged();
                                goto label_29;
                            }
                        }
                    }
                }
            }
        }
    }

    static zacd zaa(GoogleApiManager googleApiManager0, int v, ApiKey apiKey0) {
        if(googleApiManager0.zaD()) {
            RootTelemetryConfiguration rootTelemetryConfiguration0 = RootTelemetryConfigManager.getInstance().getConfig();
            if(rootTelemetryConfiguration0 == null) {
                goto label_15;
            }
            if(rootTelemetryConfiguration0.getMethodInvocationTelemetryEnabled()) {
                boolean z = rootTelemetryConfiguration0.getMethodTimingTelemetryEnabled();
                zabq zabq0 = googleApiManager0.zai(apiKey0);
                if(zabq0 == null) {
                    goto label_16;
                }
                if(zabq0.zaf() instanceof BaseGmsClient) {
                    BaseGmsClient baseGmsClient0 = (BaseGmsClient)zabq0.zaf();
                    if(!baseGmsClient0.hasConnectionInfo() || baseGmsClient0.isConnecting()) {
                        goto label_16;
                    }
                    ConnectionTelemetryConfiguration connectionTelemetryConfiguration0 = zacd.zab(zabq0, baseGmsClient0, v);
                    if(connectionTelemetryConfiguration0 != null) {
                        zabq0.zaq();
                        z = connectionTelemetryConfiguration0.getMethodTimingTelemetryEnabled();
                        goto label_16;
                    label_15:
                        z = true;
                    label_16:
                        long v1 = 0L;
                        long v2 = z ? System.currentTimeMillis() : 0L;
                        if(z) {
                            v1 = SystemClock.elapsedRealtime();
                        }
                        return new zacd(googleApiManager0, v, apiKey0, v2, v1, null, null);
                    }
                }
            }
        }
        return null;
    }

    private static ConnectionTelemetryConfiguration zab(zabq zabq0, BaseGmsClient baseGmsClient0, int v) {
        ConnectionTelemetryConfiguration connectionTelemetryConfiguration0 = baseGmsClient0.getTelemetryConfiguration();
        if(connectionTelemetryConfiguration0 != null && connectionTelemetryConfiguration0.getMethodInvocationTelemetryEnabled()) {
            int[] arr_v = connectionTelemetryConfiguration0.getMethodInvocationMethodKeyAllowlist();
            if(arr_v == null) {
                int[] arr_v1 = connectionTelemetryConfiguration0.getMethodInvocationMethodKeyDisallowlist();
                if(arr_v1 == null || !ArrayUtils.contains(arr_v1, v)) {
                    return zabq0.zac() >= connectionTelemetryConfiguration0.getMaxMethodInvocationsLogged() ? null : connectionTelemetryConfiguration0;
                }
                return null;
            }
            if(ArrayUtils.contains(arr_v, v)) {
                return zabq0.zac() >= connectionTelemetryConfiguration0.getMaxMethodInvocationsLogged() ? null : connectionTelemetryConfiguration0;
            }
        }
        return null;
    }
}

