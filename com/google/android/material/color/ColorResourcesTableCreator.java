package com.google.android.material.color;

import android.content.Context;
import android.util.Pair;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;

final class ColorResourcesTableCreator {
    static class ColorResource {
        private final short entryId;
        private final String name;
        private final byte packageId;
        private final byte typeId;
        private final int value;

        ColorResource(int v, String s, int v1) {
            this.name = s;
            this.value = v1;
            this.entryId = (short)(0xFFFF & v);
            this.typeId = (byte)(v >> 16 & 0xFF);
            this.packageId = (byte)(v >> 24 & 0xFF);
        }

        static byte access$200(ColorResource colorResourcesTableCreator$ColorResource0) {
            return colorResourcesTableCreator$ColorResource0.typeId;
        }

        static byte access$300(ColorResource colorResourcesTableCreator$ColorResource0) {
            return colorResourcesTableCreator$ColorResource0.packageId;
        }
    }

    static class PackageChunk {
        private static final short HEADER_SIZE = 0x120;
        private static final int PACKAGE_NAME_MAX_LENGTH = 0x80;
        private final ResChunkHeader header;
        private final StringPoolChunk keyStrings;
        private final PackageInfo packageInfo;
        private final TypeSpecChunk typeSpecChunk;
        private final StringPoolChunk typeStrings;

        PackageChunk(PackageInfo colorResourcesTableCreator$PackageInfo0, List list0) {
            this.packageInfo = colorResourcesTableCreator$PackageInfo0;
            this.typeStrings = new StringPoolChunk(false, new String[]{"?1", "?2", "?3", "?4", "?5", "color"});
            String[] arr_s = new String[list0.size()];
            for(int v = 0; v < list0.size(); ++v) {
                arr_s[v] = ((ColorResource)list0.get(v)).name;
            }
            this.keyStrings = new StringPoolChunk(true, arr_s);
            this.typeSpecChunk = new TypeSpecChunk(list0);
            this.header = new ResChunkHeader(0x200, 0x120, this.getChunkSize());
        }

        int getChunkSize() {
            return this.typeStrings.getChunkSize() + 0x120 + this.keyStrings.getChunkSize() + this.typeSpecChunk.getChunkSizeWithTypeChunk();
        }

        void writeTo(ByteArrayOutputStream byteArrayOutputStream0) throws IOException {
            this.header.writeTo(byteArrayOutputStream0);
            byteArrayOutputStream0.write(ColorResourcesTableCreator.intToByteArray(this.packageInfo.id));
            char[] arr_c = this.packageInfo.name.toCharArray();
            for(int v = 0; v < 0x80; ++v) {
                if(v < arr_c.length) {
                    byteArrayOutputStream0.write(ColorResourcesTableCreator.charToByteArray(arr_c[v]));
                }
                else {
                    byteArrayOutputStream0.write(new byte[]{0, 0});
                }
            }
            byteArrayOutputStream0.write(new byte[]{0x20, 1, 0, 0});
            byteArrayOutputStream0.write(new byte[]{0, 0, 0, 0});
            byteArrayOutputStream0.write(ColorResourcesTableCreator.intToByteArray(this.typeStrings.getChunkSize() + 0x120));
            byteArrayOutputStream0.write(new byte[]{0, 0, 0, 0});
            byteArrayOutputStream0.write(new byte[]{0, 0, 0, 0});
            this.typeStrings.writeTo(byteArrayOutputStream0);
            this.keyStrings.writeTo(byteArrayOutputStream0);
            this.typeSpecChunk.writeTo(byteArrayOutputStream0);
        }
    }

    static class PackageInfo {
        private final int id;
        private final String name;

        PackageInfo(int v, String s) {
            this.id = v;
            this.name = s;
        }
    }

    static class ResChunkHeader {
        private final int chunkSize;
        private final short headerSize;
        private final short type;

        ResChunkHeader(short v, short v1, int v2) {
            this.type = v;
            this.headerSize = v1;
            this.chunkSize = v2;
        }

        void writeTo(ByteArrayOutputStream byteArrayOutputStream0) throws IOException {
            byteArrayOutputStream0.write(ColorResourcesTableCreator.shortToByteArray(this.type));
            byteArrayOutputStream0.write(ColorResourcesTableCreator.shortToByteArray(this.headerSize));
            byteArrayOutputStream0.write(ColorResourcesTableCreator.intToByteArray(this.chunkSize));
        }
    }

