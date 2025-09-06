package kotlinx.coroutines.internal;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001R\u001E\u0010\u0002\u001A\b\u0012\u0002\b\u0003\u0018\u00010\u0003X¦\u000E¢\u0006\f\u001A\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001A\u00020\tX¦\u000E¢\u0006\f\u001A\u0004\b\n\u0010\u000B\"\u0004\b\f\u0010\r¨\u0006\u000E"}, d2 = {"Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "", "heap", "Lkotlinx/coroutines/internal/ThreadSafeHeap;", "getHeap", "()Lkotlinx/coroutines/internal/ThreadSafeHeap;", "setHeap", "(Lkotlinx/coroutines/internal/ThreadSafeHeap;)V", "index", "", "getIndex", "()I", "setIndex", "(I)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface ThreadSafeHeapNode {
    ThreadSafeHeap getHeap();

    int getIndex();

    void setHeap(ThreadSafeHeap arg1);

    void setIndex(int arg1);
}

