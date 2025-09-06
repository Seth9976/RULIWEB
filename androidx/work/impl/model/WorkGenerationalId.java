package androidx.work.impl.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000B\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000B\u001A\u00020\u0003HÆ\u0003J\t\u0010\f\u001A\u00020\u0005HÆ\u0003J\u001D\u0010\r\u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u0005HÆ\u0001J\u0013\u0010\u000E\u001A\u00020\u000F2\b\u0010\u0010\u001A\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001A\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001A\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Landroidx/work/impl/model/WorkGenerationalId;", "", "workSpecId", "", "generation", "", "(Ljava/lang/String;I)V", "getGeneration", "()I", "getWorkSpecId", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class WorkGenerationalId {
    private final int generation;
    private final String workSpecId;

    public WorkGenerationalId(String s, int v) {
        Intrinsics.checkNotNullParameter(s, "workSpecId");
        super();
        this.workSpecId = s;
        this.generation = v;
    }

    public final String component1() {
        return this.workSpecId;
    }

    public final int component2() {
        return this.generation;
    }

    public final WorkGenerationalId copy(String s, int v) {
        Intrinsics.checkNotNullParameter(s, "workSpecId");
        return new WorkGenerationalId(s, v);
    }

    public static WorkGenerationalId copy$default(WorkGenerationalId workGenerationalId0, String s, int v, int v1, Object object0) {
        if((v1 & 1) != 0) {
            s = workGenerationalId0.workSpecId;
        }
        if((v1 & 2) != 0) {
            v = workGenerationalId0.generation;
        }
        return workGenerationalId0.copy(s, v);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof WorkGenerationalId)) {
            return false;
        }
        return Intrinsics.areEqual(this.workSpecId, ((WorkGenerationalId)object0).workSpecId) ? this.generation == ((WorkGenerationalId)object0).generation : false;
    }

    public final int getGeneration() {
        return this.generation;
    }

    public final String getWorkSpecId() {
        return this.workSpecId;
    }

    @Override
    public int hashCode() {
        return this.workSpecId.hashCode() * 0x1F + this.generation;
    }

    @Override
    public String toString() {
        return "WorkGenerationalId(workSpecId=" + this.workSpecId + ", generation=" + this.generation + ')';
    }
}

