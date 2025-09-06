package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.Preconditions;

public class RegistrationMethods {
    public static class Builder {
        private RemoteCall zaa;
        private RemoteCall zab;
        private Runnable zac;
        private ListenerHolder zad;
        private Feature[] zae;
        private boolean zaf;
        private int zag;

        private Builder() {
            this.zac = zacj.zaa;
            this.zaf = true;
        }

        Builder(zacm zacm0) {
            this.zac = zacj.zaa;
            this.zaf = true;
        }

        public RegistrationMethods build() {
            boolean z = true;
            Preconditions.checkArgument(this.zaa != null, "Must set register function");
            Preconditions.checkArgument(this.zab != null, "Must set unregister function");
            if(this.zad == null) {
                z = false;
            }
            Preconditions.checkArgument(z, "Must set holder");
            ListenerKey listenerHolder$ListenerKey0 = (ListenerKey)Preconditions.checkNotNull(this.zad.getListenerKey(), "Key must not be null");
            return new RegistrationMethods(new zack(this, this.zad, this.zae, this.zaf, this.zag), new zacl(this, listenerHolder$ListenerKey0), this.zac, null);
        }

        public Builder onConnectionSuspended(Runnable runnable0) {
            this.zac = runnable0;
            return this;
        }

        public Builder register(RemoteCall remoteCall0) {
            this.zaa = remoteCall0;
            return this;
        }

        public Builder setAutoResolveMissingFeatures(boolean z) {
            this.zaf = z;
            return this;
        }

        public Builder setFeatures(Feature[] arr_feature) {
            this.zae = arr_feature;
            return this;
        }

        public Builder setMethodKey(int v) {
            this.zag = v;
            return this;
        }

        public Builder unregister(RemoteCall remoteCall0) {
            this.zab = remoteCall0;
            return this;
        }

        public Builder withHolder(ListenerHolder listenerHolder0) {
            this.zad = listenerHolder0;
            return this;
        }
    }

    public final RegisterListenerMethod register;
    public final UnregisterListenerMethod zaa;
    public final Runnable zab;

    RegistrationMethods(RegisterListenerMethod registerListenerMethod0, UnregisterListenerMethod unregisterListenerMethod0, Runnable runnable0, zacn zacn0) {
        this.register = registerListenerMethod0;
        this.zaa = unregisterListenerMethod0;
        this.zab = runnable0;
    }

    public static Builder builder() {
        return new Builder(null);
    }
}

