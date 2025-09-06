package com.google.android.gms.auth.api.signin.internal;

import android.content.Intent;
import android.os.Bundle;
import androidx.loader.app.LoaderManager.LoaderCallbacks;
import androidx.loader.content.Loader;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Set;

final class zbv implements LoaderCallbacks {
    final SignInHubActivity zba;

    zbv(SignInHubActivity signInHubActivity0, zbw zbw0) {
        this.zba = signInHubActivity0;
        super();
    }

    @Override  // androidx.loader.app.LoaderManager$LoaderCallbacks
    public final Loader onCreateLoader(int v, Bundle bundle0) {
        Set set0 = GoogleApiClient.getAllClients();
        return new zbc(this.zba, set0);
    }

    @Override  // androidx.loader.app.LoaderManager$LoaderCallbacks
    public final void onLoadFinished(Loader loader0, Object object0) {
        Void void0 = (Void)object0;
        int v = this.zba.zbe;
        Intent intent0 = this.zba.zbf;
        this.zba.setResult(v, intent0);
        this.zba.finish();
    }

    @Override  // androidx.loader.app.LoaderManager$LoaderCallbacks
    public final void onLoaderReset(Loader loader0) {
    }
}

