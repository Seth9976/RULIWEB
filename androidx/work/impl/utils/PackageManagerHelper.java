package androidx.work.impl.utils;

import android.content.ComponentName;
import android.content.Context;
import androidx.work.Logger;

public class PackageManagerHelper {
    private static final String TAG;

    static {
        PackageManagerHelper.TAG = "WM-PackageManagerHelper";
    }

    private static int getComponentEnabledSetting(Context context0, String s) {
        return context0.getPackageManager().getComponentEnabledSetting(new ComponentName(context0, s));
    }

    private static boolean isComponentEnabled(int setting, boolean defaults) {
        return setting == 0 ? defaults : setting == 1;
    }

    public static boolean isComponentExplicitlyEnabled(Context context0, Class class0) {
        return PackageManagerHelper.isComponentEnabled(PackageManagerHelper.getComponentEnabledSetting(context0, class0.getName()), false);
    }

    public static boolean isComponentExplicitlyEnabled(Context context0, String s) {
        return PackageManagerHelper.getComponentEnabledSetting(context0, s) == 1;
    }

    public static void setComponentEnabled(Context context0, Class class0, boolean z) {
        String s = "enabled";
        try {
            if(z == PackageManagerHelper.isComponentEnabled(PackageManagerHelper.getComponentEnabledSetting(context0, class0.getName()), false)) {
                Logger.get().debug("WM-PackageManagerHelper", "Skipping component enablement for " + class0.getName());
                return;
            }
            context0.getPackageManager().setComponentEnabledSetting(new ComponentName(context0, class0.getName()), (z ? 1 : 2), 1);
            Logger.get().debug("WM-PackageManagerHelper", class0.getName() + " " + (z ? "enabled" : "disabled"));
        }
        catch(Exception exception0) {
            Logger logger0 = Logger.get();
            StringBuilder stringBuilder0 = new StringBuilder();
            stringBuilder0.append(class0.getName());
            stringBuilder0.append("could not be ");
            if(!z) {
                s = "disabled";
            }
            stringBuilder0.append(s);
            logger0.debug("WM-PackageManagerHelper", stringBuilder0.toString(), exception0);
        }
    }
}

