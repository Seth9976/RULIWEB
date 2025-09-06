package kotlinx.coroutines;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001A\u00020\u000BH\u0016R\u0014\u0010\u0002\u001A\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\u0005R\u0016\u0010\u0006\u001A\u0004\u0018\u00010\u00078VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lkotlinx/coroutines/Empty;", "Lkotlinx/coroutines/Incomplete;", "isActive", "", "(Z)V", "()Z", "list", "Lkotlinx/coroutines/NodeList;", "getList", "()Lkotlinx/coroutines/NodeList;", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class Empty implements Incomplete {
    private final boolean isActive;

    public Empty(boolean z) {
        this.isActive = z;
    }

    @Override  // kotlinx.coroutines.Incomplete
    public NodeList getList() {
        return null;
    }

    @Override  // kotlinx.coroutines.Incomplete
    public boolean isActive() {
        return this.isActive;
    }

    // 去混淆评级： 低(20)
    @Override
    public String toString() {
        return "Empty{" + (this.isActive() ? "Active" : "New") + '}';
    }
}

