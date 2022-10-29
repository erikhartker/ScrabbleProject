
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI extends Settings{

    JFrame frame;


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI window = new GUI();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GUI() {
        initialize();
    }

    public int getboardSize() {
        return boardSize;
    }
    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {


        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(176, 224, 230));
        frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);


        System.out.println(super.boardSize);
        System.out.println(letterValues[0]);
        System.out.println(letterVal);

        JButton btnSettings = new JButton("Settings");
        btnSettings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Settings window = new Settings();
                window.frame.setVisible(true);
            }
        });
        btnSettings.setBounds(178, 138, 143, 39);
        frame.getContentPane().add(btnSettings);

        JButton btnPlayGame = new JButton("Play Game");
        btnPlayGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Board window = new Board();
                window.frame.setVisible(true);
            }
        });
        btnPlayGame.setBackground(new Color(255, 255, 255));

        btnPlayGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        btnPlayGame.setBounds(178, 194, 143, 39);
        frame.getContentPane().add(btnPlayGame);

        JLabel lblNewLabel = new JLabel("Menu");
        lblNewLabel.setFont(new Font("Bangla MN", Font.PLAIN, 20));
        lblNewLabel.setBounds(218, 16, 64, 52);
        frame.getContentPane().add(lblNewLabel);
    }
}