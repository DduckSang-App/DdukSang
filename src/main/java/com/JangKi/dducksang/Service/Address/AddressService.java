package com.JangKi.dducksang.Service.Address;

import com.JangKi.dducksang.Web.Dto.AddressDto.AddressDto;
import com.JangKi.dducksang.domain.Address.Repository.Address;
import com.JangKi.dducksang.domain.Address.Repository.AddressRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepo addressRepo;

    @Transactional(readOnly = true)
    public List<Address> AllAddressService() {
        return addressRepo.AddressList();
    }

    // 시군구 검색
    @Transactional(readOnly = true)
    public List<AddressDto.searchAddressDto> SiGunGuSearchService(String city)
    {
        List<AddressDto.searchAddressDto> sidongList = addressRepo.SiGunGuList(city).stream()
            .map(addressInfoDto -> {
                return new AddressDto.searchAddressDto(addressInfoDto.getName());
            }).collect(Collectors.toList());

        return sidongList;
    }

    // code -> ID 반환
    @Transactional(readOnly = true)
    public Address searchSigunguCode(Long cityCode)
    {
        return addressRepo.searchCode(cityCode);
    }

    //
    @Transactional(readOnly = true)
    public Long searchCityCode(String cityName)
    {
        return addressRepo.searchAddressCode(cityName);
    }
}
