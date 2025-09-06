package kotlinx.coroutines.stream;

import java.util.stream.Stream;
import kotlin.Metadata;
import kotlinx.coroutines.flow.Flow;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\u001C\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003¨\u0006\u0004"}, d2 = {"consumeAsFlow", "Lkotlinx/coroutines/flow/Flow;", "T", "Ljava/util/stream/Stream;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class StreamKt {
    public static final Flow consumeAsFlow(Stream stream0) {
        return new StreamFlow(stream0);
    }
}

