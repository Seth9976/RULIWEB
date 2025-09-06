package okio;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001A\u00020\u00062\b\u0010\u0007\u001A\u0004\u0018\u00010\u0006H\u0014J\b\u0010\b\u001A\u00020\tH\u0014R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lokio/SocketAsyncTimeout;", "Lokio/AsyncTimeout;", "socket", "Ljava/net/Socket;", "(Ljava/net/Socket;)V", "newTimeoutException", "Ljava/io/IOException;", "cause", "timedOut", "", "okio"}, k = 1, mv = {1, 6, 0}, xi = 0x30)
final class SocketAsyncTimeout extends AsyncTimeout {
    private final Socket socket;

    public SocketAsyncTimeout(Socket socket0) {
        Intrinsics.checkNotNullParameter(socket0, "socket");
        super();
        this.socket = socket0;
    }

    @Override  // okio.AsyncTimeout
    protected IOException newTimeoutException(IOException iOException0) {
        SocketTimeoutException socketTimeoutException0 = new SocketTimeoutException("timeout");
        if(iOException0 != null) {
            socketTimeoutException0.initCause(iOException0);
        }
        return socketTimeoutException0;
    }

    @Override  // okio.AsyncTimeout
    protected void timedOut() {
        try {
            this.socket.close();
        }
        catch(Exception exception0) {
            Okio__JvmOkioKt.logger.log(Level.WARNING, "Failed to close timed out socket " + this.socket, exception0);
        }
        catch(AssertionError assertionError0) {
            if(!Okio.isAndroidGetsocknameError(assertionError0)) {
                throw assertionError0;
            }
            Okio__JvmOkioKt.logger.log(Level.WARNING, "Failed to close timed out socket " + this.socket, assertionError0);
        }
    }
}

