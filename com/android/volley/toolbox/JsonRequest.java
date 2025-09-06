package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import java.io.UnsupportedEncodingException;

public abstract class JsonRequest extends Request {
    protected static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE;
    private Listener mListener;
    private final Object mLock;
    private final String mRequestBody;

    static {
        JsonRequest.PROTOCOL_CONTENT_TYPE = "application/json; charset=utf-8";
    }

    public JsonRequest(int v, String s, String s1, Listener response$Listener0, ErrorListener response$ErrorListener0) {
        super(v, s, response$ErrorListener0);
        this.mLock = new Object();
        this.mListener = response$Listener0;
        this.mRequestBody = s1;
    }

    @Deprecated
    public JsonRequest(String s, String s1, Listener response$Listener0, ErrorListener response$ErrorListener0) {
        this(-1, s, s1, response$Listener0, response$ErrorListener0);
    }

    @Override  // com.android.volley.Request
    public void cancel() {
        super.cancel();
        synchronized(this.mLock) {
            this.mListener = null;
        }
    }

    @Override  // com.android.volley.Request
    protected void deliverResponse(Object object0) {
        Listener response$Listener0;
        synchronized(this.mLock) {
            response$Listener0 = this.mListener;
        }
        if(response$Listener0 != null) {
            response$Listener0.onResponse(object0);
        }
    }

    @Override  // com.android.volley.Request
    public byte[] getBody() {
        try {
            return this.mRequestBody == null ? null : this.mRequestBody.getBytes("utf-8");
        }
        catch(UnsupportedEncodingException unused_ex) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", new Object[]{this.mRequestBody, "utf-8"});
            return null;
        }
    }

    @Override  // com.android.volley.Request
    public String getBodyContentType() [...] // 潜在的解密器

    @Override  // com.android.volley.Request
    @Deprecated
    public byte[] getPostBody() {
        return this.getBody();
    }

    // 去混淆评级： 低(20)
    @Override  // com.android.volley.Request
    @Deprecated
    public String getPostBodyContentType() {
        return "application/json; charset=utf-8";
    }

    @Override  // com.android.volley.Request
    protected abstract Response parseNetworkResponse(NetworkResponse arg1);
}