    static class ResEntry {
        private static final byte DATA_TYPE_AARRGGBB = 28;
        private static final short ENTRY_SIZE = 8;
        private static final short FLAG_PUBLIC = 2;
        private static final int SIZE = 16;
        private static final short VALUE_SIZE = 8;
        private final int data;
        private final int keyStringIndex;

        ResEntry(int v, int v1) {
            this.keyStringIndex = v;
            this.data = v1;
        }

        void writeTo(ByteArrayOutputStream byteArrayOutputStream0) throws IOException {
            byteArrayOutputStream0.write(new byte[]{8, 0});
            byteArrayOutputStream0.write(new byte[]{2, 0});
            byteArrayOutputStream0.write(ColorResourcesTableCreator.intToByteArray(this.keyStringIndex));
            byteArrayOutputStream0.write(new byte[]{8, 0});
            byteArrayOutputStream0.write(new byte[]{0, 28});
            byteArrayOutputStream0.write(ColorResourcesTableCreator.intToByteArray(this.data));
        }
    }

    static class ResTable {
        private static final short HEADER_SIZE = 12;
        private final ResChunkHeader header;
        private final List packageChunks;
        private final int packageCount;
        private final StringPoolChunk stringPool;

        ResTable(Map map0) {
            this.packageChunks = new ArrayList();
            this.packageCount = map0.size();
            this.stringPool = new StringPoolChunk(new String[0]);
            for(Object object0: map0.entrySet()) {
                List list0 = (List)((Map.Entry)object0).getValue();
                Collections.sort(list0, ColorResourcesTableCreator.COLOR_RESOURCE_COMPARATOR);
                PackageChunk colorResourcesTableCreator$PackageChunk0 = new PackageChunk(((PackageInfo)((Map.Entry)object0).getKey()), list0);
                this.packageChunks.add(colorResourcesTableCreator$PackageChunk0);
            }
            this.header = new ResChunkHeader(2, 12, this.getOverallSize());
        }

        private int getOverallSize() {
            int v = 0;
            for(Object object0: this.packageChunks) {
                v += ((PackageChunk)object0).getChunkSize();
            }
            return this.stringPool.getChunkSize() + 12 + v;
        }

        void writeTo(ByteArrayOutputStream byteArrayOutputStream0) throws IOException {
            this.header.writeTo(byteArrayOutputStream0);
            byteArrayOutputStream0.write(ColorResourcesTableCreator.intToByteArray(this.packageCount));
            this.stringPool.writeTo(byteArrayOutputStream0);
            for(Object object0: this.packageChunks) {
                ((PackageChunk)object0).writeTo(byteArrayOutputStream0);
            }
        }
    }

    static class StringPoolChunk {
        private static final int FLAG_UTF8 = 0x100;
        private static final short HEADER_SIZE = 28;
        private static final int STYLED_SPAN_LIST_END = -1;
        private final int chunkSize;
        private final ResChunkHeader header;
        private final int stringCount;
        private final List stringIndex;
        private final List strings;
        private final int stringsPaddingSize;
        private final int stringsStart;
        private final int styledSpanCount;
        private final List styledSpanIndex;
        private final List styledSpans;
        private final int styledSpansStart;
        private final boolean utf8Encode;

        StringPoolChunk(boolean z, String[] arr_s) {
            this.stringIndex = new ArrayList();
            this.styledSpanIndex = new ArrayList();
            this.strings = new ArrayList();
            this.styledSpans = new ArrayList();
            this.utf8Encode = z;
            int v = 0;
            int v2 = 0;
            for(int v1 = 0; v1 < arr_s.length; ++v1) {
                Pair pair0 = this.processString(arr_s[v1]);
                this.stringIndex.add(v2);
                v2 += ((byte[])pair0.first).length;
                this.strings.add(((byte[])pair0.first));
                this.styledSpans.add(((List)pair0.second));
            }
            int v3 = 0;
            for(Object object0: this.styledSpans) {
                for(Object object1: ((List)object0)) {
                    this.stringIndex.add(v2);
                    v2 += ((StringStyledSpan)object1).styleString.length;
                    byte[] arr_b = ((StringStyledSpan)object1).styleString;
                    this.strings.add(arr_b);
                }
                this.styledSpanIndex.add(v3);
                v3 += ((List)object0).size() * 12 + 4;
            }
            int v4 = v2 % 4 == 0 ? 0 : 4 - v2 % 4;
            this.stringsPaddingSize = v4;
            int v5 = this.strings.size();
            this.stringCount = v5;
            this.styledSpanCount = this.strings.size() - arr_s.length;
            boolean z1 = this.strings.size() - arr_s.length > 0;
            if(!z1) {
                this.styledSpanIndex.clear();
                this.styledSpans.clear();
            }
            int v6 = v5 * 4 + 28 + this.styledSpanIndex.size() * 4;
            this.stringsStart = v6;
            int v7 = v2 + v4;
            this.styledSpansStart = z1 ? v6 + v7 : 0;
            if(z1) {
                v = v3;
            }
            int v8 = v6 + v7 + v;
            this.chunkSize = v8;
            this.header = new ResChunkHeader(1, 28, v8);
        }

