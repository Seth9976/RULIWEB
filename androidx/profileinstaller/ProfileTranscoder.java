package androidx.profileinstaller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Map.Entry;
import java.util.TreeMap;

class ProfileTranscoder {
    private static final int FIRST_FLAG = 1;
    private static final int HOT = 1;
    private static final int INLINE_CACHE_MEGAMORPHIC_ENCODING = 7;
    private static final int INLINE_CACHE_MISSING_TYPES_ENCODING = 6;
    private static final int LAST_FLAG = 4;
    static final byte[] MAGIC_PROF = null;
    static final byte[] MAGIC_PROFM = null;
    private static final int POST_STARTUP = 4;
    private static final int STARTUP = 2;

    static {
        ProfileTranscoder.MAGIC_PROF = new byte[]{0x70, 0x72, 0x6F, 0};
        ProfileTranscoder.MAGIC_PROFM = new byte[]{0x70, 0x72, 109, 0};
    }

    private static int computeMethodFlags(DexProfileData dexProfileData0) {
        int v = 0;
        for(Object object0: dexProfileData0.methods.entrySet()) {
            v |= (int)(((Integer)((Map.Entry)object0).getValue()));
        }
        return v;
    }

    private static byte[] createCompressibleBody(DexProfileData[] arr_dexProfileData, byte[] arr_b) throws IOException {
        int v = 0;
        int v2 = 0;
        for(int v1 = 0; v1 < arr_dexProfileData.length; ++v1) {
            DexProfileData dexProfileData0 = arr_dexProfileData[v1];
            v2 += Encoding.utf8Length(ProfileTranscoder.generateDexKey(dexProfileData0.apkName, dexProfileData0.dexName, arr_b)) + 16 + dexProfileData0.classSetSize * 2 + dexProfileData0.hotMethodRegionSize + ProfileTranscoder.getMethodBitmapStorageSize(dexProfileData0.numMethodIds);
        }
        ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream(v2);
        if(Arrays.equals(arr_b, ProfileVersion.V009_O_MR1)) {
            while(v < arr_dexProfileData.length) {
                DexProfileData dexProfileData1 = arr_dexProfileData[v];
                ProfileTranscoder.writeLineHeader(byteArrayOutputStream0, dexProfileData1, ProfileTranscoder.generateDexKey(dexProfileData1.apkName, dexProfileData1.dexName, arr_b));
                ProfileTranscoder.writeLineData(byteArrayOutputStream0, dexProfileData1);
                ++v;
            }
        }
        else {
            for(int v3 = 0; v3 < arr_dexProfileData.length; ++v3) {
                DexProfileData dexProfileData2 = arr_dexProfileData[v3];
                ProfileTranscoder.writeLineHeader(byteArrayOutputStream0, dexProfileData2, ProfileTranscoder.generateDexKey(dexProfileData2.apkName, dexProfileData2.dexName, arr_b));
            }
            while(v < arr_dexProfileData.length) {
                ProfileTranscoder.writeLineData(byteArrayOutputStream0, arr_dexProfileData[v]);
                ++v;
            }
        }
        if(byteArrayOutputStream0.size() != v2) {
            throw Encoding.error(("The bytes saved do not match expectation. actual=" + byteArrayOutputStream0.size() + " expected=" + v2));
        }
        return byteArrayOutputStream0.toByteArray();
    }

    private static WritableFileSection createCompressibleClassSection(DexProfileData[] arr_dexProfileData) throws IOException {
        try(ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream()) {
            int v1 = 0;
            for(int v = 0; true; ++v) {
                if(v >= arr_dexProfileData.length) {
                    break;
                }
                DexProfileData dexProfileData0 = arr_dexProfileData[v];
                Encoding.writeUInt16(byteArrayOutputStream0, v);
                Encoding.writeUInt16(byteArrayOutputStream0, dexProfileData0.classSetSize);
                v1 = v1 + 4 + dexProfileData0.classSetSize * 2;
                ProfileTranscoder.writeClasses(byteArrayOutputStream0, dexProfileData0);
            }
            byte[] arr_b = byteArrayOutputStream0.toByteArray();
            if(v1 != arr_b.length) {
                throw Encoding.error(("Expected size " + v1 + ", does not match actual size " + arr_b.length));
            }
            return new WritableFileSection(FileSectionType.CLASSES, v1, arr_b, true);
        }
    }

