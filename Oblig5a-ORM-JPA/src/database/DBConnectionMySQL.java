package database;

import java.sql.*;

public class DBConnectionMySQL {
    private static DBConnectionMySQL instance;
    private Connection connection;
    private String url = "jdbc:mysql://mysql-ait.stud.idi.ntnu.no:3306/hanswf";
    private String user = "hanswf";
    private String password = "M95OyPuT";

    private DBConnectionMySQL() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DBConnectionMySQL getInstance() throws SQLException {
        if(instance == null) {
            instance = new DBConnectionMySQL();
        } else if(instance.getConnection().isClosed()) {
            instance = new DBConnectionMySQL();
        }
        return instance;
    }

    public static void main(String[]args) {
        DBConnectionMySQL db = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            db = DBConnectionMySQL.getInstance();
            con = db.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from contacts");
            while(rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
            }

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            close(con, stmt, rs);
        }
    }

    private static void close(Connection con, Statement stmt, ResultSet rs) {
        try {
            if(con != null) con.close();
            if(stmt != null) stmt.close();
            if(rs != null) rs.close();
        } catch(SQLException e ) {
            e.printStackTrace();
        }
    }


}
