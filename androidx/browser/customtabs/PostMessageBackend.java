package androidx.browser.customtabs;

import android.content.Context;
import android.os.Bundle;

public interface PostMessageBackend {
    void onDisconnectChannel(Context arg1);

    boolean onNotifyMessageChannelReady(Bundle arg1);

    boolean onPostMessage(String arg1, Bundle arg2);
}

