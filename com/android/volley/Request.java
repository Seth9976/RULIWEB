package com.android.volley;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Map;

public abstract class Request implements Comparable {
    public interface Method {
        public static final int DELETE = 3;
        public static final int DEPRECATED_GET_OR_POST = -1;
        public static final int GET = 0;
        public static final int HEAD = 4;
        public static final int OPTIONS = 5;
        public static final int PATCH = 7;
        public static final int POST = 1;
        public static final int PUT = 2;
        public static final int TRACE = 6;

    }

    interface NetworkRequestCompleteListener {
        void onNoUsableResponseReceived(Request arg1);

        void onResponseReceived(Request arg1, Response arg2);
    }

    public static enum Priority {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE;

    }

    private static final String DEFAULT_PARAMS_ENCODING = "UTF-8";
    private Entry mCacheEntry;
    private boolean mCanceled;
    private final int mDefaultTrafficStatsTag;
    private ErrorListener mErrorListener;
    private final MarkerLog mEventLog;
    private final Object mLock;
    private final int mMethod;
    private NetworkRequestCompleteListener mRequestCompleteListener;
    private RequestQueue mRequestQueue;
    private boolean mResponseDelivered;
    private RetryPolicy mRetryPolicy;
    private Integer mSequence;
    private boolean mShouldCache;
    private boolean mShouldRetryConnectionErrors;
    private boolean mShouldRetryServerErrors;
    private Object mTag;
    private final String mUrl;

    public Request(int v, String s, ErrorListener response$ErrorListener0) {
        this.mEventLog = MarkerLog.ENABLED ? new MarkerLog() : null;
        this.mLock = new Object();
        this.mShouldCache = true;
        this.mCanceled = false;
        this.mResponseDelivered = false;
        this.mShouldRetryServerErrors = false;
        this.mShouldRetryConnectionErrors = false;
        this.mCacheEntry = null;
        this.mMethod = v;
        this.mUrl = s;
        this.mErrorListener = response$ErrorListener0;
        this.setRetryPolicy(new DefaultRetryPolicy());
        this.mDefaultTrafficStatsTag = Request.findDefaultTrafficStatsTag(s);
    }

    @Deprecated
    public Request(String s, ErrorListener response$ErrorListener0) {
        this(-1, s, response$ErrorListener0);
    }

    public void addMarker(String s) {
        if(MarkerLog.ENABLED) {
            this.mEventLog.add(s, Thread.currentThread().getId());
        }
    }

    public void cancel() {
        synchronized(this.mLock) {
            this.mCanceled = true;
            this.mErrorListener = null;
        }
    }

