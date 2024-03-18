package com.istato.admin.utils;

public enum FieldSeprators {

    BLANK(" "), HYPEN("-");
    private final String stringValue;

    FieldSeprators(final String s) {
        stringValue = s;
    }

    public String toFaceValue() {
        return stringValue;
    }

}
