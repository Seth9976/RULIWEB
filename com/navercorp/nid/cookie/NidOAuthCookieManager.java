package com.navercorp.nid.cookie;

import android.webkit.CookieManager;
import com.navercorp.nid.log.NidLog;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0006\u001A\u00020\u0004J&\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\u00040\b2\u0018\u0010\t\u001A\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\b0\nJ\u001C\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u00042\f\u0010\u000E\u001A\b\u0012\u0004\u0012\u00020\u00040\bR\u000E\u0010\u0003\u001A\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u000F"}, d2 = {"Lcom/navercorp/nid/cookie/NidOAuthCookieManager;", "", "()V", "NID_DOMAIN", "", "TAG", "getCookie", "getCookieListFromHeader", "", "header", "", "setCookie", "", "url", "cookies", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NidOAuthCookieManager {
    public static final NidOAuthCookieManager INSTANCE;
    private static final String NID_DOMAIN;
    private static final String TAG;

    static {
        NidOAuthCookieManager.INSTANCE = new NidOAuthCookieManager();
        NidOAuthCookieManager.TAG = "NidOAuthCookieManager";
        NidOAuthCookieManager.NID_DOMAIN = "https://nid.naver.com";
    }

    public final String getCookie() {
        String s = CookieManager.getInstance().getCookie(NidOAuthCookieManager.NID_DOMAIN);
        Intrinsics.checkNotNullExpressionValue(s, "getInstance().getCookie(NID_DOMAIN)");
        return s;
    }

    public final List getCookieListFromHeader(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "header");
        ArrayList arrayList0 = new ArrayList();
        for(Object object0: map0.keySet()) {
            String s = (String)object0;
            if(StringsKt.equals("Set-Cookie", s, true)) {
                List list0 = (List)map0.get(s);
                if(list0 != null && !list0.isEmpty()) {
                    for(Object object1: list0) {
                        String s1 = StringsKt.trim(((String)object1)).toString();
                        if(StringsKt.endsWith$default(s1, ";", false, 2, null)) {
                            arrayList0.add(String.valueOf(s1));
                        }
                        else {
                            arrayList0.add(s1 + ";");
                        }
                        NidLog.d("NidOAuthCookieManager", "cookie : " + s1);
                    }
                }
            }
        }
        return arrayList0;
    }

    public final void setCookie(String s, List list0) {
        Intrinsics.checkNotNullParameter(s, "url");
        Intrinsics.checkNotNullParameter(list0, "cookies");
        CookieManager cookieManager0 = CookieManager.getInstance();
        NidLog.i("NidOAuthCookieManager", "setCookie url : " + s);
        for(Object object0: list0) {
            cookieManager0.setCookie(s, ((String)object0));
            NidLog.i("NidOAuthCookieManager", "setCookie : " + ((String)object0));
        }
        cookieManager0.flush();
    }
}

