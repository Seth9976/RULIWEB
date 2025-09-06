package com.google.android.gms.common.api;

public class Response {
    private Result zza;

    public Response() {
    }

    protected Response(Result result0) {
        this.zza = result0;
    }

    protected Result getResult() {
        return this.zza;
    }

    public void setResult(Result result0) {
        this.zza = result0;
    }
}

