package kotlin.io;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001A\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"Lkotlin/io/AccessDeniedException;", "Lkotlin/io/FileSystemException;", "file", "Ljava/io/File;", "other", "reason", "", "(Ljava/io/File;Ljava/io/File;Ljava/lang/String;)V", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class AccessDeniedException extends FileSystemException {
    public AccessDeniedException(File file0, File file1, String s) {
        Intrinsics.checkNotNullParameter(file0, "file");
        super(file0, file1, s);
    }

    public AccessDeniedException(File file0, File file1, String s, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            file1 = null;
        }
        if((v & 4) != 0) {
            s = null;
        }
        this(file0, file1, s);
    }
}

