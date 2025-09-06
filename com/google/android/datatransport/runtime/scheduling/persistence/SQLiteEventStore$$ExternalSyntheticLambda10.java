package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;

public final class SQLiteEventStore..ExternalSyntheticLambda10 implements Function {
    public final SQLiteEventStore f$0;
    public final long f$1;

    public SQLiteEventStore..ExternalSyntheticLambda10(SQLiteEventStore sQLiteEventStore0, long v) {
        this.f$0 = sQLiteEventStore0;
        this.f$1 = v;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$Function
    public final Object apply(Object object0) {
        return this.f$0.lambda$cleanUp$12$com-google-android-datatransport-runtime-scheduling-persistence-SQLiteEventStore(this.f$1, ((SQLiteDatabase)object0));
    }
}

