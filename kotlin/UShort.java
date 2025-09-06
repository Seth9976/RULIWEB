package kotlin;

import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.UIntRange;
import kotlin.ranges.URangesKt;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u000F\n\u0000\n\u0002\u0010\n\n\u0002\b\t\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000B\n\u0002\u0010\u0000\n\u0002\b!\n\u0002\u0018\u0002\n\u0002\b\u000F\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000E\n\u0002\b\u000E\b\u0087@\u0018\u0000 v2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001vB\u0014\b\u0001\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0004\u0010\u0005J\u001B\u0010\b\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\f\u00F8\u0001\u0000\u00A2\u0006\u0004\b\n\u0010\u000BJ\u001B\u0010\f\u001A\u00020\r2\u0006\u0010\t\u001A\u00020\u000EH\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u000F\u0010\u0010J\u001B\u0010\f\u001A\u00020\r2\u0006\u0010\t\u001A\u00020\u0011H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0012\u0010\u0013J\u001B\u0010\f\u001A\u00020\r2\u0006\u0010\t\u001A\u00020\u0014H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0015\u0010\u0016J\u001B\u0010\f\u001A\u00020\r2\u0006\u0010\t\u001A\u00020\u0000H\u0097\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001A\u0010\u0005J\u001B\u0010\u001B\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u000EH\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001C\u0010\u0010J\u001B\u0010\u001B\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u0011H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001D\u0010\u0013J\u001B\u0010\u001B\u001A\u00020\u00142\u0006\u0010\t\u001A\u00020\u0014H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001E\u0010\u001FJ\u001B\u0010\u001B\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b \u0010\u0018J\u001A\u0010!\u001A\u00020\"2\b\u0010\t\u001A\u0004\u0018\u00010#H\u00D6\u0003\u00A2\u0006\u0004\b$\u0010%J\u001B\u0010&\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u000EH\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b\'\u0010\u0010J\u001B\u0010&\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u0011H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b(\u0010\u0013J\u001B\u0010&\u001A\u00020\u00142\u0006\u0010\t\u001A\u00020\u0014H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b)\u0010\u001FJ\u001B\u0010&\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u0000H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b*\u0010\u0018J\u0010\u0010+\u001A\u00020\rH\u00D6\u0001\u00A2\u0006\u0004\b,\u0010-J\u0016\u0010.\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b/\u0010\u0005J\u0016\u00100\u001A\u00020\u0000H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b1\u0010\u0005J\u001B\u00102\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u000EH\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b3\u0010\u0010J\u001B\u00102\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u0011H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b4\u0010\u0013J\u001B\u00102\u001A\u00020\u00142\u0006\u0010\t\u001A\u00020\u0014H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b5\u0010\u001FJ\u001B\u00102\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b6\u0010\u0018J\u001B\u00107\u001A\u00020\u000E2\u0006\u0010\t\u001A\u00020\u000EH\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b8\u00109J\u001B\u00107\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u0011H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b:\u0010\u0013J\u001B\u00107\u001A\u00020\u00142\u0006\u0010\t\u001A\u00020\u0014H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b;\u0010\u001FJ\u001B\u00107\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b<\u0010\u000BJ\u001B\u0010=\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\f\u00F8\u0001\u0000\u00A2\u0006\u0004\b>\u0010\u000BJ\u001B\u0010?\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u000EH\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\b@\u0010\u0010J\u001B\u0010?\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u0011H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bA\u0010\u0013J\u001B\u0010?\u001A\u00020\u00142\u0006\u0010\t\u001A\u00020\u0014H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bB\u0010\u001FJ\u001B\u0010?\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bC\u0010\u0018J\u001B\u0010D\u001A\u00020E2\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bF\u0010GJ\u001B\u0010H\u001A\u00020E2\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bI\u0010GJ\u001B\u0010J\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u000EH\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bK\u0010\u0010J\u001B\u0010J\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u0011H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bL\u0010\u0013J\u001B\u0010J\u001A\u00020\u00142\u0006\u0010\t\u001A\u00020\u0014H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bM\u0010\u001FJ\u001B\u0010J\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bN\u0010\u0018J\u001B\u0010O\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u000EH\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bP\u0010\u0010J\u001B\u0010O\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u0011H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bQ\u0010\u0013J\u001B\u0010O\u001A\u00020\u00142\u0006\u0010\t\u001A\u00020\u0014H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bR\u0010\u001FJ\u001B\u0010O\u001A\u00020\u00112\u0006\u0010\t\u001A\u00020\u0000H\u0087\n\u00F8\u0001\u0000\u00A2\u0006\u0004\bS\u0010\u0018J\u0010\u0010T\u001A\u00020UH\u0087\b\u00A2\u0006\u0004\bV\u0010WJ\u0010\u0010X\u001A\u00020YH\u0087\b\u00A2\u0006\u0004\bZ\u0010[J\u0010\u0010\\\u001A\u00020]H\u0087\b\u00A2\u0006\u0004\b^\u0010_J\u0010\u0010`\u001A\u00020\rH\u0087\b\u00A2\u0006\u0004\ba\u0010-J\u0010\u0010b\u001A\u00020cH\u0087\b\u00A2\u0006\u0004\bd\u0010eJ\u0010\u0010f\u001A\u00020\u0003H\u0087\b\u00A2\u0006\u0004\bg\u0010\u0005J\u000F\u0010h\u001A\u00020iH\u0016\u00A2\u0006\u0004\bj\u0010kJ\u0016\u0010l\u001A\u00020\u000EH\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bm\u0010WJ\u0016\u0010n\u001A\u00020\u0011H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bo\u0010-J\u0016\u0010p\u001A\u00020\u0014H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bq\u0010eJ\u0016\u0010r\u001A\u00020\u0000H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bs\u0010\u0005J\u001B\u0010t\u001A\u00020\u00002\u0006\u0010\t\u001A\u00020\u0000H\u0087\f\u00F8\u0001\u0000\u00A2\u0006\u0004\bu\u0010\u000BR\u0016\u0010\u0002\u001A\u00020\u00038\u0000X\u0081\u0004\u00A2\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002\u0092\u0001\u00020\u0003\u00F8\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00A8\u0006w"}, d2 = {"Lkotlin/UShort;", "", "data", "", "constructor-impl", "(S)S", "getData$annotations", "()V", "and", "other", "and-xj2QHRw", "(SS)S", "compareTo", "", "Lkotlin/UByte;", "compareTo-7apg3OU", "(SB)I", "Lkotlin/UInt;", "compareTo-WZ4Q5Ns", "(SI)I", "Lkotlin/ULong;", "compareTo-VKZWuLQ", "(SJ)I", "compareTo-xj2QHRw", "(SS)I", "dec", "dec-Mh2AYeg", "div", "div-7apg3OU", "div-WZ4Q5Ns", "div-VKZWuLQ", "(SJ)J", "div-xj2QHRw", "equals", "", "", "equals-impl", "(SLjava/lang/Object;)Z", "floorDiv", "floorDiv-7apg3OU", "floorDiv-WZ4Q5Ns", "floorDiv-VKZWuLQ", "floorDiv-xj2QHRw", "hashCode", "hashCode-impl", "(S)I", "inc", "inc-Mh2AYeg", "inv", "inv-Mh2AYeg", "minus", "minus-7apg3OU", "minus-WZ4Q5Ns", "minus-VKZWuLQ", "minus-xj2QHRw", "mod", "mod-7apg3OU", "(SB)B", "mod-WZ4Q5Ns", "mod-VKZWuLQ", "mod-xj2QHRw", "or", "or-xj2QHRw", "plus", "plus-7apg3OU", "plus-WZ4Q5Ns", "plus-VKZWuLQ", "plus-xj2QHRw", "rangeTo", "Lkotlin/ranges/UIntRange;", "rangeTo-xj2QHRw", "(SS)Lkotlin/ranges/UIntRange;", "rangeUntil", "rangeUntil-xj2QHRw", "rem", "rem-7apg3OU", "rem-WZ4Q5Ns", "rem-VKZWuLQ", "rem-xj2QHRw", "times", "times-7apg3OU", "times-WZ4Q5Ns", "times-VKZWuLQ", "times-xj2QHRw", "toByte", "", "toByte-impl", "(S)B", "toDouble", "", "toDouble-impl", "(S)D", "toFloat", "", "toFloat-impl", "(S)F", "toInt", "toInt-impl", "toLong", "", "toLong-impl", "(S)J", "toShort", "toShort-impl", "toString", "", "toString-impl", "(S)Ljava/lang/String;", "toUByte", "toUByte-w2LRezQ", "toUInt", "toUInt-pVg5ArA", "toULong", "toULong-s-VKNKU", "toUShort", "toUShort-Mh2AYeg", "xor", "xor-xj2QHRw", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
@JvmInline
public final class UShort implements Comparable {
    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001A\u00020\u0004X\u0086Tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u0016\u0010\u0006\u001A\u00020\u0004X\u0086Tø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R\u000E\u0010\u0007\u001A\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\t\u001A\u00020\bX\u0086T¢\u0006\u0002\n\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\n"}, d2 = {"Lkotlin/UShort$Companion;", "", "()V", "MAX_VALUE", "Lkotlin/UShort;", "S", "MIN_VALUE", "SIZE_BITS", "", "SIZE_BYTES", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion = null;
    public static final short MAX_VALUE = -1;
    public static final short MIN_VALUE = 0;
    public static final int SIZE_BITS = 16;
    public static final int SIZE_BYTES = 2;
    private final short data;

    static {
        UShort.Companion = new Companion(null);
    }

    private UShort(short v) {
        this.data = v;
    }

    private static final short and-xj2QHRw(short v, short v1) {
        return (short)(v & v1);
    }

    public static final UShort box-impl(short v) {
        return new UShort(v);
    }

    @Override
    public int compareTo(Object object0) {
        return Intrinsics.compare(this.unbox-impl() & 0xFFFF, ((UShort)object0).unbox-impl() & 0xFFFF);
    }

    private static final int compareTo-7apg3OU(short v, byte b) {
        return Intrinsics.compare(v & 0xFFFF, b & 0xFF);
    }

    private static final int compareTo-VKZWuLQ(short v, long v1) {
        return UByte..ExternalSyntheticBackport0.m(((long)v) & 0xFFFFL, v1);
    }

    private static final int compareTo-WZ4Q5Ns(short v, int v1) {
        return UByte..ExternalSyntheticBackport0.m$2(v & 0xFFFF, v1);
    }

    private int compareTo-xj2QHRw(short v) {
        return Intrinsics.compare(this.unbox-impl() & 0xFFFF, v & 0xFFFF);
    }

    private static int compareTo-xj2QHRw(short v, short v1) {
        return Intrinsics.compare(v & 0xFFFF, v1 & 0xFFFF);
    }

    public static short constructor-impl(short v) [...] // Inlined contents

    private static final short dec-Mh2AYeg(short v) {
        return (short)(v - 1);
    }

    private static final int div-7apg3OU(short v, byte b) {
        return (int)((((long)(v & 0xFFFF)) & 0xFFFFFFFFL) / (((long)(b & 0xFF)) & 0xFFFFFFFFL));
    }

    private static final long div-VKZWuLQ(short v, long v1) {
        return UByte..ExternalSyntheticBackport0.m$1(((long)v) & 0xFFFFL, v1);
    }

    private static final int div-WZ4Q5Ns(short v, int v1) {
        return UByte..ExternalSyntheticBackport0.m(v & 0xFFFF, v1);
    }

    private static final int div-xj2QHRw(short v, short v1) {
        return (int)((((long)(v & 0xFFFF)) & 0xFFFFFFFFL) / (((long)(v1 & 0xFFFF)) & 0xFFFFFFFFL));
    }

    @Override
    public boolean equals(Object object0) {
        return UShort.equals-impl(this.data, object0);
    }

    public static boolean equals-impl(short v, Object object0) {
        return object0 instanceof UShort ? v == ((UShort)object0).unbox-impl() : false;
    }

    public static final boolean equals-impl0(short v, short v1) {
        return v == v1;
    }

    private static final int floorDiv-7apg3OU(short v, byte b) {
        return (int)((((long)(v & 0xFFFF)) & 0xFFFFFFFFL) / (((long)(b & 0xFF)) & 0xFFFFFFFFL));
    }

    private static final long floorDiv-VKZWuLQ(short v, long v1) {
        return UByte..ExternalSyntheticBackport0.m$1(((long)v) & 0xFFFFL, v1);
    }

    private static final int floorDiv-WZ4Q5Ns(short v, int v1) {
        return (int)((((long)(v & 0xFFFF)) & 0xFFFFFFFFL) / (((long)v1) & 0xFFFFFFFFL));
    }

    private static final int floorDiv-xj2QHRw(short v, short v1) {
        return UByte..ExternalSyntheticBackport0.m(v & 0xFFFF, v1 & 0xFFFF);
    }

    public static void getData$annotations() {
    }

    @Override
    public int hashCode() {
        return this.data;
    }

    public static int hashCode-impl(short v) [...] // Inlined contents

    private static final short inc-Mh2AYeg(short v) {
        return (short)(v + 1);
    }

    private static final short inv-Mh2AYeg(short v) {
        return (short)(~v);
    }

    private static final int minus-7apg3OU(short v, byte b) {
        return (v & 0xFFFF) - (b & 0xFF);
    }

    private static final long minus-VKZWuLQ(short v, long v1) {
        return (((long)v) & 0xFFFFL) - v1;
    }

    private static final int minus-WZ4Q5Ns(short v, int v1) {
        return (v & 0xFFFF) - v1;
    }

    private static final int minus-xj2QHRw(short v, short v1) {
        return (v & 0xFFFF) - (v1 & 0xFFFF);
    }

    private static final byte mod-7apg3OU(short v, byte b) {
        return UByte.constructor-impl(((byte)(((int)((((long)(v & 0xFFFF)) & 0xFFFFFFFFL) % (((long)(b & 0xFF)) & 0xFFFFFFFFL))))));
    }

    private static final long mod-VKZWuLQ(short v, long v1) {
        return UByte..ExternalSyntheticBackport0.m(((long)v) & 0xFFFFL, v1);
    }

    private static final int mod-WZ4Q5Ns(short v, int v1) {
        return (int)((((long)(v & 0xFFFF)) & 0xFFFFFFFFL) % (((long)v1) & 0xFFFFFFFFL));
    }

    private static final short mod-xj2QHRw(short v, short v1) {
        return UShort.constructor-impl(((short)(((int)((((long)(v & 0xFFFF)) & 0xFFFFFFFFL) % (((long)(v1 & 0xFFFF)) & 0xFFFFFFFFL))))));
    }

    private static final short or-xj2QHRw(short v, short v1) {
        return (short)(v | v1);
    }

    private static final int plus-7apg3OU(short v, byte b) {
        return (v & 0xFFFF) + (b & 0xFF);
    }

    private static final long plus-VKZWuLQ(short v, long v1) {
        return (((long)v) & 0xFFFFL) + v1;
    }

    private static final int plus-WZ4Q5Ns(short v, int v1) {
        return (v & 0xFFFF) + v1;
    }

    private static final int plus-xj2QHRw(short v, short v1) {
        return (v & 0xFFFF) + (v1 & 0xFFFF);
    }

    private static final UIntRange rangeTo-xj2QHRw(short v, short v1) {
        return new UIntRange(UInt.constructor-impl(v & 0xFFFF), UInt.constructor-impl(v1 & 0xFFFF), null);
    }

    private static final UIntRange rangeUntil-xj2QHRw(short v, short v1) {
        return URangesKt.until-J1ME1BU(v & 0xFFFF, v1 & 0xFFFF);
    }

    private static final int rem-7apg3OU(short v, byte b) {
        return UByte..ExternalSyntheticBackport0.m$1(v & 0xFFFF, b & 0xFF);
    }

    private static final long rem-VKZWuLQ(short v, long v1) {
        return UByte..ExternalSyntheticBackport0.m(((long)v) & 0xFFFFL, v1);
    }

    private static final int rem-WZ4Q5Ns(short v, int v1) {
        return (int)((((long)(v & 0xFFFF)) & 0xFFFFFFFFL) % (((long)v1) & 0xFFFFFFFFL));
    }

    private static final int rem-xj2QHRw(short v, short v1) {
        return (int)((((long)(v & 0xFFFF)) & 0xFFFFFFFFL) % (((long)(v1 & 0xFFFF)) & 0xFFFFFFFFL));
    }

    private static final int times-7apg3OU(short v, byte b) {
        return UInt.constructor-impl(UInt.constructor-impl(v & 0xFFFF) * UInt.constructor-impl(b & 0xFF));
    }

    private static final long times-VKZWuLQ(short v, long v1) {
        return (((long)v) & 0xFFFFL) * v1;
    }

    private static final int times-WZ4Q5Ns(short v, int v1) {
        return (v & 0xFFFF) * v1;
    }

    private static final int times-xj2QHRw(short v, short v1) {
        return UInt.constructor-impl(UInt.constructor-impl(v & 0xFFFF) * UInt.constructor-impl(v1 & 0xFFFF));
    }

    private static final byte toByte-impl(short v) {
        return (byte)v;
    }

    private static final double toDouble-impl(short v) {
        return (double)(v & 0xFFFF);
    }

    private static final float toFloat-impl(short v) {
        return (float)(v & 0xFFFF);
    }

    private static final int toInt-impl(short v) {
        return v & 0xFFFF;
    }

    private static final long toLong-impl(short v) {
        return ((long)v) & 0xFFFFL;
    }

    private static final short toShort-impl(short v) {
        return v;
    }

    @Override
    public String toString() {
        return UShort.toString-impl(this.data);
    }

    public static String toString-impl(short v) {
        return String.valueOf(v & 0xFFFF);
    }

    private static final byte toUByte-w2LRezQ(short v) {
        return (byte)v;
    }

    private static final int toUInt-pVg5ArA(short v) {
        return v & 0xFFFF;
    }

    private static final long toULong-s-VKNKU(short v) {
        return ((long)v) & 0xFFFFL;
    }

    private static final short toUShort-Mh2AYeg(short v) {
        return v;
    }

    public final short unbox-impl() {
        return this.data;
    }

    private static final short xor-xj2QHRw(short v, short v1) {
        return (short)(v ^ v1);
    }
}

