package com.google.android.gms.common.moduleinstall;

import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.tasks.Task;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;

public interface ModuleInstallClient extends HasApiKey {
    Task areModulesAvailable(OptionalModuleApi[] arg1);

    Task deferredInstall(OptionalModuleApi[] arg1);

    Task getInstallModulesIntent(OptionalModuleApi[] arg1);

    @ResultIgnorabilityUnspecified
    Task installModules(ModuleInstallRequest arg1);

    Task releaseModules(OptionalModuleApi[] arg1);

    @ResultIgnorabilityUnspecified
    Task unregisterListener(InstallStatusListener arg1);
}

