package com.google.android.gms.dynamic;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface LifecycleDelegate {
    void onCreate(Bundle arg1);

    View onCreateView(LayoutInflater arg1, ViewGroup arg2, Bundle arg3);

    void onDestroy();

    void onDestroyView();

    void onInflate(Activity arg1, Bundle arg2, Bundle arg3);

    void onLowMemory();

    void onPause();

    void onResume();

    void onSaveInstanceState(Bundle arg1);

    void onStart();

    void onStop();
}

