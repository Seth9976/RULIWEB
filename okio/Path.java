package okio;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.internal._PathKt;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u000F\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 .2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001.B\u000F\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u0004J\u0011\u0010\u001D\u001A\u00020\u001E2\u0006\u0010\u001F\u001A\u00020\u0000H\u0096\u0002J\u0016\u0010 \u001A\u00020\u00002\u0006\u0010!\u001A\u00020\rH\u0087\u0002\u00A2\u0006\u0002\b\"J\u0016\u0010 \u001A\u00020\u00002\u0006\u0010!\u001A\u00020\u0003H\u0087\u0002\u00A2\u0006\u0002\b\"J\u0016\u0010 \u001A\u00020\u00002\u0006\u0010!\u001A\u00020\u0000H\u0087\u0002\u00A2\u0006\u0002\b\"J\u0013\u0010#\u001A\u00020\b2\b\u0010\u001F\u001A\u0004\u0018\u00010$H\u0096\u0002J\b\u0010%\u001A\u00020\u001EH\u0016J\u0006\u0010&\u001A\u00020\u0000J\u000E\u0010\'\u001A\u00020\u00002\u0006\u0010\u001F\u001A\u00020\u0000J\u0018\u0010\"\u001A\u00020\u00002\u0006\u0010!\u001A\u00020\r2\b\b\u0002\u0010(\u001A\u00020\bJ\u0018\u0010\"\u001A\u00020\u00002\u0006\u0010!\u001A\u00020\u00032\b\b\u0002\u0010(\u001A\u00020\bJ\u0018\u0010\"\u001A\u00020\u00002\u0006\u0010!\u001A\u00020\u00002\b\b\u0002\u0010(\u001A\u00020\bJ\u0006\u0010)\u001A\u00020*J\b\u0010+\u001A\u00020,H\u0007J\b\u0010-\u001A\u00020\rH\u0016R\u0014\u0010\u0002\u001A\u00020\u0003X\u0080\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001A\u00020\b8F\u00A2\u0006\u0006\u001A\u0004\b\u0007\u0010\tR\u0011\u0010\n\u001A\u00020\b8F\u00A2\u0006\u0006\u001A\u0004\b\n\u0010\tR\u0011\u0010\u000B\u001A\u00020\b8F\u00A2\u0006\u0006\u001A\u0004\b\u000B\u0010\tR\u0011\u0010\f\u001A\u00020\r8G\u00A2\u0006\u0006\u001A\u0004\b\f\u0010\u000ER\u0011\u0010\u000F\u001A\u00020\u00038G\u00A2\u0006\u0006\u001A\u0004\b\u000F\u0010\u0006R\u0013\u0010\u0010\u001A\u0004\u0018\u00010\u00008G\u00A2\u0006\u0006\u001A\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0012\u001A\u0004\u0018\u00010\u00008F\u00A2\u0006\u0006\u001A\u0004\b\u0013\u0010\u0011R\u0017\u0010\u0014\u001A\b\u0012\u0004\u0012\u00020\r0\u00158F\u00A2\u0006\u0006\u001A\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001A\b\u0012\u0004\u0012\u00020\u00030\u00158F\u00A2\u0006\u0006\u001A\u0004\b\u0019\u0010\u0017R\u0013\u0010\u001A\u001A\u0004\u0018\u00010\u001B8G\u00A2\u0006\u0006\u001A\u0004\b\u001A\u0010\u001C\u00A8\u0006/"}, d2 = {"Lokio/Path;", "", "bytes", "Lokio/ByteString;", "(Lokio/ByteString;)V", "getBytes$okio", "()Lokio/ByteString;", "isAbsolute", "", "()Z", "isRelative", "isRoot", "name", "", "()Ljava/lang/String;", "nameBytes", "parent", "()Lokio/Path;", "root", "getRoot", "segments", "", "getSegments", "()Ljava/util/List;", "segmentsBytes", "getSegmentsBytes", "volumeLetter", "", "()Ljava/lang/Character;", "compareTo", "", "other", "div", "child", "resolve", "equals", "", "hashCode", "normalized", "relativeTo", "normalize", "toFile", "Ljava/io/File;", "toNioPath", "Ljava/nio/file/Path;", "toString", "Companion", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class Path implements Comparable {
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001B\u0010\u0005\u001A\u00020\u0006*\u00020\u00072\b\b\u0002\u0010\b\u001A\u00020\tH\u0007¢\u0006\u0002\b\nJ\u001B\u0010\u0005\u001A\u00020\u0006*\u00020\u000B2\b\b\u0002\u0010\b\u001A\u00020\tH\u0007¢\u0006\u0002\b\nJ\u001B\u0010\f\u001A\u00020\u0006*\u00020\u00042\b\b\u0002\u0010\b\u001A\u00020\tH\u0007¢\u0006\u0002\b\nR\u0010\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lokio/Path$Companion;", "", "()V", "DIRECTORY_SEPARATOR", "", "toOkioPath", "Lokio/Path;", "Ljava/io/File;", "normalize", "", "get", "Ljava/nio/file/Path;", "toPath", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final Path get(File file0) {
            Intrinsics.checkNotNullParameter(file0, "<this>");
            return Companion.get$default(this, file0, false, 1, null);
        }

        @JvmStatic
        public final Path get(File file0, boolean z) {
            Intrinsics.checkNotNullParameter(file0, "<this>");
            String s = file0.toString();
            Intrinsics.checkNotNullExpressionValue(s, "toString()");
            return this.get(s, z);
        }

        @JvmStatic
        public final Path get(String s) {
            Intrinsics.checkNotNullParameter(s, "<this>");
            return Companion.get$default(this, s, false, 1, null);
        }

        @JvmStatic
        public final Path get(String s, boolean z) {
            Intrinsics.checkNotNullParameter(s, "<this>");
            return _PathKt.commonToPath(s, z);
        }

        @JvmStatic
        public final Path get(java.nio.file.Path path0) {
            Intrinsics.checkNotNullParameter(path0, "<this>");
            return Companion.get$default(this, path0, false, 1, null);
        }

        @JvmStatic
        public final Path get(java.nio.file.Path path0, boolean z) {
            Intrinsics.checkNotNullParameter(path0, "<this>");
            return this.get(path0.toString(), z);
        }

        public static Path get$default(Companion path$Companion0, File file0, boolean z, int v, Object object0) {
            if((v & 1) != 0) {
                z = false;
            }
            return path$Companion0.get(file0, z);
        }

        public static Path get$default(Companion path$Companion0, String s, boolean z, int v, Object object0) {
            if((v & 1) != 0) {
                z = false;
            }
            return path$Companion0.get(s, z);
        }

        public static Path get$default(Companion path$Companion0, java.nio.file.Path path0, boolean z, int v, Object object0) {
            if((v & 1) != 0) {
                z = false;
            }
            return path$Companion0.get(path0, z);
        }
    }

    public static final Companion Companion;
    public static final String DIRECTORY_SEPARATOR;
    private final ByteString bytes;

    static {
        Path.Companion = new Companion(null);
        Intrinsics.checkNotNullExpressionValue("/", "separator");
        Path.DIRECTORY_SEPARATOR = "/";
    }

    public Path(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "bytes");
        super();
        this.bytes = byteString0;
    }

    @Override
    public int compareTo(Object object0) {
        return this.compareTo(((Path)object0));
    }

    public int compareTo(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "other");
        return this.getBytes$okio().compareTo(path0.getBytes$okio());
    }

    // 去混淆评级： 低(20)
    @Override
    public boolean equals(Object object0) {
        return object0 instanceof Path && Intrinsics.areEqual(((Path)object0).getBytes$okio(), this.getBytes$okio());
    }

    @JvmStatic
    public static final Path get(File file0) {
        return Path.Companion.get(file0);
    }

    @JvmStatic
    public static final Path get(File file0, boolean z) {
        return Path.Companion.get(file0, z);
    }

    @JvmStatic
    public static final Path get(String s) {
        return Path.Companion.get(s);
    }

    @JvmStatic
    public static final Path get(String s, boolean z) {
        return Path.Companion.get(s, z);
    }

    @JvmStatic
    public static final Path get(java.nio.file.Path path0) {
        return Path.Companion.get(path0);
    }

    @JvmStatic
    public static final Path get(java.nio.file.Path path0, boolean z) {
        return Path.Companion.get(path0, z);
    }

    public final ByteString getBytes$okio() {
        return this.bytes;
    }

    public final Path getRoot() {
        int v = _PathKt.access$rootLength(this);
        return v == -1 ? null : new Path(this.getBytes$okio().substring(0, v));
    }

    public final List getSegments() {
        List list0 = new ArrayList();
        int v = _PathKt.access$rootLength(this);
        if(v == -1) {
            v = 0;
        }
        else if(v < this.getBytes$okio().size() && this.getBytes$okio().getByte(v) == 92) {
            ++v;
        }
        int v1 = this.getBytes$okio().size();
        int v2 = v;
        while(v < v1) {
            switch(this.getBytes$okio().getByte(v)) {
                case 0x2F: 
                case 92: {
                    list0.add(this.getBytes$okio().substring(v2, v));
                    v2 = v + 1;
                }
            }
            ++v;
        }
        if(v2 < this.getBytes$okio().size()) {
            list0.add(this.getBytes$okio().substring(v2, this.getBytes$okio().size()));
        }
        ArrayList arrayList0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list0, 10));
        for(Object object0: list0) {
            arrayList0.add(((ByteString)object0).utf8());
        }
        return arrayList0;
    }

    public final List getSegmentsBytes() {
        List list0 = new ArrayList();
        int v = _PathKt.access$rootLength(this);
        if(v == -1) {
            v = 0;
        }
        else if(v < this.getBytes$okio().size() && this.getBytes$okio().getByte(v) == 92) {
            ++v;
        }
        int v1 = this.getBytes$okio().size();
        int v2 = v;
        while(v < v1) {
            switch(this.getBytes$okio().getByte(v)) {
                case 0x2F: 
                case 92: {
                    list0.add(this.getBytes$okio().substring(v2, v));
                    v2 = v + 1;
                }
            }
            ++v;
        }
        if(v2 < this.getBytes$okio().size()) {
            list0.add(this.getBytes$okio().substring(v2, this.getBytes$okio().size()));
        }
        return list0;
    }

    @Override
    public int hashCode() {
        return this.getBytes$okio().hashCode();
    }

    public final boolean isAbsolute() {
        return _PathKt.access$rootLength(this) != -1;
    }

    public final boolean isRelative() {
        return _PathKt.access$rootLength(this) == -1;
    }

    public final boolean isRoot() {
        return _PathKt.access$rootLength(this) == this.getBytes$okio().size();
    }

    public final String name() {
        return this.nameBytes().utf8();
    }

    public final ByteString nameBytes() {
        int v = _PathKt.access$getIndexOfLastSlash(this);
        if(v != -1) {
            return ByteString.substring$default(this.getBytes$okio(), v + 1, 0, 2, null);
        }
        return this.volumeLetter() == null || this.getBytes$okio().size() != 2 ? this.getBytes$okio() : ByteString.EMPTY;
    }

    public final Path normalized() {
        return Path.Companion.get(this.toString(), true);
    }

    public final Path parent() {
        if(!Intrinsics.areEqual(this.getBytes$okio(), _PathKt.access$getDOT$p()) && !Intrinsics.areEqual(this.getBytes$okio(), _PathKt.access$getSLASH$p()) && !Intrinsics.areEqual(this.getBytes$okio(), _PathKt.access$getBACKSLASH$p()) && !_PathKt.access$lastSegmentIsDotDot(this)) {
            int v = _PathKt.access$getIndexOfLastSlash(this);
            if(v == 2 && this.volumeLetter() != null) {
                return this.getBytes$okio().size() == 3 ? null : new Path(ByteString.substring$default(this.getBytes$okio(), 0, 3, 1, null));
            }
            if(v == 1 && this.getBytes$okio().startsWith(_PathKt.access$getBACKSLASH$p())) {
                return null;
            }
            if(v == -1 && this.volumeLetter() != null) {
                return this.getBytes$okio().size() == 2 ? null : new Path(ByteString.substring$default(this.getBytes$okio(), 0, 2, 1, null));
            }
            if(v == -1) {
                return new Path(_PathKt.access$getDOT$p());
            }
            return v == 0 ? new Path(ByteString.substring$default(this.getBytes$okio(), 0, 1, 1, null)) : new Path(ByteString.substring$default(this.getBytes$okio(), 0, v, 1, null));
        }
        return null;
    }

    public final Path relativeTo(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "other");
        if(!Intrinsics.areEqual(this.getRoot(), path0.getRoot())) {
            throw new IllegalArgumentException(("Paths of different roots cannot be relative to each other: " + this + " and " + path0).toString());
        }
        List list0 = this.getSegmentsBytes();
        List list1 = path0.getSegmentsBytes();
        int v = Math.min(list0.size(), list1.size());
        int v1;
        for(v1 = 0; v1 < v && Intrinsics.areEqual(list0.get(v1), list1.get(v1)); ++v1) {
        }
        if(v1 == v && this.getBytes$okio().size() == path0.getBytes$okio().size()) {
            return Companion.get$default(Path.Companion, ".", false, 1, null);
        }
        if(list1.subList(v1, list1.size()).indexOf(_PathKt.access$getDOT_DOT$p()) != -1) {
            throw new IllegalArgumentException(("Impossible relative path to resolve: " + this + " and " + path0).toString());
        }
        Buffer buffer0 = new Buffer();
        ByteString byteString0 = _PathKt.access$getSlash(path0);
        if(byteString0 == null) {
            byteString0 = _PathKt.access$getSlash(this);
            if(byteString0 == null) {
                byteString0 = _PathKt.access$toSlash("/");
            }
        }
        int v2 = list1.size();
        for(int v3 = v1; v3 < v2; ++v3) {
            buffer0.write(_PathKt.access$getDOT_DOT$p());
            buffer0.write(byteString0);
        }
        int v4 = list0.size();
        while(v1 < v4) {
            buffer0.write(((ByteString)list0.get(v1)));
            buffer0.write(byteString0);
            ++v1;
        }
        return _PathKt.toPath(buffer0, false);
    }

    public final Path resolve(String s) {
        Intrinsics.checkNotNullParameter(s, "child");
        return _PathKt.commonResolve(this, _PathKt.toPath(new Buffer().writeUtf8(s), false), false);
    }

    public final Path resolve(String s, boolean z) {
        Intrinsics.checkNotNullParameter(s, "child");
        return _PathKt.commonResolve(this, _PathKt.toPath(new Buffer().writeUtf8(s), false), z);
    }

    public final Path resolve(ByteString byteString0) {
        Intrinsics.checkNotNullParameter(byteString0, "child");
        return _PathKt.commonResolve(this, _PathKt.toPath(new Buffer().write(byteString0), false), false);
    }

    public final Path resolve(ByteString byteString0, boolean z) {
        Intrinsics.checkNotNullParameter(byteString0, "child");
        return _PathKt.commonResolve(this, _PathKt.toPath(new Buffer().write(byteString0), false), z);
    }

    public final Path resolve(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "child");
        return _PathKt.commonResolve(this, path0, false);
    }

    public final Path resolve(Path path0, boolean z) {
        Intrinsics.checkNotNullParameter(path0, "child");
        return _PathKt.commonResolve(this, path0, z);
    }

    public static Path resolve$default(Path path0, String s, boolean z, int v, Object object0) {
        if((v & 2) != 0) {
            z = false;
        }
        return path0.resolve(s, z);
    }

    public static Path resolve$default(Path path0, ByteString byteString0, boolean z, int v, Object object0) {
        if((v & 2) != 0) {
            z = false;
        }
        return path0.resolve(byteString0, z);
    }

    public static Path resolve$default(Path path0, Path path1, boolean z, int v, Object object0) {
        if((v & 2) != 0) {
            z = false;
        }
        return path0.resolve(path1, z);
    }

    public final File toFile() {
        return new File(this.toString());
    }

    public final java.nio.file.Path toNioPath() {
        java.nio.file.Path path0 = Paths.get(this.toString(), new String[0]);
        Intrinsics.checkNotNullExpressionValue(path0, "get(toString())");
        return path0;
    }

    @Override
    public String toString() {
        return this.getBytes$okio().utf8();
    }

    public final Character volumeLetter() {
        if(ByteString.indexOf$default(this.getBytes$okio(), _PathKt.access$getSLASH$p(), 0, 2, null) != -1) {
            return null;
        }
        if(this.getBytes$okio().size() < 2) {
            return null;
        }
        if(this.getBytes$okio().getByte(1) != 58) {
            return null;
        }
        int v = (char)this.getBytes$okio().getByte(0);
        return (97 > v || v >= 0x7B) && (65 > v || v >= 91) ? null : Character.valueOf(((char)v));
    }
}