        StringPoolChunk(String[] arr_s) {
            this(false, arr_s);
        }

        int getChunkSize() {
            return this.chunkSize;
        }

        // 去混淆评级： 低(20)
        private Pair processString(String s) {
            return this.utf8Encode ? new Pair(ColorResourcesTableCreator.stringToByteArrayUtf8(s), Collections.EMPTY_LIST) : new Pair(ColorResourcesTableCreator.stringToByteArray(s), Collections.EMPTY_LIST);
        }

        void writeTo(ByteArrayOutputStream byteArrayOutputStream0) throws IOException {
            this.header.writeTo(byteArrayOutputStream0);
            byteArrayOutputStream0.write(ColorResourcesTableCreator.intToByteArray(this.stringCount));
            byteArrayOutputStream0.write(ColorResourcesTableCreator.intToByteArray(this.styledSpanCount));
            byteArrayOutputStream0.write(ColorResourcesTableCreator.intToByteArray((this.utf8Encode ? 0x100 : 0)));
            byteArrayOutputStream0.write(ColorResourcesTableCreator.intToByteArray(this.stringsStart));
            byteArrayOutputStream0.write(ColorResourcesTableCreator.intToByteArray(this.styledSpansStart));
            for(Object object0: this.stringIndex) {
                byteArrayOutputStream0.write(ColorResourcesTableCreator.intToByteArray(((int)(((Integer)object0)))));
            }
            for(Object object1: this.styledSpanIndex) {
                byteArrayOutputStream0.write(ColorResourcesTableCreator.intToByteArray(((int)(((Integer)object1)))));
            }
            for(Object object2: this.strings) {
                byteArrayOutputStream0.write(((byte[])object2));
            }
            int v = this.stringsPaddingSize;
            if(v > 0) {
                byteArrayOutputStream0.write(new byte[v]);
            }
            for(Object object3: this.styledSpans) {
                for(Object object4: ((List)object3)) {
                    ((StringStyledSpan)object4).writeTo(byteArrayOutputStream0);
                }
                byteArrayOutputStream0.write(new byte[]{-1, -1, -1, -1});
            }
        }
    }

    static class StringStyledSpan {
        private int firstCharacterIndex;
        private int lastCharacterIndex;
        private int nameReference;
        private byte[] styleString;

        void writeTo(ByteArrayOutputStream byteArrayOutputStream0) throws IOException {
            byteArrayOutputStream0.write(ColorResourcesTableCreator.intToByteArray(this.nameReference));
            byteArrayOutputStream0.write(ColorResourcesTableCreator.intToByteArray(this.firstCharacterIndex));
            byteArrayOutputStream0.write(ColorResourcesTableCreator.intToByteArray(this.lastCharacterIndex));
        }
    }

    static class TypeChunk {
        private static final byte CONFIG_SIZE = 0x40;
        private static final short HEADER_SIZE = 84;
        private static final int OFFSET_NO_ENTRY = -1;
        private final byte[] config;
        private final int entryCount;
        private final ResChunkHeader header;
        private final int[] offsetTable;
        private final ResEntry[] resEntries;

        TypeChunk(List list0, Set set0, int v) {
            byte[] arr_b = new byte[0x40];
            this.config = arr_b;
            this.entryCount = v;
            arr_b[0] = 0x40;
            this.resEntries = new ResEntry[list0.size()];
            for(int v2 = 0; v2 < list0.size(); ++v2) {
                this.resEntries[v2] = new ResEntry(v2, ((ColorResource)list0.get(v2)).value);
            }
            this.offsetTable = new int[v];
            int v3 = 0;
            for(int v1 = 0; v1 < v; v1 = (short)(v1 + 1)) {
                if(set0.contains(((short)v1))) {
                    this.offsetTable[v1] = v3;
                    v3 += 16;
                }
                else {
                    this.offsetTable[v1] = -1;
                }
            }
            this.header = new ResChunkHeader(0x201, 84, this.getChunkSize());
        }

        int getChunkSize() {
            return this.getEntryStart() + this.resEntries.length * 16;
        }

        private int getEntryStart() {
            return this.getOffsetTableSize() + 84;
        }

