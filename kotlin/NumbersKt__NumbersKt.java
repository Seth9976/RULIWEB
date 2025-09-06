package kotlin;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0005\n\u0002\u0010\n\n\u0002\b\b\u001A\r\u0010\u0000\u001A\u00020\u0001*\u00020\u0002H\u0087\b\u001A\r\u0010\u0000\u001A\u00020\u0001*\u00020\u0003H\u0087\b\u001A\r\u0010\u0004\u001A\u00020\u0001*\u00020\u0002H\u0087\b\u001A\r\u0010\u0004\u001A\u00020\u0001*\u00020\u0003H\u0087\b\u001A\r\u0010\u0005\u001A\u00020\u0001*\u00020\u0002H\u0087\b\u001A\r\u0010\u0005\u001A\u00020\u0001*\u00020\u0003H\u0087\b\u001A\u0014\u0010\u0006\u001A\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001A\u00020\u0001H\u0007\u001A\u0014\u0010\u0006\u001A\u00020\u0003*\u00020\u00032\u0006\u0010\u0007\u001A\u00020\u0001H\u0007\u001A\u0014\u0010\b\u001A\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001A\u00020\u0001H\u0007\u001A\u0014\u0010\b\u001A\u00020\u0003*\u00020\u00032\u0006\u0010\u0007\u001A\u00020\u0001H\u0007\u001A\r\u0010\t\u001A\u00020\u0002*\u00020\u0002H\u0087\b\u001A\r\u0010\t\u001A\u00020\u0003*\u00020\u0003H\u0087\b\u001A\r\u0010\n\u001A\u00020\u0002*\u00020\u0002H\u0087\b\u001A\r\u0010\n\u001A\u00020\u0003*\u00020\u0003H\u0087\b¨\u0006\u000B"}, d2 = {"countLeadingZeroBits", "", "", "", "countOneBits", "countTrailingZeroBits", "rotateLeft", "bitCount", "rotateRight", "takeHighestOneBit", "takeLowestOneBit", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/NumbersKt")
class NumbersKt__NumbersKt extends NumbersKt__NumbersJVMKt {
    private static final int countLeadingZeroBits(byte b) {
        return Integer.numberOfLeadingZeros(b & 0xFF) - 24;
    }

    private static final int countLeadingZeroBits(short v) {
        return Integer.numberOfLeadingZeros(v & 0xFFFF) - 16;
    }

    private static final int countOneBits(byte b) {
        return Integer.bitCount(b & 0xFF);
    }

    private static final int countOneBits(short v) {
        return Integer.bitCount(v & 0xFFFF);
    }

    private static final int countTrailingZeroBits(byte b) {
        return Integer.numberOfTrailingZeros(b | 0x100);
    }

    private static final int countTrailingZeroBits(short v) {
        return Integer.numberOfTrailingZeros(v | 0x10000);
    }

    public static final byte rotateLeft(byte b, int v) {
        return (byte)((b & 0xFF) >>> 8 - (v & 7) | b << (v & 7));
    }

    public static final short rotateLeft(short v, int v1) {
        return (short)((v & 0xFFFF) >>> 16 - (v1 & 15) | v << (v1 & 15));
    }

    public static final byte rotateRight(byte b, int v) {
        return (byte)((b & 0xFF) >>> (v & 7) | b << 8 - (v & 7));
    }

    public static final short rotateRight(short v, int v1) {
        return (short)((v & 0xFFFF) >>> (v1 & 15) | v << 16 - (v1 & 15));
    }

    private static final byte takeHighestOneBit(byte b) {
        return (byte)Integer.highestOneBit(b & 0xFF);
    }

    private static final short takeHighestOneBit(short v) {
        return (short)Integer.highestOneBit(v & 0xFFFF);
    }

    private static final byte takeLowestOneBit(byte b) {
        return (byte)Integer.lowestOneBit(b);
    }

    private static final short takeLowestOneBit(short v) {
        return (short)Integer.lowestOneBit(v);
    }
}

