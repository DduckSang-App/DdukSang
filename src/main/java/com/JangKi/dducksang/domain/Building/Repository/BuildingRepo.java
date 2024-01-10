package com.JangKi.dducksang.domain.Building.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepo extends JpaRepository<Building, Long>, BuildingRepoCustom{

    @Query("SELECT b FROM Building b WHERE b.sigunguCode = :sigunguCode and b.eupmyundongCode = :eupmyundongCode and b.aptName = :aptName")
    Building searchBuilding(@Param("sigunguCode") int sigunguCode, @Param("eupmyundongCode") int eupmyundongCode, @Param("aptName") String aptName);

    @Query("SELECT b FROM Building b WHERE b.Id = :Id")
    Building returnBuilding(@Param("Id") Long Id);
}
