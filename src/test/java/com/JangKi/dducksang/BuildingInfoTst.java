//package com.JangKi.dducksang;
//
//import com.JangKi.dducksang.Service.Address.AddressService;
//import com.JangKi.dducksang.Service.Building.BuildingService;
//import com.JangKi.dducksang.Service.Sales.SalesService;
//import com.JangKi.dducksang.Web.Dto.AddressDto.AddressDto;
//import com.JangKi.dducksang.Web.Dto.BuildingDto.BuildingRequestDto;
//import com.JangKi.dducksang.Web.Dto.Map.MapListDto;
//import com.JangKi.dducksang.Web.Dto.SalesDto.SalesRequestDto;
//import com.JangKi.dducksang.domain.Address.Repository.Address;
//import com.JangKi.dducksang.domain.Address.Repository.AddressRepo;
//import com.JangKi.dducksang.domain.Building.Repository.Building;
//import com.JangKi.dducksang.domain.Building.Repository.BuildingRepo;
//import com.JangKi.dducksang.domain.Sales.Sales;
//import com.JangKi.dducksang.domain.Sales.SalesRepo;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.BDDMockito;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.test.context.TestPropertySource;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.Year;
//import java.time.format.DateTimeFormatter;
//
//
//@ExtendWith(MockitoExtension.class)
//@TestPropertySource("classpath:application-test.properties") //test용 properties 파일 설정
//public class BuildingInfoTst {
//
//    @InjectMocks
//    private BuildingService buildingService;
//
//    @InjectMocks
//    private AddressService addressService;
//
//    @InjectMocks
//    private SalesService salesService;
//
//    @Mock
//    private BuildingRepo buildingRepo;
//
//    @Mock
//    private AddressRepo addressRepo;
//
//    @Mock
//    private SalesRepo salesRepo;
//
//    @Test
//    @DisplayName("String date -> date 변환 테스트")
//    public void conertDate()
//    {
//        String dateStr = "2019-02-02";
//
//        Year year = Year.of(2019);
//        try {
//            System.out.println(year);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    @DisplayName("매매 데이터 Building 저장 - Address와 연결")
//    public void createBuildingInfo()
//    {
//        // 실제 데이터에서 한 개 뽑아서 테스트
//        MapListDto mapListDto = new MapListDto(75000, 2008, 2015, 2, 16, 11110,
//                11500, " ", "사직동", "광화문스페이스본(101동~105동)",
//                94.28, 11);
//
//        // convert total Code
//        String codeStr = String.valueOf(mapListDto.getSigunguCode()) + String.valueOf(mapListDto.getEupmyundongCode());
//
//        Long code = Long.valueOf(codeStr);
//
//        // Inject Address
//        AddressDto.AddressInfoDto addressInfoDto = new AddressDto.AddressInfoDto(code, "서울특별시 종로구 사직동", "서울특별시", "종로구", "사직동", "");
//
//        Mockito.when(addressService.searchSigunguCode(code)).thenReturn(addressInfoDto.toEntity());
//
//        Year year = Year.of(mapListDto.getBuildYear());
//
//        Address address = addressService.searchSigunguCode(code);
//
//        BuildingRequestDto.BuildingSaveDto buildingSaveDto = new BuildingRequestDto.BuildingSaveDto(year,
//                mapListDto.getSigunguCode(), mapListDto.getEupmyundongCode(), mapListDto.getSigungu(), mapListDto.getDong(), mapListDto.getAptName());
//
//        buildingSaveDto.setAddressID(address);
//
//        Building saveBuilding = buildingSaveDto.toEntity();
//
//        saveBuilding.updateID(1L);
//
//        // stub
//        BDDMockito.given(buildingRepo.save(saveBuilding)).willReturn(saveBuilding);
//
//        // when
//        Long saveId = buildingService.save(saveBuilding);
//
//        // then
//        System.out.println(saveBuilding.getId() + " " + saveId);
//        Assertions.assertThat(saveBuilding.getId()).isEqualTo(saveId);
//    }
//
//    @Test
//    @DisplayName("매매 데이터 Sales 저장 - Building와 연결")
//    public void SalesDBcreate()
//    {
//        // 실제 데이터에서 한 개 뽑아서 테스트
//        MapListDto mapListDto = new MapListDto(75000, 2008, 2015, 2, 16, 11110,
//                11500, " ", "사직동", "광화문스페이스본(101동~105동)",
//                94.28, 11);
//
//        Year year = Year.of(mapListDto.getBuildYear());
//
//        BuildingRequestDto.BuildingSaveDto saveDto = new BuildingRequestDto.BuildingSaveDto(year, mapListDto.getSigunguCode(), mapListDto.getEupmyundongCode(),
//                mapListDto.getSigungu(), mapListDto.getDong(), mapListDto.getAptName());
//
//        Building buildingEntity = saveDto.toEntity();
//        buildingEntity.updateID(1L);
//
//        Mockito.when(buildingService.searchBuildingID(mapListDto.getSigunguCode(), mapListDto.getEupmyundongCode(), mapListDto.getAptName()))
//                .thenReturn(buildingEntity);
//
//        LocalDate localDate = LocalDate.of(mapListDto.getDealYear(), mapListDto.getDealMonth(),mapListDto.getDealDay());
//
//        SalesRequestDto.SalesSaveDto salesSaveDto = new SalesRequestDto.SalesSaveDto(localDate, 2000, 32.5, 2);
//
//        Building building = buildingService.searchBuildingID(mapListDto.getSigunguCode(), mapListDto.getEupmyundongCode(), mapListDto.getAptName());
//
//        salesSaveDto.updateBuilding(building);
//
//        Sales salesSave = salesSaveDto.toEntity();
//
//        salesSave.updateID(1L);
//
//        // stub
//        BDDMockito.given(salesRepo.save(salesSave)).willReturn(salesSave);
//
//        // when
//        Long saveId = salesService.save(salesSave);
//
//        // then
//        System.out.println(salesSave.getId() + " " + saveId);
//        Assertions.assertThat(salesSave.getId()).isEqualTo(saveId);
//    }
//}
