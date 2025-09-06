package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.Header;
import com.android.volley.Network;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class BasicNetwork implements Network {
    private static final int DEFAULT_POOL_SIZE = 0x1000;
    private final BaseHttpStack mBaseHttpStack;
    @Deprecated
    protected final HttpStack mHttpStack;
    protected final ByteArrayPool mPool;

    public BasicNetwork(BaseHttpStack baseHttpStack0) {
        this(baseHttpStack0, new ByteArrayPool(0x1000));
    }

    public BasicNetwork(BaseHttpStack baseHttpStack0, ByteArrayPool byteArrayPool0) {
        this.mBaseHttpStack = baseHttpStack0;
        this.mHttpStack = baseHttpStack0;
        this.mPool = byteArrayPool0;
    }

    @Deprecated
    public BasicNetwork(HttpStack httpStack0) {
        this(httpStack0, new ByteArrayPool(0x1000));
    }

    @Deprecated
    public BasicNetwork(HttpStack httpStack0, ByteArrayPool byteArrayPool0) {
        this.mHttpStack = httpStack0;
        this.mBaseHttpStack = new AdaptedHttpStack(httpStack0);
        this.mPool = byteArrayPool0;
    }

    @Deprecated
    protected static Map convertHeaders(Header[] arr_header) {
        Map map0 = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for(int v = 0; v < arr_header.length; ++v) {
            map0.put(arr_header[v].getName(), arr_header[v].getValue());
        }
        return map0;
    }

    @Override  // com.android.volley.Network
    public NetworkResponse performRequest(Request request0) throws VolleyError {
        byte[] arr_b1;
        List list0;
        int v1;
        byte[] arr_b;
        HttpResponse httpResponse1;
        IOException iOException1;
        HttpResponse httpResponse0;
        long v = SystemClock.elapsedRealtime();
        while(true) {
            try {
                Map map0 = HttpHeaderParser.getCacheHeaders(request0.getCacheEntry());
                httpResponse0 = this.mBaseHttpStack.executeRequest(request0, map0);
            }
            catch(IOException iOException0) {
                iOException1 = iOException0;
                httpResponse1 = null;
                arr_b = null;
                goto label_28;
            }
            try {
                v1 = httpResponse0.getStatusCode();
                list0 = httpResponse0.getHeaders();
                if(v1 == 304) {
                    return NetworkUtility.getNotModifiedNetworkResponse(request0, SystemClock.elapsedRealtime() - v, list0);
                }
                InputStream inputStream0 = httpResponse0.getContent();
                arr_b1 = inputStream0 == null ? new byte[0] : NetworkUtility.inputStreamToBytes(inputStream0, httpResponse0.getContentLength(), this.mPool);
            }
            catch(IOException iOException2) {
                arr_b = null;
                httpResponse1 = httpResponse0;
                goto label_27;
            }
            try {
                NetworkUtility.logSlowRequests(SystemClock.elapsedRealtime() - v, request0, arr_b1, v1);
                if(v1 < 200 || v1 > 299) {
                    throw new IOException();
                }
                return new NetworkResponse(v1, arr_b1, false, SystemClock.elapsedRealtime() - v, list0);
            }
            catch(IOException iOException2) {
                httpResponse1 = httpResponse0;
                arr_b = arr_b1;
            }
        label_27:
            iOException1 = iOException2;
        label_28:
            NetworkUtility.attemptRetryOnException(request0, NetworkUtility.shouldRetryException(request0, iOException1, v, httpResponse1, arr_b));
        }
    }
}