        private int getOffsetTableSize() {
            return this.offsetTable.length * 4;
        }

        void writeTo(ByteArrayOutputStream byteArrayOutputStream0) throws IOException {
            this.header.writeTo(byteArrayOutputStream0);
            byteArrayOutputStream0.write(new byte[]{0, 0, 0, 0});
            byteArrayOutputStream0.write(ColorResourcesTableCreator.intToByteArray(this.entryCount));
            byteArrayOutputStream0.write(ColorResourcesTableCreator.intToByteArray(this.getEntryStart()));
            byteArrayOutputStream0.write(this.config);
            int[] arr_v = this.offsetTable;
            for(int v1 = 0; v1 < arr_v.length; ++v1) {
                byteArrayOutputStream0.write(ColorResourcesTableCreator.intToByteArray(arr_v[v1]));
            }
            ResEntry[] arr_colorResourcesTableCreator$ResEntry = this.resEntries;
            for(int v = 0; v < arr_colorResourcesTableCreator$ResEntry.length; ++v) {
                arr_colorResourcesTableCreator$ResEntry[v].writeTo(byteArrayOutputStream0);
            }
        }
    }

    static class TypeSpecChunk {
        private static final short HEADER_SIZE = 16;
        private static final int SPEC_PUBLIC = 0x40000000;
        private final int entryCount;
        private final int[] entryFlags;
        private final ResChunkHeader header;
        private final TypeChunk typeChunk;

        TypeSpecChunk(List list0) {
            this.entryCount = ((ColorResource)list0.get(list0.size() - 1)).entryId + 1;
            HashSet hashSet0 = new HashSet();
            for(Object object0: list0) {
                hashSet0.add(((ColorResource)object0).entryId);
            }
            this.entryFlags = new int[this.entryCount];
            for(int v = 0; v < this.entryCount; v = (short)(v + 1)) {
                if(hashSet0.contains(((short)v))) {
                    this.entryFlags[v] = 0x40000000;
                }
            }
            this.header = new ResChunkHeader(0x202, 16, this.getChunkSize());
            this.typeChunk = new TypeChunk(list0, hashSet0, this.entryCount);
        }

        private int getChunkSize() {
            return this.entryCount * 4 + 16;
        }

        int getChunkSizeWithTypeChunk() {
            return this.getChunkSize() + this.typeChunk.getChunkSize();
        }

        void writeTo(ByteArrayOutputStream byteArrayOutputStream0) throws IOException {
            this.header.writeTo(byteArrayOutputStream0);
            byteArrayOutputStream0.write(new byte[]{0, 0, 0, 0});
            byteArrayOutputStream0.write(ColorResourcesTableCreator.intToByteArray(this.entryCount));
            int[] arr_v = this.entryFlags;
            for(int v = 0; v < arr_v.length; ++v) {
                byteArrayOutputStream0.write(ColorResourcesTableCreator.intToByteArray(arr_v[v]));
            }
            this.typeChunk.writeTo(byteArrayOutputStream0);
        }
    }

    private static final byte ANDROID_PACKAGE_ID = 1;
    private static final PackageInfo ANDROID_PACKAGE_INFO = null;
    private static final byte APPLICATION_PACKAGE_ID = 0x7F;
    private static final Comparator COLOR_RESOURCE_COMPARATOR = null;
    private static final short HEADER_TYPE_PACKAGE = 0x200;
    private static final short HEADER_TYPE_RES_TABLE = 2;
    private static final short HEADER_TYPE_STRING_POOL = 1;
    private static final short HEADER_TYPE_TYPE = 0x201;
    private static final short HEADER_TYPE_TYPE_SPEC = 0x202;
    private static final String RESOURCE_TYPE_NAME_COLOR = "color";
    private static byte typeIdColor;

    static {
        ColorResourcesTableCreator.ANDROID_PACKAGE_INFO = new PackageInfo(1, "android");
        ColorResourcesTableCreator.COLOR_RESOURCE_COMPARATOR = new Comparator() {
            public int compare(ColorResource colorResourcesTableCreator$ColorResource0, ColorResource colorResourcesTableCreator$ColorResource1) {
                return ColorResource.access$000(colorResourcesTableCreator$ColorResource0) - ColorResource.access$000(colorResourcesTableCreator$ColorResource1);
            }

            @Override
            public int compare(Object object0, Object object1) {
                return this.compare(((ColorResource)object0), ((ColorResource)object1));
            }
        };
    }

    static byte access$1300() [...] // 潜在的解密器

