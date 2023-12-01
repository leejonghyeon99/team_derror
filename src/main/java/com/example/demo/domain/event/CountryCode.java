package com.example.demo.domain.event;


public enum CountryCode {
    US("US", "미국"),
    AD("AD", "안도라"),
    AI("AI", "앵귈라"),
    AR("AR", "아르헨티나"),
    AU("AU", "호주"),
    AT("AT", "오스트리아"),
    AZ("AZ", "아제르바이잔"),
    BS("BS", "바하마"),
    BH("BH", "바레인"),
    BB("BB", "바베이도스"),
    BE("BE", "벨기에"),
    BM("BM", "버뮤다"),
    BR("BR", "브라질"),
    BG("BG", "불가리아"),
    CA("CA", "캐나다"),
    CL("CL", "칠레"),
    CN("CN", "중국"),
    CO("CO", "콜롬비아"),
    CR("CR", "코스타리카"),
    HR("HR", "크로아티아"),
    CY("CY", "키프로스"),
    CZ("CZ", "체코"),
    DK("DK", "덴마크"),
    DO("DO", "도미니카 공화국"),
    EC("EC", "에콰도르"),
    EE("EE", "에스토니아"),
    FO("FO", "페로 제도"),
    FI("FI", "핀란드"),
    FR("FR", "프랑스"),
    GE("GE", "조지아"),
    DE("DE", "독일"),
    GH("GH", "가나"),
    GI("GI", "지브롤터"),
    GB("GB", "영국"),
    GR("GR", "그리스"),
    HK("HK", "홍콩"),
    HU("HU", "헝가리"),
    IS("IS", "아이슬란드"),
    IN("IN", "인도"),
    IE("IE", "아일랜드"),
    IL("IL", "이스라엘"),
    IT("IT", "이탈리아"),
    JM("JM", "자메이카"),
    JP("JP", "일본"),
    KR("KR", "대한민국"),
    LV("LV", "라트비아"),
    LB("LB", "레바논"),
    LT("LT", "리투아니아"),
    LU("LU", "룩셈부르크"),
    MY("MY", "말레이시아"),
    MT("MT", "몰타"),
    MX("MX", "멕시코"),
    MC("MC", "모나코"),
    ME("ME", "몬테네그로"),
    MA("MA", "모로코"),
    NL("NL", "네덜란드"),
    AN("AN", "네덜란드령 앤틸리스"),
    NZ("NZ", "뉴질랜드"),
    ND("ND", "북아일랜드"),
    NO("NO", "노르웨이"),
    PE("PE", "페루"),
    PL("PL", "폴란드"),
    PT("PT", "포르투갈"),
    RO("RO", "루마니아"),
    RU("RU", "러시아 연방"),
    LC("LC", "세인트루시아"),
    SA("SA", "사우디아라비아"),
    RS("RS", "세르비아"),
    SG("SG", "싱가포르"),
    SK("SK", "슬로바키아"),
    SI("SI", "슬로베니아"),
    ZA("ZA", "남아프리카 공화국"),
    ES("ES", "스페인"),
    SE("SE", "스웨덴"),
    CH("CH", "스위스"),
    TW("TW", "대만"),
    TH("TH", "태국"),
    TT("TT", "트리니다드 토바고"),
    TR("TR", "터키"),
    UA("UA", "우크라이나"),
    AE("AE", "아랍에미리트"),
    UY("UY", "우루과이"),
    VE("VE", "베네수엘라");

    private final String code; // 국가 코드를 저장할 변수
    private final String value; // 국가이름 저장할 변수

    CountryCode(String code, String value) {
        this.code = code;
        this.value = value;
    }
    public String getCode(){
        return code;
    }
    public String getValue(){
        return value;
    }
}
