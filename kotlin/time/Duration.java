package kotlin.time;

import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.LongRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u000F\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000B\n\u0002\u0010\u0000\n\u0002\b\u001B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000E\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0087@\u0018\u0000 \u00A6\u00012\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002\u00A6\u0001B\u0014\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u00F8\u0001\u0000\u00A2\u0006\u0004\b\u0004\u0010\u0005J%\u0010D\u001A\u00020\u00002\u0006\u0010E\u001A\u00020\u00032\u0006\u0010F\u001A\u00020\u0003H\u0002\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bG\u0010HJ\u001B\u0010I\u001A\u00020\t2\u0006\u0010J\u001A\u00020\u0000H\u0096\u0002\u00F8\u0001\u0000\u00A2\u0006\u0004\bK\u0010LJ\u001E\u0010M\u001A\u00020\u00002\u0006\u0010N\u001A\u00020\u000FH\u0086\u0002\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bO\u0010PJ\u001E\u0010M\u001A\u00020\u00002\u0006\u0010N\u001A\u00020\tH\u0086\u0002\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bO\u0010QJ\u001B\u0010M\u001A\u00020\u000F2\u0006\u0010J\u001A\u00020\u0000H\u0086\u0002\u00F8\u0001\u0000\u00A2\u0006\u0004\bR\u0010SJ\u001A\u0010T\u001A\u00020U2\b\u0010J\u001A\u0004\u0018\u00010VH\u00D6\u0003\u00A2\u0006\u0004\bW\u0010XJ\u0010\u0010Y\u001A\u00020\tH\u00D6\u0001\u00A2\u0006\u0004\bZ\u0010\rJ\r\u0010[\u001A\u00020U\u00A2\u0006\u0004\b\\\u0010]J\u000F\u0010^\u001A\u00020UH\u0002\u00A2\u0006\u0004\b_\u0010]J\u000F\u0010`\u001A\u00020UH\u0002\u00A2\u0006\u0004\ba\u0010]J\r\u0010b\u001A\u00020U\u00A2\u0006\u0004\bc\u0010]J\r\u0010d\u001A\u00020U\u00A2\u0006\u0004\be\u0010]J\r\u0010f\u001A\u00020U\u00A2\u0006\u0004\bg\u0010]J\u001B\u0010h\u001A\u00020\u00002\u0006\u0010J\u001A\u00020\u0000H\u0086\u0002\u00F8\u0001\u0000\u00A2\u0006\u0004\bi\u0010jJ\u001B\u0010k\u001A\u00020\u00002\u0006\u0010J\u001A\u00020\u0000H\u0086\u0002\u00F8\u0001\u0000\u00A2\u0006\u0004\bl\u0010jJ\u001E\u0010m\u001A\u00020\u00002\u0006\u0010N\u001A\u00020\u000FH\u0086\u0002\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bn\u0010PJ\u001E\u0010m\u001A\u00020\u00002\u0006\u0010N\u001A\u00020\tH\u0086\u0002\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\bn\u0010QJ\u009D\u0001\u0010o\u001A\u0002Hp\"\u0004\b\u0000\u0010p2u\u0010q\u001Aq\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(u\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(v\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(w\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(x\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(y\u0012\u0004\u0012\u0002Hp0rH\u0086\b\u00F8\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00A2\u0006\u0004\bz\u0010{J\u0088\u0001\u0010o\u001A\u0002Hp\"\u0004\b\u0000\u0010p2`\u0010q\u001A\\\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(v\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(w\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(x\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(y\u0012\u0004\u0012\u0002Hp0|H\u0086\b\u00F8\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00A2\u0006\u0004\bz\u0010}Js\u0010o\u001A\u0002Hp\"\u0004\b\u0000\u0010p2K\u0010q\u001AG\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(w\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(x\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(y\u0012\u0004\u0012\u0002Hp0~H\u0086\b\u00F8\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00A2\u0006\u0004\bz\u0010\u007FJ`\u0010o\u001A\u0002Hp\"\u0004\b\u0000\u0010p27\u0010q\u001A3\u0012\u0013\u0012\u00110\u0003\u00A2\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(x\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\bs\u0012\b\bt\u0012\u0004\b\b(y\u0012\u0004\u0012\u0002Hp0\u0080\u0001H\u0086\b\u00F8\u0001\u0002\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u00A2\u0006\u0005\bz\u0010\u0081\u0001J\u0019\u0010\u0082\u0001\u001A\u00020\u000F2\u0007\u0010\u0083\u0001\u001A\u00020=\u00A2\u0006\u0006\b\u0084\u0001\u0010\u0085\u0001J\u0019\u0010\u0086\u0001\u001A\u00020\t2\u0007\u0010\u0083\u0001\u001A\u00020=\u00A2\u0006\u0006\b\u0087\u0001\u0010\u0088\u0001J\u0011\u0010\u0089\u0001\u001A\u00030\u008A\u0001\u00A2\u0006\u0006\b\u008B\u0001\u0010\u008C\u0001J\u0019\u0010\u008D\u0001\u001A\u00020\u00032\u0007\u0010\u0083\u0001\u001A\u00020=\u00A2\u0006\u0006\b\u008E\u0001\u0010\u008F\u0001J\u0011\u0010\u0090\u0001\u001A\u00020\u0003H\u0007\u00A2\u0006\u0005\b\u0091\u0001\u0010\u0005J\u0011\u0010\u0092\u0001\u001A\u00020\u0003H\u0007\u00A2\u0006\u0005\b\u0093\u0001\u0010\u0005J\u0013\u0010\u0094\u0001\u001A\u00030\u008A\u0001H\u0016\u00A2\u0006\u0006\b\u0095\u0001\u0010\u008C\u0001J%\u0010\u0094\u0001\u001A\u00030\u008A\u00012\u0007\u0010\u0083\u0001\u001A\u00020=2\t\b\u0002\u0010\u0096\u0001\u001A\u00020\t\u00A2\u0006\u0006\b\u0095\u0001\u0010\u0097\u0001J!\u0010\u0098\u0001\u001A\u00020\u00002\u0007\u0010\u0083\u0001\u001A\u00020=H\u0000\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0006\b\u0099\u0001\u0010\u008F\u0001J\u0018\u0010\u009A\u0001\u001A\u00020\u0000H\u0086\u0002\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0005\b\u009B\u0001\u0010\u0005JK\u0010\u009C\u0001\u001A\u00030\u009D\u0001*\b0\u009E\u0001j\u0003`\u009F\u00012\u0007\u0010\u00A0\u0001\u001A\u00020\t2\u0007\u0010\u00A1\u0001\u001A\u00020\t2\u0007\u0010\u00A2\u0001\u001A\u00020\t2\b\u0010\u0083\u0001\u001A\u00030\u008A\u00012\u0007\u0010\u00A3\u0001\u001A\u00020UH\u0002\u00A2\u0006\u0006\b\u00A4\u0001\u0010\u00A5\u0001R\u0017\u0010\u0006\u001A\u00020\u00008F\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\u0006\u001A\u0004\b\u0007\u0010\u0005R\u001A\u0010\b\u001A\u00020\t8@X\u0081\u0004\u00A2\u0006\f\u0012\u0004\b\n\u0010\u000B\u001A\u0004\b\f\u0010\rR\u001A\u0010\u000E\u001A\u00020\u000F8FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u0010\u0010\u000B\u001A\u0004\b\u0011\u0010\u0012R\u001A\u0010\u0013\u001A\u00020\u000F8FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u0014\u0010\u000B\u001A\u0004\b\u0015\u0010\u0012R\u001A\u0010\u0016\u001A\u00020\u000F8FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u0017\u0010\u000B\u001A\u0004\b\u0018\u0010\u0012R\u001A\u0010\u0019\u001A\u00020\u000F8FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u001A\u0010\u000B\u001A\u0004\b\u001B\u0010\u0012R\u001A\u0010\u001C\u001A\u00020\u000F8FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u001D\u0010\u000B\u001A\u0004\b\u001E\u0010\u0012R\u001A\u0010\u001F\u001A\u00020\u000F8FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b \u0010\u000B\u001A\u0004\b!\u0010\u0012R\u001A\u0010\"\u001A\u00020\u000F8FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b#\u0010\u000B\u001A\u0004\b$\u0010\u0012R\u0011\u0010%\u001A\u00020\u00038F\u00A2\u0006\u0006\u001A\u0004\b&\u0010\u0005R\u0011\u0010\'\u001A\u00020\u00038F\u00A2\u0006\u0006\u001A\u0004\b(\u0010\u0005R\u0011\u0010)\u001A\u00020\u00038F\u00A2\u0006\u0006\u001A\u0004\b*\u0010\u0005R\u0011\u0010+\u001A\u00020\u00038F\u00A2\u0006\u0006\u001A\u0004\b,\u0010\u0005R\u0011\u0010-\u001A\u00020\u00038F\u00A2\u0006\u0006\u001A\u0004\b.\u0010\u0005R\u0011\u0010/\u001A\u00020\u00038F\u00A2\u0006\u0006\u001A\u0004\b0\u0010\u0005R\u0011\u00101\u001A\u00020\u00038F\u00A2\u0006\u0006\u001A\u0004\b2\u0010\u0005R\u001A\u00103\u001A\u00020\t8@X\u0081\u0004\u00A2\u0006\f\u0012\u0004\b4\u0010\u000B\u001A\u0004\b5\u0010\rR\u001A\u00106\u001A\u00020\t8@X\u0081\u0004\u00A2\u0006\f\u0012\u0004\b7\u0010\u000B\u001A\u0004\b8\u0010\rR\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001A\u00109\u001A\u00020\t8@X\u0081\u0004\u00A2\u0006\f\u0012\u0004\b:\u0010\u000B\u001A\u0004\b;\u0010\rR\u0014\u0010<\u001A\u00020=8BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b>\u0010?R\u0015\u0010@\u001A\u00020\t8\u00C2\u0002X\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\bA\u0010\rR\u0014\u0010B\u001A\u00020\u00038BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\bC\u0010\u0005\u0088\u0001\u0002\u0092\u0001\u00020\u0003\u00F8\u0001\u0000\u0082\u0002\u000F\n\u0002\b\u0019\n\u0002\b!\n\u0005\b\u009920\u0001\u00A8\u0006\u00A7\u0001"}, d2 = {"Lkotlin/time/Duration;", "", "rawValue", "", "constructor-impl", "(J)J", "absoluteValue", "getAbsoluteValue-UwyO8pc", "hoursComponent", "", "getHoursComponent$annotations", "()V", "getHoursComponent-impl", "(J)I", "inDays", "", "getInDays$annotations", "getInDays-impl", "(J)D", "inHours", "getInHours$annotations", "getInHours-impl", "inMicroseconds", "getInMicroseconds$annotations", "getInMicroseconds-impl", "inMilliseconds", "getInMilliseconds$annotations", "getInMilliseconds-impl", "inMinutes", "getInMinutes$annotations", "getInMinutes-impl", "inNanoseconds", "getInNanoseconds$annotations", "getInNanoseconds-impl", "inSeconds", "getInSeconds$annotations", "getInSeconds-impl", "inWholeDays", "getInWholeDays-impl", "inWholeHours", "getInWholeHours-impl", "inWholeMicroseconds", "getInWholeMicroseconds-impl", "inWholeMilliseconds", "getInWholeMilliseconds-impl", "inWholeMinutes", "getInWholeMinutes-impl", "inWholeNanoseconds", "getInWholeNanoseconds-impl", "inWholeSeconds", "getInWholeSeconds-impl", "minutesComponent", "getMinutesComponent$annotations", "getMinutesComponent-impl", "nanosecondsComponent", "getNanosecondsComponent$annotations", "getNanosecondsComponent-impl", "secondsComponent", "getSecondsComponent$annotations", "getSecondsComponent-impl", "storageUnit", "Lkotlin/time/DurationUnit;", "getStorageUnit-impl", "(J)Lkotlin/time/DurationUnit;", "unitDiscriminator", "getUnitDiscriminator-impl", "value", "getValue-impl", "addValuesMixedRanges", "thisMillis", "otherNanos", "addValuesMixedRanges-UwyO8pc", "(JJJ)J", "compareTo", "other", "compareTo-LRDsOJo", "(JJ)I", "div", "scale", "div-UwyO8pc", "(JD)J", "(JI)J", "div-LRDsOJo", "(JJ)D", "equals", "", "", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "isFinite", "isFinite-impl", "(J)Z", "isInMillis", "isInMillis-impl", "isInNanos", "isInNanos-impl", "isInfinite", "isInfinite-impl", "isNegative", "isNegative-impl", "isPositive", "isPositive-impl", "minus", "minus-LRDsOJo", "(JJ)J", "plus", "plus-LRDsOJo", "times", "times-UwyO8pc", "toComponents", "T", "action", "Lkotlin/Function5;", "Lkotlin/ParameterName;", "name", "days", "hours", "minutes", "seconds", "nanoseconds", "toComponents-impl", "(JLkotlin/jvm/functions/Function5;)Ljava/lang/Object;", "Lkotlin/Function4;", "(JLkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "Lkotlin/Function3;", "(JLkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "Lkotlin/Function2;", "(JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "toDouble", "unit", "toDouble-impl", "(JLkotlin/time/DurationUnit;)D", "toInt", "toInt-impl", "(JLkotlin/time/DurationUnit;)I", "toIsoString", "", "toIsoString-impl", "(J)Ljava/lang/String;", "toLong", "toLong-impl", "(JLkotlin/time/DurationUnit;)J", "toLongMilliseconds", "toLongMilliseconds-impl", "toLongNanoseconds", "toLongNanoseconds-impl", "toString", "toString-impl", "decimals", "(JLkotlin/time/DurationUnit;I)Ljava/lang/String;", "truncateTo", "truncateTo-UwyO8pc$kotlin_stdlib", "unaryMinus", "unaryMinus-UwyO8pc", "appendFractional", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "whole", "fractional", "fractionalSize", "isoZeroes", "appendFractional-impl", "(JLjava/lang/StringBuilder;IIILjava/lang/String;Z)V", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
@JvmInline
public final class Duration implements Comparable {
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000E\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00A2\u0006\u0002\u0010\u0002J \u0010*\u001A\u00020\r2\u0006\u0010+\u001A\u00020\r2\u0006\u0010,\u001A\u00020-2\u0006\u0010.\u001A\u00020-H\u0007J\u001D\u0010\f\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\rH\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b/\u0010\u0011J\u001D\u0010\f\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\u0012H\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b/\u0010\u0014J\u001D\u0010\f\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\u0015H\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b/\u0010\u0017J\u001D\u0010\u0018\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\rH\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b0\u0010\u0011J\u001D\u0010\u0018\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\u0012H\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b0\u0010\u0014J\u001D\u0010\u0018\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\u0015H\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b0\u0010\u0017J\u001D\u0010\u001B\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\rH\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b1\u0010\u0011J\u001D\u0010\u001B\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\u0012H\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b1\u0010\u0014J\u001D\u0010\u001B\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\u0015H\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b1\u0010\u0017J\u001D\u0010\u001E\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\rH\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b2\u0010\u0011J\u001D\u0010\u001E\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\u0012H\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b2\u0010\u0014J\u001D\u0010\u001E\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\u0015H\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b2\u0010\u0017J\u001D\u0010!\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\rH\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b3\u0010\u0011J\u001D\u0010!\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\u0012H\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b3\u0010\u0014J\u001D\u0010!\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\u0015H\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b3\u0010\u0017J\u001D\u0010$\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\rH\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b4\u0010\u0011J\u001D\u0010$\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\u0012H\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b4\u0010\u0014J\u001D\u0010$\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\u0015H\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b4\u0010\u0017J\u001B\u00105\u001A\u00020\u00042\u0006\u0010+\u001A\u000206\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b7\u00108J\u001B\u00109\u001A\u00020\u00042\u0006\u0010+\u001A\u000206\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b:\u00108J\u001B\u0010;\u001A\u0004\u0018\u00010\u00042\u0006\u0010+\u001A\u000206\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0002\b<J\u001B\u0010=\u001A\u0004\u0018\u00010\u00042\u0006\u0010+\u001A\u000206\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0002\b>J\u001D\u0010\'\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\rH\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b?\u0010\u0011J\u001D\u0010\'\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\u0012H\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b?\u0010\u0014J\u001D\u0010\'\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\u0015H\u0007\u00F8\u0001\u0001\u00F8\u0001\u0000\u00A2\u0006\u0004\b?\u0010\u0017R\u0019\u0010\u0003\u001A\u00020\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\n\n\u0002\u0010\u0007\u001A\u0004\b\u0005\u0010\u0006R\u001C\u0010\b\u001A\u00020\u0004X\u0080\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\n\n\u0002\u0010\u0007\u001A\u0004\b\t\u0010\u0006R\u0019\u0010\n\u001A\u00020\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\n\n\u0002\u0010\u0007\u001A\u0004\b\u000B\u0010\u0006R%\u0010\f\u001A\u00020\u0004*\u00020\r8\u00C6\u0002X\u0087\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\f\u0012\u0004\b\u000E\u0010\u000F\u001A\u0004\b\u0010\u0010\u0011R%\u0010\f\u001A\u00020\u0004*\u00020\u00128\u00C6\u0002X\u0087\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\f\u0012\u0004\b\u000E\u0010\u0013\u001A\u0004\b\u0010\u0010\u0014R%\u0010\f\u001A\u00020\u0004*\u00020\u00158\u00C6\u0002X\u0087\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\f\u0012\u0004\b\u000E\u0010\u0016\u001A\u0004\b\u0010\u0010\u0017R%\u0010\u0018\u001A\u00020\u0004*\u00020\r8\u00C6\u0002X\u0087\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\f\u0012\u0004\b\u0019\u0010\u000F\u001A\u0004\b\u001A\u0010\u0011R%\u0010\u0018\u001A\u00020\u0004*\u00020\u00128\u00C6\u0002X\u0087\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\f\u0012\u0004\b\u0019\u0010\u0013\u001A\u0004\b\u001A\u0010\u0014R%\u0010\u0018\u001A\u00020\u0004*\u00020\u00158\u00C6\u0002X\u0087\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\f\u0012\u0004\b\u0019\u0010\u0016\u001A\u0004\b\u001A\u0010\u0017R%\u0010\u001B\u001A\u00020\u0004*\u00020\r8\u00C6\u0002X\u0087\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\f\u0012\u0004\b\u001C\u0010\u000F\u001A\u0004\b\u001D\u0010\u0011R%\u0010\u001B\u001A\u00020\u0004*\u00020\u00128\u00C6\u0002X\u0087\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\f\u0012\u0004\b\u001C\u0010\u0013\u001A\u0004\b\u001D\u0010\u0014R%\u0010\u001B\u001A\u00020\u0004*\u00020\u00158\u00C6\u0002X\u0087\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\f\u0012\u0004\b\u001C\u0010\u0016\u001A\u0004\b\u001D\u0010\u0017R%\u0010\u001E\u001A\u00020\u0004*\u00020\r8\u00C6\u0002X\u0087\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\f\u0012\u0004\b\u001F\u0010\u000F\u001A\u0004\b \u0010\u0011R%\u0010\u001E\u001A\u00020\u0004*\u00020\u00128\u00C6\u0002X\u0087\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\f\u0012\u0004\b\u001F\u0010\u0013\u001A\u0004\b \u0010\u0014R%\u0010\u001E\u001A\u00020\u0004*\u00020\u00158\u00C6\u0002X\u0087\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\f\u0012\u0004\b\u001F\u0010\u0016\u001A\u0004\b \u0010\u0017R%\u0010!\u001A\u00020\u0004*\u00020\r8\u00C6\u0002X\u0087\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\f\u0012\u0004\b\"\u0010\u000F\u001A\u0004\b#\u0010\u0011R%\u0010!\u001A\u00020\u0004*\u00020\u00128\u00C6\u0002X\u0087\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\f\u0012\u0004\b\"\u0010\u0013\u001A\u0004\b#\u0010\u0014R%\u0010!\u001A\u00020\u0004*\u00020\u00158\u00C6\u0002X\u0087\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\f\u0012\u0004\b\"\u0010\u0016\u001A\u0004\b#\u0010\u0017R%\u0010$\u001A\u00020\u0004*\u00020\r8\u00C6\u0002X\u0087\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\f\u0012\u0004\b%\u0010\u000F\u001A\u0004\b&\u0010\u0011R%\u0010$\u001A\u00020\u0004*\u00020\u00128\u00C6\u0002X\u0087\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\f\u0012\u0004\b%\u0010\u0013\u001A\u0004\b&\u0010\u0014R%\u0010$\u001A\u00020\u0004*\u00020\u00158\u00C6\u0002X\u0087\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\f\u0012\u0004\b%\u0010\u0016\u001A\u0004\b&\u0010\u0017R%\u0010\'\u001A\u00020\u0004*\u00020\r8\u00C6\u0002X\u0087\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\f\u0012\u0004\b(\u0010\u000F\u001A\u0004\b)\u0010\u0011R%\u0010\'\u001A\u00020\u0004*\u00020\u00128\u00C6\u0002X\u0087\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\f\u0012\u0004\b(\u0010\u0013\u001A\u0004\b)\u0010\u0014R%\u0010\'\u001A\u00020\u0004*\u00020\u00158\u00C6\u0002X\u0087\u0004\u00F8\u0001\u0000\u00F8\u0001\u0001\u00A2\u0006\f\u0012\u0004\b(\u0010\u0016\u001A\u0004\b)\u0010\u0017\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!\u00A8\u0006@"}, d2 = {"Lkotlin/time/Duration$Companion;", "", "()V", "INFINITE", "Lkotlin/time/Duration;", "getINFINITE-UwyO8pc", "()J", "J", "NEG_INFINITE", "getNEG_INFINITE-UwyO8pc$kotlin_stdlib", "ZERO", "getZERO-UwyO8pc", "days", "", "getDays-UwyO8pc$annotations", "(D)V", "getDays-UwyO8pc", "(D)J", "", "(I)V", "(I)J", "", "(J)V", "(J)J", "hours", "getHours-UwyO8pc$annotations", "getHours-UwyO8pc", "microseconds", "getMicroseconds-UwyO8pc$annotations", "getMicroseconds-UwyO8pc", "milliseconds", "getMilliseconds-UwyO8pc$annotations", "getMilliseconds-UwyO8pc", "minutes", "getMinutes-UwyO8pc$annotations", "getMinutes-UwyO8pc", "nanoseconds", "getNanoseconds-UwyO8pc$annotations", "getNanoseconds-UwyO8pc", "seconds", "getSeconds-UwyO8pc$annotations", "getSeconds-UwyO8pc", "convert", "value", "sourceUnit", "Lkotlin/time/DurationUnit;", "targetUnit", "days-UwyO8pc", "hours-UwyO8pc", "microseconds-UwyO8pc", "milliseconds-UwyO8pc", "minutes-UwyO8pc", "nanoseconds-UwyO8pc", "parse", "", "parse-UwyO8pc", "(Ljava/lang/String;)J", "parseIsoString", "parseIsoString-UwyO8pc", "parseIsoStringOrNull", "parseIsoStringOrNull-FghU774", "parseOrNull", "parseOrNull-FghU774", "seconds-UwyO8pc", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final double convert(double f, DurationUnit durationUnit0, DurationUnit durationUnit1) {
            Intrinsics.checkNotNullParameter(durationUnit0, "sourceUnit");
            Intrinsics.checkNotNullParameter(durationUnit1, "targetUnit");
            return DurationUnitKt.convertDurationUnit(f, durationUnit0, durationUnit1);
        }

        @Deprecated(message = "Use \'Double.days\' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.days", imports = {"kotlin.time.Duration.Companion.days"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.6")
        public final long days-UwyO8pc(double f) {
            return DurationKt.toDuration(f, DurationUnit.DAYS);
        }

        @Deprecated(message = "Use \'Int.days\' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.days", imports = {"kotlin.time.Duration.Companion.days"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.6")
        public final long days-UwyO8pc(int v) {
            return DurationKt.toDuration(v, DurationUnit.DAYS);
        }

        @Deprecated(message = "Use \'Long.days\' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.days", imports = {"kotlin.time.Duration.Companion.days"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.6")
        public final long days-UwyO8pc(long v) {
            return DurationKt.toDuration(v, DurationUnit.DAYS);
        }

        private final long getDays-UwyO8pc(double f) {
            return DurationKt.toDuration(f, DurationUnit.DAYS);
        }

        private final long getDays-UwyO8pc(int v) {
            return DurationKt.toDuration(v, DurationUnit.DAYS);
        }

        private final long getDays-UwyO8pc(long v) {
            return DurationKt.toDuration(v, DurationUnit.DAYS);
        }

        public static void getDays-UwyO8pc$annotations(double f) {
        }

        public static void getDays-UwyO8pc$annotations(int v) {
        }

        public static void getDays-UwyO8pc$annotations(long v) {
        }

        private final long getHours-UwyO8pc(double f) {
            return DurationKt.toDuration(f, DurationUnit.HOURS);
        }

        private final long getHours-UwyO8pc(int v) {
            return DurationKt.toDuration(v, DurationUnit.HOURS);
        }

        private final long getHours-UwyO8pc(long v) {
            return DurationKt.toDuration(v, DurationUnit.HOURS);
        }

        public static void getHours-UwyO8pc$annotations(double f) {
        }

        public static void getHours-UwyO8pc$annotations(int v) {
        }

        public static void getHours-UwyO8pc$annotations(long v) {
        }

        // 去混淆评级： 低(20)
        public final long getINFINITE-UwyO8pc() [...] // 潜在的解密器

        private final long getMicroseconds-UwyO8pc(double f) {
            return DurationKt.toDuration(f, DurationUnit.MICROSECONDS);
        }

        private final long getMicroseconds-UwyO8pc(int v) {
            return DurationKt.toDuration(v, DurationUnit.MICROSECONDS);
        }

        private final long getMicroseconds-UwyO8pc(long v) {
            return DurationKt.toDuration(v, DurationUnit.MICROSECONDS);
        }

        public static void getMicroseconds-UwyO8pc$annotations(double f) {
        }

        public static void getMicroseconds-UwyO8pc$annotations(int v) {
        }

        public static void getMicroseconds-UwyO8pc$annotations(long v) {
        }

        private final long getMilliseconds-UwyO8pc(double f) {
            return DurationKt.toDuration(f, DurationUnit.MILLISECONDS);
        }

        private final long getMilliseconds-UwyO8pc(int v) {
            return DurationKt.toDuration(v, DurationUnit.MILLISECONDS);
        }

        private final long getMilliseconds-UwyO8pc(long v) {
            return DurationKt.toDuration(v, DurationUnit.MILLISECONDS);
        }

        public static void getMilliseconds-UwyO8pc$annotations(double f) {
        }

        public static void getMilliseconds-UwyO8pc$annotations(int v) {
        }

        public static void getMilliseconds-UwyO8pc$annotations(long v) {
        }

        private final long getMinutes-UwyO8pc(double f) {
            return DurationKt.toDuration(f, DurationUnit.MINUTES);
        }

        private final long getMinutes-UwyO8pc(int v) {
            return DurationKt.toDuration(v, DurationUnit.MINUTES);
        }

        private final long getMinutes-UwyO8pc(long v) {
            return DurationKt.toDuration(v, DurationUnit.MINUTES);
        }

        public static void getMinutes-UwyO8pc$annotations(double f) {
        }

        public static void getMinutes-UwyO8pc$annotations(int v) {
        }

        public static void getMinutes-UwyO8pc$annotations(long v) {
        }

        // 去混淆评级： 低(20)
        public final long getNEG_INFINITE-UwyO8pc$kotlin_stdlib() [...] // 潜在的解密器

        private final long getNanoseconds-UwyO8pc(double f) {
            return DurationKt.toDuration(f, DurationUnit.NANOSECONDS);
        }

        private final long getNanoseconds-UwyO8pc(int v) {
            return DurationKt.toDuration(v, DurationUnit.NANOSECONDS);
        }

        private final long getNanoseconds-UwyO8pc(long v) {
            return DurationKt.toDuration(v, DurationUnit.NANOSECONDS);
        }

        public static void getNanoseconds-UwyO8pc$annotations(double f) {
        }

        public static void getNanoseconds-UwyO8pc$annotations(int v) {
        }

        public static void getNanoseconds-UwyO8pc$annotations(long v) {
        }

        private final long getSeconds-UwyO8pc(double f) {
            return DurationKt.toDuration(f, DurationUnit.SECONDS);
        }

        private final long getSeconds-UwyO8pc(int v) {
            return DurationKt.toDuration(v, DurationUnit.SECONDS);
        }

        private final long getSeconds-UwyO8pc(long v) {
            return DurationKt.toDuration(v, DurationUnit.SECONDS);
        }

        public static void getSeconds-UwyO8pc$annotations(double f) {
        }

        public static void getSeconds-UwyO8pc$annotations(int v) {
        }

        public static void getSeconds-UwyO8pc$annotations(long v) {
        }

        // 去混淆评级： 低(20)
        public final long getZERO-UwyO8pc() [...] // 潜在的解密器

        @Deprecated(message = "Use \'Double.hours\' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.6")
        public final long hours-UwyO8pc(double f) {
            return DurationKt.toDuration(f, DurationUnit.HOURS);
        }

        @Deprecated(message = "Use \'Int.hours\' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.6")
        public final long hours-UwyO8pc(int v) {
            return DurationKt.toDuration(v, DurationUnit.HOURS);
        }

        @Deprecated(message = "Use \'Long.hours\' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.hours", imports = {"kotlin.time.Duration.Companion.hours"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.6")
        public final long hours-UwyO8pc(long v) {
            return DurationKt.toDuration(v, DurationUnit.HOURS);
        }

        @Deprecated(message = "Use \'Double.microseconds\' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.6")
        public final long microseconds-UwyO8pc(double f) {
            return DurationKt.toDuration(f, DurationUnit.MICROSECONDS);
        }

        @Deprecated(message = "Use \'Int.microseconds\' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.6")
        public final long microseconds-UwyO8pc(int v) {
            return DurationKt.toDuration(v, DurationUnit.MICROSECONDS);
        }

        @Deprecated(message = "Use \'Long.microseconds\' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.microseconds", imports = {"kotlin.time.Duration.Companion.microseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.6")
        public final long microseconds-UwyO8pc(long v) {
            return DurationKt.toDuration(v, DurationUnit.MICROSECONDS);
        }

        @Deprecated(message = "Use \'Double.milliseconds\' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.6")
        public final long milliseconds-UwyO8pc(double f) {
            return DurationKt.toDuration(f, DurationUnit.MILLISECONDS);
        }

        @Deprecated(message = "Use \'Int.milliseconds\' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.6")
        public final long milliseconds-UwyO8pc(int v) {
            return DurationKt.toDuration(v, DurationUnit.MILLISECONDS);
        }

        @Deprecated(message = "Use \'Long.milliseconds\' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.milliseconds", imports = {"kotlin.time.Duration.Companion.milliseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.6")
        public final long milliseconds-UwyO8pc(long v) {
            return DurationKt.toDuration(v, DurationUnit.MILLISECONDS);
        }

        @Deprecated(message = "Use \'Double.minutes\' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.6")
        public final long minutes-UwyO8pc(double f) {
            return DurationKt.toDuration(f, DurationUnit.MINUTES);
        }

        @Deprecated(message = "Use \'Int.minutes\' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.6")
        public final long minutes-UwyO8pc(int v) {
            return DurationKt.toDuration(v, DurationUnit.MINUTES);
        }

        @Deprecated(message = "Use \'Long.minutes\' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.minutes", imports = {"kotlin.time.Duration.Companion.minutes"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.6")
        public final long minutes-UwyO8pc(long v) {
            return DurationKt.toDuration(v, DurationUnit.MINUTES);
        }

        @Deprecated(message = "Use \'Double.nanoseconds\' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.6")
        public final long nanoseconds-UwyO8pc(double f) {
            return DurationKt.toDuration(f, DurationUnit.NANOSECONDS);
        }

        @Deprecated(message = "Use \'Int.nanoseconds\' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.6")
        public final long nanoseconds-UwyO8pc(int v) {
            return DurationKt.toDuration(v, DurationUnit.NANOSECONDS);
        }

        @Deprecated(message = "Use \'Long.nanoseconds\' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.nanoseconds", imports = {"kotlin.time.Duration.Companion.nanoseconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.6")
        public final long nanoseconds-UwyO8pc(long v) {
            return DurationKt.toDuration(v, DurationUnit.NANOSECONDS);
        }

        public final long parse-UwyO8pc(String s) {
            Intrinsics.checkNotNullParameter(s, "value");
            try {
                return DurationKt.parseDuration(s, false);
            }
            catch(IllegalArgumentException illegalArgumentException0) {
                throw new IllegalArgumentException("Invalid duration string format: \'" + s + "\'.", illegalArgumentException0);
            }
        }

        public final long parseIsoString-UwyO8pc(String s) {
            Intrinsics.checkNotNullParameter(s, "value");
            try {
                return DurationKt.parseDuration(s, true);
            }
            catch(IllegalArgumentException illegalArgumentException0) {
                throw new IllegalArgumentException("Invalid ISO duration string format: \'" + s + "\'.", illegalArgumentException0);
            }
        }

        public final Duration parseIsoStringOrNull-FghU774(String s) {
            Intrinsics.checkNotNullParameter(s, "value");
            try {
                return Duration.box-impl(DurationKt.parseDuration(s, true));
            }
            catch(IllegalArgumentException unused_ex) {
                return null;
            }
        }

        public final Duration parseOrNull-FghU774(String s) {
            Intrinsics.checkNotNullParameter(s, "value");
            try {
                return Duration.box-impl(DurationKt.parseDuration(s, false));
            }
            catch(IllegalArgumentException unused_ex) {
                return null;
            }
        }

        @Deprecated(message = "Use \'Double.seconds\' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.6")
        public final long seconds-UwyO8pc(double f) {
            return DurationKt.toDuration(f, DurationUnit.SECONDS);
        }

        @Deprecated(message = "Use \'Int.seconds\' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.6")
        public final long seconds-UwyO8pc(int v) {
            return DurationKt.toDuration(v, DurationUnit.SECONDS);
        }

        @Deprecated(message = "Use \'Long.seconds\' extension property from Duration.Companion instead.", replaceWith = @ReplaceWith(expression = "value.seconds", imports = {"kotlin.time.Duration.Companion.seconds"}))
        @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.6")
        public final long seconds-UwyO8pc(long v) {
            return DurationKt.toDuration(v, DurationUnit.SECONDS);
        }
    }

    public static final Companion Companion;
    private static final long INFINITE;
    private static final long NEG_INFINITE;
    private static final long ZERO;
    private final long rawValue;

    static {
        Duration.Companion = new Companion(null);
        Duration.ZERO = Duration.constructor-impl(0L);
        Duration.INFINITE = DurationKt.access$durationOfMillis(0x3FFFFFFFFFFFFFFFL);
        Duration.NEG_INFINITE = DurationKt.access$durationOfMillis(0xC000000000000001L);
    }

    private Duration(long v) {
        this.rawValue = v;
    }

    public static final long access$getINFINITE$cp() [...] // 潜在的解密器

    public static final long access$getNEG_INFINITE$cp() [...] // 潜在的解密器

    public static final long access$getZERO$cp() [...] // 潜在的解密器

    private static final long addValuesMixedRanges-UwyO8pc(long v, long v1, long v2) {
        long v3 = DurationKt.access$nanosToMillis(v2);
        long v4 = v1 + v3;
        return new LongRange(-4611686018426L, 4611686018426L).contains(v4) ? DurationKt.access$durationOfNanos(DurationKt.access$millisToNanos(v4) + (v2 - DurationKt.access$millisToNanos(v3))) : DurationKt.access$durationOfMillis(RangesKt.coerceIn(v4, 0xC000000000000001L, 0x3FFFFFFFFFFFFFFFL));
    }

    private static final void appendFractional-impl(long v, StringBuilder stringBuilder0, int v1, int v2, int v3, String s, boolean z) {
        stringBuilder0.append(v1);
        if(v2 != 0) {
            stringBuilder0.append('.');
            CharSequence charSequence0 = StringsKt.padStart(String.valueOf(v2), v3, '0');
            int v4 = -1;
            int v5 = charSequence0.length() - 1;
            if(v5 >= 0) {
                while(true) {
                    if(charSequence0.charAt(v5) != 0x30) {
                        v4 = v5;
                        break;
                    }
                    if(v5 - 1 < 0) {
                        break;
                    }
                    --v5;
                }
            }
            if(z || v4 + 1 >= 3) {
                stringBuilder0.append(charSequence0, 0, (v4 + 3) / 3 * 3);
            }
            else {
                stringBuilder0.append(charSequence0, 0, v4 + 1);
            }
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "this.append(value, startIndex, endIndex)");
        }
        stringBuilder0.append(s);
    }

    public static final Duration box-impl(long v) {
        return new Duration(v);
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo-LRDsOJo(((Duration)object0).unbox-impl());
    }

    public static int compareTo-LRDsOJo(long v, long v1) {
        if((v ^ v1) >= 0L && (((int)(v ^ v1)) & 1) != 0) {
            int v2 = (((int)v) & 1) - (((int)v1) & 1);
            return Duration.isNegative-impl(v) ? -v2 : v2;
        }
        return Intrinsics.compare(v, v1);
    }

    public int compareTo-LRDsOJo(long v) {
        return Duration.compareTo-LRDsOJo(this.rawValue, v);
    }

    // 去混淆评级： 中等(70)
    public static long constructor-impl(long v) {
        return v;
    }

    public static final double div-LRDsOJo(long v, long v1) {
        DurationUnit durationUnit0 = (DurationUnit)ComparisonsKt.maxOf(Duration.getStorageUnit-impl(v), Duration.getStorageUnit-impl(v1));
        return Duration.toDouble-impl(v, durationUnit0) / Duration.toDouble-impl(v1, durationUnit0);
    }

    public static final long div-UwyO8pc(long v, double f) {
        int v1 = MathKt.roundToInt(f);
        if(((double)v1) == f && v1 != 0) {
            return Duration.div-UwyO8pc(v, v1);
        }
        DurationUnit durationUnit0 = Duration.getStorageUnit-impl(v);
        return DurationKt.toDuration(Duration.toDouble-impl(v, durationUnit0) / f, durationUnit0);
    }

    public static final long div-UwyO8pc(long v, int v1) {
        if(v1 == 0) {
            if(Duration.isPositive-impl(v)) {
                return Duration.INFINITE;
            }
            if(!Duration.isNegative-impl(v)) {
                throw new IllegalArgumentException("Dividing zero duration by zero yields an undefined result.");
            }
            return Duration.NEG_INFINITE;
        }
        if(Duration.isInNanos-impl(v)) {
            return DurationKt.access$durationOfNanos((v >> 1) / ((long)v1));
        }
        if(Duration.isInfinite-impl(v)) {
            return Duration.times-UwyO8pc(v, MathKt.getSign(v1));
        }
        long v2 = (v >> 1) / ((long)v1);
        return new LongRange(-4611686018426L, 4611686018426L).contains(v2) ? DurationKt.access$durationOfNanos(DurationKt.access$millisToNanos(v2) + DurationKt.access$millisToNanos((v >> 1) - v2 * ((long)v1)) / ((long)v1)) : DurationKt.access$durationOfMillis(v2);
    }

    @Override
    public boolean equals(Object object0) {
        return Duration.equals-impl(this.rawValue, object0);
    }

    public static boolean equals-impl(long v, Object object0) {
        return object0 instanceof Duration ? v == ((Duration)object0).unbox-impl() : false;
    }

    public static final boolean equals-impl0(long v, long v1) {
        return v == v1;
    }

    // 去混淆评级： 低(20)
    public static final long getAbsoluteValue-UwyO8pc(long v) {
        return Duration.isNegative-impl(v) ? Duration.unaryMinus-UwyO8pc(v) : v;
    }

    public static void getHoursComponent$annotations() {
    }

    // 去混淆评级： 低(20)
    public static final int getHoursComponent-impl(long v) {
        return Duration.isInfinite-impl(v) ? 0 : ((int)(Duration.getInWholeHours-impl(v) % 24L));
    }

    @Deprecated(message = "Use inWholeDays property instead or convert toDouble(DAYS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.DAYS)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static void getInDays$annotations() {
    }

    public static final double getInDays-impl(long v) {
        return Duration.toDouble-impl(v, DurationUnit.DAYS);
    }

    @Deprecated(message = "Use inWholeHours property instead or convert toDouble(HOURS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.HOURS)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static void getInHours$annotations() {
    }

    public static final double getInHours-impl(long v) {
        return Duration.toDouble-impl(v, DurationUnit.HOURS);
    }

    @Deprecated(message = "Use inWholeMicroseconds property instead or convert toDouble(MICROSECONDS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.MICROSECONDS)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static void getInMicroseconds$annotations() {
    }

    public static final double getInMicroseconds-impl(long v) {
        return Duration.toDouble-impl(v, DurationUnit.MICROSECONDS);
    }

    @Deprecated(message = "Use inWholeMilliseconds property instead or convert toDouble(MILLISECONDS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.MILLISECONDS)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static void getInMilliseconds$annotations() {
    }

    public static final double getInMilliseconds-impl(long v) {
        return Duration.toDouble-impl(v, DurationUnit.MILLISECONDS);
    }

    @Deprecated(message = "Use inWholeMinutes property instead or convert toDouble(MINUTES) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.MINUTES)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static void getInMinutes$annotations() {
    }

    public static final double getInMinutes-impl(long v) {
        return Duration.toDouble-impl(v, DurationUnit.MINUTES);
    }

    @Deprecated(message = "Use inWholeNanoseconds property instead or convert toDouble(NANOSECONDS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.NANOSECONDS)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static void getInNanoseconds$annotations() {
    }

    public static final double getInNanoseconds-impl(long v) {
        return Duration.toDouble-impl(v, DurationUnit.NANOSECONDS);
    }

    @Deprecated(message = "Use inWholeSeconds property instead or convert toDouble(SECONDS) if a double value is required.", replaceWith = @ReplaceWith(expression = "toDouble(DurationUnit.SECONDS)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static void getInSeconds$annotations() {
    }

    public static final double getInSeconds-impl(long v) {
        return Duration.toDouble-impl(v, DurationUnit.SECONDS);
    }

    public static final long getInWholeDays-impl(long v) {
        return Duration.toLong-impl(v, DurationUnit.DAYS);
    }

    public static final long getInWholeHours-impl(long v) {
        return Duration.toLong-impl(v, DurationUnit.HOURS);
    }

    public static final long getInWholeMicroseconds-impl(long v) {
        return Duration.toLong-impl(v, DurationUnit.MICROSECONDS);
    }

    // 去混淆评级： 低(20)
    public static final long getInWholeMilliseconds-impl(long v) {
        return !Duration.isInMillis-impl(v) || !Duration.isFinite-impl(v) ? Duration.toLong-impl(v, DurationUnit.MILLISECONDS) : v >> 1;
    }

    public static final long getInWholeMinutes-impl(long v) {
        return Duration.toLong-impl(v, DurationUnit.MINUTES);
    }

    public static final long getInWholeNanoseconds-impl(long v) {
        if(Duration.isInNanos-impl(v)) {
            return v >> 1;
        }
        if(v >> 1 > 0x8637BD05AF6L) {
            return 0x7FFFFFFFFFFFFFFFL;
        }
        return v >> 1 >= 0xFFFFF79C842FA50AL ? DurationKt.access$millisToNanos(v >> 1) : 0x8000000000000000L;
    }

    public static final long getInWholeSeconds-impl(long v) {
        return Duration.toLong-impl(v, DurationUnit.SECONDS);
    }

    public static void getMinutesComponent$annotations() {
    }

    // 去混淆评级： 低(20)
    public static final int getMinutesComponent-impl(long v) {
        return Duration.isInfinite-impl(v) ? 0 : ((int)(Duration.getInWholeMinutes-impl(v) % 60L));
    }

    public static void getNanosecondsComponent$annotations() {
    }

    public static final int getNanosecondsComponent-impl(long v) {
        if(Duration.isInfinite-impl(v)) {
            return 0;
        }
        return Duration.isInMillis-impl(v) ? ((int)DurationKt.access$millisToNanos((v >> 1) % 1000L)) : ((int)((v >> 1) % 1000000000L));
    }

    public static void getSecondsComponent$annotations() {
    }

    // 去混淆评级： 低(20)
    public static final int getSecondsComponent-impl(long v) {
        return Duration.isInfinite-impl(v) ? 0 : ((int)(Duration.getInWholeSeconds-impl(v) % 60L));
    }

    // 去混淆评级： 低(20)
    private static final DurationUnit getStorageUnit-impl(long v) {
        return Duration.isInNanos-impl(v) ? DurationUnit.NANOSECONDS : DurationUnit.MILLISECONDS;
    }

    private static final int getUnitDiscriminator-impl(long v) {
        return ((int)v) & 1;
    }

    private static final long getValue-impl(long v) [...] // Inlined contents

    @Override
    public int hashCode() {
        return Duration.hashCode-impl(this.rawValue);
    }

    public static int hashCode-impl(long v) {
        return (int)(v ^ v >>> 0x20);
    }

    public static final boolean isFinite-impl(long v) {
        return !Duration.isInfinite-impl(v);
    }

    private static final boolean isInMillis-impl(long v) {
        return (((int)v) & 1) == 1;
    }

    private static final boolean isInNanos-impl(long v) {
        return (((int)v) & 1) == 0;
    }

    public static final boolean isInfinite-impl(long v) {
        return v == Duration.INFINITE || v == Duration.NEG_INFINITE;
    }

    public static final boolean isNegative-impl(long v) {
        return v < 0L;
    }

    public static final boolean isPositive-impl(long v) {
        return v > 0L;
    }

    public static final long minus-LRDsOJo(long v, long v1) {
        return Duration.plus-LRDsOJo(v, Duration.unaryMinus-UwyO8pc(v1));
    }

    public static final long plus-LRDsOJo(long v, long v1) {
        if(Duration.isInfinite-impl(v)) {
            if(!Duration.isFinite-impl(v1) && (v1 ^ v) < 0L) {
                throw new IllegalArgumentException("Summing infinite durations of different signs yields an undefined result.");
            }
            return v;
        }
        if(Duration.isInfinite-impl(v1)) {
            return v1;
        }
        if((((int)v) & 1) == (((int)v1) & 1)) {
            long v2 = (v >> 1) + (v1 >> 1);
            return Duration.isInNanos-impl(v) ? DurationKt.durationOfNanosNormalized(v2) : DurationKt.durationOfMillisNormalized(v2);
        }
        return Duration.isInMillis-impl(v) ? Duration.addValuesMixedRanges-UwyO8pc(v, v >> 1, v1 >> 1) : Duration.addValuesMixedRanges-UwyO8pc(v, v1 >> 1, v >> 1);
    }

    public static final long times-UwyO8pc(long v, double f) {
        int v1 = MathKt.roundToInt(f);
        if(((double)v1) == f) {
            return Duration.times-UwyO8pc(v, v1);
        }
        DurationUnit durationUnit0 = Duration.getStorageUnit-impl(v);
        return DurationKt.toDuration(Duration.toDouble-impl(v, durationUnit0) * f, durationUnit0);
    }

    public static final long times-UwyO8pc(long v, int v1) {
        if(Duration.isInfinite-impl(v)) {
            if(v1 == 0) {
                throw new IllegalArgumentException("Multiplying infinite duration by zero yields an undefined result.");
            }
            return v1 <= 0 ? Duration.unaryMinus-UwyO8pc(v) : v;
        }
        if(v1 == 0) {
            return 0L;
        }
        long v2 = (v >> 1) * ((long)v1);
        if(Duration.isInNanos-impl(v)) {
            if(new LongRange(0xFFFFFFFF80000001L, 0x7FFFFFFFL).contains(v >> 1)) {
                return DurationKt.durationOfNanos(v2);
            }
            if(v2 / ((long)v1) == v >> 1) {
                return DurationKt.durationOfNanosNormalized(v2);
            }
            long v3 = DurationKt.access$nanosToMillis(v >> 1);
            long v4 = v3 * ((long)v1);
            long v5 = DurationKt.access$nanosToMillis(((v >> 1) - DurationKt.access$millisToNanos(v3)) * ((long)v1)) + v4;
            if(v4 / ((long)v1) == v3 && (v5 ^ v4) >= 0L) {
                return DurationKt.durationOfMillis(RangesKt.coerceIn(v5, new LongRange(0xC000000000000001L, 0x3FFFFFFFFFFFFFFFL)));
            }
            return MathKt.getSign(v >> 1) * MathKt.getSign(v1) <= 0 ? Duration.NEG_INFINITE : Duration.INFINITE;
        }
        if(v2 / ((long)v1) == v >> 1) {
            return DurationKt.durationOfMillis(RangesKt.coerceIn(v2, new LongRange(0xC000000000000001L, 0x3FFFFFFFFFFFFFFFL)));
        }
        return MathKt.getSign(v >> 1) * MathKt.getSign(v1) <= 0 ? Duration.NEG_INFINITE : Duration.INFINITE;
    }

    public static final Object toComponents-impl(long v, Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "action");
        return function20.invoke(Duration.getInWholeSeconds-impl(v), Duration.getNanosecondsComponent-impl(v));
    }

    public static final Object toComponents-impl(long v, Function3 function30) {
        Intrinsics.checkNotNullParameter(function30, "action");
        return function30.invoke(Duration.getInWholeMinutes-impl(v), Duration.getSecondsComponent-impl(v), Duration.getNanosecondsComponent-impl(v));
    }

    public static final Object toComponents-impl(long v, Function4 function40) {
        Intrinsics.checkNotNullParameter(function40, "action");
        return function40.invoke(Duration.getInWholeHours-impl(v), Duration.getMinutesComponent-impl(v), Duration.getSecondsComponent-impl(v), Duration.getNanosecondsComponent-impl(v));
    }

    public static final Object toComponents-impl(long v, Function5 function50) {
        Intrinsics.checkNotNullParameter(function50, "action");
        return function50.invoke(Duration.getInWholeDays-impl(v), Duration.getHoursComponent-impl(v), Duration.getMinutesComponent-impl(v), Duration.getSecondsComponent-impl(v), Duration.getNanosecondsComponent-impl(v));
    }

    public static final double toDouble-impl(long v, DurationUnit durationUnit0) {
        Intrinsics.checkNotNullParameter(durationUnit0, "unit");
        if(v == Duration.INFINITE) {
            return Infinity;
        }
        return v == Duration.NEG_INFINITE ? -Infinity : DurationUnitKt.convertDurationUnit(v >> 1, Duration.getStorageUnit-impl(v), durationUnit0);
    }

    public static final int toInt-impl(long v, DurationUnit durationUnit0) {
        Intrinsics.checkNotNullParameter(durationUnit0, "unit");
        return (int)RangesKt.coerceIn(Duration.toLong-impl(v, durationUnit0), 0xFFFFFFFF80000000L, 0x7FFFFFFFL);
    }

    public static final String toIsoString-impl(long v) {
        StringBuilder stringBuilder0 = new StringBuilder();
        if(Duration.isNegative-impl(v)) {
            stringBuilder0.append('-');
        }
        stringBuilder0.append("PT");
        long v1 = Duration.getAbsoluteValue-UwyO8pc(v);
        long v2 = Duration.getInWholeHours-impl(v1);
        int v3 = Duration.getMinutesComponent-impl(v1);
        int v4 = Duration.getSecondsComponent-impl(v1);
        int v5 = Duration.getNanosecondsComponent-impl(v1);
        long v6 = Duration.isInfinite-impl(v) ? 0x9184E729FFFL : v2;
        boolean z = true;
        boolean z1 = v4 != 0 || v5 != 0;
        if(v3 == 0 && (!z1 || v6 == 0L)) {
            z = false;
        }
        if(v6 != 0L) {
            stringBuilder0.append(v6);
            stringBuilder0.append('H');
        }
        if(z) {
            stringBuilder0.append(v3);
            stringBuilder0.append('M');
        }
        if(z1 || v6 == 0L && !z) {
            Duration.appendFractional-impl(v, stringBuilder0, v4, v5, 9, "S", true);
        }
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public static final long toLong-impl(long v, DurationUnit durationUnit0) {
        Intrinsics.checkNotNullParameter(durationUnit0, "unit");
        if(v == Duration.INFINITE) {
            return 0x7FFFFFFFFFFFFFFFL;
        }
        return v == Duration.NEG_INFINITE ? 0x8000000000000000L : DurationUnitKt.convertDurationUnit(v >> 1, Duration.getStorageUnit-impl(v), durationUnit0);
    }

    @Deprecated(message = "Use inWholeMilliseconds property instead.", replaceWith = @ReplaceWith(expression = "this.inWholeMilliseconds", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static final long toLongMilliseconds-impl(long v) {
        return Duration.getInWholeMilliseconds-impl(v);
    }

    @Deprecated(message = "Use inWholeNanoseconds property instead.", replaceWith = @ReplaceWith(expression = "this.inWholeNanoseconds", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.8", hiddenSince = "1.9", warningSince = "1.5")
    public static final long toLongNanoseconds-impl(long v) {
        return Duration.getInWholeNanoseconds-impl(v);
    }

    @Override
    public String toString() {
        return Duration.toString-impl(this.rawValue);
    }

    public static String toString-impl(long v) {
        if(v == 0L) {
            return "0s";
        }
        if(v == 0x7FFFFFFFFFFFFFFFL) {
            return "Infinity";
        }
        if(v == Duration.NEG_INFINITE) {
            return "-Infinity";
        }
        boolean z = Duration.isNegative-impl(v);
        StringBuilder stringBuilder0 = new StringBuilder();
        if(z) {
            stringBuilder0.append('-');
        }
        long v1 = Duration.getAbsoluteValue-UwyO8pc(v);
        long v2 = Duration.getInWholeDays-impl(v1);
        int v3 = Duration.getHoursComponent-impl(v1);
        int v4 = Duration.getMinutesComponent-impl(v1);
        int v5 = Duration.getSecondsComponent-impl(v1);
        int v6 = Duration.getNanosecondsComponent-impl(v1);
        int v7 = 0;
        boolean z1 = v5 != 0 || v6 != 0;
        if(v2 != 0L) {
            stringBuilder0.append(v2);
            stringBuilder0.append('d');
            v7 = 1;
        }
        if(v3 != 0 || v2 != 0L && (v4 != 0 || z1)) {
            if(v7 > 0) {
                stringBuilder0.append(' ');
            }
            stringBuilder0.append(v3);
            stringBuilder0.append('h');
            ++v7;
        }
        if(v4 != 0 || z1 && (v3 != 0 || v2 != 0L)) {
            if(v7 > 0) {
                stringBuilder0.append(' ');
            }
            stringBuilder0.append(v4);
            stringBuilder0.append('m');
            ++v7;
        }
        if(z1) {
            if(v7 > 0) {
                stringBuilder0.append(' ');
            }
            if(v5 != 0 || v2 != 0L || v3 != 0 || v4 != 0) {
                Duration.appendFractional-impl(v, stringBuilder0, v5, v6, 9, "s", false);
            }
            else if(v6 >= 1000000) {
                Duration.appendFractional-impl(v, stringBuilder0, v6 / 1000000, v6 % 1000000, 6, "ms", false);
            }
            else if(v6 >= 1000) {
                Duration.appendFractional-impl(v, stringBuilder0, v6 / 1000, v6 % 1000, 3, "us", false);
            }
            else {
                stringBuilder0.append(v6);
                stringBuilder0.append("ns");
            }
            ++v7;
        }
        if(z && v7 > 1) {
            stringBuilder0.insert(1, '(').append(')');
        }
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public static final String toString-impl(long v, DurationUnit durationUnit0, int v1) {
        Intrinsics.checkNotNullParameter(durationUnit0, "unit");
        if(v1 < 0) {
            throw new IllegalArgumentException(("decimals must be not negative, but was " + v1).toString());
        }
        double f = Duration.toDouble-impl(v, durationUnit0);
        return Double.isInfinite(f) ? String.valueOf(f) : DurationJvmKt.formatToExactDecimals(f, RangesKt.coerceAtMost(v1, 12)) + DurationUnitKt.shortName(durationUnit0);
    }

    public static String toString-impl$default(long v, DurationUnit durationUnit0, int v1, int v2, Object object0) {
        if((v2 & 2) != 0) {
            v1 = 0;
        }
        return Duration.toString-impl(v, durationUnit0, v1);
    }

    public static final long truncateTo-UwyO8pc$kotlin_stdlib(long v, DurationUnit durationUnit0) {
        Intrinsics.checkNotNullParameter(durationUnit0, "unit");
        DurationUnit durationUnit1 = Duration.getStorageUnit-impl(v);
        return durationUnit0.compareTo(durationUnit1) <= 0 || Duration.isInfinite-impl(v) ? v : DurationKt.toDuration((v >> 1) - (v >> 1) % DurationUnitKt.convertDurationUnit(1L, durationUnit0, durationUnit1), durationUnit1);
    }

    public static final long unaryMinus-UwyO8pc(long v) {
        return DurationKt.durationOf(-(v >> 1), ((int)v) & 1);
    }

    public final long unbox-impl() {
        return this.rawValue;
    }
}

