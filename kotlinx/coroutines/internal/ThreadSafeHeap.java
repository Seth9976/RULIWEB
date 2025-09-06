package kotlinx.coroutines.internal;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000F\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0017\u0018\u0000*\u0012\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00060\u0004j\u0002`\u0005B\u0005\u00A2\u0006\u0002\u0010\u0006J\u0015\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00028\u0000H\u0001\u00A2\u0006\u0002\u0010\u0019J\u0013\u0010\u001A\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u00028\u0000\u00A2\u0006\u0002\u0010\u0019J,\u0010\u001B\u001A\u00020\r2\u0006\u0010\u0018\u001A\u00028\u00002\u0014\u0010\u001C\u001A\u0010\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00020\r0\u001DH\u0086\b\u00A2\u0006\u0002\u0010\u001EJ\u0006\u0010\u001F\u001A\u00020\u0017J0\u0010 \u001A\u0004\u0018\u00018\u00002!\u0010!\u001A\u001D\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u000F\u0012\u0004\u0012\u00020\r0\u001D\u00A2\u0006\u0002\u0010$J\u000F\u0010%\u001A\u0004\u0018\u00018\u0000H\u0001\u00A2\u0006\u0002\u0010&J\r\u0010\'\u001A\u0004\u0018\u00018\u0000\u00A2\u0006\u0002\u0010&J\u0015\u0010(\u001A\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\nH\u0002\u00A2\u0006\u0002\u0010)J\u0013\u0010*\u001A\u00020\r2\u0006\u0010\u0018\u001A\u00028\u0000\u00A2\u0006\u0002\u0010+J\u0015\u0010,\u001A\u00028\u00002\u0006\u0010-\u001A\u00020\u0010H\u0001\u00A2\u0006\u0002\u0010.J$\u0010/\u001A\u0004\u0018\u00018\u00002\u0012\u0010!\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\r0\u001DH\u0086\b\u00A2\u0006\u0002\u0010$J\r\u00100\u001A\u0004\u0018\u00018\u0000\u00A2\u0006\u0002\u0010&J\u0011\u00101\u001A\u00020\u00172\u0006\u00102\u001A\u00020\u0010H\u0082\u0010J\u0011\u00103\u001A\u00020\u00172\u0006\u00102\u001A\u00020\u0010H\u0082\u0010J\u0018\u00104\u001A\u00020\u00172\u0006\u00102\u001A\u00020\u00102\u0006\u00105\u001A\u00020\u0010H\u0002R\t\u0010\u0007\u001A\u00020\bX\u0082\u0004R\u001A\u0010\t\u001A\f\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0018\u00010\nX\u0082\u000E\u00A2\u0006\u0004\n\u0002\u0010\u000BR\u0011\u0010\f\u001A\u00020\r8F\u00A2\u0006\u0006\u001A\u0004\b\f\u0010\u000ER$\u0010\u0011\u001A\u00020\u00102\u0006\u0010\u000F\u001A\u00020\u00108F@BX\u0086\u000E\u00A2\u0006\f\u001A\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015\u00A8\u00066"}, d2 = {"Lkotlinx/coroutines/internal/ThreadSafeHeap;", "T", "Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "()V", "_size", "Lkotlinx/atomicfu/AtomicInt;", "a", "", "[Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "isEmpty", "", "()Z", "value", "", "size", "getSize", "()I", "setSize", "(I)V", "addImpl", "", "node", "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;)V", "addLast", "addLastIf", "cond", "Lkotlin/Function1;", "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;Lkotlin/jvm/functions/Function1;)Z", "clear", "find", "predicate", "Lkotlin/ParameterName;", "name", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "firstImpl", "()Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "peek", "realloc", "()[Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "remove", "(Lkotlinx/coroutines/internal/ThreadSafeHeapNode;)Z", "removeAtImpl", "index", "(I)Lkotlinx/coroutines/internal/ThreadSafeHeapNode;", "removeFirstIf", "removeFirstOrNull", "siftDownFrom", "i", "siftUpFrom", "swap", "j", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class ThreadSafeHeap {
    @Volatile
    private volatile int _size;
    private static final AtomicIntegerFieldUpdater _size$FU;
    private ThreadSafeHeapNode[] a;

    static {
        ThreadSafeHeap._size$FU = AtomicIntegerFieldUpdater.newUpdater(ThreadSafeHeap.class, "_size");
    }

    public final void addImpl(ThreadSafeHeapNode threadSafeHeapNode0) {
        threadSafeHeapNode0.setHeap(this);
        ThreadSafeHeapNode[] arr_threadSafeHeapNode = this.realloc();
        int v = this.getSize();
        this.setSize(v + 1);
        arr_threadSafeHeapNode[v] = threadSafeHeapNode0;
        threadSafeHeapNode0.setIndex(v);
        this.siftUpFrom(v);
    }

    public final void addLast(ThreadSafeHeapNode threadSafeHeapNode0) {
        synchronized(this) {
            this.addImpl(threadSafeHeapNode0);
        }
    }

    public final boolean addLastIf(ThreadSafeHeapNode threadSafeHeapNode0, Function1 function10) {
        boolean z;
        synchronized(this) {
            if(((Boolean)function10.invoke(this.firstImpl())).booleanValue()) {
                this.addImpl(threadSafeHeapNode0);
                z = true;
            }
            else {
                z = false;
            }
            return z;
        }
    }

    public final void clear() {
        synchronized(this) {
            ThreadSafeHeapNode[] arr_threadSafeHeapNode = this.a;
            if(arr_threadSafeHeapNode != null) {
                ArraysKt.fill$default(arr_threadSafeHeapNode, null, 0, 0, 6, null);
            }
            ThreadSafeHeap._size$FU.set(this, 0);
        }
    }

    public final ThreadSafeHeapNode find(Function1 function10) {
        ThreadSafeHeapNode threadSafeHeapNode0;
        synchronized(this) {
            int v1 = this.getSize();
            for(int v2 = 0; true; ++v2) {
                threadSafeHeapNode0 = null;
                if(v2 >= v1) {
                    break;
                }
                ThreadSafeHeapNode[] arr_threadSafeHeapNode = this.a;
                if(arr_threadSafeHeapNode != null) {
                    threadSafeHeapNode0 = arr_threadSafeHeapNode[v2];
                }
                Intrinsics.checkNotNull(threadSafeHeapNode0);
                if(((Boolean)function10.invoke(threadSafeHeapNode0)).booleanValue()) {
                    break;
                }
            }
            return threadSafeHeapNode0;
        }
    }

    public final ThreadSafeHeapNode firstImpl() {
        return this.a == null ? null : this.a[0];
    }

    public final int getSize() {
        return ThreadSafeHeap._size$FU.get(this);
    }

    public final boolean isEmpty() {
        return this.getSize() == 0;
    }

    public final ThreadSafeHeapNode peek() {
        synchronized(this) {
            return this.firstImpl();
        }
    }

    private final ThreadSafeHeapNode[] realloc() {
        ThreadSafeHeapNode[] arr_threadSafeHeapNode = this.a;
        if(arr_threadSafeHeapNode == null) {
            ThreadSafeHeapNode[] arr_threadSafeHeapNode1 = new ThreadSafeHeapNode[4];
            this.a = arr_threadSafeHeapNode1;
            return arr_threadSafeHeapNode1;
        }
        if(this.getSize() >= arr_threadSafeHeapNode.length) {
            Object[] arr_object = Arrays.copyOf(arr_threadSafeHeapNode, this.getSize() * 2);
            Intrinsics.checkNotNullExpressionValue(arr_object, "copyOf(this, newSize)");
            arr_threadSafeHeapNode = (ThreadSafeHeapNode[])arr_object;
            this.a = arr_threadSafeHeapNode;
        }
        return arr_threadSafeHeapNode;
    }

    public final boolean remove(ThreadSafeHeapNode threadSafeHeapNode0) {
        boolean z;
        synchronized(this) {
            if(threadSafeHeapNode0.getHeap() == null) {
                z = false;
            }
            else {
                this.removeAtImpl(threadSafeHeapNode0.getIndex());
                z = true;
            }
            return z;
        }
    }

    public final ThreadSafeHeapNode removeAtImpl(int v) {
        ThreadSafeHeapNode[] arr_threadSafeHeapNode = this.a;
        Intrinsics.checkNotNull(arr_threadSafeHeapNode);
        this.setSize(this.getSize() - 1);
        if(v < this.getSize()) {
            this.swap(v, this.getSize());
            int v1 = (v - 1) / 2;
            if(v > 0) {
                ThreadSafeHeapNode threadSafeHeapNode0 = arr_threadSafeHeapNode[v];
                Intrinsics.checkNotNull(threadSafeHeapNode0);
                ThreadSafeHeapNode threadSafeHeapNode1 = arr_threadSafeHeapNode[v1];
                Intrinsics.checkNotNull(threadSafeHeapNode1);
                if(((Comparable)threadSafeHeapNode0).compareTo(threadSafeHeapNode1) < 0) {
                    this.swap(v, v1);
                    this.siftUpFrom(v1);
                }
                else {
                    this.siftDownFrom(v);
                }
            }
            else {
                this.siftDownFrom(v);
            }
        }
        ThreadSafeHeapNode threadSafeHeapNode2 = arr_threadSafeHeapNode[this.getSize()];
        Intrinsics.checkNotNull(threadSafeHeapNode2);
        threadSafeHeapNode2.setHeap(null);
        threadSafeHeapNode2.setIndex(-1);
        arr_threadSafeHeapNode[this.getSize()] = null;
        return threadSafeHeapNode2;
    }

    public final ThreadSafeHeapNode removeFirstIf(Function1 function10) {
        synchronized(this) {
            ThreadSafeHeapNode threadSafeHeapNode0 = null;
            ThreadSafeHeapNode threadSafeHeapNode1 = this.firstImpl();
            if(threadSafeHeapNode1 == null) {
                return null;
            }
            if(((Boolean)function10.invoke(threadSafeHeapNode1)).booleanValue()) {
                threadSafeHeapNode0 = this.removeAtImpl(0);
            }
            return threadSafeHeapNode0;
        }
    }

    public final ThreadSafeHeapNode removeFirstOrNull() {
        synchronized(this) {
            return this.getSize() <= 0 ? null : this.removeAtImpl(0);
        }
    }

    private final void setSize(int v) {
        ThreadSafeHeap._size$FU.set(this, v);
    }

    private final void siftDownFrom(int v) {
        int v1;
        while((v1 = v * 2 + 1) < this.getSize()) {
            ThreadSafeHeapNode[] arr_threadSafeHeapNode = this.a;
            Intrinsics.checkNotNull(arr_threadSafeHeapNode);
            int v2 = v * 2 + 2;
            if(v2 < this.getSize()) {
                ThreadSafeHeapNode threadSafeHeapNode0 = arr_threadSafeHeapNode[v2];
                Intrinsics.checkNotNull(threadSafeHeapNode0);
                ThreadSafeHeapNode threadSafeHeapNode1 = arr_threadSafeHeapNode[v1];
                Intrinsics.checkNotNull(threadSafeHeapNode1);
                if(((Comparable)threadSafeHeapNode0).compareTo(threadSafeHeapNode1) >= 0) {
                    v2 = v1;
                }
            }
            else {
                v2 = v1;
            }
            ThreadSafeHeapNode threadSafeHeapNode2 = arr_threadSafeHeapNode[v];
            Intrinsics.checkNotNull(threadSafeHeapNode2);
            ThreadSafeHeapNode threadSafeHeapNode3 = arr_threadSafeHeapNode[v2];
            Intrinsics.checkNotNull(threadSafeHeapNode3);
            if(((Comparable)threadSafeHeapNode2).compareTo(threadSafeHeapNode3) <= 0) {
                break;
            }
            this.swap(v, v2);
            v = v2;
        }
    }

    private final void siftUpFrom(int v) {
        while(v > 0) {
            ThreadSafeHeapNode[] arr_threadSafeHeapNode = this.a;
            Intrinsics.checkNotNull(arr_threadSafeHeapNode);
            int v1 = (v - 1) / 2;
            ThreadSafeHeapNode threadSafeHeapNode0 = arr_threadSafeHeapNode[v1];
            Intrinsics.checkNotNull(threadSafeHeapNode0);
            ThreadSafeHeapNode threadSafeHeapNode1 = arr_threadSafeHeapNode[v];
            Intrinsics.checkNotNull(threadSafeHeapNode1);
            if(((Comparable)threadSafeHeapNode0).compareTo(threadSafeHeapNode1) <= 0) {
                break;
            }
            this.swap(v, v1);
            v = v1;
        }
    }

    private final void swap(int v, int v1) {
        ThreadSafeHeapNode[] arr_threadSafeHeapNode = this.a;
        Intrinsics.checkNotNull(arr_threadSafeHeapNode);
        ThreadSafeHeapNode threadSafeHeapNode0 = arr_threadSafeHeapNode[v1];
        Intrinsics.checkNotNull(threadSafeHeapNode0);
        ThreadSafeHeapNode threadSafeHeapNode1 = arr_threadSafeHeapNode[v];
        Intrinsics.checkNotNull(threadSafeHeapNode1);
        arr_threadSafeHeapNode[v] = threadSafeHeapNode0;
        arr_threadSafeHeapNode[v1] = threadSafeHeapNode1;
        threadSafeHeapNode0.setIndex(v);
        threadSafeHeapNode1.setIndex(v1);
    }
}

