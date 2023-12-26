package com.JangKi.dducksang.Web;

import com.JangKi.dducksang.Service.Address.AddressService;
import com.JangKi.dducksang.domain.Address.Repository.Address;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

//        for(Address ad : addresses)
//        {
//            log.info(ad.getName() + " " + ad.getCode());
//        }

        return addressService.AllAddressService();
    }
}
