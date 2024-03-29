package com.JangKi.dducksang.Service.Sales;

import com.JangKi.dducksang.Web.Dto.Map.MapListDto;
import com.JangKi.dducksang.Web.Dto.SalesDto.SalesRequestDto;
import com.JangKi.dducksang.domain.Building.Repository.Building;
import com.JangKi.dducksang.domain.Sales.Sales;
import com.JangKi.dducksang.domain.Sales.SalesRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class SalesService {

    private final SalesRepo salesRepo;

    @Transactional
    public Long save(Sales Entity)
    {
        Sales saveSales = salesRepo.save(Entity);

        return saveSales.getId();
    }

    @Transactional
    public void SalesSave(Building building, MapListDto mapListDto)
    {
        LocalDate localDate = LocalDate.of(mapListDto.getDealYear(), mapListDto.getDealMonth() , mapListDto.getDealDay());

        SalesRequestDto.SalesSaveDto salesSaveDto = new SalesRequestDto.SalesSaveDto(localDate, mapListDto.getAmount(), mapListDto.getDedicatedArea(),
                mapListDto.getFloor());

        salesSaveDto.updateBuilding(building);

        salesRepo.save(salesSaveDto.toEntity());
    }

    @Transactional(readOnly = true)
    public List<Map<String, Object>> InfoSalesList(Long Id)
    {
        return salesRepo.SalesInfo(Id);
    }


    @Transactional(readOnly = true)
    public Double CalculateSalesAmount(String dateStr, Long BuildingID)
    {
        return salesRepo.CalculateAverage(dateStr,BuildingID);
    }
}
