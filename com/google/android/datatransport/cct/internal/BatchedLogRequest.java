package com.google.android.datatransport.cct.internal;

import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.annotations.Encodable.Field;
import com.google.firebase.encoders.annotations.Encodable;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;
import java.util.List;

@Encodable
public abstract class BatchedLogRequest {
    public static BatchedLogRequest create(List list0) {
        return new AutoValue_BatchedLogRequest(list0);
    }

    public static DataEncoder createDataEncoder() {
        return new JsonDataEncoderBuilder().configureWith(AutoBatchedLogRequestEncoder.CONFIG).ignoreNullValues(true).build();
    }

    @Field(name = "logRequest")
    public abstract List getLogRequests();
}

