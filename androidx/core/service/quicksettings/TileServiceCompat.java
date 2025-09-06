package androidx.core.service.quicksettings;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build.VERSION;
import android.service.quicksettings.TileService;

public class TileServiceCompat {
    static class Api24Impl {
        static void startActivityAndCollapse(TileService tileService0, Intent intent0) {
            tileService0.startActivityAndCollapse(intent0);
        }
    }

    static class Api34Impl {
        static void startActivityAndCollapse(TileService tileService0, PendingIntent pendingIntent0) {
            tileService0.startActivityAndCollapse(pendingIntent0);
        }
    }

    interface TileServiceWrapper {
        void startActivityAndCollapse(PendingIntent arg1);

        void startActivityAndCollapse(Intent arg1);
    }

    private static TileServiceWrapper sTileServiceWrapper;

    public static void clearTileServiceWrapper() {
        TileServiceCompat.sTileServiceWrapper = null;
    }

    public static void setTileServiceWrapper(TileServiceWrapper tileServiceCompat$TileServiceWrapper0) {
        TileServiceCompat.sTileServiceWrapper = tileServiceCompat$TileServiceWrapper0;
    }

    public static void startActivityAndCollapse(TileService tileService0, PendingIntentActivityWrapper pendingIntentActivityWrapper0) {
        if(Build.VERSION.SDK_INT >= 34) {
            TileServiceWrapper tileServiceCompat$TileServiceWrapper0 = TileServiceCompat.sTileServiceWrapper;
            if(tileServiceCompat$TileServiceWrapper0 != null) {
                tileServiceCompat$TileServiceWrapper0.startActivityAndCollapse(pendingIntentActivityWrapper0.getPendingIntent());
                return;
            }
            Api34Impl.startActivityAndCollapse(tileService0, pendingIntentActivityWrapper0.getPendingIntent());
            return;
        }
        if(Build.VERSION.SDK_INT >= 24) {
            TileServiceWrapper tileServiceCompat$TileServiceWrapper1 = TileServiceCompat.sTileServiceWrapper;
            if(tileServiceCompat$TileServiceWrapper1 != null) {
                tileServiceCompat$TileServiceWrapper1.startActivityAndCollapse(pendingIntentActivityWrapper0.getIntent());
                return;
            }
            Api24Impl.startActivityAndCollapse(tileService0, pendingIntentActivityWrapper0.getIntent());
        }
    }
}

