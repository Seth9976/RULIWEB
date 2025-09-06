package kotlin.io;

import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\r\b\u0080\b\u0018\u00002\u00020\u0001B\u001D\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0016\u001A\u00020\u0003HÆ\u0003J\u000F\u0010\u0017\u001A\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J#\u0010\u0018\u001A\u00020\u00002\b\b\u0002\u0010\u0002\u001A\u00020\u00032\u000E\b\u0002\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u0019\u001A\u00020\b2\b\u0010\u001A\u001A\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001B\u001A\u00020\u0013HÖ\u0001J\u0016\u0010\u001C\u001A\u00020\u00032\u0006\u0010\u001D\u001A\u00020\u00132\u0006\u0010\u001E\u001A\u00020\u0013J\t\u0010\u001F\u001A\u00020\rHÖ\u0001R\u0011\u0010\u0007\u001A\u00020\b8F¢\u0006\u0006\u001A\u0004\b\u0007\u0010\tR\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000BR\u0011\u0010\f\u001A\u00020\r8F¢\u0006\u0006\u001A\u0004\b\u000E\u0010\u000FR\u0017\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001A\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001A\u00020\u00138F¢\u0006\u0006\u001A\u0004\b\u0014\u0010\u0015¨\u0006 "}, d2 = {"Lkotlin/io/FilePathComponents;", "", "root", "Ljava/io/File;", "segments", "", "(Ljava/io/File;Ljava/util/List;)V", "isRooted", "", "()Z", "getRoot", "()Ljava/io/File;", "rootName", "", "getRootName", "()Ljava/lang/String;", "getSegments", "()Ljava/util/List;", "size", "", "getSize", "()I", "component1", "component2", "copy", "equals", "other", "hashCode", "subPath", "beginIndex", "endIndex", "toString", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class FilePathComponents {
    private final File root;
    private final List segments;

    public FilePathComponents(File file0, List list0) {
        Intrinsics.checkNotNullParameter(file0, "root");
        Intrinsics.checkNotNullParameter(list0, "segments");
        super();
        this.root = file0;
        this.segments = list0;
    }

    public final File component1() {
        return this.root;
    }

    public final List component2() {
        return this.segments;
    }

    public final FilePathComponents copy(File file0, List list0) {
        Intrinsics.checkNotNullParameter(file0, "root");
        Intrinsics.checkNotNullParameter(list0, "segments");
        return new FilePathComponents(file0, list0);
    }

    public static FilePathComponents copy$default(FilePathComponents filePathComponents0, File file0, List list0, int v, Object object0) {
        if((v & 1) != 0) {
            file0 = filePathComponents0.root;
        }
        if((v & 2) != 0) {
            list0 = filePathComponents0.segments;
        }
        return filePathComponents0.copy(file0, list0);
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof FilePathComponents)) {
            return false;
        }
        return Intrinsics.areEqual(this.root, ((FilePathComponents)object0).root) ? Intrinsics.areEqual(this.segments, ((FilePathComponents)object0).segments) : false;
    }

    public final File getRoot() {
        return this.root;
    }

    public final String getRootName() {
        String s = this.root.getPath();
        Intrinsics.checkNotNullExpressionValue(s, "root.path");
        return s;
    }

    public final List getSegments() {
        return this.segments;
    }

    public final int getSize() {
        return this.segments.size();
    }

    @Override
    public int hashCode() {
        return this.root.hashCode() * 0x1F + this.segments.hashCode();
    }

    public final boolean isRooted() {
        String s = this.root.getPath();
        Intrinsics.checkNotNullExpressionValue(s, "root.path");
        return s.length() > 0;
    }

    public final File subPath(int v, int v1) {
        if(v < 0 || v > v1 || v1 > this.getSize()) {
            throw new IllegalArgumentException();
        }
        List list0 = this.segments.subList(v, v1);
        Intrinsics.checkNotNullExpressionValue("/", "separator");
        return new File(CollectionsKt.joinToString$default(list0, "/", null, null, 0, null, null, 62, null));
    }

    @Override
    public String toString() {
        return "FilePathComponents(root=" + this.root + ", segments=" + this.segments + ')';
    }
}

