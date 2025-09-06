package androidx.webkit;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class UserAgentMetadata {
    public static final class BrandVersion {
        public static final class Builder {
            private String mBrand;
            private String mFullVersion;
            private String mMajorVersion;

            public Builder() {
            }

            public Builder(BrandVersion userAgentMetadata$BrandVersion0) {
                this.mBrand = userAgentMetadata$BrandVersion0.getBrand();
                this.mMajorVersion = userAgentMetadata$BrandVersion0.getMajorVersion();
                this.mFullVersion = userAgentMetadata$BrandVersion0.getFullVersion();
            }

            public BrandVersion build() {
                if(this.mBrand == null || this.mBrand.trim().isEmpty() || (this.mMajorVersion == null || this.mMajorVersion.trim().isEmpty()) || (this.mFullVersion == null || this.mFullVersion.trim().isEmpty())) {
                    throw new IllegalStateException("Brand name, major version and full version should not be null or blank.");
                }
                return new BrandVersion(this.mBrand, this.mMajorVersion, this.mFullVersion, null);
            }

            public Builder setBrand(String s) {
                if(s.trim().isEmpty()) {
                    throw new IllegalArgumentException("Brand should not be blank.");
                }
                this.mBrand = s;
                return this;
            }

            public Builder setFullVersion(String s) {
                if(s.trim().isEmpty()) {
                    throw new IllegalArgumentException("FullVersion should not be blank.");
                }
                this.mFullVersion = s;
                return this;
            }

            public Builder setMajorVersion(String s) {
                if(s.trim().isEmpty()) {
                    throw new IllegalArgumentException("MajorVersion should not be blank.");
                }
                this.mMajorVersion = s;
                return this;
            }
        }

        private final String mBrand;
        private final String mFullVersion;
        private final String mMajorVersion;

        private BrandVersion(String s, String s1, String s2) {
            this.mBrand = s;
            this.mMajorVersion = s1;
            this.mFullVersion = s2;
        }

        BrandVersion(String s, String s1, String s2, androidx.webkit.UserAgentMetadata.1 userAgentMetadata$10) {
            this(s, s1, s2);
        }

        @Override
        public boolean equals(Object object0) {
            if(this == object0) {
                return true;
            }
            return object0 instanceof BrandVersion ? Objects.equals(this.mBrand, ((BrandVersion)object0).mBrand) && Objects.equals(this.mMajorVersion, ((BrandVersion)object0).mMajorVersion) && Objects.equals(this.mFullVersion, ((BrandVersion)object0).mFullVersion) : false;
        }

        public String getBrand() {
            return this.mBrand;
        }

        public String getFullVersion() {
            return this.mFullVersion;
        }

        public String getMajorVersion() {
            return this.mMajorVersion;
        }

        @Override
        public int hashCode() {
            return Objects.hash(new Object[]{this.mBrand, this.mMajorVersion, this.mFullVersion});
        }

        @Override
        public String toString() {
            return this.mBrand + "," + this.mMajorVersion + "," + this.mFullVersion;
        }
    }

    public static final class androidx.webkit.UserAgentMetadata.Builder {
        private String mArchitecture;
        private int mBitness;
        private List mBrandVersionList;
        private String mFullVersion;
        private boolean mMobile;
        private String mModel;
        private String mPlatform;
        private String mPlatformVersion;
        private boolean mWow64;

        public androidx.webkit.UserAgentMetadata.Builder() {
            this.mBrandVersionList = new ArrayList();
            this.mMobile = true;
            this.mBitness = 0;
            this.mWow64 = false;
        }

        public androidx.webkit.UserAgentMetadata.Builder(UserAgentMetadata userAgentMetadata0) {
            this.mBrandVersionList = new ArrayList();
            this.mMobile = true;
            this.mBitness = 0;
            this.mWow64 = false;
            this.mBrandVersionList = userAgentMetadata0.getBrandVersionList();
            this.mFullVersion = userAgentMetadata0.getFullVersion();
            this.mPlatform = userAgentMetadata0.getPlatform();
            this.mPlatformVersion = userAgentMetadata0.getPlatformVersion();
            this.mArchitecture = userAgentMetadata0.getArchitecture();
            this.mModel = userAgentMetadata0.getModel();
            this.mMobile = userAgentMetadata0.isMobile();
            this.mBitness = userAgentMetadata0.getBitness();
            this.mWow64 = userAgentMetadata0.isWow64();
        }

        public UserAgentMetadata build() {
            return new UserAgentMetadata(this.mBrandVersionList, this.mFullVersion, this.mPlatform, this.mPlatformVersion, this.mArchitecture, this.mModel, this.mMobile, this.mBitness, this.mWow64, null);
        }

        public androidx.webkit.UserAgentMetadata.Builder setArchitecture(String s) {
            this.mArchitecture = s;
            return this;
        }

        public androidx.webkit.UserAgentMetadata.Builder setBitness(int v) {
            this.mBitness = v;
            return this;
        }

        public androidx.webkit.UserAgentMetadata.Builder setBrandVersionList(List list0) {
            this.mBrandVersionList = list0;
            return this;
        }

        public androidx.webkit.UserAgentMetadata.Builder setFullVersion(String s) {
            if(s == null) {
                this.mFullVersion = null;
                return this;
            }
            if(s.trim().isEmpty()) {
                throw new IllegalArgumentException("Full version should not be blank.");
            }
            this.mFullVersion = s;
            return this;
        }

        public androidx.webkit.UserAgentMetadata.Builder setMobile(boolean z) {
            this.mMobile = z;
            return this;
        }

        public androidx.webkit.UserAgentMetadata.Builder setModel(String s) {
            this.mModel = s;
            return this;
        }

        public androidx.webkit.UserAgentMetadata.Builder setPlatform(String s) {
            if(s == null) {
                this.mPlatform = null;
                return this;
            }
            if(s.trim().isEmpty()) {
                throw new IllegalArgumentException("Platform should not be blank.");
            }
            this.mPlatform = s;
            return this;
        }

        public androidx.webkit.UserAgentMetadata.Builder setPlatformVersion(String s) {
            this.mPlatformVersion = s;
            return this;
        }

        public androidx.webkit.UserAgentMetadata.Builder setWow64(boolean z) {
            this.mWow64 = z;
            return this;
        }
    }

    public static final int BITNESS_DEFAULT;
    private final String mArchitecture;
    private int mBitness;
    private final List mBrandVersionList;
    private final String mFullVersion;
    private boolean mMobile;
    private final String mModel;
    private final String mPlatform;
    private final String mPlatformVersion;
    private boolean mWow64;

    private UserAgentMetadata(List list0, String s, String s1, String s2, String s3, String s4, boolean z, int v, boolean z1) {
        this.mBrandVersionList = list0;
        this.mFullVersion = s;
        this.mPlatform = s1;
        this.mPlatformVersion = s2;
        this.mArchitecture = s3;
        this.mModel = s4;
        this.mMobile = z;
        this.mBitness = v;
        this.mWow64 = z1;
    }

    UserAgentMetadata(List list0, String s, String s1, String s2, String s3, String s4, boolean z, int v, boolean z1, androidx.webkit.UserAgentMetadata.1 userAgentMetadata$10) {
        this(list0, s, s1, s2, s3, s4, z, v, z1);
    }

    // 去混淆评级： 低(23)
    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        return object0 instanceof UserAgentMetadata ? this.mMobile == ((UserAgentMetadata)object0).mMobile && this.mBitness == ((UserAgentMetadata)object0).mBitness && this.mWow64 == ((UserAgentMetadata)object0).mWow64 && Objects.equals(this.mBrandVersionList, ((UserAgentMetadata)object0).mBrandVersionList) && Objects.equals(this.mFullVersion, ((UserAgentMetadata)object0).mFullVersion) && Objects.equals(this.mPlatform, ((UserAgentMetadata)object0).mPlatform) && Objects.equals(this.mPlatformVersion, ((UserAgentMetadata)object0).mPlatformVersion) && Objects.equals(this.mArchitecture, ((UserAgentMetadata)object0).mArchitecture) && Objects.equals(this.mModel, ((UserAgentMetadata)object0).mModel) : false;
    }

    public String getArchitecture() {
        return this.mArchitecture;
    }

    public int getBitness() {
        return this.mBitness;
    }

    public List getBrandVersionList() {
        return this.mBrandVersionList;
    }

    public String getFullVersion() {
        return this.mFullVersion;
    }

    public String getModel() {
        return this.mModel;
    }

    public String getPlatform() {
        return this.mPlatform;
    }

    public String getPlatformVersion() {
        return this.mPlatformVersion;
    }

    @Override
    public int hashCode() {
        return Objects.hash(new Object[]{this.mBrandVersionList, this.mFullVersion, this.mPlatform, this.mPlatformVersion, this.mArchitecture, this.mModel, Boolean.valueOf(this.mMobile), this.mBitness, Boolean.valueOf(this.mWow64)});
    }

    public boolean isMobile() {
        return this.mMobile;
    }

    public boolean isWow64() {
        return this.mWow64;
    }

    class androidx.webkit.UserAgentMetadata.1 {
    }

}

