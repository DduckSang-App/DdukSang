package com.JangKi.dducksang.Service.Building;

import com.JangKi.dducksang.domain.Building.Repository.BuildingRepo;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuildingService {

    private final BuildingRepo buildingRepo;


}
