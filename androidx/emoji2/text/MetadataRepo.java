package androidx.emoji2.text;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.SparseArray;
import androidx.core.os.TraceCompat;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.flatbuffer.MetadataList;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class MetadataRepo {
    static class Node {
        private final SparseArray mChildren;
        private TypefaceEmojiRasterizer mData;

        private Node() {
            this(1);
        }

        Node(int v) {
            this.mChildren = new SparseArray(v);
        }

        Node get(int v) {
            return this.mChildren == null ? null : ((Node)this.mChildren.get(v));
        }

        final TypefaceEmojiRasterizer getData() {
            return this.mData;
        }

        void put(TypefaceEmojiRasterizer typefaceEmojiRasterizer0, int v, int v1) {
            Node metadataRepo$Node0 = this.get(typefaceEmojiRasterizer0.getCodepointAt(v));
            if(metadataRepo$Node0 == null) {
                metadataRepo$Node0 = new Node();
                int v2 = typefaceEmojiRasterizer0.getCodepointAt(v);
                this.mChildren.put(v2, metadataRepo$Node0);
            }
            if(v1 > v) {
                metadataRepo$Node0.put(typefaceEmojiRasterizer0, v + 1, v1);
                return;
            }
            metadataRepo$Node0.mData = typefaceEmojiRasterizer0;
        }
    }

    private static final int DEFAULT_ROOT_SIZE = 0x400;
    private static final String S_TRACE_CREATE_REPO = "EmojiCompat.MetadataRepo.create";
    private final char[] mEmojiCharArray;
    private final MetadataList mMetadataList;
    private final Node mRootNode;
    private final Typeface mTypeface;

    private MetadataRepo(Typeface typeface0, MetadataList metadataList0) {
        this.mTypeface = typeface0;
        this.mMetadataList = metadataList0;
        this.mRootNode = new Node(0x400);
        this.mEmojiCharArray = new char[metadataList0.listLength() * 2];
        this.constructIndex(metadataList0);
    }

    private void constructIndex(MetadataList metadataList0) {
        int v = metadataList0.listLength();
        for(int v1 = 0; v1 < v; ++v1) {
            TypefaceEmojiRasterizer typefaceEmojiRasterizer0 = new TypefaceEmojiRasterizer(this, v1);
            typefaceEmojiRasterizer0.getId();
            this.put(typefaceEmojiRasterizer0);
        }
    }

    public static MetadataRepo create(AssetManager assetManager0, String s) throws IOException {
        try {
            TraceCompat.beginSection("EmojiCompat.MetadataRepo.create");
            return new MetadataRepo(Typeface.createFromAsset(assetManager0, s), MetadataListReader.read(assetManager0, s));
        }
        finally {
            TraceCompat.endSection();
        }
    }

    public static MetadataRepo create(Typeface typeface0) {
        try {
            TraceCompat.beginSection("EmojiCompat.MetadataRepo.create");
            return new MetadataRepo(typeface0, new MetadataList());
        }
        finally {
            TraceCompat.endSection();
        }
    }

    public static MetadataRepo create(Typeface typeface0, InputStream inputStream0) throws IOException {
        try {
            TraceCompat.beginSection("EmojiCompat.MetadataRepo.create");
            return new MetadataRepo(typeface0, MetadataListReader.read(inputStream0));
        }
        finally {
            TraceCompat.endSection();
        }
    }

    public static MetadataRepo create(Typeface typeface0, ByteBuffer byteBuffer0) throws IOException {
        try {
            TraceCompat.beginSection("EmojiCompat.MetadataRepo.create");
            return new MetadataRepo(typeface0, MetadataListReader.read(byteBuffer0));
        }
        finally {
            TraceCompat.endSection();
        }
    }

    public char[] getEmojiCharArray() {
        return this.mEmojiCharArray;
    }

    public MetadataList getMetadataList() {
        return this.mMetadataList;
    }

    int getMetadataVersion() {
        return this.mMetadataList.version();
    }

    Node getRootNode() {
        return this.mRootNode;
    }

    Typeface getTypeface() {
        return this.mTypeface;
    }

    void put(TypefaceEmojiRasterizer typefaceEmojiRasterizer0) {
        Preconditions.checkNotNull(typefaceEmojiRasterizer0, "emoji metadata cannot be null");
        Preconditions.checkArgument(typefaceEmojiRasterizer0.getCodepointsLength() > 0, "invalid metadata codepoint length");
        int v = typefaceEmojiRasterizer0.getCodepointsLength();
        this.mRootNode.put(typefaceEmojiRasterizer0, 0, v - 1);
    }
}

