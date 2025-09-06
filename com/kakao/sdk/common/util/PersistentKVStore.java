package com.kakao.sdk.common.util;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001A\u00020\u0000H&J\b\u0010\u0003\u001A\u00020\u0000H&J\u001A\u0010\u0004\u001A\u00020\u00052\u0006\u0010\u0006\u001A\u00020\u00072\b\b\u0002\u0010\b\u001A\u00020\u0005H&J\u001E\u0010\t\u001A\u0004\u0018\u00010\u00072\u0006\u0010\u0006\u001A\u00020\u00072\n\b\u0002\u0010\b\u001A\u0004\u0018\u00010\u0007H&J\u0018\u0010\n\u001A\u00020\u00002\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\u000B\u001A\u00020\u0005H&J\u0018\u0010\f\u001A\u00020\u00002\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\u000B\u001A\u00020\u0007H&J\u0010\u0010\r\u001A\u00020\u00002\u0006\u0010\u0006\u001A\u00020\u0007H&¨\u0006\u000E"}, d2 = {"Lcom/kakao/sdk/common/util/PersistentKVStore;", "", "apply", "commit", "getLong", "", "key", "", "fallbackValue", "getString", "putLong", "value", "putString", "remove", "common_release"}, k = 1, mv = {1, 5, 1}, xi = 0x30)
public interface PersistentKVStore {
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 0x30)
    public static final class DefaultImpls {
        public static long getLong$default(PersistentKVStore persistentKVStore0, String s, long v, int v1, Object object0) {
            if(object0 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getLong");
            }
            if((v1 & 2) != 0) {
                v = 0L;
            }
            return persistentKVStore0.getLong(s, v);
        }

        public static String getString$default(PersistentKVStore persistentKVStore0, String s, String s1, int v, Object object0) {
            if(object0 != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getString");
            }
            if((v & 2) != 0) {
                s1 = null;
            }
            return persistentKVStore0.getString(s, s1);
        }
    }

    PersistentKVStore apply();

    PersistentKVStore commit();

    long getLong(String arg1, long arg2);

    String getString(String arg1, String arg2);

    PersistentKVStore putLong(String arg1, long arg2);

    PersistentKVStore putString(String arg1, String arg2);

    PersistentKVStore remove(String arg1);
}

