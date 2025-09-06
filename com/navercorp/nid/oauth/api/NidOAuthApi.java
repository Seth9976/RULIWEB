package com.navercorp.nid.oauth.api;

import com.navercorp.nid.oauth.NidOAuthPreferencesManager;
import com.navercorp.nid.util.NidDeviceUtil;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u0017\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u0017\u0010\b\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"Lcom/navercorp/nid/oauth/api/NidOAuthApi;", "", "()V", "deleteToken", "Lretrofit2/Response;", "Lcom/navercorp/nid/oauth/data/NidOAuthResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestAccessToken", "requestRefreshToken", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NidOAuthApi {
    public final Object deleteToken(Continuation continuation0) {
        String s = NidOAuthPreferencesManager.getClientId();
        String s1 = s == null ? "" : s;
        String s2 = NidOAuthPreferencesManager.getClientSecret();
        String s3 = s2 == null ? "" : s2;
        String s4 = NidOAuthPreferencesManager.getAccessToken();
        return s4 == null ? DefaultImpls.requestDeleteToken$default(NidOAuthLoginService.Factory.create(), s1, s3, null, "", null, null, "android-5.10.0", NidDeviceUtil.INSTANCE.getLocale(), continuation0, 52, null) : DefaultImpls.requestDeleteToken$default(NidOAuthLoginService.Factory.create(), s1, s3, null, s4, null, null, "android-5.10.0", NidDeviceUtil.INSTANCE.getLocale(), continuation0, 52, null);
    }

    public final Object requestAccessToken(Continuation continuation0) {
        String s = NidOAuthPreferencesManager.getClientId();
        String s1 = s == null ? "" : s;
        String s2 = NidOAuthPreferencesManager.getClientSecret();
        String s3 = s2 == null ? "" : s2;
        String s4 = NidOAuthPreferencesManager.INSTANCE.getState();
        String s5 = s4 == null ? "" : s4;
        String s6 = NidOAuthPreferencesManager.INSTANCE.getCode();
        return s6 == null ? DefaultImpls.requestAccessToken$default(NidOAuthLoginService.Factory.create(), s1, s3, null, s5, "", null, "android-5.10.0", NidDeviceUtil.INSTANCE.getLocale(), continuation0, 36, null) : DefaultImpls.requestAccessToken$default(NidOAuthLoginService.Factory.create(), s1, s3, null, s5, s6, null, "android-5.10.0", NidDeviceUtil.INSTANCE.getLocale(), continuation0, 36, null);
    }

    public final Object requestRefreshToken(Continuation continuation0) {
        String s = NidOAuthPreferencesManager.getClientId();
        String s1 = s == null ? "" : s;
        String s2 = NidOAuthPreferencesManager.getClientSecret();
        String s3 = s2 == null ? "" : s2;
        String s4 = NidOAuthPreferencesManager.getRefreshToken();
        return s4 == null ? DefaultImpls.requestRefreshToken$default(NidOAuthLoginService.Factory.create(), s1, s3, null, "", null, "android-5.10.0", NidDeviceUtil.INSTANCE.getLocale(), continuation0, 20, null) : DefaultImpls.requestRefreshToken$default(NidOAuthLoginService.Factory.create(), s1, s3, null, s4, null, "android-5.10.0", NidDeviceUtil.INSTANCE.getLocale(), continuation0, 20, null);
    }
}

