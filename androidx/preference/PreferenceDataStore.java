package androidx.preference;

import java.util.Set;

public abstract class PreferenceDataStore {
    public boolean getBoolean(String s, boolean z) [...] // Inlined contents

    public float getFloat(String s, float f) [...] // Inlined contents

    public int getInt(String s, int v) [...] // Inlined contents

    public long getLong(String s, long v) [...] // Inlined contents

    public String getString(String s, String s1) [...] // Inlined contents

    public Set getStringSet(String s, Set set0) [...] // Inlined contents

    public void putBoolean(String s, boolean z) {
        throw new UnsupportedOperationException("Not implemented on this data store");
    }

    public void putFloat(String s, float f) {
        throw new UnsupportedOperationException("Not implemented on this data store");
    }

    public void putInt(String s, int v) {
        throw new UnsupportedOperationException("Not implemented on this data store");
    }

    public void putLong(String s, long v) {
        throw new UnsupportedOperationException("Not implemented on this data store");
    }

    public void putString(String s, String s1) {
        throw new UnsupportedOperationException("Not implemented on this data store");
    }

    public void putStringSet(String s, Set set0) {
        throw new UnsupportedOperationException("Not implemented on this data store");
    }
}

