package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import androidx.collection.ArrayMap;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.internal.common.zzi;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Map;
import java.util.WeakHashMap;

public final class zzd extends Fragment implements LifecycleFragment {
    private static final WeakHashMap zza;
    private final Map zzb;
    private int zzc;
    private Bundle zzd;

    static {
        zzd.zza = new WeakHashMap();
    }

    public zzd() {
        this.zzb = Collections.synchronizedMap(new ArrayMap());
        this.zzc = 0;
    }

    @Override  // com.google.android.gms.common.api.internal.LifecycleFragment
    public final void addCallback(String s, LifecycleCallback lifecycleCallback0) {
        if(this.zzb.containsKey(s)) {
            throw new IllegalArgumentException("LifecycleCallback with tag " + s + " already added to this fragment.");
        }
        this.zzb.put(s, lifecycleCallback0);
        if(this.zzc > 0) {
            new zzi(Looper.getMainLooper()).post(new zzc(this, lifecycleCallback0, s));
        }
    }

    @Override  // androidx.fragment.app.Fragment
    public final void dump(String s, FileDescriptor fileDescriptor0, PrintWriter printWriter0, String[] arr_s) {
        super.dump(s, fileDescriptor0, printWriter0, arr_s);
        for(Object object0: this.zzb.values()) {
            ((LifecycleCallback)object0).dump(s, fileDescriptor0, printWriter0, arr_s);
        }
    }

    @Override  // com.google.android.gms.common.api.internal.LifecycleFragment
    public final LifecycleCallback getCallbackOrNull(String s, Class class0) {
        return (LifecycleCallback)class0.cast(this.zzb.get(s));
    }

    @Override  // com.google.android.gms.common.api.internal.LifecycleFragment
    public final Activity getLifecycleActivity() {
        return this.getActivity();
    }

    @Override  // com.google.android.gms.common.api.internal.LifecycleFragment
    public final boolean isCreated() {
        return this.zzc > 0;
    }

    @Override  // com.google.android.gms.common.api.internal.LifecycleFragment
    public final boolean isStarted() {
        return this.zzc >= 2;
    }

    @Override  // androidx.fragment.app.Fragment
    public final void onActivityResult(int v, int v1, Intent intent0) {
        super.onActivityResult(v, v1, intent0);
        for(Object object0: this.zzb.values()) {
            ((LifecycleCallback)object0).onActivityResult(v, v1, intent0);
        }
    }

    @Override  // androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle0) {
        super.onCreate(bundle0);
        this.zzc = 1;
        this.zzd = bundle0;
        for(Object object0: this.zzb.entrySet()) {
            ((LifecycleCallback)((Map.Entry)object0).getValue()).onCreate((bundle0 == null ? null : bundle0.getBundle(((String)((Map.Entry)object0).getKey()))));
        }
    }

    @Override  // androidx.fragment.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
        this.zzc = 5;
        for(Object object0: this.zzb.values()) {
            ((LifecycleCallback)object0).onDestroy();
        }
    }

    @Override  // androidx.fragment.app.Fragment
    public final void onResume() {
        super.onResume();
        this.zzc = 3;
        for(Object object0: this.zzb.values()) {
            ((LifecycleCallback)object0).onResume();
        }
    }

    @Override  // androidx.fragment.app.Fragment
    public final void onSaveInstanceState(Bundle bundle0) {
        super.onSaveInstanceState(bundle0);
        if(bundle0 != null) {
            for(Object object0: this.zzb.entrySet()) {
                Bundle bundle1 = new Bundle();
                ((LifecycleCallback)((Map.Entry)object0).getValue()).onSaveInstanceState(bundle1);
                bundle0.putBundle(((String)((Map.Entry)object0).getKey()), bundle1);
            }
        }
    }

    @Override  // androidx.fragment.app.Fragment
    public final void onStart() {
        super.onStart();
        this.zzc = 2;
        for(Object object0: this.zzb.values()) {
            ((LifecycleCallback)object0).onStart();
        }
    }

    @Override  // androidx.fragment.app.Fragment
    public final void onStop() {
        super.onStop();
        this.zzc = 4;
        for(Object object0: this.zzb.values()) {
            ((LifecycleCallback)object0).onStop();
        }
    }

    static int zza(zzd zzd0) {
        return zzd0.zzc;
    }

    static Bundle zzb(zzd zzd0) {
        return zzd0.zzd;
    }

    public static zzd zzc(FragmentActivity fragmentActivity0) {
        zzd zzd1;
        WeakHashMap weakHashMap0 = zzd.zza;
        WeakReference weakReference0 = (WeakReference)weakHashMap0.get(fragmentActivity0);
        if(weakReference0 != null) {
            zzd zzd0 = (zzd)weakReference0.get();
            if(zzd0 != null) {
                return zzd0;
            }
        }
        try {
            zzd1 = (zzd)fragmentActivity0.getSupportFragmentManager().findFragmentByTag("SupportLifecycleFragmentImpl");
        }
        catch(ClassCastException classCastException0) {
            throw new IllegalStateException("Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl", classCastException0);
        }
        if(zzd1 == null || zzd1.isRemoving()) {
            zzd1 = new zzd();
            fragmentActivity0.getSupportFragmentManager().beginTransaction().add(zzd1, "SupportLifecycleFragmentImpl").commitAllowingStateLoss();
        }
        weakHashMap0.put(fragmentActivity0, new WeakReference(zzd1));
        return zzd1;
    }
}

