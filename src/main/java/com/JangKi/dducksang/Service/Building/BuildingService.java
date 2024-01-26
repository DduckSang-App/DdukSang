package com.JangKi.dducksang.Service.Building;

import com.JangKi.dducksang.APImodel.OpenAPI;
import com.JangKi.dducksang.Service.Address.AddressService;
import com.JangKi.dducksang.Web.Dto.AddressDto.AddressDto;
import com.JangKi.dducksang.Web.Dto.BuildingDto.BuildingRequestDto;
import com.JangKi.dducksang.Web.Dto.BuildingDto.BuildingResponseDto;
import com.JangKi.dducksang.Web.Dto.Map.MapListDto;
import com.JangKi.dducksang.domain.Address.Repository.Address;
import com.JangKi.dducksang.domain.Building.Repository.Building;
import com.JangKi.dducksang.domain.Building.Repository.BuildingRepo;
import com.JangKi.dducksang.domain.Sales.Sales;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuildingService {

    private final BuildingRepo buildingRepo;

    private final AddressService addressService;

    private final OpenAPI openAPI;

    @Transactional
    public List<MapListDto> search(String code, String dealDay, String pageNo)
    {
        List<MapListDto> list = openAPI.getApi(code, dealDay, pageNo);

        log.info("list sizeE : " + list.size());

        return list;
    }

    @Transactional
    public Long save(Building Entity)
    {
        Building saveBuilding = buildingRepo.save(Entity);

        return saveBuilding.getId();
    }

    @Transactional(readOnly = true)
    public Building searchBuildingID(int siCode, int epCode, String aptName)
    {
        return buildingRepo.searchBuilding(siCode, epCode, aptName);
    }

    @Transactional
    public Long saveBuilding(MapListDto mapListDto)
    {
        Long BuildingID;
        // 만약에 없으면
        if(!buildingRepo.exist(mapListDto.getSigunguCode(), mapListDto.getEupmyundongCode(),mapListDto.getAptName()))
        {
            String codeStr = String.valueOf(mapListDto.getSigunguCode()).concat(String.valueOf(mapListDto.getEupmyundongCode()));

            Long code = Long.valueOf(codeStr);

            Address address = addressService.searchSigunguCode(code);

            Year year = Year.of(mapListDto.getBuildYear());

            // Building을 저장해준다.
            BuildingRequestDto.BuildingSaveDto buildingSaveDto = new BuildingRequestDto.BuildingSaveDto(year,
                    mapListDto.getSigunguCode(), mapListDto.getEupmyundongCode(), mapListDto.getSigungu(), mapListDto.getDong(), mapListDto.getAptName(),
                    mapListDto.getRoadBuildingCode(), mapListDto.getRoadSubBuildingCode(), mapListDto.getRoadName(), Long.valueOf(codeStr), mapListDto.getBonbun(), mapListDto.getBubun());

            buildingSaveDto.setAddressID(address);

            Building saveBuilding = buildingSaveDto.toEntity();

            BuildingID = save(saveBuilding);
        }

        else{
            Building building = searchBuildingID(mapListDto.getSigunguCode(), mapListDto.getEupmyundongCode(),mapListDto.getAptName());
            BuildingID = building.getId();
        }

        return BuildingID;
    }

    @Transactional(readOnly = true)
    public Building returnBuilding(Long ID)
    {
        return buildingRepo.returnBuilding(ID);
    }


    @Transactional(readOnly = true)
    public List<Object> InfoBuildingService(Long cityID)
    {
        return buildingRepo.buildingSalesList(cityID);
    }

    @Transactional(readOnly = true)
    public List<Building> searchBuilding(int siCode, int epCode)
    {
        return buildingRepo.searchBuildingByCode(siCode, epCode);
    }

    @Transactional(readOnly = true)
    public List<AddressDto.searchAddressDto> searchBuildingName(String name)
    {
        return buildingRepo.searchBuilding(name);
    }

    @Transactional(readOnly = true)
    public List<Sales> salesTransactionService(Long cityID, String dateStr)
    {
        return buildingRepo.salesTransactionList(cityID, dateStr);
    }


}
