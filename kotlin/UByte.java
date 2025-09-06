package kotlin;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.URangesKt;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000F\n\u0000\n\u0002\u0010\u0005\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0010\u000B\n\u0002\u0010\u0000\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u000E\b\u0087@\u0018\u0000 v2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001vB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0004\u0010\u0005J\u001B\u0010\b\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\f\u00F8\u0001\u0000\u00A2\u0006\u0004\b\n\u0010\u000BJ\u001B\u0010\f\u001A\u00020\r2\u0006\u0010\t\u001A\u00020\u0000H\u0097\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u000E\u0010\u000FJ\u001B\u0010\f\u001A\u00020\r2\u0006\u0010\t\u001A\u00020\u0010H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0011\u0010\u0012J\u001B\u0010\f\u001A\u00020\r2\u0006\u0010\t\u001A\u00020\u0013H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0014\u0010\u0015J\u001B\u0010\f\u001A\u00020\r2\u0006\u0010\t\u001A\u00020\u0016H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001A\u0010\u0005J\u001B\u0010\u001B\u001A\u00020\u00102\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001C\u0010\u000FJ\u001B\u0010\u001B\u001A\u00020\u00102\u0006\u0010\t\u001A\u00020\u0010H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001D\u0010\u0012J\u001B\u0010\u001B\u001A\u00020\u00132\u0006\u0010\t\u001A\u00020\u0013H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001E\u0010\u001FJ\u001B\u0010\u001B\u001A\u00020\u00102\u0006\u0010\t\u001A\u00020\u0016H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b \u0010\u0018J\u001A\u0010!\u001A\u00020\"2\b\u0010\t\u001A\u0004\u0018\u00010#H\u00D6\u0003\u00A2\u0006\u0004\b$\u0010%J\u001B\u0010&\u001A\u00020\u00102\u0006\u0010\t\u001A\u00020\u0000H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b\'\u0010\u000FJ\u001B\u0010&\u001A\u00020\u00102\u0006\u0010\t\u001A\u00020\u0010H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b(\u0010\u0012J\u001B\u0010&\u001A\u00020\u00132\u0006\u0010\t\u001A\u00020\u0013H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b)\u0010\u001FJ\u001B\u0010&\u001A\u00020\u00102\u0006\u0010\t\u001A\u00020\u0016H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b*\u0010\u0018J\u0010\u0010+\u001A\u00020\rH\u00D6\u0001\u00A2\u0006\u0004\b,\u0010-J\u0016\u0010.\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b/\u0010\u0005J\u0016\u00100\u001A\u00020\u0000H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b1\u0010\u0005J\u001B\u00102\u001A\u00020\u00102\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b3\u0010\u000FJ\u001B\u00102\u001A\u00020\u00102\u0006\u0010\t\u001A\u00020\u0010H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b4\u0010\u0012J\u001B\u00102\u001A\u00020\u00132\u0006\u0010\t\u001A\u00020\u0013H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b5\u0010\u001FJ\u001B\u00102\u001A\u00020\u00102\u0006\u0010\t\u001A\u00020\u0016H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b6\u0010\u0018J\u001B\u00107\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b8\u0010\u000BJ\u001B\u00107\u001A\u00020\u00102\u0006\u0010\t\u001A\u00020\u0010H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b9\u0010\u0012J\u001B\u00107\u001A\u00020\u00132\u0006\u0010\t\u001A\u00020\u0013H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b:\u0010\u001FJ\u001B\u00107\u001A\u00020\u00162\u0006\u0010\t\u001A\u00020\u0016H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b;\u0010<J\u001B\u0010=\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\f\u00F8\u0001\u0000\u00A2\u0006\u0004\b>\u0010\u000BJ\u001B\u0010?\u001A\u00020\u00102\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b@\u0010\u000FJ\u001B\u0010?\u001A\u00020\u00102\u0006\u0010\t\u001A\u00020\u0010H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bA\u0010\u0012J\u001B\u0010?\u001A\u00020\u00132\u0006\u0010\t\u001A\u00020\u0013H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bB\u0010\u001FJ\u001B\u0010?\u001A\u00020\u00102\u0006\u0010\t\u001A\u00020\u0016H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bC\u0010\u0018J\u001B\u0010D\u001A\u00020E2\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bF\u0010GJ\u001B\u0010H\u001A\u00020E2\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bI\u0010GJ\u001B\u0010J\u001A\u00020\u00102\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bK\u0010\u000FJ\u001B\u0010J\u001A\u00020\u00102\u0006\u0010\t\u001A\u00020\u0010H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bL\u0010\u0012J\u001B\u0010J\u001A\u00020\u00132\u0006\u0010\t\u001A\u00020\u0013H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bM\u0010\u001FJ\u001B\u0010J\u001A\u00020\u00102\u0006\u0010\t\u001A\u00020\u0016H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bN\u0010\u0018J\u001B\u0010O\u001A\u00020\u00102\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bP\u0010\u000FJ\u001B\u0010O\u001A\u00020\u00102\u0006\u0010\t\u001A\u00020\u0010H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bQ\u0010\u0012J\u001B\u0010O\u001A\u00020\u00132\u0006\u0010\t\u001A\u00020\u0013H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bR\u0010\u001FJ\u001B\u0010O\u001A\u00020\u00102\u0006\u0010\t\u001A\u00020\u0016H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bS\u0010\u0018J\u0010\u0010T\u001A\u00020\u0003H\u0087\b\u00A2\u0006\u0004\bU\u0010\u0005J\u0010\u0010V\u001A\u00020WH\u0087\b\u00A2\u0006\u0004\bX\u0010YJ\u0010\u0010Z\u001A\u00020[H\u0087\b\u00A2\u0006\u0004\b\\\u0010]J\u0010\u0010^\u001A\u00020\rH\u0087\b\u00A2\u0006\u0004\b_\u0010-J\u0010\u0010`\u001A\u00020aH\u0087\b\u00A2\u0006\u0004\bb\u0010cJ\u0010\u0010d\u001A\u00020eH\u0087\b\u00A2\u0006\u0004\bf\u0010gJ\u000F\u0010h\u001A\u00020iH\u0016\u00A2\u0006\u0004\bj\u0010kJ\u0016\u0010l\u001A\u00020\u0000H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bm\u0010\u0005J\u0016\u0010n\u001A\u00020\u0010H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bo\u0010-J\u0016\u0010p\u001A\u00020\u0013H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bq\u0010cJ\u0016\u0010r\u001A\u00020\u0016H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bs\u0010gJ\u001B\u0010t\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\f\u00F8\u0001\u0000\u00A2\u0006\u0004\bu\u0010\u000BR\u0016\u0010\u0002\u001A\u00020\u00038\u0000X\u0081\u0004\u00A2\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003\u00F8\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00A8\u0006w"}, d2 = {"Lkotlin/UByte;", "", "data", "", "constructor-impl", "(B)B", "getData$annotations", "()V", "and", "other", "and-7apg3OU", "(BB)B", "compareTo", "", "compareTo-7apg3OU", "(BB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(BI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(BJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(BS)I", "dec", "dec-w2LRezQ", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(BJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(BLjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "(B)I", "inc", "inc-w2LRezQ", "inv", "inv-w2LRezQ", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(BS)S", "or", "or-7apg3OU", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-7apg3OU", "(BB)Lkotlin/ranges/UIntRange;", "rangeUntil", "rangeUntil-7apg3OU", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "toByte-impl", "toDouble", "", "toDouble-impl", "(B)D", "toFloat", "", "toFloat-impl", "(B)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(B)J", "toShort", "", "toShort-impl", "(B)S", "toString", "", "toString-impl", "(B)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-7apg3OU", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
@JvmInline
public final class UByte implements Comparable {
    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001A\u00020\u0004X\u0086Tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001A\u00020\u0004X\u0086Tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u000E\u0010\u0007\u001A\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\bX\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\n"}, d2 = {"Lkotlin/UByte$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UByte;", "B", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion = null;
    public static final byte MAX_VALUE = -1;
    public static final byte MIN_VALUE = 0;
    public static final int SIZE_BITS = 8;
    public static final int SIZE_BYTES = 1;
    private final byte data;

    static {
        UByte.Companion = new Companion(null);
    }

    private UByte(byte b) {
        this.data = b;
    }

    private static final byte and-7apg3OU(byte b, byte b1) {
        return (byte)(b & b1);
    }

    public static final UByte box-impl(byte b) {
        return new UByte(b);
    }

    @Override
    public int compareTo(Object object0) {
        return Intrinsics.compare(this.unbox-impl() & 0xFF, ((UByte)object0).unbox-impl() & 0xFF);
    }

    private int compareTo-7apg3OU(byte b) {
        return Intrinsics.compare(this.unbox-impl() & 0xFF, b & 0xFF);
    }

    private static int compareTo-7apg3OU(byte b, byte b1) {
        return Intrinsics.compare(b & 0xFF, b1 & 0xFF);
    }

    private static final int compareTo-VKZWuLQ(byte b, long v) {
        return UByte..ExternalSyntheticBackport0.m(((long)b) & 0xFFL, v);
    }

    private static final int compareTo-WZ4Q5Ns(byte b, int v) {
        return UByte..ExternalSyntheticBackport0.m$2(b & 0xFF, v);
    }

    private static final int compareTo-xj2QHRw(byte b, short v) {
        return Intrinsics.compare(b & 0xFF, v & 0xFFFF);
    }

    public static byte constructor-impl(byte b) [...] // Inlined contents

    private static final byte dec-w2LRezQ(byte b) {
        return (byte)(b - 1);
    }

    private static final int div-7apg3OU(byte b, byte b1) {
        return (int)((((long)(b & 0xFF)) & 0xFFFFFFFFL) / (((long)(b1 & 0xFF)) & 0xFFFFFFFFL));
    }

    private static final long div-VKZWuLQ(byte b, long v) {
        return UByte..ExternalSyntheticBackport0.m$1(((long)b) & 0xFFL, v);
    }

    private static final int div-WZ4Q5Ns(byte b, int v) {
        return (int)((((long)(b & 0xFF)) & 0xFFFFFFFFL) / (((long)v) & 0xFFFFFFFFL));
    }

    private static final int div-xj2QHRw(byte b, short v) {
        return (int)((((long)(b & 0xFF)) & 0xFFFFFFFFL) / (((long)(v & 0xFFFF)) & 0xFFFFFFFFL));
    }

    @Override
    public boolean equals(Object object0) {
        return UByte.equals-impl(this.data, object0);
    }

    public static boolean equals-impl(byte b, Object object0) {
        return object0 instanceof UByte ? b == ((UByte)object0).unbox-impl() : false;
    }

    public static final boolean equals-impl0(byte b, byte b1) {
        return b == b1;
    }

    private static final int floorDiv-7apg3OU(byte b, byte b1) {
        return (int)((((long)(b & 0xFF)) & 0xFFFFFFFFL) / (((long)(b1 & 0xFF)) & 0xFFFFFFFFL));
    }

    private static final long floorDiv-VKZWuLQ(byte b, long v) {
        return UByte..ExternalSyntheticBackport0.m$1(((long)b) & 0xFFL, v);
    }

    private static final int floorDiv-WZ4Q5Ns(byte b, int v) {
        return (int)((((long)UInt.constructor-impl(b & 0xFF)) & 0xFFFFFFFFL) / (((long)v) & 0xFFFFFFFFL));
    }

    private static final int floorDiv-xj2QHRw(byte b, short v) {
        return (int)((((long)(b & 0xFF)) & 0xFFFFFFFFL) / (((long)(v & 0xFFFF)) & 0xFFFFFFFFL));
    }

    public static void getData$annotations() {
    }

    @Override
    public int hashCode() {
        return this.data;
    }

    public static int hashCode-impl(byte b) [...] // Inlined contents

    private static final byte inc-w2LRezQ(byte b) {
        return (byte)(b + 1);
    }

    private static final byte inv-w2LRezQ(byte b) {
        return (byte)(~b);
    }

    private static final int minus-7apg3OU(byte b, byte b1) {
        return (b & 0xFF) - (b1 & 0xFF);
    }

    private static final long minus-VKZWuLQ(byte b, long v) {
        return (((long)b) & 0xFFL) - v;
    }

    private static final int minus-WZ4Q5Ns(byte b, int v) {
        return (b & 0xFF) - v;
    }

    private static final int minus-xj2QHRw(byte b, short v) {
        return UInt.constructor-impl(UInt.constructor-impl(b & 0xFF) - UInt.constructor-impl(v & 0xFFFF));
    }

    private static final byte mod-7apg3OU(byte b, byte b1) {
        return UByte.constructor-impl(((byte)(((int)((((long)(b & 0xFF)) & 0xFFFFFFFFL) % (((long)(b1 & 0xFF)) & 0xFFFFFFFFL))))));
    }

    private static final long mod-VKZWuLQ(byte b, long v) {
        return UByte..ExternalSyntheticBackport0.m(((long)b) & 0xFFL, v);
    }

    private static final int mod-WZ4Q5Ns(byte b, int v) {
        return (int)((((long)(b & 0xFF)) & 0xFFFFFFFFL) % (((long)v) & 0xFFFFFFFFL));
    }

    private static final short mod-xj2QHRw(byte b, short v) {
        return UShort.constructor-impl(((short)(((int)((((long)(b & 0xFF)) & 0xFFFFFFFFL) % (((long)(v & 0xFFFF)) & 0xFFFFFFFFL))))));
    }

    private static final byte or-7apg3OU(byte b, byte b1) {
        return (byte)(b | b1);
    }

    private static final int plus-7apg3OU(byte b, byte b1) {
        return (b & 0xFF) + (b1 & 0xFF);
    }

    private static final long plus-VKZWuLQ(byte b, long v) {
        return (((long)b) & 0xFFL) + v;
    }

    private static final int plus-WZ4Q5Ns(byte b, int v) {
        return (b & 0xFF) + v;
    }

    private static final int plus-xj2QHRw(byte b, short v) {
        return (b & 0xFF) + (v & 0xFFFF);
    }

    private static final UIntRange rangeTo-7apg3OU(byte b, byte b1) {
        return new UIntRange(UInt.constructor-impl(b & 0xFF), UInt.constructor-impl(b1 & 0xFF), null);
    }

    private static final UIntRange rangeUntil-7apg3OU(byte b, byte b1) {
        return URangesKt.until-J1ME1BU(b & 0xFF, b1 & 0xFF);
    }

    private static final int rem-7apg3OU(byte b, byte b1) {
        return (int)((((long)(b & 0xFF)) & 0xFFFFFFFFL) % (((long)(b1 & 0xFF)) & 0xFFFFFFFFL));
    }

    private static final long rem-VKZWuLQ(byte b, long v) {
        return UByte..ExternalSyntheticBackport0.m(((long)b) & 0xFFL, v);
    }

    private static final int rem-WZ4Q5Ns(byte b, int v) {
        return (int)((((long)(b & 0xFF)) & 0xFFFFFFFFL) % (((long)v) & 0xFFFFFFFFL));
    }

    private static final int rem-xj2QHRw(byte b, short v) {
        return (int)((((long)(b & 0xFF)) & 0xFFFFFFFFL) % (((long)(v & 0xFFFF)) & 0xFFFFFFFFL));
    }

    private static final int times-7apg3OU(byte b, byte b1) {
        return UInt.constructor-impl(UInt.constructor-impl(b & 0xFF) * UInt.constructor-impl(b1 & 0xFF));
    }

    private static final long times-VKZWuLQ(byte b, long v) {
        return (((long)b) & 0xFFL) * v;
    }

    private static final int times-WZ4Q5Ns(byte b, int v) {
        return (b & 0xFF) * v;
    }

    private static final int times-xj2QHRw(byte b, short v) {
        return UInt.constructor-impl(UInt.constructor-impl(b & 0xFF) * UInt.constructor-impl(v & 0xFFFF));
    }

    private static final byte toByte-impl(byte b) {
        return b;
    }

    private static final double toDouble-impl(byte b) {
        return (double)(b & 0xFF);
    }

    private static final float toFloat-impl(byte b) {
        return (float)(b & 0xFF);
    }

    private static final int toInt-impl(byte b) {
        return b & 0xFF;
    }

    private static final long toLong-impl(byte b) {
        return ((long)b) & 0xFFL;
    }

    private static final short toShort-impl(byte b) {
        return (short)(((short)b) & 0xFF);
    }

    @Override
    public String toString() {
        return UByte.toString-impl(this.data);
    }

    public static String toString-impl(byte b) {
        return String.valueOf(b & 0xFF);
    }

    private static final byte toUByte-w2LRezQ(byte b) {
        return b;
    }

    private static final int toUInt-pVg5ArA(byte b) {
        return b & 0xFF;
    }

    private static final long toULong-s-VKNKU(byte b) {
        return ((long)b) & 0xFFL;
    }

    private static final short toUShort-Mh2AYeg(byte b) {
        return (short)(((short)b) & 0xFF);
    }

    public final byte unbox-impl() {
        return this.data;
    }

    private static final byte xor-7apg3OU(byte b, byte b1) {
        return (byte)(b ^ b1);
    }
}

