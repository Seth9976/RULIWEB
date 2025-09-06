package androidx.work.impl.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000B\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u001D\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\n\u001A\u00020\u0003HÆ\u0003J\t\u0010\u000B\u001A\u00020\u0005HÆ\u0003J\t\u0010\f\u001A\u00020\u0005HÆ\u0003J\'\u0010\r\u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u00052\b\b\u0002\u0010\u0006\u001A\u00020\u0005HÆ\u0001J\u0013\u0010\u000E\u001A\u00020\u000F2\b\u0010\u0010\u001A\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001A\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001A\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001A\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0010\u0010\u0006\u001A\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/work/impl/model/SystemIdInfo;", "", "workSpecId", "", "generation", "", "systemId", "(Ljava/lang/String;II)V", "getGeneration", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class SystemIdInfo {
    private final int generation;
    public final int systemId;
    public final String workSpecId;

    public SystemIdInfo(String s, int v, int v1) {
        Intrinsics.checkNotNullParameter(s, "workSpecId");
        super();
        this.workSpecId = s;
        this.generation = v;
        this.systemId = v1;
    }

    public final String component1() {
        return this.workSpecId;
    }

    public final int component2() {
        return this.generation;
    }

    public final int component3() {
        return this.systemId;
    }

    public final SystemIdInfo copy(String s, int v, int v1) {
        Intrinsics.checkNotNullParameter(s, "workSpecId");
        return new SystemIdInfo(s, v, v1);
    }

    public static SystemIdInfo copy$default(SystemIdInfo systemIdInfo0, String s, int v, int v1, int v2, Object object0) {
        if((v2 & 1) != 0) {
            s = systemIdInfo0.workSpecId;
        }
        if((v2 & 2) != 0) {
            v = systemIdInfo0.generation;
        }
        if((v2 & 4) != 0) {
            v1 = systemIdInfo0.systemId;
        }
        return systemIdInfo0.copy(s, v, v1);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof SystemIdInfo)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.workSpecId, ((SystemIdInfo)object0).workSpecId)) {
            return false;
        }
        return this.generation == ((SystemIdInfo)object0).generation ? this.systemId == ((SystemIdInfo)object0).systemId : false;
    }

    public final int getGeneration() {
        return this.generation;
    }

    @Override
    public int hashCode() {
        return (this.workSpecId.hashCode() * 0x1F + this.generation) * 0x1F + this.systemId;
    }

    @Override
    public String toString() {
        return "SystemIdInfo(workSpecId=" + this.workSpecId + ", generation=" + this.generation + ", systemId=" + this.systemId + ')';
    }
}

