package com.navercorp.nid.scheme.api;

import android.content.Context;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b¨\u0006\t"}, d2 = {"Lcom/navercorp/nid/scheme/api/NidSchemeApi;", "", "()V", "requestSchemeLog", "", "context", "Landroid/content/Context;", "log", "", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NidSchemeApi {
    public final void requestSchemeLog(Context context0, String s) {
        Intrinsics.checkNotNullParameter(context0, "context");
        Intrinsics.checkNotNullParameter(s, "log");
        if(s.length() == 0) {
            return;
        }
        Map map0 = new LinkedHashMap();
        map0.put("body", s);
        NidSchemeService.Factory.create().requestSchemeLog(map0).enqueue(new com.navercorp.nid.scheme.api.NidSchemeApi.requestSchemeLog.1());

        @Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u001E\u0010\u0003\u001A\u00020\u00042\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00020\u00062\u0006\u0010\u0007\u001A\u00020\bH\u0016J$\u0010\t\u001A\u00020\u00042\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00020\u00062\f\u0010\n\u001A\b\u0012\u0004\u0012\u00020\u00020\u000BH\u0016¨\u0006\f"}, d2 = {"com/navercorp/nid/scheme/api/NidSchemeApi$requestSchemeLog$1", "Lretrofit2/Callback;", "", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
        public final class com.navercorp.nid.scheme.api.NidSchemeApi.requestSchemeLog.1 implements Callback {
            @Override  // retrofit2.Callback
            public void onFailure(Call call0, Throwable throwable0) {
                Intrinsics.checkNotNullParameter(call0, "call");
                Intrinsics.checkNotNullParameter(throwable0, "t");
            }

            @Override  // retrofit2.Callback
            public void onResponse(Call call0, Response response0) {
                Intrinsics.checkNotNullParameter(call0, "call");
                Intrinsics.checkNotNullParameter(response0, "response");
            }
        }

    }
}

