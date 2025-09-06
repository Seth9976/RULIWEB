package kotlin.experimental;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0002\b\u0004\u001A\u0015\u0010\u0000\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0001H\u0087\f\u001A\u0015\u0010\u0000\u001A\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001A\u00020\u0003H\u0087\f\u001A\r\u0010\u0004\u001A\u00020\u0001*\u00020\u0001H\u0087\b\u001A\r\u0010\u0004\u001A\u00020\u0003*\u00020\u0003H\u0087\b\u001A\u0015\u0010\u0005\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0001H\u0087\f\u001A\u0015\u0010\u0005\u001A\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001A\u00020\u0003H\u0087\f\u001A\u0015\u0010\u0006\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0001H\u0087\f\u001A\u0015\u0010\u0006\u001A\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001A\u00020\u0003H\u0087\f¨\u0006\u0007"}, d2 = {"and", "", "other", "", "inv", "or", "xor", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class BitwiseOperationsKt {
    private static final byte and(byte b, byte b1) {
        return (byte)(b & b1);
    }

    private static final short and(short v, short v1) {
        return (short)(v & v1);
    }

    private static final byte inv(byte b) {
        return (byte)(~b);
    }

    private static final short inv(short v) {
        return (short)(~v);
    }

    private static final byte or(byte b, byte b1) {
        return (byte)(b | b1);
    }

    private static final short or(short v, short v1) {
        return (short)(v | v1);
    }

    private static final byte xor(byte b, byte b1) {
        return (byte)(b ^ b1);
    }

    private static final short xor(short v, short v1) {
        return (short)(v ^ v1);
    }
}

