package androidx.constraintlayout.core.dsl;

public class KeyCycle extends KeyAttribute {
    public static enum Wave {
        SIN,
        SQUARE,
        TRIANGLE,
        SAW,
        REVERSE_SAW,
        COS;

        private static Wave[] $values() [...] // Inlined contents
    }

    private static final String TAG = "KeyCycle";
    private float mWaveOffset;
    private float mWavePeriod;
    private float mWavePhase;
    private Wave mWaveShape;

    KeyCycle(int v, String s) {
        super(v, s);
        this.mWaveShape = null;
        this.mWavePeriod = NaNf;
        this.mWaveOffset = NaNf;
        this.mWavePhase = NaNf;
        this.TYPE = "KeyCycle";
    }

    @Override  // androidx.constraintlayout.core.dsl.KeyAttribute
    protected void attributesToString(StringBuilder stringBuilder0) {
        super.attributesToString(stringBuilder0);
        if(this.mWaveShape != null) {
            stringBuilder0.append("shape:\'");
            stringBuilder0.append(this.mWaveShape);
            stringBuilder0.append("\',\n");
        }
        this.append(stringBuilder0, "period", this.mWavePeriod);
        this.append(stringBuilder0, "offset", this.mWaveOffset);
        this.append(stringBuilder0, "phase", this.mWavePhase);
    }

    public float getOffset() {
        return this.mWaveOffset;
    }

    public float getPeriod() {
        return this.mWavePeriod;
    }

    public float getPhase() {
        return this.mWavePhase;
    }

    public Wave getShape() {
        return this.mWaveShape;
    }

    public void setOffset(float f) {
        this.mWaveOffset = f;
    }

    public void setPeriod(float f) {
        this.mWavePeriod = f;
    }

    public void setPhase(float f) {
        this.mWavePhase = f;
    }

    public void setShape(Wave keyCycle$Wave0) {
        this.mWaveShape = keyCycle$Wave0;
    }
}

