package okio.internal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import okio.FileHandle;
import okio.FileMetadata;
import okio.FileSystem;
import okio.Path;
import okio.Sink;
import okio.Source;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0000\u0018\u0000 *2\u00020\u0001:\u0001*B\u0017\b\u0000\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u00A2\u0006\u0002\u0010\u0006J\u0018\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\n2\u0006\u0010\u0012\u001A\u00020\u0005H\u0016J\u0018\u0010\u0013\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\n2\u0006\u0010\u0016\u001A\u00020\nH\u0016J\u0010\u0010\u0017\u001A\u00020\n2\u0006\u0010\u0018\u001A\u00020\nH\u0016J\u0010\u0010\u0019\u001A\u00020\n2\u0006\u0010\u0018\u001A\u00020\nH\u0002J\u0018\u0010\u001A\u001A\u00020\u00142\u0006\u0010\u001B\u001A\u00020\n2\u0006\u0010\u001C\u001A\u00020\u0005H\u0016J\u0018\u0010\u001D\u001A\u00020\u00142\u0006\u0010\u0015\u001A\u00020\n2\u0006\u0010\u0016\u001A\u00020\nH\u0016J\u0018\u0010\u001E\u001A\u00020\u00142\u0006\u0010\u0018\u001A\u00020\n2\u0006\u0010\u0012\u001A\u00020\u0005H\u0016J\u0016\u0010\u001F\u001A\b\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\u001B\u001A\u00020\nH\u0016J\u0018\u0010 \u001A\n\u0012\u0004\u0012\u00020\n\u0018\u00010\b2\u0006\u0010\u001B\u001A\u00020\nH\u0016J\u0012\u0010!\u001A\u0004\u0018\u00010\"2\u0006\u0010\u0018\u001A\u00020\nH\u0016J\u0010\u0010#\u001A\u00020$2\u0006\u0010\u0011\u001A\u00020\nH\u0016J \u0010%\u001A\u00020$2\u0006\u0010\u0011\u001A\u00020\n2\u0006\u0010\u001C\u001A\u00020\u00052\u0006\u0010\u0012\u001A\u00020\u0005H\u0016J\u0018\u0010&\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\n2\u0006\u0010\u001C\u001A\u00020\u0005H\u0016J\u0010\u0010\u0015\u001A\u00020\'2\u0006\u0010\u0011\u001A\u00020\nH\u0016J\f\u0010(\u001A\u00020)*\u00020\nH\u0002R-\u0010\u0007\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\n0\t0\b8BX\u0082\u0084\u0002\u00A2\u0006\f\n\u0004\b\r\u0010\u000E\u001A\u0004\b\u000B\u0010\f\u00A8\u0006+"}, d2 = {"Lokio/internal/ResourceFileSystem;", "Lokio/FileSystem;", "classLoader", "Ljava/lang/ClassLoader;", "indexEagerly", "", "(Ljava/lang/ClassLoader;Z)V", "roots", "", "Lkotlin/Pair;", "Lokio/Path;", "getRoots", "()Ljava/util/List;", "roots$delegate", "Lkotlin/Lazy;", "appendingSink", "Lokio/Sink;", "file", "mustExist", "atomicMove", "", "source", "target", "canonicalize", "path", "canonicalizeInternal", "createDirectory", "dir", "mustCreate", "createSymlink", "delete", "list", "listOrNull", "metadataOrNull", "Lokio/FileMetadata;", "openReadOnly", "Lokio/FileHandle;", "openReadWrite", "sink", "Lokio/Source;", "toRelativePath", "", "Companion", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
public final class ResourceFileSystem extends FileSystem {
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\u0004H\u0002J\u0012\u0010\n\u001A\u00020\u0004*\u00020\u00042\u0006\u0010\u000B\u001A\u00020\u0004J\u001C\u0010\f\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00020\u000F\u0012\u0004\u0012\u00020\u00040\u000E0\r*\u00020\u0010J\u0018\u0010\u0011\u001A\u0010\u0012\u0004\u0012\u00020\u000F\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000E*\u00020\u0012J\u0018\u0010\u0013\u001A\u0010\u0012\u0004\u0012\u00020\u000F\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u000E*\u00020\u0012R\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, d2 = {"Lokio/internal/ResourceFileSystem$Companion;", "", "()V", "ROOT", "Lokio/Path;", "getROOT", "()Lokio/Path;", "keepPath", "", "path", "removeBase", "base", "toClasspathRoots", "", "Lkotlin/Pair;", "Lokio/FileSystem;", "Ljava/lang/ClassLoader;", "toFileRoot", "Ljava/net/URL;", "toJarRoot", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
    static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final Path getROOT() {
            return ResourceFileSystem.ROOT;
        }

        private final boolean keepPath(Path path0) {
            return !StringsKt.endsWith(path0.name(), ".class", true);
        }

        public final Path removeBase(Path path0, Path path1) {
            Intrinsics.checkNotNullParameter(path0, "<this>");
            Intrinsics.checkNotNullParameter(path1, "base");
            return this.getROOT().resolve(StringsKt.replace$default(StringsKt.removePrefix(path0.toString(), path1.toString()), '\\', '/', false, 4, null));
        }

        public final List toClasspathRoots(ClassLoader classLoader0) {
            Intrinsics.checkNotNullParameter(classLoader0, "<this>");
            Enumeration enumeration0 = classLoader0.getResources("");
            Intrinsics.checkNotNullExpressionValue(enumeration0, "getResources(\"\")");
            ArrayList arrayList0 = Collections.list(enumeration0);
            Intrinsics.checkNotNullExpressionValue(arrayList0, "list(this)");
            Collection collection0 = new ArrayList();
            for(Object object0: arrayList0) {
                Intrinsics.checkNotNullExpressionValue(((URL)object0), "it");
                Pair pair0 = ResourceFileSystem.Companion.toFileRoot(((URL)object0));
                if(pair0 != null) {
                    collection0.add(pair0);
                }
            }
            Enumeration enumeration1 = classLoader0.getResources("META-INF/MANIFEST.MF");
            Intrinsics.checkNotNullExpressionValue(enumeration1, "getResources(\"META-INF/MANIFEST.MF\")");
            ArrayList arrayList1 = Collections.list(enumeration1);
            Intrinsics.checkNotNullExpressionValue(arrayList1, "list(this)");
            Collection collection1 = new ArrayList();
            for(Object object1: arrayList1) {
                Intrinsics.checkNotNullExpressionValue(((URL)object1), "it");
                Pair pair1 = ResourceFileSystem.Companion.toJarRoot(((URL)object1));
                if(pair1 != null) {
                    collection1.add(pair1);
                }
            }
            return CollectionsKt.plus(((List)collection0), ((List)collection1));
        }

        public final Pair toFileRoot(URL uRL0) {
            Intrinsics.checkNotNullParameter(uRL0, "<this>");
            if(!Intrinsics.areEqual(uRL0.getProtocol(), "file")) {
                return null;
            }
            File file0 = new File(uRL0.toURI());
            Path path0 = okio.Path.Companion.get$default(Path.Companion, file0, false, 1, null);
            return TuplesKt.to(FileSystem.SYSTEM, path0);
        }

        public final Pair toJarRoot(URL uRL0) {
            Intrinsics.checkNotNullParameter(uRL0, "<this>");
            String s = uRL0.toString();
            Intrinsics.checkNotNullExpressionValue(s, "toString()");
            if(!StringsKt.startsWith$default(s, "jar:file:", false, 2, null)) {
                return null;
            }
            int v = StringsKt.lastIndexOf$default(s, "!", 0, false, 6, null);
            if(v == -1) {
                return null;
            }
            String s1 = s.substring(4, v);
            Intrinsics.checkNotNullExpressionValue(s1, "this as java.lang.String…ing(startIndex, endIndex)");
            File file0 = new File(URI.create(s1));
            return TuplesKt.to(ZipKt.openZip(okio.Path.Companion.get$default(Path.Companion, file0, false, 1, null), FileSystem.SYSTEM, okio.internal.ResourceFileSystem.Companion.toJarRoot.zip.1.INSTANCE), this.getROOT());

            @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "entry", "Lokio/internal/ZipEntry;", "invoke", "(Lokio/internal/ZipEntry;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 6, 0}, xi = 0x30)
            final class okio.internal.ResourceFileSystem.Companion.toJarRoot.zip.1 extends Lambda implements Function1 {
                public static final okio.internal.ResourceFileSystem.Companion.toJarRoot.zip.1 INSTANCE;

                static {
                    okio.internal.ResourceFileSystem.Companion.toJarRoot.zip.1.INSTANCE = new okio.internal.ResourceFileSystem.Companion.toJarRoot.zip.1();
                }

                okio.internal.ResourceFileSystem.Companion.toJarRoot.zip.1() {
                    super(1);
                }

                public final Boolean invoke(ZipEntry zipEntry0) {
                    Intrinsics.checkNotNullParameter(zipEntry0, "entry");
                    return Boolean.valueOf(ResourceFileSystem.Companion.keepPath(zipEntry0.getCanonicalPath()));
                }

                @Override  // kotlin.jvm.functions.Function1
                public Object invoke(Object object0) {
                    return this.invoke(((ZipEntry)object0));
                }
            }

        }
    }

    private static final Companion Companion;
    @Deprecated
    private static final Path ROOT;
    private final Lazy roots$delegate;

    static {
        ResourceFileSystem.Companion = new Companion(null);
        ResourceFileSystem.ROOT = okio.Path.Companion.get$default(Path.Companion, "/", false, 1, null);
    }

    public ResourceFileSystem(ClassLoader classLoader0, boolean z) {
        Intrinsics.checkNotNullParameter(classLoader0, "classLoader");
        super();
        this.roots$delegate = LazyKt.lazy(new Function0() {
            final ClassLoader $classLoader;

            {
                this.$classLoader = classLoader0;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }

            public final List invoke() {
                return ResourceFileSystem.Companion.toClasspathRoots(this.$classLoader);
            }
        });
        if(z) {
            this.getRoots().size();
        }
    }

    @Override  // okio.FileSystem
    public Sink appendingSink(Path path0, boolean z) {
        Intrinsics.checkNotNullParameter(path0, "file");
        throw new IOException(this + " is read-only");
    }

    @Override  // okio.FileSystem
    public void atomicMove(Path path0, Path path1) {
        Intrinsics.checkNotNullParameter(path0, "source");
        Intrinsics.checkNotNullParameter(path1, "target");
        throw new IOException(this + " is read-only");
    }

    @Override  // okio.FileSystem
    public Path canonicalize(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "path");
        return this.canonicalizeInternal(path0);
    }

    private final Path canonicalizeInternal(Path path0) {
        return ResourceFileSystem.ROOT.resolve(path0, true);
    }

    @Override  // okio.FileSystem
    public void createDirectory(Path path0, boolean z) {
        Intrinsics.checkNotNullParameter(path0, "dir");
        throw new IOException(this + " is read-only");
    }

    @Override  // okio.FileSystem
    public void createSymlink(Path path0, Path path1) {
        Intrinsics.checkNotNullParameter(path0, "source");
        Intrinsics.checkNotNullParameter(path1, "target");
        throw new IOException(this + " is read-only");
    }

    @Override  // okio.FileSystem
    public void delete(Path path0, boolean z) {
        Intrinsics.checkNotNullParameter(path0, "path");
        throw new IOException(this + " is read-only");
    }

    private final List getRoots() {
        return (List)this.roots$delegate.getValue();
    }

    @Override  // okio.FileSystem
    public List list(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "dir");
        String s = this.toRelativePath(path0);
        Set set0 = new LinkedHashSet();
        boolean z = false;
        for(Object object0: this.getRoots()) {
            FileSystem fileSystem0 = (FileSystem)((Pair)object0).component1();
            Path path1 = (Path)((Pair)object0).component2();
            try {
                Iterable iterable0 = fileSystem0.list(path1.resolve(s));
                Collection collection0 = new ArrayList();
                for(Object object1: iterable0) {
                    if(Companion.access$keepPath(ResourceFileSystem.Companion, ((Path)object1))) {
                        collection0.add(object1);
                    }
                }
                Collection collection1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(((List)collection0), 10));
                for(Object object2: ((List)collection0)) {
                    collection1.add(ResourceFileSystem.Companion.removeBase(((Path)object2), path1));
                }
                CollectionsKt.addAll(set0, ((List)collection1));
                z = true;
            }
            catch(IOException unused_ex) {
            }
        }
        if(!z) {
            throw new FileNotFoundException("file not found: " + path0);
        }
        return CollectionsKt.toList(set0);
    }

    @Override  // okio.FileSystem
    public List listOrNull(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "dir");
        String s = this.toRelativePath(path0);
        Set set0 = new LinkedHashSet();
        Iterator iterator0 = this.getRoots().iterator();
        boolean z = false;
        while(true) {
            List list0 = null;
            if(!iterator0.hasNext()) {
                break;
            }
            Object object0 = iterator0.next();
            Path path1 = (Path)((Pair)object0).component2();
            List list1 = ((FileSystem)((Pair)object0).component1()).listOrNull(path1.resolve(s));
            if(list1 != null) {
                Collection collection0 = new ArrayList();
                for(Object object1: list1) {
                    if(Companion.access$keepPath(ResourceFileSystem.Companion, ((Path)object1))) {
                        collection0.add(object1);
                    }
                }
                Collection collection1 = new ArrayList(CollectionsKt.collectionSizeOrDefault(((List)collection0), 10));
                for(Object object2: ((List)collection0)) {
                    collection1.add(ResourceFileSystem.Companion.removeBase(((Path)object2), path1));
                }
                list0 = (List)collection1;
            }
            if(list0 != null) {
                CollectionsKt.addAll(set0, list0);
                z = true;
            }
        }
        return z ? CollectionsKt.toList(set0) : null;
    }

    @Override  // okio.FileSystem
    public FileMetadata metadataOrNull(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "path");
        if(!Companion.access$keepPath(ResourceFileSystem.Companion, path0)) {
            return null;
        }
        String s = this.toRelativePath(path0);
        for(Object object0: this.getRoots()) {
            FileMetadata fileMetadata0 = ((FileSystem)((Pair)object0).component1()).metadataOrNull(((Path)((Pair)object0).component2()).resolve(s));
            if(fileMetadata0 != null) {
                return fileMetadata0;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    @Override  // okio.FileSystem
    public FileHandle openReadOnly(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "file");
        if(!Companion.access$keepPath(ResourceFileSystem.Companion, path0)) {
            throw new FileNotFoundException("file not found: " + path0);
        }
        String s = this.toRelativePath(path0);
        for(Object object0: this.getRoots()) {
            FileSystem fileSystem0 = (FileSystem)((Pair)object0).component1();
            Path path1 = (Path)((Pair)object0).component2();
            try {
                return fileSystem0.openReadOnly(path1.resolve(s));
            }
            catch(FileNotFoundException unused_ex) {
            }
        }
        throw new FileNotFoundException("file not found: " + path0);
    }

    @Override  // okio.FileSystem
    public FileHandle openReadWrite(Path path0, boolean z, boolean z1) {
        Intrinsics.checkNotNullParameter(path0, "file");
        throw new IOException("resources are not writable");
    }

    @Override  // okio.FileSystem
    public Sink sink(Path path0, boolean z) {
        Intrinsics.checkNotNullParameter(path0, "file");
        throw new IOException(this + " is read-only");
    }

    @Override  // okio.FileSystem
    public Source source(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "file");
        if(!Companion.access$keepPath(ResourceFileSystem.Companion, path0)) {
            throw new FileNotFoundException("file not found: " + path0);
        }
        String s = this.toRelativePath(path0);
        for(Object object0: this.getRoots()) {
            FileSystem fileSystem0 = (FileSystem)((Pair)object0).component1();
            Path path1 = (Path)((Pair)object0).component2();
            try {
                return fileSystem0.source(path1.resolve(s));
            }
            catch(FileNotFoundException unused_ex) {
            }
        }
        throw new FileNotFoundException("file not found: " + path0);
    }

    private final String toRelativePath(Path path0) {
        return this.canonicalizeInternal(path0).relativeTo(ResourceFileSystem.ROOT).toString();
    }
}

