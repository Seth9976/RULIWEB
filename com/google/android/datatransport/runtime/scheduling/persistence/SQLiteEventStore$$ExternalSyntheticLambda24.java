package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;
import com.google.android.datatransport.runtime.TransportContext;

public final class SQLiteEventStore..ExternalSyntheticLambda24 implements Function {
    public final SQLiteEventStore f$0;
    public final TransportContext f$1;

    public SQLiteEventStore..ExternalSyntheticLambda24(SQLiteEventStore sQLiteEventStore0, TransportContext transportContext0) {
        this.f$0 = sQLiteEventStore0;
        this.f$1 = transportContext0;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$Function
    public final Object apply(Object object0) {
        return this.f$0.lambda$loadBatch$8$com-google-android-datatransport-runtime-scheduling-persistence-SQLiteEventStore(this.f$1, ((SQLiteDatabase)object0));
    }
}

