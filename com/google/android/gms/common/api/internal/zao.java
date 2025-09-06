package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiActivity;
import com.google.android.gms.common.internal.Preconditions;

final class zao implements Runnable {
    final zap zaa;
    private final zam zab;

    zao(zap zap0, zam zam0) {
        this.zaa = zap0;
        super();
        this.zab = zam0;
    }

    @Override
    public final void run() {
        if(!this.zaa.zaa) {
            return;
        }
        ConnectionResult connectionResult0 = this.zab.zab();
        if(connectionResult0.hasResolution()) {
            this.zaa.mLifecycleFragment.startActivityForResult(GoogleApiActivity.zaa(this.zaa.getActivity(), ((PendingIntent)Preconditions.checkNotNull(connectionResult0.getResolution())), this.zab.zaa(), false), 1);
            return;
        }
        Activity activity0 = this.zaa.getActivity();
        if(this.zaa.zac.getErrorResolutionIntent(activity0, connectionResult0.getErrorCode(), null) != null) {
            Activity activity1 = this.zaa.getActivity();
            this.zaa.zac.zag(activity1, this.zaa.mLifecycleFragment, connectionResult0.getErrorCode(), 2, this.zaa);
            return;
        }
        if(connectionResult0.getErrorCode() == 18) {
            Activity activity2 = this.zaa.getActivity();
            Dialog dialog0 = this.zaa.zac.zab(activity2, this.zaa);
            Context context0 = this.zaa.getActivity().getApplicationContext();
            zan zan0 = new zan(this, dialog0);
            this.zaa.zac.zac(context0, zan0);
            return;
        }
        this.zaa.zaa(connectionResult0, this.zab.zaa());
    }
}

