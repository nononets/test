package com.koitt.www.db;
 
/**
 * 
 * @author 송은혁
 * @since 2019-11-12
 * @version 1.0
 * @see
 * 
 */
 
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
 
public class Db {
    InitialContext init = null;
    DataSource ds = null;
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
     
    public Db() {
        try {
            init = new InitialContext();
             
            ds = (DataSource) init.lookup("java:comp/env/jdbc/Test");
 
            conn = ds.getConnection();
             
        } catch (Exception e) {
            e.printStackTrace();
             
        }       
    }
     
    public Db(String resourceName) {
        try {
            init = new InitialContext();
             
            ds = (DataSource) init.lookup("java:comp/env/" + resourceName);
             
            conn = ds.getConnection();
             
        } catch (Exception e) {
            e.printStackTrace();
             
        }       
    }
     
    public Connection getConn() {
        return conn;
         
    }
     
    public Statement getStmt(Connection conn) {
        try {
            stmt = conn.createStatement();
             
        } catch (SQLException e) {
            e.printStackTrace();
             
        }
         
        return stmt;
    }
     
    public PreparedStatement getPstmt(Connection conn, String sql) {
        try {
            pstmt = conn.prepareStatement(sql);
             
        } catch (SQLException e) {
            e.printStackTrace();
             
        }
         
        return pstmt;
    }
     
    public ResultSet getRs(Statement stmt, String sql) {
        try {
            rs = stmt.executeQuery(sql);
             
        } catch (SQLException e) {
            e.printStackTrace();
             
        }
         
        return rs;
    }
     
    public ResultSet getRs(PreparedStatement pstmt) {
        try {
            rs = pstmt.executeQuery();
             
        } catch (SQLException e) {
            e.printStackTrace();
             
        }
         
        return rs;
    }
     
    public void close(Object ... obj) {
        try {
            Object o = null;
             
            for (int i = 0; i < obj.length; i++) {
                o = obj[i];
                 
                if (o instanceof ResultSet) {
                    ((ResultSet) o).close();
                     
                } else if(o instanceof PreparedStatement) {
                    ((PreparedStatement) o).close();
                     
                } else if(o instanceof Statement) {
                    ((Statement) o).close();
                     
                } else if(o instanceof Connection) {
                    ((Connection) o).close();
                     
                } else {
                    continue;
                     
                }
                 
            }
             
        } catch (Exception e) {
            e.printStackTrace();
 
        } finally {
            try {
                init.close();
                 
            } catch (Exception e2) {
                e2.printStackTrace();
                 
            }
             
        }
         
    }
     
}