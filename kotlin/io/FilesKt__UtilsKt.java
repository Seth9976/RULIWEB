package kotlin.io;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u001A*\u0010\t\u001A\u00020\u00022\b\b\u0002\u0010\n\u001A\u00020\u00012\n\b\u0002\u0010\u000B\u001A\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001A\u0004\u0018\u00010\u0002H\u0007\u001A*\u0010\r\u001A\u00020\u00022\b\b\u0002\u0010\n\u001A\u00020\u00012\n\b\u0002\u0010\u000B\u001A\u0004\u0018\u00010\u00012\n\b\u0002\u0010\f\u001A\u0004\u0018\u00010\u0002H\u0007\u001A8\u0010\u000E\u001A\u00020\u000F*\u00020\u00022\u0006\u0010\u0010\u001A\u00020\u00022\b\b\u0002\u0010\u0011\u001A\u00020\u000F2\u001A\b\u0002\u0010\u0012\u001A\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013\u001A&\u0010\u0016\u001A\u00020\u0002*\u00020\u00022\u0006\u0010\u0010\u001A\u00020\u00022\b\b\u0002\u0010\u0011\u001A\u00020\u000F2\b\b\u0002\u0010\u0017\u001A\u00020\u0018\u001A\n\u0010\u0019\u001A\u00020\u000F*\u00020\u0002\u001A\u0012\u0010\u001A\u001A\u00020\u000F*\u00020\u00022\u0006\u0010\u001B\u001A\u00020\u0002\u001A\u0012\u0010\u001A\u001A\u00020\u000F*\u00020\u00022\u0006\u0010\u001B\u001A\u00020\u0001\u001A\n\u0010\u001C\u001A\u00020\u0002*\u00020\u0002\u001A\u001D\u0010\u001C\u001A\b\u0012\u0004\u0012\u00020\u00020\u001D*\b\u0012\u0004\u0012\u00020\u00020\u001DH\u0002\u00A2\u0006\u0002\b\u001E\u001A\u0011\u0010\u001C\u001A\u00020\u001F*\u00020\u001FH\u0002\u00A2\u0006\u0002\b\u001E\u001A\u0012\u0010 \u001A\u00020\u0002*\u00020\u00022\u0006\u0010!\u001A\u00020\u0002\u001A\u0014\u0010\"\u001A\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u0010!\u001A\u00020\u0002\u001A\u0012\u0010#\u001A\u00020\u0002*\u00020\u00022\u0006\u0010!\u001A\u00020\u0002\u001A\u0012\u0010$\u001A\u00020\u0002*\u00020\u00022\u0006\u0010%\u001A\u00020\u0002\u001A\u0012\u0010$\u001A\u00020\u0002*\u00020\u00022\u0006\u0010%\u001A\u00020\u0001\u001A\u0012\u0010&\u001A\u00020\u0002*\u00020\u00022\u0006\u0010%\u001A\u00020\u0002\u001A\u0012\u0010&\u001A\u00020\u0002*\u00020\u00022\u0006\u0010%\u001A\u00020\u0001\u001A\u0012\u0010\'\u001A\u00020\u000F*\u00020\u00022\u0006\u0010\u001B\u001A\u00020\u0002\u001A\u0012\u0010\'\u001A\u00020\u000F*\u00020\u00022\u0006\u0010\u001B\u001A\u00020\u0001\u001A\u0012\u0010(\u001A\u00020\u0001*\u00020\u00022\u0006\u0010!\u001A\u00020\u0002\u001A\u001B\u0010)\u001A\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010!\u001A\u00020\u0002H\u0002\u00A2\u0006\u0002\b*\"\u0015\u0010\u0000\u001A\u00020\u0001*\u00020\u00028F\u00A2\u0006\u0006\u001A\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001A\u00020\u0001*\u00020\u00028F\u00A2\u0006\u0006\u001A\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0007\u001A\u00020\u0001*\u00020\u00028F\u00A2\u0006\u0006\u001A\u0004\b\b\u0010\u0004\u00A8\u0006+"}, d2 = {"extension", "", "Ljava/io/File;", "getExtension", "(Ljava/io/File;)Ljava/lang/String;", "invariantSeparatorsPath", "getInvariantSeparatorsPath", "nameWithoutExtension", "getNameWithoutExtension", "createTempDir", "prefix", "suffix", "directory", "createTempFile", "copyRecursively", "", "target", "overwrite", "onError", "Lkotlin/Function2;", "Ljava/io/IOException;", "Lkotlin/io/OnErrorAction;", "copyTo", "bufferSize", "", "deleteRecursively", "endsWith", "other", "normalize", "", "normalize$FilesKt__UtilsKt", "Lkotlin/io/FilePathComponents;", "relativeTo", "base", "relativeToOrNull", "relativeToOrSelf", "resolve", "relative", "resolveSibling", "startsWith", "toRelativeString", "toRelativeStringOrNull", "toRelativeStringOrNull$FilesKt__UtilsKt", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/io/FilesKt")
class FilesKt__UtilsKt extends FilesKt__FileTreeWalkKt {
    public static final boolean copyRecursively(File file0, File file1, boolean z, Function2 function20) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(file1, "target");
        Intrinsics.checkNotNullParameter(function20, "onError");
        if(!file0.exists()) {
            return function20.invoke(file0, new NoSuchFileException(file0, null, "The source file doesn\'t exist.", 2, null)) != OnErrorAction.TERMINATE;
        }
        try {
            Iterator iterator0 = FilesKt.walkTopDown(file0).onFail(new Function2(function20) {
                final Function2 $onError;

                {
                    this.$onError = function20;
                    super(2);
                }

                @Override  // kotlin.jvm.functions.Function2
                public Object invoke(Object object0, Object object1) {
                    this.invoke(((File)object0), ((IOException)object1));
                    return Unit.INSTANCE;
                }

                public final void invoke(File file0, IOException iOException0) {
                    Intrinsics.checkNotNullParameter(file0, "f");
                    Intrinsics.checkNotNullParameter(iOException0, "e");
                    if(this.$onError.invoke(file0, iOException0) == OnErrorAction.TERMINATE) {
                        throw new TerminateException(file0);
                    }
                }
            }).iterator();
            while(true) {
                if(!iterator0.hasNext()) {
                    return true;
                }
                Object object0 = iterator0.next();
                File file2 = (File)object0;
                if(!file2.exists()) {
                    if(function20.invoke(file2, new NoSuchFileException(file2, null, "The source file doesn\'t exist.", 2, null)) != OnErrorAction.TERMINATE) {
                        continue;
                    }
                    return false;
                }
                File file3 = new File(file1, FilesKt.toRelativeString(file2, file0));
                if(file3.exists() && (!file2.isDirectory() || !file3.isDirectory()) && (!z || !FilesKt.deleteRecursively(file3) || !file3.isDirectory() && !file3.delete())) {
                    if(function20.invoke(file3, new FileAlreadyExistsException(file2, file3, "The destination file already exists.")) != OnErrorAction.TERMINATE) {
                        continue;
                    }
                    return false;
                }
                if(file2.isDirectory()) {
                    file3.mkdirs();
                }
                else if(FilesKt.copyTo$default(file2, file3, z, 0, 4, null).length() != file2.length() && function20.invoke(file2, new IOException("Source file wasn\'t copied completely, length of destination file differs.")) == OnErrorAction.TERMINATE) {
                    break;
                }
            }
        }
        catch(TerminateException unused_ex) {
        }
        return false;
    }

    public static boolean copyRecursively$default(File file0, File file1, boolean z, Function2 function20, int v, Object object0) {
        if((v & 2) != 0) {
            z = false;
        }
        if((v & 4) != 0) {
            function20 = kotlin.io.FilesKt__UtilsKt.copyRecursively.1.INSTANCE;
        }
        return FilesKt.copyRecursively(file0, file1, z, function20);

        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Ljava/io/File;", "exception", "Ljava/io/IOException;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 0x30)
        final class kotlin.io.FilesKt__UtilsKt.copyRecursively.1 extends Lambda implements Function2 {
            public static final kotlin.io.FilesKt__UtilsKt.copyRecursively.1 INSTANCE;

            static {
                kotlin.io.FilesKt__UtilsKt.copyRecursively.1.INSTANCE = new kotlin.io.FilesKt__UtilsKt.copyRecursively.1();
            }

            kotlin.io.FilesKt__UtilsKt.copyRecursively.1() {
                super(2);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((File)object0), ((IOException)object1));
            }

            public final Void invoke(File file0, IOException iOException0) {
                Intrinsics.checkNotNullParameter(file0, "<anonymous parameter 0>");
                Intrinsics.checkNotNullParameter(iOException0, "exception");
                throw iOException0;
            }
        }

    }

    public static final File copyTo(File file0, File file1, boolean z, int v) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(file1, "target");
        if(!file0.exists()) {
            throw new NoSuchFileException(file0, null, "The source file doesn\'t exist.", 2, null);
        }
        if(file1.exists()) {
            if(!z) {
                throw new FileAlreadyExistsException(file0, file1, "The destination file already exists.");
            }
            if(!file1.delete()) {
                throw new FileAlreadyExistsException(file0, file1, "Tried to overwrite the destination, but failed to delete it.");
            }
        }
        if(file0.isDirectory()) {
            if(!file1.mkdirs()) {
                throw new FileSystemException(file0, file1, "Failed to create target directory.");
            }
            return file1;
        }
        File file2 = file1.getParentFile();
        if(file2 != null) {
            file2.mkdirs();
        }
        Closeable closeable0 = new FileInputStream(file0);
        try {
            FileInputStream fileInputStream0 = (FileInputStream)closeable0;
            Closeable closeable1 = new FileOutputStream(file1);
            try {
                ByteStreamsKt.copyTo(fileInputStream0, ((FileOutputStream)closeable1), v);
            }
            catch(Throwable throwable1) {
                CloseableKt.closeFinally(closeable1, throwable1);
                throw throwable1;
            }
            CloseableKt.closeFinally(closeable1, null);
        }
        catch(Throwable throwable0) {
            CloseableKt.closeFinally(closeable0, throwable0);
            throw throwable0;
        }
        CloseableKt.closeFinally(closeable0, null);
        return file1;
    }

    public static File copyTo$default(File file0, File file1, boolean z, int v, int v1, Object object0) {
        if((v1 & 2) != 0) {
            z = false;
        }
        if((v1 & 4) != 0) {
            v = 0x2000;
        }
        return FilesKt.copyTo(file0, file1, z, v);
    }

    @Deprecated(message = "Avoid creating temporary directories in the default temp location with this function due to too wide permissions on the newly created directory. Use kotlin.io.path.createTempDirectory instead.")
    public static final File createTempDir(String s, String s1, File file0) {
        Intrinsics.checkNotNullParameter(s, "prefix");
        File file1 = File.createTempFile(s, s1, file0);
        file1.delete();
        if(!file1.mkdir()) {
            throw new IOException("Unable to create temporary directory " + file1 + '.');
        }
        Intrinsics.checkNotNullExpressionValue(file1, "dir");
        return file1;
    }

    public static File createTempDir$default(String s, String s1, File file0, int v, Object object0) {
        if((v & 1) != 0) {
            s = "tmp";
        }
        if((v & 2) != 0) {
            s1 = null;
        }
        if((v & 4) != 0) {
            file0 = null;
        }
        return FilesKt.createTempDir(s, s1, file0);
    }

    @Deprecated(message = "Avoid creating temporary files in the default temp location with this function due to too wide permissions on the newly created file. Use kotlin.io.path.createTempFile instead or resort to java.io.File.createTempFile.")
    public static final File createTempFile(String s, String s1, File file0) {
        Intrinsics.checkNotNullParameter(s, "prefix");
        File file1 = File.createTempFile(s, s1, file0);
        Intrinsics.checkNotNullExpressionValue(file1, "createTempFile(prefix, suffix, directory)");
        return file1;
    }

    public static File createTempFile$default(String s, String s1, File file0, int v, Object object0) {
        if((v & 1) != 0) {
            s = "tmp";
        }
        if((v & 2) != 0) {
            s1 = null;
        }
        if((v & 4) != 0) {
            file0 = null;
        }
        return FilesKt.createTempFile(s, s1, file0);
    }

    public static final boolean deleteRecursively(File file0) {
        boolean z;
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Iterator iterator0 = FilesKt.walkBottomUp(file0).iterator();
    alab1:
        while(true) {
            for(z = true; true; z = false) {
                if(!iterator0.hasNext()) {
                    break alab1;
                }
                Object object0 = iterator0.next();
                if((((File)object0).delete() || !((File)object0).exists()) && z) {
                    break;
                }
            }
        }
        return z;
    }

    public static final boolean endsWith(File file0, File file1) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(file1, "other");
        FilePathComponents filePathComponents0 = FilesKt.toComponents(file0);
        FilePathComponents filePathComponents1 = FilesKt.toComponents(file1);
        if(filePathComponents1.isRooted()) {
            return Intrinsics.areEqual(file0, file1);
        }
        int v = filePathComponents0.getSize() - filePathComponents1.getSize();
        return v >= 0 ? filePathComponents0.getSegments().subList(v, filePathComponents0.getSize()).equals(filePathComponents1.getSegments()) : false;
    }

    public static final boolean endsWith(File file0, String s) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(s, "other");
        return FilesKt.endsWith(file0, new File(s));
    }

    public static final String getExtension(File file0) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        String s = file0.getName();
        Intrinsics.checkNotNullExpressionValue(s, "name");
        return StringsKt.substringAfterLast(s, '.', "");
    }

    public static final String getInvariantSeparatorsPath(File file0) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        if(File.separatorChar != 0x2F) {
            String s = file0.getPath();
            Intrinsics.checkNotNullExpressionValue(s, "path");
            return StringsKt.replace$default(s, File.separatorChar, '/', false, 4, null);
        }
        String s1 = file0.getPath();
        Intrinsics.checkNotNullExpressionValue(s1, "path");
        return s1;
    }

    public static final String getNameWithoutExtension(File file0) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        String s = file0.getName();
        Intrinsics.checkNotNullExpressionValue(s, "name");
        return StringsKt.substringBeforeLast$default(s, ".", null, 2, null);
    }

    public static final File normalize(File file0) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        FilePathComponents filePathComponents0 = FilesKt.toComponents(file0);
        List list0 = FilesKt__UtilsKt.normalize$FilesKt__UtilsKt(filePathComponents0.getSegments());
        Intrinsics.checkNotNullExpressionValue("/", "separator");
        return FilesKt.resolve(filePathComponents0.getRoot(), CollectionsKt.joinToString$default(list0, "/", null, null, 0, null, null, 62, null));
    }

    private static final List normalize$FilesKt__UtilsKt(List list0) {
        ArrayList arrayList0 = new ArrayList(list0.size());
        for(Object object0: list0) {
            File file0 = (File)object0;
            String s = file0.getName();
            if(Intrinsics.areEqual(s, ".")) {
            }
            else if(!Intrinsics.areEqual(s, "..")) {
                arrayList0.add(file0);
            }
            else if(arrayList0.isEmpty() || Intrinsics.areEqual(((File)CollectionsKt.last(arrayList0)).getName(), "..")) {
                arrayList0.add(file0);
            }
            else {
                arrayList0.remove(arrayList0.size() - 1);
            }
        }
        return arrayList0;
    }

    private static final FilePathComponents normalize$FilesKt__UtilsKt(FilePathComponents filePathComponents0) {
        return new FilePathComponents(filePathComponents0.getRoot(), FilesKt__UtilsKt.normalize$FilesKt__UtilsKt(filePathComponents0.getSegments()));
    }

    public static final File relativeTo(File file0, File file1) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(file1, "base");
        return new File(FilesKt.toRelativeString(file0, file1));
    }

    public static final File relativeToOrNull(File file0, File file1) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(file1, "base");
        String s = FilesKt__UtilsKt.toRelativeStringOrNull$FilesKt__UtilsKt(file0, file1);
        return s == null ? null : new File(s);
    }

    public static final File relativeToOrSelf(File file0, File file1) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(file1, "base");
        String s = FilesKt__UtilsKt.toRelativeStringOrNull$FilesKt__UtilsKt(file0, file1);
        return s == null ? file0 : new File(s);
    }

    public static final File resolve(File file0, File file1) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(file1, "relative");
        if(FilesKt.isRooted(file1)) {
            return file1;
        }
        String s = file0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "this.toString()");
        return s.length() != 0 && !StringsKt.endsWith$default(s, File.separatorChar, false, 2, null) ? new File(s + File.separatorChar + file1) : new File(s + file1);
    }

    public static final File resolve(File file0, String s) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(s, "relative");
        return FilesKt.resolve(file0, new File(s));
    }

    public static final File resolveSibling(File file0, File file1) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(file1, "relative");
        FilePathComponents filePathComponents0 = FilesKt.toComponents(file0);
        return filePathComponents0.getSize() == 0 ? FilesKt.resolve(FilesKt.resolve(filePathComponents0.getRoot(), new File("..")), file1) : FilesKt.resolve(FilesKt.resolve(filePathComponents0.getRoot(), filePathComponents0.subPath(0, filePathComponents0.getSize() - 1)), file1);
    }

    public static final File resolveSibling(File file0, String s) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(s, "relative");
        return FilesKt.resolveSibling(file0, new File(s));
    }

    public static final boolean startsWith(File file0, File file1) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(file1, "other");
        FilePathComponents filePathComponents0 = FilesKt.toComponents(file0);
        FilePathComponents filePathComponents1 = FilesKt.toComponents(file1);
        if(!Intrinsics.areEqual(filePathComponents0.getRoot(), filePathComponents1.getRoot())) {
            return false;
        }
        return filePathComponents0.getSize() >= filePathComponents1.getSize() ? filePathComponents0.getSegments().subList(0, filePathComponents1.getSize()).equals(filePathComponents1.getSegments()) : false;
    }

    public static final boolean startsWith(File file0, String s) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(s, "other");
        return FilesKt.startsWith(file0, new File(s));
    }

    public static final String toRelativeString(File file0, File file1) {
        Intrinsics.checkNotNullParameter(file0, "<this>");
        Intrinsics.checkNotNullParameter(file1, "base");
        String s = FilesKt__UtilsKt.toRelativeStringOrNull$FilesKt__UtilsKt(file0, file1);
        if(s == null) {
            throw new IllegalArgumentException("this and base files have different roots: " + file0 + " and " + file1 + '.');
        }
        return s;
    }

    private static final String toRelativeStringOrNull$FilesKt__UtilsKt(File file0, File file1) {
        FilePathComponents filePathComponents0 = FilesKt__UtilsKt.normalize$FilesKt__UtilsKt(FilesKt.toComponents(file0));
        FilePathComponents filePathComponents1 = FilesKt__UtilsKt.normalize$FilesKt__UtilsKt(FilesKt.toComponents(file1));
        if(!Intrinsics.areEqual(filePathComponents0.getRoot(), filePathComponents1.getRoot())) {
            return null;
        }
        int v = filePathComponents1.getSize();
        int v1 = filePathComponents0.getSize();
        int v2 = Math.min(v1, v);
        int v3;
        for(v3 = 0; v3 < v2 && Intrinsics.areEqual(filePathComponents0.getSegments().get(v3), filePathComponents1.getSegments().get(v3)); ++v3) {
        }
        StringBuilder stringBuilder0 = new StringBuilder();
        int v4 = v - 1;
        if(v3 <= v4) {
            while(true) {
                if(Intrinsics.areEqual(((File)filePathComponents1.getSegments().get(v4)).getName(), "..")) {
                    return null;
                }
                stringBuilder0.append("..");
                if(v4 != v3) {
                    stringBuilder0.append(File.separatorChar);
                }
                if(v4 == v3) {
                    break;
                }
                --v4;
            }
        }
        if(v3 < v1) {
            if(v3 < v) {
                stringBuilder0.append(File.separatorChar);
            }
            List list0 = CollectionsKt.drop(filePathComponents0.getSegments(), v3);
            Intrinsics.checkNotNullExpressionValue("/", "separator");
            CollectionsKt.joinTo$default(list0, stringBuilder0, "/", null, null, 0, null, null, 0x7C, null);
        }
        return stringBuilder0.toString();
    }
}

