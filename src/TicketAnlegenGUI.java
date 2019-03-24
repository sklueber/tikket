/*
 * Informatikprojekt aus 2019. Erstellt von Simon, Max, Nico.
 * Zuletzt bearbeitet 24.03.19 23:16.
 * Keiner klaut das hier! (c) 2019.
 */

/*
 * Informatikprojekt aus 2019. Erstellt von Simon, Max, Nico am 24.03.19 22:28.
 * Zuletzt bearbeitet 24.03.19 21:33.
 * Keiner klaut das hier! (c) 2019.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketAnlegenGUI extends JFrame {
    private JButton bDruckenundSenden = new JButton();
    private JButton bSenden = new JButton();
    private JTextField jTextField1 = new JTextField();
    private JLabel lTicketVorschau = new JLabel();
    private JLabel lTicketVorschau1 = new JLabel();
    private JButton bAnlegen = new JButton();
    private JButton bVeranstaltungsliste = new JButton();
    private JButton bTicketliste = new JButton();
    private JButton bZurruck = new JButton();

    public TicketAnlegenGUI() {
        super();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        int frameWidth = 900;
        int frameHeight = 900;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("tikket | Ticket erstellen");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);

        bDruckenundSenden.setBounds(150, 40, 225, 50);
        bDruckenundSenden.setText("Drucken und Senden");
        bDruckenundSenden.setMargin(new Insets(2, 2, 2, 2));
        bDruckenundSenden.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bDruckenundSenden_ActionPerformed(evt);
            }
        });
        bDruckenundSenden.setFont(new Font("Calibri", Font.PLAIN, 24));
        bDruckenundSenden.setBorderPainted(false);
        bDruckenundSenden.setFocusPainted(false);
        bDruckenundSenden.setContentAreaFilled(true);
        bDruckenundSenden.setBackground(Color.DARK_GRAY);
        bDruckenundSenden.setForeground(Color.WHITE);
        cp.add(bDruckenundSenden);
        bSenden.setBounds(550, 40, 225, 50);
        bSenden.setText("Senden");
        bSenden.setMargin(new Insets(2, 2, 2, 2));
        bSenden.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bSenden_ActionPerformed(evt);
            }
        });
        bSenden.setFont(new Font("Calibri", Font.PLAIN, 24));
        bSenden.setBorderPainted(false);
        bSenden.setFocusPainted(false);
        bSenden.setContentAreaFilled(true);
        bSenden.setBackground(Color.DARK_GRAY);
        bSenden.setForeground(Color.WHITE);
        cp.add(bSenden);
        jTextField1.setBounds(200, 112, 500, 76);
        jTextField1.setToolTipText("(E-Mail)");
        cp.add(jTextField1);
        lTicketVorschau.setBounds(200, 200, 500, 63);
        lTicketVorschau.setText("Ticket-Vorschau");
        lTicketVorschau.setHorizontalAlignment(SwingConstants.CENTER);
        lTicketVorschau.setFont(new Font("Calibri", Font.BOLD, 48));
        lTicketVorschau.setForeground(Color.WHITE);
        cp.add(lTicketVorschau);
        lTicketVorschau1.setBounds(120, 288, 660, 330);
        lTicketVorschau1.setText("(Ticket-Vorschau)");
        lTicketVorschau1.setHorizontalAlignment(SwingConstants.CENTER);
        lTicketVorschau1.setBackground(Color.DARK_GRAY);
        lTicketVorschau1.setOpaque(true);
        lTicketVorschau1.setFont(new Font("Dialog", Font.BOLD, 12));
        lTicketVorschau1.setForeground(new Color(0xC0C0C0));
        cp.add(lTicketVorschau1);
        bAnlegen.setBounds(337, 632, 226, 50);
        bAnlegen.setText("Anlegen");
        bAnlegen.setMargin(new Insets(2, 2, 2, 2));
        bAnlegen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bAnlegen_ActionPerformed(evt);
            }
        });
        bAnlegen.setFont(new Font("Calibri", Font.PLAIN, 24));
        bAnlegen.setBorderPainted(false);
        bAnlegen.setFocusPainted(false);
        bAnlegen.setContentAreaFilled(true);
        bAnlegen.setBackground(Color.DARK_GRAY);
        bAnlegen.setForeground(Color.WHITE);
        cp.add(bAnlegen);

        bVeranstaltungsliste.setBounds(75, 780, 200, 50);
        bVeranstaltungsliste.setText("Veranstaltungsliste");
        bVeranstaltungsliste.setMargin(new Insets(2, 2, 2, 2));
        bVeranstaltungsliste.addActionListener(new ActionListener() {
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
        bZurruck.setBounds(625, 780, 200, 50);
        bZurruck.setText("Zurück");
        bZurruck.setMargin(new Insets(2, 2, 2, 2));
        bZurruck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                bZurueckActionPerformed(evt);
            }
        });
        bZurruck.setFont(new Font("Calibri", Font.PLAIN, 24));
        bZurruck.setBorderPainted(false);
        bZurruck.setFocusPainted(false);
        bZurruck.setContentAreaFilled(true);
        bZurruck.setBackground(Color.DARK_GRAY);
        bZurruck.setForeground(Color.WHITE);
        cp.add(bZurruck);
        cp.setBackground(Color.BLACK);

        setVisible(true);
    }

    public static void main(String[] args) {
        new TicketAnlegenGUI();
    } // end of main

    public void bDruckenundSenden_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
        new BestaetigungsGUI();
    }

    public void bSenden_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
        new BestaetigungsGUI();
    }

    public void bAnlegen_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
        new BestaetigungsGUI();
    }

    public void bVeranstaltungsliste_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
        dispose();
    }

    public void bTicketliste_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
        new TicketlisteGUI();
        dispose();
    }

    public void bZurueckActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
        dispose();
    }

}
