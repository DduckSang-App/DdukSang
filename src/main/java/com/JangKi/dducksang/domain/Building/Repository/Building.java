package com.JangKi.dducksang.domain.Building.Repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column
    private int amount;//거래금액

    @Column
    private int buildYear;//건축년도

    @Column
    private int dealYear; //년

    @Column
    private int dealMonth; //월

    @Column
    private int dealDay; //일

    @Column
    private int sigunguCode; //법정동시군구코드

    @Column
    private int eupmyundongCode; //법정동읍면동코드

    @Column
    private String sigungu; //중개사소재지

    @Column
    private String dong; //법정동

    @Column
    private String aptName; //아파트

    @Column
    private double dedicatedArea; //전용면적

    @Column
    private int floor; //층

    @Builder
    public Building(int amount, int buildYear, int dealYear, int dealMonth, int dealDay, int sigunguCode, int eupmyundongCode, String sigungu, String dong, String aptName, double dedicatedArea, int floor)
    {
        this.amount = amount;
        this.buildYear = buildYear;
        this.dealYear = dealYear;
        this.dealMonth = dealMonth;
        this.dealDay = dealDay;
        this.sigunguCode = sigunguCode;
        this.eupmyundongCode = eupmyundongCode;
        this.sigungu = sigungu;
        this.dong = dong;
        this.aptName = aptName;
        this.dedicatedArea = dedicatedArea;
        this.floor = floor;
    }
}
