package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public class LifecycleCallback {
    protected final LifecycleFragment mLifecycleFragment;

    protected LifecycleCallback(LifecycleFragment lifecycleFragment0) {
        this.mLifecycleFragment = lifecycleFragment0;
    }

    public void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
    }

    public Activity getActivity() {
        Activity activity0 = this.mLifecycleFragment.getLifecycleActivity();
        Preconditions.checkNotNull(activity0);
        return activity0;
    }

    private static LifecycleFragment getChimeraLifecycleFragmentImpl(LifecycleActivity lifecycleActivity0) {
        throw new IllegalStateException("Method not available in SDK.");
    }

    public static LifecycleFragment getFragment(Activity activity0) {
        return LifecycleCallback.getFragment(new LifecycleActivity(activity0));
    }

    public static LifecycleFragment getFragment(ContextWrapper contextWrapper0) {
        throw new UnsupportedOperationException();
    }

    protected static LifecycleFragment getFragment(LifecycleActivity lifecycleActivity0) {
        if(lifecycleActivity0.zzd()) {
            return zzd.zzc(lifecycleActivity0.zzb());
        }
        if(!lifecycleActivity0.zzc()) {
            throw new IllegalArgumentException("Can\'t get fragment for unexpected activity.");
        }
        return zzb.zzc(lifecycleActivity0.zza());
    }

    public void onActivityResult(int v, int v1, Intent intent0) {
    }

    public void onCreate(Bundle bundle0) {
    }

    public void onDestroy() {
    }

    public void onResume() {
    }

    public void onSaveInstanceState(Bundle bundle0) {
    }

    public void onStart() {
    }

    public void onStop() {
    }
}

