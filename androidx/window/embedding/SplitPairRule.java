package androidx.window.embedding;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.UByte..ExternalSyntheticBackport0;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001BY\u0012\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001A\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001A\u00020\u0006\u0012\b\b\u0002\u0010\b\u001A\u00020\u0006\u0012\b\b\u0002\u0010\t\u001A\u00020\n\u0012\b\b\u0002\u0010\u000B\u001A\u00020\n\u0012\b\b\u0002\u0010\f\u001A\u00020\r\u0012\b\b\u0002\u0010\u000E\u001A\u00020\n¢\u0006\u0002\u0010\u000FJ\u0013\u0010\u0016\u001A\u00020\u00062\b\u0010\u0017\u001A\u0004\u0018\u00010\u0018H\u0096\u0002J\b\u0010\u0019\u001A\u00020\nH\u0016J\u0016\u0010\u001A\u001A\u00020\u00002\u0006\u0010\u001B\u001A\u00020\u0004H\u0080\u0002¢\u0006\u0002\b\u001CR\u0011\u0010\b\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0007\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u0015\u0010\u0011¨\u0006\u001D"}, d2 = {"Landroidx/window/embedding/SplitPairRule;", "Landroidx/window/embedding/SplitRule;", "filters", "", "Landroidx/window/embedding/SplitPairFilter;", "finishPrimaryWithSecondary", "", "finishSecondaryWithPrimary", "clearTop", "minWidth", "", "minSmallestWidth", "splitRatio", "", "layoutDir", "(Ljava/util/Set;ZZZIIFI)V", "getClearTop", "()Z", "getFilters", "()Ljava/util/Set;", "getFinishPrimaryWithSecondary", "getFinishSecondaryWithPrimary", "equals", "other", "", "hashCode", "plus", "filter", "plus$window_release", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class SplitPairRule extends SplitRule {
    private final boolean clearTop;
    private final Set filters;
    private final boolean finishPrimaryWithSecondary;
    private final boolean finishSecondaryWithPrimary;

    public SplitPairRule(Set set0, boolean z, boolean z1, boolean z2, int v, int v1, float f, int v2) {
        Intrinsics.checkNotNullParameter(set0, "filters");
        super(v, v1, f, v2);
        this.finishPrimaryWithSecondary = z;
        this.finishSecondaryWithPrimary = z1;
        this.clearTop = z2;
        this.filters = CollectionsKt.toSet(set0);
    }

    public SplitPairRule(Set set0, boolean z, boolean z1, boolean z2, int v, int v1, float f, int v2, int v3, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v3 & 2) != 0) {
            z = false;
        }
        if((v3 & 4) != 0) {
            z1 = true;
        }
        if((v3 & 8) != 0) {
            z2 = false;
        }
        if((v3 & 16) != 0) {
            v = 0;
        }
        if((v3 & 0x20) != 0) {
            v1 = 0;
        }
        if((v3 & 0x40) != 0) {
            f = 0.5f;
        }
        this(set0, z, z1, z2, v, v1, f, ((v3 & 0x80) == 0 ? v2 : 3));
    }

    @Override  // androidx.window.embedding.SplitRule
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof SplitPairRule)) {
            return false;
        }
        if(!super.equals(object0)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.filters, ((SplitPairRule)object0).filters)) {
            return false;
        }
        if(this.finishPrimaryWithSecondary != ((SplitPairRule)object0).finishPrimaryWithSecondary) {
            return false;
        }
        return this.finishSecondaryWithPrimary == ((SplitPairRule)object0).finishSecondaryWithPrimary ? this.clearTop == ((SplitPairRule)object0).clearTop : false;
    }

    public final boolean getClearTop() {
        return this.clearTop;
    }

    public final Set getFilters() {
        return this.filters;
    }

    public final boolean getFinishPrimaryWithSecondary() {
        return this.finishPrimaryWithSecondary;
    }

    public final boolean getFinishSecondaryWithPrimary() {
        return this.finishSecondaryWithPrimary;
    }

    @Override  // androidx.window.embedding.SplitRule
    public int hashCode() {
        return (((super.hashCode() * 0x1F + this.filters.hashCode()) * 0x1F + UByte..ExternalSyntheticBackport0.m(this.finishPrimaryWithSecondary)) * 0x1F + UByte..ExternalSyntheticBackport0.m(this.finishSecondaryWithPrimary)) * 0x1F + UByte..ExternalSyntheticBackport0.m(this.clearTop);
    }

    public final SplitPairRule plus$window_release(SplitPairFilter splitPairFilter0) {
        Intrinsics.checkNotNullParameter(splitPairFilter0, "filter");
        Set set0 = new LinkedHashSet();
        set0.addAll(this.filters);
        set0.add(splitPairFilter0);
        Set set1 = CollectionsKt.toSet(set0);
        int v = this.getMinWidth();
        int v1 = this.getMinSmallestWidth();
        float f = this.getSplitRatio();
        int v2 = this.getLayoutDirection();
        return new SplitPairRule(set1, this.finishPrimaryWithSecondary, this.finishSecondaryWithPrimary, this.clearTop, v, v1, f, v2);
    }
}

