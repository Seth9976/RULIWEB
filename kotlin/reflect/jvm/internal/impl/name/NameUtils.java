package kotlin.reflect.jvm.internal.impl.name;

import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

public final class NameUtils {
    public static final NameUtils INSTANCE;
    private static final Regex SANITIZE_AS_JAVA_INVALID_CHARACTERS;

    static {
        NameUtils.INSTANCE = new NameUtils();
        NameUtils.SANITIZE_AS_JAVA_INVALID_CHARACTERS = new Regex("[^\\p{L}\\p{Digit}]");
    }

    @JvmStatic
    public static final Name contextReceiverName(int v) {
        Name name0 = Name.identifier(("_context_receiver_" + v));
        Intrinsics.checkNotNullExpressionValue(name0, "identifier(\"_context_receiver_$index\")");
        return name0;
    }

    @JvmStatic
    public static final String sanitizeAsJavaIdentifier(String s) {
        Intrinsics.checkNotNullParameter(s, "name");
        return NameUtils.SANITIZE_AS_JAVA_INVALID_CHARACTERS.replace(s, "_");
    }
}

