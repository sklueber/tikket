/*
 * Informatikprojekt aus 2019. Erstellt von Simon und Max.
 * Zuletzt bearbeitet 26.03.19 00:01.
 * Keiner klaut das hier! Copyright oder so (c) 2019.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientStarten extends JFrame {
    private JButton bVerbinden;
    private JTextField tServerIP;
    private JTextField tServerPort;
    private JPanel ClientStarten;
    private JLabel lServerPort;
    private JLabel lServerIP;
    private JLabel lClientStarten;
    private tikketClient gestartetVon;
    private String ip;
    private int port;

    public ClientStarten(tikketClient client) {
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
        gestartetVon = client;
        Runtime.getRuntime().addShutdownHook(new ShutdownThread());
        JFrame frame = new JFrame("Client Starten");
        frame.setContentPane(this.ClientStarten);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        bVerbinden.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ip = tServerIP.getText();
                port = Integer.parseInt(tServerPort.getText());
                gestartetVon.setTikketServerHost(ip);
                gestartetVon.setTikketServerPort(port);
                gestartetVon.socketErstellen();
                gestartetVon.starten();
            }
        });
    }

    class ShutdownThread extends Thread {
        public void run() {
            gestartetVon.beenden();
        }
    }
}
