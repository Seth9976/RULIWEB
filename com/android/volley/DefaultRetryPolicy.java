package com.android.volley;

public class DefaultRetryPolicy implements RetryPolicy {
    public static final float DEFAULT_BACKOFF_MULT = 1.0f;
    public static final int DEFAULT_MAX_RETRIES = 1;
    public static final int DEFAULT_TIMEOUT_MS = 2500;
    private final float mBackoffMultiplier;
    private int mCurrentRetryCount;
    private int mCurrentTimeoutMs;
    private final int mMaxNumRetries;

    public DefaultRetryPolicy() {
        this(2500, 1, 1.0f);
    }

    public DefaultRetryPolicy(int v, int v1, float f) {
        this.mCurrentTimeoutMs = v;
        this.mMaxNumRetries = v1;
        this.mBackoffMultiplier = f;
    }

    public float getBackoffMultiplier() {
        return this.mBackoffMultiplier;
    }

    @Override  // com.android.volley.RetryPolicy
    public int getCurrentRetryCount() {
        return this.mCurrentRetryCount;
    }

    @Override  // com.android.volley.RetryPolicy
    public int getCurrentTimeout() {
        return this.mCurrentTimeoutMs;
    }

    protected boolean hasAttemptRemaining() {
        return this.mCurrentRetryCount <= this.mMaxNumRetries;
    }

    @Override  // com.android.volley.RetryPolicy
    public void retry(VolleyError volleyError0) throws VolleyError {
        ++this.mCurrentRetryCount;
        this.mCurrentTimeoutMs += (int)(((float)this.mCurrentTimeoutMs) * this.mBackoffMultiplier);
        if(!this.hasAttemptRemaining()) {
            throw volleyError0;
        }
    }
}

