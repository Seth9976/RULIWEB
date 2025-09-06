package androidx.window.embedding;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u001F\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\b\u0010\u0005\u001A\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\r\u001A\u00020\u000E2\b\u0010\u000F\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u0010\u001A\u00020\u0011H\u0016J\u0016\u0010\u0012\u001A\u00020\u000E2\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\u0016J\u0016\u0010\u0017\u001A\u00020\u000E2\u0006\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0018\u001A\u00020\u0014J\b\u0010\u0019\u001A\u00020\u0006H\u0016R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001A\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000BR\u0011\u0010\u0004\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\t¨\u0006\u001A"}, d2 = {"Landroidx/window/embedding/SplitPairFilter;", "", "primaryActivityName", "Landroid/content/ComponentName;", "secondaryActivityName", "secondaryActivityIntentAction", "", "(Landroid/content/ComponentName;Landroid/content/ComponentName;Ljava/lang/String;)V", "getPrimaryActivityName", "()Landroid/content/ComponentName;", "getSecondaryActivityIntentAction", "()Ljava/lang/String;", "getSecondaryActivityName", "equals", "", "other", "hashCode", "", "matchesActivityIntentPair", "primaryActivity", "Landroid/app/Activity;", "secondaryActivityIntent", "Landroid/content/Intent;", "matchesActivityPair", "secondaryActivity", "toString", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class SplitPairFilter {
    private final ComponentName primaryActivityName;
    private final String secondaryActivityIntentAction;
    private final ComponentName secondaryActivityName;

    public SplitPairFilter(ComponentName componentName0, ComponentName componentName1, String s) {
        Intrinsics.checkNotNullParameter(componentName0, "primaryActivityName");
        Intrinsics.checkNotNullParameter(componentName1, "secondaryActivityName");
        super();
        this.primaryActivityName = componentName0;
        this.secondaryActivityName = componentName1;
        this.secondaryActivityIntentAction = s;
        String s1 = componentName0.getPackageName();
        Intrinsics.checkNotNullExpressionValue(s1, "primaryActivityName.packageName");
        String s2 = componentName0.getClassName();
        Intrinsics.checkNotNullExpressionValue(s2, "primaryActivityName.className");
        String s3 = componentName1.getPackageName();
        Intrinsics.checkNotNullExpressionValue(s3, "secondaryActivityName.packageName");
        String s4 = componentName1.getClassName();
        Intrinsics.checkNotNullExpressionValue(s4, "secondaryActivityName.className");
        if(s1.length() == 0 || s3.length() == 0) {
            throw new IllegalArgumentException("Package name must not be empty");
        }
        if(s2.length() == 0 || s4.length() == 0) {
            throw new IllegalArgumentException("Activity class name must not be empty.");
        }
        if(StringsKt.contains$default(s1, "*", false, 2, null) && StringsKt.indexOf$default(s1, "*", 0, false, 6, null) != s1.length() - 1) {
            throw new IllegalArgumentException("Wildcard in package name is only allowed at the end.");
        }
        if(StringsKt.contains$default(s2, "*", false, 2, null) && StringsKt.indexOf$default(s2, "*", 0, false, 6, null) != s2.length() - 1) {
            throw new IllegalArgumentException("Wildcard in class name is only allowed at the end.");
        }
        if(StringsKt.contains$default(s3, "*", false, 2, null) && StringsKt.indexOf$default(s3, "*", 0, false, 6, null) != s3.length() - 1) {
            throw new IllegalArgumentException("Wildcard in package name is only allowed at the end.");
        }
        if(StringsKt.contains$default(s4, "*", false, 2, null) && StringsKt.indexOf$default(s4, "*", 0, false, 6, null) != s4.length() - 1) {
            throw new IllegalArgumentException("Wildcard in class name is only allowed at the end.");
        }
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof SplitPairFilter)) {
            return false;
        }
        if(!Intrinsics.areEqual(this.primaryActivityName, ((SplitPairFilter)object0).primaryActivityName)) {
            return false;
        }
        return Intrinsics.areEqual(this.secondaryActivityName, ((SplitPairFilter)object0).secondaryActivityName) ? Intrinsics.areEqual(this.secondaryActivityIntentAction, ((SplitPairFilter)object0).secondaryActivityIntentAction) : false;
    }

    public final ComponentName getPrimaryActivityName() {
        return this.primaryActivityName;
    }

    public final String getSecondaryActivityIntentAction() {
        return this.secondaryActivityIntentAction;
    }

    public final ComponentName getSecondaryActivityName() {
        return this.secondaryActivityName;
    }

    @Override
    public int hashCode() {
        int v = this.primaryActivityName.hashCode();
        int v1 = this.secondaryActivityName.hashCode();
        return this.secondaryActivityIntentAction == null ? (v * 0x1F + v1) * 0x1F : (v * 0x1F + v1) * 0x1F + this.secondaryActivityIntentAction.hashCode();
    }

    public final boolean matchesActivityIntentPair(Activity activity0, Intent intent0) {
        Intrinsics.checkNotNullParameter(activity0, "primaryActivity");
        Intrinsics.checkNotNullParameter(intent0, "secondaryActivityIntent");
        ComponentName componentName0 = activity0.getComponentName();
        if(!MatcherUtils.INSTANCE.areComponentsMatching$window_release(componentName0, this.primaryActivityName)) {
            return false;
        }
        ComponentName componentName1 = intent0.getComponent();
        return MatcherUtils.INSTANCE.areComponentsMatching$window_release(componentName1, this.secondaryActivityName) ? this.secondaryActivityIntentAction == null || Intrinsics.areEqual(this.secondaryActivityIntentAction, intent0.getAction()) : false;
    }

    public final boolean matchesActivityPair(Activity activity0, Activity activity1) {
        boolean z;
        Intrinsics.checkNotNullParameter(activity0, "primaryActivity");
        Intrinsics.checkNotNullParameter(activity1, "secondaryActivity");
        ComponentName componentName0 = activity0.getComponentName();
        if(MatcherUtils.INSTANCE.areComponentsMatching$window_release(componentName0, this.primaryActivityName)) {
            ComponentName componentName1 = activity1.getComponentName();
            z = MatcherUtils.INSTANCE.areComponentsMatching$window_release(componentName1, this.secondaryActivityName);
        }
        else {
            z = false;
        }
        if(activity1.getIntent() != null) {
            if(z) {
                Intent intent0 = activity1.getIntent();
                Intrinsics.checkNotNullExpressionValue(intent0, "secondaryActivity.intent");
                return this.matchesActivityIntentPair(activity0, intent0);
            }
            return false;
        }
        return z;
    }

    @Override
    public String toString() {
        return "SplitPairFilter{primaryActivityName=" + this.primaryActivityName + ", secondaryActivityName=" + this.secondaryActivityName + ", secondaryActivityAction=" + this.secondaryActivityIntentAction + '}';
    }
}

