package com.google.crypto.tink.shaded.protobuf;

import java.nio.ByteBuffer;

@CheckReturnValue
abstract class BufferAllocator {
    private static final BufferAllocator UNPOOLED;

    static {
        BufferAllocator.UNPOOLED = new BufferAllocator() {
            @Override  // com.google.crypto.tink.shaded.protobuf.BufferAllocator
            public AllocatedBuffer allocateDirectBuffer(int v) {
                return AllocatedBuffer.wrap(ByteBuffer.allocateDirect(v));
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.BufferAllocator
            public AllocatedBuffer allocateHeapBuffer(int v) {
                return AllocatedBuffer.wrap(new byte[v]);
            }
        };
    }

    public abstract AllocatedBuffer allocateDirectBuffer(int arg1);

    public abstract AllocatedBuffer allocateHeapBuffer(int arg1);

    public static BufferAllocator unpooled() {
        return BufferAllocator.UNPOOLED;
    }
}

