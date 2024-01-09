package com.JangKi.dducksang.domain.Address.Repository;

import com.JangKi.dducksang.Web.Dto.AddressDto.AddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepo extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a order by a.code DESC")
    List<Address> AddressList();

    @Query("SELECT a FROM Address a WHERE a.located_nm LIKE %:SiGunGu% order by a.located_nm ASC")
    List<AddressDto.AddressInfoDto> SiGunGuList(@Param("SiGunGu") String SiGunGu);

    @Query("SELECT a FROM Address a WHERE a.code = :city")
    Address searchCode(@Param("city") Long city);
}
