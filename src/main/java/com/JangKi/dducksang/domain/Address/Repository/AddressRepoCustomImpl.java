package com.JangKi.dducksang.domain.Address.Repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.ast.tree.expression.Distinct;

import java.util.List;

import static com.JangKi.dducksang.domain.Address.Repository.QAddress.address;

@RequiredArgsConstructor
public class AddressRepoCustomImpl implements AddressRepoCustom{

    private final JPAQueryFactory queryFactory;

    // 1. 모두 조회
    public List<String> searchAll() {
        List<String> fetchString = queryFactory
                .select(address.code.stringValue().substring(1, 5).as("code"))
                .from(address)
                .fetch();
        return fetchString;
    }

    // 2. DISTINCT를 통한 조회
    public List<String> searchAllByDISTINCT()
    {
        List<String> fetchString = queryFactory
                .selectDistinct(address.code.stringValue().substring(1, 5).as("code"))
                .from(address)
                .fetch();

        return fetchString;
    }


    // 3. Group BY를 통한 조회
    public List<String> searchAllByGroupBy()
    {
        List<String> fetchString = queryFactory
                .select(address.code.stringValue().substring(0, 5).as("code"))
                .from(address)
                .groupBy(address.code.stringValue().substring(0, 5))
                .fetch();

        return fetchString;
    }

}
