package androidx.core.content;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Parcelable;
import androidx.core.util.Preconditions;
import java.io.Serializable;
import java.util.ArrayList;

public final class IntentCompat {
    static class Api33Impl {
        static Object[] getParcelableArrayExtra(Intent intent0, String s, Class class0) {
            return intent0.getParcelableArrayExtra(s, class0);
        }

        static ArrayList getParcelableArrayListExtra(Intent intent0, String s, Class class0) {
            return intent0.getParcelableArrayListExtra(s, class0);
        }

        static Object getParcelableExtra(Intent intent0, String s, Class class0) {
            return intent0.getParcelableExtra(s, class0);
        }

        static Serializable getSerializableExtra(Intent intent0, String s, Class class0) {
            return intent0.getSerializableExtra(s, class0);
        }
    }

    public static final String ACTION_CREATE_REMINDER = "android.intent.action.CREATE_REMINDER";
    public static final String CATEGORY_LEANBACK_LAUNCHER = "android.intent.category.LEANBACK_LAUNCHER";
    public static final String EXTRA_HTML_TEXT = "android.intent.extra.HTML_TEXT";
    public static final String EXTRA_START_PLAYBACK = "android.intent.extra.START_PLAYBACK";
    public static final String EXTRA_TIME = "android.intent.extra.TIME";

    public static Intent createManageUnusedAppRestrictionsIntent(Context context0, String s) {
        if(!PackageManagerCompat.areUnusedAppRestrictionsAvailable(context0.getPackageManager())) {
            throw new UnsupportedOperationException("Unused App Restriction features are not available on this device");
        }
        if(Build.VERSION.SDK_INT >= 0x1F) {
            return new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.fromParts("package", s, null));
        }
        Intent intent0 = new Intent("android.intent.action.AUTO_REVOKE_PERMISSIONS").setData(Uri.fromParts("package", s, null));
        return Build.VERSION.SDK_INT < 30 ? intent0.setPackage(((String)Preconditions.checkNotNull(PackageManagerCompat.getPermissionRevocationVerifierApp(context0.getPackageManager())))) : intent0;
    }

    public static Parcelable[] getParcelableArrayExtra(Intent intent0, String s, Class class0) {
        return Build.VERSION.SDK_INT < 34 ? intent0.getParcelableArrayExtra(s) : ((Parcelable[])Api33Impl.getParcelableArrayExtra(intent0, s, class0));
    }

    public static ArrayList getParcelableArrayListExtra(Intent intent0, String s, Class class0) {
        return Build.VERSION.SDK_INT < 34 ? intent0.getParcelableArrayListExtra(s) : Api33Impl.getParcelableArrayListExtra(intent0, s, class0);
    }

    public static Object getParcelableExtra(Intent intent0, String s, Class class0) {
        if(Build.VERSION.SDK_INT >= 34) {
            return Api33Impl.getParcelableExtra(intent0, s, class0);
        }
        Parcelable parcelable0 = intent0.getParcelableExtra(s);
        return class0.isInstance(parcelable0) ? parcelable0 : null;
    }

    public static Serializable getSerializableExtra(Intent intent0, String s, Class class0) {
        if(Build.VERSION.SDK_INT >= 34) {
            return Api33Impl.getSerializableExtra(intent0, s, class0);
        }
        Serializable serializable0 = intent0.getSerializableExtra(s);
        return class0.isInstance(serializable0) ? serializable0 : null;
    }

    public static Intent makeMainSelectorActivity(String s, String s1) {
        return Intent.makeMainSelectorActivity(s, s1);
    }
}

