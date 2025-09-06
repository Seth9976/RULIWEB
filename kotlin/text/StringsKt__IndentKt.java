package kotlin.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000\u001E\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000B\u001A!\u0010\u0000\u001A\u000E\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0002H\u0002¢\u0006\u0002\b\u0004\u001A\u0011\u0010\u0005\u001A\u00020\u0006*\u00020\u0002H\u0002¢\u0006\u0002\b\u0007\u001A\u0014\u0010\b\u001A\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0003\u001A\u00020\u0002\u001AJ\u0010\t\u001A\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00020\n2\u0006\u0010\u000B\u001A\u00020\u00062\u0012\u0010\f\u001A\u000E\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0014\u0010\r\u001A\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001H\u0082\b¢\u0006\u0002\b\u000E\u001A\u0014\u0010\u000F\u001A\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001A\u00020\u0002\u001A\u001E\u0010\u0011\u001A\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001A\u00020\u00022\b\b\u0002\u0010\u0012\u001A\u00020\u0002\u001A\f\u0010\u0013\u001A\u00020\u0002*\u00020\u0002H\u0007\u001A\u0016\u0010\u0014\u001A\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0012\u001A\u00020\u0002H\u0007¨\u0006\u0015"}, d2 = {"getIndentFunction", "Lkotlin/Function1;", "", "indent", "getIndentFunction$StringsKt__IndentKt", "indentWidth", "", "indentWidth$StringsKt__IndentKt", "prependIndent", "reindent", "", "resultSizeEstimate", "indentAddFunction", "indentCutFunction", "reindent$StringsKt__IndentKt", "replaceIndent", "newIndent", "replaceIndentByMargin", "marginPrefix", "trimIndent", "trimMargin", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/text/StringsKt")
class StringsKt__IndentKt extends StringsKt__AppendableKt {
    private static final Function1 getIndentFunction$StringsKt__IndentKt(String s) {
        return s.length() == 0 ? kotlin.text.StringsKt__IndentKt.getIndentFunction.1.INSTANCE : new Function1(s) {
            final String $indent;

            {
                this.$indent = s;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((String)object0));
            }

            public final String invoke(String s) {
                Intrinsics.checkNotNullParameter(s, "line");
                return this.$indent + s;
            }
        };

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "line", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 0x30)
        final class kotlin.text.StringsKt__IndentKt.getIndentFunction.1 extends Lambda implements Function1 {
            public static final kotlin.text.StringsKt__IndentKt.getIndentFunction.1 INSTANCE;

            static {
                kotlin.text.StringsKt__IndentKt.getIndentFunction.1.INSTANCE = new kotlin.text.StringsKt__IndentKt.getIndentFunction.1();
            }

            kotlin.text.StringsKt__IndentKt.getIndentFunction.1() {
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((String)object0));
            }

            public final String invoke(String s) {
                Intrinsics.checkNotNullParameter(s, "line");
                return s;
            }
        }

    }

    private static final int indentWidth$StringsKt__IndentKt(String s) {
        int v = s.length();
        int v1;
        for(v1 = 0; true; ++v1) {
            if(v1 >= v) {
                v1 = -1;
                break;
            }
            if(!CharsKt.isWhitespace(s.charAt(v1))) {
                break;
            }
        }
        return v1 == -1 ? s.length() : v1;
    }

    public static final String prependIndent(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(s1, "indent");
        return SequencesKt.joinToString$default(SequencesKt.map(StringsKt.lineSequence(s), new Function1(s1) {
            final String $indent;

            {
                this.$indent = s;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((String)object0));
            }

            public final String invoke(String s) {
                Intrinsics.checkNotNullParameter(s, "it");
                if(StringsKt.isBlank(s)) {
                    return s.length() >= this.$indent.length() ? s : this.$indent;
                }
                return this.$indent + s;
            }
        }), "\n", null, null, 0, null, null, 62, null);
    }

    public static String prependIndent$default(String s, String s1, int v, Object object0) {
        if((v & 1) != 0) {
            s1 = "    ";
        }
        return StringsKt.prependIndent(s, s1);
    }

    private static final String reindent$StringsKt__IndentKt(List list0, int v, Function1 function10, Function1 function11) {
        int v1 = CollectionsKt.getLastIndex(list0);
        Collection collection0 = new ArrayList();
        int v2 = 0;
        for(Object object0: list0) {
            if(v2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String s = (String)object0;
            if(v2 != 0 && v2 != v1 || !StringsKt.isBlank(s)) {
                String s1 = (String)function11.invoke(s);
                if(s1 != null) {
                    String s2 = (String)function10.invoke(s1);
                    if(s2 != null) {
                        s = s2;
                    }
                }
            }
            else {
                s = null;
            }
            if(s != null) {
                collection0.add(s);
            }
            ++v2;
        }
        String s3 = ((StringBuilder)CollectionsKt.joinTo$default(((List)collection0), new StringBuilder(v), "\n", null, null, 0, null, null, 0x7C, null)).toString();
        Intrinsics.checkNotNullExpressionValue(s3, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
        return s3;
    }

    public static final String replaceIndent(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(s1, "newIndent");
        List list0 = StringsKt.lines(s);
        Collection collection0 = new ArrayList();
        for(Object object0: list0) {
            if(!StringsKt.isBlank(((String)object0))) {
                collection0.add(object0);
            }
        }
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(((List)collection0), 10));
        for(Object object1: ((List)collection0)) {
            arrayList0.add(StringsKt__IndentKt.indentWidth$StringsKt__IndentKt(((String)object1)));
        }
        Integer integer0 = (Integer)CollectionsKt.minOrNull(arrayList0);
        int v = 0;
        int v1 = integer0 == null ? 0 : ((int)integer0);
        int v2 = s.length();
        int v3 = s1.length();
        int v4 = list0.size();
        Function1 function10 = StringsKt__IndentKt.getIndentFunction$StringsKt__IndentKt(s1);
        int v5 = CollectionsKt.getLastIndex(list0);
        Collection collection1 = new ArrayList();
        for(Object object2: list0) {
            if(v < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String s2 = (String)object2;
            if(v != 0 && v != v5 || !StringsKt.isBlank(s2)) {
                String s3 = StringsKt.drop(s2, v1);
                if(s3 != null) {
                    String s4 = (String)function10.invoke(s3);
                    if(s4 != null) {
                        s2 = s4;
                    }
                }
            }
            else {
                s2 = null;
            }
            if(s2 != null) {
                collection1.add(s2);
            }
            ++v;
        }
        String s5 = ((StringBuilder)CollectionsKt.joinTo$default(((List)collection1), new StringBuilder(v2 + v3 * v4), "\n", null, null, 0, null, null, 0x7C, null)).toString();
        Intrinsics.checkNotNullExpressionValue(s5, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
        return s5;
    }

    public static String replaceIndent$default(String s, String s1, int v, Object object0) {
        if((v & 1) != 0) {
            s1 = "";
        }
        return StringsKt.replaceIndent(s, s1);
    }

    public static final String replaceIndentByMargin(String s, String s1, String s2) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(s1, "newIndent");
        Intrinsics.checkNotNullParameter(s2, "marginPrefix");
        if(StringsKt.isBlank(s2)) {
            throw new IllegalArgumentException("marginPrefix must be non-blank string.");
        }
        List list0 = StringsKt.lines(s);
        int v = s.length();
        int v1 = s1.length();
        int v2 = list0.size();
        Function1 function10 = StringsKt__IndentKt.getIndentFunction$StringsKt__IndentKt(s1);
        int v3 = CollectionsKt.getLastIndex(list0);
        Collection collection0 = new ArrayList();
        int v4 = 0;
        for(Object object0: list0) {
            if(v4 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String s3 = (String)object0;
            String s4 = null;
            if(v4 != 0 && v4 != v3 || !StringsKt.isBlank(s3)) {
                CharSequence charSequence0 = s3;
                int v5 = charSequence0.length();
                int v6 = 0;
                while(true) {
                    if(v6 < v5) {
                        if(CharsKt.isWhitespace(charSequence0.charAt(v6))) {
                            ++v6;
                            continue;
                        }
                        else {
                            break;
                        }
                    }
                    v6 = -1;
                    break;
                }
                if(v6 != -1 && StringsKt.startsWith$default(s3, s2, v6, false, 4, null)) {
                    Intrinsics.checkNotNull(s3, "null cannot be cast to non-null type java.lang.String");
                    s4 = s3.substring(v6 + s2.length());
                    Intrinsics.checkNotNullExpressionValue(s4, "this as java.lang.String).substring(startIndex)");
                }
                if(s4 != null) {
                    String s5 = (String)function10.invoke(s4);
                    if(s5 != null) {
                        s3 = s5;
                    }
                }
            }
            else {
                s3 = null;
            }
            if(s3 != null) {
                collection0.add(s3);
            }
            ++v4;
        }
        String s6 = ((StringBuilder)CollectionsKt.joinTo$default(((List)collection0), new StringBuilder(v + v1 * v2), "\n", null, null, 0, null, null, 0x7C, null)).toString();
        Intrinsics.checkNotNullExpressionValue(s6, "mapIndexedNotNull { inde…\"\\n\")\n        .toString()");
        return s6;
    }

    public static String replaceIndentByMargin$default(String s, String s1, String s2, int v, Object object0) {
        if((v & 1) != 0) {
            s1 = "";
        }
        if((v & 2) != 0) {
            s2 = "|";
        }
        return StringsKt.replaceIndentByMargin(s, s1, s2);
    }

    public static final String trimIndent(String s) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        return StringsKt.replaceIndent(s, "");
    }

    public static final String trimMargin(String s, String s1) {
        Intrinsics.checkNotNullParameter(s, "<this>");
        Intrinsics.checkNotNullParameter(s1, "marginPrefix");
        return StringsKt.replaceIndentByMargin(s, "", s1);
    }

    public static String trimMargin$default(String s, String s1, int v, Object object0) {
        if((v & 1) != 0) {
            s1 = "|";
        }
        return StringsKt.trimMargin(s, s1);
    }
}

