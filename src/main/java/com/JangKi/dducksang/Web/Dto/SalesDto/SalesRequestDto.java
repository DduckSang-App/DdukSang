package com.JangKi.dducksang.Web.Dto.SalesDto;

import com.JangKi.dducksang.domain.Building.Repository.Building;
import com.JangKi.dducksang.domain.Sales.Sales;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;


public class SalesRequestDto {

    @Getter
    @NoArgsConstructor
    public static class SalesSaveDto{
        private LocalDate DealYear;

        private int amount;// 판매금액

        private double dedicatedArea; // 전용면적

        private int floor; //판매된 층수

        private Building building;

        public SalesSaveDto(LocalDate DealYear, int amount, double dedicatedArea, int floor)
        {
            this.DealYear = DealYear;
            this.amount = amount;
            this.dedicatedArea =dedicatedArea;
            this.floor = floor;
        }

        public void updateBuilding(Building building)
        {
            this.building = building;
        }

        @NonNull
        @Builder
        public Sales toEntity()
        {
            return  Sales.builder()
                    .salesDate(DealYear)
                    .amount(amount)
                    .dedicatedArea(dedicatedArea)
                    .floor(floor)
                    .building(building)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class RequestAPI
    {
        private Long sigunguCode; // 시군구 코드

        private int dealMonth; // 계약월
    }

}
