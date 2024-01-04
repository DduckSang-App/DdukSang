//package com.JangKi.dducksang.domain.Address.Repository;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.GenericGenerator;
//import org.springframework.data.repository.query.Param;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class JDBCAddress {
//    @Id
//    @GenericGenerator(
//            name = "SequenceGenerator",
//            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//            parameters = {
//                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "hibernate_sequence"),
//                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "pooled"),
//                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
//                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "500")
//            }
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "SequenceGenerator"
//    )
//    private Long ID;
//
//    Long code;
//
//    @Column(columnDefinition = "TEXT")
//    String located_nm; // 전체지역 이름
//
//    @Column(columnDefinition = "TEXT")
//    String locate_v1;
//
//    @Column(columnDefinition = "TEXT")
//    String locate_v2;
//
//    @Column(columnDefinition = "TEXT")
//    String locate_v3;
//
//    @Column(columnDefinition = "TEXT")
//    String locate_v4;
//
//    @Column(columnDefinition = "TEXT")
//    String locate_v5;
//
//    @Builder
//    public JDBCAddress(Long code, String located_nm, String locate_v1,String locate_v2, String locate_v3, String locate_v4, String locate_v5)
//    {
//        this.code = code;
//        this.located_nm = located_nm;
//        this.locate_v1 = locate_v1;
//        this.locate_v2 = locate_v2;
//        this.locate_v3 = locate_v3;
//        this.locate_v4 = locate_v4;
//        this.locate_v5 = locate_v5;
//    }
//}
//
