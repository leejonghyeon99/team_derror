package com.example.demo.domain.event;

public enum ClassificationName {
    Miscellaneous("Miscellaneous", "전체"),
    Music("Music", "노래"),
    Arts("Arts", "예술"),
    Theatre("Theatre", "연극"),
    Sports("Sports", "스포츠"),
    Film("Film", "영화");

    private final String className;
    private final String value;

    ClassificationName(String className, String value) {
        this.className = className;
        this.value = value;
    }
    public String getClassificationName() {
        return className;
    }
    public String getValue() {
        return value;
    }
}

