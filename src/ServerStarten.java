import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ServerStarten {
    private JPanel ServerStarten;
    private JLabel lServerPort;
    private JTextField tServerPort;
    private JButton bStarten;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Server Starten");
        frame.setContentPane(new ServerStarten().ServerStarten);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void runServer(String port) throws IOException {
        int portInt = Integer.parseInt(port);
        tikketServer tikketServer = new tikketServer(portInt);
        tikketServer.veranstalterErstellen("testVr");
        tikketServer.veranstaltungErstellen("test", "heute", "hier", 1);
        tikketServer.veranstaltungAusgeben();
        System.out.println("jo");
    }

    public ServerStarten() {

        bStarten.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    runServer(tServerPort.getText());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}