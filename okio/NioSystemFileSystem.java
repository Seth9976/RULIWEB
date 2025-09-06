package okio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileTime;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Platform..ExternalSyntheticApiModelOutline0;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u0006H\u0016J\u0018\u0010\b\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u0006H\u0016J\u0012\u0010\t\u001A\u0004\u0018\u00010\n2\u0006\u0010\u000B\u001A\u00020\u0006H\u0016J\b\u0010\f\u001A\u00020\rH\u0016J\u0013\u0010\u000E\u001A\u0004\u0018\u00010\u000F*\u00020\u0010H\u0002¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Lokio/NioSystemFileSystem;", "Lokio/JvmSystemFileSystem;", "()V", "atomicMove", "", "source", "Lokio/Path;", "target", "createSymlink", "metadataOrNull", "Lokio/FileMetadata;", "path", "toString", "", "zeroToNull", "", "Ljava/nio/file/attribute/FileTime;", "(Ljava/nio/file/attribute/FileTime;)Ljava/lang/Long;", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class NioSystemFileSystem extends JvmSystemFileSystem {
    @Override  // okio.JvmSystemFileSystem
    public void atomicMove(Path path0, Path path1) {
        Intrinsics.checkNotNullParameter(path0, "source");
        Intrinsics.checkNotNullParameter(path1, "target");
        try {
            Files.move(path0.toNioPath(), path1.toNioPath(), new CopyOption[]{Platform..ExternalSyntheticApiModelOutline0.m(), StandardCopyOption.REPLACE_EXISTING});
        }
        catch(NoSuchFileException noSuchFileException0) {
            throw new FileNotFoundException(Platform..ExternalSyntheticApiModelOutline0.m(noSuchFileException0));
        }
        catch(UnsupportedOperationException unused_ex) {
            throw new IOException("atomic move not supported");
        }
    }

    @Override  // okio.JvmSystemFileSystem
    public void createSymlink(Path path0, Path path1) {
        Intrinsics.checkNotNullParameter(path0, "source");
        Intrinsics.checkNotNullParameter(path1, "target");
        Files.createSymbolicLink(path0.toNioPath(), path1.toNioPath(), new FileAttribute[0]);
    }

    @Override  // okio.JvmSystemFileSystem
    public FileMetadata metadataOrNull(Path path0) {
        BasicFileAttributes basicFileAttributes0;
        Intrinsics.checkNotNullParameter(path0, "path");
        java.nio.file.Path path1 = path0.toNioPath();
        Long long0 = null;
        try {
            basicFileAttributes0 = Files.readAttributes(path1, BasicFileAttributes.class, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
        }
        catch(NoSuchFileException | FileSystemException unused_ex) {
            return null;
        }
        java.nio.file.Path path2 = Platform..ExternalSyntheticApiModelOutline0.m(basicFileAttributes0) ? Files.readSymbolicLink(path1) : null;
        boolean z = Platform..ExternalSyntheticApiModelOutline0.m$1(basicFileAttributes0);
        boolean z1 = basicFileAttributes0.isDirectory();
        Path path3 = path2 == null ? null : Companion.get$default(Path.Companion, path2, false, 1, null);
        Long long1 = Platform..ExternalSyntheticApiModelOutline0.m(basicFileAttributes0);
        FileTime fileTime0 = Platform..ExternalSyntheticApiModelOutline0.m(basicFileAttributes0);
        Long long2 = fileTime0 == null ? null : this.zeroToNull(fileTime0);
        FileTime fileTime1 = Platform..ExternalSyntheticApiModelOutline0.m$1(basicFileAttributes0);
        Long long3 = fileTime1 == null ? null : this.zeroToNull(fileTime1);
        FileTime fileTime2 = Platform..ExternalSyntheticApiModelOutline0.m$2(basicFileAttributes0);
        if(fileTime2 != null) {
            long0 = this.zeroToNull(fileTime2);
        }
        return new FileMetadata(z, z1, path3, long1, long2, long3, long0, null, 0x80, null);
    }

    @Override  // okio.JvmSystemFileSystem
    public String toString() {
        return "NioSystemFileSystem";
    }

    private final Long zeroToNull(FileTime fileTime0) {
        Long long0 = Platform..ExternalSyntheticApiModelOutline0.m(fileTime0);
        return long0.longValue() == 0L ? null : long0;
    }
}

