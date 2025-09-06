package androidx.core.provider;

import android.util.Base64;
import androidx.core.util.Preconditions;
import java.util.List;

public final class FontRequest {
    private final List mCertificates;
    private final int mCertificatesArray;
    private final String mIdentifier;
    private final String mProviderAuthority;
    private final String mProviderPackage;
    private final String mQuery;

    public FontRequest(String s, String s1, String s2, int v) {
        this.mProviderAuthority = (String)Preconditions.checkNotNull(s);
        this.mProviderPackage = (String)Preconditions.checkNotNull(s1);
        this.mQuery = (String)Preconditions.checkNotNull(s2);
        this.mCertificates = null;
        Preconditions.checkArgument(v != 0);
        this.mCertificatesArray = v;
        this.mIdentifier = s + "-" + s1 + "-" + s2;
    }

    public FontRequest(String s, String s1, String s2, List list0) {
        this.mProviderAuthority = (String)Preconditions.checkNotNull(s);
        this.mProviderPackage = (String)Preconditions.checkNotNull(s1);
        this.mQuery = (String)Preconditions.checkNotNull(s2);
        this.mCertificates = (List)Preconditions.checkNotNull(list0);
        this.mCertificatesArray = 0;
        this.mIdentifier = s + "-" + s1 + "-" + s2;
    }

    private String createIdentifier(String s, String s1, String s2) [...] // Inlined contents

    public List getCertificates() {
        return this.mCertificates;
    }

    public int getCertificatesArrayResId() {
        return this.mCertificatesArray;
    }

    String getId() {
        return this.mIdentifier;
    }

    @Deprecated
    public String getIdentifier() {
        return this.mIdentifier;
    }

    public String getProviderAuthority() {
        return this.mProviderAuthority;
    }

    public String getProviderPackage() {
        return this.mProviderPackage;
    }

    public String getQuery() {
        return this.mQuery;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("FontRequest {mProviderAuthority: " + this.mProviderAuthority + ", mProviderPackage: " + this.mProviderPackage + ", mQuery: " + this.mQuery + ", mCertificates:");
        for(int v = 0; v < this.mCertificates.size(); ++v) {
            stringBuilder0.append(" [");
            List list0 = (List)this.mCertificates.get(v);
            for(int v1 = 0; v1 < list0.size(); ++v1) {
                stringBuilder0.append(" \"");
                stringBuilder0.append(Base64.encodeToString(((byte[])list0.get(v1)), 0));
                stringBuilder0.append("\"");
            }
            stringBuilder0.append(" ]");
        }
        stringBuilder0.append("}");
        stringBuilder0.append("mCertificatesArray: " + this.mCertificatesArray);
        return stringBuilder0.toString();
    }
}

