/*
 * Informatikprojekt aus 2019. Erstellt von Simon und Max.
 * Zuletzt bearbeitet 03.04.19 04:58 .
 * Keiner klaut das hier! Copyright tikket (c) 2019.
 */

package com.tikket.tikketClient;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
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
    private JLabel lScanEinlass;
    private JTextField tScaninputEinlass;
    private JButton bScanEinlass;
    private JButton bTicketNeu;
    private JButton bTicketAktualisieren;
    private JButton erstellenButton;
    private JButton bVeranstaltungAktualisieren;
    private JButton bVeranstaltungSetzen;
    private JScrollPane spVeranstaltungen;
    private JScrollPane spTickets;
    private JTable tableTickets;
    private JTable tableVeranstaltungen;
    private JButton bTicketsDrucken;
    private JButton bTicketsVersenden;
    private JTextField tMailTickets;
    private JButton bScanAuslass;
    private JTextField tScaninputAuslass;
    private JLabel lScanAuslass;
    private JLabel laktVA;

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
        //Aktuelle VA darstellen
        laktVA.setText("Veranstaltung: " + gestartetVon.veranstaltungNameAusgeben());
        frame.pack();
        frame.setVisible(true);

        bTicketAktualisieren.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableTickets = createTicketsTable();
                spTickets.setViewportView(tableTickets);
                frame.repaint();

            }
        });
        bVeranstaltungAktualisieren.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableVeranstaltungen = createVeranstaltungsTable();
                spVeranstaltungen.setViewportView(tableVeranstaltungen);
                frame.repaint();
            }
        });

        bScanEinlass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tScaninputEinlass.getText().matches("\\d{9}")) {
                    int scan = Integer.parseInt((tScaninputEinlass.getText()));

                    if (gestartetVon.ticketPruefen(scan)) {
                        if (gestartetVon.ticketEinlass(scan)) {
                            bScanEinlass.setForeground(Color.green);
                            tScaninputEinlass.setText("");
                        } else {
                            System.err.println("Ticket gültig, aber nicht erfolgreich eingelassen.");
                        }
                    } else {
                        bScanEinlass.setForeground(Color.red);
                        tScaninputEinlass.setText("");

                    }
                } else {
                    System.err.println("Bitte gültige UUID eingeben");
                    bScanEinlass.setForeground(Color.yellow);
                    tScaninputEinlass.setText("");
                }
                tScaninputEinlass.requestFocus();
            }
        });
        bTicketNeu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gestartetVon.ticketErstellen();
                tableTickets = createTicketsTable();
                spTickets.setViewportView(tableTickets);
                frame.repaint();
            }
        });
        bTicketsVersenden.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tableTickets.getSelectedRow();
                if (tableTickets.getSelectedRows().length != 0 && !tMailTickets.getText().isEmpty()) {
                    String mail = tMailTickets.getText();
                    Object rslt = tableTickets.getValueAt(row, 1);
                    String str = rslt.toString();
                    int uuid = Integer.parseInt(str);
                    gestartetVon.ticketSenden(mail, uuid);
                }
            }
        });
        bTicketsDrucken.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tableTickets.getSelectedRow();
                Object rslt = tableTickets.getValueAt(row, 1);
                String str = rslt.toString();
                CodeGenerator.barcodeErstellen(str);
                Drucker drucker = new Drucker();
                drucker.drucken("Tikket_Barcode.png");
            }
        });
        bScanAuslass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tScaninputAuslass.getText().matches("\\d{9}")) {
                    int scan = Integer.parseInt((tScaninputAuslass.getText()));
                    if (gestartetVon.ticketAuslass(scan)) {
                        bScanAuslass.setForeground(Color.green);
                        tScaninputAuslass.setText("");
                    }
                } else {
                    System.err.println("Bitte gültige UUID eingeben");
                    bScanAuslass.setForeground(Color.yellow);
                    tScaninputAuslass.setText("");
                }
                tScaninputAuslass.requestFocus();
            }
        });
        bVeranstaltungSetzen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tableVeranstaltungen.getSelectedRows().length != 0) {
                    int row = tableVeranstaltungen.getSelectedRow();
                    Object rslt = tableVeranstaltungen.getValueAt(row, 0);
                    String str = rslt.toString();
                    int iID = Integer.parseInt(str);
                    gestartetVon.VeranstaltungSetzen(iID);

                    tableTickets = createTicketsTable();
                    spTickets.setViewportView(tableTickets);
                    frame.repaint();

                    laktVA.setText("Veranstaltung: " + gestartetVon.veranstaltungNameAusgeben());

                    panelStatus.repaint();
                }
            }
        });
    }

    private JTable createTicketsTable() {
        String tkt = gestartetVon.ticketAusgeben();
        String[] einzelneStrings = tkt.split("//");

        String[] ueberschriften = new String[]{"ID", "UUID", "Statuscode"};

        //Fügt die Elemente aus dem empfangenem String in ein 2d Array. Das war eine Scheißarbeit xD
        Object[][] ticketsDaten = new Object[einzelneStrings.length - 1][einzelneStrings[0].split("\\*").length];

        for (int i = 0; i < einzelneStrings.length - 1; i++) {

            String[] einzelneBefehle = einzelneStrings[i].split("\\*");
            for (int j = 0; j < einzelneBefehle.length; j++) {
                ticketsDaten[i][j] = einzelneBefehle[j];
            }
        }

        class TicketModel extends AbstractTableModel {
            public int getRowCount() {
                return ticketsDaten.length;
            }

            public int getColumnCount() {
                return ueberschriften.length;
            }

            public Object getValueAt(int row, int column) {
                return ticketsDaten[row][column];
            }

            public String getColumnName(int column) {
                return ueberschriften[column];
            }

            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        }

        JTable table = new JTable(new TicketModel());
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(true);
        table.setFillsViewportHeight(true);
        return table;
    }

    private JTable createVeranstaltungsTable() {
        String va = gestartetVon.veranstaltungAusgeben();
        String[] einzelneStrings = va.split("//");

        String[] ueberschriften = new String[]{"ID", "Name", "Datum", "Ort"};

        //Fügt die Elemente aus dem empfangenem String in ein 2d Array. Das war eine Scheißarbeit xD
        Object[][] veranstaltungsDaten = new Object[einzelneStrings.length - 1][einzelneStrings[0].split("\\*").length];

        for (int i = 0; i < einzelneStrings.length - 1; i++) {

            String[] einzelneBefehle = einzelneStrings[i].split("\\*");
            for (int j = 0; j < einzelneBefehle.length; j++) {
                veranstaltungsDaten[i][j] = einzelneBefehle[j];
            }
        }

        class VeranstaltungsModel extends AbstractTableModel {
            public int getRowCount() {
                return veranstaltungsDaten.length;
            }

            public int getColumnCount() {
                return ueberschriften.length;
            }

            public Object getValueAt(int row, int column) {
                return veranstaltungsDaten[row][column];
            }

            public String getColumnName(int column) {
                return ueberschriften[column];
            }

            public Class getColumnClass(int c) {
                return getValueAt(0, c).getClass();
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        }


        JTable table = new JTable(new VeranstaltungsModel());
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setCellSelectionEnabled(false);
        table.setRowSelectionAllowed(true);
        table.setFillsViewportHeight(true);
        return table;
    }

    private void createUIComponents() {
        tableVeranstaltungen = createVeranstaltungsTable();
        tableTickets = createTicketsTable();
    }
}