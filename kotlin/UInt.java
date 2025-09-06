package kotlin;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.UIntRange;
import kotlin.ranges.URangesKt;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000F\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0010\u000B\n\u0002\u0010\u0000\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u000E\b\u0087@\u0018\u0000 {2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001{B\u0014\b\u0001\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0004\u0010\u0005J\u001B\u0010\b\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\f\u00F8\u0001\u0000\u00A2\u0006\u0004\b\n\u0010\u000BJ\u001B\u0010\f\u001A\u00020\u00032\u0006\u0010\t\u001A\u00020\rH\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u000E\u0010\u000FJ\u001B\u0010\f\u001A\u00020\u00032\u0006\u0010\t\u001A\u00020\u0000H\u0097\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0010\u0010\u000BJ\u001B\u0010\f\u001A\u00020\u00032\u0006\u0010\t\u001A\u00020\u0011H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0012\u0010\u0013J\u001B\u0010\f\u001A\u00020\u00032\u0006\u0010\t\u001A\u00020\u0014H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0015\u0010\u0016J\u0016\u0010\u0017\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0018\u0010\u0005J\u001B\u0010\u0019\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\rH\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001A\u0010\u000FJ\u001B\u0010\u0019\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001B\u0010\u000BJ\u001B\u0010\u0019\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u0011H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001C\u0010\u001DJ\u001B\u0010\u0019\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0014H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001E\u0010\u0016J\u001A\u0010\u001F\u001A\u00020 2\b\u0010\t\u001A\u0004\u0018\u00010!H\u00D6\u0003\u00A2\u0006\u0004\b\"\u0010#J\u001B\u0010$\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\rH\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b%\u0010\u000FJ\u001B\u0010$\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b&\u0010\u000BJ\u001B\u0010$\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u0011H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b\'\u0010\u001DJ\u001B\u0010$\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0014H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b(\u0010\u0016J\u0010\u0010)\u001A\u00020\u0003H\u00D6\u0001\u00A2\u0006\u0004\b*\u0010\u0005J\u0016\u0010+\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b,\u0010\u0005J\u0016\u0010-\u001A\u00020\u0000H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b.\u0010\u0005J\u001B\u0010/\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\rH\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b0\u0010\u000FJ\u001B\u0010/\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b1\u0010\u000BJ\u001B\u0010/\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u0011H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b2\u0010\u001DJ\u001B\u0010/\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0014H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b3\u0010\u0016J\u001B\u00104\u001A\u00020\r2\u0006\u0010\t\u001A\u00020\rH\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b5\u00106J\u001B\u00104\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b7\u0010\u000BJ\u001B\u00104\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u0011H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b8\u0010\u001DJ\u001B\u00104\u001A\u00020\u00142\u0006\u0010\t\u001A\u00020\u0014H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b9\u0010:J\u001B\u0010;\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\f\u00F8\u0001\u0000\u00A2\u0006\u0004\b<\u0010\u000BJ\u001B\u0010=\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\rH\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b>\u0010\u000FJ\u001B\u0010=\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b?\u0010\u000BJ\u001B\u0010=\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u0011H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b@\u0010\u001DJ\u001B\u0010=\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0014H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bA\u0010\u0016J\u001B\u0010B\u001A\u00020C2\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bD\u0010EJ\u001B\u0010F\u001A\u00020C2\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bG\u0010EJ\u001B\u0010H\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\rH\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bI\u0010\u000FJ\u001B\u0010H\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bJ\u0010\u000BJ\u001B\u0010H\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u0011H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bK\u0010\u001DJ\u001B\u0010H\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0014H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bL\u0010\u0016J\u001E\u0010M\u001A\u00020\u00002\u0006\u0010N\u001A\u00020\u0003H\u0087\f\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bO\u0010\u000BJ\u001E\u0010P\u001A\u00020\u00002\u0006\u0010N\u001A\u00020\u0003H\u0087\f\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bQ\u0010\u000BJ\u001B\u0010R\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\rH\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bS\u0010\u000FJ\u001B\u0010R\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bT\u0010\u000BJ\u001B\u0010R\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u0011H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bU\u0010\u001DJ\u001B\u0010R\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0014H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bV\u0010\u0016J\u0010\u0010W\u001A\u00020XH\u0087\b\u00A2\u0006\u0004\bY\u0010ZJ\u0010\u0010[\u001A\u00020\\H\u0087\b\u00A2\u0006\u0004\b]\u0010^J\u0010\u0010_\u001A\u00020`H\u0087\b\u00A2\u0006\u0004\ba\u0010bJ\u0010\u0010c\u001A\u00020\u0003H\u0087\b\u00A2\u0006\u0004\bd\u0010\u0005J\u0010\u0010e\u001A\u00020fH\u0087\b\u00A2\u0006\u0004\bg\u0010hJ\u0010\u0010i\u001A\u00020jH\u0087\b\u00A2\u0006\u0004\bk\u0010lJ\u000F\u0010m\u001A\u00020nH\u0016\u00A2\u0006\u0004\bo\u0010pJ\u0016\u0010q\u001A\u00020\rH\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\br\u0010ZJ\u0016\u0010s\u001A\u00020\u0000H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bt\u0010\u0005J\u0016\u0010u\u001A\u00020\u0011H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bv\u0010hJ\u0016\u0010w\u001A\u00020\u0014H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bx\u0010lJ\u001B\u0010y\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\f\u00F8\u0001\u0000\u00A2\u0006\u0004\bz\u0010\u000BR\u0016\u0010\u0002\u001A\u00020\u00038\u0000X\u0081\u0004\u00A2\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003\u00F8\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00A8\u0006|"}, d2 = {"Lkotlin/UInt;", "", "data", "", "constructor-impl", "(I)I", "getData$annotations", "()V", "and", "other", "and-WZ4Q5Ns", "(II)I", "compareTo", "Lkotlin/UByte;", "compareTo-7apg3OU", "(IB)I", "compareTo-WZ4Q5Ns", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(IJ)I", "Lkotlin/UShort;", "compareTo-xj2QHRw", "(IS)I", "dec", "dec-pVg5ArA", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(IJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(ILjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "inc", "inc-pVg5ArA", "inv", "inv-pVg5ArA", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "(IB)B", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "(IS)S", "or", "or-WZ4Q5Ns", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-WZ4Q5Ns", "(II)Lkotlin/ranges/UIntRange;", "rangeUntil", "rangeUntil-WZ4Q5Ns", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "shl", "bitCount", "shl-pVg5ArA", "shr", "shr-pVg5ArA", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(I)B", "toDouble", "", "toDouble-impl", "(I)D", "toFloat", "", "toFloat-impl", "(I)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(I)J", "toShort", "", "toShort-impl", "(I)S", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-WZ4Q5Ns", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
@JvmInline
public final class UInt implements Comparable {
    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001A\u00020\u0004X\u0086Tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001A\u00020\u0004X\u0086Tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u000E\u0010\u0007\u001A\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\bX\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\n"}, d2 = {"Lkotlin/UInt$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UInt;", "I", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion = null;
    public static final int MAX_VALUE = -1;
    public static final int MIN_VALUE = 0;
    public static final int SIZE_BITS = 0x20;
    public static final int SIZE_BYTES = 4;
    private final int data;

    static {
        UInt.Companion = new Companion(null);
    }

    private UInt(int v) {
        this.data = v;
    }

    private static final int and-WZ4Q5Ns(int v, int v1) {
        return v & v1;
    }

    public static final UInt box-impl(int v) {
        return new UInt(v);
    }

    @Override
    public int compareTo(Object object0) {
        return UnsignedKt.uintCompare(this.unbox-impl(), ((UInt)object0).unbox-impl());
    }

    private static final int compareTo-7apg3OU(int v, byte b) {
        return UByte..ExternalSyntheticBackport0.m$2(v, b & 0xFF);
    }

    private static final int compareTo-VKZWuLQ(int v, long v1) {
        return UByte..ExternalSyntheticBackport0.m(((long)v) & 0xFFFFFFFFL, v1);
    }

    private int compareTo-WZ4Q5Ns(int v) {
        return UnsignedKt.uintCompare(this.unbox-impl(), v);
    }

    private static int compareTo-WZ4Q5Ns(int v, int v1) {
        return UnsignedKt.uintCompare(v, v1);
    }

    private static final int compareTo-xj2QHRw(int v, short v1) {
        return UByte..ExternalSyntheticBackport0.m$2(v, UInt.constructor-impl(v1 & 0xFFFF));
    }

    public static int constructor-impl(int v) [...] // Inlined contents

    private static final int dec-pVg5ArA(int v) {
        return v - 1;
    }

    private static final int div-7apg3OU(int v, byte b) {
        return (int)((((long)v) & 0xFFFFFFFFL) / (((long)(b & 0xFF)) & 0xFFFFFFFFL));
    }

    private static final long div-VKZWuLQ(int v, long v1) {
        return UByte..ExternalSyntheticBackport0.m$1(((long)v) & 0xFFFFFFFFL, v1);
    }

    private static final int div-WZ4Q5Ns(int v, int v1) {
        return UnsignedKt.uintDivide-J1ME1BU(v, v1);
    }

    private static final int div-xj2QHRw(int v, short v1) {
        return UByte..ExternalSyntheticBackport0.m(v, v1 & 0xFFFF);
    }

    @Override
    public boolean equals(Object object0) {
        return UInt.equals-impl(this.data, object0);
    }

    public static boolean equals-impl(int v, Object object0) {
        return object0 instanceof UInt ? v == ((UInt)object0).unbox-impl() : false;
    }

    public static final boolean equals-impl0(int v, int v1) {
        return v == v1;
    }

    private static final int floorDiv-7apg3OU(int v, byte b) {
        return (int)((((long)v) & 0xFFFFFFFFL) / (((long)(b & 0xFF)) & 0xFFFFFFFFL));
    }

    private static final long floorDiv-VKZWuLQ(int v, long v1) {
        return UByte..ExternalSyntheticBackport0.m$1(((long)v) & 0xFFFFFFFFL, v1);
    }

    private static final int floorDiv-WZ4Q5Ns(int v, int v1) {
        return (int)((((long)v) & 0xFFFFFFFFL) / (((long)v1) & 0xFFFFFFFFL));
    }

    private static final int floorDiv-xj2QHRw(int v, short v1) {
        return (int)((((long)v) & 0xFFFFFFFFL) / (((long)(v1 & 0xFFFF)) & 0xFFFFFFFFL));
    }

    public static void getData$annotations() {
    }

    @Override
    public int hashCode() {
        return this.data;
    }

    public static int hashCode-impl(int v) [...] // Inlined contents

    private static final int inc-pVg5ArA(int v) {
        return v + 1;
    }

    private static final int inv-pVg5ArA(int v) {
        return ~v;
    }

    private static final int minus-7apg3OU(int v, byte b) {
        return v - (b & 0xFF);
    }

    private static final long minus-VKZWuLQ(int v, long v1) {
        return (((long)v) & 0xFFFFFFFFL) - v1;
    }

    private static final int minus-WZ4Q5Ns(int v, int v1) {
        return v - v1;
    }

    private static final int minus-xj2QHRw(int v, short v1) {
        return v - (v1 & 0xFFFF);
    }

    private static final byte mod-7apg3OU(int v, byte b) {
        return UByte.constructor-impl(((byte)(((int)((((long)v) & 0xFFFFFFFFL) % (((long)(b & 0xFF)) & 0xFFFFFFFFL))))));
    }

    private static final long mod-VKZWuLQ(int v, long v1) {
        return UByte..ExternalSyntheticBackport0.m(ULong.constructor-impl(((long)v) & 0xFFFFFFFFL), v1);
    }

    private static final int mod-WZ4Q5Ns(int v, int v1) {
        return UByte..ExternalSyntheticBackport0.m$1(v, v1);
    }

    private static final short mod-xj2QHRw(int v, short v1) {
        return UShort.constructor-impl(((short)(((int)((((long)v) & 0xFFFFFFFFL) % (((long)(v1 & 0xFFFF)) & 0xFFFFFFFFL))))));
    }

    private static final int or-WZ4Q5Ns(int v, int v1) {
        return v | v1;
    }

    private static final int plus-7apg3OU(int v, byte b) {
        return v + (b & 0xFF);
    }

    private static final long plus-VKZWuLQ(int v, long v1) {
        return (((long)v) & 0xFFFFFFFFL) + v1;
    }

    private static final int plus-WZ4Q5Ns(int v, int v1) {
        return UInt.constructor-impl(v + v1);
    }

    private static final int plus-xj2QHRw(int v, short v1) {
        return v + (v1 & 0xFFFF);
    }

    private static final UIntRange rangeTo-WZ4Q5Ns(int v, int v1) {
        return new UIntRange(v, v1, null);
    }

    private static final UIntRange rangeUntil-WZ4Q5Ns(int v, int v1) {
        return URangesKt.until-J1ME1BU(v, v1);
    }

    private static final int rem-7apg3OU(int v, byte b) {
        return (int)((((long)v) & 0xFFFFFFFFL) % (((long)(b & 0xFF)) & 0xFFFFFFFFL));
    }

    private static final long rem-VKZWuLQ(int v, long v1) {
        return UByte..ExternalSyntheticBackport0.m(((long)v) & 0xFFFFFFFFL, v1);
    }

    private static final int rem-WZ4Q5Ns(int v, int v1) {
        return UnsignedKt.uintRemainder-J1ME1BU(v, v1);
    }

    private static final int rem-xj2QHRw(int v, short v1) {
        return (int)((((long)v) & 0xFFFFFFFFL) % (((long)(v1 & 0xFFFF)) & 0xFFFFFFFFL));
    }

    private static final int shl-pVg5ArA(int v, int v1) {
        return UInt.constructor-impl(v << v1);
    }

    private static final int shr-pVg5ArA(int v, int v1) {
        return v >>> v1;
    }

    private static final int times-7apg3OU(int v, byte b) {
        return v * (b & 0xFF);
    }

    private static final long times-VKZWuLQ(int v, long v1) {
        return (((long)v) & 0xFFFFFFFFL) * v1;
    }

    private static final int times-WZ4Q5Ns(int v, int v1) {
        return v * v1;
    }

    private static final int times-xj2QHRw(int v, short v1) {
        return v * (v1 & 0xFFFF);
    }

    private static final byte toByte-impl(int v) {
        return (byte)v;
    }

    private static final double toDouble-impl(int v) {
        return ((double)(0x7FFFFFFF & v)) + ((double)(v >>> 0x1F << 30)) * 2.0;
    }

    private static final float toFloat-impl(int v) {
        return (float)UnsignedKt.uintToDouble(v);
    }

    private static final int toInt-impl(int v) {
        return v;
    }

    private static final long toLong-impl(int v) {
        return ((long)v) & 0xFFFFFFFFL;
    }

    private static final short toShort-impl(int v) {
        return (short)v;
    }

    @Override
    public String toString() {
        return UInt.toString-impl(this.data);
    }

    public static String toString-impl(int v) {
        return String.valueOf(((long)v) & 0xFFFFFFFFL);
    }

    private static final byte toUByte-w2LRezQ(int v) {
        return (byte)v;
    }

    private static final int toUInt-pVg5ArA(int v) {
        return v;
    }

    private static final long toULong-s-VKNKU(int v) {
        return ((long)v) & 0xFFFFFFFFL;
    }

    private static final short toUShort-Mh2AYeg(int v) {
        return (short)v;
    }

    public final int unbox-impl() {
        return this.data;
    }

    private static final int xor-WZ4Q5Ns(int v, int v1) {
        return v ^ v1;
    }
}

