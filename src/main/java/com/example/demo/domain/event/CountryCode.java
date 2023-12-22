package com.example.demo.domain.event;

/**
 * @author 종선
 */


public enum CountryCode {
    US("US", "미국"),
    AU("AU", "호주"),
    AT("AT", "오스트리아"),
    BE("BE", "벨기에"),
    CA("CA", "캐나다"),
    CZ("CZ", "체코"),
    DK("DK", "덴마크"),
    EE("EE", "에스토니아"),
    FI("FI", "핀란드"),
    FR("FR", "프랑스"),
    DE("DE", "독일"),
    GB("GB", "영국"),
    IE("IE", "아일랜드"),
    LU("LU", "룩셈부르크"),
    MX("MX", "멕시코"),
    NL("NL", "네덜란드"),
    NZ("NZ", "뉴질랜드"),
    NO("NO", "노르웨이"),
    PL("PL", "폴란드"),
    ZA("ZA", "남아프리카 공화국"),
    ES("ES", "스페인"),
    SE("SE", "스웨덴"),
    CH("CH", "스위스"),
    TR("TR", "터키");




    private final String code; // 국가 코드를 저장할 변수
    private final String value;


    CountryCode(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
