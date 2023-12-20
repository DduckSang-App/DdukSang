package com.JangKi.dducksang.Web.Dto.AddressDto;

import com.JangKi.dducksang.domain.Address.Repository.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AddressDto {

    // Address 정보 출력
    @Getter
    @NoArgsConstructor
    public static class AddressInfoDto{
        private Long id;

        private Long code;

        private String name;

        public AddressInfoDto(Address entity)
        {
            this.id = entity.getAdd_ID();
            this.code = entity.getCode();
            this.name = entity.getName();
        }
    }
}
