package com.JangKi.dducksang.domain.Address.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepo extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a order by a.code DESC")
    List<Address> AddressList();

}
