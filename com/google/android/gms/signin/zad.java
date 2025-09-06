package com.google.android.gms.signin;

import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;

public final class zad {
    public static final ClientKey zaa;
    public static final ClientKey zab;
    public static final AbstractClientBuilder zac;
    static final AbstractClientBuilder zad;
    public static final Scope zae;
    public static final Scope zaf;
    public static final Api zag;
    public static final Api zah;

    static {
        ClientKey api$ClientKey0 = new ClientKey();
        zad.zaa = api$ClientKey0;
        ClientKey api$ClientKey1 = new ClientKey();
        zad.zab = api$ClientKey1;
        zaa zaa0 = new zaa();
        zad.zac = zaa0;
        zab zab0 = new zab();
        zad.zad = zab0;
        zad.zae = new Scope("profile");
        zad.zaf = new Scope("email");
        zad.zag = new Api("SignIn.API", zaa0, api$ClientKey0);
        zad.zah = new Api("SignIn.INTERNAL_API", zab0, api$ClientKey1);
    }
}

