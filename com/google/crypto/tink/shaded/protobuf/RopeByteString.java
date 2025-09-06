package com.google.crypto.tink.shaded.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

final class RopeByteString extends ByteString {
    static class Balancer {
        private final ArrayDeque prefixesStack;

        private Balancer() {
            this.prefixesStack = new ArrayDeque();
        }

        Balancer(com.google.crypto.tink.shaded.protobuf.RopeByteString.1 ropeByteString$10) {
        }

        static ByteString access$100(Balancer ropeByteString$Balancer0, ByteString byteString0, ByteString byteString1) {
            return ropeByteString$Balancer0.balance(byteString0, byteString1);
        }

        private ByteString balance(ByteString byteString0, ByteString byteString1) {
            this.doBalance(byteString0);
            this.doBalance(byteString1);
            ByteString byteString2;
            for(byteString2 = (ByteString)this.prefixesStack.pop(); !this.prefixesStack.isEmpty(); byteString2 = new RopeByteString(((ByteString)this.prefixesStack.pop()), byteString2, null)) {
            }
            return byteString2;
        }

        private void doBalance(ByteString byteString0) {
            if(byteString0.isBalanced()) {
                this.insert(byteString0);
                return;
            }
            if(!(byteString0 instanceof RopeByteString)) {
                throw new IllegalArgumentException("Has a new type of ByteString been created? Found " + byteString0.getClass());
            }
            this.doBalance(((RopeByteString)byteString0).left);
            this.doBalance(((RopeByteString)byteString0).right);
        }

        private int getDepthBinForLength(int v) {
            int v1 = Arrays.binarySearch(RopeByteString.minLengthByDepth, v);
            return v1 >= 0 ? v1 : -(v1 + 1) - 1;
        }

        private void insert(ByteString byteString0) {
            int v = this.getDepthBinForLength(byteString0.size());
            int v1 = RopeByteString.minLength(v + 1);
            if(!this.prefixesStack.isEmpty() && ((ByteString)this.prefixesStack.peek()).size() < v1) {
                int v2 = RopeByteString.minLength(v);
                ByteString byteString1;
                for(byteString1 = (ByteString)this.prefixesStack.pop(); !this.prefixesStack.isEmpty() && ((ByteString)this.prefixesStack.peek()).size() < v2; byteString1 = new RopeByteString(((ByteString)this.prefixesStack.pop()), byteString1, null)) {
                }
                RopeByteString ropeByteString0;
                for(ropeByteString0 = new RopeByteString(byteString1, byteString0, null); !this.prefixesStack.isEmpty(); ropeByteString0 = new RopeByteString(((ByteString)this.prefixesStack.pop()), ropeByteString0, null)) {
                    int v3 = RopeByteString.minLength(this.getDepthBinForLength(ropeByteString0.size()) + 1);
                    if(((ByteString)this.prefixesStack.peek()).size() >= v3) {
                        break;
                    }
                }
                this.prefixesStack.push(ropeByteString0);
                return;
            }
            this.prefixesStack.push(byteString0);
        }
    }

    static final class PieceIterator implements Iterator {
        private final ArrayDeque breadCrumbs;
        private LeafByteString next;

        private PieceIterator(ByteString byteString0) {
            if(byteString0 instanceof RopeByteString) {
                ArrayDeque arrayDeque0 = new ArrayDeque(((RopeByteString)byteString0).getTreeDepth());
                this.breadCrumbs = arrayDeque0;
                arrayDeque0.push(((RopeByteString)byteString0));
                this.next = this.getLeafByLeft(((RopeByteString)byteString0).left);
                return;
            }
            this.breadCrumbs = null;
            this.next = (LeafByteString)byteString0;
        }

        PieceIterator(ByteString byteString0, com.google.crypto.tink.shaded.protobuf.RopeByteString.1 ropeByteString$10) {
            this(byteString0);
        }

        private LeafByteString getLeafByLeft(ByteString byteString0) {
            while(byteString0 instanceof RopeByteString) {
                this.breadCrumbs.push(((RopeByteString)byteString0));
                byteString0 = ((RopeByteString)byteString0).left;
            }
            return (LeafByteString)byteString0;
        }

