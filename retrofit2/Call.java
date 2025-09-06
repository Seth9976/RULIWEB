package retrofit2;

import java.io.IOException;
import okhttp3.Request;
import okio.Timeout;

public interface Call extends Cloneable {
    void cancel();

    Call clone();

    void enqueue(Callback arg1);

    Response execute() throws IOException;

    boolean isCanceled();

    boolean isExecuted();

    Request request();

    Timeout timeout();
}

