package com.JangKi.dducksang.domain.Building.Repository;

import com.JangKi.dducksang.domain.Sales.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BuildingRepo extends JpaRepository<Building, Long>, BuildingRepoCustom{

    @Query("SELECT b FROM Building b WHERE b.sigunguCode = :sigunguCode and b.eupmyundongCode = :eupmyundongCode and b.aptName = :aptName")
    Building searchBuilding(@Param("sigunguCode") int sigunguCode, @Param("eupmyundongCode") int eupmyundongCode, @Param("aptName") String aptName);

    @Query("SELECT b FROM Building b WHERE b.Id = :Id")
    Building returnBuilding(@Param("Id") Long Id);

    @Query("SELECT s FROM Building b inner join Sales s on b.Id = s.building.Id where b.Id = :cityID")
    List<Object> buildingSalesList(@Param("cityID") Long cityID);

    @Query("SELECT b FROM Building b WHERE b.sigunguCode = :siCode and b.eupmyundongCode = :epCode")
    List<Building> searchBuildingByCode(@Param("siCode") int siCode, @Param("epCode") int epCode);

    @Query("SELECT b FROM Building b WHERE b.sigunguCode = :siCode")
    List<Building> searchBuildingFromSigungu(@Param("siCode") int siCode);
}

