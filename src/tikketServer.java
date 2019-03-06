import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class tikketServer {
    public static void connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:tikket_db.db"; //Location der Datenbank
            conn = DriverManager.getConnection(url);

            System.out.println("Verbindung zur tikket-Datenbank hergestellt"); //Success

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage()); //Failure
            }
        }
    }

    public static void main(String[] args) {
        connect();
    }

    private void ticketErstellen() {
    }

    ;

    private void ticketPruefen() {
    }

    ;

    private void ticketAusgeben() {
    }

    ;

    private void veranstaltungErstellen() {
    }

    ;

    private void veranstaltungAusgeben() {
    }

    ;

}