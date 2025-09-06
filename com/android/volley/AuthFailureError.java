package com.android.volley;

import android.content.Intent;

public class AuthFailureError extends VolleyError {
    private Intent mResolutionIntent;

    public AuthFailureError() {
    }

    public AuthFailureError(Intent intent0) {
        this.mResolutionIntent = intent0;
    }

    public AuthFailureError(NetworkResponse networkResponse0) {
        super(networkResponse0);
    }

    public AuthFailureError(String s) {
        super(s);
    }

    public AuthFailureError(String s, Exception exception0) {
        super(s, exception0);
    }

    @Override
    public String getMessage() {
        return this.mResolutionIntent == null ? super.getMessage() : "User needs to (re)enter credentials.";
    }

    public Intent getResolutionIntent() {
        return this.mResolutionIntent;
    }
}

