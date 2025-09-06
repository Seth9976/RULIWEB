package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.Api.AnyClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;

public class BaseImplementation {
    public static abstract class ApiMethodImpl extends BasePendingResult implements ResultHolder {
        private final Api api;
        private final AnyClientKey clientKey;

        @Deprecated
        protected ApiMethodImpl(AnyClientKey api$AnyClientKey0, GoogleApiClient googleApiClient0) {
            super(((GoogleApiClient)Preconditions.checkNotNull(googleApiClient0, "GoogleApiClient must not be null")));
            this.clientKey = (AnyClientKey)Preconditions.checkNotNull(api$AnyClientKey0);
            this.api = null;
        }

        protected ApiMethodImpl(Api api0, GoogleApiClient googleApiClient0) {
            super(((GoogleApiClient)Preconditions.checkNotNull(googleApiClient0, "GoogleApiClient must not be null")));
            Preconditions.checkNotNull(api0, "Api must not be null");
            this.clientKey = api0.zab();
            this.api = api0;
        }

        protected ApiMethodImpl(CallbackHandler basePendingResult$CallbackHandler0) {
            super(basePendingResult$CallbackHandler0);
            this.clientKey = new AnyClientKey();
            this.api = null;
        }

        protected abstract void doExecute(AnyClient arg1) throws RemoteException;

        public final Api getApi() {
            return this.api;
        }

        public final AnyClientKey getClientKey() {
            return this.clientKey;
        }

        protected void onSetFailedResult(Result result0) {
        }

        public final void run(AnyClient api$AnyClient0) throws DeadObjectException {
            try {
                this.doExecute(api$AnyClient0);
            }
            catch(DeadObjectException deadObjectException0) {
                this.setFailedResult(deadObjectException0);
                throw deadObjectException0;
            }
            catch(RemoteException remoteException0) {
                this.setFailedResult(remoteException0);
            }
        }

        private void setFailedResult(RemoteException remoteException0) {
            this.setFailedResult(new Status(8, remoteException0.getLocalizedMessage(), null));
        }

        @Override  // com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder
        public final void setFailedResult(Status status0) {
            Preconditions.checkArgument(!status0.isSuccess(), "Failed result must not be success");
            this.setResult(this.createFailedResult(status0));
        }

        @Override  // com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder
        public void setResult(Object object0) {
            super.setResult(((Result)object0));
        }
    }

    public interface ResultHolder {
        void setFailedResult(Status arg1);

        void setResult(Object arg1);
    }

}

