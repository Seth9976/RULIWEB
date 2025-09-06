package okhttp3;

import java.io.IOException;
import kotlin.Metadata;
import okio.Timeout;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u001A\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0011J\b\u0010\u0002\u001A\u00020\u0003H&J\b\u0010\u0004\u001A\u00020\u0000H&J\u0010\u0010\u0005\u001A\u00020\u00032\u0006\u0010\u0006\u001A\u00020\u0007H&J\b\u0010\b\u001A\u00020\tH&J\b\u0010\n\u001A\u00020\u000BH&J\b\u0010\f\u001A\u00020\u000BH&J\b\u0010\r\u001A\u00020\u000EH&J\b\u0010\u000F\u001A\u00020\u0010H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0012À\u0006\u0001"}, d2 = {"Lokhttp3/Call;", "", "cancel", "", "clone", "enqueue", "responseCallback", "Lokhttp3/Callback;", "execute", "Lokhttp3/Response;", "isCanceled", "", "isExecuted", "request", "Lokhttp3/Request;", "timeout", "Lokio/Timeout;", "Factory", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
public interface Call extends Cloneable {
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lokhttp3/Call$Factory;", "", "newCall", "Lokhttp3/Call;", "request", "Lokhttp3/Request;", "okhttp"}, k = 1, mv = {1, 7, 1}, xi = 0x30)
    public interface Factory {
        Call newCall(Request arg1);
    }

    void cancel();

    Call clone();

    void enqueue(Callback arg1);

    Response execute() throws IOException;

    boolean isCanceled();

    boolean isExecuted();

    Request request();

    Timeout timeout();
}

