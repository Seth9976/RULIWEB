package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.CommonStatusCodes;

@Deprecated
public final class GoogleSignInStatusCodes extends CommonStatusCodes {
    public static final int SIGN_IN_CANCELLED = 0x30D5;
    public static final int SIGN_IN_CURRENTLY_IN_PROGRESS = 0x30D6;
    public static final int SIGN_IN_FAILED = 12500;

    @Override  // com.google.android.gms.common.api.CommonStatusCodes
    public static String getStatusCodeString(int v) {
        switch(v) {
            case 12500: {
                return "A non-recoverable sign in failure occurred";
            }
            case 0x30D5: {
                return "Sign in action cancelled";
            }
            case 0x30D6: {
                return "Sign-in in progress";
            }
            default: {
                return CommonStatusCodes.getStatusCodeString(v);
            }
        }
    }
}

