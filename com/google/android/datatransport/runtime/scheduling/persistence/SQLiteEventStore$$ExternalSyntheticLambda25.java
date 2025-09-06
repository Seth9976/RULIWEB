package com.google.android.datatransport.runtime.scheduling.persistence;

public final class SQLiteEventStore..ExternalSyntheticLambda25 implements Producer {
    public final SchemaManager f$0;

    public SQLiteEventStore..ExternalSyntheticLambda25(SchemaManager schemaManager0) {
        this.f$0 = schemaManager0;
    }

    @Override  // com.google.android.datatransport.runtime.scheduling.persistence.SQLiteEventStore$Producer
    public final Object produce() {
        return this.f$0.getWritableDatabase();
    }
}

