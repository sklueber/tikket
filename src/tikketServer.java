import java.io.IOException;
import java.net.ServerSocket;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class tikketServer {
    private int SrvVa_ID;
    private String SrvVa_name;
    private final ServerSocket server;

    public tikketServer(int port) throws IOException {
        server = new ServerSocket(port);
        System.out.println("tikketServer wurde auf Port " + port + " gestartet");
    }

    public static void main(String[] args) throws IOException {
        tikketServer tktSrv = new tikketServer(2001);

//        tktSrv.veranstaltungErstellen("geiles Konzert", "20190311", "hier", 1);
//        tktSrv.veranstaltungAusgeben();

//        tktSrv.ticketAusgeben();
//        tktSrv.ticketErstellen();
//        tktSrv.ticketAusgeben();
//        tktSrv.ticketPruefen(354779);

//        tktSrv.aktuelleVeranstaltungAusgeben();
//
//        tktSrv.veranstalterErstellen("Max Testveranstalter");
//        tktSrv.veranstalterAusgeben();
//        tktSrv.veranstaltungAusgeben();
//        tktSrv.veranstaltungErstellen("Max Testveranstaltung die erste", "heute", "irgendwo", 1);
//        tktSrv.veranstaltungAusgeben();
//        tktSrv.veranstaltungWechseln(1);

//        tktSrv.aktuelleVeranstaltungAusgeben();
    }

    private void verbinde() {

    }

    private static Connection DBconnect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:src/tikket_db.db"; //Location der Datenbank
            conn = DriverManager.getConnection(url);
//            System.out.println("Verbindung zur tikket-Datenbank hergestellt");
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private void ticketErstellen() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
        String currentDate = dateFormat.format(date);

        int zufall = ThreadLocalRandom.current().nextInt(0, 999999 + 1);

        try (Connection conn = DBconnect()) {
            String sqlInsertTicket = "INSERT INTO tickets(tkt_UUID, tkt_status, tkt_created, tkt_va) VALUES(?, ?,?,?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlInsertTicket)) {
                pstmt.setInt(1, zufall); //TODO in Java schon Unique machen. Ist bisher nur random
                pstmt.setInt(2, 1);
                pstmt.setString(3, currentDate);
                pstmt.setInt(4, SrvVa_ID);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean ticketPruefen(int UUID) {
        String sql = "SELECT tkt_ID, tkt_UUID, tkt_status, tkt_created, tkt_va FROM  tickets WHERE tkt_UUID = " + UUID + " AND tkt_va = " + SrvVa_ID;

        try (Connection conn = DBconnect()) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    if (rs.getInt("tkt_status") == 1) {
                        return true;
                    }
                    if (rs.isClosed()) {
                        return false;
                    }
                }
            }
        } catch (SQLException e) {
            if (e.getMessage().equals("ResultSet closed")) {
                System.out.println(e.getMessage());
            }
            return false;
        }
        return false;
    }

    private void ticketAusgeben() {
        String sql = "SELECT tkt_ID, tkt_UUID, tkt_status, tkt_created, tkt_va FROM  tickets";

        try (Connection conn = DBconnect()) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    //ResultSet durchloopen
                    while (rs.next()) {
                        System.out.println(
                                "Ticket ID: " +
                                        rs.getInt("tkt_ID") + "; UUID: " +
                                        rs.getInt("tkt_UUID") + "; Status: " +
                                        rs.getInt("tkt_status") + "; Timestamp: " +
                                        rs.getString("tkt_created") + "; Veranstaltungs ID: " +
                                        rs.getInt("tkt_va")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void veranstaltungErstellen(String va_name, String va_datum, String va_ort, int va_vr) {
        String sql = "INSERT INTO veranstaltungen(va_name, va_datum, va_ort, va_vr) VALUES(?,?,?,?)";

        try (Connection conn = DBconnect()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, va_name);
                pstmt.setString(2, va_datum);
                pstmt.setString(3, va_ort);
                pstmt.setInt(4, va_vr);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void veranstaltungAusgeben() {
        String sql = "SELECT va_name, va_datum, va_ort, va_vr FROM  veranstaltungen";

        try (Connection conn = DBconnect()) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    while (rs.next()) {
                        System.out.println(
                                rs.getString("va_name") + "\t" +
                                        rs.getString("va_datum") + "\t" +
                                        rs.getString("va_ort") + "\t" +
                                        rs.getInt("va_vr")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void veranstalterErstellen(String vr_name) {
        String sql = "INSERT INTO veranstalter(vr_name) VALUES(?)";

        try (Connection conn = DBconnect()) {
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

        try (Connection conn = DBconnect()) {
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

    private void veranstalterEntfernen(int pVr_ID) {
        String sql = "DELETE FROM veranstalter WHERE vr_ID = " + pVr_ID;

        try (Connection conn = DBconnect()) {
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void veranstaltungWechseln(int va_id) {
        String sql = "SELECT va_ID, va_name FROM  veranstaltungen WHERE va_ID = " + va_id;

        try (Connection conn = DBconnect()) {
            try (Statement stmt = conn.createStatement()) {
                try (ResultSet rs = stmt.executeQuery(sql)) {
                    SrvVa_name = rs.getString("va_name");
                    SrvVa_ID = va_id;
                }
                catch (SQLException e) {
                    System.out.println("Keine Veranstaltung mit der ID gefunden. Error: " + e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void aktuelleVeranstaltungAusgeben() {
        System.out.println("Aktuelle VA ID: " + SrvVa_ID);
        System.out.println("Aktueller VA Name: " + SrvVa_name);
    }
}