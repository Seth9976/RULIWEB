package com.google.android.gms.fido.u2f.api.messagebased;

@Deprecated
public enum ResponseType {
    REGISTER("u2f_register_response"),
    SIGN("u2f_sign_response");

    private final String zzb;

    private ResponseType(String s1) {
        this.zzb = s1;
    }

    public static ResponseType getResponseTypeForRequestType(RequestType requestType0) throws UnsupportedRequestTypeException {
        if(requestType0 != null) {
            switch(requestType0.ordinal()) {
                case 0: {
                    return ResponseType.REGISTER;
                }
                case 1: {
                    return ResponseType.SIGN;
                }
                default: {
                    throw new UnsupportedRequestTypeException(requestType0.toString());
                }
            }
        }
        throw new UnsupportedRequestTypeException(null);
    }

    @Override
    public String toString() {
        return this.zzb;
    }
}

