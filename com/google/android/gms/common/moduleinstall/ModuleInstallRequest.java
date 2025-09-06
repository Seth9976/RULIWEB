package com.google.android.gms.common.moduleinstall;

import com.google.android.gms.common.api.OptionalModuleApi;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public final class ModuleInstallRequest {
    public static class Builder {
        private final List zaa;
        private InstallStatusListener zab;
        private Executor zac;

        public Builder() {
            this.zaa = new ArrayList();
        }

        public Builder addApi(OptionalModuleApi optionalModuleApi0) {
            this.zaa.add(optionalModuleApi0);
            return this;
        }

        public ModuleInstallRequest build() {
            return new ModuleInstallRequest(this.zaa, this.zab, this.zac, true, null);
        }

        public Builder setListener(InstallStatusListener installStatusListener0) {
            return this.setListener(installStatusListener0, null);
        }

        public Builder setListener(InstallStatusListener installStatusListener0, Executor executor0) {
            this.zab = installStatusListener0;
            this.zac = executor0;
            return this;
        }
    }

    private final List zaa;
    private final InstallStatusListener zab;
    private final Executor zac;

    ModuleInstallRequest(List list0, InstallStatusListener installStatusListener0, Executor executor0, boolean z, zac zac0) {
        Preconditions.checkNotNull(list0, "APIs must not be null.");
        Preconditions.checkArgument(!list0.isEmpty(), "APIs must not be empty.");
        if(executor0 != null) {
            Preconditions.checkNotNull(installStatusListener0, "Listener must not be null when listener executor is set.");
        }
        this.zaa = list0;
        this.zab = installStatusListener0;
        this.zac = executor0;
    }

    public List getApis() {
        return this.zaa;
    }

    public InstallStatusListener getListener() {
        return this.zab;
    }

    public Executor getListenerExecutor() {
        return this.zac;
    }

    public static Builder newBuilder() {
        return new Builder();
    }
}

