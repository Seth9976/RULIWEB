package com.google.android.datatransport.cct.internal;

import com.google.firebase.encoders.annotations.Encodable.Field;
import java.util.List;

final class AutoValue_BatchedLogRequest extends BatchedLogRequest {
    private final List logRequests;

    AutoValue_BatchedLogRequest(List list0) {
        if(list0 == null) {
            throw new NullPointerException("Null logRequests");
        }
        this.logRequests = list0;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof BatchedLogRequest) {
            List list0 = ((BatchedLogRequest)object0).getLogRequests();
            return this.logRequests.equals(list0);
        }
        return false;
    }

    @Override  // com.google.android.datatransport.cct.internal.BatchedLogRequest
    @Field(name = "logRequest")
    public List getLogRequests() {
        return this.logRequests;
    }

    @Override
    public int hashCode() {
        return this.logRequests.hashCode() ^ 1000003;
    }

    @Override
    public String toString() {
        return "BatchedLogRequest{logRequests=" + this.logRequests + "}";
    }
}

