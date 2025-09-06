package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002J,\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u00022\b\b\u0002\u0010\u0004\u001A\u00020\u00052\b\b\u0002\u0010\u0006\u001A\u00020\u00072\b\b\u0002\u0010\b\u001A\u00020\tH&¨\u0006\n"}, d2 = {"Lkotlinx/coroutines/flow/internal/FusibleFlow;", "T", "Lkotlinx/coroutines/flow/Flow;", "fuse", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface FusibleFlow extends Flow {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public static final class DefaultImpls {
        public static Flow fuse$default(FusibleFlow fusibleFlow0, CoroutineContext coroutineContext0, int v, BufferOverflow bufferOverflow0, int v1, Object object0) {
            if(object0 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fuse");
            }
            if((v1 & 1) != 0) {
                coroutineContext0 = EmptyCoroutineContext.INSTANCE;
            }
            if((v1 & 2) != 0) {
                v = -3;
            }
            if((v1 & 4) != 0) {
                bufferOverflow0 = BufferOverflow.SUSPEND;
            }
            return fusibleFlow0.fuse(coroutineContext0, v, bufferOverflow0);
        }
    }

    Flow fuse(CoroutineContext arg1, int arg2, BufferOverflow arg3);
}

