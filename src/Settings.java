import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Settings{

    public static int[] letterAmounts = new int[27];
    public static int[] letterValues = new int[27];
    String[] letterNames = { "a", "b", "c", "d", "e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","bl" };
    public static int boardSize=15;
    int letterVal=0;
    int letterAmountVal=0;
    JFrame frame;
    private JTextField txtBoardSize;
    private JTextField letterValueTextField;
    private JTextField textField_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Settings window = new Settings();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public Settings() {
        initialize();
    }

    public void setboardSize(int n) {
        boardSize = n;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(250, 250, 210));
        frame.getContentPane().setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        txtBoardSize = new JTextField();
        txtBoardSize.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strBoardSize = txtBoardSize.getText();
                boardSize = Integer.parseInt(strBoardSize);

            }
        });

        txtBoardSize.setBounds(276, 76, 58, 26);
        frame.getContentPane().add(txtBoardSize);

        txtBoardSize.setColumns(10);

        System.out.println(boardSize);

        JLabel lblBoardSize = new JLabel("Board Size");
        lblBoardSize.setBounds(159, 70, 102, 42);
        lblBoardSize.setFont(new Font("Telugu Sangam MN", Font.PLAIN, 16));
        lblBoardSize.setHorizontalAlignment(SwingConstants.LEFT);
        frame.getContentPane().add(lblBoardSize);


        JLabel lblLetterValues = new JLabel("Letter Values");
        lblLetterValues.setBounds(159, 136, 102, 42);
        lblLetterValues.setHorizontalAlignment(SwingConstants.CENTER);
        lblLetterValues.setFont(new Font("Telugu Sangam MN", Font.PLAIN, 16));
        frame.getContentPane().add(lblLetterValues);

        JLabel lblLetterAmounts = new JLabel("Letter Amounts");
        lblLetterAmounts.setBounds(159, 205, 114, 42);
        lblLetterAmounts.setHorizontalAlignment(SwingConstants.CENTER);
        lblLetterAmounts.setFont(new Font("Telugu Sangam MN", Font.PLAIN, 16));
        frame.getContentPane().add(lblLetterAmounts);


        JButton btnGoBack = new JButton("Main Menu");
        btnGoBack.setBackground(Color.WHITE);
        btnGoBack.setBounds(180, 284, 117, 29);
        btnGoBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GUI window = new GUI();
                window.frame.setVisible(true);
                System.out.println(boardSize);
            }
        });
        frame.getContentPane().add(btnGoBack);

        JComboBox comboBox = new JComboBox(letterNames);
        comboBox.setBounds(346, 143, 52, 27);
        frame.getContentPane().add(comboBox);


        JComboBox comboBox_1 = new JComboBox(letterNames);
        comboBox_1.setBounds(346, 212, 52, 27);
        frame.getContentPane().add(comboBox_1);

        letterValueTextField = new JTextField();
        letterValueTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strLetterVal = letterValueTextField.getText();
                letterVal = Integer.parseInt(strLetterVal);
                System.out.println(letterVal);

                comboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String letterSelect = (String)comboBox.getSelectedItem();
                        for (int i = 0; i<=26; i++) {
                            if (letterNames[i]==letterSelect) {
                                letterValues[i]=letterVal;
                            }
                        }

                    }
                });


            }
        });
        letterValueTextField.setColumns(10);
        letterValueTextField.setBounds(276, 142, 58, 26);
        frame.getContentPane().add(letterValueTextField);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(276, 211, 58, 26);
        frame.getContentPane().add(textField_1);


        textField_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strLetterAmount = letterValueTextField.getText();
                letterAmountVal = Integer.parseInt(strLetterAmount);

                comboBox_1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String letterAmountSelect = (String)comboBox_1.getSelectedItem();
                        for (int i = 0; i<=26; i++) {
                            if (letterNames[i]==letterAmountSelect) {
                                letterAmounts[i]=letterAmountVal;
                            }
                        }

                    }
                });

            }
        });




        JButton btnDefaultVals = new JButton("Default Values");
        btnDefaultVals.setBounds(358, 27, 117, 29);
        frame.getContentPane().add(btnDefaultVals);

        btnDefaultVals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SetDefaultValues();

            }
        });


        frame.setBounds(100, 100, 500, 400);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    public void SetDefaultValues() {
        letterAmounts[0]=9;
        letterAmounts[1]=2;
        letterAmounts[2]=2;
        letterAmounts[3]=4;
        letterAmounts[4]=12;
        letterAmounts[5]=2;
        letterAmounts[6]=3;
        letterAmounts[7]=2;
        letterAmounts[8]=9;
        letterAmounts[9]=1;
        letterAmounts[10]=1;
        letterAmounts[11]=4;
        letterAmounts[12]=2;
        letterAmounts[13]=6;
        letterAmounts[14]=8;
        letterAmounts[15]=2;
        letterAmounts[16]=1;
        letterAmounts[17]=6;
        letterAmounts[18]=4;
        letterAmounts[19]=6;
        letterAmounts[20]=4;
        letterAmounts[21]=2;
        letterAmounts[22]=2;
        letterAmounts[23]=1;
        letterAmounts[24]=2;
        letterAmounts[25]=1;
        letterAmounts[26]=2;

        letterValues[0] = 1;
        letterValues[1] = 3;
        letterValues[2] = 3;
        letterValues[3] = 2;
        letterValues[4] = 1;
        letterValues[5] = 4;
        letterValues[6] = 2;
        letterValues[7] = 4;
        letterValues[8] = 1;
        letterValues[9] = 8;
        letterValues[10] = 5;
        letterValues[11] = 1;
        letterValues[12] = 3;
        letterValues[13] = 1;
        letterValues[14] = 1;
        letterValues[15] = 3;
        letterValues[16] = 10;
        letterValues[17] = 1;
        letterValues[18] = 1;
        letterValues[19] = 1;
        letterValues[20] = 1;
        letterValues[21] = 4;
        letterValues[22] = 4;
        letterValues[23] = 8;
        letterValues[24] = 4;
        letterValues[25] = 10;
        letterValues[26] = 0;

        boardSize = 15;

    }

}
