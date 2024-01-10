package com.JangKi.dducksang.domain.Sales;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SalesRepo extends JpaRepository<Sales, Long> {
    @Query("SELECT s FROM Sales s left join Building b on s.building.Id = b.Id where b.Id = :ID")
    List<Map<String, Object>> SalesInfo(@Param("ID") Long ID);
}
