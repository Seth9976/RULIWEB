package androidx.window.embedding;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000B\u001A\u00020\f2\b\u0010\r\u001A\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\u000E\u001A\u00020\u000FH\u0016J\u000E\u0010\u0010\u001A\u00020\f2\u0006\u0010\u0011\u001A\u00020\u0012J\u000E\u0010\u0013\u001A\u00020\f2\u0006\u0010\u0014\u001A\u00020\u0015J\b\u0010\u0016\u001A\u00020\u0005H\u0016R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001A\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\n¨\u0006\u0017"}, d2 = {"Landroidx/window/embedding/ActivityFilter;", "", "componentName", "Landroid/content/ComponentName;", "intentAction", "", "(Landroid/content/ComponentName;Ljava/lang/String;)V", "getComponentName", "()Landroid/content/ComponentName;", "getIntentAction", "()Ljava/lang/String;", "equals", "", "other", "hashCode", "", "matchesActivity", "activity", "Landroid/app/Activity;", "matchesIntent", "intent", "Landroid/content/Intent;", "toString", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class ActivityFilter {
    private final ComponentName componentName;
    private final String intentAction;

    public ActivityFilter(ComponentName componentName0, String s) {
        Intrinsics.checkNotNullParameter(componentName0, "componentName");
        super();
        this.componentName = componentName0;
        this.intentAction = s;
        String s1 = componentName0.getPackageName();
        Intrinsics.checkNotNullExpressionValue(s1, "componentName.packageName");
        String s2 = componentName0.getClassName();
        Intrinsics.checkNotNullExpressionValue(s2, "componentName.className");
        if(s1.length() <= 0) {
            throw new IllegalArgumentException("Package name must not be empty");
        }
        if(s2.length() <= 0) {
            throw new IllegalArgumentException("Activity class name must not be empty.");
        }
        if(StringsKt.contains$default(s1, "*", false, 2, null) && StringsKt.indexOf$default(s1, "*", 0, false, 6, null) != s1.length() - 1) {
            throw new IllegalArgumentException("Wildcard in package name is only allowed at the end.");
        }
        if(StringsKt.contains$default(s2, "*", false, 2, null) && StringsKt.indexOf$default(s2, "*", 0, false, 6, null) != s2.length() - 1) {
            throw new IllegalArgumentException("Wildcard in class name is only allowed at the end.");
        }
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof ActivityFilter)) {
            return false;
        }
        return Intrinsics.areEqual(this.componentName, ((ActivityFilter)object0).componentName) ? Intrinsics.areEqual(this.intentAction, ((ActivityFilter)object0).intentAction) : false;
    }

    public final ComponentName getComponentName() {
        return this.componentName;
    }

    public final String getIntentAction() {
        return this.intentAction;
    }

    @Override
    public int hashCode() {
        int v = this.componentName.hashCode();
        return this.intentAction == null ? v * 0x1F : v * 0x1F + this.intentAction.hashCode();
    }

    public final boolean matchesActivity(Activity activity0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
        if(MatcherUtils.INSTANCE.areActivityOrIntentComponentsMatching$window_release(activity0, this.componentName)) {
            String s = this.intentAction;
            if(s != null) {
                Intent intent0 = activity0.getIntent();
                return Intrinsics.areEqual(s, (intent0 == null ? null : intent0.getAction()));
            }
            return true;
        }
        return false;
    }

    public final boolean matchesIntent(Intent intent0) {
        Intrinsics.checkNotNullParameter(intent0, "intent");
        ComponentName componentName0 = intent0.getComponent();
        return MatcherUtils.INSTANCE.areComponentsMatching$window_release(componentName0, this.componentName) ? this.intentAction == null || Intrinsics.areEqual(this.intentAction, intent0.getAction()) : false;
    }

    @Override
    public String toString() {
        return "ActivityFilter(componentName=" + this.componentName + ", intentAction=" + this.intentAction + ')';
    }
}

