package com.navercorp.nid.profile.api;

import com.navercorp.nid.oauth.NidOAuthPreferencesManager;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0017\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u0017\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\b0\u0004H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"Lcom/navercorp/nid/profile/api/NidProfileApi;", "", "()V", "getNidProfileMap", "Lretrofit2/Response;", "Lcom/navercorp/nid/profile/data/NidProfileMap;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestApi", "Lcom/navercorp/nid/profile/data/NidProfileResponse;", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NidProfileApi {
    public final Object getNidProfileMap(Continuation continuation0) {
        String s = NidOAuthPreferencesManager.getAccessToken();
        if(s == null) {
            s = "";
        }
        return NidProfileService.Factory.create().getProfileMap("Bearer " + s, continuation0);
    }

    public final Object requestApi(Continuation continuation0) {
        String s = NidOAuthPreferencesManager.getAccessToken();
        if(s == null) {
            s = "";
        }
        return NidProfileService.Factory.create().requestApi("Bearer " + s, continuation0);
    }
}

