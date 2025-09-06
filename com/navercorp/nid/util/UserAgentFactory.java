package com.navercorp.nid.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Build;
import com.navercorp.nid.NaverIdLoginSDK;
import com.navercorp.nid.log.NidLog;
import kotlin.Metadata;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;
import kotlin.text.Regex;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001A\u00020\u0004J\n\u0010\u0006\u001A\u0004\u0018\u00010\u0004H\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/navercorp/nid/util/UserAgentFactory;", "", "()V", "TAG", "", "create", "generateAppInfo", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class UserAgentFactory {
    public static final UserAgentFactory INSTANCE;
    private static final String TAG;

    static {
        UserAgentFactory.INSTANCE = new UserAgentFactory();
        UserAgentFactory.TAG = "UserAgentFactory";
    }

    public final String create() {
        String s = UserAgentFactoryKt.refine(("Android/" + Build.VERSION.RELEASE));
        String s1 = UserAgentFactoryKt.refine(("Model/" + Build.MODEL));
        String s2 = this.generateAppInfo();
        return s2 == null || s2.length() == 0 ? s + " " + s1 : s + " " + s1 + " " + s2 + " " + "OAuthLoginMod/5.10.0";
    }

    private final String generateAppInfo() {
        try {
            String s = "";
            PackageManager packageManager0 = NaverIdLoginSDK.INSTANCE.getApplicationContext().getPackageManager();
            PackageInfo packageInfo0 = packageManager0.getPackageInfo("com.ruliweb.www.ruliapp", 0x1C0);
            CharSequence charSequence0 = packageInfo0.applicationInfo.loadDescription(packageManager0);
            if(charSequence0 != null && new Regex("^[a-zA-Z0-9]*$").matches(charSequence0)) {
                s = ",appId:" + charSequence0;
            }
            long v = Build.VERSION.SDK_INT < 28 ? ((long)packageInfo0.versionCode) : LinkFollowing..ExternalSyntheticApiModelOutline0.m(packageInfo0);
            return UserAgentFactoryKt.refine(("com.ruliweb.www.ruliapp/" + packageInfo0.versionName + "(" + v + ",uid:" + packageInfo0.applicationInfo.uid + s + ")"));
        }
        catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
            NidLog.e("UserAgentFactory", packageManager$NameNotFoundException0);
            return null;
        }
    }
}

