package com.google.android.gms.auth.api.phone;

import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.tasks.Task;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public interface SmsCodeAutofillClient extends HasApiKey {
    @Retention(RetentionPolicy.SOURCE)
    @Target({ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
    public @interface PermissionState {
        public static final int DENIED = 2;
        public static final int GRANTED = 1;
        public static final int NONE;

    }

    Task checkPermissionState();

    Task hasOngoingSmsRequest(String arg1);

    Task startSmsCodeRetriever();
}

