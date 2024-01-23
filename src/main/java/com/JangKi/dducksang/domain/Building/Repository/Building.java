package com.JangKi.dducksang.domain.Building.Repository;

import com.JangKi.dducksang.domain.Address.Repository.Address;
import com.JangKi.dducksang.domain.Sales.Sales;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private Year buildYear;//건축년도

    @Column
    private Long code; // 전체 코드

    @Column
    private int sigunguCode; //법정동시군구코드

    @Column
    private int eupmyundongCode; //법정동읍면동코드

    @Column
    private String sigungu; //중개사소재지

    @Column
    private String dong; //법정동

    @Column
    private String aptName; //아파트 이름

    @Column
    private String roadBuildingCode; // 건물명본번호코드

    @Column
    private String roadSubBuildingCode; // 건물명부번호코드

    @Column
    private String roadName; // 도로명

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AddressID")
    @JsonBackReference(value = "relation-Address-Building")
    private Address address;

    @JsonManagedReference(value = "relation-Building-Sales")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "building", orphanRemoval = true)
    private List<Sales> salesList = new ArrayList<>();

    @Builder
    public Building(Year buildYear, Long code, int sigunguCode, int eupmyundongCode, String sigungu, String dong, String aptName, Address address, List<Sales> salesList, String roadBuildingCode, String roadSubBuildingCode, String roadName)
    {
        this.buildYear = buildYear;
        this.code = code;
        this.sigunguCode = sigunguCode;
        this.eupmyundongCode = eupmyundongCode;
        this.sigungu = sigungu;
        this.dong = dong;
        this.aptName = aptName;
        this.address = address;
        this.salesList = salesList;
        this.roadBuildingCode = roadBuildingCode;
        this.roadSubBuildingCode = roadSubBuildingCode;
        this.roadName = roadName;
    }


    // For test Args
    public void updateID(Long id)
    {
        this.Id = id;
    }
}
