/*
 * Informatikprojekt aus 2019. Erstellt von Simon und Max.
 * Zuletzt bearbeitet 03.04.19 05:45 .
 * Keiner klaut das hier! Copyright tikket (c) 2019.
 */

package com.tikket.tikketClient;

import javax.swing.*;
import java.awt.event.*;
import java.net.URL;

public class veranstaltungErstellen extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel lTitle;
    private JTextField tName;
    private JTextField tOrt;
    private JLabel lName;
    private JLabel lDatum;
    private JLabel lOrt;
    private JTextField tDatum;

    public tikketClient gestartetVon;

    public veranstaltungErstellen() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        gestartetVon.VeranstaltungErstellen(tName.getText(), tDatum.getText(), tOrt.getText(), 1);
        dispose();
    }

    private void onCancel() {
        this.dispose();
    }

    public static void main(tikketClient[] args) {
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
        URL iconURL = tikketClientGUI.class.getClassLoader().getResource("images/tikket_icon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        veranstaltungErstellen dialog = new veranstaltungErstellen();
        dialog.gestartetVon = args[0];
        dialog.setIconImage(icon.getImage()); //Icon einfügen
        dialog.setTitle("tikketClient | Veranstaltung erstellen");
        dialog.pack();
        dialog.setVisible(true);
    }
}
