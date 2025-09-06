package androidx.core.net;

import android.net.TrafficStats;
import android.os.Build.VERSION;
import android.os.ParcelFileDescriptor;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

public final class TrafficStatsCompat {
    static class Api24Impl {
        static void tagDatagramSocket(DatagramSocket datagramSocket0) throws SocketException {
            TrafficStats.tagDatagramSocket(datagramSocket0);
        }

        static void untagDatagramSocket(DatagramSocket datagramSocket0) throws SocketException {
            TrafficStats.untagDatagramSocket(datagramSocket0);
        }
    }

    @Deprecated
    public static void clearThreadStatsTag() {
        TrafficStats.clearThreadStatsTag();
    }

    @Deprecated
    public static int getThreadStatsTag() {
        return TrafficStats.getThreadStatsTag();
    }

    @Deprecated
    public static void incrementOperationCount(int v) {
        TrafficStats.incrementOperationCount(v);
    }

    @Deprecated
    public static void incrementOperationCount(int v, int v1) {
        TrafficStats.incrementOperationCount(v, v1);
    }

    @Deprecated
    public static void setThreadStatsTag(int v) {
        TrafficStats.setThreadStatsTag(v);
    }

    public static void tagDatagramSocket(DatagramSocket datagramSocket0) throws SocketException {
        if(Build.VERSION.SDK_INT >= 24) {
            Api24Impl.tagDatagramSocket(datagramSocket0);
            return;
        }
        ParcelFileDescriptor parcelFileDescriptor0 = ParcelFileDescriptor.fromDatagramSocket(datagramSocket0);
        TrafficStats.tagSocket(new DatagramSocketWrapper(datagramSocket0, parcelFileDescriptor0.getFileDescriptor()));
        parcelFileDescriptor0.detachFd();
    }

    @Deprecated
    public static void tagSocket(Socket socket0) throws SocketException {
        TrafficStats.tagSocket(socket0);
    }

    public static void untagDatagramSocket(DatagramSocket datagramSocket0) throws SocketException {
        if(Build.VERSION.SDK_INT >= 24) {
            Api24Impl.untagDatagramSocket(datagramSocket0);
            return;
        }
        ParcelFileDescriptor parcelFileDescriptor0 = ParcelFileDescriptor.fromDatagramSocket(datagramSocket0);
        TrafficStats.untagSocket(new DatagramSocketWrapper(datagramSocket0, parcelFileDescriptor0.getFileDescriptor()));
        parcelFileDescriptor0.detachFd();
    }

    @Deprecated
    public static void untagSocket(Socket socket0) throws SocketException {
        TrafficStats.untagSocket(socket0);
    }
}

