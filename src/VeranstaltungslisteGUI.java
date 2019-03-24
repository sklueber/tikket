/*
 * Informatikprojekt aus 2019. Erstellt von Simon, Max, Nico.
 * Zuletzt bearbeitet 24.03.19 23:16.
 * Keiner klaut das hier! (c) 2019.
 */

/*
 * Informatikprojekt aus 2019. Erstellt von Simon, Max, Nico am 24.03.19 22:28.
 * Zuletzt bearbeitet 24.03.19 21:57.
 * Keiner klaut das hier! (c) 2019.
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VeranstaltungslisteGUI extends JFrame {
    private JLabel lVeranstaltungsliste = new JLabel();
    private JTable Veranstaltungsliste = new JTable(5, 5);
    private DefaultTableModel VeranstaltungslisteModel = (DefaultTableModel) Veranstaltungsliste.getModel();
    private JScrollPane VeranstaltungslisteScrollPane = new JScrollPane(Veranstaltungsliste);
    private JButton bHinzufugen = new JButton();
    private JButton bEntfernen = new JButton();
    private JButton bAuswahlen = new JButton();
    private JButton bTicketliste = new JButton();
    private JButton bZurueck = new JButton();
    private BestaetigungsGUI bGUI = new BestaetigungsGUI();

    private tikketClient client;

    public VeranstaltungslisteGUI(tikketClient gestartetVon) {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int frameWidth = 900;
        int frameHeight = 891;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("tikket | Veranstaltungsübersicht");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);

        bGUI.setVisible(false);
        lVeranstaltungsliste.setBounds(200, 30, 500, 63);
        lVeranstaltungsliste.setText("Veranstaltungsübersicht");
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
        Veranstaltungsliste.setFillsViewportHeight(false);
        Veranstaltungsliste.setBackground(new Color(0xC0C0C0));
        Veranstaltungsliste.setForeground(new Color(0x808040));
        cp.add(VeranstaltungslisteScrollPane);

        bHinzufugen.setBounds(70, 376, 200, 50);
        bHinzufugen.setText("Hinzufügen");
        bHinzufugen.setMargin(new Insets(2, 2, 2, 2));
        bHinzufugen.addActionListener(new ActionListener() {
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
        bEntfernen.addActionListener(new ActionListener() {
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
        bAuswahlen.addActionListener(new ActionListener() {
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

        bTicketliste.setBounds(350, 780, 200, 50);
        bTicketliste.setText("Ticketliste");
        bTicketliste.setMargin(new Insets(2, 2, 2, 2));
        bTicketliste.addActionListener(new ActionListener() {
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
        bZurueck.setBounds(625, 780, 200, 50);
        bZurueck.setText("Zurück");
        bZurueck.setMargin(new Insets(2, 2, 2, 2));
        bZurueck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bZurueck_ActionPerformed(evt);
            }
        });
        bZurueck.setFont(new Font("Calibri", Font.PLAIN, 24));
        bZurueck.setBorderPainted(false);
        bZurueck.setFocusPainted(false);
        bZurueck.setContentAreaFilled(true);
        bZurueck.setBackground(Color.DARK_GRAY);
        bZurueck.setForeground(Color.WHITE);
        cp.add(bZurueck);
        Veranstaltungsliste.setBackground(Color.DARK_GRAY);
        cp.setBackground(Color.BLACK);
        setVisible(true);
    }

    public void bHinzufugen_ActionPerformed(ActionEvent evt) {
        client.veranstaltungAusgeben();
//        bGUI.labelH();
//        bGUI.setVisible(true);
    }

    public void bEntfernen_ActionPerformed(ActionEvent evt) {
        bGUI.labelE();
        bGUI.setVisible(true);
    }

    public void bAuswahlen_ActionPerformed(ActionEvent evt) {
        bGUI.labelA();
        bGUI.setVisible(true);
    }

    public void bVeranstaltungsliste_ActionPerformed(ActionEvent evt) {
        dispose();
    }

    public void bTicketliste_ActionPerformed(ActionEvent evt) {
        new TicketlisteGUI();
    }

    public void bZurueck_ActionPerformed(ActionEvent evt) {
        dispose();
    }

}