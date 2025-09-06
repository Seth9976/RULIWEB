package com.google.android.gms.dynamite;

import android.content.Context;

final class zzj implements VersionPolicy {
    @Override  // com.google.android.gms.dynamite.DynamiteModule$VersionPolicy
    public final SelectionResult selectModule(Context context0, String s, IVersions dynamiteModule$VersionPolicy$IVersions0) throws LoadingException {
        SelectionResult dynamiteModule$VersionPolicy$SelectionResult0 = new SelectionResult();
        int v = dynamiteModule$VersionPolicy$IVersions0.zza(context0, s);
        dynamiteModule$VersionPolicy$SelectionResult0.localVersion = v;
        int v1 = 1;
        int v2 = 0;
        int v3 = v == 0 ? dynamiteModule$VersionPolicy$IVersions0.zzb(context0, s, true) : dynamiteModule$VersionPolicy$IVersions0.zzb(context0, s, false);
        dynamiteModule$VersionPolicy$SelectionResult0.remoteVersion = v3;
        int v4 = dynamiteModule$VersionPolicy$SelectionResult0.localVersion;
        if(v4 != 0) {
            v2 = v4;
        label_17:
            if(v2 >= v3) {
                v1 = -1;
            }
        }
        else if(v3 == 0) {
            v1 = 0;
        }
        else {
            goto label_17;
        }
        dynamiteModule$VersionPolicy$SelectionResult0.selection = v1;
        return dynamiteModule$VersionPolicy$SelectionResult0;
    }
}

