package com.google.android.gms.dynamic;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;

final class zae implements View.OnClickListener {
    final Context zaa;
    final Intent zab;

    zae(Context context0, Intent intent0) {
        this.zaa = context0;
        this.zab = intent0;
        super();
    }

    @Override  // android.view.View$OnClickListener
    public final void onClick(View view0) {
        try {
            this.zaa.startActivity(this.zab);
        }
        catch(ActivityNotFoundException activityNotFoundException0) {
            Log.e("DeferredLifecycleHelper", "Failed to start resolution intent", activityNotFoundException0);
        }
    }
}

