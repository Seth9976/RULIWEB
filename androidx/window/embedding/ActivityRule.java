package androidx.window.embedding;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.UByte..ExternalSyntheticBackport0;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u001D\u0012\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\f\u001A\u00020\u00062\b\u0010\r\u001A\u0004\u0018\u00010\u000EH\u0096\u0002J\b\u0010\u000F\u001A\u00020\u0010H\u0016J\u0016\u0010\u0011\u001A\u00020\u00002\u0006\u0010\u0012\u001A\u00020\u0004H\u0080\u0002¢\u0006\u0002\b\u0013R\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000B¨\u0006\u0014"}, d2 = {"Landroidx/window/embedding/ActivityRule;", "Landroidx/window/embedding/EmbeddingRule;", "filters", "", "Landroidx/window/embedding/ActivityFilter;", "alwaysExpand", "", "(Ljava/util/Set;Z)V", "getAlwaysExpand", "()Z", "getFilters", "()Ljava/util/Set;", "equals", "other", "", "hashCode", "", "plus", "filter", "plus$window_release", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class ActivityRule extends EmbeddingRule {
    private final boolean alwaysExpand;
    private final Set filters;

    public ActivityRule(Set set0, boolean z) {
        Intrinsics.checkNotNullParameter(set0, "filters");
        super();
        this.alwaysExpand = z;
        this.filters = CollectionsKt.toSet(set0);
    }

    public ActivityRule(Set set0, boolean z, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            z = false;
        }
        this(set0, z);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof ActivityRule)) {
            return false;
        }
        return Intrinsics.areEqual(this.filters, ((ActivityRule)object0).filters) ? this.alwaysExpand == ((ActivityRule)object0).alwaysExpand : false;
    }

    public final boolean getAlwaysExpand() {
        return this.alwaysExpand;
    }

    public final Set getFilters() {
        return this.filters;
    }

    @Override
    public int hashCode() {
        return this.filters.hashCode() * 0x1F + UByte..ExternalSyntheticBackport0.m(this.alwaysExpand);
    }

    public final ActivityRule plus$window_release(ActivityFilter activityFilter0) {
        Intrinsics.checkNotNullParameter(activityFilter0, "filter");
        Set set0 = new LinkedHashSet();
        set0.addAll(this.filters);
        set0.add(activityFilter0);
        return new ActivityRule(CollectionsKt.toSet(set0), this.alwaysExpand);
    }
}

