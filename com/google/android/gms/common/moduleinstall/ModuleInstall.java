package com.google.android.gms.common.moduleinstall;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.moduleinstall.internal.zay;

public final class ModuleInstall {
    public static ModuleInstallClient getClient(Activity activity0) {
        return new zay(activity0);
    }

    public static ModuleInstallClient getClient(Context context0) {
        return new zay(context0);
    }
}

