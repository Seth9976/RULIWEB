package androidx.appcompat.app;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.os.IBinder;

public final class AppLocalesMetadataHolderService extends Service {
    static class Api24Impl {
        static int getDisabledComponentFlag() [...] // Inlined contents
    }

    public static ServiceInfo getServiceInfo(Context context0) throws PackageManager.NameNotFoundException {
        return context0.getPackageManager().getServiceInfo(new ComponentName(context0, AppLocalesMetadataHolderService.class), 640);
    }

    @Override  // android.app.Service
    public IBinder onBind(Intent intent0) {
        throw new UnsupportedOperationException();
    }
}

