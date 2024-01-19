package com.JangKi.dducksang.domain.Building.Repository;

import com.JangKi.dducksang.Web.Dto.AddressDto.AddressDto;
import com.JangKi.dducksang.Web.Dto.BuildingDto.BuildingResponseDto;
import com.JangKi.dducksang.domain.Address.Repository.Address;

import java.util.List;

public interface BuildingRepoCustom {
    Boolean exist(int sigunguCode, int eupmyundong, String aptName);

    List<AddressDto.searchAddressDto> searchBuilding(String name);
}
