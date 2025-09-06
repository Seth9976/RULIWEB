package androidx.profileinstaller;

import java.util.TreeMap;

class DexProfileData {
    final String apkName;
    int classSetSize;
    int[] classes;
    final long dexChecksum;
    final String dexName;
    final int hotMethodRegionSize;
    long mTypeIdCount;
    final TreeMap methods;
    final int numMethodIds;

    DexProfileData(String s, String s1, long v, long v1, int v2, int v3, int v4, int[] arr_v, TreeMap treeMap0) {
        this.apkName = s;
        this.dexName = s1;
        this.dexChecksum = v;
        this.mTypeIdCount = v1;
        this.classSetSize = v2;
        this.hotMethodRegionSize = v3;
        this.numMethodIds = v4;
        this.classes = arr_v;
        this.methods = treeMap0;
    }
}

