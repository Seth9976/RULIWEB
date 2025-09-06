package androidx.constraintlayout.core.dsl;

public abstract class Guideline extends Helper {
    private int mEnd;
    private float mPercent;
    private int mStart;

    Guideline(String s) {
        super(s, new HelperType(""));
        this.mStart = 0x80000000;
        this.mEnd = 0x80000000;
        this.mPercent = NaNf;
    }

    public int getEnd() {
        return this.mEnd;
    }

    public float getPercent() {
        return this.mPercent;
    }

    public int getStart() {
        return this.mStart;
    }

    public void setEnd(int v) {
        this.mEnd = v;
        this.configMap.put("end", String.valueOf(this.mEnd));
    }

    public void setPercent(float f) {
        this.mPercent = f;
        this.configMap.put("percent", String.valueOf(this.mPercent));
    }

    public void setStart(int v) {
        this.mStart = v;
        this.configMap.put("start", String.valueOf(this.mStart));
    }
}

