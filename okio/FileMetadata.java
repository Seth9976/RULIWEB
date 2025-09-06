package okio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KClasses;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000E\n\u0000\u0018\u00002\u00020\u0001Bo\u0012\b\b\u0002\u0010\u0002\u001A\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001A\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001A\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\n\u001A\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u000B\u001A\u0004\u0018\u00010\b\u0012\u0018\b\u0002\u0010\f\u001A\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000E\u0012\u0004\u0012\u00020\u00010\r\u00A2\u0006\u0002\u0010\u000FJu\u0010\u001B\u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u00032\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001A\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001A\u0004\u0018\u00010\b2\n\b\u0002\u0010\n\u001A\u0004\u0018\u00010\b2\n\b\u0002\u0010\u000B\u001A\u0004\u0018\u00010\b2\u0018\b\u0002\u0010\f\u001A\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000E\u0012\u0004\u0012\u00020\u00010\r\u00A2\u0006\u0002\u0010\u001CJ\'\u0010\u001D\u001A\u0004\u0018\u0001H\u001E\"\b\b\u0000\u0010\u001E*\u00020\u00012\u000E\u0010\u001F\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u001E0\u000E\u00A2\u0006\u0002\u0010 J\b\u0010!\u001A\u00020\"H\u0016R\u0015\u0010\t\u001A\u0004\u0018\u00010\b\u00A2\u0006\n\n\u0002\u0010\u0012\u001A\u0004\b\u0010\u0010\u0011R!\u0010\f\u001A\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000E\u0012\u0004\u0012\u00020\u00010\r\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001A\u00020\u0003\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0004\u0010\u0015R\u0011\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\u0015R\u0015\u0010\u000B\u001A\u0004\u0018\u00010\b\u00A2\u0006\n\n\u0002\u0010\u0012\u001A\u0004\b\u0016\u0010\u0011R\u0015\u0010\n\u001A\u0004\u0018\u00010\b\u00A2\u0006\n\n\u0002\u0010\u0012\u001A\u0004\b\u0017\u0010\u0011R\u0015\u0010\u0007\u001A\u0004\u0018\u00010\b\u00A2\u0006\n\n\u0002\u0010\u0012\u001A\u0004\b\u0018\u0010\u0011R\u0013\u0010\u0005\u001A\u0004\u0018\u00010\u0006\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0019\u0010\u001A\u00A8\u0006#"}, d2 = {"Lokio/FileMetadata;", "", "isRegularFile", "", "isDirectory", "symlinkTarget", "Lokio/Path;", "size", "", "createdAtMillis", "lastModifiedAtMillis", "lastAccessedAtMillis", "extras", "", "Lkotlin/reflect/KClass;", "(ZZLokio/Path;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/util/Map;)V", "getCreatedAtMillis", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getExtras", "()Ljava/util/Map;", "()Z", "getLastAccessedAtMillis", "getLastModifiedAtMillis", "getSize", "getSymlinkTarget", "()Lokio/Path;", "copy", "(ZZLokio/Path;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Long;Ljava/util/Map;)Lokio/FileMetadata;", "extra", "T", "type", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "toString", "", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class FileMetadata {
    private final Long createdAtMillis;
    private final Map extras;
    private final boolean isDirectory;
    private final boolean isRegularFile;
    private final Long lastAccessedAtMillis;
    private final Long lastModifiedAtMillis;
    private final Long size;
    private final Path symlinkTarget;

    public FileMetadata() {
        this(false, false, null, null, null, null, null, null, 0xFF, null);
    }

    public FileMetadata(boolean z, boolean z1, Path path0, Long long0, Long long1, Long long2, Long long3, Map map0) {
        Intrinsics.checkNotNullParameter(map0, "extras");
        super();
        this.isRegularFile = z;
        this.isDirectory = z1;
        this.symlinkTarget = path0;
        this.size = long0;
        this.createdAtMillis = long1;
        this.lastModifiedAtMillis = long2;
        this.lastAccessedAtMillis = long3;
        this.extras = MapsKt.toMap(map0);
    }

    public FileMetadata(boolean z, boolean z1, Path path0, Long long0, Long long1, Long long2, Long long3, Map map0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            z = false;
        }
        if((v & 2) != 0) {
            z1 = false;
        }
        if((v & 4) != 0) {
            path0 = null;
        }
        if((v & 8) != 0) {
            long0 = null;
        }
        if((v & 16) != 0) {
            long1 = null;
        }
        if((v & 0x20) != 0) {
            long2 = null;
        }
        if((v & 0x40) != 0) {
            long3 = null;
        }
        if((v & 0x80) != 0) {
            map0 = MapsKt.emptyMap();
        }
        this(z, z1, path0, long0, long1, long2, long3, map0);
    }

    public final FileMetadata copy(boolean z, boolean z1, Path path0, Long long0, Long long1, Long long2, Long long3, Map map0) {
        Intrinsics.checkNotNullParameter(map0, "extras");
        return new FileMetadata(z, z1, path0, long0, long1, long2, long3, map0);
    }

    public static FileMetadata copy$default(FileMetadata fileMetadata0, boolean z, boolean z1, Path path0, Long long0, Long long1, Long long2, Long long3, Map map0, int v, Object object0) {
        if((v & 1) != 0) {
            z = fileMetadata0.isRegularFile;
        }
        if((v & 2) != 0) {
            z1 = fileMetadata0.isDirectory;
        }
        if((v & 4) != 0) {
            path0 = fileMetadata0.symlinkTarget;
        }
        if((v & 8) != 0) {
            long0 = fileMetadata0.size;
        }
        if((v & 16) != 0) {
            long1 = fileMetadata0.createdAtMillis;
        }
        if((v & 0x20) != 0) {
            long2 = fileMetadata0.lastModifiedAtMillis;
        }
        if((v & 0x40) != 0) {
            long3 = fileMetadata0.lastAccessedAtMillis;
        }
        if((v & 0x80) != 0) {
            map0 = fileMetadata0.extras;
        }
        return fileMetadata0.copy(z, z1, path0, long0, long1, long2, long3, map0);
    }

    public final Object extra(KClass kClass0) {
        Intrinsics.checkNotNullParameter(kClass0, "type");
        Object object0 = this.extras.get(kClass0);
        return object0 == null ? null : KClasses.cast(kClass0, object0);
    }

    public final Long getCreatedAtMillis() {
        return this.createdAtMillis;
    }

    public final Map getExtras() {
        return this.extras;
    }

    public final Long getLastAccessedAtMillis() {
        return this.lastAccessedAtMillis;
    }

    public final Long getLastModifiedAtMillis() {
        return this.lastModifiedAtMillis;
    }

    public final Long getSize() {
        return this.size;
    }

    public final Path getSymlinkTarget() {
        return this.symlinkTarget;
    }

    public final boolean isDirectory() {
        return this.isDirectory;
    }

    public final boolean isRegularFile() {
        return this.isRegularFile;
    }

    @Override
    public String toString() {
        List list0 = new ArrayList();
        if(this.isRegularFile) {
            list0.add("isRegularFile");
        }
        if(this.isDirectory) {
            list0.add("isDirectory");
        }
        if(this.size != null) {
            list0.add("byteCount=" + this.size);
        }
        if(this.createdAtMillis != null) {
            list0.add("createdAt=" + this.createdAtMillis);
        }
        if(this.lastModifiedAtMillis != null) {
            list0.add("lastModifiedAt=" + this.lastModifiedAtMillis);
        }
        if(this.lastAccessedAtMillis != null) {
            list0.add("lastAccessedAt=" + this.lastAccessedAtMillis);
        }
        if(!this.extras.isEmpty()) {
            list0.add("extras=" + this.extras);
        }
        return CollectionsKt.joinToString$default(list0, ", ", "FileMetadata(", ")", 0, null, null, 56, null);
    }
}

