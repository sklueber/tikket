import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerStarten {
    private JPanel ServerStarten;
    private JLabel lServerPort;
    private JTextField tServerPort;
    private JButton bStarten;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Server Starten");
        frame.setContentPane(new ServerStarten().ServerStarten);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private int PortLesen() {
        String ausgelesen = tServerPort.getText();
        int ergebnis = Integer.getInteger(ausgelesen);
        System.out.print(ergebnis);
        return ergebnis;
    }

    public ServerStarten() {
        bStarten.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*try {*/
                    //new tikketServer(PortLesen());
                    System.out.print(PortLesen());
                } /*catch (IOException e1) {
                    e1.printStackTrace();
                }*/
        });

        tServerPort.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


}
