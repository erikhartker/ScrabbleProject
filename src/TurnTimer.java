
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class TurnTimer extends Board{

    JFrame frame;
    int delay = 1000;
    int secs = 0;
    boolean timerOn = true;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TurnTimer window = new TurnTimer();
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
    public TurnTimer() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 250, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("0:00");
        JButton start = new JButton("Start");
        frame.getContentPane().add(start, BorderLayout.PAGE_END);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        frame.getContentPane().add(label, BorderLayout.CENTER);

        JButton btnReset = new JButton("Reset");
        frame.getContentPane().add(btnReset, BorderLayout.NORTH);
        btnReset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                label.setText("0:00");
                secs = 0;

            }
        });



        start.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (timerOn == true) {
                    ActionListener taskPerformer = new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            int mins = secs/60;
                            int seconds = secs%60;
                            String timeSecs = String.valueOf(seconds);
                            String timeMins = String.valueOf(mins);
                            if (seconds<10) {
                                label.setText(timeMins + ":0" + timeSecs);
                            }
                            else {
                                label.setText(timeMins + ":" + timeSecs);
                            }
                            secs++;
                        }
                    };
                    new Timer(delay, taskPerformer).start();
                    timerOn = false;
                }
            }
        });

    }

}

