package com.google.gson.internal.sql;

import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.DefaultDateTypeAdapter.DateType;
import java.sql.Date;
import java.sql.Timestamp;

public final class SqlTypesSupport {
    public static final DateType DATE_DATE_TYPE;
    public static final TypeAdapterFactory DATE_FACTORY;
    public static final boolean SUPPORTS_SQL_TYPES;
    public static final DateType TIMESTAMP_DATE_TYPE;
    public static final TypeAdapterFactory TIMESTAMP_FACTORY;
    public static final TypeAdapterFactory TIME_FACTORY;

    static {
        SqlTypesSupport.SUPPORTS_SQL_TYPES = true;
        SqlTypesSupport.DATE_DATE_TYPE = new DateType(Date.class) {
            protected Date deserialize(java.util.Date date0) {
                return new Date(date0.getTime());
            }

            @Override  // com.google.gson.internal.bind.DefaultDateTypeAdapter$DateType
            protected java.util.Date deserialize(java.util.Date date0) {
                return this.deserialize(date0);
            }
        };
        SqlTypesSupport.TIMESTAMP_DATE_TYPE = new DateType(Timestamp.class) {
            protected Timestamp deserialize(java.util.Date date0) {
                return new Timestamp(date0.getTime());
            }

            @Override  // com.google.gson.internal.bind.DefaultDateTypeAdapter$DateType
            protected java.util.Date deserialize(java.util.Date date0) {
                return this.deserialize(date0);
            }
        };
        SqlTypesSupport.DATE_FACTORY = SqlDateTypeAdapter.FACTORY;
        SqlTypesSupport.TIME_FACTORY = SqlTimeTypeAdapter.FACTORY;
        SqlTypesSupport.TIMESTAMP_FACTORY = SqlTimestampTypeAdapter.FACTORY;
    }
}

