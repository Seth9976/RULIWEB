package kotlin.collections.unsigned;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UInt;
import kotlin.UIntArray;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.UnsignedKt;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(d1 = {"\u0000h\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b \n\u0002\u0010\u000F\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001A\u001C\u0010\u0000\u001A\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0004\u0010\u0005\u001A\u001C\u0010\u0000\u001A\b\u0012\u0004\u0012\u00020\u00060\u0001*\u00020\u0007H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\b\u0010\t\u001A\u001C\u0010\u0000\u001A\b\u0012\u0004\u0012\u00020\n0\u0001*\u00020\u000BH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\f\u0010\r\u001A\u001C\u0010\u0000\u001A\b\u0012\u0004\u0012\u00020\u000E0\u0001*\u00020\u000FH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0010\u0010\u0011\u001A2\u0010\u0012\u001A\u00020\u0013*\u00020\u00032\u0006\u0010\u0014\u001A\u00020\u00022\b\b\u0002\u0010\u0015\u001A\u00020\u00132\b\b\u0002\u0010\u0016\u001A\u00020\u0013H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0017\u0010\u0018\u001A2\u0010\u0012\u001A\u00020\u0013*\u00020\u00072\u0006\u0010\u0014\u001A\u00020\u00062\b\b\u0002\u0010\u0015\u001A\u00020\u00132\b\b\u0002\u0010\u0016\u001A\u00020\u0013H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0019\u0010\u001A\u001A2\u0010\u0012\u001A\u00020\u0013*\u00020\u000B2\u0006\u0010\u0014\u001A\u00020\n2\b\b\u0002\u0010\u0015\u001A\u00020\u00132\b\b\u0002\u0010\u0016\u001A\u00020\u0013H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001B\u0010\u001C\u001A2\u0010\u0012\u001A\u00020\u0013*\u00020\u000F2\u0006\u0010\u0014\u001A\u00020\u000E2\b\b\u0002\u0010\u0015\u001A\u00020\u00132\b\b\u0002\u0010\u0016\u001A\u00020\u0013H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u001D\u0010\u001E\u001A\u001F\u0010\u001F\u001A\u00020\u0002*\u00020\u00032\u0006\u0010 \u001A\u00020\u0013H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b!\u0010\"\u001A\u001F\u0010\u001F\u001A\u00020\u0006*\u00020\u00072\u0006\u0010 \u001A\u00020\u0013H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b#\u0010$\u001A\u001F\u0010\u001F\u001A\u00020\n*\u00020\u000B2\u0006\u0010 \u001A\u00020\u0013H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b%\u0010&\u001A\u001F\u0010\u001F\u001A\u00020\u000E*\u00020\u000F2\u0006\u0010 \u001A\u00020\u0013H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0004\b\'\u0010(\u001A\u0018\u0010)\u001A\u0004\u0018\u00010\u0002*\u00020\u0003H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b*\u0010+\u001A\u0018\u0010)\u001A\u0004\u0018\u00010\u0006*\u00020\u0007H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b,\u0010-\u001A\u0018\u0010)\u001A\u0004\u0018\u00010\n*\u00020\u000BH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b.\u0010/\u001A\u0018\u0010)\u001A\u0004\u0018\u00010\u000E*\u00020\u000FH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\b0\u00101\u001A@\u00102\u001A\u0004\u0018\u00010\u0002\"\u000E\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u00032\u0012\u00105\u001A\u000E\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H306H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b7\u00108\u001A@\u00102\u001A\u0004\u0018\u00010\u0006\"\u000E\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u00072\u0012\u00105\u001A\u000E\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H306H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b9\u0010:\u001A@\u00102\u001A\u0004\u0018\u00010\n\"\u000E\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u000B2\u0012\u00105\u001A\u000E\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u0002H306H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b;\u0010<\u001A@\u00102\u001A\u0004\u0018\u00010\u000E\"\u000E\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u000F2\u0012\u00105\u001A\u000E\u0012\u0004\u0012\u00020\u000E\u0012\u0004\u0012\u0002H306H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b=\u0010>\u001A4\u0010?\u001A\u0004\u0018\u00010\u0002*\u00020\u00032\u001A\u0010@\u001A\u0016\u0012\u0006\b\u0000\u0012\u00020\u00020Aj\n\u0012\u0006\b\u0000\u0012\u00020\u0002`BH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\bC\u0010D\u001A4\u0010?\u001A\u0004\u0018\u00010\u0006*\u00020\u00072\u001A\u0010@\u001A\u0016\u0012\u0006\b\u0000\u0012\u00020\u00060Aj\n\u0012\u0006\b\u0000\u0012\u00020\u0006`BH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\bE\u0010F\u001A4\u0010?\u001A\u0004\u0018\u00010\n*\u00020\u000B2\u001A\u0010@\u001A\u0016\u0012\u0006\b\u0000\u0012\u00020\n0Aj\n\u0012\u0006\b\u0000\u0012\u00020\n`BH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\bG\u0010H\u001A4\u0010?\u001A\u0004\u0018\u00010\u000E*\u00020\u000F2\u001A\u0010@\u001A\u0016\u0012\u0006\b\u0000\u0012\u00020\u000E0Aj\n\u0012\u0006\b\u0000\u0012\u00020\u000E`BH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\bI\u0010J\u001A\u0018\u0010K\u001A\u0004\u0018\u00010\u0002*\u00020\u0003H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\bL\u0010+\u001A\u0018\u0010K\u001A\u0004\u0018\u00010\u0006*\u00020\u0007H\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\bM\u0010-\u001A\u0018\u0010K\u001A\u0004\u0018\u00010\n*\u00020\u000BH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\bN\u0010/\u001A\u0018\u0010K\u001A\u0004\u0018\u00010\u000E*\u00020\u000FH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\bO\u00101\u001A@\u0010P\u001A\u0004\u0018\u00010\u0002\"\u000E\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u00032\u0012\u00105\u001A\u000E\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H306H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bQ\u00108\u001A@\u0010P\u001A\u0004\u0018\u00010\u0006\"\u000E\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u00072\u0012\u00105\u001A\u000E\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H306H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bR\u0010:\u001A@\u0010P\u001A\u0004\u0018\u00010\n\"\u000E\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u000B2\u0012\u00105\u001A\u000E\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u0002H306H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bS\u0010<\u001A@\u0010P\u001A\u0004\u0018\u00010\u000E\"\u000E\b\u0000\u00103*\b\u0012\u0004\u0012\u0002H304*\u00020\u000F2\u0012\u00105\u001A\u000E\u0012\u0004\u0012\u00020\u000E\u0012\u0004\u0012\u0002H306H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bT\u0010>\u001A4\u0010U\u001A\u0004\u0018\u00010\u0002*\u00020\u00032\u001A\u0010@\u001A\u0016\u0012\u0006\b\u0000\u0012\u00020\u00020Aj\n\u0012\u0006\b\u0000\u0012\u00020\u0002`BH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\bV\u0010D\u001A4\u0010U\u001A\u0004\u0018\u00010\u0006*\u00020\u00072\u001A\u0010@\u001A\u0016\u0012\u0006\b\u0000\u0012\u00020\u00060Aj\n\u0012\u0006\b\u0000\u0012\u00020\u0006`BH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\bW\u0010F\u001A4\u0010U\u001A\u0004\u0018\u00010\n*\u00020\u000B2\u001A\u0010@\u001A\u0016\u0012\u0006\b\u0000\u0012\u00020\n0Aj\n\u0012\u0006\b\u0000\u0012\u00020\n`BH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\bX\u0010H\u001A4\u0010U\u001A\u0004\u0018\u00010\u000E*\u00020\u000F2\u001A\u0010@\u001A\u0016\u0012\u0006\b\u0000\u0012\u00020\u000E0Aj\n\u0012\u0006\b\u0000\u0012\u00020\u000E`BH\u0007\u00F8\u0001\u0000\u00A2\u0006\u0004\bY\u0010J\u001A.\u0010Z\u001A\u00020[*\u00020\u00032\u0012\u00105\u001A\u000E\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020[06H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b\\\u0010]\u001A.\u0010Z\u001A\u00020^*\u00020\u00032\u0012\u00105\u001A\u000E\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020^06H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b_\u0010`\u001A.\u0010Z\u001A\u00020[*\u00020\u00072\u0012\u00105\u001A\u000E\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020[06H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b\\\u0010a\u001A.\u0010Z\u001A\u00020^*\u00020\u00072\u0012\u00105\u001A\u000E\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020^06H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b_\u0010b\u001A.\u0010Z\u001A\u00020[*\u00020\u000B2\u0012\u00105\u001A\u000E\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020[06H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b\\\u0010c\u001A.\u0010Z\u001A\u00020^*\u00020\u000B2\u0012\u00105\u001A\u000E\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020^06H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b_\u0010d\u001A.\u0010Z\u001A\u00020[*\u00020\u000F2\u0012\u00105\u001A\u000E\u0012\u0004\u0012\u00020\u000E\u0012\u0004\u0012\u00020[06H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b\\\u0010e\u001A.\u0010Z\u001A\u00020^*\u00020\u000F2\u0012\u00105\u001A\u000E\u0012\u0004\u0012\u00020\u000E\u0012\u0004\u0012\u00020^06H\u0087\b\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b_\u0010f\u0082\u0002\u000B\n\u0002\b\u0019\n\u0005\b\u009920\u0001\u00A8\u0006g"}, d2 = {"asList", "", "Lkotlin/UByte;", "Lkotlin/UByteArray;", "asList-GBYM_sE", "([B)Ljava/util/List;", "Lkotlin/UInt;", "Lkotlin/UIntArray;", "asList--ajY-9A", "([I)Ljava/util/List;", "Lkotlin/ULong;", "Lkotlin/ULongArray;", "asList-QwZRm1k", "([J)Ljava/util/List;", "Lkotlin/UShort;", "Lkotlin/UShortArray;", "asList-rL5Bavg", "([S)Ljava/util/List;", "binarySearch", "", "element", "fromIndex", "toIndex", "binarySearch-WpHrYlw", "([BBII)I", "binarySearch-2fe2U9s", "([IIII)I", "binarySearch-K6DWlUc", "([JJII)I", "binarySearch-EtDCXyQ", "([SSII)I", "elementAt", "index", "elementAt-PpDY95g", "([BI)B", "elementAt-qFRl0hI", "([II)I", "elementAt-r7IrZao", "([JI)J", "elementAt-nggk6HY", "([SI)S", "max", "max-GBYM_sE", "([B)Lkotlin/UByte;", "max--ajY-9A", "([I)Lkotlin/UInt;", "max-QwZRm1k", "([J)Lkotlin/ULong;", "max-rL5Bavg", "([S)Lkotlin/UShort;", "maxBy", "R", "", "selector", "Lkotlin/Function1;", "maxBy-JOV_ifY", "([BLkotlin/jvm/functions/Function1;)Lkotlin/UByte;", "maxBy-jgv0xPQ", "([ILkotlin/jvm/functions/Function1;)Lkotlin/UInt;", "maxBy-MShoTSo", "([JLkotlin/jvm/functions/Function1;)Lkotlin/ULong;", "maxBy-xTcfx_M", "([SLkotlin/jvm/functions/Function1;)Lkotlin/UShort;", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "maxWith-XMRcp5o", "([BLjava/util/Comparator;)Lkotlin/UByte;", "maxWith-YmdZ_VM", "([ILjava/util/Comparator;)Lkotlin/UInt;", "maxWith-zrEWJaI", "([JLjava/util/Comparator;)Lkotlin/ULong;", "maxWith-eOHTfZs", "([SLjava/util/Comparator;)Lkotlin/UShort;", "min", "min-GBYM_sE", "min--ajY-9A", "min-QwZRm1k", "min-rL5Bavg", "minBy", "minBy-JOV_ifY", "minBy-jgv0xPQ", "minBy-MShoTSo", "minBy-xTcfx_M", "minWith", "minWith-XMRcp5o", "minWith-YmdZ_VM", "minWith-zrEWJaI", "minWith-eOHTfZs", "sumOf", "Ljava/math/BigDecimal;", "sumOfBigDecimal", "([BLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "Ljava/math/BigInteger;", "sumOfBigInteger", "([BLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([ILkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([ILkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([JLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([JLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "([SLkotlin/jvm/functions/Function1;)Ljava/math/BigDecimal;", "([SLkotlin/jvm/functions/Function1;)Ljava/math/BigInteger;", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, pn = "kotlin.collections", xi = 49, xs = "kotlin/collections/unsigned/UArraysKt")
class UArraysKt___UArraysJvmKt {
    public static final List asList--ajY-9A(int[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$asList");
        return new RandomAccess() {
            @Override  // kotlin.collections.AbstractCollection
            public final boolean contains(Object object0) {
                return object0 instanceof UInt ? this.contains-WZ4Q5Ns(((UInt)object0).unbox-impl()) : false;
            }

            public boolean contains-WZ4Q5Ns(int v) {
                return UIntArray.contains-WZ4Q5Ns(arr_v, v);
            }

            @Override  // kotlin.collections.AbstractList
            public Object get(int v) {
                return UInt.box-impl(this.get-pVg5ArA(v));
            }

            public int get-pVg5ArA(int v) {
                return UIntArray.get-pVg5ArA(arr_v, v);
            }

            @Override  // kotlin.collections.AbstractList
            public int getSize() {
                return UIntArray.getSize-impl(arr_v);
            }

            @Override  // kotlin.collections.AbstractList
            public final int indexOf(Object object0) {
                return object0 instanceof UInt ? this.indexOf-WZ4Q5Ns(((UInt)object0).unbox-impl()) : -1;
            }

            public int indexOf-WZ4Q5Ns(int v) {
                return ArraysKt.indexOf(arr_v, v);
            }

            @Override  // kotlin.collections.AbstractCollection
            public boolean isEmpty() {
                return UIntArray.isEmpty-impl(arr_v);
            }

            @Override  // kotlin.collections.AbstractList
            public final int lastIndexOf(Object object0) {
                return object0 instanceof UInt ? this.lastIndexOf-WZ4Q5Ns(((UInt)object0).unbox-impl()) : -1;
            }

            public int lastIndexOf-WZ4Q5Ns(int v) {
                return ArraysKt.lastIndexOf(arr_v, v);
            }
        };
    }

    public static final List asList-GBYM_sE(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "$this$asList");
        return new RandomAccess() {
            @Override  // kotlin.collections.AbstractCollection
            public final boolean contains(Object object0) {
                return object0 instanceof UByte ? this.contains-7apg3OU(((UByte)object0).unbox-impl()) : false;
            }

            public boolean contains-7apg3OU(byte b) {
                return UByteArray.contains-7apg3OU(arr_b, b);
            }

            @Override  // kotlin.collections.AbstractList
            public Object get(int v) {
                return UByte.box-impl(this.get-w2LRezQ(v));
            }

            public byte get-w2LRezQ(int v) {
                return UByteArray.get-w2LRezQ(arr_b, v);
            }

            @Override  // kotlin.collections.AbstractList
            public int getSize() {
                return UByteArray.getSize-impl(arr_b);
            }

            @Override  // kotlin.collections.AbstractList
            public final int indexOf(Object object0) {
                return object0 instanceof UByte ? this.indexOf-7apg3OU(((UByte)object0).unbox-impl()) : -1;
            }

            public int indexOf-7apg3OU(byte b) {
                return ArraysKt.indexOf(arr_b, b);
            }

            @Override  // kotlin.collections.AbstractCollection
            public boolean isEmpty() {
                return UByteArray.isEmpty-impl(arr_b);
            }

            @Override  // kotlin.collections.AbstractList
            public final int lastIndexOf(Object object0) {
                return object0 instanceof UByte ? this.lastIndexOf-7apg3OU(((UByte)object0).unbox-impl()) : -1;
            }

            public int lastIndexOf-7apg3OU(byte b) {
                return ArraysKt.lastIndexOf(arr_b, b);
            }
        };
    }

    public static final List asList-QwZRm1k(long[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$asList");
        return new RandomAccess() {
            @Override  // kotlin.collections.AbstractCollection
            public final boolean contains(Object object0) {
                return object0 instanceof ULong ? this.contains-VKZWuLQ(((ULong)object0).unbox-impl()) : false;
            }

            public boolean contains-VKZWuLQ(long v) {
                return ULongArray.contains-VKZWuLQ(arr_v, v);
            }

            @Override  // kotlin.collections.AbstractList
            public Object get(int v) {
                return ULong.box-impl(this.get-s-VKNKU(v));
            }

            public long get-s-VKNKU(int v) {
                return ULongArray.get-s-VKNKU(arr_v, v);
            }

            @Override  // kotlin.collections.AbstractList
            public int getSize() {
                return ULongArray.getSize-impl(arr_v);
            }

            @Override  // kotlin.collections.AbstractList
            public final int indexOf(Object object0) {
                return object0 instanceof ULong ? this.indexOf-VKZWuLQ(((ULong)object0).unbox-impl()) : -1;
            }

            public int indexOf-VKZWuLQ(long v) {
                return ArraysKt.indexOf(arr_v, v);
            }

            @Override  // kotlin.collections.AbstractCollection
            public boolean isEmpty() {
                return ULongArray.isEmpty-impl(arr_v);
            }

            @Override  // kotlin.collections.AbstractList
            public final int lastIndexOf(Object object0) {
                return object0 instanceof ULong ? this.lastIndexOf-VKZWuLQ(((ULong)object0).unbox-impl()) : -1;
            }

            public int lastIndexOf-VKZWuLQ(long v) {
                return ArraysKt.lastIndexOf(arr_v, v);
            }
        };
    }

    public static final List asList-rL5Bavg(short[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$asList");
        return new RandomAccess() {
            @Override  // kotlin.collections.AbstractCollection
            public final boolean contains(Object object0) {
                return object0 instanceof UShort ? this.contains-xj2QHRw(((UShort)object0).unbox-impl()) : false;
            }

            public boolean contains-xj2QHRw(short v) {
                return UShortArray.contains-xj2QHRw(arr_v, v);
            }

            @Override  // kotlin.collections.AbstractList
            public Object get(int v) {
                return UShort.box-impl(this.get-Mh2AYeg(v));
            }

            public short get-Mh2AYeg(int v) {
                return UShortArray.get-Mh2AYeg(arr_v, v);
            }

            @Override  // kotlin.collections.AbstractList
            public int getSize() {
                return UShortArray.getSize-impl(arr_v);
            }

            @Override  // kotlin.collections.AbstractList
            public final int indexOf(Object object0) {
                return object0 instanceof UShort ? this.indexOf-xj2QHRw(((UShort)object0).unbox-impl()) : -1;
            }

            public int indexOf-xj2QHRw(short v) {
                return ArraysKt.indexOf(arr_v, v);
            }

            @Override  // kotlin.collections.AbstractCollection
            public boolean isEmpty() {
                return UShortArray.isEmpty-impl(arr_v);
            }

            @Override  // kotlin.collections.AbstractList
            public final int lastIndexOf(Object object0) {
                return object0 instanceof UShort ? this.lastIndexOf-xj2QHRw(((UShort)object0).unbox-impl()) : -1;
            }

            public int lastIndexOf-xj2QHRw(short v) {
                return ArraysKt.lastIndexOf(arr_v, v);
            }
        };
    }

    public static final int binarySearch-2fe2U9s(int[] arr_v, int v, int v1, int v2) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$binarySearch");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(v1, v2, UIntArray.getSize-impl(arr_v));
        int v3 = v2 - 1;
        while(v1 <= v3) {
            int v4 = v1 + v3 >>> 1;
            int v5 = UnsignedKt.uintCompare(arr_v[v4], v);
            if(v5 < 0) {
                v1 = v4 + 1;
                continue;
            }
            if(v5 > 0) {
                v3 = v4 - 1;
                continue;
            }
            return v4;
        }
        return -(v1 + 1);
    }

    public static int binarySearch-2fe2U9s$default(int[] arr_v, int v, int v1, int v2, int v3, Object object0) {
        if((v3 & 2) != 0) {
            v1 = 0;
        }
        if((v3 & 4) != 0) {
            v2 = UIntArray.getSize-impl(arr_v);
        }
        return UArraysKt.binarySearch-2fe2U9s(arr_v, v, v1, v2);
    }

    public static final int binarySearch-EtDCXyQ(short[] arr_v, short v, int v1, int v2) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$binarySearch");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(v1, v2, UShortArray.getSize-impl(arr_v));
        int v3 = v2 - 1;
        while(v1 <= v3) {
            int v4 = v1 + v3 >>> 1;
            int v5 = UnsignedKt.uintCompare(arr_v[v4], v & 0xFFFF);
            if(v5 < 0) {
                v1 = v4 + 1;
                continue;
            }
            if(v5 > 0) {
                v3 = v4 - 1;
                continue;
            }
            return v4;
        }
        return -(v1 + 1);
    }

    public static int binarySearch-EtDCXyQ$default(short[] arr_v, short v, int v1, int v2, int v3, Object object0) {
        if((v3 & 2) != 0) {
            v1 = 0;
        }
        if((v3 & 4) != 0) {
            v2 = UShortArray.getSize-impl(arr_v);
        }
        return UArraysKt.binarySearch-EtDCXyQ(arr_v, v, v1, v2);
    }

    public static final int binarySearch-K6DWlUc(long[] arr_v, long v, int v1, int v2) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$binarySearch");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(v1, v2, ULongArray.getSize-impl(arr_v));
        int v3 = v2 - 1;
        while(v1 <= v3) {
            int v4 = v1 + v3 >>> 1;
            int v5 = UnsignedKt.ulongCompare(arr_v[v4], v);
            if(v5 < 0) {
                v1 = v4 + 1;
                continue;
            }
            if(v5 > 0) {
                v3 = v4 - 1;
                continue;
            }
            return v4;
        }
        return -(v1 + 1);
    }

    public static int binarySearch-K6DWlUc$default(long[] arr_v, long v, int v1, int v2, int v3, Object object0) {
        if((v3 & 2) != 0) {
            v1 = 0;
        }
        if((v3 & 4) != 0) {
            v2 = ULongArray.getSize-impl(arr_v);
        }
        return UArraysKt.binarySearch-K6DWlUc(arr_v, v, v1, v2);
    }

    public static final int binarySearch-WpHrYlw(byte[] arr_b, byte b, int v, int v1) {
        Intrinsics.checkNotNullParameter(arr_b, "$this$binarySearch");
        AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(v, v1, UByteArray.getSize-impl(arr_b));
        int v2 = v1 - 1;
        while(v <= v2) {
            int v3 = v + v2 >>> 1;
            int v4 = UnsignedKt.uintCompare(arr_b[v3], b & 0xFF);
            if(v4 < 0) {
                v = v3 + 1;
                continue;
            }
            if(v4 > 0) {
                v2 = v3 - 1;
                continue;
            }
            return v3;
        }
        return -(v + 1);
    }

    public static int binarySearch-WpHrYlw$default(byte[] arr_b, byte b, int v, int v1, int v2, Object object0) {
        if((v2 & 2) != 0) {
            v = 0;
        }
        if((v2 & 4) != 0) {
            v1 = UByteArray.getSize-impl(arr_b);
        }
        return UArraysKt.binarySearch-WpHrYlw(arr_b, b, v, v1);
    }

    private static final byte elementAt-PpDY95g(byte[] arr_b, int v) {
        Intrinsics.checkNotNullParameter(arr_b, "$this$elementAt");
        return UByteArray.get-w2LRezQ(arr_b, v);
    }

    private static final short elementAt-nggk6HY(short[] arr_v, int v) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$elementAt");
        return UShortArray.get-Mh2AYeg(arr_v, v);
    }

    private static final int elementAt-qFRl0hI(int[] arr_v, int v) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$elementAt");
        return UIntArray.get-pVg5ArA(arr_v, v);
    }

    private static final long elementAt-r7IrZao(long[] arr_v, int v) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$elementAt");
        return ULongArray.get-s-VKNKU(arr_v, v);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final UInt max--ajY-9A(int[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$max");
        return UArraysKt.maxOrNull--ajY-9A(arr_v);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final UByte max-GBYM_sE(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "$this$max");
        return UArraysKt.maxOrNull-GBYM_sE(arr_b);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final ULong max-QwZRm1k(long[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$max");
        return UArraysKt.maxOrNull-QwZRm1k(arr_v);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final UShort max-rL5Bavg(short[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$max");
        return UArraysKt.maxOrNull-rL5Bavg(arr_v);
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    private static final UByte maxBy-JOV_ifY(byte[] arr_b, Function1 function10) {
        Intrinsics.checkNotNullParameter(arr_b, "$this$maxBy");
        Intrinsics.checkNotNullParameter(function10, "selector");
        if(UByteArray.isEmpty-impl(arr_b)) {
            return null;
        }
        int v = UByteArray.get-w2LRezQ(arr_b, 0);
        int v1 = ArraysKt.getLastIndex(arr_b);
        if(v1 == 0) {
            return UByte.box-impl(((byte)v));
        }
        Comparable comparable0 = (Comparable)function10.invoke(UByte.box-impl(((byte)v)));
        IntIterator intIterator0 = new IntRange(1, v1).iterator();
        while(intIterator0.hasNext()) {
            int v2 = UByteArray.get-w2LRezQ(arr_b, intIterator0.nextInt());
            Comparable comparable1 = (Comparable)function10.invoke(UByte.box-impl(((byte)v2)));
            if(comparable0.compareTo(comparable1) < 0) {
                v = v2;
                comparable0 = comparable1;
            }
        }
        return UByte.box-impl(((byte)v));
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    private static final ULong maxBy-MShoTSo(long[] arr_v, Function1 function10) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$maxBy");
        Intrinsics.checkNotNullParameter(function10, "selector");
        if(ULongArray.isEmpty-impl(arr_v)) {
            return null;
        }
        long v = ULongArray.get-s-VKNKU(arr_v, 0);
        int v1 = ArraysKt.getLastIndex(arr_v);
        if(v1 == 0) {
            return ULong.box-impl(v);
        }
        Comparable comparable0 = (Comparable)function10.invoke(ULong.box-impl(v));
        IntIterator intIterator0 = new IntRange(1, v1).iterator();
        while(intIterator0.hasNext()) {
            long v2 = ULongArray.get-s-VKNKU(arr_v, intIterator0.nextInt());
            Comparable comparable1 = (Comparable)function10.invoke(ULong.box-impl(v2));
            if(comparable0.compareTo(comparable1) < 0) {
                v = v2;
                comparable0 = comparable1;
            }
        }
        return ULong.box-impl(v);
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    private static final UInt maxBy-jgv0xPQ(int[] arr_v, Function1 function10) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$maxBy");
        Intrinsics.checkNotNullParameter(function10, "selector");
        if(UIntArray.isEmpty-impl(arr_v)) {
            return null;
        }
        int v = UIntArray.get-pVg5ArA(arr_v, 0);
        int v1 = ArraysKt.getLastIndex(arr_v);
        if(v1 == 0) {
            return UInt.box-impl(v);
        }
        Comparable comparable0 = (Comparable)function10.invoke(UInt.box-impl(v));
        IntIterator intIterator0 = new IntRange(1, v1).iterator();
        while(intIterator0.hasNext()) {
            int v2 = UIntArray.get-pVg5ArA(arr_v, intIterator0.nextInt());
            Comparable comparable1 = (Comparable)function10.invoke(UInt.box-impl(v2));
            if(comparable0.compareTo(comparable1) < 0) {
                v = v2;
                comparable0 = comparable1;
            }
        }
        return UInt.box-impl(v);
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    private static final UShort maxBy-xTcfx_M(short[] arr_v, Function1 function10) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$maxBy");
        Intrinsics.checkNotNullParameter(function10, "selector");
        if(UShortArray.isEmpty-impl(arr_v)) {
            return null;
        }
        int v = UShortArray.get-Mh2AYeg(arr_v, 0);
        int v1 = ArraysKt.getLastIndex(arr_v);
        if(v1 == 0) {
            return UShort.box-impl(((short)v));
        }
        Comparable comparable0 = (Comparable)function10.invoke(UShort.box-impl(((short)v)));
        IntIterator intIterator0 = new IntRange(1, v1).iterator();
        while(intIterator0.hasNext()) {
            int v2 = UShortArray.get-Mh2AYeg(arr_v, intIterator0.nextInt());
            Comparable comparable1 = (Comparable)function10.invoke(UShort.box-impl(((short)v2)));
            if(comparable0.compareTo(comparable1) < 0) {
                v = v2;
                comparable0 = comparable1;
            }
        }
        return UShort.box-impl(((short)v));
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final UByte maxWith-XMRcp5o(byte[] arr_b, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(arr_b, "$this$maxWith");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return UArraysKt.maxWithOrNull-XMRcp5o(arr_b, comparator0);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final UInt maxWith-YmdZ_VM(int[] arr_v, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$maxWith");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return UArraysKt.maxWithOrNull-YmdZ_VM(arr_v, comparator0);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final UShort maxWith-eOHTfZs(short[] arr_v, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$maxWith");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return UArraysKt.maxWithOrNull-eOHTfZs(arr_v, comparator0);
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final ULong maxWith-zrEWJaI(long[] arr_v, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$maxWith");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return UArraysKt.maxWithOrNull-zrEWJaI(arr_v, comparator0);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final UInt min--ajY-9A(int[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$min");
        return UArraysKt.minOrNull--ajY-9A(arr_v);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final UByte min-GBYM_sE(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "$this$min");
        return UArraysKt.minOrNull-GBYM_sE(arr_b);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final ULong min-QwZRm1k(long[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$min");
        return UArraysKt.minOrNull-QwZRm1k(arr_v);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final UShort min-rL5Bavg(short[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$min");
        return UArraysKt.minOrNull-rL5Bavg(arr_v);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    private static final UByte minBy-JOV_ifY(byte[] arr_b, Function1 function10) {
        Intrinsics.checkNotNullParameter(arr_b, "$this$minBy");
        Intrinsics.checkNotNullParameter(function10, "selector");
        if(UByteArray.isEmpty-impl(arr_b)) {
            return null;
        }
        int v = UByteArray.get-w2LRezQ(arr_b, 0);
        int v1 = ArraysKt.getLastIndex(arr_b);
        if(v1 == 0) {
            return UByte.box-impl(((byte)v));
        }
        Comparable comparable0 = (Comparable)function10.invoke(UByte.box-impl(((byte)v)));
        IntIterator intIterator0 = new IntRange(1, v1).iterator();
        while(intIterator0.hasNext()) {
            int v2 = UByteArray.get-w2LRezQ(arr_b, intIterator0.nextInt());
            Comparable comparable1 = (Comparable)function10.invoke(UByte.box-impl(((byte)v2)));
            if(comparable0.compareTo(comparable1) > 0) {
                v = v2;
                comparable0 = comparable1;
            }
        }
        return UByte.box-impl(((byte)v));
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    private static final ULong minBy-MShoTSo(long[] arr_v, Function1 function10) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$minBy");
        Intrinsics.checkNotNullParameter(function10, "selector");
        if(ULongArray.isEmpty-impl(arr_v)) {
            return null;
        }
        long v = ULongArray.get-s-VKNKU(arr_v, 0);
        int v1 = ArraysKt.getLastIndex(arr_v);
        if(v1 == 0) {
            return ULong.box-impl(v);
        }
        Comparable comparable0 = (Comparable)function10.invoke(ULong.box-impl(v));
        IntIterator intIterator0 = new IntRange(1, v1).iterator();
        while(intIterator0.hasNext()) {
            long v2 = ULongArray.get-s-VKNKU(arr_v, intIterator0.nextInt());
            Comparable comparable1 = (Comparable)function10.invoke(ULong.box-impl(v2));
            if(comparable0.compareTo(comparable1) > 0) {
                v = v2;
                comparable0 = comparable1;
            }
        }
        return ULong.box-impl(v);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    private static final UInt minBy-jgv0xPQ(int[] arr_v, Function1 function10) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$minBy");
        Intrinsics.checkNotNullParameter(function10, "selector");
        if(UIntArray.isEmpty-impl(arr_v)) {
            return null;
        }
        int v = UIntArray.get-pVg5ArA(arr_v, 0);
        int v1 = ArraysKt.getLastIndex(arr_v);
        if(v1 == 0) {
            return UInt.box-impl(v);
        }
        Comparable comparable0 = (Comparable)function10.invoke(UInt.box-impl(v));
        IntIterator intIterator0 = new IntRange(1, v1).iterator();
        while(intIterator0.hasNext()) {
            int v2 = UIntArray.get-pVg5ArA(arr_v, intIterator0.nextInt());
            Comparable comparable1 = (Comparable)function10.invoke(UInt.box-impl(v2));
            if(comparable0.compareTo(comparable1) > 0) {
                v = v2;
                comparable0 = comparable1;
            }
        }
        return UInt.box-impl(v);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    private static final UShort minBy-xTcfx_M(short[] arr_v, Function1 function10) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$minBy");
        Intrinsics.checkNotNullParameter(function10, "selector");
        if(UShortArray.isEmpty-impl(arr_v)) {
            return null;
        }
        int v = UShortArray.get-Mh2AYeg(arr_v, 0);
        int v1 = ArraysKt.getLastIndex(arr_v);
        if(v1 == 0) {
            return UShort.box-impl(((short)v));
        }
        Comparable comparable0 = (Comparable)function10.invoke(UShort.box-impl(((short)v)));
        IntIterator intIterator0 = new IntRange(1, v1).iterator();
        while(intIterator0.hasNext()) {
            int v2 = UShortArray.get-Mh2AYeg(arr_v, intIterator0.nextInt());
            Comparable comparable1 = (Comparable)function10.invoke(UShort.box-impl(((short)v2)));
            if(comparable0.compareTo(comparable1) > 0) {
                v = v2;
                comparable0 = comparable1;
            }
        }
        return UShort.box-impl(((short)v));
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final UByte minWith-XMRcp5o(byte[] arr_b, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(arr_b, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return UArraysKt.minWithOrNull-XMRcp5o(arr_b, comparator0);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final UInt minWith-YmdZ_VM(int[] arr_v, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return UArraysKt.minWithOrNull-YmdZ_VM(arr_v, comparator0);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final UShort minWith-eOHTfZs(short[] arr_v, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return UArraysKt.minWithOrNull-eOHTfZs(arr_v, comparator0);
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final ULong minWith-zrEWJaI(long[] arr_v, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$minWith");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return UArraysKt.minWithOrNull-zrEWJaI(arr_v, comparator0);
    }

    private static final BigDecimal sumOfBigDecimal(byte[] arr_b, Function1 function10) {
        Intrinsics.checkNotNullParameter(arr_b, "$this$sumOf");
        Intrinsics.checkNotNullParameter(function10, "selector");
        BigDecimal bigDecimal0 = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigDecimal0, "valueOf(this.toLong())");
        int v = UByteArray.getSize-impl(arr_b);
        for(int v1 = 0; v1 < v; ++v1) {
            bigDecimal0 = bigDecimal0.add(((BigDecimal)function10.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(arr_b, v1)))));
            Intrinsics.checkNotNullExpressionValue(bigDecimal0, "this.add(other)");
        }
        return bigDecimal0;
    }

    private static final BigDecimal sumOfBigDecimal(int[] arr_v, Function1 function10) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$sumOf");
        Intrinsics.checkNotNullParameter(function10, "selector");
        BigDecimal bigDecimal0 = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigDecimal0, "valueOf(this.toLong())");
        int v = UIntArray.getSize-impl(arr_v);
        for(int v1 = 0; v1 < v; ++v1) {
            bigDecimal0 = bigDecimal0.add(((BigDecimal)function10.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(arr_v, v1)))));
            Intrinsics.checkNotNullExpressionValue(bigDecimal0, "this.add(other)");
        }
        return bigDecimal0;
    }

    private static final BigDecimal sumOfBigDecimal(long[] arr_v, Function1 function10) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$sumOf");
        Intrinsics.checkNotNullParameter(function10, "selector");
        BigDecimal bigDecimal0 = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigDecimal0, "valueOf(this.toLong())");
        int v = ULongArray.getSize-impl(arr_v);
        for(int v1 = 0; v1 < v; ++v1) {
            bigDecimal0 = bigDecimal0.add(((BigDecimal)function10.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(arr_v, v1)))));
            Intrinsics.checkNotNullExpressionValue(bigDecimal0, "this.add(other)");
        }
        return bigDecimal0;
    }

    private static final BigDecimal sumOfBigDecimal(short[] arr_v, Function1 function10) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$sumOf");
        Intrinsics.checkNotNullParameter(function10, "selector");
        BigDecimal bigDecimal0 = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigDecimal0, "valueOf(this.toLong())");
        int v = UShortArray.getSize-impl(arr_v);
        for(int v1 = 0; v1 < v; ++v1) {
            bigDecimal0 = bigDecimal0.add(((BigDecimal)function10.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(arr_v, v1)))));
            Intrinsics.checkNotNullExpressionValue(bigDecimal0, "this.add(other)");
        }
        return bigDecimal0;
    }

    private static final BigInteger sumOfBigInteger(byte[] arr_b, Function1 function10) {
        Intrinsics.checkNotNullParameter(arr_b, "$this$sumOf");
        Intrinsics.checkNotNullParameter(function10, "selector");
        BigInteger bigInteger0 = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigInteger0, "valueOf(this.toLong())");
        int v = UByteArray.getSize-impl(arr_b);
        for(int v1 = 0; v1 < v; ++v1) {
            bigInteger0 = bigInteger0.add(((BigInteger)function10.invoke(UByte.box-impl(UByteArray.get-w2LRezQ(arr_b, v1)))));
            Intrinsics.checkNotNullExpressionValue(bigInteger0, "this.add(other)");
        }
        return bigInteger0;
    }

    private static final BigInteger sumOfBigInteger(int[] arr_v, Function1 function10) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$sumOf");
        Intrinsics.checkNotNullParameter(function10, "selector");
        BigInteger bigInteger0 = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigInteger0, "valueOf(this.toLong())");
        int v = UIntArray.getSize-impl(arr_v);
        for(int v1 = 0; v1 < v; ++v1) {
            bigInteger0 = bigInteger0.add(((BigInteger)function10.invoke(UInt.box-impl(UIntArray.get-pVg5ArA(arr_v, v1)))));
            Intrinsics.checkNotNullExpressionValue(bigInteger0, "this.add(other)");
        }
        return bigInteger0;
    }

    private static final BigInteger sumOfBigInteger(long[] arr_v, Function1 function10) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$sumOf");
        Intrinsics.checkNotNullParameter(function10, "selector");
        BigInteger bigInteger0 = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigInteger0, "valueOf(this.toLong())");
        int v = ULongArray.getSize-impl(arr_v);
        for(int v1 = 0; v1 < v; ++v1) {
            bigInteger0 = bigInteger0.add(((BigInteger)function10.invoke(ULong.box-impl(ULongArray.get-s-VKNKU(arr_v, v1)))));
            Intrinsics.checkNotNullExpressionValue(bigInteger0, "this.add(other)");
        }
        return bigInteger0;
    }

    private static final BigInteger sumOfBigInteger(short[] arr_v, Function1 function10) {
        Intrinsics.checkNotNullParameter(arr_v, "$this$sumOf");
        Intrinsics.checkNotNullParameter(function10, "selector");
        BigInteger bigInteger0 = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigInteger0, "valueOf(this.toLong())");
        int v = UShortArray.getSize-impl(arr_v);
        for(int v1 = 0; v1 < v; ++v1) {
            bigInteger0 = bigInteger0.add(((BigInteger)function10.invoke(UShort.box-impl(UShortArray.get-Mh2AYeg(arr_v, v1)))));
            Intrinsics.checkNotNullExpressionValue(bigInteger0, "this.add(other)");
        }
        return bigInteger0;
    }
}

