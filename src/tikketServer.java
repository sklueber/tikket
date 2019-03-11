import java.sql.*;
import java.time.Instant;

public class tikketServer {

    public static void main(String[] args) {
        test();
    }

    /*Bietet eine Spielwiese zum Testen an :) */
    private static void test() {
        tikketServer tktSrv = new tikketServer();

        tktSrv.veranstalterErstellen("MaxStockhausenIst1nicerDude");
        tktSrv.veranstalterAusgeben();

        tktSrv.ticketErstellen(1, 1);
        tktSrv.ticketAusgeben();
    }

    private static Connection connect() {
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


    private void ticketErstellen(int tkt_status, int tkt_va) {
        String sql = "INSERT INTO tickets(tkt_status, tkt_created, tkt_va) VALUES(?,?,?)";

        try (Connection conn = connect()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, tkt_status);
                pstmt.setTimestamp(2, Timestamp.from(Instant.now())); //TODO Timestamp wird von der DB nicht angenommen. Fixen
                pstmt.setInt(3, tkt_va);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void ticketPruefen() {
    }

    private void ticketAusgeben() {
        String sql = "SELECT tkt_ID, tkt_status, tkt_created, tkt_va FROM  tickets";

        try (Connection conn = connect()) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    //ResultSet durchloopen
                    while (rs.next()) {
                        System.out.println(
                                rs.getInt("tkt_ID") + "\t" +
                                rs.getInt("tkt_status") + "\t" +
                                rs.getTimestamp("tkt_created") + "\t" +
                                rs.getInt("tkt_va")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void veranstaltungErstellen() {
    }

    private void veranstaltungAusgeben() {

    }

    private void veranstalterErstellen(String vr_name) {
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

    private void veranstalterAusgeben() {
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