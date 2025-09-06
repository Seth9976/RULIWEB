package com.google.android.gms.common.moduleinstall.internal;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.RegistrationMethods;
import com.google.android.gms.common.api.internal.TaskApiCall.Builder;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.moduleinstall.InstallStatusListener;
import com.google.android.gms.common.moduleinstall.ModuleAvailabilityResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstallClient;
import com.google.android.gms.common.moduleinstall.ModuleInstallIntentResponse;
import com.google.android.gms.common.moduleinstall.ModuleInstallRequest;
import com.google.android.gms.common.moduleinstall.ModuleInstallResponse;
import com.google.android.gms.internal.base.zav;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

public final class zay extends GoogleApi implements ModuleInstallClient {
    public static final int zab;
    private static final ClientKey zac;
    private static final AbstractClientBuilder zad;
    private static final Api zae;

    static {
        ClientKey api$ClientKey0 = new ClientKey();
        zay.zac = api$ClientKey0;
        zaq zaq0 = new zaq();
        zay.zad = zaq0;
        zay.zae = new Api("ModuleInstall.API", zaq0, api$ClientKey0);
    }

    public zay(Activity activity0) {
        super(activity0, zay.zae, ApiOptions.NO_OPTIONS, Settings.DEFAULT_SETTINGS);
    }

    public zay(Context context0) {
        super(context0, zay.zae, ApiOptions.NO_OPTIONS, Settings.DEFAULT_SETTINGS);
    }

    @Override  // com.google.android.gms.common.moduleinstall.ModuleInstallClient
    public final Task areModulesAvailable(OptionalModuleApi[] arr_optionalModuleApi) {
        ApiFeatureRequest apiFeatureRequest0 = zay.zad(false, arr_optionalModuleApi);
        if(apiFeatureRequest0.getApiFeatures().isEmpty()) {
            return Tasks.forResult(new ModuleAvailabilityResponse(true, 0));
        }
        Builder taskApiCall$Builder0 = TaskApiCall.builder();
        taskApiCall$Builder0.setFeatures(new Feature[]{zav.zaa});
        taskApiCall$Builder0.setMethodKey(27301);
        taskApiCall$Builder0.setAutoResolveMissingFeatures(false);
        taskApiCall$Builder0.run(new zal(this, apiFeatureRequest0));
        return this.doRead(taskApiCall$Builder0.build());
    }

    @Override  // com.google.android.gms.common.moduleinstall.ModuleInstallClient
    public final Task deferredInstall(OptionalModuleApi[] arr_optionalModuleApi) {
        ApiFeatureRequest apiFeatureRequest0 = zay.zad(false, arr_optionalModuleApi);
        if(apiFeatureRequest0.getApiFeatures().isEmpty()) {
            return Tasks.forResult(null);
        }
        Builder taskApiCall$Builder0 = TaskApiCall.builder();
        taskApiCall$Builder0.setFeatures(new Feature[]{zav.zaa});
        taskApiCall$Builder0.setMethodKey(27302);
        taskApiCall$Builder0.setAutoResolveMissingFeatures(false);
        taskApiCall$Builder0.run(new zap(this, apiFeatureRequest0));
        return this.doRead(taskApiCall$Builder0.build());
    }

    @Override  // com.google.android.gms.common.moduleinstall.ModuleInstallClient
    public final Task getInstallModulesIntent(OptionalModuleApi[] arr_optionalModuleApi) {
        ApiFeatureRequest apiFeatureRequest0 = zay.zad(true, arr_optionalModuleApi);
        if(apiFeatureRequest0.getApiFeatures().isEmpty()) {
            return Tasks.forResult(new ModuleInstallIntentResponse(null));
        }
        Builder taskApiCall$Builder0 = TaskApiCall.builder();
        taskApiCall$Builder0.setFeatures(new Feature[]{zav.zaa});
        taskApiCall$Builder0.setMethodKey(27307);
        taskApiCall$Builder0.run(new zan(this, apiFeatureRequest0));
        return this.doRead(taskApiCall$Builder0.build());
    }

