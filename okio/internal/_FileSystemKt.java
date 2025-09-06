package okio.internal;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;
import okio.BufferedSink;
import okio.FileMetadata;
import okio.FileSystem;
import okio.Okio;
import okio.Path;
import okio.Source;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001AI\u0010\u0000\u001A\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001A\u00020\u00052\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\u00030\u00072\u0006\u0010\b\u001A\u00020\u00032\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\nH\u0080@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001A\u001C\u0010\r\u001A\u00020\u0001*\u00020\u00052\u0006\u0010\u000E\u001A\u00020\u00032\u0006\u0010\u000F\u001A\u00020\u0003H\u0000\u001A\u001C\u0010\u0010\u001A\u00020\u0001*\u00020\u00052\u0006\u0010\u0011\u001A\u00020\u00032\u0006\u0010\u0012\u001A\u00020\nH\u0000\u001A\u001C\u0010\u0013\u001A\u00020\u0001*\u00020\u00052\u0006\u0010\u0014\u001A\u00020\u00032\u0006\u0010\u0015\u001A\u00020\nH\u0000\u001A\u0014\u0010\u0016\u001A\u00020\n*\u00020\u00052\u0006\u0010\b\u001A\u00020\u0003H\u0000\u001A\"\u0010\u0017\u001A\b\u0012\u0004\u0012\u00020\u00030\u0018*\u00020\u00052\u0006\u0010\u0011\u001A\u00020\u00032\u0006\u0010\t\u001A\u00020\nH\u0000\u001A\u0014\u0010\u0019\u001A\u00020\u001A*\u00020\u00052\u0006\u0010\b\u001A\u00020\u0003H\u0000\u001A\u0016\u0010\u001B\u001A\u0004\u0018\u00010\u0003*\u00020\u00052\u0006\u0010\b\u001A\u00020\u0003H\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001C"}, d2 = {"collectRecursively", "", "Lkotlin/sequences/SequenceScope;", "Lokio/Path;", "fileSystem", "Lokio/FileSystem;", "stack", "Lkotlin/collections/ArrayDeque;", "path", "followSymlinks", "", "postorder", "(Lkotlin/sequences/SequenceScope;Lokio/FileSystem;Lkotlin/collections/ArrayDeque;Lokio/Path;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "commonCopy", "source", "target", "commonCreateDirectories", "dir", "mustCreate", "commonDeleteRecursively", "fileOrDirectory", "mustExist", "commonExists", "commonListRecursively", "Lkotlin/sequences/Sequence;", "commonMetadata", "Lokio/FileMetadata;", "symlinkTarget", "okio"}, k = 2, mv = {1, 6, 0}, xi = 0x30)
public final class _FileSystemKt {
    public static final Object collectRecursively(SequenceScope sequenceScope0, FileSystem fileSystem0, ArrayDeque arrayDeque0, Path path0, boolean z, boolean z1, Continuation continuation0) {
        ArrayDeque arrayDeque2;
        Iterator iterator0;
        boolean z5;
        Path path4;
        SequenceScope sequenceScope1;
        boolean z2;
        FileSystem fileSystem1;
        ArrayDeque arrayDeque1;
        okio.internal._FileSystemKt.collectRecursively.1 _FileSystemKt$collectRecursively$10;
        if(continuation0 instanceof okio.internal._FileSystemKt.collectRecursively.1) {
            _FileSystemKt$collectRecursively$10 = (okio.internal._FileSystemKt.collectRecursively.1)continuation0;
            if((_FileSystemKt$collectRecursively$10.label & 0x80000000) == 0) {
                _FileSystemKt$collectRecursively$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    Object L$4;
                    boolean Z$0;
                    boolean Z$1;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return _FileSystemKt.collectRecursively(null, null, null, null, false, false, this);
                    }
                };
            }
            else {
                _FileSystemKt$collectRecursively$10.label ^= 0x80000000;
            }
        }
        else {
            _FileSystemKt$collectRecursively$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                Object L$2;
                Object L$3;
                Object L$4;
                boolean Z$0;
                boolean Z$1;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return _FileSystemKt.collectRecursively(null, null, null, null, false, false, this);
                }
            };
        }
        Object object0 = _FileSystemKt$collectRecursively$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int v = 0;
        switch(_FileSystemKt$collectRecursively$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                if(z1) {
                    arrayDeque1 = arrayDeque0;
                }
                else {
                    _FileSystemKt$collectRecursively$10.L$0 = sequenceScope0;
                    _FileSystemKt$collectRecursively$10.L$1 = fileSystem0;
                    arrayDeque1 = arrayDeque0;
                    _FileSystemKt$collectRecursively$10.L$2 = arrayDeque1;
                    _FileSystemKt$collectRecursively$10.L$3 = path0;
                    _FileSystemKt$collectRecursively$10.Z$0 = z;
                    _FileSystemKt$collectRecursively$10.Z$1 = false;
                    _FileSystemKt$collectRecursively$10.label = 1;
                    if(sequenceScope0.yield(path0, _FileSystemKt$collectRecursively$10) == object1) {
                        return object1;
                    }
                }
                fileSystem1 = fileSystem0;
                z2 = z;
                sequenceScope1 = sequenceScope0;
                goto label_42;
            }
            case 1: {
                boolean z3 = _FileSystemKt$collectRecursively$10.Z$1;
                boolean z4 = _FileSystemKt$collectRecursively$10.Z$0;
                Path path1 = (Path)_FileSystemKt$collectRecursively$10.L$3;
                arrayDeque1 = (ArrayDeque)_FileSystemKt$collectRecursively$10.L$2;
                fileSystem1 = (FileSystem)_FileSystemKt$collectRecursively$10.L$1;
                sequenceScope1 = (SequenceScope)_FileSystemKt$collectRecursively$10.L$0;
                ResultKt.throwOnFailure(object0);
                z1 = z3;
                z2 = z4;
                path0 = path1;
            label_42:
                List list0 = fileSystem1.listOrNull(path0);
                if(list0 == null) {
                    list0 = CollectionsKt.emptyList();
                }
                if(!list0.isEmpty()) {
                    Path path2 = path0;
                    while(true) {
                        if(z2 && arrayDeque1.contains(path2)) {
                            throw new IOException("symlink cycle at " + path0);
                        }
                        Path path3 = _FileSystemKt.symlinkTarget(fileSystem1, path2);
                        if(path3 == null) {
                            if(!z2 && v != 0) {
                                break;
                            }
                            arrayDeque1.addLast(path2);
                            try {
                                path4 = path0;
                                z5 = z1;
                                iterator0 = list0.iterator();
                                goto label_75;
                            }
                            catch(Throwable throwable0) {
                                arrayDeque2 = arrayDeque1;
                                arrayDeque2.removeLast();
                                throw throwable0;
                            }
                        }
                        ++v;
                        path2 = path3;
                    }
                }
                goto label_95;
            }
            case 2: {
                break;
            }
            case 3: {
                ResultKt.throwOnFailure(object0);
                return Unit.INSTANCE;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        boolean z6 = _FileSystemKt$collectRecursively$10.Z$1;
        boolean z7 = _FileSystemKt$collectRecursively$10.Z$0;
        iterator0 = (Iterator)_FileSystemKt$collectRecursively$10.L$4;
        Path path5 = (Path)_FileSystemKt$collectRecursively$10.L$3;
        arrayDeque2 = (ArrayDeque)_FileSystemKt$collectRecursively$10.L$2;
        fileSystem1 = (FileSystem)_FileSystemKt$collectRecursively$10.L$1;
        sequenceScope1 = (SequenceScope)_FileSystemKt$collectRecursively$10.L$0;
        try {
            ResultKt.throwOnFailure(object0);
            z5 = z6;
            z2 = z7;
            path4 = path5;
        }
        catch(Throwable throwable0) {
            arrayDeque2.removeLast();
            throw throwable0;
        }
        while(true) {
            arrayDeque1 = arrayDeque2;
            try {
            label_75:
                if(!iterator0.hasNext()) {
                    goto label_92;
                }
                Object object2 = iterator0.next();
                _FileSystemKt$collectRecursively$10.L$0 = sequenceScope1;
                _FileSystemKt$collectRecursively$10.L$1 = fileSystem1;
                _FileSystemKt$collectRecursively$10.L$2 = arrayDeque1;
                _FileSystemKt$collectRecursively$10.L$3 = path4;
                _FileSystemKt$collectRecursively$10.L$4 = iterator0;
                _FileSystemKt$collectRecursively$10.Z$0 = z2;
                _FileSystemKt$collectRecursively$10.Z$1 = z5;
                _FileSystemKt$collectRecursively$10.label = 2;
                arrayDeque2 = arrayDeque1;
                if(_FileSystemKt.collectRecursively(sequenceScope1, fileSystem1, arrayDeque1, ((Path)object2), z2, z5, _FileSystemKt$collectRecursively$10) == object1) {
                    return object1;
                }
                continue;
            }
            catch(Throwable throwable0) {
            }
            break;
        }
        arrayDeque2 = arrayDeque1;
        arrayDeque2.removeLast();
        throw throwable0;
    label_92:
        arrayDeque1.removeLast();
        z1 = z5;
        path0 = path4;
    label_95:
        if(z1) {
            _FileSystemKt$collectRecursively$10.L$0 = null;
            _FileSystemKt$collectRecursively$10.L$1 = null;
            _FileSystemKt$collectRecursively$10.L$2 = null;
            _FileSystemKt$collectRecursively$10.L$3 = null;
            _FileSystemKt$collectRecursively$10.L$4 = null;
            _FileSystemKt$collectRecursively$10.label = 3;
            return sequenceScope1.yield(path0, _FileSystemKt$collectRecursively$10) == object1 ? object1 : Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    public static final void commonCopy(FileSystem fileSystem0, Path path0, Path path1) throws IOException {
        Long long1;
        Long long0;
        Intrinsics.checkNotNullParameter(fileSystem0, "<this>");
        Intrinsics.checkNotNullParameter(path0, "source");
        Intrinsics.checkNotNullParameter(path1, "target");
        Closeable closeable0 = fileSystem0.source(path0);
        Throwable throwable0 = null;
        try {
            Source source0 = (Source)closeable0;
            Closeable closeable1 = Okio.buffer(fileSystem0.sink(path1));
            try {
                long0 = ((BufferedSink)closeable1).writeAll(source0);
                throwable2 = null;
            }
            catch(Throwable throwable2) {
                long0 = null;
            }
            if(closeable1 != null) {
                try {
                    closeable1.close();
                }
                catch(Throwable throwable3) {
                    if(throwable2 == null) {
                        throwable2 = throwable3;
                    }
                    else {
                        ExceptionsKt.addSuppressed(throwable2, throwable3);
                    }
                }
            }
            if(throwable2 != null) {
                throw throwable2;
            }
            Intrinsics.checkNotNull(long0);
            long1 = long0.longValue();
        }
        catch(Throwable throwable1) {
            throwable0 = throwable1;
            long1 = null;
        }
        if(closeable0 != null) {
            try {
                closeable0.close();
            }
            catch(Throwable throwable4) {
                if(throwable0 == null) {
                    throwable0 = throwable4;
                }
                else {
                    ExceptionsKt.addSuppressed(throwable0, throwable4);
                }
            }
        }
        if(throwable0 != null) {
            throw throwable0;
        }
        Intrinsics.checkNotNull(long1);
    }

    public static final void commonCreateDirectories(FileSystem fileSystem0, Path path0, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem0, "<this>");
        Intrinsics.checkNotNullParameter(path0, "dir");
        ArrayDeque arrayDeque0 = new ArrayDeque();
        for(Path path1 = path0; path1 != null && !fileSystem0.exists(path1); path1 = path1.parent()) {
            arrayDeque0.addFirst(path1);
        }
        if(z && arrayDeque0.isEmpty()) {
            throw new IOException(path0 + " already exist.");
        }
        for(Object object0: arrayDeque0) {
            fileSystem0.createDirectory(((Path)object0));
        }
    }

    public static final void commonDeleteRecursively(FileSystem fileSystem0, Path path0, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem0, "<this>");
        Intrinsics.checkNotNullParameter(path0, "fileOrDirectory");
        Iterator iterator0 = SequencesKt.sequence(new Function2(fileSystem0, path0, null) {
            final Path $fileOrDirectory;
            final FileSystem $this_commonDeleteRecursively;
            private Object L$0;
            int label;

            {
                this.$this_commonDeleteRecursively = fileSystem0;
                this.$fileOrDirectory = path0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                okio.internal._FileSystemKt.commonDeleteRecursively.sequence.1 _FileSystemKt$commonDeleteRecursively$sequence$10 = new okio.internal._FileSystemKt.commonDeleteRecursively.sequence.1(this.$this_commonDeleteRecursively, this.$fileOrDirectory, continuation0);
                _FileSystemKt$commonDeleteRecursively$sequence$10.L$0 = object0;
                return _FileSystemKt$commonDeleteRecursively$sequence$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((SequenceScope)object0), ((Continuation)object1));
            }

            public final Object invoke(SequenceScope sequenceScope0, Continuation continuation0) {
                return ((okio.internal._FileSystemKt.commonDeleteRecursively.sequence.1)this.create(sequenceScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        SequenceScope sequenceScope0 = (SequenceScope)this.L$0;
                        ArrayDeque arrayDeque0 = new ArrayDeque();
                        this.label = 1;
                        return _FileSystemKt.collectRecursively(sequenceScope0, this.$this_commonDeleteRecursively, arrayDeque0, this.$fileOrDirectory, false, true, this) == object1 ? object1 : Unit.INSTANCE;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }
        }).iterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            fileSystem0.delete(((Path)object0), z && !iterator0.hasNext());
        }
    }

    public static final boolean commonExists(FileSystem fileSystem0, Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem0, "<this>");
        Intrinsics.checkNotNullParameter(path0, "path");
        return fileSystem0.metadataOrNull(path0) != null;
    }

    public static final Sequence commonListRecursively(FileSystem fileSystem0, Path path0, boolean z) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem0, "<this>");
        Intrinsics.checkNotNullParameter(path0, "dir");
        return SequencesKt.sequence(new Function2(path0, fileSystem0, z, null) {
            final Path $dir;
            final boolean $followSymlinks;
            final FileSystem $this_commonListRecursively;
            private Object L$0;
            Object L$1;
            Object L$2;
            int label;

            {
                this.$dir = path0;
                this.$this_commonListRecursively = fileSystem0;
                this.$followSymlinks = z;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                okio.internal._FileSystemKt.commonListRecursively.1 _FileSystemKt$commonListRecursively$10 = new okio.internal._FileSystemKt.commonListRecursively.1(this.$dir, this.$this_commonListRecursively, this.$followSymlinks, continuation0);
                _FileSystemKt$commonListRecursively$10.L$0 = object0;
                return _FileSystemKt$commonListRecursively$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((SequenceScope)object0), ((Continuation)object1));
            }

            public final Object invoke(SequenceScope sequenceScope0, Continuation continuation0) {
                return ((okio.internal._FileSystemKt.commonListRecursively.1)this.create(sequenceScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Iterator iterator0;
                ArrayDeque arrayDeque1;
                SequenceScope sequenceScope1;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        SequenceScope sequenceScope0 = (SequenceScope)this.L$0;
                        ArrayDeque arrayDeque0 = new ArrayDeque();
                        arrayDeque0.addLast(this.$dir);
                        sequenceScope1 = sequenceScope0;
                        arrayDeque1 = arrayDeque0;
                        iterator0 = this.$this_commonListRecursively.list(this.$dir).iterator();
                        break;
                    }
                    case 1: {
                        iterator0 = (Iterator)this.L$2;
                        ArrayDeque arrayDeque2 = (ArrayDeque)this.L$1;
                        sequenceScope1 = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        arrayDeque1 = arrayDeque2;
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                while(iterator0.hasNext()) {
                    Object object2 = iterator0.next();
                    this.L$0 = sequenceScope1;
                    this.L$1 = arrayDeque1;
                    this.L$2 = iterator0;
                    this.label = 1;
                    if(_FileSystemKt.collectRecursively(sequenceScope1, this.$this_commonListRecursively, arrayDeque1, ((Path)object2), this.$followSymlinks, false, this) == object1) {
                        return object1;
                    }
                    if(false) {
                        break;
                    }
                }
                return Unit.INSTANCE;
            }
        });
    }

    public static final FileMetadata commonMetadata(FileSystem fileSystem0, Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem0, "<this>");
        Intrinsics.checkNotNullParameter(path0, "path");
        FileMetadata fileMetadata0 = fileSystem0.metadataOrNull(path0);
        if(fileMetadata0 == null) {
            throw new FileNotFoundException("no such file: " + path0);
        }
        return fileMetadata0;
    }

    public static final Path symlinkTarget(FileSystem fileSystem0, Path path0) throws IOException {
        Intrinsics.checkNotNullParameter(fileSystem0, "<this>");
        Intrinsics.checkNotNullParameter(path0, "path");
        Path path1 = fileSystem0.metadata(path0).getSymlinkTarget();
        if(path1 == null) {
            return null;
        }
        Path path2 = path0.parent();
        Intrinsics.checkNotNull(path2);
        return path2.resolve(path1);
    }
}

