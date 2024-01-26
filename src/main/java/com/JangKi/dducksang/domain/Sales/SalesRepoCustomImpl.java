package com.JangKi.dducksang.domain.Sales;

import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.JangKi.dducksang.domain.Sales.QSales.sales;

@RequiredArgsConstructor
public class SalesRepoCustomImpl implements SalesRepoCustom{

    private final JPAQueryFactory queryFactory;
    public double CalculateAverage(String dateStr, Long BuildingID)
    {
        DateTemplate formattedDate = Expressions.dateTemplate(
                String.class
                , "DATE_FORMAT({0}, {1})"
                , sales.salesDate
                , ConstantImpl.create("%Y%m"));

        Double average = queryFactory
                .select(
                        sales.amount.avg()
                )
                .from(sales)
                .where(sales.building.Id.eq(BuildingID))
                .where(formattedDate.eq(dateStr))
                .fetchOne();

        return average != null ? average : 0.0;
    }
}
