package kotlin.collections.builders;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.jvm.internal.markers.KMutableMap.Entry;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000\u00A8\u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0010#\n\u0002\u0010\'\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u001F\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u001E\n\u0002\b\u0003\n\u0002\u0010&\n\u0002\b\u000B\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u001A\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 }*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u00060\u0004j\u0002`\u0005:\t}~\u007F\u0080\u0001\u0081\u0001\u0082\u0001B\u0007\b\u0016\u00A2\u0006\u0002\u0010\u0006B\u000F\b\u0016\u0012\u0006\u0010\u0007\u001A\u00020\b\u00A2\u0006\u0002\u0010\tBE\b\u0002\u0012\f\u0010\n\u001A\b\u0012\u0004\u0012\u00028\u00000\u000B\u0012\u000E\u0010\f\u001A\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000B\u0012\u0006\u0010\r\u001A\u00020\u000E\u0012\u0006\u0010\u000F\u001A\u00020\u000E\u0012\u0006\u0010\u0010\u001A\u00020\b\u0012\u0006\u0010\u0011\u001A\u00020\b\u00A2\u0006\u0002\u0010\u0012J\u0017\u00102\u001A\u00020\b2\u0006\u00103\u001A\u00028\u0000H\u0000\u00A2\u0006\u0004\b4\u00105J\u0013\u00106\u001A\b\u0012\u0004\u0012\u00028\u00010\u000BH\u0002\u00A2\u0006\u0002\u00107J\u0012\u00108\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000109J\r\u0010:\u001A\u00020;H\u0000\u00A2\u0006\u0002\b<J\b\u0010=\u001A\u00020;H\u0016J\b\u0010>\u001A\u00020;H\u0002J\u0019\u0010?\u001A\u00020!2\n\u0010@\u001A\u0006\u0012\u0002\b\u00030AH\u0000\u00A2\u0006\u0002\bBJ!\u0010C\u001A\u00020!2\u0012\u0010D\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010EH\u0000\u00A2\u0006\u0002\bFJ\u0015\u0010G\u001A\u00020!2\u0006\u00103\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010HJ\u0015\u0010I\u001A\u00020!2\u0006\u0010J\u001A\u00028\u0001H\u0016\u00A2\u0006\u0002\u0010HJ\u0018\u0010K\u001A\u00020!2\u000E\u0010L\u001A\n\u0012\u0002\b\u0003\u0012\u0002\b\u000309H\u0002J\u0010\u0010M\u001A\u00020;2\u0006\u0010\u0013\u001A\u00020\bH\u0002J\u0010\u0010N\u001A\u00020;2\u0006\u0010O\u001A\u00020\bH\u0002J\u0019\u0010P\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010QH\u0000\u00A2\u0006\u0002\bRJ\u0013\u0010S\u001A\u00020!2\b\u0010L\u001A\u0004\u0018\u00010TH\u0096\u0002J\u0015\u0010U\u001A\u00020\b2\u0006\u00103\u001A\u00028\u0000H\u0002\u00A2\u0006\u0002\u00105J\u0015\u0010V\u001A\u00020\b2\u0006\u0010J\u001A\u00028\u0001H\u0002\u00A2\u0006\u0002\u00105J\u0018\u0010W\u001A\u0004\u0018\u00018\u00012\u0006\u00103\u001A\u00028\u0000H\u0096\u0002\u00A2\u0006\u0002\u0010XJ\u0015\u0010Y\u001A\u00020\b2\u0006\u00103\u001A\u00028\u0000H\u0002\u00A2\u0006\u0002\u00105J\b\u0010Z\u001A\u00020\bH\u0016J\b\u0010[\u001A\u00020!H\u0016J\u0019\u0010\\\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010]H\u0000\u00A2\u0006\u0002\b^J\u001F\u0010_\u001A\u0004\u0018\u00018\u00012\u0006\u00103\u001A\u00028\u00002\u0006\u0010J\u001A\u00028\u0001H\u0016\u00A2\u0006\u0002\u0010`J\u001E\u0010a\u001A\u00020;2\u0014\u0010b\u001A\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000109H\u0016J\"\u0010c\u001A\u00020!2\u0018\u0010b\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010E0AH\u0002J\u001C\u0010d\u001A\u00020!2\u0012\u0010D\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010EH\u0002J\u0010\u0010e\u001A\u00020!2\u0006\u0010f\u001A\u00020\bH\u0002J\u0010\u0010g\u001A\u00020;2\u0006\u0010h\u001A\u00020\bH\u0002J\u0017\u0010i\u001A\u0004\u0018\u00018\u00012\u0006\u00103\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010XJ!\u0010j\u001A\u00020!2\u0012\u0010D\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010EH\u0000\u00A2\u0006\u0002\bkJ\u0010\u0010l\u001A\u00020;2\u0006\u0010m\u001A\u00020\bH\u0002J\u0017\u0010n\u001A\u00020\b2\u0006\u00103\u001A\u00028\u0000H\u0000\u00A2\u0006\u0004\bo\u00105J\u0010\u0010p\u001A\u00020;2\u0006\u0010q\u001A\u00020\bH\u0002J\u0017\u0010r\u001A\u00020!2\u0006\u0010s\u001A\u00028\u0001H\u0000\u00A2\u0006\u0004\bt\u0010HJ\u0010\u0010u\u001A\u00020!2\u0006\u0010v\u001A\u00020\bH\u0002J\b\u0010w\u001A\u00020xH\u0016J\u0019\u0010y\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010zH\u0000\u00A2\u0006\u0002\b{J\b\u0010|\u001A\u00020TH\u0002R\u0014\u0010\u0013\u001A\u00020\b8@X\u0080\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0014\u0010\u0015R&\u0010\u0016\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00180\u00178VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0019\u0010\u001AR\u001C\u0010\u001B\u001A\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u001CX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\u000EX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u001D\u001A\u00020\bX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u001E\u001A\u00020\b8BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\u001F\u0010\u0015R\u001E\u0010\"\u001A\u00020!2\u0006\u0010 \u001A\u00020!@BX\u0080\u000E\u00A2\u0006\b\n\u0000\u001A\u0004\b#\u0010$R\u001A\u0010%\u001A\b\u0012\u0004\u0012\u00028\u00000\u00178VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b&\u0010\u001AR\u0016\u0010\n\u001A\b\u0012\u0004\u0012\u00028\u00000\u000BX\u0082\u000E\u00A2\u0006\u0004\n\u0002\u0010\'R\u0016\u0010(\u001A\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010)X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\bX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0010\u001A\u00020\bX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\u000EX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u001E\u0010*\u001A\u00020\b2\u0006\u0010 \u001A\u00020\b@RX\u0096\u000E\u00A2\u0006\b\n\u0000\u001A\u0004\b+\u0010\u0015R\u001A\u0010,\u001A\b\u0012\u0004\u0012\u00028\u00010-8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b.\u0010/R\u0018\u0010\f\u001A\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000BX\u0082\u000E\u00A2\u0006\u0004\n\u0002\u0010\'R\u0016\u00100\u001A\n\u0012\u0004\u0012\u00028\u0001\u0018\u000101X\u0082\u000E\u00A2\u0006\u0002\n\u0000\u00A8\u0006\u0083\u0001"}, d2 = {"Lkotlin/collections/builders/MapBuilder;", "K", "V", "", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "()V", "initialCapacity", "", "(I)V", "keysArray", "", "valuesArray", "presenceArray", "", "hashArray", "maxProbeDistance", "length", "([Ljava/lang/Object;[Ljava/lang/Object;[I[III)V", "capacity", "getCapacity$kotlin_stdlib", "()I", "entries", "", "", "getEntries", "()Ljava/util/Set;", "entriesView", "Lkotlin/collections/builders/MapBuilderEntries;", "hashShift", "hashSize", "getHashSize", "<set-?>", "", "isReadOnly", "isReadOnly$kotlin_stdlib", "()Z", "keys", "getKeys", "[Ljava/lang/Object;", "keysView", "Lkotlin/collections/builders/MapBuilderKeys;", "size", "getSize", "values", "", "getValues", "()Ljava/util/Collection;", "valuesView", "Lkotlin/collections/builders/MapBuilderValues;", "addKey", "key", "addKey$kotlin_stdlib", "(Ljava/lang/Object;)I", "allocateValuesArray", "()[Ljava/lang/Object;", "build", "", "checkIsMutable", "", "checkIsMutable$kotlin_stdlib", "clear", "compact", "containsAllEntries", "m", "", "containsAllEntries$kotlin_stdlib", "containsEntry", "entry", "", "containsEntry$kotlin_stdlib", "containsKey", "(Ljava/lang/Object;)Z", "containsValue", "value", "contentEquals", "other", "ensureCapacity", "ensureExtraCapacity", "n", "entriesIterator", "Lkotlin/collections/builders/MapBuilder$EntriesItr;", "entriesIterator$kotlin_stdlib", "equals", "", "findKey", "findValue", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "hash", "hashCode", "isEmpty", "keysIterator", "Lkotlin/collections/builders/MapBuilder$KeysItr;", "keysIterator$kotlin_stdlib", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "putAll", "from", "putAllEntries", "putEntry", "putRehash", "i", "rehash", "newHashSize", "remove", "removeEntry", "removeEntry$kotlin_stdlib", "removeHashAt", "removedHash", "removeKey", "removeKey$kotlin_stdlib", "removeKeyAt", "index", "removeValue", "element", "removeValue$kotlin_stdlib", "shouldCompact", "extraCapacity", "toString", "", "valuesIterator", "Lkotlin/collections/builders/MapBuilder$ValuesItr;", "valuesIterator$kotlin_stdlib", "writeReplace", "Companion", "EntriesItr", "EntryRef", "Itr", "KeysItr", "ValuesItr", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class MapBuilder implements Serializable, Map, KMutableMap {
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001A\u00020\t2\u0006\u0010\u000E\u001A\u00020\tH\u0002J\u0010\u0010\u000F\u001A\u00020\t2\u0006\u0010\u0010\u001A\u00020\tH\u0002R \u0010\u0003\u001A\u000E\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u0007R\u000E\u0010\b\u001A\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lkotlin/collections/builders/MapBuilder$Companion;", "", "()V", "Empty", "Lkotlin/collections/builders/MapBuilder;", "", "getEmpty$kotlin_stdlib", "()Lkotlin/collections/builders/MapBuilder;", "INITIAL_CAPACITY", "", "INITIAL_MAX_PROBE_DISTANCE", "MAGIC", "TOMBSTONE", "computeHashSize", "capacity", "computeShift", "hashSize", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public static final int access$computeHashSize(Companion mapBuilder$Companion0, int v) {
            return mapBuilder$Companion0.computeHashSize(v);
        }

        public static final int access$computeShift(Companion mapBuilder$Companion0, int v) {
            return mapBuilder$Companion0.computeShift(v);
        }

        private final int computeHashSize(int v) {
            return Integer.highestOneBit(RangesKt.coerceAtLeast(v, 1) * 3);
        }

        private final int computeShift(int v) {
            return Integer.numberOfLeadingZeros(v) + 1;
        }

        public final MapBuilder getEmpty$kotlin_stdlib() {
            return MapBuilder.Empty;
        }
    }

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010)\n\u0002\u0010\'\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00050\u0004B\u0019\u0012\u0012\u0010\u0006\u001A\u000E\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0007¢\u0006\u0002\u0010\bJ\u0015\u0010\t\u001A\u000E\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\nH\u0096\u0002J\u0012\u0010\u000B\u001A\u00020\f2\n\u0010\r\u001A\u00060\u000Ej\u0002`\u000FJ\r\u0010\u0010\u001A\u00020\u0011H\u0000¢\u0006\u0002\b\u0012¨\u0006\u0013"}, d2 = {"Lkotlin/collections/builders/MapBuilder$EntriesItr;", "K", "V", "Lkotlin/collections/builders/MapBuilder$Itr;", "", "", "map", "Lkotlin/collections/builders/MapBuilder;", "(Lkotlin/collections/builders/MapBuilder;)V", "next", "Lkotlin/collections/builders/MapBuilder$EntryRef;", "nextAppendString", "", "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "nextHashCode", "", "nextHashCode$kotlin_stdlib", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class EntriesItr extends Itr implements Iterator, KMutableIterator {
        public EntriesItr(MapBuilder mapBuilder0) {
            Intrinsics.checkNotNullParameter(mapBuilder0, "map");
            super(mapBuilder0);
        }

        @Override
        public Object next() {
            return this.next();
        }

        public EntryRef next() {
            if(this.getIndex$kotlin_stdlib() >= this.getMap$kotlin_stdlib().length) {
                throw new NoSuchElementException();
            }
            int v = this.getIndex$kotlin_stdlib();
            this.setIndex$kotlin_stdlib(v + 1);
            this.setLastIndex$kotlin_stdlib(v);
            EntryRef mapBuilder$EntryRef0 = new EntryRef(this.getMap$kotlin_stdlib(), this.getLastIndex$kotlin_stdlib());
            this.initNext$kotlin_stdlib();
            return mapBuilder$EntryRef0;
        }

        public final void nextAppendString(StringBuilder stringBuilder0) {
            Intrinsics.checkNotNullParameter(stringBuilder0, "sb");
            if(this.getIndex$kotlin_stdlib() >= this.getMap$kotlin_stdlib().length) {
                throw new NoSuchElementException();
            }
            int v = this.getIndex$kotlin_stdlib();
            this.setIndex$kotlin_stdlib(v + 1);
            this.setLastIndex$kotlin_stdlib(v);
            Object[] arr_object = this.getMap$kotlin_stdlib().keysArray;
            Object object0 = arr_object[this.getLastIndex$kotlin_stdlib()];
            if(Intrinsics.areEqual(object0, this.getMap$kotlin_stdlib())) {
                stringBuilder0.append("(this Map)");
            }
            else {
                stringBuilder0.append(object0);
            }
            stringBuilder0.append('=');
            Object[] arr_object1 = this.getMap$kotlin_stdlib().valuesArray;
            Intrinsics.checkNotNull(arr_object1);
            Object object1 = arr_object1[this.getLastIndex$kotlin_stdlib()];
            if(Intrinsics.areEqual(object1, this.getMap$kotlin_stdlib())) {
                stringBuilder0.append("(this Map)");
            }
            else {
                stringBuilder0.append(object1);
            }
            this.initNext$kotlin_stdlib();
        }

        public final int nextHashCode$kotlin_stdlib() {
            if(this.getIndex$kotlin_stdlib() >= this.getMap$kotlin_stdlib().length) {
                throw new NoSuchElementException();
            }
            int v = this.getIndex$kotlin_stdlib();
            this.setIndex$kotlin_stdlib(v + 1);
            this.setLastIndex$kotlin_stdlib(v);
            Object[] arr_object = this.getMap$kotlin_stdlib().keysArray;
            Object object0 = arr_object[this.getLastIndex$kotlin_stdlib()];
            int v1 = 0;
            int v2 = object0 == null ? 0 : object0.hashCode();
            Object[] arr_object1 = this.getMap$kotlin_stdlib().valuesArray;
            Intrinsics.checkNotNull(arr_object1);
            Object object1 = arr_object1[this.getLastIndex$kotlin_stdlib()];
            if(object1 != null) {
                v1 = object1.hashCode();
            }
            this.initNext$kotlin_stdlib();
            return v2 ^ v1;
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000E\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B!\u0012\u0012\u0010\u0004\u001A\u000E\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bJ\u0013\u0010\u000E\u001A\u00020\u000F2\b\u0010\u0010\u001A\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001A\u00020\u0007H\u0016J\u0015\u0010\u0013\u001A\u00028\u00032\u0006\u0010\u0014\u001A\u00028\u0003H\u0016¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001A\u00020\u0017H\u0016R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001A\u00028\u00028VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\n\u0010\u000BR\u001A\u0010\u0004\u001A\u000E\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001A\u00028\u00038VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\r\u0010\u000B¨\u0006\u0018"}, d2 = {"Lkotlin/collections/builders/MapBuilder$EntryRef;", "K", "V", "", "map", "Lkotlin/collections/builders/MapBuilder;", "index", "", "(Lkotlin/collections/builders/MapBuilder;I)V", "key", "getKey", "()Ljava/lang/Object;", "value", "getValue", "equals", "", "other", "", "hashCode", "setValue", "newValue", "(Ljava/lang/Object;)Ljava/lang/Object;", "toString", "", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class EntryRef implements Map.Entry, Entry {
        private final int index;
        private final MapBuilder map;

        public EntryRef(MapBuilder mapBuilder0, int v) {
            Intrinsics.checkNotNullParameter(mapBuilder0, "map");
            super();
            this.map = mapBuilder0;
            this.index = v;
        }

        // 去混淆评级： 低(30)
        @Override
        public boolean equals(Object object0) {
            return object0 instanceof Map.Entry && Intrinsics.areEqual(((Map.Entry)object0).getKey(), this.getKey()) && Intrinsics.areEqual(((Map.Entry)object0).getValue(), this.getValue());
        }

        @Override
        public Object getKey() {
            Object[] arr_object = this.map.keysArray;
            return arr_object[this.index];
        }

        @Override
        public Object getValue() {
            Object[] arr_object = this.map.valuesArray;
            Intrinsics.checkNotNull(arr_object);
            return arr_object[this.index];
        }

        @Override
        public int hashCode() {
            Object object0 = this.getKey();
            int v = 0;
            int v1 = object0 == null ? 0 : object0.hashCode();
            Object object1 = this.getValue();
            if(object1 != null) {
                v = object1.hashCode();
            }
            return v1 ^ v;
        }

        @Override
        public Object setValue(Object object0) {
            this.map.checkIsMutable$kotlin_stdlib();
            Object[] arr_object = this.map.allocateValuesArray();
            Object object1 = arr_object[this.index];
            arr_object[this.index] = object0;
            return object1;
        }

        @Override
        public String toString() {
            return this.getKey() + '=' + this.getValue();
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0010\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u00020\u0003B\u0019\u0012\u0012\u0010\u0004\u001A\u000E\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0012\u001A\u00020\u0013J\r\u0010\u0014\u001A\u00020\u0015H\u0000¢\u0006\u0002\b\u0016J\u0006\u0010\u0017\u001A\u00020\u0015R\u001A\u0010\u0007\u001A\u00020\bX\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\t\u0010\n\"\u0004\b\u000B\u0010\fR\u001A\u0010\r\u001A\u00020\bX\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u000E\u0010\n\"\u0004\b\u000F\u0010\fR \u0010\u0004\u001A\u000E\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0010\u0010\u0011¨\u0006\u0018"}, d2 = {"Lkotlin/collections/builders/MapBuilder$Itr;", "K", "V", "", "map", "Lkotlin/collections/builders/MapBuilder;", "(Lkotlin/collections/builders/MapBuilder;)V", "index", "", "getIndex$kotlin_stdlib", "()I", "setIndex$kotlin_stdlib", "(I)V", "lastIndex", "getLastIndex$kotlin_stdlib", "setLastIndex$kotlin_stdlib", "getMap$kotlin_stdlib", "()Lkotlin/collections/builders/MapBuilder;", "hasNext", "", "initNext", "", "initNext$kotlin_stdlib", "remove", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static class Itr {
        private int index;
        private int lastIndex;
        private final MapBuilder map;

        public Itr(MapBuilder mapBuilder0) {
            Intrinsics.checkNotNullParameter(mapBuilder0, "map");
            super();
            this.map = mapBuilder0;
            this.lastIndex = -1;
            this.initNext$kotlin_stdlib();
        }

        public final int getIndex$kotlin_stdlib() {
            return this.index;
        }

        public final int getLastIndex$kotlin_stdlib() {
            return this.lastIndex;
        }

        public final MapBuilder getMap$kotlin_stdlib() {
            return this.map;
        }

        public final boolean hasNext() {
            return this.index < this.map.length;
        }

        public final void initNext$kotlin_stdlib() {
            while(this.index < this.map.length) {
                int v = this.index;
                if(this.map.presenceArray[v] >= 0) {
                    break;
                }
                this.index = v + 1;
            }
        }

        public final void remove() {
            if(this.lastIndex == -1) {
                throw new IllegalStateException("Call next() before removing element from the iterator.");
            }
            this.map.checkIsMutable$kotlin_stdlib();
            this.map.removeKeyAt(this.lastIndex);
            this.lastIndex = -1;
        }

        public final void setIndex$kotlin_stdlib(int v) {
            this.index = v;
        }

        public final void setLastIndex$kotlin_stdlib(int v) {
            this.lastIndex = v;
        }
    }

    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010)\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00010\u0004B\u0019\u0012\u0012\u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0006¢\u0006\u0002\u0010\u0007J\u000E\u0010\b\u001A\u00028\u0002H\u0096\u0002¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lkotlin/collections/builders/MapBuilder$KeysItr;", "K", "V", "Lkotlin/collections/builders/MapBuilder$Itr;", "", "map", "Lkotlin/collections/builders/MapBuilder;", "(Lkotlin/collections/builders/MapBuilder;)V", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class KeysItr extends Itr implements Iterator, KMutableIterator {
        public KeysItr(MapBuilder mapBuilder0) {
            Intrinsics.checkNotNullParameter(mapBuilder0, "map");
            super(mapBuilder0);
        }

        @Override
        public Object next() {
            if(this.getIndex$kotlin_stdlib() >= this.getMap$kotlin_stdlib().length) {
                throw new NoSuchElementException();
            }
            int v = this.getIndex$kotlin_stdlib();
            this.setIndex$kotlin_stdlib(v + 1);
            this.setLastIndex$kotlin_stdlib(v);
            Object[] arr_object = this.getMap$kotlin_stdlib().keysArray;
            Object object0 = arr_object[this.getLastIndex$kotlin_stdlib()];
            this.initNext$kotlin_stdlib();
            return object0;
        }
    }

    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010)\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00020\u0004B\u0019\u0012\u0012\u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0006¢\u0006\u0002\u0010\u0007J\u000E\u0010\b\u001A\u00028\u0003H\u0096\u0002¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lkotlin/collections/builders/MapBuilder$ValuesItr;", "K", "V", "Lkotlin/collections/builders/MapBuilder$Itr;", "", "map", "Lkotlin/collections/builders/MapBuilder;", "(Lkotlin/collections/builders/MapBuilder;)V", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class ValuesItr extends Itr implements Iterator, KMutableIterator {
        public ValuesItr(MapBuilder mapBuilder0) {
            Intrinsics.checkNotNullParameter(mapBuilder0, "map");
            super(mapBuilder0);
        }

        @Override
        public Object next() {
            if(this.getIndex$kotlin_stdlib() >= this.getMap$kotlin_stdlib().length) {
                throw new NoSuchElementException();
            }
            int v = this.getIndex$kotlin_stdlib();
            this.setIndex$kotlin_stdlib(v + 1);
            this.setLastIndex$kotlin_stdlib(v);
            Object[] arr_object = this.getMap$kotlin_stdlib().valuesArray;
            Intrinsics.checkNotNull(arr_object);
            Object object0 = arr_object[this.getLastIndex$kotlin_stdlib()];
            this.initNext$kotlin_stdlib();
            return object0;
        }
    }

    public static final Companion Companion = null;
    private static final MapBuilder Empty = null;
    private static final int INITIAL_CAPACITY = 8;
    private static final int INITIAL_MAX_PROBE_DISTANCE = 2;
    private static final int MAGIC = -1640531527;
    private static final int TOMBSTONE = -1;
    private MapBuilderEntries entriesView;
    private int[] hashArray;
    private int hashShift;
    private boolean isReadOnly;
    private Object[] keysArray;
    private MapBuilderKeys keysView;
    private int length;
    private int maxProbeDistance;
    private int[] presenceArray;
    private int size;
    private Object[] valuesArray;
    private MapBuilderValues valuesView;

    static {
        MapBuilder.Companion = new Companion(null);
        MapBuilder mapBuilder0 = new MapBuilder(0);
        mapBuilder0.isReadOnly = true;
        MapBuilder.Empty = mapBuilder0;
    }

    public MapBuilder() {
        this(8);
    }

    public MapBuilder(int v) {
        this(ListBuilderKt.arrayOfUninitializedElements(v), null, new int[v], new int[Companion.access$computeHashSize(MapBuilder.Companion, v)], 2, 0);
    }

    private MapBuilder(Object[] arr_object, Object[] arr_object1, int[] arr_v, int[] arr_v1, int v, int v1) {
        this.keysArray = arr_object;
        this.valuesArray = arr_object1;
        this.presenceArray = arr_v;
        this.hashArray = arr_v1;
        this.maxProbeDistance = v;
        this.length = v1;
        this.hashShift = Companion.access$computeShift(MapBuilder.Companion, this.getHashSize());
    }

    public final int addKey$kotlin_stdlib(Object object0) {
        int v;
        this.checkIsMutable$kotlin_stdlib();
        while(true) {
            v = this.hash(object0);
            int v1 = RangesKt.coerceAtMost(this.maxProbeDistance * 2, this.getHashSize() / 2);
            int v2 = 0;
        label_4:
            int v3 = this.hashArray[v];
            if(v3 <= 0) {
                if(this.length >= this.getCapacity$kotlin_stdlib()) {
                    this.ensureExtraCapacity(1);
                    continue;
                }
                int v4 = this.length;
                this.length = v4 + 1;
                this.keysArray[v4] = object0;
                this.presenceArray[v4] = v;
                this.hashArray[v] = v4 + 1;
                this.size = this.size() + 1;
                if(v2 > this.maxProbeDistance) {
                    this.maxProbeDistance = v2;
                }
                return v4;
            }
            if(Intrinsics.areEqual(this.keysArray[v3 - 1], object0)) {
                return -v3;
            }
            ++v2;
            if(v2 <= v1) {
                break;
            }
            this.rehash(this.getHashSize() * 2);
        }
        if(v != 0) {
            --v;
            goto label_4;
        }
        v = this.getHashSize() - 1;
        goto label_4;
    }

    private final Object[] allocateValuesArray() {
        Object[] arr_object = this.valuesArray;
        if(arr_object != null) {
            return arr_object;
        }
        Object[] arr_object1 = ListBuilderKt.arrayOfUninitializedElements(this.getCapacity$kotlin_stdlib());
        this.valuesArray = arr_object1;
        return arr_object1;
    }

    public final Map build() {
        this.checkIsMutable$kotlin_stdlib();
        this.isReadOnly = true;
        if(this.size() > 0) {
            return this;
        }
        Intrinsics.checkNotNull(MapBuilder.Empty, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.builders.MapBuilder, V of kotlin.collections.builders.MapBuilder>");
        return MapBuilder.Empty;
    }

    public final void checkIsMutable$kotlin_stdlib() {
        if(this.isReadOnly) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void clear() {
        this.checkIsMutable$kotlin_stdlib();
        IntIterator intIterator0 = new IntRange(0, this.length - 1).iterator();
        while(intIterator0.hasNext()) {
            int v = intIterator0.nextInt();
            int[] arr_v = this.presenceArray;
            int v1 = arr_v[v];
            if(v1 >= 0) {
                this.hashArray[v1] = 0;
                arr_v[v] = -1;
            }
        }
        ListBuilderKt.resetRange(this.keysArray, 0, this.length);
        Object[] arr_object = this.valuesArray;
        if(arr_object != null) {
            ListBuilderKt.resetRange(arr_object, 0, this.length);
        }
        this.size = 0;
        this.length = 0;
    }

    private final void compact() {
        int v2;
        Object[] arr_object = this.valuesArray;
        int v1 = 0;
        for(int v = 0; true; ++v) {
            v2 = this.length;
            if(v >= v2) {
                break;
            }
            if(this.presenceArray[v] >= 0) {
                this.keysArray[v1] = this.keysArray[v];
                if(arr_object != null) {
                    arr_object[v1] = arr_object[v];
                }
                ++v1;
            }
        }
        ListBuilderKt.resetRange(this.keysArray, v1, v2);
        if(arr_object != null) {
            ListBuilderKt.resetRange(arr_object, v1, this.length);
        }
        this.length = v1;
    }

    public final boolean containsAllEntries$kotlin_stdlib(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "m");
        for(Object object0: collection0) {
            if(object0 != null) {
                try {
                    if(!this.containsEntry$kotlin_stdlib(((Map.Entry)object0))) {
                        return false;
                    }
                    continue;
                }
                catch(ClassCastException unused_ex) {
                }
            }
            return false;
        }
        return true;
    }

    public final boolean containsEntry$kotlin_stdlib(Map.Entry map$Entry0) {
        Intrinsics.checkNotNullParameter(map$Entry0, "entry");
        int v = this.findKey(map$Entry0.getKey());
        if(v < 0) {
            return false;
        }
        Object[] arr_object = this.valuesArray;
        Intrinsics.checkNotNull(arr_object);
        return Intrinsics.areEqual(arr_object[v], map$Entry0.getValue());
    }

    @Override
    public boolean containsKey(Object object0) {
        return this.findKey(object0) >= 0;
    }

    @Override
    public boolean containsValue(Object object0) {
        return this.findValue(object0) >= 0;
    }

    private final boolean contentEquals(Map map0) {
        return this.size() == map0.size() && this.containsAllEntries$kotlin_stdlib(map0.entrySet());
    }

    private final void ensureCapacity(int v) {
        if(v < 0) {
            throw new OutOfMemoryError();
        }
        if(v > this.getCapacity$kotlin_stdlib()) {
            int v1 = this.getCapacity$kotlin_stdlib() * 3 / 2;
            if(v <= v1) {
                v = v1;
            }
            this.keysArray = ListBuilderKt.copyOfUninitializedElements(this.keysArray, v);
            this.valuesArray = this.valuesArray == null ? null : ListBuilderKt.copyOfUninitializedElements(this.valuesArray, v);
            int[] arr_v = Arrays.copyOf(this.presenceArray, v);
            Intrinsics.checkNotNullExpressionValue(arr_v, "copyOf(this, newSize)");
            this.presenceArray = arr_v;
            int v2 = Companion.access$computeHashSize(MapBuilder.Companion, v);
            if(v2 > this.getHashSize()) {
                this.rehash(v2);
            }
        }
    }

    private final void ensureExtraCapacity(int v) {
        if(this.shouldCompact(v)) {
            this.rehash(this.getHashSize());
            return;
        }
        this.ensureCapacity(this.length + v);
    }

    public final EntriesItr entriesIterator$kotlin_stdlib() {
        return new EntriesItr(this);
    }

    @Override
    public final Set entrySet() {
        return this.getEntries();
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 == this || object0 instanceof Map && this.contentEquals(((Map)object0));
    }

    private final int findKey(Object object0) {
        int v = this.hash(object0);
        int v1 = this.maxProbeDistance;
        while(true) {
            int v2 = this.hashArray[v];
            if(v2 == 0) {
                return -1;
            }
            if(v2 > 0 && Intrinsics.areEqual(this.keysArray[v2 - 1], object0)) {
                return v2 - 1;
            }
            --v1;
            if(v1 < 0) {
                return -1;
            }
            if(v == 0) {
                v = this.getHashSize() - 1;
            }
            else {
                --v;
            }
        }
    }

    private final int findValue(Object object0) {
        int v = this.length;
    alab1:
        while(true) {
            do {
                --v;
                if(v < 0) {
                    break alab1;
                }
                if(this.presenceArray[v] < 0) {
                    continue alab1;
                }
                Object[] arr_object = this.valuesArray;
                Intrinsics.checkNotNull(arr_object);
            }
            while(!Intrinsics.areEqual(arr_object[v], object0));
            return v;
        }
        return -1;
    }

    @Override
    public Object get(Object object0) {
        int v = this.findKey(object0);
        if(v < 0) {
            return null;
        }
        Object[] arr_object = this.valuesArray;
        Intrinsics.checkNotNull(arr_object);
        return arr_object[v];
    }

    public final int getCapacity$kotlin_stdlib() {
        return this.keysArray.length;
    }

    public Set getEntries() {
        MapBuilderEntries mapBuilderEntries0 = this.entriesView;
        if(mapBuilderEntries0 == null) {
            MapBuilderEntries mapBuilderEntries1 = new MapBuilderEntries(this);
            this.entriesView = mapBuilderEntries1;
            return mapBuilderEntries1;
        }
        return mapBuilderEntries0;
    }

    private final int getHashSize() {
        return this.hashArray.length;
    }

    public Set getKeys() {
        MapBuilderKeys mapBuilderKeys0 = this.keysView;
        if(mapBuilderKeys0 == null) {
            MapBuilderKeys mapBuilderKeys1 = new MapBuilderKeys(this);
            this.keysView = mapBuilderKeys1;
            return mapBuilderKeys1;
        }
        return mapBuilderKeys0;
    }

    public int getSize() {
        return this.size;
    }

    public Collection getValues() {
        MapBuilderValues mapBuilderValues0 = this.valuesView;
        if(mapBuilderValues0 == null) {
            MapBuilderValues mapBuilderValues1 = new MapBuilderValues(this);
            this.valuesView = mapBuilderValues1;
            return mapBuilderValues1;
        }
        return mapBuilderValues0;
    }

    private final int hash(Object object0) {
        return object0 == null ? 0 : object0.hashCode() * -1640531527 >>> this.hashShift;
    }

    @Override
    public int hashCode() {
        int v = 0;
        EntriesItr mapBuilder$EntriesItr0 = this.entriesIterator$kotlin_stdlib();
        while(mapBuilder$EntriesItr0.hasNext()) {
            v += mapBuilder$EntriesItr0.nextHashCode$kotlin_stdlib();
        }
        return v;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    public final boolean isReadOnly$kotlin_stdlib() {
        return this.isReadOnly;
    }

    @Override
    public final Set keySet() {
        return this.getKeys();
    }

    public final KeysItr keysIterator$kotlin_stdlib() {
        return new KeysItr(this);
    }

    @Override
    public Object put(Object object0, Object object1) {
        this.checkIsMutable$kotlin_stdlib();
        int v = this.addKey$kotlin_stdlib(object0);
        Object[] arr_object = this.allocateValuesArray();
        if(v < 0) {
            Object object2 = arr_object[-v - 1];
            arr_object[-v - 1] = object1;
            return object2;
        }
        arr_object[v] = object1;
        return null;
    }

    @Override
    public void putAll(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "from");
        this.checkIsMutable$kotlin_stdlib();
        this.putAllEntries(map0.entrySet());
    }

    private final boolean putAllEntries(Collection collection0) {
        boolean z = false;
        if(collection0.isEmpty()) {
            return false;
        }
        this.ensureExtraCapacity(collection0.size());
        for(Object object0: collection0) {
            if(this.putEntry(((Map.Entry)object0))) {
                z = true;
            }
        }
        return z;
    }

    private final boolean putEntry(Map.Entry map$Entry0) {
        int v = this.addKey$kotlin_stdlib(map$Entry0.getKey());
        Object[] arr_object = this.allocateValuesArray();
        if(v >= 0) {
            arr_object[v] = map$Entry0.getValue();
            return true;
        }
        Object object0 = arr_object[-v - 1];
        if(!Intrinsics.areEqual(map$Entry0.getValue(), object0)) {
            arr_object[-v - 1] = map$Entry0.getValue();
            return true;
        }
        return false;
    }

    private final boolean putRehash(int v) {
        int v1 = this.hash(this.keysArray[v]);
        int v2 = this.maxProbeDistance;
        while(true) {
            int[] arr_v = this.hashArray;
            if(arr_v[v1] == 0) {
                arr_v[v1] = v + 1;
                this.presenceArray[v] = v1;
                return true;
            }
            --v2;
            if(v2 < 0) {
                return false;
            }
            if(v1 == 0) {
                v1 = this.getHashSize() - 1;
            }
            else {
                --v1;
            }
        }
    }

    private final void rehash(int v) {
        if(this.length > this.size()) {
            this.compact();
        }
        if(v == this.getHashSize()) {
            ArraysKt.fill(this.hashArray, 0, 0, this.getHashSize());
        }
        else {
            this.hashArray = new int[v];
            this.hashShift = Companion.access$computeShift(MapBuilder.Companion, v);
        }
        for(int v1 = 0; v1 < this.length; ++v1) {
            if(!this.putRehash(v1)) {
                throw new IllegalStateException("This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?");
            }
        }
    }

    @Override
    public Object remove(Object object0) {
        int v = this.removeKey$kotlin_stdlib(object0);
        if(v < 0) {
            return null;
        }
        Object[] arr_object = this.valuesArray;
        Intrinsics.checkNotNull(arr_object);
        Object object1 = arr_object[v];
        ListBuilderKt.resetAt(arr_object, v);
        return object1;
    }

    public final boolean removeEntry$kotlin_stdlib(Map.Entry map$Entry0) {
        Intrinsics.checkNotNullParameter(map$Entry0, "entry");
        this.checkIsMutable$kotlin_stdlib();
        int v = this.findKey(map$Entry0.getKey());
        if(v < 0) {
            return false;
        }
        Object[] arr_object = this.valuesArray;
        Intrinsics.checkNotNull(arr_object);
        if(!Intrinsics.areEqual(arr_object[v], map$Entry0.getValue())) {
            return false;
        }
        this.removeKeyAt(v);
        return true;
    }

    private final void removeHashAt(int v) {
        int v1 = RangesKt.coerceAtMost(this.maxProbeDistance * 2, this.getHashSize() / 2);
        int v2 = 0;
        int v3 = v;
        do {
            v = v == 0 ? this.getHashSize() - 1 : v - 1;
            ++v2;
            if(v2 > this.maxProbeDistance) {
                this.hashArray[v3] = 0;
                return;
            }
            int[] arr_v = this.hashArray;
            int v4 = arr_v[v];
            if(v4 == 0) {
                arr_v[v3] = 0;
                return;
            }
            if(v4 < 0) {
                arr_v[v3] = -1;
                v3 = v;
                v2 = 0;
            }
            else if((this.hash(this.keysArray[v4 - 1]) - v & this.getHashSize() - 1) >= v2) {
                this.hashArray[v3] = v4;
                this.presenceArray[v4 - 1] = v3;
                v3 = v;
                v2 = 0;
            }
            --v1;
        }
        while(v1 >= 0);
        this.hashArray[v3] = -1;
    }

    public final int removeKey$kotlin_stdlib(Object object0) {
        this.checkIsMutable$kotlin_stdlib();
        int v = this.findKey(object0);
        if(v < 0) {
            return -1;
        }
        this.removeKeyAt(v);
        return v;
    }

    private final void removeKeyAt(int v) {
        ListBuilderKt.resetAt(this.keysArray, v);
        this.removeHashAt(this.presenceArray[v]);
        this.presenceArray[v] = -1;
        this.size = this.size() - 1;
    }

    public final boolean removeValue$kotlin_stdlib(Object object0) {
        this.checkIsMutable$kotlin_stdlib();
        int v = this.findValue(object0);
        if(v < 0) {
            return false;
        }
        this.removeKeyAt(v);
        return true;
    }

    private final boolean shouldCompact(int v) {
        int v1 = this.getCapacity$kotlin_stdlib() - this.length;
        int v2 = this.length - this.size();
        return v1 < v && v1 + v2 >= v && v2 >= this.getCapacity$kotlin_stdlib() / 4;
    }

    @Override
    public final int size() {
        return this.getSize();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder(this.size() * 3 + 2);
        stringBuilder0.append("{");
        EntriesItr mapBuilder$EntriesItr0 = this.entriesIterator$kotlin_stdlib();
        for(int v = 0; mapBuilder$EntriesItr0.hasNext(); ++v) {
            if(v > 0) {
                stringBuilder0.append(", ");
            }
            mapBuilder$EntriesItr0.nextAppendString(stringBuilder0);
        }
        stringBuilder0.append("}");
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "sb.toString()");
        return s;
    }

    @Override
    public final Collection values() {
        return this.getValues();
    }

    public final ValuesItr valuesIterator$kotlin_stdlib() {
        return new ValuesItr(this);
    }

    private final Object writeReplace() {
        if(!this.isReadOnly) {
            throw new NotSerializableException("The map cannot be serialized while it is being built.");
        }
        return new SerializedMap(this);
    }
}

