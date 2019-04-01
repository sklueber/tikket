/*
 * Informatikprojekt aus 2019. Erstellt von Simon und Max.
 * Zuletzt bearbeitet 02.04.19 00:16 .
 * Keiner klaut das hier! Copyright tikket (c) 2019.
 */

package com.tikket.tikketClient;

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
    private JButton bTicketNeu;
    private JButton bTicketAktualisieren;
    private JButton erstellenButton;
    private JButton bVeranstaltungAktualisieren;
    private JButton bVeranstaltungSetzen;
    private JScrollPane spVeranstaltungen;
    private JScrollPane spTickets;
    private JTable tableTickets;
    private JTable tableVeranstaltungen;

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
        //Icon
        URL iconURL = tikketClientGUI.class.getClassLoader().getResource("images/tikket_icon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        JFrame frame = new JFrame("tikketClient");
        frame.setIconImage(icon.getImage()); //Icon einfügen
        frame.setContentPane(this.tikketClientGUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        bTicketAktualisieren.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestartetVon.ticketAusgeben();
            }
        });
        bVeranstaltungAktualisieren.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableVeranstaltungen = createVeranstaltungsTable();
                frame.pack();
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


    private JTable createVeranstaltungsTable() {

        String va = gestartetVon.veranstaltungAusgeben();
        System.out.println(va);
        String[] einzelneStrings = va.split("//");

        String[] ueberschriften = new String[]{"ID", "Name", "Datum", "Ort"};

        //Fügt die Elemente aus dem empfangenem String in ein 2d Array. Das war eine Scheißarbeit xD
        Object[][] veranstaltungsDaten = new Object[einzelneStrings.length - 1][einzelneStrings[0].split("\\*").length];

        for (int i = 0; i < einzelneStrings.length - 1; i++) {

            String[] einzelneBefehle = einzelneStrings[i].split("\\*");
            for (int j = 0; j < einzelneBefehle.length; j++) {
                veranstaltungsDaten[i][j] = einzelneBefehle[j];
//                System.out.println(veranstaltungsDaten[i][j]);
            }
        }

        Class[] columnClass = new Class[]{
                Integer.class, String.class, String.class, String.class
        };

        JTable table = new JTable(veranstaltungsDaten, ueberschriften);
        table.setRowSelectionAllowed(false);
        table.setFillsViewportHeight(true);
        table.setEditingRow(gestartetVon.verantaltungIDausgeben());
        return table;
    }

    private void createUIComponents() {
        tableVeranstaltungen = createVeranstaltungsTable();
    }
}