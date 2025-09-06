package kotlin.reflect.jvm.internal.impl.renderer;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

public abstract enum RenderingFormat {
    static final class HTML extends RenderingFormat {
        HTML(String s, int v) {
            super(null);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.renderer.RenderingFormat
        public String escape(String s) {
            Intrinsics.checkNotNullParameter(s, "string");
            return StringsKt.replace$default(StringsKt.replace$default(s, "<", "&lt;", false, 4, null), ">", "&gt;", false, 4, null);
        }
    }

    static final class PLAIN extends RenderingFormat {
        PLAIN(String s, int v) {
            super(null);
        }

        @Override  // kotlin.reflect.jvm.internal.impl.renderer.RenderingFormat
        public String escape(String s) {
            Intrinsics.checkNotNullParameter(s, "string");
            return s;
        }
    }

    PLAIN /* 警告！未生成枚举子类：kotlin.reflect.jvm.internal.impl.renderer.RenderingFormat$PLAIN */,
    HTML /* 警告！未生成枚举子类：kotlin.reflect.jvm.internal.impl.renderer.RenderingFormat$HTML */;

    private static final RenderingFormat[] $values() [...] // Inlined contents

    private RenderingFormat() {
    }

    public RenderingFormat(DefaultConstructorMarker defaultConstructorMarker0) {
        this();
    }

    public abstract String escape(String arg1);
}

