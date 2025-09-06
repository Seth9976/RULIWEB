package androidx.collection;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.UByte..ExternalSyntheticBackport0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0016\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\r\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\u0007\b\u0004\u00A2\u0006\u0002\u0010\u0002J:\u0010\u0010\u001A\u00020\u00112!\u0010\u0012\u001A\u001D\u0012\u0013\u0012\u00110\u0014\u00A2\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00110\u0013H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\u0006\u0010\u0018\u001A\u00020\u0011J:\u0010\u0018\u001A\u00020\u00112!\u0010\u0012\u001A\u001D\u0012\u0013\u0012\u00110\u0014\u00A2\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00110\u0013H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\u0011\u0010\u0019\u001A\u00020\u00112\u0006\u0010\u0017\u001A\u00020\u0014H\u0086\u0002J\b\u0010\u001A\u001A\u00020\u0004H\u0007J:\u0010\u001A\u001A\u00020\u00042!\u0010\u0012\u001A\u001D\u0012\u0013\u0012\u00110\u0014\u00A2\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00110\u0013H\u0087\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\u0013\u0010\u001B\u001A\u00020\u00112\b\u0010\u001C\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\u0016\u0010\u001D\u001A\u00020\u00042\u0006\u0010\u0017\u001A\u00020\u0014H\u0080\b\u00A2\u0006\u0002\b\u001EJ\t\u0010\u001F\u001A\u00020\u0014H\u0086\bJ:\u0010\u001F\u001A\u00020\u00142!\u0010\u0012\u001A\u001D\u0012\u0013\u0012\u00110\u0014\u00A2\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00110\u0013H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J:\u0010 \u001A\u00020!2!\u0010\"\u001A\u001D\u0012\u0013\u0012\u00110\u0014\u00A2\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020!0\u0013H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J:\u0010#\u001A\u00020!2!\u0010\"\u001A\u001D\u0012\u0013\u0012\u00110\u0004\u00A2\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b($\u0012\u0004\u0012\u00020!0\u0013H\u0081\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\b\u0010%\u001A\u00020\u0004H\u0016J\u0006\u0010&\u001A\u00020\u0011J\u0006\u0010\'\u001A\u00020\u0011J:\u0010(\u001A\u00020)2\b\b\u0002\u0010*\u001A\u00020+2\b\b\u0002\u0010,\u001A\u00020+2\b\b\u0002\u0010-\u001A\u00020+2\b\b\u0002\u0010.\u001A\u00020\u00042\b\b\u0002\u0010/\u001A\u00020+H\u0007JT\u0010(\u001A\u00020)2\b\b\u0002\u0010*\u001A\u00020+2\b\b\u0002\u0010,\u001A\u00020+2\b\b\u0002\u0010-\u001A\u00020+2\b\b\u0002\u0010.\u001A\u00020\u00042\b\b\u0002\u0010/\u001A\u00020+2\u0014\b\u0004\u00100\u001A\u000E\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020+0\u0013H\u0087\b\u00F8\u0001\u0000J\u0006\u00101\u001A\u00020\u0011J\b\u00102\u001A\u00020)H\u0016R\u0012\u0010\u0003\u001A\u00020\u00048\u0000@\u0000X\u0081\u000E\u00A2\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001A\u00020\u00048\u0000@\u0000X\u0081\u000E\u00A2\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001A\u00020\u00048G\u00A2\u0006\u0006\u001A\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001A\u00020\n8\u0000@\u0000X\u0081\u000E\u00A2\u0006\b\n\u0000\u0012\u0004\b\u000B\u0010\u0002R\u0018\u0010\f\u001A\u00020\n8\u0000@\u0000X\u0081\u000E\u00A2\u0006\b\n\u0000\u0012\u0004\b\r\u0010\u0002R\u0011\u0010\u000E\u001A\u00020\u00048G\u00A2\u0006\u0006\u001A\u0004\b\u000F\u0010\b\u0082\u0001\u00013\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u00064"}, d2 = {"Landroidx/collection/LongSet;", "", "()V", "_capacity", "", "_size", "capacity", "getCapacity", "()I", "elements", "", "getElements$annotations", "metadata", "getMetadata$annotations", "size", "getSize", "all", "", "predicate", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "element", "any", "contains", "count", "equals", "other", "findElementIndex", "findElementIndex$collection", "first", "forEach", "", "block", "forEachIndex", "index", "hashCode", "isEmpty", "isNotEmpty", "joinToString", "", "separator", "", "prefix", "postfix", "limit", "truncated", "transform", "none", "toString", "Landroidx/collection/MutableLongSet;", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class LongSet {
    public int _capacity;
    public int _size;
    public long[] elements;
    public long[] metadata;

    private LongSet() {
        this.metadata = ScatterMapKt.EmptyGroup;
        this.elements = LongSetKt.getEmptyLongArray();
    }

    public LongSet(DefaultConstructorMarker defaultConstructorMarker0) {
    }

    public final boolean all(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        long[] arr_v = this.elements;
        long[] arr_v1 = this.metadata;
        int v = arr_v1.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v1[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L && !((Boolean)function10.invoke(((long)arr_v[(v1 << 3) + v4]))).booleanValue()) {
                            return false;
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_17;
                    }
                    break;
                }
            label_17:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
        return true;
    }

    public final boolean any() {
        return this._size != 0;
    }

    public final boolean any(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        long[] arr_v = this.elements;
        long[] arr_v1 = this.metadata;
        int v = arr_v1.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v1[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L && ((Boolean)function10.invoke(((long)arr_v[(v1 << 3) + v4]))).booleanValue()) {
                            return true;
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_17;
                    }
                    break;
                }
            label_17:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
        return false;
    }

    public final boolean contains(long v) {
        int v1 = (int)(v ^ v >>> 0x20);
        int v2 = v1 * 0xCC9E2D51 ^ v1 * 0xCC9E2D51 << 16;
        int v3 = this._capacity;
        int v4 = v2 >>> 7 & v3;
        int v5 = 0;
        while(true) {
            int v6 = (v4 & 7) << 3;
            long v7 = this.metadata[(v4 >> 3) + 1] << 0x40 - v6 & -((long)v6) >> 0x3F | this.metadata[v4 >> 3] >>> v6;
            long v8 = ((long)(v2 & 0x7F)) * 0x101010101010101L ^ v7;
            for(long v9 = ~v8 & v8 - 0x101010101010101L & 0x8080808080808080L; v9 != 0L; v9 &= v9 - 1L) {
                int v10 = (Long.numberOfTrailingZeros(v9) >> 3) + v4 & v3;
                if(this.elements[v10] == v) {
                    return v10 >= 0;
                }
            }
            if((v7 & ~v7 << 6 & 0x8080808080808080L) != 0L) {
                return false;
            }
            v5 += 8;
            v4 = v4 + v5 & v3;
        }
    }

    public final int count() {
        return this._size;
    }

    public final int count(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        long[] arr_v = this.elements;
        long[] arr_v1 = this.metadata;
        int v = arr_v1.length - 2;
        if(v >= 0) {
            int v2 = 0;
            for(int v1 = 0; true; ++v1) {
                long v3 = arr_v1[v1];
                if((~v3 << 7 & v3 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v4 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v5 = 0; v5 < v4; ++v5) {
                        if((0xFFL & v3) < 0x80L && ((Boolean)function10.invoke(((long)arr_v[(v1 << 3) + v5]))).booleanValue()) {
                            ++v2;
                        }
                        v3 >>= 8;
                    }
                    if(v4 != 8) {
                        return v2;
                    }
                }
                if(v1 == v) {
                    break;
                }
            }
            return v2;
        }
        return 0;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof LongSet)) {
            return false;
        }
        if(((LongSet)object0)._size != this._size) {
            return false;
        }
        long[] arr_v = this.elements;
        long[] arr_v1 = this.metadata;
        int v = arr_v1.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v1[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L && !((LongSet)object0).contains(arr_v[(v1 << 3) + v4])) {
                            return false;
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_22;
                    }
                    break;
                }
            label_22:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
        return true;
    }

    public final int findElementIndex$collection(long v) {
        int v1 = (int)(v ^ v >>> 0x20);
        int v2 = v1 * 0xCC9E2D51 ^ v1 * 0xCC9E2D51 << 16;
        int v3 = this._capacity;
        int v4 = v2 >>> 7 & v3;
        int v5 = 0;
        while(true) {
            int v6 = (v4 & 7) << 3;
            long v7 = this.metadata[(v4 >> 3) + 1] << 0x40 - v6 & -((long)v6) >> 0x3F | this.metadata[v4 >> 3] >>> v6;
            long v8 = ((long)(v2 & 0x7F)) * 0x101010101010101L ^ v7;
            for(long v9 = ~v8 & v8 - 0x101010101010101L & 0x8080808080808080L; v9 != 0L; v9 &= v9 - 1L) {
                int v10 = (Long.numberOfTrailingZeros(v9) >> 3) + v4 & v3;
                if(this.elements[v10] == v) {
                    return v10;
                }
            }
            if((v7 & ~v7 << 6 & 0x8080808080808080L) != 0L) {
                return -1;
            }
            v5 += 8;
            v4 = v4 + v5 & v3;
        }
    }

    public final long first() {
        long[] arr_v = this.elements;
        long[] arr_v1 = this.metadata;
        int v = arr_v1.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v1[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L) {
                            return arr_v[(v1 << 3) + v4];
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_16;
                    }
                    break;
                }
            label_16:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
        throw new NoSuchElementException("The LongSet is empty");
    }

    public final long first(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        long[] arr_v = this.elements;
        long[] arr_v1 = this.metadata;
        int v = arr_v1.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v1[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L) {
                            long v5 = arr_v[(v1 << 3) + v4];
                            if(((Boolean)function10.invoke(v5)).booleanValue()) {
                                return v5;
                            }
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_19;
                    }
                    break;
                }
            label_19:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
        throw new NoSuchElementException("Could not find a match");
    }

    public final void forEach(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "block");
        long[] arr_v = this.elements;
        long[] arr_v1 = this.metadata;
        int v = arr_v1.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v1[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L) {
                            function10.invoke(((long)arr_v[(v1 << 3) + v4]));
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_17;
                    }
                    break;
                }
            label_17:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
    }

    public final void forEachIndex(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "block");
        long[] arr_v = this.metadata;
        int v = arr_v.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L) {
                            function10.invoke(((int)((v1 << 3) + v4)));
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_16;
                    }
                    break;
                }
            label_16:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
    }

    public final int getCapacity() {
        return this._capacity;
    }

    public static void getElements$annotations() {
    }

    public static void getMetadata$annotations() {
    }

    public final int getSize() {
        return this._size;
    }

    @Override
    public int hashCode() {
        long[] arr_v = this.elements;
        long[] arr_v1 = this.metadata;
        int v = arr_v1.length - 2;
        if(v >= 0) {
            int v2 = 0;
            for(int v1 = 0; true; ++v1) {
                long v3 = arr_v1[v1];
                if((~v3 << 7 & v3 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v4 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v5 = 0; v5 < v4; ++v5) {
                        if((0xFFL & v3) < 0x80L) {
                            v2 += UByte..ExternalSyntheticBackport0.m(arr_v[(v1 << 3) + v5]);
                        }
                        v3 >>= 8;
                    }
                    if(v4 != 8) {
                        return v2;
                    }
                }
                if(v1 == v) {
                    break;
                }
            }
            return v2;
        }
        return 0;
    }

    public final boolean isEmpty() {
        return this._size == 0;
    }

    public final boolean isNotEmpty() {
        return this._size != 0;
    }

    public final String joinToString() {
        return LongSet.joinToString$default(this, null, null, null, 0, null, 0x1F, null);
    }

    public final String joinToString(CharSequence charSequence0) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        return LongSet.joinToString$default(this, charSequence0, null, null, 0, null, 30, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        return LongSet.joinToString$default(this, charSequence0, charSequence1, null, 0, null, 28, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        return LongSet.joinToString$default(this, charSequence0, charSequence1, charSequence2, 0, null, 24, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        return LongSet.joinToString$default(this, charSequence0, charSequence1, charSequence2, v, null, 16, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, CharSequence charSequence3) {
        int v9;
        int v7;
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        Intrinsics.checkNotNullParameter(charSequence3, "truncated");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(charSequence1);
        long[] arr_v = this.elements;
        long[] arr_v1 = this.metadata;
        int v1 = arr_v1.length - 2;
        if(v1 >= 0) {
            int v2 = 0;
            int v3 = 0;
            while(true) {
                long v4 = arr_v1[v2];
                if((~v4 << 7 & v4 & 0x8080808080808080L) == 0x8080808080808080L) {
                    goto label_38;
                }
                int v5 = 8 - (~(v2 - v1) >>> 0x1F);
                int v6 = 0;
                while(v6 < v5) {
                    if((v4 & 0xFFL) < 0x80L) {
                        v7 = v2;
                        long v8 = arr_v[(v2 << 3) + v6];
                        if(v3 == v) {
                            stringBuilder0.append(charSequence3);
                            goto label_45;
                        }
                        if(v3 != 0) {
                            stringBuilder0.append(charSequence0);
                        }
                        stringBuilder0.append(v8);
                        ++v3;
                    }
                    else {
                        v7 = v2;
                    }
                    v4 >>= 8;
                    ++v6;
                    v2 = v7;
                }
                if(v5 == 8) {
                    v9 = v2;
                    goto label_39;
                }
                else {
                    stringBuilder0.append(charSequence2);
                    goto label_45;
                label_38:
                    v9 = v2;
                label_39:
                    if(v9 != v1) {
                        v2 = v9 + 1;
                        continue;
                    }
                    break;
                }
                goto label_45;
            }
        }
        stringBuilder0.append(charSequence2);
    label_45:
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, CharSequence charSequence3, Function1 function10) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        Intrinsics.checkNotNullParameter(charSequence3, "truncated");
        Intrinsics.checkNotNullParameter(function10, "transform");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(charSequence1);
        long[] arr_v = this.elements;
        long[] arr_v1 = this.metadata;
        int v1 = arr_v1.length - 2;
        if(v1 >= 0) {
            int v2 = 0;
            int v3 = 0;
            while(true) {
                long v4 = arr_v1[v2];
                if((~v4 << 7 & v4 & 0x8080808080808080L) == 0x8080808080808080L) {
                    goto label_33;
                }
                int v5 = 8 - (~(v2 - v1) >>> 0x1F);
                int v6 = 0;
                while(v6 < v5) {
                    if((v4 & 0xFFL) < 0x80L) {
                        long v7 = arr_v[(v2 << 3) + v6];
                        if(v3 == v) {
                            stringBuilder0.append(charSequence3);
                            goto label_39;
                        }
                        if(v3 != 0) {
                            stringBuilder0.append(charSequence0);
                        }
                        stringBuilder0.append(((CharSequence)function10.invoke(v7)));
                        ++v3;
                    }
                    v4 >>= 8;
                    ++v6;
                }
                if(v5 == 8) {
                label_33:
                    if(v2 != v1) {
                        ++v2;
                        continue;
                    }
                    break;
                }
                else {
                    stringBuilder0.append(charSequence2);
                }
                goto label_39;
            }
        }
        stringBuilder0.append(charSequence2);
    label_39:
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, Function1 function10) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        Intrinsics.checkNotNullParameter(function10, "transform");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(charSequence1);
        long[] arr_v = this.elements;
        long[] arr_v1 = this.metadata;
        int v1 = arr_v1.length - 2;
        if(v1 >= 0) {
            int v2 = 0;
            int v3 = 0;
            while(true) {
                long v4 = arr_v1[v2];
                if((~v4 << 7 & v4 & 0x8080808080808080L) == 0x8080808080808080L) {
                    goto label_32;
                }
                int v5 = 8 - (~(v2 - v1) >>> 0x1F);
                int v6 = 0;
                while(v6 < v5) {
                    if((v4 & 0xFFL) < 0x80L) {
                        long v7 = arr_v[(v2 << 3) + v6];
                        if(v3 == v) {
                            stringBuilder0.append("...");
                            goto label_38;
                        }
                        if(v3 != 0) {
                            stringBuilder0.append(charSequence0);
                        }
                        stringBuilder0.append(((CharSequence)function10.invoke(v7)));
                        ++v3;
                    }
                    v4 >>= 8;
                    ++v6;
                }
                if(v5 == 8) {
                label_32:
                    if(v2 != v1) {
                        ++v2;
                        continue;
                    }
                    break;
                }
                else {
                    stringBuilder0.append(charSequence2);
                }
                goto label_38;
            }
        }
        stringBuilder0.append(charSequence2);
    label_38:
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, Function1 function10) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        Intrinsics.checkNotNullParameter(function10, "transform");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(charSequence1);
        long[] arr_v = this.elements;
        long[] arr_v1 = this.metadata;
        int v = arr_v1.length - 2;
        if(v >= 0) {
            int v1 = 0;
            int v2 = 0;
            while(true) {
                long v3 = arr_v1[v1];
                if((~v3 << 7 & v3 & 0x8080808080808080L) == 0x8080808080808080L) {
                    goto label_32;
                }
                int v4 = 8 - (~(v1 - v) >>> 0x1F);
                int v5 = 0;
                while(v5 < v4) {
                    if((v3 & 0xFFL) < 0x80L) {
                        long v6 = arr_v[(v1 << 3) + v5];
                        if(v2 == -1) {
                            stringBuilder0.append("...");
                            goto label_38;
                        }
                        if(v2 != 0) {
                            stringBuilder0.append(charSequence0);
                        }
                        stringBuilder0.append(((CharSequence)function10.invoke(v6)));
                        ++v2;
                    }
                    v3 >>= 8;
                    ++v5;
                }
                if(v4 == 8) {
                label_32:
                    if(v1 != v) {
                        ++v1;
                        continue;
                    }
                    break;
                }
                else {
                    stringBuilder0.append(charSequence2);
                }
                goto label_38;
            }
        }
        stringBuilder0.append(charSequence2);
    label_38:
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, Function1 function10) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(function10, "transform");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(charSequence1);
        long[] arr_v = this.elements;
        long[] arr_v1 = this.metadata;
        int v = arr_v1.length - 2;
        if(v >= 0) {
            int v1 = 0;
            int v2 = 0;
            while(true) {
                long v3 = arr_v1[v1];
                if((~v3 << 7 & v3 & 0x8080808080808080L) == 0x8080808080808080L) {
                    goto label_31;
                }
                int v4 = 8 - (~(v1 - v) >>> 0x1F);
                int v5 = 0;
                while(v5 < v4) {
                    if((v3 & 0xFFL) < 0x80L) {
                        long v6 = arr_v[(v1 << 3) + v5];
                        if(v2 == -1) {
                            stringBuilder0.append("...");
                            goto label_37;
                        }
                        if(v2 != 0) {
                            stringBuilder0.append(charSequence0);
                        }
                        stringBuilder0.append(((CharSequence)function10.invoke(v6)));
                        ++v2;
                    }
                    v3 >>= 8;
                    ++v5;
                }
                if(v4 == 8) {
                label_31:
                    if(v1 != v) {
                        ++v1;
                        continue;
                    }
                    break;
                }
                else {
                    stringBuilder0.append("");
                }
                goto label_37;
            }
        }
        stringBuilder0.append("");
    label_37:
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final String joinToString(CharSequence charSequence0, Function1 function10) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(function10, "transform");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("");
        long[] arr_v = this.elements;
        long[] arr_v1 = this.metadata;
        int v = arr_v1.length - 2;
        if(v >= 0) {
            int v1 = 0;
            int v2 = 0;
            while(true) {
                long v3 = arr_v1[v1];
                if((~v3 << 7 & v3 & 0x8080808080808080L) == 0x8080808080808080L) {
                    goto label_30;
                }
                int v4 = 8 - (~(v1 - v) >>> 0x1F);
                int v5 = 0;
                while(v5 < v4) {
                    if((v3 & 0xFFL) < 0x80L) {
                        long v6 = arr_v[(v1 << 3) + v5];
                        if(v2 == -1) {
                            stringBuilder0.append("...");
                            goto label_36;
                        }
                        if(v2 != 0) {
                            stringBuilder0.append(charSequence0);
                        }
                        stringBuilder0.append(((CharSequence)function10.invoke(v6)));
                        ++v2;
                    }
                    v3 >>= 8;
                    ++v5;
                }
                if(v4 == 8) {
                label_30:
                    if(v1 != v) {
                        ++v1;
                        continue;
                    }
                    break;
                }
                else {
                    stringBuilder0.append("");
                }
                goto label_36;
            }
        }
        stringBuilder0.append("");
    label_36:
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final String joinToString(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "transform");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("");
        long[] arr_v = this.elements;
        long[] arr_v1 = this.metadata;
        int v = arr_v1.length - 2;
        if(v >= 0) {
            int v1 = 0;
            int v2 = 0;
            while(true) {
                long v3 = arr_v1[v1];
                if((~v3 << 7 & v3 & 0x8080808080808080L) == 0x8080808080808080L) {
                    goto label_29;
                }
                int v4 = 8 - (~(v1 - v) >>> 0x1F);
                int v5 = 0;
                while(v5 < v4) {
                    if((v3 & 0xFFL) < 0x80L) {
                        long v6 = arr_v[(v1 << 3) + v5];
                        if(v2 == -1) {
                            stringBuilder0.append("...");
                            goto label_35;
                        }
                        if(v2 != 0) {
                            stringBuilder0.append(", ");
                        }
                        stringBuilder0.append(((CharSequence)function10.invoke(v6)));
                        ++v2;
                    }
                    v3 >>= 8;
                    ++v5;
                }
                if(v4 == 8) {
                label_29:
                    if(v1 != v) {
                        ++v1;
                        continue;
                    }
                    break;
                }
                else {
                    stringBuilder0.append("");
                }
                goto label_35;
            }
        }
        stringBuilder0.append("");
    label_35:
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public static String joinToString$default(LongSet longSet0, CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, CharSequence charSequence3, int v1, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: joinToString");
        }
        if((v1 & 1) != 0) {
            charSequence0 = ", ";
        }
        if((v1 & 2) != 0) {
            charSequence1 = "";
        }
        if((v1 & 4) != 0) {
            charSequence2 = "";
        }
        if((v1 & 8) != 0) {
            v = -1;
        }
        if((v1 & 16) != 0) {
            charSequence3 = "...";
        }
        return longSet0.joinToString(charSequence0, charSequence1, charSequence2, v, charSequence3);
    }

    public static String joinToString$default(LongSet longSet0, CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, CharSequence charSequence3, Function1 function10, int v1, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: joinToString");
        }
        CharSequence charSequence4 = (v1 & 1) == 0 ? charSequence0 : ", ";
        CharSequence charSequence5 = (v1 & 2) == 0 ? charSequence1 : "";
        CharSequence charSequence6 = (v1 & 4) == 0 ? charSequence2 : "";
        CharSequence charSequence7 = (v1 & 16) == 0 ? charSequence3 : "...";
        Intrinsics.checkNotNullParameter(charSequence4, "separator");
        Intrinsics.checkNotNullParameter(charSequence5, "prefix");
        Intrinsics.checkNotNullParameter(charSequence6, "postfix");
        Intrinsics.checkNotNullParameter(charSequence7, "truncated");
        Intrinsics.checkNotNullParameter(function10, "transform");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(charSequence5);
        long[] arr_v = longSet0.elements;
        long[] arr_v1 = longSet0.metadata;
        int v2 = arr_v1.length - 2;
        if(v2 >= 0) {
            int v3 = 0;
            int v4 = 0;
            while(true) {
                long v5 = arr_v1[v3];
                if((~v5 << 7 & v5 & 0x8080808080808080L) == 0x8080808080808080L) {
                    goto label_50;
                }
                int v6 = 8 - (~(v3 - v2) >>> 0x1F);
                int v7 = 0;
                while(v7 < v6) {
                    if((v5 & 0xFFL) < 0x80L) {
                        long v8 = arr_v[(v3 << 3) + v7];
                        if(v4 == ((v1 & 8) == 0 ? v : -1)) {
                            stringBuilder0.append(charSequence7);
                            goto label_56;
                        }
                        if(v4 != 0) {
                            stringBuilder0.append(charSequence4);
                        }
                        stringBuilder0.append(((CharSequence)function10.invoke(v8)));
                        ++v4;
                    }
                    v5 >>= 8;
                    ++v7;
                }
                if(v6 == 8) {
                label_50:
                    if(v3 != v2) {
                        ++v3;
                        continue;
                    }
                    break;
                }
                else {
                    stringBuilder0.append(charSequence6);
                }
                goto label_56;
            }
        }
        stringBuilder0.append(charSequence6);
    label_56:
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final boolean none() {
        return this._size == 0;
    }

    @Override
    public String toString() {
        return LongSet.joinToString$default(this, null, "[", "]", 0, null, 25, null);
    }
}

