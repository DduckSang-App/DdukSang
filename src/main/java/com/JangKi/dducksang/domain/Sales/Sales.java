package com.JangKi.dducksang.domain.Sales;

import com.JangKi.dducksang.domain.Address.Repository.Address;
import com.JangKi.dducksang.domain.Building.Repository.Building;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.websocket.OnOpen;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sales {

    // 판매정보 데이터
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Temporal(TemporalType.DATE)
    private LocalDate salesDate; // 판매일자

    @Column
    private int amount;// 판매금액

    @Column
    private double dedicatedArea; // 전용면적

    @Column
    private int floor; //판매된 층수

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BuildingID")
    @JsonBackReference(value = "relation-Building-Sales")
    private Building building;

    @Builder
    public Sales(LocalDate salesDate, int amount, double dedicatedArea, int floor, Building building)
    {
        this.salesDate = salesDate;
        this.amount = amount;
        this.dedicatedArea = dedicatedArea;
        this.floor = floor;
        this.building = building;
    }

    // For Test Args
    public void updateID(Long id)
    {
        this.Id = id;
    }

}
