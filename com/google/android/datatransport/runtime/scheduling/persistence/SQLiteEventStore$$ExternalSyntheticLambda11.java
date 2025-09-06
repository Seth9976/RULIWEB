package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;

public final class SQLiteEventStore..ExternalSyntheticLambda11 implements Function {
    public final SQLiteEventStore f$0;
    public final String f$1;
    public final String f$2;

    public SQLiteEventStore..ExternalSyntheticLambda11(SQLiteEventStore sQLiteEventStore0, String s, String s1) {
        this.f$0 = sQLiteEventStore0;
        this.f$1 = s;
        this.f$2 = s1;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$Function
    public final Object apply(Object object0) {
        return this.f$0.lambda$recordFailure$4$com-google-android-datatransport-runtime-scheduling-persistence-SQLiteEventStore(this.f$1, this.f$2, ((SQLiteDatabase)object0));
    }
}

