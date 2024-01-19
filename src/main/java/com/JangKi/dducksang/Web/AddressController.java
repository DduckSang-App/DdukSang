package com.JangKi.dducksang.Web;

import com.JangKi.dducksang.Service.Address.AddressService;
import com.JangKi.dducksang.Service.Building.BuildingService;
import com.JangKi.dducksang.Web.Dto.AddressDto.AddressDto;
import com.JangKi.dducksang.Web.Dto.AddressDto.AddressRequestDto;
import com.JangKi.dducksang.domain.Address.Repository.Address;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AddressController {

    private final AddressService addressService;

    private final BuildingService buildingService;

    @GetMapping("/allAddress")
    public List<Address> findAllAdress()
    {
        List<Address> addresses = addressService.AllAddressService();

        return addressService.AllAddressService();
    }

    @PostMapping("/searchAddress")
    public List<AddressDto.searchAddressDto> findSiGungu(@RequestBody AddressRequestDto.SigunguRequestDto city)
    {
        List<AddressDto.searchAddressDto> siList = addressService.SiGunGuSearchService(city.getSiGunGu());

        List<AddressDto.searchAddressDto> aptList = buildingService.searchBuildingName(city.getSiGunGu());

        List<AddressDto.searchAddressDto> totalList = Stream.concat(
                siList.stream(),
                aptList.stream()
        ).collect(Collectors.toList());

        totalList = totalList.stream()
                .sorted(
                        Comparator.comparingInt(AddressDto.searchAddressDto::getType)
                                .thenComparing(AddressDto.searchAddressDto::getLocatedNM)
                                .thenComparing(AddressDto.searchAddressDto::getAptName)
                ).collect(Collectors.toList());

        return totalList;
    }
}
