package com.JangKi.dducksang.Web.Dto.AddressDto;

import com.JangKi.dducksang.domain.Address.Repository.Address;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AddressDto {

    // Address 정보 출력
    @Getter
    @Setter
    @NoArgsConstructor
    public static class AddressInfoDto{
        private Long id;

        private Long code;

        private String name;

        private String name_v1;

        private String name_v2;

        private String name_v3;

        public AddressInfoDto(Long code, String name, String name_v1, String name_v2, String name_v3)
        {
            this.code = code;
            this.name = name;
            this.name_v1 = name_v1;
            this.name_v2 = name_v2;
            this.name_v3 = name_v3;
        }

        public AddressInfoDto(Address entity)
        {
            this.id = entity.getID();
            this.code = entity.getCode();
            this.name = entity.getLocated_nm();
            this.name_v1 = entity.getLocate_v1();
            this.name_v2 = entity.getLocate_v2();
            this.name_v3 = entity.getLocate_v3();
        }

        public Address toEntity()
        {
            return Address.builder()
                    .code(code)
                    .located_nm(name)
                    .locate_v1(name_v1)
                    .locate_v2(name_v2)
                    .locate_v3(name_v3)
                    .build();
        }

    }

    @Getter
    @NoArgsConstructor
    public static class SiGunGuAddress{
        private String sigungu;

        public SiGunGuAddress(Address entity)
        {
            this.sigungu = entity.getLocated_nm();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class searchAddressDto{
        private String aptName;

        private String locatedNM;

        private int type; // type 1 -> 지역 , type 2-> 아파트

        public searchAddressDto(String aptName, String locatedNM)
        {
            this.aptName = aptName;
            this.locatedNM = locatedNM;
            this.type = 2;
        }

        public searchAddressDto(String locatedNM)
        {
            this.locatedNM = locatedNM;
            this.type = 1;
            this.aptName = "";
        }

    }
}
