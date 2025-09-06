package com.android.volley.toolbox;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

public final class HttpResponse {
    private final InputStream mContent;
    private final byte[] mContentBytes;
    private final int mContentLength;
    private final List mHeaders;
    private final int mStatusCode;

    public HttpResponse(int v, List list0) {
        this(v, list0, -1, null);
    }

    public HttpResponse(int v, List list0, int v1, InputStream inputStream0) {
        this.mStatusCode = v;
        this.mHeaders = list0;
        this.mContentLength = v1;
        this.mContent = inputStream0;
        this.mContentBytes = null;
    }

    public HttpResponse(int v, List list0, byte[] arr_b) {
        this.mStatusCode = v;
        this.mHeaders = list0;
        this.mContentLength = arr_b.length;
        this.mContentBytes = arr_b;
        this.mContent = null;
    }

    public final InputStream getContent() {
        InputStream inputStream0 = this.mContent;
        if(inputStream0 != null) {
            return inputStream0;
        }
        return this.mContentBytes != null ? new ByteArrayInputStream(this.mContentBytes) : null;
    }

    public final byte[] getContentBytes() {
        return this.mContentBytes;
    }

    public final int getContentLength() {
        return this.mContentLength;
    }

    public final List getHeaders() {
        return Collections.unmodifiableList(this.mHeaders);
    }

    public final int getStatusCode() {
        return this.mStatusCode;
    }
}

