package com.JangKi.dducksang.Web.Dto.AddressDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class AddressRequestDto {

    @Getter
    @NoArgsConstructor
    public static class SigunguRequestDto{
        public String SiGunGu;

        public String startDate;

        public String endDate;
    }
}
