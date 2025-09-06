package okio.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.Path;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0010\b\u0000\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001A\u00020\u0007\u0012\b\b\u0002\u0010\b\u001A\u00020\t\u0012\b\b\u0002\u0010\n\u001A\u00020\t\u0012\b\b\u0002\u0010\u000B\u001A\u00020\t\u0012\b\b\u0002\u0010\f\u001A\u00020\r\u0012\n\b\u0002\u0010\u000E\u001A\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\u000F\u001A\u00020\t¢\u0006\u0002\u0010\u0010R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\u00030\u0014¢\u0006\b\n\u0000\u001A\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001A\u00020\u0007¢\u0006\b\n\u0000\u001A\u0004\b\u0017\u0010\u0018R\u0011\u0010\n\u001A\u00020\t¢\u0006\b\n\u0000\u001A\u0004\b\u0019\u0010\u001AR\u0011\u0010\f\u001A\u00020\r¢\u0006\b\n\u0000\u001A\u0004\b\u001B\u0010\u001CR\u0011\u0010\b\u001A\u00020\t¢\u0006\b\n\u0000\u001A\u0004\b\u001D\u0010\u001AR\u0011\u0010\u0004\u001A\u00020\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u0004\u0010\u001ER\u0015\u0010\u000E\u001A\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010!\u001A\u0004\b\u001F\u0010 R\u0011\u0010\u000F\u001A\u00020\t¢\u0006\b\n\u0000\u001A\u0004\b\"\u0010\u001AR\u0011\u0010\u000B\u001A\u00020\t¢\u0006\b\n\u0000\u001A\u0004\b#\u0010\u001A¨\u0006$"}, d2 = {"Lokio/internal/ZipEntry;", "", "canonicalPath", "Lokio/Path;", "isDirectory", "", "comment", "", "crc", "", "compressedSize", "size", "compressionMethod", "", "lastModifiedAtMillis", "offset", "(Lokio/Path;ZLjava/lang/String;JJJILjava/lang/Long;J)V", "getCanonicalPath", "()Lokio/Path;", "children", "", "getChildren", "()Ljava/util/List;", "getComment", "()Ljava/lang/String;", "getCompressedSize", "()J", "getCompressionMethod", "()I", "getCrc", "()Z", "getLastModifiedAtMillis", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getOffset", "getSize", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class ZipEntry {
    private final Path canonicalPath;
    private final List children;
    private final String comment;
    private final long compressedSize;
    private final int compressionMethod;
    private final long crc;
    private final boolean isDirectory;
    private final Long lastModifiedAtMillis;
    private final long offset;
    private final long size;

    public ZipEntry(Path path0, boolean z, String s, long v, long v1, long v2, int v3, Long long0, long v4) {
        Intrinsics.checkNotNullParameter(path0, "canonicalPath");
        Intrinsics.checkNotNullParameter(s, "comment");
        super();
        this.canonicalPath = path0;
        this.isDirectory = z;
        this.comment = s;
        this.crc = v;
        this.compressedSize = v1;
        this.size = v2;
        this.compressionMethod = v3;
        this.lastModifiedAtMillis = long0;
        this.offset = v4;
        this.children = new ArrayList();
    }

    public ZipEntry(Path path0, boolean z, String s, long v, long v1, long v2, int v3, Long long0, long v4, int v5, DefaultConstructorMarker defaultConstructorMarker0) {
        this(path0, ((v5 & 2) == 0 ? z : false), ((v5 & 4) == 0 ? s : ""), ((v5 & 8) == 0 ? v : -1L), ((v5 & 16) == 0 ? v1 : -1L), ((v5 & 0x20) == 0 ? v2 : -1L), ((v5 & 0x40) == 0 ? v3 : -1), ((v5 & 0x80) == 0 ? long0 : null), ((v5 & 0x100) == 0 ? v4 : -1L));
    }

    public final Path getCanonicalPath() {
        return this.canonicalPath;
    }

    public final List getChildren() {
        return this.children;
    }

    public final String getComment() {
        return this.comment;
    }

    public final long getCompressedSize() {
        return this.compressedSize;
    }

    public final int getCompressionMethod() {
        return this.compressionMethod;
    }

    public final long getCrc() {
        return this.crc;
    }

    public final Long getLastModifiedAtMillis() {
        return this.lastModifiedAtMillis;
    }

    public final long getOffset() {
        return this.offset;
    }

    public final long getSize() {
        return this.size;
    }

    public final boolean isDirectory() {
        return this.isDirectory;
    }
}

