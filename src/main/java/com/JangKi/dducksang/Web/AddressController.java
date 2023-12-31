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

    @CrossOrigin(origins = "http://ec2-15-164-32-179.ap-northeast-2.compute.amazonaws.com:8080/SearchSigungu", methods = RequestMethod.GET)
    @GetMapping("/allAddress")
    public List<Address> findAllAdress()
    {
        List<Address> addresses = addressService.AllAddressService();

//        for(Address ad : addresses)
//        {
//            log.info(ad.getName() + " " + ad.getCode());
//        }

        return addressService.AllAddressService();
    }

    @CrossOrigin(origins = "http://ec2-15-164-32-179.ap-northeast-2.compute.amazonaws.com:8080/SearchSigungu", methods = RequestMethod.POST)
    @PostMapping("/SearchSigungu")
    public List<AddressDto.AddressInfoDto> findSiGungu(@RequestBody AddressRequestDto.SigunguRequestDto city)
    {
        log.info(city.getSiGunGu());
        List<AddressDto.AddressInfoDto> SigunguList = addressService.SiGunGuSearchService(city.getSiGunGu());

        return SigunguList;
    }
}
