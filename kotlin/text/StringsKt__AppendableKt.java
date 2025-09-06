package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u001A5\u0010\u0000\u001A\u0002H\u0001\"\f\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u0003*\u0002H\u00012\u0016\u0010\u0004\u001A\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u0005\"\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007\u001A9\u0010\b\u001A\u00020\t\"\u0004\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u00032\u0006\u0010\n\u001A\u0002H\u00012\u0014\u0010\u000B\u001A\u0010\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u0006\u0018\u00010\fH\u0000¢\u0006\u0002\u0010\r\u001A\u0015\u0010\u000E\u001A\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u0003H\u0087\b\u001A\u001D\u0010\u000E\u001A\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u00032\u0006\u0010\u0004\u001A\u00020\u000FH\u0087\b\u001A\u001F\u0010\u000E\u001A\u00060\u0002j\u0002`\u0003*\u00060\u0002j\u0002`\u00032\b\u0010\u0004\u001A\u0004\u0018\u00010\u0006H\u0087\b\u001A7\u0010\u0010\u001A\u0002H\u0001\"\f\b\u0000\u0010\u0001*\u00060\u0002j\u0002`\u0003*\u0002H\u00012\u0006\u0010\u0004\u001A\u00020\u00062\u0006\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00020\u0012H\u0007¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"append", "T", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "value", "", "", "(Ljava/lang/Appendable;[Ljava/lang/CharSequence;)Ljava/lang/Appendable;", "appendElement", "", "element", "transform", "Lkotlin/Function1;", "(Ljava/lang/Appendable;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "appendLine", "", "appendRange", "startIndex", "", "endIndex", "(Ljava/lang/Appendable;Ljava/lang/CharSequence;II)Ljava/lang/Appendable;", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/text/StringsKt")
class StringsKt__AppendableKt {
    public static final Appendable append(Appendable appendable0, CharSequence[] arr_charSequence) {
        Intrinsics.checkNotNullParameter(appendable0, "<this>");
        Intrinsics.checkNotNullParameter(arr_charSequence, "value");
        for(int v = 0; v < arr_charSequence.length; ++v) {
            appendable0.append(arr_charSequence[v]);
        }
        return appendable0;
    }

    public static final void appendElement(Appendable appendable0, Object object0, Function1 function10) {
        Intrinsics.checkNotNullParameter(appendable0, "<this>");
        if(function10 != null) {
            appendable0.append(((CharSequence)function10.invoke(object0)));
            return;
        }
        if((object0 == null ? true : object0 instanceof CharSequence)) {
            appendable0.append(((CharSequence)object0));
            return;
        }
        if(object0 instanceof Character) {
            appendable0.append(((Character)object0).charValue());
            return;
        }
        appendable0.append(String.valueOf(object0));
    }

    private static final Appendable appendLine(Appendable appendable0) {
        Intrinsics.checkNotNullParameter(appendable0, "<this>");
        Appendable appendable1 = appendable0.append('\n');
        Intrinsics.checkNotNullExpressionValue(appendable1, "append(\'\\n\')");
        return appendable1;
    }

    private static final Appendable appendLine(Appendable appendable0, char c) {
        Intrinsics.checkNotNullParameter(appendable0, "<this>");
        Appendable appendable1 = appendable0.append(c);
        Intrinsics.checkNotNullExpressionValue(appendable1, "append(value)");
        Appendable appendable2 = appendable1.append('\n');
        Intrinsics.checkNotNullExpressionValue(appendable2, "append(\'\\n\')");
        return appendable2;
    }

    private static final Appendable appendLine(Appendable appendable0, CharSequence charSequence0) {
        Intrinsics.checkNotNullParameter(appendable0, "<this>");
        Appendable appendable1 = appendable0.append(charSequence0);
        Intrinsics.checkNotNullExpressionValue(appendable1, "append(value)");
        Appendable appendable2 = appendable1.append('\n');
        Intrinsics.checkNotNullExpressionValue(appendable2, "append(\'\\n\')");
        return appendable2;
    }

    public static final Appendable appendRange(Appendable appendable0, CharSequence charSequence0, int v, int v1) {
        Intrinsics.checkNotNullParameter(appendable0, "<this>");
        Intrinsics.checkNotNullParameter(charSequence0, "value");
        Appendable appendable1 = appendable0.append(charSequence0, v, v1);
        Intrinsics.checkNotNull(appendable1, "null cannot be cast to non-null type T of kotlin.text.StringsKt__AppendableKt.appendRange");
        return appendable1;
    }
}

