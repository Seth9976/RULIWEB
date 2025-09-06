package com.google.firebase.iid.internal;

import com.google.android.gms.tasks.Task;
import java.io.IOException;

public interface FirebaseInstanceIdInternal {
    public interface NewTokenListener {
        void onNewToken(String arg1);
    }

    void addNewTokenListener(NewTokenListener arg1);

    void deleteToken(String arg1, String arg2) throws IOException;

    String getId();

    String getToken();

    Task getTokenTask();
}

