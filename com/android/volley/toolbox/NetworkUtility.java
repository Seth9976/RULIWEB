package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.AuthFailureError;
import com.android.volley.Cache.Entry;
import com.android.volley.ClientError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.List;

final class NetworkUtility {
    static class RetryInfo {
        private final VolleyError errorToRetry;
        private final String logPrefix;

        private RetryInfo(String s, VolleyError volleyError0) {
            this.logPrefix = s;
            this.errorToRetry = volleyError0;
        }

        RetryInfo(String s, VolleyError volleyError0, com.android.volley.toolbox.NetworkUtility.1 networkUtility$10) {
            this(s, volleyError0);
        }

        static VolleyError access$000(RetryInfo networkUtility$RetryInfo0) {
            return networkUtility$RetryInfo0.errorToRetry;
        }

        static String access$100(RetryInfo networkUtility$RetryInfo0) {
            return networkUtility$RetryInfo0.logPrefix;
        }
    }

    private static final int SLOW_REQUEST_THRESHOLD_MS = 3000;

    static void attemptRetryOnException(Request request0, RetryInfo networkUtility$RetryInfo0) throws VolleyError {
        RetryPolicy retryPolicy0 = request0.getRetryPolicy();
        int v = request0.getTimeoutMs();
        try {
            retryPolicy0.retry(RetryInfo.access$000(networkUtility$RetryInfo0));
        }
        catch(VolleyError volleyError0) {
            request0.addMarker(String.format("%s-timeout-giveup [timeout=%s]", RetryInfo.access$100(networkUtility$RetryInfo0), v));
            throw volleyError0;
        }
        request0.addMarker(String.format("%s-retry [timeout=%s]", RetryInfo.access$100(networkUtility$RetryInfo0), v));
    }

    static NetworkResponse getNotModifiedNetworkResponse(Request request0, long v, List list0) {
        Entry cache$Entry0 = request0.getCacheEntry();
        if(cache$Entry0 == null) {
            return new NetworkResponse(304, null, true, v, list0);
        }
        List list1 = HttpHeaderParser.combineHeaders(list0, cache$Entry0);
        return new NetworkResponse(304, cache$Entry0.data, true, v, list1);
    }

    static byte[] inputStreamToBytes(InputStream inputStream0, int v, ByteArrayPool byteArrayPool0) throws IOException {
        byte[] arr_b1;
        byte[] arr_b;
        PoolingByteArrayOutputStream poolingByteArrayOutputStream0 = new PoolingByteArrayOutputStream(byteArrayPool0, v);
        try {
            arr_b = null;
            arr_b = byteArrayPool0.getBuf(0x400);
            int v1;
            while((v1 = inputStream0.read(arr_b)) != -1) {
                poolingByteArrayOutputStream0.write(arr_b, 0, v1);
            }
            arr_b1 = poolingByteArrayOutputStream0.toByteArray();
        }
        catch(Throwable throwable0) {
            if(inputStream0 != null) {
                try {
                    inputStream0.close();
                }
                catch(IOException unused_ex) {
                    VolleyLog.v("Error occurred when closing InputStream", new Object[0]);
                }
            }
            byteArrayPool0.returnBuf(arr_b);
            poolingByteArrayOutputStream0.close();
            throw throwable0;
        }
        try {
            inputStream0.close();
        }
        catch(IOException unused_ex) {
            VolleyLog.v("Error occurred when closing InputStream", new Object[0]);
        }
        byteArrayPool0.returnBuf(arr_b);
        poolingByteArrayOutputStream0.close();
        return arr_b1;
    }

    static void logSlowRequests(long v, Request request0, byte[] arr_b, int v1) {
        if(!VolleyLog.DEBUG && v <= 3000L) {
            return;
        }
        Long long0 = v;
        Integer integer0 = arr_b == null ? "null" : ((int)arr_b.length);
        VolleyLog.d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", new Object[]{request0, long0, integer0, v1, request0.getRetryPolicy().getCurrentRetryCount()});
    }

    static RetryInfo shouldRetryException(Request request0, IOException iOException0, long v, HttpResponse httpResponse0, byte[] arr_b) throws VolleyError {
        if(iOException0 instanceof SocketTimeoutException) {
            return new RetryInfo("socket", new TimeoutError(), null);
        }
        if(iOException0 instanceof MalformedURLException) {
            throw new RuntimeException("Bad URL " + request0.getUrl(), iOException0);
        }
        if(httpResponse0 != null) {
            int v1 = httpResponse0.getStatusCode();
            VolleyLog.e("Unexpected response code %d for %s", new Object[]{v1, request0.getUrl()});
            if(arr_b != null) {
                List list0 = httpResponse0.getHeaders();
                NetworkResponse networkResponse0 = new NetworkResponse(v1, arr_b, false, SystemClock.elapsedRealtime() - v, list0);
                if(v1 != 401 && v1 != 403) {
                    if(v1 >= 400 && v1 <= 0x1F3) {
                        throw new ClientError(networkResponse0);
                    }
                    if(v1 < 500 || v1 > 599 || !request0.shouldRetryServerErrors()) {
                        throw new ServerError(networkResponse0);
                    }
                    return new RetryInfo("server", new ServerError(networkResponse0), null);
                }
                return new RetryInfo("auth", new AuthFailureError(networkResponse0), null);
            }
            return new RetryInfo("network", new NetworkError(), null);
        }
        if(!request0.shouldRetryConnectionErrors()) {
            throw new NoConnectionError(iOException0);
        }
        return new RetryInfo("connection", new NoConnectionError(), null);
    }

    class com.android.volley.toolbox.NetworkUtility.1 {
    }

}

