package com.squareup.moshi;

final class JsonScope {
    static final int CLOSED = 8;
    static final int DANGLING_NAME = 4;
    static final int EMPTY_ARRAY = 1;
    static final int EMPTY_DOCUMENT = 6;
    static final int EMPTY_OBJECT = 3;
    static final int NONEMPTY_ARRAY = 2;
    static final int NONEMPTY_DOCUMENT = 7;
    static final int NONEMPTY_OBJECT = 5;
    static final int STREAMING_VALUE = 9;

    static String getPath(int v, int[] arr_v, String[] arr_s, int[] arr_v1) {
        StringBuilder stringBuilder0 = new StringBuilder("$");
        for(int v1 = 0; v1 < v; ++v1) {
            switch(arr_v[v1]) {
                case 1: 
                case 2: {
                    stringBuilder0.append('[');
                    stringBuilder0.append(arr_v1[v1]);
                    stringBuilder0.append(']');
                    break;
                }
                case 3: 
                case 4: 
                case 5: {
                    stringBuilder0.append('.');
                    String s = arr_s[v1];
                    if(s != null) {
                        stringBuilder0.append(s);
                    }
                }
            }
        }
        return stringBuilder0.toString();
    }
}

