package com.motok.motoK.account.domain.enums;

public enum AccountCode {
    PLUS(1, "plus"), MINUS(2, "minus");

    private final int code;
    private final String value;

    public int code() {
        return this.code;
    }

    public String value() {
        return this.value;
    }

    AccountCode(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public boolean isPlus() {
        return this == PLUS;
    }
}
