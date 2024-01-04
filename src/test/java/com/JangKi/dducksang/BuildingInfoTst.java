package com.JangKi.dducksang;

import com.JangKi.dducksang.Service.Building.BuildingService;
import com.JangKi.dducksang.domain.Building.Repository.BuildingRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.TestPropertySource;

@ExtendWith(MockitoExtension.class)
@TestPropertySource("classpath:application-test.properties") //test용 properties 파일 설정
public class BuildingInfoTst {

    @InjectMocks
    BuildingService buildingService;

    @Mock
    BuildingRepo buildingRepo;

    @Test
    @DisplayName("일부 매매 데이터 받아오기 테스트")
    void createBuildingInfo()
    {
        // 서울 특별시 종로구 [ 11110 ] 에 대해서 테스트한다.

    }
}
