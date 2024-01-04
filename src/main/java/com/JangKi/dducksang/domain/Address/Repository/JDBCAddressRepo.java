//package com.JangKi.dducksang.domain.Address.Repository;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.jdbc.core.BatchPreparedStatementSetter;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//@RequiredArgsConstructor
//public class JDBCAddressRepo implements JDBCRepo {
//    private final JdbcTemplate jdbcTemplate;
//
//    @Value("${batchSize}")
//    private int batchSize;
//
//    @Override
//    public void saveAll(List<JDBCAddress> items) {
//        int batchCount = 0;
//        List<JDBCAddress> subItems = new ArrayList<>();
//        for (int i = 0; i < items.size(); i++) {
//            subItems.add(items.get(i));
//            if ((i + 1) % batchSize == 0) {
//                batchCount = batchInsert(batchSize, batchCount, subItems);
//            }
//        }
//        if (!subItems.isEmpty()) {
//            batchCount = batchInsert(batchSize, batchCount, subItems);
//        }
//        System.out.println("batchCount: " + batchCount);
//    }
//
//    private int batchInsert(int batchSize, int batchCount, List<JDBCAddress> subItems) {
//        jdbcTemplate.batchUpdate("INSERT INTO JDBCAddress(code, located_nm, locate_v1, locate_v2, locate_v3, locate_v4)" +
//                        "VALUES (?, ?, ?, ?, ?, ?, ?)",
//                new BatchPreparedStatementSetter() {
//                    @Override
//                    public void setValues(PreparedStatement ps, int i) throws SQLException {
//                        ps.setLong(1, subItems.get(i).getCode());
//                        ps.setString(2, subItems.get(i).getLocated_nm());
//                        ps.setString(3, subItems.get(i).getLocate_v1());
//                        ps.setString(4, subItems.get(i).getLocate_v2());
//                        ps.setString(5, subItems.get(i).getLocate_v3());
//                        ps.setString(6, subItems.get(i).getLocate_v4());
//                    }
//                    @Override
//                    public int getBatchSize() {
//                        return subItems.size();
//                    }
//                });
//        subItems.clear();
//        batchCount++;
//        return batchCount;
//    }
////    public void saveAll(List<JDBCAddress> addresses)
////    {
////        jdbcTemplate.batchUpdate(
////                "INSERT INTO JDBCAddress(code, located_nm, locate_v1, locate_v2, locate_v3, locate_v4)" +
////                        "VALUES (?, ?, ?, ?, ?, ?, ?)",
//////
//////                insert into JDBCAddress(ID, code, located_nm, locate_v1, locate_v2, locate_v3, locate_v4, locate_v5)" +
//////                "values(?, ?, ?, ?, ?, ?, ?, ?) on confli   ct(ID) do nothing
////                new BatchPreparedStatementSetter() {
////                    @Override
////                    public void setValues(PreparedStatement ps, int i) throws SQLException {
////                        ps.setLong(1, addresses.get(i).getID() != null ? addresses.get(i).getID() : genratedID());
////                        ps.setLong(2, addresses.get(i).getCode());
////                        ps.setString(3, addresses.get(i).getLocated_nm());
////                        ps.setString(4, addresses.get(i).getLocate_v1());
////                        ps.setString(5, addresses.get(i).getLocate_v2());
////                        ps.setString(6, addresses.get(i).getLocate_v3());
////                        ps.setString(7, addresses.get(i).getLocate_v4());
////                    }
////
////                    @Override
////                    public int getBatchSize() {
////                        return addresses.size();
////                    }
////                }
////        );
////    }
//
//}