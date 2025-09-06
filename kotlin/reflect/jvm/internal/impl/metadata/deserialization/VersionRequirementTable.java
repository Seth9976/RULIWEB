package kotlin.reflect.jvm.internal.impl.metadata.deserialization;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public final class VersionRequirementTable {
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final VersionRequirementTable create(kotlin.reflect.jvm.internal.impl.metadata.ProtoBuf.VersionRequirementTable protoBuf$VersionRequirementTable0) {
            Intrinsics.checkNotNullParameter(protoBuf$VersionRequirementTable0, "table");
            if(protoBuf$VersionRequirementTable0.getRequirementCount() == 0) {
                return this.getEMPTY();
            }
            List list0 = protoBuf$VersionRequirementTable0.getRequirementList();
            Intrinsics.checkNotNullExpressionValue(list0, "table.requirementList");
            return new VersionRequirementTable(list0, null);
        }

        public final VersionRequirementTable getEMPTY() {
            return VersionRequirementTable.EMPTY;
        }
    }

    public static final Companion Companion;
    private static final VersionRequirementTable EMPTY;
    private final List infos;

    static {
        VersionRequirementTable.Companion = new Companion(null);
        VersionRequirementTable.EMPTY = new VersionRequirementTable(CollectionsKt.emptyList());
    }

    private VersionRequirementTable(List list0) {
        this.infos = list0;
    }

    public VersionRequirementTable(List list0, DefaultConstructorMarker defaultConstructorMarker0) {
        this(list0);
    }
}

