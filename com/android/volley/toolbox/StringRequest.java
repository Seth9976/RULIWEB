package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.Response;
import java.io.UnsupportedEncodingException;

public class StringRequest extends Request {
    private Listener mListener;
    private final Object mLock;

    public StringRequest(int v, String s, Listener response$Listener0, ErrorListener response$ErrorListener0) {
        super(v, s, response$ErrorListener0);
        this.mLock = new Object();
        this.mListener = response$Listener0;
    }

    public StringRequest(String s, Listener response$Listener0, ErrorListener response$ErrorListener0) {
        this(0, s, response$Listener0, response$ErrorListener0);
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
        this.deliverResponse(((String)object0));
    }

    protected void deliverResponse(String s) {
        Listener response$Listener0;
        synchronized(this.mLock) {
            response$Listener0 = this.mListener;
        }
        if(response$Listener0 != null) {
            response$Listener0.onResponse(s);
        }
    }

    @Override  // com.android.volley.Request
    protected Response parseNetworkResponse(NetworkResponse networkResponse0) {
        try {
            String s = HttpHeaderParser.parseCharset(networkResponse0.headers);
            return Response.success(new String(networkResponse0.data, s), HttpHeaderParser.parseCacheHeaders(networkResponse0));
        }
        catch(UnsupportedEncodingException unused_ex) {
            return Response.success(new String(networkResponse0.data), HttpHeaderParser.parseCacheHeaders(networkResponse0));
        }
    }
}