    public int compareTo(Request request0) {
        Priority request$Priority0 = this.getPriority();
        Priority request$Priority1 = request0.getPriority();
        return request$Priority0 == request$Priority1 ? ((int)this.mSequence) - ((int)request0.mSequence) : request$Priority1.ordinal() - request$Priority0.ordinal();
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((Request)object0));
    }

    public void deliverError(VolleyError volleyError0) {
        ErrorListener response$ErrorListener0;
        synchronized(this.mLock) {
            response$ErrorListener0 = this.mErrorListener;
        }
        if(response$ErrorListener0 != null) {
            response$ErrorListener0.onErrorResponse(volleyError0);
        }
    }

    protected abstract void deliverResponse(Object arg1);

    private byte[] encodeParameters(Map map0, String s) {
        StringBuilder stringBuilder0 = new StringBuilder();
        try {
            for(Object object0: map0.entrySet()) {
                Map.Entry map$Entry0 = (Map.Entry)object0;
                if(map$Entry0.getKey() == null || map$Entry0.getValue() == null) {
                    throw new IllegalArgumentException(String.format("Request#getParams() or Request#getPostParams() returned a map containing a null key or value: (%s, %s). All keys and values must be non-null.", map$Entry0.getKey(), map$Entry0.getValue()));
                }
                stringBuilder0.append(URLEncoder.encode(((String)map$Entry0.getKey()), s));
                stringBuilder0.append('=');
                stringBuilder0.append(URLEncoder.encode(((String)map$Entry0.getValue()), s));
                stringBuilder0.append('&');
            }
            return stringBuilder0.toString().getBytes(s);
        }
        catch(UnsupportedEncodingException unsupportedEncodingException0) {
            throw new RuntimeException("Encoding not supported: " + s, unsupportedEncodingException0);
        }
    }

    private static int findDefaultTrafficStatsTag(String s) {
        if(!TextUtils.isEmpty(s)) {
            Uri uri0 = Uri.parse(s);
            if(uri0 != null) {
                String s1 = uri0.getHost();
                return s1 == null ? 0 : s1.hashCode();
            }
        }
        return 0;
    }

    void finish(String s) {
        RequestQueue requestQueue0 = this.mRequestQueue;
        if(requestQueue0 != null) {
            requestQueue0.finish(this);
        }
        if(MarkerLog.ENABLED) {
            long v = Thread.currentThread().getId();
            if(Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Request.this.mEventLog.add(s, v);
                        Request.this.mEventLog.finish(Request.this.toString());
                    }
                });
                return;
            }
            this.mEventLog.add(s, v);
            this.mEventLog.finish(this.toString());
        }
    }

    public byte[] getBody() throws AuthFailureError {
        Map map0 = this.getParams();
        return map0 == null || map0.size() <= 0 ? null : this.encodeParameters(map0, "UTF-8");
    }

    // 去混淆评级： 低(20)
    public String getBodyContentType() {
        return "application/x-www-form-urlencoded; charset=UTF-8";
    }

    public Entry getCacheEntry() {
        return this.mCacheEntry;
    }

    public String getCacheKey() {
        String s = this.getUrl();
        int v = this.getMethod();
        return v == -1 || v == 0 ? s : Integer.toString(v) + '-' + s;
    }

    public ErrorListener getErrorListener() {
        synchronized(this.mLock) {
        }
        return this.mErrorListener;
    }

    public Map getHeaders() throws AuthFailureError {
        return Collections.EMPTY_MAP;
    }

    public int getMethod() {
        return this.mMethod;
    }

    protected Map getParams() throws AuthFailureError {
        return null;
    }

    protected String getParamsEncoding() [...] // Inlined contents

    @Deprecated
    public byte[] getPostBody() throws AuthFailureError {
        Map map0 = this.getPostParams();
        return map0 == null || map0.size() <= 0 ? null : this.encodeParameters(map0, this.getPostParamsEncoding());
    }

    @Deprecated
    public String getPostBodyContentType() {
        return this.getBodyContentType();
    }

    @Deprecated
    protected Map getPostParams() throws AuthFailureError {
        return this.getParams();
    }

    @Deprecated
    protected String getPostParamsEncoding() {
        return "UTF-8";
    }

    public Priority getPriority() {
        return Priority.NORMAL;
    }

    public RetryPolicy getRetryPolicy() {
        return this.mRetryPolicy;
    }

    public final int getSequence() {
        Integer integer0 = this.mSequence;
        if(integer0 == null) {
            throw new IllegalStateException("getSequence called before setSequence");
        }
        return (int)integer0;
    }

    public Object getTag() {
        return this.mTag;
    }

    public final int getTimeoutMs() {
        return this.getRetryPolicy().getCurrentTimeout();
    }

    public int getTrafficStatsTag() {
        return this.mDefaultTrafficStatsTag;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public boolean hasHadResponseDelivered() {
        synchronized(this.mLock) {
        }
        return this.mResponseDelivered;
    }

    public boolean isCanceled() {
        synchronized(this.mLock) {
        }
        return this.mCanceled;
    }

    public void markDelivered() {
        synchronized(this.mLock) {
            this.mResponseDelivered = true;
        }
    }

    void notifyListenerResponseNotUsable() {
        NetworkRequestCompleteListener request$NetworkRequestCompleteListener0;
        synchronized(this.mLock) {
            request$NetworkRequestCompleteListener0 = this.mRequestCompleteListener;
        }
        if(request$NetworkRequestCompleteListener0 != null) {
            request$NetworkRequestCompleteListener0.onNoUsableResponseReceived(this);
        }
    }

    void notifyListenerResponseReceived(Response response0) {
        NetworkRequestCompleteListener request$NetworkRequestCompleteListener0;
        synchronized(this.mLock) {
            request$NetworkRequestCompleteListener0 = this.mRequestCompleteListener;
        }
        if(request$NetworkRequestCompleteListener0 != null) {
            request$NetworkRequestCompleteListener0.onResponseReceived(this, response0);
        }
    }

    protected VolleyError parseNetworkError(VolleyError volleyError0) [...] // Inlined contents

    protected abstract Response parseNetworkResponse(NetworkResponse arg1);

    void sendEvent(int v) {
        RequestQueue requestQueue0 = this.mRequestQueue;
        if(requestQueue0 != null) {
            requestQueue0.sendRequestEvent(this, v);
        }
    }

    public Request setCacheEntry(Entry cache$Entry0) {
        this.mCacheEntry = cache$Entry0;
        return this;
    }

    void setNetworkRequestCompleteListener(NetworkRequestCompleteListener request$NetworkRequestCompleteListener0) {
        synchronized(this.mLock) {
            this.mRequestCompleteListener = request$NetworkRequestCompleteListener0;
        }
    }

    public Request setRequestQueue(RequestQueue requestQueue0) {
        this.mRequestQueue = requestQueue0;
        return this;
    }

    public Request setRetryPolicy(RetryPolicy retryPolicy0) {
        this.mRetryPolicy = retryPolicy0;
        return this;
    }

    public final Request setSequence(int v) {
        this.mSequence = v;
        return this;
    }

    public final Request setShouldCache(boolean z) {
        this.mShouldCache = z;
        return this;
    }

    public final Request setShouldRetryConnectionErrors(boolean z) {
        this.mShouldRetryConnectionErrors = z;
        return this;
    }

    public final Request setShouldRetryServerErrors(boolean z) {
        this.mShouldRetryServerErrors = z;
        return this;
    }

    public Request setTag(Object object0) {
        this.mTag = object0;
        return this;
    }

    public final boolean shouldCache() {
        return this.mShouldCache;
    }

    public final boolean shouldRetryConnectionErrors() {
        return this.mShouldRetryConnectionErrors;
    }

    public final boolean shouldRetryServerErrors() {
        return this.mShouldRetryServerErrors;
    }

    // 去混淆评级： 低(20)
    @Override
    public String toString() {
        return (this.isCanceled() ? "[X] " : "[ ] ") + this.getUrl() + " " + ("0x" + Integer.toHexString(this.getTrafficStatsTag())) + " " + this.getPriority() + " " + this.mSequence;
    }
}

