package com.navercorp.nid.util;

import android.content.Context;
import android.os.Build.VERSION;
import com.navercorp.nid.NaverIdLoginSDK;
import java.net.URLEncoder;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001A\u00020\u0004J\u0006\u0010\u0005\u001A\u00020\u0006J\u0006\u0010\u0007\u001A\u00020\b¨\u0006\t"}, d2 = {"Lcom/navercorp/nid/util/NidDeviceUtil;", "", "()V", "getLocale", "", "getSystemLocale", "Ljava/util/Locale;", "isKorean", "", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NidDeviceUtil {
    public static final NidDeviceUtil INSTANCE;

    static {
        NidDeviceUtil.INSTANCE = new NidDeviceUtil();
    }

    public final String getLocale() {
        Locale locale0 = this.getSystemLocale();
        String s = locale0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "systemLocale.toString()");
        if(s.length() == 0) {
            return "ko_KR";
        }
        String s1 = locale0.toString();
        Intrinsics.checkNotNullExpressionValue(s1, "systemLocale.toString()");
        return StringsKt.equals(s1, URLEncoder.encode(s1, "utf-8"), true) ? s1 : locale0.getLanguage() + "_" + locale0.getCountry();
    }

    public final Locale getSystemLocale() {
        Context context0 = NaverIdLoginSDK.INSTANCE.getApplicationContext();
        if(Build.VERSION.SDK_INT >= 24) {
            Locale locale0 = context0.getResources().getConfiguration().getLocales().get(0);
            Intrinsics.checkNotNullExpressionValue(locale0, "{\n            context.re….locales.get(0)\n        }");
            return locale0;
        }
        Locale locale1 = context0.getResources().getConfiguration().locale;
        Intrinsics.checkNotNullExpressionValue(locale1, "{\n            context.re…guration.locale\n        }");
        return locale1;
    }

    public final boolean isKorean() {
        String s = this.getSystemLocale().getLanguage();
        Intrinsics.checkNotNullExpressionValue(s, "getSystemLocale().language");
        return StringsKt.startsWith$default(s, "ko", false, 2, null);
    }
}

