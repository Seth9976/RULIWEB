package kotlin.io.path;

import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010(\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001D\u0012\u0006\u0010\u0003\u001A\u00020\u0002\u0012\u000E\u0010\u0004\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u000E\u0010\u0014\u001A\b\u0012\u0004\u0012\u00020\u00020\u0015H\u0002J\u000E\u0010\u0016\u001A\b\u0012\u0004\u0012\u00020\u00020\u0015H\u0002J\u000F\u0010\u0017\u001A\b\u0012\u0004\u0012\u00020\u00020\u0015H\u0096\u0002JE\u0010\u0018\u001A\u00020\u0019*\b\u0012\u0004\u0012\u00020\u00020\u001A2\u0006\u0010\u001B\u001A\u00020\u001C2\u0006\u0010\u001D\u001A\u00020\u001E2\u0018\u0010\u001F\u001A\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001C0!\u0012\u0004\u0012\u00020\u00190 H\u0082Hø\u0001\u0000¢\u0006\u0002\u0010\"R\u0014\u0010\b\u001A\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b\n\u0010\u000BR\u0014\u0010\f\u001A\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b\r\u0010\u000BR\u0014\u0010\u000E\u001A\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b\u000E\u0010\u000BR\u001A\u0010\u000F\u001A\b\u0012\u0004\u0012\u00020\u00100\u00058BX\u0082\u0004¢\u0006\u0006\u001A\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0004\u001A\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0013R\u000E\u0010\u0003\u001A\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, d2 = {"Lkotlin/io/path/PathTreeWalk;", "Lkotlin/sequences/Sequence;", "Ljava/nio/file/Path;", "start", "options", "", "Lkotlin/io/path/PathWalkOption;", "(Ljava/nio/file/Path;[Lkotlin/io/path/PathWalkOption;)V", "followLinks", "", "getFollowLinks", "()Z", "includeDirectories", "getIncludeDirectories", "isBFS", "linkOptions", "Ljava/nio/file/LinkOption;", "getLinkOptions", "()[Ljava/nio/file/LinkOption;", "[Lkotlin/io/path/PathWalkOption;", "bfsIterator", "", "dfsIterator", "iterator", "yieldIfNeeded", "", "Lkotlin/sequences/SequenceScope;", "node", "Lkotlin/io/path/PathNode;", "entriesReader", "Lkotlin/io/path/DirectoryEntriesReader;", "entriesAction", "Lkotlin/Function1;", "", "(Lkotlin/sequences/SequenceScope;Lkotlin/io/path/PathNode;Lkotlin/io/path/DirectoryEntriesReader;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlin-stdlib-jdk7"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class PathTreeWalk implements Sequence {
    private final PathWalkOption[] options;
    private final Path start;

    public PathTreeWalk(Path path0, PathWalkOption[] arr_pathWalkOption) {
        Intrinsics.checkNotNullParameter(path0, "start");
        Intrinsics.checkNotNullParameter(arr_pathWalkOption, "options");
        super();
        this.start = path0;
        this.options = arr_pathWalkOption;
    }

    private final Iterator bfsIterator() {
        return SequencesKt.iterator(new Function2(null) {
            private Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            Object L$5;
            int label;

            {
                PathTreeWalk.this = pathTreeWalk0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlin.io.path.PathTreeWalk.bfsIterator.1 pathTreeWalk$bfsIterator$10 = new kotlin.io.path.PathTreeWalk.bfsIterator.1(PathTreeWalk.this, continuation0);
                pathTreeWalk$bfsIterator$10.L$0 = object0;
                return pathTreeWalk$bfsIterator$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((SequenceScope)object0), ((Continuation)object1));
            }

            public final Object invoke(SequenceScope sequenceScope0, Continuation continuation0) {
                return ((kotlin.io.path.PathTreeWalk.bfsIterator.1)this.create(sequenceScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                SequenceScope sequenceScope2;
                ArrayDeque arrayDeque2;
                DirectoryEntriesReader directoryEntriesReader2;
                PathNode pathNode0;
                PathTreeWalk pathTreeWalk0;
                Path path0;
                SequenceScope sequenceScope1;
                DirectoryEntriesReader directoryEntriesReader1;
                ArrayDeque arrayDeque1;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        SequenceScope sequenceScope0 = (SequenceScope)this.L$0;
                        ArrayDeque arrayDeque0 = new ArrayDeque();
                        DirectoryEntriesReader directoryEntriesReader0 = new DirectoryEntriesReader(PathTreeWalk.this.getFollowLinks());
                        arrayDeque0.addLast(new PathNode(PathTreeWalk.this.start, PathTreeWalkKt.keyOf(PathTreeWalk.this.start, PathTreeWalk.this.getLinkOptions()), null));
                        arrayDeque1 = arrayDeque0;
                        directoryEntriesReader1 = directoryEntriesReader0;
                        sequenceScope1 = sequenceScope0;
                        break;
                    }
                    case 1: {
                        path0 = (Path)this.L$5;
                        pathTreeWalk0 = (PathTreeWalk)this.L$4;
                        pathNode0 = (PathNode)this.L$3;
                        directoryEntriesReader2 = (DirectoryEntriesReader)this.L$2;
                        arrayDeque2 = (ArrayDeque)this.L$1;
                        sequenceScope2 = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_47;
                    }
                    case 2: {
                        directoryEntriesReader1 = (DirectoryEntriesReader)this.L$2;
                        arrayDeque1 = (ArrayDeque)this.L$1;
                        sequenceScope1 = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                while(!arrayDeque1.isEmpty()) {
                    PathNode pathNode1 = (PathNode)arrayDeque1.removeFirst();
                    PathTreeWalk pathTreeWalk1 = PathTreeWalk.this;
                    Path path1 = pathNode1.getPath();
                    LinkOption[] arr_linkOption = pathTreeWalk1.getLinkOptions();
                    LinkOption[] arr_linkOption1 = (LinkOption[])Arrays.copyOf(arr_linkOption, arr_linkOption.length);
                    if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(path1, ((LinkOption[])Arrays.copyOf(arr_linkOption1, arr_linkOption1.length)))) {
                        if(PathTreeWalkKt.createsCycle(pathNode1)) {
                            throw PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path1.toString());
                        }
                        if(pathTreeWalk1.getIncludeDirectories()) {
                            this.L$0 = sequenceScope1;
                            this.L$1 = arrayDeque1;
                            this.L$2 = directoryEntriesReader1;
                            this.L$3 = pathNode1;
                            this.L$4 = pathTreeWalk1;
                            this.L$5 = path1;
                            this.label = 1;
                            if(sequenceScope1.yield(path1, this) == object1) {
                                return object1;
                            }
                            directoryEntriesReader2 = directoryEntriesReader1;
                            path0 = path1;
                            arrayDeque2 = arrayDeque1;
                            pathTreeWalk0 = pathTreeWalk1;
                            sequenceScope2 = sequenceScope1;
                            pathNode0 = pathNode1;
                        label_47:
                            path1 = path0;
                            directoryEntriesReader1 = directoryEntriesReader2;
                            pathTreeWalk1 = pathTreeWalk0;
                            arrayDeque1 = arrayDeque2;
                            pathNode1 = pathNode0;
                            sequenceScope1 = sequenceScope2;
                        }
                        LinkOption[] arr_linkOption2 = pathTreeWalk1.getLinkOptions();
                        LinkOption[] arr_linkOption3 = (LinkOption[])Arrays.copyOf(arr_linkOption2, arr_linkOption2.length);
                        if(!LinkFollowing..ExternalSyntheticApiModelOutline0.m(path1, ((LinkOption[])Arrays.copyOf(arr_linkOption3, arr_linkOption3.length)))) {
                            continue;
                        }
                        arrayDeque1.addAll(directoryEntriesReader1.readEntries(pathNode1));
                        continue;
                    }
                    if(!PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path1, ((LinkOption[])Arrays.copyOf(new LinkOption[]{LinkFollowing..ExternalSyntheticApiModelOutline0.m()}, 1)))) {
                        continue;
                    }
                    this.L$0 = sequenceScope1;
                    this.L$1 = arrayDeque1;
                    this.L$2 = directoryEntriesReader1;
                    this.L$3 = null;
                    this.L$4 = null;
                    this.L$5 = null;
                    this.label = 2;
                    if(sequenceScope1.yield(path1, this) != object1) {
                        continue;
                    }
                    return object1;
                }
                return Unit.INSTANCE;
            }
        });
    }

    private final Iterator dfsIterator() {
        return SequencesKt.iterator(new Function2(null) {
            private Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            Object L$5;
            int label;

            {
                PathTreeWalk.this = pathTreeWalk0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlin.io.path.PathTreeWalk.dfsIterator.1 pathTreeWalk$dfsIterator$10 = new kotlin.io.path.PathTreeWalk.dfsIterator.1(PathTreeWalk.this, continuation0);
                pathTreeWalk$dfsIterator$10.L$0 = object0;
                return pathTreeWalk$dfsIterator$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((SequenceScope)object0), ((Continuation)object1));
            }

            public final Object invoke(SequenceScope sequenceScope0, Continuation continuation0) {
                return ((kotlin.io.path.PathTreeWalk.dfsIterator.1)this.create(sequenceScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                SequenceScope sequenceScope1;
                ArrayDeque arrayDeque1;
                DirectoryEntriesReader directoryEntriesReader1;
                SequenceScope sequenceScope0;
                ArrayDeque arrayDeque0;
                DirectoryEntriesReader directoryEntriesReader0;
                PathNode pathNode0;
                PathTreeWalk pathTreeWalk0;
                Path path0;
                SequenceScope sequenceScope2;
                ArrayDeque arrayDeque2;
                DirectoryEntriesReader directoryEntriesReader2;
                PathNode pathNode1;
                PathTreeWalk pathTreeWalk1;
                Path path1;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        sequenceScope1 = (SequenceScope)this.L$0;
                        ArrayDeque arrayDeque3 = new ArrayDeque();
                        directoryEntriesReader1 = new DirectoryEntriesReader(PathTreeWalk.this.getFollowLinks());
                        LinkOption[] arr_linkOption = PathTreeWalk.this.getLinkOptions();
                        Object object2 = PathTreeWalkKt.keyOf(PathTreeWalk.this.start, arr_linkOption);
                        PathNode pathNode2 = new PathNode(PathTreeWalk.this.start, object2, null);
                        PathTreeWalk pathTreeWalk2 = PathTreeWalk.this;
                        Path path2 = pathNode2.getPath();
                        LinkOption[] arr_linkOption1 = pathTreeWalk2.getLinkOptions();
                        LinkOption[] arr_linkOption2 = (LinkOption[])Arrays.copyOf(arr_linkOption1, arr_linkOption1.length);
                        if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(path2, ((LinkOption[])Arrays.copyOf(arr_linkOption2, arr_linkOption2.length)))) {
                            if(PathTreeWalkKt.createsCycle(pathNode2)) {
                                throw PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path2.toString());
                            }
                            if(pathTreeWalk2.getIncludeDirectories()) {
                                this.L$0 = sequenceScope1;
                                this.L$1 = arrayDeque3;
                                this.L$2 = directoryEntriesReader1;
                                this.L$3 = pathNode2;
                                this.L$4 = pathTreeWalk2;
                                this.L$5 = path2;
                                this.label = 1;
                                if(sequenceScope1.yield(path2, this) == object1) {
                                    return object1;
                                }
                                directoryEntriesReader2 = directoryEntriesReader1;
                                path1 = path2;
                                sequenceScope2 = sequenceScope1;
                                pathNode1 = pathNode2;
                                pathTreeWalk1 = pathTreeWalk2;
                                arrayDeque2 = arrayDeque3;
                            label_52:
                                path2 = path1;
                                directoryEntriesReader1 = directoryEntriesReader2;
                                pathNode2 = pathNode1;
                                sequenceScope1 = sequenceScope2;
                                arrayDeque3 = arrayDeque2;
                                pathTreeWalk2 = pathTreeWalk1;
                            }
                            LinkOption[] arr_linkOption3 = pathTreeWalk2.getLinkOptions();
                            LinkOption[] arr_linkOption4 = (LinkOption[])Arrays.copyOf(arr_linkOption3, arr_linkOption3.length);
                            if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(path2, ((LinkOption[])Arrays.copyOf(arr_linkOption4, arr_linkOption4.length)))) {
                                pathNode2.setContentIterator(directoryEntriesReader1.readEntries(pathNode2).iterator());
                                arrayDeque3.addLast(pathNode2);
                            }
                        }
                        else if(PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path2, ((LinkOption[])Arrays.copyOf(new LinkOption[]{LinkOption.NOFOLLOW_LINKS}, 1)))) {
                            this.L$0 = sequenceScope1;
                            this.L$1 = arrayDeque3;
                            this.L$2 = directoryEntriesReader1;
                            this.label = 2;
                            if(sequenceScope1.yield(path2, this) != object1) {
                                arrayDeque1 = arrayDeque3;
                                break;
                            }
                            return object1;
                        }
                        arrayDeque1 = arrayDeque3;
                        break;
                    }
                    case 1: {
                        path1 = (Path)this.L$5;
                        pathTreeWalk1 = (PathTreeWalk)this.L$4;
                        pathNode1 = (PathNode)this.L$3;
                        directoryEntriesReader2 = (DirectoryEntriesReader)this.L$2;
                        arrayDeque2 = (ArrayDeque)this.L$1;
                        sequenceScope2 = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_52;
                    }
                    case 3: {
                        path0 = (Path)this.L$5;
                        pathTreeWalk0 = (PathTreeWalk)this.L$4;
                        pathNode0 = (PathNode)this.L$3;
                        directoryEntriesReader0 = (DirectoryEntriesReader)this.L$2;
                        arrayDeque0 = (ArrayDeque)this.L$1;
                        sequenceScope0 = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_99;
                    }
                    case 2: 
                    case 4: {
                        directoryEntriesReader1 = (DirectoryEntriesReader)this.L$2;
                        arrayDeque1 = (ArrayDeque)this.L$1;
                        sequenceScope1 = (SequenceScope)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                while(!arrayDeque1.isEmpty()) {
                    Iterator iterator0 = ((PathNode)arrayDeque1.last()).getContentIterator();
                    Intrinsics.checkNotNull(iterator0);
                    if(iterator0.hasNext()) {
                        Object object3 = iterator0.next();
                        PathNode pathNode3 = (PathNode)object3;
                        PathTreeWalk pathTreeWalk3 = PathTreeWalk.this;
                        Path path3 = pathNode3.getPath();
                        LinkOption[] arr_linkOption5 = pathTreeWalk3.getLinkOptions();
                        LinkOption[] arr_linkOption6 = (LinkOption[])Arrays.copyOf(arr_linkOption5, arr_linkOption5.length);
                        if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(path3, ((LinkOption[])Arrays.copyOf(arr_linkOption6, arr_linkOption6.length)))) {
                            if(PathTreeWalkKt.createsCycle(pathNode3)) {
                                throw PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path3.toString());
                            }
                            if(pathTreeWalk3.getIncludeDirectories()) {
                                this.L$0 = sequenceScope1;
                                this.L$1 = arrayDeque1;
                                this.L$2 = directoryEntriesReader1;
                                this.L$3 = pathNode3;
                                this.L$4 = pathTreeWalk3;
                                this.L$5 = path3;
                                this.label = 3;
                                if(sequenceScope1.yield(path3, this) != object1) {
                                    directoryEntriesReader0 = directoryEntriesReader1;
                                    path0 = path3;
                                    arrayDeque0 = arrayDeque1;
                                    pathTreeWalk0 = pathTreeWalk3;
                                    sequenceScope0 = sequenceScope1;
                                    pathNode0 = pathNode3;
                                label_99:
                                    path3 = path0;
                                    directoryEntriesReader1 = directoryEntriesReader0;
                                    pathTreeWalk3 = pathTreeWalk0;
                                    arrayDeque1 = arrayDeque0;
                                    pathNode3 = pathNode0;
                                    sequenceScope1 = sequenceScope0;
                                    goto label_105;
                                }
                            }
                            else {
                            label_105:
                                LinkOption[] arr_linkOption7 = pathTreeWalk3.getLinkOptions();
                                LinkOption[] arr_linkOption8 = (LinkOption[])Arrays.copyOf(arr_linkOption7, arr_linkOption7.length);
                                if(!LinkFollowing..ExternalSyntheticApiModelOutline0.m(path3, ((LinkOption[])Arrays.copyOf(arr_linkOption8, arr_linkOption8.length)))) {
                                    continue;
                                }
                                pathNode3.setContentIterator(directoryEntriesReader1.readEntries(pathNode3).iterator());
                                arrayDeque1.addLast(pathNode3);
                                continue;
                            }
                        }
                        else {
                            if(!PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path3, ((LinkOption[])Arrays.copyOf(new LinkOption[]{LinkOption.NOFOLLOW_LINKS}, 1)))) {
                                continue;
                            }
                            this.L$0 = sequenceScope1;
                            this.L$1 = arrayDeque1;
                            this.L$2 = directoryEntriesReader1;
                            this.L$3 = null;
                            this.L$4 = null;
                            this.L$5 = null;
                            this.label = 4;
                            if(sequenceScope1.yield(path3, this) == object1) {
                                return object1;
                            }
                            continue;
                        }
                        return object1;
                    }
                    arrayDeque1.removeLast();
                }
                return Unit.INSTANCE;
            }
        });
    }

    private final boolean getFollowLinks() {
        return ArraysKt.contains(this.options, PathWalkOption.FOLLOW_LINKS);
    }

    private final boolean getIncludeDirectories() {
        return ArraysKt.contains(this.options, PathWalkOption.INCLUDE_DIRECTORIES);
    }

    private final LinkOption[] getLinkOptions() {
        boolean z = this.getFollowLinks();
        return LinkFollowing.INSTANCE.toLinkOptions(z);
    }

    private final boolean isBFS() {
        return ArraysKt.contains(this.options, PathWalkOption.BREADTH_FIRST);
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.sequences.Sequence
    public Iterator iterator() {
        return this.isBFS() ? this.bfsIterator() : this.dfsIterator();
    }

    private final Object yieldIfNeeded(SequenceScope sequenceScope0, PathNode pathNode0, DirectoryEntriesReader directoryEntriesReader0, Function1 function10, Continuation continuation0) {
        Path path0 = pathNode0.getPath();
        LinkOption[] arr_linkOption = this.getLinkOptions();
        LinkOption[] arr_linkOption1 = (LinkOption[])Arrays.copyOf(arr_linkOption, arr_linkOption.length);
        if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(path0, ((LinkOption[])Arrays.copyOf(arr_linkOption1, arr_linkOption1.length)))) {
            if(PathTreeWalkKt.createsCycle(pathNode0)) {
                throw PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path0.toString());
            }
            if(this.getIncludeDirectories()) {
                sequenceScope0.yield(path0, continuation0);
            }
            LinkOption[] arr_linkOption2 = this.getLinkOptions();
            LinkOption[] arr_linkOption3 = (LinkOption[])Arrays.copyOf(arr_linkOption2, arr_linkOption2.length);
            if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(path0, ((LinkOption[])Arrays.copyOf(arr_linkOption3, arr_linkOption3.length)))) {
                function10.invoke(directoryEntriesReader0.readEntries(pathNode0));
                return Unit.INSTANCE;
            }
        }
        else if(PathTreeWalk..ExternalSyntheticApiModelOutline0.m(path0, ((LinkOption[])Arrays.copyOf(new LinkOption[]{LinkFollowing..ExternalSyntheticApiModelOutline0.m()}, 1)))) {
            sequenceScope0.yield(path0, continuation0);
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }
}

