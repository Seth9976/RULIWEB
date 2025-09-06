package com.google.android.gms.dynamite;

import android.content.Context;

final class zzg implements VersionPolicy {
    @Override  // com.google.android.gms.dynamite.DynamiteModule$VersionPolicy
    public final SelectionResult selectModule(Context context0, String s, IVersions dynamiteModule$VersionPolicy$IVersions0) throws LoadingException {
        SelectionResult dynamiteModule$VersionPolicy$SelectionResult0 = new SelectionResult();
        int v = dynamiteModule$VersionPolicy$IVersions0.zza(context0, s);
        dynamiteModule$VersionPolicy$SelectionResult0.localVersion = v;
        if(v != 0) {
            dynamiteModule$VersionPolicy$SelectionResult0.selection = -1;
            return dynamiteModule$VersionPolicy$SelectionResult0;
        }
        int v1 = dynamiteModule$VersionPolicy$IVersions0.zzb(context0, s, true);
        dynamiteModule$VersionPolicy$SelectionResult0.remoteVersion = v1;
        if(v1 != 0) {
            dynamiteModule$VersionPolicy$SelectionResult0.selection = 1;
        }
        return dynamiteModule$VersionPolicy$SelectionResult0;
    }
}

