package com.JangKi.dducksang.domain.Address.Repository;

import com.JangKi.dducksang.domain.Building.Repository.Building;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/* 지역번호 관리 리스트 */
//@NoArgsConstructor
////@AllArgsConstructor
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @JsonManagedReference(value = "relation-Address-Building")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address", orphanRemoval = true)
    private List<Building> buildingList = new ArrayList<>();

    @Builder
    public Address(Long code, String located_nm, String locate_v1,String locate_v2, String locate_v3, String locate_v4, List<Building> buildingList)
    {
        this.code = code;
        this.located_nm = located_nm;
        this.locate_v1 = locate_v1;
        this.locate_v2 = locate_v2;
        this.locate_v3 = locate_v3;
        this.locate_v4 = locate_v4;
        this.buildingList = buildingList;
    }

    // For Test Args
    public void updateID(Long id)
    {
        this.ID = id;
    }

}
