package com.ASR_JAVA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.sql.*;


public class jdbc {
    public void delete_data(int id) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String dt = formatter.format(date);

        try (Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/CBA_21162101003", "root", "devsql1408")) {
            try (Statement stmt = con.createStatement()) {
                String table_name = "java_data";
                String set_sql = "set sql_safe_updates = 0";
                String delete = "delete from java_data where id = "+id+" ";
                stmt.execute(set_sql);
                stmt.execute(delete);
                System.out.println("Data has been deleted successfully.");
            }
            catch(Exception s){
                System.out.println("Sorry some occurred ,please try again.");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void get_data(int id) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        PreparedStatement p = null;
        ResultSet rs = null;
        try (Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/CBA_21162101003", "root", "devsql1408")) {
            try {
                String sql = "select * from java_data where id ="+id+" ";
                p = con.prepareStatement(sql);
                rs = p.executeQuery();
                while (rs.next()) {

                    int i_d = rs.getInt("id");
                    String date = rs.getString("date");
                    String  data= rs.getString("ASR_Data");
                    String  name = rs.getString("user_name");
                    System.out.println(i_d + "\t\t" + date
                            + "\t\t" + data + "\t\t"+name);
                }
            }
            catch (SQLException e) {
                System.out.println("Sorry some occurred ,please try again.");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
}

        public static void insert_data(int id,String data,String user_name) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String dt = formatter.format(date);
        try (Connection con = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/CBA_21162101003", "root", "devsql1408")) {
            try (Statement stmt = con.createStatement()) {
                String table_name = "java_data";
                String tableSql = String.format("CREATE TABLE IF NOT EXISTS %s"
                        + "(emp_id int PRIMARY KEY AUTO_INCREMENT, name varchar(30),"
                        + "position varchar(30), salary double)",table_name);
                String insert = String.format("insert into %s values("+id+",'"+dt+"',' "+data+"','"+user_name+"')",table_name);
                stmt.execute(insert);
                System.out.println("Data has been inserted successfully.");
            }
            catch(Exception s){
                System.out.println("Sorry some occurred ,please try again.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}