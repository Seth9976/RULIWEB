package com.google.android.datatransport.runtime;

import com.google.android.datatransport.Priority;
import java.util.Arrays;

final class AutoValue_TransportContext extends TransportContext {
    static final class Builder extends com.google.android.datatransport.runtime.TransportContext.Builder {
        private String backendName;
        private byte[] extras;
        private Priority priority;

        @Override  // com.google.android.datatransport.runtime.TransportContext$Builder
        public TransportContext build() {
            String s = this.backendName == null ? " backendName" : "";
            if(this.priority == null) {
                s = s + " priority";
            }
            if(!s.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + s);
            }
            return new AutoValue_TransportContext(this.backendName, this.extras, this.priority, null);
        }

        @Override  // com.google.android.datatransport.runtime.TransportContext$Builder
        public com.google.android.datatransport.runtime.TransportContext.Builder setBackendName(String s) {
            if(s == null) {
                throw new NullPointerException("Null backendName");
            }
            this.backendName = s;
            return this;
        }

        @Override  // com.google.android.datatransport.runtime.TransportContext$Builder
        public com.google.android.datatransport.runtime.TransportContext.Builder setExtras(byte[] arr_b) {
            this.extras = arr_b;
            return this;
        }

        @Override  // com.google.android.datatransport.runtime.TransportContext$Builder
        public com.google.android.datatransport.runtime.TransportContext.Builder setPriority(Priority priority0) {
            if(priority0 == null) {
                throw new NullPointerException("Null priority");
            }
            this.priority = priority0;
            return this;
        }
    }

    private final String backendName;
    private final byte[] extras;
    private final Priority priority;

    private AutoValue_TransportContext(String s, byte[] arr_b, Priority priority0) {
        this.backendName = s;
        this.extras = arr_b;
        this.priority = priority0;
    }

    AutoValue_TransportContext(String s, byte[] arr_b, Priority priority0, com.google.android.datatransport.runtime.AutoValue_TransportContext.1 autoValue_TransportContext$10) {
        this(s, arr_b, priority0);
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof TransportContext) {
            String s = ((TransportContext)object0).getBackendName();
            if(this.backendName.equals(s)) {
                byte[] arr_b = ((TransportContext)object0) instanceof AutoValue_TransportContext ? ((AutoValue_TransportContext)(((TransportContext)object0))).extras : ((TransportContext)object0).getExtras();
                if(Arrays.equals(this.extras, arr_b)) {
                    Priority priority0 = ((TransportContext)object0).getPriority();
                    return this.priority.equals(priority0);
                }
            }
        }
        return false;
    }

    @Override  // com.google.android.datatransport.runtime.TransportContext
    public String getBackendName() {
        return this.backendName;
    }

    @Override  // com.google.android.datatransport.runtime.TransportContext
    public byte[] getExtras() {
        return this.extras;
    }

    @Override  // com.google.android.datatransport.runtime.TransportContext
    public Priority getPriority() {
        return this.priority;
    }

    @Override
    public int hashCode() {
        int v = Arrays.hashCode(this.extras);
        return ((this.backendName.hashCode() ^ 1000003) * 1000003 ^ v) * 1000003 ^ this.priority.hashCode();
    }

    class com.google.android.datatransport.runtime.AutoValue_TransportContext.1 {
    }

}

