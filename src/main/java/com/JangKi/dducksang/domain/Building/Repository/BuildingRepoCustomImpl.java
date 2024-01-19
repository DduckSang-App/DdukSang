package com.JangKi.dducksang.domain.Building.Repository;

import com.JangKi.dducksang.Web.Dto.AddressDto.AddressDto;
import com.JangKi.dducksang.Web.Dto.BuildingDto.BuildingResponseDto;
import com.JangKi.dducksang.domain.Address.Repository.Address;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static com.JangKi.dducksang.domain.Address.Repository.QAddress.address;
import static com.JangKi.dducksang.domain.Building.Repository.QBuilding.building;

@RequiredArgsConstructor
public class BuildingRepoCustomImpl implements BuildingRepoCustom{

    private final JPAQueryFactory queryFactory;

    public Boolean exist(int sigunguCode, int eupmyundong, String aptName) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(building)
                .where(building.sigunguCode.eq(sigunguCode)
                        .and(building.eupmyundongCode.eq(eupmyundong)
                                .and(building.aptName.eq(aptName)))
                )
                .fetchFirst(); // limit 1

        return fetchOne != null; // 1개가 있는지 없는지 판단 (없으면 null이라 null체크)
    }

    public List<AddressDto.searchAddressDto> searchBuilding(String name)
    {
        List<Tuple> fetchBuilding = queryFactory
                .select(building.address.located_nm, building.aptName)
                .from(building)
                .join(building.address, address)
                .where(building.aptName.contains(name))
                .fetch();

        List<AddressDto.searchAddressDto> searchBuildingList = fetchBuilding.stream()
                .map(tuple -> {
                    String locateNM = tuple.get(building.address.located_nm);
                    String aptName = tuple.get(building.aptName);

                    return new AddressDto.searchAddressDto(aptName, locateNM);
                })
                .collect(Collectors.toList());

        return searchBuildingList;
    }
}
