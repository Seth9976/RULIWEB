package com.google.android.datatransport.cct.internal;

public abstract class AndroidClientInfo {
    public static abstract class Builder {
        public abstract AndroidClientInfo build();

        public abstract Builder setApplicationBuild(String arg1);

        public abstract Builder setCountry(String arg1);

        public abstract Builder setDevice(String arg1);

        public abstract Builder setFingerprint(String arg1);

        public abstract Builder setHardware(String arg1);

        public abstract Builder setLocale(String arg1);

        public abstract Builder setManufacturer(String arg1);

        public abstract Builder setMccMnc(String arg1);

        public abstract Builder setModel(String arg1);

        public abstract Builder setOsBuild(String arg1);

        public abstract Builder setProduct(String arg1);

        public abstract Builder setSdkVersion(Integer arg1);
    }

    public static Builder builder() {
        return new com.google.android.datatransport.cct.internal.AutoValue_AndroidClientInfo.Builder();
    }

    public abstract String getApplicationBuild();

    public abstract String getCountry();

    public abstract String getDevice();

    public abstract String getFingerprint();

    public abstract String getHardware();

    public abstract String getLocale();

    public abstract String getManufacturer();

    public abstract String getMccMnc();

    public abstract String getModel();

    public abstract String getOsBuild();

    public abstract String getProduct();

    public abstract Integer getSdkVersion();
}

