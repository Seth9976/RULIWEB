package retrofit2;

import java.util.Objects;
import javax.annotation.Nullable;

public class HttpException extends RuntimeException {
    private final int code;
    private final String message;
    private final transient Response response;

    public HttpException(Response response0) {
        super(HttpException.getMessage(response0));
        this.code = response0.code();
        this.message = response0.message();
        this.response = response0;
    }

    public int code() {
        return this.code;
    }

    private static String getMessage(Response response0) {
        Objects.requireNonNull(response0, "response == null");
        return "HTTP " + response0.code() + " " + response0.message();
    }

    public String message() {
        return this.message;
    }

    @Nullable
    public Response response() {
        return this.response;
    }
}

