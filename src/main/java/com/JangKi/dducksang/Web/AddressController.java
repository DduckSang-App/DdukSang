package com.JangKi.dducksang.Web;

import com.JangKi.dducksang.Service.Address.AddressService;
import com.JangKi.dducksang.Web.Dto.AddressDto.AddressDto;
import com.JangKi.dducksang.Web.Dto.AddressDto.AddressRequestDto;
import com.JangKi.dducksang.domain.Address.Repository.Address;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/allAddress")
    public List<Address> findAllAdress()
    {
        List<Address> addresses = addressService.AllAddressService();

        return addressService.AllAddressService();
    }

    @PostMapping("/SearchSigungu")
    public List<AddressDto.AddressInfoDto> findSiGungu(@RequestBody AddressRequestDto.SigunguRequestDto city)
    {
        log.info(city.getSiGunGu());
        List<AddressDto.AddressInfoDto> SigunguList = addressService.SiGunGuSearchService(city.getSiGunGu());

        return SigunguList;
    }
}
