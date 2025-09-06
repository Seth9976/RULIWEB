package androidx.window.embedding;

import android.app.Activity;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001D\u0012\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\b\u0002\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0011\u0010\n\u001A\u00020\u00062\u0006\u0010\u000B\u001A\u00020\u0004H\u0086\u0002J\u0013\u0010\f\u001A\u00020\u00062\b\u0010\r\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000E\u001A\u00020\u000FH\u0016J\u0006\u0010\u0005\u001A\u00020\u0006J\b\u0010\u0010\u001A\u00020\u0011H\u0016R\u001A\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/window/embedding/ActivityStack;", "", "activities", "", "Landroid/app/Activity;", "isEmpty", "", "(Ljava/util/List;Z)V", "getActivities$window_release", "()Ljava/util/List;", "contains", "activity", "equals", "other", "hashCode", "", "toString", "", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class ActivityStack {
    private final List activities;
    private final boolean isEmpty;

    public ActivityStack(List list0, boolean z) {
        Intrinsics.checkNotNullParameter(list0, "activities");
        super();
        this.activities = list0;
        this.isEmpty = z;
    }

    public ActivityStack(List list0, boolean z, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            z = false;
        }
        this(list0, z);
    }

    public final boolean contains(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
        return this.activities.contains(activity0);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof ActivityStack ? !Intrinsics.areEqual(this.activities, ((ActivityStack)object0).activities) && this.isEmpty != ((ActivityStack)object0).isEmpty : false;
    }

    public final List getActivities$window_release() {
        return this.activities;
    }

    @Override
    public int hashCode() {
        return this.isEmpty * 0x1F + this.activities.hashCode();
    }

    public final boolean isEmpty() {
        return this.isEmpty;
    }

    @Override
    public String toString() {
        String s = "ActivityStack{" + ("activities=" + this.getActivities$window_release()) + ("isEmpty=" + this.isEmpty + '}');
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }
}

