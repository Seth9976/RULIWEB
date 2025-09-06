package kotlin.coroutines.jvm.internal;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\n\n\u0000\u001A\u0010\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\u0001\u001A\u0010\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0002\u001A\u00020\u0006H\u0001\u001A\u0010\u0010\u0007\u001A\u00020\b2\u0006\u0010\u0002\u001A\u00020\tH\u0001\u001A\u0010\u0010\n\u001A\u00020\u000B2\u0006\u0010\u0002\u001A\u00020\fH\u0001\u001A\u0010\u0010\r\u001A\u00020\u000E2\u0006\u0010\u0002\u001A\u00020\u000FH\u0001\u001A\u0010\u0010\u0010\u001A\u00020\u00112\u0006\u0010\u0002\u001A\u00020\u0012H\u0001\u001A\u0010\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0002\u001A\u00020\u0015H\u0001\u001A\u0010\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0002\u001A\u00020\u0018H\u0001¨\u0006\u0019"}, d2 = {"boxBoolean", "Ljava/lang/Boolean;", "primitive", "", "boxByte", "Ljava/lang/Byte;", "", "boxChar", "Ljava/lang/Character;", "", "boxDouble", "Ljava/lang/Double;", "", "boxFloat", "Ljava/lang/Float;", "", "boxInt", "Ljava/lang/Integer;", "", "boxLong", "Ljava/lang/Long;", "", "boxShort", "Ljava/lang/Short;", "", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class Boxing {
    public static final Boolean boxBoolean(boolean z) {
        return Boolean.valueOf(z);
    }

    public static final Byte boxByte(byte b) {
        return b;
    }

    public static final Character boxChar(char c) {
        return new Character(c);
    }

    public static final Double boxDouble(double f) {
        return new Double(f);
    }

    public static final Float boxFloat(float f) {
        return new Float(f);
    }

    public static final Integer boxInt(int v) {
        return new Integer(v);
    }

    public static final Long boxLong(long v) {
        return new Long(v);
    }

    public static final Short boxShort(short v) {
        return new Short(v);
    }
}

