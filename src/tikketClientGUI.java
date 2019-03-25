/*
 * Informatikprojekt aus 2019. Erstellt von Simon und Max.
 * Zuletzt bearbeitet 25.03.19 23:43.
 * Keiner klaut das hier! Copyright oder so (c) 2019.
 */

import javax.swing.*;
import java.net.URL;

public class tikketClientGUI {
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

    public static void main(String[] args) {
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
        URL iconURL = tikketClientGUI.class.getResource("tikket_icon.png"); //Icon auslesen
        ImageIcon icon = new ImageIcon(iconURL);
        JFrame frame = new JFrame("tikketClient");
        frame.setIconImage(icon.getImage()); //Icon einfügen
        frame.setContentPane(new tikketClientGUI().tikketClientGUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
