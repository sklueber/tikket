import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class tikketServer {

    public static void main(String[] args) {
    connect();
//    veranstalterErstellen("MaxStockhausenIst1nicerDude");
//    veranstalterAusgeben();

}

    public static Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:tikket_db.db"; //Location der Datenbank
            conn = DriverManager.getConnection(url);
            System.out.println("Verbindung zur tikket-Datenbank hergestellt");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    return conn;
    }


    private void ticketErstellen() {
    }

    private void ticketPruefen() {
    }

    private void ticketAusgeben() {
    }

    private void veranstaltungErstellen() {
    }

    private void veranstaltungAusgeben() {

    }

    private static void veranstalterErstellen(String vr_name) {
        String sql = "INSERT INTO veranstalter(vr_name) VALUES(?)";

        try (Connection conn = connect()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, vr_name);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void veranstalterAusgeben() {
        String sql = "SELECT vr_ID, vr_name FROM  veranstalter";

        try (Connection conn = connect()) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    //ResultSet durchloopen
                    while (rs.next()) {
                        System.out.println(rs.getInt("vr_ID") + "\t" + rs.getString("vr_name") + "\t");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void veranstalterEntfernen(){

    }
}