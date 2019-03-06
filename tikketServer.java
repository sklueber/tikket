import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;


public class tikketServer {
    public static Connection connect() {
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
        return conn;
    }

    public static void main(String[] args) {
        //connect();
        veranstalterErstellen("hallo");
    }

    private void ticketErstellen(){

    }

    private void ticketPruefen() {

    }

    private void ticketAusgeben() {

    }

    private void veranstaltungErstellen() {

    }

    private void veranstaltungAusgeben() {

    }

    private static void veranstalterErstellen(String vr_name){
            String sql = "INSERT INTO veranstalter(vr_ID,vr_name) VALUES(?,?)";

            try (Connection conn = connect();
                 PreparedStatement prepState = conn.prepareStatement(sql)) {
                prepState.setString(2, vr_name);
                prepState.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    private void veranstalterEntfernen(){

    }
}