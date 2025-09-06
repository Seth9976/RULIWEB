package androidx.core.content.res;

import android.content.res.Resources;

public final class ConfigurationHelper {
    public static int getDensityDpi(Resources resources0) {
        return resources0.getConfiguration().densityDpi;
    }
}

