/*
 * Informatikprojekt aus 2019. Erstellt von Simon und Max.
 * Zuletzt bearbeitet 25.03.19 23:54.
 * Keiner klaut das hier! Copyright oder so (c) 2019.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ServerStarten {
    private JPanel ServerStarten;
    private JLabel lServerPort;
    private JTextField tServerPort;
    private JButton bStarten;
    private JLabel lServerStarten;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
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