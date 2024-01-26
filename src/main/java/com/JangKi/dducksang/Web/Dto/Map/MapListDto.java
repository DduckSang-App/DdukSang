package com.JangKi.dducksang.Web.Dto.Map;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MapListDto {

    private int amount;//거래금액

    private int buildYear;//건축년도

    private int dealYear; //년

    private int dealMonth; //월

    private int dealDay; //일

    private int sigunguCode; //법정동시군구코드

    private int eupmyundongCode; //법정동읍면동코드

    private String sigungu; //중개사소재지

    private String dong; //법정동

    private String aptName; //아파트

    private double dedicatedArea; //전용면적

    private int floor; //층

    private String roadBuildingCode;

    private String roadSubBuildingCode;

    private String roadName;

    private String Bonbun;

    private String Bubun;

    public MapListDto(int amount, int buildYear, int dealYear, int dealMonth, int dealDay, int sigunguCode, int eupmyundongCode, String dong, String aptName, double dedicatedArea, int floor, String roadBuildingCode, String roadSubBuildingCode, String roadName
    ,String Bonbun, String Bubun)
    {
        this.amount = amount;
        this.buildYear =buildYear;
        this.dealYear = dealYear;
        this.dealMonth = dealMonth;
        this.dealDay = dealDay;
        this.sigunguCode = sigunguCode;
        this.eupmyundongCode = eupmyundongCode;
        this.dong = dong;
        this.aptName = aptName;
        this.dedicatedArea = dedicatedArea;
        this.floor = floor;
        this.roadBuildingCode = roadBuildingCode;
        this.roadSubBuildingCode = roadSubBuildingCode;
        this.roadName = roadName;
        this.Bonbun = Bonbun;
        this.Bubun = Bubun;
    }
}
