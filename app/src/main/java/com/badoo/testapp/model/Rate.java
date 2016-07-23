package com.badoo.testapp.model;

/**
 * A rate conversion model
 */
public class Rate {
    private String from;
    private String to;
    private float rate;

    public Rate(String from, String to, float rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public float getRate() {
        return rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rate)) return false;

        Rate that = (Rate) o;

        if (Float.compare(that.rate, rate) != 0) return false;
        if (!from.equals(that.from)) return false;
        return to.equals(that.to);

    }

    @Override
    public int hashCode() {
        int result = from.hashCode();
        result = 31 * result + to.hashCode();
        result = 31 * result + (rate != +0.0f ? Float.floatToIntBits(rate) : 0);
        return result;
    }
}
