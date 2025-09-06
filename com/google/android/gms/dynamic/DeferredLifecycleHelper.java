package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.util.LinkedList;

public abstract class DeferredLifecycleHelper {
    private LifecycleDelegate zaa;
    private Bundle zab;
    private LinkedList zac;
    private final OnDelegateCreatedListener zad;

    public DeferredLifecycleHelper() {
        this.zad = new zaa(this);
    }

    protected abstract void createDelegate(OnDelegateCreatedListener arg1);

    public LifecycleDelegate getDelegate() {
        return this.zaa;
    }

    protected void handleGooglePlayUnavailable(FrameLayout frameLayout0) {
        DeferredLifecycleHelper.showGooglePlayUnavailableMessage(frameLayout0);
    }

    public void onCreate(Bundle bundle0) {
        this.zaf(bundle0, new zac(this, bundle0));
    }

    @ResultIgnorabilityUnspecified
    public View onCreateView(LayoutInflater layoutInflater0, ViewGroup viewGroup0, Bundle bundle0) {
        View view0 = new FrameLayout(layoutInflater0.getContext());
        this.zaf(bundle0, new zad(this, ((FrameLayout)view0), layoutInflater0, viewGroup0, bundle0));
        if(this.zaa == null) {
            this.handleGooglePlayUnavailable(((FrameLayout)view0));
        }
        return view0;
    }

    public void onDestroy() {
        LifecycleDelegate lifecycleDelegate0 = this.zaa;
        if(lifecycleDelegate0 != null) {
            lifecycleDelegate0.onDestroy();
            return;
        }
        this.zae(1);
    }

    public void onDestroyView() {
        LifecycleDelegate lifecycleDelegate0 = this.zaa;
        if(lifecycleDelegate0 != null) {
            lifecycleDelegate0.onDestroyView();
            return;
        }
        this.zae(2);
    }

    public void onInflate(Activity activity0, Bundle bundle0, Bundle bundle1) {
        this.zaf(bundle1, new zab(this, activity0, bundle0, bundle1));
    }

    public void onLowMemory() {
        LifecycleDelegate lifecycleDelegate0 = this.zaa;
        if(lifecycleDelegate0 != null) {
            lifecycleDelegate0.onLowMemory();
        }
    }

    public void onPause() {
        LifecycleDelegate lifecycleDelegate0 = this.zaa;
        if(lifecycleDelegate0 != null) {
            lifecycleDelegate0.onPause();
            return;
        }
        this.zae(5);
    }

    public void onResume() {
        this.zaf(null, new zag(this));
    }

    public void onSaveInstanceState(Bundle bundle0) {
        LifecycleDelegate lifecycleDelegate0 = this.zaa;
        if(lifecycleDelegate0 != null) {
            lifecycleDelegate0.onSaveInstanceState(bundle0);
            return;
        }
        Bundle bundle1 = this.zab;
        if(bundle1 != null) {
            bundle0.putAll(bundle1);
        }
    }

    public void onStart() {
        this.zaf(null, new zaf(this));
    }

    public void onStop() {
        LifecycleDelegate lifecycleDelegate0 = this.zaa;
        if(lifecycleDelegate0 != null) {
            lifecycleDelegate0.onStop();
            return;
        }
        this.zae(4);
    }

    public static void showGooglePlayUnavailableMessage(FrameLayout frameLayout0) {
        GoogleApiAvailability googleApiAvailability0 = GoogleApiAvailability.getInstance();
        Context context0 = frameLayout0.getContext();
        int v = googleApiAvailability0.isGooglePlayServicesAvailable(context0);
        String s = com.google.android.gms.common.internal.zac.zac(context0, v);
        String s1 = com.google.android.gms.common.internal.zac.zab(context0, v);
        LinearLayout linearLayout0 = new LinearLayout(frameLayout0.getContext());
        linearLayout0.setOrientation(1);
        linearLayout0.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        frameLayout0.addView(linearLayout0);
        TextView textView0 = new TextView(frameLayout0.getContext());
        textView0.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        textView0.setText(s);
        linearLayout0.addView(textView0);
        Intent intent0 = googleApiAvailability0.getErrorResolutionIntent(context0, v, null);
        if(intent0 != null) {
            Button button0 = new Button(context0);
            button0.setId(0x1020019);
            button0.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            button0.setText(s1);
            linearLayout0.addView(button0);
            button0.setOnClickListener(new zae(context0, intent0));
        }
    }

    static void zad(DeferredLifecycleHelper deferredLifecycleHelper0, Bundle bundle0) {
        deferredLifecycleHelper0.zab = null;
    }

    private final void zae(int v) {
        while(!this.zac.isEmpty() && ((zah)this.zac.getLast()).zaa() >= v) {
            this.zac.removeLast();
        }
    }

    private final void zaf(Bundle bundle0, zah zah0) {
        LifecycleDelegate lifecycleDelegate0 = this.zaa;
        if(lifecycleDelegate0 != null) {
            zah0.zab(lifecycleDelegate0);
            return;
        }
        if(this.zac == null) {
            this.zac = new LinkedList();
        }
        this.zac.add(zah0);
        if(bundle0 != null) {
            Bundle bundle1 = this.zab;
            if(bundle1 == null) {
                this.zab = (Bundle)bundle0.clone();
            }
            else {
                bundle1.putAll(bundle0);
            }
        }
        this.createDelegate(this.zad);
    }
}

