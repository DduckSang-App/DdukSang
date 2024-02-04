package com.JangKi.dducksang.domain.Building.Repository;

import com.JangKi.dducksang.Web.Dto.AddressDto.AddressDto;
import com.JangKi.dducksang.Web.Dto.BuildingDto.BuildingResponseDto;
import com.JangKi.dducksang.domain.Address.Repository.Address;
import com.JangKi.dducksang.domain.Sales.Sales;

import java.util.List;

public interface BuildingRepoCustom {
    Boolean exist(int sigunguCode, int eupmyundong, String aptName);

    List<AddressDto.searchAddressDto> searchBuilding(String name);

    List<Sales> salesTransactionList(Long cityID, String dateStr);

    Boolean existBuildingInCode(int sigunguCode, int eupmyundongCode);

    List<Building> searchBuildingOutOfRange(String code, int range);
}
