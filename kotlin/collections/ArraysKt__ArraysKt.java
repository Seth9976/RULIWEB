package kotlin.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.collections.unsigned.UArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A5\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u00032\u0010\u0010\u0004\u001A\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0003H\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001A#\u0010\u0007\u001A\u00020\b\"\u0004\b\u0000\u0010\u0002*\f\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0018\u00010\u0003H\u0001¢\u0006\u0004\b\t\u0010\n\u001A?\u0010\u000B\u001A\u00020\f\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\n\u0010\r\u001A\u00060\u000Ej\u0002`\u000F2\u0010\u0010\u0010\u001A\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00030\u0011H\u0002¢\u0006\u0004\b\u0012\u0010\u0013\u001A+\u0010\u0014\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0015\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u000E\b\u0001\u0012\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00030\u0003¢\u0006\u0002\u0010\u0016\u001A;\u0010\u0017\u001A\u0002H\u0018\"\u0010\b\u0000\u0010\u0019*\u0006\u0012\u0002\b\u00030\u0003*\u0002H\u0018\"\u0004\b\u0001\u0010\u0018*\u0002H\u00192\f\u0010\u001A\u001A\b\u0012\u0004\u0012\u0002H\u00180\u001BH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u001C\u001A)\u0010\u001D\u001A\u00020\u0001*\b\u0012\u0002\b\u0003\u0018\u00010\u0003H\u0087\b\u0082\u0002\u000E\n\f\b\u0000\u0012\u0002\u0018\u0001\u001A\u0004\b\u0003\u0010\u0000¢\u0006\u0002\u0010\u001E\u001AG\u0010\u001F\u001A\u001A\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00180\u00150 \"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0018*\u0016\u0012\u0012\b\u0001\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00180 0\u0003¢\u0006\u0002\u0010!\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\""}, d2 = {"contentDeepEqualsImpl", "", "T", "", "other", "contentDeepEquals", "([Ljava/lang/Object;[Ljava/lang/Object;)Z", "contentDeepToStringImpl", "", "contentDeepToString", "([Ljava/lang/Object;)Ljava/lang/String;", "contentDeepToStringInternal", "", "result", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "processed", "", "contentDeepToStringInternal$ArraysKt__ArraysKt", "([Ljava/lang/Object;Ljava/lang/StringBuilder;Ljava/util/List;)V", "flatten", "", "([[Ljava/lang/Object;)Ljava/util/List;", "ifEmpty", "R", "C", "defaultValue", "Lkotlin/Function0;", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNullOrEmpty", "([Ljava/lang/Object;)Z", "unzip", "Lkotlin/Pair;", "([Lkotlin/Pair;)Lkotlin/Pair;", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/collections/ArraysKt")
class ArraysKt__ArraysKt extends ArraysKt__ArraysJVMKt {
    public static final boolean contentDeepEquals(Object[] arr_object, Object[] arr_object1) {
        if(arr_object == arr_object1) {
            return true;
        }
        if(arr_object != null && arr_object1 != null && arr_object.length == arr_object1.length) {
            for(int v = 0; v < arr_object.length; ++v) {
                Object object0 = arr_object[v];
                Object object1 = arr_object1[v];
                if(object0 != object1) {
                    if(object0 == null || object1 == null) {
                        return false;
                    }
                    if(object0 instanceof Object[] && object1 instanceof Object[]) {
                        if(!ArraysKt.contentDeepEquals(((Object[])object0), ((Object[])object1))) {
                            return false;
                        }
                    }
                    else if(object0 instanceof byte[] && object1 instanceof byte[]) {
                        if(!Arrays.equals(((byte[])object0), ((byte[])object1))) {
                            return false;
                        }
                    }
                    else if(object0 instanceof short[] && object1 instanceof short[]) {
                        if(!Arrays.equals(((short[])object0), ((short[])object1))) {
                            return false;
                        }
                    }
                    else if(object0 instanceof int[] && object1 instanceof int[]) {
                        if(!Arrays.equals(((int[])object0), ((int[])object1))) {
                            return false;
                        }
                    }
                    else if(object0 instanceof long[] && object1 instanceof long[]) {
                        if(!Arrays.equals(((long[])object0), ((long[])object1))) {
                            return false;
                        }
                    }
                    else if(object0 instanceof float[] && object1 instanceof float[]) {
                        if(!Arrays.equals(((float[])object0), ((float[])object1))) {
                            return false;
                        }
                    }
                    else if(object0 instanceof double[] && object1 instanceof double[]) {
                        if(!Arrays.equals(((double[])object0), ((double[])object1))) {
                            return false;
                        }
                    }
                    else if(object0 instanceof char[] && object1 instanceof char[]) {
                        if(!Arrays.equals(((char[])object0), ((char[])object1))) {
                            return false;
                        }
                    }
                    else if(object0 instanceof boolean[] && object1 instanceof boolean[]) {
                        if(!Arrays.equals(((boolean[])object0), ((boolean[])object1))) {
                            return false;
                        }
                    }
                    else if(object0 instanceof UByteArray && object1 instanceof UByteArray) {
                        if(!UArraysKt.contentEquals-kV0jMPg(((UByteArray)object0).unbox-impl(), ((UByteArray)object1).unbox-impl())) {
                            return false;
                        }
                    }
                    else if(object0 instanceof UShortArray && object1 instanceof UShortArray) {
                        if(!UArraysKt.contentEquals-FGO6Aew(((UShortArray)object0).unbox-impl(), ((UShortArray)object1).unbox-impl())) {
                            return false;
                        }
                    }
                    else if(object0 instanceof UIntArray && object1 instanceof UIntArray) {
                        if(!UArraysKt.contentEquals-KJPZfPQ(((UIntArray)object0).unbox-impl(), ((UIntArray)object1).unbox-impl())) {
                            return false;
                        }
                    }
                    else if(object0 instanceof ULongArray && object1 instanceof ULongArray) {
                        if(!UArraysKt.contentEquals-lec5QzE(((ULongArray)object0).unbox-impl(), ((ULongArray)object1).unbox-impl())) {
                            return false;
                        }
                    }
                    else if(!Intrinsics.areEqual(object0, object1)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public static final String contentDeepToString(Object[] arr_object) {
        if(arr_object == null) {
            return "null";
        }
        StringBuilder stringBuilder0 = new StringBuilder(RangesKt.coerceAtMost(arr_object.length, 0x19999999) * 5 + 2);
        ArraysKt__ArraysKt.contentDeepToStringInternal$ArraysKt__ArraysKt(arr_object, stringBuilder0, new ArrayList());
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder(capacity).…builderAction).toString()");
        return s;
    }

    private static final void contentDeepToStringInternal$ArraysKt__ArraysKt(Object[] arr_object, StringBuilder stringBuilder0, List list0) {
        if(list0.contains(arr_object)) {
            stringBuilder0.append("[...]");
            return;
        }
        list0.add(arr_object);
        stringBuilder0.append('[');
        for(int v = 0; v < arr_object.length; ++v) {
            if(v != 0) {
                stringBuilder0.append(", ");
            }
            byte[] arr_b = null;
            Object object0 = arr_object[v];
            if(object0 == null) {
                stringBuilder0.append("null");
            }
            else if(object0 instanceof Object[]) {
                ArraysKt__ArraysKt.contentDeepToStringInternal$ArraysKt__ArraysKt(((Object[])object0), stringBuilder0, list0);
            }
            else if(object0 instanceof byte[]) {
                String s = Arrays.toString(((byte[])object0));
                Intrinsics.checkNotNullExpressionValue(s, "toString(this)");
                stringBuilder0.append(s);
            }
            else if(object0 instanceof short[]) {
                String s1 = Arrays.toString(((short[])object0));
                Intrinsics.checkNotNullExpressionValue(s1, "toString(this)");
                stringBuilder0.append(s1);
            }
            else if(object0 instanceof int[]) {
                String s2 = Arrays.toString(((int[])object0));
                Intrinsics.checkNotNullExpressionValue(s2, "toString(this)");
                stringBuilder0.append(s2);
            }
            else if(object0 instanceof long[]) {
                String s3 = Arrays.toString(((long[])object0));
                Intrinsics.checkNotNullExpressionValue(s3, "toString(this)");
                stringBuilder0.append(s3);
            }
            else if(object0 instanceof float[]) {
                String s4 = Arrays.toString(((float[])object0));
                Intrinsics.checkNotNullExpressionValue(s4, "toString(this)");
                stringBuilder0.append(s4);
            }
            else if(object0 instanceof double[]) {
                String s5 = Arrays.toString(((double[])object0));
                Intrinsics.checkNotNullExpressionValue(s5, "toString(this)");
                stringBuilder0.append(s5);
            }
            else if(object0 instanceof char[]) {
                String s6 = Arrays.toString(((char[])object0));
                Intrinsics.checkNotNullExpressionValue(s6, "toString(this)");
                stringBuilder0.append(s6);
            }
            else if(object0 instanceof boolean[]) {
                String s7 = Arrays.toString(((boolean[])object0));
                Intrinsics.checkNotNullExpressionValue(s7, "toString(this)");
                stringBuilder0.append(s7);
            }
            else if(object0 instanceof UByteArray) {
                if(((UByteArray)object0) != null) {
                    arr_b = ((UByteArray)object0).unbox-impl();
                }
                stringBuilder0.append(UArraysKt.contentToString-2csIQuQ(arr_b));
            }
            else if(object0 instanceof UShortArray) {
                if(((UShortArray)object0) != null) {
                    arr_b = ((UShortArray)object0).unbox-impl();
                }
                stringBuilder0.append(UArraysKt.contentToString-d-6D3K8(((short[])arr_b)));
            }
            else if(object0 instanceof UIntArray) {
                if(((UIntArray)object0) != null) {
                    arr_b = ((UIntArray)object0).unbox-impl();
                }
                stringBuilder0.append(UArraysKt.contentToString-XUkPCBk(((int[])arr_b)));
            }
            else if(object0 instanceof ULongArray) {
                if(((ULongArray)object0) != null) {
                    arr_b = ((ULongArray)object0).unbox-impl();
                }
                stringBuilder0.append(UArraysKt.contentToString-uLth9ew(((long[])arr_b)));
            }
            else {
                stringBuilder0.append(object0.toString());
            }
        }
        stringBuilder0.append(']');
        list0.remove(CollectionsKt.getLastIndex(list0));
    }

    public static final List flatten(Object[][] arr2_object) {
        Intrinsics.checkNotNullParameter(arr2_object, "<this>");
        int v2 = 0;
        for(int v1 = 0; v1 < arr2_object.length; ++v1) {
            v2 += arr2_object[v1].length;
        }
        ArrayList arrayList0 = new ArrayList(v2);
        for(int v = 0; v < arr2_object.length; ++v) {
            CollectionsKt.addAll(arrayList0, arr2_object[v]);
        }
        return arrayList0;
    }

    private static final Object ifEmpty(Object[] arr_object, Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "defaultValue");
        return arr_object.length == 0 ? function00.invoke() : arr_object;
    }

    private static final boolean isNullOrEmpty(Object[] arr_object) {
        return arr_object == null || arr_object.length == 0;
    }

    public static final Pair unzip(Pair[] arr_pair) {
        Intrinsics.checkNotNullParameter(arr_pair, "<this>");
        ArrayList arrayList0 = new ArrayList(arr_pair.length);
        ArrayList arrayList1 = new ArrayList(arr_pair.length);
        for(int v = 0; v < arr_pair.length; ++v) {
            Pair pair0 = arr_pair[v];
            arrayList0.add(pair0.getFirst());
            arrayList1.add(pair0.getSecond());
        }
        return TuplesKt.to(arrayList0, arrayList1);
    }
}

