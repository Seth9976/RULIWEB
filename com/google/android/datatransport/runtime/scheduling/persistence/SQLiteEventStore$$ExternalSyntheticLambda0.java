package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.firebase.transport.ClientMetrics.Builder;
import java.util.Map;

public final class SQLiteEventStore..ExternalSyntheticLambda0 implements Function {
    public final SQLiteEventStore f$0;
    public final String f$1;
    public final Map f$2;
    public final Builder f$3;

    public SQLiteEventStore..ExternalSyntheticLambda0(SQLiteEventStore sQLiteEventStore0, String s, Map map0, Builder clientMetrics$Builder0) {
        this.f$0 = sQLiteEventStore0;
        this.f$1 = s;
        this.f$2 = map0;
        this.f$3 = clientMetrics$Builder0;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$Function
    public final Object apply(Object object0) {
        return this.f$0.lambda$loadClientMetrics$20$com-google-android-datatransport-runtime-scheduling-persistence-SQLiteEventStore(this.f$1, this.f$2, this.f$3, ((SQLiteDatabase)object0));
    }
}