    private static WritableFileSection createCompressibleMethodsSection(DexProfileData[] arr_dexProfileData) throws IOException {
        try(ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream()) {
            int v1 = 0;
            for(int v = 0; true; ++v) {
                if(v >= arr_dexProfileData.length) {
                    break;
                }
                DexProfileData dexProfileData0 = arr_dexProfileData[v];
                int v2 = ProfileTranscoder.computeMethodFlags(dexProfileData0);
                byte[] arr_b = ProfileTranscoder.createMethodBitmapRegionForS(v2, dexProfileData0);
                byte[] arr_b1 = ProfileTranscoder.createMethodsWithInlineCaches(dexProfileData0);
                Encoding.writeUInt16(byteArrayOutputStream0, v);
                int v3 = arr_b.length + 2 + arr_b1.length;
                Encoding.writeUInt32(byteArrayOutputStream0, ((long)v3));
                Encoding.writeUInt16(byteArrayOutputStream0, v2);
                byteArrayOutputStream0.write(arr_b);
                byteArrayOutputStream0.write(arr_b1);
                v1 = v1 + 6 + v3;
            }
            byte[] arr_b2 = byteArrayOutputStream0.toByteArray();
            if(v1 != arr_b2.length) {
                throw Encoding.error(("Expected size " + v1 + ", does not match actual size " + arr_b2.length));
            }
            return new WritableFileSection(FileSectionType.METHODS, v1, arr_b2, true);
        }
    }

    private static byte[] createMethodBitmapRegionForS(int v, DexProfileData dexProfileData0) throws IOException {
        try(ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream()) {
            ProfileTranscoder.writeMethodBitmapForS(byteArrayOutputStream0, v, dexProfileData0);
            return byteArrayOutputStream0.toByteArray();
        }
    }

    private static byte[] createMethodsWithInlineCaches(DexProfileData dexProfileData0) throws IOException {
        try(ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream()) {
            ProfileTranscoder.writeMethodsWithInlineCaches(byteArrayOutputStream0, dexProfileData0);
            return byteArrayOutputStream0.toByteArray();
        }
    }

    private static String enforceSeparator(String s, String s1) {
        if("!".equals(s1)) {
            return s.replace(":", "!");
        }
        return ":".equals(s1) ? s.replace("!", ":") : s;
    }

    private static String extractKey(String s) {
        int v = s.indexOf("!");
        if(v < 0) {
            v = s.indexOf(":");
        }
        return v <= 0 ? s : s.substring(v + 1);
    }

    private static DexProfileData findByDexName(DexProfileData[] arr_dexProfileData, String s) {
        if(arr_dexProfileData.length <= 0) {
            return null;
        }
        String s1 = ProfileTranscoder.extractKey(s);
        for(int v = 0; v < arr_dexProfileData.length; ++v) {
            if(arr_dexProfileData[v].dexName.equals(s1)) {
                return arr_dexProfileData[v];
            }
        }
        return null;
    }

    private static String generateDexKey(String s, String s1, byte[] arr_b) {
        String s2 = ProfileVersion.dexKeySeparator(arr_b);
        if(s.length() <= 0) {
            return ProfileTranscoder.enforceSeparator(s1, s2);
        }
        if(s1.equals("classes.dex")) {
            return s;
        }
        if(!s1.contains("!") && !s1.contains(":")) {
            return s1.endsWith(".apk") ? s1 : s + ProfileVersion.dexKeySeparator(arr_b) + s1;
        }
        return ProfileTranscoder.enforceSeparator(s1, s2);
    }

