package androidx.browser.customtabs;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.customtabs.ICustomTabsService.Stub;

public abstract class CustomTabsServiceConnection implements ServiceConnection {
    private Context mApplicationContext;

    Context getApplicationContext() {
        return this.mApplicationContext;
    }

    public abstract void onCustomTabsServiceConnected(ComponentName arg1, CustomTabsClient arg2);

    @Override  // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName0, IBinder iBinder0) {
        if(this.mApplicationContext == null) {
            throw new IllegalStateException("Custom Tabs Service connected before an applicationcontext has been provided.");
        }
        this.onCustomTabsServiceConnected(componentName0, new CustomTabsClient(Stub.asInterface(iBinder0), componentName0, this.mApplicationContext) {
        });
    }

    void setApplicationContext(Context context0) {
        this.mApplicationContext = context0;
    }
}

