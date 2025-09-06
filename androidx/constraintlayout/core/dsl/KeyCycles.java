package androidx.constraintlayout.core.dsl;

public class KeyCycles extends KeyAttributes {
    public static enum Wave {
        SIN,
        SQUARE,
        TRIANGLE,
        SAW,
        REVERSE_SAW,
        COS;

        private static Wave[] $values() [...] // Inlined contents
    }

    private float[] mWaveOffset;
    private float[] mWavePeriod;
    private float[] mWavePhase;
    private Wave mWaveShape;

    KeyCycles(int v, String[] arr_s) {
        super(v, arr_s);
        this.mWaveShape = null;
        this.mWavePeriod = null;
        this.mWaveOffset = null;
        this.mWavePhase = null;
        this.TYPE = "KeyCycle";
    }

    @Override  // androidx.constraintlayout.core.dsl.KeyAttributes
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

    public float[] getWaveOffset() {
        return this.mWaveOffset;
    }

    public float[] getWavePeriod() {
        return this.mWavePeriod;
    }

    public float[] getWavePhase() {
        return this.mWavePhase;
    }

    public Wave getWaveShape() {
        return this.mWaveShape;
    }

    public void setWaveOffset(float[] arr_f) {
        this.mWaveOffset = arr_f;
    }

    public void setWavePeriod(float[] arr_f) {
        this.mWavePeriod = arr_f;
    }

    public void setWavePhase(float[] arr_f) {
        this.mWavePhase = arr_f;
    }

    public void setWaveShape(Wave keyCycles$Wave0) {
        this.mWaveShape = keyCycles$Wave0;
    }
}

