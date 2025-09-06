package androidx.work;

import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000\u001A\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001A\u0015\u0010\u0000\u001A\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\u0086\b\u001A\u001F\u0010\u0004\u001A\u00020\u0001*\u00020\u00012\u0010\b\u0001\u0010\u0005\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00070\u0006H\u0086\b¨\u0006\b"}, d2 = {"OneTimeWorkRequestBuilder", "Landroidx/work/OneTimeWorkRequest$Builder;", "W", "Landroidx/work/ListenableWorker;", "setInputMerger", "inputMerger", "Lkotlin/reflect/KClass;", "Landroidx/work/InputMerger;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class OneTimeWorkRequestKt {
    public static final Builder OneTimeWorkRequestBuilder() {
        Intrinsics.reifiedOperationMarker(4, "W");
        return new Builder(ListenableWorker.class);
    }

    public static final Builder setInputMerger(Builder oneTimeWorkRequest$Builder0, KClass kClass0) {
        Intrinsics.checkNotNullParameter(oneTimeWorkRequest$Builder0, "<this>");
        Intrinsics.checkNotNullParameter(kClass0, "inputMerger");
        return oneTimeWorkRequest$Builder0.setInputMerger(JvmClassMappingKt.getJavaClass(kClass0));
    }
}

