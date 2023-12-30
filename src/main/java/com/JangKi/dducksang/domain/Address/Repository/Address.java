package com.JangKi.dducksang.domain.Address.Repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/* 지역번호 관리 리스트 */
//@NoArgsConstructor
////@AllArgsConstructor
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Add_ID;

//    @Column(columnDefinition = "TEXT")
    Long code; // 법정동 코드

//    @Column(columnDefinition = "TEXT")
    String name; // 법정동명

    public Address(Long code, String name)
    {
        this.code = code;
        this.name = name;
    }
}
