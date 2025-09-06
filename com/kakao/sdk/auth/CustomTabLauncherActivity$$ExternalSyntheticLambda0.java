package com.kakao.sdk.auth;

import android.os.Handler.Callback;
import android.os.Message;

public final class CustomTabLauncherActivity..ExternalSyntheticLambda0 implements Handler.Callback {
    public final CustomTabLauncherActivity f$0;

    public CustomTabLauncherActivity..ExternalSyntheticLambda0(CustomTabLauncherActivity customTabLauncherActivity0) {
        this.f$0 = customTabLauncherActivity0;
    }

    @Override  // android.os.Handler$Callback
    public final boolean handleMessage(Message message0) {
        return CustomTabLauncherActivity.loadData$lambda-1(this.f$0, message0);
    }
}

