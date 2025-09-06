package retrofit2;

public interface Callback {
    void onFailure(Call arg1, Throwable arg2);

    void onResponse(Call arg1, Response arg2);
}

