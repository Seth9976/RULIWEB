package androidx.appcompat.app;

class TwilightCalculator {
    private static final float ALTIDUTE_CORRECTION_CIVIL_TWILIGHT = -0.10472f;
    private static final float C1 = 0.03342f;
    private static final float C2 = 0.000349f;
    private static final float C3 = 0.000005f;
    public static final int DAY = 0;
    private static final float DEGREES_TO_RADIANS = 0.017453f;
    private static final float J0 = 0.0009f;
    public static final int NIGHT = 1;
    private static final float OBLIQUITY = 0.40928f;
    private static final long UTC_2000 = 946728000000L;
    private static TwilightCalculator sInstance;
    public int state;
    public long sunrise;
    public long sunset;

    public void calculateTwilight(long v, double f, double f1) {
        float f2 = ((float)(v - 946728000000L)) / 86400000.0f;
        float f3 = 0.017202f * f2 + 6.24006f;
        double f4 = Math.sin(f3) * 0.03342 + ((double)f3) + Math.sin(2.0f * f3) * 0.000349 + Math.sin(f3 * 3.0f) * 0.000005 + 4.938186;
        double f5 = ((double)(((float)Math.round(((double)(f2 - 0.0009f)) - -f1 / 360.0)) + 0.0009f)) + -f1 / 360.0 + Math.sin(f3) * 0.0053 + Math.sin(2.0 * f4) * -0.0069;
        double f6 = Math.asin(Math.sin(f4) * 0.397949);
        double f7 = (-0.104528 - Math.sin(0.017453 * f) * Math.sin(f6)) / (Math.cos(0.017453 * f) * Math.cos(f6));
        if(f7 >= 1.0) {
            this.state = 1;
            this.sunset = -1L;
            this.sunrise = -1L;
            return;
        }
        if(f7 <= -1.0) {
            this.state = 0;
            this.sunset = -1L;
            this.sunrise = -1L;
            return;
        }
        double f8 = (double)(((float)(Math.acos(f7) / 6.283185)));
        this.sunset = Math.round((f5 + f8) * 86400000.0) + 946728000000L;
        long v1 = Math.round((f5 - f8) * 86400000.0);
        this.sunrise = v1 + 946728000000L;
        if(v1 + 946728000000L < v && this.sunset > v) {
            this.state = 0;
            return;
        }
        this.state = 1;
    }

    static TwilightCalculator getInstance() {
        if(TwilightCalculator.sInstance == null) {
            TwilightCalculator.sInstance = new TwilightCalculator();
        }
        return TwilightCalculator.sInstance;
    }
}

