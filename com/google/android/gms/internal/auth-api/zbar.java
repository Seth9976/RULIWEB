package com.google.android.gms.internal.auth-api;

import com.google.android.gms.common.Feature;

public final class zbar {
    public static final Feature zba;
    public static final Feature zbb;
    public static final Feature zbc;
    public static final Feature zbd;
    public static final Feature zbe;
    public static final Feature zbf;
    public static final Feature zbg;
    public static final Feature zbh;
    public static final Feature zbi;
    public static final Feature zbj;
    public static final Feature[] zbk;

    static {
        Feature feature0 = new Feature("auth_api_credentials_begin_sign_in", 9L);
        zbar.zba = feature0;
        Feature feature1 = new Feature("auth_api_credentials_sign_out", 2L);
        zbar.zbb = feature1;
        Feature feature2 = new Feature("auth_api_credentials_authorize", 1L);
        zbar.zbc = feature2;
        Feature feature3 = new Feature("auth_api_credentials_revoke_access", 1L);
        zbar.zbd = feature3;
        Feature feature4 = new Feature("auth_api_credentials_save_password", 4L);
        zbar.zbe = feature4;
        Feature feature5 = new Feature("auth_api_credentials_get_sign_in_intent", 6L);
        zbar.zbf = feature5;
        Feature feature6 = new Feature("auth_api_credentials_save_account_linking_token", 3L);
        zbar.zbg = feature6;
        Feature feature7 = new Feature("auth_api_credentials_get_phone_number_hint_intent", 3L);
        zbar.zbh = feature7;
        Feature feature8 = new Feature("auth_api_credentials_verify_with_google", 1L);
        zbar.zbi = feature8;
        Feature feature9 = new Feature("auth_api_credentials_credential_provider", 1L);
        zbar.zbj = feature9;
        zbar.zbk = new Feature[]{feature0, feature1, feature2, feature3, feature4, feature5, feature6, feature7, feature8, feature9};
    }
}

