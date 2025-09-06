package com.navercorp.nid.oauth;

import kotlin.Deprecated;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001A\u00020\u00048\u0006X\u0087T¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0002R\u000E\u0010\t\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u000BX\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/navercorp/nid/oauth/NidOAuthConstants;", "", "()V", "PACKAGE_NAME_CHROMEAPP", "", "PACKAGE_NAME_NAVERAPP", "SCHEME_OAUTH_LOGIN", "SCHEME_OAUTH_LOGIN_2NDAPP", "getSCHEME_OAUTH_LOGIN_2NDAPP$annotations", "SDK_VERSION", "TIME_OUT", "", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NidOAuthConstants {
    public static final NidOAuthConstants INSTANCE = null;
    public static final String PACKAGE_NAME_CHROMEAPP = "com.android.chrome";
    public static final String PACKAGE_NAME_NAVERAPP = "com.nhn.android.search";
    public static final String SCHEME_OAUTH_LOGIN = "com.nhn.android.search.action.OAUTH2_LOGIN";
    public static final String SCHEME_OAUTH_LOGIN_2NDAPP = "com.naver.android.action.OAUTH2_LOGIN";
    public static final String SDK_VERSION = "5.10.0";
    public static final long TIME_OUT = 10000L;

    static {
        NidOAuthConstants.INSTANCE = new NidOAuthConstants();
    }

    @Deprecated(message = "This field was deprecated")
    public static void getSCHEME_OAUTH_LOGIN_2NDAPP$annotations() {
    }
}

