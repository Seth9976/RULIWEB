package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000E\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00132\u00020\u0001:\u0004\u0011\u0012\u0013\u0014B\u001F\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u000F\u001A\u00020\u0010H\u0016R\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001A\u00020\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\u000E¨\u0006\u0015"}, d2 = {"Lkotlin/text/HexFormat;", "", "upperCase", "", "bytes", "Lkotlin/text/HexFormat$BytesHexFormat;", "number", "Lkotlin/text/HexFormat$NumberHexFormat;", "(ZLkotlin/text/HexFormat$BytesHexFormat;Lkotlin/text/HexFormat$NumberHexFormat;)V", "getBytes", "()Lkotlin/text/HexFormat$BytesHexFormat;", "getNumber", "()Lkotlin/text/HexFormat$NumberHexFormat;", "getUpperCase", "()Z", "toString", "", "Builder", "BytesHexFormat", "Companion", "NumberHexFormat", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class HexFormat {
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0001¢\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001A\u00020\u0014H\u0001J%\u0010\u0007\u001A\u00020\u00152\u0017\u0010\u0016\u001A\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00150\u0017¢\u0006\u0002\b\u0018H\u0087\bø\u0001\u0000J%\u0010\n\u001A\u00020\u00152\u0017\u0010\u0016\u001A\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00150\u0017¢\u0006\u0002\b\u0018H\u0087\bø\u0001\u0000R\u0010\u0010\u0003\u001A\u0004\u0018\u00010\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u0004\u0018\u00010\u0006X\u0082\u000E¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001A\u00020\u00048F¢\u0006\u0006\u001A\u0004\b\b\u0010\tR\u0011\u0010\n\u001A\u00020\u00068F¢\u0006\u0006\u001A\u0004\b\u000B\u0010\fR\u001A\u0010\r\u001A\u00020\u000EX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u000F\u0010\u0010\"\u0004\b\u0011\u0010\u0012\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0019"}, d2 = {"Lkotlin/text/HexFormat$Builder;", "", "()V", "_bytes", "Lkotlin/text/HexFormat$BytesHexFormat$Builder;", "_number", "Lkotlin/text/HexFormat$NumberHexFormat$Builder;", "bytes", "getBytes", "()Lkotlin/text/HexFormat$BytesHexFormat$Builder;", "number", "getNumber", "()Lkotlin/text/HexFormat$NumberHexFormat$Builder;", "upperCase", "", "getUpperCase", "()Z", "setUpperCase", "(Z)V", "build", "Lkotlin/text/HexFormat;", "", "builderAction", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Builder {
        private kotlin.text.HexFormat.BytesHexFormat.Builder _bytes;
        private kotlin.text.HexFormat.NumberHexFormat.Builder _number;
        private boolean upperCase;

        public Builder() {
            this.upperCase = HexFormat.Companion.getDefault().getUpperCase();
        }

        public final HexFormat build() {
            BytesHexFormat hexFormat$BytesHexFormat0;
            boolean z = this.upperCase;
            kotlin.text.HexFormat.BytesHexFormat.Builder hexFormat$BytesHexFormat$Builder0 = this._bytes;
            if(hexFormat$BytesHexFormat$Builder0 == null) {
                hexFormat$BytesHexFormat0 = BytesHexFormat.Companion.getDefault$kotlin_stdlib();
            }
            else {
                hexFormat$BytesHexFormat0 = hexFormat$BytesHexFormat$Builder0.build$kotlin_stdlib();
                if(hexFormat$BytesHexFormat0 == null) {
                    hexFormat$BytesHexFormat0 = BytesHexFormat.Companion.getDefault$kotlin_stdlib();
                }
            }
            kotlin.text.HexFormat.NumberHexFormat.Builder hexFormat$NumberHexFormat$Builder0 = this._number;
            if(hexFormat$NumberHexFormat$Builder0 != null) {
                NumberHexFormat hexFormat$NumberHexFormat0 = hexFormat$NumberHexFormat$Builder0.build$kotlin_stdlib();
                return hexFormat$NumberHexFormat0 == null ? new HexFormat(z, hexFormat$BytesHexFormat0, NumberHexFormat.Companion.getDefault$kotlin_stdlib()) : new HexFormat(z, hexFormat$BytesHexFormat0, hexFormat$NumberHexFormat0);
            }
            return new HexFormat(z, hexFormat$BytesHexFormat0, NumberHexFormat.Companion.getDefault$kotlin_stdlib());
        }

        private final void bytes(Function1 function10) {
            Intrinsics.checkNotNullParameter(function10, "builderAction");
            function10.invoke(this.getBytes());
        }

        public final kotlin.text.HexFormat.BytesHexFormat.Builder getBytes() {
            if(this._bytes == null) {
                this._bytes = new kotlin.text.HexFormat.BytesHexFormat.Builder();
            }
            kotlin.text.HexFormat.BytesHexFormat.Builder hexFormat$BytesHexFormat$Builder0 = this._bytes;
            Intrinsics.checkNotNull(hexFormat$BytesHexFormat$Builder0);
            return hexFormat$BytesHexFormat$Builder0;
        }

        public final kotlin.text.HexFormat.NumberHexFormat.Builder getNumber() {
            if(this._number == null) {
                this._number = new kotlin.text.HexFormat.NumberHexFormat.Builder();
            }
            kotlin.text.HexFormat.NumberHexFormat.Builder hexFormat$NumberHexFormat$Builder0 = this._number;
            Intrinsics.checkNotNull(hexFormat$NumberHexFormat$Builder0);
            return hexFormat$NumberHexFormat$Builder0;
        }

        public final boolean getUpperCase() {
            return this.upperCase;
        }

        private final void number(Function1 function10) {
            Intrinsics.checkNotNullParameter(function10, "builderAction");
            function10.invoke(this.getNumber());
        }

        public final void setUpperCase(boolean z) {
            this.upperCase = z;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u001B2\u00020\u0001:\u0002\u001A\u001BB7\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\u0006\u0010\u0007\u001A\u00020\u0006\u0012\u0006\u0010\b\u001A\u00020\u0006\u0012\u0006\u0010\t\u001A\u00020\u0006¢\u0006\u0002\u0010\nJ%\u0010\u0013\u001A\u00060\u0014j\u0002`\u00152\n\u0010\u0016\u001A\u00060\u0014j\u0002`\u00152\u0006\u0010\u0017\u001A\u00020\u0006H\u0000¢\u0006\u0002\b\u0018J\b\u0010\u0019\u001A\u00020\u0006H\u0016R\u0011\u0010\b\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u0011\u0010\u0007\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\r\u0010\fR\u0011\u0010\t\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u000E\u0010\fR\u0011\u0010\u0004\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u000F\u0010\u0010R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\u0012\u0010\f¨\u0006\u001C"}, d2 = {"Lkotlin/text/HexFormat$BytesHexFormat;", "", "bytesPerLine", "", "bytesPerGroup", "groupSeparator", "", "byteSeparator", "bytePrefix", "byteSuffix", "(IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBytePrefix", "()Ljava/lang/String;", "getByteSeparator", "getByteSuffix", "getBytesPerGroup", "()I", "getBytesPerLine", "getGroupSeparator", "appendOptionsTo", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "sb", "indent", "appendOptionsTo$kotlin_stdlib", "toString", "Builder", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class BytesHexFormat {
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u000B\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\r\u0010\u001C\u001A\u00020\u001DH\u0000¢\u0006\u0002\b\u001ER$\u0010\u0005\u001A\u00020\u00042\u0006\u0010\u0003\u001A\u00020\u0004@FX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR$\u0010\n\u001A\u00020\u00042\u0006\u0010\u0003\u001A\u00020\u0004@FX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u000B\u0010\u0007\"\u0004\b\f\u0010\tR$\u0010\r\u001A\u00020\u00042\u0006\u0010\u0003\u001A\u00020\u0004@FX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u000E\u0010\u0007\"\u0004\b\u000F\u0010\tR$\u0010\u0011\u001A\u00020\u00102\u0006\u0010\u0003\u001A\u00020\u0010@FX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R$\u0010\u0016\u001A\u00020\u00102\u0006\u0010\u0003\u001A\u00020\u0010@FX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0017\u0010\u0013\"\u0004\b\u0018\u0010\u0015R\u001A\u0010\u0019\u001A\u00020\u0004X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u001A\u0010\u0007\"\u0004\b\u001B\u0010\t¨\u0006\u001F"}, d2 = {"Lkotlin/text/HexFormat$BytesHexFormat$Builder;", "", "()V", "value", "", "bytePrefix", "getBytePrefix", "()Ljava/lang/String;", "setBytePrefix", "(Ljava/lang/String;)V", "byteSeparator", "getByteSeparator", "setByteSeparator", "byteSuffix", "getByteSuffix", "setByteSuffix", "", "bytesPerGroup", "getBytesPerGroup", "()I", "setBytesPerGroup", "(I)V", "bytesPerLine", "getBytesPerLine", "setBytesPerLine", "groupSeparator", "getGroupSeparator", "setGroupSeparator", "build", "Lkotlin/text/HexFormat$BytesHexFormat;", "build$kotlin_stdlib", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
        public static final class kotlin.text.HexFormat.BytesHexFormat.Builder {
            private String bytePrefix;
            private String byteSeparator;
            private String byteSuffix;
            private int bytesPerGroup;
            private int bytesPerLine;
            private String groupSeparator;

            public kotlin.text.HexFormat.BytesHexFormat.Builder() {
                this.bytesPerLine = BytesHexFormat.Companion.getDefault$kotlin_stdlib().getBytesPerLine();
                this.bytesPerGroup = BytesHexFormat.Companion.getDefault$kotlin_stdlib().getBytesPerGroup();
                this.groupSeparator = "  ";
                this.byteSeparator = "";
                this.bytePrefix = "";
                this.byteSuffix = "";
            }

            public final BytesHexFormat build$kotlin_stdlib() {
                return new BytesHexFormat(this.bytesPerLine, this.bytesPerGroup, this.groupSeparator, this.byteSeparator, this.bytePrefix, this.byteSuffix);
            }

            public final String getBytePrefix() {
                return this.bytePrefix;
            }

            public final String getByteSeparator() {
                return this.byteSeparator;
            }

            public final String getByteSuffix() {
                return this.byteSuffix;
            }

            public final int getBytesPerGroup() {
                return this.bytesPerGroup;
            }

            public final int getBytesPerLine() {
                return this.bytesPerLine;
            }

            public final String getGroupSeparator() {
                return this.groupSeparator;
            }

            public final void setBytePrefix(String s) {
                Intrinsics.checkNotNullParameter(s, "value");
                if(StringsKt.contains$default(s, '\n', false, 2, null) || StringsKt.contains$default(s, '\r', false, 2, null)) {
                    throw new IllegalArgumentException("LF and CR characters are prohibited in bytePrefix, but was " + s);
                }
                this.bytePrefix = s;
            }

            public final void setByteSeparator(String s) {
                Intrinsics.checkNotNullParameter(s, "value");
                if(StringsKt.contains$default(s, '\n', false, 2, null) || StringsKt.contains$default(s, '\r', false, 2, null)) {
                    throw new IllegalArgumentException("LF and CR characters are prohibited in byteSeparator, but was " + s);
                }
                this.byteSeparator = s;
            }

            public final void setByteSuffix(String s) {
                Intrinsics.checkNotNullParameter(s, "value");
                if(StringsKt.contains$default(s, '\n', false, 2, null) || StringsKt.contains$default(s, '\r', false, 2, null)) {
                    throw new IllegalArgumentException("LF and CR characters are prohibited in byteSuffix, but was " + s);
                }
                this.byteSuffix = s;
            }

            public final void setBytesPerGroup(int v) {
                if(v <= 0) {
                    throw new IllegalArgumentException("Non-positive values are prohibited for bytesPerGroup, but was " + v);
                }
                this.bytesPerGroup = v;
            }

            public final void setBytesPerLine(int v) {
                if(v <= 0) {
                    throw new IllegalArgumentException("Non-positive values are prohibited for bytesPerLine, but was " + v);
                }
                this.bytesPerLine = v;
            }

            public final void setGroupSeparator(String s) {
                Intrinsics.checkNotNullParameter(s, "<set-?>");
                this.groupSeparator = s;
            }
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001A\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/text/HexFormat$BytesHexFormat$Companion;", "", "()V", "Default", "Lkotlin/text/HexFormat$BytesHexFormat;", "getDefault$kotlin_stdlib", "()Lkotlin/text/HexFormat$BytesHexFormat;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
        public static final class Companion {
            private Companion() {
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }

            public final BytesHexFormat getDefault$kotlin_stdlib() {
                return BytesHexFormat.Default;
            }
        }

        public static final Companion Companion;
        private static final BytesHexFormat Default;
        private final String bytePrefix;
        private final String byteSeparator;
        private final String byteSuffix;
        private final int bytesPerGroup;
        private final int bytesPerLine;
        private final String groupSeparator;

        static {
            BytesHexFormat.Companion = new Companion(null);
            BytesHexFormat.Default = new BytesHexFormat(0x7FFFFFFF, 0x7FFFFFFF, "  ", "", "", "");
        }

        public BytesHexFormat(int v, int v1, String s, String s1, String s2, String s3) {
            Intrinsics.checkNotNullParameter(s, "groupSeparator");
            Intrinsics.checkNotNullParameter(s1, "byteSeparator");
            Intrinsics.checkNotNullParameter(s2, "bytePrefix");
            Intrinsics.checkNotNullParameter(s3, "byteSuffix");
            super();
            this.bytesPerLine = v;
            this.bytesPerGroup = v1;
            this.groupSeparator = s;
            this.byteSeparator = s1;
            this.bytePrefix = s2;
            this.byteSuffix = s3;
        }

        public final StringBuilder appendOptionsTo$kotlin_stdlib(StringBuilder stringBuilder0, String s) {
            Intrinsics.checkNotNullParameter(stringBuilder0, "sb");
            Intrinsics.checkNotNullParameter(s, "indent");
            stringBuilder0.append(s);
            stringBuilder0.append("bytesPerLine = ");
            stringBuilder0.append(this.bytesPerLine);
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "sb.append(indent).append…= \").append(bytesPerLine)");
            stringBuilder0.append(",");
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
            stringBuilder0.append('\n');
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
            stringBuilder0.append(s);
            stringBuilder0.append("bytesPerGroup = ");
            stringBuilder0.append(this.bytesPerGroup);
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "sb.append(indent).append… \").append(bytesPerGroup)");
            stringBuilder0.append(",");
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
            stringBuilder0.append('\n');
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
            stringBuilder0.append(s);
            stringBuilder0.append("groupSeparator = \"");
            stringBuilder0.append(this.groupSeparator);
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "sb.append(indent).append…\").append(groupSeparator)");
            stringBuilder0.append("\",");
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
            stringBuilder0.append('\n');
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
            stringBuilder0.append(s);
            stringBuilder0.append("byteSeparator = \"");
            stringBuilder0.append(this.byteSeparator);
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "sb.append(indent).append…\"\").append(byteSeparator)");
            stringBuilder0.append("\",");
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
            stringBuilder0.append('\n');
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
            stringBuilder0.append(s);
            stringBuilder0.append("bytePrefix = \"");
            stringBuilder0.append(this.bytePrefix);
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "sb.append(indent).append…= \\\"\").append(bytePrefix)");
            stringBuilder0.append("\",");
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
            stringBuilder0.append('\n');
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
            stringBuilder0.append(s);
            stringBuilder0.append("byteSuffix = \"");
            stringBuilder0.append(this.byteSuffix);
            stringBuilder0.append("\"");
            return stringBuilder0;
        }

        public final String getBytePrefix() {
            return this.bytePrefix;
        }

        public final String getByteSeparator() {
            return this.byteSeparator;
        }

        public final String getByteSuffix() {
            return this.byteSuffix;
        }

        public final int getBytesPerGroup() {
            return this.bytesPerGroup;
        }

        public final int getBytesPerLine() {
            return this.bytesPerLine;
        }

        public final String getGroupSeparator() {
            return this.groupSeparator;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder0 = new StringBuilder();
            stringBuilder0.append("BytesHexFormat(");
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\"BytesHexFormat(\")");
            stringBuilder0.append('\n');
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
            StringBuilder stringBuilder1 = this.appendOptionsTo$kotlin_stdlib(stringBuilder0, "    ");
            stringBuilder1.append('\n');
            Intrinsics.checkNotNullExpressionValue(stringBuilder1, "append(\'\\n\')");
            stringBuilder0.append(")");
            String s = stringBuilder0.toString();
            Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
            return s;
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"Lkotlin/text/HexFormat$Companion;", "", "()V", "Default", "Lkotlin/text/HexFormat;", "getDefault", "()Lkotlin/text/HexFormat;", "UpperCase", "getUpperCase", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class kotlin.text.HexFormat.Companion {
        private kotlin.text.HexFormat.Companion() {
        }

        public kotlin.text.HexFormat.Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final HexFormat getDefault() {
            return HexFormat.Default;
        }

        public final HexFormat getUpperCase() {
            return HexFormat.UpperCase;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0002\u0014\u0015B\u001F\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J%\u0010\r\u001A\u00060\u000Ej\u0002`\u000F2\n\u0010\u0010\u001A\u00060\u000Ej\u0002`\u000F2\u0006\u0010\u0011\u001A\u00020\u0003H\u0000¢\u0006\u0002\b\u0012J\b\u0010\u0013\u001A\u00020\u0003H\u0016R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000BR\u0011\u0010\u0004\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\t¨\u0006\u0016"}, d2 = {"Lkotlin/text/HexFormat$NumberHexFormat;", "", "prefix", "", "suffix", "removeLeadingZeros", "", "(Ljava/lang/String;Ljava/lang/String;Z)V", "getPrefix", "()Ljava/lang/String;", "getRemoveLeadingZeros", "()Z", "getSuffix", "appendOptionsTo", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "sb", "indent", "appendOptionsTo$kotlin_stdlib", "toString", "Builder", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class NumberHexFormat {
        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\r\u0010\u0013\u001A\u00020\u0014H\u0000¢\u0006\u0002\b\u0015R$\u0010\u0005\u001A\u00020\u00042\u0006\u0010\u0003\u001A\u00020\u0004@FX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001A\u0010\n\u001A\u00020\u000BX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\f\u0010\r\"\u0004\b\u000E\u0010\u000FR$\u0010\u0010\u001A\u00020\u00042\u0006\u0010\u0003\u001A\u00020\u0004@FX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\t¨\u0006\u0016"}, d2 = {"Lkotlin/text/HexFormat$NumberHexFormat$Builder;", "", "()V", "value", "", "prefix", "getPrefix", "()Ljava/lang/String;", "setPrefix", "(Ljava/lang/String;)V", "removeLeadingZeros", "", "getRemoveLeadingZeros", "()Z", "setRemoveLeadingZeros", "(Z)V", "suffix", "getSuffix", "setSuffix", "build", "Lkotlin/text/HexFormat$NumberHexFormat;", "build$kotlin_stdlib", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
        public static final class kotlin.text.HexFormat.NumberHexFormat.Builder {
            private String prefix;
            private boolean removeLeadingZeros;
            private String suffix;

            public kotlin.text.HexFormat.NumberHexFormat.Builder() {
                this.prefix = "";
                this.suffix = "";
                this.removeLeadingZeros = NumberHexFormat.Companion.getDefault$kotlin_stdlib().getRemoveLeadingZeros();
            }

            public final NumberHexFormat build$kotlin_stdlib() {
                return new NumberHexFormat(this.prefix, this.suffix, this.removeLeadingZeros);
            }

            public final String getPrefix() {
                return this.prefix;
            }

            public final boolean getRemoveLeadingZeros() {
                return this.removeLeadingZeros;
            }

            public final String getSuffix() {
                return this.suffix;
            }

            public final void setPrefix(String s) {
                Intrinsics.checkNotNullParameter(s, "value");
                if(StringsKt.contains$default(s, '\n', false, 2, null) || StringsKt.contains$default(s, '\r', false, 2, null)) {
                    throw new IllegalArgumentException("LF and CR characters are prohibited in prefix, but was " + s);
                }
                this.prefix = s;
            }

            public final void setRemoveLeadingZeros(boolean z) {
                this.removeLeadingZeros = z;
            }

            public final void setSuffix(String s) {
                Intrinsics.checkNotNullParameter(s, "value");
                if(StringsKt.contains$default(s, '\n', false, 2, null) || StringsKt.contains$default(s, '\r', false, 2, null)) {
                    throw new IllegalArgumentException("LF and CR characters are prohibited in suffix, but was " + s);
                }
                this.suffix = s;
            }
        }

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001A\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/text/HexFormat$NumberHexFormat$Companion;", "", "()V", "Default", "Lkotlin/text/HexFormat$NumberHexFormat;", "getDefault$kotlin_stdlib", "()Lkotlin/text/HexFormat$NumberHexFormat;", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
        public static final class kotlin.text.HexFormat.NumberHexFormat.Companion {
            private kotlin.text.HexFormat.NumberHexFormat.Companion() {
            }

            public kotlin.text.HexFormat.NumberHexFormat.Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }

            public final NumberHexFormat getDefault$kotlin_stdlib() {
                return NumberHexFormat.Default;
            }
        }

        public static final kotlin.text.HexFormat.NumberHexFormat.Companion Companion;
        private static final NumberHexFormat Default;
        private final String prefix;
        private final boolean removeLeadingZeros;
        private final String suffix;

        static {
            NumberHexFormat.Companion = new kotlin.text.HexFormat.NumberHexFormat.Companion(null);
            NumberHexFormat.Default = new NumberHexFormat("", "", false);
        }

        public NumberHexFormat(String s, String s1, boolean z) {
            Intrinsics.checkNotNullParameter(s, "prefix");
            Intrinsics.checkNotNullParameter(s1, "suffix");
            super();
            this.prefix = s;
            this.suffix = s1;
            this.removeLeadingZeros = z;
        }

        public final StringBuilder appendOptionsTo$kotlin_stdlib(StringBuilder stringBuilder0, String s) {
            Intrinsics.checkNotNullParameter(stringBuilder0, "sb");
            Intrinsics.checkNotNullParameter(s, "indent");
            stringBuilder0.append(s);
            stringBuilder0.append("prefix = \"");
            stringBuilder0.append(this.prefix);
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "sb.append(indent).append…fix = \\\"\").append(prefix)");
            stringBuilder0.append("\",");
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
            stringBuilder0.append('\n');
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
            stringBuilder0.append(s);
            stringBuilder0.append("suffix = \"");
            stringBuilder0.append(this.suffix);
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "sb.append(indent).append…fix = \\\"\").append(suffix)");
            stringBuilder0.append("\",");
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
            stringBuilder0.append('\n');
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
            stringBuilder0.append(s);
            stringBuilder0.append("removeLeadingZeros = ");
            stringBuilder0.append(this.removeLeadingZeros);
            return stringBuilder0;
        }

        public final String getPrefix() {
            return this.prefix;
        }

        public final boolean getRemoveLeadingZeros() {
            return this.removeLeadingZeros;
        }

        public final String getSuffix() {
            return this.suffix;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder0 = new StringBuilder();
            stringBuilder0.append("NumberHexFormat(");
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\"NumberHexFormat(\")");
            stringBuilder0.append('\n');
            Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
            StringBuilder stringBuilder1 = this.appendOptionsTo$kotlin_stdlib(stringBuilder0, "    ");
            stringBuilder1.append('\n');
            Intrinsics.checkNotNullExpressionValue(stringBuilder1, "append(\'\\n\')");
            stringBuilder0.append(")");
            String s = stringBuilder0.toString();
            Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
            return s;
        }
    }

    public static final kotlin.text.HexFormat.Companion Companion;
    private static final HexFormat Default;
    private static final HexFormat UpperCase;
    private final BytesHexFormat bytes;
    private final NumberHexFormat number;
    private final boolean upperCase;

    static {
        HexFormat.Companion = new kotlin.text.HexFormat.Companion(null);
        HexFormat.Default = new HexFormat(false, BytesHexFormat.Companion.getDefault$kotlin_stdlib(), NumberHexFormat.Companion.getDefault$kotlin_stdlib());
        HexFormat.UpperCase = new HexFormat(true, BytesHexFormat.Companion.getDefault$kotlin_stdlib(), NumberHexFormat.Companion.getDefault$kotlin_stdlib());
    }

    public HexFormat(boolean z, BytesHexFormat hexFormat$BytesHexFormat0, NumberHexFormat hexFormat$NumberHexFormat0) {
        Intrinsics.checkNotNullParameter(hexFormat$BytesHexFormat0, "bytes");
        Intrinsics.checkNotNullParameter(hexFormat$NumberHexFormat0, "number");
        super();
        this.upperCase = z;
        this.bytes = hexFormat$BytesHexFormat0;
        this.number = hexFormat$NumberHexFormat0;
    }

    public final BytesHexFormat getBytes() {
        return this.bytes;
    }

    public final NumberHexFormat getNumber() {
        return this.number;
    }

    public final boolean getUpperCase() {
        return this.upperCase;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("HexFormat(");
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\"HexFormat(\")");
        stringBuilder0.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
        stringBuilder0.append("    upperCase = ");
        stringBuilder0.append(this.upperCase);
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\"    upperCase = \").append(upperCase)");
        stringBuilder0.append(",");
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(value)");
        stringBuilder0.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
        stringBuilder0.append("    bytes = BytesHexFormat(");
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\"    bytes = BytesHexFormat(\")");
        stringBuilder0.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
        StringBuilder stringBuilder1 = this.bytes.appendOptionsTo$kotlin_stdlib(stringBuilder0, "        ");
        stringBuilder1.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder1, "append(\'\\n\')");
        stringBuilder0.append("    ),");
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\"    ),\")");
        stringBuilder0.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
        stringBuilder0.append("    number = NumberHexFormat(");
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\"    number = NumberHexFormat(\")");
        stringBuilder0.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
        StringBuilder stringBuilder2 = this.number.appendOptionsTo$kotlin_stdlib(stringBuilder0, "        ");
        stringBuilder2.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder2, "append(\'\\n\')");
        stringBuilder0.append("    )");
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\"    )\")");
        stringBuilder0.append('\n');
        Intrinsics.checkNotNullExpressionValue(stringBuilder0, "append(\'\\n\')");
        stringBuilder0.append(")");
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }
}

