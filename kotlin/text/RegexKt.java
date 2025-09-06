package kotlin.text;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001C\n\u0000\u001A-\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0014\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0082\b\u001A\u001E\u0010\u0007\u001A\u0004\u0018\u00010\b*\u00020\t2\u0006\u0010\n\u001A\u00020\u00062\u0006\u0010\u000B\u001A\u00020\fH\u0002\u001A\u0016\u0010\r\u001A\u0004\u0018\u00010\b*\u00020\t2\u0006\u0010\u000B\u001A\u00020\fH\u0002\u001A\f\u0010\u000E\u001A\u00020\u000F*\u00020\u0010H\u0002\u001A\u0014\u0010\u000E\u001A\u00020\u000F*\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u0006H\u0002\u001A\u0012\u0010\u0012\u001A\u00020\u0006*\b\u0012\u0004\u0012\u00020\u00030\u0013H\u0002¨\u0006\u0014"}, d2 = {"fromInt", "", "T", "Lkotlin/text/FlagEnum;", "", "value", "", "findNext", "Lkotlin/text/MatchResult;", "Ljava/util/regex/Matcher;", "from", "input", "", "matchEntire", "range", "Lkotlin/ranges/IntRange;", "Ljava/util/regex/MatchResult;", "groupIndex", "toInt", "", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class RegexKt {
    public static final IntRange access$range(MatchResult matchResult0, int v) {
        return RegexKt.range(matchResult0, v);
    }

    public static final int access$toInt(Iterable iterable0) {
        return RegexKt.toInt(iterable0);
    }

    private static final kotlin.text.MatchResult findNext(Matcher matcher0, int v, CharSequence charSequence0) {
        return !matcher0.find(v) ? null : new MatcherMatchResult(matcher0, charSequence0);
    }

    private static final Set fromInt(int v) {
        Intrinsics.reifiedOperationMarker(4, "T");
        EnumSet enumSet0 = EnumSet.allOf(Enum.class);
        Intrinsics.checkNotNullExpressionValue(enumSet0, "fromInt$lambda$1");
        Intrinsics.needClassReification();
        CollectionsKt.retainAll(enumSet0, new Function1(v) {
            final int $value;

            {
                this.$value = v;
                super(1);
            }

            public final Boolean invoke(Enum enum0) {
                int v = ((FlagEnum)enum0).getMask();
                int v1 = ((FlagEnum)enum0).getValue();
                return (this.$value & v) == v1;
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Enum)object0));
            }
        });
        Set set0 = Collections.unmodifiableSet(enumSet0);
        Intrinsics.checkNotNullExpressionValue(set0, "unmodifiableSet(EnumSet.…mask == it.value }\n    })");
        return set0;
    }

    private static final kotlin.text.MatchResult matchEntire(Matcher matcher0, CharSequence charSequence0) {
        return !matcher0.matches() ? null : new MatcherMatchResult(matcher0, charSequence0);
    }

    private static final IntRange range(MatchResult matchResult0) {
        return RangesKt.until(matchResult0.start(), matchResult0.end());
    }

    private static final IntRange range(MatchResult matchResult0, int v) {
        return RangesKt.until(matchResult0.start(v), matchResult0.end(v));
    }

    private static final int toInt(Iterable iterable0) {
        int v = 0;
        for(Object object0: iterable0) {
            v |= ((FlagEnum)object0).getValue();
        }
        return v;
    }
}

