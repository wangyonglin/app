package com.kernel;

public class BooleanHelper {
    private Boolean that = false;
    public static BooleanHelper retFalse() {
        return new BooleanHelper(false);
    }
    public static BooleanHelper retTrue() {
        return new BooleanHelper(true);
    }
    public BooleanHelper(Boolean that) {
        this.that = that;
    }

    public Boolean getThat() {
        return that;
    }

    public void setThat(Boolean that) {
        this.that = that;
    }
}
