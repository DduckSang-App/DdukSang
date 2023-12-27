package com.JangKi.dducksang.domain.Address.Repository;

import com.JangKi.dducksang.Web.Dto.AddressDto.AddressDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepo extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a order by a.code DESC")
    List<Address> AddressList();

    @Query("SELECT a FROM Address a WHERE a.name LIKE %:SiGunGu% order by a.name ASC")
    List<AddressDto.AddressInfoDto> SiGunGuList(@Param("SiGunGu") String SiGunGu);
}
