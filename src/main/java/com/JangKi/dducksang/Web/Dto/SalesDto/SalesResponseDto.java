package com.JangKi.dducksang.Web.Dto.SalesDto;

import com.JangKi.dducksang.domain.Building.Repository.Building;
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

        List<Map<String, Object>> salesList = new ArrayList<>();

        public void setLocatedNM(String locatedNM)
        {
            this.locatedNM = locatedNM;
        }

        public void setSalesList(List<Map<String, Object>> list)
        {
            this.salesList = list;
        }
        public SalesInfoResponseDto(Building building)
        {
            this.buildYear = building.getBuildYear();
            this.aptName = building.getAptName();
            this.dong = building.getDong();
        }
    }
}
