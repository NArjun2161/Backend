package com.istato.admin.utils;

public enum FieldSeprators {

    BLANK(" "), HYPEN("-"), DOT("."), FORWARD_SLASH("/");
    private final String stringValue;

    FieldSeprators(final String s) {
        stringValue = s;
    }

    public String toFaceValue() {
        return stringValue;
    }

}
