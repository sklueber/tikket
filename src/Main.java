import javax.swing.*;

public class Main {
    private JTextField ticketGueltig;
    private JLabel vaNameUeberschrift;
    private JLabel ticketNummer;
    private JLabel ticketTimestamp;
    private JButton ticketErstellen;
    private JLabel statistikVerkauft;
    private JLabel statistikEingelassen;
    private JPanel Startseite;
    private JLabel labelStatistikVerkauft;
    private JLabel labelStatistikEingelassen;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Main");
        frame.setContentPane(new Main().Startseite);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
