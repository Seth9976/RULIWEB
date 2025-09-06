package androidx.constraintlayout.core.motion.utils;

public final class TypedValues.CycleType.-CC {
    static {
    }

    public static int getId(String s) {
        s.hashCode();
        switch(s) {
            case "alpha": {
                return 403;
            }
            case "curveFit": {
                return 401;
            }
            case "easing": {
                return 420;
            }
            case "pathRotate": {
                return 0x1A0;
            }
            case "pivotX": {
                return 313;
            }
            case "pivotY": {
                return 314;
            }
            case "progress": {
                return 315;
            }
            case "rotationX": {
                return 308;
            }
            case "rotationY": {
                return 309;
            }
            case "rotationZ": {
                return 310;
            }
            case "scaleX": {
                return 311;
            }
            case "scaleY": {
                return 312;
            }
            case "translationX": {
                return 304;
            }
            case "translationY": {
                return 305;
            }
            case "translationZ": {
                return 306;
            }
            case "visibility": {
                return 402;
            }
            default: {
                return -1;
            }
        }
    }

    public static int getType(int v) {
        switch(v) {
            case 100: 
            case 401: 
            case 402: {
                return 2;
            }
            case 304: 
            case 305: 
            case 306: 
            case 307: 
            case 308: 
            case 309: 
            case 310: 
            case 311: 
            case 312: 
            case 313: 
            case 314: 
            case 315: 
            case 403: 
            case 0x1A0: {
                return 4;
            }
            case 101: 
            case 420: 
            case 421: {
                return 8;
            }
            default: {
                return v == 423 || v == 424 || v == 425 ? 4 : -1;
            }
        }
    }
}

