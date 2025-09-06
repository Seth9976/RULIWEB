package kotlin.io.path;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystemException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.SecureDirectoryStream;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics.Kotlin;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SpreadBuilder;

@Metadata(d1 = {"\u0000v\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A$\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0082\b\u00A2\u0006\u0002\b\u0006\u001A\u001D\u0010\u0007\u001A\u00020\u00012\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u0002\u001A\u00020\u0003H\u0002\u00A2\u0006\u0002\b\n\u001A\u001D\u0010\u000B\u001A\u00020\u00012\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\u0002\u001A\u00020\u0003H\u0002\u00A2\u0006\u0002\b\r\u001A&\u0010\u000E\u001A\u0004\u0018\u0001H\u000F\"\u0004\b\u0000\u0010\u000F2\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u000F0\u0005H\u0082\b\u00A2\u0006\u0004\b\u0010\u0010\u0011\u001Aw\u0010\u0012\u001A\u00020\t*\u00020\t2\u0006\u0010\u0013\u001A\u00020\t2Q\b\u0002\u0010\u0014\u001AK\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0013\u0012\u0017\u0012\u00150\u0019j\u0002`\u001A\u00A2\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001B\u0012\u0004\u0012\u00020\u001C0\u00152\u0006\u0010\u001D\u001A\u00020\u001E2\u0006\u0010\u001F\u001A\u00020\u001EH\u0007\u001A\u00B4\u0001\u0010\u0012\u001A\u00020\t*\u00020\t2\u0006\u0010\u0013\u001A\u00020\t2Q\b\u0002\u0010\u0014\u001AK\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0013\u0012\u0017\u0012\u00150\u0019j\u0002`\u001A\u00A2\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u001B\u0012\u0004\u0012\u00020\u001C0\u00152\u0006\u0010\u001D\u001A\u00020\u001E2C\b\u0002\u0010 \u001A=\u0012\u0004\u0012\u00020!\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0018\u0012\u0013\u0012\u00110\t\u00A2\u0006\f\b\u0016\u0012\b\b\u0017\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\"0\u0015\u00A2\u0006\u0002\b#H\u0007\u001A\f\u0010$\u001A\u00020\u0001*\u00020\tH\u0007\u001A\u001B\u0010%\u001A\f\u0012\b\u0012\u00060\u0019j\u0002`\u001A0&*\u00020\tH\u0002\u00A2\u0006\u0002\b\'\u001A\'\u0010(\u001A\u00020\u0001*\b\u0012\u0004\u0012\u00020\t0)2\u0006\u0010\u0017\u001A\u00020\t2\u0006\u0010\u0002\u001A\u00020\u0003H\u0002\u00A2\u0006\u0002\b*\u001A\'\u0010+\u001A\u00020\u0001*\b\u0012\u0004\u0012\u00020\t0)2\u0006\u0010\u0017\u001A\u00020\t2\u0006\u0010\u0002\u001A\u00020\u0003H\u0002\u00A2\u0006\u0002\b,\u001A5\u0010-\u001A\u00020\u001E*\b\u0012\u0004\u0012\u00020\t0)2\u0006\u0010.\u001A\u00020\t2\u0012\u0010/\u001A\n\u0012\u0006\b\u0001\u0012\u00020100\"\u000201H\u0002\u00A2\u0006\u0004\b2\u00103\u001A\u0011\u00104\u001A\u000205*\u00020\"H\u0003\u00A2\u0006\u0002\b6\u001A\u0011\u00104\u001A\u000205*\u00020\u001CH\u0003\u00A2\u0006\u0002\b6\u00A8\u00067"}, d2 = {"collectIfThrows", "", "collector", "Lkotlin/io/path/ExceptionsCollector;", "function", "Lkotlin/Function0;", "collectIfThrows$PathsKt__PathRecursiveFunctionsKt", "insecureEnterDirectory", "path", "Ljava/nio/file/Path;", "insecureEnterDirectory$PathsKt__PathRecursiveFunctionsKt", "insecureHandleEntry", "entry", "insecureHandleEntry$PathsKt__PathRecursiveFunctionsKt", "tryIgnoreNoSuchFileException", "R", "tryIgnoreNoSuchFileException$PathsKt__PathRecursiveFunctionsKt", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "copyToRecursively", "target", "onError", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "source", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "Lkotlin/io/path/OnErrorResult;", "followLinks", "", "overwrite", "copyAction", "Lkotlin/io/path/CopyActionContext;", "Lkotlin/io/path/CopyActionResult;", "Lkotlin/ExtensionFunctionType;", "deleteRecursively", "deleteRecursivelyImpl", "", "deleteRecursivelyImpl$PathsKt__PathRecursiveFunctionsKt", "enterDirectory", "Ljava/nio/file/SecureDirectoryStream;", "enterDirectory$PathsKt__PathRecursiveFunctionsKt", "handleEntry", "handleEntry$PathsKt__PathRecursiveFunctionsKt", "isDirectory", "entryName", "options", "", "Ljava/nio/file/LinkOption;", "isDirectory$PathsKt__PathRecursiveFunctionsKt", "(Ljava/nio/file/SecureDirectoryStream;Ljava/nio/file/Path;[Ljava/nio/file/LinkOption;)Z", "toFileVisitResult", "Ljava/nio/file/FileVisitResult;", "toFileVisitResult$PathsKt__PathRecursiveFunctionsKt", "kotlin-stdlib-jdk7"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/io/path/PathsKt")
class PathsKt__PathRecursiveFunctionsKt extends PathsKt__PathReadWriteKt {
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 0x30)
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;
        public static final int[] $EnumSwitchMapping$1;

        static {
            int[] arr_v = new int[CopyActionResult.values().length];
            try {
                arr_v[CopyActionResult.CONTINUE.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[CopyActionResult.TERMINATE.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[CopyActionResult.SKIP_SUBTREE.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
            int[] arr_v1 = new int[OnErrorResult.values().length];
            try {
                arr_v1[OnErrorResult.TERMINATE.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v1[OnErrorResult.SKIP_SUBTREE.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$1 = arr_v1;
        }
    }

    private static final void collectIfThrows$PathsKt__PathRecursiveFunctionsKt(ExceptionsCollector exceptionsCollector0, Function0 function00) {
        try {
            function00.invoke();
        }
        catch(Exception exception0) {
            exceptionsCollector0.collect(exception0);
        }
    }

    public static final Path copyToRecursively(Path path0, Path path1, Function3 function30, boolean z, Function3 function31) {
        boolean z1 = false;
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(path1, "target");
        Intrinsics.checkNotNullParameter(function30, "onError");
        Intrinsics.checkNotNullParameter(function31, "copyAction");
        LinkOption[] arr_linkOption = LinkFollowing.INSTANCE.toLinkOptions(z);
        LinkOption[] arr_linkOption1 = (LinkOption[])Arrays.copyOf(arr_linkOption, arr_linkOption.length);
        if(!PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path0, ((LinkOption[])Arrays.copyOf(arr_linkOption1, arr_linkOption1.length)))) {
            throw PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path0.toString(), path1.toString(), "The source file doesn\'t exist.");
        }
        if(PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path0, ((LinkOption[])Arrays.copyOf(new LinkOption[0], 0))) && (z || !PathTreeWalk..ExternalSyntheticApiModelOutline0.m$1(path0))) {
            boolean z2 = PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path1, ((LinkOption[])Arrays.copyOf(new LinkOption[0], 0))) && !PathTreeWalk..ExternalSyntheticApiModelOutline0.m$1(path1);
            if(!z2 || !PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path0, path1)) {
                if(Intrinsics.areEqual(path0.getFileSystem(), path1.getFileSystem())) {
                    if(z2) {
                        z1 = PathTreeWalk..ExternalSyntheticApiModelOutline0.m$1(path1.toRealPath(new LinkOption[0]), path0.toRealPath(new LinkOption[0]));
                    }
                    else {
                        Path path2 = LinkFollowing..ExternalSyntheticApiModelOutline0.m$1(path1);
                        if(path2 != null && PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path2, ((LinkOption[])Arrays.copyOf(new LinkOption[0], 0))) && PathTreeWalk..ExternalSyntheticApiModelOutline0.m$1(path2.toRealPath(new LinkOption[0]), path0.toRealPath(new LinkOption[0]))) {
                            z1 = true;
                        }
                    }
                }
                if(z1) {
                    throw PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path0.toString(), path1.toString(), "Recursively copying a directory into its subdirectory is prohibited.");
                }
            }
        }
        PathsKt.visitFileTree$default(path0, 0, z, new Function1(function31, path0, path1, function30) {
            final Function3 $copyAction;
            final Function3 $onError;
            final Path $target;
            final Path $this_copyToRecursively;

            {
                this.$copyAction = function30;
                this.$this_copyToRecursively = path0;
                this.$target = path1;
                this.$onError = function31;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((FileVisitorBuilder)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(FileVisitorBuilder fileVisitorBuilder0) {
                Intrinsics.checkNotNullParameter(fileVisitorBuilder0, "$this$visitFileTree");
                fileVisitorBuilder0.onPreVisitDirectory(new Function2(this.$this_copyToRecursively, this.$target, this.$onError) {
                    final Function3 $copyAction;
                    final Function3 $onError;
                    final Path $target;
                    final Path $this_copyToRecursively;

                    {
                        this.$copyAction = function30;
                        this.$this_copyToRecursively = path0;
                        this.$target = path1;
                        this.$onError = function31;
                        super(2, Kotlin.class, "copy", "copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(Lkotlin/jvm/functions/Function3;Ljava/nio/file/Path;Ljava/nio/file/Path;Lkotlin/jvm/functions/Function3;Ljava/nio/file/Path;Ljava/nio/file/attribute/BasicFileAttributes;)Ljava/nio/file/FileVisitResult;", 0);
                    }

                    @Override  // kotlin.jvm.functions.Function2
                    public Object invoke(Object object0, Object object1) {
                        return this.invoke(((Path)object0), ((BasicFileAttributes)object1));
                    }

                    public final FileVisitResult invoke(Path path0, BasicFileAttributes basicFileAttributes0) {
                        Intrinsics.checkNotNullParameter(path0, "p0");
                        Intrinsics.checkNotNullParameter(basicFileAttributes0, "p1");
                        return PathsKt__PathRecursiveFunctionsKt.copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(this.$copyAction, this.$this_copyToRecursively, this.$target, this.$onError, path0, basicFileAttributes0);
                    }
                });
                fileVisitorBuilder0.onVisitFile(new Function2(this.$this_copyToRecursively, this.$target, this.$onError) {
                    final Function3 $copyAction;
                    final Function3 $onError;
                    final Path $target;
                    final Path $this_copyToRecursively;

                    {
                        this.$copyAction = function30;
                        this.$this_copyToRecursively = path0;
                        this.$target = path1;
                        this.$onError = function31;
                        super(2, Kotlin.class, "copy", "copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(Lkotlin/jvm/functions/Function3;Ljava/nio/file/Path;Ljava/nio/file/Path;Lkotlin/jvm/functions/Function3;Ljava/nio/file/Path;Ljava/nio/file/attribute/BasicFileAttributes;)Ljava/nio/file/FileVisitResult;", 0);
                    }

                    @Override  // kotlin.jvm.functions.Function2
                    public Object invoke(Object object0, Object object1) {
                        return this.invoke(((Path)object0), ((BasicFileAttributes)object1));
                    }

                    public final FileVisitResult invoke(Path path0, BasicFileAttributes basicFileAttributes0) {
                        Intrinsics.checkNotNullParameter(path0, "p0");
                        Intrinsics.checkNotNullParameter(basicFileAttributes0, "p1");
                        return PathsKt__PathRecursiveFunctionsKt.copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(this.$copyAction, this.$this_copyToRecursively, this.$target, this.$onError, path0, basicFileAttributes0);
                    }
                });
                fileVisitorBuilder0.onVisitFileFailed(new Function2(this.$this_copyToRecursively, this.$target) {
                    final Function3 $onError;
                    final Path $target;
                    final Path $this_copyToRecursively;

                    {
                        this.$onError = function30;
                        this.$this_copyToRecursively = path0;
                        this.$target = path1;
                        super(2, Kotlin.class, "error", "copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt(Lkotlin/jvm/functions/Function3;Ljava/nio/file/Path;Ljava/nio/file/Path;Ljava/nio/file/Path;Ljava/lang/Exception;)Ljava/nio/file/FileVisitResult;", 0);
                    }

                    @Override  // kotlin.jvm.functions.Function2
                    public Object invoke(Object object0, Object object1) {
                        return this.invoke(((Path)object0), ((Exception)object1));
                    }

                    public final FileVisitResult invoke(Path path0, Exception exception0) {
                        Intrinsics.checkNotNullParameter(path0, "p0");
                        Intrinsics.checkNotNullParameter(exception0, "p1");
                        return PathsKt__PathRecursiveFunctionsKt.copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt(this.$onError, this.$this_copyToRecursively, this.$target, path0, exception0);
                    }
                });
                fileVisitorBuilder0.onPostVisitDirectory(new Function2(this.$this_copyToRecursively, this.$target) {
                    final Function3 $onError;
                    final Path $target;
                    final Path $this_copyToRecursively;

                    {
                        this.$onError = function30;
                        this.$this_copyToRecursively = path0;
                        this.$target = path1;
                        super(2);
                    }

                    @Override  // kotlin.jvm.functions.Function2
                    public Object invoke(Object object0, Object object1) {
                        return this.invoke(((Path)object0), ((IOException)object1));
                    }

                    public final FileVisitResult invoke(Path path0, IOException iOException0) {
                        Intrinsics.checkNotNullParameter(path0, "directory");
                        return iOException0 == null ? PathTreeWalk..ExternalSyntheticApiModelOutline0.m$2() : PathsKt__PathRecursiveFunctionsKt.copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt(this.$onError, this.$this_copyToRecursively, this.$target, path0, iOException0);
                    }
                });
            }
        }, 1, null);
        return path1;
    }

    public static final Path copyToRecursively(Path path0, Path path1, Function3 function30, boolean z, boolean z1) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        Intrinsics.checkNotNullParameter(path1, "target");
        Intrinsics.checkNotNullParameter(function30, "onError");
        return z1 ? PathsKt.copyToRecursively(path0, path1, function30, z, new Function3(z) {
            final boolean $followLinks;

            {
                this.$followLinks = z;
                super(3);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((CopyActionContext)object0), ((Path)object1), ((Path)object2));
            }

            public final CopyActionResult invoke(CopyActionContext copyActionContext0, Path path0, Path path1) {
                Intrinsics.checkNotNullParameter(copyActionContext0, "$this$copyToRecursively");
                Intrinsics.checkNotNullParameter(path0, "src");
                Intrinsics.checkNotNullParameter(path1, "dst");
                LinkOption[] arr_linkOption = LinkFollowing.INSTANCE.toLinkOptions(this.$followLinks);
                boolean z = LinkFollowing..ExternalSyntheticApiModelOutline0.m(path1, ((LinkOption[])Arrays.copyOf(new LinkOption[]{LinkOption.NOFOLLOW_LINKS}, 1)));
                LinkOption[] arr_linkOption1 = (LinkOption[])Arrays.copyOf(arr_linkOption, arr_linkOption.length);
                if(!LinkFollowing..ExternalSyntheticApiModelOutline0.m(path0, ((LinkOption[])Arrays.copyOf(arr_linkOption1, arr_linkOption1.length))) || !z) {
                    if(z) {
                        PathsKt.deleteRecursively(path1);
                    }
                    SpreadBuilder spreadBuilder0 = new SpreadBuilder(2);
                    spreadBuilder0.addSpread(arr_linkOption);
                    spreadBuilder0.add(StandardCopyOption.REPLACE_EXISTING);
                    CopyOption[] arr_copyOption = (CopyOption[])spreadBuilder0.toArray(new CopyOption[spreadBuilder0.size()]);
                    Intrinsics.checkNotNullExpressionValue(Files.copy(path0, path1, ((CopyOption[])Arrays.copyOf(arr_copyOption, arr_copyOption.length))), "copy(this, target, *options)");
                }
                return CopyActionResult.CONTINUE;
            }
        }) : PathsKt.copyToRecursively$default(path0, path1, function30, z, null, 8, null);
    }

    private static final FileVisitResult copyToRecursively$copy$PathsKt__PathRecursiveFunctionsKt(Function3 function30, Path path0, Path path1, Function3 function31, Path path2, BasicFileAttributes basicFileAttributes0) {
        try {
            Path path3 = PathsKt__PathRecursiveFunctionsKt.copyToRecursively$destination$PathsKt__PathRecursiveFunctionsKt(path0, path1, path2);
            return PathsKt__PathRecursiveFunctionsKt.toFileVisitResult$PathsKt__PathRecursiveFunctionsKt(((CopyActionResult)function30.invoke(DefaultCopyActionContext.INSTANCE, path2, path3)));
        }
        catch(Exception exception0) {
            return PathsKt__PathRecursiveFunctionsKt.copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt(function31, path0, path1, path2, exception0);
        }
    }

    public static Path copyToRecursively$default(Path path0, Path path1, Function3 function30, boolean z, Function3 function31, int v, Object object0) {
        if((v & 2) != 0) {
            function30 = kotlin.io.path.PathsKt__PathRecursiveFunctionsKt.copyToRecursively.3.INSTANCE;
        }
        if((v & 8) != 0) {
            function31 = new Function3(z) {
                final boolean $followLinks;

                {
                    this.$followLinks = z;
                    super(3);
                }

                @Override  // kotlin.jvm.functions.Function3
                public Object invoke(Object object0, Object object1, Object object2) {
                    return this.invoke(((CopyActionContext)object0), ((Path)object1), ((Path)object2));
                }

                public final CopyActionResult invoke(CopyActionContext copyActionContext0, Path path0, Path path1) {
                    Intrinsics.checkNotNullParameter(copyActionContext0, "$this$null");
                    Intrinsics.checkNotNullParameter(path0, "src");
                    Intrinsics.checkNotNullParameter(path1, "dst");
                    return copyActionContext0.copyToIgnoringExistingDirectory(path0, path1, this.$followLinks);
                }
            };
        }
        return PathsKt.copyToRecursively(path0, path1, function30, z, function31);

        @Metadata(d1 = {"\u0000\u001A\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00032\n\u0010\u0005\u001A\u00060\u0006j\u0002`\u0007H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Ljava/nio/file/Path;", "<anonymous parameter 1>", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 0x30)
        final class kotlin.io.path.PathsKt__PathRecursiveFunctionsKt.copyToRecursively.3 extends Lambda implements Function3 {
            public static final kotlin.io.path.PathsKt__PathRecursiveFunctionsKt.copyToRecursively.3 INSTANCE;

            static {
                kotlin.io.path.PathsKt__PathRecursiveFunctionsKt.copyToRecursively.3.INSTANCE = new kotlin.io.path.PathsKt__PathRecursiveFunctionsKt.copyToRecursively.3();
            }

            kotlin.io.path.PathsKt__PathRecursiveFunctionsKt.copyToRecursively.3() {
                super(3);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((Path)object0), ((Path)object1), ((Exception)object2));
            }

            public final Void invoke(Path path0, Path path1, Exception exception0) {
                Intrinsics.checkNotNullParameter(path0, "<anonymous parameter 0>");
                Intrinsics.checkNotNullParameter(path1, "<anonymous parameter 1>");
                Intrinsics.checkNotNullParameter(exception0, "exception");
                throw exception0;
            }
        }

    }

    public static Path copyToRecursively$default(Path path0, Path path1, Function3 function30, boolean z, boolean z1, int v, Object object0) {
        if((v & 2) != 0) {
            function30 = kotlin.io.path.PathsKt__PathRecursiveFunctionsKt.copyToRecursively.1.INSTANCE;
        }
        return PathsKt.copyToRecursively(path0, path1, function30, z, z1);

        @Metadata(d1 = {"\u0000\u001A\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u00032\n\u0010\u0005\u001A\u00060\u0006j\u0002`\u0007H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Ljava/nio/file/Path;", "<anonymous parameter 1>", "exception", "Ljava/lang/Exception;", "Lkotlin/Exception;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 0x30)
        final class kotlin.io.path.PathsKt__PathRecursiveFunctionsKt.copyToRecursively.1 extends Lambda implements Function3 {
            public static final kotlin.io.path.PathsKt__PathRecursiveFunctionsKt.copyToRecursively.1 INSTANCE;

            static {
                kotlin.io.path.PathsKt__PathRecursiveFunctionsKt.copyToRecursively.1.INSTANCE = new kotlin.io.path.PathsKt__PathRecursiveFunctionsKt.copyToRecursively.1();
            }

            kotlin.io.path.PathsKt__PathRecursiveFunctionsKt.copyToRecursively.1() {
                super(3);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((Path)object0), ((Path)object1), ((Exception)object2));
            }

            public final Void invoke(Path path0, Path path1, Exception exception0) {
                Intrinsics.checkNotNullParameter(path0, "<anonymous parameter 0>");
                Intrinsics.checkNotNullParameter(path1, "<anonymous parameter 1>");
                Intrinsics.checkNotNullParameter(exception0, "exception");
                throw exception0;
            }
        }

    }

    private static final Path copyToRecursively$destination$PathsKt__PathRecursiveFunctionsKt(Path path0, Path path1, Path path2) {
        Path path3 = path1.resolve(PathsKt.relativeTo(path2, path0).toString());
        Intrinsics.checkNotNullExpressionValue(path3, "target.resolve(relativePath.pathString)");
        return path3;
    }

    private static final FileVisitResult copyToRecursively$error$PathsKt__PathRecursiveFunctionsKt(Function3 function30, Path path0, Path path1, Path path2, Exception exception0) {
        return PathsKt__PathRecursiveFunctionsKt.toFileVisitResult$PathsKt__PathRecursiveFunctionsKt(((OnErrorResult)function30.invoke(path2, PathsKt__PathRecursiveFunctionsKt.copyToRecursively$destination$PathsKt__PathRecursiveFunctionsKt(path0, path1, path2), exception0)));
    }

    public static final void deleteRecursively(Path path0) {
        Intrinsics.checkNotNullParameter(path0, "<this>");
        List list0 = PathsKt__PathRecursiveFunctionsKt.deleteRecursivelyImpl$PathsKt__PathRecursiveFunctionsKt(path0);
        if(!list0.isEmpty()) {
            FileSystemException fileSystemException0 = LinkFollowing..ExternalSyntheticApiModelOutline0.m("Failed to delete one or more files. See suppressed exceptions for details.");
            for(Object object0: list0) {
                ExceptionsKt.addSuppressed(fileSystemException0, ((Exception)object0));
            }
            throw fileSystemException0;
        }
    }

    private static final List deleteRecursivelyImpl$PathsKt__PathRecursiveFunctionsKt(Path path0) {
        DirectoryStream directoryStream0;
        int v = 0;
        int v1 = 1;
        ExceptionsCollector exceptionsCollector0 = new ExceptionsCollector(0, 1, null);
        Path path1 = LinkFollowing..ExternalSyntheticApiModelOutline0.m$1(path0);
        if(path1 != null) {
            try {
                directoryStream0 = null;
                directoryStream0 = Files.newDirectoryStream(path1);
            }
            catch(Throwable unused_ex) {
            }
            if(directoryStream0 != null) {
                try {
                    if(PathTreeWalk..ExternalSyntheticApiModelOutline0.m(directoryStream0)) {
                        exceptionsCollector0.setPath(path1);
                        Path path2 = path0.getFileName();
                        Intrinsics.checkNotNullExpressionValue(path2, "this.fileName");
                        PathsKt__PathRecursiveFunctionsKt.handleEntry$PathsKt__PathRecursiveFunctionsKt(((SecureDirectoryStream)directoryStream0), path2, exceptionsCollector0);
                    }
                    else {
                        v = 1;
                    }
                }
                catch(Throwable throwable0) {
                    CloseableKt.closeFinally(directoryStream0, throwable0);
                    throw throwable0;
                }
                CloseableKt.closeFinally(directoryStream0, null);
                v1 = v;
            }
        }
        if(v1 != 0) {
            PathsKt__PathRecursiveFunctionsKt.insecureHandleEntry$PathsKt__PathRecursiveFunctionsKt(path0, exceptionsCollector0);
        }
        return exceptionsCollector0.getCollectedExceptions();
    }

    private static final void enterDirectory$PathsKt__PathRecursiveFunctionsKt(SecureDirectoryStream secureDirectoryStream0, Path path0, ExceptionsCollector exceptionsCollector0) {
        SecureDirectoryStream secureDirectoryStream1;
        try {
            try {
                secureDirectoryStream1 = null;
                secureDirectoryStream1 = secureDirectoryStream0.newDirectoryStream(path0, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
            }
            catch(NoSuchFileException unused_ex) {
            }
            if(secureDirectoryStream1 != null) {
                try {
                    for(Object object0: secureDirectoryStream1) {
                        Path path1 = ((Path)object0).getFileName();
                        Intrinsics.checkNotNullExpressionValue(path1, "entry.fileName");
                        PathsKt__PathRecursiveFunctionsKt.handleEntry$PathsKt__PathRecursiveFunctionsKt(secureDirectoryStream1, path1, exceptionsCollector0);
                    }
                }
                catch(Throwable throwable0) {
                    CloseableKt.closeFinally(secureDirectoryStream1, throwable0);
                    throw throwable0;
                }
                CloseableKt.closeFinally(secureDirectoryStream1, null);
            }
        }
        catch(Exception exception0) {
            exceptionsCollector0.collect(exception0);
        }
    }

    private static final void handleEntry$PathsKt__PathRecursiveFunctionsKt(SecureDirectoryStream secureDirectoryStream0, Path path0, ExceptionsCollector exceptionsCollector0) {
        exceptionsCollector0.enterEntry(path0);
        try {
            if(PathsKt__PathRecursiveFunctionsKt.isDirectory$PathsKt__PathRecursiveFunctionsKt(secureDirectoryStream0, path0, new LinkOption[]{LinkOption.NOFOLLOW_LINKS})) {
                PathsKt__PathRecursiveFunctionsKt.enterDirectory$PathsKt__PathRecursiveFunctionsKt(secureDirectoryStream0, path0, exceptionsCollector0);
                try {
                    secureDirectoryStream0.deleteDirectory(path0);
                    goto label_9;
                label_5:
                    PathTreeWalk..ExternalSyntheticApiModelOutline0.m$1(secureDirectoryStream0, path0);
                }
                catch(NoSuchFileException unused_ex) {
                }
            }
            else {
                goto label_5;
            }
        }
        catch(Exception exception0) {
            exceptionsCollector0.collect(exception0);
        }
    label_9:
        exceptionsCollector0.exitEntry(path0);
    }

    private static final void insecureEnterDirectory$PathsKt__PathRecursiveFunctionsKt(Path path0, ExceptionsCollector exceptionsCollector0) {
        DirectoryStream directoryStream0;
        try {
            try {
                directoryStream0 = null;
                directoryStream0 = Files.newDirectoryStream(path0);
            }
            catch(NoSuchFileException unused_ex) {
            }
            if(directoryStream0 != null) {
                try {
                    for(Object object0: directoryStream0) {
                        Intrinsics.checkNotNullExpressionValue(((Path)object0), "entry");
                        PathsKt__PathRecursiveFunctionsKt.insecureHandleEntry$PathsKt__PathRecursiveFunctionsKt(((Path)object0), exceptionsCollector0);
                    }
                }
                catch(Throwable throwable0) {
                    CloseableKt.closeFinally(directoryStream0, throwable0);
                    throw throwable0;
                }
                CloseableKt.closeFinally(directoryStream0, null);
            }
        }
        catch(Exception exception0) {
            exceptionsCollector0.collect(exception0);
        }
    }

    private static final void insecureHandleEntry$PathsKt__PathRecursiveFunctionsKt(Path path0, ExceptionsCollector exceptionsCollector0) {
        try {
            if(!Files.isDirectory(path0, ((LinkOption[])Arrays.copyOf(new LinkOption[]{LinkOption.NOFOLLOW_LINKS}, 1)))) {
                Files.deleteIfExists(path0);
                return;
            }
            PathsKt__PathRecursiveFunctionsKt.insecureEnterDirectory$PathsKt__PathRecursiveFunctionsKt(path0, exceptionsCollector0);
            if(exceptionsCollector0.getTotalExceptions() == exceptionsCollector0.getTotalExceptions()) {
                Files.deleteIfExists(path0);
            }
        }
        catch(Exception exception0) {
            exceptionsCollector0.collect(exception0);
        }
    }

    private static final boolean isDirectory$PathsKt__PathRecursiveFunctionsKt(SecureDirectoryStream secureDirectoryStream0, Path path0, LinkOption[] arr_linkOption) {
        Boolean boolean0 = null;
        try {
            boolean0 = Boolean.valueOf(PathTreeWalk..ExternalSyntheticApiModelOutline0.m(secureDirectoryStream0.getFileAttributeView(path0, PathTreeWalk..ExternalSyntheticApiModelOutline0.m$1(), ((LinkOption[])Arrays.copyOf(arr_linkOption, arr_linkOption.length)))).readAttributes().isDirectory());
        }
        catch(NoSuchFileException unused_ex) {
        }
        return boolean0 == null ? false : boolean0.booleanValue();
    }

    private static final FileVisitResult toFileVisitResult$PathsKt__PathRecursiveFunctionsKt(CopyActionResult copyActionResult0) {
        switch(WhenMappings.$EnumSwitchMapping$0[copyActionResult0.ordinal()]) {
            case 1: {
                return PathTreeWalk..ExternalSyntheticApiModelOutline0.m$2();
            }
            case 2: {
                return FileVisitResult.TERMINATE;
            }
            case 3: {
                return PathTreeWalk..ExternalSyntheticApiModelOutline0.m$1();
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    private static final FileVisitResult toFileVisitResult$PathsKt__PathRecursiveFunctionsKt(OnErrorResult onErrorResult0) {
        switch(WhenMappings.$EnumSwitchMapping$1[onErrorResult0.ordinal()]) {
            case 1: {
                return FileVisitResult.TERMINATE;
            }
            case 2: {
                return FileVisitResult.SKIP_SUBTREE;
            }
            default: {
                throw new NoWhenBranchMatchedException();
            }
        }
    }

    private static final Object tryIgnoreNoSuchFileException$PathsKt__PathRecursiveFunctionsKt(Function0 function00) {
        try {
            return function00.invoke();
        }
        catch(NoSuchFileException unused_ex) {
            return null;
        }
    }
}

