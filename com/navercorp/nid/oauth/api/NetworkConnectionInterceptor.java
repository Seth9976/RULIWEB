package com.navercorp.nid.oauth.api;

import com.navercorp.nid.exception.NoConnectivityException;
import com.navercorp.nid.util.NidNetworkUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor.Chain;
import okhttp3.Interceptor;
import okhttp3.Response;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/navercorp/nid/oauth/api/NetworkConnectionInterceptor;", "Lokhttp3/Interceptor;", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "Nid-OAuth_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NetworkConnectionInterceptor implements Interceptor {
    @Override  // okhttp3.Interceptor
    public Response intercept(Chain interceptor$Chain0) throws NoConnectivityException {
        Intrinsics.checkNotNullParameter(interceptor$Chain0, "chain");
        if(NidNetworkUtil.INSTANCE.isNotAvailable()) {
            throw new NoConnectivityException();
        }
        return interceptor$Chain0.proceed(interceptor$Chain0.request().newBuilder().build());
    }
}

