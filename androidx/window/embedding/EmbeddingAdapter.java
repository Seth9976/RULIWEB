package androidx.window.embedding;

import android.app.Activity;
import android.content.Intent;
import android.util.Pair;
import android.view.WindowMetrics;
import androidx.window.extensions.embedding.ActivityRule.Builder;
import androidx.window.extensions.embedding.ActivityStack;
import androidx.window.extensions.embedding.EmbeddingRule;
import androidx.window.extensions.embedding.SplitPairRule.Builder;
import androidx.window.extensions.embedding.SplitPlaceholderRule.Builder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0001\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0002J\u001A\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\u00040\u00072\f\u0010\b\u001A\b\u0012\u0004\u0012\u00020\u00060\u0007J\u001A\u0010\u0003\u001A\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\f0\tJ(\u0010\r\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000F0\u000E2\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u00020\u00130\tH\u0007J(\u0010\u0014\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u000F0\u000E2\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u00020\u00130\tH\u0007J\u001C\u0010\u0015\u001A\b\u0012\u0004\u0012\u00020\u00100\u000E2\f\u0010\u0016\u001A\b\u0012\u0004\u0012\u00020\u00170\tH\u0007J\u001C\u0010\u0018\u001A\b\u0012\u0004\u0012\u00020\u00110\u000E2\f\u0010\u0016\u001A\b\u0012\u0004\u0012\u00020\u00170\tH\u0007J\u0016\u0010\u0019\u001A\b\u0012\u0004\u0012\u00020\u001A0\u000E2\u0006\u0010\u001B\u001A\u00020\u001CH\u0007J*\u0010\u001D\u001A\u0002H\u001E\"\u0004\b\u0000\u0010\u001E\"\u0004\b\u0001\u0010\u001F*\u000E\u0012\u0004\u0012\u0002H\u001E\u0012\u0004\u0012\u0002H\u001F0\u000FH\u0082\u0002¢\u0006\u0002\u0010 J*\u0010!\u001A\u0002H\u001F\"\u0004\b\u0000\u0010\u001E\"\u0004\b\u0001\u0010\u001F*\u000E\u0012\u0004\u0012\u0002H\u001E\u0012\u0004\u0012\u0002H\u001F0\u000FH\u0082\u0002¢\u0006\u0002\u0010 ¨\u0006\""}, d2 = {"Landroidx/window/embedding/EmbeddingAdapter;", "", "()V", "translate", "Landroidx/window/embedding/SplitInfo;", "splitInfo", "Landroidx/window/extensions/embedding/SplitInfo;", "", "splitInfoList", "", "Landroidx/window/extensions/embedding/EmbeddingRule;", "rules", "Landroidx/window/embedding/EmbeddingRule;", "translateActivityIntentPredicates", "Ljava/util/function/Predicate;", "Landroid/util/Pair;", "Landroid/app/Activity;", "Landroid/content/Intent;", "splitPairFilters", "Landroidx/window/embedding/SplitPairFilter;", "translateActivityPairPredicates", "translateActivityPredicates", "activityFilters", "Landroidx/window/embedding/ActivityFilter;", "translateIntentPredicates", "translateParentMetricsPredicate", "Landroid/view/WindowMetrics;", "splitRule", "Landroidx/window/embedding/SplitRule;", "component1", "F", "S", "(Landroid/util/Pair;)Ljava/lang/Object;", "component2", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class EmbeddingAdapter {
    // 检测为 Lambda 实现
    public static boolean $r8$lambda$-QIZxMKEpTExKx7Gy-tkwkvFXwg(EmbeddingAdapter embeddingAdapter0, Set set0, Pair pair0) [...]

    // 检测为 Lambda 实现
    public static boolean $r8$lambda$BsxG3N2i5a4O41R3N4HmGj3svxU(Set set0, Activity activity0) [...]

    // 检测为 Lambda 实现
    public static boolean $r8$lambda$cS0oi1t0ZamdVaatgGdu58WtzyQ(Set set0, Intent intent0) [...]

    // 检测为 Lambda 实现
    public static boolean $r8$lambda$cuYtR81jQjtuFyZjsF3nZQqYIos(EmbeddingAdapter embeddingAdapter0, Set set0, Pair pair0) [...]

    // 检测为 Lambda 实现
    public static boolean $r8$lambda$g0h9C17qZk8AsU2UaDHhRyR3IKQ(SplitRule splitRule0, WindowMetrics windowMetrics0) [...]

    private final Object component1(Pair pair0) {
        Intrinsics.checkNotNullParameter(pair0, "<this>");
        return pair0.first;
    }

    private final Object component2(Pair pair0) {
        Intrinsics.checkNotNullParameter(pair0, "<this>");
        return pair0.second;
    }

    private final SplitInfo translate(androidx.window.extensions.embedding.SplitInfo splitInfo0) {
        boolean z1;
        ActivityStack activityStack0 = splitInfo0.getPrimaryActivityStack();
        Intrinsics.checkNotNullExpressionValue(activityStack0, "splitInfo.primaryActivityStack");
        boolean z = false;
        try {
            z1 = false;
            z1 = activityStack0.isEmpty();
        }
        catch(NoSuchMethodError unused_ex) {
        }
        List list0 = activityStack0.getActivities();
        Intrinsics.checkNotNullExpressionValue(list0, "primaryActivityStack.activities");
        androidx.window.embedding.ActivityStack activityStack1 = new androidx.window.embedding.ActivityStack(list0, z1);
        ActivityStack activityStack2 = splitInfo0.getSecondaryActivityStack();
        Intrinsics.checkNotNullExpressionValue(activityStack2, "splitInfo.secondaryActivityStack");
        try {
            z = activityStack2.isEmpty();
        }
        catch(NoSuchMethodError unused_ex) {
        }
        List list1 = activityStack2.getActivities();
        Intrinsics.checkNotNullExpressionValue(list1, "secondaryActivityStack.activities");
        return new SplitInfo(activityStack1, new androidx.window.embedding.ActivityStack(list1, z), splitInfo0.getSplitRatio());
    }

    public final List translate(List list0) {
        Intrinsics.checkNotNullParameter(list0, "splitInfoList");
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            arrayList0.add(this.translate(((androidx.window.extensions.embedding.SplitInfo)object0)));
        }
        return arrayList0;
    }

    public final Set translate(Set set0) {
        EmbeddingRule embeddingRule1;
        Intrinsics.checkNotNullParameter(set0, "rules");
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(set0, 10));
        for(Object object0: set0) {
            androidx.window.embedding.EmbeddingRule embeddingRule0 = (androidx.window.embedding.EmbeddingRule)object0;
            if(embeddingRule0 instanceof SplitPairRule) {
                androidx.window.extensions.embedding.SplitPairRule splitPairRule0 = new SplitPairRule.Builder(this.translateActivityPairPredicates(((SplitPairRule)embeddingRule0).getFilters()), this.translateActivityIntentPredicates(((SplitPairRule)embeddingRule0).getFilters()), this.translateParentMetricsPredicate(((SplitRule)embeddingRule0))).setSplitRatio(((SplitPairRule)embeddingRule0).getSplitRatio()).setLayoutDirection(((SplitPairRule)embeddingRule0).getLayoutDirection()).setShouldFinishPrimaryWithSecondary(((SplitPairRule)embeddingRule0).getFinishPrimaryWithSecondary()).setShouldFinishSecondaryWithPrimary(((SplitPairRule)embeddingRule0).getFinishSecondaryWithPrimary()).setShouldClearTop(((SplitPairRule)embeddingRule0).getClearTop()).build();
                Intrinsics.checkNotNullExpressionValue(splitPairRule0, "SplitPairRuleBuilder(\n  …                 .build()");
                embeddingRule1 = (EmbeddingRule)splitPairRule0;
            }
            else if(embeddingRule0 instanceof SplitPlaceholderRule) {
                androidx.window.extensions.embedding.SplitPlaceholderRule splitPlaceholderRule0 = new SplitPlaceholderRule.Builder(((SplitPlaceholderRule)embeddingRule0).getPlaceholderIntent(), this.translateActivityPredicates(((SplitPlaceholderRule)embeddingRule0).getFilters()), this.translateIntentPredicates(((SplitPlaceholderRule)embeddingRule0).getFilters()), this.translateParentMetricsPredicate(((SplitRule)embeddingRule0))).setSplitRatio(((SplitPlaceholderRule)embeddingRule0).getSplitRatio()).setLayoutDirection(((SplitPlaceholderRule)embeddingRule0).getLayoutDirection()).build();
                Intrinsics.checkNotNullExpressionValue(splitPlaceholderRule0, "SplitPlaceholderRuleBuil…                 .build()");
                embeddingRule1 = (EmbeddingRule)splitPlaceholderRule0;
            }
            else {
                if(!(embeddingRule0 instanceof ActivityRule)) {
                    throw new IllegalArgumentException("Unsupported rule type");
                }
                androidx.window.extensions.embedding.ActivityRule activityRule0 = new ActivityRule.Builder(this.translateActivityPredicates(((ActivityRule)embeddingRule0).getFilters()), this.translateIntentPredicates(((ActivityRule)embeddingRule0).getFilters())).setShouldAlwaysExpand(((ActivityRule)embeddingRule0).getAlwaysExpand()).build();
                Intrinsics.checkNotNullExpressionValue(activityRule0, "ActivityRuleBuilder(\n   …                 .build()");
                embeddingRule1 = (EmbeddingRule)activityRule0;
            }
            arrayList0.add(embeddingRule1);
        }
        return CollectionsKt.toSet(arrayList0);
    }

    public final Predicate translateActivityIntentPredicates(Set set0) {
        Intrinsics.checkNotNullParameter(set0, "splitPairFilters");
        return (Pair pair0) -> EmbeddingAdapter.translateActivityIntentPredicates$lambda-3(this, set0, pair0);
    }

    private static final boolean translateActivityIntentPredicates$lambda-3(EmbeddingAdapter embeddingAdapter0, Set set0, Pair pair0) {
        Intrinsics.checkNotNullParameter(embeddingAdapter0, "this$0");
        Intrinsics.checkNotNullParameter(set0, "$splitPairFilters");
        Intrinsics.checkNotNullExpressionValue(pair0, "(first, second)");
        Activity activity0 = (Activity)embeddingAdapter0.component1(pair0);
        Intent intent0 = (Intent)embeddingAdapter0.component2(pair0);
        if(set0 instanceof Collection && set0.isEmpty()) {
            return false;
        }
        for(Object object0: set0) {
            if(((SplitPairFilter)object0).matchesActivityIntentPair(activity0, intent0)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public final Predicate translateActivityPairPredicates(Set set0) {
        Intrinsics.checkNotNullParameter(set0, "splitPairFilters");
        return (Pair pair0) -> EmbeddingAdapter.translateActivityPairPredicates$lambda-1(this, set0, pair0);
    }

    private static final boolean translateActivityPairPredicates$lambda-1(EmbeddingAdapter embeddingAdapter0, Set set0, Pair pair0) {
        Intrinsics.checkNotNullParameter(embeddingAdapter0, "this$0");
        Intrinsics.checkNotNullParameter(set0, "$splitPairFilters");
        Intrinsics.checkNotNullExpressionValue(pair0, "(first, second)");
        Activity activity0 = (Activity)embeddingAdapter0.component1(pair0);
        Activity activity1 = (Activity)embeddingAdapter0.component2(pair0);
        if(set0 instanceof Collection && set0.isEmpty()) {
            return false;
        }
        for(Object object0: set0) {
            if(((SplitPairFilter)object0).matchesActivityPair(activity0, activity1)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public final Predicate translateActivityPredicates(Set set0) {
        Intrinsics.checkNotNullParameter(set0, "activityFilters");
        return (Activity activity0) -> EmbeddingAdapter.translateActivityPredicates$lambda-6(set0, activity0);
    }

    private static final boolean translateActivityPredicates$lambda-6(Set set0, Activity activity0) {
        Intrinsics.checkNotNullParameter(set0, "$activityFilters");
        if(set0 instanceof Collection && set0.isEmpty()) {
            return false;
        }
        for(Object object0: set0) {
            Intrinsics.checkNotNullExpressionValue(activity0, "activity");
            if(((ActivityFilter)object0).matchesActivity(activity0)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public final Predicate translateIntentPredicates(Set set0) {
        Intrinsics.checkNotNullParameter(set0, "activityFilters");
        return (Intent intent0) -> EmbeddingAdapter.translateIntentPredicates$lambda-8(set0, intent0);
    }

    private static final boolean translateIntentPredicates$lambda-8(Set set0, Intent intent0) {
        Intrinsics.checkNotNullParameter(set0, "$activityFilters");
        if(set0 instanceof Collection && set0.isEmpty()) {
            return false;
        }
        for(Object object0: set0) {
            Intrinsics.checkNotNullExpressionValue(intent0, "intent");
            if(((ActivityFilter)object0).matchesIntent(intent0)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public final Predicate translateParentMetricsPredicate(SplitRule splitRule0) {
        Intrinsics.checkNotNullParameter(splitRule0, "splitRule");
        return (WindowMetrics windowMetrics0) -> EmbeddingAdapter.translateParentMetricsPredicate$lambda-4(splitRule0, windowMetrics0);
    }

    private static final boolean translateParentMetricsPredicate$lambda-4(SplitRule splitRule0, WindowMetrics windowMetrics0) {
        Intrinsics.checkNotNullParameter(splitRule0, "$splitRule");
        Intrinsics.checkNotNullExpressionValue(windowMetrics0, "windowMetrics");
        return splitRule0.checkParentMetrics(windowMetrics0);
    }
}

