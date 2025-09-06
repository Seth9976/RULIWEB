package androidx.constraintlayout.core.motion.utils;

public final class TypedValues.PositionType.-CC {
    static {
    }

    public static int getId(String s) {
        s.hashCode();
        switch(s) {
            case "drawPath": {
                return 502;
            }
            case "percentHeight": {
                return 504;
            }
            case "percentWidth": {
                return 503;
            }
            case "percentX": {
                return 506;
            }
            case "percentY": {
                return 507;
            }
            case "sizePercent": {
                return 505;
            }
            case "transitionEasing": {
                return 501;
            }
            default: {
                return -1;
            }
        }
    }

    public static int getType(int v) {
        switch(v) {
            case 101: 
            case 501: 
            case 502: {
                return 8;
            }
            case 503: 
            case 504: 
            case 505: 
            case 506: 
            case 507: {
                return 4;
            }
            case 100: 
            case 508: {
                return 2;
            }
            default: {
                return -1;
            }
        }
    }
}

