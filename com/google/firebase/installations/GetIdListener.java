package com.google.firebase.installations;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.installations.local.PersistedInstallationEntry;

class GetIdListener implements StateListener {
    final TaskCompletionSource taskCompletionSource;

    public GetIdListener(TaskCompletionSource taskCompletionSource0) {
        this.taskCompletionSource = taskCompletionSource0;
    }

    @Override  // com.google.firebase.installations.StateListener
    public boolean onException(Exception exception0) {
        return false;
    }

    @Override  // com.google.firebase.installations.StateListener
    public boolean onStateReached(PersistedInstallationEntry persistedInstallationEntry0) {
        if(!persistedInstallationEntry0.isUnregistered() && !persistedInstallationEntry0.isRegistered() && !persistedInstallationEntry0.isErrored()) {
            return false;
        }
        String s = persistedInstallationEntry0.getFirebaseInstallationId();
        this.taskCompletionSource.trySetResult(s);
        return true;
    }
}

