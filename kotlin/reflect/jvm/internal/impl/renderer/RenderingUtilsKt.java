package kotlin.reflect.jvm.internal.impl.renderer;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.name.FqNameUnsafe;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.text.StringsKt;

public final class RenderingUtilsKt {
    public static final String render(FqNameUnsafe fqNameUnsafe0) {
        Intrinsics.checkNotNullParameter(fqNameUnsafe0, "<this>");
        List list0 = fqNameUnsafe0.pathSegments();
        Intrinsics.checkNotNullExpressionValue(list0, "pathSegments()");
        return RenderingUtilsKt.renderFqName(list0);
    }

    public static final String render(Name name0) {
        Intrinsics.checkNotNullParameter(name0, "<this>");
        if(RenderingUtilsKt.shouldBeEscaped(name0)) {
            String s = name0.asString();
            Intrinsics.checkNotNullExpressionValue(s, "asString()");
            return "`" + s + '`';
        }
        String s1 = name0.asString();
        Intrinsics.checkNotNullExpressionValue(s1, "asString()");
        return s1;
    }

    public static final String renderFqName(List list0) {
        Intrinsics.checkNotNullParameter(list0, "pathSegments");
        StringBuilder stringBuilder0 = new StringBuilder();
        for(Object object0: list0) {
            if(stringBuilder0.length() > 0) {
                stringBuilder0.append(".");
            }
            stringBuilder0.append(RenderingUtilsKt.render(((Name)object0)));
        }
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public static final String replacePrefixesInTypeRepresentations(String s, String s1, String s2, String s3, String s4) {
        Intrinsics.checkNotNullParameter(s, "lowerRendered");
        Intrinsics.checkNotNullParameter(s1, "lowerPrefix");
        Intrinsics.checkNotNullParameter(s2, "upperRendered");
        Intrinsics.checkNotNullParameter(s3, "upperPrefix");
        Intrinsics.checkNotNullParameter(s4, "foldedPrefix");
        if(StringsKt.startsWith$default(s, s1, false, 2, null) && StringsKt.startsWith$default(s2, s3, false, 2, null)) {
            String s5 = s.substring(s1.length());
            Intrinsics.checkNotNullExpressionValue(s5, "this as java.lang.String).substring(startIndex)");
            String s6 = s2.substring(s3.length());
            Intrinsics.checkNotNullExpressionValue(s6, "this as java.lang.String).substring(startIndex)");
            String s7 = s4 + s5;
            if(Intrinsics.areEqual(s5, s6)) {
                return s7;
            }
            return RenderingUtilsKt.typeStringsDifferOnlyInNullability(s5, s6) ? s7 + '!' : null;
        }
        return null;
    }

    private static final boolean shouldBeEscaped(Name name0) {
        String s = name0.asString();
        Intrinsics.checkNotNullExpressionValue(s, "asString()");
        if(!KeywordStringsGenerated.KEYWORDS.contains(s)) {
            for(int v = 0; v < s.length(); ++v) {
                int v1 = s.charAt(v);
                if(!Character.isLetterOrDigit(((char)v1)) && v1 != 0x5F) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    public static final boolean typeStringsDifferOnlyInNullability(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "lower");
        Intrinsics.checkNotNullParameter(s1, "upper");
        return Intrinsics.areEqual(s, StringsKt.replace$default(s1, "?", "", false, 4, null)) || StringsKt.endsWith$default(s1, "?", false, 2, null) && Intrinsics.areEqual((s + '?'), s1) || Intrinsics.areEqual(("(" + s + ")?"), s1);
    }
}

