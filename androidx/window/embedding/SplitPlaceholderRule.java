package androidx.window.embedding;

import android.content.Intent;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001BC\u0012\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001A\u00020\b\u0012\b\b\u0002\u0010\t\u001A\u00020\b\u0012\b\b\u0002\u0010\n\u001A\u00020\u000B\u0012\b\b\u0002\u0010\f\u001A\u00020\b¢\u0006\u0002\u0010\rJ\u0013\u0010\u0012\u001A\u00020\u00132\b\u0010\u0014\u001A\u0004\u0018\u00010\u0015H\u0096\u0002J\b\u0010\u0016\u001A\u00020\bH\u0016J\u0016\u0010\u0017\u001A\u00020\u00002\u0006\u0010\u0018\u001A\u00020\u0004H\u0080\u0002¢\u0006\u0002\b\u0019R\u0017\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\u000FR\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u0010\u0010\u0011¨\u0006\u001A"}, d2 = {"Landroidx/window/embedding/SplitPlaceholderRule;", "Landroidx/window/embedding/SplitRule;", "filters", "", "Landroidx/window/embedding/ActivityFilter;", "placeholderIntent", "Landroid/content/Intent;", "minWidth", "", "minSmallestWidth", "splitRatio", "", "layoutDirection", "(Ljava/util/Set;Landroid/content/Intent;IIFI)V", "getFilters", "()Ljava/util/Set;", "getPlaceholderIntent", "()Landroid/content/Intent;", "equals", "", "other", "", "hashCode", "plus", "filter", "plus$window_release", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class SplitPlaceholderRule extends SplitRule {
    private final Set filters;
    private final Intent placeholderIntent;

    public SplitPlaceholderRule(Set set0, Intent intent0, int v, int v1, float f, int v2) {
        Intrinsics.checkNotNullParameter(set0, "filters");
        Intrinsics.checkNotNullParameter(intent0, "placeholderIntent");
        super(v, v1, f, v2);
        this.placeholderIntent = intent0;
        this.filters = CollectionsKt.toSet(set0);
    }

    public SplitPlaceholderRule(Set set0, Intent intent0, int v, int v1, float f, int v2, int v3, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v3 & 4) != 0) {
            v = 0;
        }
        if((v3 & 8) != 0) {
            v1 = 0;
        }
        if((v3 & 16) != 0) {
            f = 0.5f;
        }
        this(set0, intent0, v, v1, f, ((v3 & 0x20) == 0 ? v2 : 3));
    }

    @Override  // androidx.window.embedding.SplitRule
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof SplitPlaceholderRule)) {
            return false;
        }
        if(!super.equals(object0)) {
            return false;
        }
        if(!super.equals(object0)) {
            return false;
        }
        return Intrinsics.areEqual(this.filters, ((SplitPlaceholderRule)object0).filters) ? Intrinsics.areEqual(this.placeholderIntent, ((SplitPlaceholderRule)object0).placeholderIntent) : false;
    }

    public final Set getFilters() {
        return this.filters;
    }

    public final Intent getPlaceholderIntent() {
        return this.placeholderIntent;
    }

    @Override  // androidx.window.embedding.SplitRule
    public int hashCode() {
        return (super.hashCode() * 0x1F + this.filters.hashCode()) * 0x1F + this.placeholderIntent.hashCode();
    }

    public final SplitPlaceholderRule plus$window_release(ActivityFilter activityFilter0) {
        Intrinsics.checkNotNullParameter(activityFilter0, "filter");
        Set set0 = new LinkedHashSet();
        set0.addAll(this.filters);
        set0.add(activityFilter0);
        Set set1 = CollectionsKt.toSet(set0);
        int v = this.getMinWidth();
        int v1 = this.getMinSmallestWidth();
        float f = this.getSplitRatio();
        int v2 = this.getLayoutDirection();
        return new SplitPlaceholderRule(set1, this.placeholderIntent, v, v1, f, v2);
    }
}

