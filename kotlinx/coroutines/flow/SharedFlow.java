package kotlinx.coroutines.flow;

import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J\u001F\u0010\u0007\u001A\u00020\b2\f\u0010\t\u001A\b\u0012\u0004\u0012\u00028\u00000\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000BR\u0018\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004X¦\u0004¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/flow/SharedFlow;", "T", "Lkotlinx/coroutines/flow/Flow;", "replayCache", "", "getReplayCache", "()Ljava/util/List;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface SharedFlow extends Flow {
    @Override  // kotlinx.coroutines.flow.Flow
    Object collect(FlowCollector arg1, Continuation arg2);

    List getReplayCache();
}

