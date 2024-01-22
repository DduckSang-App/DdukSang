package com.JangKi.dducksang.Web;

import com.JangKi.dducksang.APImodel.OpenAPI;
import com.JangKi.dducksang.Service.Address.AddressService;
import com.JangKi.dducksang.Service.Building.BuildingService;
import com.JangKi.dducksang.Service.Sales.SalesService;
import com.JangKi.dducksang.Web.Dto.AddressDto.AddressRequestDto;
import com.JangKi.dducksang.Web.Dto.Map.MapListDto;
import com.JangKi.dducksang.Web.Dto.SalesDto.SalesRequestDto;
import com.JangKi.dducksang.Web.Dto.SalesDto.SalesResponseDto;
import com.JangKi.dducksang.domain.Building.Repository.Building;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BuildingSalesController {

    private final OpenAPI openAPI;

    private final SalesService salesService;

    private final BuildingService buildingService;

    private final AddressService addressService;

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

            log.info("list Size - " + list.size());

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
//    List<SalesResponseDto.SalesInfoResponseDto>
    @PostMapping("/InfoBuilding")
    public List<SalesResponseDto.SalesInfoResponseDto> InfoBuilding(@RequestBody AddressRequestDto.SigunguRequestDto requestDto)
    {
        String locateStr = requestDto.getSiGunGu();

        // locateNM -> code convert
        String code = String.valueOf(addressService.searchCityCode(locateStr));

        int sigunguCode = Integer.valueOf(code.substring(0, 5));
        int epmundongCodee = Integer.valueOf(code.substring(5));

//        log.info(sigunguCode + " " + epmundongCodee);
        // 해당하는 Building 찾아주기
        List<Building> buildingList = buildingService.searchBuilding(sigunguCode, epmundongCodee);

        List<SalesResponseDto.SalesInfoResponseDto> searchSalesList = new ArrayList<>();

        for(Building building : buildingList)
        {
            SalesResponseDto.SalesInfoResponseDto responseDto = new SalesResponseDto.SalesInfoResponseDto(building);

            responseDto.setLocatedNM(locateStr);

//            log.info(building.getId() + " ");

            // 해당 매물 조회
            List<Object> salesList = buildingService.InfoBuildingService(building.getId());
//            List<Map<String, Object>> salesList = salesService.InfoSalesList(building.getId());

            responseDto.setSalesList(salesList);
//            for(Object o : salesList)
//            {
//                log.info(o.getClass().getName() + " " + o.getClass().getSimpleName());
//            }

            searchSalesList.add(responseDto);
        }

        return searchSalesList;
    }

}
