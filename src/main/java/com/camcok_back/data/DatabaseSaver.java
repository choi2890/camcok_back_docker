package com.camcok_back.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

public class DatabaseSaver {
    private static final String URL = "jdbc:mysql://localhost:3306/camcok_db";
    private static final String USER = "root";
    private static final String PASSWORD = "my1234";

    public static void saveData(List<Map<String, Object>> dataList) throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

        // 예시로, 'data_table' 테이블에 'key'와 'value' 컬럼을 가정합니다.
        String sql = "INSERT INTO data_table (camp_Recom, lctCl,animalCmgCl,tooltip,tourEraCl) VALUES (?, ?,?,?,?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Map<String, Object> data : dataList) {
                pstmt.setString(1, data.get("desiredcamp_Recom").toString());
                pstmt.setString(2, data.get("desiredlctCl").toString());
                pstmt.setString(3, data.get("desiredanimalCmgCl").toString());
                pstmt.setString(4, data.get("desiredtooltip").toString());
                pstmt.setString(5, data.get("desiredtourEraCl").toString());
                pstmt.executeUpdate();
            }
        } finally {
            conn.close();
        }
    }
}
