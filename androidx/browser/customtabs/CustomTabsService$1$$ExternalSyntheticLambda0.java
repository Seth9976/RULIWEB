package androidx.browser.customtabs;

import android.os.IBinder.DeathRecipient;

public final class CustomTabsService.1..ExternalSyntheticLambda0 implements IBinder.DeathRecipient {
    public final androidx.browser.customtabs.CustomTabsService.1 f$0;
    public final CustomTabsSessionToken f$1;

    public CustomTabsService.1..ExternalSyntheticLambda0(androidx.browser.customtabs.CustomTabsService.1 customTabsService$10, CustomTabsSessionToken customTabsSessionToken0) {
        this.f$0 = customTabsService$10;
        this.f$1 = customTabsSessionToken0;
    }

    @Override  // android.os.IBinder$DeathRecipient
    public final void binderDied() {
        this.f$0.lambda$newSessionInternal$0$androidx-browser-customtabs-CustomTabsService$1(this.f$1);
    }
}