    private static int getMethodBitmapStorageSize(int v) {
        return (v * 2 + 7 & -8) / 8;
    }

    private static int getMethodBitmapStorageSizeForS(int v, int v1) {
        return ProfileTranscoder.roundUpToByte(Integer.bitCount(v & -2) * v1) / 8;
    }

    private static int methodFlagBitmapIndex(int v, int v1, int v2) {
        switch(v) {
            case 1: {
                throw Encoding.error("HOT methods are not stored in the bitmap");
            }
            case 2: {
                return v1;
            }
            case 4: {
                return v1 + v2;
            }
            default: {
                throw Encoding.error(("Unexpected flag: " + v));
            }
        }
    }

    private static int[] readClasses(InputStream inputStream0, int v) throws IOException {
        int[] arr_v = new int[v];
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            v2 += Encoding.readUInt16(inputStream0);
            arr_v[v1] = v2;
        }
        return arr_v;
    }

    private static int readFlagsFromBitmap(BitSet bitSet0, int v, int v1) {
        int v2 = bitSet0.get(ProfileTranscoder.methodFlagBitmapIndex(2, v, v1)) ? 2 : 0;
        return bitSet0.get(ProfileTranscoder.methodFlagBitmapIndex(4, v, v1)) ? v2 | 4 : v2;
    }

    static byte[] readHeader(InputStream inputStream0, byte[] arr_b) throws IOException {
        if(!Arrays.equals(arr_b, Encoding.read(inputStream0, arr_b.length))) {
            throw Encoding.error("Invalid magic");
        }
        return Encoding.read(inputStream0, ProfileVersion.V010_P.length);
    }

    private static void readHotMethodRegion(InputStream inputStream0, DexProfileData dexProfileData0) throws IOException {
        int v = inputStream0.available() - dexProfileData0.hotMethodRegionSize;
        int v1 = 0;
        while(inputStream0.available() > v) {
            v1 += Encoding.readUInt16(inputStream0);
            dexProfileData0.methods.put(v1, 1);
            for(int v2 = Encoding.readUInt16(inputStream0); v2 > 0; --v2) {
                ProfileTranscoder.skipInlineCache(inputStream0);
            }
        }
        if(inputStream0.available() != v) {
            throw Encoding.error("Read too much data during profile line parse");
        }
    }

    static DexProfileData[] readMeta(InputStream inputStream0, byte[] arr_b, byte[] arr_b1, DexProfileData[] arr_dexProfileData) throws IOException {
        if(Arrays.equals(arr_b, ProfileVersion.METADATA_V001_N)) {
            if(Arrays.equals(ProfileVersion.V015_S, arr_b1)) {
                throw Encoding.error("Requires new Baseline Profile Metadata. Please rebuild the APK with Android Gradle Plugin 7.2 Canary 7 or higher");
            }
            return ProfileTranscoder.readMetadata001(inputStream0, arr_b, arr_dexProfileData);
        }
        if(!Arrays.equals(arr_b, ProfileVersion.METADATA_V002)) {
            throw Encoding.error("Unsupported meta version");
        }
        return ProfileTranscoder.readMetadataV002(inputStream0, arr_b1, arr_dexProfileData);
    }

    static DexProfileData[] readMetadata001(InputStream inputStream0, byte[] arr_b, DexProfileData[] arr_dexProfileData) throws IOException {
        if(!Arrays.equals(arr_b, ProfileVersion.METADATA_V001_N)) {
            throw Encoding.error("Unsupported meta version");
        }
        int v = Encoding.readUInt8(inputStream0);
        long v1 = Encoding.readUInt32(inputStream0);
        byte[] arr_b1 = Encoding.readCompressed(inputStream0, ((int)Encoding.readUInt32(inputStream0)), ((int)v1));
        if(inputStream0.read() <= 0) {
            try(ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(arr_b1)) {
                return ProfileTranscoder.readMetadataForNBody(byteArrayInputStream0, v, arr_dexProfileData);
            }
        }
        throw Encoding.error("Content found after the end of file");
    }

    private static DexProfileData[] readMetadataForNBody(InputStream inputStream0, int v, DexProfileData[] arr_dexProfileData) throws IOException {
        if(inputStream0.available() == 0) {
            return new DexProfileData[0];
        }
        if(v != arr_dexProfileData.length) {
            throw Encoding.error("Mismatched number of dex files found in metadata");
        }
        String[] arr_s = new String[v];
        int[] arr_v = new int[v];
        for(int v2 = 0; v2 < v; ++v2) {
            int v3 = Encoding.readUInt16(inputStream0);
            arr_v[v2] = Encoding.readUInt16(inputStream0);
            arr_s[v2] = Encoding.readString(inputStream0, v3);
        }
        for(int v1 = 0; v1 < v; ++v1) {
            DexProfileData dexProfileData0 = arr_dexProfileData[v1];
            if(!dexProfileData0.dexName.equals(arr_s[v1])) {
                throw Encoding.error("Order of dexfiles in metadata did not match baseline");
            }
            dexProfileData0.classSetSize = arr_v[v1];
            dexProfileData0.classes = ProfileTranscoder.readClasses(inputStream0, dexProfileData0.classSetSize);
        }
        return arr_dexProfileData;
    }

    static DexProfileData[] readMetadataV002(InputStream inputStream0, byte[] arr_b, DexProfileData[] arr_dexProfileData) throws IOException {
        int v = Encoding.readUInt16(inputStream0);
        long v1 = Encoding.readUInt32(inputStream0);
        byte[] arr_b1 = Encoding.readCompressed(inputStream0, ((int)Encoding.readUInt32(inputStream0)), ((int)v1));
        if(inputStream0.read() <= 0) {
            try(ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(arr_b1)) {
                return ProfileTranscoder.readMetadataV002Body(byteArrayInputStream0, arr_b, v, arr_dexProfileData);
            }
        }
        throw Encoding.error("Content found after the end of file");
    }

    private static DexProfileData[] readMetadataV002Body(InputStream inputStream0, byte[] arr_b, int v, DexProfileData[] arr_dexProfileData) throws IOException {
        if(inputStream0.available() == 0) {
            return new DexProfileData[0];
        }
        if(v != arr_dexProfileData.length) {
            throw Encoding.error("Mismatched number of dex files found in metadata");
        }
        for(int v1 = 0; v1 < v; ++v1) {
            Encoding.readUInt16(inputStream0);
            String s = Encoding.readString(inputStream0, Encoding.readUInt16(inputStream0));
            long v2 = Encoding.readUInt32(inputStream0);
            int v3 = Encoding.readUInt16(inputStream0);
            DexProfileData dexProfileData0 = ProfileTranscoder.findByDexName(arr_dexProfileData, s);
            if(dexProfileData0 == null) {
                throw Encoding.error(("Missing profile key: " + s));
            }
            dexProfileData0.mTypeIdCount = v2;
            int[] arr_v = ProfileTranscoder.readClasses(inputStream0, v3);
            if(Arrays.equals(arr_b, ProfileVersion.V001_N)) {
                dexProfileData0.classSetSize = v3;
                dexProfileData0.classes = arr_v;
            }
        }
        return arr_dexProfileData;
    }

    private static void readMethodBitmap(InputStream inputStream0, DexProfileData dexProfileData0) throws IOException {
        BitSet bitSet0 = BitSet.valueOf(Encoding.read(inputStream0, (dexProfileData0.numMethodIds * 2 + 7 & -8) / 8));
        for(int v = 0; v < dexProfileData0.numMethodIds; ++v) {
            int v1 = ProfileTranscoder.readFlagsFromBitmap(bitSet0, v, dexProfileData0.numMethodIds);
            if(v1 != 0) {
                Integer integer0 = (Integer)dexProfileData0.methods.get(v);
                if(integer0 == null) {
                    integer0 = 0;
                }
                dexProfileData0.methods.put(v, ((int)(v1 | ((int)integer0))));
            }
        }
    }

    static DexProfileData[] readProfile(InputStream inputStream0, byte[] arr_b, String s) throws IOException {
        if(!Arrays.equals(arr_b, ProfileVersion.V010_P)) {
            throw Encoding.error("Unsupported version");
        }
        int v = Encoding.readUInt8(inputStream0);
        long v1 = Encoding.readUInt32(inputStream0);
        byte[] arr_b1 = Encoding.readCompressed(inputStream0, ((int)Encoding.readUInt32(inputStream0)), ((int)v1));
        if(inputStream0.read() <= 0) {
            try(ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(arr_b1)) {
                return ProfileTranscoder.readUncompressedBody(byteArrayInputStream0, s, v);
            }
        }
        throw Encoding.error("Content found after the end of file");
    }

    private static DexProfileData[] readUncompressedBody(InputStream inputStream0, String s, int v) throws IOException {
        if(inputStream0.available() == 0) {
            return new DexProfileData[0];
        }
        DexProfileData[] arr_dexProfileData = new DexProfileData[v];
        for(int v2 = 0; v2 < v; ++v2) {
            int v3 = Encoding.readUInt16(inputStream0);
            int v4 = Encoding.readUInt16(inputStream0);
            long v5 = Encoding.readUInt32(inputStream0);
            long v6 = Encoding.readUInt32(inputStream0);
            long v7 = Encoding.readUInt32(inputStream0);
            String s1 = Encoding.readString(inputStream0, v3);
            TreeMap treeMap0 = new TreeMap();
            arr_dexProfileData[v2] = new DexProfileData(s, s1, v6, 0L, v4, ((int)v5), ((int)v7), new int[v4], treeMap0);
        }
        for(int v1 = 0; v1 < v; ++v1) {
            DexProfileData dexProfileData0 = arr_dexProfileData[v1];
            ProfileTranscoder.readHotMethodRegion(inputStream0, dexProfileData0);
            dexProfileData0.classes = ProfileTranscoder.readClasses(inputStream0, dexProfileData0.classSetSize);
            ProfileTranscoder.readMethodBitmap(inputStream0, dexProfileData0);
        }
        return arr_dexProfileData;
    }

    private static int roundUpToByte(int v) [...] // Inlined contents

    private static void setMethodBitmapBit(byte[] arr_b, int v, int v1, DexProfileData dexProfileData0) {
        int v2 = ProfileTranscoder.methodFlagBitmapIndex(v, v1, dexProfileData0.numMethodIds);
        arr_b[v2 / 8] = (byte)(1 << v2 % 8 | arr_b[v2 / 8]);
    }

    private static void skipInlineCache(InputStream inputStream0) throws IOException {
        Encoding.readUInt16(inputStream0);
        int v = Encoding.readUInt8(inputStream0);
        if(v != 6 && v != 7) {
            while(v > 0) {
                Encoding.readUInt8(inputStream0);
                for(int v1 = Encoding.readUInt8(inputStream0); v1 > 0; --v1) {
                    Encoding.readUInt16(inputStream0);
                }
                --v;
            }
        }
    }

    static boolean transcodeAndWriteBody(OutputStream outputStream0, byte[] arr_b, DexProfileData[] arr_dexProfileData) throws IOException {
        if(Arrays.equals(arr_b, ProfileVersion.V015_S)) {
            ProfileTranscoder.writeProfileForS(outputStream0, arr_dexProfileData);
            return true;
        }
        if(Arrays.equals(arr_b, ProfileVersion.V010_P)) {
            ProfileTranscoder.writeProfileForP(outputStream0, arr_dexProfileData);
            return true;
        }
        if(Arrays.equals(arr_b, ProfileVersion.V005_O)) {
            ProfileTranscoder.writeProfileForO(outputStream0, arr_dexProfileData);
            return true;
        }
        if(Arrays.equals(arr_b, ProfileVersion.V009_O_MR1)) {
            ProfileTranscoder.writeProfileForO_MR1(outputStream0, arr_dexProfileData);
            return true;
        }
        if(Arrays.equals(arr_b, ProfileVersion.V001_N)) {
            ProfileTranscoder.writeProfileForN(outputStream0, arr_dexProfileData);
            return true;
        }
        return false;
    }

    private static void writeClasses(OutputStream outputStream0, DexProfileData dexProfileData0) throws IOException {
        int[] arr_v = dexProfileData0.classes;
        int v = 0;
        for(int v1 = 0; v < arr_v.length; v1 = v2) {
            int v2 = arr_v[v];
            Integer integer0 = v2;
            integer0.getClass();
            Encoding.writeUInt16(outputStream0, v2 - v1);
            integer0.getClass();
            ++v;
        }
    }

    private static WritableFileSection writeDexFileSection(DexProfileData[] arr_dexProfileData) throws IOException {
        try(ByteArrayOutputStream byteArrayOutputStream0 = new ByteArrayOutputStream()) {
            Encoding.writeUInt16(byteArrayOutputStream0, arr_dexProfileData.length);
            int v = 2;
            for(int v1 = 0; true; ++v1) {
                if(v1 >= arr_dexProfileData.length) {
                    break;
                }
                DexProfileData dexProfileData0 = arr_dexProfileData[v1];
                Encoding.writeUInt32(byteArrayOutputStream0, dexProfileData0.dexChecksum);
                Encoding.writeUInt32(byteArrayOutputStream0, dexProfileData0.mTypeIdCount);
                Encoding.writeUInt32(byteArrayOutputStream0, ((long)dexProfileData0.numMethodIds));
                String s = ProfileTranscoder.generateDexKey(dexProfileData0.apkName, dexProfileData0.dexName, ProfileVersion.V015_S);
                int v2 = Encoding.utf8Length(s);
                Encoding.writeUInt16(byteArrayOutputStream0, v2);
                v = v + 14 + v2;
                Encoding.writeString(byteArrayOutputStream0, s);
            }
            byte[] arr_b = byteArrayOutputStream0.toByteArray();
            if(v != arr_b.length) {
                throw Encoding.error(("Expected size " + v + ", does not match actual size " + arr_b.length));
            }
            return new WritableFileSection(FileSectionType.DEX_FILES, v, arr_b, false);
        }
    }

    static void writeHeader(OutputStream outputStream0, byte[] arr_b) throws IOException {
        outputStream0.write(ProfileTranscoder.MAGIC_PROF);
        outputStream0.write(arr_b);
    }

    private static void writeLineData(OutputStream outputStream0, DexProfileData dexProfileData0) throws IOException {
        ProfileTranscoder.writeMethodsWithInlineCaches(outputStream0, dexProfileData0);
        ProfileTranscoder.writeClasses(outputStream0, dexProfileData0);
        ProfileTranscoder.writeMethodBitmap(outputStream0, dexProfileData0);
    }

    private static void writeLineHeader(OutputStream outputStream0, DexProfileData dexProfileData0, String s) throws IOException {
        Encoding.writeUInt16(outputStream0, Encoding.utf8Length(s));
        Encoding.writeUInt16(outputStream0, dexProfileData0.classSetSize);
        Encoding.writeUInt32(outputStream0, ((long)dexProfileData0.hotMethodRegionSize));
        Encoding.writeUInt32(outputStream0, dexProfileData0.dexChecksum);
        Encoding.writeUInt32(outputStream0, ((long)dexProfileData0.numMethodIds));
        Encoding.writeString(outputStream0, s);
    }

    private static void writeMethodBitmap(OutputStream outputStream0, DexProfileData dexProfileData0) throws IOException {
        byte[] arr_b = new byte[ProfileTranscoder.getMethodBitmapStorageSize(dexProfileData0.numMethodIds)];
        for(Object object0: dexProfileData0.methods.entrySet()) {
            int v = (int)(((Integer)((Map.Entry)object0).getKey()));
            int v1 = (int)(((Integer)((Map.Entry)object0).getValue()));
            if((v1 & 2) != 0) {
                ProfileTranscoder.setMethodBitmapBit(arr_b, 2, v, dexProfileData0);
            }
            if((v1 & 4) != 0) {
                ProfileTranscoder.setMethodBitmapBit(arr_b, 4, v, dexProfileData0);
            }
        }
        outputStream0.write(arr_b);
    }

    private static void writeMethodBitmapForS(OutputStream outputStream0, int v, DexProfileData dexProfileData0) throws IOException {
        byte[] arr_b = new byte[ProfileTranscoder.getMethodBitmapStorageSizeForS(v, dexProfileData0.numMethodIds)];
        for(Object object0: dexProfileData0.methods.entrySet()) {
            int v1 = (int)(((Integer)((Map.Entry)object0).getKey()));
            int v2 = (int)(((Integer)((Map.Entry)object0).getValue()));
            int v3 = 0;
            for(int v4 = 1; v4 <= 4; v4 <<= 1) {
                if(v4 != 1 && (v4 & v) != 0) {
                    if((v4 & v2) == v4) {
                        int v5 = dexProfileData0.numMethodIds * v3 + v1;
                        arr_b[v5 / 8] = (byte)(1 << v5 % 8 | arr_b[v5 / 8]);
                    }
                    ++v3;
                }
            }
        }
        outputStream0.write(arr_b);
    }

    private static void writeMethodsWithInlineCaches(OutputStream outputStream0, DexProfileData dexProfileData0) throws IOException {
        int v = 0;
        for(Object object0: dexProfileData0.methods.entrySet()) {
            int v1 = (int)(((Integer)((Map.Entry)object0).getKey()));
            if((((int)(((Integer)((Map.Entry)object0).getValue()))) & 1) != 0) {
                Encoding.writeUInt16(outputStream0, v1 - v);
                Encoding.writeUInt16(outputStream0, 0);
                v = v1;
            }
        }
    }

    private static void writeProfileForN(OutputStream outputStream0, DexProfileData[] arr_dexProfileData) throws IOException {
        Encoding.writeUInt16(outputStream0, arr_dexProfileData.length);
        for(int v = 0; v < arr_dexProfileData.length; ++v) {
            DexProfileData dexProfileData0 = arr_dexProfileData[v];
            String s = ProfileTranscoder.generateDexKey(dexProfileData0.apkName, dexProfileData0.dexName, ProfileVersion.V001_N);
            Encoding.writeUInt16(outputStream0, Encoding.utf8Length(s));
            Encoding.writeUInt16(outputStream0, dexProfileData0.methods.size());
            Encoding.writeUInt16(outputStream0, dexProfileData0.classes.length);
            Encoding.writeUInt32(outputStream0, dexProfileData0.dexChecksum);
            Encoding.writeString(outputStream0, s);
            for(Object object0: dexProfileData0.methods.keySet()) {
                Encoding.writeUInt16(outputStream0, ((int)(((Integer)object0))));
            }
            int[] arr_v = dexProfileData0.classes;
            for(int v1 = 0; v1 < arr_v.length; ++v1) {
                Encoding.writeUInt16(outputStream0, arr_v[v1]);
            }
        }
    }

    private static void writeProfileForO(OutputStream outputStream0, DexProfileData[] arr_dexProfileData) throws IOException {
        Encoding.writeUInt8(outputStream0, arr_dexProfileData.length);
        for(int v = 0; v < arr_dexProfileData.length; ++v) {
            DexProfileData dexProfileData0 = arr_dexProfileData[v];
            String s = ProfileTranscoder.generateDexKey(dexProfileData0.apkName, dexProfileData0.dexName, ProfileVersion.V005_O);
            Encoding.writeUInt16(outputStream0, Encoding.utf8Length(s));
            Encoding.writeUInt16(outputStream0, dexProfileData0.classes.length);
            Encoding.writeUInt32(outputStream0, ((long)(dexProfileData0.methods.size() * 4)));
            Encoding.writeUInt32(outputStream0, dexProfileData0.dexChecksum);
            Encoding.writeString(outputStream0, s);
            for(Object object0: dexProfileData0.methods.keySet()) {
                Encoding.writeUInt16(outputStream0, ((int)(((Integer)object0))));
                Encoding.writeUInt16(outputStream0, 0);
            }
            int[] arr_v = dexProfileData0.classes;
            for(int v1 = 0; v1 < arr_v.length; ++v1) {
                Encoding.writeUInt16(outputStream0, arr_v[v1]);
            }
        }
    }

    private static void writeProfileForO_MR1(OutputStream outputStream0, DexProfileData[] arr_dexProfileData) throws IOException {
        byte[] arr_b = ProfileTranscoder.createCompressibleBody(arr_dexProfileData, ProfileVersion.V009_O_MR1);
        Encoding.writeUInt8(outputStream0, arr_dexProfileData.length);
        Encoding.writeCompressed(outputStream0, arr_b);
    }

    private static void writeProfileForP(OutputStream outputStream0, DexProfileData[] arr_dexProfileData) throws IOException {
        byte[] arr_b = ProfileTranscoder.createCompressibleBody(arr_dexProfileData, ProfileVersion.V010_P);
        Encoding.writeUInt8(outputStream0, arr_dexProfileData.length);
        Encoding.writeCompressed(outputStream0, arr_b);
    }

    private static void writeProfileForS(OutputStream outputStream0, DexProfileData[] arr_dexProfileData) throws IOException {
        ProfileTranscoder.writeProfileSections(outputStream0, arr_dexProfileData);
    }

    private static void writeProfileSections(OutputStream outputStream0, DexProfileData[] arr_dexProfileData) throws IOException {
        int v3;
        ArrayList arrayList0 = new ArrayList(3);
        ArrayList arrayList1 = new ArrayList(3);
        arrayList0.add(ProfileTranscoder.writeDexFileSection(arr_dexProfileData));
        arrayList0.add(ProfileTranscoder.createCompressibleClassSection(arr_dexProfileData));
        arrayList0.add(ProfileTranscoder.createCompressibleMethodsSection(arr_dexProfileData));
        long v = ((long)ProfileVersion.V015_S.length) + ((long)ProfileTranscoder.MAGIC_PROF.length) + 4L + ((long)(arrayList0.size() * 16));
        Encoding.writeUInt32(outputStream0, ((long)arrayList0.size()));
        for(int v2 = 0; v2 < arrayList0.size(); ++v2) {
            WritableFileSection writableFileSection0 = (WritableFileSection)arrayList0.get(v2);
            Encoding.writeUInt32(outputStream0, writableFileSection0.mType.getValue());
            Encoding.writeUInt32(outputStream0, v);
            if(writableFileSection0.mNeedsCompression) {
                byte[] arr_b = Encoding.compress(writableFileSection0.mContents);
                arrayList1.add(arr_b);
                Encoding.writeUInt32(outputStream0, ((long)arr_b.length));
                Encoding.writeUInt32(outputStream0, ((long)writableFileSection0.mContents.length));
                v3 = arr_b.length;
            }
            else {
                arrayList1.add(writableFileSection0.mContents);
                Encoding.writeUInt32(outputStream0, ((long)writableFileSection0.mContents.length));
                Encoding.writeUInt32(outputStream0, 0L);
                v3 = writableFileSection0.mContents.length;
            }
            v += (long)v3;
        }
        for(int v1 = 0; v1 < arrayList1.size(); ++v1) {
            outputStream0.write(((byte[])arrayList1.get(v1)));
        }
    }
}

