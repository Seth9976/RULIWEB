package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.conn.ConnectTimeoutException;

class AdaptedHttpStack extends BaseHttpStack {
    private final HttpStack mHttpStack;

    AdaptedHttpStack(HttpStack httpStack0) {
        this.mHttpStack = httpStack0;
    }

    @Override  // com.android.volley.toolbox.BaseHttpStack
    public HttpResponse executeRequest(Request request0, Map map0) throws IOException, AuthFailureError {
        org.apache.http.HttpResponse httpResponse0;
        try {
            httpResponse0 = this.mHttpStack.performRequest(request0, map0);
        }
        catch(ConnectTimeoutException connectTimeoutException0) {
            throw new SocketTimeoutException(connectTimeoutException0.getMessage());
        }
        int v = httpResponse0.getStatusLine().getStatusCode();
        Header[] arr_header = httpResponse0.getAllHeaders();
        ArrayList arrayList0 = new ArrayList(arr_header.length);
        for(int v1 = 0; v1 < arr_header.length; ++v1) {
            Header header0 = arr_header[v1];
            arrayList0.add(new com.android.volley.Header(header0.getName(), header0.getValue()));
        }
        if(httpResponse0.getEntity() == null) {
            return new HttpResponse(v, arrayList0);
        }
        long v2 = httpResponse0.getEntity().getContentLength();
        if(((long)(((int)v2))) != v2) {
            throw new IOException("Response too large: " + v2);
        }
        return new HttpResponse(v, arrayList0, ((int)httpResponse0.getEntity().getContentLength()), httpResponse0.getEntity().getContent());
    }
}

