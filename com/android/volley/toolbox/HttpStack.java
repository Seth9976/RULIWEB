package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import java.io.IOException;
import java.util.Map;
import org.apache.http.HttpResponse;

@Deprecated
public interface HttpStack {
    HttpResponse performRequest(Request arg1, Map arg2) throws IOException, AuthFailureError;
}

