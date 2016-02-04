package com.razorthink.junit.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

/**
 * Created by shams on 2/3/16.
 */
public class BaseDAO {

    public boolean executeQuery(String query) {
        final Connection conn = H2DBManager.getInstance().getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != conn) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public HashSet<String> executeQueryAndReturn(String query) {
        HashSet<String> messages = new HashSet<String>();
        final Connection conn = H2DBManager.getInstance().getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                messages.add(rs.getString("message"));
            }
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != conn) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return messages;
    }
}
