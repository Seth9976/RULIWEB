package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

public class BitEncoding {
    static final boolean $assertionsDisabled;
    private static final boolean FORCE_8TO7_ENCODING;

    private static void $$$reportNull$$$0(int v) {
        IllegalStateException illegalStateException0;
        int v1;
        String s;
        switch(v) {
            case 1: 
            case 3: 
            case 6: 
            case 8: 
            case 10: 
            case 12: 
            case 14: {
                s = "@NotNull method %s.%s must not return null";
                break;
            }
            default: {
                s = "Argument for @NotNull parameter \'%s\' of %s.%s must not be null";
            }
        }
        switch(v) {
            case 1: 
            case 3: 
            case 6: 
            case 8: 
            case 10: 
            case 12: 
            case 14: {
                v1 = 2;
                break;
            }
            default: {
                v1 = 3;
            }
        }
        Object[] arr_object = new Object[v1];
        switch(v) {
            case 1: 
            case 3: 
            case 6: 
            case 8: 
            case 10: 
            case 12: 
            case 14: {
                arr_object[0] = "kotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/BitEncoding";
                break;
            }
            default: {
                arr_object[0] = "data";
            }
        }
        switch(v) {
            case 1: {
                arr_object[1] = "encodeBytes";
                break;
            }
            case 3: {
                arr_object[1] = "encode8to7";
                break;
            }
            case 6: {
                arr_object[1] = "splitBytesToStringArray";
                break;
            }
            case 8: {
                arr_object[1] = "decodeBytes";
                break;
            }
            case 10: {
                arr_object[1] = "dropMarker";
                break;
            }
            case 12: {
                arr_object[1] = "combineStringArrayIntoBytes";
                break;
            }
            case 14: {
                arr_object[1] = "decode7to8";
                break;
            }
            default: {
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/metadata/jvm/deserialization/BitEncoding";
            }
        }
        switch(v) {
            case 2: {
                arr_object[2] = "encode8to7";
                break;
            }
            case 4: {
                arr_object[2] = "addModuloByte";
                break;
            }
            case 5: {
                arr_object[2] = "splitBytesToStringArray";
                break;
            }
            case 7: {
                arr_object[2] = "decodeBytes";
                break;
            }
            case 9: {
                arr_object[2] = "dropMarker";
                break;
            }
            case 11: {
                arr_object[2] = "combineStringArrayIntoBytes";
                break;
            }
            case 13: {
                arr_object[2] = "decode7to8";
                break;
            }
            case 1: 
            case 3: 
            case 6: 
            case 8: 
            case 10: 
            case 12: 
            case 14: {
                break;
            }
            default: {
                arr_object[2] = "encodeBytes";
            }
        }
        String s1 = String.format(s, arr_object);
        switch(v) {
            case 1: 
            case 3: 
            case 6: 
            case 8: 
            case 10: 
            case 12: 
            case 14: {
                illegalStateException0 = new IllegalStateException(s1);
                break;
            }
            default: {
                illegalStateException0 = new IllegalArgumentException(s1);
            }
        }
        throw illegalStateException0;
    }

    static {
        String s = null;
        try {
            s = System.getProperty("kotlin.jvm.serialization.use8to7");
        }
        catch(SecurityException unused_ex) {
        }
        BitEncoding.FORCE_8TO7_ENCODING = "true".equals(s);
    }

    private static void addModuloByte(byte[] arr_b, int v) {
        if(arr_b == null) {
            BitEncoding.$$$reportNull$$$0(4);
        }
        for(int v1 = 0; v1 < arr_b.length; ++v1) {
            arr_b[v1] = (byte)(arr_b[v1] + v & 0x7F);
        }
    }

    private static byte[] combineStringArrayIntoBytes(String[] arr_s) {
        if(arr_s == null) {
            BitEncoding.$$$reportNull$$$0(11);
        }
        int v1 = 0;
        for(int v = 0; v < arr_s.length; ++v) {
            v1 += arr_s[v].length();
        }
        byte[] arr_b = new byte[v1];
        for(int v2 = 0; v2 < arr_s.length; ++v2) {
            String s = arr_s[v2];
            int v4 = s.length();
            int v5 = 0;
            for(int v3 = 0; v5 < v4; ++v3) {
                arr_b[v3] = (byte)s.charAt(v5);
                ++v5;
            }
        }
        return arr_b;
    }

    private static byte[] decode7to8(byte[] arr_b) {
        if(arr_b == null) {
            BitEncoding.$$$reportNull$$$0(13);
        }
        int v = arr_b.length * 7 / 8;
        byte[] arr_b1 = new byte[v];
        int v2 = 0;
        int v3 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            arr_b1[v1] = (byte)(((arr_b[v2] & 0xFF) >>> v3) + ((arr_b[v2 + 1] & (1 << v3 + 1) - 1) << 7 - v3));
            if(v3 == 6) {
                v2 += 2;
                v3 = 0;
            }
            else {
                ++v2;
                ++v3;
            }
        }
        return arr_b1;
    }

    public static byte[] decodeBytes(String[] arr_s) {
        if(arr_s == null) {
            BitEncoding.$$$reportNull$$$0(7);
        }
        if(arr_s.length > 0 && !arr_s[0].isEmpty()) {
            int v = arr_s[0].charAt(0);
            if(v == 0) {
                byte[] arr_b = UtfEncodingKt.stringsToBytes(BitEncoding.dropMarker(arr_s));
                if(arr_b == null) {
                    BitEncoding.$$$reportNull$$$0(8);
                }
                return arr_b;
            }
            if(v == 0xFFFF) {
                arr_s = BitEncoding.dropMarker(arr_s);
            }
        }
        byte[] arr_b1 = BitEncoding.combineStringArrayIntoBytes(arr_s);
        BitEncoding.addModuloByte(arr_b1, 0x7F);
        return BitEncoding.decode7to8(arr_b1);
    }

    private static String[] dropMarker(String[] arr_s) {
        if(arr_s == null) {
            BitEncoding.$$$reportNull$$$0(9);
        }
        String[] arr_s1 = (String[])arr_s.clone();
        arr_s1[0] = arr_s1[0].substring(1);
        return arr_s1;
    }
}

