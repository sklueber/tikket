import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientStarten {
    public ClientStarten() {
        bVerbinden.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Client Starten");
        frame.setContentPane(new ClientStarten().ClientStarten);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    private JButton bVerbinden;
    private JTextField tServerIP;
    private JTextField tServerPort;
    private JPanel ClientStarten;
}
