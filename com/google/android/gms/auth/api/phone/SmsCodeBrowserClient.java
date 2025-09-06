package com.google.android.gms.auth.api.phone;

import com.google.android.gms.common.api.HasApiKey;
import com.google.android.gms.tasks.Task;

public interface SmsCodeBrowserClient extends HasApiKey {
    Task startSmsCodeRetriever();
}

