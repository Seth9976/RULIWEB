package com.google.firebase.messaging;

import java.util.Locale;

public final class SendException extends Exception {
    public static final int ERROR_INVALID_PARAMETERS = 1;
    public static final int ERROR_SIZE = 2;
    public static final int ERROR_TOO_MANY_MESSAGES = 4;
    public static final int ERROR_TTL_EXCEEDED = 3;
    public static final int ERROR_UNKNOWN;
    private final int errorCode;

    SendException(String s) {
        super(s);
        this.errorCode = this.parseErrorCode(s);
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    private int parseErrorCode(String s) {
        if(s == null) {
            return 0;
        }
        String s1 = s.toLowerCase(Locale.US);
        s1.hashCode();
        switch(s1) {
            case "invalid_parameters": {
                return 1;
            }
            case "messagetoobig": {
                return 2;
            }
            case "missing_to": {
                return 1;
            }
            case "service_not_available": {
                return 3;
            }
            case "toomanymessages": {
                return 4;
            }
            default: {
                return 0;
            }
        }
    }
}

