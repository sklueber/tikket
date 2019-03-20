import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BestaetigungsGUI extends JFrame {
    private JLabel lErfolgreichHinzugefugtH = new JLabel();
    private JLabel lErfolgreichHinzugefugtE = new JLabel();
    private JLabel lErfolgreichHinzugefugtA = new JLabel();
    private JButton bOK = new JButton();
    private String H = new String();

    public BestaetigungsGUI() {
        super();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 300; 
        int frameHeight = 200;
        setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        setLocation(x, y);
        setTitle("BestaetigungsGUI");
        setResizable(false);
        Container cp = getContentPane();
        cp.setLayout(null);

        cp.setBackground(Color.BLACK);
        lErfolgreichHinzugefugtH.setBounds(25, 32, 250, 33);
        lErfolgreichHinzugefugtH.setText("Erfolgreich ausgeführt");
        lErfolgreichHinzugefugtH.setFont(new Font("Calibri", Font.BOLD, 24));
        lErfolgreichHinzugefugtH.setForeground(Color.WHITE);
        lErfolgreichHinzugefugtH.setHorizontalAlignment(SwingConstants.CENTER);
        cp.add(lErfolgreichHinzugefugtH);
        lErfolgreichHinzugefugtH.setVisible(true);
        
        bOK.setBounds(112, 100, 74, 35);
        bOK.setText("OK");
        bOK.setMargin(new Insets(2, 2, 2, 2));
        bOK.addActionListener(new ActionListener() { 
                public void actionPerformed(ActionEvent evt) { 
                    bOK_ActionPerformed(evt);
                }
            });
        //bOK.setBackground(new Color(0x404040));
        bOK.setFont(new Font("Calibri", Font.PLAIN, 24));
        bOK.setBorderPainted(false);
        bOK.setFocusPainted(false);
        bOK.setContentAreaFilled(true);
        bOK.setBackground(Color.DARK_GRAY);
        bOK.setForeground(Color.WHITE);
        bOK.setHorizontalTextPosition(SwingConstants.CENTER);
        cp.add(bOK);

        setVisible(true);
    }

    public static void main(String[] args) {
        new BestaetigungsGUI();
    }

    public void bOK_ActionPerformed(ActionEvent evt) {
        // TODO hier Quelltext einfügen
        //setVisible(false);
        dispose();
    }

    public void labelE ()
    {
        lErfolgreichHinzugefugtH.setText("Erfolgreich entfernt");
        // Ende Methoden
    }
    
    public void labelA ()
    {
        lErfolgreichHinzugefugtH.setText("Erfolgreich ausgewählt");
    }
    
    public void labelH ()
    {
        lErfolgreichHinzugefugtH.setText("Erfolgreich hinzugefügt");
    }
}
