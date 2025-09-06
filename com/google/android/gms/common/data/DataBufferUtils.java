package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.ArrayList;
import jeb.synthetic.TWR;

public final class DataBufferUtils {
    public static final String KEY_NEXT_PAGE_TOKEN = "next_page_token";
    public static final String KEY_PREV_PAGE_TOKEN = "prev_page_token";

    public static ArrayList freezeAndClose(DataBuffer dataBuffer0) {
        ArrayList arrayList0 = new ArrayList(dataBuffer0.getCount());
        try {
            for(Object object0: dataBuffer0) {
                arrayList0.add(((Freezable)object0).freeze());
            }
        }
        catch(Throwable throwable0) {
            TWR.safeClose$NT(dataBuffer0, throwable0);
            throw throwable0;
        }
        dataBuffer0.close();
        return arrayList0;
    }

    public static boolean hasData(DataBuffer dataBuffer0) {
        return dataBuffer0 != null && dataBuffer0.getCount() > 0;
    }

    public static boolean hasNextPage(DataBuffer dataBuffer0) {
        Bundle bundle0 = dataBuffer0.getMetadata();
        return bundle0 != null && bundle0.getString("next_page_token") != null;
    }

    public static boolean hasPrevPage(DataBuffer dataBuffer0) {
        Bundle bundle0 = dataBuffer0.getMetadata();
        return bundle0 != null && bundle0.getString("prev_page_token") != null;
    }
}

