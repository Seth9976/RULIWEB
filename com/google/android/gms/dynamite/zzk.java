package com.google.android.gms.dynamite;

import android.content.Context;

final class zzk implements VersionPolicy {
    @Override  // com.google.android.gms.dynamite.DynamiteModule$VersionPolicy
    public final SelectionResult selectModule(Context context0, String s, IVersions dynamiteModule$VersionPolicy$IVersions0) throws LoadingException {
        SelectionResult dynamiteModule$VersionPolicy$SelectionResult0 = new SelectionResult();
        dynamiteModule$VersionPolicy$SelectionResult0.localVersion = dynamiteModule$VersionPolicy$IVersions0.zza(context0, s);
        int v = 1;
        int v1 = dynamiteModule$VersionPolicy$IVersions0.zzb(context0, s, true);
        dynamiteModule$VersionPolicy$SelectionResult0.remoteVersion = v1;
        int v2 = dynamiteModule$VersionPolicy$SelectionResult0.localVersion;
        if(v2 == 0 && v1 == 0) {
            v = 0;
        }
        else if(v1 < v2) {
            v = -1;
        }
        dynamiteModule$VersionPolicy$SelectionResult0.selection = v;
        return dynamiteModule$VersionPolicy$SelectionResult0;
    }
}

