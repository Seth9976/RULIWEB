package kotlin.reflect;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001F\u0010\u0000\u001A\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\u00028À\u0002X\u0080\u0004¢\u0006\u0006\u001A\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"qualifiedOrSimpleName", "", "Lkotlin/reflect/KClass;", "getQualifiedOrSimpleName", "(Lkotlin/reflect/KClass;)Ljava/lang/String;", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class KClassesImplKt {
    public static final String getQualifiedOrSimpleName(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        return kClass0.getQualifiedName();
    }
}

