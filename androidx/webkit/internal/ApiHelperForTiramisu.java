package androidx.webkit.internal;

import android.content.ComponentName;
import android.content.pm.PackageManager.ComponentInfoFlags;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import androidx.work.impl.utils.NetworkApi23..ExternalSyntheticApiModelOutline0;

public class ApiHelperForTiramisu {
    static ServiceInfo getServiceInfo(PackageManager packageManager0, ComponentName componentName0, PackageManager.ComponentInfoFlags packageManager$ComponentInfoFlags0) throws PackageManager.NameNotFoundException {
        return NetworkApi23..ExternalSyntheticApiModelOutline0.m(packageManager0, componentName0, packageManager$ComponentInfoFlags0);
    }

    static PackageManager.ComponentInfoFlags of(long v) {
        return NetworkApi23..ExternalSyntheticApiModelOutline0.m(v);
    }
}

