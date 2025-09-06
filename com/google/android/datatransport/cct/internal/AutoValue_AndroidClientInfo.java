package com.google.android.datatransport.cct.internal;

final class AutoValue_AndroidClientInfo extends AndroidClientInfo {
    static final class Builder extends com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder {
        private String applicationBuild;
        private String country;
        private String device;
        private String fingerprint;
        private String hardware;
        private String locale;
        private String manufacturer;
        private String mccMnc;
        private String model;
        private String osBuild;
        private String product;
        private Integer sdkVersion;

        @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo$Builder
        public AndroidClientInfo build() {
            return new AutoValue_AndroidClientInfo(this.sdkVersion, this.model, this.hardware, this.device, this.product, this.osBuild, this.manufacturer, this.fingerprint, this.locale, this.country, this.mccMnc, this.applicationBuild, null);
        }

        @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo$Builder
        public com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder setApplicationBuild(String s) {
            this.applicationBuild = s;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo$Builder
        public com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder setCountry(String s) {
            this.country = s;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo$Builder
        public com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder setDevice(String s) {
            this.device = s;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo$Builder
        public com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder setFingerprint(String s) {
            this.fingerprint = s;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo$Builder
        public com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder setHardware(String s) {
            this.hardware = s;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo$Builder
        public com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder setLocale(String s) {
            this.locale = s;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo$Builder
        public com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder setManufacturer(String s) {
            this.manufacturer = s;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo$Builder
        public com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder setMccMnc(String s) {
            this.mccMnc = s;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo$Builder
        public com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder setModel(String s) {
            this.model = s;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo$Builder
        public com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder setOsBuild(String s) {
            this.osBuild = s;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo$Builder
        public com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder setProduct(String s) {
            this.product = s;
            return this;
        }

        @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo$Builder
        public com.google.android.datatransport.cct.internal.AndroidClientInfo.Builder setSdkVersion(Integer integer0) {
            this.sdkVersion = integer0;
            return this;
        }
    }

    private final String applicationBuild;
    private final String country;
    private final String device;
    private final String fingerprint;
    private final String hardware;
    private final String locale;
    private final String manufacturer;
    private final String mccMnc;
    private final String model;
    private final String osBuild;
    private final String product;
    private final Integer sdkVersion;

    private AutoValue_AndroidClientInfo(Integer integer0, String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10) {
        this.sdkVersion = integer0;
        this.model = s;
        this.hardware = s1;
        this.device = s2;
        this.product = s3;
        this.osBuild = s4;
        this.manufacturer = s5;
        this.fingerprint = s6;
        this.locale = s7;
        this.country = s8;
        this.mccMnc = s9;
        this.applicationBuild = s10;
    }

    AutoValue_AndroidClientInfo(Integer integer0, String s, String s1, String s2, String s3, String s4, String s5, String s6, String s7, String s8, String s9, String s10, com.google.android.datatransport.cct.internal.AutoValue_AndroidClientInfo.1 autoValue_AndroidClientInfo$10) {
        this(integer0, s, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10);
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof AndroidClientInfo) {
            AndroidClientInfo androidClientInfo0 = (AndroidClientInfo)object0;
            Integer integer0 = this.sdkVersion;
            if(integer0 != null) {
                if(integer0.equals(androidClientInfo0.getSdkVersion())) {
                label_9:
                    String s = this.model;
                    if(s != null) {
                        if(s.equals(androidClientInfo0.getModel())) {
                        label_14:
                            String s1 = this.hardware;
                            if(s1 != null) {
                                if(s1.equals(androidClientInfo0.getHardware())) {
                                label_19:
                                    String s2 = this.device;
                                    if(s2 != null) {
                                        if(s2.equals(androidClientInfo0.getDevice())) {
                                        label_24:
                                            String s3 = this.product;
                                            if(s3 != null) {
                                                if(s3.equals(androidClientInfo0.getProduct())) {
                                                label_29:
                                                    String s4 = this.osBuild;
                                                    if(s4 != null) {
                                                        if(s4.equals(androidClientInfo0.getOsBuild())) {
                                                        label_34:
                                                            String s5 = this.manufacturer;
                                                            if(s5 != null) {
                                                                if(s5.equals(androidClientInfo0.getManufacturer())) {
                                                                label_39:
                                                                    String s6 = this.fingerprint;
                                                                    if(s6 != null) {
                                                                        if(s6.equals(androidClientInfo0.getFingerprint())) {
                                                                        label_44:
                                                                            String s7 = this.locale;
                                                                            if(s7 != null) {
                                                                                if(s7.equals(androidClientInfo0.getLocale())) {
                                                                                label_49:
                                                                                    String s8 = this.country;
                                                                                    if(s8 != null) {
                                                                                        if(s8.equals(androidClientInfo0.getCountry())) {
                                                                                        label_54:
                                                                                            String s9 = this.mccMnc;
                                                                                            if(s9 == null) {
                                                                                                if(androidClientInfo0.getMccMnc() == null) {
                                                                                                    return this.applicationBuild == null ? androidClientInfo0.getApplicationBuild() == null : this.applicationBuild.equals(androidClientInfo0.getApplicationBuild());
                                                                                                }
                                                                                            }
                                                                                            else if(s9.equals(androidClientInfo0.getMccMnc())) {
                                                                                                return this.applicationBuild == null ? androidClientInfo0.getApplicationBuild() == null : this.applicationBuild.equals(androidClientInfo0.getApplicationBuild());
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                    else if(androidClientInfo0.getCountry() == null) {
                                                                                        goto label_54;
                                                                                    }
                                                                                }
                                                                            }
                                                                            else if(androidClientInfo0.getLocale() == null) {
                                                                                goto label_49;
                                                                            }
                                                                        }
                                                                    }
                                                                    else if(androidClientInfo0.getFingerprint() == null) {
                                                                        goto label_44;
                                                                    }
                                                                }
                                                            }
                                                            else if(androidClientInfo0.getManufacturer() == null) {
                                                                goto label_39;
                                                            }
                                                        }
                                                    }
                                                    else if(androidClientInfo0.getOsBuild() == null) {
                                                        goto label_34;
                                                    }
                                                }
                                            }
                                            else if(androidClientInfo0.getProduct() == null) {
                                                goto label_29;
                                            }
                                        }
                                    }
                                    else if(androidClientInfo0.getDevice() == null) {
                                        goto label_24;
                                    }
                                }
                            }
                            else if(androidClientInfo0.getHardware() == null) {
                                goto label_19;
                            }
                        }
                    }
                    else if(androidClientInfo0.getModel() == null) {
                        goto label_14;
                    }
                }
            }
            else if(androidClientInfo0.getSdkVersion() == null) {
                goto label_9;
            }
        }
        return false;
    }

    @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getApplicationBuild() {
        return this.applicationBuild;
    }

    @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getCountry() {
        return this.country;
    }

    @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getDevice() {
        return this.device;
    }

    @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getFingerprint() {
        return this.fingerprint;
    }

    @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getHardware() {
        return this.hardware;
    }

    @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getLocale() {
        return this.locale;
    }

    @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getManufacturer() {
        return this.manufacturer;
    }

    @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getMccMnc() {
        return this.mccMnc;
    }

    @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getModel() {
        return this.model;
    }

    @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getOsBuild() {
        return this.osBuild;
    }

    @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public String getProduct() {
        return this.product;
    }

    @Override  // com.google.android.datatransport.cct.internal.AndroidClientInfo
    public Integer getSdkVersion() {
        return this.sdkVersion;
    }

    @Override
    public int hashCode() {
        int v = 0;
        int v1 = this.sdkVersion == null ? 0 : this.sdkVersion.hashCode();
        int v2 = this.model == null ? 0 : this.model.hashCode();
        int v3 = this.hardware == null ? 0 : this.hardware.hashCode();
        int v4 = this.device == null ? 0 : this.device.hashCode();
        int v5 = this.product == null ? 0 : this.product.hashCode();
        int v6 = this.osBuild == null ? 0 : this.osBuild.hashCode();
        int v7 = this.manufacturer == null ? 0 : this.manufacturer.hashCode();
        int v8 = this.fingerprint == null ? 0 : this.fingerprint.hashCode();
        int v9 = this.locale == null ? 0 : this.locale.hashCode();
        int v10 = this.country == null ? 0 : this.country.hashCode();
        int v11 = this.mccMnc == null ? 0 : this.mccMnc.hashCode();
        String s = this.applicationBuild;
        if(s != null) {
            v = s.hashCode();
        }
        return (((((((((((v1 ^ 1000003) * 1000003 ^ v2) * 1000003 ^ v3) * 1000003 ^ v4) * 1000003 ^ v5) * 1000003 ^ v6) * 1000003 ^ v7) * 1000003 ^ v8) * 1000003 ^ v9) * 1000003 ^ v10) * 1000003 ^ v11) * 1000003 ^ v;
    }

    @Override
    public String toString() {
        return "AndroidClientInfo{sdkVersion=" + this.sdkVersion + ", model=" + this.model + ", hardware=" + this.hardware + ", device=" + this.device + ", product=" + this.product + ", osBuild=" + this.osBuild + ", manufacturer=" + this.manufacturer + ", fingerprint=" + this.fingerprint + ", locale=" + this.locale + ", country=" + this.country + ", mccMnc=" + this.mccMnc + ", applicationBuild=" + this.applicationBuild + "}";
    }

    class com.google.android.datatransport.cct.internal.AutoValue_AndroidClientInfo.1 {
    }

}

