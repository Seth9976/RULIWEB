package com.google.firebase.installations;

import com.google.android.gms.tasks.Task;
import com.google.firebase.installations.internal.FidListener;
import com.google.firebase.installations.internal.FidListenerHandle;

public interface FirebaseInstallationsApi {
    Task delete();

    Task getId();

    Task getToken(boolean arg1);

    FidListenerHandle registerFidListener(FidListener arg1);
}

