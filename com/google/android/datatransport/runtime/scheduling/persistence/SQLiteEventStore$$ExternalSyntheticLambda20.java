package com.google.android.datatransport.runtime.scheduling.persistence;

import android.database.sqlite.SQLiteDatabase;

public final class SQLiteEventStore..ExternalSyntheticLambda20 implements Producer {
    public final SQLiteDatabase f$0;

    public SQLiteEventStore..ExternalSyntheticLambda20(SQLiteDatabase sQLiteDatabase0) {
        this.f$0 = sQLiteDatabase0;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$Producer
    public final Object produce() {
        return SQLiteEventStore.lambda$ensureBeginTransaction$24(this.f$0);
    }
}