    @Override  // com.google.android.gms.common.moduleinstall.ModuleInstallClient
    public final Task installModules(ModuleInstallRequest moduleInstallRequest0) {
        ApiFeatureRequest apiFeatureRequest0 = ApiFeatureRequest.fromModuleInstallRequest(moduleInstallRequest0);
        InstallStatusListener installStatusListener0 = moduleInstallRequest0.getListener();
        Executor executor0 = moduleInstallRequest0.getListenerExecutor();
        if(apiFeatureRequest0.getApiFeatures().isEmpty()) {
            return Tasks.forResult(new ModuleInstallResponse(0));
        }
        if(installStatusListener0 == null) {
            Builder taskApiCall$Builder0 = TaskApiCall.builder();
            taskApiCall$Builder0.setFeatures(new Feature[]{zav.zaa});
            taskApiCall$Builder0.setAutoResolveMissingFeatures(true);
            taskApiCall$Builder0.setMethodKey(27304);
            taskApiCall$Builder0.run(new zao(this, apiFeatureRequest0));
            return this.doRead(taskApiCall$Builder0.build());
        }
        Preconditions.checkNotNull(installStatusListener0);
        ListenerHolder listenerHolder0 = executor0 == null ? this.registerListener(installStatusListener0, "InstallStatusListener") : ListenerHolders.createListenerHolder(installStatusListener0, executor0, "InstallStatusListener");
        zaab zaab0 = new zaab(listenerHolder0);
        AtomicReference atomicReference0 = new AtomicReference();
        zai zai0 = new zai(this, atomicReference0, installStatusListener0, apiFeatureRequest0, zaab0);
        zaj zaj0 = new zaj(this, zaab0);
        com.google.android.gms.common.api.internal.RegistrationMethods.Builder registrationMethods$Builder0 = RegistrationMethods.builder();
        registrationMethods$Builder0.withHolder(listenerHolder0);
        registrationMethods$Builder0.setFeatures(new Feature[]{zav.zaa});
        registrationMethods$Builder0.setAutoResolveMissingFeatures(true);
        registrationMethods$Builder0.register(zai0);
        registrationMethods$Builder0.unregister(zaj0);
        registrationMethods$Builder0.setMethodKey(27305);
        return this.doRegisterEventListener(registrationMethods$Builder0.build()).onSuccessTask(new zak(atomicReference0));
    }

    @Override  // com.google.android.gms.common.moduleinstall.ModuleInstallClient
    public final Task releaseModules(OptionalModuleApi[] arr_optionalModuleApi) {
        ApiFeatureRequest apiFeatureRequest0 = zay.zad(false, arr_optionalModuleApi);
        if(apiFeatureRequest0.getApiFeatures().isEmpty()) {
            return Tasks.forResult(null);
        }
        Builder taskApiCall$Builder0 = TaskApiCall.builder();
        taskApiCall$Builder0.setFeatures(new Feature[]{zav.zaa});
        taskApiCall$Builder0.setMethodKey(27303);
        taskApiCall$Builder0.setAutoResolveMissingFeatures(false);
        taskApiCall$Builder0.run(new zam(this, apiFeatureRequest0));
        return this.doRead(taskApiCall$Builder0.build());
    }

    @Override  // com.google.android.gms.common.moduleinstall.ModuleInstallClient
    @ResultIgnorabilityUnspecified
    public final Task unregisterListener(InstallStatusListener installStatusListener0) {
        return this.doUnregisterEventListener(ListenerHolders.createListenerKey(installStatusListener0, "InstallStatusListener"), 27306);
    }

    static final ApiFeatureRequest zad(boolean z, OptionalModuleApi[] arr_optionalModuleApi) {
        Preconditions.checkNotNull(arr_optionalModuleApi, "Requested APIs must not be null.");
        Preconditions.checkArgument(arr_optionalModuleApi.length > 0, "Please provide at least one OptionalModuleApi.");
        for(int v = 0; v < arr_optionalModuleApi.length; ++v) {
            Preconditions.checkNotNull(arr_optionalModuleApi[v], "Requested API must not be null.");
        }
        return ApiFeatureRequest.zaa(Arrays.asList(arr_optionalModuleApi), z);
    }
}

