import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class VeranstaltungslisteGUI extends JFrame {
    // Anfang Attribute
    private JLabel lVeranstaltungsliste = new JLabel();
    private JTable Veranstaltungsliste = new JTable(5, 5);
    private DefaultTableModel VeranstaltungslisteModel = (DefaultTableModel) Veranstaltungsliste.getModel();
    private JScrollPane VeranstaltungslisteScrollPane = new JScrollPane(Veranstaltungsliste);
    private JButton bHinzufugen = new JButton();
    private JButton bEntfernen = new JButton();
    private JButton bAuswahlen = new JButton();
    private JButton bVeranstaltungsliste = new JButton();
    private JButton bTicketliste = new JButton();
    private JButton bZurruck = new JButton();
    // Ende Attribute

    public VeranstaltungslisteGUI() { 
        // Frame-Initialisierung
        super();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 900; 
        int frameHeight = 891;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("GUI");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);
        // Anfang Komponenten

        lVeranstaltungsliste.setBounds(200, 30, 500, 63);
        lVeranstaltungsliste.setText("Veranstaltungsliste");
        lVeranstaltungsliste.setForeground(Color.WHITE);
        lVeranstaltungsliste.setFont(new Font("Calibri", Font.BOLD, 48));
        lVeranstaltungsliste.setHorizontalAlignment(SwingConstants.CENTER);
        lVeranstaltungsliste.setHorizontalTextPosition(SwingConstants.CENTER);
        cp.add(lVeranstaltungsliste);
        cp.setBackground(new Color(0xC0C0C0));
        VeranstaltungslisteScrollPane.setBounds(70, 144, 750, 190);
        Veranstaltungsliste.setRowHeight(30);
        Veranstaltungsliste.getColumnModel().getColumn(0).setHeaderValue("Name");
        Veranstaltungsliste.getColumnModel().getColumn(1).setHeaderValue("Ort");
        Veranstaltungsliste.getColumnModel().getColumn(2).setHeaderValue("Datum");
        Veranstaltungsliste.getColumnModel().getColumn(3).setHeaderValue("Veranstalter");
        Veranstaltungsliste.getColumnModel().getColumn(4).setHeaderValue("Typ");
        Veranstaltungsliste.setFillsViewportHeight(false);
        Veranstaltungsliste.setBackground(new Color(0xC0C0C0));
        Veranstaltungsliste.setForeground(new Color(0x808040));
        cp.add(VeranstaltungslisteScrollPane);
        bHinzufugen.setBounds(70, 376, 200, 50);
        bHinzufugen.setText("Hinzufügen");
        bHinzufugen.setMargin(new Insets(2, 2, 2, 2));
        bHinzufugen.addActionListener(new ActionListener() 
            { 
                public void actionPerformed(ActionEvent evt) { 
                    bHinzufugen_ActionPerformed(evt);
                }
            }
        );
        bHinzufugen.setFont(new Font("Calibri", Font.PLAIN, 24));
        bHinzufugen.setBorderPainted(false);
        bHinzufugen.setFocusPainted(false);
        bHinzufugen.setContentAreaFilled(true);
        bHinzufugen.setBackground(Color.DARK_GRAY);
        bHinzufugen.setForeground(Color.WHITE);
        cp.add(bHinzufugen);
        bEntfernen.setBounds(342, 376, 200, 50);
        bEntfernen.setText("Entfernen");
        bEntfernen.setMargin(new Insets(2, 2, 2, 2));
        bEntfernen.addActionListener(new ActionListener() 
            { 
                public void actionPerformed(ActionEvent evt) { 
                    bEntfernen_ActionPerformed(evt);
                }
            });
        bEntfernen.setFont(new Font("Calibri", Font.PLAIN, 24));
        bEntfernen.setBorderPainted(false);
        bEntfernen.setFocusPainted(false);
        bEntfernen.setContentAreaFilled(true);
        bEntfernen.setBackground(Color.DARK_GRAY);
        bEntfernen.setForeground(Color.WHITE);
        cp.add(bEntfernen);
        bAuswahlen.setBounds(622, 376, 200, 50);
        bAuswahlen.setText("Auswählen");
        bAuswahlen.setMargin(new Insets(2, 2, 2, 2));
        bAuswahlen.addActionListener(new ActionListener() 
            { 
                public void actionPerformed(ActionEvent evt) { 
                    bAuswahlen_ActionPerformed(evt);
                }
            });
        bAuswahlen.setFont(new Font("Calibri", Font.PLAIN, 24));
        bAuswahlen.setBorderPainted(false);
        bAuswahlen.setFocusPainted(false);
        bAuswahlen.setContentAreaFilled(true);
        bAuswahlen.setBackground(Color.DARK_GRAY);
        bAuswahlen.setForeground(Color.WHITE);
        cp.add(bAuswahlen);
        bVeranstaltungsliste.setBounds(75, 780, 200, 50);
        bVeranstaltungsliste.setText("Veranstaltungsliste");
        bVeranstaltungsliste.setMargin(new Insets(2, 2, 2, 2));
        bVeranstaltungsliste.addActionListener(new ActionListener()
            { 
                public void actionPerformed(ActionEvent evt) { 
                    bVeranstaltungsliste_ActionPerformed(evt);
                }
            });
        bVeranstaltungsliste.setFont(new Font("Calibri", Font.PLAIN, 24));
        bVeranstaltungsliste.setBorderPainted(false);
        bVeranstaltungsliste.setFocusPainted(false);
        bVeranstaltungsliste.setContentAreaFilled(true);
        bVeranstaltungsliste.setBackground(Color.DARK_GRAY);
        bVeranstaltungsliste.setForeground(Color.WHITE);
        cp.add(bVeranstaltungsliste);
        bTicketliste.setBounds(350, 780, 200, 50);
        bTicketliste.setText("Ticketliste");
        bTicketliste.setMargin(new Insets(2, 2, 2, 2));
        bTicketliste.addActionListener(new ActionListener()
            { 
                public void actionPerformed(ActionEvent evt) { 
                    bTicketliste_ActionPerformed(evt);
                }
            });
        bTicketliste.setFont(new Font("Calibri", Font.PLAIN, 24));
        bTicketliste.setBorderPainted(false);
        bTicketliste.setFocusPainted(false);
        bTicketliste.setContentAreaFilled(true);
        bTicketliste.setBackground(Color.DARK_GRAY);
        bTicketliste.setForeground(Color.WHITE);
        cp.add(bTicketliste);
        bZurruck.setBounds(625, 780, 200, 50);
        bZurruck.setText("Zurrück");
        bZurruck.setMargin(new Insets(2, 2, 2, 2));
        bZurruck.addActionListener(new ActionListener() 
            { 
                public void actionPerformed(ActionEvent evt) { 
                    bZurruck_ActionPerformed(evt);
                }
            });
        bZurruck.setFont(new Font("Calibri", Font.PLAIN, 24));
        bZurruck.setBorderPainted(false);
        bZurruck.setFocusPainted(false);
        bZurruck.setContentAreaFilled(true);
        bZurruck.setBackground(Color.DARK_GRAY);
        bZurruck.setForeground(Color.WHITE);
        cp.add(bZurruck);
        Veranstaltungsliste.setBackground(Color.DARK_GRAY);
        cp.setBackground(Color.BLACK);
        // Ende Komponenten

        setVisible(true);
    } // end of public gUI

    // Anfang Methoden

    public static void main(String[] args) {
        new VeranstaltungslisteGUI();
    } // end of main

    public void bHinzufugen_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
        new BestaetigungsGUI();
        //BestaetigungsGUI.labelE();
    } // end of bHinzufugen_ActionPerformed

    public void bEntfernen_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
        new BestaetigungsGUI();
    } // end of bEntfernen_ActionPerformed

    public void bAuswahlen_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen

    } // end of bAuswahlen_ActionPerformed

    public void bVeranstaltungsliste_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen

    } // end of bVeranstaltungsliste_ActionPerformed

    public void bTicketliste_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
        new TicketlisteGUI();
        setVisible(false);
    } // end of bTicketliste_ActionPerformed

    public void bZurruck_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
        new StartseiteGUI();
        setVisible(false);
    } // end of bZurruck_ActionPerformed

    // Ende Methoden
} // end of class gUI