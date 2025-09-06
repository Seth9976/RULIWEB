package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

class RopeByteString extends ByteString {
    static class Balancer {
        private final Stack prefixesStack;

        private Balancer() {
            this.prefixesStack = new Stack();
        }

        Balancer(kotlin.reflect.jvm.internal.impl.protobuf.RopeByteString.1 ropeByteString$10) {
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
            int v1 = RopeByteString.minLengthByDepth[v + 1];
            if(!this.prefixesStack.isEmpty() && ((ByteString)this.prefixesStack.peek()).size() < v1) {
                int v2 = RopeByteString.minLengthByDepth[v];
                ByteString byteString1;
                for(byteString1 = (ByteString)this.prefixesStack.pop(); !this.prefixesStack.isEmpty() && ((ByteString)this.prefixesStack.peek()).size() < v2; byteString1 = new RopeByteString(((ByteString)this.prefixesStack.pop()), byteString1, null)) {
                }
                RopeByteString ropeByteString0;
                for(ropeByteString0 = new RopeByteString(byteString1, byteString0, null); !this.prefixesStack.isEmpty(); ropeByteString0 = new RopeByteString(((ByteString)this.prefixesStack.pop()), ropeByteString0, null)) {
                    int v3 = RopeByteString.minLengthByDepth[this.getDepthBinForLength(ropeByteString0.size()) + 1];
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

    static class PieceIterator implements Iterator {
        private final Stack breadCrumbs;
        private LiteralByteString next;

        private PieceIterator(ByteString byteString0) {
            this.breadCrumbs = new Stack();
            this.next = this.getLeafByLeft(byteString0);
        }

        PieceIterator(ByteString byteString0, kotlin.reflect.jvm.internal.impl.protobuf.RopeByteString.1 ropeByteString$10) {
            this(byteString0);
        }

        private LiteralByteString getLeafByLeft(ByteString byteString0) {
            while(byteString0 instanceof RopeByteString) {
                this.breadCrumbs.push(((RopeByteString)byteString0));
                byteString0 = ((RopeByteString)byteString0).left;
            }
            return (LiteralByteString)byteString0;
        }

        private LiteralByteString getNextNonEmptyLeaf() {
            LiteralByteString literalByteString0;
            do {
                if(this.breadCrumbs.isEmpty()) {
                    return null;
                }
                literalByteString0 = this.getLeafByLeft(((RopeByteString)this.breadCrumbs.pop()).right);
            }
            while(literalByteString0.isEmpty());
            return literalByteString0;
        }

        @Override
        public boolean hasNext() {
            return this.next != null;
        }

        @Override
        public Object next() {
            return this.next();
        }

        public LiteralByteString next() {
            LiteralByteString literalByteString0 = this.next;
            if(literalByteString0 == null) {
                throw new NoSuchElementException();
            }
            this.next = this.getNextNonEmptyLeaf();
            return literalByteString0;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    class RopeByteIterator implements ByteIterator {
        private ByteIterator bytes;
        int bytesRemaining;
        private final PieceIterator pieces;

        private RopeByteIterator() {
            PieceIterator ropeByteString$PieceIterator0 = new PieceIterator(ropeByteString0, null);
            this.pieces = ropeByteString$PieceIterator0;
            this.bytes = ropeByteString$PieceIterator0.next().iterator();
            this.bytesRemaining = ropeByteString0.size();
        }

        RopeByteIterator(kotlin.reflect.jvm.internal.impl.protobuf.RopeByteString.1 ropeByteString$10) {
        }

        @Override
        public boolean hasNext() {
            return this.bytesRemaining > 0;
        }

        public Byte next() {
            return this.nextByte();
        }

        @Override
        public Object next() {
            return this.next();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString$ByteIterator
        public byte nextByte() {
            if(!this.bytes.hasNext()) {
                this.bytes = this.pieces.next().iterator();
            }
            --this.bytesRemaining;
            return this.bytes.nextByte();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    class RopeInputStream extends InputStream {
        private LiteralByteString currentPiece;
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
                        LiteralByteString literalByteString0 = this.pieceIterator.next();
                        this.currentPiece = literalByteString0;
                        this.currentPieceSize = literalByteString0.size();
                        return;
                    }
                    this.currentPiece = null;
                    this.currentPieceSize = 0;
                }
            }
        }

        @Override
        public int available() throws IOException {
            return RopeByteString.this.size() - (this.currentPieceOffsetInRope + this.currentPieceIndex);
        }

        private void initialize() {
            PieceIterator ropeByteString$PieceIterator0 = new PieceIterator(RopeByteString.this, null);
            this.pieceIterator = ropeByteString$PieceIterator0;
            LiteralByteString literalByteString0 = ropeByteString$PieceIterator0.next();
            this.currentPiece = literalByteString0;
            this.currentPieceSize = literalByteString0.size();
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
            LiteralByteString literalByteString0 = this.currentPiece;
            if(literalByteString0 == null) {
                return -1;
            }
            int v = this.currentPieceIndex;
            this.currentPieceIndex = v + 1;
            return literalByteString0.byteAt(v) & 0xFF;
        }

        @Override
        public int read(byte[] arr_b, int v, int v1) {
            arr_b.getClass();
            if(v < 0 || v1 < 0 || v1 > arr_b.length - v) {
                throw new IndexOutOfBoundsException();
            }
            return this.readSkipInternal(arr_b, v, v1);
        }

        private int readSkipInternal(byte[] arr_b, int v, int v1) {
            int v2;
            for(v2 = v1; v2 > 0; v2 -= v3) {
                this.advanceIfCurrentPieceFullyRead();
                if(this.currentPiece == null) {
                    return v2 == v1 ? -1 : v1 - v2;
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

    private int hash;
    private final ByteString left;
    private final int leftLength;
    private static final int[] minLengthByDepth;
    private final ByteString right;
    private final int totalLength;
    private final int treeDepth;

    static {
        ArrayList arrayList0 = new ArrayList();
        int v = 1;
        for(int v1 = 1; v1 > 0; v1 = v2) {
            arrayList0.add(v1);
            int v2 = v + v1;
            v = v1;
        }
        arrayList0.add(0x7FFFFFFF);
        RopeByteString.minLengthByDepth = new int[arrayList0.size()];
        for(int v3 = 0; true; ++v3) {
            int[] arr_v = RopeByteString.minLengthByDepth;
            if(v3 >= arr_v.length) {
                break;
            }
            arr_v[v3] = (int)(((Integer)arrayList0.get(v3)));
        }
    }

    private RopeByteString(ByteString byteString0, ByteString byteString1) {
        this.hash = 0;
        this.left = byteString0;
        this.right = byteString1;
        int v = byteString0.size();
        this.leftLength = v;
        this.totalLength = v + byteString1.size();
        this.treeDepth = Math.max(byteString0.getTreeDepth(), byteString1.getTreeDepth()) + 1;
    }

    RopeByteString(ByteString byteString0, ByteString byteString1, kotlin.reflect.jvm.internal.impl.protobuf.RopeByteString.1 ropeByteString$10) {
        this(byteString0, byteString1);
    }

    static ByteString concatenate(ByteString byteString0, ByteString byteString1) {
        RopeByteString ropeByteString0 = byteString0 instanceof RopeByteString ? ((RopeByteString)byteString0) : null;
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
        if(ropeByteString0 != null && ropeByteString0.right.size() + byteString1.size() < 0x80) {
            LiteralByteString literalByteString0 = RopeByteString.concatenateBytes(ropeByteString0.right, byteString1);
            return new RopeByteString(ropeByteString0.left, literalByteString0);
        }
        if(ropeByteString0 != null && ropeByteString0.left.getTreeDepth() > ropeByteString0.right.getTreeDepth() && ropeByteString0.getTreeDepth() > byteString1.getTreeDepth()) {
            RopeByteString ropeByteString1 = new RopeByteString(ropeByteString0.right, byteString1);
            return new RopeByteString(ropeByteString0.left, ropeByteString1);
        }
        return v >= RopeByteString.minLengthByDepth[Math.max(byteString0.getTreeDepth(), byteString1.getTreeDepth()) + 1] ? new RopeByteString(byteString0, byteString1) : Balancer.access$100(new Balancer(null), byteString0, byteString1);
    }

    private static LiteralByteString concatenateBytes(ByteString byteString0, ByteString byteString1) {
        int v = byteString0.size();
        int v1 = byteString1.size();
        byte[] arr_b = new byte[v + v1];
        byteString0.copyTo(arr_b, 0, 0, v);
        byteString1.copyTo(arr_b, 0, v, v1);
        return new LiteralByteString(arr_b);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
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

    @Override
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
        if(this.hash != 0) {
            int v = ((ByteString)object0).peekCachedHashCode();
            return v == 0 || this.hash == v ? this.equalsFragments(((ByteString)object0)) : false;
        }
        return this.equalsFragments(((ByteString)object0));
    }

    private boolean equalsFragments(ByteString byteString0) {
        PieceIterator ropeByteString$PieceIterator0 = new PieceIterator(this, null);
        Object object0 = ropeByteString$PieceIterator0.next();
        LiteralByteString literalByteString0 = (LiteralByteString)object0;
        PieceIterator ropeByteString$PieceIterator1 = new PieceIterator(byteString0, null);
        Object object1 = ropeByteString$PieceIterator1.next();
        LiteralByteString literalByteString1 = (LiteralByteString)object1;
        int v = 0;
        int v1 = 0;
        int v2 = 0;
        while(true) {
            int v3 = literalByteString0.size() - v;
            int v4 = literalByteString1.size() - v1;
            int v5 = Math.min(v3, v4);
            if(!(v == 0 ? literalByteString0.equalsRange(literalByteString1, v1, v5) : literalByteString1.equalsRange(literalByteString0, v, v5))) {
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
                literalByteString0 = (LiteralByteString)object2;
                v = 0;
            }
            else {
                v += v5;
            }
            if(v5 == v4) {
                Object object3 = ropeByteString$PieceIterator1.next();
                literalByteString1 = (LiteralByteString)object3;
                v1 = 0;
            }
            else {
                v1 += v5;
            }
        }
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected int getTreeDepth() {
        return this.treeDepth;
    }

    @Override
    public int hashCode() {
        int v = this.hash;
        if(v == 0) {
            v = this.partialHash(this.totalLength, 0, this.totalLength);
            if(v == 0) {
                v = 1;
            }
            this.hash = v;
        }
        return v;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected boolean isBalanced() {
        return this.totalLength >= RopeByteString.minLengthByDepth[this.treeDepth];
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    public boolean isValidUtf8() {
        int v = this.left.partialIsValidUtf8(0, 0, this.leftLength);
        return this.right.partialIsValidUtf8(v, 0, this.right.size()) == 0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    public Iterator iterator() {
        return this.iterator();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    public ByteIterator iterator() {
        return new RopeByteIterator(this, null);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    public CodedInputStream newCodedInput() {
        return CodedInputStream.newInstance(new RopeInputStream(this));
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
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

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
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

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    protected int peekCachedHashCode() {
        return this.hash;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    public int size() {
        return this.totalLength;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
    public String toString(String s) throws UnsupportedEncodingException {
        return new String(this.toByteArray(), s);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.protobuf.ByteString
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

    class kotlin.reflect.jvm.internal.impl.protobuf.RopeByteString.1 {
    }

}

