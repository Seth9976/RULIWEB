package kotlin.reflect.jvm;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.jvm.internal.KClassImpl;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0019\u0010\u0000\u001A\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028F¢\u0006\u0006\u001A\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"jvmName", "", "Lkotlin/reflect/KClass;", "getJvmName", "(Lkotlin/reflect/KClass;)Ljava/lang/String;", "kotlin-reflection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class KClassesJvm {
    public static final String getJvmName(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "<this>");
        String s = ((KClassImpl)kClass0).getJClass().getName();
        Intrinsics.checkNotNullExpressionValue(s, "this as KClassImpl).jClass.name");
        return s;
    }
}

