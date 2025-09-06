package kotlin.io.path;

import java.nio.file.FileVisitor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0001\u0018\u00002\u00020\u0001B\u0005\u00A2\u0006\u0002\u0010\u0002J\f\u0010\u000E\u001A\b\u0012\u0004\u0012\u00020\u00070\u000FJ\b\u0010\u0010\u001A\u00020\u0011H\u0002J\u001A\u0010\u0012\u001A\u00020\u00112\b\u0010\u0013\u001A\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001A\u00020\u0016H\u0002JB\u0010\u0005\u001A\u00020\u001128\u0010\u0013\u001A4\u0012\u0013\u0012\u00110\u0007\u00A2\u0006\f\b\u0017\u0012\b\b\u0015\u0012\u0004\b\b(\u0018\u0012\u0015\u0012\u0013\u0018\u00010\b\u00A2\u0006\f\b\u0017\u0012\b\b\u0015\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\t0\u0006H\u0016J@\u0010\n\u001A\u00020\u001126\u0010\u0013\u001A2\u0012\u0013\u0012\u00110\u0007\u00A2\u0006\f\b\u0017\u0012\b\b\u0015\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\u000B\u00A2\u0006\f\b\u0017\u0012\b\b\u0015\u0012\u0004\b\b(\u001A\u0012\u0004\u0012\u00020\t0\u0006H\u0016J@\u0010\f\u001A\u00020\u001126\u0010\u0013\u001A2\u0012\u0013\u0012\u00110\u0007\u00A2\u0006\f\b\u0017\u0012\b\b\u0015\u0012\u0004\b\b(\u001B\u0012\u0013\u0012\u00110\u000B\u00A2\u0006\f\b\u0017\u0012\b\b\u0015\u0012\u0004\b\b(\u001A\u0012\u0004\u0012\u00020\t0\u0006H\u0016J@\u0010\r\u001A\u00020\u001126\u0010\u0013\u001A2\u0012\u0013\u0012\u00110\u0007\u00A2\u0006\f\b\u0017\u0012\b\b\u0015\u0012\u0004\b\b(\u001B\u0012\u0013\u0012\u00110\b\u00A2\u0006\f\b\u0017\u0012\b\b\u0015\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\t0\u0006H\u0016R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u000E\u00A2\u0006\u0002\n\u0000R$\u0010\u0005\u001A\u0018\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0006X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\"\u0010\n\u001A\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000B\u0012\u0004\u0012\u00020\t\u0018\u00010\u0006X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\"\u0010\f\u001A\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u000B\u0012\u0004\u0012\u00020\t\u0018\u00010\u0006X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\"\u0010\r\u001A\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0006X\u0082\u000E\u00A2\u0006\u0002\n\u0000\u00A8\u0006\u001C"}, d2 = {"Lkotlin/io/path/FileVisitorBuilderImpl;", "Lkotlin/io/path/FileVisitorBuilder;", "()V", "isBuilt", "", "onPostVisitDirectory", "Lkotlin/Function2;", "Ljava/nio/file/Path;", "Ljava/io/IOException;", "Ljava/nio/file/FileVisitResult;", "onPreVisitDirectory", "Ljava/nio/file/attribute/BasicFileAttributes;", "onVisitFile", "onVisitFileFailed", "build", "Ljava/nio/file/FileVisitor;", "checkIsNotBuilt", "", "checkNotDefined", "function", "", "name", "", "Lkotlin/ParameterName;", "directory", "exception", "attributes", "file", "kotlin-stdlib-jdk7"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class FileVisitorBuilderImpl implements FileVisitorBuilder {
    private boolean isBuilt;
    private Function2 onPostVisitDirectory;
    private Function2 onPreVisitDirectory;
    private Function2 onVisitFile;
    private Function2 onVisitFileFailed;

    public final FileVisitor build() {
        this.checkIsNotBuilt();
        this.isBuilt = true;
        return LinkFollowing..ExternalSyntheticApiModelOutline0.m(new FileVisitorImpl(this.onPreVisitDirectory, this.onVisitFile, this.onVisitFileFailed, this.onPostVisitDirectory));
    }

    private final void checkIsNotBuilt() {
        if(this.isBuilt) {
            throw new IllegalStateException("This builder was already built");
        }
    }

    private final void checkNotDefined(Object object0, String s) {
        if(object0 != null) {
            throw new IllegalStateException(s + " was already defined");
        }
    }

    @Override  // kotlin.io.path.FileVisitorBuilder
    public void onPostVisitDirectory(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "function");
        this.checkIsNotBuilt();
        this.checkNotDefined(this.onPostVisitDirectory, "onPostVisitDirectory");
        this.onPostVisitDirectory = function20;
    }

    @Override  // kotlin.io.path.FileVisitorBuilder
    public void onPreVisitDirectory(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "function");
        this.checkIsNotBuilt();
        this.checkNotDefined(this.onPreVisitDirectory, "onPreVisitDirectory");
        this.onPreVisitDirectory = function20;
    }

    @Override  // kotlin.io.path.FileVisitorBuilder
    public void onVisitFile(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "function");
        this.checkIsNotBuilt();
        this.checkNotDefined(this.onVisitFile, "onVisitFile");
        this.onVisitFile = function20;
    }

    @Override  // kotlin.io.path.FileVisitorBuilder
    public void onVisitFileFailed(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "function");
        this.checkIsNotBuilt();
        this.checkNotDefined(this.onVisitFileFailed, "onVisitFileFailed");
        this.onVisitFileFailed = function20;
    }
}

