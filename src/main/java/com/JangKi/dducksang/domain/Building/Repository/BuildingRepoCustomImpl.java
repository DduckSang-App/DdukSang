package com.JangKi.dducksang.domain.Building.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.JangKi.dducksang.domain.Building.Repository.QBuilding.building;

@RequiredArgsConstructor
public class BuildingRepoCustomImpl implements BuildingRepoCustom{

    private final JPAQueryFactory queryFactory;

    public Boolean exist(int sigunguCode, int eupmyundong, String aptName) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(building)
                .where(building.sigunguCode.eq(sigunguCode)
                        .and(building.eupmyundongCode.eq(eupmyundong)
                                .and(building.aptName.eq(aptName)))
                )
                .fetchFirst(); // limit 1

        return fetchOne != null; // 1개가 있는지 없는지 판단 (없으면 null이라 null체크)
    }
}
