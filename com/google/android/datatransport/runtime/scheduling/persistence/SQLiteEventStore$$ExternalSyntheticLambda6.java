package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.Cursor;
import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics.Builder;
import java.util.Map;

public final class SQLiteEventStore..ExternalSyntheticLambda6 implements Function {
    public final SQLiteEventStore f$0;
    public final Map f$1;
    public final Builder f$2;

    public SQLiteEventStore..ExternalSyntheticLambda6(SQLiteEventStore sQLiteEventStore0, Map map0, Builder clientMetrics$Builder0) {
        this.f$0 = sQLiteEventStore0;
        this.f$1 = map0;
        this.f$2 = clientMetrics$Builder0;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$Function
    public final Object apply(Object object0) {
        return this.f$0.lambda$loadClientMetrics$19$com-google-android-datatransport-runtime-scheduling-persistence-SQLiteEventStore(this.f$1, this.f$2, ((Cursor)object0));
    }
}

