package com.google.android.gms.dynamite;

import android.content.Context;

final class zzh implements VersionPolicy {
    @Override  // com.google.android.gms.dynamite.DynamiteModule$VersionPolicy
    public final SelectionResult selectModule(Context context0, String s, IVersions dynamiteModule$VersionPolicy$IVersions0) throws LoadingException {
        SelectionResult dynamiteModule$VersionPolicy$SelectionResult0 = new SelectionResult();
        int v = 0;
        int v1 = dynamiteModule$VersionPolicy$IVersions0.zzb(context0, s, false);
        dynamiteModule$VersionPolicy$SelectionResult0.remoteVersion = v1;
        if(v1 != 0) {
            v = 1;
        }
        dynamiteModule$VersionPolicy$SelectionResult0.selection = v;
        return dynamiteModule$VersionPolicy$SelectionResult0;
    }
}

