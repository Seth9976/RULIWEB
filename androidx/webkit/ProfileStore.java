package androidx.webkit;

import java.util.List;

public interface ProfileStore {
    boolean deleteProfile(String arg1);

    List getAllProfileNames();

    Profile getOrCreateProfile(String arg1);

    Profile getProfile(String arg1);
}

