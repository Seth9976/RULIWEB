package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.Response;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONException;

public class JsonArrayRequest extends JsonRequest {
    public JsonArrayRequest(int v, String s, JSONArray jSONArray0, Listener response$Listener0, ErrorListener response$ErrorListener0) {
        super(v, s, (jSONArray0 == null ? null : jSONArray0.toString()), response$Listener0, response$ErrorListener0);
    }

    public JsonArrayRequest(String s, Listener response$Listener0, ErrorListener response$ErrorListener0) {
        super(0, s, null, response$Listener0, response$ErrorListener0);
    }

    @Override  // com.android.volley.toolbox.JsonRequest
    protected Response parseNetworkResponse(NetworkResponse networkResponse0) {
        try {
            String s = HttpHeaderParser.parseCharset(networkResponse0.headers, "utf-8");
            return Response.success(new JSONArray(new String(networkResponse0.data, s)), HttpHeaderParser.parseCacheHeaders(networkResponse0));
        }
        catch(UnsupportedEncodingException unsupportedEncodingException0) {
            return Response.error(new ParseError(unsupportedEncodingException0));
        }
        catch(JSONException jSONException0) {
            return Response.error(new ParseError(jSONException0));
        }
    }
}

