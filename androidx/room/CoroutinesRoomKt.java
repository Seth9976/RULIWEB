package androidx.room;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorsKt;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001A\f\u0010\u0005\u001A\u00020\u0001*\u00020\u0002H\u0007\"\u0018\u0010\u0000\u001A\u00020\u0001*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001A\u0004\b\u0003\u0010\u0004¨\u0006\u0006"}, d2 = {"transactionDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "Landroidx/room/RoomDatabase;", "getTransactionDispatcher", "(Landroidx/room/RoomDatabase;)Lkotlinx/coroutines/CoroutineDispatcher;", "getQueryDispatcher", "room-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class CoroutinesRoomKt {
    public static final CoroutineDispatcher getQueryDispatcher(RoomDatabase roomDatabase0) {
        Map map0 = roomDatabase0.getBackingFieldMap();
        CoroutineDispatcher coroutineDispatcher0 = map0.get("QueryDispatcher");
        if(coroutineDispatcher0 == null) {
            coroutineDispatcher0 = ExecutorsKt.from(roomDatabase0.getQueryExecutor());
            map0.put("QueryDispatcher", coroutineDispatcher0);
        }
        Intrinsics.checkNotNull(coroutineDispatcher0, "null cannot be cast to non-null type kotlinx.coroutines.CoroutineDispatcher");
        return coroutineDispatcher0;
    }

    public static final CoroutineDispatcher getTransactionDispatcher(RoomDatabase roomDatabase0) {
        Map map0 = roomDatabase0.getBackingFieldMap();
        CoroutineDispatcher coroutineDispatcher0 = map0.get("TransactionDispatcher");
        if(coroutineDispatcher0 == null) {
            coroutineDispatcher0 = ExecutorsKt.from(roomDatabase0.getTransactionExecutor());
            map0.put("TransactionDispatcher", coroutineDispatcher0);
        }
        Intrinsics.checkNotNull(coroutineDispatcher0, "null cannot be cast to non-null type kotlinx.coroutines.CoroutineDispatcher");
        return coroutineDispatcher0;
    }
}

