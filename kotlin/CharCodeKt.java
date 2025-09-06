package kotlin;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\b\n\u0002\u0010\f\n\u0002\b\u0006\u001A\u0011\u0010\u0007\u001A\u00020\u00022\u0006\u0010\u0000\u001A\u00020\u0001H\u0087\b\"\u001F\u0010\u0000\u001A\u00020\u0001*\u00020\u00028Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001A\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"code", "", "", "getCode$annotations", "(C)V", "getCode", "(C)I", "Char", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class CharCodeKt {
    private static final char Char(int v) {
        if(v < 0 || v > 0xFFFF) {
            throw new IllegalArgumentException("Invalid Char code: " + v);
        }
        return (char)v;
    }

    private static final int getCode(char c) {
        return c;
    }

    public static void getCode$annotations(char c) {
    }
}

