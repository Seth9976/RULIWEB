package okio.internal;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u001D\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0004\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001A\u00020\u0006¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000BR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\t¨\u0006\r"}, d2 = {"Lokio/internal/EocdRecord;", "", "entryCount", "", "centralDirectoryOffset", "commentByteCount", "", "(JJI)V", "getCentralDirectoryOffset", "()J", "getCommentByteCount", "()I", "getEntryCount", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
final class EocdRecord {
    private final long centralDirectoryOffset;
    private final int commentByteCount;
    private final long entryCount;

    public EocdRecord(long v, long v1, int v2) {
        this.entryCount = v;
        this.centralDirectoryOffset = v1;
        this.commentByteCount = v2;
    }

    public final long getCentralDirectoryOffset() {
        return this.centralDirectoryOffset;
    }

    public final int getCommentByteCount() {
        return this.commentByteCount;
    }

    public final long getEntryCount() {
        return this.entryCount;
    }
}

