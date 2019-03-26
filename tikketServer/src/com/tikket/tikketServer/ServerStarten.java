package com.tikket.tikketServer;/*
 * Informatikprojekt aus 2019. Erstellt von Simon und Max.
 * Zuletzt bearbeitet 26.03.19 01:05.
 * Keiner klaut das hier! Copyright oder so (c) 2019.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

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
        URL iconURL = ServerStarten.class.getResource("tikketServer/src/resources/images/tikket_iconServer.png"); //Icon auslesen // TODO: 26.03.2019 Hardcoded String entfernen
        System.out.println(iconURL);
//        ImageIcon icon = new ImageIcon(iconURL);
        JFrame frame = new JFrame("Server Starten");
        frame.setContentPane(new ServerStarten().ServerStarten);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        frame.setIconImage(icon.getImage()); //Icon einfügen
        frame.pack();
        frame.setVisible(true);
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

    private void runServer(String port) throws IOException {
        int portInt = Integer.parseInt(port);
        tikketServer Server = new tikketServer(portInt);
        tikketServer.DBconnect();
        System.out.println("jo");
    }


}