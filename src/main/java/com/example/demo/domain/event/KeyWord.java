package com.example.demo.domain.event;

/**
 * @author 종선
 */

public enum KeyWord {

    Music("Music", "노래"),
    Arts("Arts", "예술"),
    Theatre("Theatre", "연극"),
    Sports("Sports", "스포츠"),
    Film("Film", "영화");

    private final String keyword;
    private final String value;

    KeyWord(String keyword, String value) {
        this.keyword = keyword;
        this.value = value;
    }
    public String getKeyword() {
        return keyword;
    }
    public String getValue() {
        return value;
    }
}
