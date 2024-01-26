package com.JangKi.dducksang.Web;

import com.JangKi.dducksang.APImodel.OpenAPI;
import com.JangKi.dducksang.Service.Address.AddressService;
import com.JangKi.dducksang.Service.Building.BuildingService;
import com.JangKi.dducksang.Service.Sales.SalesService;
import com.JangKi.dducksang.Web.Dto.AddressDto.AddressDto;
import com.JangKi.dducksang.Web.Dto.AddressDto.AddressRequestDto;
import com.JangKi.dducksang.Web.Dto.Map.MapListDto;
import com.JangKi.dducksang.Web.Dto.SalesDto.SalesRequestDto;
import com.JangKi.dducksang.Web.Dto.SalesDto.SalesResponseDto;
import com.JangKi.dducksang.domain.Building.Repository.Building;
import com.JangKi.dducksang.domain.Sales.Sales;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

            log.info(sigunguCode + "'s list Size - " + list.size());

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

        //TODO
        // try catch로 예외상황 처리해줘야함! [ StringIndexOutOfBoundsException -> code를 찾지 못했을 때 ]
        try{
            int sigunguCode = Integer.valueOf(code.substring(0, 5));
            int epmundongCodee = Integer.valueOf(code.substring(5));

            // 해당하는 Building 찾아주기
            List<Building> buildingList = buildingService.searchBuilding(sigunguCode, epmundongCodee);

            List<SalesResponseDto.SalesInfoResponseDto> searchSalesList = new ArrayList<>();

            for(Building building : buildingList)
            {
                SalesResponseDto.SalesInfoResponseDto responseDto = new SalesResponseDto.SalesInfoResponseDto(building);

                responseDto.setLocatedNM(locateStr);

                // 해당 매물 조회
                List<Sales> PrevsalesList = buildingService.salesTransactionService(building.getId(), requestDto.startDate);

                // 알아보고 싶은 최근 데이터 기준
                List<Sales> salesList = buildingService.salesTransactionService(building.getId(), requestDto.endDate);
                // 1번 성능 비교 [ 단순 반복문 ]
                StopWatch stopWatch = new StopWatch();
                stopWatch.start();

                int totalAmount = 0;
                int cnt = 0;

                for(Sales sale : PrevsalesList) {
                    totalAmount += sale.getAmount();
                    cnt++;
                }

                double average = (cnt != 0 ? totalAmount/cnt : 0.0);

                int totalAmount2 = 0;
                cnt = 0;

                for(Sales sale : salesList)
                {
                    totalAmount2 += sale.getAmount();
                    cnt++;
                }

                double average2 = (cnt != 0 ? totalAmount2 / cnt : 0.0);

                double upDownPercent = (average != 0.0 && average2 != 0.0 ? (average2 - average) / average : 0.0);

                responseDto.updateVal(average, average2, upDownPercent);

                // 가격 순 높은 순으로 정렬
                salesList = salesList.stream()
                        .sorted(
                                Comparator.comparingInt(Sales::getAmount).reversed()
                        ).collect(Collectors.toList());

                responseDto.setSalesList(salesList);

                searchSalesList.add(responseDto);
            }

            return searchSalesList;
        }
        catch(StringIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
