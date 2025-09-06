package kotlin;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.ULongRange;
import kotlin.ranges.URangesKt;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u000F\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000B\n\u0002\u0010\u0000\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u000E\b\u0087@\u0018\u0000 ~2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001~B\u0014\b\u0001\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0004\u0010\u0005J\u001B\u0010\b\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\f\u00F8\u0001\u0000\u00A2\u0006\u0004\b\n\u0010\u000BJ\u001B\u0010\f\u001A\u00020\r2\u0006\u0010\t\u001A\u00020\u000EH\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u000F\u0010\u0010J\u001B\u0010\f\u001A\u00020\r2\u0006\u0010\t\u001A\u00020\u0011H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0012\u0010\u0013J\u001B\u0010\f\u001A\u00020\r2\u0006\u0010\t\u001A\u00020\u0000H\u0097\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0014\u0010\u0015J\u001B\u0010\f\u001A\u00020\r2\u0006\u0010\t\u001A\u00020\u0016H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001A\u0010\u0005J\u001B\u0010\u001B\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u000EH\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001C\u0010\u001DJ\u001B\u0010\u001B\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0011H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001E\u0010\u001FJ\u001B\u0010\u001B\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b \u0010\u000BJ\u001B\u0010\u001B\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0016H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b!\u0010\"J\u001A\u0010#\u001A\u00020$2\b\u0010\t\u001A\u0004\u0018\u00010%H\u00D6\u0003\u00A2\u0006\u0004\b&\u0010\'J\u001B\u0010(\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u000EH\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b)\u0010\u001DJ\u001B\u0010(\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0011H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b*\u0010\u001FJ\u001B\u0010(\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b+\u0010\u000BJ\u001B\u0010(\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0016H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b,\u0010\"J\u0010\u0010-\u001A\u00020\rH\u00D6\u0001\u00A2\u0006\u0004\b.\u0010/J\u0016\u00100\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b1\u0010\u0005J\u0016\u00102\u001A\u00020\u0000H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b3\u0010\u0005J\u001B\u00104\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u000EH\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b5\u0010\u001DJ\u001B\u00104\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0011H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b6\u0010\u001FJ\u001B\u00104\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b7\u0010\u000BJ\u001B\u00104\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0016H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b8\u0010\"J\u001B\u00109\u001A\u00020\u000E2\u0006\u0010\t\u001A\u00020\u000EH\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b:\u0010;J\u001B\u00109\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u0011H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b<\u0010\u0013J\u001B\u00109\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b=\u0010\u000BJ\u001B\u00109\u001A\u00020\u00162\u0006\u0010\t\u001A\u00020\u0016H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b>\u0010?J\u001B\u0010@\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\f\u00F8\u0001\u0000\u00A2\u0006\u0004\bA\u0010\u000BJ\u001B\u0010B\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u000EH\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bC\u0010\u001DJ\u001B\u0010B\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0011H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bD\u0010\u001FJ\u001B\u0010B\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bE\u0010\u000BJ\u001B\u0010B\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0016H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bF\u0010\"J\u001B\u0010G\u001A\u00020H2\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bI\u0010JJ\u001B\u0010K\u001A\u00020H2\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bL\u0010JJ\u001B\u0010M\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u000EH\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bN\u0010\u001DJ\u001B\u0010M\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0011H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bO\u0010\u001FJ\u001B\u0010M\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bP\u0010\u000BJ\u001B\u0010M\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0016H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bQ\u0010\"J\u001E\u0010R\u001A\u00020\u00002\u0006\u0010S\u001A\u00020\rH\u0087\f\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bT\u0010\u001FJ\u001E\u0010U\u001A\u00020\u00002\u0006\u0010S\u001A\u00020\rH\u0087\f\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bV\u0010\u001FJ\u001B\u0010W\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u000EH\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bX\u0010\u001DJ\u001B\u0010W\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0011H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bY\u0010\u001FJ\u001B\u0010W\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bZ\u0010\u000BJ\u001B\u0010W\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0016H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b[\u0010\"J\u0010\u0010\\\u001A\u00020]H\u0087\b\u00A2\u0006\u0004\b^\u0010_J\u0010\u0010`\u001A\u00020aH\u0087\b\u00A2\u0006\u0004\bb\u0010cJ\u0010\u0010d\u001A\u00020eH\u0087\b\u00A2\u0006\u0004\bf\u0010gJ\u0010\u0010h\u001A\u00020\rH\u0087\b\u00A2\u0006\u0004\bi\u0010/J\u0010\u0010j\u001A\u00020\u0003H\u0087\b\u00A2\u0006\u0004\bk\u0010\u0005J\u0010\u0010l\u001A\u00020mH\u0087\b\u00A2\u0006\u0004\bn\u0010oJ\u000F\u0010p\u001A\u00020qH\u0016\u00A2\u0006\u0004\br\u0010sJ\u0016\u0010t\u001A\u00020\u000EH\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bu\u0010_J\u0016\u0010v\u001A\u00020\u0011H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bw\u0010/J\u0016\u0010x\u001A\u00020\u0000H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\by\u0010\u0005J\u0016\u0010z\u001A\u00020\u0016H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b{\u0010oJ\u001B\u0010|\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\f\u00F8\u0001\u0000\u00A2\u0006\u0004\b}\u0010\u000BR\u0016\u0010\u0002\u001A\u00020\u00038\u0000X\u0081\u0004\u00A2\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003\u00F8\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00A8\u0006\u007F"}, d2 = {"Lkotlin/ULong;", "", "data", "", "constructor-impl", "(J)J", "getData$annotations", "()V", "and", "other", "and-VKZWuLQ", "(JJ)J", "compareTo", "", "Lkotlin/UByte;", "compareTo-7apg3OU", "(JB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(JI)I", "compareTo-VKZWuLQ", "(JJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(JS)I", "dec", "dec-s-VKNKU", "div", "div-7apg3OU", "(JB)J", "div-WZ4Q5Ns", "(JI)J", "div-VKZWuLQ", "div-xj2QHRw", "(JS)J", "equals", "", "", "equals-impl", "(JLjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "(J)I", "inc", "inc-s-VKNKU", "inv", "inv-s-VKNKU", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "(JB)B", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(JS)S", "or", "or-VKZWuLQ", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/ULongRange;", "rangeTo-VKZWuLQ", "(JJ)Lkotlin/ranges/ULongRange;", "rangeUntil", "rangeUntil-VKZWuLQ", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-s-VKNKU", "shr", "shr-s-VKNKU", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(J)B", "toDouble", "", "toDouble-impl", "(J)D", "toFloat", "", "toFloat-impl", "(J)F", "toInt", "toInt-impl", "toLong", "toLong-impl", "toShort", "", "toShort-impl", "(J)S", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-VKZWuLQ", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
@JvmInline
public final class ULong implements Comparable {
    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001A\u00020\u0004X\u0086Tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001A\u00020\u0004X\u0086Tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u000E\u0010\u0007\u001A\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\bX\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\n"}, d2 = {"Lkotlin/ULong$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/ULong;", "J", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion = null;
    public static final long MAX_VALUE = -1L;
    public static final long MIN_VALUE = 0L;
    public static final int SIZE_BITS = 0x40;
    public static final int SIZE_BYTES = 8;
    private final long data;

    static {
        ULong.Companion = new Companion(null);
    }

    private ULong(long v) {
        this.data = v;
    }

    private static final long and-VKZWuLQ(long v, long v1) {
        return v & v1;
    }

    public static final ULong box-impl(long v) {
        return new ULong(v);
    }

    @Override
    public int compareTo(Object object0) {
        return UnsignedKt.ulongCompare(this.unbox-impl(), ((ULong)object0).unbox-impl());
    }

    private static final int compareTo-7apg3OU(long v, byte b) {
        return UByte..ExternalSyntheticBackport0.m(v, ((long)b) & 0xFFL);
    }

    private int compareTo-VKZWuLQ(long v) {
        return UnsignedKt.ulongCompare(this.unbox-impl(), v);
    }

    private static int compareTo-VKZWuLQ(long v, long v1) {
        return UnsignedKt.ulongCompare(v, v1);
    }

    private static final int compareTo-WZ4Q5Ns(long v, int v1) {
        return UByte..ExternalSyntheticBackport0.m(v, ((long)v1) & 0xFFFFFFFFL);
    }

    private static final int compareTo-xj2QHRw(long v, short v1) {
        return UByte..ExternalSyntheticBackport0.m(v, ((long)v1) & 0xFFFFL);
    }

    public static long constructor-impl(long v) [...] // Inlined contents

    private static final long dec-s-VKNKU(long v) {
        return v - 1L;
    }

    private static final long div-7apg3OU(long v, byte b) {
        return UByte..ExternalSyntheticBackport0.m$1(v, ((long)b) & 0xFFL);
    }

    private static final long div-VKZWuLQ(long v, long v1) {
        return UnsignedKt.ulongDivide-eb3DHEI(v, v1);
    }

    private static final long div-WZ4Q5Ns(long v, int v1) {
        return UByte..ExternalSyntheticBackport0.m$1(v, ((long)v1) & 0xFFFFFFFFL);
    }

    private static final long div-xj2QHRw(long v, short v1) {
        return UByte..ExternalSyntheticBackport0.m$1(v, ((long)v1) & 0xFFFFL);
    }

    @Override
    public boolean equals(Object object0) {
        return ULong.equals-impl(this.data, object0);
    }

    public static boolean equals-impl(long v, Object object0) {
        return object0 instanceof ULong ? v == ((ULong)object0).unbox-impl() : false;
    }

    public static final boolean equals-impl0(long v, long v1) {
        return v == v1;
    }

    private static final long floorDiv-7apg3OU(long v, byte b) {
        return UByte..ExternalSyntheticBackport0.m$1(v, ((long)b) & 0xFFL);
    }

    private static final long floorDiv-VKZWuLQ(long v, long v1) {
        return UByte..ExternalSyntheticBackport0.m$1(v, v1);
    }

    private static final long floorDiv-WZ4Q5Ns(long v, int v1) {
        return UByte..ExternalSyntheticBackport0.m$1(v, ((long)v1) & 0xFFFFFFFFL);
    }

    private static final long floorDiv-xj2QHRw(long v, short v1) {
        return UByte..ExternalSyntheticBackport0.m$1(v, ULong.constructor-impl(((long)v1) & 0xFFFFL));
    }

    public static void getData$annotations() {
    }

    @Override
    public int hashCode() {
        return ULong.hashCode-impl(this.data);
    }

    public static int hashCode-impl(long v) {
        return (int)(v ^ v >>> 0x20);
    }

    private static final long inc-s-VKNKU(long v) {
        return v + 1L;
    }

    private static final long inv-s-VKNKU(long v) {
        return ULong.constructor-impl(~v);
    }

    private static final long minus-7apg3OU(long v, byte b) {
        return ULong.constructor-impl(v - ULong.constructor-impl(((long)b) & 0xFFL));
    }

    private static final long minus-VKZWuLQ(long v, long v1) {
        return v - v1;
    }

    private static final long minus-WZ4Q5Ns(long v, int v1) {
        return v - (((long)v1) & 0xFFFFFFFFL);
    }

    private static final long minus-xj2QHRw(long v, short v1) {
        return v - (((long)v1) & 0xFFFFL);
    }

    private static final byte mod-7apg3OU(long v, byte b) {
        return UByte.constructor-impl(((byte)(((int)UByte..ExternalSyntheticBackport0.m(v, ((long)b) & 0xFFL)))));
    }

    private static final long mod-VKZWuLQ(long v, long v1) {
        return UByte..ExternalSyntheticBackport0.m(v, v1);
    }

    private static final int mod-WZ4Q5Ns(long v, int v1) {
        return UInt.constructor-impl(((int)UByte..ExternalSyntheticBackport0.m(v, ((long)v1) & 0xFFFFFFFFL)));
    }

    private static final short mod-xj2QHRw(long v, short v1) {
        return UShort.constructor-impl(((short)(((int)UByte..ExternalSyntheticBackport0.m(v, ((long)v1) & 0xFFFFL)))));
    }

    private static final long or-VKZWuLQ(long v, long v1) {
        return v | v1;
    }

    private static final long plus-7apg3OU(long v, byte b) {
        return ULong.constructor-impl(v + ULong.constructor-impl(((long)b) & 0xFFL));
    }

    private static final long plus-VKZWuLQ(long v, long v1) {
        return v + v1;
    }

    private static final long plus-WZ4Q5Ns(long v, int v1) {
        return v + (((long)v1) & 0xFFFFFFFFL);
    }

    private static final long plus-xj2QHRw(long v, short v1) {
        return ULong.constructor-impl(v + ULong.constructor-impl(((long)v1) & 0xFFFFL));
    }

    private static final ULongRange rangeTo-VKZWuLQ(long v, long v1) {
        return new ULongRange(v, v1, null);
    }

    private static final ULongRange rangeUntil-VKZWuLQ(long v, long v1) {
        return URangesKt.until-eb3DHEI(v, v1);
    }

    private static final long rem-7apg3OU(long v, byte b) {
        return UByte..ExternalSyntheticBackport0.m(v, ((long)b) & 0xFFL);
    }

    private static final long rem-VKZWuLQ(long v, long v1) {
        return UnsignedKt.ulongRemainder-eb3DHEI(v, v1);
    }

    private static final long rem-WZ4Q5Ns(long v, int v1) {
        return UByte..ExternalSyntheticBackport0.m(v, ((long)v1) & 0xFFFFFFFFL);
    }

    private static final long rem-xj2QHRw(long v, short v1) {
        return UByte..ExternalSyntheticBackport0.m(v, ULong.constructor-impl(((long)v1) & 0xFFFFL));
    }

    private static final long shl-s-VKNKU(long v, int v1) {
        return v << v1;
    }

    private static final long shr-s-VKNKU(long v, int v1) {
        return v >>> v1;
    }

    private static final long times-7apg3OU(long v, byte b) {
        return v * (((long)b) & 0xFFL);
    }

    private static final long times-VKZWuLQ(long v, long v1) {
        return v * v1;
    }

    private static final long times-WZ4Q5Ns(long v, int v1) {
        return v * (((long)v1) & 0xFFFFFFFFL);
    }

    private static final long times-xj2QHRw(long v, short v1) {
        return v * (((long)v1) & 0xFFFFL);
    }

    private static final byte toByte-impl(long v) {
        return (byte)(((int)v));
    }

    private static final double toDouble-impl(long v) {
        return ((double)(v >>> 11)) * 2048.0 + ((double)(v & 0x7FFL));
    }

    private static final float toFloat-impl(long v) {
        return (float)UnsignedKt.ulongToDouble(v);
    }

    private static final int toInt-impl(long v) {
        return (int)v;
    }

    private static final long toLong-impl(long v) {
        return v;
    }

    private static final short toShort-impl(long v) {
        return (short)(((int)v));
    }

    @Override
    public String toString() {
        return ULong.toString-impl(this.data);
    }

    public static String toString-impl(long v) {
        return UnsignedKt.ulongToString(v);
    }

    private static final byte toUByte-w2LRezQ(long v) {
        return (byte)(((int)v));
    }

    private static final int toUInt-pVg5ArA(long v) {
        return (int)v;
    }

    private static final long toULong-s-VKNKU(long v) {
        return v;
    }

    private static final short toUShort-Mh2AYeg(long v) {
        return (short)(((int)v));
    }

    public final long unbox-impl() {
        return this.data;
    }

    private static final long xor-VKZWuLQ(long v, long v1) {
        return v ^ v1;
    }
}