    private static byte[] charToByteArray(char c) {
        return new byte[]{((byte)(c & 0xFF)), ((byte)(c >> 8 & 0xFF))};
    }

    static byte[] create(Context context0, Map map0) throws IOException {
        PackageInfo colorResourcesTableCreator$PackageInfo1;
        if(map0.entrySet().isEmpty()) {
            throw new IllegalArgumentException("No color resources provided for harmonization.");
        }
        PackageInfo colorResourcesTableCreator$PackageInfo0 = new PackageInfo(0x7F, "com.ruliweb.www.ruliapp");
        HashMap hashMap0 = new HashMap();
        ColorResource colorResourcesTableCreator$ColorResource0 = null;
        for(Object object0: map0.entrySet()) {
            ColorResource colorResourcesTableCreator$ColorResource1 = new ColorResource(((int)(((Integer)((Map.Entry)object0).getKey()))), context0.getResources().getResourceName(((int)(((Integer)((Map.Entry)object0).getKey())))), ((int)(((Integer)((Map.Entry)object0).getValue()))));
            if(!context0.getResources().getResourceTypeName(((int)(((Integer)((Map.Entry)object0).getKey())))).equals("color")) {
                throw new IllegalArgumentException("Non color resource found: name=" + ColorResource.access$100(colorResourcesTableCreator$ColorResource1) + ", typeId=" + Integer.toHexString(ColorResource.access$200(colorResourcesTableCreator$ColorResource1) & 0xFF));
            }
            switch(ColorResource.access$300(colorResourcesTableCreator$ColorResource1)) {
                case 1: {
                    colorResourcesTableCreator$PackageInfo1 = ColorResourcesTableCreator.ANDROID_PACKAGE_INFO;
                    break;
                }
                case 0x7F: {
                    colorResourcesTableCreator$PackageInfo1 = colorResourcesTableCreator$PackageInfo0;
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Not supported with unknown package id: " + ColorResource.access$300(colorResourcesTableCreator$ColorResource1));
                }
            }
            if(!hashMap0.containsKey(colorResourcesTableCreator$PackageInfo1)) {
                hashMap0.put(colorResourcesTableCreator$PackageInfo1, new ArrayList());
            }
            ((List)hashMap0.get(colorResourcesTableCreator$PackageInfo1)).add(colorResourcesTableCreator$ColorResource1);
            colorResourcesTableCreator$ColorResource0 = colorResourcesTableCreator$ColorResource1;
        }
        byte b = ColorResource.access$200(colorResourcesTableCreator$ColorResource0);
        ColorResourcesTableCreator.typeIdColor = b;
        if(b == 0) {
            throw new IllegalArgumentException("No color resources found for harmonization.");
        }
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream();
        new ResTable(hashMap0).writeTo(byteArrayOutputStream0);
        return byteArrayOutputStream0.toByteArray();
    }

    private static byte[] intToByteArray(int v) {
        return new byte[]{((byte)(v & 0xFF)), ((byte)(v >> 8 & 0xFF)), ((byte)(v >> 16 & 0xFF)), ((byte)(v >> 24 & 0xFF))};
    }

    private static byte[] shortToByteArray(short v) {
        return new byte[]{((byte)(v & 0xFF)), ((byte)(v >> 8 & 0xFF))};
    }

    private static byte[] stringToByteArray(String s) {
        char[] arr_c = s.toCharArray();
        int v = arr_c.length * 2;
        byte[] arr_b = new byte[v + 4];
        byte[] arr_b1 = ColorResourcesTableCreator.shortToByteArray(((short)arr_c.length));
        arr_b[0] = arr_b1[0];
        arr_b[1] = arr_b1[1];
        for(int v1 = 0; v1 < arr_c.length; ++v1) {
            byte[] arr_b2 = ColorResourcesTableCreator.charToByteArray(arr_c[v1]);
            arr_b[v1 * 2 + 2] = arr_b2[0];
            arr_b[v1 * 2 + 3] = arr_b2[1];
        }
        arr_b[v + 2] = 0;
        arr_b[v + 3] = 0;
        return arr_b;
    }

    private static byte[] stringToByteArrayUtf8(String s) {
        byte[] arr_b = s.getBytes(Charset.forName("UTF-8"));
        byte[] arr_b1 = new byte[arr_b.length + 3];
        System.arraycopy(arr_b, 0, arr_b1, 2, ((int)(((byte)arr_b.length))));
        arr_b1[1] = (byte)arr_b.length;
        arr_b1[0] = (byte)arr_b.length;
        arr_b1[arr_b.length + 2] = 0;
        return arr_b1;
    }
}

