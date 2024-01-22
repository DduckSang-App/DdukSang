package com.JangKi.dducksang.Web.Dto.BuildingDto;

import com.JangKi.dducksang.domain.Address.Repository.Address;
import com.JangKi.dducksang.domain.Building.Repository.Building;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;

public class BuildingRequestDto {

    @Getter
    @NoArgsConstructor
    public static class BuildingSaveDto{

        private Year buildYear;

        private int sigunguCode;

        private int eupmyundongCode;

        private String sigungu;

        private String dong;

        private String aptName;

        private Address addressID;

        private String roadBuildingCode;

        private String roadSubBuildingCode;

        public BuildingSaveDto(Year buildYear, int sigunguCode, int eupmyundongCode, String sigungu, String dong, String aptName, String roadBuildingCode, String roadSubBuildingCode)
        {
            this.buildYear = buildYear;
            this.sigunguCode = sigunguCode;
            this.eupmyundongCode = eupmyundongCode;
            this.sigungu = sigungu;
            this.dong = dong;
            this.aptName = aptName;
            this.roadBuildingCode = roadBuildingCode;
            this.roadSubBuildingCode = roadSubBuildingCode;
        }

        public void setAddressID(Address addressID)
        {
            this.addressID = addressID;
        }

        @NotNull
        public Building toEntity()
        {
            return  Building.builder()
                    .buildYear(buildYear)
                    .sigunguCode(sigunguCode)
                    .eupmyundongCode(eupmyundongCode)
                    .sigungu(sigungu)
                    .dong(dong)
                    .aptName(aptName)
                    .address(addressID)
                    .roadBuildingCode(roadBuildingCode)
                    .roadSubBuildingCode(roadSubBuildingCode)
                    .build();
        }
    }
}
