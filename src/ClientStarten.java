import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.sun.deploy.uitoolkit.ToolkitStore.dispose;

public class ClientStarten {
    private JButton bVerbinden;
    private JTextField tServerIP;
    private JTextField tServerPort;
    private JPanel ClientStarten;
    private JLabel lServerPort;
    private JLabel lServerIP;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Client Starten");
        frame.setContentPane(new ClientStarten().ClientStarten);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public ClientStarten() {
        bVerbinden.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new tikketClient(tServerIP.getText(), Integer.parseInt(tServerPort.getText()));
                new StartseiteGUI();
            }
        });
    }
}
