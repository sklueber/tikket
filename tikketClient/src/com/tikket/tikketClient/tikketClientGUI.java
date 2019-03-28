package com.tikket.tikketClient;/*
 * Informatikprojekt aus 2019. Erstellt von Simon und Max.
 * Zuletzt bearbeitet 26.03.19 00:59.
 * Keiner klaut das hier! Copyright oder so (c) 2019.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class tikketClientGUI {
    private tikketClient gestartetVon;

    private JPanel tikketClientGUI;
    private JTabbedPane tabPane;
    private JPanel panelEinlass;
    private JPanel panelAuslass;
    private JPanel panelTickets;
    private JPanel panelVeranstaltungen;
    private JPanel panelInfo;
    private JPanel panelStatus;
    private JLabel lStatistik;
    private JLabel lScanergebnis;
    private JTextField tScaneingabeEinlass;
    private JButton bScan;
    private JList listTickets;
    private JList listVeranstaltungen;
    private JButton bTicketNeu;
    private JButton bTEMPauslesen;
    private JButton erstellenButton;
    private JButton bVeranstaltungAuslesen;
    private JButton bVeranstaltungSetzen;

    public tikketClientGUI(tikketClient client) {
        this.gestartetVon = client;
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
        URL iconURL = tikketClientGUI.class.getClassLoader().getResource("images/tikket_icon.png"); //Icon auslesen
        ImageIcon icon = new ImageIcon(iconURL);
        JFrame frame = new JFrame("tikketClient");
        frame.setIconImage(icon.getImage()); //Icon einfügen
        frame.setContentPane(this.tikketClientGUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        bTEMPauslesen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestartetVon.ticketAusgeben();
            }
        });
        bVeranstaltungAuslesen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestartetVon.veranstaltungAusgeben();
            }
        });
        bScan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int scan = Integer.parseInt((tScaneingabeEinlass.getText()));
                if (gestartetVon.ticketPruefen(scan)) {
                    gestartetVon.ticketEinlass(scan);
                    System.out.println("Ticket erfolgreich eingelassen.");
                }// TODO: 28.03.2019 Auf Erfolg überprüfen
            }
        });
        bTicketNeu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestartetVon.ticketErstellen();
            }
        });
    }
}
