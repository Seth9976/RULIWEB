package androidx.work;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u00112\u00020\u0001:\u0002\u0010\u0011BG\b\u0000\u0012\u000E\b\u0002\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000E\b\u0002\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u000E\b\u0002\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u000E\b\u0002\u0010\b\u001A\b\u0012\u0004\u0012\u00020\t0\u0003¢\u0006\u0002\u0010\nR\u0017\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u0017\u0010\b\u001A\b\u0012\u0004\u0012\u00020\t0\u0003¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\fR\u0017\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\fR\u0017\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u000F\u0010\f¨\u0006\u0012"}, d2 = {"Landroidx/work/WorkQuery;", "", "ids", "", "Ljava/util/UUID;", "uniqueWorkNames", "", "tags", "states", "Landroidx/work/WorkInfo$State;", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V", "getIds", "()Ljava/util/List;", "getStates", "getTags", "getUniqueWorkNames", "Builder", "Companion", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class WorkQuery {
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0014\u0010\u000B\u001A\u00020\u00002\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\fJ\u0014\u0010\r\u001A\u00020\u00002\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\u00070\fJ\u0014\u0010\u000E\u001A\u00020\u00002\f\u0010\b\u001A\b\u0012\u0004\u0012\u00020\t0\fJ\u0014\u0010\u000F\u001A\u00020\u00002\f\u0010\n\u001A\b\u0012\u0004\u0012\u00020\t0\fJ\u0006\u0010\u0010\u001A\u00020\u0011R\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001A\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001A\b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/work/WorkQuery$Builder;", "", "()V", "ids", "", "Ljava/util/UUID;", "states", "Landroidx/work/WorkInfo$State;", "tags", "", "uniqueWorkNames", "addIds", "", "addStates", "addTags", "addUniqueWorkNames", "build", "Landroidx/work/WorkQuery;", "Companion", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Builder {
        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001A\u00020\u00042\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0007J\u0016\u0010\b\u001A\u00020\u00042\f\u0010\t\u001A\b\u0012\u0004\u0012\u00020\n0\u0006H\u0007J\u0016\u0010\u000B\u001A\u00020\u00042\f\u0010\f\u001A\b\u0012\u0004\u0012\u00020\r0\u0006H\u0007J\u0016\u0010\u000E\u001A\u00020\u00042\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u00020\r0\u0006H\u0007¨\u0006\u0010"}, d2 = {"Landroidx/work/WorkQuery$Builder$Companion;", "", "()V", "fromIds", "Landroidx/work/WorkQuery$Builder;", "ids", "", "Ljava/util/UUID;", "fromStates", "states", "Landroidx/work/WorkInfo$State;", "fromTags", "tags", "", "fromUniqueWorkNames", "uniqueWorkNames", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class Companion {
            private Companion() {
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }

            @JvmStatic
            public final Builder fromIds(List list0) {
                Intrinsics.checkNotNullParameter(list0, "ids");
                Builder workQuery$Builder0 = new Builder(null);
                workQuery$Builder0.addIds(list0);
                return workQuery$Builder0;
            }

            @JvmStatic
            public final Builder fromStates(List list0) {
                Intrinsics.checkNotNullParameter(list0, "states");
                Builder workQuery$Builder0 = new Builder(null);
                workQuery$Builder0.addStates(list0);
                return workQuery$Builder0;
            }

            @JvmStatic
            public final Builder fromTags(List list0) {
                Intrinsics.checkNotNullParameter(list0, "tags");
                Builder workQuery$Builder0 = new Builder(null);
                workQuery$Builder0.addTags(list0);
                return workQuery$Builder0;
            }

            @JvmStatic
            public final Builder fromUniqueWorkNames(List list0) {
                Intrinsics.checkNotNullParameter(list0, "uniqueWorkNames");
                Builder workQuery$Builder0 = new Builder(null);
                workQuery$Builder0.addUniqueWorkNames(list0);
                return workQuery$Builder0;
            }
        }

        public static final Companion Companion;
        private final List ids;
        private final List states;
        private final List tags;
        private final List uniqueWorkNames;

        static {
            Builder.Companion = new Companion(null);
        }

        private Builder() {
            this.ids = new ArrayList();
            this.uniqueWorkNames = new ArrayList();
            this.tags = new ArrayList();
            this.states = new ArrayList();
        }

        public Builder(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Builder addIds(List list0) {
            Intrinsics.checkNotNullParameter(list0, "ids");
            CollectionsKt.addAll(this.ids, list0);
            return this;
        }

        public final Builder addStates(List list0) {
            Intrinsics.checkNotNullParameter(list0, "states");
            CollectionsKt.addAll(this.states, list0);
            return this;
        }

        public final Builder addTags(List list0) {
            Intrinsics.checkNotNullParameter(list0, "tags");
            CollectionsKt.addAll(this.tags, list0);
            return this;
        }

        public final Builder addUniqueWorkNames(List list0) {
            Intrinsics.checkNotNullParameter(list0, "uniqueWorkNames");
            CollectionsKt.addAll(this.uniqueWorkNames, list0);
            return this;
        }

        public final WorkQuery build() {
            if(this.ids.isEmpty() && this.uniqueWorkNames.isEmpty() && this.tags.isEmpty() && this.states.isEmpty()) {
                throw new IllegalArgumentException("Must specify ids, uniqueNames, tags or states when building a WorkQuery");
            }
            return new WorkQuery(this.ids, this.uniqueWorkNames, this.tags, this.states);
        }

        @JvmStatic
        public static final Builder fromIds(List list0) {
            return Builder.Companion.fromIds(list0);
        }

        @JvmStatic
        public static final Builder fromStates(List list0) {
            return Builder.Companion.fromStates(list0);
        }

        @JvmStatic
        public static final Builder fromTags(List list0) {
            return Builder.Companion.fromTags(list0);
        }

        @JvmStatic
        public static final Builder fromUniqueWorkNames(List list0) {
            return Builder.Companion.fromUniqueWorkNames(list0);
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\u0003\u001A\u00020\u00042\u0012\u0010\u0005\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006\"\u00020\u0007H\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\u0003\u001A\u00020\u00042\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\tH\u0007J!\u0010\n\u001A\u00020\u00042\u0012\u0010\u000B\u001A\n\u0012\u0006\b\u0001\u0012\u00020\f0\u0006\"\u00020\fH\u0007¢\u0006\u0002\u0010\rJ\u0016\u0010\n\u001A\u00020\u00042\f\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\f0\tH\u0007J!\u0010\u000E\u001A\u00020\u00042\u0012\u0010\u000F\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u0006\"\u00020\u0010H\u0007¢\u0006\u0002\u0010\u0011J\u0016\u0010\u000E\u001A\u00020\u00042\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u00020\u00100\tH\u0007J!\u0010\u0012\u001A\u00020\u00042\u0012\u0010\u0013\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00100\u0006\"\u00020\u0010H\u0007¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001A\u00020\u00042\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\u00100\tH\u0007¨\u0006\u0014"}, d2 = {"Landroidx/work/WorkQuery$Companion;", "", "()V", "fromIds", "Landroidx/work/WorkQuery;", "ids", "", "Ljava/util/UUID;", "([Ljava/util/UUID;)Landroidx/work/WorkQuery;", "", "fromStates", "states", "Landroidx/work/WorkInfo$State;", "([Landroidx/work/WorkInfo$State;)Landroidx/work/WorkQuery;", "fromTags", "tags", "", "([Ljava/lang/String;)Landroidx/work/WorkQuery;", "fromUniqueWorkNames", "uniqueWorkNames", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class androidx.work.WorkQuery.Companion {
        private androidx.work.WorkQuery.Companion() {
        }

        public androidx.work.WorkQuery.Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final WorkQuery fromIds(List list0) {
            Intrinsics.checkNotNullParameter(list0, "ids");
            return new WorkQuery(list0, null, null, null, 14, null);
        }

        @JvmStatic
        public final WorkQuery fromIds(UUID[] arr_uUID) {
            Intrinsics.checkNotNullParameter(arr_uUID, "ids");
            return new WorkQuery(ArraysKt.toList(arr_uUID), null, null, null, 14, null);
        }

        @JvmStatic
        public final WorkQuery fromStates(List list0) {
            Intrinsics.checkNotNullParameter(list0, "states");
            return new WorkQuery(null, null, null, list0, 7, null);
        }

        @JvmStatic
        public final WorkQuery fromStates(State[] arr_workInfo$State) {
            Intrinsics.checkNotNullParameter(arr_workInfo$State, "states");
            return new WorkQuery(null, null, null, ArraysKt.toList(arr_workInfo$State), 7, null);
        }

        @JvmStatic
        public final WorkQuery fromTags(List list0) {
            Intrinsics.checkNotNullParameter(list0, "tags");
            return new WorkQuery(null, null, list0, null, 11, null);
        }

        @JvmStatic
        public final WorkQuery fromTags(String[] arr_s) {
            Intrinsics.checkNotNullParameter(arr_s, "tags");
            return new WorkQuery(null, null, ArraysKt.toList(arr_s), null, 11, null);
        }

        @JvmStatic
        public final WorkQuery fromUniqueWorkNames(List list0) {
            Intrinsics.checkNotNullParameter(list0, "uniqueWorkNames");
            return new WorkQuery(null, list0, null, null, 13, null);
        }

        @JvmStatic
        public final WorkQuery fromUniqueWorkNames(String[] arr_s) {
            Intrinsics.checkNotNullParameter(arr_s, "uniqueWorkNames");
            return new WorkQuery(null, ArraysKt.toList(arr_s), null, null, 13, null);
        }
    }

    public static final androidx.work.WorkQuery.Companion Companion;
    private final List ids;
    private final List states;
    private final List tags;
    private final List uniqueWorkNames;

    static {
        WorkQuery.Companion = new androidx.work.WorkQuery.Companion(null);
    }

    public WorkQuery() {
        this(null, null, null, null, 15, null);
    }

    public WorkQuery(List list0, List list1, List list2, List list3) {
        Intrinsics.checkNotNullParameter(list0, "ids");
        Intrinsics.checkNotNullParameter(list1, "uniqueWorkNames");
        Intrinsics.checkNotNullParameter(list2, "tags");
        Intrinsics.checkNotNullParameter(list3, "states");
        super();
        this.ids = list0;
        this.uniqueWorkNames = list1;
        this.tags = list2;
        this.states = list3;
    }

    public WorkQuery(List list0, List list1, List list2, List list3, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            list0 = CollectionsKt.emptyList();
        }
        if((v & 2) != 0) {
            list1 = CollectionsKt.emptyList();
        }
        if((v & 4) != 0) {
            list2 = CollectionsKt.emptyList();
        }
        if((v & 8) != 0) {
            list3 = CollectionsKt.emptyList();
        }
        this(list0, list1, list2, list3);
    }

    @JvmStatic
    public static final WorkQuery fromIds(List list0) {
        return WorkQuery.Companion.fromIds(list0);
    }

    @JvmStatic
    public static final WorkQuery fromIds(UUID[] arr_uUID) {
        return WorkQuery.Companion.fromIds(arr_uUID);
    }

    @JvmStatic
    public static final WorkQuery fromStates(List list0) {
        return WorkQuery.Companion.fromStates(list0);
    }

    @JvmStatic
    public static final WorkQuery fromStates(State[] arr_workInfo$State) {
        return WorkQuery.Companion.fromStates(arr_workInfo$State);
    }

    @JvmStatic
    public static final WorkQuery fromTags(List list0) {
        return WorkQuery.Companion.fromTags(list0);
    }

    @JvmStatic
    public static final WorkQuery fromTags(String[] arr_s) {
        return WorkQuery.Companion.fromTags(arr_s);
    }

    @JvmStatic
    public static final WorkQuery fromUniqueWorkNames(List list0) {
        return WorkQuery.Companion.fromUniqueWorkNames(list0);
    }

    @JvmStatic
    public static final WorkQuery fromUniqueWorkNames(String[] arr_s) {
        return WorkQuery.Companion.fromUniqueWorkNames(arr_s);
    }

    public final List getIds() {
        return this.ids;
    }

    public final List getStates() {
        return this.states;
    }

    public final List getTags() {
        return this.tags;
    }

    public final List getUniqueWorkNames() {
        return this.uniqueWorkNames;
    }
}

