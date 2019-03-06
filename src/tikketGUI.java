import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tikketGUI {
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
    private JButton ticketlisteButton;
    private JButton veranstaltungButton;
    private JButton homeButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("tikketGUI");
        frame.setContentPane(new tikketGUI().Startseite);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBackground(Color.blue);
        frame.setVisible(true);

        JButton veranstaltungButton = new JButton("veranstaltungsSeite");
        veranstaltungButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            veranstaltungButton.setBackground(Color.blue); }


        });


}}