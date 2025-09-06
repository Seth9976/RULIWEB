package kotlin;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KProperty0;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KProperty;

@Metadata(d1 = {"\u00004\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A4\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0010\u0003\u001A\u0004\u0018\u00010\u00042\n\u0010\u0005\u001A\u0006\u0012\u0002\b\u00030\u0006H\u0087\n¢\u0006\u0002\u0010\u0007\u001A>\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\b\"\u0004\b\u0001\u0010\u0001*\u000E\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u00010\t2\u0006\u0010\u0003\u001A\u0002H\b2\n\u0010\u0005\u001A\u0006\u0012\u0002\b\u00030\u0006H\u0087\n¢\u0006\u0002\u0010\n\u001A<\u0010\u000B\u001A\u00020\f\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\r2\b\u0010\u0003\u001A\u0004\u0018\u00010\u00042\n\u0010\u0005\u001A\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u000E\u001A\u0002H\u0001H\u0087\n¢\u0006\u0002\u0010\u000F\u001AF\u0010\u000B\u001A\u00020\f\"\u0004\b\u0000\u0010\b\"\u0004\b\u0001\u0010\u0001*\u000E\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u00010\u00102\u0006\u0010\u0003\u001A\u0002H\b2\n\u0010\u0005\u001A\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u000E\u001A\u0002H\u0001H\u0087\n¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"getValue", "V", "Lkotlin/reflect/KProperty0;", "thisRef", "", "property", "Lkotlin/reflect/KProperty;", "(Lkotlin/reflect/KProperty0;Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "T", "Lkotlin/reflect/KProperty1;", "(Lkotlin/reflect/KProperty1;Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "setValue", "", "Lkotlin/reflect/KMutableProperty0;", "value", "(Lkotlin/reflect/KMutableProperty0;Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "Lkotlin/reflect/KMutableProperty1;", "(Lkotlin/reflect/KMutableProperty1;Ljava/lang/Object;Lkotlin/reflect/KProperty;Ljava/lang/Object;)V", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class PropertyReferenceDelegatesKt {
    private static final Object getValue(KProperty0 kProperty00, Object object0, KProperty kProperty0) {
        Intrinsics.checkNotNullParameter(kProperty00, "<this>");
        Intrinsics.checkNotNullParameter(kProperty0, "property");
        return kProperty00.get();
    }

    private static final Object getValue(KProperty1 kProperty10, Object object0, KProperty kProperty0) {
        Intrinsics.checkNotNullParameter(kProperty10, "<this>");
        Intrinsics.checkNotNullParameter(kProperty0, "property");
        return kProperty10.get(object0);
    }

    private static final void setValue(KMutableProperty0 kMutableProperty00, Object object0, KProperty kProperty0, Object object1) {
        Intrinsics.checkNotNullParameter(kMutableProperty00, "<this>");
        Intrinsics.checkNotNullParameter(kProperty0, "property");
        kMutableProperty00.set(object1);
    }

    private static final void setValue(KMutableProperty1 kMutableProperty10, Object object0, KProperty kProperty0, Object object1) {
        Intrinsics.checkNotNullParameter(kMutableProperty10, "<this>");
        Intrinsics.checkNotNullParameter(kProperty0, "property");
        kMutableProperty10.set(object0, object1);
    }
}

