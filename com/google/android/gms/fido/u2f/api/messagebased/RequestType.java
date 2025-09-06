package com.google.android.gms.fido.u2f.api.messagebased;

@Deprecated
public enum RequestType {
    public static class UnsupportedRequestTypeException extends Exception {
        public UnsupportedRequestTypeException(String s) {
            super("Unsupported request type " + s);
        }
    }

    REGISTER("u2f_register_request"),
    SIGN("u2f_sign_request");

    private final String zzb;

    private RequestType(String s1) {
        this.zzb = s1;
    }

    public static RequestType fromString(String s) throws UnsupportedRequestTypeException {
        RequestType[] arr_requestType = RequestType.values();
        for(int v = 0; v < arr_requestType.length; ++v) {
            RequestType requestType0 = arr_requestType[v];
            if(s.equals(requestType0.zzb)) {
                return requestType0;
            }
        }
        throw new UnsupportedRequestTypeException(s);
    }

    @Override
    public String toString() {
        return this.zzb;
    }
}

