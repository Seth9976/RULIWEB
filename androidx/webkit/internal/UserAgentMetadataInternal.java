package androidx.webkit.internal;

import androidx.webkit.UserAgentMetadata.BrandVersion;
import androidx.webkit.UserAgentMetadata.Builder;
import androidx.webkit.UserAgentMetadata;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAgentMetadataInternal {
    private static final String ARCHITECTURE = "ARCHITECTURE";
    private static final String BITNESS = "BITNESS";
    private static final int BRAND_VERSION_LENGTH = 3;
    private static final String BRAND_VERSION_LIST = "BRAND_VERSION_LIST";
    private static final String FULL_VERSION = "FULL_VERSION";
    private static final String MOBILE = "MOBILE";
    private static final String MODEL = "MODEL";
    private static final String PLATFORM = "PLATFORM";
    private static final String PLATFORM_VERSION = "PLATFORM_VERSION";
    private static final String WOW64 = "WOW64";

    static Map convertUserAgentMetadataToMap(UserAgentMetadata userAgentMetadata0) {
        Map map0 = new HashMap();
        map0.put("BRAND_VERSION_LIST", UserAgentMetadataInternal.getBrandVersionArray(userAgentMetadata0.getBrandVersionList()));
        map0.put("FULL_VERSION", userAgentMetadata0.getFullVersion());
        map0.put("PLATFORM", userAgentMetadata0.getPlatform());
        map0.put("PLATFORM_VERSION", userAgentMetadata0.getPlatformVersion());
        map0.put("ARCHITECTURE", userAgentMetadata0.getArchitecture());
        map0.put("MODEL", userAgentMetadata0.getModel());
        map0.put("MOBILE", Boolean.valueOf(userAgentMetadata0.isMobile()));
        map0.put("BITNESS", userAgentMetadata0.getBitness());
        map0.put("WOW64", Boolean.valueOf(userAgentMetadata0.isWow64()));
        return map0;
    }

    private static String[][] getBrandVersionArray(List list0) {
        if(list0 != null && !list0.isEmpty()) {
            String[][] arr2_s = new String[list0.size()][3];
            for(int v = 0; v < list0.size(); ++v) {
                String[] arr_s = arr2_s[v];
                arr_s[0] = ((BrandVersion)list0.get(v)).getBrand();
                String[] arr_s1 = arr2_s[v];
                arr_s1[1] = ((BrandVersion)list0.get(v)).getMajorVersion();
                String[] arr_s2 = arr2_s[v];
                arr_s2[2] = ((BrandVersion)list0.get(v)).getFullVersion();
            }
            return arr2_s;
        }
        return null;
    }

    static UserAgentMetadata getUserAgentMetadataFromMap(Map map0) {
        Builder userAgentMetadata$Builder0 = new Builder();
        Object object0 = map0.get("BRAND_VERSION_LIST");
        if(object0 != null) {
            ArrayList arrayList0 = new ArrayList();
            for(int v = 0; v < ((String[][])object0).length; ++v) {
                String[] arr_s = ((String[][])object0)[v];
                arrayList0.add(new androidx.webkit.UserAgentMetadata.BrandVersion.Builder().setBrand(arr_s[0]).setMajorVersion(arr_s[1]).setFullVersion(arr_s[2]).build());
            }
            userAgentMetadata$Builder0.setBrandVersionList(arrayList0);
        }
        String s = (String)map0.get("FULL_VERSION");
        if(s != null) {
            userAgentMetadata$Builder0.setFullVersion(s);
        }
        String s1 = (String)map0.get("PLATFORM");
        if(s1 != null) {
            userAgentMetadata$Builder0.setPlatform(s1);
        }
        String s2 = (String)map0.get("PLATFORM_VERSION");
        if(s2 != null) {
            userAgentMetadata$Builder0.setPlatformVersion(s2);
        }
        String s3 = (String)map0.get("ARCHITECTURE");
        if(s3 != null) {
            userAgentMetadata$Builder0.setArchitecture(s3);
        }
        String s4 = (String)map0.get("MODEL");
        if(s4 != null) {
            userAgentMetadata$Builder0.setModel(s4);
        }
        Boolean boolean0 = (Boolean)map0.get("MOBILE");
        if(boolean0 != null) {
            userAgentMetadata$Builder0.setMobile(boolean0.booleanValue());
        }
        Integer integer0 = (Integer)map0.get("BITNESS");
        if(integer0 != null) {
            userAgentMetadata$Builder0.setBitness(((int)integer0));
        }
        Boolean boolean1 = (Boolean)map0.get("WOW64");
        if(boolean1 != null) {
            userAgentMetadata$Builder0.setWow64(boolean1.booleanValue());
        }
        return userAgentMetadata$Builder0.build();
    }
}

