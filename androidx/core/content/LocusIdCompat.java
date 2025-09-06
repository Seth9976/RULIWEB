package androidx.core.content;

import android.content.LocusId;
import android.os.Build.VERSION;
import androidx.core.util.Preconditions;

public final class LocusIdCompat {
    static class Api29Impl {
        static LocusId create(String s) {
            return new LocusId(s);
        }

        static String getId(LocusId locusId0) {
            return locusId0.getId();
        }
    }

    private final String mId;
    private final LocusId mWrapped;

    public LocusIdCompat(String s) {
        this.mId = (String)Preconditions.checkStringNotEmpty(s, "id cannot be empty");
        if(Build.VERSION.SDK_INT >= 29) {
            this.mWrapped = Api29Impl.create(s);
            return;
        }
        this.mWrapped = null;
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 == null) {
            return false;
        }
        if(this.getClass() != object0.getClass()) {
            return false;
        }
        return this.mId == null ? ((LocusIdCompat)object0).mId == null : this.mId.equals(((LocusIdCompat)object0).mId);
    }

    public String getId() {
        return this.mId;
    }

    private String getSanitizedId() {
        return this.mId.length() + "_chars";
    }

    @Override
    public int hashCode() {
        return this.mId == null ? 0x1F : this.mId.hashCode() + 0x1F;
    }

    public LocusId toLocusId() {
        return this.mWrapped;
    }

    public static LocusIdCompat toLocusIdCompat(LocusId locusId0) {
        Preconditions.checkNotNull(locusId0, "locusId cannot be null");
        return new LocusIdCompat(((String)Preconditions.checkStringNotEmpty(Api29Impl.getId(locusId0), "id cannot be empty")));
    }

    @Override
    public String toString() {
        return "LocusIdCompat[" + this.getSanitizedId() + "]";
    }
}

