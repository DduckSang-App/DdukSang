package com.JangKi.dducksang.Web.Dto.SalesDto;

import com.JangKi.dducksang.domain.Building.Repository.Building;
import com.JangKi.dducksang.domain.Sales.Sales;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SalesResponseDto {

    @Getter
    @NoArgsConstructor
    public static class SalesInfoResponseDto{
        String locatedNM; // 지역 전체 출력

        Year buildYear; // 건물 생성 일자

        String aptName; // 아파트 이름

        String dong; // 동 이름

        String roadName; // 도로명

        String BonBun_BuBun; // 본번 부번코드
        
        double PrevAverage; // 전월 매매 평균
        double NowAverage; // 이번달 매매 평균
        
        double UpDownPercent; // 전월대비 상승률
        
        List<Sales> salesList = new ArrayList<>();

        public void setLocatedNM(String locatedNM)
        {
            this.locatedNM = locatedNM;
        }

        public void setSalesList(List<Sales> list)
        {
            this.salesList = list;
        }
        public SalesInfoResponseDto(Building building)
        {
            this.buildYear = building.getBuildYear();
            this.aptName = building.getAptName();
            this.dong = building.getDong();
            this.roadName = building.getRoadName().concat(" ").concat(building.getRoadBuildingCode()).concat("-").concat(building.getRoadSubBuildingCode());
            this.BonBun_BuBun = String.valueOf(Integer.parseInt(building.getBonbun())).concat("-").concat(String.valueOf(Integer.parseInt(building.getBubun())));
        }

        public void updateVal(double prevAverage, double nowAverage, double upDownPercent)
        {
            this.PrevAverage = prevAverage;
            this.NowAverage = nowAverage;
            this.UpDownPercent = upDownPercent;
        }
    }
}
