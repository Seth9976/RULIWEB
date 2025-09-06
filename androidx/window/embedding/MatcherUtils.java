package androidx.window.embedding;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\bÁ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001D\u0010\u0007\u001A\u00020\u00042\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000BH\u0000¢\u0006\u0002\b\fJ\u001F\u0010\r\u001A\u00020\u00042\b\u0010\u000E\u001A\u0004\u0018\u00010\u000B2\u0006\u0010\n\u001A\u00020\u000BH\u0000¢\u0006\u0002\b\u000FJ\u0018\u0010\u0010\u001A\u00020\u00042\u0006\u0010\u0011\u001A\u00020\u00062\u0006\u0010\u0012\u001A\u00020\u0006H\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/window/embedding/MatcherUtils;", "", "()V", "sDebugMatchers", "", "sMatchersTag", "", "areActivityOrIntentComponentsMatching", "activity", "Landroid/app/Activity;", "ruleComponent", "Landroid/content/ComponentName;", "areActivityOrIntentComponentsMatching$window_release", "areComponentsMatching", "activityComponent", "areComponentsMatching$window_release", "wildcardMatch", "name", "pattern", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class MatcherUtils {
    public static final MatcherUtils INSTANCE = null;
    public static final boolean sDebugMatchers = false;
    public static final String sMatchersTag = "SplitRuleResolution";

    static {
        MatcherUtils.INSTANCE = new MatcherUtils();
    }

    public final boolean areActivityOrIntentComponentsMatching$window_release(Activity activity0, ComponentName componentName0) {
        Intrinsics.checkNotNullParameter(activity0, "activity");
        Intrinsics.checkNotNullParameter(componentName0, "ruleComponent");
        if(this.areComponentsMatching$window_release(activity0.getComponentName(), componentName0)) {
            return true;
        }
        Intent intent0 = activity0.getIntent();
        if(intent0 == null) {
            return false;
        }
        ComponentName componentName1 = intent0.getComponent();
        return componentName1 == null ? false : MatcherUtils.INSTANCE.areComponentsMatching$window_release(componentName1, componentName0);
    }

    public final boolean areComponentsMatching$window_release(ComponentName componentName0, ComponentName componentName1) {
        boolean z;
        Intrinsics.checkNotNullParameter(componentName1, "ruleComponent");
        if(componentName0 == null) {
            return Intrinsics.areEqual(componentName1.getPackageName(), "*") && Intrinsics.areEqual(componentName1.getClassName(), "*");
        }
        String s = componentName0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "activityComponent.toString()");
        if(StringsKt.contains$default(s, "*", false, 2, null)) {
            throw new IllegalArgumentException("Wildcard can only be part of the rule.");
        }
        if(Intrinsics.areEqual(componentName0.getPackageName(), componentName1.getPackageName())) {
            z = true;
        }
        else {
            String s1 = componentName0.getPackageName();
            Intrinsics.checkNotNullExpressionValue(s1, "activityComponent.packageName");
            String s2 = componentName1.getPackageName();
            Intrinsics.checkNotNullExpressionValue(s2, "ruleComponent.packageName");
            z = this.wildcardMatch(s1, s2);
        }
        if(!Intrinsics.areEqual(componentName0.getClassName(), componentName1.getClassName())) {
            String s3 = componentName0.getClassName();
            Intrinsics.checkNotNullExpressionValue(s3, "activityComponent.className");
            String s4 = componentName1.getClassName();
            Intrinsics.checkNotNullExpressionValue(s4, "ruleComponent.className");
            return this.wildcardMatch(s3, s4) ? z : false;
        }
        return z;
    }

    private final boolean wildcardMatch(String s, String s1) {
        if(!StringsKt.contains$default(s1, "*", false, 2, null)) {
            return false;
        }
        if(Intrinsics.areEqual(s1, "*")) {
            return true;
        }
        if(StringsKt.indexOf$default(s1, "*", 0, false, 6, null) != StringsKt.lastIndexOf$default(s1, "*", 0, false, 6, null) || !StringsKt.endsWith$default(s1, "*", false, 2, null)) {
            throw new IllegalArgumentException("Name pattern with a wildcard must only contain a single wildcard in the end");
        }
        String s2 = s1.substring(0, s1.length() - 1);
        Intrinsics.checkNotNullExpressionValue(s2, "this as java.lang.String…ing(startIndex, endIndex)");
        return StringsKt.startsWith$default(s, s2, false, 2, null);
    }
}

