
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;

import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class Scoreboard extends Board{

    JFrame frame;
    int scoreAmount = turnTotals.size();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Scoreboard window = new Scoreboard();
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
    public Scoreboard() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame = new JFrame();
        frame.setBounds(100, 100, 450, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);


        JButton btnUpdate = new JButton("Update");
        menuBar.add(btnUpdate);


        frame.getContentPane().setLayout(new GridLayout(0,4));
        JLabel player1 = new JLabel("Player 1 Score");
        player1.setHorizontalAlignment(SwingConstants.CENTER);
        player1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.getContentPane().add(player1);
        JLabel player2 = new JLabel("Player 2 Score");
        player2.setHorizontalAlignment(SwingConstants.CENTER);
        player2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.getContentPane().add(player2);
        JLabel player3 = new JLabel("Player 3 Score");
        player3.setHorizontalAlignment(SwingConstants.CENTER);
        player3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.getContentPane().add(player3);
        JLabel player4 = new JLabel("Player 4 Score");
        player4.setHorizontalAlignment(SwingConstants.CENTER);
        player4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.getContentPane().add(player4);


        btnUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(scoreAmount);
                System.out.println(turnTotals.size());
                if (scoreAmount != turnTotals.size()) {
                    for (int gridx = scoreAmount; gridx < turnTotals.size(); gridx++) {
                        JLabel Score = new JLabel(String.valueOf(turnTotals.get(gridx)));
                        Score.setHorizontalAlignment(SwingConstants.CENTER);
                        Score.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                        frame.getContentPane().add(Score);
                    }
                }
                SwingUtilities.updateComponentTreeUI(frame);
                scoreAmount = turnTotals.size();

            }
        });

        for (int gridx = 0; gridx < turnTotals.size(); gridx++) {
            JLabel Score = new JLabel(String.valueOf(turnTotals.get(gridx)));
            Score.setHorizontalAlignment(SwingConstants.CENTER);
            Score.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            frame.getContentPane().add(Score);
        }




    }

}
