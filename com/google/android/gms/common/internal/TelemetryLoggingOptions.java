package com.google.android.gms.common.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;

public class TelemetryLoggingOptions implements Optional {
    public static class Builder {
        private String zaa;

        private Builder() {
        }

        Builder(zaac zaac0) {
        }

        public TelemetryLoggingOptions build() {
            return new TelemetryLoggingOptions(this.zaa, null);
        }

        public Builder setApi(String s) {
            this.zaa = s;
            return this;
        }
    }

    public static final TelemetryLoggingOptions zaa;
    private final String zab;

    static {
        TelemetryLoggingOptions.zaa = TelemetryLoggingOptions.builder().build();
    }

    TelemetryLoggingOptions(String s, zaad zaad0) {
        this.zab = s;
    }

    public static Builder builder() {
        return new Builder(null);
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        return object0 instanceof TelemetryLoggingOptions ? Objects.equal(this.zab, ((TelemetryLoggingOptions)object0).zab) : false;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zab});
    }

    public final Bundle zaa() {
        Bundle bundle0 = new Bundle();
        String s = this.zab;
        if(s != null) {
            bundle0.putString("api", s);
        }
        return bundle0;
    }
}

