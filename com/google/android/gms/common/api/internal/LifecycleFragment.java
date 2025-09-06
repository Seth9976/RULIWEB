package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;

public interface LifecycleFragment {
    void addCallback(String arg1, LifecycleCallback arg2);

    LifecycleCallback getCallbackOrNull(String arg1, Class arg2);

    Activity getLifecycleActivity();

    boolean isCreated();

    boolean isStarted();

    void startActivityForResult(Intent arg1, int arg2);
}

