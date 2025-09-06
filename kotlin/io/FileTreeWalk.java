package kotlin.io;

import java.io.File;
import java.util.ArrayDeque;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.AbstractIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010(\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\u001A\u001B\u001CB\u0019\b\u0010\u0012\u0006\u0010\u0003\u001A\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0005\u00A2\u0006\u0002\u0010\u0006B\u0089\u0001\b\u0002\u0012\u0006\u0010\u0003\u001A\u00020\u0002\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0005\u0012\u0014\u0010\u0007\u001A\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u0012\u0014\u0010\n\u001A\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000B\u0018\u00010\b\u00128\u0010\f\u001A4\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u000B\u0018\u00010\r\u0012\b\b\u0002\u0010\u0013\u001A\u00020\u0014\u00A2\u0006\u0002\u0010\u0015J\u000F\u0010\u0016\u001A\b\u0012\u0004\u0012\u00020\u00020\u0017H\u0096\u0002J\u000E\u0010\u0013\u001A\u00020\u00002\u0006\u0010\u0018\u001A\u00020\u0014J\u001A\u0010\u0007\u001A\u00020\u00002\u0012\u0010\u0019\u001A\u000E\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t0\bJ \u0010\f\u001A\u00020\u00002\u0018\u0010\u0019\u001A\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000B0\rJ\u001A\u0010\n\u001A\u00020\u00002\u0012\u0010\u0019\u001A\u000E\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000B0\bR\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0013\u001A\u00020\u0014X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001C\u0010\u0007\u001A\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u0004\u00A2\u0006\u0002\n\u0000R@\u0010\f\u001A4\u0012\u0013\u0012\u00110\u0002\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011\u00A2\u0006\f\b\u000E\u0012\b\b\u000F\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u000B\u0018\u00010\rX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001C\u0010\n\u001A\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000B\u0018\u00010\bX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0003\u001A\u00020\u0002X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006\u001D"}, d2 = {"Lkotlin/io/FileTreeWalk;", "Lkotlin/sequences/Sequence;", "Ljava/io/File;", "start", "direction", "Lkotlin/io/FileWalkDirection;", "(Ljava/io/File;Lkotlin/io/FileWalkDirection;)V", "onEnter", "Lkotlin/Function1;", "", "onLeave", "", "onFail", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "f", "Ljava/io/IOException;", "e", "maxDepth", "", "(Ljava/io/File;Lkotlin/io/FileWalkDirection;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;I)V", "iterator", "", "depth", "function", "DirectoryState", "FileTreeWalkIterator", "WalkState", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class FileTreeWalk implements Sequence {
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\"\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lkotlin/io/FileTreeWalk$DirectoryState;", "Lkotlin/io/FileTreeWalk$WalkState;", "rootDir", "Ljava/io/File;", "(Ljava/io/File;)V", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    static abstract class DirectoryState extends WalkState {
        public DirectoryState(File file0) {
            Intrinsics.checkNotNullParameter(file0, "rootDir");
            super(file0);
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003\r\u000E\u000FB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0007\u001A\u00020\bH\u0014J\u0010\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\u0002H\u0002J\u000B\u0010\f\u001A\u0004\u0018\u00010\u0002H\u0082\u0010R\u0014\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;", "Lkotlin/collections/AbstractIterator;", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk;)V", "state", "Ljava/util/ArrayDeque;", "Lkotlin/io/FileTreeWalk$WalkState;", "computeNext", "", "directoryState", "Lkotlin/io/FileTreeWalk$DirectoryState;", "root", "gotoNext", "BottomUpDirectoryState", "SingleFileState", "TopDownDirectoryState", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    final class FileTreeWalkIterator extends AbstractIterator {
        @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\r\u001A\u0004\u0018\u00010\u0003H\u0016R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u000E¢\u0006\u0002\n\u0000R\u0018\u0010\t\u001A\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\nX\u0082\u000E¢\u0006\u0004\n\u0002\u0010\u000BR\u000E\u0010\f\u001A\u00020\u0006X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u000E"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$BottomUpDirectoryState;", "Lkotlin/io/FileTreeWalk$DirectoryState;", "rootDir", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "failed", "", "fileIndex", "", "fileList", "", "[Ljava/io/File;", "rootVisited", "step", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
        final class BottomUpDirectoryState extends DirectoryState {
            private boolean failed;
            private int fileIndex;
            private File[] fileList;
            private boolean rootVisited;

            public BottomUpDirectoryState(File file0) {
                Intrinsics.checkNotNullParameter(file0, "rootDir");
                FileTreeWalkIterator.this = fileTreeWalk$FileTreeWalkIterator0;
                super(file0);
            }

            @Override  // kotlin.io.FileTreeWalk$WalkState
            public File step() {
                if(!this.failed && this.fileList == null) {
                    Function1 function10 = FileTreeWalk.this.onEnter;
                    if(function10 != null && !((Boolean)function10.invoke(this.getRoot())).booleanValue()) {
                        return null;
                    }
                    File[] arr_file = this.getRoot().listFiles();
                    this.fileList = arr_file;
                    if(arr_file == null) {
                        Function2 function20 = FileTreeWalk.this.onFail;
                        if(function20 != null) {
                            function20.invoke(this.getRoot(), new AccessDeniedException(this.getRoot(), null, "Cannot list files in a directory", 2, null));
                        }
                        this.failed = true;
                    }
                }
                File[] arr_file1 = this.fileList;
                if(arr_file1 != null) {
                    int v = this.fileIndex;
                    Intrinsics.checkNotNull(arr_file1);
                    if(v < arr_file1.length) {
                        File[] arr_file2 = this.fileList;
                        Intrinsics.checkNotNull(arr_file2);
                        int v1 = this.fileIndex;
                        this.fileIndex = v1 + 1;
                        return arr_file2[v1];
                    }
                }
                if(!this.rootVisited) {
                    this.rootVisited = true;
                    return this.getRoot();
                }
                Function1 function11 = FileTreeWalk.this.onLeave;
                if(function11 != null) {
                    function11.invoke(this.getRoot());
                }
                return null;
            }
        }

        @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0007\u001A\u0004\u0018\u00010\u0003H\u0016R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$SingleFileState;", "Lkotlin/io/FileTreeWalk$WalkState;", "rootFile", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "visited", "", "step", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
        final class SingleFileState extends WalkState {
            private boolean visited;

            public SingleFileState(File file0) {
                Intrinsics.checkNotNullParameter(file0, "rootFile");
                FileTreeWalkIterator.this = fileTreeWalk$FileTreeWalkIterator0;
                super(file0);
            }

            @Override  // kotlin.io.FileTreeWalk$WalkState
            public File step() {
                if(this.visited) {
                    return null;
                }
                this.visited = true;
                return this.getRoot();
            }
        }

        @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\f\u001A\u0004\u0018\u00010\u0003H\u0016R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u000E¢\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001A\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\bX\u0082\u000E¢\u0006\u0004\n\u0002\u0010\tR\u000E\u0010\n\u001A\u00020\u000BX\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlin/io/FileTreeWalk$FileTreeWalkIterator$TopDownDirectoryState;", "Lkotlin/io/FileTreeWalk$DirectoryState;", "rootDir", "Ljava/io/File;", "(Lkotlin/io/FileTreeWalk$FileTreeWalkIterator;Ljava/io/File;)V", "fileIndex", "", "fileList", "", "[Ljava/io/File;", "rootVisited", "", "step", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
        final class TopDownDirectoryState extends DirectoryState {
            private int fileIndex;
            private File[] fileList;
            private boolean rootVisited;

            public TopDownDirectoryState(File file0) {
                Intrinsics.checkNotNullParameter(file0, "rootDir");
                FileTreeWalkIterator.this = fileTreeWalk$FileTreeWalkIterator0;
                super(file0);
            }

            @Override  // kotlin.io.FileTreeWalk$WalkState
            public File step() {
                if(!this.rootVisited) {
                    Function1 function10 = FileTreeWalk.this.onEnter;
                    if(function10 != null && !((Boolean)function10.invoke(this.getRoot())).booleanValue()) {
                        return null;
                    }
                    this.rootVisited = true;
                    return this.getRoot();
                }
                File[] arr_file = this.fileList;
                if(arr_file != null) {
                    int v = this.fileIndex;
                    Intrinsics.checkNotNull(arr_file);
                    if(v >= arr_file.length) {
                        Function1 function11 = FileTreeWalk.this.onLeave;
                        if(function11 != null) {
                            function11.invoke(this.getRoot());
                        }
                        return null;
                    }
                }
                if(this.fileList == null) {
                    File[] arr_file1 = this.getRoot().listFiles();
                    this.fileList = arr_file1;
                    if(arr_file1 == null) {
                        Function2 function20 = FileTreeWalk.this.onFail;
                        if(function20 != null) {
                            function20.invoke(this.getRoot(), new AccessDeniedException(this.getRoot(), null, "Cannot list files in a directory", 2, null));
                        }
                    }
                    File[] arr_file2 = this.fileList;
                    if(arr_file2 == null) {
                        goto label_26;
                    }
                    Intrinsics.checkNotNull(arr_file2);
                    if(arr_file2.length == 0) {
                    label_26:
                        Function1 function12 = FileTreeWalk.this.onLeave;
                        if(function12 != null) {
                            function12.invoke(this.getRoot());
                        }
                        return null;
                    }
                }
                File[] arr_file3 = this.fileList;
                Intrinsics.checkNotNull(arr_file3);
                int v1 = this.fileIndex;
                this.fileIndex = v1 + 1;
                return arr_file3[v1];
            }
        }

        @Metadata(k = 3, mv = {1, 9, 0}, xi = 0x30)
        public final class WhenMappings {
            public static final int[] $EnumSwitchMapping$0;

            static {
                int[] arr_v = new int[FileWalkDirection.values().length];
                try {
                    arr_v[FileWalkDirection.TOP_DOWN.ordinal()] = 1;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                try {
                    arr_v[FileWalkDirection.BOTTOM_UP.ordinal()] = 2;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                WhenMappings.$EnumSwitchMapping$0 = arr_v;
            }
        }

        private final ArrayDeque state;

        public FileTreeWalkIterator() {
            ArrayDeque arrayDeque0 = new ArrayDeque();
            this.state = arrayDeque0;
            if(fileTreeWalk0.start.isDirectory()) {
                arrayDeque0.push(this.directoryState(fileTreeWalk0.start));
                return;
            }
            if(fileTreeWalk0.start.isFile()) {
                arrayDeque0.push(new SingleFileState(this, fileTreeWalk0.start));
                return;
            }
            this.done();
        }

        @Override  // kotlin.collections.AbstractIterator
        protected void computeNext() {
            File file0 = this.gotoNext();
            if(file0 != null) {
                this.setNext(file0);
                return;
            }
            this.done();
        }

        private final DirectoryState directoryState(File file0) {
            switch(WhenMappings.$EnumSwitchMapping$0[FileTreeWalk.this.direction.ordinal()]) {
                case 1: {
                    return new TopDownDirectoryState(this, file0);
                }
                case 2: {
                    return new BottomUpDirectoryState(this, file0);
                }
                default: {
                    throw new NoWhenBranchMatchedException();
                }
            }
        }

        private final File gotoNext() {
            File file0;
            while(true) {
                WalkState fileTreeWalk$WalkState0 = (WalkState)this.state.peek();
                if(fileTreeWalk$WalkState0 == null) {
                    return null;
                }
                file0 = fileTreeWalk$WalkState0.step();
                if(file0 == null) {
                    this.state.pop();
                }
                else {
                    if(Intrinsics.areEqual(file0, fileTreeWalk$WalkState0.getRoot()) || !file0.isDirectory() || this.state.size() >= FileTreeWalk.this.maxDepth) {
                        break;
                    }
                    DirectoryState fileTreeWalk$DirectoryState0 = this.directoryState(file0);
                    this.state.push(fileTreeWalk$DirectoryState0);
                }
            }
            return file0;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\"\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\n\u0010\u0007\u001A\u0004\u0018\u00010\u0003H&R\u0011\u0010\u0002\u001A\u00020\u0003¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"Lkotlin/io/FileTreeWalk$WalkState;", "", "root", "Ljava/io/File;", "(Ljava/io/File;)V", "getRoot", "()Ljava/io/File;", "step", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    static abstract class WalkState {
        private final File root;

        public WalkState(File file0) {
            Intrinsics.checkNotNullParameter(file0, "root");
            super();
            this.root = file0;
        }

        public final File getRoot() {
            return this.root;
        }

        public abstract File step();
    }

    private final FileWalkDirection direction;
    private final int maxDepth;
    private final Function1 onEnter;
    private final Function2 onFail;
    private final Function1 onLeave;
    private final File start;

    public FileTreeWalk(File file0, FileWalkDirection fileWalkDirection0) {
        Intrinsics.checkNotNullParameter(file0, "start");
        Intrinsics.checkNotNullParameter(fileWalkDirection0, "direction");
        this(file0, fileWalkDirection0, null, null, null, 0, 0x20, null);
    }

    public FileTreeWalk(File file0, FileWalkDirection fileWalkDirection0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 2) != 0) {
            fileWalkDirection0 = FileWalkDirection.TOP_DOWN;
        }
        this(file0, fileWalkDirection0);
    }

    private FileTreeWalk(File file0, FileWalkDirection fileWalkDirection0, Function1 function10, Function1 function11, Function2 function20, int v) {
        this.start = file0;
        this.direction = fileWalkDirection0;
        this.onEnter = function10;
        this.onLeave = function11;
        this.onFail = function20;
        this.maxDepth = v;
    }

    FileTreeWalk(File file0, FileWalkDirection fileWalkDirection0, Function1 function10, Function1 function11, Function2 function20, int v, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 2) != 0) {
            fileWalkDirection0 = FileWalkDirection.TOP_DOWN;
        }
        this(file0, fileWalkDirection0, function10, function11, function20, ((v1 & 0x20) == 0 ? v : 0x7FFFFFFF));
    }

    @Override  // kotlin.sequences.Sequence
    public Iterator iterator() {
        return new FileTreeWalkIterator(this);
    }

    public final FileTreeWalk maxDepth(int v) {
        if(v <= 0) {
            throw new IllegalArgumentException("depth must be positive, but was " + v + '.');
        }
        return new FileTreeWalk(this.start, this.direction, this.onEnter, this.onLeave, this.onFail, v);
    }

    public final FileTreeWalk onEnter(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "function");
        return new FileTreeWalk(this.start, this.direction, function10, this.onLeave, this.onFail, this.maxDepth);
    }

    public final FileTreeWalk onFail(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "function");
        return new FileTreeWalk(this.start, this.direction, this.onEnter, this.onLeave, function20, this.maxDepth);
    }

    public final FileTreeWalk onLeave(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "function");
        return new FileTreeWalk(this.start, this.direction, this.onEnter, function10, this.onFail, this.maxDepth);
    }
}

