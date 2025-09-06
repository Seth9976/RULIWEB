package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.Header;
import com.android.volley.Request;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

public abstract class BaseHttpStack implements HttpStack {
    public abstract HttpResponse executeRequest(Request arg1, Map arg2) throws IOException, AuthFailureError;

    @Override  // com.android.volley.toolbox.HttpStack
    @Deprecated
    public final org.apache.http.HttpResponse performRequest(Request request0, Map map0) throws IOException, AuthFailureError {
        HttpResponse httpResponse0 = this.executeRequest(request0, map0);
        org.apache.http.HttpResponse httpResponse1 = new BasicHttpResponse(((StatusLine)new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), httpResponse0.getStatusCode(), "")));
        ArrayList arrayList0 = new ArrayList();
        for(Object object0: httpResponse0.getHeaders()) {
            arrayList0.add(new BasicHeader(((Header)object0).getName(), ((Header)object0).getValue()));
        }
        ((BasicHttpResponse)httpResponse1).setHeaders(((org.apache.http.Header[])arrayList0.toArray(new org.apache.http.Header[0])));
        InputStream inputStream0 = httpResponse0.getContent();
        if(inputStream0 != null) {
            BasicHttpEntity basicHttpEntity0 = new BasicHttpEntity();
            basicHttpEntity0.setContent(inputStream0);
            basicHttpEntity0.setContentLength(((long)httpResponse0.getContentLength()));
            ((BasicHttpResponse)httpResponse1).setEntity(((HttpEntity)basicHttpEntity0));
        }
        return httpResponse1;
    }
}

