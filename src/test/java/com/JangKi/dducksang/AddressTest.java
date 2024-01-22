package com.JangKi.dducksang;


import com.JangKi.dducksang.Service.Address.AddressService;
import com.JangKi.dducksang.Service.Building.BuildingService;
import com.JangKi.dducksang.Web.Dto.AddressDto.AddressDto;
import com.JangKi.dducksang.Web.Dto.BuildingDto.BuildingRequestDto;
import com.JangKi.dducksang.Web.Dto.Map.MapListDto;
import com.JangKi.dducksang.domain.Address.Repository.Address;
import com.JangKi.dducksang.domain.Address.Repository.AddressRepo;
import com.JangKi.dducksang.domain.Building.Repository.Building;
import com.JangKi.dducksang.domain.Building.Repository.BuildingRepo;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

import java.time.Year;

@ExtendWith(MockitoExtension.class)
@TestPropertySource("classpath:application-test.properties") //test용 properties 파일 설정
public class AddressTest {

    @InjectMocks
    private AddressService addressService;

    @InjectMocks
    private BuildingService buildingService;

    @Mock
    private BuildingRepo buildingRepo;

    @Mock
    private AddressRepo addressRepo;

    @Nested
    public class AddressServiceTest{

        @Test
        @DisplayName("시군구/아파트 검색 시, 해당하는 주소 반환")
        public void searchSigunguTest()
        {

        }

    }
}
