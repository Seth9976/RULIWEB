package androidx.window.embedding;

import android.app.Activity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001F\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0011\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u0010H\u0086\u0002J\u0013\u0010\u0011\u001A\u00020\u000E2\b\u0010\u0012\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0013\u001A\u00020\u0014H\u0016J\b\u0010\u0015\u001A\u00020\u0016H\u0016R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\f¨\u0006\u0017"}, d2 = {"Landroidx/window/embedding/SplitInfo;", "", "primaryActivityStack", "Landroidx/window/embedding/ActivityStack;", "secondaryActivityStack", "splitRatio", "", "(Landroidx/window/embedding/ActivityStack;Landroidx/window/embedding/ActivityStack;F)V", "getPrimaryActivityStack", "()Landroidx/window/embedding/ActivityStack;", "getSecondaryActivityStack", "getSplitRatio", "()F", "contains", "", "activity", "Landroid/app/Activity;", "equals", "other", "hashCode", "", "toString", "", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class SplitInfo {
    private final ActivityStack primaryActivityStack;
    private final ActivityStack secondaryActivityStack;
    private final float splitRatio;

    public SplitInfo(ActivityStack activityStack0, ActivityStack activityStack1, float f) {
        Intrinsics.checkNotNullParameter(activityStack0, "primaryActivityStack");
        Intrinsics.checkNotNullParameter(activityStack1, "secondaryActivityStack");
        super();
        this.primaryActivityStack = activityStack0;
        this.secondaryActivityStack = activityStack1;
        this.splitRatio = f;
    }

    public final boolean contains(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
        return this.primaryActivityStack.contains(activity0) || this.secondaryActivityStack.contains(activity0);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof SplitInfo)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.primaryActivityStack, ((SplitInfo)object0).primaryActivityStack)) {
            return false;
        }
        return Intrinsics.areEqual(this.secondaryActivityStack, ((SplitInfo)object0).secondaryActivityStack) ? this.splitRatio == ((SplitInfo)object0).splitRatio : false;
    }

    public final ActivityStack getPrimaryActivityStack() {
        return this.primaryActivityStack;
    }

    public final ActivityStack getSecondaryActivityStack() {
        return this.secondaryActivityStack;
    }

    public final float getSplitRatio() {
        return this.splitRatio;
    }

    @Override
    public int hashCode() {
        return (this.primaryActivityStack.hashCode() * 0x1F + this.secondaryActivityStack.hashCode()) * 0x1F + Float.floatToIntBits(this.splitRatio);
    }

    @Override
    public String toString() {
        String s = "SplitInfo:{" + ("primaryActivityStack=" + this.getPrimaryActivityStack() + ',') + ("secondaryActivityStack=" + this.getSecondaryActivityStack() + ',') + ("splitRatio=" + this.getSplitRatio() + '}');
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }
}

