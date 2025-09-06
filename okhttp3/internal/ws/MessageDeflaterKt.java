package okhttp3.internal.ws;

import kotlin.Metadata;
import okio.ByteString;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"EMPTY_DEFLATE_BLOCK", "Lokio/ByteString;", "LAST_OCTETS_COUNT_TO_REMOVE_AFTER_DEFLATION", "", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 0x30)
public final class MessageDeflaterKt {
    private static final ByteString EMPTY_DEFLATE_BLOCK = null;
    private static final int LAST_OCTETS_COUNT_TO_REMOVE_AFTER_DEFLATION = 4;

    static {
        MessageDeflaterKt.EMPTY_DEFLATE_BLOCK = ByteString.Companion.decodeHex("000000ffff");
    }

    public static final ByteString access$getEMPTY_DEFLATE_BLOCK$p() {
        return MessageDeflaterKt.EMPTY_DEFLATE_BLOCK;
    }
}

