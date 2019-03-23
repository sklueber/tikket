import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartseiteGUI extends JFrame {
    private JLabel lVeranstaltungsname = new JLabel();
    private JLabel lUngultigRotGultigGrun = new JLabel();
    private JLabel lTicketID = new JLabel();
    private JLabel lZeitstempel = new JLabel();
    private JButton bTicketanlegen = new JButton();
    private JLabel lBesucherzahlen = new JLabel();
    private JLabel lAngelegteTickets1 = new JLabel();
    private JLabel jNumberField1 = new JLabel();
    private JLabel jNumberField2 = new JLabel();
    private JButton bVeranstaltungsliste = new JButton();
    private JButton bTicketliste = new JButton();
    private JButton bZurueck = new JButton();

    public StartseiteGUI() {
        super();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(900, 900);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("tikket | Einlass");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);

        lVeranstaltungsname.setBounds(200, 30, 500, 63);
        lVeranstaltungsname.setText("~Veranstaltungsname~");
        lVeranstaltungsname.setHorizontalAlignment(SwingConstants.CENTER);
        lVeranstaltungsname.setFont(new Font("Calibri", Font.BOLD, 48));
        lVeranstaltungsname.setForeground(Color.WHITE);
        cp.add(lVeranstaltungsname);

        lUngultigRotGultigGrun.setBounds(275, 120, 350, 100);
        lUngultigRotGultigGrun.setText("Ungültig = Rot // Gültig = Grün");
        lUngultigRotGultigGrun.setFont(new Font("Calibri", Font.BOLD, 20));
        lUngultigRotGultigGrun.setHorizontalAlignment(SwingConstants.CENTER);
        lUngultigRotGultigGrun.setBackground(Color.RED);
        lUngultigRotGultigGrun.setOpaque(true);
        lUngultigRotGultigGrun.setForeground(Color.WHITE);
        cp.add(lUngultigRotGultigGrun);

        lTicketID.setBounds(200, 248, 230, 40);
        lTicketID.setText("  Ticket-ID: ");
        lTicketID.setFont(new Font("Calibri", Font.PLAIN, 24));
        lTicketID.setForeground(Color.WHITE);
        cp.add(lTicketID);

        lZeitstempel.setBounds(470, 248, 230, 40);
        lZeitstempel.setFont(new Font("Calibri", Font.PLAIN, 24));
        lZeitstempel.setText("  Zeitstempel: ");
        lZeitstempel.setForeground(Color.WHITE);
        cp.add(lZeitstempel);

        bTicketanlegen.setBounds(275, 320, 350, 100);
        bTicketanlegen.setText("Ticket anlegen");
        bTicketanlegen.setMargin(new Insets(2, 2, 2, 2));
        bTicketanlegen.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent evt) { 
                    bTicketanlegen_ActionPerformed(evt);
                }
            });
        bTicketanlegen.setFont(new Font("Calibri", Font.BOLD, 48));
        bTicketanlegen.setBorderPainted(false);
        bTicketanlegen.setFocusPainted(false);
        bTicketanlegen.setContentAreaFilled(true);
        bTicketanlegen.setBackground(Color.DARK_GRAY);
        bTicketanlegen.setForeground(Color.WHITE);
        cp.add(bTicketanlegen);
        lBesucherzahlen.setBounds(200, 448, 200, 40);
        lBesucherzahlen.setText("Besucherzahlen:");
        lBesucherzahlen.setFont(new Font("Calibri", Font.PLAIN, 24));
        lBesucherzahlen.setForeground(Color.WHITE);
        cp.add(lBesucherzahlen);
        lAngelegteTickets1.setBounds(200, 512, 200, 40);
        lAngelegteTickets1.setText("Angelegte Tickets:");
        lAngelegteTickets1.setFont(new Font("Calibri", Font.PLAIN, 24));
        lAngelegteTickets1.setForeground(Color.WHITE);
        cp.add(lAngelegteTickets1);
        jNumberField1.setBounds(430, 448, 270, 40);
        jNumberField1.setFont(new Font("Calibri", Font.PLAIN, 24));
        jNumberField1.setText("0");
        jNumberField1.setForeground(Color.WHITE);
        cp.add(jNumberField1);
        jNumberField2.setBounds(430, 512, 270, 40);
        jNumberField2.setFont(new Font("Calibri", Font.PLAIN, 24));
        jNumberField2.setText("0");
        jNumberField2.setForeground(Color.WHITE);
        cp.add(jNumberField2);
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
        cp.setBackground(Color.BLACK);

        setVisible(true);
    }

    public static void main(String[] args) {
        new StartseiteGUI();
    } // end of main

    private void bTicketanlegen_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
        new TicketAnlegenGUI();
        //setVisible(false);
        dispose();
    }

    private void bVeranstaltungsliste_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
        new VeranstaltungslisteGUI();
        //setVisible(false);
        dispose();
    }

    private void bTicketliste_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
        new TicketlisteGUI();
        //setVisible(false);
        dispose();
    }

    private void bZurueck_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen

    }

}
