package okio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004:\u0001\u0015B\u001F\b\u0002\u0012\u000E\u0010\u0005\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\b¢\u0006\u0002\u0010\tJ\u0011\u0010\u0013\u001A\u00020\u00022\u0006\u0010\u0014\u001A\u00020\u000EH\u0096\u0002R\u001E\u0010\u0005\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0006X\u0080\u0004¢\u0006\n\n\u0002\u0010\f\u001A\u0004\b\n\u0010\u000BR\u0014\u0010\r\u001A\u00020\u000E8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u000F\u0010\u0010R\u0014\u0010\u0007\u001A\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Lokio/Options;", "Lkotlin/collections/AbstractList;", "Lokio/ByteString;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "byteStrings", "", "trie", "", "([Lokio/ByteString;[I)V", "getByteStrings$okio", "()[Lokio/ByteString;", "[Lokio/ByteString;", "size", "", "getSize", "()I", "getTrie$okio", "()[I", "get", "index", "Companion", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class Options extends AbstractList implements RandomAccess {
    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JT\u0010\b\u001A\u00020\t2\b\b\u0002\u0010\n\u001A\u00020\u00042\u0006\u0010\u000B\u001A\u00020\u00052\b\b\u0002\u0010\f\u001A\u00020\r2\f\u0010\u000E\u001A\b\u0012\u0004\u0012\u00020\u00100\u000F2\b\b\u0002\u0010\u0011\u001A\u00020\r2\b\b\u0002\u0010\u0012\u001A\u00020\r2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\r0\u000FH\u0002J!\u0010\u0014\u001A\u00020\u00152\u0012\u0010\u000E\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u0016\"\u00020\u0010H\u0007¢\u0006\u0002\u0010\u0017R\u0018\u0010\u0003\u001A\u00020\u0004*\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b\u0006\u0010\u0007¨\u0006\u0018"}, d2 = {"Lokio/Options$Companion;", "", "()V", "intCount", "", "Lokio/Buffer;", "getIntCount", "(Lokio/Buffer;)J", "buildTrieRecursive", "", "nodeOffset", "node", "byteStringOffset", "", "byteStrings", "", "Lokio/ByteString;", "fromIndex", "toIndex", "indexes", "of", "Lokio/Options;", "", "([Lokio/ByteString;)Lokio/Options;", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        private final void buildTrieRecursive(long v, Buffer buffer0, int v1, List list0, int v2, int v3, List list1) {
            long v15;
            int v14;
            int v6;
            int v5;
            if(v2 >= v3) {
                throw new IllegalArgumentException("Failed requirement.");
            }
            for(int v4 = v2; v4 < v3; ++v4) {
                if(((ByteString)list0.get(v4)).size() < v1) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
            }
            ByteString byteString0 = (ByteString)list0.get(v2);
            ByteString byteString1 = (ByteString)list0.get(v3 - 1);
            if(v1 == byteString0.size()) {
                v5 = v2 + 1;
                v6 = ((Number)list1.get(v2)).intValue();
                byteString0 = (ByteString)list0.get(v2 + 1);
            }
            else {
                v5 = v2;
                v6 = -1;
            }
            if(byteString0.getByte(v1) != byteString1.getByte(v1)) {
                int v7 = v5 + 1;
                int v8 = 1;
                while(v7 < v3) {
                    if(((ByteString)list0.get(v7 - 1)).getByte(v1) != ((ByteString)list0.get(v7)).getByte(v1)) {
                        ++v8;
                    }
                    ++v7;
                }
                long v9 = v + this.getIntCount(buffer0) + 2L + ((long)(v8 * 2));
                buffer0.writeInt(v8);
                buffer0.writeInt(v6);
                for(int v10 = v5; v10 < v3; ++v10) {
                    int v11 = ((ByteString)list0.get(v10)).getByte(v1);
                    if(v10 == v5 || v11 != ((ByteString)list0.get(v10 - 1)).getByte(v1)) {
                        buffer0.writeInt(v11 & 0xFF);
                    }
                }
                Buffer buffer1 = new Buffer();
                while(v5 < v3) {
                    int v12 = ((ByteString)list0.get(v5)).getByte(v1);
                    int v13 = v5 + 1;
                    while(true) {
                        if(v13 < v3) {
                            if(v12 == ((ByteString)list0.get(v13)).getByte(v1)) {
                                ++v13;
                                continue;
                            }
                            else {
                                v14 = v13;
                                break;
                            }
                        }
                        v14 = v3;
                        break;
                    }
                    if(v5 + 1 != v14 || v1 + 1 != ((ByteString)list0.get(v5)).size()) {
                        buffer0.writeInt(-((int)(this.getIntCount(buffer1) + v9)));
                        v15 = v9;
                        this.buildTrieRecursive(v15, buffer1, v1 + 1, list0, v5, v14, list1);
                    }
                    else {
                        buffer0.writeInt(((Number)list1.get(v5)).intValue());
                        v15 = v9;
                    }
                    v9 = v15;
                    v5 = v14;
                }
                buffer0.writeAll(buffer1);
                return;
            }
            int v16 = Math.min(byteString0.size(), byteString1.size());
            int v17 = 0;
            for(int v18 = v1; v18 < v16 && byteString0.getByte(v18) == byteString1.getByte(v18); ++v18) {
                ++v17;
            }
            long v19 = this.getIntCount(buffer0);
            buffer0.writeInt(-v17);
            buffer0.writeInt(v6);
            int v20 = v1 + v17;
            while(v1 < v20) {
                buffer0.writeInt(byteString0.getByte(v1) & 0xFF);
                ++v1;
            }
            if(v5 + 1 == v3) {
                if(v20 != ((ByteString)list0.get(v5)).size()) {
                    throw new IllegalStateException("Check failed.");
                }
                buffer0.writeInt(((Number)list1.get(v5)).intValue());
                return;
            }
            long v21 = v + v19 + 2L + ((long)v17) + 1L;
            Buffer buffer2 = new Buffer();
            buffer0.writeInt(-((int)(this.getIntCount(buffer2) + v21)));
            this.buildTrieRecursive(v21, buffer2, v20, list0, v5, v3, list1);
            buffer0.writeAll(buffer2);
        }

        static void buildTrieRecursive$default(Companion options$Companion0, long v, Buffer buffer0, int v1, List list0, int v2, int v3, List list1, int v4, Object object0) {
            if((v4 & 1) != 0) {
                v = 0L;
            }
            options$Companion0.buildTrieRecursive(v, buffer0, ((v4 & 4) == 0 ? v1 : 0), list0, ((v4 & 16) == 0 ? v2 : 0), ((v4 & 0x20) == 0 ? v3 : list0.size()), list1);
        }

        private final long getIntCount(Buffer buffer0) {
            return buffer0.size() / 4L;
        }

        @JvmStatic
        public final Options of(ByteString[] arr_byteString) {
            Intrinsics.checkNotNullParameter(arr_byteString, "byteStrings");
            if(arr_byteString.length == 0) {
                return new Options(new ByteString[0], new int[]{0, -1}, null);
            }
            List list0 = ArraysKt.toMutableList(arr_byteString);
            CollectionsKt.sort(list0);
            ArrayList arrayList0 = new ArrayList(arr_byteString.length);
            for(int v1 = 0; v1 < arr_byteString.length; ++v1) {
                ByteString byteString0 = arr_byteString[v1];
                arrayList0.add(-1);
            }
            Object[] arr_object = arrayList0.toArray(new Integer[0]);
            if(arr_object == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
            }
            List list1 = CollectionsKt.mutableListOf(Arrays.copyOf(((Integer[])arr_object), ((Integer[])arr_object).length));
            int v2 = 0;
            for(int v3 = 0; v2 < arr_byteString.length; ++v3) {
                list1.set(CollectionsKt.binarySearch$default(list0, arr_byteString[v2], 0, 0, 6, null), v3);
                ++v2;
            }
            if(((ByteString)list0.get(0)).size() <= 0) {
                throw new IllegalArgumentException("the empty byte string is not a supported option");
            }
            for(int v4 = 0; v4 < list0.size(); ++v4) {
                ByteString byteString1 = (ByteString)list0.get(v4);
                int v5 = v4 + 1;
                while(v5 < list0.size()) {
                    ByteString byteString2 = (ByteString)list0.get(v5);
                    if(!byteString2.startsWith(byteString1)) {
                        break;
                    }
                    if(byteString2.size() == byteString1.size()) {
                        throw new IllegalArgumentException(("duplicate option: " + byteString2).toString());
                    }
                    if(((Number)list1.get(v5)).intValue() > ((Number)list1.get(v4)).intValue()) {
                        list0.remove(v5);
                        list1.remove(v5);
                    }
                    else {
                        ++v5;
                    }
                }
            }
            Buffer buffer0 = new Buffer();
            Companion.buildTrieRecursive$default(this, 0L, buffer0, 0, list0, 0, 0, list1, 53, null);
            int[] arr_v = new int[((int)this.getIntCount(buffer0))];
            for(int v = 0; !buffer0.exhausted(); ++v) {
                arr_v[v] = buffer0.readInt();
            }
            Object[] arr_object1 = Arrays.copyOf(arr_byteString, arr_byteString.length);
            Intrinsics.checkNotNullExpressionValue(arr_object1, "copyOf(this, size)");
            return new Options(((ByteString[])arr_object1), arr_v, null);
        }
    }

    public static final Companion Companion;
    private final ByteString[] byteStrings;
    private final int[] trie;

    static {
        Options.Companion = new Companion(null);
    }

    private Options(ByteString[] arr_byteString, int[] arr_v) {
        this.byteStrings = arr_byteString;
        this.trie = arr_v;
    }

    public Options(ByteString[] arr_byteString, int[] arr_v, DefaultConstructorMarker defaultConstructorMarker0) {
        this(arr_byteString, arr_v);
    }

    @Override  // kotlin.collections.AbstractCollection
    public final boolean contains(Object object0) {
        return object0 instanceof ByteString ? this.contains(((ByteString)object0)) : false;
    }

    public boolean contains(ByteString byteString0) {
        return super.contains(byteString0);
    }

    @Override  // kotlin.collections.AbstractList
    public Object get(int v) {
        return this.get(v);
    }

    public ByteString get(int v) {
        return this.byteStrings[v];
    }

    public final ByteString[] getByteStrings$okio() {
        return this.byteStrings;
    }

    @Override  // kotlin.collections.AbstractList
    public int getSize() {
        return this.byteStrings.length;
    }

    public final int[] getTrie$okio() {
        return this.trie;
    }

    @Override  // kotlin.collections.AbstractList
    public final int indexOf(Object object0) {
        return object0 instanceof ByteString ? this.indexOf(((ByteString)object0)) : -1;
    }

    public int indexOf(ByteString byteString0) {
        return super.indexOf(byteString0);
    }

    @Override  // kotlin.collections.AbstractList
    public final int lastIndexOf(Object object0) {
        return object0 instanceof ByteString ? this.lastIndexOf(((ByteString)object0)) : -1;
    }

    public int lastIndexOf(ByteString byteString0) {
        return super.lastIndexOf(byteString0);
    }

    @JvmStatic
    public static final Options of(ByteString[] arr_byteString) {
        return Options.Companion.of(arr_byteString);
    }
}

