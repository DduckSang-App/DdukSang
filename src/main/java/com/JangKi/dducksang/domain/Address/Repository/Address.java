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
    private Long ID;

    Long code;

    @Column(columnDefinition = "TEXT")
    String located_nm; // 전체지역 이름

    @Column(columnDefinition = "TEXT")
    String locate_v1;

    @Column(columnDefinition = "TEXT")
    String locate_v2;

    @Column(columnDefinition = "TEXT")
    String locate_v3;

    @Column(columnDefinition = "TEXT")
    String locate_v4;

    @Column(columnDefinition = "TEXT")
    String locate_v5;

    @Builder
    public Address(Long code, String located_nm, String locate_v1,String locate_v2, String locate_v3, String locate_v4, String locate_v5)
    {
        this.code = code;
        this.located_nm = located_nm;
        this.locate_v1 = locate_v1;
        this.locate_v2 = locate_v2;
        this.locate_v3 = locate_v3;
        this.locate_v4 = locate_v4;
        this.locate_v5 = locate_v5;
    }
}
