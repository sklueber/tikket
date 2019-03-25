/*
 * Informatikprojekt aus 2019. Erstellt von Simon, und Max.
 * Zuletzt bearbeitet 25.03.19 08:25.
 * Keiner klaut das hier! (c) 2019.
 */

import javax.swing.*;

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
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException e) {
        e.printStackTrace();
        } catch (InstantiationException e) {
        e.printStackTrace();
        } catch (IllegalAccessException e) {
        e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
        e.printStackTrace();
        }

        JFrame frame = new JFrame("tikketClientGUI");
        frame.setContentPane(new tikketClientGUI().tikketClientGUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
    }
}
