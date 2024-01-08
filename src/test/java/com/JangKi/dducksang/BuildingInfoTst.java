package com.JangKi.dducksang;

import com.JangKi.dducksang.Service.Address.AddressService;
import com.JangKi.dducksang.Service.Building.BuildingService;
import com.JangKi.dducksang.Web.Dto.BuildingDto.BuildingRequestDto;
import com.JangKi.dducksang.Web.Dto.Map.MapListDto;
import com.JangKi.dducksang.domain.Address.Repository.Address;
import com.JangKi.dducksang.domain.Address.Repository.AddressRepo;
import com.JangKi.dducksang.domain.Building.Repository.Building;
import com.JangKi.dducksang.domain.Building.Repository.BuildingRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ExtendWith(MockitoExtension.class)
@TestPropertySource("classpath:application-test.properties") //test용 properties 파일 설정
public class BuildingInfoTst {

    @InjectMocks
    private BuildingService buildingService;

    @InjectMocks
    private AddressService addressService;

    @Mock
    private BuildingRepo buildingRepo;

    @Mock
    private AddressRepo addressRepo;

    @Test
    @DisplayName("String date -> date 변환 테스트")
    public void conertDate()
    {
        String dateStr = "2019-02-02";

        LocalDate LDate = LocalDate.of(2019, 2, 2);
        try {
            System.out.println(LDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("매매 데이터 Building 저장 - Address와 연결")
    public void createBuildingInfo()
    {
        // 실제 데이터에서 한 개 뽑아서 테스트
        MapListDto mapListDto = new MapListDto(75000, 2008, 2015, 2, 16, 11110,
                11500, " ", "사직동", "광화문스페이스본(101동~105동)",
                94.28, 11);

        // convert total Code
        String codeStr = String.valueOf(mapListDto.getSigunguCode()) + String.valueOf(mapListDto.getEupmyundongCode());

        Long code = Long.valueOf(codeStr);

        Address address = addressService.searchSigunguCode(code);

        LocalDate date = LocalDate.of(mapListDto.getBuildYear(), mapListDto.getDealMonth(), mapListDto.getDealDay());

//        LocalDate date = LocalDate.parse(String.valueOf(mapListDto.getBuildYear()) + "-" + mapListDto.getDealMonth() + "-" + mapListDto.getDealDay(), formatter);

        System.out.println(date);

        BuildingRequestDto.BuildingSaveDto buildingSaveDto = new BuildingRequestDto.BuildingSaveDto(date,
                mapListDto.getSigunguCode(), mapListDto.getEupmyundongCode(), mapListDto.getSigungu(), mapListDto.getDong(), mapListDto.getAptName());

        buildingSaveDto.setAddressID(address);

        Building saveBuilding = buildingSaveDto.toEntity();

        //stub
        BDDMockito.given(buildingRepo.save(saveBuilding)).willReturn(saveBuilding);

        //when
        Long saveId = buildingService.save(saveBuilding);

        // then
        Assertions.assertThat(saveBuilding.getId()).isEqualTo(saveId);
    }
}
