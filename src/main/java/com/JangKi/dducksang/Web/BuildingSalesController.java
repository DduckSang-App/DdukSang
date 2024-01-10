package com.JangKi.dducksang.Web;

import com.JangKi.dducksang.APImodel.OpenAPI;
import com.JangKi.dducksang.Service.Building.BuildingService;
import com.JangKi.dducksang.Service.Sales.SalesService;
import com.JangKi.dducksang.Web.Dto.Map.MapListDto;
import com.JangKi.dducksang.Web.Dto.SalesDto.SalesRequestDto;
import com.JangKi.dducksang.domain.Building.Repository.Building;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BuildingSalesController {

    private final OpenAPI openAPI;

    private final SalesService salesService;

    private final BuildingService buildingService;

    @PostMapping("/saveSalesList")
    public void saveSalesList(@RequestBody SalesRequestDto.RequestAPI requestDto)
    {
        String sigunguCode = String.valueOf(requestDto.getSigunguCode());
        String dealMonth = String.valueOf(requestDto.getDealMonth());

        int page = 1;

        while(true)
        {
            String pageNo = String.valueOf(page);

            List<MapListDto> list = openAPI.getApi(sigunguCode, dealMonth, pageNo);

            if(list.size() == 0)
                break;

            for(MapListDto mapList : list)
            {
//                 Address -> Building 1 : N //
                Long buildingID = buildingService.saveBuilding(mapList);

//                 Building -> Sales 1 : N //
                Building building = buildingService.returnBuilding(buildingID);

                salesService.SalesSave(building, mapList);
            }

            page++;
        }
    }
}
