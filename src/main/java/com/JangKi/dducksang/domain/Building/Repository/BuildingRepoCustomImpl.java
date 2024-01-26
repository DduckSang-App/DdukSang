package com.JangKi.dducksang.domain.Building.Repository;

import com.JangKi.dducksang.Web.Dto.AddressDto.AddressDto;
import com.JangKi.dducksang.Web.Dto.BuildingDto.BuildingResponseDto;
import com.JangKi.dducksang.domain.Address.Repository.Address;
import com.JangKi.dducksang.domain.Sales.Sales;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.stream.Collectors;

import static com.JangKi.dducksang.domain.Address.Repository.QAddress.address;
import static com.JangKi.dducksang.domain.Building.Repository.QBuilding.building;
import static com.JangKi.dducksang.domain.Sales.QSales.sales;

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
//    @Query("SELECT s FROM Building b inner join Sales s on b.Id = s.building.Id where b.Id = :cityID")
    public List<Sales> salesTransactionList(Long cityID, String dateStr)
    {
        DateTemplate formattedDate = Expressions.dateTemplate(
                String.class
                , "DATE_FORMAT({0}, {1})"
                , sales.salesDate
                , ConstantImpl.create("%Y%m"));

        List<Sales> fetchList = queryFactory
                .select(sales)
                .from(sales)
                .join(sales.building, building)
                .where(building.Id.eq(cityID))
                .where(formattedDate.eq(dateStr))
                .fetch();

        return fetchList;
    }
}
