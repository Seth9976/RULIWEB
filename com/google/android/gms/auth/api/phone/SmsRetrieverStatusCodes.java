package com.google.android.gms.auth.api.phone;

import com.google.android.gms.common.api.CommonStatusCodes;

public final class SmsRetrieverStatusCodes extends CommonStatusCodes {
    public static final int API_NOT_AVAILABLE = 36501;
    public static final int PLATFORM_NOT_SUPPORTED = 36500;
    public static final int USER_PERMISSION_REQUIRED = 36502;

    @Override  // com.google.android.gms.common.api.CommonStatusCodes
    public static String getStatusCodeString(int v) {
        switch(v) {
            case 36500: {
                return "PLATFORM_NOT_SUPPORTED";
            }
            case 36501: {
                return "API_NOT_AVAILABLE";
            }
            case 36502: {
                return "USER_PERMISSION_REQUIRED";
            }
            default: {
                return CommonStatusCodes.getStatusCodeString(v);
            }
        }
    }
}

