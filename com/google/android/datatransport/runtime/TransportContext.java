package com.google.android.datatransport.runtime;

import android.util.Base64;
import com.google.android.datatransport.Priority;

public abstract class TransportContext {
    public static abstract class Builder {
        public abstract TransportContext build();

        public abstract Builder setBackendName(String arg1);

        public abstract Builder setExtras(byte[] arg1);

        public abstract Builder setPriority(Priority arg1);
    }

    public static Builder builder() {
        return new com.google.android.datatransport.runtime.AutoValue_TransportContext.Builder().setPriority(Priority.DEFAULT);
    }

    public abstract String getBackendName();

    public abstract byte[] getExtras();

    public abstract Priority getPriority();

    public boolean shouldUploadClientHealthMetrics() {
        return this.getExtras() != null;
    }

    @Override
    public final String toString() {
        String s = this.getBackendName();
        Priority priority0 = this.getPriority();
        return this.getExtras() == null ? String.format("TransportContext(%s, %s, %s)", s, priority0, "") : String.format("TransportContext(%s, %s, %s)", s, priority0, Base64.encodeToString(this.getExtras(), 2));
    }

    public TransportContext withPriority(Priority priority0) {
        return TransportContext.builder().setBackendName(this.getBackendName()).setPriority(priority0).setExtras(this.getExtras()).build();
    }
}

