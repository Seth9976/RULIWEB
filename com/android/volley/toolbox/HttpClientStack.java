package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

@Deprecated
public class HttpClientStack implements HttpStack {
    public static final class HttpPatch extends HttpEntityEnclosingRequestBase {
        public static final String METHOD_NAME = "PATCH";

        public HttpPatch() {
        }

        public HttpPatch(String s) {
            this.setURI(URI.create(s));
        }

        public HttpPatch(URI uRI0) {
            this.setURI(uRI0);
        }

        public String getMethod() {
            return "PATCH";
        }
    }

    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    protected final HttpClient mClient;

    public HttpClientStack(HttpClient httpClient0) {
        this.mClient = httpClient0;
    }

    static HttpUriRequest createHttpRequest(Request request0, Map map0) throws AuthFailureError {
        switch(request0.getMethod()) {
            case -1: {
                byte[] arr_b = request0.getPostBody();
                if(arr_b != null) {
                    HttpUriRequest httpUriRequest0 = new HttpPost(request0.getUrl());
                    ((HttpPost)httpUriRequest0).addHeader("Content-Type", request0.getPostBodyContentType());
                    ((HttpPost)httpUriRequest0).setEntity(((HttpEntity)new ByteArrayEntity(arr_b)));
                    return httpUriRequest0;
                }
                return new HttpGet(request0.getUrl());
            }
            case 0: {
                return new HttpGet(request0.getUrl());
            }
            case 1: {
                HttpUriRequest httpUriRequest1 = new HttpPost(request0.getUrl());
                ((HttpPost)httpUriRequest1).addHeader("Content-Type", request0.getBodyContentType());
                HttpClientStack.setEntityIfNonEmptyBody(((HttpEntityEnclosingRequestBase)httpUriRequest1), request0);
                return httpUriRequest1;
            }
            case 2: {
                HttpUriRequest httpUriRequest2 = new HttpPut(request0.getUrl());
                ((HttpPut)httpUriRequest2).addHeader("Content-Type", request0.getBodyContentType());
                HttpClientStack.setEntityIfNonEmptyBody(((HttpEntityEnclosingRequestBase)httpUriRequest2), request0);
                return httpUriRequest2;
            }
            case 3: {
                return new HttpDelete(request0.getUrl());
            }
            case 4: {
                return new HttpHead(request0.getUrl());
            }
            case 5: {
                return new HttpOptions(request0.getUrl());
            }
            case 6: {
                return new HttpTrace(request0.getUrl());
            }
            case 7: {
                HttpUriRequest httpUriRequest3 = new HttpPatch(request0.getUrl());
                ((HttpPatch)httpUriRequest3).addHeader("Content-Type", request0.getBodyContentType());
                HttpClientStack.setEntityIfNonEmptyBody(((HttpEntityEnclosingRequestBase)httpUriRequest3), request0);
                return httpUriRequest3;
            }
            default: {
                throw new IllegalStateException("Unknown request method.");
            }
        }
    }

    private static List getPostParameterPairs(Map map0) {
        List list0 = new ArrayList(map0.size());
        for(Object object0: map0.keySet()) {
            list0.add(new BasicNameValuePair(((String)object0), ((String)map0.get(((String)object0)))));
        }
        return list0;
    }

    protected void onPrepareRequest(HttpUriRequest httpUriRequest0) throws IOException {
    }

    @Override  // com.android.volley.toolbox.HttpStack
    public HttpResponse performRequest(Request request0, Map map0) throws IOException, AuthFailureError {
        HttpUriRequest httpUriRequest0 = HttpClientStack.createHttpRequest(request0, map0);
        HttpClientStack.setHeaders(httpUriRequest0, map0);
        HttpClientStack.setHeaders(httpUriRequest0, request0.getHeaders());
        HttpParams httpParams0 = httpUriRequest0.getParams();
        int v = request0.getTimeoutMs();
        HttpConnectionParams.setConnectionTimeout(httpParams0, 5000);
        HttpConnectionParams.setSoTimeout(httpParams0, v);
        return this.mClient.execute(httpUriRequest0);
    }

    private static void setEntityIfNonEmptyBody(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase0, Request request0) throws AuthFailureError {
        byte[] arr_b = request0.getBody();
        if(arr_b != null) {
            httpEntityEnclosingRequestBase0.setEntity(((HttpEntity)new ByteArrayEntity(arr_b)));
        }
    }

    private static void setHeaders(HttpUriRequest httpUriRequest0, Map map0) {
        for(Object object0: map0.keySet()) {
            httpUriRequest0.setHeader(((String)object0), ((String)map0.get(((String)object0))));
        }
    }
}

