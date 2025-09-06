package kotlin.coroutines.jvm.internal;

import java.lang.reflect.Field;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u0018\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00012\u0006\u0010\u0005\u001A\u00020\u0001H\u0002\u001A\u000E\u0010\u0006\u001A\u0004\u0018\u00010\u0007*\u00020\bH\u0002\u001A\f\u0010\t\u001A\u00020\u0001*\u00020\bH\u0002\u001A\u0019\u0010\n\u001A\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000B*\u00020\bH\u0001¢\u0006\u0002\u0010\r\u001A\u0013\u0010\u000E\u001A\u0004\u0018\u00010\u000F*\u00020\bH\u0001¢\u0006\u0002\b\u0010\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"COROUTINES_DEBUG_METADATA_VERSION", "", "checkDebugMetadataVersion", "", "expected", "actual", "getDebugMetadataAnnotation", "Lkotlin/coroutines/jvm/internal/DebugMetadata;", "Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "getLabel", "getSpilledVariableFieldMapping", "", "", "(Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;)[Ljava/lang/String;", "getStackTraceElementImpl", "Ljava/lang/StackTraceElement;", "getStackTraceElement", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class DebugMetadataKt {
    private static final int COROUTINES_DEBUG_METADATA_VERSION = 1;

    private static final void checkDebugMetadataVersion(int v, int v1) {
        if(v1 > v) {
            throw new IllegalStateException(("Debug metadata version mismatch. Expected: " + v + ", got " + v1 + ". Please update the Kotlin standard library.").toString());
        }
    }

    private static final DebugMetadata getDebugMetadataAnnotation(BaseContinuationImpl baseContinuationImpl0) {
        return (DebugMetadata)baseContinuationImpl0.getClass().getAnnotation(DebugMetadata.class);
    }

    private static final int getLabel(BaseContinuationImpl baseContinuationImpl0) {
        try {
            Field field0 = baseContinuationImpl0.getClass().getDeclaredField("label");
            field0.setAccessible(true);
            Object object0 = field0.get(baseContinuationImpl0);
            Integer integer0 = object0 instanceof Integer ? ((Integer)object0) : null;
            return integer0 == null ? -1 : ((int)integer0) - 1;
        }
        catch(Exception unused_ex) {
            return -1;
        }
    }

    public static final String[] getSpilledVariableFieldMapping(BaseContinuationImpl baseContinuationImpl0) {
        Intrinsics.checkNotNullParameter(baseContinuationImpl0, "<this>");
        DebugMetadata debugMetadata0 = DebugMetadataKt.getDebugMetadataAnnotation(baseContinuationImpl0);
        if(debugMetadata0 == null) {
            return null;
        }
        DebugMetadataKt.checkDebugMetadataVersion(1, debugMetadata0.v());
        ArrayList arrayList0 = new ArrayList();
        int v = DebugMetadataKt.getLabel(baseContinuationImpl0);
        int[] arr_v = debugMetadata0.i();
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            if(arr_v[v1] == v) {
                arrayList0.add(debugMetadata0.s()[v1]);
                arrayList0.add(debugMetadata0.n()[v1]);
            }
        }
        return (String[])arrayList0.toArray(new String[0]);
    }

    public static final StackTraceElement getStackTraceElement(BaseContinuationImpl baseContinuationImpl0) {
        Intrinsics.checkNotNullParameter(baseContinuationImpl0, "<this>");
        DebugMetadata debugMetadata0 = DebugMetadataKt.getDebugMetadataAnnotation(baseContinuationImpl0);
        if(debugMetadata0 == null) {
            return null;
        }
        DebugMetadataKt.checkDebugMetadataVersion(1, debugMetadata0.v());
        int v = DebugMetadataKt.getLabel(baseContinuationImpl0);
        int v1 = v >= 0 ? debugMetadata0.l()[v] : -1;
        String s = ModuleNameRetriever.INSTANCE.getModuleName(baseContinuationImpl0);
        return s == null ? new StackTraceElement(debugMetadata0.c(), debugMetadata0.m(), debugMetadata0.f(), v1) : new StackTraceElement(s + '/' + debugMetadata0.c(), debugMetadata0.m(), debugMetadata0.f(), v1);
    }
}

