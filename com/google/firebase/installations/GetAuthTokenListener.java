package com.google.firebase.installations;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.installations.local.PersistedInstallationEntry;

class GetAuthTokenListener implements StateListener {
    private final TaskCompletionSource resultTaskCompletionSource;
    private final Utils utils;

    public GetAuthTokenListener(Utils utils0, TaskCompletionSource taskCompletionSource0) {
        this.utils = utils0;
        this.resultTaskCompletionSource = taskCompletionSource0;
    }

    @Override  // com.google.firebase.installations.StateListener
    public boolean onException(Exception exception0) {
        this.resultTaskCompletionSource.trySetException(exception0);
        return true;
    }

    @Override  // com.google.firebase.installations.StateListener
    public boolean onStateReached(PersistedInstallationEntry persistedInstallationEntry0) {
        if(persistedInstallationEntry0.isRegistered() && !this.utils.isAuthTokenExpired(persistedInstallationEntry0)) {
            InstallationTokenResult installationTokenResult0 = InstallationTokenResult.builder().setToken(persistedInstallationEntry0.getAuthToken()).setTokenExpirationTimestamp(persistedInstallationEntry0.getExpiresInSecs()).setTokenCreationTimestamp(persistedInstallationEntry0.getTokenCreationEpochInSecs()).build();
            this.resultTaskCompletionSource.setResult(installationTokenResult0);
            return true;
        }
        return false;
    }
}

