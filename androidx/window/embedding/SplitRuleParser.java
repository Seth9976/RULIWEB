package androidx.window.embedding;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import androidx.window.R.styleable;
import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001A\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\b\u0010\u0007\u001A\u0004\u0018\u00010\bH\u0002J\u0018\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000EH\u0002J\u0018\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000EH\u0002J\u0018\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000EH\u0002J\u0018\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000EH\u0002J\u0018\u0010\u0015\u001A\u00020\u00162\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u000EH\u0002J%\u0010\u0017\u001A\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00182\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\u001A\u001A\u00020\u001BH\u0000¢\u0006\u0002\b\u001CJ \u0010\u001D\u001A\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00182\u0006\u0010\u000B\u001A\u00020\f2\u0006\u0010\u001E\u001A\u00020\u001BH\u0002¨\u0006\u001F"}, d2 = {"Landroidx/window/embedding/SplitRuleParser;", "", "()V", "buildClassName", "Landroid/content/ComponentName;", "pkg", "", "clsSeq", "", "parseActivityFilter", "Landroidx/window/embedding/ActivityFilter;", "context", "Landroid/content/Context;", "parser", "Landroid/content/res/XmlResourceParser;", "parseSplitActivityRule", "Landroidx/window/embedding/ActivityRule;", "parseSplitPairFilter", "Landroidx/window/embedding/SplitPairFilter;", "parseSplitPairRule", "Landroidx/window/embedding/SplitPairRule;", "parseSplitPlaceholderRule", "Landroidx/window/embedding/SplitPlaceholderRule;", "parseSplitRules", "", "Landroidx/window/embedding/EmbeddingRule;", "staticRuleResourceId", "", "parseSplitRules$window_release", "parseSplitXml", "splitResourceId", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class SplitRuleParser {
    private final ComponentName buildClassName(String s, CharSequence charSequence0) {
        if(charSequence0 == null || charSequence0.length() == 0) {
            throw new IllegalArgumentException("Activity name must not be null");
        }
        String s1 = charSequence0.toString();
        if(s1.charAt(0) == 46) {
            return new ComponentName(s, s + s1);
        }
        int v = StringsKt.indexOf$default(s1, '/', 0, false, 6, null);
        if(v > 0) {
            s = s1.substring(0, v);
            Intrinsics.checkNotNullExpressionValue(s, "this as java.lang.String…ing(startIndex, endIndex)");
            s1 = s1.substring(v + 1);
            Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String).substring(startIndex)");
        }
        return Intrinsics.areEqual(s1, "*") || StringsKt.indexOf$default(s1, '.', 0, false, 6, null) >= 0 ? new ComponentName(s, s1) : new ComponentName(s, s + '.' + s1);
    }

    private final ActivityFilter parseActivityFilter(Context context0, XmlResourceParser xmlResourceParser0) {
        TypedArray typedArray0 = context0.getTheme().obtainStyledAttributes(xmlResourceParser0, styleable.ActivityFilter, 0, 0);
        String s = typedArray0.getString(styleable.ActivityFilter_activityName);
        String s1 = typedArray0.getString(styleable.ActivityFilter_activityAction);
        Intrinsics.checkNotNullExpressionValue("com.ruliweb.www.ruliapp", "packageName");
        return new ActivityFilter(this.buildClassName("com.ruliweb.www.ruliapp", s), s1);
    }

    private final ActivityRule parseSplitActivityRule(Context context0, XmlResourceParser xmlResourceParser0) {
        boolean z = context0.getTheme().obtainStyledAttributes(xmlResourceParser0, styleable.ActivityRule, 0, 0).getBoolean(styleable.ActivityRule_alwaysExpand, false);
        return new ActivityRule(SetsKt.emptySet(), z);
    }

    private final SplitPairFilter parseSplitPairFilter(Context context0, XmlResourceParser xmlResourceParser0) {
        TypedArray typedArray0 = context0.getTheme().obtainStyledAttributes(xmlResourceParser0, styleable.SplitPairFilter, 0, 0);
        String s = typedArray0.getString(styleable.SplitPairFilter_primaryActivityName);
        String s1 = typedArray0.getString(styleable.SplitPairFilter_secondaryActivityName);
        String s2 = typedArray0.getString(styleable.SplitPairFilter_secondaryActivityAction);
        Intrinsics.checkNotNullExpressionValue("com.ruliweb.www.ruliapp", "packageName");
        return new SplitPairFilter(this.buildClassName("com.ruliweb.www.ruliapp", s), this.buildClassName("com.ruliweb.www.ruliapp", s1), s2);
    }

    private final SplitPairRule parseSplitPairRule(Context context0, XmlResourceParser xmlResourceParser0) {
        TypedArray typedArray0 = context0.getTheme().obtainStyledAttributes(xmlResourceParser0, styleable.SplitPairRule, 0, 0);
        float f = typedArray0.getFloat(styleable.SplitPairRule_splitRatio, 0.0f);
        int v = (int)typedArray0.getDimension(styleable.SplitPairRule_splitMinWidth, 0.0f);
        int v1 = (int)typedArray0.getDimension(styleable.SplitPairRule_splitMinSmallestWidth, 0.0f);
        int v2 = typedArray0.getInt(styleable.SplitPairRule_splitLayoutDirection, 3);
        boolean z = typedArray0.getBoolean(styleable.SplitPairRule_finishPrimaryWithSecondary, false);
        boolean z1 = typedArray0.getBoolean(styleable.SplitPairRule_finishSecondaryWithPrimary, true);
        boolean z2 = typedArray0.getBoolean(styleable.SplitPairRule_clearTop, false);
        return new SplitPairRule(SetsKt.emptySet(), z, z1, z2, v, v1, f, v2);
    }

    private final SplitPlaceholderRule parseSplitPlaceholderRule(Context context0, XmlResourceParser xmlResourceParser0) {
        TypedArray typedArray0 = context0.getTheme().obtainStyledAttributes(xmlResourceParser0, styleable.SplitPlaceholderRule, 0, 0);
        String s = typedArray0.getString(styleable.SplitPlaceholderRule_placeholderActivityName);
        float f = typedArray0.getFloat(styleable.SplitPlaceholderRule_splitRatio, 0.0f);
        int v = (int)typedArray0.getDimension(styleable.SplitPlaceholderRule_splitMinWidth, 0.0f);
        int v1 = (int)typedArray0.getDimension(styleable.SplitPlaceholderRule_splitMinSmallestWidth, 0.0f);
        int v2 = typedArray0.getInt(styleable.SplitPlaceholderRule_splitLayoutDirection, 3);
        Intrinsics.checkNotNullExpressionValue("com.ruliweb.www.ruliapp", "packageName");
        ComponentName componentName0 = this.buildClassName("com.ruliweb.www.ruliapp", s);
        Set set0 = SetsKt.emptySet();
        Intent intent0 = new Intent().setComponent(componentName0);
        Intrinsics.checkNotNullExpressionValue(intent0, "Intent().setComponent(pl…eholderActivityClassName)");
        return new SplitPlaceholderRule(set0, intent0, v, v1, f, v2);
    }

    public final Set parseSplitRules$window_release(Context context0, int v) {
        Intrinsics.checkNotNullParameter(context0, "context");
        return this.parseSplitXml(context0, v);
    }

    private final Set parseSplitXml(Context context0, int v) {
        SplitPairRule splitPairRule1;
        SplitPlaceholderRule splitPlaceholderRule1;
        ActivityRule activityRule1;
        XmlResourceParser xmlResourceParser0;
        Resources resources0 = context0.getResources();
        try {
            xmlResourceParser0 = resources0.getXml(v);
            Intrinsics.checkNotNullExpressionValue(xmlResourceParser0, "resources.getXml(splitResourceId)");
        }
        catch(Resources.NotFoundException unused_ex) {
            return null;
        }
        HashSet hashSet0 = new HashSet();
        int v1 = xmlResourceParser0.getDepth();
        int v2 = xmlResourceParser0.next();
        ActivityRule activityRule0 = null;
        SplitPairRule splitPairRule0 = null;
        SplitPlaceholderRule splitPlaceholderRule0 = null;
        while(v2 != 1 && (v2 != 3 || xmlResourceParser0.getDepth() > v1)) {
            if(xmlResourceParser0.getEventType() == 2 && !Intrinsics.areEqual("split-config", xmlResourceParser0.getName())) {
                String s = xmlResourceParser0.getName();
                if(s != null) {
                    switch(s) {
                        case "ActivityFilter": {
                            if(activityRule0 == null && splitPlaceholderRule0 == null) {
                                throw new IllegalArgumentException("Found orphaned ActivityFilter");
                            }
                            ActivityFilter activityFilter0 = this.parseActivityFilter(context0, xmlResourceParser0);
                            if(activityRule0 != null) {
                                hashSet0.remove(activityRule0);
                                activityRule1 = activityRule0.plus$window_release(activityFilter0);
                                hashSet0.add(activityRule1);
                                activityRule0 = activityRule1;
                            }
                            else if(splitPlaceholderRule0 != null) {
                                hashSet0.remove(splitPlaceholderRule0);
                                splitPlaceholderRule1 = splitPlaceholderRule0.plus$window_release(activityFilter0);
                                hashSet0.add(splitPlaceholderRule1);
                                splitPlaceholderRule0 = splitPlaceholderRule1;
                            }
                            break;
                        }
                        case "ActivityRule": {
                            activityRule1 = this.parseSplitActivityRule(context0, xmlResourceParser0);
                            hashSet0.add(activityRule1);
                            splitPairRule0 = null;
                            splitPlaceholderRule0 = null;
                            activityRule0 = activityRule1;
                            break;
                        }
                        case "SplitPairFilter": {
                            if(splitPairRule0 == null) {
                                throw new IllegalArgumentException("Found orphaned SplitPairFilter outside of SplitPairRule");
                            }
                            SplitPairFilter splitPairFilter0 = this.parseSplitPairFilter(context0, xmlResourceParser0);
                            hashSet0.remove(splitPairRule0);
                            splitPairRule1 = splitPairRule0.plus$window_release(splitPairFilter0);
                            hashSet0.add(splitPairRule1);
                            splitPairRule0 = splitPairRule1;
                            break;
                        }
                        case "SplitPairRule": {
                            splitPairRule1 = this.parseSplitPairRule(context0, xmlResourceParser0);
                            hashSet0.add(splitPairRule1);
                            activityRule0 = null;
                            splitPlaceholderRule0 = null;
                            splitPairRule0 = splitPairRule1;
                            break;
                        }
                        case "SplitPlaceholderRule": {
                            splitPlaceholderRule1 = this.parseSplitPlaceholderRule(context0, xmlResourceParser0);
                            hashSet0.add(splitPlaceholderRule1);
                            activityRule0 = null;
                            splitPairRule0 = null;
                            splitPlaceholderRule0 = splitPlaceholderRule1;
                        }
                    }
                }
            }
            v2 = xmlResourceParser0.next();
        }
        return hashSet0;
    }
}

