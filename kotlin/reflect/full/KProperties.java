package kotlin.reflect.full;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KProperty2;
import kotlin.reflect.jvm.internal.KPropertyImpl;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001A\u0016\u0010\u0000\u001A\u0004\u0018\u00010\u0001*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0002H\u0007\u001A/\u0010\u0000\u001A\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0003*\u0010\u0012\u0004\u0012\u0002H\u0003\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00042\u0006\u0010\u0005\u001A\u0002H\u0003H\u0007¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"getExtensionDelegate", "", "Lkotlin/reflect/KProperty1;", "D", "Lkotlin/reflect/KProperty2;", "receiver", "(Lkotlin/reflect/KProperty2;Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-reflection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class KProperties {
    public static final Object getExtensionDelegate(KProperty1 kProperty10) {
        Intrinsics.checkNotNullParameter(kProperty10, "<this>");
        return kProperty10.getDelegate(KPropertyImpl.Companion.getEXTENSION_PROPERTY_DELEGATE());
    }

    public static final Object getExtensionDelegate(KProperty2 kProperty20, Object object0) {
        Intrinsics.checkNotNullParameter(kProperty20, "<this>");
        return kProperty20.getDelegate(object0, KPropertyImpl.Companion.getEXTENSION_PROPERTY_DELEGATE());
    }
}

