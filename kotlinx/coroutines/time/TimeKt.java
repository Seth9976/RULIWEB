package kotlinx.coroutines.time;

import java.time.Duration;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.TimeoutKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.selects.OnTimeoutKt;
import kotlinx.coroutines.selects.SelectBuilder;
import retrofit2.Platform..ExternalSyntheticApiModelOutline0;

@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001A\u0019\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u001AU\u0010\u0005\u001A\u0002H\u0006\"\u0004\b\u0000\u0010\u00062\u0006\u0010\u0002\u001A\u00020\u00032\'\u0010\u0007\u001A#\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\n\u0012\u0006\u0012\u0004\u0018\u00010\u000B0\b¢\u0006\u0002\b\fH\u0086@ø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001¢\u0006\u0002\u0010\r\u001AJ\u0010\u000E\u001A\u0004\u0018\u0001H\u0006\"\u0004\b\u0000\u0010\u00062\u0006\u0010\u0002\u001A\u00020\u00032\'\u0010\u0007\u001A#\b\u0001\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\n\u0012\u0006\u0012\u0004\u0018\u00010\u000B0\b¢\u0006\u0002\b\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\r\u001A\f\u0010\u000F\u001A\u00020\u0010*\u00020\u0003H\u0002\u001A&\u0010\u0011\u001A\b\u0012\u0004\u0012\u0002H\u00060\u0012\"\u0004\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\u00122\u0006\u0010\u0013\u001A\u00020\u0003H\u0007\u001AD\u0010\u0014\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0015*\b\u0012\u0004\u0012\u0002H\u00150\u00162\u0006\u0010\u0002\u001A\u00020\u00032\u001C\u0010\u0007\u001A\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00150\n\u0012\u0006\u0012\u0004\u0018\u00010\u000B0\u0017ø\u0001\u0000¢\u0006\u0002\u0010\u0018\u001A&\u0010\u0019\u001A\b\u0012\u0004\u0012\u0002H\u00060\u0012\"\u0004\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\u00122\u0006\u0010\u001A\u001A\u00020\u0003H\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001B"}, d2 = {"delay", "", "duration", "Ljava/time/Duration;", "(Ljava/time/Duration;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withTimeout", "T", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Ljava/time/Duration;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withTimeoutOrNull", "coerceToMillis", "", "debounce", "Lkotlinx/coroutines/flow/Flow;", "timeout", "onTimeout", "R", "Lkotlinx/coroutines/selects/SelectBuilder;", "Lkotlin/Function1;", "(Lkotlinx/coroutines/selects/SelectBuilder;Ljava/time/Duration;Lkotlin/jvm/functions/Function1;)V", "sample", "period", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class TimeKt {
    private static final long coerceToMillis(Duration duration0) {
        if(duration0.compareTo(Duration.ZERO) <= 0) {
            return 0L;
        }
        if(duration0.compareTo(Platform..ExternalSyntheticApiModelOutline0.m(Platform..ExternalSyntheticApiModelOutline0.m())) <= 0) {
            return 1L;
        }
        return duration0.getSeconds() < 0x20C49BA5E353F7L || duration0.getSeconds() == 0x20C49BA5E353F7L && duration0.getNano() < 807000000 ? Platform..ExternalSyntheticApiModelOutline0.m(duration0) : 0x7FFFFFFFFFFFFFFFL;
    }

    public static final Flow debounce(Flow flow0, Duration duration0) {
        return FlowKt.debounce(flow0, TimeKt.coerceToMillis(duration0));
    }

    public static final Object delay(Duration duration0, Continuation continuation0) {
        Object object0 = DelayKt.delay(TimeKt.coerceToMillis(duration0), continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    public static final void onTimeout(SelectBuilder selectBuilder0, Duration duration0, Function1 function10) {
        OnTimeoutKt.onTimeout(selectBuilder0, TimeKt.coerceToMillis(duration0), function10);
    }

    public static final Flow sample(Flow flow0, Duration duration0) {
        return FlowKt.sample(flow0, TimeKt.coerceToMillis(duration0));
    }

    public static final Object withTimeout(Duration duration0, Function2 function20, Continuation continuation0) {
        return TimeoutKt.withTimeout(TimeKt.coerceToMillis(duration0), function20, continuation0);
    }

    public static final Object withTimeoutOrNull(Duration duration0, Function2 function20, Continuation continuation0) {
        return TimeoutKt.withTimeoutOrNull(TimeKt.coerceToMillis(duration0), function20, continuation0);
    }
}

