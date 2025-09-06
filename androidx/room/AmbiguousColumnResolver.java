package androidx.room;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.ranges.IntRange;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001:\u0003\u001B\u001C\u001DB\u0007\b\u0002¢\u0006\u0002\u0010\u0002JV\u0010\u0003\u001A\u00020\u0004\"\u0004\b\u0000\u0010\u00052\u0012\u0010\u0006\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u00070\u00072\u000E\b\u0002\u0010\b\u001A\b\u0012\u0004\u0012\u0002H\u00050\t2\b\b\u0002\u0010\n\u001A\u00020\u000B2\u0018\u0010\f\u001A\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u0007\u0012\u0004\u0012\u00020\u00040\rH\u0002JO\u0010\u000E\u001A\u00020\u00042\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\u000F0\u00072\f\u0010\u0010\u001A\b\u0012\u0004\u0012\u00020\u00120\u00112$\u0010\u0013\u001A \u0012\u0004\u0012\u00020\u000B\u0012\u0004\u0012\u00020\u000B\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000F0\u0007\u0012\u0004\u0012\u00020\u00040\u0014H\u0002¢\u0006\u0002\u0010\u0015J5\u0010\u0016\u001A\b\u0012\u0004\u0012\u00020\u00170\u00112\f\u0010\u0018\u001A\b\u0012\u0004\u0012\u00020\u00120\u00112\u0012\u0010\u0019\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\u0011H\u0007¢\u0006\u0002\u0010\u001A¨\u0006\u001E"}, d2 = {"Landroidx/room/AmbiguousColumnResolver;", "", "()V", "dfs", "", "T", "content", "", "current", "", "depth", "", "block", "Lkotlin/Function1;", "rabinKarpSearch", "Landroidx/room/AmbiguousColumnResolver$ResultColumn;", "pattern", "", "", "onHashMatch", "Lkotlin/Function3;", "(Ljava/util/List;[Ljava/lang/String;Lkotlin/jvm/functions/Function3;)V", "resolve", "", "resultColumns", "mappings", "([Ljava/lang/String;[[Ljava/lang/String;)[[I", "Match", "ResultColumn", "Solution", "room-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class AmbiguousColumnResolver {
    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u001B\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007R\u0017\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000B¨\u0006\f"}, d2 = {"Landroidx/room/AmbiguousColumnResolver$Match;", "", "resultRange", "Lkotlin/ranges/IntRange;", "resultIndices", "", "", "(Lkotlin/ranges/IntRange;Ljava/util/List;)V", "getResultIndices", "()Ljava/util/List;", "getResultRange", "()Lkotlin/ranges/IntRange;", "room-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class Match {
        private final List resultIndices;
        private final IntRange resultRange;

        public Match(IntRange intRange0, List list0) {
            Intrinsics.checkNotNullParameter(intRange0, "resultRange");
            Intrinsics.checkNotNullParameter(list0, "resultIndices");
            super();
            this.resultRange = intRange0;
            this.resultIndices = list0;
        }

        public final List getResultIndices() {
            return this.resultIndices;
        }

        public final IntRange getResultRange() {
            return this.resultRange;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000B\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000B\u001A\u00020\u0003HÆ\u0003J\t\u0010\f\u001A\u00020\u0005HÆ\u0003J\u001D\u0010\r\u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u0005HÆ\u0001J\u0013\u0010\u000E\u001A\u00020\u000F2\b\u0010\u0010\u001A\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001A\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001A\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Landroidx/room/AmbiguousColumnResolver$ResultColumn;", "", "name", "", "index", "", "(Ljava/lang/String;I)V", "getIndex", "()I", "getName", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "room-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class ResultColumn {
        private final int index;
        private final String name;

        public ResultColumn(String s, int v) {
            Intrinsics.checkNotNullParameter(s, "name");
            super();
            this.name = s;
            this.index = v;
        }

        public final String component1() {
            return this.name;
        }

        public final int component2() {
            return this.index;
        }

        public final ResultColumn copy(String s, int v) {
            Intrinsics.checkNotNullParameter(s, "name");
            return new ResultColumn(s, v);
        }

        public static ResultColumn copy$default(ResultColumn ambiguousColumnResolver$ResultColumn0, String s, int v, int v1, Object object0) {
            if((v1 & 1) != 0) {
                s = ambiguousColumnResolver$ResultColumn0.name;
            }
            if((v1 & 2) != 0) {
                v = ambiguousColumnResolver$ResultColumn0.index;
            }
            return ambiguousColumnResolver$ResultColumn0.copy(s, v);
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            if(!(object0 instanceof ResultColumn)) {
                return false;
            }
            return Intrinsics.areEqual(this.name, ((ResultColumn)object0).name) ? this.index == ((ResultColumn)object0).index : false;
        }

        public final int getIndex() {
            return this.index;
        }

        public final String getName() {
            return this.name;
        }

        @Override
        public int hashCode() {
            return this.name.hashCode() * 0x1F + this.index;
        }

        @Override
        public String toString() {
            return "ResultColumn(name=" + this.name + ", index=" + this.index + ')';
        }
    }

    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u000F\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000B\b\u0002\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B#\u0012\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\u0006¢\u0006\u0002\u0010\bJ\u0011\u0010\u000E\u001A\u00020\u00062\u0006\u0010\u000F\u001A\u00020\u0000H\u0096\u0002R\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\nR\u0017\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u0011\u0010\u0007\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\n¨\u0006\u0011"}, d2 = {"Landroidx/room/AmbiguousColumnResolver$Solution;", "", "matches", "", "Landroidx/room/AmbiguousColumnResolver$Match;", "coverageOffset", "", "overlaps", "(Ljava/util/List;II)V", "getCoverageOffset", "()I", "getMatches", "()Ljava/util/List;", "getOverlaps", "compareTo", "other", "Companion", "room-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class Solution implements Comparable {
        @Metadata(d1 = {"\u0000\u001E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0007\u001A\u00020\u00042\f\u0010\b\u001A\b\u0012\u0004\u0012\u00020\n0\tR\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u000B"}, d2 = {"Landroidx/room/AmbiguousColumnResolver$Solution$Companion;", "", "()V", "NO_SOLUTION", "Landroidx/room/AmbiguousColumnResolver$Solution;", "getNO_SOLUTION", "()Landroidx/room/AmbiguousColumnResolver$Solution;", "build", "matches", "", "Landroidx/room/AmbiguousColumnResolver$Match;", "room-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class Companion {
            private Companion() {
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }

            public final Solution build(List list0) {
                Intrinsics.checkNotNullParameter(list0, "matches");
                int v = 0;
                int v1 = 0;
                for(Object object0: list0) {
                    v1 += ((Match)object0).getResultRange().getLast() - ((Match)object0).getResultRange().getFirst() + 1 - ((Match)object0).getResultIndices().size();
                }
                Iterator iterator1 = list0.iterator();
                if(!iterator1.hasNext()) {
                    throw new NoSuchElementException();
                }
                Object object1 = iterator1.next();
                int v2 = ((Match)object1).getResultRange().getFirst();
                while(iterator1.hasNext()) {
                    Object object2 = iterator1.next();
                    int v3 = ((Match)object2).getResultRange().getFirst();
                    if(v2 > v3) {
                        v2 = v3;
                    }
                }
                Iterator iterator2 = list0.iterator();
                if(!iterator2.hasNext()) {
                    throw new NoSuchElementException();
                }
                Object object3 = iterator2.next();
                int v4 = ((Match)object3).getResultRange().getLast();
                while(iterator2.hasNext()) {
                    Object object4 = iterator2.next();
                    int v5 = ((Match)object4).getResultRange().getLast();
                    if(v4 < v5) {
                        v4 = v5;
                    }
                }
                Iterable iterable0 = new IntRange(v2, v4);
                if(!(iterable0 instanceof Collection) || !((Collection)iterable0).isEmpty()) {
                    int v6 = 0;
                    Iterator iterator3 = iterable0.iterator();
                label_32:
                    while(iterator3.hasNext()) {
                        int v7 = ((IntIterator)iterator3).nextInt();
                        int v8 = 0;
                        Iterator iterator4 = list0.iterator();
                        do {
                            if(!iterator4.hasNext()) {
                                continue label_32;
                            }
                            Object object5 = iterator4.next();
                            if(((Match)object5).getResultRange().contains(v7)) {
                                ++v8;
                            }
                        }
                        while(v8 <= 1);
                        ++v6;
                        if(v6 < 0) {
                            CollectionsKt.throwCountOverflow();
                        }
                    }
                    v = v6;
                }
                return new Solution(list0, v1, v);
            }

            public final Solution getNO_SOLUTION() {
                return Solution.NO_SOLUTION;
            }
        }

        public static final Companion Companion;
        private static final Solution NO_SOLUTION;
        private final int coverageOffset;
        private final List matches;
        private final int overlaps;

        static {
            Solution.Companion = new Companion(null);
            Solution.NO_SOLUTION = new Solution(CollectionsKt.emptyList(), 0x7FFFFFFF, 0x7FFFFFFF);
        }

        public Solution(List list0, int v, int v1) {
            Intrinsics.checkNotNullParameter(list0, "matches");
            super();
            this.matches = list0;
            this.coverageOffset = v;
            this.overlaps = v1;
        }

        public int compareTo(Solution ambiguousColumnResolver$Solution0) {
            Intrinsics.checkNotNullParameter(ambiguousColumnResolver$Solution0, "other");
            int v = Intrinsics.compare(this.overlaps, ambiguousColumnResolver$Solution0.overlaps);
            return v == 0 ? Intrinsics.compare(this.coverageOffset, ambiguousColumnResolver$Solution0.coverageOffset) : v;
        }

        @Override
        public int compareTo(Object object0) {
            return this.compareTo(((Solution)object0));
        }

        public final int getCoverageOffset() {
            return this.coverageOffset;
        }

        public final List getMatches() {
            return this.matches;
        }

        public final int getOverlaps() {
            return this.overlaps;
        }
    }

    public static final AmbiguousColumnResolver INSTANCE;

    static {
        AmbiguousColumnResolver.INSTANCE = new AmbiguousColumnResolver();
    }

    private final void dfs(List list0, List list1, int v, Function1 function10) {
        if(v == list0.size()) {
            function10.invoke(CollectionsKt.toList(list1));
            return;
        }
        for(Object object0: ((Iterable)list0.get(v))) {
            list1.add(object0);
            AmbiguousColumnResolver.INSTANCE.dfs(list0, list1, v + 1, function10);
            CollectionsKt.removeLast(list1);
        }
    }

    static void dfs$default(AmbiguousColumnResolver ambiguousColumnResolver0, List list0, List list1, int v, Function1 function10, int v1, Object object0) {
        if((v1 & 2) != 0) {
            list1 = new ArrayList();
        }
        if((v1 & 4) != 0) {
            v = 0;
        }
        ambiguousColumnResolver0.dfs(list0, list1, v, function10);
    }

    private final void rabinKarpSearch(List list0, String[] arr_s, Function3 function30) {
        int v = 0;
        int v2 = 0;
        for(int v1 = 0; v1 < arr_s.length; ++v1) {
            v2 += arr_s[v1].hashCode();
        }
        int v3 = arr_s.length;
        int v4 = 0;
        for(Object object0: list0.subList(0, v3)) {
            v4 += ((ResultColumn)object0).getName().hashCode();
        }
        while(true) {
            if(v2 == v4) {
                function30.invoke(v, v3, list0.subList(v, v3));
            }
            if(v3 + 1 > list0.size()) {
                break;
            }
            v4 = v4 - ((ResultColumn)list0.get(v)).getName().hashCode() + ((ResultColumn)list0.get(v3)).getName().hashCode();
            ++v;
            ++v3;
        }
    }

    @JvmStatic
    public static final int[][] resolve(String[] arr_s, String[][] arr2_s) {
        Intrinsics.checkNotNullParameter(arr_s, "resultColumns");
        Intrinsics.checkNotNullParameter(arr2_s, "mappings");
        for(int v = 0; v < arr_s.length; ++v) {
            String s = arr_s[v];
            if(s.charAt(0) == 0x60 && s.charAt(s.length() - 1) == 0x60) {
                s = s.substring(1, s.length() - 1);
                Intrinsics.checkNotNullExpressionValue(s, "this as java.lang.String…ing(startIndex, endIndex)");
            }
            Locale locale0 = Locale.US;
            Intrinsics.checkNotNullExpressionValue(locale0, "US");
            String s1 = s.toLowerCase(locale0);
            Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String).toLowerCase(locale)");
            arr_s[v] = s1;
        }
        for(int v1 = 0; v1 < arr2_s.length; ++v1) {
            int v2 = arr2_s[v1].length;
            for(int v3 = 0; v3 < v2; ++v3) {
                String[] arr_s1 = arr2_s[v1];
                String s2 = arr_s1[v3];
                Locale locale1 = Locale.US;
                Intrinsics.checkNotNullExpressionValue(locale1, "US");
                String s3 = s2.toLowerCase(locale1);
                Intrinsics.checkNotNullExpressionValue(s3, "this as java.lang.String).toLowerCase(locale)");
                arr_s1[v3] = s3;
            }
        }
        Set set0 = SetsKt.createSetBuilder();
        for(int v4 = 0; v4 < arr2_s.length; ++v4) {
            CollectionsKt.addAll(set0, arr2_s[v4]);
        }
        Set set1 = SetsKt.build(set0);
        List list0 = CollectionsKt.createListBuilder();
        int v5 = 0;
        for(int v6 = 0; v5 < arr_s.length; ++v6) {
            String s4 = arr_s[v5];
            if(set1.contains(s4)) {
                list0.add(new ResultColumn(s4, v6));
            }
            ++v5;
        }
        List list1 = CollectionsKt.build(list0);
        ArrayList arrayList0 = new ArrayList(arr2_s.length);
        for(int v7 = 0; v7 < arr2_s.length; ++v7) {
            arrayList0.add(new ArrayList());
        }
        int v8 = 0;
        for(int v9 = 0; v8 < arr2_s.length; ++v9) {
            String[] arr_s2 = arr2_s[v8];
            Function3 function30 = new Function3(arr_s2, arrayList0, v9) {
                final String[] $mapping;
                final int $mappingIndex;
                final List $mappingMatches;

                {
                    this.$mapping = arr_s;
                    this.$mappingMatches = list0;
                    this.$mappingIndex = v;
                    super(3);
                }

                @Override  // kotlin.jvm.functions.Function3
                public Object invoke(Object object0, Object object1, Object object2) {
                    this.invoke(((Number)object0).intValue(), ((Number)object1).intValue(), ((List)object2));
                    return Unit.INSTANCE;
                }

                public final void invoke(int v, int v1, List list0) {
                    Intrinsics.checkNotNullParameter(list0, "resultColumnsSublist");
                    String[] arr_s = this.$mapping;
                    ArrayList arrayList0 = new ArrayList(arr_s.length);
                    int v2 = 0;
                    while(v2 < arr_s.length) {
                        String s = arr_s[v2];
                        for(Object object0: list0) {
                            if(!Intrinsics.areEqual(s, ((ResultColumn)object0).component1())) {
                                continue;
                            }
                            goto label_12;
                        }
                        object0 = null;
                    label_12:
                        if(((ResultColumn)object0) != null) {
                            arrayList0.add(((ResultColumn)object0).getIndex());
                            ++v2;
                            continue;
                        }
                        return;
                    }
                    ((List)this.$mappingMatches.get(this.$mappingIndex)).add(new Match(new IntRange(v, v1 - 1), arrayList0));
                }
            };
            AmbiguousColumnResolver.INSTANCE.rabinKarpSearch(list1, arr_s2, function30);
            if(((List)arrayList0.get(v9)).isEmpty()) {
                Collection collection0 = new ArrayList(arr_s2.length);
                int v10 = arr_s2.length;
                for(int v11 = 0; v11 < v10; ++v11) {
                    String s5 = arr_s2[v11];
                    List list2 = CollectionsKt.createListBuilder();
                    for(Object object0: list1) {
                        ResultColumn ambiguousColumnResolver$ResultColumn0 = (ResultColumn)object0;
                        if(Intrinsics.areEqual(s5, ambiguousColumnResolver$ResultColumn0.getName())) {
                            list2.add(ambiguousColumnResolver$ResultColumn0.getIndex());
                        }
                    }
                    List list3 = CollectionsKt.build(list2);
                    if(list3.isEmpty()) {
                        throw new IllegalStateException(("Column " + s5 + " not found in result").toString());
                    }
                    collection0.add(list3);
                }
                androidx.room.AmbiguousColumnResolver.resolve.1.2 ambiguousColumnResolver$resolve$1$20 = new Function1(arrayList0, v9) {
                    final int $mappingIndex;
                    final List $mappingMatches;

                    {
                        this.$mappingMatches = list0;
                        this.$mappingIndex = v;
                        super(1);
                    }

                    @Override  // kotlin.jvm.functions.Function1
                    public Object invoke(Object object0) {
                        this.invoke(((List)object0));
                        return Unit.INSTANCE;
                    }

                    public final void invoke(List list0) {
                        Intrinsics.checkNotNullParameter(list0, "indices");
                        Iterator iterator0 = list0.iterator();
                        if(!iterator0.hasNext()) {
                            throw new NoSuchElementException();
                        }
                        Object object0 = iterator0.next();
                        int v = ((Number)object0).intValue();
                        while(iterator0.hasNext()) {
                            Object object1 = iterator0.next();
                            int v1 = ((Number)object1).intValue();
                            if(v > v1) {
                                v = v1;
                            }
                        }
                        Iterator iterator1 = list0.iterator();
                        if(!iterator1.hasNext()) {
                            throw new NoSuchElementException();
                        }
                        Object object2 = iterator1.next();
                        int v2 = ((Number)object2).intValue();
                        while(iterator1.hasNext()) {
                            Object object3 = iterator1.next();
                            int v3 = ((Number)object3).intValue();
                            if(v2 < v3) {
                                v2 = v3;
                            }
                        }
                        ((List)this.$mappingMatches.get(this.$mappingIndex)).add(new Match(new IntRange(v, v2), list0));
                    }
                };
                AmbiguousColumnResolver.dfs$default(AmbiguousColumnResolver.INSTANCE, ((List)collection0), null, 0, ambiguousColumnResolver$resolve$1$20, 6, null);
            }
            ++v8;
        }
        if(!(arrayList0 instanceof Collection) || !arrayList0.isEmpty()) {
            for(Object object1: arrayList0) {
                if(((List)object1).isEmpty()) {
                    throw new IllegalStateException("Failed to find matches for all mappings");
                }
                if(false) {
                    break;
                }
            }
        }
        ObjectRef ref$ObjectRef0 = new ObjectRef();
        ref$ObjectRef0.element = Solution.Companion.getNO_SOLUTION();
        androidx.room.AmbiguousColumnResolver.resolve.4 ambiguousColumnResolver$resolve$40 = new Function1(ref$ObjectRef0) {
            final ObjectRef $bestSolution;

            {
                this.$bestSolution = ref$ObjectRef0;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((List)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(List list0) {
                Intrinsics.checkNotNullParameter(list0, "it");
                Solution ambiguousColumnResolver$Solution0 = Solution.Companion.build(list0);
                if(ambiguousColumnResolver$Solution0.compareTo(((Solution)this.$bestSolution.element)) < 0) {
                    this.$bestSolution.element = ambiguousColumnResolver$Solution0;
                }
            }
        };
        AmbiguousColumnResolver.dfs$default(AmbiguousColumnResolver.INSTANCE, arrayList0, null, 0, ambiguousColumnResolver$resolve$40, 6, null);
        Iterable iterable0 = ((Solution)ref$ObjectRef0.element).getMatches();
        ArrayList arrayList1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable0, 10));
        for(Object object2: iterable0) {
            arrayList1.add(CollectionsKt.toIntArray(((Match)object2).getResultIndices()));
        }
        return (int[][])arrayList1.toArray(new int[0][]);
    }
}

