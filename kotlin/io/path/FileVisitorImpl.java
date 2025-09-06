package kotlin.io.path;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000B\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001Bw\u0012\u001A\u0010\u0003\u001A\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004\u0012\u001A\u0010\u0007\u001A\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004\u0012\u001A\u0010\b\u001A\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004\u0012\u001C\u0010\n\u001A\u0018\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004¢\u0006\u0002\u0010\u000BJ\u001A\u0010\f\u001A\u00020\u00062\u0006\u0010\r\u001A\u00020\u00022\b\u0010\u000E\u001A\u0004\u0018\u00010\tH\u0016J\u0018\u0010\u000F\u001A\u00020\u00062\u0006\u0010\r\u001A\u00020\u00022\u0006\u0010\u0010\u001A\u00020\u0005H\u0016J\u0018\u0010\u0011\u001A\u00020\u00062\u0006\u0010\u0012\u001A\u00020\u00022\u0006\u0010\u0010\u001A\u00020\u0005H\u0016J\u0018\u0010\u0013\u001A\u00020\u00062\u0006\u0010\u0012\u001A\u00020\u00022\u0006\u0010\u000E\u001A\u00020\tH\u0016R$\u0010\n\u001A\u0018\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0003\u001A\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0007\u001A\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\b\u001A\u0016\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lkotlin/io/path/FileVisitorImpl;", "Ljava/nio/file/SimpleFileVisitor;", "Ljava/nio/file/Path;", "onPreVisitDirectory", "Lkotlin/Function2;", "Ljava/nio/file/attribute/BasicFileAttributes;", "Ljava/nio/file/FileVisitResult;", "onVisitFile", "onVisitFileFailed", "Ljava/io/IOException;", "onPostVisitDirectory", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;)V", "postVisitDirectory", "dir", "exc", "preVisitDirectory", "attrs", "visitFile", "file", "visitFileFailed", "kotlin-stdlib-jdk7"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class FileVisitorImpl extends SimpleFileVisitor {
    private final Function2 onPostVisitDirectory;
    private final Function2 onPreVisitDirectory;
    private final Function2 onVisitFile;
    private final Function2 onVisitFileFailed;

    public FileVisitorImpl(Function2 function20, Function2 function21, Function2 function22, Function2 function23) {
        this.onPreVisitDirectory = function20;
        this.onVisitFile = function21;
        this.onVisitFileFailed = function22;
        this.onPostVisitDirectory = function23;
    }

    @Override
    public FileVisitResult postVisitDirectory(Object object0, IOException iOException0) {
        return this.postVisitDirectory(((Path)object0), iOException0);
    }

    public FileVisitResult postVisitDirectory(Path path0, IOException iOException0) {
        Intrinsics.checkNotNullParameter(path0, "dir");
        Function2 function20 = this.onPostVisitDirectory;
        if(function20 != null) {
            FileVisitResult fileVisitResult0 = LinkFollowing..ExternalSyntheticApiModelOutline0.m(function20.invoke(path0, iOException0));
            if(fileVisitResult0 != null) {
                return fileVisitResult0;
            }
        }
        FileVisitResult fileVisitResult1 = super.postVisitDirectory(path0, iOException0);
        Intrinsics.checkNotNullExpressionValue(fileVisitResult1, "super.postVisitDirectory(dir, exc)");
        return fileVisitResult1;
    }

    @Override
    public FileVisitResult preVisitDirectory(Object object0, BasicFileAttributes basicFileAttributes0) {
        return this.preVisitDirectory(((Path)object0), basicFileAttributes0);
    }

    public FileVisitResult preVisitDirectory(Path path0, BasicFileAttributes basicFileAttributes0) {
        Intrinsics.checkNotNullParameter(path0, "dir");
        Intrinsics.checkNotNullParameter(basicFileAttributes0, "attrs");
        Function2 function20 = this.onPreVisitDirectory;
        if(function20 != null) {
            FileVisitResult fileVisitResult0 = LinkFollowing..ExternalSyntheticApiModelOutline0.m(function20.invoke(path0, basicFileAttributes0));
            if(fileVisitResult0 != null) {
                return fileVisitResult0;
            }
        }
        FileVisitResult fileVisitResult1 = super.preVisitDirectory(path0, basicFileAttributes0);
        Intrinsics.checkNotNullExpressionValue(fileVisitResult1, "super.preVisitDirectory(dir, attrs)");
        return fileVisitResult1;
    }

    @Override
    public FileVisitResult visitFile(Object object0, BasicFileAttributes basicFileAttributes0) {
        return this.visitFile(((Path)object0), basicFileAttributes0);
    }

    public FileVisitResult visitFile(Path path0, BasicFileAttributes basicFileAttributes0) {
        Intrinsics.checkNotNullParameter(path0, "file");
        Intrinsics.checkNotNullParameter(basicFileAttributes0, "attrs");
        Function2 function20 = this.onVisitFile;
        if(function20 != null) {
            FileVisitResult fileVisitResult0 = LinkFollowing..ExternalSyntheticApiModelOutline0.m(function20.invoke(path0, basicFileAttributes0));
            if(fileVisitResult0 != null) {
                return fileVisitResult0;
            }
        }
        FileVisitResult fileVisitResult1 = super.visitFile(path0, basicFileAttributes0);
        Intrinsics.checkNotNullExpressionValue(fileVisitResult1, "super.visitFile(file, attrs)");
        return fileVisitResult1;
    }

    @Override
    public FileVisitResult visitFileFailed(Object object0, IOException iOException0) {
        return this.visitFileFailed(((Path)object0), iOException0);
    }

    public FileVisitResult visitFileFailed(Path path0, IOException iOException0) {
        Intrinsics.checkNotNullParameter(path0, "file");
        Intrinsics.checkNotNullParameter(iOException0, "exc");
        Function2 function20 = this.onVisitFileFailed;
        if(function20 != null) {
            FileVisitResult fileVisitResult0 = LinkFollowing..ExternalSyntheticApiModelOutline0.m(function20.invoke(path0, iOException0));
            if(fileVisitResult0 != null) {
                return fileVisitResult0;
            }
        }
        FileVisitResult fileVisitResult1 = super.visitFileFailed(path0, iOException0);
        Intrinsics.checkNotNullExpressionValue(fileVisitResult1, "super.visitFileFailed(file, exc)");
        return fileVisitResult1;
    }
}

