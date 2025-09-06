package com.android.volley;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.TreeMap;

public class NetworkResponse {
    public final List allHeaders;
    public final byte[] data;
    public final Map headers;
    public final long networkTimeMs;
    public final boolean notModified;
    public final int statusCode;

    private NetworkResponse(int v, byte[] arr_b, Map map0, List list0, boolean z, long v1) {
        this.statusCode = v;
        this.data = arr_b;
        this.headers = map0;
        this.allHeaders = list0 == null ? null : Collections.unmodifiableList(list0);
        this.notModified = z;
        this.networkTimeMs = v1;
    }

    @Deprecated
    public NetworkResponse(int v, byte[] arr_b, Map map0, boolean z) {
        this(v, arr_b, map0, z, 0L);
    }

    @Deprecated
    public NetworkResponse(int v, byte[] arr_b, Map map0, boolean z, long v1) {
        this(v, arr_b, map0, NetworkResponse.toAllHeaderList(map0), z, v1);
    }

    public NetworkResponse(int v, byte[] arr_b, boolean z, long v1, List list0) {
        this(v, arr_b, NetworkResponse.toHeaderMap(list0), list0, z, v1);
    }

    public NetworkResponse(byte[] arr_b) {
        this(200, arr_b, false, 0L, Collections.EMPTY_LIST);
    }

    @Deprecated
    public NetworkResponse(byte[] arr_b, Map map0) {
        this(200, arr_b, map0, false, 0L);
    }

    private static List toAllHeaderList(Map map0) {
        if(map0 == null) {
            return null;
        }
        if(map0.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        List list0 = new ArrayList(map0.size());
        for(Object object0: map0.entrySet()) {
            list0.add(new Header(((String)((Map.Entry)object0).getKey()), ((String)((Map.Entry)object0).getValue())));
        }
        return list0;
    }

    private static Map toHeaderMap(List list0) {
        if(list0 == null) {
            return null;
        }
        if(list0.isEmpty()) {
            return Collections.EMPTY_MAP;
        }
        Map map0 = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for(Object object0: list0) {
            map0.put(((Header)object0).getName(), ((Header)object0).getValue());
        }
        return map0;
    }
}

