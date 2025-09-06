package androidx.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableList;
import kotlin.jvm.internal.markers.KMutableListIterator;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u001E\n\u0002\u0010\u001C\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0003678B\u000F\u0012\b\b\u0002\u0010\u0003\u001A\u00020\u0004\u00A2\u0006\u0002\u0010\u0005J\u0013\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00028\u0000\u00A2\u0006\u0002\u0010\u000EJ\u001D\u0010\u000B\u001A\u00020\u000F2\b\b\u0001\u0010\u0010\u001A\u00020\u00042\u0006\u0010\r\u001A\u00028\u0000\u00A2\u0006\u0002\u0010\u0011J\u0014\u0010\u0012\u001A\u00020\f2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002J\u0014\u0010\u0012\u001A\u00020\f2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0014J\u0019\u0010\u0012\u001A\u00020\f2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0015\u00A2\u0006\u0002\u0010\u0016J\u001E\u0010\u0012\u001A\u00020\f2\b\b\u0001\u0010\u0010\u001A\u00020\u00042\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002J#\u0010\u0012\u001A\u00020\f2\b\b\u0001\u0010\u0010\u001A\u00020\u00042\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0015\u00A2\u0006\u0002\u0010\u0017J\u001E\u0010\u0012\u001A\u00020\f2\b\b\u0001\u0010\u0010\u001A\u00020\u00042\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0018J\u0014\u0010\u0012\u001A\u00020\f2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0019J\u0014\u0010\u0012\u001A\u00020\f2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u001AJ\u0014\u0010\u0012\u001A\u00020\f2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u001BJ\u000E\u0010\u001C\u001A\b\u0012\u0004\u0012\u00028\u00000\u001AH\u0016J\f\u0010\u001D\u001A\b\u0012\u0004\u0012\u00028\u00000\u001EJ\u0006\u0010\u001F\u001A\u00020\u000FJ\u000E\u0010 \u001A\u00020\u000F2\u0006\u0010\u0006\u001A\u00020\u0004J\u0016\u0010!\u001A\u00020\u000F2\u0006\u0010\r\u001A\u00028\u0000H\u0086\n\u00A2\u0006\u0002\u0010\"J\u0017\u0010!\u001A\u00020\u000F2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0086\u0002J\u0017\u0010!\u001A\u00020\u000F2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0086\u0002J\u001C\u0010!\u001A\u00020\u000F2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0086\u0002\u00A2\u0006\u0002\u0010#J\u0017\u0010!\u001A\u00020\u000F2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0019H\u0086\u0002J\u0017\u0010!\u001A\u00020\u000F2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u001AH\u0086\u0002J\u0017\u0010!\u001A\u00020\u000F2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u001BH\u0086\u0002J\u0016\u0010$\u001A\u00020\u000F2\u0006\u0010\r\u001A\u00028\u0000H\u0086\n\u00A2\u0006\u0002\u0010\"J\u0017\u0010$\u001A\u00020\u000F2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0086\u0002J\u0017\u0010$\u001A\u00020\u000F2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0014H\u0086\u0002J\u001C\u0010$\u001A\u00020\u000F2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0015H\u0086\u0002\u00A2\u0006\u0002\u0010#J\u0017\u0010$\u001A\u00020\u000F2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0019H\u0086\u0002J\u0017\u0010$\u001A\u00020\u000F2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u001AH\u0086\u0002J\u0017\u0010$\u001A\u00020\u000F2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u001BH\u0086\u0002J\u0013\u0010%\u001A\u00020\f2\u0006\u0010\r\u001A\u00028\u0000\u00A2\u0006\u0002\u0010\u000EJ\u0014\u0010&\u001A\u00020\f2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002J\u0014\u0010&\u001A\u00020\f2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0014J\u0019\u0010&\u001A\u00020\f2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0015\u00A2\u0006\u0002\u0010\u0016J\u0014\u0010&\u001A\u00020\f2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0019J\u0014\u0010&\u001A\u00020\f2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u001AJ\u0014\u0010&\u001A\u00020\f2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u001BJ\u0015\u0010\'\u001A\u00028\u00002\b\b\u0001\u0010\u0010\u001A\u00020\u0004\u00A2\u0006\u0002\u0010(J/\u0010)\u001A\u00020\u000F2!\u0010*\u001A\u001D\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\f0+H\u0086\b\u00F8\u0001\u0000J\u001A\u0010.\u001A\u00020\u000F2\b\b\u0001\u0010/\u001A\u00020\u00042\b\b\u0001\u00100\u001A\u00020\u0004J\u0014\u00101\u001A\u00020\f2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002J\u0019\u00101\u001A\u00020\f2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0015\u00A2\u0006\u0002\u0010\u0016J\u0014\u00101\u001A\u00020\f2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0018J\u0014\u00101\u001A\u00020\f2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u0019J\u0014\u00101\u001A\u00020\f2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00000\u001BJ \u00102\u001A\u00028\u00002\b\b\u0001\u0010\u0010\u001A\u00020\u00042\u0006\u0010\r\u001A\u00028\u0000H\u0086\u0002\u00A2\u0006\u0002\u00103J\u0010\u00104\u001A\u00020\u000F2\b\b\u0002\u00105\u001A\u00020\u0004R\u0012\u0010\u0006\u001A\u00020\u00048\u00C6\u0002\u00A2\u0006\u0006\u001A\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001A\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\nX\u0082\u000E\u00A2\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u00069"}, d2 = {"Landroidx/collection/MutableObjectList;", "E", "Landroidx/collection/ObjectList;", "initialCapacity", "", "(I)V", "capacity", "getCapacity", "()I", "list", "Landroidx/collection/MutableObjectList$ObjectListMutableList;", "add", "", "element", "(Ljava/lang/Object;)Z", "", "index", "(ILjava/lang/Object;)V", "addAll", "elements", "Landroidx/collection/ScatterSet;", "", "([Ljava/lang/Object;)Z", "(I[Ljava/lang/Object;)Z", "", "", "", "Lkotlin/sequences/Sequence;", "asList", "asMutableList", "", "clear", "ensureCapacity", "minusAssign", "(Ljava/lang/Object;)V", "([Ljava/lang/Object;)V", "plusAssign", "remove", "removeAll", "removeAt", "(I)Ljava/lang/Object;", "removeIf", "predicate", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "removeRange", "start", "end", "retainAll", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "trim", "minCapacity", "MutableObjectListIterator", "ObjectListMutableList", "SubList", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class MutableObjectList extends ObjectList {
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010+\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\t\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001B\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0015\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00028\u0001H\u0016¢\u0006\u0002\u0010\fJ\t\u0010\r\u001A\u00020\u000EH\u0096\u0002J\b\u0010\u000F\u001A\u00020\u000EH\u0016J\u000E\u0010\u0010\u001A\u00028\u0001H\u0096\u0002¢\u0006\u0002\u0010\u0011J\b\u0010\u0012\u001A\u00020\u0006H\u0016J\r\u0010\u0013\u001A\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0011J\b\u0010\u0014\u001A\u00020\u0006H\u0016J\b\u0010\u0015\u001A\u00020\nH\u0016J\u0015\u0010\u0016\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00028\u0001H\u0016¢\u0006\u0002\u0010\fR\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\u0006X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/collection/MutableObjectList$MutableObjectListIterator;", "T", "", "list", "", "index", "", "(Ljava/util/List;I)V", "prevIndex", "add", "", "element", "(Ljava/lang/Object;)V", "hasNext", "", "hasPrevious", "next", "()Ljava/lang/Object;", "nextIndex", "previous", "previousIndex", "remove", "set", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class MutableObjectListIterator implements ListIterator, KMutableListIterator {
        private final List list;
        private int prevIndex;

        public MutableObjectListIterator(List list0, int v) {
            Intrinsics.checkNotNullParameter(list0, "list");
            super();
            this.list = list0;
            this.prevIndex = v - 1;
        }

        @Override
        public void add(Object object0) {
            int v = this.prevIndex + 1;
            this.prevIndex = v;
            this.list.add(v, object0);
        }

        @Override
        public boolean hasNext() {
            return this.prevIndex < this.list.size() - 1;
        }

        @Override
        public boolean hasPrevious() {
            return this.prevIndex >= 0;
        }

        @Override
        public Object next() {
            int v = this.prevIndex + 1;
            this.prevIndex = v;
            return this.list.get(v);
        }

        @Override
        public int nextIndex() {
            return this.prevIndex + 1;
        }

        @Override
        public Object previous() {
            int v = this.prevIndex;
            this.prevIndex = v - 1;
            return this.list.get(v);
        }

        @Override
        public int previousIndex() {
            return this.prevIndex;
        }

        @Override
        public void remove() {
            this.list.remove(this.prevIndex);
            --this.prevIndex;
        }

        @Override
        public void set(Object object0) {
            this.list.set(this.prevIndex, object0);
        }
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u001E\n\u0002\b\t\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0010+\n\u0002\b\n\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00010\u0004\u00A2\u0006\u0002\u0010\u0005J\u0015\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00028\u0001H\u0016\u00A2\u0006\u0002\u0010\rJ\u001D\u0010\n\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u00072\u0006\u0010\f\u001A\u00028\u0001H\u0016\u00A2\u0006\u0002\u0010\u0010J\u001E\u0010\u0011\u001A\u00020\u000B2\u0006\u0010\u000F\u001A\u00020\u00072\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u00028\u00010\u0013H\u0016J\u0016\u0010\u0011\u001A\u00020\u000B2\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u00028\u00010\u0013H\u0016J\b\u0010\u0014\u001A\u00020\u000EH\u0016J\u0016\u0010\u0015\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00028\u0001H\u0096\u0002\u00A2\u0006\u0002\u0010\rJ\u0016\u0010\u0016\u001A\u00020\u000B2\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u00028\u00010\u0013H\u0016J\u0016\u0010\u0017\u001A\u00028\u00012\u0006\u0010\u000F\u001A\u00020\u0007H\u0096\u0002\u00A2\u0006\u0002\u0010\u0018J\u0015\u0010\u0019\u001A\u00020\u00072\u0006\u0010\f\u001A\u00028\u0001H\u0016\u00A2\u0006\u0002\u0010\u001AJ\b\u0010\u001B\u001A\u00020\u000BH\u0016J\u000F\u0010\u001C\u001A\b\u0012\u0004\u0012\u00028\u00010\u001DH\u0096\u0002J\u0015\u0010\u001E\u001A\u00020\u00072\u0006\u0010\f\u001A\u00028\u0001H\u0016\u00A2\u0006\u0002\u0010\u001AJ\u000E\u0010\u001F\u001A\b\u0012\u0004\u0012\u00028\u00010 H\u0016J\u0016\u0010\u001F\u001A\b\u0012\u0004\u0012\u00028\u00010 2\u0006\u0010\u000F\u001A\u00020\u0007H\u0016J\u0015\u0010!\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00028\u0001H\u0016\u00A2\u0006\u0002\u0010\rJ\u0016\u0010\"\u001A\u00020\u000B2\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u00028\u00010\u0013H\u0016J\u0015\u0010#\u001A\u00028\u00012\u0006\u0010\u000F\u001A\u00020\u0007H\u0016\u00A2\u0006\u0002\u0010\u0018J\u0016\u0010$\u001A\u00020\u000B2\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u00028\u00010\u0013H\u0016J\u001E\u0010%\u001A\u00028\u00012\u0006\u0010\u000F\u001A\u00020\u00072\u0006\u0010\f\u001A\u00028\u0001H\u0096\u0002\u00A2\u0006\u0002\u0010&J\u001E\u0010\'\u001A\b\u0012\u0004\u0012\u00028\u00010\u00022\u0006\u0010(\u001A\u00020\u00072\u0006\u0010)\u001A\u00020\u0007H\u0016R\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00010\u0004X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001A\u00020\u00078VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\b\u0010\t\u00A8\u0006*"}, d2 = {"Landroidx/collection/MutableObjectList$ObjectListMutableList;", "T", "", "objectList", "Landroidx/collection/MutableObjectList;", "(Landroidx/collection/MutableObjectList;)V", "size", "", "getSize", "()I", "add", "", "element", "(Ljava/lang/Object;)Z", "", "index", "(ILjava/lang/Object;)V", "addAll", "elements", "", "clear", "contains", "containsAll", "get", "(I)Ljava/lang/Object;", "indexOf", "(Ljava/lang/Object;)I", "isEmpty", "iterator", "", "lastIndexOf", "listIterator", "", "remove", "removeAll", "removeAt", "retainAll", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "subList", "fromIndex", "toIndex", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class ObjectListMutableList implements List, KMutableList {
        private final MutableObjectList objectList;

        public ObjectListMutableList(MutableObjectList mutableObjectList0) {
            Intrinsics.checkNotNullParameter(mutableObjectList0, "objectList");
            super();
            this.objectList = mutableObjectList0;
        }

        @Override
        public void add(int v, Object object0) {
            this.objectList.add(v, object0);
        }

        @Override
        public boolean add(Object object0) {
            return this.objectList.add(object0);
        }

        @Override
        public boolean addAll(int v, Collection collection0) {
            Intrinsics.checkNotNullParameter(collection0, "elements");
            return this.objectList.addAll(v, collection0);
        }

        @Override
        public boolean addAll(Collection collection0) {
            Intrinsics.checkNotNullParameter(collection0, "elements");
            return this.objectList.addAll(collection0);
        }

        @Override
        public void clear() {
            this.objectList.clear();
        }

        @Override
        public boolean contains(Object object0) {
            return this.objectList.contains(object0);
        }

        @Override
        public boolean containsAll(Collection collection0) {
            Intrinsics.checkNotNullParameter(collection0, "elements");
            return this.objectList.containsAll(collection0);
        }

        @Override
        public Object get(int v) {
            ObjectListKt.checkIndex(this, v);
            return this.objectList.get(v);
        }

        public int getSize() {
            return this.objectList.getSize();
        }

        @Override
        public int indexOf(Object object0) {
            return this.objectList.indexOf(object0);
        }

        @Override
        public boolean isEmpty() {
            return this.objectList.isEmpty();
        }

        @Override
        public Iterator iterator() {
            return new MutableObjectListIterator(this, 0);
        }

        @Override
        public int lastIndexOf(Object object0) {
            return this.objectList.lastIndexOf(object0);
        }

        @Override
        public ListIterator listIterator() {
            return new MutableObjectListIterator(this, 0);
        }

        @Override
        public ListIterator listIterator(int v) {
            return new MutableObjectListIterator(this, v);
        }

        @Override
        public final Object remove(int v) {
            return this.removeAt(v);
        }

        @Override
        public boolean remove(Object object0) {
            return this.objectList.remove(object0);
        }

        @Override
        public boolean removeAll(Collection collection0) {
            Intrinsics.checkNotNullParameter(collection0, "elements");
            return this.objectList.removeAll(collection0);
        }

        public Object removeAt(int v) {
            ObjectListKt.checkIndex(this, v);
            return this.objectList.removeAt(v);
        }

        @Override
        public boolean retainAll(Collection collection0) {
            Intrinsics.checkNotNullParameter(collection0, "elements");
            return this.objectList.retainAll(collection0);
        }

        @Override
        public Object set(int v, Object object0) {
            ObjectListKt.checkIndex(this, v);
            return this.objectList.set(v, object0);
        }

        @Override
        public final int size() {
            return this.getSize();
        }

        @Override
        public List subList(int v, int v1) {
            ObjectListKt.checkSubIndex(this, v, v1);
            return new SubList(this, v, v1);
        }

        @Override
        public Object[] toArray() {
            return CollectionToArray.toArray(this);
        }

        @Override
        public Object[] toArray(Object[] arr_object) {
            Intrinsics.checkNotNullParameter(arr_object, "array");
            return CollectionToArray.toArray(this, arr_object);
        }
    }

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u001E\n\u0002\b\t\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0010+\n\u0002\b\n\b\u0002\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B#\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00010\u0002\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0005\u00A2\u0006\u0002\u0010\u0007J\u0015\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00028\u0001H\u0016\u00A2\u0006\u0002\u0010\u000EJ\u001D\u0010\u000B\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u00052\u0006\u0010\r\u001A\u00028\u0001H\u0016\u00A2\u0006\u0002\u0010\u0011J\u001E\u0010\u0012\u001A\u00020\f2\u0006\u0010\u0010\u001A\u00020\u00052\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00010\u0014H\u0016J\u0016\u0010\u0012\u001A\u00020\f2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00010\u0014H\u0016J\b\u0010\u0015\u001A\u00020\u000FH\u0016J\u0016\u0010\u0016\u001A\u00020\f2\u0006\u0010\r\u001A\u00028\u0001H\u0096\u0002\u00A2\u0006\u0002\u0010\u000EJ\u0016\u0010\u0017\u001A\u00020\f2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00010\u0014H\u0016J\u0016\u0010\u0018\u001A\u00028\u00012\u0006\u0010\u0010\u001A\u00020\u0005H\u0096\u0002\u00A2\u0006\u0002\u0010\u0019J\u0015\u0010\u001A\u001A\u00020\u00052\u0006\u0010\r\u001A\u00028\u0001H\u0016\u00A2\u0006\u0002\u0010\u001BJ\b\u0010\u001C\u001A\u00020\fH\u0016J\u000F\u0010\u001D\u001A\b\u0012\u0004\u0012\u00028\u00010\u001EH\u0096\u0002J\u0015\u0010\u001F\u001A\u00020\u00052\u0006\u0010\r\u001A\u00028\u0001H\u0016\u00A2\u0006\u0002\u0010\u001BJ\u000E\u0010 \u001A\b\u0012\u0004\u0012\u00028\u00010!H\u0016J\u0016\u0010 \u001A\b\u0012\u0004\u0012\u00028\u00010!2\u0006\u0010\u0010\u001A\u00020\u0005H\u0016J\u0015\u0010\"\u001A\u00020\f2\u0006\u0010\r\u001A\u00028\u0001H\u0016\u00A2\u0006\u0002\u0010\u000EJ\u0016\u0010#\u001A\u00020\f2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00010\u0014H\u0016J\u0015\u0010$\u001A\u00028\u00012\u0006\u0010\u0010\u001A\u00020\u0005H\u0016\u00A2\u0006\u0002\u0010\u0019J\u0016\u0010%\u001A\u00020\f2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00028\u00010\u0014H\u0016J\u001E\u0010&\u001A\u00028\u00012\u0006\u0010\u0010\u001A\u00020\u00052\u0006\u0010\r\u001A\u00028\u0001H\u0096\u0002\u00A2\u0006\u0002\u0010\'J\u001E\u0010(\u001A\b\u0012\u0004\u0012\u00028\u00010\u00022\u0006\u0010)\u001A\u00020\u00052\u0006\u0010*\u001A\u00020\u0005H\u0016R\u000E\u0010\u0006\u001A\u00020\u0005X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00010\u0002X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010\b\u001A\u00020\u00058VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\t\u0010\nR\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006+"}, d2 = {"Landroidx/collection/MutableObjectList$SubList;", "T", "", "list", "start", "", "end", "(Ljava/util/List;II)V", "size", "getSize", "()I", "add", "", "element", "(Ljava/lang/Object;)Z", "", "index", "(ILjava/lang/Object;)V", "addAll", "elements", "", "clear", "contains", "containsAll", "get", "(I)Ljava/lang/Object;", "indexOf", "(Ljava/lang/Object;)I", "isEmpty", "iterator", "", "lastIndexOf", "listIterator", "", "remove", "removeAll", "removeAt", "retainAll", "set", "(ILjava/lang/Object;)Ljava/lang/Object;", "subList", "fromIndex", "toIndex", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class SubList implements List, KMutableList {
        private int end;
        private final List list;
        private final int start;

        public SubList(List list0, int v, int v1) {
            Intrinsics.checkNotNullParameter(list0, "list");
            super();
            this.list = list0;
            this.start = v;
            this.end = v1;
        }

        @Override
        public void add(int v, Object object0) {
            this.list.add(v + this.start, object0);
            ++this.end;
        }

        @Override
        public boolean add(Object object0) {
            int v = this.end;
            this.end = v + 1;
            this.list.add(v, object0);
            return true;
        }

        @Override
        public boolean addAll(int v, Collection collection0) {
            Intrinsics.checkNotNullParameter(collection0, "elements");
            this.list.addAll(v + this.start, collection0);
            this.end += collection0.size();
            return collection0.size() > 0;
        }

        @Override
        public boolean addAll(Collection collection0) {
            Intrinsics.checkNotNullParameter(collection0, "elements");
            this.list.addAll(this.end, collection0);
            this.end += collection0.size();
            return collection0.size() > 0;
        }

        @Override
        public void clear() {
            int v = this.end - 1;
            int v1 = this.start;
            if(v1 <= v) {
                while(true) {
                    this.list.remove(v);
                    if(v == v1) {
                        break;
                    }
                    --v;
                }
            }
            this.end = this.start;
        }

        @Override
        public boolean contains(Object object0) {
            int v = this.start;
            int v1 = this.end;
            while(v < v1) {
                if(Intrinsics.areEqual(this.list.get(v), object0)) {
                    return true;
                }
                ++v;
            }
            return false;
        }

        @Override
        public boolean containsAll(Collection collection0) {
            Intrinsics.checkNotNullParameter(collection0, "elements");
            for(Object object0: collection0) {
                if(!this.contains(object0)) {
                    return false;
                }
                if(false) {
                    break;
                }
            }
            return true;
        }

        @Override
        public Object get(int v) {
            ObjectListKt.checkIndex(this, v);
            return this.list.get(v + this.start);
        }

        public int getSize() {
            return this.end - this.start;
        }

        @Override
        public int indexOf(Object object0) {
            int v = this.start;
            int v1 = this.end;
            while(v < v1) {
                if(Intrinsics.areEqual(this.list.get(v), object0)) {
                    return v - this.start;
                }
                ++v;
            }
            return -1;
        }

        @Override
        public boolean isEmpty() {
            return this.end == this.start;
        }

        @Override
        public Iterator iterator() {
            return new MutableObjectListIterator(this, 0);
        }

        @Override
        public int lastIndexOf(Object object0) {
            int v = this.end - 1;
            int v1 = this.start;
            if(v1 <= v) {
                while(true) {
                    if(Intrinsics.areEqual(this.list.get(v), object0)) {
                        return v - this.start;
                    }
                    if(v == v1) {
                        break;
                    }
                    --v;
                }
            }
            return -1;
        }

        @Override
        public ListIterator listIterator() {
            return new MutableObjectListIterator(this, 0);
        }

        @Override
        public ListIterator listIterator(int v) {
            return new MutableObjectListIterator(this, v);
        }

        @Override
        public final Object remove(int v) {
            return this.removeAt(v);
        }

        @Override
        public boolean remove(Object object0) {
            int v = this.start;
            int v1 = this.end;
            while(v < v1) {
                if(Intrinsics.areEqual(this.list.get(v), object0)) {
                    this.list.remove(v);
                    --this.end;
                    return true;
                }
                ++v;
            }
            return false;
        }

        @Override
        public boolean removeAll(Collection collection0) {
            Intrinsics.checkNotNullParameter(collection0, "elements");
            int v = this.end;
            for(Object object0: collection0) {
                this.remove(object0);
            }
            return v != this.end;
        }

        public Object removeAt(int v) {
            ObjectListKt.checkIndex(this, v);
            Object object0 = this.list.remove(v + this.start);
            --this.end;
            return object0;
        }

        @Override
        public boolean retainAll(Collection collection0) {
            Intrinsics.checkNotNullParameter(collection0, "elements");
            int v = this.end;
            int v1 = v - 1;
            int v2 = this.start;
            if(v2 <= v1) {
                while(true) {
                    if(!collection0.contains(this.list.get(v1))) {
                        this.list.remove(v1);
                        --this.end;
                    }
                    if(v1 == v2) {
                        break;
                    }
                    --v1;
                }
            }
            return v != this.end;
        }

        @Override
        public Object set(int v, Object object0) {
            ObjectListKt.checkIndex(this, v);
            return this.list.set(v + this.start, object0);
        }

        @Override
        public final int size() {
            return this.getSize();
        }

        @Override
        public List subList(int v, int v1) {
            ObjectListKt.checkSubIndex(this, v, v1);
            return new SubList(this, v, v1);
        }

        @Override
        public Object[] toArray() {
            return CollectionToArray.toArray(this);
        }

        @Override
        public Object[] toArray(Object[] arr_object) {
            Intrinsics.checkNotNullParameter(arr_object, "array");
            return CollectionToArray.toArray(this, arr_object);
        }
    }

    private ObjectListMutableList list;

    public MutableObjectList() {
        this(0, 1, null);
    }

    public MutableObjectList(int v) {
        super(v, null);
    }

    public MutableObjectList(int v, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 1) != 0) {
            v = 16;
        }
        this(v);
    }

    public final void add(int v, Object object0) {
        if(v < 0 || v > this._size) {
            throw new IndexOutOfBoundsException("Index " + v + " must be in 0.." + this._size);
        }
        this.ensureCapacity(this._size + 1);
        Object[] arr_object = this.content;
        if(v != this._size) {
            ArraysKt.copyInto(arr_object, arr_object, v + 1, v, this._size);
        }
        arr_object[v] = object0;
        ++this._size;
    }

    public final boolean add(Object object0) {
        this.ensureCapacity(this._size + 1);
        this.content[this._size] = object0;
        ++this._size;
        return true;
    }

    public final boolean addAll(int v, ObjectList objectList0) {
        Intrinsics.checkNotNullParameter(objectList0, "elements");
        if(v < 0 || v > this._size) {
            throw new IndexOutOfBoundsException("Index " + v + " must be in 0.." + this._size);
        }
        if(objectList0.isEmpty()) {
            return false;
        }
        this.ensureCapacity(this._size + objectList0._size);
        Object[] arr_object = this.content;
        if(v != this._size) {
            ArraysKt.copyInto(arr_object, arr_object, objectList0._size + v, v, this._size);
        }
        ArraysKt.copyInto(objectList0.content, arr_object, v, 0, objectList0._size);
        this._size += objectList0._size;
        return true;
    }

    public final boolean addAll(int v, Collection collection0) {
        int v1 = 0;
        Intrinsics.checkNotNullParameter(collection0, "elements");
        if(v < 0 || v > this._size) {
            throw new IndexOutOfBoundsException("Index " + v + " must be in 0.." + this._size);
        }
        if(collection0.isEmpty()) {
            return false;
        }
        this.ensureCapacity(this._size + collection0.size());
        Object[] arr_object = this.content;
        if(v != this._size) {
            ArraysKt.copyInto(arr_object, arr_object, collection0.size() + v, v, this._size);
        }
        for(Object object0: collection0) {
            if(v1 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arr_object[v1 + v] = object0;
            ++v1;
        }
        this._size += collection0.size();
        return true;
    }

    public final boolean addAll(int v, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        if(v < 0 || v > this._size) {
            throw new IndexOutOfBoundsException("Index " + v + " must be in 0.." + this._size);
        }
        if(arr_object.length == 0) {
            return false;
        }
        this.ensureCapacity(this._size + arr_object.length);
        Object[] arr_object1 = this.content;
        if(v != this._size) {
            ArraysKt.copyInto(arr_object1, arr_object1, arr_object.length + v, v, this._size);
        }
        ArraysKt.copyInto$default(arr_object, arr_object1, v, 0, 0, 12, null);
        this._size += arr_object.length;
        return true;
    }

    public final boolean addAll(ObjectList objectList0) {
        Intrinsics.checkNotNullParameter(objectList0, "elements");
        int v = this._size;
        this.plusAssign(objectList0);
        return v != this._size;
    }

    public final boolean addAll(ScatterSet scatterSet0) {
        Intrinsics.checkNotNullParameter(scatterSet0, "elements");
        int v = this._size;
        this.plusAssign(scatterSet0);
        return v != this._size;
    }

    public final boolean addAll(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "elements");
        int v = this._size;
        this.plusAssign(iterable0);
        return v != this._size;
    }

    public final boolean addAll(List list0) {
        Intrinsics.checkNotNullParameter(list0, "elements");
        int v = this._size;
        this.plusAssign(list0);
        return v != this._size;
    }

    public final boolean addAll(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "elements");
        int v = this._size;
        this.plusAssign(sequence0);
        return v != this._size;
    }

    public final boolean addAll(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        int v = this._size;
        this.plusAssign(arr_object);
        return v != this._size;
    }

    @Override  // androidx.collection.ObjectList
    public List asList() {
        return this.asMutableList();
    }

    public final List asMutableList() {
        ObjectListMutableList mutableObjectList$ObjectListMutableList0 = this.list;
        if(mutableObjectList$ObjectListMutableList0 != null) {
            return mutableObjectList$ObjectListMutableList0;
        }
        ObjectListMutableList mutableObjectList$ObjectListMutableList1 = new ObjectListMutableList(this);
        this.list = mutableObjectList$ObjectListMutableList1;
        return mutableObjectList$ObjectListMutableList1;
    }

    public final void clear() {
        ArraysKt.fill(this.content, null, 0, this._size);
        this._size = 0;
    }

    public final void ensureCapacity(int v) {
        Object[] arr_object = this.content;
        if(arr_object.length < v) {
            Object[] arr_object1 = Arrays.copyOf(arr_object, Math.max(v, arr_object.length * 3 / 2));
            Intrinsics.checkNotNullExpressionValue(arr_object1, "copyOf(this, newSize)");
            this.content = arr_object1;
        }
    }

    public final int getCapacity() {
        return this.content.length;
    }

    public final void minusAssign(ObjectList objectList0) {
        Intrinsics.checkNotNullParameter(objectList0, "elements");
        Object[] arr_object = objectList0.content;
        int v = objectList0._size;
        for(int v1 = 0; v1 < v; ++v1) {
            this.remove(arr_object[v1]);
        }
    }

    public final void minusAssign(ScatterSet scatterSet0) {
        Intrinsics.checkNotNullParameter(scatterSet0, "elements");
        Object[] arr_object = scatterSet0.elements;
        long[] arr_v = scatterSet0.metadata;
        int v = arr_v.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L) {
                            this.remove(arr_object[(v1 << 3) + v4]);
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

    public final void minusAssign(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "elements");
        for(Object object0: iterable0) {
            this.remove(object0);
        }
    }

    public final void minusAssign(Object object0) {
        this.remove(object0);
    }

    public final void minusAssign(List list0) {
        Intrinsics.checkNotNullParameter(list0, "elements");
        int v = list0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            this.remove(list0.get(v1));
        }
    }

    public final void minusAssign(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "elements");
        for(Object object0: sequence0) {
            this.remove(object0);
        }
    }

    public final void minusAssign(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        for(int v = 0; v < arr_object.length; ++v) {
            this.remove(arr_object[v]);
        }
    }

    public final void plusAssign(ObjectList objectList0) {
        Intrinsics.checkNotNullParameter(objectList0, "elements");
        if(objectList0.isEmpty()) {
            return;
        }
        this.ensureCapacity(this._size + objectList0._size);
        ArraysKt.copyInto(objectList0.content, this.content, this._size, 0, objectList0._size);
        this._size += objectList0._size;
    }

    public final void plusAssign(ScatterSet scatterSet0) {
        Intrinsics.checkNotNullParameter(scatterSet0, "elements");
        if(!scatterSet0.isEmpty()) {
            this.ensureCapacity(this._size + scatterSet0.getSize());
            Object[] arr_object = scatterSet0.elements;
            long[] arr_v = scatterSet0.metadata;
            int v = arr_v.length - 2;
            if(v >= 0) {
                int v1 = 0;
                while(true) {
                    long v2 = arr_v[v1];
                    if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                        int v3 = 8 - (~(v1 - v) >>> 0x1F);
                        for(int v4 = 0; v4 < v3; ++v4) {
                            if((0xFFL & v2) < 0x80L) {
                                this.add(arr_object[(v1 << 3) + v4]);
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
        }
    }

    public final void plusAssign(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "elements");
        for(Object object0: iterable0) {
            this.add(object0);
        }
    }

    public final void plusAssign(Object object0) {
        this.add(object0);
    }

    public final void plusAssign(List list0) {
        Intrinsics.checkNotNullParameter(list0, "elements");
        if(list0.isEmpty()) {
            return;
        }
        int v = this._size;
        this.ensureCapacity(list0.size() + v);
        Object[] arr_object = this.content;
        int v1 = list0.size();
        for(int v2 = 0; v2 < v1; ++v2) {
            arr_object[v2 + v] = list0.get(v2);
        }
        this._size += list0.size();
    }

    public final void plusAssign(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "elements");
        for(Object object0: sequence0) {
            this.add(object0);
        }
    }

    public final void plusAssign(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        if(arr_object.length == 0) {
            return;
        }
        this.ensureCapacity(this._size + arr_object.length);
        ArraysKt.copyInto$default(arr_object, this.content, this._size, 0, 0, 12, null);
        this._size += arr_object.length;
    }

    public final boolean remove(Object object0) {
        int v = this.indexOf(object0);
        if(v >= 0) {
            this.removeAt(v);
            return true;
        }
        return false;
    }

    public final boolean removeAll(ObjectList objectList0) {
        Intrinsics.checkNotNullParameter(objectList0, "elements");
        int v = this._size;
        this.minusAssign(objectList0);
        return v != this._size;
    }

    public final boolean removeAll(ScatterSet scatterSet0) {
        Intrinsics.checkNotNullParameter(scatterSet0, "elements");
        int v = this._size;
        this.minusAssign(scatterSet0);
        return v != this._size;
    }

    public final boolean removeAll(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "elements");
        int v = this._size;
        this.minusAssign(iterable0);
        return v != this._size;
    }

    public final boolean removeAll(List list0) {
        Intrinsics.checkNotNullParameter(list0, "elements");
        int v = this._size;
        this.minusAssign(list0);
        return v != this._size;
    }

    public final boolean removeAll(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "elements");
        int v = this._size;
        this.minusAssign(sequence0);
        return v != this._size;
    }

    public final boolean removeAll(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        int v = this._size;
        for(int v1 = 0; v1 < arr_object.length; ++v1) {
            this.remove(arr_object[v1]);
        }
        return v != this._size;
    }

    public final Object removeAt(int v) {
        if(v < 0 || v >= this._size) {
            throw new IndexOutOfBoundsException("Index " + v + " must be in 0.." + (this._size - 1));
        }
        Object[] arr_object = this.content;
        Object object0 = arr_object[v];
        if(v != this._size - 1) {
            ArraysKt.copyInto(arr_object, arr_object, v, v + 1, this._size);
        }
        --this._size;
        arr_object[this._size] = null;
        return object0;
    }

    public final void removeIf(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        int v = this._size;
        Object[] arr_object = this.content;
        int v1 = 0;
        IntRange intRange0 = RangesKt.until(0, this._size);
        int v2 = intRange0.getFirst();
        int v3 = intRange0.getLast();
        if(v2 <= v3) {
            while(true) {
                arr_object[v2 - v1] = arr_object[v2];
                if(((Boolean)function10.invoke(arr_object[v2])).booleanValue()) {
                    ++v1;
                }
                if(v2 == v3) {
                    break;
                }
                ++v2;
            }
        }
        ArraysKt.fill(arr_object, null, v - v1, v);
        this._size -= v1;
    }

    public final void removeRange(int v, int v1) {
        if(v < 0 || v > this._size || v1 < 0 || v1 > this._size) {
            throw new IndexOutOfBoundsException("Start (" + v + ") and end (" + v1 + ") must be in 0.." + this._size);
        }
        if(v1 < v) {
            throw new IllegalArgumentException("Start (" + v + ") is more than end (" + v1 + ')');
        }
        if(v1 != v) {
            if(v1 < this._size) {
                ArraysKt.copyInto(this.content, this.content, v, v1, this._size);
            }
            int v2 = this._size - (v1 - v);
            ArraysKt.fill(this.content, null, v2, this._size);
            this._size = v2;
        }
    }

    public final boolean retainAll(ObjectList objectList0) {
        Intrinsics.checkNotNullParameter(objectList0, "elements");
        int v = this._size;
        Object[] arr_object = this.content;
        for(int v1 = this._size - 1; -1 < v1; --v1) {
            if(!objectList0.contains(arr_object[v1])) {
                this.removeAt(v1);
            }
        }
        return v != this._size;
    }

    public final boolean retainAll(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "elements");
        int v = this._size;
        Object[] arr_object = this.content;
        for(int v1 = this._size - 1; -1 < v1; --v1) {
            if(!CollectionsKt.contains(iterable0, arr_object[v1])) {
                this.removeAt(v1);
            }
        }
        return v != this._size;
    }

    public final boolean retainAll(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "elements");
        int v = this._size;
        Object[] arr_object = this.content;
        for(int v1 = this._size - 1; -1 < v1; --v1) {
            if(!collection0.contains(arr_object[v1])) {
                this.removeAt(v1);
            }
        }
        return v != this._size;
    }

    public final boolean retainAll(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "elements");
        int v = this._size;
        Object[] arr_object = this.content;
        for(int v1 = this._size - 1; -1 < v1; --v1) {
            if(!SequencesKt.contains(sequence0, arr_object[v1])) {
                this.removeAt(v1);
            }
        }
        return v != this._size;
    }

    public final boolean retainAll(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        int v = this._size;
        Object[] arr_object1 = this.content;
        for(int v1 = this._size - 1; -1 < v1; --v1) {
            if(ArraysKt.indexOf(arr_object, arr_object1[v1]) < 0) {
                this.removeAt(v1);
            }
        }
        return v != this._size;
    }

    public final Object set(int v, Object object0) {
        if(v < 0 || v >= this._size) {
            throw new IndexOutOfBoundsException("set index " + v + " must be between 0 .. " + (this._size - 1));
        }
        Object[] arr_object = this.content;
        Object object1 = arr_object[v];
        arr_object[v] = object0;
        return object1;
    }

    public final void trim(int v) {
        int v1 = Math.max(v, this._size);
        if(this.content.length > v1) {
            Object[] arr_object = Arrays.copyOf(this.content, v1);
            Intrinsics.checkNotNullExpressionValue(arr_object, "copyOf(this, newSize)");
            this.content = arr_object;
        }
    }

    public static void trim$default(MutableObjectList mutableObjectList0, int v, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = mutableObjectList0._size;
        }
        mutableObjectList0.trim(v);
    }
}

