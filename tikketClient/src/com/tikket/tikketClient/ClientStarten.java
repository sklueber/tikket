/*
 * Informatikprojekt aus 2019. Erstellt von Simon und Max.
 * Zuletzt bearbeitet 26.03.19 01:08.
 * Keiner klaut das hier! Copyright oder so (c) 2019.
 */

package com.tikket.tikketClient;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

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

    public ClientStarten(tikketClient client) { //TODO (von Simon) Rueckmeldung bei Verbindungsfehler anzeigen, Konsole ist ja nicht sichtbar
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
        URL iconURL = this.getClass().getClassLoader().getResource("images/tikket_icon.png"); //Icon auslesen
        ImageIcon icon = new ImageIcon(iconURL);
        gestartetVon = client;
        Runtime.getRuntime().addShutdownHook(new ShutdownThread());
        JFrame frame = new JFrame("Client Starten");
        frame.setIconImage(icon.getImage()); //Icon einfügen
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
