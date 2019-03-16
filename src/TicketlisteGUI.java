import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicketlisteGUI extends JFrame {
    private JLabel lVeranstaltungsliste = new JLabel();
    private JTable Ticketliste = new JTable(5, 4);
    private DefaultTableModel VeranstaltungslisteModel = (DefaultTableModel) Ticketliste.getModel();
    private JScrollPane VeranstaltungslisteScrollPane = new JScrollPane(Ticketliste);
    private JButton bHinzufugen = new JButton();
    private JButton bEntfernen = new JButton();
    private JButton bAuswahlen = new JButton();
    private JButton bVeranstaltungsliste = new JButton();
    private JButton bTicketliste = new JButton();
    private JButton bZurruck = new JButton();
    private BestaetigungsGUI bGUI = new BestaetigungsGUI();

    public TicketlisteGUI() {
        super();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 900; 
        int frameHeight = 900;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("TicketlisteGUI");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);
        
        bGUI.setVisible(false);
        lVeranstaltungsliste.setBounds(200, 30, 500, 63);
        lVeranstaltungsliste.setText("Ticketliste");
        lVeranstaltungsliste.setForeground(Color.WHITE);
        lVeranstaltungsliste.setFont(new Font("Calibri", Font.BOLD, 48));
        lVeranstaltungsliste.setHorizontalAlignment(SwingConstants.CENTER);
        lVeranstaltungsliste.setHorizontalTextPosition(SwingConstants.CENTER);
        cp.add(lVeranstaltungsliste);
        cp.setBackground(new Color(0xC0C0C0));
        VeranstaltungslisteScrollPane.setBounds(70, 144, 750, 190);
        Ticketliste.setRowHeight(30);
        Ticketliste.setFillsViewportHeight(false);
        Ticketliste.setBackground(new Color(0xC0C0C0));
        Ticketliste.setForeground(new Color(0x808040));
        Ticketliste.getColumnModel().getColumn(0).setHeaderValue("Ticket-Nr");
        Ticketliste.getColumnModel().getColumn(1).setHeaderValue("Datum");
        Ticketliste.getColumnModel().getColumn(2).setHeaderValue("E-Mail");
        Ticketliste.getColumnModel().getColumn(3).setHeaderValue("Veranstaltung");
        cp.add(VeranstaltungslisteScrollPane);
        bHinzufugen.setBounds(70, 376, 200, 50);
        bHinzufugen.setText("Hinzufügen");
        bHinzufugen.setMargin(new Insets(2, 2, 2, 2));
        bHinzufugen.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent evt) { 
                    bHinzufugen_ActionPerformed(evt);
                }
            });
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
        bZurruck.setText("Zurrück");
        bZurruck.setMargin(new Insets(2, 2, 2, 2));
        bZurruck.addActionListener(new ActionListener() { 
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
        cp.setBackground(Color.BLACK);
        Ticketliste.setBackground(Color.DARK_GRAY);

        setVisible(true);
    }


    public static void main(String[] args) {
        new TicketlisteGUI();
    } // end of main

    public void bHinzufugen_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
        bGUI.labelH();
        bGUI.setVisible(true);
    }

    public void bEntfernen_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
        bGUI.labelE();
        bGUI.setVisible(true);
    }
    
    public void bAuswahlen_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
        bGUI.labelA();
        bGUI.setVisible(true);
    }

    public void bVeranstaltungsliste_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
        new VeranstaltungslisteGUI();
        setVisible(false);
    }

    public void bTicketliste_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
        new TicketlisteGUI();
        setVisible(false);
    }

    public void bZurruck_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
        new StartseiteGUI();
        setVisible(false);
    }

}