        private LeafByteString getNextNonEmptyLeaf() {
            while(this.breadCrumbs != null && !this.breadCrumbs.isEmpty()) {
                LeafByteString byteString$LeafByteString0 = this.getLeafByLeft(((RopeByteString)this.breadCrumbs.pop()).right);
                if(!byteString$LeafByteString0.isEmpty()) {
                    return byteString$LeafByteString0;
                }
                if(false) {
                    break;
                }
            }
            return null;
        }

        @Override
        public boolean hasNext() {
            return this.next != null;
        }

        public LeafByteString next() {
            LeafByteString byteString$LeafByteString0 = this.next;
            if(byteString$LeafByteString0 == null) {
                throw new NoSuchElementException();
            }
            this.next = this.getNextNonEmptyLeaf();
            return byteString$LeafByteString0;
        }

        @Override
        public Object next() {
            return this.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    class RopeInputStream extends InputStream {
        private LeafByteString currentPiece;
        private int currentPieceIndex;
        private int currentPieceOffsetInRope;
        private int currentPieceSize;
        private int mark;
        private PieceIterator pieceIterator;

        public RopeInputStream() {
            this.initialize();
        }

        private void advanceIfCurrentPieceFullyRead() {
            if(this.currentPiece != null) {
                int v = this.currentPieceSize;
                if(this.currentPieceIndex == v) {
                    this.currentPieceOffsetInRope += v;
                    this.currentPieceIndex = 0;
                    if(this.pieceIterator.hasNext()) {
                        LeafByteString byteString$LeafByteString0 = this.pieceIterator.next();
                        this.currentPiece = byteString$LeafByteString0;
                        this.currentPieceSize = byteString$LeafByteString0.size();
                        return;
                    }
                    this.currentPiece = null;
                    this.currentPieceSize = 0;
                }
            }
        }

        @Override
        public int available() throws IOException {
            return this.availableInternal();
        }

        private int availableInternal() {
            return RopeByteString.this.size() - (this.currentPieceOffsetInRope + this.currentPieceIndex);
        }

        private void initialize() {
            PieceIterator ropeByteString$PieceIterator0 = new PieceIterator(RopeByteString.this, null);
            this.pieceIterator = ropeByteString$PieceIterator0;
            LeafByteString byteString$LeafByteString0 = ropeByteString$PieceIterator0.next();
            this.currentPiece = byteString$LeafByteString0;
            this.currentPieceSize = byteString$LeafByteString0.size();
            this.currentPieceIndex = 0;
            this.currentPieceOffsetInRope = 0;
        }

        @Override
        public void mark(int v) {
            this.mark = this.currentPieceOffsetInRope + this.currentPieceIndex;
        }

        @Override
        public boolean markSupported() {
            return true;
        }

        @Override
        public int read() throws IOException {
            this.advanceIfCurrentPieceFullyRead();
            LeafByteString byteString$LeafByteString0 = this.currentPiece;
            if(byteString$LeafByteString0 == null) {
                return -1;
            }
            int v = this.currentPieceIndex;
            this.currentPieceIndex = v + 1;
            return byteString$LeafByteString0.byteAt(v) & 0xFF;
        }

        @Override
        public int read(byte[] arr_b, int v, int v1) {
            arr_b.getClass();
            if(v < 0 || v1 < 0 || v1 > arr_b.length - v) {
                throw new IndexOutOfBoundsException();
            }
            int v2 = this.readSkipInternal(arr_b, v, v1);
            return v2 != 0 || v1 <= 0 && this.availableInternal() != 0 ? v2 : -1;
        }

        private int readSkipInternal(byte[] arr_b, int v, int v1) {
            int v2;
            for(v2 = v1; v2 > 0; v2 -= v3) {
                this.advanceIfCurrentPieceFullyRead();
                if(this.currentPiece == null) {
                    break;
                }
                int v3 = Math.min(this.currentPieceSize - this.currentPieceIndex, v2);
                if(arr_b != null) {
                    this.currentPiece.copyTo(arr_b, this.currentPieceIndex, v, v3);
                    v += v3;
                }
                this.currentPieceIndex += v3;
            }
            return v1 - v2;
        }

        @Override
        public void reset() {
            synchronized(this) {
                this.initialize();
                this.readSkipInternal(null, 0, this.mark);
            }
        }

        @Override
        public long skip(long v) {
            if(v < 0L) {
                throw new IndexOutOfBoundsException();
            }
            if(v > 0x7FFFFFFFL) {
                v = 0x7FFFFFFFL;
            }
            return (long)this.readSkipInternal(null, 0, ((int)v));
        }
    }

    private final ByteString left;
    private final int leftLength;
    static final int[] minLengthByDepth = null;
    private final ByteString right;
    private static final long serialVersionUID = 1L;
    private final int totalLength;
    private final int treeDepth;

    static {
        RopeByteString.minLengthByDepth = new int[]{1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 0x90, 0xE9, 377, 610, 987, 0x63D, 0xA18, 0x1055, 6765, 10946, 0x452F, 0x6FF1, 0xB520, 75025, 0x1DA31, 0x2FF42, 0x4D973, 0x7D8B5, 832040, 0x148ADD, 0x213D05, 0x35C7E2, 0x5704E7, 0x8CCCC9, 0xE3D1B0, 0x1709E79, 0x2547029, 0x3C50EA2, 102334155, 165580141, 0xFF80C38, 0x19D699A5, 701408733, 1134903170, 0x6D73E55F, 0x7FFFFFFF};
    }

    private RopeByteString(ByteString byteString0, ByteString byteString1) {
        this.left = byteString0;
        this.right = byteString1;
        int v = byteString0.size();
        this.leftLength = v;
        this.totalLength = v + byteString1.size();
        this.treeDepth = Math.max(byteString0.getTreeDepth(), byteString1.getTreeDepth()) + 1;
    }

    RopeByteString(ByteString byteString0, ByteString byteString1, com.google.crypto.tink.shaded.protobuf.RopeByteString.1 ropeByteString$10) {
        this(byteString0, byteString1);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public ByteBuffer asReadOnlyByteBuffer() {
        return ByteBuffer.wrap(this.toByteArray()).asReadOnlyBuffer();
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public List asReadOnlyByteBufferList() {
        List list0 = new ArrayList();
        PieceIterator ropeByteString$PieceIterator0 = new PieceIterator(this, null);
        while(ropeByteString$PieceIterator0.hasNext()) {
            list0.add(ropeByteString$PieceIterator0.next().asReadOnlyByteBuffer());
        }
        return list0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public byte byteAt(int v) {
        RopeByteString.checkIndex(v, this.totalLength);
        return this.internalByteAt(v);
    }

    static ByteString concatenate(ByteString byteString0, ByteString byteString1) {
        if(byteString1.size() == 0) {
            return byteString0;
        }
        if(byteString0.size() == 0) {
            return byteString1;
        }
        int v = byteString0.size() + byteString1.size();
        if(v < 0x80) {
            return RopeByteString.concatenateBytes(byteString0, byteString1);
        }
        if(byteString0 instanceof RopeByteString) {
            if(((RopeByteString)byteString0).right.size() + byteString1.size() < 0x80) {
                ByteString byteString2 = RopeByteString.concatenateBytes(((RopeByteString)byteString0).right, byteString1);
                return new RopeByteString(((RopeByteString)byteString0).left, byteString2);
            }
            if(((RopeByteString)byteString0).left.getTreeDepth() > ((RopeByteString)byteString0).right.getTreeDepth() && ((RopeByteString)byteString0).getTreeDepth() > byteString1.getTreeDepth()) {
                RopeByteString ropeByteString0 = new RopeByteString(((RopeByteString)byteString0).right, byteString1);
                return new RopeByteString(((RopeByteString)byteString0).left, ropeByteString0);
            }
        }
        return v >= RopeByteString.minLength(Math.max(byteString0.getTreeDepth(), byteString1.getTreeDepth()) + 1) ? new RopeByteString(byteString0, byteString1) : Balancer.access$100(new Balancer(null), byteString0, byteString1);
    }

    private static ByteString concatenateBytes(ByteString byteString0, ByteString byteString1) {
        int v = byteString0.size();
        int v1 = byteString1.size();
        byte[] arr_b = new byte[v + v1];
        byteString0.copyTo(arr_b, 0, 0, v);
        byteString1.copyTo(arr_b, 0, v, v1);
        return ByteString.wrap(arr_b);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public void copyTo(ByteBuffer byteBuffer0) {
        this.left.copyTo(byteBuffer0);
        this.right.copyTo(byteBuffer0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    protected void copyToInternal(byte[] arr_b, int v, int v1, int v2) {
        int v3 = this.leftLength;
        if(v + v2 <= v3) {
            this.left.copyToInternal(arr_b, v, v1, v2);
            return;
        }
        if(v >= v3) {
            this.right.copyToInternal(arr_b, v - v3, v1, v2);
            return;
        }
        int v4 = v3 - v;
        this.left.copyToInternal(arr_b, v, v1, v4);
        this.right.copyToInternal(arr_b, 0, v1 + v4, v2 - v4);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof ByteString)) {
            return false;
        }
        if(this.totalLength != ((ByteString)object0).size()) {
            return false;
        }
        if(this.totalLength == 0) {
            return true;
        }
        int v = this.peekCachedHashCode();
        int v1 = ((ByteString)object0).peekCachedHashCode();
        return v == 0 || v1 == 0 || v == v1 ? this.equalsFragments(((ByteString)object0)) : false;
    }

    private boolean equalsFragments(ByteString byteString0) {
        PieceIterator ropeByteString$PieceIterator0 = new PieceIterator(this, null);
        Object object0 = ropeByteString$PieceIterator0.next();
        LeafByteString byteString$LeafByteString0 = (LeafByteString)object0;
        PieceIterator ropeByteString$PieceIterator1 = new PieceIterator(byteString0, null);
        Object object1 = ropeByteString$PieceIterator1.next();
        LeafByteString byteString$LeafByteString1 = (LeafByteString)object1;
        int v = 0;
        int v1 = 0;
        int v2 = 0;
        while(true) {
            int v3 = byteString$LeafByteString0.size() - v;
            int v4 = byteString$LeafByteString1.size() - v1;
            int v5 = Math.min(v3, v4);
            if(!(v == 0 ? byteString$LeafByteString0.equalsRange(byteString$LeafByteString1, v1, v5) : byteString$LeafByteString1.equalsRange(byteString$LeafByteString0, v, v5))) {
                return false;
            }
            v2 += v5;
            int v6 = this.totalLength;
            if(v2 >= v6) {
                if(v2 != v6) {
                    throw new IllegalStateException();
                }
                return true;
            }
            if(v5 == v3) {
                Object object2 = ropeByteString$PieceIterator0.next();
                byteString$LeafByteString0 = (LeafByteString)object2;
                v = 0;
            }
            else {
                v += v5;
            }
            if(v5 == v4) {
                Object object3 = ropeByteString$PieceIterator1.next();
                byteString$LeafByteString1 = (LeafByteString)object3;
                v1 = 0;
            }
            else {
                v1 += v5;
            }
        }
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    protected int getTreeDepth() {
        return this.treeDepth;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    byte internalByteAt(int v) {
        return v >= this.leftLength ? this.right.internalByteAt(v - this.leftLength) : this.left.internalByteAt(v);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    protected boolean isBalanced() {
        int v = RopeByteString.minLength(this.treeDepth);
        return this.totalLength >= v;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public boolean isValidUtf8() {
        int v = this.left.partialIsValidUtf8(0, 0, this.leftLength);
        return this.right.partialIsValidUtf8(v, 0, this.right.size()) == 0;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public ByteIterator iterator() {
        return new AbstractByteIterator() {
            ByteIterator current;
            final PieceIterator pieces;

            {
                this.pieces = new PieceIterator(ropeByteString0, null);
                this.current = this.nextPiece();
            }

            @Override
            public boolean hasNext() {
                return this.current != null;
            }

            @Override  // com.google.crypto.tink.shaded.protobuf.ByteString$ByteIterator
            public byte nextByte() {
                ByteIterator byteString$ByteIterator0 = this.current;
                if(byteString$ByteIterator0 == null) {
                    throw new NoSuchElementException();
                }
                byte b = byteString$ByteIterator0.nextByte();
                if(!this.current.hasNext()) {
                    this.current = this.nextPiece();
                }
                return b;
            }

            // 去混淆评级： 低(20)
            private ByteIterator nextPiece() {
                return this.pieces.hasNext() ? this.pieces.next().iterator() : null;
            }
        };
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public Iterator iterator() {
        return this.iterator();
    }

    static int minLength(int v) {
        return v < RopeByteString.minLengthByDepth.length ? RopeByteString.minLengthByDepth[v] : 0x7FFFFFFF;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public CodedInputStream newCodedInput() {
        return CodedInputStream.newInstance(this.asReadOnlyByteBufferList(), true);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public InputStream newInput() {
        return new RopeInputStream(this);
    }

    static RopeByteString newInstanceForTest(ByteString byteString0, ByteString byteString1) {
        return new RopeByteString(byteString0, byteString1);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    protected int partialHash(int v, int v1, int v2) {
        int v3 = this.leftLength;
        if(v1 + v2 <= v3) {
            return this.left.partialHash(v, v1, v2);
        }
        if(v1 >= v3) {
            return this.right.partialHash(v, v1 - v3, v2);
        }
        int v4 = v3 - v1;
        int v5 = this.left.partialHash(v, v1, v4);
        return this.right.partialHash(v5, 0, v2 - v4);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    protected int partialIsValidUtf8(int v, int v1, int v2) {
        int v3 = this.leftLength;
        if(v1 + v2 <= v3) {
            return this.left.partialIsValidUtf8(v, v1, v2);
        }
        if(v1 >= v3) {
            return this.right.partialIsValidUtf8(v, v1 - v3, v2);
        }
        int v4 = v3 - v1;
        int v5 = this.left.partialIsValidUtf8(v, v1, v4);
        return this.right.partialIsValidUtf8(v5, 0, v2 - v4);
    }

    private void readObject(ObjectInputStream objectInputStream0) throws IOException {
        throw new InvalidObjectException("RopeByteStream instances are not to be serialized directly");
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public int size() {
        return this.totalLength;
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public ByteString substring(int v, int v1) {
        int v2 = RopeByteString.checkRange(v, v1, this.totalLength);
        if(v2 == 0) {
            return ByteString.EMPTY;
        }
        if(v2 == this.totalLength) {
            return this;
        }
        int v3 = this.leftLength;
        if(v1 <= v3) {
            return this.left.substring(v, v1);
        }
        return v >= v3 ? this.right.substring(v - v3, v1 - v3) : new RopeByteString(this.left.substring(v), this.right.substring(0, v1 - this.leftLength));
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    protected String toStringInternal(Charset charset0) {
        return new String(this.toByteArray(), charset0);
    }

    Object writeReplace() {
        return ByteString.wrap(this.toByteArray());
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    void writeTo(ByteOutput byteOutput0) throws IOException {
        this.left.writeTo(byteOutput0);
        this.right.writeTo(byteOutput0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    public void writeTo(OutputStream outputStream0) throws IOException {
        this.left.writeTo(outputStream0);
        this.right.writeTo(outputStream0);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    void writeToInternal(OutputStream outputStream0, int v, int v1) throws IOException {
        int v2 = this.leftLength;
        if(v + v1 <= v2) {
            this.left.writeToInternal(outputStream0, v, v1);
            return;
        }
        if(v >= v2) {
            this.right.writeToInternal(outputStream0, v - v2, v1);
            return;
        }
        int v3 = v2 - v;
        this.left.writeToInternal(outputStream0, v, v3);
        this.right.writeToInternal(outputStream0, 0, v1 - v3);
    }

    @Override  // com.google.crypto.tink.shaded.protobuf.ByteString
    void writeToReverse(ByteOutput byteOutput0) throws IOException {
        this.right.writeToReverse(byteOutput0);
        this.left.writeToReverse(byteOutput0);
    }
}

