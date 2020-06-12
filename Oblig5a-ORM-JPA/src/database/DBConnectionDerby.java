package database;

import java.sql.*;

/**
 * Klasse for å hente tilkobling til den lokale databasen. Klassen bruker det såkalte singleton designmønsteret,
 * i den forstand at den har et statisk medlem som inneholder instansen av dennne klassen. I tillegg, er
 * konstruktøren privat som vil hindre at objekter av denne klassen instansieres fra andre klasser.
 * Klassen har også en statisk metode som returnerer instancen av denne klassen.
 *
 * Dette designmønsteret er hensiktsmessig for denne klassen i den forstand at man trenger bare i dette tilfellet
 * en tilkobling til databasen. Man trenger derfor kun en instanse av denne klassen, og vil dermed hindre at
 * flere opprettes. Tilkobligen skal representere noe som er unikt, og det er ikke nødvendig med flere
 * tilkoblinger til en og samme database.
 */

public class DBConnectionDerby {
    private static DBConnectionDerby instance;
    private Connection con;
    private String url = "jdbc:derby://localhost:1527/contactslocaldb";
    private String user = "app";
    private String pass = "app";

    /**
     * Den private konstruktøren som henter en tilkobling til databasen. Konstruktøren er privat for at det ikke
     * skal lages objekter fra utsiden.
     * @throws SQLException dersom metoden ikke klarer å finne driveren.
     */
    private DBConnectionDerby() throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            this.con = DriverManager.getConnection(url, user, pass);
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get metode for Connection con
     * @return con
     */

    private Connection getCon() {
        return con;
    }

    /**
     * Her brukes lazy initilized singleton pattern i den forstand at instansen initialiseres ikke når den
     * deklareres men den initialiseres gjennom denne metoden, som er den såkalte "static factory method."
     * @return instance, som er instansen av denne klassen.
     */

    private static DBConnectionDerby getInstance() {
        try {
            if(instance == null)
                instance = new DBConnectionDerby();
            if(instance.getCon().isClosed())
                instance = new DBConnectionDerby();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public static void main(String[]args) {
        DBConnectionDerby db = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            db = getInstance();
            con = db.getCon();
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from contacts");

            while(rs.next()) {
                System.out.println(rs.getString(1) +
                        " " + rs.getString(2) +
                        " " + rs.getString(3));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                close(con, stmt, rs);
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metode for lukke con, stmt, og rs
     * @param con tilkoblingen
     * @param stmt statement
     * @param rs resultset
     * @throws SQLException
     */

    private static void close(Connection con, Statement stmt, ResultSet rs) throws SQLException {
        if(con != null) con.close();
        if(stmt != null) stmt.close();
        if(rs != null) rs.close();
    }
}
