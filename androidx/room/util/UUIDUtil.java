package androidx.room.util;

import java.nio.ByteBuffer;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\u001A\u000E\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003\u001A\u000E\u0010\u0004\u001A\u00020\u00032\u0006\u0010\u0005\u001A\u00020\u0001¨\u0006\u0006"}, d2 = {"convertByteToUUID", "Ljava/util/UUID;", "bytes", "", "convertUUIDToByte", "uuid", "room-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class UUIDUtil {
    public static final UUID convertByteToUUID(byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "bytes");
        ByteBuffer byteBuffer0 = ByteBuffer.wrap(arr_b);
        return new UUID(byteBuffer0.getLong(), byteBuffer0.getLong());
    }

    public static final byte[] convertUUIDToByte(UUID uUID0) {
        Intrinsics.checkNotNullParameter(uUID0, "uuid");
        ByteBuffer byteBuffer0 = ByteBuffer.wrap(new byte[16]);
        byteBuffer0.putLong(uUID0.getMostSignificantBits());
        byteBuffer0.putLong(uUID0.getLeastSignificantBits());
        byte[] arr_b = byteBuffer0.array();
        Intrinsics.checkNotNullExpressionValue(arr_b, "buffer.array()");
        return arr_b;
    }
}

