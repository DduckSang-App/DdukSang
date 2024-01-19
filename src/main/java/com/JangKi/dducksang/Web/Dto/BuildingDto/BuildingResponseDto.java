package com.JangKi.dducksang.Web.Dto.BuildingDto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class BuildingResponseDto {

    @Getter
    @NoArgsConstructor
    public static class BuildingNameDto{

        private String codeName;

        private String aptName;

        public BuildingNameDto(String codeName, String aptName)
        {
            this.codeName = codeName;
            this.aptName = aptName;
        }
    }
}
