package kotlin.text;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0010\u0000\n\u0002\u0010\u000B\n\u0002\u0010\u000E\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\u001A\u001F\u0010\u0000\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001A\u0004\u0018\u00010\u0004H\u0087\b\u001A\u001D\u0010\u0000\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001A\u00020\u0005H\u0087\b\u001A\u001D\u0010\u0000\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001A\u00020\u0006H\u0087\b\u001A\u001D\u0010\u0000\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001A\u00020\u0007H\u0087\b\u001A\u001D\u0010\u0000\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001A\u00020\bH\u0087\b\u001A\u001D\u0010\u0000\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001A\u00020\tH\u0087\b\u001A\u001D\u0010\u0000\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001A\u00020\nH\u0087\b\u001A%\u0010\u0000\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u000E\u0010\u0003\u001A\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0002H\u0087\b\u001A-\u0010\u000B\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\b2\u0006\u0010\u000E\u001A\u00020\bH\u0087\b\u001A-\u0010\u000B\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001A\u00020\u000F2\u0006\u0010\r\u001A\u00020\b2\u0006\u0010\u000E\u001A\u00020\bH\u0087\b\u001A\u0014\u0010\u0010\u001A\u00060\u0011j\u0002`\u0012*\u00060\u0011j\u0002`\u0012H\u0007\u001A\u001D\u0010\u0010\u001A\u00060\u0011j\u0002`\u0012*\u00060\u0011j\u0002`\u00122\u0006\u0010\u0003\u001A\u00020\u0013H\u0087\b\u001A\u001F\u0010\u0010\u001A\u00060\u0011j\u0002`\u0012*\u00060\u0011j\u0002`\u00122\b\u0010\u0003\u001A\u0004\u0018\u00010\u000FH\u0087\b\u001A\u0014\u0010\u0010\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u0002H\u0007\u001A\u001F\u0010\u0010\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001A\u0004\u0018\u00010\u0004H\u0087\b\u001A\u001F\u0010\u0010\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001A\u0004\u0018\u00010\u0014H\u0087\b\u001A\u001D\u0010\u0010\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001A\u00020\u0015H\u0087\b\u001A\u001D\u0010\u0010\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001A\u00020\u0005H\u0087\b\u001A\u001D\u0010\u0010\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001A\u00020\u0013H\u0087\b\u001A\u001D\u0010\u0010\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001A\u00020\fH\u0087\b\u001A\u001F\u0010\u0010\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001A\u0004\u0018\u00010\u000FH\u0087\b\u001A\u001D\u0010\u0010\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001A\u00020\u0006H\u0087\b\u001A\u001D\u0010\u0010\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001A\u00020\u0007H\u0087\b\u001A\u001D\u0010\u0010\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001A\u00020\bH\u0087\b\u001A\u001D\u0010\u0010\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001A\u00020\tH\u0087\b\u001A\u001D\u0010\u0010\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001A\u00020\nH\u0087\b\u001A\u001F\u0010\u0010\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\b\u0010\u0003\u001A\u0004\u0018\u00010\u0016H\u0087\b\u001A%\u0010\u0010\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u000E\u0010\u0003\u001A\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0002H\u0087\b\u001A\u0014\u0010\u0017\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u0002H\u0007\u001A\u001D\u0010\u0018\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0019\u001A\u00020\bH\u0087\b\u001A%\u0010\u001A\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\r\u001A\u00020\b2\u0006\u0010\u000E\u001A\u00020\bH\u0087\b\u001A5\u0010\u001B\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0019\u001A\u00020\b2\u0006\u0010\u0003\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\b2\u0006\u0010\u000E\u001A\u00020\bH\u0087\b\u001A5\u0010\u001B\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0019\u001A\u00020\b2\u0006\u0010\u0003\u001A\u00020\u000F2\u0006\u0010\r\u001A\u00020\b2\u0006\u0010\u000E\u001A\u00020\bH\u0087\b\u001A!\u0010\u001C\u001A\u00020\u001D*\u00060\u0001j\u0002`\u00022\u0006\u0010\u0019\u001A\u00020\b2\u0006\u0010\u0003\u001A\u00020\u0013H\u0087\n\u001A-\u0010\u001E\u001A\u00060\u0001j\u0002`\u0002*\u00060\u0001j\u0002`\u00022\u0006\u0010\r\u001A\u00020\b2\u0006\u0010\u000E\u001A\u00020\b2\u0006\u0010\u0003\u001A\u00020\u0016H\u0087\b\u001A7\u0010\u001F\u001A\u00020\u001D*\u00060\u0001j\u0002`\u00022\u0006\u0010 \u001A\u00020\f2\b\b\u0002\u0010!\u001A\u00020\b2\b\b\u0002\u0010\r\u001A\u00020\b2\b\b\u0002\u0010\u000E\u001A\u00020\bH\u0087\b\u00A8\u0006\""}, d2 = {"appendLine", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "value", "Ljava/lang/StringBuffer;", "", "", "", "", "", "", "appendRange", "", "startIndex", "endIndex", "", "appendln", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "", "", "", "", "clear", "deleteAt", "index", "deleteRange", "insertRange", "set", "", "setRange", "toCharArray", "destination", "destinationOffset", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/text/StringsKt")
class StringsKt__StringBuilderJVMKt extends StringsKt__RegexExtensionsKt {
    private static final StringBuilder appendLine(StringBuilder stringBuilder0, byte b) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.append(((int)b));
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value.toInt())");
        stringBuilder0.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
        return stringBuilder0;
    }

    private static final StringBuilder appendLine(StringBuilder stringBuilder0, double f) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.append(f);
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
        stringBuilder0.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
        return stringBuilder0;
    }

    private static final StringBuilder appendLine(StringBuilder stringBuilder0, float f) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.append(f);
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
        stringBuilder0.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
        return stringBuilder0;
    }

    private static final StringBuilder appendLine(StringBuilder stringBuilder0, int v) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.append(v);
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
        stringBuilder0.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
        return stringBuilder0;
    }

    private static final StringBuilder appendLine(StringBuilder stringBuilder0, long v) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.append(v);
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
        stringBuilder0.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
        return stringBuilder0;
    }

    private static final StringBuilder appendLine(StringBuilder stringBuilder0, StringBuffer stringBuffer0) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.append(stringBuffer0);
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
        stringBuilder0.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
        return stringBuilder0;
    }

    private static final StringBuilder appendLine(StringBuilder stringBuilder0, StringBuilder stringBuilder1) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.append(stringBuilder1);
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
        stringBuilder0.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
        return stringBuilder0;
    }

    private static final StringBuilder appendLine(StringBuilder stringBuilder0, short v) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.append(((int)v));
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value.toInt())");
        stringBuilder0.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
        return stringBuilder0;
    }

    private static final StringBuilder appendRange(StringBuilder stringBuilder0, CharSequence charSequence0, int v, int v1) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        Intrinsics.checkNotNullParameter(charSequence0, "value");
        stringBuilder0.append(charSequence0, v, v1);
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "this.append(value, startIndex, endIndex)");
        return stringBuilder0;
    }

    private static final StringBuilder appendRange(StringBuilder stringBuilder0, char[] arr_c, int v, int v1) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        Intrinsics.checkNotNullParameter(arr_c, "value");
        stringBuilder0.append(arr_c, v, v1 - v);
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "this.append(value, start…x, endIndex - startIndex)");
        return stringBuilder0;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character \'\\n\' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine()", imports = {}))
    public static final Appendable appendln(Appendable appendable0) {
        Intrinsics.checkNotNullParameter(appendable0, "<this>");
        Appendable appendable1 = appendable0.append("\n");
        Intrinsics.checkNotNullExpressionValue(appendable1, "append(SystemProperties.LINE_SEPARATOR)");
        return appendable1;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character \'\\n\' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final Appendable appendln(Appendable appendable0, char c) {
        Intrinsics.checkNotNullParameter(appendable0, "<this>");
        Appendable appendable1 = appendable0.append(c);
        Intrinsics.checkNotNullExpressionValue(appendable1, "append(value)");
        return StringsKt.appendln(appendable1);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character \'\\n\' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final Appendable appendln(Appendable appendable0, CharSequence charSequence0) {
        Intrinsics.checkNotNullParameter(appendable0, "<this>");
        Appendable appendable1 = appendable0.append(charSequence0);
        Intrinsics.checkNotNullExpressionValue(appendable1, "append(value)");
        return StringsKt.appendln(appendable1);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character \'\\n\' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine()", imports = {}))
    public static final StringBuilder appendln(StringBuilder stringBuilder0) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.append("\n");
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(SystemProperties.LINE_SEPARATOR)");
        return stringBuilder0;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character \'\\n\' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder stringBuilder0, byte b) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.append(((int)b));
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value.toInt())");
        return StringsKt.appendln(stringBuilder0);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character \'\\n\' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder stringBuilder0, char c) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.append(c);
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
        return StringsKt.appendln(stringBuilder0);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character \'\\n\' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder stringBuilder0, double f) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.append(f);
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
        return StringsKt.appendln(stringBuilder0);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character \'\\n\' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder stringBuilder0, float f) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.append(f);
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
        return StringsKt.appendln(stringBuilder0);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character \'\\n\' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder stringBuilder0, int v) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.append(v);
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
        return StringsKt.appendln(stringBuilder0);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character \'\\n\' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder stringBuilder0, long v) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.append(v);
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
        return StringsKt.appendln(stringBuilder0);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character \'\\n\' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder stringBuilder0, CharSequence charSequence0) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.append(charSequence0);
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
        return StringsKt.appendln(stringBuilder0);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character \'\\n\' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder stringBuilder0, Object object0) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.append(object0);
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
        return StringsKt.appendln(stringBuilder0);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character \'\\n\' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder stringBuilder0, String s) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.append(s);
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
        return StringsKt.appendln(stringBuilder0);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character \'\\n\' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder stringBuilder0, StringBuffer stringBuffer0) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.append(stringBuffer0);
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
        return StringsKt.appendln(stringBuilder0);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character \'\\n\' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder stringBuilder0, StringBuilder stringBuilder1) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.append(stringBuilder1);
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
        return StringsKt.appendln(stringBuilder0);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character \'\\n\' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder stringBuilder0, short v) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.append(((int)v));
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value.toInt())");
        return StringsKt.appendln(stringBuilder0);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character \'\\n\' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder stringBuilder0, boolean z) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.append(z);
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
        return StringsKt.appendln(stringBuilder0);
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use appendLine instead. Note that the new method always appends the line feed character \'\\n\' regardless of the system line separator.", replaceWith = @ReplaceWith(expression = "appendLine(value)", imports = {}))
    private static final StringBuilder appendln(StringBuilder stringBuilder0, char[] arr_c) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        Intrinsics.checkNotNullParameter(arr_c, "value");
        stringBuilder0.append(arr_c);
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
        return StringsKt.appendln(stringBuilder0);
    }

    public static final StringBuilder clear(StringBuilder stringBuilder0) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.setLength(0);
        return stringBuilder0;
    }

    private static final StringBuilder deleteAt(StringBuilder stringBuilder0, int v) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        StringBuilder stringBuilder1 = stringBuilder0.deleteCharAt(v);
        Intrinsics.checkNotNullExpressionValue(stringBuilder1, "this.deleteCharAt(index)");
        return stringBuilder1;
    }

    private static final StringBuilder deleteRange(StringBuilder stringBuilder0, int v, int v1) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        StringBuilder stringBuilder1 = stringBuilder0.delete(v, v1);
        Intrinsics.checkNotNullExpressionValue(stringBuilder1, "this.delete(startIndex, endIndex)");
        return stringBuilder1;
    }

    private static final StringBuilder insertRange(StringBuilder stringBuilder0, int v, CharSequence charSequence0, int v1, int v2) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        Intrinsics.checkNotNullParameter(charSequence0, "value");
        StringBuilder stringBuilder1 = stringBuilder0.insert(v, charSequence0, v1, v2);
        Intrinsics.checkNotNullExpressionValue(stringBuilder1, "this.insert(index, value, startIndex, endIndex)");
        return stringBuilder1;
    }

    private static final StringBuilder insertRange(StringBuilder stringBuilder0, int v, char[] arr_c, int v1, int v2) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        Intrinsics.checkNotNullParameter(arr_c, "value");
        StringBuilder stringBuilder1 = stringBuilder0.insert(v, arr_c, v1, v2 - v1);
        Intrinsics.checkNotNullExpressionValue(stringBuilder1, "this.insert(index, value…x, endIndex - startIndex)");
        return stringBuilder1;
    }

    private static final void set(StringBuilder stringBuilder0, int v, char c) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        stringBuilder0.setCharAt(v, c);
    }

    private static final StringBuilder setRange(StringBuilder stringBuilder0, int v, int v1, String s) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        Intrinsics.checkNotNullParameter(s, "value");
        StringBuilder stringBuilder1 = stringBuilder0.replace(v, v1, s);
        Intrinsics.checkNotNullExpressionValue(stringBuilder1, "this.replace(startIndex, endIndex, value)");
        return stringBuilder1;
    }

    private static final void toCharArray(StringBuilder stringBuilder0, char[] arr_c, int v, int v1, int v2) {
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        Intrinsics.checkNotNullParameter(arr_c, "destination");
        stringBuilder0.getChars(v1, v2, arr_c, v);
    }

    static void toCharArray$default(StringBuilder stringBuilder0, char[] arr_c, int v, int v1, int v2, int v3, Object object0) {
        if((v3 & 2) != 0) {
            v = 0;
        }
        if((v3 & 4) != 0) {
            v1 = 0;
        }
        if((v3 & 8) != 0) {
            v2 = stringBuilder0.length();
        }
        Intrinsics.checkNotNullParameter(stringBuilder0, "<this>");
        Intrinsics.checkNotNullParameter(arr_c, "destination");
        stringBuilder0.getChars(v1, v2, arr_c, v);
    }
}

